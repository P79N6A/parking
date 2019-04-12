package com.zoeeasy.cloud.sys.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.CreationAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 操作日志表(sys_audit_log)表实体类
 *
 * @author AkeemSuper
 * @date 2019-02-20 17:38:58
 */
@TableName("sys_audit_log")
@Data
@EqualsAndHashCode(callSuper = true)
public class AuditLogEntity extends CreationAuditedEntity<Long> {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    @TableField("tenantId")
    private Long tenantId;

    /**
     * 用户ID
     */
    @TableField("userId")
    private Long userId;

    /**
     * 登录名
     */
    @TableField("account")
    private String account;
    
    /**
     * 日志标题
     */
    @TableField("title")
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @TableField("type")
    private Integer type;

    /**
     * 操作方式
     */
    @TableField("logType")
    private Integer logType;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户
     */
    @TableField("operatorType")
    private Integer operatorType;

    /**
     * 操作IP地址
     */
    @TableField("remoteAddress")
    private String remoteAddress;

    /**
     * 用户代理
     */
    @TableField("userAgent")
    private String userAgent;

    /**
     * 请求URI
     */
    @TableField("requestUrl")
    private String requestUrl;

    /**
     * 方法名称
     */
    @TableField("method")
    private String method;

    /**
     * 操作状态（１正常 0异常）
     */
    @TableField("status")
    private Integer status;

    /**
     * 请求参数
     */
    @TableField("params")
    private String params;

    /**
     * 日志内容
     */
    @TableField("content")
    private String content;

    /**
     * 异常信息
     */
    @TableField("exception")
    private String exception;

    /**
     * 创建者
     */
    @TableField(value = "creatorUserId", fill = FieldFill.INSERT)
    protected Long creatorUserId;

    /**
     * 创建日期
     */
    @TableField(value = "creationTime", fill = FieldFill.INSERT)
    protected Date creationTime;
}