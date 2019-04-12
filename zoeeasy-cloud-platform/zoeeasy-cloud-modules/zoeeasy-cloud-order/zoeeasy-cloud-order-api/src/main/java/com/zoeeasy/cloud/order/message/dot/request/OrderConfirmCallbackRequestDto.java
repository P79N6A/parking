package com.zoeeasy.cloud.order.message.dot.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/12/6 0006
 */
@Data
@ApiModel(value = "OrderConfirmCallbackRequestDto", description = "账单支付回调消息发送请求参数")
public class OrderConfirmCallbackRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 第三方平台账单编号
     */
    @ApiModelProperty(value = "第三方平台账单编号", required = true)
    @NotBlank(message = "第三方平台账单编号不能为空")
    private String thirdBillNo;

    /**
     * 平台停车场ID
     */
    @ApiModelProperty(value = "平台停车场ID", required = true)
    @NotNull(message = "平台停车场ID不能为空")
    private Long parkingId;

    /**
     * 实付金额
     */
    @ApiModelProperty(value = "实付金额", required = true)
    @NotNull(message = "实付金额不能为空")
    private Integer actualPayAmount;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private Integer payWay;

    /**
     * 支付成功时间
     */
    @ApiModelProperty(value = "支付成功时间", required = true)
    @NotNull(message = "支付成功时间不能为空")
    private Date succeedPayTime;
}
