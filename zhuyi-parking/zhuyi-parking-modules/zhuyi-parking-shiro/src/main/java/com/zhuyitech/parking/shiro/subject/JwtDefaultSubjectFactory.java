package com.zhuyitech.parking.shiro.subject;

import com.zhuyitech.parking.shiro.authc.JwtAuthenticationToken;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * JwtDefaultSubjectFactory
 *
 * @author walkman
 */
public class JwtDefaultSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {

        boolean authenticated = context.isAuthenticated();
        if (authenticated) {
            AuthenticationToken token = context.getAuthenticationToken();
            if (token instanceof JwtAuthenticationToken) {
                //不创建session
                JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) token;
                context.setSessionCreationEnabled(false);
                Subject ct = super.createSubject(context);
                ct.getSession(false);
            }
        }
        return super.createSubject(context);
    }
}
