package com.zhuyitech.parking.shiro.authc;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;


/**
 * 参考org.apache.shiro.authc.UsernamePasswordToken，增加了用户类型参数
 *
 * @author walkman
 * @see org.apache.shiro.authc.UsernamePasswordToken
 */
public class WeixinOpenIdToken implements HostAuthenticationToken, RememberMeAuthenticationToken {

    /**
     * 用户名
     */
    private String openId;

    /**
     * 是否记住我
     * 默认值：<code>false</code>
     */
    private boolean rememberMe = false;

    /**
     * 主机名称或ip
     */
    private String host;

    public WeixinOpenIdToken() {

    }

    /**
     * 构造方法
     *
     * @param openId     微信OpenID
     * @param rememberMe 是否记住我
     * @param host       主机或ip
     */
    public WeixinOpenIdToken(final String openId,
                             final boolean rememberMe, final String host) {
        this.openId = openId;
        this.rememberMe = rememberMe;
        this.host = host;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * Simply returns {@link #getOpenId() getOpenId()}.
     *
     * @return the {@link #getOpenId() username}.
     * @see org.apache.shiro.authc.AuthenticationToken#getPrincipal()
     */
    @Override
    public Object getPrincipal() {
        return getOpenId();
    }

    /**
     * Returns the {@link #getOpenId() password} char array.
     *
     * @return the {@link #getOpenId() password} char array.
     * @see org.apache.shiro.authc.AuthenticationToken#getCredentials()
     */
    @Override
    public Object getCredentials() {
        return getOpenId();
    }

    @Override
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    /**
     * 清除数据
     * 密码如果不为空，设置成0x00
     */
    public void clear() {
        this.openId = null;
        this.host = null;
        this.rememberMe = false;
    }

    /**
     * 重写toString方法
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(" - ");
        sb.append(openId);
        sb.append(", rememberMe=").append(rememberMe);
        if (host != null) {
            sb.append(" (").append(host).append(")");
        }
        return sb.toString();
    }
}


