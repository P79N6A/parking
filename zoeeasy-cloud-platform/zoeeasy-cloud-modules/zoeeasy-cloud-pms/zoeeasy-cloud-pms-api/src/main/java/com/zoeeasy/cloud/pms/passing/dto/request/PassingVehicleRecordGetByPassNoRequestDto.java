package com.zoeeasy.cloud.pms.passing.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pms.passing.cts.PassingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author AkeemSuper
 * @date 2018/10/15 0015
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "passingVehicleRecordGetByPassNoRequestDto", description = "根据ID获取过车记录")
public class PassingVehicleRecordGetByPassNoRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 平台过车流水号
     */
    @ApiModelProperty(value = "passingNo", required = true)
    @NotBlank(message = PassingConstant.PASSING_NUMBER_NOT_EMPTY)
    @Length(min = PassingConstant.PASSING_NUMBER_MIN_LENGTH, max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String passingNo;
}
