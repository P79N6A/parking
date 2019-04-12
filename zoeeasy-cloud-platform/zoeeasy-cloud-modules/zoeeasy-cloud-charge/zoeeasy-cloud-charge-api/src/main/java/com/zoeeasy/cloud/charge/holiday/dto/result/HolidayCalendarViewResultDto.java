package com.zoeeasy.cloud.charge.holiday.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
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
@ApiModel(value = "HolidayCalendarViewResultDto", description = "节假日配置视图")
public class HolidayCalendarViewResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 节假日名称
     */
    @ApiModelProperty("节假日名称")
    private String holidayName;

    /**
     * 假期类型
     */
    @ApiModelProperty("假期类型")
    private Integer holidayType;

    /**
     * 选定日期
     */
    @ApiModelProperty("选定日期")
    private String date;
}
