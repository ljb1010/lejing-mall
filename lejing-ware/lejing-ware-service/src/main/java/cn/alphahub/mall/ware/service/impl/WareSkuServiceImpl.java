package cn.alphahub.mall.ware.service.impl;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.ware.domain.WareSku;
import cn.alphahub.mall.ware.mapper.WareSkuMapper;
import cn.alphahub.mall.ware.service.WareSkuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品库存Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:19:57
 */
@Service
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper, WareSku> implements WareSkuService {

    /**
     * 查询商品库存分页列表
     *
     * @param pageDomain 分页数据
     * @param wareSku    分页对象
     * @return 商品库存分页数据
     */
    @Override
    public PageResult<WareSku> queryPage(PageDomain pageDomain, WareSku wareSku) {
        // 1. 构造mybatis-plus查询wrapper
        QueryWrapper<WareSku> wrapper = new QueryWrapper<>(wareSku);
        // 2. 创建一个分页对象
        PageResult<WareSku> pageResult = new PageResult<>();
        // 3. 开始分页
        pageResult.startPage(pageDomain);
        // 4. 执行Dao|Mapper SQL查询
        List<WareSku> wareSkuList = this.list(wrapper);
        // 5. 分装并返回数据
        return pageResult.getPage(wareSkuList);
    }

}
