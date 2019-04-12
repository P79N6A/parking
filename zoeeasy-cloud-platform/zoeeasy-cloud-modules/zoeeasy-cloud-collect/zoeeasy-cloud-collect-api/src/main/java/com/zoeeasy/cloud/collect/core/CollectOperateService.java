package com.zoeeasy.cloud.collect.core;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.collect.dto.request.OpenStrobeRequestDto;
import com.zoeeasy.cloud.collect.dto.request.PaymentNotifyRequestDto;
import com.zoeeasy.cloud.collect.dto.request.QueryPriceRequestDto;

/**
 * 道闸业务下行接口
 *
 * @author wf
 * @date 2019-03-01
 */
public interface CollectOperateService {

    /**
     * 道闸查询价格接口
     *
     * @param requestDto
     * @return
     * @throws ValidationException
     * @throws BusinessException
     */
    ResultDto queryPrice(QueryPriceRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 道闸支付通知接口
     *
     * @param requestDto
     * @return
     * @throws ValidationException
     * @throws BusinessException
     */
    ResultDto paymentNotify(PaymentNotifyRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 道闸远程抬杆接口
     *
     * @param requestDto
     * @return
     * @throws ValidationException
     * @throws BusinessException
     */
    ResultDto openStrobe(OpenStrobeRequestDto requestDto) throws ValidationException, BusinessException;

}
