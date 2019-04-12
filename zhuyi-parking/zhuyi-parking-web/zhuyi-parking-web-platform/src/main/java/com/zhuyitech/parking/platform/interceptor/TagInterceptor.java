package com.zhuyitech.parking.platform.interceptor;

import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.session.SessionParkingInfo;
import com.zoeeasy.cloud.pms.park.ParkingQrcodeService;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingQrcodeGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingQrcodeResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器，根据noncestr得到parkingId
 *
 * @author zwq
 */
@Component
@Slf4j
public class TagInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ParkingQrcodeService parkingQrcodeService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        String noncestr = request.getHeader("noncestr");
        log.error("TagInterceptor被加载" + noncestr);

        if (parkingQrcodeService == null) {//解决service为null无法注入问题

            BeanFactory factory = WebApplicationContextUtils
                    .getRequiredWebApplicationContext(request.getServletContext());
            parkingQrcodeService = (ParkingQrcodeService) factory
                    .getBean("parkingQrcodeService");
        }

        if (null != noncestr && null != parkingQrcodeService) {
            ParkingQrcodeGetRequestDto parkingQrcodeGetRequestDto = new ParkingQrcodeGetRequestDto();
            parkingQrcodeGetRequestDto.setNoncestr(noncestr);
            ObjectResultDto<ParkingQrcodeResultDto> qrcodeResultDto = parkingQrcodeService.getParkingQrcode(parkingQrcodeGetRequestDto);
            if (qrcodeResultDto.isSuccess() && null != qrcodeResultDto.getData()) {
                ParkingQrcodeResultDto parkingQrcodeResultDto = qrcodeResultDto.getData();
                SessionParkingInfo sessionParkingInfo = new SessionParkingInfo();
                sessionParkingInfo.setParkingId(parkingQrcodeResultDto.getParkingId());
                FundamentalRequestContext.getFundamentalRequestContext().setCurrentParking(sessionParkingInfo);
            }
        }
        return true;
    }
}
