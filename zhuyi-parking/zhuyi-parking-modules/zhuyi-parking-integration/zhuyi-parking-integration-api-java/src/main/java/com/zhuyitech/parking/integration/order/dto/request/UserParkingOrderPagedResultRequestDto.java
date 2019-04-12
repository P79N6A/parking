package com.zhuyitech.parking.integration.order.dto.request;


import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * 根据车牌分页获取用户订单列表请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserParkingOrderPagedResultRequestDto", description = "根据车牌分页获取用户订单列表请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserParkingOrderPagedResultRequestDto extends SessionPagedRequestDto {

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

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色(1蓝牌，2黄牌 ,6新能源")
    private Integer plateColor;

}
