package com.zhuyitech.parking.tool.dto.result.traffic;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/5/21 0021
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TrafficRestrictionPolicyResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 限行城市Id
     */
    @ApiModelProperty(value = "限行城市Id")
    private Long cityId;

    /**
     * carType
     */
    @ApiModelProperty(value = "carType")
    private Integer carType;

    /**
     * 限行标题
     */
    @ApiModelProperty(value = "限行标题")
    private String title;

    /**
     * 限行时间
     */
    @ApiModelProperty(value = "限行时间")
    private String restrictionTime;

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

}
