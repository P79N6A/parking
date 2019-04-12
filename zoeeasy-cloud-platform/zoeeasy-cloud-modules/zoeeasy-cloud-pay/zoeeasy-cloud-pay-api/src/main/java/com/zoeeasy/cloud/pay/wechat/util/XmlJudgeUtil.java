package com.zoeeasy.cloud.pay.wechat.util;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

/**
 * <pre>
 *     xml工具类
 * </pre>
 *
 * @author zwq
 * @date 2017-07-10-15:14
 */
public class XmlJudgeUtil {


    public static boolean isXML(String value) {
        try {
            DocumentHelper.parseText(value);
        } catch (DocumentException e) {
            return false;
        }
        return true;
    }

}
