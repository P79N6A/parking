package com.zhuyitech.parking.ucc.dto.request.trade;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 用户停车订单支付状态更新请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserParkingOrderUpdateRequestDto", description = "用户停车订单支付状态更新请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserParkingOrderUpdateRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    @NotBlank(message = "订单号不能为空")
    private String orderNo;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态", required = true)
    @NotNull(message = "支付状态")
    private Integer payStatus;

    /**
     * 实际支付金额
     */
    @ApiModelProperty(value = "实际支付金额")
    private BigDecimal actualPayAmount;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    private Date payTime;

}
