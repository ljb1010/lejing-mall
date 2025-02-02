package cn.alphahub.mall.ware.domain;

import cn.alphahub.common.util.IdSerializer;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 撤销日志表
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-24 15:19:57
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("undo_log")
public class UndoLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId
    @JsonSerialize(using = IdSerializer.class)
    private Long id;

    /**
     * 相关的branch id
     */
    @JsonSerialize(using = IdSerializer.class)
    private Long branchId;

    /**
     * 相关的xid
     */
    private String xid;

    /**
     * 内容
     */
    private String context;

    /**
     * 回滚信息
     */
    private String rollbackInfo;

    /**
     * 日志状态码
     */
    private Integer logStatus;

    /**
     * 日志创建时间
     */
    private Date logCreated;

    /**
     * 日志修改时间
     */
    private Date logModified;

    /**
     * 其他信息
     */
    private String ext;

}
