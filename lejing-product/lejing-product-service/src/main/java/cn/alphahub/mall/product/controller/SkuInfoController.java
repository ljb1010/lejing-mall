package cn.alphahub.mall.product.controller;

import cn.alphahub.common.constant.HttpStatus;
import cn.alphahub.common.core.controller.BaseController;
import cn.alphahub.common.core.domain.BaseResult;
import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.SkuInfo;
import cn.alphahub.mall.product.service.SkuInfoService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * sku信息Controller
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:36:31
 */
@RestController
@RequestMapping("product/skuinfo")
public class SkuInfoController extends BaseController {
    @Resource
    private SkuInfoService skuInfoService;

    /**
     * 查询sku信息列表
     *
     * @param page        当前页码,默认第1页
     * @param rows        显示行数,默认10条
     * @param orderColumn 排序排序字段,默认不排序
     * @param isAsc       排序方式,desc或者asc
     * @param skuInfo     sku信息,查询字段选择性传入,默认为等值查询
     * @param sidx        排序字段
     * @param order       排序方式:asc/desc
     * @param key         检索关键字
     * @param catelogId   三級分類id
     * @param brandId     品牌id
     * @param min         最低價格
     * @param max         最大價格
     * @return sku信息分页数据
     */
    @GetMapping("/list")
    public BaseResult<PageResult<SkuInfo>> list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "orderColumn", defaultValue = "") String orderColumn,
            @RequestParam(value = "isAsc", defaultValue = "") String isAsc,
            SkuInfo skuInfo,
            @RequestParam(value = "sidx", defaultValue = "") String sidx,
            @RequestParam(value = "order", defaultValue = "") String order,
            @RequestParam(value = "key", defaultValue = "") String key,
            @RequestParam(value = "catelogId", defaultValue = "") Long catelogId,
            @RequestParam(value = "brandId", defaultValue = "") Long brandId,
            @RequestParam(value = "min", defaultValue = "") Long min,
            @RequestParam(value = "max", defaultValue = "") Long max
    ) {
        /*
        {
            sidx: 'id',//排序字段
            order: 'asc/desc',//排序方式
            key: '华为',//检索关键字
            catelogId: 0,
            brandId: 0,
            min: 0,
            max: 0
        }
        */
        PageDomain pageDomain;
        if (StringUtils.isAllBlank(sidx, order)) {
            pageDomain = new PageDomain(page, rows, sidx, order);
        } else {
            pageDomain = new PageDomain(page, rows, orderColumn, isAsc);
        }
        PageResult<SkuInfo> pageResult = skuInfoService.queryPage(pageDomain, skuInfo, key, catelogId, brandId, min, max);
        if (ObjectUtils.isNotEmpty(pageResult.getItems())) {
            return BaseResult.ok(pageResult);
        }
        return BaseResult.fail(HttpStatus.NOT_FOUND, "查询结果为空");
    }

    /**
     * 获取sku信息详情
     *
     * @param skuId sku信息主键id
     * @return sku信息详细信息
     */
    @GetMapping("/info/{skuId}")
    public BaseResult<SkuInfo> info(@PathVariable("skuId") Long skuId) {
        SkuInfo skuInfo = skuInfoService.getById(skuId);
        return ObjectUtils.anyNotNull(skuInfo) ? BaseResult.ok(skuInfo) : BaseResult.fail();
    }

    /**
     * 新增sku信息
     *
     * @param skuInfo sku信息元数据
     * @return 成功返回true, 失败返回false
     */
    @PostMapping("/save")
    public BaseResult<Boolean> save(@RequestBody SkuInfo skuInfo) {
        boolean save = skuInfoService.save(skuInfo);
        return toOperationResult(save);
    }

    /**
     * 修改sku信息
     *
     * @param skuInfo sku信息,根据id选择性更新
     * @return 成功返回true, 失败返回false
     */
    @PutMapping("/update")
    public BaseResult<Boolean> update(@RequestBody SkuInfo skuInfo) {
        boolean update = skuInfoService.updateById(skuInfo);
        return toOperationResult(update);
    }

    /**
     * 批量删除sku信息
     *
     * @param skuIds sku信息id集合
     * @return 成功返回true, 失败返回false
     */
    @DeleteMapping("/delete/{skuIds}")
    public BaseResult<Boolean> delete(@PathVariable Long[] skuIds) {
        boolean delete = skuInfoService.removeByIds(Arrays.asList(skuIds));
        return toOperationResult(delete);
    }
}
