package com.zoeeasy.cloud.pms.park.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车位视图模型
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLotResultDto", description = "车位视图模型")
public class ParkingLotResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty("停车场ID")
    private Long parkingId;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 车库ID
     */
    @ApiModelProperty("车库ID")
    private Long garageId;

    /**
     * 车库名称
     */
    @ApiModelProperty("车库名称")
    private String garageName;

    /**
     * 泊车区域ID
     */
    @ApiModelProperty("泊车区域ID")
    private Long parkingAreaId;

    /**
     * 泊车区域名称
     */
    @ApiModelProperty("泊车区域名称")
    private String parkingAreaName;

    /**
     * 海康平台泊位ID
     */
    @ApiModelProperty("海康平台泊位ID")
    private String hikParkingLotId;

    /**
     * 海康平台泊位号(停车场唯一)
     */
    @ApiModelProperty("海康平台泊位号")
    private String hikBerthNumber;

    /**
     * 支付宝平台泊位ID
     */
    @ApiModelProperty("支付宝平台泊位ID")
    private String aliParkingLotId;

    /**
     * 编号(平台唯一)
     */
    @ApiModelProperty("编号")
    private String code;

    /**
     * 泊位编号：停车场内唯一
     */
    @ApiModelProperty("泊位编号")
    private String number;

    /**
     * 简称
     */
    @ApiModelProperty("简称")
    private String name;

    /**
     * 车位状态
     */
    @ApiModelProperty("车位状态")
    private Integer status;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

    /**
     * 楼层id
     */
    @ApiModelProperty("楼层id")
    private Long floorId;

    /**
     * 楼层name
     */
    @ApiModelProperty("楼层name")
    private String floorName;

}
