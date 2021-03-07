package cn.alphahub.mall.search.repository;

import cn.alphahub.mall.search.domain.SkuModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 商品-持久层接口
 *
 * @author Weasley J
 * @date 2021年3月7日
 */
@Repository
public interface ProductRepository extends ElasticsearchRepository<SkuModel, Long> {

}
