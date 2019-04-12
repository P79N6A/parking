package com.zoeeasy.cloud.charge.park.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 停车场收费规则明细
 *
 * @Date: 2018/1/26
 * @author: yuzhicheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingChargeRuleItemUpdateRequestDto", description = "停车场收费规则明细")
public class ParkingChargeRuleItemUpdateRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 收费规则ID
     */
    @ApiModelProperty(value = "收费规则ID", required = true)
    @NotNull(message = ChargeConstant.CHARGE_RULE_ID_EMPTY)
    private Long ruleId;

    /**
     * 上线时间
     */
    @ApiModelProperty("上线时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATE)
    private Date onlineTime;

    /**
     * 下线时间
     */
    @ApiModelProperty("下线时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATE)
    private Date offlineTime;

}
