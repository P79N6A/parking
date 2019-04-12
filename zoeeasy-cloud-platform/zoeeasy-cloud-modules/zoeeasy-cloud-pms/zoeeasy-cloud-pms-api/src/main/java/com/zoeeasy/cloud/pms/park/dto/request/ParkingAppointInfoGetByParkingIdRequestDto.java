package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author walkman
 * @since 2018/9/28 0028
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAppointInfoGetByParkingIdRequestDto", description = "根据停车场id获取停车场收费信息请求参数")
public class ParkingAppointInfoGetByParkingIdRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = "停车场id不能为空")
    private Long parkingId;
}
