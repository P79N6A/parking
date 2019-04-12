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
@ApiModel(value = "TrafficRestrictionPolicyGetResultDto", description = "限行城市返回结果类")
public class TrafficRestrictionPolicyGetResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 城市名称
     */
    @ApiModelProperty(value = "城市名称")
    private String cityName;

    /**
     * 城市编码
     */
    @ApiModelProperty(value = "城市编码")
    private String adCode;

    /**
     * 城市拼音
     */
    @ApiModelProperty(value = "城市拼音", example = "beijing")
    private String pinyinName;

    /**
     * 是否限行
     */
    @ApiModelProperty(value = "是否限行")
    private boolean trafficRestriction;

    /**
     * 首字母
     */
    @ApiModelProperty(value = "首字母")
    private String initial;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private String carType;

    /**
     * 城市车牌前缀
     */
    @ApiModelProperty(value = "城市车牌前缀")
    private String cityPrefix;

    /**
     * 限行尾号处理方式
     */
    @ApiModelProperty("限行尾号处理方式")
    private Integer limitPattern;

    /**
     * 限行政策
     */
    @ApiModelProperty(value = "trafficRestriction")
    private List<TrafficRestrictionPolicyResultDto> trafficRestrictionPolicys;

}
