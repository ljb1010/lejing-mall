package cn.alphahub.mall.member.controller;

import cn.alphahub.common.constant.HttpStatus;
import cn.alphahub.common.core.controller.BaseController;
import cn.alphahub.common.core.domain.BaseResult;
import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.coupon.domain.Coupon;
import cn.alphahub.mall.member.domain.Member;
import cn.alphahub.mall.member.feign.CouponClient;
import cn.alphahub.mall.member.service.MemberService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 会员Controller
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-14 18:59:58
 */
@RestController
@RequestMapping("member/member")
public class MemberController extends BaseController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private CouponClient couponClient;

    /**
     * 查询会员列表
     *
     * @param page        当前页码,默认第1页
     * @param rows        显示行数,默认10条
     * @param orderColumn 排序排序字段,默认不排序
     * @param isAsc       排序方式,desc或者asc
     * @param member      会员,查询字段选择性传入,默认为等值查询
     * @return 会员分页数据
     */
    @GetMapping("/list")
    public BaseResult<PageResult<Member>> list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "orderColumn", defaultValue = "") String orderColumn,
            @RequestParam(value = "isAsc", defaultValue = "") String isAsc,
            Member member
    ) {
        PageDomain pageDomain = new PageDomain(page, rows, orderColumn, isAsc);
        PageResult<Member> pageResult = memberService.queryPage(pageDomain, member);
        if (ObjectUtils.isNotEmpty(pageResult.getItems())) {
            return BaseResult.ok(pageResult);
        }
        return BaseResult.fail(HttpStatus.NOT_FOUND, "查询结果为空");
    }

    /**
     * 获取会员详情
     *
     * @param id 会员主键id
     * @return 会员详细信息
     */
    @GetMapping("/info/{id}")
    public BaseResult<Member> info(@PathVariable("id") Long id) {
        Member member = memberService.getById(id);
        return ObjectUtils.anyNotNull(member) ? BaseResult.ok(member) : BaseResult.fail();
    }

    /**
     * 新增会员
     *
     * @param member 会员元数据
     * @return 成功返回true, 失败返回false
     */
    @PostMapping("/save")
    public BaseResult<Boolean> save(@RequestBody Member member) {
        boolean save = memberService.save(member);
        return toOperationResult(save);
    }

    /**
     * 修改会员
     *
     * @param member 会员,根据id选择性更新
     * @return 成功返回true, 失败返回false
     */
    @PutMapping("/update")
    public BaseResult<Boolean> update(@RequestBody Member member) {
        boolean update = memberService.updateById(member);
        return toOperationResult(update);
    }

    /**
     * 批量删除会员
     *
     * @param ids 会员id集合
     * @return 成功返回true, 失败返回false
     */
    @DeleteMapping("/delete/{ids}")
    public BaseResult<Boolean> delete(@PathVariable Long[] ids) {
        boolean delete = memberService.removeByIds(Arrays.asList(ids));
        return toOperationResult(delete);
    }

    /**
     * 获取优惠券信息-测试feign远程调用
     *
     * @param couponId coupon id
     * @return 优惠券
     */
    @GetMapping("/coupon/{couponId}")
    public Coupon getMemberCoupon(@PathVariable("couponId") Long couponId) {
        BaseResult<Coupon> info = couponClient.info(couponId);
        return ObjectUtils.isNotEmpty(info) ? doConvertType(info, Coupon.class) : null;
    }

    /**
     * 查询优惠券信息列表-测试feign远程调用
     *
     * @param page        当前页码,默认第1页
     * @param rows        显示行数,默认10条
     * @param orderColumn 排序排序字段,默认不排序
     * @param isAsc       排序方式,desc或者asc
     * @param coupon      优惠券信息,字段选择性传入,默认为等值查询
     * @return 优惠券信息分页数据
     */
    @PostMapping("/coupon/list")
    public BaseResult<PageResult<Coupon>> getCouponList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "orderColumn", defaultValue = "") String orderColumn,
            @RequestParam(value = "isAsc", defaultValue = "") String isAsc,
            Coupon coupon
    ) {
        return couponClient.list(page, rows, orderColumn, isAsc, coupon);
    }
}
