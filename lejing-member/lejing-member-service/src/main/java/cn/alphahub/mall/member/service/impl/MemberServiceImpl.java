package cn.alphahub.mall.member.service.impl;

import cn.alphahub.common.core.page.PageDomain;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.member.domain.Member;
import cn.alphahub.mall.member.mapper.MemberMapper;
import cn.alphahub.mall.member.service.MemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员Service业务层处理
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 16:15:38
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    /**
     * 查询会员分页列表
     *
     * @param pageDomain 分页数据
     * @param member     分页对象
     * @return 会员分页数据
     */
    @Override
    public PageResult<Member> queryPage(PageDomain pageDomain, Member member) {
        // 1. 构造mybatis-plus查询wrapper
        QueryWrapper<Member> wrapper = new QueryWrapper<>(member);
        // 2. 创建一个分页对象
        PageResult<Member> pageResult = new PageResult<>();
        // 3. 开始分页
        pageResult.startPage(pageDomain);
        // 4. 执行Dao|Mapper SQL查询
        List<Member> memberList = this.list(wrapper);
        // 5. 分装并返回数据
        return pageResult.getPage(memberList);
    }

}
