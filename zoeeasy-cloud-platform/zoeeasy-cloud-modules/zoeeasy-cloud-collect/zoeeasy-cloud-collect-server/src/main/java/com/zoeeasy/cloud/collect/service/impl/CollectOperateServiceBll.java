package com.zoeeasy.cloud.collect.service.impl;

import com.zoeeasy.cloud.collect.dto.request.OpenStrobeRequestDto;
import com.zoeeasy.cloud.collect.dto.request.PaymentNotifyRequestDto;
import com.zoeeasy.cloud.collect.dto.request.QueryPriceRequestDto;
import com.zoeeasy.cloud.collect.dto.result.PaymentNotifyResultDto;
import com.zoeeasy.cloud.collect.dto.result.QueryPriceResultDto;
import com.zoeeasy.cloud.collect.enums.BizEnum;
import com.zoeeasy.cloud.collect.msgbody.request.OpenStrobeBody;
import com.zoeeasy.cloud.collect.msgbody.request.PaymentNotifyBody;
import com.zoeeasy.cloud.collect.msgbody.request.QueryPriceBody;
import com.zoeeasy.cloud.collect.msgbody.result.PaymentNotifyResultBody;
import com.zoeeasy.cloud.collect.msgbody.result.QueryPriceResultBody;

/**
 * 业务处理请求、结果转换处理
 *
 * @author wf
 */
public class CollectOperateServiceBll {

    public static QueryPriceBody convertQueryPriceRequest(QueryPriceRequestDto requestDto) {
        QueryPriceBody queryPriceReqBody = new QueryPriceBody();
        queryPriceReqBody.setService(BizEnum.QUERY_PRICE.getComment());
        queryPriceReqBody.setLocalCode(requestDto.getLocalCode());
        queryPriceReqBody.setPlateNumber(requestDto.getPlateNumber());
        queryPriceReqBody.setPlateColor(requestDto.getPlateColor());
        return queryPriceReqBody;
    }

    public static QueryPriceResultDto convertQueryPriceResult(QueryPriceResultBody queryPriceRespBody) {
        QueryPriceResultDto queryPriceResultDto = new QueryPriceResultDto();
        queryPriceResultDto.setParkingOrderNo(queryPriceRespBody.getParkingOrderNo());
        queryPriceResultDto.setPlateNumber(queryPriceRespBody.getPlateNumber());
        queryPriceResultDto.setInTime(queryPriceRespBody.getPassInTime());
        queryPriceResultDto.setDuration(queryPriceRespBody.getDuration());
        queryPriceResultDto.setPrice(queryPriceRespBody.getPrice());
        queryPriceResultDto.setFreeOutTime(queryPriceRespBody.getFreeOutTime());
        return queryPriceResultDto;
    }

    public static PaymentNotifyBody convertPaymentAdviceRequest(PaymentNotifyRequestDto requestDto) {
        PaymentNotifyBody resultReqBody = new PaymentNotifyBody();
        resultReqBody.setService(BizEnum.PAYMENT_NOTIFY.getComment());
        resultReqBody.setLocalCode(requestDto.getLocalCode());
        resultReqBody.setGateCode(requestDto.getGateCode());
        resultReqBody.setParkingOrderNo(requestDto.getParkingOrderNo());
        resultReqBody.setPayAmount(requestDto.getPayAmount());
        resultReqBody.setPayTime(requestDto.getPayTime());
        resultReqBody.setPlateNumber(requestDto.getPlateNumber());
        return resultReqBody;
    }

    public static PaymentNotifyResultDto convertPaymentAdviceResult(PaymentNotifyResultBody paymentResultRespBody) {
        PaymentNotifyResultDto paymentNotifyResultDto = new PaymentNotifyResultDto();
        paymentNotifyResultDto.setLocalCode(paymentResultRespBody.getLocalCode());
        paymentNotifyResultDto.setParkingOrderNo(paymentResultRespBody.getParkingOrderNo());
        return paymentNotifyResultDto;
    }

    public static OpenStrobeBody convertOpenStrobeRequest(OpenStrobeRequestDto requestDto) {
        OpenStrobeBody setOpenStrobeReqBody = new OpenStrobeBody();
        setOpenStrobeReqBody.setService(BizEnum.OPEN_STROBE.getComment());
        setOpenStrobeReqBody.setLocalCode(requestDto.getLocalCode());
        setOpenStrobeReqBody.setGateCode(requestDto.getGateCode());
        return setOpenStrobeReqBody;
    }

}
