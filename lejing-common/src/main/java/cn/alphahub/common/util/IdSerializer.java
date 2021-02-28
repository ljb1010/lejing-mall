package cn.alphahub.common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * <p>id序列化器</p>
 * <b>将返回给前端的Long型id转为String，防止精度丢失</b>
 * <li>
 * 添加到实体类的主键id上，jackson在序列化是会自动序列化id为string类型<br/>
 * 用法示例：<br/>@JsonSerialize(using = IdSerializer.class)<br/>private Long id;<br/>
 * </li>
 *
 * @author liuwenjing
 * @date 2021年2月28日
 * @see com.fasterxml.jackson.databind.JsonSerializer
 */
public class IdSerializer extends JsonSerializer<Long> {

    @Override
    public void serialize(Long id, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(id.toString());
    }
}
