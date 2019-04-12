package com.zoeeasy.cloud.sys.visit.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2019/2/22 0022
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "VisitLogResultDto", description = "用户登录日志返回结果")
public class VisitLogResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String account;

    /**
     * 客户端ID
     */
    @ApiModelProperty("客户端ID")
    private String clientId;

    /**
     * 客户端IP
     */
    @ApiModelProperty("客户端IP")
    private String clientIp;

    /**
     * 客户端系统版本
     */
    @ApiModelProperty("客户端系统版本")
    private String clientOSVersion;

    /**
     * 客户端操作系统位数
     */
    @ApiModelProperty("客户端操作系统位数")
    private String clientOSArch;

    /**
     * 客户端系统名称
     */
    @ApiModelProperty("客户端系统名称")
    private String clientOSName;

    /**
     * 浏览器基本信息
     */
    @ApiModelProperty("浏览器基本信息")
    private String clientAgent;

    /**
     * 客户端发出请求时的完整URL
     */
    @ApiModelProperty("客户端发出请求时的完整URL")
    private String requestUrl;

    /**
     * 请求行中的资源名部分
     */
    @ApiModelProperty("请求行中的资源名部分")
    private String requestUri;

    /**
     * 客户机请求方式
     */
    @ApiModelProperty("客户机请求方式")
    private String requestVerb;

    /**
     * WEB服务器的IP地址
     */
    @ApiModelProperty("WEB服务器的IP地址")
    private String localAddress;

    /**
     * WEB服务器的主机名
     */
    @ApiModelProperty("WEB服务器的主机名")
    private String localName;

    /**
     * 当前会话请求的sessionId
     */
    @ApiModelProperty("当前会话请求的sessionId")
    private String sessionId;

    /**
     * 访问令牌
     */
    @ApiModelProperty("访问令牌")
    private String accessToken;

    /**
     * 访问令牌
     */
    @ApiModelProperty("访问令牌")
    private String refreshToken;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date creationTime;
}
