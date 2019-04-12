package com.zhuyitech.parking.tool.dto.result.traffic;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
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
public class TrafficRestrictionCityListResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 城市名称
     */
    @ApiModelProperty(value = "cityName")
    private String cityName;

    /**
     * 首字母
     */
    @ApiModelProperty("首字母")
    private String initial;

}
