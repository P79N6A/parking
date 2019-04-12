package com.zhuyitech.parking.ucc.dto.request.account;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * 用户账户积分余额扣除请求参数
 *
 * @author walkman
 */
@ApiModel(value = "AccountPointSubtractRequestDto", description = "用户账户积分余额扣除请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountPointSubtractRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(name = "userId", value = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @ApiModelProperty(name = "amount", value = "积分")
    @NotNull(message = "积分不能为空")
    private BigDecimal amount;

    /**
     * 业务流水号
     */
    @ApiModelProperty(name = "requestNo", value = "业务流水号")
    private String requestNo;

    /**
     * 交易流水号
     */
    @ApiModelProperty(name = "bankTrxNo", value = "交易流水号")
    private String bankTrxNo;

    /**
     * 交易类型
     */
    @ApiModelProperty(name = "trxType", value = "交易类型")
    private String trxType;

    /**
     * 备注
     */
    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

}