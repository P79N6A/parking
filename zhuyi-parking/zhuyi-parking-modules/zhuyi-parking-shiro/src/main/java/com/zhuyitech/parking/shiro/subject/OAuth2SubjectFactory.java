package com.zhuyitech.parking.shiro.subject;

import com.zhuyitech.parking.shiro.authc.OAuth2AuthenticationToken;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;


/**
 * @author walkman
 */
public class OAuth2SubjectFactory extends DefaultWebSubjectFactory {

    public OAuth2SubjectFactory() {

    }

    @Override
    public Subject createSubject(SubjectContext context) {
        boolean authenticated = context.isAuthenticated();
        if (authenticated) {
            AuthenticationToken token = context.getAuthenticationToken();
            if (token instanceof OAuth2AuthenticationToken) {
                OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) token;
                if (oAuth2AuthenticationToken.isRememberMe()) {
                    context.setAuthenticated(false);
                    context.setSessionCreationEnabled(false);
                }
            }
        }
        return super.createSubject(context);
    }


}
