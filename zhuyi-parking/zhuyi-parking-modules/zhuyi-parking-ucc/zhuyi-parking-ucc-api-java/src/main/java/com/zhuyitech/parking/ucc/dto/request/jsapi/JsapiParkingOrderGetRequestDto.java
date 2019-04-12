package com.zhuyitech.parking.ucc.dto.request.jsapi;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * 获取用户订单详情请求参数
 *
 * @author zwq
 */
@ApiModel(value = "JsapiParkingOrderGetRequestDto")
@Data
@EqualsAndHashCode(callSuper = true)
public class JsapiParkingOrderGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    @NotEmpty(message = OrderConstant.ORDER_NO_NOT_EMPTY)
    private String orderNo;

}
