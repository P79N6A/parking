package com.zhuyitech.parking.pay.wechat.util;

import java.math.BigDecimal;

/**
 *  <pre>
 *
 *  </pre>
 *
 * @author walkman
 * @date 2017-07-11-10:11
 */
public class ConverterUtils {

    /**
     * 将单位分转换成单位元
     *
     * @param fee 将要被转换为元的分的数值
     */
    public static String feeToYuan(Integer fee) {
        return BigDecimal.valueOf(Double.valueOf(fee) / 100).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /**
     * 将单位为元转换为单位为分
     *
     * @param yuan 将要转换的元的数值字符串
     */
    public static Integer yuanToFee(String yuan) {
        return new BigDecimal(yuan).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
    }



}
