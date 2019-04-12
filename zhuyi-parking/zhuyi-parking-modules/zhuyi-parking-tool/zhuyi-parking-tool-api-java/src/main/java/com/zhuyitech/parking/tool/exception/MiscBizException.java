package com.zhuyitech.parking.tool.exception;

import com.scapegoat.infrastructure.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MessageBizException
 *
 * @author walkman
 */
public class MiscBizException extends BusinessException {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(MiscBizException.class);

    public MiscBizException() {

    }

    public MiscBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public MiscBizException(int code, String msg) {
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
    public MiscBizException newInstance(String msgFormat, Object... args) {
        return new MiscBizException(this.exeCode, msgFormat, args);
    }
}
