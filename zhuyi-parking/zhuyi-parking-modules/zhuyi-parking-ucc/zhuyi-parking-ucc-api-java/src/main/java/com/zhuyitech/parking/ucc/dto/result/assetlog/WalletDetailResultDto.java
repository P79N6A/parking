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
 * 钱包账单详情
 *
 * @author zwq
 * @date 2018-06-26
 */
@ApiModel(value = "WalletDetailResultDto", description = "钱包账单详情")
@Data
@EqualsAndHashCode(callSuper = true)
public class WalletDetailResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date creationTime;

    /**
     * 业务类型
     */
    @ApiModelProperty(value = "业务类型")
    private Integer bizType;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额")
    private String orderAmount;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer payStatus;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private Integer payWay;

    /**
     * 业务流水号
     */
    @ApiModelProperty(value = "业务流水号")
    private String bizNo;

    /**
     * 业务流水号
     */
    @ApiModelProperty(value = "业务流水号")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date payTime;

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

}
