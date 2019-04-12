package com.zoeeasy.cloud.pms.dock.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.scapegoat.infrastructure.validate.annotaion.validation.IP;
import com.zoeeasy.cloud.pms.dock.cts.DockConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author AkeemSuper
 * @date 2018/12/4 0004
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RegisterParkingRequestDto", description = "客户端系统注册请求参数")
public class RegisterParkingRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Long tenantId;

    /**
     * 对接终端编号
     */
    @ApiModelProperty(value = "对接终端编号", required = true)
    @NotBlank(message = DockConstant.TERMINATE_CODE_EMPTY)
    private String terminateCode;

    /**
     * 对接终端名称
     */
    @ApiModelProperty(value = "对接终端名称", required = true)
    @NotBlank(message = DockConstant.TERMINATE_NAME_EMPTY)
    private String terminateName;

    /**
     * 对接终端版本号
     */
    @ApiModelProperty(value = "对接终端版本号", required = true)
    @NotBlank(message = DockConstant.TERMINATE_VERSION_EMPTY)
    private String terminateVersion;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 安全秘钥
     */
    @ApiModelProperty(value = "安全秘钥")
    private String accessSecrete;

    /**
     * token值
     */
    @ApiModelProperty(value = "token值")
    private String token;

    /**
     * token有效开始时间
     */
    @ApiModelProperty(value = "token有效开始时间")
    private String startTime;

    /**
     * token有效截止时间
     */
    @ApiModelProperty(value = "token有效截止时间")
    private String endTime;

    /**
     * 请求接入的网域类型，1-公网，2-专网
     */
    @ApiModelProperty("请求接入的网域类型，1-公网，2-专网")
    private Integer netZoneType;

    /**
     * 对接终端/平台的IP
     */
    @ApiModelProperty(value = "对接终端/平台的IP", required = true)
    @NotBlank(message = DockConstant.PLATFORM_IP_EMPTY)
    @IP
    private String platformIp;

    /**
     * 对接终端/平台的端口
     */
    @ApiModelProperty(value = "对接终端/平台的端口", required = true)
    @NotNull(message = DockConstant.PLATFORM_PORT_EMPTY)
    @Range(max = DockConstant.INT_MAX, message = DockConstant.PORT_VALUE_OUT_RANGE)
    private Integer platformPort;

    /**
     * 终端接入的通讯协议版本号
     */
    @ApiModelProperty(value = "对接终端/终端接入的通讯协议版本号")
    private String protocolVersion;

    /**
     * 对接终端/平台注册的账单下发请求URL
     */
    @ApiModelProperty(value = "对接终端/平台注册的账单下发请求URL", required = true)
    @NotBlank(message = DockConstant.PLACE_ORDER_URL_EMPTY)
    private String placeOrderUrl;

    /**
     * 对接终端/平台注册的账单支付通知URL
     */
    @ApiModelProperty(value = "对接终端/平台注册的账单支付通知URL", required = true)
    @NotBlank(message = DockConstant.NOTIFY_ORDER_URL_EMPTY)
    private String notifyOrderUrl;

    /**
     * 对接终端/平台注册的远程开闸请求接口URL
     */
    @ApiModelProperty(value = "对接终端/平台注册的远程开闸请求接口URL", required = true)
    @NotBlank(message = DockConstant.OPEN_STROBE_URL_EMPTY)
    private String openStrobeUrl;

    /**
     * 心跳时间间隔：单位秒
     */
    @ApiModelProperty(value = "心跳时间间隔：单位秒", required = true)
    @NotNull(message = DockConstant.HEART_BEAT_INTERVAL_EMPTY)
    @Range(max = DockConstant.SMALL_INT_MAX, message = DockConstant.HEART_VALUE_OUT_RANGE)
    private Integer heartBeatInterval;

}
