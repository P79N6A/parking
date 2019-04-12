package com.zoeeasy.cloud.order.parking.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 人工添加停车账单请求参数
 *
 * @Date: 2018/7/25
 * @author: wh
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderAddRequestDto")
public class ParkingOrderAddRequestDto extends SignedSessionRequestDto {
    private static final long serialVersionUID = 1L;

    /**
     * 停车场Id
     */
    @ApiModelProperty(value = "停车场Id")
    @NotNull(message = "停车场Id不能为空")
    private Long parkingId;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    @NotBlank(message = "车牌号不能为空")
    private String plateNumber;

    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额")
    @NotBlank(message = "订单金额不能为空")
    private String payableAmount;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态(0 全部 1 未支付 2已支付),默认0")
    @NotNull(message = "支付状态不能为空")
    private Integer payStatus;

    /**
     * 是否出场
     */
    @ApiModelProperty(value = "是否出场(0 全部 1 未出场 2 已出场),默认0")
    @NotNull(message = "出场状态不能为空")
    private Integer outed;

    /**
     * 入场时间
     */
    @ApiModelProperty(value = "入场时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    @NotNull(message = "入场时间不能为空")
    private Date startTime;

    /**
     * 出场时间
     */
    @ApiModelProperty(value = "出场时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date endTime;

    /**
     * 编辑人员
     */
    @ApiModelProperty(value = "编辑人员")
    @NotBlank(message = "编辑人员不能为空")
    private String editor;
}
