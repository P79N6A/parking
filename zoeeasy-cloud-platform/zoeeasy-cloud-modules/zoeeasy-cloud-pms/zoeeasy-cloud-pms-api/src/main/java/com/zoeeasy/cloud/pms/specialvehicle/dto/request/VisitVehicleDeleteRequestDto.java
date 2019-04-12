package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除访客车请求参数
 *
 * @date: 2018/10/18.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "VisitVehicleDeleteRequestDto", description = "删除访客车请求参数")
public class VisitVehicleDeleteRequestDto extends SignedSessionEntityDto<Long> {
}
