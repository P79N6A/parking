package com.zhuyitech.parking.ucc.car.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import com.scapegoat.infrastructure.jackson.annotation.ImageUrl;

import java.util.Date;

import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户车辆绑定信息视图
 *
 * @author yuzhicheng
 */
@ApiModel(value = "UserCarInfoResultDto", description = "用户车辆绑定信息视图")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCarInfoResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", notes = "用户ID")
    private Long userId;
    /**
     * 品牌
     */
    @ApiModelProperty(value = "品牌", notes = "品牌")
    private String carBrand;

    /**
     * 车辆型号
     */
    @ApiModelProperty(value = "车辆型号", notes = "车辆型号")
    private String carType;

    /**
     * 车辆颜色
     */
    @ApiModelProperty(value = "车辆颜色", notes = "车辆颜色")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer carColor;

    /**
     * 车辆等级
     */
    @ApiModelProperty(value = "车辆等级", notes = "车辆等级")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer carLevel;

    /**
     * 车牌类型
     */
    @ApiModelProperty(value = "车牌类型", notes = "车牌类型")
    private String plateType;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色", notes = "车牌颜色")
    private Integer plateColor;

    /**
     * 车牌归属地
     */
    @ApiModelProperty(value = "车牌归属地", notes = "车牌归属地")
    private String platePrefix;

    /**
     * 车牌首字母
     */
    @ApiModelProperty(value = "车牌首字母", notes = "车牌首字母")
    private String plateInitial;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", notes = "车牌号")
    private String plateNumber;

    /**
     * 车牌尾数
     */
    @ApiModelProperty("车牌尾数")
    private String plateLastNumber;

    /**
     * 车牌全号
     */
    @ApiModelProperty(value = "车牌全号", notes = "车牌全号")
    private String fullPlateNumber;

    /**
     * 车架号
     */
    @ApiModelProperty(value = "车架号", notes = "车架号")
    private String vehicleNumber;

    /**
     * 发动机号
     */
    @ApiModelProperty(value = "发动机号", notes = "发动机号")
    private String engineNumber;

    /**
     * 注册日期
     */
    @ApiModelProperty(value = "注册日期", notes = "注册日期(yyyy-MM-dd)")
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
    @ApiModelProperty(value = "行驶证正页图片", notes = "行驶证正页图片")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String licensePhotoFront;

    /**
     * 行驶证副页图片
     */
    @ApiModelProperty(value = "行驶证副页图片", notes = "行驶证副页图片")
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
     * 处理状态 1认证中 2已认证 3 认证不通过
     */
    @ApiModelProperty("处理状态")
    private Integer status;

    /**
     * 申请时间
     */
    @ApiModelProperty(value = "申请时间", notes = "申请时间(yyyy-MM-dd)")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATE, timezone = Const.TIMEZONE_GMT8)
    private Date applyTime;

    /**
     * 审核时间
     */
    @ApiModelProperty(value = "审核时间", notes = "审核时间(yyyy-MM-dd)")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATE, timezone = Const.TIMEZONE_GMT8)
    private Date authTime;

}
