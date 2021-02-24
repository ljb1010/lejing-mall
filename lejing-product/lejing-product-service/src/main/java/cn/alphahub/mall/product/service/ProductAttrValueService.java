package cn.alphahub.mall.product.service;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.ProductAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * spu属性值Service接口
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:36:31
 */
public interface ProductAttrValueService extends IService<ProductAttrValue> {

    /**
     * 查询spu属性值分页列表
     *
     * @param pageDomain       分页数据
     * @param productAttrValue 分页对象
     * @return spu属性值分页数据
     */
    PageResult<ProductAttrValue> queryPage(PageDomain pageDomain, ProductAttrValue productAttrValue);

    void saveProductAttrValues(List<ProductAttrValue> productAttrValues);
}
