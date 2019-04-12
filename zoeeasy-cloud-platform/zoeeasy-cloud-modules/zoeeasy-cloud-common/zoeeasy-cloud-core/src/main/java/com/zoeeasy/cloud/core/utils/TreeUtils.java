package com.zoeeasy.cloud.core.utils;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TreeUtils {

    /**
     * 创建pathCode,
     * Example: if numbers are 4,2 then returns "00004.00002";
     *
     * @param numbers
     * @return
     */
    public static String createCode(int... numbers) {
        if (CollectionUtils.isEmpty(Arrays.asList(numbers))) {
            return null;
        }
        //必须将普通数组 boxed才能 在 map 里面 toString
        return Arrays.stream(numbers).boxed().map(i -> String.format("%05d", i))
                .collect(Collectors.joining("."));
    }

    /**
     * Appends a child code to a parent code.
     * Example: if parentCode = "00001", childCode = "00042" then returns "00001.00042".
     *
     * @param parentCode Parent code. Can be null or empty if parent is a root.
     * @param childCode  Child code
     * @return
     */
    public static String appendCode(String parentCode, String childCode) {
        if (StringUtils.isEmpty(childCode)) {
            throw new IllegalArgumentException("childCode can not be null or empty.");
        }
        if (StringUtils.isEmpty(parentCode)) {
            return childCode;
        }
        return parentCode + "." + childCode;
    }

    /**
     * Gets relative code to the parent.
     * Example: if code = "00019.00055.00001" and parentCode = "00019" then returns "00055.00001".
     *
     * @param code
     * @param parentCode
     * @return
     */
    public static String getRelativeCode(String code, String parentCode) {

        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("code can not be null or empty.");
        }
        if (StringUtils.isEmpty(parentCode)) {
            return code;
        }
        if (code.length() == parentCode.length())
            return null;
        return code.substring(parentCode.length() + 1);
    }

    /**
     * Calculates next code for given code.
     * Example: if code = "00019.00055.00001" returns "00019.00055.00002".
     *
     * @param code The code.
     * @return
     */
    public static String calculateNextCode(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("code can not be null or empty.");
        }
        return appendCode(getParentCode(code), createCode(Integer.valueOf(getLastUnitCode(code)) + 1));
    }

    /**
     * Gets the last unit code.
     * Example: if code = "00019.00055.00001" returns "00001".
     *
     * @param code The code.
     * @return
     */
    public static String getLastUnitCode(String code) {

        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("code can not be null or empty.");
        }
        String[] strArray = code.split("\\.");
        return strArray[strArray.length - 1];
    }

    /**
     * Gets parent code.
     * Example: if code = "00019.00055.00001" returns "00019.00055".
     *
     * @param code
     * @return
     */
    public static String getParentCode(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("code can not be null or empty.");
        }
        String[] strArray = code.split("\\.");
        if (strArray.length == 1)
            return null;
        return StringUtils.join(Arrays.copyOf(strArray, strArray.length - 1), ".");
    }

    /**
     * Gets parent code path.
     * Example: if code = "00019.00055.00001" returns "00019,00019.00055".
     *
     * @param code
     * @return
     */
    public static String[] getParentCodes(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("code can not be null or empty.");
        }
        String[] strArray = code.split("\\.");
        if (strArray.length == 1)
            return null;
        String[] result = new String[strArray.length - 1];
        for (int i = 0; i < strArray.length - 1; i++) {
            result[i] = StringUtils.join(Arrays.copyOf(strArray, i + 1), ".");
        }
        return result;
    }

    /**
     * Gets parent code path.
     * * Example: if code = "00019.00055.00001" returns "00019,00019.00055,00019.00055.00001".
     *
     * @param code
     * @return
     */
    public static String[] getParentCodePath(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("code can not be null or empty.");
        }
        String[] strArray = code.split("\\.");
        if (strArray.length == 1)
            return strArray;
        String[] result = new String[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            result[i] = StringUtils.join(Arrays.copyOf(strArray, i + 1), ".");
        }
        return result;
    }

    public static void main(String[] args) {
        String[] result1 = getParentCodePath("1000");
        System.out.println(result1);
        String[] result2 = getParentCodePath("00001.00002");
        System.out.println(result2);

        String[] result3 = getParentCodePath("00001.00002.0003");
        System.out.println(result3);
        String[] result4 = getParentCodePath("00001.00002.0003.0004");
        System.out.println(result4);
    }
}
