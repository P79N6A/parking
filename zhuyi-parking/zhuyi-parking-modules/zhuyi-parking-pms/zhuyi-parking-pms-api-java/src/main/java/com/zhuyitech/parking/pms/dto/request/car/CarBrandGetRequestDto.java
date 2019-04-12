package com.zhuyitech.parking.pms.dto.request.car;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取车型请求参数
 *
 * @author walkman
 */
@ApiModel(value = "CarBrandGetRequestDto", description = "获取车型请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class CarBrandGetRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;
}