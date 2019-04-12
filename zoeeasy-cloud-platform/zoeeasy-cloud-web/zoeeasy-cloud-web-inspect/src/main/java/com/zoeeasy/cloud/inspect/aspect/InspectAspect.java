package com.zoeeasy.cloud.inspect.aspect;

import com.alibaba.fastjson.JSON;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.session.SessionParkingInfo;
import com.zoeeasy.cloud.inspect.utils.HttpContextUtils;
import com.zoeeasy.cloud.inspect.utils.ParkingIdCacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author AkeemSuper
 * @date 2018/11/21 0021
 */
@Aspect
@Component
@Slf4j
public class InspectAspect {

    public InspectAspect() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + InspectAspect.class.getName() + " loaded");
    }

    @Pointcut("execution(* com.zoeeasy.cloud.inspect.controller.inspect.*.*(..))")
    public void parkingIdCut() {
    }

    @Before("parkingIdCut()")
    public void setParkingId() {
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String parkingIdKey = request.getHeader(ParkingIdCacheUtils.INSPECT_PARKING);
        if (StringUtils.isNotEmpty(parkingIdKey)) {
            String parkingIdByRedis = ParkingIdCacheUtils.getParkingIdByRedis(parkingIdKey);
            if (StringUtils.isNotEmpty(parkingIdByRedis)) {
                SessionParkingInfo sessionParkingInfo = JSON.parseObject(parkingIdByRedis, SessionParkingInfo.class);
                FundamentalRequestContext.getFundamentalRequestContext().setCurrentParking(sessionParkingInfo);
            }
        }
    }

}
