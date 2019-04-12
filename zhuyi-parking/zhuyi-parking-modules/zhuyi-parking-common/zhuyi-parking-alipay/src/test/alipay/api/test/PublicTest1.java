/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.zhuyitech.parking.alipay.api.test;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.scapegoat.infrastructure.common.utils.Base64;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

import static com.alipay.api.internal.util.AlipaySignature.getPrivateKeyFromPKCS8;
import static com.alipay.api.internal.util.AlipaySignature.rsaEncrypt;

/*import java.util.HashMap;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.json.JSONWriter;
import com.alipay.api.request.AlipayMobilePublicAccountAddRequest;
import com.alipay.api.request.AlipayMobilePublicMenuGetRequest;
import com.alipay.api.result.AlipayMobilePublicAccountAddResponse;
import com.alipay.api.result.AlipayMobilePublicMenuGetResponse;*/

/**
 * 
 * @author wb-tongxl
 * @version $Id: PublicTest.java, v 0.1 2013-10-10 04:53:30 wb-tongxl Exp $
 */
public class PublicTest1 {

    private static final String APP_ID = "2018010801698348";

    private static final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDDu97MbfMiEBTcmZ1Vs5aY2Er2ipayIja0aYne7pJznu2K5noFiH/7597MhX1F5qust7zOnGp+BqlTCPhw3riQo+CMnAlSZSrpANOrFi/hU4MGUuxYj9pxpfjrefvQih7SWZIHrimch9igoWWiGpKssPQ7d/XoHfhd5DDYTj1xuHhvoPxxsFIks5cMzLqXNCwKwOajKFdpnpVydgKaQMvBy6ptSBZUM2PNNTp9owDq9URrjWbIb67wLCqS0aA92LCwSBERyM/NsIAkFXWmQiPTAl1HwVgt1Mifgg66/oxYya0kvTR/8noSTXxu+DR8G4FPXlv8zYQ6uOcyNk9gG5CRAgMBAAECggEADRrzG/TwpoM1ImAWrLpNhwZUXpWA6YRo7UNzu711iNBHT1mHKf+tsgwgWslFTfzX2cSjm6HVb4K6q5iaKdR/vw955XA9H4suiFNJ2NavdgLFbxL2SCe5lIpsVDfiqF/XGHZu1JzQpG5kDr0/tM7aqz1d3O13RYdcLc0tiA4VBxwY2mDvLiVYMAW/5ExmOuNNQ6fU387LCbkMLY4GfuGPkAmYUHTBEfsCaYX8fkoBXUMsEyQme/fkEFPH8UMS+4/NcJF4WCXkckRWhEuOwhU6/dZFGrpkcNdv67znaParvwxYsDENo3Pm4eUqgizQvST4E4p7Mqb+qUaIthsOS89nUQKBgQDg7P8zt7xlDuVGG0qFYYdtu/7kKjxQbQLixw8pUPgdSZdOpxeB5kmNeDxPJmL5n4tkIhmlm3cIba1UWvVg6dWYEgO3CYfRaIa5gYfXboAIP8ylDkWomXrhXt3WW3GTDbR7GEVLEDZ+J6vcgmO2cGKdq4t5Fnfy/zdkEi/ALAYNkwKBgQDexm/A2H4strbAXywut5GqENo/onrpyUNgI+wYtVP5STsbA0TLHDtCHWiVNqqyKNgt3LEIz7h5OXYvxN6RFcZc+OAvdzRbf3zOxAlbJY54vdeAoDFKk+Q+WGV+vXmQLegr8ynckmpnZHHC95ZsMiCqM+SyD4L/IYhlrxdWj+4fywKBgQCT7mt+OUaX9K8IZ+SgSt/GZVGkAL9AaiHuwtsO4mj84iy4lx7X2XzgpYaMzANmh+8mgWL6z3//NBs+ksA16DUJ2jdFR+hi6utI0I9mOg/M3SEcIYHg/GicF0exttzAyc+88d6JGvWsDS10TvGDyPCjIHMPxZ5IXGCy2V88dffZLwKBgCMdMBw7g5fUrPWhirtKYAFoap5BJBKZkK8kumAipLvUWSam2z8Qkh6XuBZc6YbvfTOtZ5I1+6PP9NsOsu43em7JI70Xkv8hFzEYIUlmoBBUDBXQKjTRHtbc5BKYFwMs2T2kNGL6CR+G64+JqwIe3Js3VsLiQwmn5XHxqUIRqb0FAoGARZGOIPZkZziAy5w/8l5sq0olIDHIW+mn2xL9Bz40QSOTnjWhCgNbx+oizfGq1UEOiIdzLPGVHAwB215FXdb8i4jtbL95Ob3qLGLZsP6f9OCQdYHlXvCLdo8KufpIPRiA/DJMuopJI7YOnKFVqTYsF0dXexmkIhlOOrMbBF6ghGo=";

    private static final String CHARSET = "UTF-8";

    private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAma31S+2cqIDZ9jb3kasYR3kVkzCs0r2hw53WX+AqNOWdIRyT762diusA6Xn/EExJ1ph1C9rWBUQy+GaM5blqz1QZbsXfVN3ITJFtWxhPN+jkKqZjdQ1mrGRxwTlcCGFbRy2FhdxsL4x2tsZDVh4P05/+3ZYuVQv2ep8O69DfEVMFbSmXUif73c2eSOthXV8OLN+gar2yhuIAkZoui35cdklfmtV3gNzNO+ykZrIVTzJr4/kIQVlxBRexdFZWgXrE35Kji8ql9p/lF/1v0tXsH0KHqg9L4HIGZ69yEXojdnplZ0h9Is4TRpFlBCDUZFS4AXJqLKQKd+lg1DkEuh6gowIDAQAB";

    /**
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        // 公众号菜单查询
        //menuGet();

        // 公众号通知消息签名验证
        //checkSign();

        // 公众号账号绑定
        //accountAdd();

        // 公众号验签&解密
        //checkSignAndDecrypt();

        // 公众号加密&加签
        //encryptAndSign();

        //wyliwuli();
        //getOrderInfoByAliPay();

        signAllString();

        //signAllString2();

        //boolean flag = AlipaySignature.rsaCheckV1("UvF5yA7+02q3jBFZi5Nfj+6Fz4K0oJ1HhglJoozGOxd8lHQVTMXAVy/zClF6R0L9mQGVcGGj2foSi6naQyC6gbAccYKUMMvpTPURehu2PimTg6hFaGCIXsD2dDVxkqWHgrHpy+5N+ubcN5k/tzauwvOHFNfvsyB8K1yRRGCBDpo8PQddrNcMuebkwYB2C9Ex7riLvjNNyGZDX3VR3GnDF7N5JosZtoO+Szs0vUDd0RaKdWrhuNEWFsU4JKjQdRA/Fg9atVF7aAovzMJ7ItIlBJczRDXpY82ExgEvAMsFJ1xQ2AkIS/ID+Bx9RAQKU/2Iy086rTgaJnKaZ/xKPHB2fg==", ALIPAY_PUBLIC_KEY, "UTF-8", "RSA2");
    }

    private static void aaa() {
        Date date = new Date();
        Long l = date.getTime();
        System.out.println(l);
    }

    /* public static String getSignContent(Map<String, String> sortedParams) {
         StringBuffer content = new StringBuffer();
         List<String> keys = new ArrayList<String>(sortedParams.keySet());
         Collections.sort(keys);
         int index = 0;
         for (int i = 0; i < keys.size(); i++) {
             String key = keys.get(i);
             String value = sortedParams.get(key);
             if (StringUtils.areNotEmpty(key, value)) {
                 content.append((index == 0 ? "" : "&") + key + "=" + value);
                 index++;
             }
         }
         return content.toString();
     }

     public static String rsaSign(String content, String privateKey,
                                  String charset) throws AlipayApiException {
         try {
             PrivateKey priKey = getPrivateKeyFromPKCS8(AlipayConstants.SIGN_TYPE_RSA,
                     new ByteArrayInputStream(privateKey.getBytes()));

             java.security.Signature signature = java.security.Signature
                     .getInstance("SHA1WithRSA");

             signature.initSign(priKey);

             if (StringUtils.isEmpty(charset)) {
                 signature.update(content.getBytes());
             } else {
                 signature.update(content.getBytes(charset));
             }

             byte[] signed = signature.sign();

             return new String(Base64.encode(signed));
         } catch (InvalidKeySpecException ie) {
             throw new AlipayApiException("RSA私钥格式不正确，请检查是否正确配置了PKCS8格式的私钥", ie);
         } catch (Exception e) {
             throw new AlipayApiException("RSAcontent = " + content + "; charset = " + charset, e);
         }
     }

     public static String createLinkString(Map<String, String> request) throws UnsupportedEncodingException {

         List<String> keys = new ArrayList<String>(request.keySet());
         Collections.sort(keys);

         String prestr = "";

         for (int i = 0; i < keys.size(); i++) {
             String key = keys.get(i);
             String value = request.get(key);

             if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                 prestr = prestr + key + "=" + URLEncoder.encode(value,"UTF-8") + "";
             } else {
                 prestr = prestr + key + "=" + URLEncoder.encode(value,"UTF-8") + "&";
             }
         }
         return prestr;
     }*/
    private static void signAllString() {
        String orderStr = "";
        try {
            Map<String, String> orderMap = new LinkedHashMap<String, String>(); // 订单实体
            Map<String, String> bizModel = new LinkedHashMap<String, String>(); // 公共实体
            /****** 2.商品参数封装开始 *****/ // 手机端用
            // 商户订单号，商户网站订单系统中唯一订单号，必填
            orderMap.put("out_trade_no", "20180113887799ABC");
            // 订单名称，必填
            orderMap.put("subject", "zfb支付111");
            // 付款金额，必填
            orderMap.put("total_amount", "0.01");
            // 销售产品码 必填
            orderMap.put("product_code", "QUICK_WAP_PAY");
            /****** --------------- 3.公共参数封装 开始 ------------------------ *****/ // 支付宝用
            /*// 1.商户appid
            bizModel.put("app_id", AlipayConfig.APPID);
            // 2.请求网关地址
            bizModel.put("method", AlipayConfig.URL);
            // 3.请求格式
            bizModel.put("format", AlipayConfig.FORMAT);
            // 4.回调地址
            bizModel.put("return_url", AlipayConfig.notify_url);
            // 5.私钥
            bizModel.put("private_key", AlipayConfig.private_key);
            // 6.商家id
            bizModel.put("seller_id", AlipayConfig.partner);
            // 7.加密格式
            bizModel.put("sign_type", AlipayConfig.sign_type + "");*/
            /****** --------------- 3.公共参数封装 结束 ------------------------ *****/
            // 实例化客户端
            AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
            // 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            // SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            // model.setPassbackParams(URLEncoder.encode((String)orderMap.get("body").toString()));;
            // //描述信息 添加附加数据
            // model.setBody(orderMap.get("body")); //商品信息
            model.setSubject(orderMap.get("subject")); // 商品名称
            model.setOutTradeNo(orderMap.get("out_trade_no")); // 商户订单号(自动生成)
            model.setTotalAmount(orderMap.get("total_amount")); // 支付金额
            model.setProductCode(orderMap.get("product_code")); // 销售产品码
            model.setSellerId("2088821924063102"); // 商家id
            request.setBizModel(model);
            request.setNotifyUrl("https://www.baidu.com/"); // 回调地址
            AlipayTradeAppPayResponse responses = alipayClient.sdkExecute(request);
            orderStr = responses.getBody();
            System.out.println(orderStr); // 就是orderString 可以直接给客户端请求，无需再做处理
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static void signAllString1() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", "UTF-8", ALIPAY_PUBLIC_KEY, "RSA2");
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        /*request.setBizContent("{" +
                "\"out_trade_no\":\"20180113887799\"," +
                //"\"trade_no\":\"2014112611001004680073956707\"" +
                "}");*/

        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo("20180113887799");
        request.setBizModel(model);
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        //Map<String,String> map = new HashMap<String, String>();
        //map = result.getgetBody();
        //String signContent = AlipaySignature.getSignContent(result.get());
        System.out.println(response.getBody());
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

    private static void signAllString2() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", "UTF-8", ALIPAY_PUBLIC_KEY, "RSA2");
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo("20180113887799");
        request.setBizModel(model);
        AlipayTradeCloseResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }
}
