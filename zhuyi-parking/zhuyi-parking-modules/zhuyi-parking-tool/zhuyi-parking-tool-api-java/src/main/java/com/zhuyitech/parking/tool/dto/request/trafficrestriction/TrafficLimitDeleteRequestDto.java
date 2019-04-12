package com.zhuyitech.parking.tool.dto.request.trafficrestriction;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除限行请求参数
 *
 * @author AkeemSuper
 * @date 2018/5/17 0017
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "trafficLimitDeleteRequestDto", description = "删除限行请求参数")
public class TrafficLimitDeleteRequestDto extends BaseDto {
    
    private static final long serialVersionUID = 1L;
}
