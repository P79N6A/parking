package com.zoeeasy.cloud.notify.validator;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.notify.cts.NotifyConstant;
import com.zoeeasy.cloud.notify.domain.Notification;
import com.zoeeasy.cloud.notify.notification.dto.request.NotificationReadRequestDto;
import com.zoeeasy.cloud.notify.notification.validator.NotificationReadRequestDtoValidator;
import com.zoeeasy.cloud.notify.service.NotificationCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @date: 2018/11/19.
 * @author：zm
 */
@Component
public class NotificationReadRequestDtoValidatorImpl extends ValidatorHandler<NotificationReadRequestDto> implements NotificationReadRequestDtoValidator {

    @Autowired
    private NotificationCrudService notificationCrudService;

    @Override
    public boolean accept(ValidatorContext context, NotificationReadRequestDto requestDto) {
        if (requestDto.getNotifyId() != null) {
            Notification notification = notificationCrudService.findByUserIdAndId(FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId(), requestDto.getNotifyId());
            if (notification == null) {
                throw new ValidationException(NotifyConstant.NOTIFY_ID_NOT_EXIST);
            }
        }
        return true;
    }
}
