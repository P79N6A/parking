package com.zoeeasy.cloud.order.parking.dto.request;

import com.scapegoat.infrastructure.core.dto.request.PagedResultRequestDto;
import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 通过车牌分页获取停车订单请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderQueryByPlatePageRequestDto", description = "分页获取停车记录请求参数")
public class ParkingOrderQueryByPlatePageRequestDto extends PagedResultRequestDto {

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
//    @NotNull(message = OrderConstant.PLATE_COLOR_NOT_EMPTY)
    private Integer plateColor;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态(0 全部 1 未支付 2 已支付),默认0")
    private Integer payStatus;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;
}
