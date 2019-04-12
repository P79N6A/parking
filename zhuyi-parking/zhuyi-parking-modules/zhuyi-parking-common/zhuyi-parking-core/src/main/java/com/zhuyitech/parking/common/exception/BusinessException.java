package com.zhuyitech.parking.common.exception;

/**
 * @author walkman
 * @date：2017年4月30日 上午11:18:04
 * @description：业务异常
 */
public class BusinessException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BusinessException(String code) {
        super(code);
    }

    public BusinessException(String code, Object... arguments) {
        super(code);
    }

    public BusinessException(String code, Throwable cause) {
        super(code, cause);
    }
}
