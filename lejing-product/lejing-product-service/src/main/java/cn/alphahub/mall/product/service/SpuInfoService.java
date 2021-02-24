package cn.alphahub.mall.product.service;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.product.domain.SpuInfo;
import cn.alphahub.mall.product.vo.SpuSaveVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * spu信息Service接口
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:36:31
 */
public interface SpuInfoService extends IService<SpuInfo> {

    void saveSpuInfo(SpuSaveVO spuSaveVO);

    /**
     * 查询spu信息分页列表
     *
     * @param pageDomain 分页数据
     * @param spuInfo    分页对象
     * @return spu信息分页数据
     */
    PageResult<SpuInfo> queryPage(PageDomain pageDomain, SpuInfo spuInfo);
}
