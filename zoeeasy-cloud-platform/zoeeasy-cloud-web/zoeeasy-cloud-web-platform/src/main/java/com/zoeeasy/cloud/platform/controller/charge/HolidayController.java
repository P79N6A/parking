package com.zoeeasy.cloud.platform.controller.charge;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiSigned;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.charge.holiday.HolidayCalendarService;
import com.zoeeasy.cloud.charge.holiday.dto.request.HolidayCalendarDeleteRequestDto;
import com.zoeeasy.cloud.charge.holiday.dto.request.HolidayCalendarViewQueryRequestDto;
import com.zoeeasy.cloud.charge.holiday.dto.request.HolidayScheduleSaveRequestDto;
import com.zoeeasy.cloud.charge.holiday.dto.result.HolidayCalendarViewResultDto;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.core.enums.OperatorType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AkeemSuper
 * @date 2018/11/21 0021
 */
@RestController
@ApiVersion(1)
@ApiSigned
@Api(tags = "节假日配置API", value = "节假日配置API", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/cloud/holiday")
@RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_HOLIDAY)
public class HolidayController {

    @Autowired
    private HolidayCalendarService holidayCalendarService;

    /**
     * 获取假期日历列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取假期日历列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = HolidayCalendarViewResultDto.class)
    @PostMapping(value = "/list")
    public ListResultDto<HolidayCalendarViewResultDto> getHolidayCalendarList(@RequestBody HolidayCalendarViewQueryRequestDto requestDto) {
        return holidayCalendarService.getHolidayCalendarList(requestDto);
    }

    /**
     * 保存节假日配置
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "保存节假日配置", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/save")
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_HOLIDAY_EDIT)
    @AuditLog(title = "节假日配置", value = "保存节假日配置", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGE)
    public ResultDto saveHolidaySchedule(@RequestBody HolidayScheduleSaveRequestDto requestDto) {
        return holidayCalendarService.saveHolidaySchedule(requestDto);
    }

    /**
     * 删除节假日配置
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "删除节假日配置", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @PostMapping(value = "/delete")
    @AuditLog(title = "节假日配置", value = "删除节假日配置", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGE)
    @RequiresPermissions(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_HOLIDAY_DELETE)
    public ResultDto deleteHolidayCalendar(@RequestBody HolidayCalendarDeleteRequestDto requestDto) {
        return holidayCalendarService.deleteHolidayCalendar(requestDto);
    }
}
