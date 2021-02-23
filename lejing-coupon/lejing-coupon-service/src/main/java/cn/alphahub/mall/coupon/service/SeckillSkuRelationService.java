package cn.alphahub.mall.coupon.service;

import cn.alphahub.common.core.service.PageService;
import cn.alphahub.common.to.SkuReductionTO;
import cn.alphahub.mall.coupon.domain.SeckillSkuRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 秒杀活动商品关联Service接口
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-07 22:41:47
 */
public interface SeckillSkuRelationService extends IService<SeckillSkuRelation>, PageService<SeckillSkuRelation> {

}
