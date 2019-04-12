package com.zoeeasy.cloud.sys.visit.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.sys.visit.cts.VisitLogConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户登录日志添加请求参数
 *
 * @author AkeemSuper
 * @date 2019/2/21 0021
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "VisitLogAddRequestDto", description = "添加用户登录日志请求参数")
public class VisitLogAddRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", required = true)
    @NotBlank(message = VisitLogConstant.USER_ID_NOT_EMPTY)
    private Long userId;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名", required = true)
    @NotBlank(message = VisitLogConstant.ACCOUNT_NOT_EMPTY)
    private String account;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    private Long tenantId;

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
}
