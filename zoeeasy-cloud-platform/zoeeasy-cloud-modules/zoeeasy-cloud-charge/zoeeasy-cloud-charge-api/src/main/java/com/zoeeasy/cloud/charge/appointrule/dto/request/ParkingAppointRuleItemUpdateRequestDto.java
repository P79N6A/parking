package com.zoeeasy.cloud.charge.appointrule.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import com.zoeeasy.cloud.charge.cts.AppointConstant;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 停车场预约规则明细
 *
 * @author walkman
 * @date 2018/3/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAppointRuleItemUpdateRequestDto", description = "停车场预约规则明细")
public class ParkingAppointRuleItemUpdateRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 预约规则ID
     */
    @ApiModelProperty(value = "预约规则ID", required = true)
    @NotNull(message = AppointConstant.RULE_ID_NOT_EMPTY)
    private Long ruleId;

    /**
     * 上线时间
     */
    @ApiModelProperty("上线时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATE)
    @NotNull(message = AppointConstant.ONLINETIME_NOT_EMPTY)
    private Date onlineTime;

    /**
     * 下线时间
     */
    @ApiModelProperty("下线时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATE)
    private Date offlineTime;

    /**
     * 上线状态
     */
    @ApiModelProperty("上线状态")
    private Integer status;
}
