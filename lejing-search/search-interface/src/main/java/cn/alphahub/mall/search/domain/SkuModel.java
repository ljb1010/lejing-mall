package cn.alphahub.mall.search.domain;

import cn.alphahub.common.util.IdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品SKU信息-数据传输模型
 * <b>elasticsearch索引库的数据结构</b>
 *
 * @author liuwenjing
 * @date 2021年3月7日
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "product", shards = 1, replicas = 1, createIndex = true)
public class SkuModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品最小规格属性id - sku id
     */
    @Id
    @Field(type = FieldType.Long)
    @JsonSerialize(using = IdSerializer.class)
    private Long skuId;

    /**
     * 商品标准单元id - spu id
     */
    @Field(type = FieldType.Long)
    @JsonSerialize(using = IdSerializer.class)
    private Long spuId;

    /**
     * 商品名称
     */
    @Field(type = FieldType.Text, index = false, analyzer = "ik_smart", searchAnalyzer = "ik_max_word")
    private String skuTitle;

    /**
     * 商品价格
     */
    @Field(type = FieldType.Keyword)
    private BigDecimal skuPrice;

    /**
     * 商品图片
     */
    @Field(type = FieldType.Keyword, index = false)
    private String skuImg;

    /**
     * 商品销量
     */
    @Field(type = FieldType.Long)
    private Long saleCount;

    /**
     * 是否有库存
     */
    @Field(type = FieldType.Boolean)
    private Boolean hasStock;

    /**
     * 热卖数量
     */
    @Field(type = FieldType.Long)
    private Long hotScore;

    /**
     * 商品品牌id
     */
    @Field(type = FieldType.Long)
    @JsonSerialize(using = IdSerializer.class)
    private Long brandId;

    /**
     * 商品品牌名称
     */
    @Field(type = FieldType.Keyword)
    private String brandName;

    /**
     * 商品品牌图片
     */
    @Field(type = FieldType.Keyword, index = false)
    private String brandImg;

    /**
     * 商品分类id
     */
    @Field(type = FieldType.Long)
    @JsonSerialize(using = IdSerializer.class)
    private Long catalogId;
    /**
     * 商品分类名称
     */
    @Field(type = FieldType.Keyword, index = false)
    private String catalogName;

    /**
     * 商品属性列表
     */
    @Field(type = FieldType.Nested, index = false)
    private List<Attrs> attrs;

}
