package cn.alphahub.common.core.page;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义分页查询结果集对象
 *
 * @param <T> 分页实体对象
 * @author lwj
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 总条数
     */
    private Long totalCount;

    /**
     * 总页数
     */
    private Long totalPage;

    /**
     * 当前页数据
     */
    private List<T> items;

    /**
     * 设置分页数据
     */
    public void startPage(PageDomain pageDomain) {
        pageDomain.startPage();
    }

    /**
     * 返回泛型T的分页列表数据
     *
     * @param list DAO|Mapper执行select语句后的list<T>集合
     * @return 返回泛型T的分页数据
     */
    public PageResult<T> getPage(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return PageResult.<T>builder()
                .totalCount(pageInfo.getTotal())
                .totalPage((long) pageInfo.getPages())
                .items(pageInfo.getList())
                .build();
    }
}
