package com.zhuyitech.parking.pay.wechat.service.impl;


import com.zhuyitech.parking.pay.wechat.params.WeChatOrderQueryParams;
import com.zhuyitech.parking.pay.wechat.service.WeChatPayService;

/**
 * ${DESCRIPTION}
 *
 * @author walkman
 * @date 2017-07-12-13:29
 */
public class WeChatPayServiceTest {

    /**
     * 统一下单
     * @param args
     */
//    public static void main(String[] args) {
//
//        WeChatUnifiedOrderParams unifiedOrderParams = new WeChatUnifiedOrderParams();
//        unifiedOrderParams.setAppid("wxadfda38db921c57b");
//        unifiedOrderParams.setMchId("1471275602");
//        unifiedOrderParams.setBody("电影表");
//        unifiedOrderParams.setNotifyURL("http://h5.hdyyyc.cn/wxpaytest/");
//        unifiedOrderParams.setOpenId("oB-qNv43OUDoMGI5tglotoQLQpQM");
//        unifiedOrderParams.setOutTradeNo("201613091059590000003433-asd009");
//        unifiedOrderParams.setSpbillCreateIp("115.236.9.250");
//        unifiedOrderParams.setTotalFee(1);
//
//        WeChatPayService weChatPayService = new WeChatPayServiceImpl();
//        weChatPayService.unifiedOrder(unifiedOrderParams);
//    }

    /**
     * 查询订单
     * @param args
     */
    public static void main(String[] args) {
        WeChatOrderQueryParams params = new WeChatOrderQueryParams();
        params.setAppid("wxadfda38db921c57b");
        params.setMchId("1471275602");
        params.setOutTradeNo("a2c0e6d80549492f9da68e6ae5b79151");
        WeChatPayService weChatPayService = new WeChatPayServiceImpl();
        weChatPayService.orderQuery(params);
    }

    /**
     * 申请退款
     * @param args
     */
    /*public static void main(String[] args) {
        WeChatRefundParams request = new WeChatRefundParams();
        request.setAppid("wxadfda38db921c57b");
        request.setMchId("1471275602");
        request.setOutTradeNo("2c264ed4cf6647c2b0273d2de3a4b97c");
        request.setOutRefundNo("589d6d292202466fa12979e1d6f1a1cc");
        request.setTotalFee(ConverterUtils.yuanToFee("0.01"));
        request.setRefundFee(ConverterUtils.yuanToFee("0.01"));
        WeChatPayService weChatPayService = new WeChatPayServiceImpl();
        weChatPayService.refund(request);
    }*/

    /**
     * 对账单
     * @param args
     */
/*    public static void main(String[] args) {
        WeChatDownloadBillParams request = new WeChatDownloadBillParams();
        request.setAppid("wxadfda38db921c57b");
        request.setMchId("1471275602");
        request.setBillType("ALL");
        request.setBillDate("20170720");
        WeChatPayService weChatPayService = new WeChatPayServiceImpl();
        weChatPayService.downloadBill(request);
    }*/

}
