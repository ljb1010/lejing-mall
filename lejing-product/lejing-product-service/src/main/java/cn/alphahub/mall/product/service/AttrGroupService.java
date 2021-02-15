package cn.alphahub.mall.product.service;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.common.core.service.PageService;
import cn.alphahub.mall.product.domain.AttrGroup;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 属性分组Service接口
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-07 22:46:24
 */
public interface AttrGroupService extends IService<AttrGroup>, PageService<AttrGroup> {

    /**
     * 根据catelogId查询属性分组列表
     *
     * @param pageDomain 分页数据实体
     * @param attrGroup  属性分组
     * @param key        检索关键字
     * @return 属性分组分页数据
     */
    PageResult<AttrGroup> queryPage(PageDomain pageDomain, AttrGroup attrGroup, String key);
}
