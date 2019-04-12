package com.zoeeasy.cloud.charge.validator.holiday;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import com.zoeeasy.cloud.charge.enums.HolidayTypeEnum;
import com.zoeeasy.cloud.charge.holiday.dto.request.HolidayScheduleSaveRequestDto;
import com.zoeeasy.cloud.charge.holiday.validator.HolidayScheduleSaveRequestDtoValidator;
import com.zoeeasy.cloud.core.cst.Const;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AkeemSuper
 * @date 2018/10/10 0010
 */
@Component
public class HolidayScheduleSaveRequestDtoValidatorImpl extends ValidatorHandler<HolidayScheduleSaveRequestDto> implements HolidayScheduleSaveRequestDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, HolidayScheduleSaveRequestDto requestDto) {
        String[] split = requestDto.getHolidayDates().split(",");
        List<Date> dates = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Const.FORMAT_DATE);
        for (String dateStr : split) {
            if (StringUtils.isEmpty(dateStr)) {
                throw new ValidationException(ChargeConstant.HOLIDAY_DATE_NOT_EMPTY);
            }
            try {
                Date date = simpleDateFormat.parse(dateStr);

                if (date.compareTo(simpleDateFormat.parse(DateUtils.getDate(Const.FORMAT_DATE))) < 0) {
                    throw new ValidationException(ChargeConstant.HOLIDAY_DATE_LIMIT);
                }
                dates.add(date);
            } catch (Exception e) {
                throw new ValidationException(ChargeConstant.HOLIDAY_DATE_NOT_STANDARD);
            }
        }
        List<Date> collect = dates.stream().sorted().collect(Collectors.toList());
        for (int i = 1; i < collect.size(); i++) {
            Date date = collect.get(i);
            Date before = collect.get(i - 1);
            if (DateUtils.add(before, 1).compareTo(date) != 0) {
                throw new ValidationException(ChargeConstant.HOLIDAY_DATE_NOT_STANDARD);
            }
        }
        //时间连续
        HolidayTypeEnum holidayTypeEnum = HolidayTypeEnum.parse(requestDto.getHolidayType());
        if (holidayTypeEnum == null) {
            throw new ValidationException(ChargeConstant.HOLIDAY_TYPE_NOT_STANDARD);
        }
        return true;
    }
}
