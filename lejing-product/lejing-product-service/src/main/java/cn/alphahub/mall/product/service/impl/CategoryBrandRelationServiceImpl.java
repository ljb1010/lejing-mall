package cn.alphahub.mall.product.service.impl;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.Brand;
import cn.alphahub.mall.product.domain.Category;
import cn.alphahub.mall.product.domain.CategoryBrandRelation;
import cn.alphahub.mall.product.mapper.BrandMapper;
import cn.alphahub.mall.product.mapper.CategoryBrandRelationMapper;
import cn.alphahub.mall.product.mapper.CategoryMapper;
import cn.alphahub.mall.product.service.CategoryBrandRelationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 品牌分类关联Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-07 22:46:24
 */
@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationMapper, CategoryBrandRelation> implements CategoryBrandRelationService {

    @Resource
    private BrandMapper brandMapper;

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 查询品牌分类关联分页列表
     *
     * @param pageDomain            分页数据
     * @param categoryBrandRelation 分页对象
     * @return 品牌分类关联分页数据
     */
    @Override
    public PageResult<CategoryBrandRelation> queryPage(PageDomain pageDomain, CategoryBrandRelation categoryBrandRelation) {
        pageDomain.startPage();
        QueryWrapper<CategoryBrandRelation> wrapper = new QueryWrapper<>(categoryBrandRelation);
        List<CategoryBrandRelation> list = this.list(wrapper);
        PageInfo<CategoryBrandRelation> pageInfo = new PageInfo<>(list);
        PageResult<CategoryBrandRelation> pageResult = PageResult.<CategoryBrandRelation>builder()
                .totalCount(pageInfo.getTotal())
                .totalPage((long) pageInfo.getPages())
                .items(pageInfo.getList())
                .build();
        return pageResult;
    }

    @Override
    public boolean saveDetail(CategoryBrandRelation categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();
        // 查询品牌名称
        Brand brand = brandMapper.selectById(brandId);
        // 查询分类名称
        Category category = categoryMapper.selectById(catelogId);
        categoryBrandRelation.setBrandName(brand.getName());
        categoryBrandRelation.setCatelogName(category.getName());
        return this.save(categoryBrandRelation);
    }

    @Override
    public boolean updateBrand(Long brandId, String name) {
        CategoryBrandRelation brandRelation = CategoryBrandRelation
                .builder()
                .brandId(brandId)
                .brandName(name)
                .build();
        UpdateWrapper<CategoryBrandRelation> updateWrapper = new UpdateWrapper<>();
        return this.update(brandRelation, updateWrapper.lambda().eq(CategoryBrandRelation::getBrandId, brandId));
    }

    /**
     * 级联更新-商品三级分类
     *
     * @param catId 商品三级分类id
     * @param name  分类名称
     * @return 成功返回true, 失败返回false
     */
    @Override
    public boolean updateCategory(Long catId, String name) {
        return this.baseMapper.updateCategory(catId, name) >= 1;
    }
}
