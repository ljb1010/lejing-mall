package cn.alphahub.mall.product.service;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.SkuInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * sku信息Service接口
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:36:31
 */
public interface SkuInfoService extends IService<SkuInfo> {

    /**
     * 查询sku信息分页列表
     *
     * @param pageDomain 分页数据
     * @param skuInfo    分页对象
     * @return sku信息分页数据
     */
    PageResult<SkuInfo> queryPage(PageDomain pageDomain, SkuInfo skuInfo);

    /**
     * 查询sku信息列表
     *
     * @param key       检索关键字
     * @param catelogId 三級分類id
     * @param brandId   品牌id
     * @param min       最低價格
     * @param max       最大價格
     * @return sku信息分页数据
     */
    PageResult<SkuInfo> queryPage(PageDomain pageDomain, String key, Long catelogId, Long brandId, Long min, Long max);
}
