package cn.alphahub.mall.product.service.impl;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.Attr;
import cn.alphahub.mall.product.domain.AttrAttrgroupRelation;
import cn.alphahub.mall.product.mapper.AttrAttrgroupRelationMapper;
import cn.alphahub.mall.product.mapper.AttrMapper;
import cn.alphahub.mall.product.service.AttrService;
import cn.alphahub.mall.product.vo.AttrVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 商品属性Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-07 22:46:24
 */
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationMapper attrAttrgroupRelationMapper;

    /**
     * 查询商品属性分页列表
     *
     * @param pageDomain 分页数据
     * @param attr       分页对象
     * @return 商品属性分页数据
     */
    @Override
    public PageResult<Attr> queryPage(PageDomain pageDomain, Attr attr) {
        pageDomain.startPage();
        QueryWrapper<Attr> wrapper = new QueryWrapper<>(attr);
        return getPageResult(wrapper);
    }

    private PageResult<Attr> getPageResult(QueryWrapper<Attr> wrapper) {
        List<Attr> list = this.list(wrapper);
        PageInfo<Attr> pageInfo = new PageInfo<>(list);
        PageResult<Attr> pageResult = PageResult.<Attr>builder()
                .totalCount(pageInfo.getTotal())
                .totalPage((long) pageInfo.getPages())
                .items(pageInfo.getList())
                .build();
        return pageResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveAttr(AttrVo attrVo) {
        Attr _attr = new Attr();
        BeanUtils.copyProperties(attrVo, _attr);
        // 保存基本数据
        boolean save = this.save(_attr);
        // 保存关联关系
        AttrAttrgroupRelation relation = new AttrAttrgroupRelation();
        relation.setAttrId(attrVo.getAttrId());
        relation.setAttrGroupId(attrVo.getAttrGroupId());
        int insert = 0;
        if (ObjectUtils.isNotEmpty(relation)) {
            insert = attrAttrgroupRelationMapper.insert(relation);
        }
        return save;
    }

    @Override
    public PageResult<Attr> queryPage(PageDomain pageDomain, Attr attr, String key, Long catelogId) {
        pageDomain.startPage();
        QueryWrapper<Attr> wrapper = new QueryWrapper<>(attr);

        if (!Objects.equals(catelogId, 0L)) {
            wrapper.eq("catelog_id", catelogId);
        }

        if (StringUtils.isNotBlank(key)) {
            wrapper.and(attrQueryWrapper -> {
                attrQueryWrapper.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        return this.getPageResult(wrapper);
    }
}
