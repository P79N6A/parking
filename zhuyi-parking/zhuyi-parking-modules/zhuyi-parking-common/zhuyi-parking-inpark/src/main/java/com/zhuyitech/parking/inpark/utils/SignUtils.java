
package com.zhuyitech.parking.inpark.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SignUtils {

    public static String createSign(String params, String privateKey) throws UnsupportedEncodingException {

        return MD5Util.MD5Encode(params + privateKey, "utf-8");
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param paramsMap 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     * @throws Exception
     */
    public static String createLinkString(Map<String, String> paramsMap) throws Exception {

        List<String> keys = new ArrayList<String>(paramsMap.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = (String) paramsMap.get(key);

            if (!"sign".equals(key)) {
                if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                    prestr = prestr + key + "=\"" + URLEncoder.encode(value, "utf-8") + "\"";
                } else {
                    prestr = prestr + key + "=\"" + URLEncoder.encode(value, "utf-8") + "\"" + "&";
                }
            }
        }

        return prestr;
    }
}
