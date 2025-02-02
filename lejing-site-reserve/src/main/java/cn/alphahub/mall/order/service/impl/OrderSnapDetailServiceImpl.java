package cn.alphahub.mall.order.service.impl;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.order.domain.OrderSnapDetail;
import cn.alphahub.mall.order.mapper.OrderSnapDetailMapper;
import cn.alphahub.mall.order.service.OrderSnapDetailService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单快照表Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-16 18:17:37
 */
@Service("orderSnapDetailService")
public class OrderSnapDetailServiceImpl extends ServiceImpl<OrderSnapDetailMapper, OrderSnapDetail> implements OrderSnapDetailService {

    /**
     * 查询订单快照表分页列表
     *
     * @param pageDomain      分页数据
     * @param orderSnapDetail 分页对象
     * @return 订单快照表分页数据
     */
    @Override
    public PageResult<OrderSnapDetail> queryPage(PageDomain pageDomain, OrderSnapDetail orderSnapDetail) {
        pageDomain.startPage();
        QueryWrapper<OrderSnapDetail> wrapper = new QueryWrapper<>(orderSnapDetail);

        // 这里可编写自己的业务查询wrapper，如果需要。
        // ...

        return getPageResult(wrapper);
    }

    /**
     * 根据查询构造器条件查询分页查询结果
     *
     * @param wrapper <b>订单快照表<b/>实体对象封装操作类
     * @return 实体对象分页查询结果
     */
    private PageResult<OrderSnapDetail> getPageResult(QueryWrapper<OrderSnapDetail> wrapper) {
        List<OrderSnapDetail> list = this.list(wrapper);
        PageInfo<OrderSnapDetail> pageInfo = new PageInfo<>(list);
        return PageResult.<OrderSnapDetail>builder()
                .totalCount(pageInfo.getTotal())
                .totalPage(pageInfo.getPages())
                .items(pageInfo.getList())
                .build();
    }

}
