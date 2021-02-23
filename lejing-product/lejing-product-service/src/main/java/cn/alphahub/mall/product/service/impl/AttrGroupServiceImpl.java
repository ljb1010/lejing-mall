package cn.alphahub.mall.product.service.impl;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.Attr;
import cn.alphahub.mall.product.domain.AttrGroup;
import cn.alphahub.mall.product.mapper.AttrGroupMapper;
import cn.alphahub.mall.product.service.AttrGroupService;
import cn.alphahub.mall.product.service.AttrService;
import cn.alphahub.mall.product.vo.AttrGroupWithAttrsVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 属性分组Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-07 22:46:24
 */
@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroup> implements AttrGroupService {
    @Resource
    private AttrService attrService;

    /**
     * 查询属性分组分页列表
     *
     * @param pageDomain 分页数据
     * @param attrGroup  分页对象
     * @return 属性分组分页数据
     */
    @Override
    public PageResult<AttrGroup> queryPage(PageDomain pageDomain, AttrGroup attrGroup) {
        pageDomain.startPage();
        QueryWrapper<AttrGroup> wrapper = new QueryWrapper<>(attrGroup);
        return getAttrGroupPageResult(wrapper);
    }

    /**
     * 根据catelogId查询属性分组列表
     *
     * @param pageDomain 分页数据
     * @param attrGroup  分页对象
     * @param key        查询关键字
     * @return
     */
    @Override
    public PageResult<AttrGroup> queryPage(PageDomain pageDomain, AttrGroup attrGroup, String key) {
        pageDomain.startPage();
        QueryWrapper<AttrGroup> wrapper = new QueryWrapper<>(attrGroup);
        //select * from pms_attr_group where catelog_id=? and (attr_group_id=key or attr_group_name like %key%)
        if (StringUtils.isNotBlank(key)) {
            wrapper.and(qw -> qw.lambda().eq(AttrGroup::getAttrGroupId, key).or().like(AttrGroup::getAttrGroupName, key));
        }
        return getAttrGroupPageResult(wrapper);
    }

    @Override
    public List<AttrGroupWithAttrsVO> getAttrGroupWithAttrsByCatelogId(Long catelogId) {
        //1、查出当前分类下的所有属性分组
        QueryWrapper<AttrGroup> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(AttrGroup::getCatelogId, catelogId);
        List<AttrGroup> attrGroups = this.list(wrapper);
        //2、查出每个属性分组的所有属性
        return attrGroups.stream().map(attrGroup -> {
            AttrGroupWithAttrsVO attrsVO = new AttrGroupWithAttrsVO();
            BeanUtils.copyProperties(attrGroup, attrsVO);
            List<Attr> attrRelations = attrService.getAttrRelations(attrGroup.getAttrGroupId());
            attrsVO.setAttrs(attrRelations);
            return attrsVO;
        }).collect(Collectors.toList());
    }

    private PageResult<AttrGroup> getAttrGroupPageResult(QueryWrapper<AttrGroup> wrapper) {
        List<AttrGroup> list = this.list(wrapper);
        PageInfo<AttrGroup> pageInfo = new PageInfo<>(list);
        return PageResult.<AttrGroup>builder()
                .totalCount(pageInfo.getTotal())
                .totalPage((long) pageInfo.getPages())
                .items(pageInfo.getList())
                .build();
    }
}
