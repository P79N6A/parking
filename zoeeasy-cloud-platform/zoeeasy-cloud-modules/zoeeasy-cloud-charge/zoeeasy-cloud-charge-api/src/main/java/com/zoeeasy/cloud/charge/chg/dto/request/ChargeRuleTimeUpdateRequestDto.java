package com.zoeeasy.cloud.charge.chg.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import com.scapegoat.infrastructure.jackson.convert.deserializer.ToIntegerFenDeserializer;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 修改收费规则时间分段请求参数
 *
 * @Date: 2018/1/26
 * @author: yuzhicheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("ChargeRuleTimeUpdateRequestDto")
public class ChargeRuleTimeUpdateRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 时间段
     */
    @ApiModelProperty(value = "时间段", required = true)
    @NotNull(message = "时段不能为空")
    @Range(max = ChargeConstant.SMALL_INT_MAX, message = ChargeConstant.PART_TIME_VALUE_OUT_OF_ROUND)
    private Integer timePart;

    /**
     * 本时间段的单价
     */
    @ApiModelProperty(value = "本时间段的单价", required = true)
    @NotNull(message = "价格不能为空")
    @JsonDeserialize(using = ToIntegerFenDeserializer.class)
    @Range(max = ChargeConstant.INT_MAX, message = ChargeConstant.PRICE_VALUE_OUT_OF_ROUND)
    private Integer price;

}
