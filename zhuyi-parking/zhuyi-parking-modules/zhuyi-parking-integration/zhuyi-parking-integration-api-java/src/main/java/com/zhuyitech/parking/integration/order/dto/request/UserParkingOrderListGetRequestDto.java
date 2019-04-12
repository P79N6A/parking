package com.zhuyitech.parking.integration.order.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * 根据车牌获取用户订单列表请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserParkingOrderListGetRequestDto", description = "根据车牌获取用户订单列表请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserParkingOrderListGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    @NotEmpty(message = "车牌号不能为空")
    private String plateNumber;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态(0 全部 1 未支付 2 已支付),默认0")
    private Integer payStatus;

}
