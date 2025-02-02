package cn.alphahub.mall.product.service;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商品三级分类Service接口
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:36:31
 */
public interface CategoryService extends IService<Category> {

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

    /**
     * 所属分类路径
     *
     * @param catelogId 所属分类id
     * @return
     */
    Long[] getCatelogFullPath(Long catelogId);


    /**
     * 级联更新-商品三级分类
     *
     * @param category 商品三级分类,根据id选择性更新
     * @return 成功返回true, 失败返回false
     */
    boolean updateCasecade(Category category);

    /**
     * 查询商品三级分类分页列表
     *
     * @param pageDomain 分页数据
     * @param category   分页对象
     * @return 商品三级分类分页数据
     */
    PageResult<Category> queryPage(PageDomain pageDomain, Category category);
}
