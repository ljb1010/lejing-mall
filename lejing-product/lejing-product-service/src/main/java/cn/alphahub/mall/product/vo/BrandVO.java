package cn.alphahub.mall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品id名称-视图对象
 *
 * @author Weasley J
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandVO {

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    private String brandName;
}
