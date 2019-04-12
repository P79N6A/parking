package com.zoeeasy.cloud.pds.business;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.pds.domain.MagneticDetectorEntity;
import com.zoeeasy.cloud.pds.service.MagneticManagerCrudService;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.ParkingLotInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingLotListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MagneticDetectorBLL {

    @Autowired
    private ParkingLotInfoService parkingLotInfoService;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private MagneticManagerCrudService magneticManagerCrudService;

    /**
     * 获取厂商、停车场、泊位
     *
     * @param magneticDetectorEntity
     * @return
     */
    public Map<String, String> getProviderCodeParking(MagneticDetectorEntity magneticDetectorEntity) {
        ParkingGetRequestDto parkingGetRequestDto = new ParkingGetRequestDto();
        ParkingLotListGetRequestDto parkingLotListGetRequestDto = new ParkingLotListGetRequestDto();
        Map<String, String> map = new HashMap<>();
        //停车场
        parkingGetRequestDto.setId(magneticDetectorEntity.getParkingId());
        ObjectResultDto<ParkingResultDto> parking = parkingInfoService.getParking(parkingGetRequestDto);
        if (null != parking.getData()) {
            String fullName = parking.getData().getFullName();
            map.put("fullName", fullName);
        }
        //泊位
        if (0 != magneticDetectorEntity.getParkingLotId()) {
            parkingLotListGetRequestDto.setParkingId(parking.getData().getId());
            parkingLotListGetRequestDto.setId(magneticDetectorEntity.getParkingLotId());
            ListResultDto<ParkingLotResultDto> parkingLotResultDtoListResultDto = parkingLotInfoService.getParkingLotList(parkingLotListGetRequestDto);
            String number = parkingLotResultDtoListResultDto.getItems().get(0).getNumber();
            map.put("number", number);
        }
        return map;
    }
}

