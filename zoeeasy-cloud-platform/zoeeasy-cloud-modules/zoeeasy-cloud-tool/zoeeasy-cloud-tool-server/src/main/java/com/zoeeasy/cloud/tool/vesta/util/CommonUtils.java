package com.zoeeasy.cloud.tool.vesta.util;

import java.util.Arrays;

public class CommonUtils {

    protected static String[] SWITCH_ON_EXPECT = new String[]{"ON", "TRUE", "on", "true"};

    protected static String[] SWITCH_OFF_EXPECT = new String[]{"OFF", "FALSE", "off", "false"};

    public static boolean isOn(String swtch) {

        if (Arrays.asList(SWITCH_ON_EXPECT).contains(swtch)) {
            return true;
        }
        return false;
    }

    public static boolean isPropKeyOn(String key) {

        String prop = System.getProperty(key);

        if (Arrays.asList(SWITCH_ON_EXPECT).contains(prop)) {
            return true;
        }

        return false;
    }
}