package com.zhuyitech.parking.shiro;

import com.scapegoat.infrastructure.core.session.SessionIdentity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 *
 * @author walkman
 * @date 2016年11月12日 上午9:49:19
 */
public final class ShiroUtils {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    private ShiroUtils() {
    }

    /**
     * 当前Subject
     *
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    /**
     * 判断用户是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null &&
                SecurityUtils.getSubject().isAuthenticated();
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    public static SessionIdentity getPrincipal() {
        return (SessionIdentity) SecurityUtils.getSubject().getPrincipal();
    }

    public static String getCaptcha(String key) {
        String captcha = getSessionAttribute(key).toString();
        getSession().removeAttribute(key);
        return captcha;
    }
}
