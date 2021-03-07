package cn.alphahub.mall.ware.service;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.ware.domain.WareSku;
import cn.alphahub.mall.ware.vo.WareSkuVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 商品库存Service接口
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:19:57
 */
public interface WareSkuService extends IService<WareSku> {

    /**
     * 查询商品库存分页列表
     *
     * @param pageDomain 分页数据
     * @param wareSku    分页对象
     * @return 商品库存分页数据
     */
    PageResult<WareSku> queryPage(PageDomain pageDomain, WareSku wareSku);


    /**
     * 更新库存信息
     *
     * @param skuId  产品skuId
     * @param wareId 库存id
     * @param skuNum 添加的库存量
     */
    Integer addStock(Long skuId, Long wareId, Integer skuNum);

    /**
     * 查看是否有库存
     *
     * @param skuIds sku id 集合
     * @return 商品库存列表
     */
    List<WareSkuVO> getSkuHasStock(List<Long> skuIds);
}
