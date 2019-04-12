package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.AuditNotPassReasonEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.AuditNotPassReasonEnumValidator;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class AuditNotPassReasonEnumValidatorImpl extends ValidatorHandler<Integer> implements AuditNotPassReasonEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, Integer target) {
        if (target != null) {
            AuditNotPassReasonEnum auditNotPassReasonEnum = AuditNotPassReasonEnum.parse(target);
            if (auditNotPassReasonEnum == null){
                throw new ValidationException(ParkingConstant.AUDIT_NOT_PASS_REASON_ERROR);
            }
        }
        return true;
    }
}
