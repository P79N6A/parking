package com.zoeeasy.cloud.sys.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.CreationAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录日志表(sys_visit_log)表实体类
 *
 * @author AkeemSuper
 * @date 2019-02-20 10:37:33
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_visit_log")
public class VisitLogEntity extends CreationAuditedEntity<Long> implements Serializable {

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
     * 客户端ID
     */
    @TableField("clientId")
    private String clientId;

    /**
     * 客户端IP
     */
    @TableField("clientIp")
    private String clientIp;

    /**
     * 客户端系统版本
     */
    @TableField("clientOSVersion")
    private String clientOSVersion;

    /**
     * 客户端操作系统位数
     */
    @TableField("clientOSArch")
    private String clientOSArch;

    /**
     * 客户端系统名称
     */
    @TableField("clientOSName")
    private String clientOSName;

    /**
     * 浏览器基本信息
     */
    @TableField("clientAgent")
    private String clientAgent;

    /**
     * 客户端发出请求时的完整URL
     */
    @TableField("requestUrl")
    private String requestUrl;

    /**
     * 请求行中的资源名部分
     */
    @TableField("requestUri")
    private String requestUri;

    /**
     * 客户机请求方式
     */
    @TableField("requestVerb")
    private String requestVerb;

    /**
     * WEB服务器的IP地址
     */
    @TableField("localAddress")
    private String localAddress;

    /**
     * WEB服务器的主机名
     */
    @TableField("localName")
    private String localName;

    /**
     * 当前会话请求的sessionId
     */
    @TableField("sessionId")
    private String sessionId;

    /**
     * 访问令牌
     */
    @TableField("accessToken")
    private String accessToken;

    /**
     * 访问令牌
     */
    @TableField("refreshToken")
    private String refreshToken;

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