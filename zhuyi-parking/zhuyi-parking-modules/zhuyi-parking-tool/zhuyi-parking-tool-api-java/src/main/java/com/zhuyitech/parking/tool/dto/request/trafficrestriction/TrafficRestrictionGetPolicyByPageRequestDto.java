package com.zhuyitech.parking.tool.dto.request.trafficrestriction;

import com.scapegoat.infrastructure.core.dto.request.PagedResultRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取限行城市列表的请求参数
 *
 * @author AkeemSuper
 * @date 2018/5/18 0018
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TrafficRestrictionGetPolicyByPageRequestDto", description = "获取限行城市的请求参数")
public class TrafficRestrictionGetPolicyByPageRequestDto extends PagedResultRequestDto {
    private static final long serialVersionUID = 1L;

    /**
     * 限行车辆类型
     */
    @ApiModelProperty(value = "限行车辆类型", notes = " 默认为1  1: 本地小客车 2,外地小客车 3:本地货车 4: 外地货车")
    private Integer carType;

    /**
     * 限行城市Id
     */
    @ApiModelProperty("限行城市Id")
    private Long trafficCityId;

}
