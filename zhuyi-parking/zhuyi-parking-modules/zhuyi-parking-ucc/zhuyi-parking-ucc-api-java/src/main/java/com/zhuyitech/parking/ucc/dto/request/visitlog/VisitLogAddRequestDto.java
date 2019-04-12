package com.zhuyitech.parking.ucc.dto.request.visitlog;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 添加登录日志请求参数
 *
 * @author walkman
 */
@ApiModel(value = "VisitLogAddRequestDto", description = "添加登录日志请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class VisitLogAddRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 登录用户ID
     */
    @ApiModelProperty(value = "登录用户ID")
    @NotEmpty(message = "登录用户ID不能为空")
    private Long userId;

    /**
     * 客户端IP
     */
    @ApiModelProperty(value = "客户端IP")
    private String clientIp;

    /**
     * 客户端系统版本
     */
    @ApiModelProperty(value = "客户端系统版本")
    private String clientOSVersion;

    /**
     * 客户端操作系统位数
     */
    @ApiModelProperty(value = "客户端操作系统位数")
    private String clientOSArch;

    /**
     * 客户端系统名称
     */
    @ApiModelProperty(value = "客户端系统名称")
    private String clientOSName;

    /**
     * 浏览器基本信息
     */
    @ApiModelProperty(value = "浏览器基本信息")
    private String clientAgent;

    /**
     * 客户端发出请求时的完整URL
     */
    @ApiModelProperty(value = "客户端发出请求时的完整URL")
    private String requestUrl;

    /**
     * 请求行中的资源名部分
     */
    @ApiModelProperty(value = "请求行中的资源名部分")
    private String requestUri;

    /**
     * 客户机请求方式
     */
    @ApiModelProperty(value = "客户机请求方式")
    private String requestVerb;

    /**
     * WEB服务器的IP地址
     */
    @ApiModelProperty(value = "WEB服务器的IP地址")
    private String localAddress;

    /**
     * WEB服务器的主机名
     */
    @ApiModelProperty(value = "WEB服务器的主机名")
    private String localName;

    /**
     * 会话ID
     */
    @ApiModelProperty(value = "会话ID")
    private String sessionId;

    /**
     * 客户端ID
     */
    @ApiModelProperty(value = "客户端ID")
    private String clientId;

    /**
     * 访问令牌
     */
    @ApiModelProperty(value = "访问令牌")
    private String accessToken;

    /**
     * 访问令牌
     */
    @ApiModelProperty(value = "访问令牌")
    private String refreshToken;

}
