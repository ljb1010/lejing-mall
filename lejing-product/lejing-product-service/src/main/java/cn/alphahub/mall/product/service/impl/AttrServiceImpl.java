package cn.alphahub.mall.product.service.impl;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.Attr;
import cn.alphahub.mall.product.domain.AttrAttrgroupRelation;
import cn.alphahub.mall.product.domain.AttrGroup;
import cn.alphahub.mall.product.domain.Category;
import cn.alphahub.mall.product.mapper.AttrAttrgroupRelationMapper;
import cn.alphahub.mall.product.mapper.AttrGroupMapper;
import cn.alphahub.mall.product.mapper.AttrMapper;
import cn.alphahub.mall.product.mapper.CategoryMapper;
import cn.alphahub.mall.product.service.AttrService;
import cn.alphahub.mall.product.service.CategoryService;
import cn.alphahub.mall.product.vo.AttrRespVo;
import cn.alphahub.mall.product.vo.AttrVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Resource
    private AttrGroupMapper attrGroupMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private CategoryService categoryService;

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

    @Override
    public PageResult<AttrRespVo> queryPage(PageDomain pageDomain, Attr attr, String key, Long catelogId) {
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

        // 处理数据
        PageResult<Attr> pageResult = this.getPageResult(wrapper);
        List<AttrRespVo> respVos = pageResult.getItems().stream().map(attr2 -> {
            AttrRespVo respVo = new AttrRespVo();
            BeanUtils.copyProperties(attr2, respVo);

            // 设置分类和分组名
            AttrAttrgroupRelation attrgroupRelation = this.attrAttrgroupRelationMapper.selectOne(
                    new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", respVo.getAttrId())
            );
            if (Objects.nonNull(attrgroupRelation)) {
                Long attrGroupId = attrgroupRelation.getAttrGroupId();
                AttrGroup attrGroup = attrGroupMapper.selectById(attrGroupId);
                respVo.setGroupName(attrGroup.getAttrGroupName());
            }
            Category category = categoryMapper.selectById(attr2.getCatelogId());
            if (Objects.nonNull(category)) {
                respVo.setCatelogName(category.getName());
            }
            return respVo;
        }).collect(Collectors.toList());

        // 重新构造一个分页对象
        return PageResult.<AttrRespVo>builder()
                .totalCount(pageResult.getTotalCount())
                .totalPage(pageResult.getTotalPage())
                .items(respVos)
                .build();
    }

    private PageResult<Attr> getPageResult(QueryWrapper<Attr> wrapper) {
        List<Attr> list = this.list(wrapper);
        PageInfo<Attr> pageInfo = new PageInfo<>(list);
        return PageResult.<Attr>builder()
                .totalCount(pageInfo.getTotal())
                .totalPage((long) pageInfo.getPages())
                .items(pageInfo.getList())
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveAttr(AttrVo attrVo) {
        Attr attr = new Attr();
        BeanUtils.copyProperties(attrVo, attr);
        // 保存基本数据
        boolean save = this.save(attr);

        int insert = 0;
        AttrAttrgroupRelation relation = new AttrAttrgroupRelation();
        relation.setAttrGroupId(attrVo.getAttrGroupId());
        relation.setAttrId(attr.getAttrId());
        if (ObjectUtils.isNotEmpty(relation)) {
            insert = attrAttrgroupRelationMapper.insert(relation);
        }

        return save && insert >= 1;
    }


    /**
     * 修改商品属性
     *
     * @param attrVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAttrById(AttrVo attrVo) {
        Attr attr = new Attr();
        BeanUtils.copyProperties(attrVo, attr);
        // 保存基本数据
        boolean update = this.updateById(attr);
        int insetIfNotExist = 0;

        AttrAttrgroupRelation relation = new AttrAttrgroupRelation();
        relation.setAttrId(attr.getAttrId());
        relation.setAttrGroupId(attrVo.getAttrGroupId());

        if (ObjectUtils.isNotEmpty(relation)) {
            Integer count = attrAttrgroupRelationMapper.selectCount(
                    new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", attr.getAttrId())
            );
            insetIfNotExist = count > 0 ?
                    attrAttrgroupRelationMapper.update(
                            relation, new UpdateWrapper<AttrAttrgroupRelation>().eq("attr_id", attr.getAttrId())
                    ) : attrAttrgroupRelationMapper.insert(relation);
        }
        return update && insetIfNotExist >= 1;
    }

    /**
     * 获取商品属性详情
     *
     * @param attrId 商品属性主键id
     * @return 商品属性详细信息
     */
    @Override
    public AttrRespVo getAttrInfoById(Long attrId) {
        AttrRespVo attrRespVo = new AttrRespVo();
        Attr attr = this.getById(attrId);
        BeanUtils.copyProperties(attr, attrRespVo);
        AttrAttrgroupRelation attrgroupRelation = attrAttrgroupRelationMapper.selectOne(
                new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", attrId)
        );
        AttrGroup attrGroup = attrGroupMapper.selectById(attrId);
        Long[] catelogFullPath = categoryService.getCatelogFullPath(attr.getCatelogId());
        Category category = categoryMapper.selectById(attr.getCatelogId());
        // 组合组装数据
        attrRespVo.setCatelogPath(catelogFullPath);
        attrRespVo.setCatelogName(Objects.nonNull(category) ? category.getName() : null);
        attrRespVo.setAttrGroupId(Objects.nonNull(attrgroupRelation) ? attrgroupRelation.getAttrGroupId() : null);
        attrRespVo.setGroupName(Objects.nonNull(attrGroup) ? attrGroup.getAttrGroupName() : null);
        return attrRespVo;
    }
}
