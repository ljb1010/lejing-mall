package cn.alphahub.mall.product.service;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.common.core.service.PageService;
import cn.alphahub.mall.product.domain.Attr;
import cn.alphahub.mall.product.vo.AttrRespVo;
import cn.alphahub.mall.product.vo.AttrVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 商品属性Service接口
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-07 22:46:24
 */
public interface AttrService extends IService<Attr>, PageService<Attr> {

    /**
     * 新增商品属性
     *
     * @param attr 商品属性元数据
     * @return 成功返回true, 失败返回false
     */
    boolean saveAttr(AttrVo attr);

    PageResult<AttrRespVo> queryPage(PageDomain pageDomain, Attr attr, String key, Long catelogId);

    /**
     * 修改
     *
     * @param attrVo
     * @return
     */
    boolean updateAttrById(AttrVo attrVo);

    /**
     * 获取商品属性详情
     *
     * @param attrId 商品属性主键id
     * @return 商品属性详细信息
     */
    AttrRespVo getAttrInfoById(Long attrId);
}
