package com.zoeeasy.cloud.pms.lane.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车道视图模型
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLaneResultDto", description = "车道视图模型")
public class ParkingLaneResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "省名称")
    private String provinceName;

    /**
     *
     */

    @ApiModelProperty(value = "市名称")
    private String cityName;
    /**
     *
     *
     */
    @ApiModelProperty(value = "自定义区域名称")
    private String customName;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkingName;

    /**
     * 停车场编号
     */
    @ApiModelProperty(value = "停车场编号")
    private String parkingCode;

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
     * 出入口ID
     */
    @ApiModelProperty("出入口ID")
    private Long gateId;

    /**
     * 出入口ID
     */
    @ApiModelProperty("出入口名称")
    private String gateName;

    /**
     * 车道序号
     */
    @ApiModelProperty("车道序号")
    private String code;

    /**
     * 车道名称
     */
    @ApiModelProperty("车道名称")
    private String name;

    /**
     * 出入口方向：，1-入车道，2-出车道 3 出入车道
     */
    @ApiModelProperty("车道方向：1-入车道 2-出车道 3 出入车道")
    private Integer direction;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;

}
