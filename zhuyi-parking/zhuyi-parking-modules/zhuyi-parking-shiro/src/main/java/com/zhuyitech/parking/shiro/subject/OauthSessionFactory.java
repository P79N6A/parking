package com.zhuyitech.parking.shiro.subject;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SimpleSession;

/**
 * @author walkman
 */
public class OauthSessionFactory implements SessionFactory {

    @Override
    public Session createSession(SessionContext initData) {
        if (initData != null) {
            String host = initData.getHost();
            if (host != null) {
                String id = (String) initData.getSessionId();
                SimpleSession session = new SimpleSession(host);
                if (id != null) {
                    session.setId(id);
                    return session;
                }
            }
        }
        return new SimpleSession();
    }
}
