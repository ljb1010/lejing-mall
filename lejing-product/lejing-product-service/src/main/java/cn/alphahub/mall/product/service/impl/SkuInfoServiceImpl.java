package cn.alphahub.mall.product.service.impl;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.SkuInfo;
import cn.alphahub.mall.product.mapper.SkuInfoMapper;
import cn.alphahub.mall.product.service.SkuInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * sku信息Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:36:31
 */
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements SkuInfoService {

    /**
     * 查询sku信息分页列表
     *
     * @param pageDomain 分页数据
     * @param skuInfo    分页对象
     * @return sku信息分页数据
     */
    @Override
    public PageResult<SkuInfo> queryPage(PageDomain pageDomain, SkuInfo skuInfo) {
        // 1. 构造mybatis-plus查询wrapper
        QueryWrapper<SkuInfo> wrapper = new QueryWrapper<>(skuInfo);
        // 2. 创建一个分页对象
        PageResult<SkuInfo> pageResult = new PageResult<>();
        // 3. 开始分页
        pageResult.startPage(pageDomain);
        // 4. 执行Dao|Mapper SQL查询
        List<SkuInfo> skuInfoList = this.list(wrapper);
        // 5. 分装并返回数据
        return pageResult.getPage(skuInfoList);
    }

}
