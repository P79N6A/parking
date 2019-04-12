package com.zhuyitech.sms.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 短信验证码信息
 *
 * @author AkeemSuper
 * @date 2018/9/10 0010
 */
@Data
public class SmsCheckInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 验证码
     */
    private String code;

    /**
     * 校验次数
     */
    private Integer checkCount;

    /**
     * 短信发送回执ID
     */
    private String smsRequestId;

}
