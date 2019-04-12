package com.zoeeasy.cloud.platform.controller.pay;

import com.alibaba.fastjson.JSON;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.web.utils.ActionUtils;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zoeeasy.cloud.integration.pay.PayNotifyService;
import com.zoeeasy.cloud.pay.trade.dto.request.alipay.AlipayAsyncNotifyResultRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.weixin.WeixinAsyncNotifyResultRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.result.alipay.AlipayAsyncNotifyResultDto;
import com.zoeeasy.cloud.pay.trade.dto.result.weixin.WeixinAsyncNotifyResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付通知回调API
 *
 * @author walkmans
 * @since 2018/1/13
 */
@Api(value = "支付通知回调API", tags = "支付通知回调API", produces = MediaType.APPLICATION_JSON_VALUE, hidden = true)
@RestController
@ApiVersion(1)
@RequestMapping("/cloud/pay/notify")
@Slf4j
public class PayNotifyController {

    @Autowired
    private PayNotifyService notifyService;

    /**
     * 支付宝回调
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "支付宝回调", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, hidden = true)
    @PostMapping(value = "/alipay")
    public void alipayNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("支付宝回调");
        // 获取支付宝POST过来反馈信息
        Map requestParams = request.getParameterMap();
        log.debug("支付宝回调结果：" + requestParams.toString());
        Map<String, String> params = new HashMap<>();
        for (Iterator iterator = requestParams.keySet().iterator(); iterator.hasNext(); ) {
            String name = (String) iterator.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        // 切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
//      boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        try {
            //验证签名
            //    ResultDto resultDto = payService.AlipaySignatureCheck(params);
            //   if (resultDto.isSuccess()) {
            // TODO 验签成功后
            //按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure

            AlipayAsyncNotifyResultRequestDto requestDto = new AlipayAsyncNotifyResultRequestDto();
            requestDto.setIp(ActionUtils.getIpAddress(request));
            requestDto.setUrl(request.getRequestURI());
            requestDto.setContent(JSON.toJSONString(params));
            if (params.get("notify_time") != null) {
                requestDto.setNotifyTime(DateUtils.parseDate(params.get("notify_time")));
            }
            if (params.get("notify_type") != null) {
                requestDto.setNotifyType(params.get("notify_type"));
            }
            if (params.get("notify_id") != null) {
                requestDto.setNotifyId(params.get("notify_id"));
            }
            if (params.get("app_id") != null) {
                requestDto.setAppId(params.get("app_id"));
            }
            if (params.get("charset") != null) {
                requestDto.setCharset(params.get("charset"));
            }
            if (params.get("version") != null) {
                requestDto.setVersion(params.get("version"));
            }
            if (params.get("sign_type") != null) {
                requestDto.setSignType(params.get("sign_type"));
            }
            if (params.get("sign") != null) {
                requestDto.setSign(params.get("sign"));
            }
            if (params.get("trade_no") != null) {
                requestDto.setTradeNo(params.get("trade_no"));
            }
            if (params.get("out_trade_no") != null) {
                requestDto.setOutTradeNo(params.get("out_trade_no"));
            }
            if (params.get("out_biz_no") != null) {
                requestDto.setOutBizNo(params.get("out_biz_no"));
            }
            if (params.get("buyer_id") != null) {
                requestDto.setBuyerId(params.get("buyer_id"));
            }
            if (params.get("buyer_logon_id") != null) {
                requestDto.setBuyerLogonId(params.get("buyer_logon_id"));
            }
            if (params.get("seller_id") != null) {
                requestDto.setSellerId(params.get("seller_id"));
            }
            if (params.get("seller_email") != null) {
                requestDto.setSellerEmail(params.get("seller_email"));
            }
            if (params.get("trade_status") != null) {
                requestDto.setTradeStatus(params.get("trade_status"));
            }
            if (params.get("total_amount") != null) {
                requestDto.setTotalAmount(new BigDecimal(params.get("total_amount")));
            }
            if (params.get("receipt_amount") != null) {
                requestDto.setReceiptAmount(new BigDecimal(params.get("receipt_amount")));
            }
            if (params.get("invoice_amount") != null) {
                requestDto.setInvoiceAmount(new BigDecimal(params.get("invoice_amount")));
            }
            if (params.get("buyer_pay_amount") != null) {
                requestDto.setBuyerPayamount(new BigDecimal(params.get("buyer_pay_amount")));
            }
            if (params.get("point_amount") != null) {
                requestDto.setPointAmount(new BigDecimal(params.get("point_amount")));
            }
            if (params.get("refund_fee") != null) {
                requestDto.setRefundFee(new BigDecimal(params.get("refund_fee")));
            }
            if (params.get("subject") != null) {
                requestDto.setSubject(params.get("subject"));
            }
            if (params.get("body") != null) {
                requestDto.setBody(params.get("body"));
            }
            if (params.get("gmt_create") != null) {
                requestDto.setGmtCreate(DateUtils.parseDate(params.get("gmt_create")));
            }
            if (params.get("gmt_payment") != null) {
                requestDto.setGmtPayment(DateUtils.parseDate(params.get("gmt_payment")));
            }
            if (params.get("gmt_refund") != null) {
                requestDto.setGmtRefund(DateUtils.parseDate(params.get("gmt_refund")));
            }
            if (params.get("gmt_close") != null) {
                requestDto.setGmtClose(DateUtils.parseDate(params.get("gmt_close")));
            }
            if (params.get("fund_bill_list") != null) {
                requestDto.setFundBillList(params.get("fund_bill_list"));
            }
            if (params.get("passback_params") != null) {
                requestDto.setPassbackParams(params.get("passback_params"));
            }
            if (params.get("voucher_detail_list") != null) {
                requestDto.setVoucherDetailList(params.get("voucher_detail_list"));
            }
            ObjectResultDto<AlipayAsyncNotifyResultDto> notifyResult = notifyService.processAliPayNotify(requestDto);
            if (notifyResult.isSuccess()) {
                response.getWriter().print("success");
            } else {
                response.getWriter().print("failure");
            }
            //  } else {
            // TODO 验签失败则记录异常日志，并在response中返回failure.
            // LOG.debug("签名验证失败！");
            // }
            //result.getWriter().append("success");
        } catch (Exception e) {
            log.error("支付宝支付回调处理失败！" + e.getMessage());
            response.getWriter().print("failure");
        }
    }

    @ApiOperation(value = "微信回调", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, hidden = true)
    @PostMapping(value = "/weixin")
    public void weixinNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("微信回调");
        String weixinReturnXml = ActionUtils.getContentFromRequest(request);
        WeixinAsyncNotifyResultRequestDto weixinAsyncNotifyResultRequestDto = new WeixinAsyncNotifyResultRequestDto();
        weixinAsyncNotifyResultRequestDto.setIp(ActionUtils.getIpAddress(request));
        weixinAsyncNotifyResultRequestDto.setUrl(request.getRequestURL().toString());
        weixinAsyncNotifyResultRequestDto.setWeixinReturnXml(weixinReturnXml.trim());
        log.debug("微信回调结果：" + weixinReturnXml);
        ObjectResultDto<WeixinAsyncNotifyResultDto> resultDto = notifyService.processWeixinNotify(weixinAsyncNotifyResultRequestDto);
        try {
            StringBuilder buffer = new StringBuilder();
            if (resultDto.isSuccess()) {
                buffer.append("<xml>")
                        .append("<return_code><![CDATA[" + resultDto.getData().getReturnCode() + "]]></return_code>")
                        .append("<return_msg><![CDATA[" + resultDto.getData().getReturnMsg() + "]]></return_msg>")
                        .append("</xml>");
            } else {
                buffer.append("<xml>")
                        .append("<return_code><![CDATA[" + "FAIL" + "]]></return_code>")
                        .append("<return_msg><![CDATA[" + "FAIL" + "]]></return_msg>")
                        .append("</xml>");
            }
            response.getWriter().print(buffer.toString());
        } catch (IOException e) {
            log.error("Error occurred." + e.getMessage(),e);
        }
    }
}
