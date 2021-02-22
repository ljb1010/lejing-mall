package cn.alphahub.mall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 属性分组关联关系-视图对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttrGroupRelationVO {

    /**
     * 属性id
     */
    private Long attrId;

    /**
     * 属性分组id
     */
    private Long attrGroupId;
}
