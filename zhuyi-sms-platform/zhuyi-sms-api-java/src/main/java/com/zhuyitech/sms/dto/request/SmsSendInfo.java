package com.zhuyitech.sms.dto.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author walkman
 */
@Data
public class SmsSendInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String phoneNumber;

    private long lastTime;

    /**
     * 校验失败的次数
     */
    private int failedCount;

    /**
     * 验证码短信发送的次数
     */
    private int sendCount;

}
