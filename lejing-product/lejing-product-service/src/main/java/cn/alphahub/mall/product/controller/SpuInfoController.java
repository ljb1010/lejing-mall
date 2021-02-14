package cn.alphahub.mall.product.controller;

import cn.alphahub.common.constant.HttpStatus;
import cn.alphahub.common.core.controller.BaseController;
import cn.alphahub.common.core.domain.BaseResult;
import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.SpuInfo;
import cn.alphahub.mall.product.service.SpuInfoService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * spu信息Controller
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-14 19:02:16
 */
@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController extends BaseController {
    @Autowired
    private SpuInfoService spuInfoService;

    /**
     * 查询spu信息列表
     *
     * @param page        当前页码,默认第1页
     * @param rows        显示行数,默认10条
     * @param orderColumn 排序排序字段,默认不排序
     * @param isAsc       排序方式,desc或者asc
     * @param spuInfo     spu信息,查询字段选择性传入,默认为等值查询
     * @return spu信息分页数据
     */
    @GetMapping("/list")
    public BaseResult<PageResult<SpuInfo>> list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "orderColumn", defaultValue = "") String orderColumn,
            @RequestParam(value = "isAsc", defaultValue = "") String isAsc,
            SpuInfo spuInfo
    ) {
        PageDomain pageDomain = new PageDomain(page, rows, orderColumn, isAsc);
        PageResult<SpuInfo> pageResult = spuInfoService.queryPage(pageDomain, spuInfo);
        if (ObjectUtils.isNotEmpty(pageResult.getItems())) {
            return BaseResult.ok(pageResult);
        }
        return BaseResult.fail(HttpStatus.NOT_FOUND, "查询结果为空");
    }

    /**
     * 获取spu信息详情
     *
     * @param id spu信息主键id
     * @return spu信息详细信息
     */
    @GetMapping("/info/{id}")
    public BaseResult<SpuInfo> info(@PathVariable("id") Long id) {
        SpuInfo spuInfo = spuInfoService.getById(id);
        return ObjectUtils.anyNotNull(spuInfo) ? BaseResult.ok(spuInfo) : BaseResult.fail();
    }

    /**
     * 新增spu信息
     *
     * @param spuInfo spu信息元数据
     * @return 成功返回true, 失败返回false
     */
    @PostMapping("/save")
    public BaseResult<Boolean> save(@RequestBody SpuInfo spuInfo) {
        boolean save = spuInfoService.save(spuInfo);
        return toOperationResult(save);
    }

    /**
     * 修改spu信息
     *
     * @param spuInfo spu信息,根据id选择性更新
     * @return 成功返回true, 失败返回false
     */
    @PutMapping("/update")
    public BaseResult<Boolean> update(@RequestBody SpuInfo spuInfo) {
        boolean update = spuInfoService.updateById(spuInfo);
        return toOperationResult(update);
    }

    /**
     * 批量删除spu信息
     *
     * @param ids spu信息id集合
     * @return 成功返回true, 失败返回false
     */
    @DeleteMapping("/delete/{ids}")
    public BaseResult<Boolean> delete(@PathVariable Long[] ids) {
        boolean delete = spuInfoService.removeByIds(Arrays.asList(ids));
        return toOperationResult(delete);
    }
}
