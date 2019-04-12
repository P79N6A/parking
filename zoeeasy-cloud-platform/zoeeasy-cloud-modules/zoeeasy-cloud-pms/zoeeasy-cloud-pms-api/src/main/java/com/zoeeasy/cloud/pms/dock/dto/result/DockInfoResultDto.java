package com.zoeeasy.cloud.pms.dock.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/12/4 0004
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "DockInfoResultDto", description = "客户端注册信息")
public class DockInfoResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 租户Id
     */
    @ApiModelProperty("tenantId")
    private Long tenantId;

    /**
     * 对接终端/平台编号
     */
    @ApiModelProperty("platformCode")
    private String platformCode;

    /**
     * 对接终端/平台名称
     */
    @ApiModelProperty("platformName")
    private String platformName;

    /**
     * 对接平台类型
     */
    @ApiModelProperty("platformType")
    private Integer platformType;

    /**
     * 对接终端/平台编号
     */
    @ApiModelProperty("terminateCode")
    private String terminateCode;

    /**
     * 对接终端/平台名称
     */
    @ApiModelProperty("terminateName")
    private String terminateName;

    /**
     * 对接终端/平台版本号
     */
    @ApiModelProperty("terminateVersion")
    private String terminateVersion;

    /**
     * 用户名
     */
    @ApiModelProperty("userName")
    private String userName;

    /**
     * 登录密码
     */
    @ApiModelProperty("password")
    private String password;

    /**
     * 访问凭证
     */
    @ApiModelProperty("accesskey")
    private String accesskey;

    /**
     * 安全秘钥
     */
    @ApiModelProperty("accessSecrete")
    private String accessSecrete;

    /**
     * token值
     */
    @ApiModelProperty("token")
    private String token;

    /**
     * token有效开始时间
     */
    @ApiModelProperty("startTime")
    private Date startTime;

    /**
     * token有效截止时间
     */
    @ApiModelProperty("endTime")
    private Date endTime;

    /**
     * 最后心跳时间
     */
    @ApiModelProperty("lastHeartBeatTime")
    private Date lastHeartBeatTime;

    /**
     * 请求接入的网域类型，1-公网，2-专网
     */
    @ApiModelProperty("netZoneType")
    private Integer netZoneType;

    /**
     * 对接终端/平台的IP
     */
    @ApiModelProperty("platformIp")
    private String platformIp;

    /**
     * 对接终端/平台的端口
     */
    @ApiModelProperty("platformPort")
    private Integer platformPort;

    /**
     * 终端接入的通讯协议版本号
     */
    @ApiModelProperty("protocolVersion")
    private String protocolVersion;

    /**
     * 对接终端/平台注册的账单下发请求URL
     */
    @ApiModelProperty("placeOrderUrl")
    private String placeOrderUrl;

    /**
     * 对接终端/平台注册的账单支付通知URL
     */
    @ApiModelProperty("notifyOrderUrl")
    private String notifyOrderUrl;

    /**
     * 对接终端/平台注册的远程开闸请求接口URL
     */
    @ApiModelProperty("openStrobeUrl")
    private String openStrobeUrl;

    /**
     * 心跳时间间隔：单位秒
     */
    @ApiModelProperty("heartBeatInterval")
    private Integer heartBeatInterval;
}
