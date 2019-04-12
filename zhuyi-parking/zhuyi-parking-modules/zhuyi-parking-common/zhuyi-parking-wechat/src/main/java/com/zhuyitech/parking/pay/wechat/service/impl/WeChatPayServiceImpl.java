package com.zhuyitech.parking.pay.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.pay.wechat.config.WechatConfig;
import com.zhuyitech.parking.pay.wechat.enums.WeixinLoginEnum;
import com.zhuyitech.parking.pay.wechat.params.WeChatCheckAccessTokenParams;
import com.zhuyitech.parking.pay.wechat.params.WeChatGetAccessTokenParams;
import com.zhuyitech.parking.pay.wechat.params.WeChatGetUserInfoParams;
import com.zhuyitech.parking.pay.wechat.params.WeChatUpdateAccessTokenParams;
import com.zhuyitech.parking.pay.wechat.result.*;
import com.zhuyitech.parking.pay.wechat.service.WeChatPayService;
import org.apache.commons.codec.Charsets;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.util.List;

/**
 * <pre>
 *     微信支付接口实现
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-13:13
 */
@Service("weChatPayService")
public class WeChatPayServiceImpl implements WeChatPayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatPayServiceImpl.class);

    /**
     * 微信支付类型(公众账号)
     */
    private static final String TRADE_TYPE = "JSAPI";

    /**
     * 签名的方式（默认为MD5）
     */
    private static final String SIGN_TYPE = "MD5";

    /**
     * 返回的code
     */
    private static final String WECHAT_SUCCESS = "SUCCESS";

    private static final String WECHAT_FAIL = "FAIL";

    @Autowired
    private WechatConfig wechatConfig;

    @Override
    public ObjectResultDto<WeChatGetAccessTokenResult> getAccessToken(WeChatGetAccessTokenParams weChatGetAccessTokenParams) {
        ObjectResultDto<WeChatGetAccessTokenResult> resultDto = new ObjectResultDto<>();
        if (StringUtils.isEmpty(weChatGetAccessTokenParams.getCode())) {
            resultDto.makeResult(WeixinLoginEnum.CODE_NOT_VAILD.getValue(), WeixinLoginEnum.CODE_NOT_VAILD.getComment());
            return resultDto;
        }
        String authUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        if (weChatGetAccessTokenParams.getGoWay()) {
            authUrl = authUrl.replace("APPID", wechatConfig.getWechatPayAppid());
            authUrl = authUrl.replace("SECRET", wechatConfig.getWechatPayAppsecret());
        } else {
            authUrl = authUrl.replace("APPID", wechatConfig.getWechatJsapiAppid());
            authUrl = authUrl.replace("SECRET", wechatConfig.getWechatJsapiAppsecret());
        }

        authUrl = authUrl.replace("CODE", weChatGetAccessTokenParams.getCode());
        try {
            String resultJson = Request.Post(authUrl).execute().returnContent().asString(Charsets.UTF_8);
            if (StringUtils.isEmpty(resultJson)) {
                return resultDto.makeResult(WeixinLoginEnum.WEIXIN_RESPONSE_NULL.getValue(), WeixinLoginEnum.WEIXIN_RESPONSE_NULL.getComment());
            }
            WeChatGetAccessTokenResult result = JSON.parseObject(resultJson, WeChatGetAccessTokenResult.class);
            if (StringUtils.isNotEmpty(result.getErrCode())) {
                return resultDto.makeResult(Integer.parseInt(result.getErrCode()), result.getErrMsg());
            }
            resultDto.setData(result);
            resultDto.success();
        } catch (IOException e) {
            LOGGER.error("获取accesstoken失败" + e.getMessage());
            resultDto.makeResult(WeixinLoginEnum.GRT_ACCESSTOKEN_ERR.getValue(), WeixinLoginEnum.GRT_ACCESSTOKEN_ERR.getComment());
        }
        return resultDto;
    }

    @Override
    public ObjectResultDto<WeChatGetUserInfoResult> getUserInfo(WeChatGetUserInfoParams weChatGetUserInfoParams) {
        ObjectResultDto<WeChatGetUserInfoResult> objectResultDto = new ObjectResultDto<WeChatGetUserInfoResult>();
        if (StringUtils.isEmpty(weChatGetUserInfoParams.getAccessToken())) {
            return objectResultDto.makeResult(WeixinLoginEnum.ACCESSTOKEN_NOT_EXISTS.getValue(), WeixinLoginEnum.ACCESSTOKEN_NOT_EXISTS.getComment());
        }
        if (StringUtils.isEmpty(weChatGetUserInfoParams.getOpenId())) {
            objectResultDto.makeResult(WeixinLoginEnum.OPENID_NOT_EXISTS.getValue(), WeixinLoginEnum.OPENID_NOT_EXISTS.getComment());
            return objectResultDto;
        }
        String authUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        authUrl = authUrl.replace("ACCESS_TOKEN", weChatGetUserInfoParams.getAccessToken());
        authUrl = authUrl.replace("OPENID", weChatGetUserInfoParams.getOpenId());
        try {
            String resultJson = Request.Get(authUrl).execute().returnContent().asString(Charsets.UTF_8);
            if (StringUtils.isEmpty(resultJson)) {
                return objectResultDto.makeResult(WeixinLoginEnum.WEIXIN_RESPONSE_NULL.getValue(), WeixinLoginEnum.WEIXIN_RESPONSE_NULL.getComment());
            }
            WeChatGetUserInfoResult result = JSON.parseObject(resultJson, WeChatGetUserInfoResult.class);
            objectResultDto.setData(result);
            objectResultDto.success();
        } catch (Exception e) {
            LOGGER.error("获取用户信息失败" + e.getMessage());
            objectResultDto.makeResult(WeixinLoginEnum.GRT_USERINFO_ERR.getValue(), WeixinLoginEnum.GRT_USERINFO_ERR.getComment());
        }
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<WeChatAccessTokenIsValidResult> checkAccessToken(WeChatCheckAccessTokenParams weChatGetUserInfoParams) {
        ObjectResultDto<WeChatAccessTokenIsValidResult> objectResultDto = new ObjectResultDto<>();
        WeChatAccessTokenIsValidResult weChatAccessTokenIsValidResult = new WeChatAccessTokenIsValidResult();
        if (StringUtils.isEmpty(weChatGetUserInfoParams.getAccessToken())) {
            return objectResultDto.makeResult(WeixinLoginEnum.ACCESSTOKEN_NOT_EXISTS.getValue(), WeixinLoginEnum.ACCESSTOKEN_NOT_EXISTS.getComment());
        }
        if (StringUtils.isEmpty(weChatGetUserInfoParams.getOpenId())) {
            return objectResultDto.makeResult(WeixinLoginEnum.OPENID_NOT_EXISTS.getValue(), WeixinLoginEnum.OPENID_NOT_EXISTS.getComment());
        }
        String authUrl = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
        authUrl = authUrl.replace("ACCESS_TOKEN", weChatGetUserInfoParams.getAccessToken());
        authUrl = authUrl.replace("OPENID", weChatGetUserInfoParams.getOpenId());
        try {
            String resultJson = Request.Get(authUrl).execute().returnContent().asString(Charsets.UTF_8);
            if (StringUtils.isEmpty(resultJson)) {
                return objectResultDto.makeResult(WeixinLoginEnum.WEIXIN_RESPONSE_NULL.getValue(), WeixinLoginEnum.WEIXIN_RESPONSE_NULL.getComment());
            }
            WeChatCheckAccessTokenResult baseResult = JSON.parseObject(resultJson, WeChatCheckAccessTokenResult.class);
            if (!"0".equals(baseResult.getErrCode())) {
                weChatAccessTokenIsValidResult.setValid(Boolean.FALSE);
            } else {
                weChatAccessTokenIsValidResult.setValid(Boolean.TRUE);
            }
            objectResultDto.setData(weChatAccessTokenIsValidResult);
            objectResultDto.success();
        } catch (Exception e) {
            LOGGER.error("检查AccessToken失败" + e.getMessage());
            objectResultDto.makeResult(WeixinLoginEnum.CHECK_ACCESSTOKEN_ERR.getValue(), WeixinLoginEnum.CHECK_ACCESSTOKEN_ERR.getComment());
        }
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<WeChatUpdateAccessTokenResult> updateAccessToken(WeChatUpdateAccessTokenParams weChatUpdateAccessTokenParams) {
        ObjectResultDto<WeChatUpdateAccessTokenResult> objectResultDto = new ObjectResultDto<>();
        if (StringUtils.isEmpty(weChatUpdateAccessTokenParams.getRefreshToken())) {
            return objectResultDto.makeResult(WeixinLoginEnum.REFRESHTOKEN_NOT_EXISTS.getValue(), WeixinLoginEnum.REFRESHTOKEN_NOT_EXISTS.getComment());
        }
        String authUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
        authUrl = authUrl.replace("REFRESH_TOKEN", weChatUpdateAccessTokenParams.getRefreshToken());
        authUrl = authUrl.replace("APPID", wechatConfig.getWechatPayAppid());
        try {
            String resultJson = Request.Get(authUrl).execute().returnContent().asString(Charsets.UTF_8);
            if (StringUtils.isEmpty(resultJson)) {
                return objectResultDto.makeResult(WeixinLoginEnum.WEIXIN_RESPONSE_NULL.getValue(), WeixinLoginEnum.WEIXIN_RESPONSE_NULL.getComment());
            }
            WeChatUpdateAccessTokenResult baseResult = JSON.parseObject(resultJson, WeChatUpdateAccessTokenResult.class);
            objectResultDto.setData(baseResult);
            objectResultDto.success();
        } catch (Exception e) {
            LOGGER.error("刷新AccessToken失败" + e.getMessage());
            objectResultDto.makeResult(WeixinLoginEnum.REFRESH_ACCESSTOKEN_RRR.getValue(), WeixinLoginEnum.REFRESH_ACCESSTOKEN_RRR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 解析微信对账单
     *
     * @param textBill 微信的对账单记录
     * @return
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
     * @param params
     * @return
     */
    private WeChatPayBaseBillResult buildBill(String[] params) {
        WeChatPayBaseBillResult billResult = new WeChatPayBaseBillResult();
        billResult.setTradeTime(params[0]);
        billResult.setAppId(params[1]);
        billResult.setMchId(params[2]);
        billResult.setSubMchId(params[3]);
        billResult.setDeviceInfo(params[4]);
        billResult.setTransationId(params[5]);
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
     * @return
     */
    private ObjectResultDto<String> postWeChatCommon(String url, String xmlParams) {
        ObjectResultDto<String> objectResultDto = new ObjectResultDto<>();
        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(xmlParams)) {
            LOGGER.error("请求地址或者请求参数为空：{}, {}", url, xmlParams);
            return objectResultDto.failed("请求地址或者请求参数为空");
        }
        LOGGER.info("微信请求的地址和参数：{}, {}", url, xmlParams);
        try {
            // 将请求的XML字符串的编码转换为ISO-8859-1
            String xmlISO = new String(xmlParams.getBytes(Charsets.UTF_8), Charsets.ISO_8859_1);
            // 发送请求微信接口
            String resultXml = Request.Post(url).bodyString(xmlISO, ContentType.APPLICATION_XML).execute().returnContent().asString(Charsets.UTF_8);
            LOGGER.info("微信接口请求响应结果：{}", resultXml);
            objectResultDto.setData(resultXml);
            return objectResultDto.success();
        } catch (Exception e) {
            LOGGER.error("请求微信接口异常", e);
            return objectResultDto.failed("请求微信接口异常");
        }
    }

    /**
     * 微信接口请求需要证书
     * (如：申请退款)
     *
     * @param url
     * @param xmlParams
     * @return
     */
    private ObjectResultDto<String> postWithKey(String url, String xmlParams) {
        ObjectResultDto<String> objectResultDto = new ObjectResultDto<>();
        FileInputStream instream = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            // 读取本机存放的PKCS12证书文件
            File certFile = new File(wechatConfig.getWechatCertificatePath());
            LOGGER.info("认证文件的路径：{}", certFile.getPath());
            instream = new FileInputStream(certFile);
            // 指定PKCS12的密码(商户ID)
            keyStore.load(instream, wechatConfig.getWechatPayMchId().toCharArray());
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, wechatConfig.getWechatPayMchId().toCharArray()).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            // 发送请求微信接口
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(xmlParams, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String resultXML = EntityUtils.toString(response.getEntity(), "UTF-8");
            EntityUtils.consume(entity);
            LOGGER.info("申请退款微信返回的结果：{}", resultXML);
            objectResultDto.setData(resultXML);
            return objectResultDto.success();
        } catch (Exception e) {
            LOGGER.error("请求微信接口异常", e);
            return objectResultDto.failed("请求微信接口异常");
        }finally{
            if(null != instream) {
                try {
                    instream.close();
                } catch (IOException e) {
                    return objectResultDto.failed("请求微信接口异常");
                }
            }
        }
    }
}
