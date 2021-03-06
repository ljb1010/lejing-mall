package cn.alphahub.mall.search.domain;

import cn.alphahub.common.util.IdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

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
public class Attrs implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 属性id
     */
    @JsonSerialize(using = IdSerializer.class)
    @Field(type = FieldType.Long)
    private Long attrId;

    /**
     * 属性名称
     */
    @Field(type = FieldType.Keyword)
    private String attrName;

    /**
     * 属性值
     */
    @Field(type = FieldType.Keyword)
    private String attrValue;

}
