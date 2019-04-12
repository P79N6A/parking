package com.zoeeasy.cloud.integration.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.scapegoat.infrastructure.common.utils.LocationUtil;
import com.scapegoat.infrastructure.common.utils.NumberUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingCurrentChargeInfoRequestDto;
import com.zoeeasy.cloud.charge.park.dto.result.ParkingCurrentChargeInfoResultDto;
import com.zoeeasy.cloud.charge.platform.PlatformChargeService;
import com.zoeeasy.cloud.integration.appoint.dto.request.AppointmentAroundParkingGetRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.request.AppointmentParkingDetailRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.result.AppointmentParkingDetailViewResultDto;
import com.zoeeasy.cloud.integration.appoint.dto.result.AppointmentParkingResultDto;
import com.zoeeasy.cloud.integration.park.ParkChargeRuleIntegrationService;
import com.zoeeasy.cloud.integration.platform.PlatformParkingInfoIntegrationService;
import com.zoeeasy.cloud.integration.platform.dto.request.ParkingAndNotifyRequestDto;
import com.zoeeasy.cloud.integration.platform.dto.request.ParkingAroundGetRequestDto;
import com.zoeeasy.cloud.integration.platform.dto.request.ParkingDetailGetRequestDto;
import com.zoeeasy.cloud.integration.platform.dto.request.ParkingExtendInfoGetRequestDto;
import com.zoeeasy.cloud.integration.platform.dto.result.*;
import com.zoeeasy.cloud.integration.platform.validator.ParkingAndNotifyRequestDtoValidator;
import com.zoeeasy.cloud.integration.utils.PmsUtils;
import com.zoeeasy.cloud.integration.utils.CommonUtils;
import com.zoeeasy.cloud.notify.platform.PlatformNotifyService;
import com.zoeeasy.cloud.notify.platform.dto.request.NotifyCountRequestDto;
import com.zoeeasy.cloud.notify.platform.dto.result.NotifyCountResultDto;
import com.zoeeasy.cloud.pms.cst.PmsConstant;
import com.zoeeasy.cloud.pms.enums.ParkingFreeLevelEnum;
import com.zoeeasy.cloud.pms.enums.ParkingStatusEnum;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.park.dto.request.AppointmentParkingCountRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingCurrentGetByIdRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.*;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.dto.request.*;
import com.zoeeasy.cloud.pms.platform.dto.result.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 停车场基础数据集成服务
 *
 * @author walkman
 */
@Service("platParkingInfoIntegrationService")
@Slf4j
public class PlatformParkingInfoIntegrationServiceImpl implements PlatformParkingInfoIntegrationService {

    @Autowired
    private ParkChargeRuleIntegrationService parkChargeRuleIntegrationService;

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @Autowired
    private PlatformChargeService platformChargeService;

    @Autowired
    private PlatformNotifyService platformNotifyService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 获取附近的停车场
     *
     * @param requestDto requestDto
     */
    @Override
    public ObjectResultDto<ParkingAroundViewResultDto> getAroundParking(ParkingAroundGetRequestDto requestDto) {
        ObjectResultDto<ParkingAroundViewResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingAroundViewResultDto parkingAroundViewResultDto = new ParkingAroundViewResultDto();
            parkingAroundViewResultDto.setTotalCount(0);

            if (requestDto.getLatitude().compareTo(0.00d) <= 0
                    || requestDto.getLongitude().compareTo(0.00d) <= 0) {
                //经纬度小于0，返回
            } else {
                //默认附近3公里
                Double maxDistance = PmsConstant.PARKING_AROUND_DISTANCE_THREE * PmsConstant.KILOMETER;
                //最近停车场
                ParkingInfoAroundResultDto nearestParking = null;
                //附近停车场列表
                List<ParkingInfoAroundResultDto> closestParkingInfoList = new ArrayList<>();
                //附近3公里停车场列表
                ParkingInfoAroundGetRequestDto parkingInfoAroundGetRequestDto = new ParkingInfoAroundGetRequestDto();
                parkingInfoAroundGetRequestDto.setLatitude(requestDto.getLatitude());
                parkingInfoAroundGetRequestDto.setLongitude(requestDto.getLongitude());
                parkingInfoAroundGetRequestDto.setMaxDistance(maxDistance);
                ListResultDto<ParkingInfoAroundResultDto> parkingInfoList3 = platformParkingInfoService.getAroundParkingInfoList(parkingInfoAroundGetRequestDto);
                if (parkingInfoList3.isSuccess() && !CollectionUtils.isEmpty(parkingInfoList3.getItems())) {
                    List<ParkingInfoAroundResultDto> closestParkingInfo3List = parkingInfoList3.getItems().stream()
                            .sorted(Comparator.comparing(ParkingInfoAroundResultDto::getDistance))
                            .collect(Collectors.toList());
                    if (!closestParkingInfo3List.isEmpty()) {

                        //如果附近3公里停车场多于MAX_PARKING_AROUND_COUNT个
                        if (closestParkingInfo3List.size() >= PmsConstant.MAX_PARKING_AROUND_COUNT) {
                            //附近1.5公里
                            Double finalMaxDistance15 = PmsConstant.PARKING_AROUND_DISTANCE_ONE * PmsConstant.KILOMETER;
                            List<ParkingInfoAroundResultDto> closestParkingInfo15List = closestParkingInfo3List.stream()
                                    .sorted(Comparator.comparing(ParkingInfoAroundResultDto::getDistance))
                                    .filter(parkingInfo -> parkingInfo.getDistance() <= finalMaxDistance15)
                                    .collect(Collectors.toList());
                            if (CollectionUtils.isEmpty(closestParkingInfo15List)) {
                                //附近1.5公里无停车场，则以附近3公里为准
                                closestParkingInfoList = closestParkingInfo3List;
                            } else {
                                closestParkingInfoList = closestParkingInfo15List;
                            }
                        } else {
                            closestParkingInfoList = closestParkingInfo3List;
                        }
                        Double minDistance = null;
                        //循环取附近的停车场
                        for (ParkingInfoAroundResultDto parkingInfo : closestParkingInfoList) {
                            if (parkingInfo.getLatitude() == null || parkingInfo.getLongitude() == null) {
                                //坐标为空
                                continue;
                            }
                            //距离
                            Double distance = parkingInfo.getDistance();
                            //距离小于最大距离(10千米)
                            if (distance != null && distance <= maxDistance) {
                                //距离小于1千米
                                if (distance.compareTo(PmsConstant.KILOMETER) <= 0) {
                                    if (minDistance != null) {
                                        if (distance < minDistance) {
                                            minDistance = distance;
                                            nearestParking = parkingInfo;
                                        }
                                    } else {
                                        minDistance = distance;
                                        nearestParking = parkingInfo;
                                    }
                                }
                            }
                        }
                    }
                }
                if (!closestParkingInfoList.isEmpty()) {
                    //附近所有停车场
                    List<ParkingAroundItemViewResultDto> closetAllParkingList = new ArrayList<>();
                    //附近免费停车场
                    List<ParkingAroundItemViewResultDto> closetFreeParkingList = new ArrayList<>();
                    //附近收费停车场
                    List<ParkingAroundItemViewResultDto> closetChargeParkingList = new ArrayList<>();
                    //按距离限定停车场数量
                    List<ParkingInfoAroundResultDto> limitParkingInfoList = closestParkingInfoList.stream()
                            .sorted(Comparator.comparing(ParkingInfoAroundResultDto::getDistance))
                            .limit(PmsConstant.MAX_PARKING_AROUND_COUNT)
                            .collect(Collectors.toList());
                    for (int i = 0; i < limitParkingInfoList.size(); i++) {
                        ParkingInfoAroundResultDto parkingInfo = limitParkingInfoList.get(i);
                        ParkingAroundItemViewResultDto parkingViewResultDto = modelMapper.map(parkingInfo, ParkingAroundItemViewResultDto.class);
                        //距离规范化
                        if (parkingViewResultDto.getDistance().compareTo(PmsConstant.KILOMETER) <= 0) {
                            parkingViewResultDto.setDistance(BigDecimal.valueOf(parkingViewResultDto.getDistance()).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
                            parkingViewResultDto.setDistanceUnit(PmsConstant.DISTANCE_UNIT_METER);
                        } else {
                            parkingViewResultDto.setDistance(BigDecimal.valueOf(parkingViewResultDto.getDistance() / PmsConstant.KILOMETER)
                                    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                            parkingViewResultDto.setDistanceUnit(PmsConstant.DISTANCE_UNIT_KILOMETER);
                        }

                        //停车场是否限免
                        if (!parkingViewResultDto.getChargeFee()) {
                            parkingViewResultDto.setChargeFee(Boolean.FALSE);
                            parkingViewResultDto.setNowFree(Boolean.TRUE);
                            parkingViewResultDto.setFreeStartTime("00:00");
                            parkingViewResultDto.setFreeEndTime("23:59");
                        } else {
                            parkingViewResultDto.setChargeFee(Boolean.TRUE);
                            //平台支持停车场才去获取具体收费信息
                            boolean useDefaultChargeInfo = true;
                            if (parkingInfo.getPlatformSupport()) {
                                //获取停车场收费信息,判断当前时段是否免费
                                ParkingCurrentChargeInfoRequestDto parkingChargeInfoGetRequestDto = new ParkingCurrentChargeInfoRequestDto();
                                parkingChargeInfoGetRequestDto.setTenantId(parkingInfo.getTenantId());
                                parkingChargeInfoGetRequestDto.setParkingId(parkingInfo.getId());
                                ObjectResultDto<ParkingCurrentChargeInfoResultDto>
                                        parkingChargeInfoObjectResultDto = platformChargeService.getParkingCurrentChargeInfo(parkingChargeInfoGetRequestDto);
                                if (parkingChargeInfoObjectResultDto.isSuccess() && parkingChargeInfoObjectResultDto.getData() != null) {
                                    ParkingCurrentChargeInfoResultDto parkingChargeInfoResultDto = parkingChargeInfoObjectResultDto.getData();
                                    parkingViewResultDto.setNowFree(parkingChargeInfoResultDto.getNowFree());
                                    parkingViewResultDto.setFreeTimeLength(parkingChargeInfoResultDto.getFreeTimeLength());
                                    parkingViewResultDto.setFreeStartTime(parkingChargeInfoResultDto.getFreeStartTime());
                                    parkingViewResultDto.setFreeEndTime(parkingChargeInfoResultDto.getFreeEndTime());
                                    parkingViewResultDto.setChargeRule(parkingChargeInfoResultDto.getChargeRule());
                                    //封顶金额转化为元
                                    if (null != parkingChargeInfoResultDto.getDayTopAmount()) {
                                        parkingViewResultDto.setDayTopAmount(NumberUtils.amountFen2Yuan(BigDecimal.valueOf(parkingChargeInfoResultDto.getDayTopAmount())));
                                    }
                                    useDefaultChargeInfo = false;
                                }
                            }
                            if (useDefaultChargeInfo) {
                                parkingViewResultDto.setNowFree(Boolean.FALSE);
                                ParkingChargeInfoResultDto parkingChargeInfoResult = this.getParkingCurrentInfoChargeInfo(parkingViewResultDto.getId());
                                if (parkingChargeInfoResult != null) {
                                    //收费详情
                                    parkingViewResultDto.setChargeRule(parkingChargeInfoResult.getChargeRule());
                                    parkingViewResultDto.setChargeDescription(parkingChargeInfoResult.getChargeDescription());
                                }
                            }
                        }

                        if (parkingViewResultDto.getPlatformSupport()) {
                            ParkingCurrentGetByIdRequestDto parkingCurrentGetByIdRequestDto = new ParkingCurrentGetByIdRequestDto();
                            parkingCurrentGetByIdRequestDto.setParkingId(parkingViewResultDto.getId());
                            ObjectResultDto<ParkingCurrentInfoResultDto> parkingCurrentGetByIdResultDtoObjectResultDto = platformParkingInfoService.selectCurrentInfoByParkingId(parkingCurrentGetByIdRequestDto);
                            if (parkingCurrentGetByIdResultDtoObjectResultDto.getData() != null) {
                                //空闲程度
                                Integer freeLevel = PmsUtils.calculateFreeLevel(parkingViewResultDto.getLotTotal(), parkingCurrentGetByIdResultDtoObjectResultDto.getData().getLotAvailable());
                                parkingViewResultDto.setFreeLevel(freeLevel);
                                parkingViewResultDto.setLotAvailable(parkingCurrentGetByIdResultDtoObjectResultDto.getData().getLotAvailable());
                                parkingViewResultDto.setLotAppointmentAvailable(parkingCurrentGetByIdResultDtoObjectResultDto.getData().getLotAppointmentAvailable());
                            }
                        } else {
                            parkingViewResultDto.setFreeLevel(ParkingFreeLevelEnum.GREEN.getValue());
                            parkingViewResultDto.setLotAvailable(0);
                            parkingViewResultDto.setLotAppointmentAvailable(0);
                        }

                        //最优停车场不空
                        if (nearestParking != null) {
                            if (!parkingViewResultDto.getId().equals(nearestParking.getId())) {
                                closetAllParkingList.add(parkingViewResultDto);
                                if (parkingViewResultDto.getNowFree()) {
                                    closetFreeParkingList.add(parkingViewResultDto);
                                } else {
                                    closetChargeParkingList.add(parkingViewResultDto);
                                }
                            } else {
                                parkingAroundViewResultDto.setClosest(parkingViewResultDto);
                            }
                        } else {
                            closetAllParkingList.add(parkingViewResultDto);
                            if (parkingViewResultDto.getNowFree()) {
                                closetFreeParkingList.add(parkingViewResultDto);
                            } else {
                                closetChargeParkingList.add(parkingViewResultDto);
                            }
                        }
                    }
                    parkingAroundViewResultDto.setAllParkings(closetAllParkingList);
                    parkingAroundViewResultDto.setFreeParkings(closetFreeParkingList);
                    parkingAroundViewResultDto.setChargeParkings(closetChargeParkingList);
                    parkingAroundViewResultDto.setTotalCount(limitParkingInfoList.size());
                }
            }
            objectResultDto.setData(parkingAroundViewResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("PlatformParkingInfoIntegrationServiceImpl@getAroundParking获取附近停车场失败，异常信息：{}", e.getMessage(),e);
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取附近的停车场基本信息列表
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingAroundBaseViewResultDto> getAroundParkingBaseViewList(ParkingAroundGetRequestDto requestDto) {
        ObjectResultDto<ParkingAroundBaseViewResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingAroundBaseViewResultDto parkingAroundViewResultDto = new ParkingAroundBaseViewResultDto();
            parkingAroundViewResultDto.setTotalCount(0);

            if (requestDto.getLatitude().compareTo(0.00d) <= 0
                    || requestDto.getLongitude().compareTo(0.00d) <= 0) {
                //经纬度小于0，返回
            } else {
                //默认附近3公里
                Double maxDistance = PmsConstant.PARKING_AROUND_DISTANCE_THREE * PmsConstant.KILOMETER;
                //最近停车场
                ParkingInfoAroundResultDto nearestParking = null;
                //附近停车场列表
                List<ParkingInfoAroundResultDto> closestParkingInfoList = new ArrayList<>();
                //附近3公里停车场列表
                ParkingInfoAroundGetRequestDto parkingInfoAroundGetRequestDto = new ParkingInfoAroundGetRequestDto();
                parkingInfoAroundGetRequestDto.setLatitude(requestDto.getLatitude());
                parkingInfoAroundGetRequestDto.setLongitude(requestDto.getLongitude());
                parkingInfoAroundGetRequestDto.setMaxDistance(maxDistance);
                ListResultDto<ParkingInfoAroundResultDto> parkingInfoList3 = platformParkingInfoService.getAroundParkingInfoList(parkingInfoAroundGetRequestDto);
                if (parkingInfoList3.isSuccess() && !CollectionUtils.isEmpty(parkingInfoList3.getItems())) {
                    List<ParkingInfoAroundResultDto> closestParkingInfo3List = parkingInfoList3.getItems().stream()
                            .sorted(Comparator.comparing(ParkingInfoAroundResultDto::getDistance))
                            .collect(Collectors.toList());
                    if (!closestParkingInfo3List.isEmpty()) {

                        //如果附近3公里停车场多于MAX_PARKING_AROUND_COUNT个
                        if (closestParkingInfo3List.size() >= PmsConstant.MAX_PARKING_AROUND_COUNT) {
                            //附近1.5公里
                            Double finalMaxDistance15 = PmsConstant.PARKING_AROUND_DISTANCE_ONE * PmsConstant.KILOMETER;
                            List<ParkingInfoAroundResultDto> closestParkingInfo15List = closestParkingInfo3List.stream()
                                    .sorted(Comparator.comparing(ParkingInfoAroundResultDto::getDistance))
                                    .filter(parkingInfo -> parkingInfo.getDistance() <= finalMaxDistance15)
                                    .collect(Collectors.toList());
                            if (CollectionUtils.isEmpty(closestParkingInfo15List)) {
                                //附近1.5公里无停车场，则以附近3公里为准
                                closestParkingInfoList = closestParkingInfo3List;
                            } else {
                                closestParkingInfoList = closestParkingInfo15List;
                            }
                        } else {
                            closestParkingInfoList = closestParkingInfo3List;
                        }
                        Double minDistance = null;
                        //循环取附近的停车场
                        for (ParkingInfoAroundResultDto parkingInfo : closestParkingInfoList) {
                            if (parkingInfo.getLatitude() == null || parkingInfo.getLongitude() == null) {
                                //坐标为空
                                continue;
                            }
                            //距离
                            Double distance = parkingInfo.getDistance();
                            //距离小于最大距离(10千米)
                            if (distance != null && distance <= maxDistance) {
                                //距离小于1千米
                                if (distance.compareTo(PmsConstant.KILOMETER) <= 0) {
                                    if (minDistance != null) {
                                        if (distance < minDistance) {
                                            minDistance = distance;
                                            nearestParking = parkingInfo;
                                        }
                                    } else {
                                        minDistance = distance;
                                        nearestParking = parkingInfo;
                                    }
                                }
                            }
                        }
                    }
                }
                if (!closestParkingInfoList.isEmpty()) {
                    //附近所有停车场
                    List<ParkingAroundItemBaseViewResultDto> closetAllParkingList = new ArrayList<>();
                    //附近免费停车场
                    List<ParkingAroundItemBaseViewResultDto> closetFreeParkingList = new ArrayList<>();
                    //附近收费停车场
                    List<ParkingAroundItemBaseViewResultDto> closetChargeParkingList = new ArrayList<>();
                    //按距离限定停车场数量
                    List<ParkingInfoAroundResultDto> limitParkingInfoList = closestParkingInfoList.stream()
                            .sorted(Comparator.comparing(ParkingInfoAroundResultDto::getDistance))
                            .limit(PmsConstant.MAX_PARKING_AROUND_COUNT)
                            .collect(Collectors.toList());
                    for (int i = 0; i < limitParkingInfoList.size(); i++) {
                        ParkingInfoAroundResultDto parkingInfo = limitParkingInfoList.get(i);
                        ParkingAroundItemBaseViewResultDto parkingViewResultDto = modelMapper.map(parkingInfo, ParkingAroundItemBaseViewResultDto.class);
                        //距离规范化
                        if (parkingViewResultDto.getDistance().compareTo(PmsConstant.KILOMETER) <= 0) {
                            parkingViewResultDto.setDistance(BigDecimal.valueOf(parkingViewResultDto.getDistance()).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
                            parkingViewResultDto.setDistanceUnit(PmsConstant.DISTANCE_UNIT_METER);
                        } else {
                            parkingViewResultDto.setDistance(BigDecimal.valueOf(parkingViewResultDto.getDistance() / PmsConstant.KILOMETER)
                                    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                            parkingViewResultDto.setDistanceUnit(PmsConstant.DISTANCE_UNIT_KILOMETER);
                        }

                        //停车场是否限免
                        if (!parkingViewResultDto.getChargeFee()) {
                            parkingViewResultDto.setChargeFee(Boolean.FALSE);
                            parkingViewResultDto.setNowFree(Boolean.TRUE);
                            parkingViewResultDto.setFreeStartTime("00:00");
                            parkingViewResultDto.setFreeEndTime("23:59");
                        } else {
                            parkingViewResultDto.setChargeFee(Boolean.TRUE);
                            parkingViewResultDto.setNowFree(Boolean.FALSE);
                            parkingViewResultDto.setFreeStartTime("");
                            parkingViewResultDto.setFreeEndTime("");
                            //平台支持停车场才去获取具体收费信息
                            if (parkingInfo.getPlatformSupport()) {
                                //获取停车场收费信息,判断当前时段是否免费
                                ParkingCurrentChargeInfoRequestDto parkingChargeInfoGetRequestDto = new ParkingCurrentChargeInfoRequestDto();
                                parkingChargeInfoGetRequestDto.setTenantId(parkingInfo.getTenantId());
                                parkingChargeInfoGetRequestDto.setParkingId(parkingInfo.getId());
                                ObjectResultDto<ParkingCurrentChargeInfoResultDto>
                                        parkingChargeInfoObjectResultDto = platformChargeService.getParkingCurrentChargeInfo(parkingChargeInfoGetRequestDto);
                                if (parkingChargeInfoObjectResultDto.isSuccess() && parkingChargeInfoObjectResultDto.getData() != null) {
                                    ParkingCurrentChargeInfoResultDto parkingChargeInfoResultDto = parkingChargeInfoObjectResultDto.getData();
                                    parkingViewResultDto.setNowFree(parkingChargeInfoResultDto.getNowFree());
                                    parkingViewResultDto.setFreeStartTime(parkingChargeInfoResultDto.getFreeStartTime());
                                    parkingViewResultDto.setFreeEndTime(parkingChargeInfoResultDto.getFreeEndTime());
                                }
                            }
                        }

                        parkingViewResultDto.setFreeLevel(ParkingFreeLevelEnum.GREEN.getValue());
                        parkingViewResultDto.setLotAvailable(0);
                        parkingViewResultDto.setLotAppointmentTotal(0);
                        parkingViewResultDto.setLotAppointmentAvailable(0);
                        if (parkingViewResultDto.getPlatformSupport()) {
                            ParkingCurrentGetByIdRequestDto parkingCurrentGetByIdRequestDto = new ParkingCurrentGetByIdRequestDto();
                            parkingCurrentGetByIdRequestDto.setParkingId(parkingViewResultDto.getId());
                            ObjectResultDto<ParkingCurrentInfoResultDto> parkingCurrentInfoResultDto = platformParkingInfoService.selectCurrentInfoByParkingId(parkingCurrentGetByIdRequestDto);
                            if (parkingCurrentInfoResultDto.isSuccess() && parkingCurrentInfoResultDto.getData() != null) {
                                //空闲程度
                                ParkingCurrentInfoResultDto parkingCurrentInfo = parkingCurrentInfoResultDto.getData();
                                Integer freeLevel = PmsUtils.calculateFreeLevel(parkingViewResultDto.getLotTotal(), parkingCurrentInfo.getLotAvailable());
                                parkingViewResultDto.setFreeLevel(freeLevel);
                                parkingViewResultDto.setLotAvailable(parkingCurrentInfo.getLotAvailable());
                                //可预约停车场
                                if (parkingViewResultDto.getSupportAppointment()) {
                                    parkingViewResultDto.setLotAppointmentAvailable(parkingCurrentInfo.getLotAppointmentAvailable());
                                }
                            }
                        }

                        //最优停车场不空
                        if (nearestParking != null) {
                            if (!parkingViewResultDto.getId().equals(nearestParking.getId())) {
                                closetAllParkingList.add(parkingViewResultDto);
                                if (parkingViewResultDto.getNowFree()) {
                                    closetFreeParkingList.add(parkingViewResultDto);
                                } else {
                                    closetChargeParkingList.add(parkingViewResultDto);
                                }
                            } else {
                                parkingAroundViewResultDto.setClosest(parkingViewResultDto);
                            }
                        } else {
                            closetAllParkingList.add(parkingViewResultDto);
                            if (parkingViewResultDto.getNowFree()) {
                                closetFreeParkingList.add(parkingViewResultDto);
                            } else {
                                closetChargeParkingList.add(parkingViewResultDto);
                            }
                        }
                    }
                    parkingAroundViewResultDto.setAllParkings(closetAllParkingList);
                    parkingAroundViewResultDto.setFreeParkings(closetFreeParkingList);
                    parkingAroundViewResultDto.setChargeParkings(closetChargeParkingList);
                    parkingAroundViewResultDto.setTotalCount(limitParkingInfoList.size());
                }
            }
            objectResultDto.setData(parkingAroundViewResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取附近停车场失败，异常信息：{}", e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取附近的停车场的扩展信息
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingAroundItemExtendViewResultDto> getAroundParkingExtendView(ParkingExtendInfoGetRequestDto requestDto) {
        ObjectResultDto<ParkingAroundItemExtendViewResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            ParkingInfoGetRequestDto parkingInfoGetRequestDto = new ParkingInfoGetRequestDto();
            parkingInfoGetRequestDto.setParkingId(requestDto.getId());
            parkingInfoGetRequestDto.setStatus(ParkingStatusEnum.ON_LINE.getValue());
            ObjectResultDto<ParkingInfoResultDto> parkingInfoResultDto = platformParkingInfoService.getParkInfoById(parkingInfoGetRequestDto);
            if (parkingInfoResultDto.isFailed() || parkingInfoResultDto.getData() == null) {
                return objectResultDto.failed(PmsResultEnum.PARKING_NOT_FOUND.getComment());
            } else {
                ParkingInfoResultDto parkingInfoResult = parkingInfoResultDto.getData();

                ParkingAroundItemExtendViewResultDto parkingViewResultDto = new ParkingAroundItemExtendViewResultDto();
                parkingViewResultDto.setId(parkingInfoResult.getId());
                parkingViewResultDto.setOpenStartTime(parkingInfoResult.getOpenStartTime());
                parkingViewResultDto.setOpenEndTime(parkingInfoResult.getOpenEndTime());
                parkingViewResultDto.setFreeTimeLength(parkingInfoResult.getFreeTime());
                parkingViewResultDto.setLotTotal(parkingInfoResult.getLotTotal());
                //停车场地址
                parkingViewResultDto.setAddress(parkingInfoResult.getAddress());
                //停车场是否限免
                boolean useDefaultChargeInfo = true;
                if (parkingInfoResult.getPlatformSupport()) {
                    //判断当前时段是否免费
                    ParkingCurrentChargeInfoRequestDto parkingChargeInfoGetRequestDto = new ParkingCurrentChargeInfoRequestDto();
                    parkingChargeInfoGetRequestDto.setTenantId(parkingInfoResult.getTenantId());
                    parkingChargeInfoGetRequestDto.setParkingId(parkingViewResultDto.getId());
                    ObjectResultDto<ParkingCurrentChargeInfoResultDto>
                            parkingChargeInfoObjectResultDto = platformChargeService.getParkingCurrentChargeInfo(parkingChargeInfoGetRequestDto);
                    if (parkingChargeInfoObjectResultDto.isSuccess() && parkingChargeInfoObjectResultDto.getData() != null) {
                        ParkingCurrentChargeInfoResultDto parkingChargeInfoResultDto = parkingChargeInfoObjectResultDto.getData();
                        useDefaultChargeInfo = false;
                        parkingViewResultDto.setChargeRule(parkingChargeInfoResultDto.getChargeRule());
                        //金额转换
                        //封顶金额转化为分
                        if (null != parkingChargeInfoResultDto.getDayTopAmount()) {
                            BigDecimal dayTopAmount = NumberUtils.amountFen2Yuan(BigDecimal.valueOf(parkingChargeInfoResultDto.getDayTopAmount()));
                            parkingViewResultDto.setDayTopAmount(NumberUtils.formatAmountYuan(dayTopAmount));
                        }
                        parkingViewResultDto.setFreeTimeLength(parkingChargeInfoResultDto.getFreeTimeLength());
                    }
                } else {
                    parkingViewResultDto.setFreeTimeLength(0);
                    parkingViewResultDto.setDayTopAmount("");
                }
                ParkingChargeInfoResultDto parkingChargeInfoResult = this.getParkingCurrentInfoChargeInfo(parkingViewResultDto.getId());
                if (parkingChargeInfoResult != null) {
                    //收费详情
                    if (useDefaultChargeInfo) {
                        parkingViewResultDto.setChargeRule(parkingChargeInfoResult.getChargeRule()==null?"":parkingChargeInfoResult.getChargeRule());
                    }
                    parkingViewResultDto.setChargeDescription(parkingChargeInfoResult.getChargeDescription()==null?"":parkingChargeInfoResult.getChargeDescription());
                }
                boolean supportAppointment = false;
                if (parkingInfoResult.getSupportAppointment()) {
                    ParkingAppointInfoResultDto parkingAppointInfoResultDto = this.getParkingAppointInfo(parkingInfoResult.getId());
                    if (parkingAppointInfoResultDto != null) {
                        parkingViewResultDto.setLotAppointmentTotal(parkingAppointInfoResultDto.getLotAppointmentTotal());
                        parkingViewResultDto.setAppointmentRule(parkingAppointInfoResultDto.getAppointmentRule());
                        supportAppointment = true;
                    }
                }
                if (!supportAppointment) {
                    parkingViewResultDto.setLotAppointmentTotal(0);
                    parkingViewResultDto.setAppointmentRule("");
                }
                objectResultDto.setData(parkingViewResultDto);
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("停车场获取失败,异常信息:{}", e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取停车场详情
     */
    @Override
    public ObjectResultDto<ParkingDetailViewResultDto> getParkingDetail(ParkingDetailGetRequestDto requestDto) {

        ObjectResultDto<ParkingDetailViewResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            ParkingInfoGetRequestDto parkingInfoGetRequestDto = new ParkingInfoGetRequestDto();
            parkingInfoGetRequestDto.setParkingId(requestDto.getId());
            parkingInfoGetRequestDto.setStatus(ParkingStatusEnum.ON_LINE.getValue());
            ObjectResultDto<ParkingInfoResultDto> parkingInfoResultDto = platformParkingInfoService.getParkInfoById(parkingInfoGetRequestDto);
            if (parkingInfoResultDto.isFailed() || parkingInfoResultDto.getData() == null) {
                return objectResultDto.failed(PmsResultEnum.PARKING_NOT_FOUND.getComment());
            } else {
                ParkingInfoResultDto parkingInfoResult = parkingInfoResultDto.getData();
                ParkingDetailViewResultDto parkingViewResultDto = modelMapper.map(parkingInfoResult, ParkingDetailViewResultDto.class);
                parkingViewResultDto.setName(parkingInfoResult.getFullName());
                //计算距离
                Double distance = LocationUtil.getDistanceFromTwoPoints(
                        requestDto.getLatitude(),
                        requestDto.getLongitude(),
                        parkingInfoResult.getLatitude(),
                        parkingInfoResult.getLongitude());
                if (distance != null) {
                    //距离规范化
                    if (distance.compareTo(PmsConstant.KILOMETER) <= 0) {
                        parkingViewResultDto.setDistance(BigDecimal.valueOf(distance).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
                        parkingViewResultDto.setDistanceUnit(PmsConstant.DISTANCE_UNIT_METER);
                    } else {
                        parkingViewResultDto.setDistance(BigDecimal.valueOf(distance / PmsConstant.KILOMETER).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                        parkingViewResultDto.setDistanceUnit(PmsConstant.DISTANCE_UNIT_KILOMETER);
                    }
                }
                //停车场地址
                //获取停车场当前信息
                ParkingCurrentInfoGetByParkingIdResultDto parkingCurrentInfoGetByParkingIdResultDto = this.getParkingCurrentInfo(parkingViewResultDto.getId());
                if (null != parkingCurrentInfoGetByParkingIdResultDto && (parkingCurrentInfoGetByParkingIdResultDto.getLotAvailable() <= parkingViewResultDto.getLotTotal())) {
                    parkingViewResultDto.setFreeLevel(CommonUtils.calculateFreeLevel(parkingViewResultDto.getLotTotal(), parkingCurrentInfoGetByParkingIdResultDto.getLotAvailable()));
                    parkingViewResultDto.setLotAvailable(parkingCurrentInfoGetByParkingIdResultDto.getLotAvailable());
                    parkingViewResultDto.setLotAppointmentAvailable(parkingCurrentInfoGetByParkingIdResultDto.getLotAppointmentAvailable());
                }
                //停车场是否限免
                if (!parkingViewResultDto.getChargeFee()) {
                    parkingViewResultDto.setChargeFee(Boolean.FALSE);
                    parkingViewResultDto.setNowFree(Boolean.TRUE);
                } else {
                    parkingViewResultDto.setChargeFee(Boolean.TRUE);
                    //平台支持停车场才去获取具体收费信息
                    boolean useDefaultChargeInfo = true;
                    if (parkingInfoResult.getPlatformSupport()) {
                        //判断当前时段是否免费
                        ParkingCurrentChargeInfoRequestDto parkingChargeInfoGetRequestDto = new ParkingCurrentChargeInfoRequestDto();
                        parkingChargeInfoGetRequestDto.setParkingId(parkingViewResultDto.getId());
                        parkingChargeInfoGetRequestDto.setTenantId(parkingInfoResult.getTenantId());
                        ObjectResultDto<ParkingCurrentChargeInfoResultDto>
                                parkingChargeInfoObjectResultDto = platformChargeService.getParkingCurrentChargeInfo(parkingChargeInfoGetRequestDto);
                        if (parkingChargeInfoObjectResultDto.isSuccess() && parkingChargeInfoObjectResultDto.getData() != null) {
                            ParkingCurrentChargeInfoResultDto parkingChargeInfoResultDto = parkingChargeInfoObjectResultDto.getData();
                            parkingViewResultDto.setNowFree(parkingChargeInfoResultDto.getNowFree());
                            parkingViewResultDto.setFreeTimeLength(parkingChargeInfoResultDto.getFreeTimeLength());
                            parkingViewResultDto.setDiscriminateLargeSmall(parkingChargeInfoResultDto.getDiscriminateLargeSmall());
                            parkingViewResultDto.setLargeVehicleRule(parkingChargeInfoResultDto.getLargeVehicleRule());
                            parkingViewResultDto.setSmallVehicleRule(parkingChargeInfoResultDto.getSmallVehicleRule());
                            parkingViewResultDto.setChargeRule(parkingChargeInfoResultDto.getChargeRule());
                            //金额转换
                            //封顶金额转化为分
                            if (null != parkingChargeInfoResultDto.getDayTopAmount()) {
                                parkingViewResultDto.setDayTopAmount(NumberUtils.amountFen2Yuan(BigDecimal.valueOf(parkingChargeInfoResultDto.getDayTopAmount())));
                            }
                            useDefaultChargeInfo = false;
                        }
                        if (useDefaultChargeInfo) {
                            parkingViewResultDto.setNowFree(Boolean.FALSE);
                            ParkingChargeInfoResultDto parkingChargeInfoResult = this.getParkingCurrentInfoChargeInfo(parkingViewResultDto.getId());
                            if (parkingChargeInfoResult != null) {
                                //收费详情
                                parkingViewResultDto.setChargeRule(parkingChargeInfoResult.getChargeRule());
                                parkingViewResultDto.setChargeDescription(parkingChargeInfoResult.getChargeDescription());
                            }
                        }
                    }
                }
                //获取停车场图像
                ParkingImageGetRequestDto parkingImageGetRequestDto = new ParkingImageGetRequestDto();
                parkingImageGetRequestDto.setParkingId(requestDto.getId());
                ListResultDto<ParkingImageViewResultDto> parkingImageList = platformParkingInfoService.getParkingImageList(parkingImageGetRequestDto);
                if (parkingImageList.isSuccess()) {
                    parkingViewResultDto.setImageUrls(parkingImageList.getItems());
                }
                objectResultDto.setData(parkingViewResultDto);
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("停车场获取失败,异常信息:{}", e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 可预约停车场数量
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<AppointmentParkingCountResultDto> getAppointmentParkingCount(AppointmentParkingCountRequestDto requestDto) {
        return platformParkingInfoService.getAppointmentParkingCount(requestDto);
    }

    /**
     * 分页获取可预约停车场
     */
    @Override
    public ListResultDto<AppointmentParkingResultDto> getAppointmentParkingList(AppointmentAroundParkingGetRequestDto requestDto) {
        ListResultDto<AppointmentParkingResultDto> pagedResultDto = new ListResultDto<>();
        try {

            final Double maxDistance = 10 * PmsConstant.KILOMETER;
            //获取距离范围
            Double[] aroundPosition = LocationUtil.getAroundPosition(requestDto.getLongitude(), requestDto.getLatitude(), maxDistance);
            if (aroundPosition != null) {

                AppointmentParkingListGetRequestDto appointmentParkingListGetRequestDto = new AppointmentParkingListGetRequestDto();
                appointmentParkingListGetRequestDto.setMinLongitude(aroundPosition[0]);
                appointmentParkingListGetRequestDto.setMaxLongitude(aroundPosition[1]);
                appointmentParkingListGetRequestDto.setMinLatitude(aroundPosition[2]);
                appointmentParkingListGetRequestDto.setMaxLatitude(aroundPosition[3]);
                ListResultDto<AppointParkingInfoResultDto> listResultDto = platformParkingInfoService.selectAppointmentParkingList(appointmentParkingListGetRequestDto);
                if (listResultDto.isFailed()) {
                    return pagedResultDto.makeResult(PmsResultEnum.GET_APPOINTPARKING_PAGELIST.getValue(), PmsResultEnum.GET_APPOINTPARKING_PAGELIST.getComment());
                }
                List<AppointParkingInfoResultDto> parkingInfoList = listResultDto.getItems();
                //循环取附近的停车场
                List<AppointParkingInfoResultDto> parkingInfoArrayList = new ArrayList<>();
                for (AppointParkingInfoResultDto parkingInfo : parkingInfoList) {
                    if (parkingInfo.getLatitude() == null || parkingInfo.getLongitude() == null) {
                        //坐标为空
                        continue;
                    }
                    //获取停车场当前信息
                    ParkingCurrentInfoGetByParkingIdResultDto parkingCurrentInfoGetByParkingIdResultDto = this.getParkingCurrentInfo(parkingInfo.getId());
                    if (null == parkingCurrentInfoGetByParkingIdResultDto) {
                        continue;
                    }
                    if (parkingCurrentInfoGetByParkingIdResultDto.getLotAppointmentAvailable() <= 0) {
                        continue;
                    }
                    //距离
                    Double distance = LocationUtil.getDistanceFromTwoPoints(requestDto.getLatitude(), requestDto.getLongitude(), parkingInfo.getLatitude(), parkingInfo.getLongitude());
                    //程度
                    Integer freeLevel = CommonUtils.calculateFreeLevel(parkingInfo.getLotTotal(), parkingCurrentInfoGetByParkingIdResultDto.getLotAvailable());
                    //距离小于最大距离(10千米)
                    if (distance != null && distance <= maxDistance) {
                        parkingInfo.setDistance(BigDecimal.valueOf(distance).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
                        parkingInfo.setFreeLevel(freeLevel);
                        parkingInfoArrayList.add(parkingInfo);
                    }
                }

                if (!parkingInfoArrayList.isEmpty()) {

                    List<AppointmentParkingResultDto> appointmentParkingResultDtoList = new ArrayList<>();
                    List<AppointParkingInfoResultDto> sortedParkingInfoList = parkingInfoArrayList.stream()
                            .sorted(Comparator.comparing(AppointParkingInfoResultDto::getDistance))
                            .collect(Collectors.toList());

                    for (int i = 0; i < sortedParkingInfoList.size(); i++) {

                        AppointParkingInfoResultDto parkingInfo = sortedParkingInfoList.get(i);

                        AppointmentParkingResultDto appointmentParkingResultDto = modelMapper.map(parkingInfo, AppointmentParkingResultDto.class);
                        appointmentParkingResultDto.setDistance(parkingInfo.getDistance());

                        //距离规范化
                        if (appointmentParkingResultDto.getDistance().compareTo(PmsConstant.KILOMETER) <= 0) {
                            appointmentParkingResultDto.setDistance(BigDecimal.valueOf(appointmentParkingResultDto.getDistance()).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
                            appointmentParkingResultDto.setDistanceUnit(PmsConstant.DISTANCE_UNIT_METER);
                        } else {
                            appointmentParkingResultDto.setDistance(BigDecimal.valueOf(appointmentParkingResultDto.getDistance() / PmsConstant.KILOMETER).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                            appointmentParkingResultDto.setDistanceUnit(PmsConstant.DISTANCE_UNIT_KILOMETER);
                        }
                        //停车场地址
                        appointmentParkingResultDto.setAddress(this.getParkingAddress(parkingInfo.getId()));

                        //获取预约信息
                        ParkingAppointInfoResultDto parkingAppointInfoResultDto = getParkingAppointInfo(parkingInfo.getId());
                        if (parkingAppointInfoResultDto != null) {
                            appointmentParkingResultDto.setLotAppointmentAvailable(parkingAppointInfoResultDto.getLotAppointmentTotal());
                        }

                        ParkingChargeInfoResultDto parkingChargeInfoResult = this.getParkingCurrentInfoChargeInfo(parkingInfo.getId());
                        if (parkingChargeInfoResult != null) {
                            //收费详情
                            appointmentParkingResultDto.setChargeDescription(parkingChargeInfoResult.getChargeDescription());
                        }
                        //停车场是否限免
                        if (!parkingInfo.getChargeFee()) {

                        } else {
                            ParkingCurrentChargeInfoRequestDto parkingChargeInfoGetRequestDto = new ParkingCurrentChargeInfoRequestDto();
                            parkingChargeInfoGetRequestDto.setTenantId(parkingInfo.getTenantId());
                            parkingChargeInfoGetRequestDto.setParkingId(appointmentParkingResultDto.getId());
                            ObjectResultDto<ParkingCurrentChargeInfoResultDto>
                                    parkingChargeInfoObjectResultDto = parkChargeRuleIntegrationService.getParkingChargeRuleCurrentInfo(parkingChargeInfoGetRequestDto);
                            if (parkingChargeInfoObjectResultDto.isSuccess()) {
                                ParkingCurrentChargeInfoResultDto parkingChargeInfoResultDto = parkingChargeInfoObjectResultDto.getData();
                                if (null != parkingChargeInfoResultDto.getDayTopAmount()) {
                                    appointmentParkingResultDto.setDayTopAmount(NumberUtils.amountFen2Yuan(BigDecimal.valueOf(parkingChargeInfoResultDto.getDayTopAmount())));
                                }
                                appointmentParkingResultDto.setFreeTimeLength(parkingChargeInfoResultDto.getFreeTimeLength());
                                appointmentParkingResultDto.setChargeRule(parkingChargeInfoResultDto.getChargeRule());
                            }
                        }
                        appointmentParkingResultDtoList.add(appointmentParkingResultDto);
                    }
                    pagedResultDto.setItems(appointmentParkingResultDtoList);
                }
                pagedResultDto.success();
            }
        } catch (Exception e) {
            log.error("获取可预约停车场失败" + e.getMessage());
            pagedResultDto.makeResult(PmsResultEnum.GET_APPOINTPARKING_PAGELIST.getValue(), PmsResultEnum.GET_APPOINTPARKING_PAGELIST.getComment());
        }
        return pagedResultDto;
    }

    /**
     * 可预约停车场详情
     */
    @Override
    public ObjectResultDto<AppointmentParkingDetailViewResultDto> getAppointmentParkingDetail(AppointmentParkingDetailRequestDto requestDto) {
        ObjectResultDto<AppointmentParkingDetailViewResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            //查询停车场
            ParkingInfoGetRequestDto parkingGetRequestDto = new ParkingInfoGetRequestDto();
            parkingGetRequestDto.setParkingId(requestDto.getParkingId());
            parkingGetRequestDto.setStatus(ParkingStatusEnum.ON_LINE.getValue());
            ObjectResultDto<ParkingInfoResultDto> parkingObject = platformParkingInfoService.getParkInfoById(parkingGetRequestDto);
            if (parkingObject.isFailed() || null == parkingObject.getData()) {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(), PmsResultEnum.PARKING_NOT_FOUND.getComment());
            }
            ParkingInfoResultDto parkingInfo = parkingObject.getData();
            if (!parkingInfo.getSupportAppointment()) {
                throw new Exception("该停车场不支持预约");
            }
            Double distance = LocationUtil.getDistanceFromTwoPoints(
                    requestDto.getLatitude(), requestDto.getLongitude(),
                    parkingInfo.getLatitude(), parkingInfo.getLongitude());

            AppointmentParkingDetailViewResultDto appointmentParkingInfoResultDto = modelMapper.map(parkingInfo, AppointmentParkingDetailViewResultDto.class);
            if (distance != null) {

                //距离规范化
                if (distance.compareTo(PmsConstant.KILOMETER) <= 0) {
                    appointmentParkingInfoResultDto.setDistance(BigDecimal.valueOf(distance).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
                    appointmentParkingInfoResultDto.setDistanceUnit(PmsConstant.DISTANCE_UNIT_METER);
                } else {
                    appointmentParkingInfoResultDto.setDistance(BigDecimal.valueOf(distance / PmsConstant.KILOMETER).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    appointmentParkingInfoResultDto.setDistanceUnit(PmsConstant.DISTANCE_UNIT_KILOMETER);
                }
            }
            //获取可预约泊位
            ParkingCurrentInfoGetByParkingIdResultDto parkingCurrentInfoGetByParkingIdResultDto = this.getParkingCurrentInfo(parkingInfo.getId());
            if (null == parkingCurrentInfoGetByParkingIdResultDto || parkingCurrentInfoGetByParkingIdResultDto.getLotAppointmentAvailable() <= 0) {
                appointmentParkingInfoResultDto.setLotAppointmentAvailable(0);
            } else {
                appointmentParkingInfoResultDto.setLotAppointmentAvailable(parkingCurrentInfoGetByParkingIdResultDto.getLotAppointmentAvailable());
            }
            //获取预约规则信息
            ParkingAppointInfoGetByParkingIdRequestDto parkingAppointInfoGetByParkingIdRequestDto = new ParkingAppointInfoGetByParkingIdRequestDto();
            parkingAppointInfoGetByParkingIdRequestDto.setParkingId(parkingInfo.getId());
            ObjectResultDto<ParkingAppointInfoResultDto> appointInfoResult = platformParkingInfoService.getAppointmentInfoByParkingId(parkingAppointInfoGetByParkingIdRequestDto);
            if (appointInfoResult.isSuccess() && null != appointInfoResult.getData()) {
                appointmentParkingInfoResultDto.setLotAppointmentTotal(appointInfoResult.getData().getLotAppointmentTotal());
                appointmentParkingInfoResultDto.setAppointmentRule(appointInfoResult.getData().getAppointmentRule());
            }
            ParkingChargeInfoResultDto parkingChargeInfoResult = this.getParkingCurrentInfoChargeInfo(parkingInfo.getId());
            if (null != parkingChargeInfoResult) {
                //收费详情
                appointmentParkingInfoResultDto.setChargeDescription(parkingChargeInfoResult.getChargeDescription());
            }
            appointmentParkingInfoResultDto.setAddress(this.getParkingAddress(requestDto.getParkingId()));
            //判断当前时段是否免费
            ParkingCurrentChargeInfoRequestDto parkingChargeInfoGetRequestDto = new ParkingCurrentChargeInfoRequestDto();
            parkingChargeInfoGetRequestDto.setParkingId(requestDto.getParkingId());
            parkingChargeInfoGetRequestDto.setTenantId(parkingInfo.getTenantId());
            ObjectResultDto<ParkingCurrentChargeInfoResultDto>
                    parkingChargeInfoObjectResultDto = parkChargeRuleIntegrationService.getParkingChargeRuleCurrentInfo(parkingChargeInfoGetRequestDto);
            if (parkingChargeInfoObjectResultDto.isSuccess()) {
                ParkingCurrentChargeInfoResultDto parkingChargeInfoResultDto = parkingChargeInfoObjectResultDto.getData();
                appointmentParkingInfoResultDto.setDiscriminateLargeSmall(parkingChargeInfoResultDto.getDiscriminateLargeSmall());
                appointmentParkingInfoResultDto.setLargeVehicleRule(parkingChargeInfoResultDto.getLargeVehicleRule());
                appointmentParkingInfoResultDto.setSmallVehicleRule(parkingChargeInfoResultDto.getSmallVehicleRule());
                if (null != parkingChargeInfoResultDto.getDayTopAmount()) {
                    appointmentParkingInfoResultDto.setDayTopAmount(NumberUtils.amountFen2Yuan(BigDecimal.valueOf(parkingChargeInfoResultDto.getDayTopAmount())));
                }
                appointmentParkingInfoResultDto.setChargeRule(parkingChargeInfoResultDto.getChargeRule());
            }
            //获取停车场图像
            ParkingImageGetRequestDto parkingImageGetRequestDto = new ParkingImageGetRequestDto();
            parkingImageGetRequestDto.setParkingId(requestDto.getParkingId());
            ListResultDto<ParkingImageViewResultDto> parkingImageList = platformParkingInfoService.getParkingImageList(parkingImageGetRequestDto);
            if (parkingImageList.isSuccess()) {
                appointmentParkingInfoResultDto.setImages(parkingImageList.getItems());
            }
            objectResultDto.setData(appointmentParkingInfoResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取可预约停车场详情失败" + e.getMessage());
            objectResultDto.makeResult(PmsResultEnum.GET_APPOINTPARKING_DEATAILS.getValue(), PmsResultEnum.GET_APPOINTPARKING_DEATAILS.getComment());
        }
        return objectResultDto;
    }

    /**
     * 获取可用车位和消息条数
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingAndNotifyResultDto> getParkingNotify(@FluentValid(ParkingAndNotifyRequestDtoValidator.class) ParkingAndNotifyRequestDto requestDto) {
        ObjectResultDto<ParkingAndNotifyResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            //获取可用车位数
            ParkingAndNotifyResultDto parkingAndNotifyResultDto = new ParkingAndNotifyResultDto();
            ParkingByParkingIdGetRequestDto parkingByParkingIdGetRequestDto = new ParkingByParkingIdGetRequestDto();
            parkingByParkingIdGetRequestDto.setParkingId(requestDto.getParkingId());
            ObjectResultDto<ParkingResultDto> lotAvailable = platformParkingInfoService.getParkingLotAvailable(parkingByParkingIdGetRequestDto);
            if (null != lotAvailable.getData()) {
                parkingAndNotifyResultDto.setLotAvailable(lotAvailable.getData().getLotAvailable());
            }
            //获取消息条数
            NotifyCountRequestDto notifyCountRequestDto = new NotifyCountRequestDto();
            notifyCountRequestDto.setParkingId(requestDto.getParkingId());
            ObjectResultDto<NotifyCountResultDto> messageListCount = platformNotifyService.getMessageListCount(notifyCountRequestDto);
            if (null != messageListCount.getData()) {
                parkingAndNotifyResultDto.setNotifyCount(messageListCount.getData().getNotifyCount());
            }
            objectResultDto.setData(parkingAndNotifyResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取可用车位和消息条数失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取停车场当前收费信息
     *
     * @return
     */
    private ParkingChargeInfoResultDto getParkingCurrentInfoChargeInfo(Long parkingId) {
        //根据停车场id获取停车场收费信息
        ParkingChargeInfoGetByParkingIdRequestDto requestDto = new ParkingChargeInfoGetByParkingIdRequestDto();
        requestDto.setParkingId(parkingId);
        ObjectResultDto<ParkingChargeInfoResultDto> parkChargeInfoByParkingId = platformParkingInfoService.getParkChargeInfoByParkingId(requestDto);
        if (parkChargeInfoByParkingId.isSuccess() && null != parkChargeInfoByParkingId.getData()) {
            return parkChargeInfoByParkingId.getData();
        }
        return null;
    }

    /**
     * 获取停车场预约信息
     *
     * @param parkingId
     * @return
     */
    private ParkingAppointInfoResultDto getParkingAppointInfo(Long parkingId) {
        //根据停车场id获取停车场收费信息
        ParkingAppointInfoGetByParkingIdRequestDto parkingAppointInfoGetByParkingIdRequestDto = new ParkingAppointInfoGetByParkingIdRequestDto();
        parkingAppointInfoGetByParkingIdRequestDto.setParkingId(parkingId);
        ObjectResultDto<ParkingAppointInfoResultDto> appointInfoResult = platformParkingInfoService.getAppointmentInfoByParkingId(parkingAppointInfoGetByParkingIdRequestDto);
        if (appointInfoResult.isSuccess() && null != appointInfoResult.getData()) {
            return appointInfoResult.getData();
        }
        return null;
    }

    /**
     * 获取停车场地址
     *
     * @return
     */
    private String getParkingAddress(Long parkingId) {
        String address = "";
        //根据停车场id获取停车场地址
        ParkingGetRequestDto parkingGetRequestDto = new ParkingGetRequestDto();
        parkingGetRequestDto.setId(parkingId);
        ObjectResultDto<String> objectResultDto = platformParkingInfoService.getParkingAddressByParkId(parkingGetRequestDto);
        if (objectResultDto.isSuccess() && null != objectResultDto.getData()) {

            address = objectResultDto.getData();
        }
        return address;
    }

    /**
     * 获取停车场当前信息
     *
     * @return
     */
    private ParkingCurrentInfoGetByParkingIdResultDto getParkingCurrentInfo(Long parkingId) {
        ParkingCurrentInfoGetByParkingIdRequestDto parkingCurrentInfoGetByParkingIdRequestDto = new ParkingCurrentInfoGetByParkingIdRequestDto();
        parkingCurrentInfoGetByParkingIdRequestDto.setParkingId(parkingId);
        ObjectResultDto<ParkingCurrentInfoGetByParkingIdResultDto> parkingCurrentInfoByParkingId = platformParkingInfoService.getParkingCurrentInfoByParkingId(parkingCurrentInfoGetByParkingIdRequestDto);
        if (parkingCurrentInfoByParkingId.isSuccess() && null != parkingCurrentInfoByParkingId.getData()) {
            return parkingCurrentInfoByParkingId.getData();
        }
        return null;
    }

}
