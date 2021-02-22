package cn.alphahub.mall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 属性分组-视图对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttrGroupVO {
    /**
     * 属性id
     */
    private Long attrId;

    /**
     * 属性分组id
     */
    private Long attrGroupId;
}
