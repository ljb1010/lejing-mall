package cn.alphahub.mall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 商品属性-响应视图对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AttrRespVO extends AttrVO {
    /**
     * "catelogName": "手机/数码/手机", //所属分类名字
     */
    private String catelogName;

    /**
     * "groupName": "主体", //所属分组名字
     */
    private String groupName;

    private Long[] catelogPath;
}
