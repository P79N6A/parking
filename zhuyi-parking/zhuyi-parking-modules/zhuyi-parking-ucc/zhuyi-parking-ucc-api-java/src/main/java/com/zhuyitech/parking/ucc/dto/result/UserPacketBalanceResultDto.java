package com.zhuyitech.parking.ucc.dto.result;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

/**
 * 当前登录用户钱包余额结果
 *
 * @author walkman
 */
@ApiModel(value = "UserPacketBalanceResultDto", description = "当前登录用户钱包余额结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPacketBalanceResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 余额
     */
    @ApiModelProperty("余额")
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#0.00")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal balance;

}
