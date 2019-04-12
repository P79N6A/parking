package com.zoeeasy.cloud.integration.service.impl;


import com.zoeeasy.cloud.integration.mock.MockService;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.tool.misc.MiscService;
import com.zoeeasy.cloud.tool.vesta.intf.IdService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 拉取全国停车场数据
 *
 * @author wangfeng
 * @date 2018/11/23 10:11
 **/
@Service("mockService")
@Slf4j
public class MockServiceImpl implements MockService {

    @Autowired
    private MiscService miscService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ParkingInfoService parkingInfoService;

//    @Autowired
//    private PlatformProcessService platformProcessService;
//
//    @Autowired
//    private PlatformParkingOrderService platformParkingOrderService;

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @Autowired
    private IdService idService;

//    @Override
//    public ResultDto fetchNationalParkingInfo(ParkingInfoFetchRequestDto requestDto) {
//        ResultDto resultDto = new ResultDto();
//        try {
//
//            final int pageSize = 50;
//            Integer pageNumber = 1;
//            Long totalCount = 0L;
//            ParkingInfoGetRequestDto parkingInfoGetRequestDto = new ParkingInfoGetRequestDto();
//            parkingInfoGetRequestDto.setKeyword(requestDto.getKeyword());
//            parkingInfoGetRequestDto.setRegion(requestDto.getRegion());
//            parkingInfoGetRequestDto.setPageSize(pageSize);
//            PagedResultDto<ParkingInfoBaseResultDto> pagedResultDto;
//
//            do {
//
//                parkingInfoGetRequestDto.setPageNum(pageNumber);
//                pagedResultDto = miscService.fetchNationalParkingInfo(parkingInfoGetRequestDto);
//
//                if (pagedResultDto.isSuccess() && pagedResultDto.getItems() != null
//                        && !pagedResultDto.getItems().isEmpty()) {
//                    totalCount = pagedResultDto.getTotalCount();
//
//                    for (ParkingInfoBaseResultDto parkingInfoBaseResultDto : pagedResultDto.getItems()) {
//                        final ParkingInfoExistDto parkingInfoExistDto = new ParkingInfoExistDto();
//                        parkingInfoExistDto.setLatitude(parkingInfoBaseResultDto.getLatitude());
//                        parkingInfoExistDto.setLongitude(parkingInfoBaseResultDto.getLongitude());
//                        parkingInfoExistDto.setName(parkingInfoBaseResultDto.getName());
//                        final ResultDto parkingInfoExist = platformParkingInfoService.parkingInfoExist(parkingInfoExistDto);
//                        if (parkingInfoExist.isSuccess()) {
//                            log.error("{} is exist.", parkingInfoExistDto);
//                            continue;
//                        }
//                        NonTenantParkingAddRequestDto parkingInfo =
//                                modelMapper.map(parkingInfoBaseResultDto, NonTenantParkingAddRequestDto.class);
//                        parkingInfo.setCode(String.valueOf(idService.genId()));
//                        parkingInfo.setFullName(parkingInfoBaseResultDto.getName());
//                        parkingInfo.setChargeFee(ChargeFeeEnum.YES.getValue());
//                        parkingInfo.setSupportAppointment(AppointmentEnum.NO.getValue());
//                        parkingInfo.setPayMode("1");
//                        parkingInfo.setPayType("1");
//                        parkingInfo.setContactPhone(parkingInfoBaseResultDto.getTelephone());
//                        parkingInfo.setLotTotal(0);
//                        parkingInfo.setLotAvailable(0);
//                        parkingInfo.setLotFixed(0);
//                        parkingInfo.setOperationState(OperationStateEnum.AVAILABLE.getValue());
//                        parkingInfoService.addNonTenantParking(parkingInfo);
//                    }
//                }
//                pageNumber++;
//            }
//            while (pageNumber <= Math.ceil(Double.valueOf(totalCount) / (double) pageSize));
//            resultDto.success();
//        } catch (Exception e) {
//            log.error("同步全国停车场数据" + e.getMessage());
//            resultDto.failed();
//        }
//        return resultDto;
//    }

//    @Override
//    public ResultDto fetchLocalParkingInfo(List<CloudParkingInfoResultDto> requestDto) {
//        ResultDto resultDto = new ResultDto();
//        if (requestDto != null) {
//            for (CloudParkingInfoResultDto parkingInfo : requestDto) {
//
//                NonTenantParkingAddRequestDto nonTenantParkingAddRequestDto = modelMapper.map(parkingInfo, NonTenantParkingAddRequestDto.class);
//                Integer operationState = parkingInfo.getStatus() == null ?
//                        ParkingStatusEnum.NOT_ON_LINE.getValue() : parkingInfo.getStatus();
//                nonTenantParkingAddRequestDto.setOperationState(operationState);
////                if (getTenantIdByParkingInfo(nonTenantParkingAddRequestDto.get) == null) {
////                    parkingInfoService.addNonTenantParking(nonTenantParkingAddRequestDto);
////                }
//            }
//            resultDto.success();
//        } else {
//            resultDto.failed();
//        }
//        return resultDto;
//    }

//    /**
//     * @param requestDto
//     * @return
//     */
//    @Override
//    public ResultDto transferOldPassingVehicleRecord(List<PassingVehicleRecordResultDto> requestDto) {
//        ResultDto resultDto = new ResultDto();
//        if (CollectionUtil.isNotEmpty(requestDto)) {
//
//            for (PassingVehicleRecordResultDto passingVehicleRecord : requestDto) {
//                //如果是固定的tenantID，可以写死，不用查数据库
//                //因为BUG导致停车场数据迁移错误
//                Long parkingId = passingVehicleRecord.getParkingId();
//                if (parkingId != null) {
//                    parkingId = transferToCloudParkingId(parkingId);
//                }
//                final ParkingResultDto parkingInfo = getTenantIdByParkingInfo(parkingId);
//                if (parkingInfo == null) {
//                    resultDto.failed();
//                    log.error("编号为{}的停车场不存在！", passingVehicleRecord.getParkingId());
//                    continue;
//                }
//                PassingVehicleRecordCreateRequestDto recordCreateRequestDto =
//                        modelMapper.map(passingVehicleRecord, PassingVehicleRecordCreateRequestDto.class);
//                if (parkingInfo.getTenantId() != null && parkingInfo.getTenantId().compareTo(0L) > 0) {
//                    recordCreateRequestDto.setTenantId(parkingInfo.getTenantId());
//                } else {
//                    recordCreateRequestDto.setTenantId(-1L);
//                }
//                recordCreateRequestDto.setParkingId(parkingId);
//                recordCreateRequestDto.setParkingCode(parkingInfo.getCode());
//                recordCreateRequestDto.setParkingName(parkingInfo.getName());
//                recordCreateRequestDto.setThirdPassingId(passingVehicleRecord.getHikPassingId());
////                recordCreateRequestDto.setParkingLotNumber(passingVehicleRecord.getParkingLotId());
//                recordCreateRequestDto.setPlateNoExist(PlateNumberUtil.isPlateNumber(passingVehicleRecord.getPlateNumber()));
//                //过车类型
//                recordCreateRequestDto.setPassingType(passingVehicleRecord.getPassCarType());
//                //临时车
//                recordCreateRequestDto.setParkingType(6);
//                recordCreateRequestDto.setAbnormalType(0);
//                if (PlateNumberUtil.isPlateNumber(passingVehicleRecord.getPlateNumber())) {
//                    recordCreateRequestDto.setPlateNoExist(Boolean.TRUE);
//                } else {
//                    recordCreateRequestDto.setPlateNoExist(Boolean.FALSE);
//                }
//                //过车图像处理
//                if (CollectionUtil.isNotEmpty(passingVehicleRecord.getImages())) {
//                    recordCreateRequestDto.setPhotoCount(passingVehicleRecord.getImages().size());
//                    recordCreateRequestDto.setPhotoUploaded(Boolean.TRUE);
//
//                    recordCreateRequestDto.setImageUrls(passingVehicleRecord.getImages());
//                }
//                platformProcessService.savePassVehicleRecord(recordCreateRequestDto);
//            }
//            resultDto.success();
//        } else {
//            resultDto.failed();
//        }
//        return resultDto;
//    }

//    @Override
//    public ResultDto transferOldParkingRecord(List<ParkingRecordResultDto> requestDto) {
//        ResultDto resultDto = new ResultDto();
//        if (CollectionUtil.isNotEmpty(requestDto)) {
//
//            for (ParkingRecordResultDto parkingRecordResultDto : requestDto) {
//
//                //因为BUG导致停车场数据迁移错误
//                Long parkingId = parkingRecordResultDto.getParkingId();
//                if (parkingId != null) {
//                    parkingId = transferToCloudParkingId(parkingId);
//                }
//                final ParkingResultDto parkingInfo = getTenantIdByParkingInfo(parkingId);
//                if (parkingInfo == null) {
//                    resultDto.failed();
//                    log.error("编号为{}的停车场不存在！", parkingRecordResultDto.getParkingId());
//                    continue;
//                }
//
//                ParkingRecordAddRequestDto recordCreateRequestDto =
//                        modelMapper.map(parkingRecordResultDto, ParkingRecordAddRequestDto.class);
//
//                recordCreateRequestDto.setTenantId(parkingInfo.getTenantId());
//                recordCreateRequestDto.setParkingCode(parkingInfo.getCode());
//                recordCreateRequestDto.setParkingName(parkingInfo.getName());
//                recordCreateRequestDto.setParkingType(6);
//
//                //车牌处理
//                if (!PlateNumberUtil.isPlateNumber(parkingRecordResultDto.getPlateNumber())) {
//                    recordCreateRequestDto.setPlateNumber("无车牌");
//                }
//
//                if (parkingRecordResultDto.getStartTime() != null && parkingRecordResultDto.getEndTime() != null) {
//                    //2049年之后的均记为0
//                    try {
//                        if (parkingRecordResultDto.getEndTime().after(DateUtils.parseDate("2049", "yyyy"))) {
//                            recordCreateRequestDto.setPeriodLength(0);
//                        } else {
//                            if (parkingRecordResultDto.getEndTime().compareTo(parkingRecordResultDto.getStartTime()) >= 0) {
//                                Long minute =
//                                        (parkingRecordResultDto.getEndTime().getTime() - parkingRecordResultDto.getStartTime().getTime())
//                                                / 1000 / 60;
//
//                                recordCreateRequestDto.setPeriodLength(minute.intValue());
//                            } else {
//                                recordCreateRequestDto.setPeriodLength(0);
//                            }
//                        }
//                    } catch (Exception e) {
//
//                    }
//                }
//                //停车场信息
//                //预约收费规则
//                if (parkingRecordResultDto.getAppointed()) {
//                    ParkingAppointInfoGetByParkingIdRequestDto parkingAppointInfoGetByParkingIdRequestDto = new ParkingAppointInfoGetByParkingIdRequestDto();
//                    parkingAppointInfoGetByParkingIdRequestDto.setParkingId(parkingInfo.getId());
//                    ObjectResultDto<ParkingAppointInfoResultDto> parkingAppointmentInfoByParkingId = platformParkingInfoService.getAppointmentInfoByParkingId(parkingAppointInfoGetByParkingIdRequestDto);
//                    if (parkingAppointmentInfoByParkingId.isSuccess() && parkingAppointmentInfoByParkingId.getData() != null) {
//                        ParkingAppointInfoResultDto parkingAppointInfoResultDto = parkingAppointmentInfoByParkingId.getData();
//                        recordCreateRequestDto.setAppointRuleId(parkingAppointInfoResultDto.getId());
//                    }
//                }
//                //收费规则
//                ParkingChargeInfoGetByParkingIdRequestDto parkingChargeInfoGetByParkingIdRequestDto = new ParkingChargeInfoGetByParkingIdRequestDto();
//                parkingChargeInfoGetByParkingIdRequestDto.setParkingId(parkingInfo.getId());
//                ObjectResultDto<ParkingChargeInfoResultDto> parkChargeInfoByParkingId = platformParkingInfoService.getParkChargeInfoByParkingId(parkingChargeInfoGetByParkingIdRequestDto);
//                if (parkChargeInfoByParkingId.isSuccess() && parkChargeInfoByParkingId.getData() != null) {
//                    ParkingChargeInfoResultDto parkingChargeInfoResultDto = parkChargeInfoByParkingId.getData();
//                    recordCreateRequestDto.setChargeId(parkingChargeInfoResultDto.getId());
//                }
//                platformProcessService.saveParkingRecord(recordCreateRequestDto);
//            }
//            resultDto.success();
//        } else {
//            resultDto.failed();
//        }
//        return resultDto;
//    }

//    @Override
//    public ResultDto transferOldParkingRecord(List<CloudParkingRecordResultDto> requestDto) {
//        ResultDto resultDto = new ResultDto();
//        if (requestDto != null) {
//            for (CloudParkingRecordResultDto recordResultDto : requestDto) {
//                //如果是固定的tenantID，可以写死，不用查数据库
//                final ParkingResultDto parkingInfo = getTenantIdByParkingInfo(recordResultDto.getParkingId());
//                if (parkingInfo == null) {
//                    resultDto.failed();
//                    log.error("编号为{}的停车场不存在！", recordResultDto.getParkingCode());
//                    return resultDto;
//                }
//                recordResultDto.setTenantId(parkingInfo.getTenantId());
//                ParkingRecordAddRequestDto recordCreateRequestDto =
//                        modelMapper.map(recordResultDto, ParkingRecordAddRequestDto.class);
//                //停车场信息
//                //预约收费规则
//                ParkingAppointInfoGetByParkingIdRequestDto parkingAppointInfoGetByParkingIdRequestDto = new ParkingAppointInfoGetByParkingIdRequestDto();
//                parkingAppointInfoGetByParkingIdRequestDto.setParkingId(parkingInfo.getId());
//                ObjectResultDto<ParkingAppointInfoResultDto> parkingAppointmentInfoByParkingId = platformParkingInfoService.getAppointmentInfoByParkingId(parkingAppointInfoGetByParkingIdRequestDto);
//                if (parkingAppointmentInfoByParkingId.isSuccess() && parkingAppointmentInfoByParkingId.getData() != null) {
//                    ParkingAppointInfoResultDto parkingAppointInfoResultDto = parkingAppointmentInfoByParkingId.getData();
//                    recordCreateRequestDto.setAppointRuleId(parkingAppointInfoResultDto.getId());
//                }
//                //收费规则
//                ParkingChargeInfoGetByParkingIdRequestDto parkingChargeInfoGetByParkingIdRequestDto = new ParkingChargeInfoGetByParkingIdRequestDto();
//                parkingChargeInfoGetByParkingIdRequestDto.setParkingId(parkingInfo.getId());
//                ObjectResultDto<ParkingChargeInfoResultDto> parkChargeInfoByParkingId = platformParkingInfoService.getParkChargeInfoByParkingId(parkingChargeInfoGetByParkingIdRequestDto);
//                if (parkChargeInfoByParkingId.isSuccess() && parkChargeInfoByParkingId.getData() != null) {
//                    ParkingChargeInfoResultDto parkingChargeInfoResultDto = parkChargeInfoByParkingId.getData();
//                    recordCreateRequestDto.setChargeId(parkingChargeInfoResultDto.getId());
//                }
//                platformProcessService.saveParkingRecord(recordCreateRequestDto);
//            }
//            resultDto.success();
//        } else {
//            resultDto.failed();
//        }
//        return resultDto;
//    }

//    @Override
//    public ResultDto transferOldParkingOrder(List<ParkingOrderResultDto> requestDto) {
//        ResultDto resultDto = new ResultDto();
//        if (CollectionUtil.isNotEmpty(requestDto)) {
//            for (ParkingOrderResultDto parkingOrderResultDto : requestDto) {
//
//                //因为BUG导致停车场数据迁移错误
//                Long parkingId = parkingOrderResultDto.getParkingId();
//                if (parkingId != null) {
//                    parkingId = transferToCloudParkingId(parkingId);
//                }
//                final ParkingResultDto parkingInfo = getTenantIdByParkingInfo(parkingId);
//                if (parkingInfo == null) {
//                    resultDto.failed();
//                    log.error("编号为{}的停车场不存在！", parkingOrderResultDto.getParkingId());
//                    continue;
//                }
//
//                ParkingOrderCreateRequestDto createRequestDto =
//                        modelMapper.map(parkingOrderResultDto, ParkingOrderCreateRequestDto.class);
//                createRequestDto.setTenantId(parkingInfo.getTenantId());
//
//                //收费规则
//                ParkingChargeInfoGetByParkingIdRequestDto parkingChargeInfoGetByParkingIdRequestDto = new ParkingChargeInfoGetByParkingIdRequestDto();
//                parkingChargeInfoGetByParkingIdRequestDto.setParkingId(parkingInfo.getId());
//                ObjectResultDto<ParkingChargeInfoResultDto> parkChargeInfoByParkingId = platformParkingInfoService.getParkChargeInfoByParkingId(parkingChargeInfoGetByParkingIdRequestDto);
//                if (parkChargeInfoByParkingId.isSuccess() && parkChargeInfoByParkingId.getData() != null) {
//                    ParkingChargeInfoResultDto parkingChargeInfoResultDto = parkChargeInfoByParkingId.getData();
//                    createRequestDto.setChargeInfoId(parkingChargeInfoResultDto.getId());
//                }
//                platformParkingOrderService.saveParkingOrder(createRequestDto);
//            }
//            resultDto.success();
//        } else {
//            resultDto.failed();
//        }
//        return resultDto;
//    }

    /**
     * @param parkingId
     * @return
     */
//    private ParkingResultDto getTenantIdByParkingInfo(Long parkingId) {
//        final ParkingGetRequestDto requestDto = new ParkingGetRequestDto();
//        requestDto.setId(parkingId);
//        final ObjectResultDto<ParkingResultDto> parkingInfo = platformParkingInfoService.getParkingInfoById(requestDto);
//        return parkingInfo.getData();
//    }

    /**
     * 转换
     *
     * @param parkingId
     * @return
     */
//    private Long transferToCloudParkingId(Long parkingId) {
//        if (parkingId.equals(45L)) {
//            return 44L;
//        } else if (parkingId.equals(11495L)) {
//            return 11494L;
//        } else if (parkingId.equals(118192L)) {
//            return 118191L;
//        } else if (parkingId.equals(118194L)) {
//            return 118193L;
//        } else {
//            return parkingId;
//        }
//    }
}
