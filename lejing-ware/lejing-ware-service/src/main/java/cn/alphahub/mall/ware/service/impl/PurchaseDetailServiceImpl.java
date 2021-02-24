package cn.alphahub.mall.ware.service.impl;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.ware.domain.PurchaseDetail;
import cn.alphahub.mall.ware.mapper.PurchaseDetailMapper;
import cn.alphahub.mall.ware.service.PurchaseDetailService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 仓储采购表Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:19:57
 */
@Service
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailMapper, PurchaseDetail> implements PurchaseDetailService {

    /**
     * 查询仓储采购表分页列表
     *
     * @param pageDomain     分页数据
     * @param purchaseDetail 分页对象
     * @return 仓储采购表分页数据
     */
    @Override
    public PageResult<PurchaseDetail> queryPage(PageDomain pageDomain, PurchaseDetail purchaseDetail) {
        // 1. 构造mybatis-plus查询wrapper
        QueryWrapper<PurchaseDetail> wrapper = new QueryWrapper<>(purchaseDetail);
        // 2. 创建一个分页对象
        PageResult<PurchaseDetail> pageResult = new PageResult<>();
        // 3. 开始分页
        pageResult.startPage(pageDomain);
        // 4. 执行Dao|Mapper SQL查询
        List<PurchaseDetail> purchaseDetailList = this.list(wrapper);
        // 5. 分装并返回数据
        return pageResult.getPage(purchaseDetailList);
    }

}
