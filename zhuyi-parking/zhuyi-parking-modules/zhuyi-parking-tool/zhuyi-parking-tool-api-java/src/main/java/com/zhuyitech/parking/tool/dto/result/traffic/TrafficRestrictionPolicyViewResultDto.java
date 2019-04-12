package com.zhuyitech.parking.tool.dto.result.traffic;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/5/21 0021
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("trafficRestrictionPolicyViewResultDto")
public class TrafficRestrictionPolicyViewResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * carType
     */
    @ApiModelProperty(value = "carType")
    private Integer carType;

    /**
     * 限行政策
     */
    @ApiModelProperty(value = "限行政策")
    private List<TrafficRestrictionPolicyResultDto> list;

}
