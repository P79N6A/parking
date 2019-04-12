package com.zoeeasy.cloud.charge.appointrule.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.charge.cts.AppointConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 删除收费规则关联停车信息表请求参数
 *
 * @author walkman
 * @date 2018/3/30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAppointRuleDeleteRequestDto", description = "删除收费规则关联停车信息表请求参数")
public class ParkingAppointRuleDeleteRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = AppointConstant.ID_NOT_EMPTY)
    private Long id;
}
