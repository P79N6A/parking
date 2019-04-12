package com.zhuyitech.parking.tool.dto.request.trafficrestriction;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 修改限行政策的请求参数
 *
 * @author AkeemSuper
 * @date 2018/5/17 0017
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TrafficRestrictionPolicyUpdateRequestDto", description = "修改限行政策的请求参数")
public class TrafficRestrictionPolicyUpdateRequestDto extends SessionEntityDto<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 限行城市Id
     */
    @ApiModelProperty(value = "限行城市Id", required = true)
    @NotNull
    private Long cityId;

    /**
     * carType
     */
    @ApiModelProperty(value = "carType")
    private Long carType;

    /**
     * 限行时间
     */
    @ApiModelProperty(value = "限行时间")
    private String restrictionTime;

    /**
     * title
     */
    @ApiModelProperty(value = "限行标题")
    private String title;

    /**
     * 限行区域
     */
    @ApiModelProperty(value = "限行区域")
    private String restrictionArea;

    /**
     * 限行规则
     */
    @ApiModelProperty(value = "限行规则")
    private String restrictionRule;

    /**
     * 限行坐标
     */
    @ApiModelProperty(value = "限行坐标")
    private String restrictionDetail;

}
