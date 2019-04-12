package com.zoeeasy.cloud.pms.lane.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车道列表视图模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLaneListGetResultDto", description = "车道列表视图模型")
public class ParkingLaneListGetResultDto extends AuditedEntityDto<Long> {
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
}
