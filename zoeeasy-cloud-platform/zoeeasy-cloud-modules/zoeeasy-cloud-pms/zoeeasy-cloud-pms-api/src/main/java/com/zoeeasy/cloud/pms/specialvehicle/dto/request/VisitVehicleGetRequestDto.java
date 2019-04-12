package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取访客车请求参数
 *
 * @date: 2018/10/18.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "VisitVehicleGetRequestDto", description = "获取访客车请求参数")
public class VisitVehicleGetRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
