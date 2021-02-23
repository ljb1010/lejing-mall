package cn.alphahub.mall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseAttrs {

    private Long attrId;

    private String attrValues;

    private int showDesc;
}
