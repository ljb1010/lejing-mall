package cn.alphahub.mall.ware.service;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.ware.domain.WareInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 仓库信息Service接口
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:19:57
 */
public interface WareInfoService extends IService<WareInfo> {

    /**
     * 查询仓库信息分页列表
     *
     * @param pageDomain 分页数据
     * @param wareInfo   分页对象
     * @return 仓库信息分页数据
     */
    PageResult<WareInfo> queryPage(PageDomain pageDomain, WareInfo wareInfo);

}
