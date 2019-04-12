package com.zhuyitech.parking.shiro.authc;

import org.apache.shiro.authc.RememberMeAuthenticationToken;

/**
 * @author walkman
 */
public class OAuth2AuthenticationToken implements RememberMeAuthenticationToken {

    private static final long serialVersionUID = 8587854556973099598L;

    /**
     * the service access_token
     */
    private String accessToken;

    private String resourceId;

    /**
     * the user identifier, username
     */
    private String username;

    /**
     * is the user in a remember me mode ?
     */
    private boolean rememberMe = false;

    public OAuth2AuthenticationToken(String accessToken, String resourceId) {
        this.accessToken = accessToken;
        this.resourceId = resourceId;
    }

    public OAuth2AuthenticationToken(String accessToken, String resourceId, boolean rememberMe) {
        this.accessToken = accessToken;
        this.resourceId = resourceId;
        this.rememberMe = rememberMe;
    }

    @Override
    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return accessToken;
    }
}
