package cn.alphahub.mall.product.service;

import cn.alphahub.common.core.service.PageService;
import cn.alphahub.mall.product.domain.AttrAttrgroupRelation;
import cn.alphahub.mall.product.vo.AttrGroupRelationVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 属性&属性分组关联Service接口
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-07 22:46:24
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelation>, PageService<AttrAttrgroupRelation> {

    /**
     * 新增属性分组关联关系
     *
     * @param relationVos 属性分组集合
     */
    Boolean addBatchRelations(List<AttrGroupRelationVO> relationVos);
}
