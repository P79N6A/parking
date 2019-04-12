package com.zoeeasy.cloud.axino.exception;

import com.zoeeasy.cloud.axino.bean.ResultMode;
import lombok.Getter;
import lombok.Setter;

/**
 * 异常返回结果
 *
 * @author sdk.jss.com.cn
 * @version 2.0
 * @since jdk1.6
 */
public class OpensnsException extends Exception {

    /**
     * 序列化UID
     */
    private static final long serialVersionUID = 8243127099991355146L;

    /**
     * 错误码
     */
    @Getter
    @Setter
    private String code;

    /**
     * 错误描述
     */
    @Getter
    @Setter
    private String describe;

    @Getter
    @Setter
    private ResultMode resultMode;

    public OpensnsException(String code, String describe) {
        super(describe);
        this.code = code;
    }

    /**
     * 构造异常
     *
     * @param code 异常状态码
     * @param msg  异常讯息
     */
    public OpensnsException(ResultMode resultMode) {
        super(resultMode.getDescribe());
        this.resultMode = resultMode;
    }

    /**
     * 构造异常
     *
     * @param code 异常状态码
     * @param ex   异常来源
     */
    public OpensnsException(String code, Exception ex) {
        super(ex);
        this.code = code;
    }

}


