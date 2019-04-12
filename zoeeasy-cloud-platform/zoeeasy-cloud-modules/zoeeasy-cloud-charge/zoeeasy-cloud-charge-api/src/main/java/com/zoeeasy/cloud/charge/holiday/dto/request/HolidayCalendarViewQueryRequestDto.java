package com.zoeeasy.cloud.charge.holiday.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/10/8 0008
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "holidayCalendarViewQueryRequestDto", description = "获取某月节假日配置")
public class HolidayCalendarViewQueryRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 获取节假日配置的日期,默认为当前月
     */
    @ApiModelProperty("获取节假日配置的月份")
    private String queryDate;
}
