package com.zhuyitech.parking.tool.dto.request.trafficrestriction;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取限行政策请求参数
 *
 * @author AkeemSuper
 * @date 2018/5/18 0018
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TrafficRestrictionPolicyGetRequestDto", description = "获取限行政策请求参数")
public class TrafficRestrictionPolicyGetRequestDto extends SessionEntityDto<Long> {
    private static final long serialVersionUID = 1L;

}
