package com.zhuyitech.parking.tool.dto.result.traffic;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/5/18 0018
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "trafficRestrictionCityListResultDto", description = "限行城市返回结果类")
public class TrafficRestrictionCityViewListResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 首字母
     */
    @ApiModelProperty(value = "首字母")
    private String initial;

    @ApiModelProperty(value = "限行城市")
    private List<TrafficRestrictionCityListResultDto> cities;

}
