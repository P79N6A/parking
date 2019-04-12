package com.zoeeasy.cloud.core.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 操作审计日志
 *
 * @author walkman
 */
@Data
public class AuditLogModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 登录名
     */
    private String account;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    private Integer type;

    /**
     * 操作方式
     */
    private Integer logType;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户
     */
    private Integer operatorType;

    /**
     * 日志标题
     */
    private String title;

    /**
     * 操作IP地址
     */
    private String remoteAddress;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 请求URI
     */
    private String requestUrl;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 日志内容
     */
    private String content;

    /**
     * 操作状态（0正常 1异常）
     */
    private Integer status;

    /**
     * 操作提交的数据
     */
    private String params;

    /**
     * 异常信息
     */
    private String exception;

}
