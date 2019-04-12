package com.zoeeasy.cloud.inspect.platform.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/11/7 0007
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkCollectorInfoResultDto", description = "停车场和收费关联视图")
public class ParkCollectorInfoResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty("停车场id")
    private Long parkingId;

    /**
     * 租户id
     */
    @ApiModelProperty("租户id")
    private Long tenantId;

    /**
     * 用户id
     */
    @ApiModelProperty("userId")
    private Long userId;
}
