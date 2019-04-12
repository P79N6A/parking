package com.zhuyitech.parking.shiro.authc;

import org.apache.shiro.authc.HostAuthenticationToken;


/**
 * 参考org.apache.shiro.authc.UsernamePasswordToken，增加了用户类型参数
 *
 * @author walkman
 * @see org.apache.shiro.authc.UsernamePasswordToken
 */
public class WeixinUnionIdToken implements HostAuthenticationToken {

    /**
     * 用户名
     */
    private String unionId;

    /**
     * 主机名称或ip
     */
    private String host;


    public WeixinUnionIdToken() {

    }

    /**
     * 构造方法
     *
     * @param unionId 微信OpenID
     * @param host    主机或ip
     */
    public WeixinUnionIdToken(final String unionId,
                              final boolean rememberMe, final String host) {
        this.unionId = unionId;
        this.host = host;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    /**
     * Simply returns {@link #getUnionId() getOpenId()}.
     *
     * @return the {@link #getUnionId() username}.
     * @see org.apache.shiro.authc.AuthenticationToken#getPrincipal()
     */
    @Override
    public Object getPrincipal() {
        return getUnionId();
    }

    /**
     * Returns the {@link #getUnionId() password} char array.
     *
     * @return the {@link #getUnionId() password} char array.
     * @see org.apache.shiro.authc.AuthenticationToken#getCredentials()
     */
    @Override
    public Object getCredentials() {
        return getUnionId();
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
        this.unionId = null;
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
        sb.append(unionId);
        if (host != null) {
            sb.append(" (").append(host).append(")");
        }
        return sb.toString();
    }
}


