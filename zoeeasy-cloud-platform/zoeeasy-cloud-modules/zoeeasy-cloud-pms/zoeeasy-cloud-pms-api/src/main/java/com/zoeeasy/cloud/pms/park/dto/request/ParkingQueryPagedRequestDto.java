package com.zoeeasy.cloud.pms.park.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.pms.park.validator.LotTypeEnumValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车场列表分页请求参数
 * Created by song on 2018/9/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingQueryPagedRequestDto", description = "停车场列表分页请求参数")
public class ParkingQueryPagedRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 区域Code
     */
    @ApiModelProperty("区域Code")
    private String areaCode;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String fullName;

    /**
     * 停车场编号
     */
    @ApiModelProperty("编号")
    private String code;

    /**
     * 停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场
     */
    @ApiModelProperty("停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场")
    @FluentValidate({LotTypeEnumValidator.class})
    private String lotType;

    /**
     * 上架状态: 1未上架 2上架审核中 3上架处理中 4已上架 5下架审核中 6下架处理中 7已下架 8上架申请驳回 9下架申请驳回
     */
    @ApiModelProperty("上架状态: 1未上架 2上架审核中 3上架处理中 4已上架 5下架审核中 6下架处理中 7已下架 8上架申请驳回 9下架申请驳回")
    private Integer runStatus;

}
