package com.zhuyitech.parking.tool.dto.request.trafficrestriction;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除限行城市的请求参数
 *
 * @author AkeemSuper
 * @date 2018/5/17 0017
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TrafficRestrictionCityDeletedRequestDto", description = "删除限行城市的请求参数")
public class TrafficRestrictionCityDeletedRequestDto extends SessionEntityDto<Long> {
    private static final long serialVersionUID = 1L;

}
