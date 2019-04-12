package com.zoeeasy.cloud.pay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.scapegoat.infrastructure.common.utils.NumberUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.common.utils.TimeUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pay.alipay.params.AlipayBillDownloadParam;
import com.zoeeasy.cloud.pay.alipay.result.AlipayDownloadBillResult;
import com.zoeeasy.cloud.pay.alipay.service.AliPayService;
import com.zoeeasy.cloud.pay.config.WechatProperty;
import com.zoeeasy.cloud.pay.constant.alipay.AlipayConst;
import com.zoeeasy.cloud.pay.domain.AlipayBillEntity;
import com.zoeeasy.cloud.pay.domain.WeixinPayBillEntity;
import com.zoeeasy.cloud.pay.enums.WeixinRefundStatusEnum;
import com.zoeeasy.cloud.pay.enums.WeixinTradeStatusEnum;
import com.zoeeasy.cloud.pay.service.AlipayBillCrudService;
import com.zoeeasy.cloud.pay.service.WeixinPayBillCrudService;
import com.zoeeasy.cloud.pay.trade.PayService;
import com.zoeeasy.cloud.pay.trade.dto.request.alipay.AlipayBillDownRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.weixin.WeixinPayBillDownRequestDto;
import com.zoeeasy.cloud.pay.wechat.params.WeChatDownloadBillParams;
import com.zoeeasy.cloud.pay.wechat.result.WeChatPayDownloadBillResult;
import com.zoeeasy.cloud.pay.wechat.service.WeChatPayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 支付服务
 *
 * @author walkman
 * @date 2018-01-11
 */
@Service(value = "payService")
@Slf4j
public class PayServiceImpl implements PayService {

    @Autowired
    private WeixinPayBillCrudService weixinPayBillCrudService;

    @Autowired
    private AlipayBillCrudService alipayBillCrudService;

    @Autowired
    private WeChatPayService weChatPayService;

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WechatProperty wechatProperty;

    /**
     * 支付宝异步通知验签
     *
     * @param map
     * @return
     */
    @Override
    public ResultDto checkAlipaySignature(Map<String, String> map, String publicKey) {
        ResultDto resultDto = new ResultDto();
        try {
            boolean retValue = AlipaySignature.rsaCheckV1(map, publicKey,
                    AlipayConst.CHARSET);
            if (retValue) {
                resultDto.success();
            }
        } catch (AlipayApiException e) {
            log.error("支付宝异步通知验签失败" + e.getErrMsg());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 支付宝账单下载
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto downloadAlipayBill(AlipayBillDownRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();

        try {
            AlipayBillDownloadParam alipayBillDownloadParam = new AlipayBillDownloadParam();
            if (StringUtils.isBlank(requestDto.getBillType())) {
                alipayBillDownloadParam.setBillType("trade");
            } else {
                alipayBillDownloadParam.setBillType(requestDto.getBillType());
            }
            alipayBillDownloadParam.setBillDate(TimeUtils.formatTime(requestDto.getBillDate(), TimeUtils.FORMAT_YYYYMMDD));
            ListResultDto<AlipayDownloadBillResult> downloadBillResultObjectResultDto = aliPayService.downloadBill(alipayBillDownloadParam);
            if (downloadBillResultObjectResultDto.isSuccess()) {

                List<AlipayDownloadBillResult> alipayDownloadBillResultList = downloadBillResultObjectResultDto.getItems();
                if (CollectionUtils.isNotEmpty(alipayDownloadBillResultList)) {
                    List<AlipayBillEntity> alipayBillEntityList = new ArrayList<>();
                    for (AlipayDownloadBillResult billResult : alipayDownloadBillResultList) {
                        AlipayBillEntity alipayBillEntity = modelMapper.map(billResult, AlipayBillEntity.class);
                        alipayBillEntity.setBillDate(requestDto.getBillDate());
                        alipayBillEntityList.add(alipayBillEntity);
                    }
                    alipayBillCrudService.insertBatch(alipayBillEntityList);
                }
            }
        } catch (Exception e) {
            log.error("微信异步通知处理失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto.success();
    }

    /**
     * 微信支付账单下载
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto downloadWeixinPayBill(WeixinPayBillDownRequestDto requestDto) {

        ResultDto resultDto = new ResultDto();
        WeChatDownloadBillParams weChatDownloadBillParams = new WeChatDownloadBillParams();
        weChatDownloadBillParams.setAppid(wechatProperty.getAppId());
        weChatDownloadBillParams.setMchId(wechatProperty.getMchId());
        if (StringUtils.isBlank(requestDto.getBillType())) {
            weChatDownloadBillParams.setBillType("ALL");
        } else {
            weChatDownloadBillParams.setBillType(requestDto.getBillType());
        }
        weChatDownloadBillParams.setBillDate(TimeUtils.formatTime(requestDto.getBillDate(), TimeUtils.FORMAT_YYYYMMDD4));

        ObjectResultDto<WeChatPayDownloadBillResult> downloadBillResultObjectResultDto = weChatPayService.downloadBill(weChatDownloadBillParams);
        if (downloadBillResultObjectResultDto.isSuccess()) {

            WeChatPayDownloadBillResult weChatPayDownloadBillResult = downloadBillResultObjectResultDto.getData();
            if (weChatPayDownloadBillResult != null) {

                if (CollectionUtils.isNotEmpty(weChatPayDownloadBillResult.getWeChatPayBaseBillResults())) {

                    List<WeixinPayBillEntity> weixinPayBillEntityList = new LinkedList<>();
                    weChatPayDownloadBillResult.getWeChatPayBaseBillResults().stream().forEach(bill -> {
                        WeixinPayBillEntity weixinPayBillEntity = new WeixinPayBillEntity();
                        weixinPayBillEntity.setTransactionId(bill.getTransactionId());
                        weixinPayBillEntity.setOutTradeNo(bill.getOutTradeNo());
                        weixinPayBillEntity.setRefundId(bill.getRefundId());
                        weixinPayBillEntity.setOutRefundNo(bill.getOutRefundNo());
                        weixinPayBillEntity.setTradeDate(TimeUtils.parseDate(bill.getTradeTime(), TimeUtils.FORMAT_YYYYMMDD4));
                        weixinPayBillEntity.setTradeType(bill.getTradeType());
                        weixinPayBillEntity.setOpenid(bill.getOpenId());
                        WeixinTradeStatusEnum tradeState = WeixinTradeStatusEnum.getByValue(bill.getTradeState());
                        if (tradeState != null) {
                            weixinPayBillEntity.setTradeState(tradeState.getCode());
                        }
                        weixinPayBillEntity.setBankType(bill.getBankType());
                        weixinPayBillEntity.setFeeType(bill.getFeeType());
                        if (StringUtils.isNotBlank(bill.getTotalFee())) {
                            weixinPayBillEntity.setTotalAmount(NumberUtils.amountYuan2FenInt(bill.getTotalFee()));
                        }
                        if (StringUtils.isNotBlank(bill.getCouponFee())) {
                            weixinPayBillEntity.setCouponAmount(NumberUtils.amountYuan2FenInt(bill.getCouponFee()));
                        }
                        if (StringUtils.isNotBlank(bill.getSettlementRefundFee())) {
                            weixinPayBillEntity.setRefundAmount(NumberUtils.amountYuan2FenInt(bill.getSettlementRefundFee()));
                        }
                        if (StringUtils.isNotBlank(bill.getCouponRefundFee())) {
                            weixinPayBillEntity.setCouponRefundAmount(NumberUtils.amountYuan2FenInt(bill.getCouponRefundFee()));
                        }
                        WeixinRefundStatusEnum refundState = WeixinRefundStatusEnum.getByValue(bill.getRefundState());
                        if (refundState != null) {
                            weixinPayBillEntity.setRefundState(refundState.getCode());
                        }
                        if (StringUtils.isNotBlank(bill.getPoundage())) {
                            weixinPayBillEntity.setFeeRate(NumberUtils.amountYuan2FenInt(bill.getPoundage()));
                        }
                        if (StringUtils.isNotBlank(bill.getPoundageRate())) {
                            weixinPayBillEntity.setPoundageRate(new BigDecimal(bill.getPoundageRate().replace("%", "")));
                        }
                        weixinPayBillEntity.setBillDate(requestDto.getBillDate());
                        weixinPayBillEntityList.add(weixinPayBillEntity);
                    });
                    weixinPayBillCrudService.insertBatch(weixinPayBillEntityList);
                }
            }
            resultDto.success();
        }
        return resultDto;
    }
}
