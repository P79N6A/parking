package com.zoeeasy.cloud.order.appoint.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.jackson.convert.serializer.ToBigDecimalYuanSerializer;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 预约订单条件查询视图
 *
 * @author zwq
 * @date 2018-06-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderPageResultDto", description = "预约订单条件查询视图")
public class AppointOrderPageResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额")
    @JsonSerialize(using = ToBigDecimalYuanSerializer.class)
    private Integer payAmount;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号")
    private String phoneNumber;

    /**
     * 预约状态
     */
    @ApiModelProperty(value = "预约状态")
    private Integer appointStatus;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkingName;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private Integer payStatus;

    /**
     * 车主用户ID
     */
    @ApiModelProperty(value = "车主用户ID")
    private Long customerUserId;

    /**
     * 是否入场
     */
    @ApiModelProperty(value = "是否入场")
    private Boolean entryStatus;

    /**
     * 支付方式(根据PayWayEnum)
     */
    @ApiModelProperty(value = "支付方式")
    private Integer payWay;

    /**
     * 支付类型(根据PayTypeEnum)
     */
    @ApiModelProperty(value = "支付类型")
    private Integer payType;

    /**
     * 取消原因
     */
    @ApiModelProperty(value = "取消原因")
    private String cancelReason;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date creationTime;

    /**
     * 是否入场(0 未入场 1 入场)
     */
    @ApiModelProperty(value = "是否入场(0 未入场 1 入场)")
    private Boolean entrance;
}
