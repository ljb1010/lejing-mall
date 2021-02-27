package cn.alphahub.mall.ware.service.impl;

import cn.alphahub.common.core.domain.BaseResult;
import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.SkuInfo;
import cn.alphahub.mall.ware.domain.WareSku;
import cn.alphahub.mall.ware.feign.SkuInfoClient;
import cn.alphahub.mall.ware.mapper.WareSkuMapper;
import cn.alphahub.mall.ware.service.WareSkuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品库存Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:19:57
 */
@Slf4j
@Service
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper, WareSku> implements WareSkuService {
    @Resource
    private WareSkuMapper wareSkuMapper;
    @Resource
    private SkuInfoClient skuInfoClient;

    /**
     * 查询商品库存分页列表
     *
     * @param pageDomain 分页数据
     * @param wareSku    分页对象
     * @return 商品库存分页数据
     */
    @Override
    public PageResult<WareSku> queryPage(PageDomain pageDomain, WareSku wareSku) {
        // 1. 构造mybatis-plus查询wrapper
        QueryWrapper<WareSku> wrapper = new QueryWrapper<>(wareSku);
        // 2. 创建一个分页对象
        PageResult<WareSku> pageResult = new PageResult<>();
        // 3. 开始分页
        pageResult.startPage(pageDomain);
        // 4. 执行Dao|Mapper SQL查询
        List<WareSku> wareSkuList = this.list(wrapper);
        // 5. 分装并返回数据
        return pageResult.getPage(wareSkuList);
    }

    /**
     * 更新库存信息
     *
     * @param skuId  产品skuId
     * @param wareId 库存id
     * @param skuNum 添加的库存量
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addStock(Long skuId, Long wareId, Integer skuNum) {
        // 没有就新增，存下就更新
        QueryWrapper<WareSku> skuQueryWrapper = new QueryWrapper<>();
        skuQueryWrapper.lambda().eq(WareSku::getSkuId, skuId).eq(WareSku::getWareId, wareId);
        List<WareSku> wareSkus = wareSkuMapper.selectList(skuQueryWrapper);
        if (CollectionUtils.isNotEmpty(wareSkus)) {
            return wareSkuMapper.addStock(skuId, wareId, skuNum);
        } else {
            WareSku entity = WareSku.builder()
                    .skuId(skuId)
                    .wareId(wareId)
                    .stock(skuNum)
                    .stockLocked(0)
                    .build();
            //TODO 远程查询sku的名字保存即可
            try {
                BaseResult<SkuInfo> baseResult = skuInfoClient.info(skuId);
                if (baseResult.getSuccess()) {
                    SkuInfo skuInfo = baseResult.getData();
                    entity.setSkuName(skuInfo.getSkuName());
                }
            } catch (Exception e) {
                log.error("远程查询sku名称失败: {}", e.getMessage(), e);
            }
            return wareSkuMapper.insert(entity);
        }
    }
}
