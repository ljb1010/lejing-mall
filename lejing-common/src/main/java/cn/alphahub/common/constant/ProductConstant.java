package cn.alphahub.common.constant;

import lombok.Getter;

/**
 * 商品相关常量类
 *
 * @author liuwenjing
 */
public class ProductConstant {

    /**
     * 商品属性枚举类
     */
    @Getter
    public enum AttrEnum {

        /**
         * 1 基本属性
         */
        BASE(1, "基本属性"),
        /**
         * 0 销售属性
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

    /**
     * 商品状态枚举
     */
    @Getter
    public enum StatusEnum {
        /**
         * 新建商品
         */
        NEW_SPU(0, "新建商品"),
        /**
         * 商品上架
         */
        SPU_UP(1, "商品上架"),
        /**
         * 商品下架
         */
        SPU_DOWN(2, "商品下架");

        /**
         * 属性类型码
         */
        private final Integer code;

        /**
         * 属性类型名称
         */
        private final String name;

        StatusEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

    }
}
