package cn.alphahub.mall.product.service.impl;

import cn.alphahub.common.core.domain.BaseResult;
import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.common.to.SkuReductionTO;
import cn.alphahub.mall.coupon.domain.SpuBounds;
import cn.alphahub.mall.product.domain.Attr;
import cn.alphahub.mall.product.domain.ProductAttrValue;
import cn.alphahub.mall.product.domain.SkuImages;
import cn.alphahub.mall.product.domain.SkuInfo;
import cn.alphahub.mall.product.domain.SkuSaleAttrValue;
import cn.alphahub.mall.product.domain.SpuInfo;
import cn.alphahub.mall.product.domain.SpuInfoDesc;
import cn.alphahub.mall.product.feign.SkuFullReductionClient;
import cn.alphahub.mall.product.feign.SpuBoundsClient;
import cn.alphahub.mall.product.mapper.SpuInfoMapper;
import cn.alphahub.mall.product.service.*;
import cn.alphahub.mall.product.vo.BaseAttrs;
import cn.alphahub.mall.product.vo.Bounds;
import cn.alphahub.mall.product.vo.Images;
import cn.alphahub.mall.product.vo.Skus;
import cn.alphahub.mall.product.vo.SpuSaveVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * spu信息Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-07 22:46:24
 */
@Slf4j
@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo> implements SpuInfoService {

    @Resource
    private SpuInfoDescService spuInfoDescService;
    @Resource
    private SpuImagesService spuImagesService;
    @Resource
    private AttrService attrService;
    @Resource
    private ProductAttrValueService productAttrValueService;
    @Resource
    private SkuInfoService skuInfoService;
    @Resource
    private SkuImagesService skuImagesService;
    @Resource
    private SkuSaleAttrValueService skuSaleAttrValueService;
    @Resource
    private SpuBoundsClient spuBoundsClient;
    @Resource
    private SkuFullReductionClient skuFullReductionClient;

    /**
     * 查询spu信息分页列表
     *
     * @param pageDomain 分页数据
     * @param spuInfo    分页对象
     * @return spu信息分页数据
     */
    @Override
    public PageResult<SpuInfo> queryPage(PageDomain pageDomain, SpuInfo spuInfo) {
        pageDomain.startPage();
        QueryWrapper<SpuInfo> wrapper = new QueryWrapper<>(spuInfo);
        List<SpuInfo> list = this.list(wrapper);
        PageInfo<SpuInfo> pageInfo = new PageInfo<>(list);
        return PageResult.<SpuInfo>builder()
                .totalCount(pageInfo.getTotal())
                .totalPage((long) pageInfo.getPages())
                .items(pageInfo.getList())
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSpuInfo(SpuSaveVO vo) {

        // 1、保存spu基本信息 pms_spu_info
        SpuInfo spuInfo = new SpuInfo();
        BeanUtils.copyProperties(vo, spuInfo);
        Date currTime = new Date();
        spuInfo.setCreateTime(currTime);
        // 保存spuInfo属性
        baseMapper.insert(spuInfo);

        // 2、保存Spu的描述图片 pms_spu_info_desc
        List<String> decryptList = vo.getDecript();
        SpuInfoDesc spuInfoDesc = new SpuInfoDesc();
        Long spuInfoId = spuInfo.getId();
        spuInfoDesc.setSpuId(spuInfoId);
        spuInfoDesc.setDecript(String.join(",", decryptList));
        spuInfoDescService.saveSpuInfoDesc(spuInfoDesc);

        // 3、保存spu的图片集 pms_spu_image
        List<String> images = vo.getImages();
        spuImagesService.saveBatch(spuInfoId, images);

        // 4、保存spu的规格参数;pms_product_attr_value
        List<BaseAttrs> baseAttrs = vo.getBaseAttrs();
        List<ProductAttrValue> productAttrValues = baseAttrs.stream().map(
                attr -> {
                    Attr byId = attrService.getById(attr.getAttrId());
                    return ProductAttrValue.builder()
                            .attrId(attr.getAttrId())
                            .attrName(Objects.nonNull(byId) ? byId.getAttrName() : "")
                            .attrValue(attr.getAttrValues())
                            .quickShow(attr.getShowDesc())
                            .spuId(spuInfoId)
                            .build();
                }
        ).collect(Collectors.toList());
        productAttrValueService.saveProductAttrValues(productAttrValues);

        // 5、保存spu的积分信息；lejing_sms -> sms_spu_bounds
        // 5、保存当前spu对应的所有sku信息:
        List<Skus> skusList = vo.getSkus();
        if (CollectionUtils.isNotEmpty(skusList)) {

            skusList.forEach(sku -> {
                List<Images> imagesList = sku.getImages();
                String defaultImg = "";
                for (Images img : imagesList) {
                    if (Objects.equals(img.getDefaultImg(), 1)) {
                        defaultImg = img.getImgUrl();
                    }
                }
                SkuInfo skuInfo = SkuInfo.builder().build();
                BeanUtils.copyProperties(sku, skuInfo);
                skuInfo.setSpuId(spuInfo.getId());
                skuInfo.setCatalogId(spuInfo.getCatalogId());
                skuInfo.setBrandId(spuInfo.getBrandId());
                skuInfo.setSkuDefaultImg(defaultImg);
                skuInfo.setSaleCount(0L);

                // 5.1）、sku的基本信息；pms_sku_info
                skuInfoService.save(skuInfo);

                Long skuId = skuInfo.getSkuId();
                List<Images> skuImages = sku.getImages();
                List<SkuImages> skuImagesList = skuImages.stream().map(img -> SkuImages.builder()
                        .skuId(skuId)
                        .defaultImg(img.getDefaultImg())
                        .imgUrl(img.getImgUrl())
                        .build()).collect(Collectors.toList()

                );
                // 5.2）、sku的图片信息；pms_sku_image
                skuImagesService.saveBatch(skuImagesList);

                // 5.3）、sku的销售属性信息：pms_sku_sale_attr_value
                List<cn.alphahub.mall.product.vo.Attr> attr = sku.getAttr();
                List<SkuSaleAttrValue> skuSaleAttrValues = attr.stream().map(a -> {
                    SkuSaleAttrValue saleAttrValue = new SkuSaleAttrValue();
                    BeanUtils.copyProperties(a, saleAttrValue);
                    saleAttrValue.setSkuId(skuId);
                    return saleAttrValue;
                }).collect(Collectors.toList());
                skuSaleAttrValueService.saveBatch(skuSaleAttrValues);

                // 5.4）、sku的优惠、满减等信息；lejing_sms -> sms_spu_bounds|sms_sku_ladder|sms_sku_full_reduction|sms_member_price
                Bounds bounds = vo.getBounds();
                SpuBounds spuBounds = new SpuBounds();
                BeanUtils.copyProperties(bounds, spuBounds);
                spuBounds.setSpuId(spuInfoId);
                BaseResult<Boolean> save = spuBoundsClient.save(spuBounds);
                if (save.getData()) {
                    log.info("{}", "远程保存商品spu积分成功");
                } else {
                    log.warn("{}", "远程保存商品spu积分失败");
                }
                SkuReductionTO skuReductionTo = new SkuReductionTO();
                skuReductionTo.setSkuId(skuId);
                BeanUtils.copyProperties(vo, skuReductionTo);
                BaseResult<Boolean> baseResult = skuFullReductionClient.saveSkuReduction(skuReductionTo);
                if (baseResult.getData()) {
                    log.info("{}", "远程保存sku成功");
                } else {
                    log.warn("{}", "远程保存sku失败");
                }
            });
        }
    }

}
