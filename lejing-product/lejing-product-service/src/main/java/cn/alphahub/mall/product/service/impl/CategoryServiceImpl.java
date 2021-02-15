package cn.alphahub.mall.product.service.impl;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.Category;
import cn.alphahub.mall.product.mapper.CategoryMapper;
import cn.alphahub.mall.product.service.CategoryBrandRelationService;
import cn.alphahub.mall.product.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 商品三级分类Service业务层处理
 *
 * @author Weasley J
 * @date 2021-02-07 22:46:24
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 查询商品三级分类分页列表
     *
     * @param pageDomain 分页数据
     * @param category   分页对象
     * @return 商品三级分类分页数据
     */
    @Override
    public PageResult<Category> queryPage(PageDomain pageDomain, Category category) {
        pageDomain.startPage();
        QueryWrapper<Category> wrapper = new QueryWrapper<>(category);
        List<Category> list = this.list(wrapper);
        PageInfo<Category> pageInfo = new PageInfo<>(list);
        PageResult<Category> pageResult = PageResult.<Category>builder()
                .totalCount(pageInfo.getTotal())
                .totalPage((long) pageInfo.getPages())
                .items(pageInfo.getList())
                .build();
        return pageResult;
    }

    /**
     * 查出所有分类及其子分类， 树形结构组装
     *
     * @return 分类及其子分类树形数据
     */
    @Override
    public List<Category> listWithTree() {
        // 1. 查出所有分类
        List<Category> categories = categoryMapper.selectList(null);
        // 一级类目
        // 2. 组装父子结构的数据
        return categories.stream()
                .filter(category -> category.getParentCid() == 0)
                .peek(menu -> menu.setChildren(getChildrenList(menu, categories)))
                .sorted(Comparator.comparingInt(Category::getSort)).collect(Collectors.toList());
    }

    @Override
    public boolean removeMenusByIds(List<Long> ids) {
        //TODO 1. 检查当前删除的菜单是否被别的地方引用
        int batchIds = categoryMapper.deleteBatchIds(ids);
        return batchIds >= 1;
    }

    /**
     * 找到catelogId所属分类的完整路径
     *
     * @param catelogId 所属分类id
     * @return [父, 子, 孙], [2,25,166]
     */
    @Override
    public Long[] getCatelogFullPath(Long catelogId) {
        List<Long> list = new ArrayList<>();
        List<Long> parentPath = getParentPath(catelogId, list);
        return parentPath.toArray(new Long[parentPath.size()]);
    }

    /**
     * 级联更新-商品三级分类
     *
     * @param category 商品三级分类,根据id选择性更新
     * @return 成功返回true, 失败返回false
     */
    @Override
    public boolean updateCasecade(Category category) {
        boolean b1 = this.updateById(category);
        boolean b2 = this.categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
        return b1 && b2;
    }

    /**
     * 递归方法找父路径
     *
     * @param catelogId 分类id
     * @return
     */
    private List<Long> getParentPath(Long catelogId, List<Long> initialList) {
        // 收集当前节点的id
        initialList.add(catelogId);
        Category category = this.getById(catelogId);
        if (!Objects.equals(category.getParentCid(), 0L)) {
            getParentPath(category.getParentCid(), initialList);
        }
        //找到的是逆序的,使用Collections转换一下: [225,25,2] -> [2,25,225]
        Collections.reverse(initialList);
        return initialList;
    }

    /**
     * 递归查找所有菜单的子菜单
     *
     * @param rootCategory  当前菜单
     * @param allCategories 所有菜单
     * @return 菜单的子菜单
     */
    private List<Category> getChildrenList(@Nonnull Category rootCategory, @Nonnull List<Category> allCategories) {
        return allCategories.stream()
                .filter(category -> category.getParentCid().equals(rootCategory.getCatId()))
                //找子菜单
                .peek(category -> category.setChildren(getChildrenList(category, allCategories)))
                //菜单排序,sort越小越靠前
                .sorted(Comparator.comparingInt(value -> ObjectUtils.isNull(value.getSort()) ? 0 : value.getSort()))
                .collect(Collectors.toList());
    }
}
