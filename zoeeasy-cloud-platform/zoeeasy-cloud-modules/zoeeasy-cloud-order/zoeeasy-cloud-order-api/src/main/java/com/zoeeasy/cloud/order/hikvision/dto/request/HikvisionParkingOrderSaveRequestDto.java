package com.zoeeasy.cloud.order.hikvision.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/10/7 0007
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "HikvisionParkingOrderSaveRequestDto", description = "海康平台订单")
public class HikvisionParkingOrderSaveRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    @NotNull(message = OrderConstant.TENANT_ID_NOT_EMPTY)
    private Long tenantId;

    /**
     * 平台停车记录编号
     */
    @ApiModelProperty(value = "平台停车记录编号")
    @Length(max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String recordNo;

    /**
     * 平台停车账单编号
     */
    @ApiModelProperty(value = "平台停车账单编号")
    @Length(max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String orderNo;

    /**
     * 账单编号
     */
    @ApiModelProperty(value = "账单编号")
    @Length(max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String billNo;

    /**
     * 平台停车场ID
     */
    @ApiModelProperty(value = "平台停车场ID")
    private Long parkingId;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    @Length(max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private Integer carType;

    /**
     * 停车场编号
     */
    @ApiModelProperty(value = "停车场编号")
    @Length(max = OrderConstant.MAX, message = OrderConstant.LENGTH_RANGE)
    private String parkCode;

    /**
     * 车辆进场时间
     */
    @ApiModelProperty(value = "车辆进场时间")
    private Date enterTime;

    /**
     * 结算时间
     */
    @ApiModelProperty(value = "结算时间")
    private Date costTime;

    /**
     * 停车时长(分钟)
     */
    @ApiModelProperty(value = "停车时长")
    private Integer parkPeriodTime;

    /**
     * 总收费金额(单位：分)
     */
    @ApiModelProperty(value = "总收费金额")
    private Integer totalCost;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    private Date payTime;

    /**
     * 总收费金额(单位：分)
     */
    @ApiModelProperty(value = "总收费金额")
    private Integer payAmount;

    /**
     * 支付方式 4支付宝5	微信11账户余额（云平台的账户）
     */
    @ApiModelProperty(value = "支付方式")
    private Integer payType;

    /**
     * 账单状态
     */
    @ApiModelProperty(value = "账单状态")
    private Integer status;
}
