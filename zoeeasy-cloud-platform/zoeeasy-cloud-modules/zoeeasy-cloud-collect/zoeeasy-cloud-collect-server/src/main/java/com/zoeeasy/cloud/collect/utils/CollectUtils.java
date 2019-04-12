package com.zoeeasy.cloud.collect.utils;

import cn.hutool.core.util.HexUtil;

/**
 * Collect工具类
 *
 * @author wf
 */
public class CollectUtils {

    /**
     * 对16进制字节数组，转为10进制进行异或运算
     *
     * @param msgBytes
     * @return 异或运算后的Integer
     * @date
     */
    public static Integer validateMessage(byte[] msgBytes) {
        Integer resultInt = Integer.parseInt(HexUtil.encodeHexStr(new byte[]{msgBytes[0]}), 16);
        for (int i = 1; i < msgBytes.length; i++) {
            resultInt = resultInt ^ Integer.parseInt(HexUtil.encodeHexStr(new byte[]{msgBytes[i]}), 16);
        }
        return resultInt;
    }

}
