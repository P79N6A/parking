package com.zhuyitech.parking.tool.dto.request.weather;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 获取用户天气信息请求参数
 *
 * @author zwq
 * @date 2018/4/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "WeatherGetByDateRequestDto", description = "获取用户天气信息请求参数")
public class WeatherGetByDateRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 时间
     */
    @ApiModelProperty("时间")
    private Date date;

    /**
     * adCode
     */
    @ApiModelProperty(value = "adCode", required = true)
    @NotBlank(message = "adCode不能为空")
    private String adCode;

}
