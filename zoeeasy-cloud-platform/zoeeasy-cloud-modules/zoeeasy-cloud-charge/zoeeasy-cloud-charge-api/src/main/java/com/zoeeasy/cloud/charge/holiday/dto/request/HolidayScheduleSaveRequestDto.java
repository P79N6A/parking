package com.zoeeasy.cloud.charge.holiday.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import com.zoeeasy.cloud.charge.holiday.validator.HolidayNameValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author AkeemSuper
 * @date 2018/10/10 0010
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "holidayScheduleSaveRequestDto", description = "获取某月节假日配置")
public class HolidayScheduleSaveRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 要保存的假期日期以,分割
     */
    @ApiModelProperty(value = "要保存的假期日期以,分割", required = true)
    @NotBlank(message = ChargeConstant.HOLIDAY_DATE_NOT_EMPTY)
    private String holidayDates;

    /**
     * 假期类型
     */
    @ApiModelProperty(value = "假期类型", required = true)
    @NotNull(message = ChargeConstant.HOLIDAY_TYPE_NOT_EMPTY)
    private Integer holidayType;

    /**
     * 假期名称
     */
    @ApiModelProperty(value = "假期名称", required = true)
    @NotBlank(message = ChargeConstant.HOLIDAY_NAME_NOT_EMPTY)
    @FluentValidate({HolidayNameValidator.class})
    private String holidayName;
}
