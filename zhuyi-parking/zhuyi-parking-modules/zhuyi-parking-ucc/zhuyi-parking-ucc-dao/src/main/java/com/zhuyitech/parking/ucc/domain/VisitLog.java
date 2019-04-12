package com.zhuyitech.parking.ucc.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.CreationAuditedEntity;

import java.util.Date;


/**
 * 用户登录/访问日志
 *
 * @author walkman
 */
@TableName("up_visit_log")
public class VisitLog extends CreationAuditedEntity<Long> {

    private static final long serialVersionUID = -2300573958701067777L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登录用户ID
     */
    @TableField(value = "userId")
    private Long userId;

    /**
     * 用户来自哪个系统
     */
    @TableField(value = "clientId")
    private String clientId;

    /**
     * 客户端IP
     */
    @TableField(value = "clientIp")
    private String clientIp;

    /**
     * 客户端系统版本
     */
    @TableField(value = "clientOSVersion")
    private String clientOSVersion;

    /**
     * 客户端操作系统位数
     */
    @TableField(value = "clientOSArch")
    private String clientOSArch;

    /**
     * 客户端系统名称
     */
    @TableField(value = "clientOSName")
    private String clientOSName;

    /**
     * 浏览器基本信息
     */
    @TableField(value = "clientAgent")
    private String clientAgent;

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
     * 当前会话请求的sessionId，用于判断当前会话是否是同一个会话，避免重复记录
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
    protected Long creatorUserId;

    /**
     * 创建日期
     */
    @TableField(value = "creationTime", fill = FieldFill.INSERT)
    protected Date creationTime;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientOSVersion() {
        return clientOSVersion;
    }

    public void setClientOSVersion(String clientOSVersion) {
        this.clientOSVersion = clientOSVersion;
    }

    public String getClientOSArch() {
        return clientOSArch;
    }

    public void setClientOSArch(String clientOSArch) {
        this.clientOSArch = clientOSArch;
    }

    public String getClientOSName() {
        return clientOSName;
    }

    public void setClientOSName(String clientOSName) {
        this.clientOSName = clientOSName;
    }

    public String getClientAgent() {
        return clientAgent;
    }

    public void setClientAgent(String clientAgent) {
        this.clientAgent = clientAgent;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getRequestVerb() {
        return requestVerb;
    }

    public void setRequestVerb(String requestVerb) {
        this.requestVerb = requestVerb;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public Long getCreatorUserId() {
        return creatorUserId;
    }

    @Override
    public void setCreatorUserId(Long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    @Override
    public Date getCreationTime() {
        return creationTime;
    }

    @Override
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
