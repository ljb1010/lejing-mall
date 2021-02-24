package cn.alphahub.mall.order.service.impl;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.order.domain.Order;
import cn.alphahub.mall.order.mapper.OrderMapper;
import cn.alphahub.mall.order.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 16:02:31
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    /**
     * 查询订单分页列表
     *
     * @param pageDomain 分页数据
     * @param order      分页对象
     * @return 订单分页数据
     */
    @Override
    public PageResult<Order> queryPage(PageDomain pageDomain, Order order) {
        // 1. 构造mybatis-plus查询wrapper
        QueryWrapper<Order> wrapper = new QueryWrapper<>(order);
        // 2. 创建一个分页对象
        PageResult<Order> pageResult = new PageResult<>();
        // 3. 开始分页
        pageResult.startPage(pageDomain);
        // 4. 执行Dao|Mapper SQL查询
        List<Order> orderList = this.list(wrapper);
        // 5. 分装并返回数据
        return pageResult.getPage(orderList);
    }

}
