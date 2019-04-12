package com.zhuyitech.parking.tool.dto.result.traffic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zhuyitech.parking.common.constant.Const;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 限行返回结果视图
 *
 * @author AkeemSuper
 * @date 2018/4/13 0013
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TrafficRestrictionPolicyInfoGetResultDto", description = "限行返回结果视图")
public class TrafficRestrictionPolicyInfoGetResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 日期
     */
    @ApiModelProperty("日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATE, timezone = Const.TIMEZONE_GMT8)
    private Date date;

    /**
     * 星期
     */
    @ApiModelProperty("星期")
    private String week;

    /**
     * 城市名称
     */
    @ApiModelProperty("城市名称")
    private String cityName;

    /**
     * 是否限行 1:是
     */
    @ApiModelProperty("是否限行 1:是")
    private Integer trafficRestriction;

    /**
     * 限行尾号
     */
    @ApiModelProperty("限行尾号")
    private String tailNumber;

    /**
     * 限行政策
     */
    @ApiModelProperty(value = "限行政策")
    private List<TrafficRestrictionPolicyResultDto> policies;

}
