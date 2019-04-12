package com.zhuyitech.parking.pay.wechat.util;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *     xml工具类
 * </pre>
 * @author zwq
 * @date 2017-07-10-15:14
 */
public class XmlJudgeUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlJudgeUtil.class);

    public static boolean isXML(String value) {
        try {
            DocumentHelper.parseText(value);
        } catch (DocumentException e) {
            return false;
        }
        return true;
    }


}
