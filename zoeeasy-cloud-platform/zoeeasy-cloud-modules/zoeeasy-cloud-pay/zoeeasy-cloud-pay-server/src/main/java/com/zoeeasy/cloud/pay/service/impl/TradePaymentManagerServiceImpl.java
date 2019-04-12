package com.zoeeasy.cloud.pay.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.NumberUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.message.RocketMessage;
import com.zoeeasy.cloud.core.cst.MessageQueueDefinitions;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.core.enums.PayTypeEnum;
import com.zoeeasy.cloud.core.enums.PayWayEnum;
import com.zoeeasy.cloud.message.MessageSendService;
import com.zoeeasy.cloud.message.payload.TradePaymentOrderPayload;
import com.zoeeasy.cloud.pay.alipay.params.AlipayPrecreateOrderParams;
import com.zoeeasy.cloud.pay.alipay.params.AlipayPrepareOrderParams;
import com.zoeeasy.cloud.pay.alipay.result.AliPayPrepareOrderResult;
import com.zoeeasy.cloud.pay.alipay.result.AlipayPrecreateOrderResult;
import com.zoeeasy.cloud.pay.alipay.service.AliPayService;
import com.zoeeasy.cloud.pay.config.AlipayProperty;
import com.zoeeasy.cloud.pay.config.WechatProperty;
import com.zoeeasy.cloud.pay.constant.PaymentConst;
import com.zoeeasy.cloud.pay.constant.wechat.WechatConst;
import com.zoeeasy.cloud.pay.domain.AlipayOrderEntity;
import com.zoeeasy.cloud.pay.domain.TradePaymentOrderEntity;
import com.zoeeasy.cloud.pay.domain.TradePaymentRecordEntity;
import com.zoeeasy.cloud.pay.domain.WeixinPayOrderEntity;
import com.zoeeasy.cloud.pay.enums.AlipayTradeStatusEnum;
import com.zoeeasy.cloud.pay.enums.FundInfoTypeEnum;
import com.zoeeasy.cloud.pay.enums.PayResultEnum;
import com.zoeeasy.cloud.pay.enums.WeixinTradeStatusEnum;
import com.zoeeasy.cloud.pay.exception.TradeBizException;
import com.zoeeasy.cloud.pay.service.AlipayOrderCrudService;
import com.zoeeasy.cloud.pay.service.TradePaymentOrderCrudService;
import com.zoeeasy.cloud.pay.service.TradePaymentRecordCrudService;
import com.zoeeasy.cloud.pay.service.WeixinPayOrderCrudService;
import com.zoeeasy.cloud.pay.trade.TradePaymentManagerService;
import com.zoeeasy.cloud.pay.trade.dto.request.alipay.AlipayAsyncNotifyResultRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.order.PlacePaymentOrderRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.trade.TradePaymentByCustomerOrderGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.trade.TradePaymentOrderGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.trade.TradePaymentRecordGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.trade.TradePaymentUpdatePayStatusRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.result.order.PlacePaymentOrderResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.trade.TradePaymentOrderResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.trade.TradePaymentRecordResultDto;
import com.zoeeasy.cloud.pay.trade.validator.PlacePaymentOrderValidator;
import com.zoeeasy.cloud.pay.wechat.params.WeChatUnifiedOrderParams;
import com.zoeeasy.cloud.pay.wechat.result.WeChatPayOrderNotifyResult;
import com.zoeeasy.cloud.pay.wechat.result.WeChatPayUnifiedOrderResult;
import com.zoeeasy.cloud.pay.wechat.service.WeChatPayService;
import com.zoeeasy.cloud.tool.vesta.intf.IdService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 交易服务(充值，付款)
 *
 * @author walkman
 * @date 2018-01-11
 */
@Service(value = "tradePaymentManagerService")
@Slf4j
public class TradePaymentManagerServiceImpl implements TradePaymentManagerService {

    @Autowired
    private TradePaymentOrderCrudService tradePaymentOrderCrudService;

    @Autowired
    private TradePaymentRecordCrudService tradePaymentRecordCrudService;

    @Autowired
    private AlipayOrderCrudService alipayOrderCrudService;

    @Autowired
    private WeixinPayOrderCrudService weixinPayOrderCrudService;

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private WeChatPayService weChatPayService;

    @Autowired
    private IdService idService;

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlipayProperty alipayProperty;

    @Autowired
    private WechatProperty wechatProperty;

    /**
     * 封装支付宝支付订单信息
     *
     * @param tradePaymentRecordEntity tradePaymentRecordEntity
     * @return
     */
    private AlipayOrderEntity sealAlipayOrder(TradePaymentRecordEntity tradePaymentRecordEntity) {

        AlipayOrderEntity alipayOrderEntity = new AlipayOrderEntity();
        if (tradePaymentRecordEntity.getCustomerUserId() == null) {
            throw new BusinessException("用户ID不能为空");
        }
        alipayOrderEntity.setTenantId(tradePaymentRecordEntity.getTenantId());
        alipayOrderEntity.setCustomerUserId(tradePaymentRecordEntity.getCustomerUserId());
        if (StringUtils.isEmpty(tradePaymentRecordEntity.getOrderNo())) {
            throw new BusinessException("支付宝订单商家订单号错误");
        }
        alipayOrderEntity.setOutTradeNo(tradePaymentRecordEntity.getOrderNo());
        if (StringUtils.isEmpty(tradePaymentRecordEntity.getProductName())) {
            throw new BusinessException("支付宝订单标题不能为空");
        }
        alipayOrderEntity.setSubject(tradePaymentRecordEntity.getProductName());
        alipayOrderEntity.setBody(tradePaymentRecordEntity.getProductName());
        if (tradePaymentRecordEntity.getOrderAmount() == null || tradePaymentRecordEntity.getOrderAmount().compareTo(0) <= 0) {
            throw new BusinessException("支付宝订单金额错误");
        }
        alipayOrderEntity.setQrCodeUrl(tradePaymentRecordEntity.getQrCodeUrl());
        //支付金额
        BigDecimal totalAmount = NumberUtils.amountFen2Yuan(BigDecimal.valueOf(tradePaymentRecordEntity.getOrderAmount()));
        alipayOrderEntity.setTotalAmount(totalAmount);
        alipayOrderEntity.setCurrencyType(PaymentConst.DEFAULT_CURRENCY_TYPE);
        alipayOrderEntity.setTradeStatus(AlipayTradeStatusEnum.WAIT_BUYER_PAY.getValue());
        alipayOrderEntity.setGmtCreate(tradePaymentRecordEntity.getOrderDate());
        return alipayOrderEntity;
    }

    /**
     * 封装微信支付订单信息
     *
     * @param prepayId                 prepayId
     * @param tradePaymentRecordEntity outOrderNo
     * @param spbillCreateIp           spbillCreateIp
     * @param tradeType                tradeType
     * @return
     */
    private WeixinPayOrderEntity sealWeixinPayOrder(TradePaymentRecordEntity tradePaymentRecordEntity,
                                                    String prepayId, String spbillCreateIp, String tradeType,
                                                    String longQrCodeUrl) {

        WeixinPayOrderEntity weixinPayOrderEntity = new WeixinPayOrderEntity();
        if (tradePaymentRecordEntity.getCustomerUserId() == null) {
            throw new BusinessException(PaymentConst.PAYMENT_PAY_USER_NOT_NULL);
        }
        weixinPayOrderEntity.setCustomerUserId(tradePaymentRecordEntity.getCustomerUserId());
        weixinPayOrderEntity.setTenantId(tradePaymentRecordEntity.getTenantId());
        if (StringUtils.isEmpty(tradePaymentRecordEntity.getOrderNo())) {
            throw new BusinessException("微信支付商家订单号错误");
        }
        weixinPayOrderEntity.setOutTradeNo(tradePaymentRecordEntity.getOrderNo());
        if (StringUtils.isEmpty(tradePaymentRecordEntity.getProductName())) {
            throw new BusinessException("微信订单标题不能为空");
        }
        weixinPayOrderEntity.setBody(tradePaymentRecordEntity.getProductName());
        if (StringUtils.isEmpty(prepayId)) {
            throw new BusinessException("微信支付预支付ID不能为空");
        }
        weixinPayOrderEntity.setPrepayId(prepayId);
        if (tradePaymentRecordEntity.getOrderAmount() == null || tradePaymentRecordEntity.getOrderAmount().compareTo(0) <= 0) {
            throw new BusinessException("微信支付订单金额错误");
        }
        weixinPayOrderEntity.setShortQrCodeUrl(tradePaymentRecordEntity.getQrCodeUrl());
        weixinPayOrderEntity.setQrCodeUrl(longQrCodeUrl);
        weixinPayOrderEntity.setTotalFee(tradePaymentRecordEntity.getOrderAmount());
        weixinPayOrderEntity.setSpbillCreateIp(spbillCreateIp);
        weixinPayOrderEntity.setTradeType(tradeType);
        weixinPayOrderEntity.setFeeType(PaymentConst.DEFAULT_CURRENCY_TYPE);
        weixinPayOrderEntity.setTradeState(WeixinTradeStatusEnum.NOTPAY.getCode());
        return weixinPayOrderEntity;
    }

    /**
     * 支付订单下单
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<PlacePaymentOrderResultDto> placePaymentOrder(@FluentValid(PlacePaymentOrderValidator.class)
                                                                                 PlacePaymentOrderRequestDto requestDto) {
        // 先判断支付订单是否已存在
        //通过用户ID、业务订单号
        TradePaymentOrderEntity tradePaymentOrderEntity =
                tradePaymentOrderCrudService.findByCustomerBizOrderNo(requestDto.getPayerUserId(),
                        requestDto.getBizOrderType(), requestDto.getBizOrderNo());
        if (tradePaymentOrderEntity == null) {
            //订单不存在则创建
            tradePaymentOrderEntity = sealTradePaymentOrder(requestDto);
            tradePaymentOrderCrudService.add(tradePaymentOrderEntity);
        } else {
            //订单存在
            if (PayStatusEnum.PAY_SUCCESS.getValue().equals(tradePaymentOrderEntity.getStatus())) {
                throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR, "订单已支付成功,无需重复支付");
            }
            //停车订单提前支付订单金额可能不一致，故不再校验金额一致
        }
        return getPlacePaymentResult(tradePaymentOrderEntity, requestDto);
    }

    /**
     * 通过支付订单及商户费率生成支付记录
     *
     * @param tradePaymentOrderEntity 支付订单
     * @return
     * @throws TradeBizException
     */
    private ObjectResultDto<PlacePaymentOrderResultDto> getPlacePaymentResult(TradePaymentOrderEntity tradePaymentOrderEntity,
                                                                              PlacePaymentOrderRequestDto placePaymentOrderRequestDto) throws TradeBizException {
        ObjectResultDto<PlacePaymentOrderResultDto> resultDto = new ObjectResultDto<>();
        PlacePaymentOrderResultDto placePaymentOrderResponseDto = new PlacePaymentOrderResultDto();
        Double feeRate = 0.0d;
        //支付方式
        PayWayEnum payWayEnum = PayWayEnum.valueOf(placePaymentOrderRequestDto.getPayWay());
        if (payWayEnum == null) {
            throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_INVALID, PaymentConst.PAYMENT_PAY_WAY_INVALID);
        }
        //支付类型
        PayTypeEnum payTypeEnum = PayTypeEnum.valuedOf(placePaymentOrderRequestDto.getPayType());
        if (payTypeEnum == null) {
            throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_INVALID, PaymentConst.PAYMENT_PAY_TYPE_INVALID);
        }
        if (PayWayEnum.ALIPAY.getValue().equals(payWayEnum.getValue())) {
            //支付宝支付
            feeRate = alipayProperty.getFeeRate();
            tradePaymentOrderEntity.setNotifyUrl(alipayProperty.getNotifyUrl());
        } else if (payWayEnum.getValue().equals(PayWayEnum.WEIXINPAY.getValue())) {
            //微信支付
            tradePaymentOrderEntity.setNotifyUrl(wechatProperty.getNotifyUrl());
            if (PayTypeEnum.WX_JSAPI.getValue().equals(placePaymentOrderRequestDto.getPayType())) {
                //微信公众号支付
                feeRate = wechatProperty.getJsFeeRate();
            } else {
                feeRate = wechatProperty.getFeeRate();
            }
        }
        if (feeRate == null || feeRate.compareTo(0.00d) <= 0) {
            feeRate = 0.0d;
        }
        //更新支付方式，因为支付方式可能会变
        tradePaymentOrderEntity.setPayWay(payWayEnum.getValue());
        tradePaymentOrderEntity.setPayType(payTypeEnum.getValue());
        //如果金额不一致，则更新订单金额
        if (tradePaymentOrderEntity.getOrderAmount().compareTo(placePaymentOrderRequestDto.getOrderAmount()) != 0) {
            tradePaymentOrderEntity.setOrderAmount(placePaymentOrderRequestDto.getOrderAmount());
        }
        //创建支付记录
        TradePaymentRecordEntity tradePaymentRecordEntity = sealTradePaymentRecord(tradePaymentOrderEntity, feeRate);
        tradePaymentRecordCrudService.add(tradePaymentRecordEntity);

        //支付订单号
        String payOrderNo = tradePaymentRecordEntity.getOrderNo();
        //更新支付订单号
        tradePaymentOrderEntity.setOrderNo(payOrderNo);

        //支付宝支付
        if (PayWayEnum.ALIPAY.getValue().equals(payWayEnum.getValue())) {
            //支付金额
            BigDecimal totalAmount = NumberUtils.amountFen2Yuan(BigDecimal.valueOf(tradePaymentRecordEntity.getOrderAmount()));
            if (PayTypeEnum.ALI_SCANBAR.getValue().equals(payTypeEnum.getValue())) {
                //支付宝扫码
                AlipayPrecreateOrderParams alipayPrecreateOrderParams = new AlipayPrecreateOrderParams();
                alipayPrecreateOrderParams.setSubject(tradePaymentOrderEntity.getProductName());
                alipayPrecreateOrderParams.setOutTradeNo(payOrderNo);
                //设置金额2位小数
                alipayPrecreateOrderParams.setTotalAmount(totalAmount);
                alipayPrecreateOrderParams.setAppId(alipayProperty.getAppId());
                alipayPrecreateOrderParams.setPrivateKey(alipayProperty.getPrivateKey());
                alipayPrecreateOrderParams.setPublicKey(alipayProperty.getPublicKey());
                //支付宝下单
                ObjectResultDto<AlipayPrecreateOrderResult> alipayPrecreateOrderResult = aliPayService.precreateOrder(alipayPrecreateOrderParams);
                if (alipayPrecreateOrderResult.isFailed() || alipayPrecreateOrderResult.getData() == null) {
                    throw new TradeBizException(TradeBizException.TRADE_ALIPAY_ERROR, PayResultEnum.ALIPAY_PLACE_ORDER_ERROR.getComment());
                }
                AlipayPrecreateOrderResult alipayPrecreateOrder = alipayPrecreateOrderResult.getData();
                placePaymentOrderResponseDto.setQrCode(alipayPrecreateOrder.getQrCode());
                placePaymentOrderResponseDto.setOrderNo(tradePaymentOrderEntity.getBizOrderNo());
                placePaymentOrderResponseDto.setPayOrderNo(payOrderNo);
                resultDto.setData(placePaymentOrderResponseDto);
                tradePaymentOrderEntity.setQrCodeUrl(alipayPrecreateOrder.getQrCode());
            } else if (PayTypeEnum.ALI_MINI.getValue().equals(placePaymentOrderRequestDto.getPayType())) {
                //支付宝小程序
                AlipayPrepareOrderParams alipayPrepareOrderParams = new AlipayPrepareOrderParams();
                alipayPrepareOrderParams.setSubject(tradePaymentOrderEntity.getProductName());
                alipayPrepareOrderParams.setOutTradeNo(payOrderNo);
                //设置金额2位小数
                alipayPrepareOrderParams.setTotalAmount(totalAmount);
                alipayPrepareOrderParams.setPublicKey(alipayProperty.getPublicKey());
                alipayPrepareOrderParams.setAppId(alipayProperty.getMiniAppId());
                alipayPrepareOrderParams.setPrivateKey(alipayProperty.getMiniPrivateKey());
                //支付宝下单
                ObjectResultDto<AliPayPrepareOrderResult> aliPayResult = aliPayService.preparePay(alipayPrepareOrderParams);
                if (aliPayResult.isFailed() || aliPayResult.getData() == null) {
                    throw new TradeBizException(TradeBizException.TRADE_ALIPAY_ERROR, PayResultEnum.ALIPAY_PLACE_ORDER_ERROR.getComment());
                }
                AliPayPrepareOrderResult aliPayPrepareOrderResult = aliPayResult.getData();
                placePaymentOrderResponseDto.setOrderNo(tradePaymentOrderEntity.getBizOrderNo());
                placePaymentOrderResponseDto.setPayOrderNo(payOrderNo);
                placePaymentOrderResponseDto.setAlipayOrderInfo(aliPayPrepareOrderResult.getOrderInfo());
                resultDto.setData(placePaymentOrderResponseDto);
            } else {
                AlipayPrepareOrderParams alipayPrepareOrderParams = new AlipayPrepareOrderParams();
                alipayPrepareOrderParams.setAppId(alipayProperty.getAppId());
                alipayPrepareOrderParams.setPrivateKey(alipayProperty.getPrivateKey());
                alipayPrepareOrderParams.setPublicKey(alipayProperty.getPublicKey());
                alipayPrepareOrderParams.setSubject(tradePaymentOrderEntity.getProductName());
                alipayPrepareOrderParams.setOutTradeNo(payOrderNo);
                //设置金额2位小数
                alipayPrepareOrderParams.setTotalAmount(totalAmount);
                //支付宝下单
                ObjectResultDto<AliPayPrepareOrderResult> aliPayResult = aliPayService.preparePay(alipayPrepareOrderParams);
                if (aliPayResult.isFailed() || aliPayResult.getData() == null) {
                    throw new TradeBizException(TradeBizException.TRADE_ALIPAY_ERROR, PayResultEnum.ALIPAY_PLACE_ORDER_ERROR.getComment());
                }
                AliPayPrepareOrderResult aliPayPrepareOrderResult = aliPayResult.getData();
                placePaymentOrderResponseDto.setOrderNo(tradePaymentOrderEntity.getBizOrderNo());
                placePaymentOrderResponseDto.setPayOrderNo(payOrderNo);
                placePaymentOrderResponseDto.setAlipayOrderInfo(aliPayPrepareOrderResult.getOrderInfo());
                resultDto.setData(placePaymentOrderResponseDto);
            }
            AlipayOrderEntity alipayOrderEntity = sealAlipayOrder(tradePaymentRecordEntity);
            alipayOrderCrudService.add(alipayOrderEntity);
            resultDto.success();
        } else if (payWayEnum.getValue().equals(PayWayEnum.WEIXINPAY.getValue())) {
            //微信支付
            WeChatUnifiedOrderParams weChatUnifiedOrderParams =
                    createWeChatUnifiedOrderParams(payTypeEnum, payOrderNo, placePaymentOrderRequestDto.getOpenId(), tradePaymentOrderEntity);
            if (weChatUnifiedOrderParams == null) {
                throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_ERROR, PaymentConst.PAYMENT_PAY_TYPE_INVALID);
            }
            //微信支付统一下单
            ObjectResultDto<WeChatPayUnifiedOrderResult> objectResultDto = weChatPayService.unifiedOrder(weChatUnifiedOrderParams);
            if (objectResultDto.isFailed() || objectResultDto.getData() == null) {
                throw new TradeBizException(TradeBizException.TRADE_WEIXIN_ERROR, PayResultEnum.WECHAT_PLACE_ORDER_ERROR.getComment());
            }
            WeChatPayUnifiedOrderResult weChatPayUnifiedOrderResult = objectResultDto.getData();
            String longQrCodeUrl = "";
            String qrCodeUrl = "";
            if (PayTypeEnum.WX_NATIVE.getValue().equals(placePaymentOrderRequestDto.getPayType())) {
                //微信NATIVE支付
                tradePaymentOrderEntity.setQrCodeUrl(weChatPayUnifiedOrderResult.getCodeURL());
                qrCodeUrl = weChatPayUnifiedOrderResult.getCodeURL();
            }
            //微信订单记录
            WeixinPayOrderEntity weixinPayOrderEntity = sealWeixinPayOrder(
                    tradePaymentRecordEntity,
                    weChatPayUnifiedOrderResult.getPrepayId(),
                    tradePaymentOrderEntity.getOrderIp(),
                    payTypeEnum.getWay(), longQrCodeUrl);
            weixinPayOrderCrudService.add(weixinPayOrderEntity);

            placePaymentOrderResponseDto.setPrepayId(weChatPayUnifiedOrderResult.getPrepayId());
            placePaymentOrderResponseDto.setTradeType(weChatPayUnifiedOrderResult.getTradeType());
            placePaymentOrderResponseDto.setPackages(weChatPayUnifiedOrderResult.getPack());
            placePaymentOrderResponseDto.setNonceStr(weChatPayUnifiedOrderResult.getNonceStr());
            placePaymentOrderResponseDto.setAppId(weChatPayUnifiedOrderResult.getAppid());
            placePaymentOrderResponseDto.setPartnerId(weChatPayUnifiedOrderResult.getMchId());
            placePaymentOrderResponseDto.setTimeStamp(weChatPayUnifiedOrderResult.getTimeStamp());
            placePaymentOrderResponseDto.setPaySign(weChatPayUnifiedOrderResult.getSign());
            placePaymentOrderResponseDto.setOrderNo(tradePaymentOrderEntity.getBizOrderNo());
            placePaymentOrderResponseDto.setPayOrderNo(payOrderNo);
            placePaymentOrderResponseDto.setQrCode(qrCodeUrl);
            resultDto.setData(placePaymentOrderResponseDto);
            resultDto.success();
        } else {
            throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_ERROR, PaymentConst.PAYMENT_PAY_WAY_INVALID);
        }
        // 更新支付订单（因为支付方式可能会变换）
        tradePaymentOrderCrudService.updateByCustomerBizOrderNo(tradePaymentOrderEntity);

        //发送支付时限消息
        RocketMessage<TradePaymentOrderPayload> message = new RocketMessage<>();
        message.setMessageId(StringUtils.getUUID());
        message.setDestination(MessageQueueDefinitions.Topic.DELAY_TRADE_PAYMENT);
        message.setSender(MessageQueueDefinitions.Sender.ORDER);
        TradePaymentOrderPayload tradePaymentOrderPayload = new TradePaymentOrderPayload();
        tradePaymentOrderPayload.setOrderNo(tradePaymentRecordEntity.getOrderNo());
        tradePaymentOrderPayload.setCustomerId(tradePaymentRecordEntity.getCustomerUserId());
        tradePaymentOrderPayload.setBizType(tradePaymentRecordEntity.getBizOrderType());
        tradePaymentOrderPayload.setBizOrderNo(tradePaymentRecordEntity.getBizOrderNo());
        tradePaymentOrderPayload.setOrderAmount(tradePaymentRecordEntity.getOrderAmount());
        tradePaymentOrderPayload.setPayWay(tradePaymentRecordEntity.getPayWay());
        tradePaymentOrderPayload.setPayType(tradePaymentRecordEntity.getPayType());
        message.setPayload(tradePaymentOrderPayload);
        messageSendService.sendAndSaveSync(message);
        return resultDto;
    }

    /**
     * 创建微信统一下单支付参数
     *
     * @param payTypeEnum
     * @param payOrderNo
     * @param openId
     * @param tradePaymentOrderEntity
     * @return
     */
    private WeChatUnifiedOrderParams createWeChatUnifiedOrderParams(PayTypeEnum payTypeEnum,
                                                                    String payOrderNo,
                                                                    String openId,
                                                                    TradePaymentOrderEntity tradePaymentOrderEntity) {
        WeChatUnifiedOrderParams weChatUnifiedOrderParams = new WeChatUnifiedOrderParams();
        if (PayTypeEnum.WX_APP.getValue().equals(payTypeEnum.getValue())) {
            //微信APP支付
            weChatUnifiedOrderParams.setAppid(wechatProperty.getAppId());
            weChatUnifiedOrderParams.setMchId(wechatProperty.getMchId());
            weChatUnifiedOrderParams.setSignKey(wechatProperty.getSignKey());
            weChatUnifiedOrderParams.setNotifyURL(wechatProperty.getNotifyUrl());
            weChatUnifiedOrderParams.setOutTradeNo(payOrderNo);
            weChatUnifiedOrderParams.setBody(tradePaymentOrderEntity.getProductName());
            weChatUnifiedOrderParams.setTotalFee(tradePaymentOrderEntity.getOrderAmount());
            weChatUnifiedOrderParams.setSpbillCreateIp(tradePaymentOrderEntity.getOrderIp());
            weChatUnifiedOrderParams.setTradeType(WechatConst.TRADE_TYPE_APP);
            weChatUnifiedOrderParams.setNonceStr(StringUtils.getUUID());
        } else if (PayTypeEnum.WX_JSAPI.getValue().equals(payTypeEnum.getValue())) {
            //微信公众号支付
            weChatUnifiedOrderParams.setAppid(wechatProperty.getJsApiAppId());
            weChatUnifiedOrderParams.setMchId(wechatProperty.getJsMchId());
            weChatUnifiedOrderParams.setSignKey(wechatProperty.getJsApiSinKey());
            weChatUnifiedOrderParams.setNotifyURL(wechatProperty.getNotifyUrl());
            weChatUnifiedOrderParams.setOutTradeNo(payOrderNo);
            weChatUnifiedOrderParams.setBody(tradePaymentOrderEntity.getProductName());
            weChatUnifiedOrderParams.setTotalFee(tradePaymentOrderEntity.getOrderAmount());
            weChatUnifiedOrderParams.setSpbillCreateIp(tradePaymentOrderEntity.getOrderIp());
            weChatUnifiedOrderParams.setTradeType(WechatConst.TRADE_TYPE_JSAPI);
            weChatUnifiedOrderParams.setNonceStr(StringUtils.getUUID());
            weChatUnifiedOrderParams.setOpenId(openId);
        } else if (PayTypeEnum.WX_MINI.getValue().equals(payTypeEnum.getValue())) {
            //微信小程序支付
            weChatUnifiedOrderParams.setAppid(wechatProperty.getWxMiniAppId());
            weChatUnifiedOrderParams.setMchId(wechatProperty.getWxMiniMchId());
            weChatUnifiedOrderParams.setSignKey(wechatProperty.getWxMiniApiSignKey());
            weChatUnifiedOrderParams.setNotifyURL(wechatProperty.getNotifyUrl());
            weChatUnifiedOrderParams.setOutTradeNo(payOrderNo);
            weChatUnifiedOrderParams.setBody(tradePaymentOrderEntity.getProductName());
            weChatUnifiedOrderParams.setTotalFee(tradePaymentOrderEntity.getOrderAmount());
            weChatUnifiedOrderParams.setSpbillCreateIp(tradePaymentOrderEntity.getOrderIp());
            weChatUnifiedOrderParams.setTradeType(WechatConst.TRADE_TYPE_MINI);
            weChatUnifiedOrderParams.setNonceStr(StringUtils.getUUID());
            weChatUnifiedOrderParams.setOpenId(openId);
        } else if (PayTypeEnum.WX_NATIVE.getValue().equals(payTypeEnum.getValue())) {
            //微信NATIVE支付
            weChatUnifiedOrderParams.setAppid(wechatProperty.getAppId());
            weChatUnifiedOrderParams.setMchId(wechatProperty.getMchId());
            weChatUnifiedOrderParams.setSignKey(wechatProperty.getSignKey());
            weChatUnifiedOrderParams.setNotifyURL(wechatProperty.getNotifyUrl());
            weChatUnifiedOrderParams.setOutTradeNo(payOrderNo);
            weChatUnifiedOrderParams.setBody(tradePaymentOrderEntity.getProductName());
            weChatUnifiedOrderParams.setTotalFee(tradePaymentOrderEntity.getOrderAmount());
            weChatUnifiedOrderParams.setSpbillCreateIp(tradePaymentOrderEntity.getOrderIp());
            weChatUnifiedOrderParams.setTradeType(WechatConst.TRADE_TYPE_NATIVE);
            weChatUnifiedOrderParams.setNonceStr(StringUtils.getUUID());
        } else {
//            throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_ERROR, PaymentConst.PAYMENT_PAY_TYPE_INVALID);
            return null;
        }
        return weChatUnifiedOrderParams;
    }


    /**
     * 支付订单实体封装
     *
     * @param placePaymentOrderRequestDto placePaymentOrderRequestDto
     * @return
     */
    private TradePaymentOrderEntity sealTradePaymentOrder(PlacePaymentOrderRequestDto placePaymentOrderRequestDto) {

        log.info("sealTradePaymentOrder");
        TradePaymentOrderEntity tradePaymentOrderEntity = new TradePaymentOrderEntity();
        if (placePaymentOrderRequestDto.getTenantId() != null) {
            tradePaymentOrderEntity.setTenantId(placePaymentOrderRequestDto.getTenantId());
        }
        tradePaymentOrderEntity.setCustomerUserId(placePaymentOrderRequestDto.getPayerUserId());
        //业务订单号
        tradePaymentOrderEntity.setBizOrderType(placePaymentOrderRequestDto.getBizOrderType());
        tradePaymentOrderEntity.setBizOrderNo(placePaymentOrderRequestDto.getBizOrderNo());
        //商品名称
        tradePaymentOrderEntity.setProductName(placePaymentOrderRequestDto.getProductName());
        //订单金额
        tradePaymentOrderEntity.setOrderAmount(placePaymentOrderRequestDto.getOrderAmount());
        //下单日期
        tradePaymentOrderEntity.setOrderDate(placePaymentOrderRequestDto.getOrderDate());
        //下单时间
        tradePaymentOrderEntity.setOrderTime(placePaymentOrderRequestDto.getOrderTime());
        //下单IP
        tradePaymentOrderEntity.setOrderIp(placePaymentOrderRequestDto.getOrderIp());
        //下单前页面
        tradePaymentOrderEntity.setOrderRefererUrl("");
//        if (StringUtils.isEmpty(placePaymentOrderRequestDto.getReturnUrl())) {
//            throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR, "页面通知错误");
//        }
//        //页面通知地址
        tradePaymentOrderEntity.setReturnUrl(placePaymentOrderRequestDto.getReturnUrl());
        //订单有效期
        if (placePaymentOrderRequestDto.getOrderPeriod() == null || placePaymentOrderRequestDto.getOrderPeriod() <= 0) {
//            throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR, "订单有效期错误");
            tradePaymentOrderEntity.setOrderPeriod(30);
        } else {
            tradePaymentOrderEntity.setOrderPeriod(placePaymentOrderRequestDto.getOrderPeriod());
            //订单过期时间
            Date expireTime = DateUtils.addMinutes(placePaymentOrderRequestDto.getOrderTime(), placePaymentOrderRequestDto.getOrderPeriod());
            //订单过期时间
            tradePaymentOrderEntity.setExpireTime(expireTime);
        }
        //支付通道名称
        PayWayEnum payWay = PayWayEnum.valueOf(placePaymentOrderRequestDto.getPayWay());
        if (payWay == null) {
            throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_ERROR, PaymentConst.PAYMENT_PAY_TYPE_INVALID);
        }
        //支付类型
        PayTypeEnum payType = PayTypeEnum.valuedOf(placePaymentOrderRequestDto.getPayType());
        if (payType == null) {
            throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_ERROR, PaymentConst.PAYMENT_PAY_TYPE_INVALID);
        }
        if (payWay.getValue().equals(PayWayEnum.WEIXINPAY.getValue())) {
            //微信支付
            if (PayTypeEnum.WX_APP.getValue().equals(payType.getValue())) {
                //微信APP支付
                tradePaymentOrderEntity.setNotifyUrl(wechatProperty.getNotifyUrl());
            } else if (PayTypeEnum.WX_JSAPI.getValue().equals(payType.getValue())) {
                //微信公众号支付
                tradePaymentOrderEntity.setNotifyUrl(wechatProperty.getNotifyUrl());
            }
        } else if (payWay.getValue().equals(PayWayEnum.ALIPAY.getValue())) {
            tradePaymentOrderEntity.setNotifyUrl(alipayProperty.getNotifyUrl());
        }
        tradePaymentOrderEntity.setPayWay(payWay.getValue());
        tradePaymentOrderEntity.setPayType(payType.getValue());
        //资金流入方向
        tradePaymentOrderEntity.setFundIntoType(FundInfoTypeEnum.PLATFORM_RECEIVES.getValue());
        //订单状态 等待支付
        tradePaymentOrderEntity.setStatus(PayStatusEnum.PAY_WAITING.getValue());
        //支付备注
        tradePaymentOrderEntity.setRemark(placePaymentOrderRequestDto.getRemark());
        tradePaymentOrderEntity.setCreationTime(DateUtils.now());
        log.info("sealTradePaymentOrder封装数据结束");
        return tradePaymentOrderEntity;
    }

    /**
     * 封装支付流水记录实体
     *
     * @param tradePaymentOrderEntity 支付订单
     * @param feeRate                 第三方支付费率
     * @return
     */
    private TradePaymentRecordEntity sealTradePaymentRecord(TradePaymentOrderEntity tradePaymentOrderEntity, Double feeRate) {

        log.info("sealTradePaymentRecord封装数据开始");
        TradePaymentRecordEntity tradePaymentRecordEntity = new TradePaymentRecordEntity();
        //支付用户
        tradePaymentRecordEntity.setTenantId(tradePaymentOrderEntity.getTenantId());
        tradePaymentRecordEntity.setCustomerUserId(tradePaymentOrderEntity.getCustomerUserId());
        tradePaymentRecordEntity.setBizOrderType(tradePaymentOrderEntity.getBizOrderType());
        tradePaymentRecordEntity.setBizOrderNo(tradePaymentOrderEntity.getBizOrderNo());
        //产品名称
        tradePaymentRecordEntity.setProductName(tradePaymentOrderEntity.getProductName());
        //支付订单号
        String payOrderNo = String.valueOf(this.idService.genId());
        tradePaymentRecordEntity.setOrderNo(payOrderNo);
        tradePaymentRecordEntity.setOrderDate(tradePaymentOrderEntity.getOrderDate());
        tradePaymentRecordEntity.setOrderTime(tradePaymentOrderEntity.getOrderTime());
        //下单IP
        tradePaymentRecordEntity.setOrderIp(tradePaymentOrderEntity.getOrderIp());
        //下单前页面
        tradePaymentRecordEntity.setOrderRefererUrl(tradePaymentOrderEntity.getOrderRefererUrl());
        //页面通知地址
        tradePaymentRecordEntity.setReturnUrl(tradePaymentOrderEntity.getReturnUrl());
        //后台通知地址
        tradePaymentRecordEntity.setNotifyUrl(tradePaymentOrderEntity.getNotifyUrl());
        //订单来源
        tradePaymentRecordEntity.setOrderFrom(tradePaymentOrderEntity.getOrderFrom());
        //订单金额
        tradePaymentRecordEntity.setOrderAmount(tradePaymentOrderEntity.getOrderAmount());
        //订单状态 等待支付
        tradePaymentRecordEntity.setStatus(PayStatusEnum.PAY_WAITING.getValue());
        //资金流入方向
        tradePaymentRecordEntity.setFundIntoType(tradePaymentOrderEntity.getFundIntoType());
        //支付通道名称
        tradePaymentRecordEntity.setPayWay(tradePaymentOrderEntity.getPayWay());
        //支付类型
        tradePaymentRecordEntity.setPayType(tradePaymentOrderEntity.getPayType());
        //支付订单表
        if (FundInfoTypeEnum.PLATFORM_RECEIVES.getValue().equals(tradePaymentOrderEntity.getFundIntoType())) {
            //平台收款 需要修改费率 成本 利润 收入 以及修改商户账户信息
            //订单金额
            //TODO 统一按分计算 yuhuanlong
            BigDecimal orderAmount = BigDecimal.valueOf(tradePaymentRecordEntity.getOrderAmount()).divide(new BigDecimal(100))
                    .setScale(0, BigDecimal.ROUND_DOWN);
            //平台收入 = 订单金额 * 支付费率(设置的费率除以100为真实费率)
            BigDecimal platIncome = orderAmount.multiply(BigDecimal.valueOf(1.0 - feeRate)).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
            //平台成本 = 订单金额 * 支付费率(设置的费率除以100为真实费率)
            BigDecimal platCost = orderAmount.multiply(BigDecimal.valueOf(feeRate).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP));
            //平台利润 = 平台收入 - 平台成本
            BigDecimal platProfit = platIncome.subtract(platCost);
            //费率
            tradePaymentRecordEntity.setFeeRate(BigDecimal.valueOf(feeRate));
            //平台成本
            tradePaymentRecordEntity.setPlatformCost(NumberUtils.amountYuan2Fen(platCost).intValue());
            //平台收入
            tradePaymentRecordEntity.setPlatformIncome(NumberUtils.amountYuan2Fen(platIncome).intValue());
            //平台利润
            tradePaymentRecordEntity.setPlatformProfit(NumberUtils.amountYuan2Fen(platProfit).intValue());
        }
        tradePaymentRecordEntity.setPayerUserId(tradePaymentOrderEntity.getCustomerUserId());
        //支付备注
        tradePaymentRecordEntity.setRemark(tradePaymentOrderEntity.getRemark());
        tradePaymentRecordEntity.setCreationTime(DateUtils.now());
        log.info("sealTradePaymentRecord封装数据结束");
        return tradePaymentRecordEntity;
    }

    /**
     * 获取支付订单
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ObjectResultDto<TradePaymentOrderResultDto> getTradePaymentOrder(TradePaymentOrderGetRequestDto requestDto) {
        ObjectResultDto<TradePaymentOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            TradePaymentOrderEntity tradePaymentOrderEntity = tradePaymentOrderCrudService.findByCustomerBizOrderNo(requestDto.getCustomerUserId(),
                    requestDto.getBizOrderType(),
                    requestDto.getBizOrderNo());
            if (tradePaymentOrderEntity != null) {
                TradePaymentOrderResultDto resultDto = modelMapper.map(tradePaymentOrderEntity, TradePaymentOrderResultDto.class);
                objectResultDto.setData(resultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取支付订单失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据支付用户ID、支付订单号获取支付订单
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ObjectResultDto<TradePaymentOrderResultDto> getTradePaymentOrder(TradePaymentByCustomerOrderGetRequestDto requestDto) {
        ObjectResultDto<TradePaymentOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            TradePaymentOrderEntity tradePaymentRecordEntity =
                    tradePaymentOrderCrudService.findByCustomerOrderNo(requestDto.getCustomerUserId(), requestDto.getOrderNo());
            if (tradePaymentRecordEntity != null) {
                TradePaymentOrderResultDto resultDto = modelMapper.map(tradePaymentRecordEntity, TradePaymentOrderResultDto.class);
                objectResultDto.setData(resultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取支付记录失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取支付记录
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ObjectResultDto<TradePaymentRecordResultDto> getTradePaymentRecord(TradePaymentRecordGetRequestDto requestDto) {
        ObjectResultDto<TradePaymentRecordResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            TradePaymentRecordEntity tradePaymentRecordEntity = tradePaymentRecordCrudService.getByOrderNo(requestDto.getOrderNo());
            if (tradePaymentRecordEntity != null) {
                TradePaymentRecordResultDto resultDto = modelMapper.map(tradePaymentRecordEntity, TradePaymentRecordResultDto.class);
                objectResultDto.setData(resultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取支付记录失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据支付用户ID、支付订单号获取支付记录
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ObjectResultDto<TradePaymentRecordResultDto> getTradePaymentRecord(TradePaymentByCustomerOrderGetRequestDto requestDto) {
        ObjectResultDto<TradePaymentRecordResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            TradePaymentRecordEntity tradePaymentRecordEntity =
                    tradePaymentRecordCrudService.getByCustomerOrderNo(requestDto.getCustomerUserId(), requestDto.getOrderNo());
            if (tradePaymentRecordEntity != null) {
                TradePaymentRecordResultDto resultDto = modelMapper.map(tradePaymentRecordEntity, TradePaymentRecordResultDto.class);
                objectResultDto.setData(resultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取支付记录失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 修改支付订单支付状态tradePaymentRecord
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ResultDto updatePayStatusTradePaymentRecord(TradePaymentUpdatePayStatusRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Integer update = tradePaymentRecordCrudService.updatePayStatus(
                    requestDto.getCustomerUserId(), requestDto.getOrderNo(),
                    requestDto.getTransactionNo(), requestDto.getSuccessPayTime(), requestDto.getPayStatus());
            if (update == 0) {
                return resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取支付记录失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改支付订单支付状态tradePaymentOrder
     *
     * @param requestDto payStatus
     * @return
     */
    @Override
    public ResultDto updatePayStatusTradePaymentOrder(TradePaymentUpdatePayStatusRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Integer update = tradePaymentOrderCrudService.updatePayStatus(
                    requestDto.getCustomerUserId(), requestDto.getOrderNo(), requestDto.getTransactionNo(),
                    requestDto.getSuccessPayTime(), requestDto.getPayStatus());
            if (update == 0) {
                return resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取支付记录失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 更新支付宝订单
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ResultDto updateAlipayOrder(AlipayAsyncNotifyResultRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            AlipayOrderEntity alipayOrderEntity = new AlipayOrderEntity();
            alipayOrderEntity.setTradeNo(requestDto.getTradeNo());
            alipayOrderEntity.setOutTradeNo(requestDto.getOutTradeNo());
            alipayOrderEntity.setOutBizNo(requestDto.getOutBizNo());
            alipayOrderEntity.setSubject(requestDto.getSubject());
            alipayOrderEntity.setTotalAmount(requestDto.getTotalAmount());
            alipayOrderEntity.setReceiptAmount(requestDto.getReceiptAmount());
            alipayOrderEntity.setRefundFee(requestDto.getRefundFee());
            alipayOrderEntity.setBuyerPayAmount(requestDto.getBuyerPayamount());
            alipayOrderEntity.setBuyerId(requestDto.getBuyerId());
            alipayOrderEntity.setBuyerLogonId(requestDto.getBuyerLogonId());
            alipayOrderEntity.setSellerId(requestDto.getSellerId());
            alipayOrderEntity.setSellerEmail(requestDto.getSellerEmail());
            alipayOrderEntity.setTotalAmount(requestDto.getTotalAmount());
            alipayOrderEntity.setReceiptAmount(requestDto.getReceiptAmount());
            alipayOrderEntity.setInvoiceAmount(requestDto.getInvoiceAmount());
            alipayOrderEntity.setBuyerPayAmount(requestDto.getBuyerPayamount());
            alipayOrderEntity.setPointAmount(requestDto.getPointAmount());
            alipayOrderEntity.setRefundFee(requestDto.getRefundFee());
            alipayOrderEntity.setSubject(requestDto.getSubject());
            alipayOrderEntity.setBody(requestDto.getBody());
            alipayOrderEntity.setGmtCreate(requestDto.getGmtCreate());
            alipayOrderEntity.setGmtPayment(requestDto.getGmtPayment());
            alipayOrderEntity.setGmtRefund(requestDto.getGmtRefund());
            alipayOrderEntity.setGmtClose(requestDto.getGmtClose());
            alipayOrderEntity.setFundBillList(requestDto.getFundBillList());
            alipayOrderEntity.setPassbackParams(requestDto.getPassbackParams());
            alipayOrderEntity.setVoucherDetailList(requestDto.getVoucherDetailList());
            //交易状态
            if (requestDto.getTradeStatus().equals(AlipayTradeStatusEnum.TRADE_SUCCESS.name())) {
                alipayOrderEntity.setTradeStatus(AlipayTradeStatusEnum.TRADE_SUCCESS.getValue());
            } else if (requestDto.getTradeStatus().equals(AlipayTradeStatusEnum.TRADE_FINISHED.name())) {
                alipayOrderEntity.setTradeStatus(AlipayTradeStatusEnum.TRADE_FINISHED.getValue());
            } else if (requestDto.getTradeStatus().equals(AlipayTradeStatusEnum.TRADE_CLOSED.name())) {
                alipayOrderEntity.setTradeStatus(AlipayTradeStatusEnum.TRADE_CLOSED.getValue());
            }
            alipayOrderCrudService.updateByOutOrderNo(alipayOrderEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("更新支付宝订单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 更新微信订单
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ResultDto updateWeixinOrder(WeChatPayOrderNotifyResult requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            WeixinPayOrderEntity weixinPayOrderEntity = new WeixinPayOrderEntity();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date endTime = sdf.parse(requestDto.getTimeEnd());
            weixinPayOrderEntity.setTransactionId(requestDto.getTransactionId());
            weixinPayOrderEntity.setTimeEnd(endTime);
            weixinPayOrderEntity.setDeviceInfo(requestDto.getDeviceInfo());
            weixinPayOrderEntity.setTotalFee(requestDto.getTotalFee());
            weixinPayOrderEntity.setCashFee(requestDto.getCashFee());
            weixinPayOrderEntity.setCashFeeType(requestDto.getCashFeeType());
            weixinPayOrderEntity.setCouponFee(requestDto.getCouponFee());
            weixinPayOrderEntity.setOutTradeNo(requestDto.getOutTradeNo());

            //交易状态
            if ("SUCCESS".equals(requestDto.getResultCode())) {
                weixinPayOrderEntity.setTradeState(WeixinTradeStatusEnum.SUCCESS.getCode());
            } else if ("FAIL".equals(requestDto.getResultCode())) {
                weixinPayOrderEntity.setTradeState(WeixinTradeStatusEnum.PAYERROR.getCode());
            } else if ("CLOSED".equals(requestDto.getResultCode())) {
                weixinPayOrderEntity.setTradeState(WeixinTradeStatusEnum.CLOSED.getCode());
            }
            weixinPayOrderCrudService.updateByOutOrderNo(weixinPayOrderEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("更新微信订单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
