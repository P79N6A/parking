package com.zhuyitech.parking.data.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.core.dto.request.PagedResultRequestDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.parking.data.domain.ParkingInfo;
import com.zhuyitech.parking.data.domain.ParkingLotInfo;
import com.zhuyitech.parking.data.domain.ParkingRecord;
import com.zhuyitech.parking.data.domain.PassingVehicleRecord;
import com.zhuyitech.parking.data.dto.result.ParkingRecordResultDto;
import com.zhuyitech.parking.data.service.ParkingInfoCrudService;
import com.zhuyitech.parking.data.service.ParkingLotInfoCrudService;
import com.zhuyitech.parking.data.service.ParkingRecordCrudService;
import com.zhuyitech.parking.data.service.PassingVehicleRecordCrudService;
import com.zhuyitech.parking.data.service.api.ParkingRecordService;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 平台停车记录服务
 * @Autor: AkeemSuper
 * @Date: 2018/3/1 0001
 */
@Service("parkingRecordService")
public class ParkingRecordServiceImpl implements ParkingRecordService {

    private static final Logger LOG = LoggerFactory.getLogger(ParkingRecordServiceImpl.class);

    @Autowired
    private ParkingRecordCrudService parkingRecordCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PassingVehicleRecordCrudService passingVehicleRecordCrudService;

    @Override
    public PagedResultDto<ParkingRecordResultDto> getParkingRecordPageList(PagedResultRequestDto requestDto) {
        PagedResultDto<ParkingRecordResultDto> resultDto = new PagedResultDto<>();
        try {
            EntityWrapper<ParkingRecord> entityWrapper = new EntityWrapper<>();
            entityWrapper.orderBy("id");
            Page<ParkingRecord> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingRecord> parkingRecordPage = parkingRecordCrudService.selectPage(page, entityWrapper);
            if (CollectionUtils.isNotEmpty(parkingRecordPage.getRecords())) {
                List<ParkingRecord> records = parkingRecordPage.getRecords();
                List<ParkingRecordResultDto> parkingResultDtoList = new ArrayList<>();
                for (ParkingRecord parkingRecord : records) {
                    ParkingRecordResultDto parkingRecordResultDto = modelMapper.map(
                            parkingRecord, ParkingRecordResultDto.class);
                    parkingRecordResultDto.setThirdParkingRecordId(parkingRecord.getHikParkingRecordId());
                    Long parkingId = parkingRecordResultDto.getParkingId();
                    ParkingInfo parkingInfo = parkingInfoCrudService.selectById(parkingId);
                    if (parkingInfo != null) {
                        parkingRecordResultDto.setParkingCode(parkingInfo.getCode());
                        parkingRecordResultDto.setParkingName(parkingInfo.getName());
                    }
                    ParkingLotInfo parkingLotInfo = parkingLotInfoCrudService.selectById(
                            parkingRecordResultDto.getParkingLotId());
                    if (parkingLotInfo != null) {
                        parkingRecordResultDto.setParkingLotCode(parkingLotInfo.getCode());
                        parkingRecordResultDto.setParkingLotNumber(parkingLotInfo.getCode());
                    }
                    PassingVehicleRecord intoRecord = passingVehicleRecordCrudService.selectById(parkingRecord.getIntoRecordId());
                    if (intoRecord != null) {
                        parkingRecordResultDto.setIntoRecordNo(intoRecord.getPassingNo());
                    }
                    PassingVehicleRecord outRecord = passingVehicleRecordCrudService.selectById(parkingRecord.getOutRecordId());
                    if (outRecord != null) {
                        parkingRecordResultDto.setOutRecordNo(outRecord.getPassingNo());
                    }
                    parkingRecordResultDto.setCarType(parkingRecord.getCarStyle());
                    parkingResultDtoList.add(parkingRecordResultDto);
                }
                resultDto.setPageNo(parkingRecordPage.getCurrent());
                resultDto.setPageSize(parkingRecordPage.getSize());
                resultDto.setTotalCount(parkingRecordPage.getTotal());
                resultDto.setItems(parkingResultDtoList);
            }
            resultDto.success();
        } catch (Exception e) {
            LOG.error("分页查询平台停车记录失败", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

}
