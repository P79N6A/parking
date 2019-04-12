package com.zoeeasy.cloud.integration.validator.inspect;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.inspect.enums.InspectReasonEnum;
import com.zoeeasy.cloud.integration.cts.IntegrationConstant;
import com.zoeeasy.cloud.integration.inspect.dto.request.InspectErrorRecordRequestDto;
import com.zoeeasy.cloud.integration.inspect.validator.InspectErrorRecordRequestDtoValidator;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @date 2018/10/29 0029
 */
@Component
public class InspectErrorRecordRequestDtoValidatorImpl extends ValidatorHandler<InspectErrorRecordRequestDto> implements InspectErrorRecordRequestDtoValidator {

    @Override
    public boolean validate(ValidatorContext context, InspectErrorRecordRequestDto requestDto) {
        InspectReasonEnum inspectReasonEnum = InspectReasonEnum.parse(requestDto.getProcessReason());
        if (null == inspectReasonEnum) {
            throw new ValidationException(IntegrationConstant.REPORT_REASON_NOT_STANDARD);
        }

        return true;
    }
}
