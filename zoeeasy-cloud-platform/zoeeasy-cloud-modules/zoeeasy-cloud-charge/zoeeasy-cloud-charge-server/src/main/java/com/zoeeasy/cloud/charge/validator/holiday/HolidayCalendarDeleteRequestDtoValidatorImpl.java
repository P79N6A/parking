package com.zoeeasy.cloud.charge.validator.holiday;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.chg.validator.HolidayCalendarDeleteRequestDtoValidator;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import com.zoeeasy.cloud.charge.enums.ChargeResultEnum;
import com.zoeeasy.cloud.charge.holiday.dto.request.HolidayCalendarDeleteRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/10/29 0029
 */
@Component
public class HolidayCalendarDeleteRequestDtoValidatorImpl extends ValidatorHandler<HolidayCalendarDeleteRequestDto> implements HolidayCalendarDeleteRequestDtoValidator {

    @Override
    public boolean accept(ValidatorContext context, HolidayCalendarDeleteRequestDto requestDto) {

        try {
            Date deleteDate = DateUtils.parseDate(requestDto.getDeleteDate(), Const.FORMAT_DATE);
            Date parseDate = DateUtils.parseDate(DateUtils.getDate(), Const.FORMAT_DATE);
            if (deleteDate.before(parseDate)) {
                throw new ValidationException(ChargeResultEnum.HOLIDAY_CALENDAR_DELETE_DATE_ERROR.getComment());
            }
        } catch (ParseException e) {
            throw new ValidationException(ChargeConstant.HOLIDAY_CALENDAR_DELETE_DATE_NOT_STANDARD);
        }
        return true;
    }
}
