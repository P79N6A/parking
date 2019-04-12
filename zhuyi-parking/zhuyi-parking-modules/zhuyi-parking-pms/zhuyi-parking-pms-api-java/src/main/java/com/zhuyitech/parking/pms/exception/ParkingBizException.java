package com.zhuyitech.parking.pms.exception;

import com.scapegoat.infrastructure.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParkingBizException extends BusinessException {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ParkingBizException.class);

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
