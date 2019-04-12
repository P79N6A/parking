package com.zoeeasy.cloud.open.interceptor;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.client.OpenClient;
import com.scapegoat.infrastructure.core.client.OpenClientProvider;
import com.scapegoat.infrastructure.core.enums.ResultStatusCode;
import com.scapegoat.infrastructure.web.base.FundamentalServletRequestWrapper;
import com.scapegoat.infrastructure.web.constant.Constants;
import com.scapegoat.infrastructure.web.utils.CommonUtils;
import com.scapegoat.infrastructure.web.utils.SigningUtility;
import io.swagger.models.HttpMethod;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class MyApiSignedHandlerInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(MyApiSignedHandlerInterceptor.class);
    private OpenClientProvider openClientProvider;

    public static final Long REQUEST_TIME_SPAN = 60*24L;

    public MyApiSignedHandlerInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        if (HttpMethod.POST.name().equals(request.getMethod()) && SigningUtility.needCheckSign(method)) {
            Class<?> returnType = method.getReturnType();
            FundamentalServletRequestWrapper servletRequestWrapper = new FundamentalServletRequestWrapper(request);
            String requestBody = servletRequestWrapper.getRequestBody(request);
            if (StringUtils.isEmpty(requestBody)) {
                CommonUtils.returnResult(returnType, response, ResultStatusCode.PARAMETER_ERROR.getCode(), ResultStatusCode.PARAMETER_ERROR.getMessage());
                return false;
            } else {
                JSONObject jsonObject = JSONObject.parseObject(requestBody, new Feature[]{Feature.OrderedField});
                String accessKey = jsonObject.getString("accessKey");
                String signature = jsonObject.getString("signature");
                Long requestTimestamp = jsonObject.getLong("timestamp");
                if (!StringUtils.isBlank(accessKey) && !StringUtils.isBlank(signature) && requestTimestamp != null) {
                    Long minute = Math.abs((System.currentTimeMillis() - requestTimestamp) / Constants.MILLISECOND_PER_MINUTE);
                    if (minute.compareTo(REQUEST_TIME_SPAN) > 0) {
                        CommonUtils.returnResult(returnType, response, ResultStatusCode.TIMESTAMP_ERROR.getCode(), ResultStatusCode.TIMESTAMP_ERROR.getMessage());
                        return false;
                    } else if (StringUtils.isEmpty(signature)) {
                        CommonUtils.returnResult(returnType, response, ResultStatusCode.SIGNATURE_ERROR.getCode(), ResultStatusCode.SIGNATURE_ERROR.getMessage());
                        return false;
                    } else {
                        OpenClient openClient = this.openClientProvider.loadOpenClient(accessKey);
                        if (openClient == null) {
                            CommonUtils.returnResult(returnType, response, ResultStatusCode.ACCESS_KEY_ERROR.getCode(), ResultStatusCode.ACCESS_KEY_ERROR.getMessage());
                            return false;
                        } else {
                            String realSign = SigningUtility.createSign(jsonObject, openClient.getSecretKey());
                            if (!realSign.equals(signature)) {
                                CommonUtils.returnResult(returnType, response, ResultStatusCode.SIGNATURE_ERROR.getCode(), ResultStatusCode.SIGNATURE_ERROR.getMessage());
                                return false;
                            } else {
                                return super.preHandle(request, response, handler);
                            }
                        }
                    }
                } else {
                    if (log.isDebugEnabled()) {
                        log.debug(String.format("请求参数不完整[accessKey:%s][timestamp:%s][signature:%s]", signature, requestTimestamp, signature));
                    }

                    CommonUtils.returnResult(returnType, response, ResultStatusCode.PARAMETER_ERROR.getCode(), ResultStatusCode.PARAMETER_ERROR.getMessage());
                    return false;
                }
            }
        } else {
            return true;
        }
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(httpServletRequest, httpServletResponse, handler, modelAndView);
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception ex) throws Exception {
        super.afterCompletion(httpServletRequest, httpServletResponse, handler, ex);
    }

    public OpenClientProvider getOpenClientProvider() {
        return this.openClientProvider;
    }

    public void setOpenClientProvider(OpenClientProvider openClientProvider) {
        this.openClientProvider = openClientProvider;
    }
}
