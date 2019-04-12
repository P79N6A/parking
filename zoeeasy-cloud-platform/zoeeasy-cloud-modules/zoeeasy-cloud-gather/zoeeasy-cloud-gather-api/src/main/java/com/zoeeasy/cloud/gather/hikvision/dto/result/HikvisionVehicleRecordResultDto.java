package com.zoeeasy.cloud.gather.hikvision.dto.result;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 海康过车记录视图
 *
 * @author AkeemSuper
 * @date 2018/9/19 0019
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "hikvisionVehicleRecordResultDto")
public class HikvisionVehicleRecordResultDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 过车记录唯一编号
     */
    @ApiModelProperty(value = "passingUuid")
    private String passingUuid;

    /**
     * 海康平台过车记录唯一ID
     */
    @ApiModelProperty(value = "uuid")
    private String uuid;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "plateNumber")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "plateColor")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "carType")
    private Integer carType;

    /**
     * 过车方向
     */
    @ApiModelProperty(value = "passDirect")
    private Integer passDirect;

    /**
     * 通过时间
     */
    @ApiModelProperty(value = "passTime")
    private Date passTime;

    /**
     * 停车场编号
     */
    @ApiModelProperty(value = "parkCode")
    private String parkCode;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "parkName")
    private String parkName;

    /**
     * 停车场出入口编号
     */
    @ApiModelProperty(value = "gateCode")
    private String gateCode;

    /**
     * 停车场出入口名称
     */
    @ApiModelProperty(value = "gateName")
    private String gateName;

    /**
     * 车道编号
     */
    @ApiModelProperty(value = "laneCode")
    private String laneCode;

    /**
     * 车道名称
     */
    @ApiModelProperty(value = "laneName")
    private String laneName;

    /**
     * 泊位编号
     */
    @ApiModelProperty(value = "berthCode")
    private String berthCode;

    /**
     * 处理状态
     */
    @ApiModelProperty(value = "processStatus")
    private Integer processStatus;

    /**
     * 处理说明
     */
    @ApiModelProperty(value = "processRemark")
    private String processRemark;

}
