package cn.alphahub.mall.coupon.service;

import cn.alphahub.common.core.service.PageService;
import cn.alphahub.common.to.SkuReductionTO;
import cn.alphahub.mall.coupon.domain.SkuFullReduction;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 商品满减信息Service接口
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-07 22:41:47
 */
public interface SkuFullReductionService extends IService<SkuFullReduction>, PageService<SkuFullReduction> {

    /**
     * 保存满减、优惠信息
     *
     * @param skuReductionTo
     */
    Boolean saveSkuReduction(SkuReductionTO skuReductionTo);
}
