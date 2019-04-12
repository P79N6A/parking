package com.zoeeasy.cloud.integration.service.impl;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.inspect.park.InspectParkService;
import com.zoeeasy.cloud.inspect.park.dto.request.ParkingByUserRequestDto;
import com.zoeeasy.cloud.inspect.park.dto.result.ParkingByUserResultDto;
import com.zoeeasy.cloud.inspect.platform.dto.result.ParkInspectorInfoResultDto;
import com.zoeeasy.cloud.integration.inspect.InspectParkIntegrationService;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/11/21 0021
 */
@Service("inspectParkIntegrationService")
@Slf4j
public class InspectParkIntegrationServiceImpl implements InspectParkIntegrationService {

    @Autowired
    private InspectParkService inspectParkService;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Override
    public ListResultDto<ParkingByUserResultDto> getParkingByUserId(ParkingByUserRequestDto requestDto) {
        ListResultDto<ParkingByUserResultDto> listResultDto = new ListResultDto<>();
        try {
            ListResultDto<ParkInspectorInfoResultDto> parkInspectInfoResult = inspectParkService.getParkingByUserId(requestDto);
            if (parkInspectInfoResult.isSuccess() && CollectionUtils.isNotEmpty(parkInspectInfoResult.getItems())) {
                List<ParkInspectorInfoResultDto> items = parkInspectInfoResult.getItems();
                List<ParkingByUserResultDto> parkingByUserResultDtos = new ArrayList<>();
                for (ParkInspectorInfoResultDto parkInspectorInfoResultDto : items) {
                    ParkingByUserResultDto parkingByUserResultDto = new ParkingByUserResultDto();
                    ParkingGetRequestDto parkingByIdGetRequestDto = new ParkingGetRequestDto();
                    parkingByIdGetRequestDto.setId(parkInspectorInfoResultDto.getParkingId());
                    ObjectResultDto<ParkingResultDto> parkingInfoById = parkingInfoService.getParkingInfo(parkingByIdGetRequestDto);
                    if (parkingInfoById.getData() != null) {
                        parkingByUserResultDto.setParkingId(parkInspectorInfoResultDto.getParkingId());
                        parkingByUserResultDto.setParkingName(parkingInfoById.getData().getFullName());
                    }
                    parkingByUserResultDtos.add(parkingByUserResultDto);
                }
                listResultDto.setItems(parkingByUserResultDtos);
            } else {

            }
            listResultDto.success();
        } catch (Exception e) {
            listResultDto.failed();
            log.error("通过userId获取停车场失败" + e.getMessage());
        }
        return listResultDto;
    }
}
