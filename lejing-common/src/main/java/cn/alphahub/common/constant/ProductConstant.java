package cn.alphahub.common.constant;

import lombok.Getter;

/**
 * 商品相关常量类
 */
public class ProductConstant {

    /**
     * 商品属性枚举类
     */
    @Getter
    public enum AttrEnum {

        /**
         * 1-基本属性
         */
        BASE(1, "基本属性"),
        /**
         * 0-销售属性
         */
        SALE(0, "销售属性");

        /**
         * 属性类型码
         */
        private final Integer code;
        /**
         * 属性类型名称
         */
        private final String name;

        AttrEnum(Integer code, String name) {
            this.code = code;
            this.name = name;
        }
    }
}
