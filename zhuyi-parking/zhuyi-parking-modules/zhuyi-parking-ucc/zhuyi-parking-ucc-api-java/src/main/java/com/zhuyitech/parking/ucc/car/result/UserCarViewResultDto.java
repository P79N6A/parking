package com.zhuyitech.parking.ucc.car.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.scapegoat.infrastructure.jackson.annotation.ImageUrl;

import java.util.Date;

import com.scapegoat.infrastructure.jackson.annotation.SensitiveInfo;
import com.scapegoat.infrastructure.jackson.enums.SensitiveType;
import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户车辆信息视图
 *
 * @author walkman
 */
@ApiModel(value = "UserCarViewResultDto", description = "用户车辆信息视图")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCarViewResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌
     */
    @ApiModelProperty("品牌")
    private String carBrand;

    /**
     * 车辆型号
     */
    @ApiModelProperty("车辆型号")
    private String carType;

    /**
     * 车牌归属地
     */
    @ApiModelProperty("车牌归属地")
    private String platePrefix;

    /**
     * 车牌首字母
     */
    @ApiModelProperty("车牌首字母")
    private String plateInitial;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    private Integer plateColor;

    /**
     * 车架号
     */
    @ApiModelProperty("车架号")
    private String vehicleNumber;

    /**
     * 发动机号
     */
    @ApiModelProperty("发动机号")
    private String engineNumber;

    /**
     * 注册日期
     */
    @ApiModelProperty("注册日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATE, timezone = Const.TIMEZONE_GMT8)
    private Date registerDate;

    /**
     * 车辆图片URL
     */
    @ApiModelProperty(value = "车辆图片URL", notes = "车辆图片URL")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String carImageUrl;

    /**
     * 行驶证正页图片
     */
    @ApiModelProperty("行驶证正页图片")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String licensePhotoFront;

    /**
     * 行驶证副页图片
     */
    @ApiModelProperty("行驶证副页图片")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String licensePhotoContrary;

    /**
     * 是否提醒限行
     */
    @ApiModelProperty("是否提醒限行")
    private Boolean hintTrafficLimit;

    /**
     * 是否提醒违章
     */
    @ApiModelProperty("是否提醒违章")
    private Boolean hintViolation;

    /**
     * 是否提醒年检
     */
    @ApiModelProperty("是否提醒年检")
    private Boolean hintYearCheck;

    /**
     * 认证状态
     */
    @ApiModelProperty(value = "认证状态(1 认证中 2 已认证 3认证不通过)")
    private Integer status;

    /**
     * 是否已认证
     */
    @ApiModelProperty(value = "是否已认证", notes = "是否已认证")
    private Boolean authenticated;

    /**
     * 是否默认车辆
     */
    @ApiModelProperty(value = "是否默认车辆")
    private Boolean defaultCar;

    /**
     * 审核驳回原因
     */
    @ApiModelProperty("审核驳回原因")
    private String rejectReason;

    /**
     * 车牌号码
     */
    @ApiModelProperty("车牌号码")
    @SensitiveInfo(SensitiveType.PLATE_NUMBER)
    private String fullPlateNumber;

}
