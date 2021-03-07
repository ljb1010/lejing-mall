package cn.alphahub.mall.product.service.impl;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.SkuInfo;
import cn.alphahub.mall.product.mapper.SkuInfoMapper;
import cn.alphahub.mall.product.service.SkuInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * sku信息Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:36:31
 */
@Slf4j
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements SkuInfoService {

    /**
     * 查询sku信息分页列表
     *
     * @param pageDomain 分页数据
     * @param skuInfo    分页对象
     * @return sku信息分页数据
     */
    @Override
    public PageResult<SkuInfo> queryPage(PageDomain pageDomain, SkuInfo skuInfo) {
        // 1. 构造mybatis-plus查询wrapper
        QueryWrapper<SkuInfo> wrapper = new QueryWrapper<>(skuInfo);
        // 2. 创建一个分页对象
        PageResult<SkuInfo> pageResult = new PageResult<>();
        // 3. 开始分页
        pageResult.startPage(pageDomain);
        // 4. 执行Dao|Mapper SQL查询
        List<SkuInfo> skuInfoList = this.list(wrapper);
        // 5. 分装并返回数据
        return pageResult.getPage(skuInfoList);
    }

    @Override
    public PageResult<SkuInfo> queryPage(PageDomain pageDomain, String key, Long catelogId, Long brandId, Long min, Long max) {
        log.info("参数列表:key->{},catelogId->{},brandId->{},min->{},max->{}", key, catelogId, brandId, min, max);
        QueryWrapper<SkuInfo> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(key)) {
            wrapper.lambda().and(w -> w.eq(SkuInfo::getSkuId, key).or().like(SkuInfo::getSkuName, key));
        }
        if (ObjectUtils.isNotEmpty(catelogId) && catelogId != 0) {
            wrapper.lambda().eq(SkuInfo::getCatalogId, catelogId);
        }
        if (ObjectUtils.isNotEmpty(brandId) && brandId != 0) {
            wrapper.lambda().eq(SkuInfo::getBrandId, brandId);
        }

        if (ObjectUtils.isNotEmpty(min)) {
            try {
                if (new BigDecimal(min).compareTo(BigDecimal.ZERO) > 0) {
                    wrapper.lambda().ge(SkuInfo::getPrice, min);
                }
            } catch (Exception e) {
                log.error("输入min数值不规范:{},{}", e.getMessage(), e);
            }
        }

        if (ObjectUtils.isNotEmpty(max)) {
            try {
                if (new BigDecimal(max).compareTo(BigDecimal.ZERO) > 0) {
                    wrapper.lambda().le(SkuInfo::getPrice, max);
                }
            } catch (Exception e) {
                log.error("输入max数值不规范:{},{}", e.getMessage(), e);
            }
        }

        PageResult<SkuInfo> pageResult = new PageResult<>();
        pageResult.startPage(pageDomain);
        List<SkuInfo> skuInfoList = this.list(wrapper);
        return pageResult.getPage(skuInfoList);
    }

    /**
     * 根据spuId查出对应的所有sku信息，品牌名字
     *
     * @param spuId spuId
     * @return 所有sku信息
     */
    @Override
    public List<SkuInfo> getSkusBySpuId(Long spuId) {
        QueryWrapper<SkuInfo> queryWrapper = new QueryWrapper<>();
        return this.list(queryWrapper.lambda().eq(SkuInfo::getSpuId, spuId));
    }

}
