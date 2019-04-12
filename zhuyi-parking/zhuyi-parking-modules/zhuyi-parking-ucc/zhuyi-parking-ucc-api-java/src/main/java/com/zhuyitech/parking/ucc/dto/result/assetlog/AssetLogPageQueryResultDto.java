package com.zhuyitech.parking.ucc.dto.result.assetlog;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 收支明细条件查询
 *
 * @author zwq
 * @date 2018-06-13
 */
@ApiModel(value = "AssetLogPageQueryResultDto", description = "收支明细条件查询")
@Data
@EqualsAndHashCode(callSuper = true)
public class AssetLogPageQueryResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date creationTime;

    /**
     * 订单类型
     */
    @ApiModelProperty(value = "订单类型")
    private Integer bizType;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private Integer payWay;

    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额")
    private String orderAmount;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String nickname;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号")
    private String phoneNumber;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", hidden = true)
    private Long userId;

    /**
     * 订单金额临时
     */
    @ApiModelProperty(value = "订单金额临时", hidden = true)
    private double amount;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String bizNo;

    /**
     * 变动方向
     */
    @ApiModelProperty(value = "变动方向 (1.+ 2.-)")
    private Integer direction;

}
