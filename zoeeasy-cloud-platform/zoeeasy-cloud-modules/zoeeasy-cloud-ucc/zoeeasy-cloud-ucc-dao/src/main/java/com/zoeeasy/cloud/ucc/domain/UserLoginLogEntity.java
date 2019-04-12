package com.zoeeasy.cloud.ucc.domain;

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
 * 系统用户登录日志表
 *
 * @author AkeemSuper
 * @date 2018/11/15 0015
 */
@TableName("ucc_user_login_log")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserLoginLogEntity extends CreationAuditedEntity<Long> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户id
     */
    @TableField(value = "tenantId")
    private Long tenantId;

    /**
     * 登录用户ID
     */
    @TableField(value = "userId")
    private Long userId;

    /**
     * 登陆渠道
     */
    @TableField(value = "channel")
    private Integer channel;

    /**
     * 客户端ID
     */
    @TableField(value = "clientId")
    private String clientId;

    /**
     * 客户端IP
     */
    @TableField(value = "clientIp")
    private String clientIp;

    /**
     * 设备信息
     */
    @TableField(value = "deviceInfo")
    private String deviceInfo;

    /**
     * 浏览器信息
     */
    @TableField(value = "browserInfo")
    private String browserInfo;

    /**
     * 客户端发出请求时的完整URL
     */
    @TableField(value = "requestUrl")
    private String requestUrl;

    /**
     * 请求行中的资源名部分
     */
    @TableField(value = "requestUri")
    private String requestUri;

    /**
     * 客户机请求方式
     */
    @TableField(value = "requestVerb")
    private String requestVerb;

    /**
     * WEB服务器的IP地址
     */
    @TableField(value = "localAddress")
    private String localAddress;

    /**
     * WEB服务器的主机名
     */
    @TableField(value = "localName")
    private String localName;

    /**
     * 当前会话请求的sessionId
     */
    @TableField(value = "sessionId")
    private String sessionId;

    /**
     * 访问令牌
     */
    @TableField(value = "accessToken")
    private String accessToken;

    /**
     * 访问令牌
     */
    @TableField(value = "refreshToken")
    private String refreshToken;

    /**
     * 创建者
     */
    @TableField(value = "creatorUserId", fill = FieldFill.INSERT)
    private Long creatorUserId;

    /**
     * 创建日期
     */
    @TableField(value = "creationTime", fill = FieldFill.INSERT)
    private Date creationTime;
}
