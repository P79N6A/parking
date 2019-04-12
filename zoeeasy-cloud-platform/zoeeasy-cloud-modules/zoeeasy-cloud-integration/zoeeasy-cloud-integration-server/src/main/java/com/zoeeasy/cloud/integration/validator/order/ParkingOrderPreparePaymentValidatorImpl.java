package com.zoeeasy.cloud.integration.validator.order;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.integration.order.dto.request.ParkingOrderPreparePaymentRequestDto;
import com.zoeeasy.cloud.integration.order.validator.ParkingOrderPreparePaymentValidator;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import com.zoeeasy.cloud.order.parking.PlatformParkingOrderService;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderGetByOrderNoRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 停车账单支付准备校验
 *
 * @author walkman
 * @since 2018-12-10
 */
@Component
public class ParkingOrderPreparePaymentValidatorImpl extends ValidatorHandler<ParkingOrderPreparePaymentRequestDto> implements ParkingOrderPreparePaymentValidator {

    @Autowired
    private PlatformParkingOrderService platformParkingOrderService;

    @Override
    public boolean validate(ValidatorContext context, ParkingOrderPreparePaymentRequestDto requestDto) {

        ParkingOrderGetByOrderNoRequestDto parkingOrderGetByOrderNoRequestDto = new ParkingOrderGetByOrderNoRequestDto();
        parkingOrderGetByOrderNoRequestDto.setOrderNo(requestDto.getOrderNo());
        ObjectResultDto<ParkingOrderResultDto> parkingOrderResultDto = platformParkingOrderService.getByOrderNo(parkingOrderGetByOrderNoRequestDto);
        if (parkingOrderResultDto.isFailed() || parkingOrderResultDto.getData() == null) {
            throw new ValidationException(OrderConstant.PARKING_ORDER_NO_NOT_FOUND);
        }
        ParkingOrderResultDto parkingOrder = parkingOrderResultDto.getData();
        //判断支付状态
        //只有在未支付和等待支付才允许支付
        PayStatusEnum payStatusEnum = PayStatusEnum.parse(parkingOrder.getPayStatus());
        if (payStatusEnum == null) {
            throw new ValidationException(OrderConstant.PARKING_ORDER_PAY_STATUS_INVALID);
        }
        if (PayStatusEnum.PAY_SUCCESS.getValue().equals(payStatusEnum.getValue())) {
            throw new ValidationException(OrderConstant.PARKING_ORDER_PAYED);
        }
        return super.validate(context, requestDto);
    }
}
