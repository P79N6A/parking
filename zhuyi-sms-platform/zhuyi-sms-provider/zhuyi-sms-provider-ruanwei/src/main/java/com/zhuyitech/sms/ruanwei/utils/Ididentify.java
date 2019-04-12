package com.zhuyitech.sms.ruanwei.utils;

import cn.net.wnd.commons.JimAES2;
import com.alibaba.fastjson.JSON;

import com.zhuyitech.sms.ruanwei.config.CheckResEnum;
import com.zhuyitech.sms.ruanwei.config.IdCheckMsg;
import com.zhuyitech.sms.ruanwei.config.IdCheckResMsg;
import com.zhuyitech.sms.ruanwei.config.IdLoginResult;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;

/**
 */
public class Ididentify {

    public static void main(String args[]) {
        IdCheckMsg msg = new IdCheckMsg();
        msg.setYwlx("check identity");
        msg.setSessionId("");
        msg.setGmsfhm("130432198706110315");
        msg.setXm("陈磊");
        msg.setFsd("北京");
        msg.setAccount("sin7");
        msg.setXp("");
        msg.setAction("qsfz");
        msg.setCid("sin718600992571");
        System.out.println("--------------checkIdentity(\"陈磊\",\"130432198706110315\")---------------");
        System.out.println(testID("陈磊", "130432198706110315"));
        System.out.println("--------------checkIdentity(\"陈磊a\",\"130432198706110315\")---------------");
        System.out.println(testID("陈磊a", "130432198706110315"));
        System.out.println("----------------checkIdentity(\"陈 磊\",\"130432198706110315\")-------------");
        System.out.println(testID("陈 磊", "130432198706110315"));
        System.out.println("------------checkIdentity(\"陈磊\",\"130432198706110315X\")-----------------");
        System.out.println(testID("陈磊", "130432198706110315X"));
        System.out.println("---------------checkIdentity(\"陈磊\",\"130432198706110X\")--------------");
        System.out.println(testID("陈磊", "130432198706110X"));
    }

    public static String testID(String name, String identity) {

        String token = getToken();

        String reMsg = "";
        StringBuffer sb = new StringBuffer();
        try {
            sb.append("mid=1&action=qsfz&token=").append(token).append("&msg=");

            IdCheckMsg msg = new IdCheckMsg();
            msg.setYwlx("check identity");
            msg.setSessionId("");
            msg.setGmsfhm(identity);
            msg.setXm(name);
            msg.setFsd("");
            msg.setAccount("");
            msg.setXp("");
            msg.setAction("qsfz");
            msg.setCid(name + identity);
            String urlEncodeMsg = URLEncoder.encode(msg.toJsonString(), "UTF-8");
            String encMsg = JimAES2.getInstance().encodeDataAes(urlEncodeMsg);
            String rec = SmsClientAccessTool.getInstance().doAccessHTTPPost(url,
                    sb.append(encMsg).append("&f=1").toString(), "");

            IdCheckResMsg resMsgObj = JSON.parseObject(rec, IdCheckResMsg.class);
            if (resMsgObj.getStatcode() == 100) {
                return "验证成功";
            } else {
                return CheckResEnum.getByValue(resMsgObj.getStatcode()).getDescription();
            }
        } catch (Exception e) {
            return "认证失败";
        }
    }

    public static String url = "http://api.g315.net:8511/wndc/tkn/queryaccount.do";
    public static String account = "xly_zz";
    public static String pwd = "zsxytest0810";

    public static String getToken() {
        IdLoginResult loginRes = new IdLoginResult();
        String reMsg = "";
        StringBuffer sb = new StringBuffer();
        try {
            String param = sb.append("msg=").append(account).append(EncryptUtil.encByMd5(pwd)).
                    append("&token=&action = login").toString();
            reMsg = SmsClientAccessTool.getInstance().doAccessHTTPPost(url, param, "");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (reMsg.length() > 0) {
            loginRes = JSON.parseObject(reMsg, IdLoginResult.class);
        }
        return loginRes.getCallbackUrl();
    }
}
