package com.zoeeasy.cloud.charge.holiday.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author AkeemSuper
 * @date 2018/10/11 0011
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "holidayCalendarDeleteRequestDto", description = "删除节假日配置请求参数")
public class HolidayCalendarDeleteRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 删除节假日配置日期
     */
    @ApiModelProperty(value = "删除节假日配置日期 yyyy-MM-dd", required = true)
    @NotBlank(message = ChargeConstant.HOLIDAY_CALENDAR_DELETE_DATE_NOT_EMPTY)
    private String deleteDate;
}
