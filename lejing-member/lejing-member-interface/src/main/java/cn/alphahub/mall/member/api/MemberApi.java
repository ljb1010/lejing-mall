package cn.alphahub.mall.member.api;

import cn.alphahub.common.core.domain.BaseResult;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.member.domain.Member;
import org.springframework.web.bind.annotation.*;

/**
 * 会员Controller
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 16:15:38
 */
@RestController
@RequestMapping("member/member")
public interface MemberApi {

    /**
     * 查询会员列表
     *
     * @param page        当前页码,默认第1页
     * @param rows        显示行数,默认10条
     * @param orderColumn 排序排序字段,默认不排序
     * @param isAsc       排序方式,desc或者asc
     * @param member      会员, 查询字段选择性传入, 默认为等值查询
     * @return 会员分页数据
     */
    @GetMapping("/list")
    BaseResult<PageResult<Member>> list(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "orderColumn", defaultValue = "") String orderColumn,
            @RequestParam(value = "isAsc", defaultValue = "") String isAsc,
            Member member
    );

    /**
     * 获取会员详情
     *
     * @param id 会员主键id
     * @return 会员详细信息
     */
    @GetMapping("/info/{id}")
    BaseResult<Member> info(@PathVariable("id") Long id);

    /**
     * 新增会员
     *
     * @param member 会员元数据
     * @return 成功返回true, 失败返回false
     */
    @PostMapping("/save")
    BaseResult<Boolean> save(@RequestBody Member member);

    /**
     * 修改会员
     *
     * @param member 会员, 根据id选择性更新
     * @return 成功返回true, 失败返回false
     */
    @PutMapping("/update")
    BaseResult<Boolean> update(@RequestBody Member member);

    /**
     * 批量删除会员
     *
     * @param ids 会员id集合
     * @return 成功返回true, 失败返回false
     */
    @DeleteMapping("/delete/{ids}")
    BaseResult<Boolean> delete(@PathVariable Long[] ids);
}
