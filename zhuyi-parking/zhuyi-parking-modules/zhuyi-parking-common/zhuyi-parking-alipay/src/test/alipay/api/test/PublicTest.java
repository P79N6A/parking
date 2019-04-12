/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */

package com.zhuyitech.parking.alipay.api.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.scapegoat.infrastructure.common.utils.Base64;

import static com.alipay.api.AlipayConstants.APP_ID;
import static com.alipay.api.AlipayConstants.CHARSET;
import static com.alipay.api.internal.util.AlipaySignature.rsaEncrypt;


import java.util.HashMap;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.json.JSONWriter;
import com.alipay.api.request.AlipayMobilePublicAccountAddRequest;
import com.alipay.api.request.AlipayMobilePublicMenuGetRequest;
import com.alipay.api.response.AlipayMobilePublicAccountAddResponse;
import com.alipay.api.response.AlipayMobilePublicMenuGetResponse;



/**
 * 
 * @author wb-tongxl
 * @version $Id: PublicTest.java, v 0.1 2013-10-10 04:53:30 wb-tongxl Exp $
 */

public class PublicTest {

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

        wyliwuli();
        //getOrderInfoByAliPay();
    }


/*
    public static void accountAdd() throws AlipayApiException {
    AlipayMobilePublicAccountAddRequest request = new AlipayMobilePublicAccountAddRequest();
    // 组装请求对象
    BindAccountBean bean = new BindAccountBean();
    bean.setAgreementId("20130829000000338233");
    bean.setDisplayName("尾号3190");
    bean.setAppId("2013082200024933");
    bean.setRealName("乔蒂");
    bean.setBindAccountNo("18618343190");
    bean.setFromUserId("2088102124069339");

    JSONWriter json = new JSONWriter();
    String bingUserStr = json.write(bean);

    request.setBizContent(bingUserStr);

    // 执行调用,转换为返回对象类型
    AlipayClient client = getAlipayClient();
    AlipayMobilePublicAccountAddResponse rsp = client.execute(request);
    System.out.println(rsp.getBody());

    }

    *//*
     */
/**
 *
 * @throws AlipayApiException
 *//*

     */
/*
    private static void checkSign() throws AlipayApiException {
    Map<String, String> request = new HashMap<String, String>();

    request
        .put(
            "biz_context",
            "<XML><AppId><![CDATA[2013082200024893]]></AppId><FromUserId><![CDATA[2088102122485786]]></FromUserId><CreateTime>1377228401913</CreateTime><MsgType><![CDATA[click]]></MsgType><EventType><![CDATA[event]]></EventType><ActionParam><![CDATA[authentication]]></ActionParam><AgreementId><![CDATA[201308220000000994]]></AgreementId><AccountNo><![CDATA[null]]></AccountNo><UserInfo><![CDATA[{\"logon_id\":\"15858179811\",\"user_name\":\"许旦辉\"}]]></UserInfo></XML>");
    request.put("charset", "GBK");
    request.put("service", "alipay.mobile.public.message.notify");
    request.put("sign_type", "RSA");
    request
        .put(
            "sign",
            "rlqgA8O+RzHBVYLyHmrbODVSANWPXf3pSrr82OCO/bm3upZiXSYrX5fZr6UBmG6BZRAydEyTIguEW6VRuAKjnaO/sOiR9BsSrOdXbD5Rhos/Xt7/mGUWbTOt/F+3W0/XLuDNmuYg1yIC/6hzkg44kgtdSTsQbOC9gWM7ayB4J4c=");

    boolean checkSign = AlipaySignature
        .rsaCheckV2(
            request,
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB",
            AlipayConstants.CHARSET_GBK);

    System.out.println(checkSign);
    }

    *//*
     */
/**
 *
 * @throws AlipayApiException
 *//*

     */
/*
    public static void menuGet() throws AlipayApiException {
    AlipayClient client = getAlipayClient();

    AlipayMobilePublicMenuGetRequest request = new AlipayMobilePublicMenuGetRequest();

    AlipayMobilePublicMenuGetResponse rsp = client.execute(request);

    System.out.println(rsp.getBody());
    }

    *//*
     */

    /**
     * @return
     * @throws AlipayApiException
     *//*

     */
/*
    private static AlipayClient getAlipayClient() {
    AlipayClient client = new DefaultAlipayClient(
    // 线上地址 
        //  "https://openapi.alipay.com/gateway.do",
        // 沙箱地址
        "http://openapi.alipaydev.com/gateway.do",
        // 公众号ID
        "2013082200024933",
        // 公众号RSA私钥
        "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMiAec6fsssguUoRN3oEVEnQaqBLZjeafXAxCbKH3MTJaXPmnXOtqFFqFtcB8J9KqyFI1+o6YBDNIdFWMKqOwDDWPKqtdo90oGav3QMikjGYjIpe/gYYCQ/In/oVMVj326GmKrSpp0P+5LNCx59ajRpO8//rnOLd6h/tNxnfahanAgMBAAECgYEAusouMFfJGsIWvLEDbPIhkE7RNxpnVP/hQqb8sM0v2EkHrAk5wG4VNBvQwWe2QsAuY6jYNgdCPgTNL5fLaOnqkyy8IobrddtT/t3vDX96NNjHP4xfhnMbpGjkKZuljWKduK2FAh83eegrSH48TuWS87LjeZNHhr5x4C0KHeBTYekCQQD5cyrFuKua6GNG0dTj5gA67R9jcmtcDWgSsuIXS0lzUeGxZC4y/y/76l6S7jBYuGkz/x2mJaZ/b3MxxcGQ01YNAkEAzcRGLTXgTMg33UOR13oqXiV9cQbraHR/aPmS8kZxkJNYows3K3umNVjLhFGusstmLIY2pIpPNUOho1YYatPGgwJBANq8vnj64p/Hv6ZOQZxGB1WksK2Hm9TwfJ5I9jDu982Ds6DV9B0L4IvKjHvTGdnye234+4rB4SpGFIFEo+PXLdECQBiOPMW2cT8YgboxDx2E4bt8g9zSM5Oym2Xeqs+o4nKbcu96LipNRkeFgjwXN1708QuNNMYsD0nO+WIxqxZMkZsCQHtS+Jj/LCnQZgLKxXZAllxqSTlBln2YnBgk6HqHLp8Eknx2rUXhoxE1vD9tNmom6PiaZlQyukrQkp5GOMWDMkU=");
    return client;
    }*//*


    private static void checkSignAndDecrypt() throws AlipayApiException {
        // 参数构建
        String biz_content = "<XML><AppId><![CDATA[2013082200024893]]></AppId><FromUserId><![CDATA[2088102122485786]]></FromUserId><CreateTime>1377228401913</CreateTime><MsgType><![CDATA[click]]></MsgType><EventType><![CDATA[event]]></EventType><ActionParam><![CDATA[authentication]]></ActionParam><AgreementId><![CDATA[201308220000000994]]></AgreementId><AccountNo><![CDATA[null]]></AccountNo><UserInfo><![CDATA[{\"logon_id\":\"15858179811\",\"user_name\":\"许旦辉\"}]]></UserInfo></XML>";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMiAec6fsssguUoRN3oEVEnQaqBLZjeafXAxCbKH3MTJaXPmnXOtqFFqFtcB8J9KqyFI1+o6YBDNIdFWMKqOwDDWPKqtdo90oGav3QMikjGYjIpe/gYYCQ/In/oVMVj326GmKrSpp0P+5LNCx59ajRpO8//rnOLd6h/tNxnfahanAgMBAAECgYEAusouMFfJGsIWvLEDbPIhkE7RNxpnVP/hQqb8sM0v2EkHrAk5wG4VNBvQwWe2QsAuY6jYNgdCPgTNL5fLaOnqkyy8IobrddtT/t3vDX96NNjHP4xfhnMbpGjkKZuljWKduK2FAh83eegrSH48TuWS87LjeZNHhr5x4C0KHeBTYekCQQD5cyrFuKua6GNG0dTj5gA67R9jcmtcDWgSsuIXS0lzUeGxZC4y/y/76l6S7jBYuGkz/x2mJaZ/b3MxxcGQ01YNAkEAzcRGLTXgTMg33UOR13oqXiV9cQbraHR/aPmS8kZxkJNYows3K3umNVjLhFGusstmLIY2pIpPNUOho1YYatPGgwJBANq8vnj64p/Hv6ZOQZxGB1WksK2Hm9TwfJ5I9jDu982Ds6DV9B0L4IvKjHvTGdnye234+4rB4SpGFIFEo+PXLdECQBiOPMW2cT8YgboxDx2E4bt8g9zSM5Oym2Xeqs+o4nKbcu96LipNRkeFgjwXN1708QuNNMYsD0nO+WIxqxZMkZsCQHtS+Jj/LCnQZgLKxXZAllxqSTlBln2YnBgk6HqHLp8Eknx2rUXhoxE1vD9tNmom6PiaZlQyukrQkp5GOMWDMkU=";
        Map<String, String> request = new HashMap<String, String>();
        request.put("biz_content", rsaEncrypt(biz_content, publicKey, "UTF-8"));
        request.put("charset", "UTF-8");
        request.put("service", "alipay.mobile.public.message.notify");
        request.put("sign_type", "RSA");
        request.put("sign", AlipaySignature.rsaSign(request, privateKey, "UTF-8"));

        // 验签&解密
        String resultContent = AlipaySignature.checkSignAndDecrypt(request, publicKey, privateKey,
            true, true);

        System.out.println(resultContent);
    }

    private static void encryptAndSign() throws AlipayApiException {
        // 参数构建
        */
/*String bizContent = "<XML><ToUserId><![CDATA[2088821924063102]]></ToUserId><AppId><![CDATA[2018010801698348]]></AppId><AgreementId><![CDATA[20131111000001895078]]></AgreementId>"
                            + "<CreateTime>12334349884</CreateTime>"
                            + "<MsgType><![CDATA[image-text]]></MsgType>"
                            + "<ArticleCount>1</ArticleCount>"
                            + "<Articles>"
                            + "<Item>"
                            + "<Title><![CDATA[[回复测试加密解密]]></Title>"
                            + "<Desc><![CDATA[测试加密解密]]></Desc>"
                            + "<Url><![CDATA[http://m.taobao.com]]></Url>"
                            + "<ActionName><![CDATA[立即前往]]></ActionName>"
                            + "</Item>"
                            + "</Articles>" + "<Push><![CDATA[false]]></Push>" + "</XML>";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDDu97MbfMiEBTcmZ1Vs5aY2Er2ipayIja0aYne7pJznu2K5noFiH/7597MhX1F5qust7zOnGp+BqlTCPhw3riQo+CMnAlSZSrpANOrFi/hU4MGUuxYj9pxpfjrefvQih7SWZIHrimch9igoWWiGpKssPQ7d/XoHfhd5DDYTj1xuHhvoPxxsFIks5cMzLqXNCwKwOajKFdpnpVydgKaQMvBy6ptSBZUM2PNNTp9owDq9URrjWbIb67wLCqS0aA92LCwSBERyM/NsIAkFXWmQiPTAl1HwVgt1Mifgg66/oxYya0kvTR/8noSTXxu+DR8G4FPXlv8zYQ6uOcyNk9gG5CRAgMBAAECggEADRrzG/TwpoM1ImAWrLpNhwZUXpWA6YRo7UNzu711iNBHT1mHKf+tsgwgWslFTfzX2cSjm6HVb4K6q5iaKdR/vw955XA9H4suiFNJ2NavdgLFbxL2SCe5lIpsVDfiqF/XGHZu1JzQpG5kDr0/tM7aqz1d3O13RYdcLc0tiA4VBxwY2mDvLiVYMAW/5ExmOuNNQ6fU387LCbkMLY4GfuGPkAmYUHTBEfsCaYX8fkoBXUMsEyQme/fkEFPH8UMS+4/NcJF4WCXkckRWhEuOwhU6/dZFGrpkcNdv67znaParvwxYsDENo3Pm4eUqgizQvST4E4p7Mqb+qUaIthsOS89nUQKBgQDg7P8zt7xlDuVGG0qFYYdtu/7kKjxQbQLixw8pUPgdSZdOpxeB5kmNeDxPJmL5n4tkIhmlm3cIba1UWvVg6dWYEgO3CYfRaIa5gYfXboAIP8ylDkWomXrhXt3WW3GTDbR7GEVLEDZ+J6vcgmO2cGKdq4t5Fnfy/zdkEi/ALAYNkwKBgQDexm/A2H4strbAXywut5GqENo/onrpyUNgI+wYtVP5STsbA0TLHDtCHWiVNqqyKNgt3LEIz7h5OXYvxN6RFcZc+OAvdzRbf3zOxAlbJY54vdeAoDFKk+Q+WGV+vXmQLegr8ynckmpnZHHC95ZsMiCqM+SyD4L/IYhlrxdWj+4fywKBgQCT7mt+OUaX9K8IZ+SgSt/GZVGkAL9AaiHuwtsO4mj84iy4lx7X2XzgpYaMzANmh+8mgWL6z3//NBs+ksA16DUJ2jdFR+hi6utI0I9mOg/M3SEcIYHg/GicF0exttzAyc+88d6JGvWsDS10TvGDyPCjIHMPxZ5IXGCy2V88dffZLwKBgCMdMBw7g5fUrPWhirtKYAFoap5BJBKZkK8kumAipLvUWSam2z8Qkh6XuBZc6YbvfTOtZ5I1+6PP9NsOsu43em7JI70Xkv8hFzEYIUlmoBBUDBXQKjTRHtbc5BKYFwMs2T2kNGL6CR+G64+JqwIe3Js3VsLiQwmn5XHxqUIRqb0FAoGARZGOIPZkZziAy5w/8l5sq0olIDHIW+mn2xL9Bz40QSOTnjWhCgNbx+oizfGq1UEOiIdzLPGVHAwB215FXdb8i4jtbL95Ob3qLGLZsP6f9OCQdYHlXvCLdo8KufpIPRiA/DJMuopJI7YOnKFVqTYsF0dXexmkIhlOOrMbBF6ghGo=";
        *//*

        String bizContent = "<XML><ToUserId><![CDATA[2088821924063102]]></ToUserId><AppId><![CDATA[2018010801698348]]></AppId>"
                + "<Method><![CDATA[alipay.trade.app.pay]]></Method>"
                + "<Charset><![CDATA[UTF-8]]></MsgType>"
                + "<SignType><![CDATA[RSA2]]></SignType>"
                + "<Subject>南非真钻</Subject>"
                + "<OutTradeNo>20180113887799</OutTradeNo>"
                + "<TotalAmount>0.01</TotalAmount>"
                + "<TimeoutExpress>30m</TimeoutExpress>"
                + "<ProductCode><![CDATA[QUICK_MSECURITY_PAY]]></ProductCode>"
                */
/*+ "<ActionName><![CDATA[立即前往]]></ActionName>"
                + "</Item>"
                + "</Articles>" + "<Push><![CDATA[false]]></Push>" *//*
 + "</XML>";
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAma31S+2cqIDZ9jb3kasYR3kVkzCs0r2hw53WX+AqNOWdIRyT762diusA6Xn/EExJ1ph1C9rWBUQy+GaM5blqz1QZbsXfVN3ITJFtWxhPN+jkKqZjdQ1mrGRxwTlcCGFbRy2FhdxsL4x2tsZDVh4P05/+3ZYuVQv2ep8O69DfEVMFbSmXUif73c2eSOthXV8OLN+gar2yhuIAkZoui35cdklfmtV3gNzNO+ykZrIVTzJr4/kIQVlxBRexdFZWgXrE35Kji8ql9p/lF/1v0tXsH0KHqg9L4HIGZ69yEXojdnplZ0h9Is4TRpFlBCDUZFS4AXJqLKQKd+lg1DkEuh6gowIDAQAB";
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDDu97MbfMiEBTcmZ1Vs5aY2Er2ipayIja0aYne7pJznu2K5noFiH/7597MhX1F5qust7zOnGp+BqlTCPhw3riQo+CMnAlSZSrpANOrFi/hU4MGUuxYj9pxpfjrefvQih7SWZIHrimch9igoWWiGpKssPQ7d/XoHfhd5DDYTj1xuHhvoPxxsFIks5cMzLqXNCwKwOajKFdpnpVydgKaQMvBy6ptSBZUM2PNNTp9owDq9URrjWbIb67wLCqS0aA92LCwSBERyM/NsIAkFXWmQiPTAl1HwVgt1Mifgg66/oxYya0kvTR/8noSTXxu+DR8G4FPXlv8zYQ6uOcyNk9gG5CRAgMBAAECggEADRrzG/TwpoM1ImAWrLpNhwZUXpWA6YRo7UNzu711iNBHT1mHKf+tsgwgWslFTfzX2cSjm6HVb4K6q5iaKdR/vw955XA9H4suiFNJ2NavdgLFbxL2SCe5lIpsVDfiqF/XGHZu1JzQpG5kDr0/tM7aqz1d3O13RYdcLc0tiA4VBxwY2mDvLiVYMAW/5ExmOuNNQ6fU387LCbkMLY4GfuGPkAmYUHTBEfsCaYX8fkoBXUMsEyQme/fkEFPH8UMS+4/NcJF4WCXkckRWhEuOwhU6/dZFGrpkcNdv67znaParvwxYsDENo3Pm4eUqgizQvST4E4p7Mqb+qUaIthsOS89nUQKBgQDg7P8zt7xlDuVGG0qFYYdtu/7kKjxQbQLixw8pUPgdSZdOpxeB5kmNeDxPJmL5n4tkIhmlm3cIba1UWvVg6dWYEgO3CYfRaIa5gYfXboAIP8ylDkWomXrhXt3WW3GTDbR7GEVLEDZ+J6vcgmO2cGKdq4t5Fnfy/zdkEi/ALAYNkwKBgQDexm/A2H4strbAXywut5GqENo/onrpyUNgI+wYtVP5STsbA0TLHDtCHWiVNqqyKNgt3LEIz7h5OXYvxN6RFcZc+OAvdzRbf3zOxAlbJY54vdeAoDFKk+Q+WGV+vXmQLegr8ynckmpnZHHC95ZsMiCqM+SyD4L/IYhlrxdWj+4fywKBgQCT7mt+OUaX9K8IZ+SgSt/GZVGkAL9AaiHuwtsO4mj84iy4lx7X2XzgpYaMzANmh+8mgWL6z3//NBs+ksA16DUJ2jdFR+hi6utI0I9mOg/M3SEcIYHg/GicF0exttzAyc+88d6JGvWsDS10TvGDyPCjIHMPxZ5IXGCy2V88dffZLwKBgCMdMBw7g5fUrPWhirtKYAFoap5BJBKZkK8kumAipLvUWSam2z8Qkh6XuBZc6YbvfTOtZ5I1+6PP9NsOsu43em7JI70Xkv8hFzEYIUlmoBBUDBXQKjTRHtbc5BKYFwMs2T2kNGL6CR+G64+JqwIe3Js3VsLiQwmn5XHxqUIRqb0FAoGARZGOIPZkZziAy5w/8l5sq0olIDHIW+mn2xL9Bz40QSOTnjWhCgNbx+oizfGq1UEOiIdzLPGVHAwB215FXdb8i4jtbL95Ob3qLGLZsP6f9OCQdYHlXvCLdo8KufpIPRiA/DJMuopJI7YOnKFVqTYsF0dXexmkIhlOOrMbBF6ghGo=";

        String aaa = rsaEncrypt(bizContent, publicKey, "UTF-8");
        System.out.println(aaa);
        String responseContent = AlipaySignature.encryptAndSign(bizContent, publicKey, privateKey,
            "UTF-8", true, true);
        System.out.println(responseContent);

    }*/
    private static void wyliwuli() throws AlipayApiException {
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数
        //此次只是参数展示，未进行字符串转义，实际情况下请转义
        //String str = "outTradeNo=20180113887799&product_code=QUICK_MSECURITY_PAY&total_amount=0.01&subject=南非真钻";
        //request.setBizContent(str);
        /*String bizContent = "<XML>"
                + "<Subject>南非真钻</Subject>"
                + "<OutTradeNo>20180113887799</OutTradeNo>"
                + "<TotalAmount>0.01</TotalAmount>"
                + "<TimeoutExpress>30m</TimeoutExpress>"
                + "<ProductCode><![CDATA[QUICK_MSECURITY_PAY]]></ProductCode>" + "</XML>";*/

        /*+ "<ActionName><![CDATA[立即前往]]></ActionName>"
                + "</Item>"
                + "</Articles>" + "<Push><![CDATA[false]]></Push>" */

       request.setBizContent("  {" +
                "    \"outTradeNo\":\"20180113887799\"," +
                "    \"product_code\":\"QUICK_MSECURITY_PAY\"," +
                "    \"total_amount\":\"0.01\"," +
                "    \"subject\":\"南非真钻\"" +
                " }");
        request.setNotifyUrl("www.taobao.com");
        AlipayTradeAppPayResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());

    }
}

    /*private static void wyliwuli2() throws AlipayApiException {
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify
        AlipayOpenPublicTemplateMessageIndustryModifyRequest request = new AlipayOpenPublicTemplateMessageIndustryModifyRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数
//此次只是参数展示，未进行字符串转义，实际情况下请转义
        request.setBizContent("  {" +
                "    \"primary_industry_name\":\"IT科技/IT软件与服务\"," +
                "    \"primary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_name\":\"IT科技/IT软件与服务\"" +
                " }");
        AlipayOpenPublicTemplateMessageIndustryModifyResponse result = alipayClient.execute(request);
    }

    */
/**
     *支付宝支付
     * @param
     * @param
     * @return
     *//*

    private static void getOrderInfoByAliPay() {
        //回调页面
        String ali_call_back_url = "http://192.168.0.242:8072/swagger-ui.html";
        String seller_id = "2088821924063102";//商户编号
        String[] parameters={
                "service=\"alipay.trade.app.pay\"",//固定值（手机快捷支付）
                "partner=\"2088821924063102\"",//合作身份者ID（16位）
                "_input_charset=\"utf-8\"",
                "notify_url=\""+ali_call_back_url+"\"",//通知地址
                "out_trade_no=\""+"20180113887799"+"\"",//商户内部订单号
                "subject=\"测试\"",//测试
                "payment_type=\"1\"",//固定值
                "seller_id=\""+seller_id+"\"",//账户邮箱
                "total_fee=\""+"0.01"+"\"",//支付金额（元）

                "body=\"订单说明\"",//订单说明
                "it_b_pay=\"30m\""
      };
        String signOrderUrl = signAllString(parameters);
        List<String> keys = new ArrayList<String>(request.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = request.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + URLEncoder.encode(value,StringHelp.UTF_8) + "";
            } else {
                prestr = prestr + key + "=" + URLEncoder.encode(value,StringHelp.UTF_8) + "&";
            }
        }
        return prestr;
        System.out.println(signOrderUrl);
    }

    */
/**
     * 支付宝签名
     * @param array
     * @return
     *//*

    private static String signAllString(String[] array){
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < array.length; i++) {
            if(i==(array.length-1)){
                sb.append(array[i]);
            }else{
                sb.append(array[i]+"&");
            }
        }
        System.out.println(sb.toString());
        String sign = "";
        try {
            sign = URLEncoder.encode(sign(sb.toString(), APP_PRIVATE_KEY, "utf-8"), "utf-8");//private_key私钥
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sb.append("&sign=\""+sign+"\"&");
        sb.append("sign_type=\"RSA\"");
        return sb.toString();
    }



        public static final String  SIGN_ALGORITHMS = "SHA1WithRSA";

        */
/**
         * RSA签名
         * @param content 待签名数据
         * @param privateKey 商户私钥
         * @param input_charset 编码格式
         * @return 签名值
         *//*

        public static String sign(String content, String privateKey, String input_charset)
        {
            try
            {
                byte[] decode = Base64.decode(privateKey);
                PKCS8EncodedKeySpec priPKCS8   = new PKCS8EncodedKeySpec(decode );
                KeyFactory keyf= KeyFactory.getInstance("RSA");
                PrivateKey priKey= keyf.generatePrivate(priPKCS8);

                java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

                signature.initSign(priKey);
                signature.update( content.getBytes(input_charset) );

                byte[] signed = signature.sign();

                return Base64.encode(signed);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }
}
*/
