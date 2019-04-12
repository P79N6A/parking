package com.zoeeasy.cloud.charge.appointrule.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.charge.cts.AppointConstant;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author zwq
 * @Description: 通过时间查询停车场对应预约规则
 * @date 2018/3/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAppointRuleGetByTimeRequestDto", description = "通过时间查询停车场对应预约规则")
public class ParkingAppointRuleGetByTimeRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    @NotNull(message = AppointConstant.PARKING_ID_NOT_EMPTY)
    private Long parkingId;

    /**
     * 预约时间
     */
    @ApiModelProperty(value = "预约时间")
    @NotNull(message = AppointConstant.APPOINTTIME_NOT_EMPTY)
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATE)
    private Date appointTime;
}
