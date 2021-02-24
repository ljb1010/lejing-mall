package cn.alphahub.mall.product.service.impl;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.ProductAttrValue;
import cn.alphahub.mall.product.mapper.ProductAttrValueMapper;
import cn.alphahub.mall.product.service.ProductAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * spu属性值Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:36:31
 */
@Service
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueMapper, ProductAttrValue> implements ProductAttrValueService {

    /**
     * 查询spu属性值分页列表
     *
     * @param pageDomain       分页数据
     * @param productAttrValue 分页对象
     * @return spu属性值分页数据
     */
    @Override
    public PageResult<ProductAttrValue> queryPage(PageDomain pageDomain, ProductAttrValue productAttrValue) {
        // 1. 构造mybatis-plus查询wrapper
        QueryWrapper<ProductAttrValue> wrapper = new QueryWrapper<>(productAttrValue);
        // 2. 创建一个分页对象
        PageResult<ProductAttrValue> pageResult = new PageResult<>();
        // 3. 开始分页
        pageResult.startPage(pageDomain);
        // 4. 执行Dao|Mapper SQL查询
        List<ProductAttrValue> productAttrValueList = this.list(wrapper);
        // 5. 分装并返回数据
        return pageResult.getPage(productAttrValueList);
    }


    @Override
    public void saveProductAttrValues(List<ProductAttrValue> productAttrValues) {
        this.saveBatch(productAttrValues);
    }
}
