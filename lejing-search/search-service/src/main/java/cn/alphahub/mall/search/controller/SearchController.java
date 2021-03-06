package cn.alphahub.mall.search.controller;

import cn.alphahub.common.core.controller.BaseController;
import cn.alphahub.common.core.domain.BaseResult;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.search.domain.SkuModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品搜索Controller
 *
 * @author Weasley J
 * @date 2021年3月7日
 */
@RestController
@RequestMapping("/elastic")
public class SearchController extends BaseController {

    /**
     * 获取商品搜索结果
     *
     * @return 商品搜索分页列表
     */
    @GetMapping("search")
    public BaseResult<PageResult<SkuModel>> getSearchResult(@RequestBody SkuModel skuModel) {
        return BaseResult.ok();
    }
}
