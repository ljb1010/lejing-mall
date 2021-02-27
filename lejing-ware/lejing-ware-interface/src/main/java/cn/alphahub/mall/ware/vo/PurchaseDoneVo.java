package cn.alphahub.mall.ware.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 完成采购-值对象
 *
 * @author liuwenjign
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDoneVo {

    /**
     * 采购单id
     */
    @NotNull
    private Long id;

    /**
     * 采购项列表
     */
    private List<PurchaseItemVo> items;

}
