package cn.alphahub.mall.search.service;

import cn.alphahub.mall.search.domain.SkuModel;

import java.util.List;

/**
 * <b>商品搜索服务顶层接口</b>
 *
 * @author liuwenjing
 * @version 1.0
 * @date 2021/03/07
 */
public interface SearchService {

    /**
     * 保存上架商品至Elasticsearch中
     *
     * @param skuModels 商品SKU信息数据
     * @return true|false
     */
    Boolean saveProduct(List<SkuModel> skuModels);

    /**
     * 删除商品
     *
     * @param spuId 商品spu id
     */
    Boolean deleteProduct(Long spuId);
}
