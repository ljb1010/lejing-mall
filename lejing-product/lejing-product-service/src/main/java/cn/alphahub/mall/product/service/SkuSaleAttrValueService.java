package cn.alphahub.mall.product.service;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.SkuSaleAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * sku销售属性&值Service接口
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:36:31
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValue> {

    /**
     * 查询sku销售属性&值分页列表
     *
     * @param pageDomain       分页数据
     * @param skuSaleAttrValue 分页对象
     * @return sku销售属性&值分页数据
     */
    PageResult<SkuSaleAttrValue> queryPage(PageDomain pageDomain, SkuSaleAttrValue skuSaleAttrValue);

}
