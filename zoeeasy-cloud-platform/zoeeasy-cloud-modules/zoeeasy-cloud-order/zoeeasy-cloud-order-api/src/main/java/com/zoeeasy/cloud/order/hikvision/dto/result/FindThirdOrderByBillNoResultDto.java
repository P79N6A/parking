package com.zoeeasy.cloud.order.hikvision.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description: 通过BillNo查询
 * @Date: 2018/1/15 0015
 * @author: zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FindThirdOrderByBillNoResultDto")
public class FindThirdOrderByBillNoResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 平台停车记录编号
     */
    @ApiModelProperty(value = "平台停车记录编号")
    private String recordNo;

    /**
     * 平台停车账单编号
     */
    @ApiModelProperty(value = "平台停车账单编号")
    private String orderNo;

    /**
     * 账单编号
     */
    @ApiModelProperty(value = "账单编号")
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
    @ApiModelProperty(value = "parkPeriodTime")
    private Integer parkPeriodTime;

    /**
     * 总收费金额(单位：分)
     */
    @ApiModelProperty(value = "totalCost")
    private Integer totalCost;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "payTime")
    private Date payTime;

    /**
     * 总收费金额(单位：分)
     */
    @ApiModelProperty(value = "payAmount")
    private Integer payAmount;

    /**
     * 支付方式 4支付宝5	微信11账户余额（云平台的账户）
     */
    @ApiModelProperty(value = "payType")
    private Integer payType;

    /**
     * 账单状态
     */
    @ApiModelProperty(value = "status")
    private Integer status;

    /**
     * 第三方平台账单编号
     */
    private String thirdBillNo;

    /**
     * 第三方平台账单数据源
     */
    private Integer thirdBillSourceType;

    /**
     * 第三方平台账单同步状态
     */
    private Integer thirdBillSyncStatus;
}
