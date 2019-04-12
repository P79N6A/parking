package com.zhuyitech.parking.tool.dto.request.trafficrestriction;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 限行消息推送请求参数
 *
 * @author AkeemSuper
 * @date 2018/6/4 0004
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "trafficRestrictionCityAddRequestDto", description = "限行消息推送请求参数")
public class PushTrafficLimitRequestDto extends BaseDto {
    private static final long serialVersionUID = 1L;

    /**
     * 限行城市pinyin
     */
    @ApiModelProperty(value = "city", required = true)
    @NotBlank
    private String cityPinyin;

}
