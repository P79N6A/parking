package com.zhuyitech.parking.alipay.api.test;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AppPayTest {

    static final String ALIPAY_URL = "https://openapi.alipay.com/gateway.do";

    static final String ALIPAY_FORMAT = "json";

    /**
     * 请求使用的编码格式，如utf-8,gbk,gb2312等
     */
    static final String ALIPAY_CHARSET = "utf-8";

    static final String ALIPAY_SIGN_TYPE = "RSA2";

    static final String ALIPAY_APPID = "";

    static final String ALIPAY_PRIVATE_KEY = "";

    static final String ALIPAY_PUBLIC_KEY = "";

    static final String ALIPAY_NOTIFY_URL = "";

    /**
     * APP支付测试
     *
     * @param amount
     * @param body
     * @return
     */
    public String aliPay(String amount, Map<String, Object> body) {

        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(ALIPAY_URL, ALIPAY_APPID, ALIPAY_PRIVATE_KEY, ALIPAY_FORMAT, ALIPAY_CHARSET, ALIPAY_PUBLIC_KEY, ALIPAY_SIGN_TYPE);
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setPassbackParams(URLEncoder.encode(body.toString()));
        //描述信息  添加附加数据
        //商品标题
        model.setSubject("魅格");
        //商家订单编号
        model.setOutTradeNo(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + (int) (Math.random() * 90000 + 10000));
        //超时关闭该订单时间
        model.setTimeoutExpress("30m");
        //订单总金额
        model.setTotalAmount(amount);
        //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        //回调地址
        request.setNotifyUrl(ALIPAY_NOTIFY_URL);
        String orderStr = "";
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            orderStr = response.getBody();
            //就是orderString 可以直接给客户端请求，无需再做处理。
            System.out.println(orderStr);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return orderStr;
    }

    public String aliPay_notify(Map requestParams) {
        System.out.println("支付宝支付结果通知" + requestParams.toString());
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();

        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> request, String publicKey, String charset, String sign_type)
        try {
            //　验证签名　　　
            boolean flag = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, ALIPAY_CHARSET, ALIPAY_SIGN_TYPE);
            if (flag) {
                if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
                    //付款金额
                    String amount = params.get("buyer_pay_amount");
                    //商户订单号
                    String out_trade_no = params.get("out_trade_no");
                    //支付宝交易号
                    String trade_no = params.get("trade_no");
                    //附加数据
                    String passback_params = URLDecoder.decode(params.get("passback_params"));
                }
            }
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "success";
    }
}
