package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author wangfeng
 * @date 2018/10/31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingChargeInfoGetBydRequestDto", description = "根据停车场收费信息ID获取停车场收费信息请求参数")
public class ParkingChargeInfoGetByIdRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场收费信息ID
     */
    @ApiModelProperty(value = "停车场收费信息ID", required = true)
    @NotNull(message = "停车场收费信息id不能为空")
    private Long id;
}
