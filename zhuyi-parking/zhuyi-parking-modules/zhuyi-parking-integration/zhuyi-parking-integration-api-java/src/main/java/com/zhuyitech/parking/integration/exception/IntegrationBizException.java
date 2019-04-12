package com.zhuyitech.parking.integration.exception;

import com.scapegoat.infrastructure.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IntegrationBizException
 *
 * @author walkman
 */
public class IntegrationBizException extends BusinessException {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationBizException.class);

    public IntegrationBizException() {

    }

    public IntegrationBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public IntegrationBizException(int code, String msg) {
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
    public IntegrationBizException newInstance(String msgFormat, Object... args) {
        return new IntegrationBizException(this.exeCode, msgFormat, args);
    }
}
