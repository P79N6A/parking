package com.zhuyitech.parking.tool.dto.request.trafficrestriction;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 添加限行城市的请求参数
 *
 * @author AkeemSuper
 * @date 2018/5/17 0017
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "trafficRestrictionCityAddRequestDto", description = "添加限行城市的请求参数")
public class TrafficRestrictionCityAddRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 城市名称
     */
    @ApiModelProperty(value = "cityName", required = true)
    @NotBlank(message = "城市名称不能为空")
    private String cityName;

    /**
     * 城市拼音
     */
    @ApiModelProperty(value = "pinyinName", required = true, example = "beijing")
    @NotBlank(message = "城市拼音不能为空")
    private String pinyinName;

    /**
     * 大写首字母
     */
    @ApiModelProperty(value = "大写首字母", required = true)
    @NotBlank(message = "大写首字母不能为空")
    private String initial;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型", required = true)
    @NotBlank(message = "车辆类型不能为空")
    private String carType;

    /**
     * 城市车牌前缀
     */
    @ApiModelProperty(value = "城市车牌前缀", required = true)
    @NotBlank(message = "车牌前缀不能为空")
    private String cityPrefix;

    /**
     * 限行尾号处理方式
     */
    @ApiModelProperty("限行尾号处理方式")
    private Integer limitPattern;

}
