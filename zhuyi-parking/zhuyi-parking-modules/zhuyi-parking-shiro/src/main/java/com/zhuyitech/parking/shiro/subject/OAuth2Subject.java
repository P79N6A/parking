package com.zhuyitech.parking.shiro.subject;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.session.mgt.DefaultWebSessionContext;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author walkman
 */
public class OAuth2Subject extends WebDelegatingSubject {

    private String accessToken;

    public OAuth2Subject(PrincipalCollection principals, boolean authenticated, String host, Session session, ServletRequest request, ServletResponse response, String accessToken, SecurityManager securityManager) {
        super(principals, authenticated, host, session, request, response, securityManager);
        this.accessToken = accessToken;
    }

    public OAuth2Subject(PrincipalCollection principals, boolean authenticated, String host, Session session, boolean sessionEnabled, ServletRequest request, ServletResponse response, String accessToken, SecurityManager securityManager) {
        super(principals, authenticated, host, session, sessionEnabled, request, response, securityManager);
        this.accessToken = accessToken;
    }

    @Override
    protected SessionContext createSessionContext() {
        DefaultWebSessionContext wsc = new DefaultWebSessionContext();
        String host = this.getHost();
        if (StringUtils.hasText(host)) {
            wsc.setHost(host);
        }
        if (StringUtils.hasText(accessToken)) {
            wsc.setSessionId(accessToken);
        }
        wsc.setServletRequest(this.getServletRequest());
        wsc.setServletResponse(this.getServletResponse());
        return wsc;
    }
}
