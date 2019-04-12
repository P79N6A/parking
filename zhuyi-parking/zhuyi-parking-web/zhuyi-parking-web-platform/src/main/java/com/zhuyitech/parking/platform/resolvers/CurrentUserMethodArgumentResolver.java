package com.zhuyitech.parking.platform.resolvers;

import com.google.common.collect.Maps;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.session.SessionIdentity;
import com.zhuyitech.parking.common.annotation.CurrentUser;
import com.zhuyitech.parking.shiro.ShiroUtils;
import com.zhuyitech.parking.ucc.user.dto.UserGetRequestDto;
import com.zhuyitech.parking.ucc.dto.result.user.UserResultDto;
import com.zhuyitech.parking.ucc.service.api.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;

/**
 * 增加方法注入，将含有CurrentUser注解的方法参数注入当前登录用户
 *
 * @author walkman
 * @date 2017/12/28
 * @see com.zhuyitech.parking.common.annotation.CurrentUser
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        //如果参数类型是User并且有CurrentUser注解则支持
        if (SessionIdentity.class.isAssignableFrom(parameter.getParameterType()) &&
                parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //取出鉴权时存入的登录用户Id
        //从数据库中查询并返回
        String username = ShiroUtils.getPrincipal().getUsername();
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        UserGetRequestDto requestDto = new UserGetRequestDto();
        requestDto.setUsername(username);
        ObjectResultDto<UserResultDto> useResultDto = userService.getUser(requestDto);

        UserResultDto user = useResultDto.getData();
        Object o = BeanUtils.instantiate(parameter.getParameterType());
        SessionIdentity sessionInfo;
        if (o instanceof SessionIdentity) {

            sessionInfo = (SessionIdentity) o;
            sessionInfo.setUserId(user.getId());
            sessionInfo.setUuid(user.getUuid());
//            sessionInfo.setUsername(user.getUsername());
//            sessionInfo.setPhoneNumber(user.getPhoneNumber());
//            sessionInfo.setEmailAddress(user.getEmailAddress());
            sessionInfo.setUserType(user.getUserType());
        } else {
            sessionInfo = new SessionIdentity();
        }
        String objName = parameter.getParameterName() + ".";
        StringBuffer tmp;
        String[] val;
        Field[] frr = parameter.getParameterType().getDeclaredFields();
        for (Iterator itr = webRequest.getParameterNames(); itr.hasNext(); ) {
            tmp = new StringBuffer();
            if (tmp.indexOf(objName) < 0) {
                continue;
            }
            for (int i = 0; i < frr.length; i++) {
                frr[i].setAccessible(true);
                if (tmp.toString().equals(objName + frr[i].getName())) {
                    val = webRequest.getParameterValues(tmp.toString());
                    frr[i].set(o, val[0]);
                }
            }
        }
        Object obj = BeanUtils.instantiate(parameter.getParameterType());
        BeanWrapper wrapper = PropertyAccessorFactory
                .forBeanPropertyAccess(obj);
        Field[] fields = parameter.getParameterType().getDeclaredFields();
        Map<String, Field> fieldMap = Maps.newHashMap();
        for (Field field : fields) {
            fieldMap.put(field.getName(), field);
        }
        for (Iterator<String> paramNames = webRequest.getParameterNames(); paramNames.hasNext(); ) {
            String paramName = paramNames.next();
            Object paramValue = webRequest.getParameter(paramName);
            wrapper.setPropertyValue(paramName, paramValue);
        }
        return sessionInfo;
    }

    private Class<?> getSessionIdentityClass(SessionIdentity sessionParameter) {

        Class<?> sessionIdentityCls = null;
        Class<?> clazz = sessionParameter.getClass();
        if (clazz != SessionIdentity.class) {
            //找出父类中的泛型 即实际sessionIdentity字段的类型
            while (!(clazz.getGenericSuperclass() instanceof ParameterizedType)
                    && clazz != SessionIdentity.class && clazz != Object.class) {
                clazz = clazz.getSuperclass();
            }
            if (clazz == SessionIdentity.class) {
                return SessionIdentity.class;
            }
            //获取当前new对象的泛型的父类类型
            ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
            Type type = parameterizedType.getActualTypeArguments()[0];
            if (type instanceof Class) {
                sessionIdentityCls = (Class<?>) type;
            } else {
                return SessionIdentity.class;
            }
        } else {
            try {
                sessionIdentityCls = clazz.getDeclaredField("sessionIdentity").getType();
            } catch (Exception e) {
//                logger.error(e.getMessage(), e);
            }
        }
        return sessionIdentityCls;
    }
}
