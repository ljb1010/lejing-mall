package cn.alphahub.mall.ware.service.impl;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.ware.domain.WareInfo;
import cn.alphahub.mall.ware.mapper.WareInfoMapper;
import cn.alphahub.mall.ware.service.WareInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 仓库信息Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:19:57
 */
@Service
public class WareInfoServiceImpl extends ServiceImpl<WareInfoMapper, WareInfo> implements WareInfoService {

    /**
     * 查询仓库信息分页列表
     *
     * @param pageDomain 分页数据
     * @param wareInfo   分页对象
     * @return 仓库信息分页数据
     */
    @Override
    public PageResult<WareInfo> queryPage(PageDomain pageDomain, WareInfo wareInfo) {
        // 1. 构造mybatis-plus查询wrapper
        QueryWrapper<WareInfo> wrapper = new QueryWrapper<>(wareInfo);
        // 2. 创建一个分页对象
        PageResult<WareInfo> pageResult = new PageResult<>();
        // 3. 开始分页
        pageResult.startPage(pageDomain);
        // 4. 执行Dao|Mapper SQL查询
        List<WareInfo> wareInfoList = this.list(wrapper);
        // 5. 分装并返回数据
        return pageResult.getPage(wareInfoList);
    }

}
