package com.zhuyitech.parking.shiro.authc;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;


/**
 * 参考org.apache.shiro.authc.UsernamePasswordToken，增加了用户类型参数
 *
 * @author zwq
 * @see org.apache.shiro.authc.UsernamePasswordToken
 */
public class AlipayUserIdToken implements HostAuthenticationToken {

    /**
     * 用户名
     */
    private String aliUserId;

    /**
     * 主机名称或ip
     */
    private String host;

    public AlipayUserIdToken() {

    }

    /**
     * 构造方法
     *
     * @param aliUserId  aliUserId
     * @param rememberMe 是否记住我
     * @param host       主机或ip
     */
    public AlipayUserIdToken(final String aliUserId, final String host) {
        this.aliUserId = aliUserId;
        this.host = host;
    }

    public String getAliUserId() {
        return aliUserId;
    }

    public void setAliUserId(String aliUserId) {
        this.aliUserId = aliUserId;
    }

    /**
     * Simply returns {@link #getAliUserId() getAliUserId()}.
     *
     * @return the {@link #getAliUserId() username}.
     * @see org.apache.shiro.authc.AuthenticationToken#getPrincipal()
     */
    @Override
    public Object getPrincipal() {
        return getAliUserId();
    }

    /**
     * Returns the {@link #getAliUserId() password} char array.
     *
     * @return the {@link #getAliUserId() password} char array.
     * @see org.apache.shiro.authc.AuthenticationToken#getCredentials()
     */
    @Override
    public Object getCredentials() {
        return getAliUserId();
    }

    @Override
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 清除数据
     * 密码如果不为空，设置成0x00
     */
    public void clear() {
        this.aliUserId = null;
        this.host = null;
    }

    /**
     * 重写toString方法
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(" - ");
        sb.append(aliUserId);
        if (host != null) {
            sb.append(" (").append(host).append(")");
        }
        return sb.toString();
    }
}


