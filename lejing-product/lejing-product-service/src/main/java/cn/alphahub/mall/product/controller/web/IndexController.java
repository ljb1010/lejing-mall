package cn.alphahub.mall.product.controller.web;

import cn.alphahub.mall.product.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <b>首页Controller</b>
 *
 * @author Weasley J
 * @version 1.0
 * @date 2021/03/09
 */
@Controller
public class IndexController {

    @Resource
    private CategoryService categoryService;

    /**
     * 进入首页返回所有1级分类
     *
     * @param model 模型
     * @return 首页视图
     */
    @GetMapping({"/", "index", "index.html"})
    public String indexPage(Model model) {
        // 1、查出所有1级分类
        /*
        List<Category> categories = categoryService.getLevel1Categories();
        model.addAttribute("categorys", categories);
        */
        return "index";
    }

    /**
     * 查出三级分类
     * 1级分类作为key，2级引用List
     */
    /*
    @ResponseBody
    @GetMapping("/index/catalog.json")
    public Map<String, List<Catalog2Vo>> getCatalogJson() {
        Map<String, List<Catalog2Vo>> map = categoryService.getCatalogJson();
        return map;
    }
    */
    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
