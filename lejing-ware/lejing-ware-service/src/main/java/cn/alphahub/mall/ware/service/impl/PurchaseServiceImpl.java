package cn.alphahub.mall.ware.service.impl;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.ware.domain.Purchase;
import cn.alphahub.mall.ware.mapper.PurchaseMapper;
import cn.alphahub.mall.ware.service.PurchaseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 采购信息Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:19:57
 */
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase> implements PurchaseService {

    /**
     * 查询采购信息分页列表
     *
     * @param pageDomain 分页数据
     * @param purchase   分页对象
     * @return 采购信息分页数据
     */
    @Override
    public PageResult<Purchase> queryPage(PageDomain pageDomain, Purchase purchase) {
        // 1. 构造mybatis-plus查询wrapper
        QueryWrapper<Purchase> wrapper = new QueryWrapper<>(purchase);
        // 2. 创建一个分页对象
        PageResult<Purchase> pageResult = new PageResult<>();
        // 3. 开始分页
        pageResult.startPage(pageDomain);
        // 4. 执行Dao|Mapper SQL查询
        List<Purchase> purchaseList = this.list(wrapper);
        // 5. 分装并返回数据
        return pageResult.getPage(purchaseList);
    }

    @Override
    public PageResult<Purchase> unReceiveList(PageDomain pageDomain, Purchase purchase) {
        QueryWrapper<Purchase> wrapper = new QueryWrapper<>(purchase);
        wrapper.lambda().eq(Purchase::getStatus, 0).or().eq(Purchase::getStatus, 1);
        PageResult<Purchase> pageResult = new PageResult<>();
        pageResult.startPage(pageDomain);
        List<Purchase> purchaseList = this.list(wrapper);
        return pageResult.getPage(purchaseList);
    }

}
