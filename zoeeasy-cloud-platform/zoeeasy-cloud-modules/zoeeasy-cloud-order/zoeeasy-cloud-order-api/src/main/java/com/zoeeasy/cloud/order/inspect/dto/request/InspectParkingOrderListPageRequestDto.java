package com.zoeeasy.cloud.order.inspect.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:获取巡检停车账单
 * @Autor: zwq
 * @Date: 2018/11/20 0002
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "InspectParkingOrderListPageRequestDto")
public class InspectParkingOrderListPageRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态(0 全部 1 未支付 2 已支付),默认0")
    private Integer payStatus;
}
