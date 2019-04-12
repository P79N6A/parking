package com.zoeeasy.cloud.core.utils;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.zoeeasy.cloud.core.cst.Const;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 *
 * @author walkman
 */
public class Utils {
    private Utils() {
    }

    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static BigDecimal turnNullToZero(BigDecimal nullValue) {
        return nullValue == null ? BigDecimal.ZERO : nullValue;
    }

    /**
     * @param nullValue
     * @return
     * @author
     */
    public static BigInteger turnNullToZero(BigInteger nullValue) {
        return nullValue == null ? BigInteger.ZERO : nullValue;
    }

    public static Integer turnNullToZero(Integer nullValue) {
        return nullValue == null ? Integer.valueOf(0) : nullValue;
    }

    /**
     * 格式化时间
     *
     * @param time
     * @return
     * @author
     */
    public static Time formatTime(Time time) {
        SimpleDateFormat sf = new SimpleDateFormat(TIME_FORMAT);
        try {
            time = new Time(sf.parse(sf.format(new Date(time.getTime()))).getTime());
        } catch (ParseException e) {
            return null;
        }
        return time;
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     * @author
     */
    public static Date formatDate(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT);
        try {
            date = new Date(sf.parse(sf.format(date)).getTime());
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    /**
     * 格式化日期
     *
     * @param dateString
     * @return
     */
    public static Date parseDate(String dateString) {
        SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT);
        Date date;
        try {
            date = sf.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    /**
     * @return String
     * @throws
     * @Description: 格式化日期，date to string(HH:mm:ss)
     * @author wang.hai
     */
    public static String formatTimeToString(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat(TIME_FORMAT);
        return sf.format(date);
    }

    /**
     * @return String
     * @throws
     * @Description: 格式化日期，date to string
     * @author wang.hai
     */
    public static String formatDateToString(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT);
        return sf.format(date);
    }

    public static String addDayOfYear(Date date, int amt) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DAY_OF_YEAR, amt);
        Date dt1 = rightNow.getTime();
        return formatDateToString(dt1);
    }

    /**
     * 时间比大小
     *
     * @param t1
     * @param t2
     * @return 0:t1=t2; <0:t1<t2; >0:t1>t2;
     */
    public static int timeCompare(String t1, String t2) {
        SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMAT);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(formatter.parse(t1));
            c2.setTime(formatter.parse(t2));
        } catch (ParseException e) {
        }
         /*eg.
          * i=r1.compareTo(r);//比较两个时间的先后r1是当前时间 r是你指定的时间
         if(i==0)System.out.println("ri=r");
         if(i<0)System.out.println("ri<r");
         if(i>0)System.out.println("ri>r");*/
        int result = c1.compareTo(c2);
        return result;
    }

    /**
     * @return Date
     * @throws
     * @Description: 获取日期
     * @author wang.hai
     * @date 2014-7-3下午7:14:33
     */
    public static Date getSessionDate() {
        return new Date();
    }

    /**
     * @return boolean
     * @throws
     * @Description: 检验验证码是否为纯数字
     */
    public static boolean checkVerificationCode(String str) {
        String regex = "^[0-9]{6}$";
        return Pattern.matches(regex, str);
    }

    /**
     * @return String
     * @throws
     * @Description: 格式化日期，date to string(HHmmss)
     * @author wang.hai
     */
    public static String formatTimeToStringNoSign(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("HHmmss");
        return sf.format(date);
    }

    /**
     * @return String
     * @throws
     * @Description: 格式化日期，date to string(yyyyMMdd)
     * @author wang.hai
     */
    public static String formatDateToStringNoSign(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(date);
    }

    /**
     * 判断是否全部为中文，是则返回false，只要有一个不是中文就返回true
     *
     * @param str
     * @return
     */
    public static boolean isChineseChar(String str) {
        boolean temp = false;
        for (int i = 0; i < str.length(); i++) {
            Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
            Matcher m = p.matcher(String.valueOf(str.charAt(i)));
            if (m.find()) {
                temp = false;
            } else {
                temp = true;
                return temp;
            }
        }
        return temp;
    }

    /**
     * 根据返回的参数创建一个map
     *
     * @param resStr http提交后返回的参数串
     * @return
     */
    public static Map<String, Object> getResponseMap(String resStr) {
        Map<String, Object> resMap = new HashMap<>();
        String[] strArray = resStr.split("&");
        for (String s : strArray) {
            String[] nameValue = s.split("=");
            if (nameValue.length == 1) {
                resMap.put(nameValue[0], "");
            } else {
                resMap.put(nameValue[0], nameValue[1]);
            }
        }
        return resMap;
    }

    public static String formatDateToString14(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sf.format(date);
    }

    /**
     * 判断字符串是否是指定日期类型,默认为yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Boolean isDate(String date, String pattern) {
        if (StringUtils.isEmpty(date)) {
            return false;
        }
        if (StringUtils.isEmpty(pattern)) {
            pattern = Const.FORMAT_DATETIME;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            simpleDateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
