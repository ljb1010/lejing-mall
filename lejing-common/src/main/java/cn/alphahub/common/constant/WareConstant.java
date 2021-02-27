package cn.alphahub.common.constant;

import lombok.Getter;

/**
 * 库存常量类
 *
 * @author liuwenjing
 */
public class WareConstant {

    /**
     * 采购单状态枚举类
     */
    @Getter
    public enum PurchaseStatusEnum {
        /**
         * 0 新建
         */
        CREATED(0, "新建"),
        /**
         * 1 已分配
         */
        ASSIGNED(1, "已分配"),
        /**
         * 2 已领取
         */
        RECEIVED(2, "已领取"),
        /**
         * 3 已完成
         */
        COMPLETED(3, "已完成"),
        /**
         * 4 有异常
         */
        HAS_ERROR(4, "有异常");

        /**
         * 属性类型码
         */
        private final Integer code;

        /**
         * 属性类型名称
         */
        private final String name;

        PurchaseStatusEnum(Integer code, String name) {
            this.code = code;
            this.name = name;
        }
    }

    /**
     * 采购需求状态枚举类
     */
    @Getter
    public enum PurchaseDemandStatusEnum {
        /**
         * 0 新建
         */
        CREATED(0, "新建"),
        /**
         * 1 已分配
         */
        ASSIGNED(1, "已分配"),
        /**
         * 2 正在采购
         */
        PURCHASING(2, "正在采购"),
        /**
         * 3 已完成
         */
        COMPLETED(3, "已完成"),
        /**
         * 4 采购失败
         */
        HAS_ERROR(4, "采购失败");

        /**
         * 属性类型码
         */
        private final Integer code;

        /**
         * 属性类型名称
         */
        private final String name;

        PurchaseDemandStatusEnum(Integer code, String name) {
            this.code = code;
            this.name = name;
        }
    }
}
