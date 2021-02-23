package cn.alphahub.mall.coupon.service.impl;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.common.to.MemberPriceTO;
import cn.alphahub.common.to.SkuReductionTO;
import cn.alphahub.mall.coupon.domain.MemberPrice;
import cn.alphahub.mall.coupon.domain.SkuFullReduction;
import cn.alphahub.mall.coupon.domain.SkuLadder;
import cn.alphahub.mall.coupon.mapper.SkuFullReductionMapper;
import cn.alphahub.mall.coupon.service.MemberPriceService;
import cn.alphahub.mall.coupon.service.SkuFullReductionService;
import cn.alphahub.mall.coupon.service.SkuLadderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品满减信息Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-07 22:41:47
 */
@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionMapper, SkuFullReduction> implements SkuFullReductionService {

    @Resource
    private SkuLadderService skuLadderService;
    @Resource
    private MemberPriceService memberPriceService;

    /**
     * 查询商品满减信息分页列表
     *
     * @param pageDomain       分页数据
     * @param skuFullReduction 分页对象
     * @return 商品满减信息分页数据
     */
    @Override
    public PageResult<SkuFullReduction> queryPage(PageDomain pageDomain, SkuFullReduction skuFullReduction) {
        pageDomain.startPage();
        QueryWrapper<SkuFullReduction> wrapper = new QueryWrapper<>(skuFullReduction);
        List<SkuFullReduction> list = this.list(wrapper);
        PageInfo<SkuFullReduction> pageInfo = new PageInfo<>(list);
        PageResult<SkuFullReduction> pageResult = PageResult.<SkuFullReduction>builder()
                .totalCount(pageInfo.getTotal())
                .totalPage((long) pageInfo.getPages())
                .items(pageInfo.getList())
                .build();
        return pageResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveSkuReduction(SkuReductionTO reductionTO) {
        // 1. 保存满减、打折、会员价
        //  5.4）、sku的优惠、满减等信息；lejing_sms -> sms_sku_ladder|sms_sku_full_reduction|sms_member_price
        // insert into sms_sku_ladder
        SkuLadder skuLadder = new SkuLadder();
        skuLadder.setSkuId(reductionTO.getSkuId());
        skuLadder.setFullCount(reductionTO.getFullCount());
        skuLadder.setDiscount(reductionTO.getDiscount());
        skuLadder.setAddOther(reductionTO.getCountStatus());
        boolean b1 = skuLadderService.save(skuLadder);

        // insert into sms_sku_full_reduction
        SkuFullReduction reduction = new SkuFullReduction();
        BeanUtils.copyProperties(reductionTO, reduction);
        boolean b2 = this.save(reduction);

        // insert into sms_member_price
        List<MemberPriceTO> memberPrice = reductionTO.getMemberPrice();
        List<MemberPrice> memberPrices = memberPrice.stream().map(item -> MemberPrice.builder()
                .skuId(reductionTO.getSkuId())
                .memberLevelId(item.getId())
                .memberLevelName(item.getName())
                .memberPrice(item.getPrice())
                .addOther(1)
                .build()).collect(Collectors.toList());
        boolean b3 = memberPriceService.saveBatch(memberPrices);

        return b1 && b2 && b3;
    }
}
