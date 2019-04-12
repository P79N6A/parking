package com.zhuyitech.parking.tool.dto.request.trafficrestriction;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 根据城市调三方查询限行请求参数
 *
 * @author AkeemSuper
 * @date 2018/4/13 0013
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TrafficRestrictionRequestDto", description = "根据城市调三方查询限行请求参数")
public class TrafficRestrictionRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 城市拼音小写
     */
    @ApiModelProperty(value = "城市拼音小写", required = true)
    @NotBlank(message = "城市拼音小写不能为空")
    private String city;

    /**
     * 类型，1:今日 2:明天 3:后天 4:第4天 5:第5天 6:第6天 默认1
     */
    @ApiModelProperty(value = "类型，1:今日 2:明天 3:后天 4:第4天 5:第5天 6:第6天 默认1")
    private Integer type;

}
