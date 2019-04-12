package com.zhuyitech.parking.tool.dto.request.trafficrestriction;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zhuyitech.parking.common.constant.Const;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 获取限行城市政策请求参数
 *
 * @author AkeemSuper
 * @date 2018/5/18 0018
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TrafficRestrictionCityGetRequestDto", description = "获取限行城市政策请求参数")
public class TrafficCityGetByAdCodeRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 限行城市id
     */
    @ApiModelProperty(value = "cityId", required = true)
    @NotNull
    private Long cityId;

    /**
     * 日历时间
     */
    @ApiModelProperty(value = "日历时间  默认当天")
    @DateTimeFormat(pattern = Const.FORMAT_DATE)
    private Date date;

    /**
     * adCode
     */
    @ApiModelProperty(value = "adCode")
    private String adCode;

}
