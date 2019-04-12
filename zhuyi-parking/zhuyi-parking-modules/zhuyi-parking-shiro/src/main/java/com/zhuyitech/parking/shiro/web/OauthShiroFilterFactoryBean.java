package com.zhuyitech.parking.shiro.web;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.PatternMatcher;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;

/**
 * @author walkman
 */
public class OauthShiroFilterFactoryBean extends ShiroFilterFactoryBean {

    private PatternMatcher patternMatcher;

    public OauthShiroFilterFactoryBean(PatternMatcher patternMatcher) {
        this.patternMatcher = patternMatcher;
    }

    @Override
    protected AbstractShiroFilter createInstance() throws Exception {
        AbstractShiroFilter instance = super.createInstance();
        FilterChainResolver filterChainResolver = instance.getFilterChainResolver();
        if (patternMatcher != null && filterChainResolver instanceof PathMatchingFilterChainResolver) {
            PathMatchingFilterChainResolver resolver = (PathMatchingFilterChainResolver) filterChainResolver;
            resolver.setPathMatcher(patternMatcher);
        }
        return instance;
    }
}
