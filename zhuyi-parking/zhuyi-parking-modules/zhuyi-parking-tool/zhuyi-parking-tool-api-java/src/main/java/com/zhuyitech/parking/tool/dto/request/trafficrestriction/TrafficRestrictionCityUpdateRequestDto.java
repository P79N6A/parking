package com.zhuyitech.parking.tool.dto.request.trafficrestriction;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 修改限行城市的请求参数
 *
 * @author AkeemSuper
 * @date 2018/5/17 0017
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "trafficRestrictionCityUpdateRequestDto", description = "修改限行城市的请求参数")
public class TrafficRestrictionCityUpdateRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 城市id
     */
    @ApiModelProperty(value = "id", required = true)
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

    @ApiModelProperty(value = "大写首字母")
    private String initial;

    /**
     * 是否限行
     */
    @ApiModelProperty(value = "trafficRestriction")
    private boolean trafficRestriction;


    /**
     * 城市车牌前缀
     */
    @ApiModelProperty("城市车牌前缀")
    @NotBlank
    private String cityPrefix;

    /**
     * 限行尾号处理方式
     */
    @ApiModelProperty("限行尾号处理方式")
    @NotBlank
    private String limitPattern;

}
