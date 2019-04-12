package com.zhuyitech.sms.message;


import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 短信验证码发送消息内容
 *
 * @author walkman
 * @date 2018-03-01
 */
@Data
public class SmsCodeSendPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 短信模板ID
     */
    private String templateId;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 验证码类型(1, 注册 2, 登录 3, 忘记密码)
     */
    private Integer verifyType;

    /**
     * 短信参数
     *
     * @return
     */
    private Map<String, String> parameters;

}
