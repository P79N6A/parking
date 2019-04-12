package com.zhuyitech.parking.shiro.authc;

import org.apache.shiro.authc.AuthenticationToken;


/**
 * JSON WEB Token AuthenticationToken
 *
 * @author walkman
 * @see org.apache.shiro.authc.AuthenticationToken
 */
public class JwtAuthenticationToken implements AuthenticationToken {

    private static final long serialVersionUID = -8000346805338984862L;

    private String username;
    private String jwtToken;
    private String clientId;

    private Integer terminateType;

    public JwtAuthenticationToken(String username, String jwtToken, String clientId, Integer terminateType) {
        this.username = username;
        this.jwtToken = jwtToken;
        this.clientId = clientId;
        this.terminateType = terminateType;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return jwtToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getTerminateType() {
        return terminateType;
    }

    public void setTerminateType(Integer terminateType) {
        this.terminateType = terminateType;
    }

}
