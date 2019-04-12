package com.zoeeasy.cloud.axino.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zoeeasy.cloud.axino.bean.PrivateData;
import com.zoeeasy.cloud.axino.bean.PublicData;
import com.zoeeasy.cloud.axino.bean.RequestMode;
import com.zoeeasy.cloud.axino.exception.OpensnsException;
import com.zoeeasy.cloud.axino.service.OpenApiV1;
import com.zoeeasy.cloud.axino.util.SecurityUtil;
import com.zoeeasy.cloud.axino.constant.StateData;
import com.zoeeasy.cloud.axino.util.ValidataUtil;

/**
 * 测试示例
 *
 * @author sdk.jss.com.cn
 * @version 2.0
 * @since jdk1.6
 */
public class TestOpenApiV1 {

    public static void main(String[] args) {
        // 定义请求头数据
        Map<String, String> headers = getHeaders();
        // 定义请求体中的公共数据
        PublicData pdData = getPublicData();
        // 定义请求体中的业务数据
        PrivateData<Object> pvData = new PrivateData<Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        String randomSixNum = "123456";// 生成6位不重复的随机数字
        map.put("signid", SecurityUtil.MD5Encrypt(randomSixNum));
        map.put("code", "AR3NP7");
        map.put("validator", "123456");
        list.add(map);
        pvData.setServicedata(list);
        RequestMode requestMode = getRequestMode(pdData, pvData);
        OpenApiV1 sdk = new OpenApiV1();
        String result = "";
        try {
            // 调用服务
            result = sdk.handle(StateData.url, headers, requestMode);
            if (!ValidataUtil.isEmpty(result)) {
                System.out.println("服务端的响应：" + result);
            }
        } catch (OpensnsException e) {
            System.out.printf("Request failed 【" + e.getCode() + ":" + e.getMessage() + "】");
            e.printStackTrace();
        }
    }


    /**
     * 定义请求头数据
     *
     * @return
     */
    private static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("appKey", StateData.app_key); // 用户申请的appkey
        headers.put("accessToken", StateData.app_accessToken); // 用户Oauth登录后得到的令牌accessToken
        headers.put("compress", StateData.app_compressType);// 压缩方式：提供GZIP 置空“”不压缩
        headers.put("signMethod", StateData.app_signType); // 加密方式：提供AES/AES，不可为空
        headers.put("dataType", StateData.app_dataType); // 数据请求格式： JSON/XML
        headers.put("appRate", StateData.app_rate); // app并发请求数 ，平台默认10如需升级请联系开放平台
        headers.put("userTax", StateData.user_Tax); // ISV下商家需要填写当前商户税号，普通商家模式用户可以不填写此值
        headers.put("Content-Type", StateData.contentType); // http发送模式
        return headers;
    }


    /**
     * 定义请求体中的公共数据
     *
     * @return
     */
    private static PublicData getPublicData() {
        PublicData pdData = new PublicData();
        pdData.setVersion(StateData.app_apiVersion); // API版本
        pdData.setTimestamp(String.valueOf(System.currentTimeMillis()));
        pdData.setMethod(StateData.app_api);// API名称
        return pdData;
    }

    private static RequestMode getRequestMode(PublicData pdata, PrivateData<Object> pvData) {
        RequestMode requestMode = new RequestMode();
        requestMode.setPrivate(pvData);
        requestMode.setPublic(pdata);
        return requestMode;
    }

}
