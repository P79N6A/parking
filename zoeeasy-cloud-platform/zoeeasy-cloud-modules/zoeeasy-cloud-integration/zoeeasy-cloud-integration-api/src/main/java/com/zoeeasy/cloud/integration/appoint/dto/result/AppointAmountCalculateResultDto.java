package com.zoeeasy.cloud.integration.appoint.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

/**
 * 预约金额计算结果
 *
 * @author zwq
 * @date 2018/3/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointAmountCalculateResultDto", description = "预约金额计算结果")
public class AppointAmountCalculateResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 预约金额
     */
    @ApiModelProperty(value = "预约金额")
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#0.00")
    private BigDecimal amount;
}
