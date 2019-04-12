package com.zoeeasy.cloud.pms.gate.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 批量删除停车场进出口请求参数
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingGateBatchDeleteRequest", description = "批量删除停车场进出口请求参数")
public class ParkingGateBatchDeleteRequestDto extends SignedSessionEntityDto {
    private static final long serialVersionUID = 1;
    /**
     * ids
     */
    @ApiModelProperty("ids")
    private List<Long> ids;
}
