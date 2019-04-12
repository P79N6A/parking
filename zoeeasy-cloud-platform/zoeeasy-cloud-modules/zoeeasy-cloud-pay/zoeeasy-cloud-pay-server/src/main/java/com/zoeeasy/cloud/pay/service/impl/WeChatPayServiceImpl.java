package com.zoeeasy.cloud.pay.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.scapegoat.infrastructure.common.utils.IOUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.pay.config.WechatProperty;
import com.zoeeasy.cloud.pay.constant.wechat.WechatConst;
import com.zoeeasy.cloud.pay.wechat.bean.*;
import com.zoeeasy.cloud.pay.wechat.params.*;
import com.zoeeasy.cloud.pay.wechat.result.*;
import com.zoeeasy.cloud.pay.wechat.service.WeChatPayService;
import com.zoeeasy.cloud.pay.wechat.util.SignUtils;
import com.zoeeasy.cloud.pay.wechat.util.XmlJudgeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     微信支付接口实现
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-13:13
 */
@Service("weChatPayService")
@Slf4j
public class WeChatPayServiceImpl implements WeChatPayService {

    @Autowired
    private WechatProperty wechatProperty;

    /**
     * @param unifiedOrderParams WeChatUnifiedOrderParams
     * @return ObjectResultDto<WeChatPayUnifiedOrderResult>
     */
    @Override
    public ObjectResultDto<WeChatPayUnifiedOrderResult> unifiedOrder(WeChatUnifiedOrderParams unifiedOrderParams) {
        ObjectResultDto<WeChatPayUnifiedOrderResult> objectResultEx = new ObjectResultDto<>();
        // 设置统一下单的请求参数对象
        WeChatPayUnifiedOrderBean unifiedOrderBean = new WeChatPayUnifiedOrderBean();
        // 构建统一下单的请求bean
        BeanUtils.copyProperties(unifiedOrderParams, unifiedOrderBean);
        // 构建签名map
        Map<String, String> signMap = new HashMap<>();
        signMap.put("appid", unifiedOrderBean.getAppid());
        signMap.put("mch_id", unifiedOrderBean.getMchId());
        signMap.put("nonce_str", unifiedOrderBean.getNonceStr());
        signMap.put("body", unifiedOrderBean.getBody());
        signMap.put("out_trade_no", unifiedOrderBean.getOutTradeNo());
        signMap.put("total_fee", unifiedOrderBean.getTotalFee().toString());
        signMap.put("spbill_create_ip", unifiedOrderBean.getSpbillCreateIp());
        signMap.put("notify_url", unifiedOrderBean.getNotifyURL());
        signMap.put("trade_type", unifiedOrderBean.getTradeType());
        if (StringUtils.isNotEmpty(unifiedOrderBean.getOpenId())) {
            signMap.put("openid", unifiedOrderBean.getOpenId());
        }
        signMap.put("sign_type", WechatConst.SIGN_TYPE);
        // 对参数进行签名
        String sign = SignUtils.createSign(signMap, unifiedOrderParams.getSignKey());
        unifiedOrderBean.setSign(sign);
        unifiedOrderBean.setSignType(WechatConst.SIGN_TYPE);

        log.info("统一下单的bean转换为xml：{}", unifiedOrderBean.toXML());
        // 调用微信请求接口
        ObjectResultDto<String> resultEx = postWeChatCommon(wechatProperty.getPrefix() + WechatConst.WECHAT_PAY_URL_UNIFIED_ORDER, unifiedOrderBean.toXML());
        if (resultEx.isFailed()) {
            return objectResultEx.makeResult(resultEx);
        }
        // 设置result
        WeChatPayUnifiedOrderResult unifiedOrderResult = WeChatPayBaseResult.fromXML(resultEx.getData(), WeChatPayUnifiedOrderResult.class);
        if (WechatConst.WECHAT_FAIL.equals(unifiedOrderResult.getResultCode()) ||
                WechatConst.WECHAT_FAIL.equals(unifiedOrderResult.getReturnCode())) {
            String errorMsg = unifiedOrderResult.getErrCodeDes() == null ? unifiedOrderResult.getReturnMsg() : unifiedOrderResult.getErrCodeDes();
            return objectResultEx.failed(errorMsg);
        }
        unifiedOrderResult.setTimeStamp(String.valueOf(System.currentTimeMillis() / 1000));
        Map<String, String> signMapSec = new HashMap<>();
        switch (unifiedOrderParams.getTradeType()) {
            case WechatConst.TRADE_TYPE_JSAPI:
                signMapSec.put("appId", unifiedOrderResult.getAppid());
                signMapSec.put("signType", WechatConst.SIGN_TYPE);
                signMapSec.put("package", "prepay_id=" + unifiedOrderResult.getPrepayId());
                signMapSec.put("nonceStr", unifiedOrderResult.getNonceStr());
                signMapSec.put("timeStamp", unifiedOrderResult.getTimeStamp());
                break;
            case WechatConst.TRADE_TYPE_APP:
                signMapSec.put("appid", unifiedOrderResult.getAppid());
                signMapSec.put("partnerid", unifiedOrderResult.getMchId());
                signMapSec.put("prepayid", unifiedOrderResult.getPrepayId());
                signMapSec.put("package", "Sign=WXPay");
                signMapSec.put("noncestr", unifiedOrderResult.getNonceStr());
                signMapSec.put("timestamp", unifiedOrderResult.getTimeStamp());
                break;
            case WechatConst.TRADE_TYPE_NATIVE:
                signMapSec.put("appid", unifiedOrderResult.getAppid());
                signMapSec.put("mch_id", unifiedOrderResult.getMchId());
                signMapSec.put("prepayid", unifiedOrderResult.getPrepayId());
                signMapSec.put("code_url", unifiedOrderResult.getCodeURL());
                signMapSec.put("package", "Sign=WXPay");
                signMapSec.put("noncestr", unifiedOrderResult.getNonceStr());
                signMapSec.put("timestamp", unifiedOrderResult.getTimeStamp());
                break;
            default:
                return objectResultEx.failed();
        }
        String signSec = SignUtils.createSign(signMapSec, unifiedOrderParams.getSignKey());
        unifiedOrderResult.setSign(signSec);
        unifiedOrderResult.setPack("Sign=WXPay");
        objectResultEx.setData(unifiedOrderResult);
        return objectResultEx.success();
    }

    /**
     * 微信查询订单
     *
     * @param orderQueryParams
     * @return
     */
    @Override
    public ObjectResultDto<WeChatPayOrderQueryResult> orderQuery(WeChatOrderQueryParams orderQueryParams) {
        ObjectResultDto<WeChatPayOrderQueryResult> objectResultEx = new ObjectResultDto<>();
        // 查询订单参数
        WeChatPayOrderQueryBean orderQueryBean = new WeChatPayOrderQueryBean();
        BeanUtils.copyProperties(orderQueryParams, orderQueryBean);
        // 构建签名map
        Map<String, String> signMap = new HashMap<>();
        signMap.put("appid", orderQueryBean.getAppid());
        signMap.put("mch_id", orderQueryBean.getMchId());
        signMap.put("nonce_str", orderQueryBean.getNonceStr());
        if (!StringUtils.isEmpty(orderQueryBean.getOutTradeNo())) {
            signMap.put("out_trade_no", orderQueryBean.getOutTradeNo());
        }
        if (!StringUtils.isEmpty(orderQueryBean.getTransactionId())) {
            signMap.put("transaction_id", orderQueryBean.getTransactionId());
        }
        // 对参数进行签名
        String sign = SignUtils.createSign(signMap, orderQueryParams.getSignKey());
        orderQueryBean.setSign(sign);

        // 调用微信请求接口
        ObjectResultDto<String> resultEx = this.postWeChatCommon(wechatProperty.getPrefix() + WechatConst.WECHAT_PAY_URL_ORDER_QUERY, orderQueryBean.toXML());
        if (resultEx.isFailed()) {
            return objectResultEx.makeResult(resultEx);
        }
        WeChatPayOrderQueryResult orderQueryResult = WeChatPayBaseResult.fromXML(resultEx.getData(), WeChatPayOrderQueryResult.class);
        if (WechatConst.WECHAT_FAIL.equals(orderQueryResult.getResultCode())
                || WechatConst.WECHAT_FAIL.equals(orderQueryResult.getReturnCode())) {
            String errorMsg = orderQueryResult.getErrCodeDes() == null ? orderQueryResult.getReturnMsg() : orderQueryResult.getErrCodeDes();
            return objectResultEx.failed(errorMsg);
        }
        objectResultEx.setData(orderQueryResult);
        return objectResultEx.success();
    }

    /**
     * 微信关闭订单
     *
     * @param closeOrderParams
     * @return
     */
    @Override
    public ObjectResultDto<WeChatPayCloseOrderResult> closeOrder(WeChatCloseOrderParams closeOrderParams) {
        ObjectResultDto<WeChatPayCloseOrderResult> objectResultEx = new ObjectResultDto<>();
        // 关闭订单参数
        WeChatPayCloseOrderBean closeOrderBean = new WeChatPayCloseOrderBean();
        BeanUtils.copyProperties(closeOrderParams, closeOrderBean);
        // 构建签名map
        Map<String, String> signMap = new HashMap<>();
        signMap.put("appid", closeOrderBean.getAppid());
        signMap.put("mch_id", closeOrderBean.getMchId());
        signMap.put("nonce_str", closeOrderBean.getNonceStr());
        signMap.put("out_trade_no", closeOrderBean.getOutTradeNo());
        // 对参数进行签名
        String sign = SignUtils.createSign(signMap, closeOrderParams.getSignKey());
        closeOrderBean.setSign(sign);

        // 调用微信请求接口
        ObjectResultDto<String> resultEx = this.postWeChatCommon(wechatProperty.getPrefix() + WechatConst.WECHAT_PAY_URL_CLOSE_ORDER, closeOrderBean.toXML());
        if (resultEx.isFailed()) {
            return objectResultEx.makeResult(resultEx);
        }
        WeChatPayCloseOrderResult closeOrderResult = WeChatPayBaseResult.fromXML(resultEx.getData(), WeChatPayCloseOrderResult.class);
        if (WechatConst.WECHAT_FAIL.equals(closeOrderResult.getResultCode()) ||
                WechatConst.WECHAT_FAIL.equals(closeOrderResult.getReturnCode())) {
            String errorMsg = closeOrderResult.getErrCodeDes() == null ? closeOrderResult.getReturnMsg() : closeOrderResult.getErrCodeDes();
            return objectResultEx.failed(errorMsg);
        }
        objectResultEx.setData(closeOrderResult);
        return objectResultEx.success();
    }

    @Override
    public ObjectResultDto<WeChatPayRefundResult> refund(WeChatRefundParams refundParams) {
        ObjectResultDto<WeChatPayRefundResult> objectResultEx = new ObjectResultDto<>();
        // 申请退款参数
        WeChatPayRefundBean refundBean = new WeChatPayRefundBean();
        BeanUtils.copyProperties(refundParams, refundBean);
        // 构建签名map
        Map<String, String> signMap = new HashMap<>();
        signMap.put("appid", refundBean.getAppid());
        signMap.put("mch_id", refundBean.getMchId());
        signMap.put("nonce_str", refundBean.getNonceStr());
        // 判断微信订单号、商户订单号是否为空
        if (!StringUtils.isEmpty(refundBean.getOutTradeNo())) {
            signMap.put("out_trade_no", refundBean.getOutTradeNo());
        }
        if (!StringUtils.isEmpty(refundBean.getTransactionId())) {
            signMap.put("transaction_id", refundBean.getTransactionId());
        }
        signMap.put("out_refund_no", refundBean.getOutRefundNo());
        signMap.put("total_fee", refundBean.getTotalFee().toString());
        signMap.put("refund_fee", refundBean.getRefundFee().toString());
        // 对参数进行签名
        String sign = SignUtils.createSign(signMap, refundParams.getSignKey());
        refundBean.setSign(sign);

        log.info("申请退款的bean转换为xml：{}", refundBean.toXML());
        // 调用微信请求接口
        ObjectResultDto<String> resultEx = this.postWithKey(wechatProperty.getPrefix() + WechatConst.WECHAT_PAY_URL_REFUND, refundBean.toXML());
        if (resultEx.isFailed()) {
            return objectResultEx.makeResult(resultEx);
        }
        WeChatPayRefundResult refundResult = WeChatPayBaseResult.fromXML(resultEx.getData(), WeChatPayRefundResult.class);
        if (WechatConst.WECHAT_FAIL.equals(refundResult.getResultCode()) || WechatConst.WECHAT_FAIL.equals(refundResult.getReturnCode())) {
            String errorMsg = refundResult.getErrCodeDes() == null ? refundResult.getReturnMsg() : refundResult.getErrCodeDes();
            return objectResultEx.failed(errorMsg);
        }
        objectResultEx.setData(refundResult);
        return objectResultEx.success();
    }

    @Override
    public ObjectResultDto<WeChatPayRefundQueryResult> refundQuery(WeChatRefundQueryParams refundQueryParams) {
        ObjectResultDto<WeChatPayRefundQueryResult> objectResultEx = new ObjectResultDto<>();
        // 查询退款参数
        WeChatPayRefundQueryBean refundQueryBean = new WeChatPayRefundQueryBean();
        BeanUtils.copyProperties(refundQueryParams, refundQueryBean);
        // 构建签名map
        Map<String, String> signMap = new HashMap<>();
        signMap.put("appid", refundQueryBean.getAppid());
        signMap.put("mch_id", refundQueryBean.getMchId());
        signMap.put("nonce_str", refundQueryBean.getNonceStr());
        // 判断微信订单号、商户订单号是否为空
        if (!StringUtils.isEmpty(refundQueryBean.getOutTradeNo())) {
            signMap.put("out_trade_no", refundQueryBean.getOutTradeNo());
        }
        if (!StringUtils.isEmpty(refundQueryBean.getTransactionId())) {
            signMap.put("transaction_id", refundQueryBean.getTransactionId());
        }
        if (!StringUtils.isEmpty(refundQueryBean.getOutRefundNo())) {
            signMap.put("out_refund_no", refundQueryBean.getOutRefundNo());
        }
        if (!StringUtils.isEmpty(refundQueryBean.getRefundId())) {
            signMap.put("refund_id", refundQueryBean.getRefundId());
        }
        // 对参数进行签名
        String sign = SignUtils.createSign(signMap, refundQueryParams.getSignKey());
        refundQueryBean.setSign(sign);

        // 调用微信请求接口
        ObjectResultDto<String> resultEx = this.postWeChatCommon(wechatProperty.getPrefix() + WechatConst.WECHAT_PAY_URL_REFUND_QUERY, refundQueryBean.toXML());
        if (resultEx.isFailed()) {
            return objectResultEx.makeResult(resultEx);
        }
        WeChatPayRefundQueryResult refundQueryResult = WeChatPayBaseResult.fromXML(resultEx.getData(), WeChatPayRefundQueryResult.class);
        if (WechatConst.WECHAT_FAIL.equals(refundQueryResult.getResultCode()) || WechatConst.WECHAT_FAIL.equals(refundQueryResult.getReturnCode())) {
            String errorMsg = refundQueryResult.getErrCodeDes() == null ? refundQueryResult.getReturnMsg() : refundQueryResult.getErrCodeDes();
            return objectResultEx.failed(errorMsg);
        }
        objectResultEx.setData(refundQueryResult);
        return objectResultEx.success();
    }

    @Override
    public ObjectResultDto<WeChatPayOrderNotifyResult> orderNotify(String notifyXML) {
        ObjectResultDto<WeChatPayOrderNotifyResult> objectResultEx = new ObjectResultDto<>();
        try {
            log.info("微信支付回调参数详细：{}", notifyXML);
            WeChatPayOrderNotifyResult notifyResult = WeChatPayBaseResult.fromXML(notifyXML, WeChatPayOrderNotifyResult.class);
            objectResultEx.setData(notifyResult);
            return objectResultEx.success();
        } catch (Exception e) {
            log.error("微信支付通知解析异常：{}", e);
            return objectResultEx.failed("微信支付通知解析异常");
        }
    }

    @Override
    public ObjectResultDto<WeChatPayRefundNotifyResult> refundNotify(String notifyXML) {
        ObjectResultDto<WeChatPayRefundNotifyResult> objectResultEx = new ObjectResultDto<>();
        try {
            log.info("微信退款回调参数详细：{}", notifyXML);
            WeChatPayRefundNotifyResult notifyResult = WeChatPayBaseResult.fromXML(notifyXML, WeChatPayRefundNotifyResult.class);
            objectResultEx.setData(notifyResult);
            return objectResultEx.success();
        } catch (Exception e) {
            log.error("微信退款通知解析异常：{}", e);
            return objectResultEx.failed("微信退款通知解析异常");
        }
    }

    @Override
    public ObjectResultDto<WeChatPayDownloadBillResult> downloadBill(WeChatDownloadBillParams downloadBillParams) {
        ObjectResultDto<WeChatPayDownloadBillResult> objectResultEx = new ObjectResultDto<>();
        // 构建对账参数
        WeChatDownloadBillBean downloadBillBean = new WeChatDownloadBillBean();
        BeanUtils.copyProperties(downloadBillParams, downloadBillBean);
        // 构建签名map
        Map<String, String> signMap = new HashMap<>();
        signMap.put("appid", downloadBillBean.getAppid());
        signMap.put("mch_id", downloadBillBean.getMchId());
        signMap.put("nonce_str", downloadBillBean.getNonceStr());
        signMap.put("bill_date", downloadBillBean.getBillDate());
        signMap.put("bill_type", downloadBillBean.getBillType());
        // 对参数进行签名
        String sign = SignUtils.createSign(signMap, downloadBillParams.getSignKey());
        downloadBillBean.setSign(sign);

        log.info("下载对账单转换为xml：{}", downloadBillBean.toXML());
        ObjectResultDto<String> resultEx = this.postWeChatCommon(wechatProperty.getPrefix() + WechatConst.WECHAT_PAY_URL_DOWNLOADBILL, downloadBillBean.toXML());
        if (resultEx.isFailed()) {
            return objectResultEx.makeResult(resultEx);
        }
        if (XmlJudgeUtil.isXML(resultEx.getData())) {
            return objectResultEx.failed("下载微信对账信息失败");
        }
        // 解析微信对账单记录
        WeChatPayDownloadBillResult billResult = this.analysisBill(resultEx.getData());
        if (null == billResult) {
            return objectResultEx.failed("微信对账信息记录不存在");
        }
        objectResultEx.setData(billResult);
        return objectResultEx.success();
    }

    /**
     * 转换短链接
     *
     * @param shortenUrlParams
     * @return
     */
    @Override
    public ObjectResultDto<WeChatShortenUrlResult> shortenUrl(WeChatShortenUrlParams shortenUrlParams) {
        ObjectResultDto<WeChatShortenUrlResult> objectResultEx = new ObjectResultDto<>();
        // 转换短链接数
        WeChatShortenUrlBean weChatShortenUrlBean = new WeChatShortenUrlBean();
        BeanUtils.copyProperties(shortenUrlParams, weChatShortenUrlBean);
        // 构建签名map
        Map<String, String> signMap = new HashMap<>();
        signMap.put("appid", weChatShortenUrlBean.getAppid());
        signMap.put("mch_id", weChatShortenUrlBean.getMchId());
        signMap.put("nonce_str", weChatShortenUrlBean.getNonceStr());
        signMap.put("long_url", weChatShortenUrlBean.getLongUrl());
        // 对参数进行签名
        String sign = SignUtils.createSign(signMap, shortenUrlParams.getSignKey());
        weChatShortenUrlBean.setSign(sign);

        // 调用微信请求接口
        ObjectResultDto<String> resultEx = this.postWeChatCommon(wechatProperty.getPrefix() + WechatConst.WECHAT_PAY_URL_SHORT_URL,
                weChatShortenUrlBean.toXML());
        if (resultEx.isFailed()) {
            return objectResultEx.makeResult(resultEx);
        }
        WeChatShortenUrlResult shortenUrlResult = WeChatPayBaseResult.fromXML(resultEx.getData(), WeChatShortenUrlResult.class);
        if (WechatConst.WECHAT_FAIL.equals(shortenUrlResult.getResultCode()) ||
                WechatConst.WECHAT_FAIL.equals(shortenUrlResult.getReturnCode())) {
            String errorMsg = shortenUrlResult.getErrCodeDes() == null ? shortenUrlResult.getReturnMsg() : shortenUrlResult.getErrCodeDes();
            return objectResultEx.failed(errorMsg);
        }
        objectResultEx.setData(shortenUrlResult);
        return objectResultEx.success();
    }

    /**
     * 解析微信对账单
     *
     * @param textBill 微信的对账单记录
     * @return WeChatPayDownloadBillResult
     */
    private WeChatPayDownloadBillResult analysisBill(String textBill) {
        if (StringUtils.isEmpty(textBill)) {
            return null;
        }
        WeChatPayDownloadBillResult downloadBillResult = new WeChatPayDownloadBillResult();
        textBill = textBill.replace("`", "");
        List<String> lists = Splitter.on("\r\n").omitEmptyStrings().trimResults().splitToList(textBill);
        List<WeChatPayBaseBillResult> baseBillResults = Lists.newArrayList();
        // 去除第一行和最后两行
        for (int i = 1; i < lists.size() - 2; i++) {
            String[] params = lists.get(i).split(",");
            WeChatPayBaseBillResult billResult = this.buildBill(params);
            baseBillResults.add(billResult);
        }
        downloadBillResult.setWeChatPayBaseBillResults(baseBillResults);
        // 获取微信对账记录最后一行
        String[] grossParams = lists.get(lists.size() - 1).split(",");
        System.out.println(grossParams.length);
        downloadBillResult.setTotalRecord(grossParams[0]);
        downloadBillResult.setTotalFee(grossParams[1]);
        downloadBillResult.setTotalRefundFee(grossParams[2]);
        downloadBillResult.setTotalCouponFee(grossParams[3]);
        downloadBillResult.setTotalPoundageFee(grossParams[4]);
        return downloadBillResult;
    }

    /**
     * 构建对账单对象
     *
     * @param params String[]
     * @return WeChatPayBaseBillResult
     */
    private WeChatPayBaseBillResult buildBill(String[] params) {
        WeChatPayBaseBillResult billResult = new WeChatPayBaseBillResult();
        billResult.setTradeTime(params[0]);
        billResult.setAppId(params[1]);
        billResult.setMchId(params[2]);
        billResult.setSubMchId(params[3]);
        billResult.setDeviceInfo(params[4]);
        billResult.setTransactionId(params[5]);
        billResult.setOutTradeNo(params[6]);
        billResult.setOpenId(params[7]);
        billResult.setTradeType(params[8]);
        billResult.setTradeState(params[9]);
        billResult.setBankType(params[10]);
        billResult.setFeeType(params[11]);
        billResult.setTotalFee(params[12]);
        billResult.setCouponFee(params[13]);
        billResult.setRefundId(params[14]);
        billResult.setOutRefundNo(params[15]);
        billResult.setSettlementRefundFee(params[16]);
        billResult.setCouponRefundFee(params[17]);
        billResult.setRefundChannel(params[18]);
        billResult.setRefundState(params[19]);
        billResult.setBody(params[20]);
        billResult.setAttach(params[21]);
        billResult.setPoundage(params[22]);
        billResult.setPoundageRate(params[23]);
        return billResult;
    }

    /**
     * https请求微信
     *
     * @param url       请求地址
     * @param xmlParams 请求的xml参数
     * @return ObjectResultDto<String>
     */
    private ObjectResultDto<String> postWeChatCommon(String url, String xmlParams) {
        ObjectResultDto<String> objectResultDto = new ObjectResultDto<>();
        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(xmlParams)) {
            log.error("请求地址或者请求参数为空：{}, {}", url, xmlParams);
            return objectResultDto.failed("请求地址或者请求参数为空");
        }
        log.info("微信请求的地址和参数：{}, {}", url, xmlParams);
        try {
            // 将请求的XML字符串的编码转换为ISO-8859-1
            String xmlISO = new String(xmlParams.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            // 发送请求微信接口
            String resultXml = Request.Post(url).bodyString(xmlISO, ContentType.APPLICATION_XML).execute().
                    returnContent().asString(StandardCharsets.UTF_8);
            log.info("微信接口请求响应结果：{}", resultXml);
            objectResultDto.setData(resultXml);
            return objectResultDto.success();
        } catch (Exception e) {
            log.error("请求微信接口异常", e);
            return objectResultDto.failed("请求微信接口异常");
        }
    }

    /**
     * 微信接口请求需要证书
     * (如：申请退款)
     *
     * @param url       地址
     * @param xmlParams 参数
     * @return 证书
     */
    private ObjectResultDto<String> postWithKey(String url, String xmlParams) {
        ObjectResultDto<String> objectResultDto = new ObjectResultDto<>();
        FileInputStream inputStream = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            // 读取本机存放的PKCS12证书文件
            File certFile = new File(wechatProperty.getCertificatePath());
            log.info("认证文件的路径：{}", certFile.getPath());
            inputStream = new FileInputStream(certFile);
            // 指定PKCS12的密码(商户ID)
            keyStore.load(inputStream, wechatProperty.getMchId().toCharArray());
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore,
                    wechatProperty.getMchId().toCharArray()).build();
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                    sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();

            // 发送请求微信接口
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(xmlParams, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String resultXML = EntityUtils.toString(response.getEntity(), "UTF-8");
            EntityUtils.consume(entity);
            log.info("申请退款微信返回的结果：{}", resultXML);
            objectResultDto.setData(resultXML);
            return objectResultDto.success();
        } catch (Exception e) {
            log.error("请求微信接口异常", e);
            return objectResultDto.failed("请求微信接口异常");
        } finally {
            IOUtils.close(inputStream);
        }
    }
}
