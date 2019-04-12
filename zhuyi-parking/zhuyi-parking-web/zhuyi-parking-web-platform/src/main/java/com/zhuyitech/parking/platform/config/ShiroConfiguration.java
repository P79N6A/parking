package com.zhuyitech.parking.platform.config;

import com.zhuyitech.parking.shiro.realm.*;
import com.zhuyitech.parking.shiro.subject.JwtDefaultSubjectFactory;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;

/**
 * Shiro配置
 *
 * @author walkman
 */
@Configuration
public class ShiroConfiguration {

    /**
     * @return
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(2);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    @Bean("simpleCredentialsMatcher")
    public SimpleCredentialsMatcher simpleCredentialsMatcher() {
        SimpleCredentialsMatcher credentialsMatcher = new SimpleCredentialsMatcher();
        return credentialsMatcher;
    }

    @Bean("userAuthorizingRealm")
    public UserAuthorizingRealm userAuthorizingRealm() {
        UserAuthorizingRealm userAuthorizingRealm = new UserAuthorizingRealm();
        userAuthorizingRealm.setName("userAuthorizingRealm");
        userAuthorizingRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userAuthorizingRealm;
    }

    @Bean("weixinUserAuthorizingRealm")
    public WeixinUserAuthorizingRealm weixinUserAuthorizingRealm() {
        WeixinUserAuthorizingRealm userAuthorizingRealm = new WeixinUserAuthorizingRealm();
        userAuthorizingRealm.setName("weixinUserAuthorizingRealm");
        userAuthorizingRealm.setCredentialsMatcher(simpleCredentialsMatcher());
        return userAuthorizingRealm;
    }

    @Bean("alipayUserAuthorizingRealm")
    public AlipayUserAuthorizingRealm alipayUserAuthorizingRealm() {
        AlipayUserAuthorizingRealm userAuthorizingRealm = new AlipayUserAuthorizingRealm();
        userAuthorizingRealm.setName("alipayUserAuthorizingRealm");
        userAuthorizingRealm.setCredentialsMatcher(simpleCredentialsMatcher());
        return userAuthorizingRealm;
    }

    @Bean("verifyCodeAuthorizingRealm")
    public VerifyCodeAuthorizingRealm verifyCodeAuthorizingRealm() {
        VerifyCodeAuthorizingRealm verifyCodeAuthorizingRealm = new VerifyCodeAuthorizingRealm();
        verifyCodeAuthorizingRealm.setName("verifyCodeAuthorizingRealm");
        verifyCodeAuthorizingRealm.setCredentialsMatcher(simpleCredentialsMatcher());
        return verifyCodeAuthorizingRealm;
    }

    @Bean("jwtAuthorizingRealm")
    public JwtAuthorizingRealm jwtAuthorizingRealm() {
        JwtAuthorizingRealm jwtAuthorizingRealm = new JwtAuthorizingRealm();
        jwtAuthorizingRealm.setName("jwtAuthorizingRealm");
        jwtAuthorizingRealm.setCredentialsMatcher(simpleCredentialsMatcher());
        return jwtAuthorizingRealm;
    }

    /**
     * 凭证匹配器
     *
     * @return
     */
    @Bean("modularRealmAuthenticator")
    public ModularRealmAuthenticator modularRealmAuthenticator() {
        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        List<Realm> realmList = new ArrayList<>();
        realmList.add(userAuthorizingRealm());
        realmList.add(weixinUserAuthorizingRealm());
        realmList.add(alipayUserAuthorizingRealm());
        realmList.add(verifyCodeAuthorizingRealm());
        realmList.add(jwtAuthorizingRealm());
        modularRealmAuthenticator.setRealms(realmList);
        return modularRealmAuthenticator;
    }

    /**
     * 权限匹配器
     *
     * @return
     */
    @Bean("modularRealmAuthorizer")
    public ModularRealmAuthorizer modularRealmAuthorizer() {
        ModularRealmAuthorizer modularRealmAuthenticator = new ModularRealmAuthorizer();
        List<Realm> realmList = new ArrayList<>();
        realmList.add(userAuthorizingRealm());
        realmList.add(weixinUserAuthorizingRealm());
        realmList.add(alipayUserAuthorizingRealm());
        realmList.add(verifyCodeAuthorizingRealm());
        realmList.add(jwtAuthorizingRealm());
        modularRealmAuthenticator.setRealms(realmList);
        return modularRealmAuthenticator;
    }

    /**
     * 设置超会话管理
     *
     * @return
     */
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
        sessionManager.setGlobalSessionTimeout(-10000L);
        sessionManager.setSessionValidationSchedulerEnabled(false);
        sessionManager.setDeleteInvalidSessions(false);
        sessionManager.setSessionIdCookieEnabled(false);
        return sessionManager;
    }

    @Bean
    public SubjectDAO subjectDAO() {
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator);
        return subjectDAO;
    }


    @Bean(name = "securityManager")
    public SecurityManager securityManager() {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(modularRealmAuthenticator());
        securityManager.setAuthorizer(modularRealmAuthorizer());
        securityManager.setSessionManager(sessionManager());
        //用户授权/认证信息Cache, 采用Redis 缓存
//        securityManager.setCacheManager(jedisCacheManager());
        // 指定 SubjectFactory
        securityManager.setSubjectDAO(subjectDAO());
        //注入记住我管理器
        securityManager.setSubjectFactory(subjectFactory());
        return securityManager;
    }

    /**
     * subject工厂管理器.
     *
     * @return
     */
    @Bean
    public DefaultWebSubjectFactory subjectFactory() {
        JwtDefaultSubjectFactory subjectFactory = new JwtDefaultSubjectFactory();
        return subjectFactory;
    }

    /**
     * 过滤器配置
     *
     * @return
     */
    @Bean("delegatingFilterProxy")
    public FilterRegistrationBean delegatingFilterProxy() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    /**
     * ShiroFilterFactoryBean
     *
     * @param securityManager securityManager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        // 添加casFilter到shiroFilter中
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * * 自动代理所有的advisor:
     * * 由Advisor决定对哪些类的方法进行AOP代理
     *
     * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * 开启shiro aop注解支持
     * 使用代理方式;所以需要开启代码支持
     *
     * @param securityManager securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
