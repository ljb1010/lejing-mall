package cn.alphahub.mall.search.service.impl;

import cn.alphahub.mall.search.domain.SkuModel;
import cn.alphahub.mall.search.repository.ProductRepository;
import cn.alphahub.mall.search.service.SearchService;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <b>商品搜索业务实现</b>
 *
 * @author Weasley
 * @version 1.0
 * @date 2021/03/07
 */
@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    @Resource
    private ProductRepository repository;
    @Resource
    private ElasticsearchRestTemplate restTemplate;

    /**
     * 使用spring提供的repository模板方法保存数据至es中
     *
     * @param skuModels 商品SKU信息数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveProduct(List<SkuModel> skuModels) {

        Set<SkuModel> oldRecords = new LinkedHashSet<>(skuModels);
        Set<SkuModel> newRecords = new LinkedHashSet<>();

        if (CollectionUtils.isEmpty(skuModels)) {
            return false;
        }

        /*
         * 你不需要创建索引和映射数据类型，因为我们已经通过Elasticsearch JPA的注解指定好了
         * 索引不存在的情况restTemplate会根据实体类的注解：
         *      (1) 为该IndexOperations绑定到的实体创建索引映射
         *      (2) 将映射写入此IndexOperations绑定到的类的索引
         */

        // res返回的是保存成功的SkuModel
        Iterable<SkuModel> res = restTemplate.save(skuModels);
        for (SkuModel skuModel : res) {
            log.info("保存成功: {}", skuModel);
            newRecords.add(skuModel);
        }
        System.out.println("\n");
        Set<SkuModel> failRecords = oldRecords.stream().filter(skuModel -> !newRecords.contains(skuModel)).collect(Collectors.toCollection(LinkedHashSet::new));
        for (SkuModel skuModel : failRecords) {
            log.info("保存失败：{}", skuModel);
        }
        return oldRecords.size() == newRecords.size();
    }

    /**
     * 删除商品
     *
     * @param spuId 商品spu id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteProduct(Long spuId) {
        try {
            repository.deleteById(spuId);
            return true;
        } catch (Exception e) {
            log.error("删除商品失败：{}\n", e.getClass(), e);
            return false;
        }
    }
}
