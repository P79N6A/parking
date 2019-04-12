package com.zoeeasy.cloud.order.appoint.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 分页预约订单列表条件查询请求参数
 *
 * @author zwq
 * @date 2018-06-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AppointOrderPagedRequestDto", description = "分页预约订单列表条件查询请求参数")
public class AppointOrderPagedRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 地区编码
     */
    @ApiModelProperty(value = "地区编码")
    private String areaCode;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额")
    private BigDecimal payAmount;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkingName;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号")
    private String phoneNumber;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String nickname;

    /**
     * 车牌号码
     */
    @ApiModelProperty(value = "车牌号码")
    private String plateNumber;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private Integer carStyle;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态")
    private Integer payStatus;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式", hidden = true)
    private Integer payWay;

    /**
     * 预约状态
     */
    @ApiModelProperty(value = "预约状态")
    private Integer appointStatus;

    /**
     * 入场状态
     */
    @ApiModelProperty(value = "入场状态")
    private Boolean entryStatus;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date endTime;

    /**
     * parkingId列表
     */
    @ApiModelProperty(value = "parkingId列表", hidden = true)
    private List<Long> parkingId;
}
