package com.zoeeasy.cloud.charge.park.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/11/30 0030
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkChargeRuleCalculateTryRequestDto", description = "停车场收费规则试算")
public class ParkChargeRuleCalculateTryRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = ChargeConstant.PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String tryPlateNumber;

    /**
     * 计算的车牌颜色
     */
    @ApiModelProperty(value = "计算的车牌颜色")
    private Integer tryPlateColor;

    /**
     * 计算的车辆类型
     */
    @ApiModelProperty(value = "计算的车辆类型", required = true)
    @NotNull(message = ChargeConstant.CHARGE_CAR_TYPE_NOT_EMPTY)
    private Integer tryCarType;

    /**
     * 计算的开始时间
     */
    @ApiModelProperty(value = "计算的开始时间", required = true)
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date tryStartTime;

    /**
     * 计算的结束时间
     */
    @ApiModelProperty(value = "计算的结束时间", required = true)
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date tryEndTime;

    /**
     * 收费规则列表
     */
    @ApiModelProperty(value = "收费规则列表", required = true)
    private List<ParkingChargeRuleTryRequestDto> parkingChargeRuleTry;
}
