package cn.alphahub.mall.ware.service;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.ware.domain.Purchase;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 采购信息Service接口
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:19:57
 */
public interface PurchaseService extends IService<Purchase> {

    /**
     * 查询采购信息分页列表
     *
     * @param pageDomain 分页数据
     * @param purchase   分页对象
     * @return 采购信息分页数据
     */
    PageResult<Purchase> queryPage(PageDomain pageDomain, Purchase purchase);

}
