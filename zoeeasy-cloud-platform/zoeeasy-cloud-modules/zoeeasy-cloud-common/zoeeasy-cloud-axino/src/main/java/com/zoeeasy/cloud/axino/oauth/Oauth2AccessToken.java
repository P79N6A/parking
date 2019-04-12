package com.zoeeasy.cloud.axino.oauth;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.zoeeasy.cloud.axino.constant.StateData;

import java.io.IOException;
import java.util.*;

/**
 * ISV类用户在获取到auth_code后可套用此方法
 *
 * @author sdk.jss.com.cn
 * @version 2.0
 * @since jdk1.6
 **/
public class Oauth2AccessToken {

    static String _url = StateData.accessToken_url; //获取token地址
    static String _header = StateData.contentType;  //http发送模式
    static String client_id = StateData.client_id;  //创建应用后，分配给应用的appKey
    static String client_secret = StateData.client_secret; //创建应用后，分配给应用的appSecret
    static String token_grant_type = StateData.grant_type_token;   //授权类型，在本步骤中，此值为“authorization_code”
    static String redirect_uri = StateData.redirect_uri;   //回调地址，必传且不能为空

    public static void main(String[] args) {

        //开发者通过code可以换取access_token、授权商户的uerId
        String code = StateData.auth_code;//临时授权码code,isv用户通过页面请求（authorize.html）返回code值；
        String taxNum = StateData.taxNum;//授权企业的税号，isv用户通过页面请求（authorize.html）返回taxnum值；
        String resValue = getAccessToken(code, taxNum);

        /**
         * ISV用户定时刷新token
         * refresh_token提供给开发者一种延长access_token有效期的机制，提升开发者提供给商户应用的使用流畅性。
         * 开发者使用code获取access_token时会返回一个refresh_token，在refresh_token有效期内可以通过refresh_token刷新access_token有效时长。
         * 注：refresh_token超时时，需重走授权流程
         */

        if (!StringUtils.isEmpty(resValue)) {
            Map<String, Object> map = parseJSON2Map(resValue);
            new RefreshTokenTimer(_url, _header,
                    String.valueOf(map.get("refresh_token")), client_secret, String.valueOf(map.get("userId"))).executeTimer();
        } else {
            System.out.println("获取授权码报错");
        }
    }

    /**
     * 获取去token
     *
     * @return
     */
    public static String getAccessToken(String code, String taxNum) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = HttpClients.createDefault();
            HttpUriRequest build = RequestBuilder
                    .post(_url)
                    .addHeader("Content-Type", _header)
                    .addParameter("client_id", client_id)
                    .addParameter("client_secret", client_secret)
                    .addParameter("grant_type", token_grant_type)
                    .addParameter("redirect_uri", redirect_uri)
                    .addParameter("code", code)
                    .addParameter("taxNum", taxNum)
                    .build();

            //请求
            response = client.execute(build);
            HttpEntity entity = response.getEntity();

            String resValue = "";
            if (entity != null) {
                resValue = EntityUtils.toString(entity);
                System.out.println("response: " + resValue);
            }
            //释放资源
            EntityUtils.consume(entity);
            return resValue;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (client != null) {
                    client.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * json转map
     *
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> parseJSON2Map(String jsonStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
//        JSONObject json = JSONObject.fromObject(jsonStr);
//        for (Object k : json.keySet()) {
//            Object v = json.get(k);
//            //如果内层还是数组的话，继续解析
//            if (v instanceof JSONArray) {
//                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//                Iterator<JSONObject> it = ((JSONArray) v).iterator();
//                while (it.hasNext()) {
//                    JSONObject json2 = it.next();
//                    list.add(parseJSON2Map(json2.toString()));
//                }
//                map.put(k.toString(), list);
//            } else {
//                map.put(k.toString(), v);
//            }
//        }
        return map;
    }

}
