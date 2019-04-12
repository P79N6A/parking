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
@ApiModel(value = "TrafficRestrictionCityPageResultDto", description = "限行城市返回结果类")
public class TrafficRestrictionCityPageResultDto extends BaseDto {

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
     * 城市编码
     */
    @ApiModelProperty(value = "adCode")
    private String adCode;

    /**
     * 城市拼音
     */
    @ApiModelProperty(value = "pinyinName", example = "beijing")
    private String pinyinName;

    /**
     * 首字母
     */
    @ApiModelProperty(value = "initial")
    private String initial;

    /**
     * 是否限行
     */
    @ApiModelProperty(value = "trafficRestriction")
    private boolean trafficRestriction;

    /**
     * 限行尾号处理方式
     */
    @ApiModelProperty(value = "limitPattern")
    private Integer limitPattern;

    /**
     * 限行政策个数
     */
    @ApiModelProperty(value = "限行政策个数")
    private Integer policyCount;

}
