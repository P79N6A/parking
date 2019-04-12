package com.zoeeasy.cloud.platform.controller.pay;

import com.zoeeasy.cloud.pay.alipay.params.AlipayPrepareOrderParams;
import com.zoeeasy.cloud.pay.alipay.service.AliPayService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cloud/pay")
public class QuickPayController {

    @Autowired
    private AliPayService aliPayService;


    @RequestMapping("/prepareAliPay")
    @ApiOperation(value = "支付宝支付", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, hidden = true)
    public void prepareAliPay(AlipayPrepareOrderParams params, String payOrderNo, String productName) {
        String   processWeixinNotifyResult = String.format("订单%s状态为非等待支付状态,````", payOrderNo);

        aliPayService.prepareAliPay(params, payOrderNo, productName);
    }

}
