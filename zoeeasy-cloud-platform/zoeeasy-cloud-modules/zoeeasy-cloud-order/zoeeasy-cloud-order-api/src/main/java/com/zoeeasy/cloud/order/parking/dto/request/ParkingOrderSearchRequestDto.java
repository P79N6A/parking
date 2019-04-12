package com.zoeeasy.cloud.order.parking.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 页面搜索停车订单列表请求参数
 *
 * @Date: 2018/6/22
 * @author: wh
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderSearchRequestDto", description = "根据页面搜索获取停车订单列表请求参数")
public class ParkingOrderSearchRequestDto extends SignedSessionPagedRequestDto {
    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    /**
     * 地区编码
     */
    @ApiModelProperty(value = "地区编码")
    private String areaCode;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkingName;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private Integer status;

    /**
     * 车位
     */
    @ApiModelProperty(value = "车位")
    private String parkingLotCode;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private Integer carStyle;

    /**
     * 是否用券
     */
    @ApiModelProperty(value = "是否用券")
    private Integer isUseVoucher;

    /**
     * 券号
     */
    @ApiModelProperty(value = "券号")
    private String voucherId;

    /**
     * 券名
     */
    @ApiModelProperty(value = "券名")
    private String voucherName;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号")
    private String username;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String nickname;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    /**
     * 订单类型
     */
    @ApiModelProperty(value = "订单类型")
    private Integer orderType;

    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额")
    private String payableAmount;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态(0 全部 1 未支付 2已支付),默认0")
    private Integer payStatus;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private Integer payWay;

    /**
     * 编辑人员
     */
    @ApiModelProperty(value = "editor")
    private String editor;

    /**
     * 是否手工单
     */
    @ApiModelProperty(value = "是否手工单")
    private Integer artificial;

    /**
     * 是否出场
     */
    @ApiModelProperty(value = "是否出场(0 全部 1 未出场 2 已出场),默认0")
    private Integer outed;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date beginDate;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date endDate;

    /**
     * parkingId列表
     */
    @ApiModelProperty(value = "parkingId列表", hidden = true)
    private List<Long> parkingId;
}


