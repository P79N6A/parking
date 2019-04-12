package com.zoeeasy.cloud.order.hikvision.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/12/3 0003
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AnyParkingOrderSaveRequestDto extends BaseDto {
    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    @ApiModelProperty("租户id")
    private Long tenantId;

    /**
     * 停车场id
     */
    @ApiModelProperty("停车场id")
    private Long parkingId;

    /**
     * 订单号
     */
    @ApiModelProperty("订单号")
    private String orderNo;

    /**
     * 停车记录号
     */
    @ApiModelProperty("停车记录号")
    private String recordNo;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNo;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty("车牌颜色")
    private Integer carType;

    /**
     * 账单编号
     */
    @ApiModelProperty("账单编号")
    private String billNo;

    /**
     * 总收费金额
     */
    @ApiModelProperty("总收费金额")
    private Integer amount;

    /**
     * 停车时长
     */
    @ApiModelProperty("停车时长")
    private Long parkingLength;

    /**
     * 车辆进场时间
     */
    @ApiModelProperty("车辆进场时间")
    private Date enterTime;

    /**
     * 结算时间
     */
    @ApiModelProperty("结算时间")
    private Date costTime;

    /**
     * 第三方订单状态
     */
    @ApiModelProperty("第三方订单状态")
    private Integer thirdOrderStatus;

}
