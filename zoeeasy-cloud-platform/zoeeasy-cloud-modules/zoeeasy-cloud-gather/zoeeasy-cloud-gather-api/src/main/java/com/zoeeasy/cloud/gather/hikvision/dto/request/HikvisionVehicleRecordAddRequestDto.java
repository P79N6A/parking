package com.zoeeasy.cloud.gather.hikvision.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/9/19 0019
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "hikvisionVehicleRecordGetRequestDto")
public class HikvisionVehicleRecordAddRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 过车记录唯一编号
     */
    @ApiModelProperty(value = "过车记录唯一编号")
    private String passingUuid;

    /**
     * 海康平台过车记录唯一ID
     */
    @ApiModelProperty(value = "海康平台过车记录唯一ID")
    private String uuid;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private Integer carType;

    /**
     * 过车方向
     */
    @ApiModelProperty(value = "过车方向")
    private Integer passDirect;

    /**
     * 通过时间
     */
    @ApiModelProperty(value = "通过时间")
    private Date passTime;

    /**
     * 停车场编号
     */
    @ApiModelProperty(value = "停车场编号")
    private String parkCode;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkName;

    /**
     * 停车场出入口编号
     */
    @ApiModelProperty(value = "停车场出入口编号")
    private String gateCode;

    /**
     * 停车场出入口名称
     */
    @ApiModelProperty(value = "停车场出入口名称")
    private String gateName;

    /**
     * 车道编号
     */
    @ApiModelProperty(value = "车道编号")
    private String laneCode;

    /**
     * 车道名称
     */
    @ApiModelProperty(value = "车道名称")
    private String laneName;

    /**
     * 泊位编号
     */
    @ApiModelProperty(value = "泊位编号")
    private String berthCode;

    /**
     * 处理状态
     */
    @ApiModelProperty(value = "处理状态")
    private Integer processStatus;

    /**
     * 处理说明
     */
    @ApiModelProperty(value = "处理说明")
    private String processRemark;
}
