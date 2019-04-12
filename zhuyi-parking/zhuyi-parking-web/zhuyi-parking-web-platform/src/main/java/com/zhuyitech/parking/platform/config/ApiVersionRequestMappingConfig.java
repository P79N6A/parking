package com.zhuyitech.parking.platform.config;


import com.scapegoat.infrastructure.web.version.ApiVersionRequestMappingHandlerMapping;
import org.springframework.boot.autoconfigure.web.WebMvcRegistrationsAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 *
 */
@Configuration
public class ApiVersionRequestMappingConfig extends WebMvcRegistrationsAdapter {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {

        RequestMappingHandlerMapping handlerMapping = new ApiVersionRequestMappingHandlerMapping();
        handlerMapping.setOrder(0);
        return handlerMapping;
    }
}


