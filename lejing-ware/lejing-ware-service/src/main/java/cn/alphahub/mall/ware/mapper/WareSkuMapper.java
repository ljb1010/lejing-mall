package cn.alphahub.mall.ware.mapper;

import cn.alphahub.mall.ware.domain.WareSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品库存
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:19:57
 */
@Mapper
public interface WareSkuMapper extends BaseMapper<WareSku> {

    /**
     * 更新库存信息
     *
     * @param skuId  产品skuId
     * @param wareId 库存id
     * @param skuNum 添加的库存量
     */
    Integer addStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("skuNum") Integer skuNum);

    /**
     * 查当前sku可用库存总量: 每个仓库的总库存量 - 每个仓库锁定的总库存量
     *
     * @param skuId skuId
     * @return 可用库存总量
     */
    Integer getSkuStockBySkuId(@Param("skuId") Long skuId);
}
