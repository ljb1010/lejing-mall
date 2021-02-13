package cn.alphahub.mall.product.service;

import cn.alphahub.common.core.service.PageService;
import cn.alphahub.mall.product.domain.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商品三级分类Service接口
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-07 22:46:24
 */
public interface CategoryService extends IService<Category>, PageService<Category> {

    /**
     * 查出所有分类及其子分类， 树形结构组装
     *
     * @return 分类及其子分类树形数据
     */
    List<Category> listWithTree();

    /**
     * 批量删除分类
     *
     * @param ids
     * @return
     */
    boolean removeMenusByIds(List<Long> ids);
}
