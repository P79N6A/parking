package com.zoeeasy.cloud.order.exception;

import com.scapegoat.infrastructure.exception.BusinessException;

public class ParkingBizException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public ParkingBizException() {

    }

    public ParkingBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public ParkingBizException(int code, String msg) {
        super(code, msg);
    }

    /**
     * 实例化异常
     *
     * @param msgFormat
     * @param args
     * @return
     */
    @Override
    public ParkingBizException newInstance(String msgFormat, Object... args) {
        return new ParkingBizException(this.exeCode, msgFormat, args);
    }

}
