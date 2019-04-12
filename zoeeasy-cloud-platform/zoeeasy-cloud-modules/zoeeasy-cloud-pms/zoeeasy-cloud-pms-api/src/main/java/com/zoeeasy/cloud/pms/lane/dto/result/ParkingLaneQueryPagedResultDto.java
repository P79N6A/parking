package com.zoeeasy.cloud.pms.lane.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车道分页列表视图模型
 * Created by Kane on 2018/10/9.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLaneQueryPagedResultDto", description = "车道分页列表视图模型")
public class ParkingLaneQueryPagedResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 车道编号
     */
    @ApiModelProperty("code")
    private String code;

    /**
     * 车道名称
     */
    @ApiModelProperty("name")
    private String name;

    /**
     * 车道类型
     */
    @ApiModelProperty("direction")
    private Integer direction;

    /**
     * 停车场编号
     */
    @ApiModelProperty("停车场编号")
    private String parkingCode;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 停车场ID
     */
    @ApiModelProperty("parkingId")
    private Long parkingId;

}
