package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除固定车请求参数
 *
 * @date: 2018/10/17.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FixedVehicleDeleteRequestDto", description = "删除固定车请求参数")
public class FixedVehicleDeleteRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
