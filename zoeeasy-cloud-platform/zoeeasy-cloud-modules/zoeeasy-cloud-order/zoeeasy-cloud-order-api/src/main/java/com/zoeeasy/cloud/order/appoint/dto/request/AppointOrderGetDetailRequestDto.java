package com.zoeeasy.cloud.order.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.order.cts.AppointOrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 获取详情请求参数
 *
 * @author zwq
 * @date 2018-06-27
 * -
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderGetDetailRequestDto", description = "获取详情请求参数")
public class AppointOrderGetDetailRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    @NotBlank(message = AppointOrderConstant.ORDERNO_NOT_EMPTY)
    private String orderNo;
}
