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
 * 用户车辆认证
 *
 * @author AkeemSuper
 * @date 2018/4/19 0019
 */
@ApiModel(description = "用户车辆认证返回结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCarAuthResultDto extends FullAuditedEntityDto<Long> {

    /**
     * 车辆Id
     */
    @ApiModelProperty(value = "车辆Id")
    private Long carId;

    /**
     * 车辆品牌
     */
    @ApiModelProperty(value = "车辆品牌")
    private String carBrand;

    /**
     * 车辆型号
     */
    @ApiModelProperty(value = "车辆型号")
    private String carType;

    /**
     * 车辆颜色
     */
    @ApiModelProperty(value = "车辆颜色")
    private Integer carColor;

    /**
     * 车辆等级
     */
    @ApiModelProperty(value = "车辆等级")
    private Integer carLevel;

    /**
     * 车牌类型
     */
    @ApiModelProperty(value = "plateType")
    private String plateType;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer plateColor;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String fullPlateNumber;

    /**
     * 车架号
     */
    @ApiModelProperty(value = "车架号")
    private String vehicleNumber;

    /**
     * 发动机号
     */
    @ApiModelProperty(value = "发动机号")
    private String engineNumber;

    /**
     * 行驶证正面图片
     */
    @ApiModelProperty(value = "行驶证正页图片", notes = "行驶证正页图片")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String licensePhotoFront;

    /**
     * 行驶证反面图片
     */
    @ApiModelProperty(value = "行驶证反面图片", notes = "行驶证反面图片")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String licensePhotoContrary;

    /**
     * 注册日期
     */
    @ApiModelProperty(value = "注册日期", notes = "注册日期(yyyy-MM-dd)")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATE, timezone = Const.TIMEZONE_GMT8)
    private Date registerDate;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATE, timezone = Const.FORMAT_DATETIME)
    private Date authTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
