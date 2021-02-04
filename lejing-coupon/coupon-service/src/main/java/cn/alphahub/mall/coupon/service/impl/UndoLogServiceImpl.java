package cn.alphahub.mall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;

import cn.alphahub.mall.coupon.mapper.UndoLogMapper;
import cn.alphahub.mall.coupon.domain.UndoLog;
import cn.alphahub.mall.coupon.service.UndoLogService;

import java.util.List;

/**
 * 撤销日志表Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-05 02:10:59
 */
@Service("undoLogService")
public class UndoLogServiceImpl extends ServiceImpl<UndoLogMapper, UndoLog> implements UndoLogService {

    /**
     * 查询撤销日志表分页列表
     *
     * @param pageDomain   分页数据
     * @param undoLog 分页对象
     * @return 撤销日志表分页数据
     */
    @Override
    public PageResult<UndoLog> queryPage(PageDomain pageDomain, UndoLog undoLog) {
        pageDomain.startPage();
        QueryWrapper<UndoLog> wrapper = new QueryWrapper<>(undoLog);
        List<UndoLog> list = this.list(wrapper);
        PageInfo<UndoLog> pageInfo = new PageInfo<>(list);
        PageResult<UndoLog> pageResult = PageResult.<UndoLog>builder()
                .totalCount(pageInfo.getTotal())
                .totalPage((long) pageInfo.getPages())
                .items(pageInfo.getList())
                .build();
        return pageResult;
    }

}