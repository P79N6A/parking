package com.zhuyitech.parking.tool.dto.request.trafficrestriction;

import com.scapegoat.infrastructure.core.dto.request.PagedResultRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/5/24 0024
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TrafficRestrictionRemindRequestDto", description = "修改限行政策的请求参数")
public class TrafficRestrictionRemindRequestDto extends PagedResultRequestDto {
    private static final long serialVersionUID = 1L;

    /**
     * 城市拼音
     */
    @ApiModelProperty(value = "城市拼音", required = true)
    private String cityPinyin;

}
