package com.zoeeasy.cloud.charge.park.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/12/10 0010
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("ParkingChargeRuleTryRequestDto")
public class ParkingChargeRuleTryRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 规则id
     */
    @ApiModelProperty("规则id")
    private Long ruleId;

    /**
     * 上线时间
     */
    @ApiModelProperty(value = "上线时间", required = true)
    @NotNull(message = "上线时间不能为空")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATE)
    private Date onlineTime;

    /**
     * 下线时间
     */
    @ApiModelProperty("下线时间")
    @NotNull(message = "下线时间不能为空")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATE)
    private Date offlineTime;
}
