package com.zoeeasy.cloud.inspect.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * HttpContextUtils
 *
 * @author walkman
 */
public class HttpContextUtils {

    private HttpContextUtils() {
    }

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
