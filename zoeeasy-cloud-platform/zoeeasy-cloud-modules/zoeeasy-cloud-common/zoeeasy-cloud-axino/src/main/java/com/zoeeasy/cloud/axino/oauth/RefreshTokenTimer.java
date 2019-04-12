package com.zoeeasy.cloud.axino.oauth;

import com.zoeeasy.cloud.axino.constant.StateData;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时刷新token
 * 当refreshToken过期时需重新执行授权流程
 *
 * @author sdk.jss.com.cn
 * @version 2.0
 * @since jdk1.6
 **/
public class RefreshTokenTimer implements Runnable {

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    private String _url; //获取token地址
    private String _header;
    private String client_secret; //创建应用后，分配给应用的appSecret

    private String refresh_token; //刷新令牌
    private String client_id; //获取access_token时授权商户的userId
    private String grant_type = StateData.grant_type_refreshToken; //授权类型，此值固定为“refresh_token”

    public RefreshTokenTimer(String url, String header, String refresh_token,
                             String client_secret, String client_id) {
        this._url = url;
        this._header = header;
        this.refresh_token = refresh_token;
        this.client_secret = client_secret;
        this.client_id = client_id;
    }

    void executeTimer() {
        executor.scheduleAtFixedRate(new RefreshTokenTimer(_url, _header,
                refresh_token, client_secret, client_id), StateData.delay, StateData.preiod, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = HttpClients.createDefault();
            HttpUriRequest build = RequestBuilder
                    .post(_url)
                    .addHeader("Content-Type", _header)
                    .addParameter("refresh_token", refresh_token)
                    .addParameter("client_id", client_id)
                    .addParameter("client_secret", client_secret)
                    .addParameter("grant_type", grant_type)
                    .build();

            //请求
            response = client.execute(build);
            HttpEntity entity = response.getEntity();

            String resValue = "";
            if (entity != null) {
                resValue = EntityUtils.toString(entity);
                System.out.println("refresh_toke_response: " + resValue);
            }

            if (!StringUtils.isEmpty(resValue)) {
                Map<String, Object> map = Oauth2AccessToken.parseJSON2Map(resValue);
                refresh_token = String.valueOf(map.get("refresh_token"));
            }
            //释放资源
            EntityUtils.consume(entity);
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
                e.printStackTrace();
            }
        }
    }
}

