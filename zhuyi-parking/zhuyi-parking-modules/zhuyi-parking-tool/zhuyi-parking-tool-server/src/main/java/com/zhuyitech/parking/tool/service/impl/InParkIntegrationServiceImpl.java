package com.zhuyitech.parking.tool.service.impl;

import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.session.SessionIdentity;
import com.zhuyitech.parking.inpark.request.InParkOrderListRequest;
import com.zhuyitech.parking.inpark.request.InParkParkingListRequest;
import com.zhuyitech.parking.inpark.result.InParkBaseResult;
import com.zhuyitech.parking.inpark.service.InParkService;
import com.zhuyitech.parking.tool.dto.request.inpark.InParkOrderUrlGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.inpark.InParkParkingUrlGetRequestDto;
import com.zhuyitech.parking.tool.dto.result.inpark.InParkOrderUrlResultDto;
import com.zhuyitech.parking.tool.dto.result.inpark.InParkParkingUrlResultDto;
import com.zhuyitech.parking.tool.service.api.InParkIntegrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Inpark对接集成服务
 *
 * @author walkman
 */
@Service("inParkIntegrationService")
@Slf4j
public class InParkIntegrationServiceImpl implements InParkIntegrationService {

    @Autowired
    private InParkService inParkService;

    /**
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<InParkParkingUrlResultDto> getParkingListUrl(InParkParkingUrlGetRequestDto requestDto) {
        ObjectResultDto<InParkParkingUrlResultDto> resultDto = new ObjectResultDto<>();
        try {
            SessionIdentity sessionIdentity = (SessionIdentity) requestDto.getSessionIdentity();
            if (sessionIdentity != null) {
                InParkParkingListRequest parkingListRequest = new InParkParkingListRequest();
                parkingListRequest.setTimePoint(String.valueOf(DateTimeUtils.nowTimeMillis() / 1000L));
                parkingListRequest.setLat(String.valueOf(requestDto.getLatitude()));
                parkingListRequest.setLng(String.valueOf(requestDto.getLongitude()));
                parkingListRequest.setMobile(sessionIdentity.getPhoneNumber());
                ObjectResultDto<InParkBaseResult> inParkResult = inParkService.getParkingList(parkingListRequest);
                if (inParkResult.isSuccess() && inParkResult.getData() != null) {
                    InParkParkingUrlResultDto urlResultDto = new InParkParkingUrlResultDto();
                    urlResultDto.setUrl(inParkResult.getData().getData());
                    resultDto.setData(urlResultDto);
                    resultDto.success();
                } else {
                    resultDto.makeResult(inParkResult);
                }
            } else {
                resultDto.unAuthorized();
            }
        } catch (Exception e) {
            log.error("请求InPark停车场H5页面失败,{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<InParkOrderUrlResultDto> getOrderListUrl(InParkOrderUrlGetRequestDto requestDto) {
        ObjectResultDto<InParkOrderUrlResultDto> resultDto = new ObjectResultDto<>();
        try {
            SessionIdentity sessionIdentity = (SessionIdentity) requestDto.getSessionIdentity();
            if (sessionIdentity != null) {
                InParkOrderListRequest orderListRequest = new InParkOrderListRequest();
                orderListRequest.setTimePoint(String.valueOf(DateTimeUtils.nowTimeMillis() / 1000L));
                orderListRequest.setMobile(sessionIdentity.getPhoneNumber());
                ObjectResultDto<InParkBaseResult> inParkResult = inParkService.getOrderList(orderListRequest);
                if (inParkResult.isSuccess() && inParkResult.getData() != null) {
                    InParkOrderUrlResultDto urlResultDto = new InParkOrderUrlResultDto();
                    urlResultDto.setUrl(inParkResult.getData().getData());
                    resultDto.setData(urlResultDto);
                    resultDto.success();
                } else {
                    resultDto.makeResult(inParkResult);
                }
            } else {
                resultDto.unAuthorized();
            }
        } catch (Exception e) {
            log.error("请求InPark停车场H5页面失败,{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
