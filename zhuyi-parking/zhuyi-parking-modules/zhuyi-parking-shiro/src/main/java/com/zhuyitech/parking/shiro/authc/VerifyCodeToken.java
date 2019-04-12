package com.zhuyitech.parking.shiro.authc;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

/**
 * 参考org.apache.shiro.authc.UsernamePasswordToken，增加了用户类型参数
 *
 * @author zwq
 * @see org.apache.shiro.authc.UsernamePasswordToken
 */
public class VerifyCodeToken implements HostAuthenticationToken {

    /**
     * 用户名
     */
    private String username;

    /**
     * 主机名称或ip
     */
    private String host;

    public VerifyCodeToken() {

    }

    /**
     * 构造方法
     *
     * @param username     userId
     * @param rememberMe 是否记住我
     * @param host       主机或ip
     */
    public VerifyCodeToken(final String username, final String host) {
        this.username = username;
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Simply returns {@link #getUsername() getAliUserId()}.
     *
     * @return the {@link #getUsername() username}.
     * @see org.apache.shiro.authc.AuthenticationToken#getPrincipal()
     */
    @Override
    public Object getPrincipal() {
        return getUsername();
    }

    /**
     * Returns the {@link #getUsername() password} char array.
     *
     * @return the {@link #getUsername() password} char array.
     * @see org.apache.shiro.authc.AuthenticationToken#getCredentials()
     */
    @Override
    public Object getCredentials() {
        return getUsername();
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
        this.username = null;
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
        sb.append(username);
        if (host != null) {
            sb.append(" (").append(host).append(")");
        }
        return sb.toString();
    }
}


