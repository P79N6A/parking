package com.zhuyitech.parking.ucc.dto.request.trade;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户支付订单请求参数
 *
 * @author walkman
 */
@ApiModel(value = "CustomerPayOrderGetRequestDto", description = "用户支付订单请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerPayOrderGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    @NotBlank(message = "支付订单号不能为空")
    private String orderNo;

    private Long customerUserId;

    private Integer bizType;

    private String bizOrderNo;

}