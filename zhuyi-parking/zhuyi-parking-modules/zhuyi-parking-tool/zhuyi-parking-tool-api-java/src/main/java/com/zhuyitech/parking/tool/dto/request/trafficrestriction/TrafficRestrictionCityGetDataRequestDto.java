package com.zhuyitech.parking.tool.dto.request.trafficrestriction;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import com.zhuyitech.parking.common.constant.Const;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 获取限行城市列表的请求参数
 *
 * @author AkeemSuper
 * @date 2018/5/18 0018
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TrafficRestrictionCityGetDataRequestDto", description = "获取限行城市的请求参数")
public class TrafficRestrictionCityGetDataRequestDto extends SessionEntityDto<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 日历时间
     */
    @ApiModelProperty(value = "日历时间  默认当天")
    @DateTimeFormat(pattern = Const.FORMAT_DATE)
    private Date date;

    /**
     * 限行车辆类型
     */
    @ApiModelProperty(value = "限行车辆类型(默认为1  1: 本地小客车 2,外地小客车 3:本地货车 4: 外地货车)", allowableValues = "1,2,3,4")
    private Integer carType;

}
