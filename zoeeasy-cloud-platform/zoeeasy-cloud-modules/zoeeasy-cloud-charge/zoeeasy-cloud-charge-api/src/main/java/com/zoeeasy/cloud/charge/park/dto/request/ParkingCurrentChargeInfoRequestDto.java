package com.zoeeasy.cloud.charge.park.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 获取停车场当前收费信息请求参数
 *
 * @author AkeemSuper
 * @since 2018/10/8 0008
 */
@ApiModel(value = "ParkingCurrentChargeInfoRequestDto", description = "获取停车场当前收费信息请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingCurrentChargeInfoRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户id")
    private Long tenantId;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = "停车场ID不能为空")
    private Long parkingId;

    /**
     * 基准时间
     */
    @ApiModelProperty(value = "基准时间")
    private Date baseTime;

}
