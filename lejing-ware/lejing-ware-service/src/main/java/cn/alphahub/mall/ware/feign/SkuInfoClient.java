package cn.alphahub.mall.ware.feign;

import cn.alphahub.mall.product.api.SkuInfoApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * sku信息 - feign客户端
 *
 * @author Weasley J
 */
@FeignClient(value = "lejing-product")
public interface SkuInfoClient extends SkuInfoApi {

}
