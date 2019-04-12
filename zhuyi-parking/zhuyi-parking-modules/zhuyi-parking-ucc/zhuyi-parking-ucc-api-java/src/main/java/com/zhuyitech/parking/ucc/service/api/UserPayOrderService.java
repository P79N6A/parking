package com.zhuyitech.parking.ucc.service.api;


import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.ucc.dto.request.trade.CustomerPayOrderGetRequestDto;
import com.zhuyitech.parking.ucc.dto.result.UserPayOrderResultDto;

/**
 * 用户收支明细服务
 *
 * @author zwq
 * @date 2018-08-03
 */
public interface UserPayOrderService {

    /**
     * 获取用户支付订单
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserPayOrderResultDto> getCustomerPayOrder(CustomerPayOrderGetRequestDto requestDto);

}
