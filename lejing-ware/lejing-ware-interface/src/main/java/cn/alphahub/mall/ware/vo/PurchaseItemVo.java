package cn.alphahub.mall.ware.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 采购项-视图对象
 *
 * @author liuwenjing
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseItemVo {

    /**
     * 采购项id
     */
    private Long itemId;

    /**
     * 采购项状态
     */
    private Integer status;

    /**
     * 原因
     */
    private String reason;
}
