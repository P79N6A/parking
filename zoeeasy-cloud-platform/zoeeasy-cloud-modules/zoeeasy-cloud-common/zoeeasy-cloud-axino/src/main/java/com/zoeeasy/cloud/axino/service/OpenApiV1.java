package com.zoeeasy.cloud.axino.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.zoeeasy.cloud.axino.bean.RequestMode;
import com.zoeeasy.cloud.axino.exception.ExConstants;
import com.zoeeasy.cloud.axino.util.SecurityUtil;
import com.zoeeasy.cloud.axino.constant.StateData;
import com.zoeeasy.cloud.axino.util.ValidataUtil;
import com.zoeeasy.cloud.axino.exception.OpensnsException;

/**
 * 提供访问爱信诺开放平台 OpenApiV1 的接口
 *
 * @author sdk.jss.com.cn
 * @version 2.0
 * @since jdk1.6
 */
public class OpenApiV1 {

    /**
     * 执行API调用
     *
     * @param serverName  服务器的地址
     * @param headers     请求头参数
     * @param requestMode 请求体model
     * @return 返回服务器响应内容
     * @throws OpensnsException
     */
    public String handle(String serverName, Map<String, String> headers, RequestMode requestMode) throws OpensnsException {
        // 客户端请求的数据进行基本校验
        requestDataValidata(serverName, headers, requestMode);
        String dataType = (String) headers.get("dataType");
        String signMethod = (String) headers.get("signMethod");
        String clientReqParam = "";
        String aesParams = "";
        try {
            if ("json".equalsIgnoreCase(dataType)) {
                clientReqParam = installJsonStr(requestMode, clientReqParam);
            } else if ("xml".equalsIgnoreCase(dataType)) {
                clientReqParam = installXmlStr(requestMode);
            }
        } catch (Exception e) {
            throw new OpensnsException(ExConstants.input_Data_Type, ExConstants.input_Data_Type_Msg);
        }
        System.out.println("客户端组装的参数格式-->：" + clientReqParam);
        try {
            // 每个APP密钥不同，根据当前APP赋值相应密钥值
            aesParams = (String) SecurityUtil.AESEncrypt(clientReqParam, StateData.app_secret);
        } catch (Exception e) {
            throw new OpensnsException(ExConstants.security_Decryption, ExConstants.security_Encryption_Msg);
        }
        // 发送请求(客户端可以自定义自己的请求)
        HttpClientService httpService = new HttpClientService();
        String result = httpService.sendSyncSingleHttp(serverName, headers, aesParams);
        // 如果返回值为密文，此处进行AES解密操作
        if ("AES/AES".equalsIgnoreCase(signMethod)) {
            result = String.valueOf(SecurityUtil.AESDecrypt(result, StateData.app_secret));
        }
        return result;
    }

    /**
     * 客户端请求数据校验
     *
     * @param headers
     * @param requestMode
     * @throws OpensnsException
     */
    private void requestDataValidata(String serverName, Map<String, String> headers, RequestMode requestMode) throws OpensnsException {
        if (ValidataUtil.isEmpty(serverName) || (headers == null || headers.isEmpty()) || requestMode == null
                || requestMode.getPublic() == null || requestMode.getPrivate() == null) {
            throw new OpensnsException(ExConstants.input_Data_Null, ExConstants.input_Data_Null_Msg);
        }
        // 检查header参数不为空
        if (ValidataUtil.isEmpty(headers.get("appKey")) // appkey
                || ValidataUtil.isEmpty(headers.get("dataType"))// 数据格式<xml/json>
                || ValidataUtil.isEmpty(headers.get("accessToken"))// 授权码
                || ValidataUtil.isEmpty(headers.get("appRate"))) {
            throw new OpensnsException(ExConstants.input_Data_Type, ExConstants.input_Data_Type_Msg1);
        }
        // 检查加密算法
        if (!valiSignMethod(String.valueOf(headers.get("signMethod")))) {
            throw new OpensnsException(ExConstants.security_Decryption_arithmeticNULL,
                    ExConstants.security_Decryption_arithmeticNULL_Msg);
        }
        // 检查public参数不为空
        if (ValidataUtil.isEmpty(requestMode.getPublic().getMethod())
                || ValidataUtil.isEmpty(requestMode.getPublic().getTimestamp())
                || ValidataUtil.isEmpty(requestMode.getPublic().getVersion())) {
            throw new OpensnsException(ExConstants.input_Data_Type, ExConstants.input_Data_Null_Msg);
        }
        String dataType = (String) headers.get("dataType");
        // 检查public参数不为空
        if ((!"json".equalsIgnoreCase(dataType)) && (!"xml".equalsIgnoreCase(dataType))) {
            throw new OpensnsException(ExConstants.input_Data_Type, ExConstants.input_Data_Type_Msg);
        }
    }

    /**
     * 判断加密方法是否正确
     * true 正确
     *
     * @param signMethod
     * @return
     */
    private boolean valiSignMethod(String signMethod) {
        boolean isFlag = false;
        if ("AES".equalsIgnoreCase(signMethod) || "AES/AES".equalsIgnoreCase(signMethod)) {
            isFlag = true;
        }
        return isFlag;
    }

    /**
     * 组装成JSON格式字符串
     *
     * @param requestMode
     * @param clientReqParam
     * @return
     */
    private String installJsonStr(RequestMode requestMode, String clientReqParam) throws Exception {
        JSONObject jSONObj = new JSONObject();
        JSONObject publicObj = new JSONObject();
        JSONObject privateObj = new JSONObject();
        publicObj.put("method", requestMode.getPublic().getMethod());
        publicObj.put("version", requestMode.getPublic().getVersion());
        publicObj.put("timestamp", requestMode.getPublic().getTimestamp());
        privateObj.put("servicedata", requestMode.getPrivate().getServicedata());
        jSONObj.put("public", publicObj);
        jSONObj.put("private", privateObj);
        return jSONObj.toString();
    }

    /**
     * 组装成XML格式字符串
     *
     * @param requestMode
     * @return
     */
    private String installXmlStr(RequestMode requestMode) throws Exception {
//        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(requestMode);
//        XMLSerializer xml = new XMLSerializer();
//        String xmlStr = xml.write(jsonObject, "UTF-8");
//        return xmlStr;
        return "";
    }

}
