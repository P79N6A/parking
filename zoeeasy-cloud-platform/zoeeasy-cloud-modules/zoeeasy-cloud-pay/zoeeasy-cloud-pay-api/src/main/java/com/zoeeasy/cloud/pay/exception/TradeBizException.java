package com.zoeeasy.cloud.pay.exception;

import com.scapegoat.infrastructure.exception.BusinessException;

/**
 * 账户服务业务异常类,异常代码8位数字组成,前4位固定1001打头,后4位自定义
 *
 * @author walkman
 * @date 2018-01-10
 */
public class TradeBizException extends BusinessException {

    private static final long serialVersionUID = 1L;

    /**
     * 支付订单号重复
     **/
    public static final int TRADE_ORDER_NO_REPEAT_ERROR = 101;

    /**
     * 错误的支付方式
     **/
    public static final int TRADE_PAY_WAY_ERROR = 102;

    /**
     * 微信异常
     **/
    public static final int TRADE_WEIXIN_ERROR = 103;

    /**
     * 订单异常
     **/
    public static final int TRADE_ORDER_ERROR = 104;

    /**
     * 交易记录状态不为成功
     **/
    public static final int TRADE_ORDER_STATUS_NOT_SUCCESS = 105;
    /**
     * 支付宝异常
     **/
    public static final int TRADE_ALIPAY_ERROR = 106;
    /**
     * 参数异常
     **/
    public static final int TRADE_PARAM_ERROR = 107;
    /**
     * 交易系统异常
     **/
    public static final int TRADE_SYSTEM_ERROR = 108;

    /**
     * 支付方式无效
     */
    public static final int TRADE_PAY_WAY_INVALID = 109;

    /**
     * 支付金额无效
     */
    public static final int TRADE_PAY_AMOUNT_INVALID = 110;


    public TradeBizException() {

    }

    public TradeBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public TradeBizException(int code, String msg) {
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
    public TradeBizException newInstance(String msgFormat, Object... args) {
        return new TradeBizException(this.exeCode, msgFormat, args);
    }
}
