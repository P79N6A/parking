package com.zoeeasy.cloud.pay.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.pay.domain.AlipayOrderEntity;
import com.zoeeasy.cloud.pay.domain.TradePaymentOrderEntity;
import com.zoeeasy.cloud.pay.domain.TradePaymentRecordEntity;
import com.zoeeasy.cloud.pay.domain.WeixinPayOrderEntity;
import com.zoeeasy.cloud.pay.service.AlipayOrderCrudService;
import com.zoeeasy.cloud.pay.service.TradePaymentOrderCrudService;
import com.zoeeasy.cloud.pay.service.TradePaymentRecordCrudService;
import com.zoeeasy.cloud.pay.service.WeixinPayOrderCrudService;
import com.zoeeasy.cloud.pay.trade.TradePaymentQueryService;
import com.zoeeasy.cloud.pay.trade.dto.request.record.*;
import com.zoeeasy.cloud.pay.trade.dto.result.record.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户微信、支付宝支付记录以及充值记录查询
 *
 * @Date: 2018/3/5
 * @author: yuzhicheng
 */
@Service(value = "tradePaymentQueryService")
@Slf4j
public class TradePaymentQueryServiceImpl implements TradePaymentQueryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WeixinPayOrderCrudService weixinPayOrderCrudService;

    @Autowired
    private AlipayOrderCrudService alipayOrderCrudService;

    @Autowired
    private TradePaymentOrderCrudService tradePaymentOrderCrudService;

    @Autowired
    private TradePaymentRecordCrudService tradePaymentRecordCrudService;

    /**
     * 分页获取微信支付记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<WxPayOrderResultDto> getWeiXinPayPagedList(WeiXinPayRecordQueryPageRequestDto requestDto) {
        PagedResultDto<WxPayOrderResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<WeixinPayOrderEntity> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getOutTradeNo())) {
                entityWrapper.eq("outTradeNo", requestDto.getOutTradeNo());
            }
            if (!StringUtils.isEmpty(requestDto.getTransactionId())) {
                entityWrapper.eq("transactionId", requestDto.getTransactionId());
            }
            if (requestDto.getTradeState() != null) {
                entityWrapper.eq("tradeState", requestDto.getTradeState());
            }
            if (!StringUtils.isEmpty(requestDto.getBody())) {
                entityWrapper.eq("body", requestDto.getBody());
            }
            if (!StringUtils.isEmpty(requestDto.getDetail())) {
                entityWrapper.eq("detail", requestDto.getDetail());
            }
            if (requestDto.getTotalFee() != null) {
                entityWrapper.eq("totalFee", requestDto.getTotalFee());
            }
            if (requestDto.getTimeStart() != null) {
                entityWrapper.eq("timeStart", requestDto.getTimeStart());
            }
            if (requestDto.getTimeEnd() != null) {
                entityWrapper.eq("timeEnd", requestDto.getTimeEnd());
            }
            Page<WeixinPayOrderEntity> weixinPayOrder = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<WeixinPayOrderEntity> weixinPayOrderPage = weixinPayOrderCrudService.selectPage(weixinPayOrder, entityWrapper);
            if (weixinPayOrderPage != null) {

                List<WxPayOrderResultDto> weiXinPayRecordResultDtos = modelMapper.map(weixinPayOrderPage.getRecords(), new TypeToken<List<WxPayOrderResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(weixinPayOrderPage.getCurrent());
                pagedResultDto.setPageSize(weixinPayOrderPage.getSize());
                pagedResultDto.setTotalCount(weixinPayOrderPage.getTotal());
                pagedResultDto.setItems(weiXinPayRecordResultDtos);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取微信支付记录失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取微信支付列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<WxPayOrderResultDto> getWeiXinPayList(WeiXinPayRecordListGetRequestDto requestDto) {
        ListResultDto<WxPayOrderResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<WeixinPayOrderEntity> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getOutTradeNo())) {
                entityWrapper.eq("outTradeNo", requestDto.getOutTradeNo());
            }
            if (!StringUtils.isEmpty(requestDto.getTransactionId())) {
                entityWrapper.eq("transactionId", requestDto.getTransactionId());
            }
            if (requestDto.getTradeState() != null) {
                entityWrapper.eq("tradeState", requestDto.getTradeState());
            }
            if (!StringUtils.isEmpty(requestDto.getBody())) {
                entityWrapper.eq("body", requestDto.getBody());
            }
            if (!StringUtils.isEmpty(requestDto.getDetail())) {
                entityWrapper.eq("detail", requestDto.getDetail());
            }
            if (requestDto.getTotalFee() != null) {
                entityWrapper.eq("totalFee", requestDto.getTotalFee());
            }
            if (requestDto.getTimeStart() != null) {
                entityWrapper.eq("timeStart", requestDto.getTimeStart());
            }
            if (requestDto.getTimeEnd() != null) {
                entityWrapper.eq("timeEnd", requestDto.getTimeEnd());
            }
            List<WeixinPayOrderEntity> weixinPayOrderEntities = weixinPayOrderCrudService.selectList(entityWrapper);
            if (!weixinPayOrderEntities.isEmpty()) {
                List<WxPayOrderResultDto> weiXinPayRecordResultDtos = modelMapper.map(weixinPayOrderEntities, new TypeToken<List<WxPayOrderResultDto>>() {
                }.getType());
                listResultDto.setItems(weiXinPayRecordResultDtos);
                listResultDto.success();
            }
        } catch (Exception e) {
            log.error("获取微信支付记录列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取微信支付记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<WxPayOrderResultDto> getWeiXinPay(WeiXinPayRecordGetRequestDto requestDto) {
        ObjectResultDto<WxPayOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<WeixinPayOrderEntity> entityWrapper = new EntityWrapper<>();
            if (requestDto.getId() != null) {
                entityWrapper.eq("id", requestDto.getId());
            }
            WeixinPayOrderEntity weixinPayOrderEntity = weixinPayOrderCrudService.selectOne(entityWrapper);
            WxPayOrderResultDto weiXinPayRecordResultDto = modelMapper.map(weixinPayOrderEntity, WxPayOrderResultDto.class);
            objectResultDto.setData(weiXinPayRecordResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取微信支付记录失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 分页获取支付宝支付记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<AliPayOrderResultDto> getAliPayPagedList(AliPayOrderQueryPageRequestDto requestDto) {
        PagedResultDto<AliPayOrderResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<AlipayOrderEntity> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getTradeNo())) {
                entityWrapper.eq("tradeNo", requestDto.getTradeNo());
            }
            if (!StringUtils.isEmpty(requestDto.getOutTradeNo())) {
                entityWrapper.eq("outTradeNo", requestDto.getOutTradeNo());
            }
            if (!StringUtils.isEmpty(requestDto.getOutBizNo())) {
                entityWrapper.eq("outBizNo", requestDto.getOutBizNo());
            }
            if (!StringUtils.isEmpty(requestDto.getSubject())) {
                entityWrapper.eq("subject", requestDto.getSubject());
            }
            if (requestDto.getTotalAmount() != null) {
                entityWrapper.eq("totalAmount", requestDto.getTotalAmount());
            }
            if (requestDto.getReceiptAmount() != null) {
                entityWrapper.eq("receiptAmount", requestDto.getReceiptAmount());
            }
            if (requestDto.getInvoiceAmount() != null) {
                entityWrapper.eq("invoiceAmount", requestDto.getInvoiceAmount());
            }
            if (requestDto.getBuyerPayAmount() != null) {
                entityWrapper.eq("buyerPayAmount", requestDto.getBuyerPayAmount());
            }
            if (requestDto.getPointAmount() != null) {
                entityWrapper.eq("pointAmount", requestDto.getPointAmount());
            }
            if (requestDto.getRefundFee() != null) {
                entityWrapper.eq("refundFee", requestDto.getRefundFee());
            }
            if (!StringUtils.isEmpty(requestDto.getBody())) {
                entityWrapper.eq("body", requestDto.getBody());
            }
            if (!StringUtils.isEmpty(requestDto.getCurrencyType())) {
                entityWrapper.eq("currencyType", requestDto.getCurrencyType());
            }
            if (requestDto.getGmtCreate() != null) {
                entityWrapper.eq("gmtCreate", requestDto.getGmtCreate());
            }
            if (requestDto.getGmtPayment() != null) {
                entityWrapper.eq("gmtPayment", requestDto.getGmtPayment());
            }
            if (requestDto.getGmtRefund() != null) {
                entityWrapper.eq("gmtRefund", requestDto.getGmtRefund());
            }
            if (requestDto.getGmtClose() != null) {
                entityWrapper.eq("gmtClose", requestDto.getGmtClose());
            }
            Page<AlipayOrderEntity> alipayOrder = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<AlipayOrderEntity> alipayOrderPage = alipayOrderCrudService.selectPage(alipayOrder, entityWrapper);
            if (alipayOrderPage != null) {

                List<AliPayOrderResultDto> aliPayRecordResultDtos = modelMapper.map(alipayOrderPage.getRecords(), new TypeToken<List<AliPayOrderResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(alipayOrderPage.getCurrent());
                pagedResultDto.setPageSize(alipayOrderPage.getSize());
                pagedResultDto.setTotalCount(alipayOrderPage.getTotal());
                pagedResultDto.setItems(aliPayRecordResultDtos);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取支付宝支付记录失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取支付宝支付记录列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<AliPayOrderResultDto> getAliPayList(AliPayOrderListGetRequestDto requestDto) {
        ListResultDto<AliPayOrderResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<AlipayOrderEntity> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getTradeNo())) {
                entityWrapper.eq("tradeNo", requestDto.getTradeNo());
            }
            if (!StringUtils.isEmpty(requestDto.getOutTradeNo())) {
                entityWrapper.eq("outTradeNo", requestDto.getOutTradeNo());
            }
            if (!StringUtils.isEmpty(requestDto.getOutBizNo())) {
                entityWrapper.eq("outBizNo", requestDto.getOutBizNo());
            }
            if (!StringUtils.isEmpty(requestDto.getSubject())) {
                entityWrapper.eq("subject", requestDto.getSubject());
            }
            if (requestDto.getTotalAmount() != null) {
                entityWrapper.eq("totalAmount", requestDto.getTotalAmount());
            }
            if (requestDto.getReceiptAmount() != null) {
                entityWrapper.eq("receiptAmount", requestDto.getReceiptAmount());
            }
            if (requestDto.getInvoiceAmount() != null) {
                entityWrapper.eq("invoiceAmount", requestDto.getInvoiceAmount());
            }
            if (requestDto.getBuyerPayAmount() != null) {
                entityWrapper.eq("buyerPayAmount", requestDto.getBuyerPayAmount());
            }
            if (requestDto.getPointAmount() != null) {
                entityWrapper.eq("pointAmount", requestDto.getPointAmount());
            }
            if (requestDto.getRefundFee() != null) {
                entityWrapper.eq("refundFee", requestDto.getRefundFee());
            }
            if (!StringUtils.isEmpty(requestDto.getBody())) {
                entityWrapper.eq("body", requestDto.getBody());
            }
            if (!StringUtils.isEmpty(requestDto.getCurrencyType())) {
                entityWrapper.eq("currencyType", requestDto.getCurrencyType());
            }
            if (requestDto.getGmtCreate() != null) {
                entityWrapper.eq("gmtCreate", requestDto.getGmtCreate());
            }
            if (requestDto.getGmtPayment() != null) {
                entityWrapper.eq("gmtPayment", requestDto.getGmtPayment());
            }
            if (requestDto.getGmtRefund() != null) {
                entityWrapper.eq("gmtRefund", requestDto.getGmtRefund());
            }
            if (requestDto.getGmtClose() != null) {
                entityWrapper.eq("gmtClose", requestDto.getGmtClose());
            }
            List<AlipayOrderEntity> alipayOrderEntities = alipayOrderCrudService.selectList(entityWrapper);
            if (!alipayOrderEntities.isEmpty()) {
                List<AliPayOrderResultDto> aliPayRecordResultDtos = modelMapper.map(alipayOrderEntities, new TypeToken<List<AliPayOrderResultDto>>() {
                }.getType());
                listResultDto.setItems(aliPayRecordResultDtos);
                listResultDto.success();
            }
        } catch (Exception e) {
            log.error("获取支付宝支付记录列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取支付宝支付记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<AliPayOrderResultDto> getAliPay(AliPayOrderGetRequestDto requestDto) {
        ObjectResultDto<AliPayOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            //TODO
//            EntityWrapper<AlipayOrderEntity> entityWrapper = new EntityWrapper<>();
//            if (requestDto.getId() != null) {
//                entityWrapper.eq("id", requestDto.getId());
//            }
//            AlipayOrderEntity alipayOrderEntity = alipayOrderCrudService.selectOne(entityWrapper);
//            AliPayOrderResultDto aliPayRecordResultDto = modelMapper.map(alipayOrderEntity, AliPayOrderResultDto.class);
//            objectResultDto.setData(aliPayRecordResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取支付宝支付记录失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据bizOrderType和bizOrderNo查询支付记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<PaymentRecordGetResultDto> getByBizOrderNoAndType(PaymentRecordGetByBizOrderRequestDto requestDto) {
        ObjectResultDto<PaymentRecordGetResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<TradePaymentRecordEntity> entityWrapper = new EntityWrapper<>();
            if (StringUtils.isNotEmpty(requestDto.getBizOrderNo())) {
                entityWrapper.eq("bizOrderNo", requestDto.getBizOrderNo());
            }
            if (null != requestDto.getBizOrderType()) {
                entityWrapper.eq("bizOrderType", requestDto.getBizOrderType());
            }
            TradePaymentRecordEntity tradePaymentRecordEntity = tradePaymentRecordCrudService.getByBizOrderNo(entityWrapper);
            if (null != tradePaymentRecordEntity) {
                PaymentRecordGetResultDto paymentRecordGetResultDto = modelMapper.map(tradePaymentRecordEntity, PaymentRecordGetResultDto.class);
                objectResultDto.setData(paymentRecordGetResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取支记录失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据订单查询
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<PaymentOrderGetByBizOrderResultDto> getPayByBizOrderNo(PaymentOrderGetByBizOrderRequestDto requestDto) {

        ListResultDto<PaymentOrderGetByBizOrderResultDto> objectResultDto = new ListResultDto<>();
        try {
            EntityWrapper<TradePaymentOrderEntity> entityWrapper = new EntityWrapper<>();
            if (StringUtils.isNotEmpty(requestDto.getBizOrderNo())) {
                entityWrapper.eq("bizOrderNo", requestDto.getBizOrderNo());
            }
            List<TradePaymentOrderEntity> tradePaymentOrderEntityList = tradePaymentOrderCrudService.selectList(entityWrapper);
            List<PaymentOrderGetByBizOrderResultDto> getByOrderNoResultDtoList = new ArrayList<>();
            for (TradePaymentOrderEntity tradePaymentOrderEntity : tradePaymentOrderEntityList) {
                PaymentOrderGetByBizOrderResultDto getByOrderNoResultDto = modelMapper.map(tradePaymentOrderEntity, PaymentOrderGetByBizOrderResultDto.class);
                getByOrderNoResultDtoList.add(getByOrderNoResultDto);
            }
            objectResultDto.setItems(getByOrderNoResultDtoList);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取支记录失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据订单查询
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<PaymentRecordResultDto> getTradePaymentRecordByOrderNo(PaymentRecordGetByOrderNoRequestDto requestDto) {
        ObjectResultDto<PaymentRecordResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            TradePaymentRecordEntity tradePaymentRecordEntity = tradePaymentRecordCrudService.getByOrderNo(requestDto.getOrderNo());
            if (null != tradePaymentRecordEntity) {
                PaymentRecordResultDto paymentRecordGetByOrderNoResultDto = modelMapper.map(tradePaymentRecordEntity, PaymentRecordResultDto.class);
                objectResultDto.setData(paymentRecordGetByOrderNoResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取支记录失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据outOrderNo获取支付宝支付记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<AliPayOrderResultDto> getAliPayByOutOrderNo(AliPayOrderByCustomerOrderNoGetRequestDto requestDto) {
        ObjectResultDto<AliPayOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<AlipayOrderEntity> entityWrapper = new EntityWrapper<>();
            if (StringUtils.isNotEmpty(requestDto.getOutOrderNo())) {
                entityWrapper.eq("outTradeNo", requestDto.getOutOrderNo());
            }
            AlipayOrderEntity alipayOrderEntity = alipayOrderCrudService.get(entityWrapper);
            if (null != alipayOrderEntity) {
                AliPayOrderResultDto aliPayRecordResultDto = modelMapper.map(alipayOrderEntity, AliPayOrderResultDto.class);
                objectResultDto.setData(aliPayRecordResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取支付宝支付记录失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据outOrderNo获取微信支付记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<WxPayOrderResultDto> getWeiXinPayByOutOrderNo(WxPayOrderByCustomerOrderNoGetRequestDto requestDto) {
        ObjectResultDto<WxPayOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<WeixinPayOrderEntity> entityWrapper = new EntityWrapper<>();
            if (StringUtils.isNotEmpty(requestDto.getOutOrderNo())) {
                entityWrapper.eq("outTradeNo", requestDto.getOutOrderNo());
            }
            WeixinPayOrderEntity weixinPayOrderEntity = weixinPayOrderCrudService.get(entityWrapper);
            if (null != weixinPayOrderEntity) {
                WxPayOrderResultDto weiXinPayRecordResultDto = modelMapper.map(weixinPayOrderEntity, WxPayOrderResultDto.class);
                objectResultDto.setData(weiXinPayRecordResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取支付宝支付记录失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
