package com.zoeeasy.cloud.integration.trade;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.integration.trade.dto.ParkingOrderChargeRequestDto;
import com.zoeeasy.cloud.integration.trade.dto.ParkingOrderChargeResultDto;

/**
 * 支付收款业务处理
 *
 * @author walkman
 */
public interface TradeChargeIntegrationService {

    /**
     * 订单二维码线下收款
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingOrderChargeResultDto> chargeParkingOrder(ParkingOrderChargeRequestDto requestDto) throws ValidationException, BusinessException;

}
