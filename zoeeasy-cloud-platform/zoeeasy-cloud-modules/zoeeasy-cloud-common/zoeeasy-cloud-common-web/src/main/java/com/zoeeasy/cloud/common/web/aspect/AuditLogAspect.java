package com.zoeeasy.cloud.common.web.aspect;


import com.alibaba.fastjson.JSON;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.session.SessionIdentity;
import com.scapegoat.infrastructure.web.utils.ActionUtils;
import com.zoeeasy.cloud.common.web.utils.ServletUtils;
import com.zoeeasy.cloud.core.annotation.AuditLog;
import com.zoeeasy.cloud.core.async.AsyncInvokeService;
import com.zoeeasy.cloud.core.async.InvokeRunnable;
import com.zoeeasy.cloud.core.enums.BusinessStatus;
import com.zoeeasy.cloud.core.model.AuditLogModel;
import com.zoeeasy.cloud.core.support.AuditLogRepository;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 操作日志记录处理
 *
 * @author walkman
 */
@Aspect
@Component
@Slf4j
public class AuditLogAspect {

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.zoeeasy.cloud.core.annotation.AuditLog) || @within(com.zoeeasy.cloud.core.annotation.AuditLog)")
    public void logPointCut() {

    }

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private AsyncInvokeService asyncInvokeService;

    /**
     * 前置通知 用于拦截操作
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        handleLog(joinPoint, null);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e) {
        try {
            // 获得注解
            AuditLog controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }
            // 获取当前的用户
            SessionIdentity currentUser = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity();

            // *========数据库日志=========*//
            AuditLogModel auditLogModel = new AuditLogModel();

            // 请求的地址
            String ip = ActionUtils.getIpAddress(ServletUtils.getRequest());
            auditLogModel.setRemoteAddress(ip);
            auditLogModel.setRequestUrl(ServletUtils.getRequest().getRequestURI());
            auditLogModel.setAccount(currentUser.getAccount());
            if (currentUser != null) {
                auditLogModel.setTenantId(currentUser.getTenantId());
                auditLogModel.setUserId(currentUser.getUserId());
            }
            //设置浏览器信息
            UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
            // 获取客户端操作系统
            String os = userAgent.getOperatingSystem().getName();
            // 获取客户端浏览器
            String browser = userAgent.getBrowser().getName();
            auditLogModel.setUserAgent(os + "/" + browser);
            auditLogModel.setStatus(BusinessStatus.SUCCESS.ordinal());
            if (e != null) {
                auditLogModel.setStatus(BusinessStatus.FAIL.ordinal());
                auditLogModel.setException(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            auditLogModel.setMethod(className + "." + methodName + "()");
            // 处理设置注解上的参数
            getControllerMethodDescription(controllerLog, auditLogModel);
            // 保存数据库
            asyncInvokeService.submit(new InvokeRunnable() {
                @Override
                public void runSafe() {
                    auditLogRepository.saveAuditLog(auditLogModel);
                }
            });
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{},堆栈信息:{}", exp.getMessage(), exp.getStackTrace());
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log      日志
     * @param auditLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(AuditLog log, AuditLogModel auditLog) throws Exception {
        // 设置action动作
        auditLog.setType(log.businessType().getValue());
        // 设置标题
        auditLog.setTitle(log.title());
        // 设置操作人类别
        auditLog.setOperatorType(log.operatorType().getValue());
        auditLog.setContent(log.value());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(auditLog);
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param auditLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(AuditLogModel auditLog) throws Exception {
        Map<String, String[]> map = ServletUtils.getRequest().getParameterMap();
        String params = JSON.toJSONString(map);
        auditLog.setParams(StringUtils.substring(params, 0, 255));
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private AuditLog getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(AuditLog.class);
        }
        return null;
    }
}
