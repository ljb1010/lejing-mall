package cn.alphahub.mall.product.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品SKU信息-数据传输模型
 *
 * @author liuwenjing
 * @date 2021年3月7日
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkuModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品最小规格属性id - SKU ID
     */
    private Long skuId;

    /**
     * 商品标准单元id - SPU ID
     */
    private Long spuId;

    /**
     * 商品名称
     */
    private String skuTitle;

    /**
     * 商品价格
     */
    private BigDecimal skuPrice;

    /**
     * 商品图片
     */
    private String skuImg;

    /**
     * 商品销量
     */
    private Long saleCount;

    /**
     * 是否有库存
     */
    private Boolean hasStock;

    /**
     * 热卖数量
     */
    private Long hotScore;

    /**
     * 商品品牌id
     */
    private Long brandId;

    /**
     * 商品分类id
     */
    private Long catalogId;

    /**
     * 商品品牌名称
     */
    private String brandName;

    /**
     * 商品品牌图片
     */
    private String brandImg;

    /**
     * 商品分类名称
     */
    private String catalogName;

    /**
     * 商品属性列表
     */
    private List<Attrs> attrs;

    /**
     * 商品属性-数据模型
     *
     * @author liuwenjing
     * @date 2021年3月7日
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Attrs implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * 属性id
         */
        private Long attrId;

        /**
         * 属性名称
         */
        private String attrName;

        /**
         * 属性值
         */
        private String attrValue;

    }

}
