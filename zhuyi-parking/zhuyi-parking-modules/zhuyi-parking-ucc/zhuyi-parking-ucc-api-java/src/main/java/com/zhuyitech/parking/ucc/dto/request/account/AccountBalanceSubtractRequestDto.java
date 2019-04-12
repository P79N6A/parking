package com.zhuyitech.parking.ucc.dto.request.account;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * 用户账户余额减款请求参数
 *
 * @author walkman
 */
@ApiModel(value = "AccountBalanceSubtractRequestDto", description = "用户账户余额减款请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountBalanceSubtractRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(name = "userId", value = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    /**
     * 金额
     */
    @ApiModelProperty(name = "amount", value = "金额")
    @NotNull(message = "金额不能为空")
    @DecimalMin(value = "0", message = "金额无效")
    private BigDecimal amount;

    /**
     * 交易流水号
     */
    @ApiModelProperty(name = "transactionNo", value = "交易流水号")
    private String transactionNo;

    /**
     * 业务流水号
     */
    @ApiModelProperty(name = "bizNo", value = "业务流水号")
    private String bizNo;

    /**
     * 交易类型
     */
    @ApiModelProperty(name = "bizType", value = "交易类型")
    private Integer bizType;

    /**
     * 备注
     */
    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

}
