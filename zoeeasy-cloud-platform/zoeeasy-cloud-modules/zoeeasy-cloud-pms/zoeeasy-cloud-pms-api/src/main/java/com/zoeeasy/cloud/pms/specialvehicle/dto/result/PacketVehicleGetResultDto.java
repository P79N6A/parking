package com.zoeeasy.cloud.pms.specialvehicle.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by song on 2018/10/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PacketVehicleGetResultDto", description = "包期车详情视图模型")
public class PacketVehicleGetResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    private String plateColor;

    /**
     * 车辆颜色
     */
    @ApiModelProperty("车辆颜色")
    private String carColor;

    /**
     * 车牌类型
     */
    @ApiModelProperty("车牌类型")
    private String plateType;

    /**
     * 车辆类型
     */
    @ApiModelProperty("车辆类型")
    private String carType;

    /**
     * 车辆品牌
     */
    @ApiModelProperty("车辆品牌")
    private String carBrand;

    /**
     * 车主姓名
     */
    @ApiModelProperty("车主姓名")
    private String ownerName;

    /**
     * 车主手机
     */
    @ApiModelProperty("车主手机")
    private String ownerPhone;

    /**
     * 车主身份证
     */
    @ApiModelProperty("车主身份证")
    private String ownerCardNo;

    /**
     * 车主地址
     */
    @ApiModelProperty("车主地址")
    private String ownerAddress;

    /**
     * 车主Email
     */
    @ApiModelProperty("车主Email")
    private String ownerEmail;

}
