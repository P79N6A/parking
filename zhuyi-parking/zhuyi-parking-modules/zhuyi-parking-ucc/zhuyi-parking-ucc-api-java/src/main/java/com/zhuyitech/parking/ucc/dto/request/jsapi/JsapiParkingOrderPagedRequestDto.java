package com.zhuyitech.parking.ucc.dto.request.jsapi;


import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


/**
 * 根据车牌分页获取用户订单列表请求参数
 *
 * @author zwq
 */
@ApiModel(value = "JsapiParkingOrderPagedRequestDto", description = "根据车牌分页获取用户订单列表请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class JsapiParkingOrderPagedRequestDto extends SessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    @NotBlank(message = OrderConstant.PLATE_NUMBER_NOT_EMPTY)
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    @NotNull(message = OrderConstant.PLATE_COLOR_NOT_EMPTY)
    private Integer plateColor;

    /**
     * 随机字符串
     */
    @ApiModelProperty(value = "随机字符串")
    private String noncestr;
}
