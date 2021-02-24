package cn.alphahub.mall.product.service.impl;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.Brand;
import cn.alphahub.mall.product.mapper.BrandMapper;
import cn.alphahub.mall.product.service.BrandService;
import cn.alphahub.mall.product.service.CategoryBrandRelationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 品牌Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:36:31
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Resource
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 查询品牌分页列表
     *
     * @param pageDomain 分页数据
     * @param brand      分页对象
     * @return 品牌分页数据
     */
    @Override
    public PageResult<Brand> queryPage(PageDomain pageDomain, Brand brand) {
        // 1. 构造mybatis-plus查询wrapper
        QueryWrapper<Brand> wrapper = new QueryWrapper<>(brand);
        // 2. 创建一个分页对象
        PageResult<Brand> pageResult = new PageResult<>();
        // 3. 开始分页
        pageResult.startPage(pageDomain);
        // 4. 执行Dao|Mapper SQL查询
        List<Brand> brandList = this.list(wrapper);
        // 5. 分装并返回数据
        return pageResult.getPage(brandList);
    }


    /**
     * 根据关键字查询品牌分页列表
     *
     * @param pageDomain 分页数据
     * @param brand      分页对象
     * @param searchKey  查询关键字
     * @return 品牌分页数据
     */
    @Override
    public PageResult<Brand> queryPage(PageDomain pageDomain, Brand brand, String searchKey) {
        pageDomain.startPage();
        QueryWrapper<Brand> wrapper = new QueryWrapper<>(brand);
        if (StringUtils.isNotBlank(searchKey)) {
            wrapper.lambda()
                    .eq(Brand::getBrandId, searchKey)
                    .or()
                    .like(Brand::getName, searchKey);
        }
        return getBrandPageResult(wrapper);
    }

    @Override
    public boolean updateDetailById(Brand brand) {
        boolean b1 = false, b2 = false;
        b1 = this.updateById(brand);
        //保证冗余字段的数据一直
        if (StringUtils.isNotEmpty(brand.getName())) {
            //同步更关联表中的数据
            b2 = categoryBrandRelationService.updateBrand(brand.getBrandId(), brand.getName());

            // TODO 更新其他关联表
        }
        return b1 && b2;
    }

    /**
     * 根据查询构造器条件查询分页查询结果
     *
     * @param wrapper 实体对象封装操作类
     * @return 实体对象分页查询结果
     */
    private PageResult<Brand> getBrandPageResult(QueryWrapper<Brand> wrapper) {
        List<Brand> list = this.list(wrapper);
        PageInfo<Brand> pageInfo = new PageInfo<>(list);
        return PageResult.<Brand>builder()
                .totalCount(pageInfo.getTotal())
                .totalPage(pageInfo.getPages())
                .items(pageInfo.getList())
                .build();
    }

}
