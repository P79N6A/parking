package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.AuditOpinionEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.AuditOpinionEnumValidator;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class AuditOpinionEnumValidatorImpl extends ValidatorHandler<Integer> implements AuditOpinionEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, Integer target) {
        if (target != null) {
            AuditOpinionEnum auditOpinionEnum = AuditOpinionEnum.parse(target);
            if (auditOpinionEnum == null) {
                throw new ValidationException(ParkingConstant.AUDIT_OPINION_ERROR);
            }
        }
        return true;
    }
}
