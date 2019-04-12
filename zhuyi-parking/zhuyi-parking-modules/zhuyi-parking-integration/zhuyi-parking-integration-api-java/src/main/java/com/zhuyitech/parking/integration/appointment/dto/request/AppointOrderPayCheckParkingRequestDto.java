package com.zhuyitech.parking.integration.appointment.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 预约订单支付判断
 *
 * @author zwq
 * @date 2018-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderPayCheckParkingRequestDto", description = "预约订单支付判断")
public class AppointOrderPayCheckParkingRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 预定订单号
     */
    @ApiModelProperty(value = "预定订单号", required = true)
    @NotBlank(message = "预定订单号不能为空")
    private String orderNo;
}
