package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.cst.PmsConstant;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 查询停车场当前状态请求参数
 *
 * @Date: 2019/1/4
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingCurrentGetByIdRequestDto", description = "查询停车场当前状态请求参数")
public class ParkingCurrentGetByIdRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @NotNull(message = PmsConstant.PARKING_ID_NOT_NULL)
    private Long parkingId;

}
