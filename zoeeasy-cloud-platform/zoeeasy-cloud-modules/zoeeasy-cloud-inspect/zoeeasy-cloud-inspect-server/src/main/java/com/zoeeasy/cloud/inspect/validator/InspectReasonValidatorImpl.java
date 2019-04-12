package com.zoeeasy.cloud.inspect.validator;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.inspect.cts.InspectConstant;
import com.zoeeasy.cloud.inspect.enums.InspectReasonEnum;
import com.zoeeasy.cloud.inspect.record.validator.InspectReasonValidator;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @date 2018/10/29 0029
 */
@Component
public class InspectReasonValidatorImpl extends ValidatorHandler<String> implements InspectReasonValidator {

    @Override
    public boolean validate(ValidatorContext context, String inspectReasons) {
        if (!StringUtils.isEmpty(inspectReasons)) {
            String[] reasons = inspectReasons.split(",");
            if (reasons.length == 0) {
                throw new ValidationException(InspectConstant.INSPECT_REASON_NOT_STANDARD);
            }
            for (String reason : reasons) {
                boolean numbers = StringUtils.isNumbers(reason, true);
                if (numbers) {
                    int inspectReason = Integer.parseInt(reason);
                    InspectReasonEnum inspectReasonEnum = InspectReasonEnum.parse(inspectReason);
                    if (null == inspectReasonEnum) {
                        throw new ValidationException(InspectConstant.INSPECT_REASON_NOT_STANDARD);
                    }
                } else {
                    throw new ValidationException(InspectConstant.INSPECT_REASON_NOT_STANDARD);
                }
            }

        }
        return true;
    }
}
