package cn.alphahub.mall.product.controller;

import cn.alphahub.common.constant.HttpStatus;
import cn.alphahub.common.core.controller.BaseController;
import cn.alphahub.common.core.domain.BaseResult;
import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.CategoryBrandRelation;
import cn.alphahub.mall.product.service.CategoryBrandRelationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 品牌分类关联Controller
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-14 19:02:16
 */
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController extends BaseController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 查询品牌分类关联列表
     *
     * @param page                  当前页码,默认第1页
     * @param rows                  显示行数,默认10条
     * @param orderColumn           排序排序字段,默认不排序
     * @param isAsc                 排序方式,desc或者asc
     * @param categoryBrandRelation 品牌分类关联,查询字段选择性传入,默认为等值查询
     * @return 品牌分类关联分页数据
     */
    @GetMapping("/list")
    public BaseResult<PageResult<CategoryBrandRelation>> list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "orderColumn", defaultValue = "") String orderColumn,
            @RequestParam(value = "isAsc", defaultValue = "") String isAsc,
            CategoryBrandRelation categoryBrandRelation
    ) {
        PageDomain pageDomain = new PageDomain(page, rows, orderColumn, isAsc);
        PageResult<CategoryBrandRelation> pageResult = categoryBrandRelationService.queryPage(pageDomain, categoryBrandRelation);
        if (ObjectUtils.isNotEmpty(pageResult.getItems())) {
            return BaseResult.ok(pageResult);
        }
        return BaseResult.fail(HttpStatus.NOT_FOUND, "查询结果为空");
    }

    /**
     * 获取当前品牌关联的分类列表
     *
     * @param brandId 品牌id
     * @return 品牌分类关联列表
     */
    @GetMapping("/catelog/list")
    public BaseResult<List<CategoryBrandRelation>> catelogList(@RequestParam(value = "brandId") Long brandId) {
        QueryWrapper<CategoryBrandRelation> wrapper = new QueryWrapper<CategoryBrandRelation>();
        wrapper.lambda().eq(CategoryBrandRelation::getBrandId, brandId);
        List<CategoryBrandRelation> categoryBrandRelations = categoryBrandRelationService.list(wrapper);
        return BaseResult.ok(categoryBrandRelations);
    }

    /**
     * 获取品牌分类关联详情
     *
     * @param id 品牌分类关联主键id
     * @return 品牌分类关联详细信息
     */
    @GetMapping("/info/{id}")
    public BaseResult<CategoryBrandRelation> info(@PathVariable("id") Long id) {
        CategoryBrandRelation categoryBrandRelation = categoryBrandRelationService.getById(id);
        return ObjectUtils.anyNotNull(categoryBrandRelation) ? BaseResult.ok(categoryBrandRelation) : BaseResult.fail();
    }

    /**
     * 新增品牌分类关联
     *
     * @param categoryBrandRelation 品牌分类关联元数据
     * @return 成功返回true, 失败返回false
     */
    @PostMapping("/save")
    public BaseResult<Boolean> save(@RequestBody CategoryBrandRelation categoryBrandRelation) {
        boolean save = categoryBrandRelationService.saveDetail(categoryBrandRelation);
        return toOperationResult(save);
    }

    /**
     * 修改品牌分类关联
     *
     * @param categoryBrandRelation 品牌分类关联,根据id选择性更新
     * @return 成功返回true, 失败返回false
     */
    @PutMapping("/update")
    public BaseResult<Boolean> update(@RequestBody CategoryBrandRelation categoryBrandRelation) {
        boolean update = categoryBrandRelationService.updateById(categoryBrandRelation);
        return toOperationResult(update);
    }

    /**
     * 批量删除品牌分类关联
     *
     * @param ids 品牌分类关联id集合
     * @return 成功返回true, 失败返回false
     */
    @DeleteMapping("/delete/{ids}")
    public BaseResult<Boolean> delete(@PathVariable Long[] ids) {
        boolean delete = categoryBrandRelationService.removeByIds(Arrays.asList(ids));
        return toOperationResult(delete);
    }
}
