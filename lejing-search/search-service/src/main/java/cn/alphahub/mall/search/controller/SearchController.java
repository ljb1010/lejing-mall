package cn.alphahub.mall.search.controller;

import cn.alphahub.common.core.controller.BaseController;
import cn.alphahub.common.core.domain.BaseResult;
import cn.alphahub.common.exception.BusinessCodeEnum;
import cn.alphahub.mall.search.domain.SkuModel;
import cn.alphahub.mall.search.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品搜索Controller
 *
 * @author Weasley J
 * @date 2021年3月7日
 */
@Slf4j
@RestController
@RequestMapping("/search")
public class SearchController extends BaseController {

    @Resource
    private SearchService searchService;

    /**
     * 保存上架商品至Elasticsearch中
     *
     * @param skuModels 商品SKU信息元数据集合
     * @return true|false
     */
    @PostMapping("/save/product")
    public BaseResult<Boolean> productStatusUp(@RequestBody List<SkuModel> skuModels) {
        Boolean saveProduct = false;
        try {
            saveProduct = searchService.saveProduct(skuModels);
        } catch (Exception e) {
            log.error("上架商品保存至Elasticsearch中失败：{}\n", e.getClass(), e);
        }
        return saveProduct ? BaseResult.ok("保存成功") : BaseResult.fail(
                BusinessCodeEnum.PRODUCT_UP_EXCEPTION.getCode(),
                BusinessCodeEnum.PRODUCT_UP_EXCEPTION.getMessage());
    }

}
