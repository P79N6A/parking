package com.zoeeasy.cloud.alipay.config;

import java.util.*;

public class AlipayCore {

    private AlipayCore() {
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (Map.Entry<String, String> entry : result.entrySet()) {
            String value = entry.getValue();
            String key = entry.getKey();
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                    || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    /**
     * 把数组所有元素，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {
                //拼接时，不包括最后一个&字符
                prestr = new StringBuilder().append(prestr).append(key).append("=").append(value).toString();
            } else {
                prestr =
                        new StringBuilder().append(prestr).append(key).append("=").append(value).append("&").toString();
            }
        }
        return prestr;
    }

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
//    public static void logResult(String sWord, String filename) {
//        FileWriter writer = null;
//        try {
//            writer = new FileWriter(AlipayConfig.log_path + "alipay_log_" + System.currentTimeMillis() + filename + ".txt");
//            writer.write(sWord);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (writer != null) {
//                try {
//                    writer.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
