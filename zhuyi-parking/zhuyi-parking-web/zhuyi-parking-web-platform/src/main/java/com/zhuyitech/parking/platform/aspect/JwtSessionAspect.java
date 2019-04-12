package com.zhuyitech.parking.platform.aspect;

import com.scapegoat.infrastructure.api.enums.ResultStatus;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ClientIdentityDto;
import com.scapegoat.infrastructure.core.dto.result.ISessionDto;
import com.scapegoat.infrastructure.core.session.SessionIdentity;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.session.JwtSessionHandler;
import com.scapegoat.infrastructure.session.SessionProcessor;
import com.scapegoat.infrastructure.session.UniversalSessionProcessor;
import com.scapegoat.infrastructure.session.aspect.JwtAccessTokenAspect;
import com.scapegoat.infrastructure.web.utils.ActionUtils;
import com.zhuyitech.parking.shiro.ShiroUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 拦截Session参数，注入登录用户信息
 *
 * @author walkman
 */
public class JwtSessionAspect {

    private static final Logger LOG = LoggerFactory.getLogger(JwtSessionAspect.class);

    public JwtSessionAspect() {
        LOG.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + JwtSessionAspect.class.getName() + " loaded");
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAccessTokenAspect.class);

    /**
     * 用于处理用户会话，使用者可以自定义注入
     */
    private SessionProcessor sessionProcessor;

    public SessionProcessor getSessionProcessor() {
        if (sessionProcessor == null) {
            sessionProcessor = new UniversalSessionProcessor(new JwtSessionHandler());
        }
        return sessionProcessor;
    }

    public void setSessionProcessor(SessionProcessor sessionProcessor) {
        this.sessionProcessor = sessionProcessor;
    }

    /**
     * @param pjp pjp
     * @return
     * @throws Throwable Throwable
     */
    public Object token(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return pjp.proceed();
        }
        HttpServletRequest request = requestAttributes.getRequest();
        ISessionDto sessionParameter = null;
        for (Object arg : args) {
            if (arg instanceof ClientIdentityDto) {
                ClientIdentityDto baseParameter = (ClientIdentityDto) arg;
                if (StringUtils.isEmpty(baseParameter.getClientIp())) {
                    if (request != null) {
                        ((ClientIdentityDto) arg).setClientIp(ActionUtils.getIpAddress(request));
                    }
                }
            }
            if (arg instanceof ISessionDto) {
                //这里假设参数中只有一个会话参数
                sessionParameter = (ISessionDto) arg;
                break;
            }
        }

        if (sessionParameter != null) {
            //防止前端注入，强制覆盖
            SessionIdentity sessionIdentity = null;
            if (ShiroUtils.isLogin()) {
                sessionIdentity = ShiroUtils.getPrincipal();
            } else {
                String accessToken = JwtSessionHandler.getAccessToken(request);
                if (StringUtils.isEmpty(accessToken)) {
                    throw new BusinessException(ResultStatus.USER_NOT_LOGIN.getCode(), ResultStatus.USER_NOT_LOGIN.getMessage());
                }
                //获取会话
                sessionIdentity = getSessionProcessor().process(sessionParameter, accessToken);
            }
            //获取会话
            if (sessionIdentity == null) {
                throw new BusinessException(ResultStatus.USER_NOT_LOGIN.getCode(),
                        ResultStatus.USER_NOT_LOGIN.getMessage());
            }
            //设置会话对象
            sessionParameter.setSessionIdentity(sessionIdentity);
        }
        return pjp.proceed();
    }
}
