package com.zoeeasy.cloud.charge.chg.dto.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.jackson.convert.serializer.ToBigDecimalYuanSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/12/4 0004
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CalculateAmountViewResultDto", description = "计算收费返回结果")
public class CalculateAmountViewResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 收费金额
     */
    @ApiModelProperty("收费金额")
    @JsonSerialize(using = ToBigDecimalYuanSerializer.class)
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
