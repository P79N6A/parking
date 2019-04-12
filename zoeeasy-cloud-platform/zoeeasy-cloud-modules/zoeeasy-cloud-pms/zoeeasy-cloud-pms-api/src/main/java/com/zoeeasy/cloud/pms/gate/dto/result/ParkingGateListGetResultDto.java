package com.zoeeasy.cloud.pms.gate.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 出入口列表视图模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingGateListGetResultDto", description = "出入口列表视图模型")
public class ParkingGateListGetResultDto extends AuditedEntityDto<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 出入口编号
     */
    @ApiModelProperty("code")
    private String code;

    /**
     * 出入口名称
     */
    @ApiModelProperty("name")
    private String name;
}
