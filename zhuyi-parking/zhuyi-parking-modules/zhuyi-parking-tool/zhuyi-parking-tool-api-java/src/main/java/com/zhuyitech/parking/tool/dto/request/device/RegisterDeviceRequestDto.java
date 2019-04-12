package com.zhuyitech.parking.tool.dto.request.device;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 注册设备请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RegisterDeviceRequestDto", description = "注册设备请求参数")
public class RegisterDeviceRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 极光推送的设备唯一标识
     */
    @ApiModelProperty(value = "极光推送的设备唯一标识", required = true)
    @NotBlank(message = "极光推送的设备唯一标识不能为空")
    private String registrationId;

    /**
     * 客户端类型
     * 0:未知 1:ANDROID 2:APPLE 3:H5 4:WEB
     */
    @NotNull(message = "客户端类型不能为空")
    @ApiModelProperty(value = "客户端类型(1:ANDROID 2:APPLE 3:H5 4:WEB)", required = true)
    private Integer terminateType;

    /**
     * 设备唯一识别码
     */
    @ApiModelProperty(value = "设备唯一识别码")
    private String deviceToken;

    /**
     * 渠道编码
     */
    @ApiModelProperty(value = "渠道编码")
    private String channelCode;

    /**
     * imei
     */
    @ApiModelProperty(value = "imei")
    private String imei;

    /**
     * 品牌
     */
    @ApiModelProperty(value = "品牌")
    private String buildBrand;

    /**
     * 机器型号
     */
    @ApiModelProperty(value = "机器型号")
    private String buildModel;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private String buildVersionRelease;

    /**
     * SDK版本
     */
    @ApiModelProperty(value = "SDK版本")
    private Integer buildVersionSdkInt;

    /**
     * 屏幕宽度
     */
    @ApiModelProperty(value = "屏幕宽度")
    private Integer screenWidth;

    /**
     * 屏幕高度
     */
    @ApiModelProperty(value = "屏幕高度")
    private Integer screenHeight;

    /**
     * 屏幕的dpi
     */
    @ApiModelProperty(value = "屏幕的dpi")
    private Integer densityDpi;

    /**
     * 应用包名
     */
    @ApiModelProperty(value = "应用包名")
    private String packageName;

    /**
     * 应用名称
     */
    @ApiModelProperty(value = "应用名称")
    private String appName;

    /**
     * 应用版本名称
     */
    @ApiModelProperty(value = "应用版本名称")
    private String appVersionName;

    /**
     * 应用版本号
     */
    @ApiModelProperty(value = "应用版本号")
    private Integer appVersionCode;

}
