package com.zoeeasy.cloud.charge.chg.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/10/13 0013
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CalculateAmountResultDto", description = "计算收费返回结果")
public class CalculateAmountResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 收费金额
     */
    @ApiModelProperty("收费金额")
    private Integer amount;

    /**
     * 收费时长
     */
    @ApiModelProperty("收费时长")
    private Long chargeDuration;

    /**
     * 免费时长
     */
    @ApiModelProperty("免费时长")
    private Long freeDuration;

    /**
     * 免费类型
     */
    @ApiModelProperty("免费类型")
    private Integer freeType;

}
