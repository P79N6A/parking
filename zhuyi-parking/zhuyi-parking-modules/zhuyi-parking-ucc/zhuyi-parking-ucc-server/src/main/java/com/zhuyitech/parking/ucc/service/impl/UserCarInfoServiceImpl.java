package com.zhuyitech.parking.ucc.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.PlateNumberUtil;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.lock.redisson.core.Lock;
import com.scapegoat.infrastructure.lock.redisson.core.LockFactory;
import com.scapegoat.infrastructure.lock.redisson.core.LockInfo;
import com.scapegoat.infrastructure.lock.redisson.enumerate.LockType;
import com.zhuyitech.parking.common.enums.PlateColorEnum;
import com.zhuyitech.parking.common.utils.VehicleNumberUtils;
import com.zhuyitech.parking.pms.dto.request.vehicle.VehicleRecordAddRequestDto;
import com.zhuyitech.parking.pms.dto.request.vehicle.VehicleRecordGetRequestDto;
import com.zhuyitech.parking.pms.dto.request.vehicle.VehicleRecordUpdateRequestDto;
import com.zhuyitech.parking.pms.dto.result.VehicleRecordResultDto;
import com.zhuyitech.parking.pms.service.api.VehicleRecordService;
import com.zhuyitech.parking.tool.dto.request.violation.VehicleViolationQueryByCarRequestDto;
import com.zhuyitech.parking.tool.dto.request.violation.VehicleViolationQueryRequestDto;
import com.zhuyitech.parking.tool.dto.result.volation.VehicleViolationCategoryResultDto;
import com.zhuyitech.parking.tool.dto.result.volation.VehicleViolationResultDto;
import com.zhuyitech.parking.tool.enums.TrafficRestrictionRemarksEnum;
import com.zhuyitech.parking.tool.service.api.VehicleViolationService;
import com.zhuyitech.parking.ucc.car.request.*;
import com.zhuyitech.parking.ucc.car.result.*;
import com.zhuyitech.parking.ucc.domain.User;
import com.zhuyitech.parking.ucc.domain.UserCarAuth;
import com.zhuyitech.parking.ucc.domain.UserCarInfo;
import com.zhuyitech.parking.ucc.domain.UserInfo;
import com.zhuyitech.parking.ucc.dto.request.mock.CarAuthImageQueryPagedResultRequestDto;
import com.zhuyitech.parking.ucc.dto.request.mock.CarAuthImageUpdateRequestDto;
import com.zhuyitech.parking.ucc.dto.request.mock.UserCarImageQueryPagedResultRequestDto;
import com.zhuyitech.parking.ucc.dto.request.mock.UserCarImageUpdateRequestDto;
import com.zhuyitech.parking.ucc.dto.request.violation.UserCarViolationQueryRequestDto;
import com.zhuyitech.parking.ucc.dto.result.mock.CarAuthImageResultDto;
import com.zhuyitech.parking.ucc.dto.result.mock.UserCarImageResultDto;
import com.zhuyitech.parking.ucc.enums.CarAuthStatusEnum;
import com.zhuyitech.parking.ucc.enums.UCenterResultEnum;
import com.zhuyitech.parking.ucc.enums.UserAuthStatusEnum;
import com.zhuyitech.parking.ucc.enums.UserCarAuthStatusEnum;
import com.zhuyitech.parking.ucc.service.UserCarAuthCrudService;
import com.zhuyitech.parking.ucc.service.UserCarInfoCrudService;
import com.zhuyitech.parking.ucc.service.UserCrudService;
import com.zhuyitech.parking.ucc.service.UserInfoCrudService;
import com.zhuyitech.parking.ucc.car.UserCarInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户车辆信息维护
 *
 * @author yuzhicheng
 */
@Service("userCarInfoService")
@Slf4j
public class UserCarInfoServiceImpl implements UserCarInfoService {

    private static final Integer LIMIT_QUANTITY = 3;

    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private UserCarInfoCrudService userCarInfoCrudService;

    @Autowired
    private UserCarAuthCrudService userCarAuthCrudService;

    @Autowired
    private VehicleViolationService vehicleViolationService;

    @Autowired
    private VehicleRecordService vehicleRecordService;

    @Autowired
    private UserInfoCrudService userInfoCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LockFactory lockFactory;

    /**
     * 新增车辆信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto addUserCar(UserCarInfoAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {

            if (StringUtils.isEmpty(requestDto.getCarBrand()) || StringUtils.isEmpty(requestDto.getCarType()) || StringUtils.isEmpty(requestDto.getCarImageUrl()) || null == requestDto.getPlateColor()) {

                return resultDto.makeResult(UCenterResultEnum.CAR_INFO_NOT_COMPLETE.getValue(),
                        UCenterResultEnum.CAR_INFO_NOT_COMPLETE.getComment()
                );
            }
            String fullPlateNumber = (StringUtils.isEmpty(requestDto.getPlatePrefix()) ? "" : requestDto.getPlatePrefix())
                    + (StringUtils.isEmpty(requestDto.getPlateInitial()) ? "" : requestDto.getPlateInitial())
                    + (StringUtils.isEmpty(requestDto.getPlateNumber()) ? "" : requestDto.getPlateNumber());
            resultDto = validatePlateNumber(fullPlateNumber, requestDto.getPlateColor());
            if (resultDto.isFailed()) {
                return resultDto;
            }

            LockInfo lockInfo = new LockInfo();
            lockInfo.setType(LockType.Fair);
            lockInfo.setName("lock.redission.zhuyi.parking.user.car." + fullPlateNumber);
            lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
            lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME);
            Lock lock = lockFactory.getLock(lockInfo);
            boolean lockAcquired = false;
            try {

                lockAcquired = lock.acquire();
                if (lockAcquired) {
                    //判断用户是否已有车辆
                    Long userId = requestDto.getSessionIdentity().getUserId();
                    Integer carCount = userCarInfoCrudService.selectCountByUserIdAndStatus(userId, null);
                    UserInfo userInfo = userInfoCrudService.findByUserId(userId);
                    if (null == userInfo) {
                        return resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
                    }
                    if (carCount.compareTo(0) > 0) {
                        /**
                         * 有车
                         * 检验用户是否实名
                         * 已实名,继续绑定,未实名返回
                         */
                        //校验是否实名
                        if (userInfo.getCertificateStatus().compareTo(UserAuthStatusEnum.AUTHENTICATED.getValue()) != 0) {
                            //未实名认证
                            return resultDto.makeResult(UCenterResultEnum.CAR_USER_NOT_REAL_NAME_AUTH.getValue(), UCenterResultEnum.CAR_USER_NOT_REAL_NAME_AUTH.getComment());
                        }
                    } else {
                        /**
                         * 无车,判断该车是否被绑定,若已被绑定则校验该用户是否实名
                         * 已实名继续绑定
                         * 未实名返回实名返回车辆已被他人绑定
                         */
                        List<UserCarInfo> byPlateNumber = userCarInfoCrudService.findByPlateNumber(fullPlateNumber);
                        if (!byPlateNumber.isEmpty()) {
                            //该车牌已被绑定校验该用户是否实名
                            //校验是否实名
                            if (userInfo.getCertificateStatus().compareTo(UserAuthStatusEnum.AUTHENTICATION_NO.getValue()) == 0) {
                                // 未认证返回车辆被他人绑定,请实名认证找回
                                return resultDto.makeResult(UCenterResultEnum.CAR_BIND_BY_OTHER.getValue(), UCenterResultEnum.CAR_BIND_BY_OTHER.getComment());
                            } else if (userInfo.getCertificateStatus().compareTo(UserAuthStatusEnum.AUTHENTICATION_PENDING.getValue()) == 0) {
                                return resultDto.makeResult(UCenterResultEnum.CAR_USER_JUST_REAL_NAME_AUTH.getValue(), UCenterResultEnum.CAR_USER_JUST_REAL_NAME_AUTH.getComment());
                            } else if (userInfo.getCertificateStatus().compareTo(UserAuthStatusEnum.AUTHENTICATION_REJECT.getValue()) == 0) {
                                return resultDto.makeResult(UCenterResultEnum.CAR_USER_REAL_NAME_AUTH_FAIL.getValue(), UCenterResultEnum.CAR_USER_REAL_NAME_AUTH_FAIL.getComment());
                            }
                        }
                    }
                    //车牌只能被同一个用户绑定一次
                    UserCarInfo existUserCarInfo = userCarInfoCrudService.findByPlateNumberAndUserId(fullPlateNumber, requestDto.getSessionIdentity().getUserId());
                    if (existUserCarInfo != null) {
                        resultDto.makeResult(UCenterResultEnum.CAR_AUTHENTICATE_SUBMITTED.getValue(),
                                UCenterResultEnum.CAR_AUTHENTICATE_SUBMITTED.getComment());
                    } else {

                        UserCarInfo userCarInfo = modelMapper.map(requestDto, UserCarInfo.class);
                        userCarInfo.setUserId(requestDto.getSessionIdentity().getUserId());
                        userCarInfo.setStatus(UserCarAuthStatusEnum.CAR_AUTH_UNAUTH.getValue());
                        userCarInfo.setPlateLastNumber(PlateNumberUtil.getLastNumber(fullPlateNumber));
                        userCarInfo.setApplyTime(new Date());
                        boolean insert = userCarInfoCrudService.insert(userCarInfo);
                        //同步到平台车辆表
                        VehicleRecordGetRequestDto vehicleRecordGetRequestDto = new VehicleRecordGetRequestDto();
                        vehicleRecordGetRequestDto.setPlateNumber(fullPlateNumber);
                        vehicleRecordGetRequestDto.setCarBrand(requestDto.getCarBrand());
                        vehicleRecordGetRequestDto.setCarType(requestDto.getCarType());
                        ObjectResultDto<VehicleRecordResultDto> vehicleRecordResultDtoObjectResultDto = vehicleRecordService.getVehicleRecord(vehicleRecordGetRequestDto);
                        if (vehicleRecordResultDtoObjectResultDto.isFailed()) {
                            return vehicleRecordResultDtoObjectResultDto;
                        }
                        if (null == vehicleRecordResultDtoObjectResultDto.getData()) {
                            //insert
                            VehicleRecordAddRequestDto vehicleRecordAddRequestDto = new VehicleRecordAddRequestDto();
                            vehicleRecordAddRequestDto.setCarStyle(userCarInfo.getCarType());
                            vehicleRecordAddRequestDto.setCarBrand(userCarInfo.getCarBrand());
                            vehicleRecordAddRequestDto.setCarType(userCarInfo.getCarType());
                            vehicleRecordAddRequestDto.setPlateNumber(userCarInfo.getFullPlateNumber());
                            vehicleRecordService.addVehicleRecord(vehicleRecordAddRequestDto);
                        } else {
                            //update
                            VehicleRecordResultDto data = vehicleRecordResultDtoObjectResultDto.getData();
                            VehicleRecordUpdateRequestDto vehicleRecordUpdateRequestDto = modelMapper.map(data, VehicleRecordUpdateRequestDto.class);
                            vehicleRecordUpdateRequestDto.setPlateNumber(userCarInfo.getFullPlateNumber());
                            vehicleRecordUpdateRequestDto.setCarBrand(userCarInfo.getCarBrand());
                            vehicleRecordUpdateRequestDto.setCarType(userCarInfo.getCarType());
                            vehicleRecordService.updateVehicleRecord(vehicleRecordUpdateRequestDto);
                        }
                        if (insert) {
                            return resultDto.success();
                        }
                    }
                }
            } finally {
                if (lockAcquired) {
                    lock.release();
                }
            }
        } catch (Exception e) {
            log.error("添加车辆信息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 车牌验证
     *
     * @param plateNumber plateNumber
     * @param plateColor  plateColor
     */
    private ResultDto validatePlateNumber(String plateNumber, Integer plateColor) {

        ResultDto resultDto = new ResultDto();
        if (StringUtils.isEmpty(plateNumber)) {
            return resultDto.makeResult(UCenterResultEnum.CAR_PLATE_NUMBER_EMPTY.getValue(),
                    UCenterResultEnum.CAR_PLATE_NUMBER_EMPTY.getComment());
        }
        PlateColorEnum plateColorEnum = PlateColorEnum.parse(plateColor);
        if (plateColorEnum == null) {
            return resultDto.makeResult(UCenterResultEnum.CAR_PLATE_COLOR_INVALID.getValue(),
                    UCenterResultEnum.CAR_PLATE_COLOR_INVALID.getComment());
        }
        if (plateColor.equals(PlateColorEnum.NEW_ENERGY.getValue())) {
            //新能源号牌
            if (!PlateNumberUtil.isNewEnergyPlateNumber(plateNumber)) {
                return resultDto.makeResult(UCenterResultEnum.CAR_PLATE_NUMBER_INVALID.getValue(),
                        UCenterResultEnum.CAR_PLATE_NUMBER_INVALID.getComment());
            }
        } else {
            if (!PlateNumberUtil.isCommonPlateNumber(plateNumber)) {
                return resultDto.makeResult(UCenterResultEnum.CAR_PLATE_NUMBER_INVALID.getValue(),
                        UCenterResultEnum.CAR_PLATE_NUMBER_INVALID.getComment());
            }
        }
        return resultDto.success();
    }

    /**
     * 修改车辆信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateUserCar(UserCarInfoUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            if (StringUtils.isEmpty(requestDto.getVehicleNumber())) {
                return resultDto.makeResult(UCenterResultEnum.CAR_IDENTIFICATION_NUMBER_EMPTY.getValue(),
                        UCenterResultEnum.CAR_IDENTIFICATION_NUMBER_EMPTY.getComment());
            }
            String fullPlateNumber = (StringUtils.isEmpty(requestDto.getPlatePrefix()) ? "" : requestDto.getPlatePrefix())
                    + (StringUtils.isEmpty(requestDto.getPlateInitial()) ? "" : requestDto.getPlateInitial())
                    + (StringUtils.isEmpty(requestDto.getPlateNumber()) ? "" : requestDto.getPlateNumber());
            resultDto = validatePlateNumber(fullPlateNumber, requestDto.getPlateColor());
            if (resultDto.isFailed()) {
                return resultDto;
            }
            if (!VehicleNumberUtils.checkVIN(requestDto.getVehicleNumber())) {
                return resultDto.makeResult(UCenterResultEnum.CAR_IDENTIFICATION_NUMBER_EMPTY.getValue(),
                        UCenterResultEnum.CAR_IDENTIFICATION_NUMBER_EMPTY.getComment()
                );
            }
            if (StringUtils.isEmpty(requestDto.getEngineNumber())) {
                return resultDto.makeResult(UCenterResultEnum.CAR_ENGINE_NUMBER_EMPTY.getValue(),
                        UCenterResultEnum.CAR_ENGINE_NUMBER_EMPTY.getComment());
            }
            if (StringUtils.isEmpty(requestDto.getLicensePhotoFront())) {
                return resultDto.makeResult(UCenterResultEnum.CAR_DRIVER_LICENSE_FRONT_PHOTO_EMPTY.getValue(),
                        UCenterResultEnum.CAR_DRIVER_LICENSE_FRONT_PHOTO_EMPTY.getComment());
            }
            if (StringUtils.isEmpty(requestDto.getLicensePhotoContrary())) {
                return resultDto.makeResult(UCenterResultEnum.CAR_DRIVER_LICENSE_CONTRARY_PHOTO_EMPTY.getValue(),
                        UCenterResultEnum.CAR_DRIVER_LICENSE_CONTRARY_PHOTO_EMPTY.getComment());
            }
            if (StringUtils.isEmpty(requestDto.getCarBrand()) || StringUtils.isEmpty(requestDto.getCarType()) || StringUtils.isEmpty(requestDto.getCarImageUrl())) {
                return resultDto.makeResult(UCenterResultEnum.CAR_INFO_NOT_COMPLETE.getValue(),
                        UCenterResultEnum.CAR_INFO_NOT_COMPLETE.getComment()
                );
            }
            UserCarInfo userCarInfo = userCarInfoCrudService.findUserCarById(userId, requestDto.getId());
            if (userCarInfo == null) {
                return resultDto.makeResult(UCenterResultEnum.CAR_AUTHENTICATE_NOT_FOUND.getValue(),
                        UCenterResultEnum.CAR_AUTHENTICATE_NOT_FOUND.getComment());
            }
            //车牌号修改的情况下
            if (!fullPlateNumber.equals(userCarInfo.getFullPlateNumber())) {
                //已提交车辆认证申请的车辆不可重复提交
                UserCarInfo existCarAuth = userCarInfoCrudService.findByPlateNumberAndUserId(fullPlateNumber, userId);
                if (existCarAuth != null) {
                    resultDto.makeResult(UCenterResultEnum.CAR_AUTHENTICATE_SUBMITTED.getValue(),
                            UCenterResultEnum.CAR_AUTHENTICATE_SUBMITTED.getComment());
                }
            }
            //审批中的不允许修改
            if (userCarInfo.getStatus().equals(UserCarAuthStatusEnum.CAR_AUTH_PENDING.getValue())) {
                return resultDto.makeResult(UCenterResultEnum.USER_AUTHENTICATE_CANNOT_MODIFY.getValue(),
                        UCenterResultEnum.USER_AUTHENTICATE_CANNOT_MODIFY.getComment());
            } else if (userCarInfo.getStatus().equals(UserCarAuthStatusEnum.CAR_AUTH_ALREADY.getValue())) {
                //审批通过的不允许修改车牌号
                String oldPlateNumber = userCarInfo.getFullPlateNumber();
                if (!oldPlateNumber.equals(fullPlateNumber)) {
                    return resultDto.makeResult(UCenterResultEnum.CAR_CANNOT_MODIFY.getValue(),
                            UCenterResultEnum.CAR_CANNOT_MODIFY.getComment());
                }
            }
            userCarInfo.setCarBrand(requestDto.getCarBrand());
            userCarInfo.setCarType(requestDto.getCarType());
            userCarInfo.setPlatePrefix(requestDto.getPlatePrefix());
            userCarInfo.setPlateInitial(requestDto.getPlateInitial());
            userCarInfo.setPlateNumber(requestDto.getPlateNumber());
            userCarInfo.setVehicleNumber(requestDto.getVehicleNumber());
            userCarInfo.setEngineNumber(requestDto.getEngineNumber());
            userCarInfo.setRegisterDate(requestDto.getRegisterDate());
            userCarInfo.setCarImageUrl(requestDto.getCarImageUrl());
            userCarInfo.setLicensePhotoFront(requestDto.getLicensePhotoFront());
            userCarInfo.setLicensePhotoContrary(requestDto.getLicensePhotoContrary());
            userCarInfo.setPlateLastNumber(PlateNumberUtil.getLastNumber(fullPlateNumber));
            if (userCarInfo.getStatus().equals(UserCarAuthStatusEnum.AUTHENTICATION_REJECT.getValue())) {
                userCarInfo.setStatus(UserCarAuthStatusEnum.CAR_AUTH_PENDING.getValue());
                userCarInfo.setApplyTime(new Date());
            }
            userCarInfo.setLastModifierUserId(requestDto.getSessionIdentity().getUserId());
            userCarInfoCrudService.updateById(userCarInfo);
            //同步平台车辆
            VehicleRecordGetRequestDto vehicleRecordGetRequestDto = new VehicleRecordGetRequestDto();
            vehicleRecordGetRequestDto.setPlateNumber(userCarInfo.getFullPlateNumber());
            ObjectResultDto<VehicleRecordResultDto> vehicleRecordResultDtoObjectResultDto = vehicleRecordService.getVehicleRecord(vehicleRecordGetRequestDto);
            VehicleRecordResultDto data = vehicleRecordResultDtoObjectResultDto.getData();
            VehicleRecordUpdateRequestDto vehicleRecordUpdateRequestDto = modelMapper.map(data, VehicleRecordUpdateRequestDto.class);
            vehicleRecordUpdateRequestDto.setPlateNumber(userCarInfo.getFullPlateNumber());
            vehicleRecordUpdateRequestDto.setCarBrand(userCarInfo.getCarBrand());
            vehicleRecordUpdateRequestDto.setCarType(userCarInfo.getCarType());
            vehicleRecordUpdateRequestDto.setVehicleNumber(userCarInfo.getVehicleNumber());
            vehicleRecordUpdateRequestDto.setEngineNumber(userCarInfo.getEngineNumber());
            vehicleRecordService.updateVehicleRecord(vehicleRecordUpdateRequestDto);

            return resultDto.success();
        } catch (Exception e) {
            log.error("修改车辆认证信息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取车辆信息
     */
    @Override
    public ObjectResultDto<UserCarInfoResultDto> getUserPlateNumberList(UserCarInfoGetRequestDto requestDto) {
        ObjectResultDto<UserCarInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            UserCarInfo userCarInfo = userCarInfoCrudService.selectById(requestDto.getId());
            if (userCarInfo != null) {
                UserCarInfoResultDto userCarAuthResultDto = modelMapper.map(userCarInfo, UserCarInfoResultDto.class);
                objectResultDto.setData(userCarAuthResultDto);
                return objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("获取车辆认证信息失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    @Override
    public ListResultDto<UserPlateNumberResultDto> getUserPlateNumberList(UserPlateNumberListGetRequestDto requestDto) {
        ListResultDto<UserPlateNumberResultDto> listResultDto = new ListResultDto<>();
        try {
            Long currentUserId = requestDto.getSessionIdentity().getUserId();
            EntityWrapper<UserCarInfo> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("userId", currentUserId);
            entityWrapper.orderBy("status");
            entityWrapper.orderBy("creationTime");
            List<UserCarInfo> carInfoList = userCarInfoCrudService.selectList(entityWrapper);
            if (null != carInfoList && carInfoList.size() > 0) {
                List<UserPlateNumberResultDto> userCarAuthResultDtos = modelMapper.map(carInfoList, new TypeToken<List<UserPlateNumberResultDto>>() {
                }.getType());
                listResultDto.setItems(userCarAuthResultDtos);
            }
            return listResultDto.success();
        } catch (Exception e) {
            log.error("获取用户车辆失败:" + e.getMessage());
            listResultDto.makeResult(UCenterResultEnum.USER_CARINFO_GET_ERROR.getValue(), UCenterResultEnum.USER_CARINFO_GET_ERROR.getComment());
        }
        return listResultDto;
    }

    /**
     * 获取用户名下所有的车牌
     *
     * @param requestDto UserPlateNumberListGetRequestDto
     * @return UserCarPlateNumberResultDto
     */
    @Override
    public ListResultDto<UserCarPlateNumberResultDto> userPlateNumberList(UserPlateNumberListGetRequestDto requestDto) {
        ListResultDto<UserCarPlateNumberResultDto> listResultDto = new ListResultDto<>();
        try {
            Long currentUserId = requestDto.getSessionIdentity().getUserId();
            List<UserCarInfo> carInfoList = userCarInfoCrudService.findByUserId(currentUserId);
            if (CollectionUtils.isNotEmpty(carInfoList)) {
                List<UserCarPlateNumberResultDto> userCarAuthResultDtos = modelMapper.map(carInfoList, new TypeToken<List<UserCarPlateNumberResultDto>>() {
                }.getType());
                listResultDto.setItems(userCarAuthResultDtos);
            }
            return listResultDto.success();
        } catch (Exception e) {
            log.error("获取用户车辆失败:" + e.getMessage());
            listResultDto.makeResult(UCenterResultEnum.USER_CARINFO_GET_ERROR.getValue(), UCenterResultEnum.USER_CARINFO_GET_ERROR.getComment());
        }
        return listResultDto;
    }

    /**
     * 获取用户名下所有的车牌
     *
     * @param userId UserPlateNumberListGetRequestDto
     * @return UserCarPlateNumberResultDto
     */
    @Override
    public ListResultDto<UserCarPlateNumberMyResultDto> getPlateNumbers(String userId) {
        ListResultDto<UserCarPlateNumberMyResultDto> listResultDto = new ListResultDto<>();
        try {
            Long currentUserId =Long.valueOf(userId);
            List<UserCarInfo> carInfoList = userCarInfoCrudService.findByUserId(currentUserId);
            if (CollectionUtils.isNotEmpty(carInfoList)) {
                List<UserCarPlateNumberMyResultDto> userCarAuthResultDtos = modelMapper.map(carInfoList, new TypeToken<List<UserCarPlateNumberMyResultDto>>() {
                }.getType());
                listResultDto.setItems(userCarAuthResultDtos);
            }
            return listResultDto.success();
        } catch (Exception e) {
            log.error("获取用户车辆失败:" + e.getMessage());
            listResultDto.makeResult(UCenterResultEnum.USER_CARINFO_GET_ERROR.getValue(), UCenterResultEnum.USER_CARINFO_GET_ERROR.getComment());
        }
        return listResultDto;
    }

    /**
     * 获取当前用户所有的车辆信息
     *
     * @param requestDto requestDto
     */
    @Override
    public ListResultDto<UserCarSummaryViewResultDto> getUserCarSummaryList(UserCarSummaryViewListGetRequestDto requestDto) {
        ListResultDto<UserCarSummaryViewResultDto> listResultDto = new ListResultDto<>();
        try {
            Long currentUserId = requestDto.getSessionIdentity().getUserId();
            //已认证车辆
            EntityWrapper<UserCarInfo> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("userId", currentUserId);
            entityWrapper.orderBy("defaultCar");
            List<UserCarInfo> carInfoList = userCarInfoCrudService.selectList(entityWrapper);
            if (CollectionUtils.isNotEmpty(carInfoList)) {
                List<UserCarSummaryViewResultDto> userCarAuthResultDtos = modelMapper.map(carInfoList, new TypeToken<List<UserCarSummaryViewResultDto>>() {
                }.getType());
                listResultDto.setItems(userCarAuthResultDtos);
            }
            return listResultDto.success();
        } catch (Exception e) {
            log.error("获取用户车辆失败:" + e.getMessage());
            listResultDto.makeResult(UCenterResultEnum.USER_CARINFO_GET_ERROR.getValue(), UCenterResultEnum.USER_CARINFO_GET_ERROR.getComment());
        }
        return listResultDto;
    }

    /**
     * 获取当前用户车辆信息
     */
    @Override
    public ObjectResultDto<UserCarViewResultDto> getUserCar(CurrentUserCarGetRequestDto requestDto) {
        ObjectResultDto<UserCarViewResultDto> resultDto = new ObjectResultDto<>();
        try {
            UserCarInfo userCarInfo = userCarInfoCrudService.findUserCarById(requestDto.getSessionIdentity().getUserId(), requestDto.getId());
            if (null == userCarInfo) {
                return resultDto.makeResult(UCenterResultEnum.CAR_UNBIND.getValue(), UCenterResultEnum.CAR_UNBIND.getComment());
            }
            UserCarViewResultDto userCarViewResultDto = modelMapper.map(userCarInfo, UserCarViewResultDto.class);
            resultDto.setData(userCarViewResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("获取用户车辆失败:" + e.getMessage());
            return resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取当前用户车辆信息
     */
    @Override
    public ObjectResultDto<UserCarViewResultDto> getUserCar(UserCarInfoGetByPlateNumberRequestDto requestDto) {
        ObjectResultDto<UserCarViewResultDto> resultDto = new ObjectResultDto<>();
        try {

            EntityWrapper<UserCarInfo> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("userId", requestDto.getUserId());
            entityWrapper.eq("concat(platePrefix,plateInitial,plateNumber)", requestDto.getPlateNumber());
            if (requestDto.getPlateColor() != null) {
                entityWrapper.eq("plateColor", requestDto.getPlateColor());
            }
            entityWrapper.orderBy("defaultCar");
            entityWrapper.last("LIMIT 1");
            UserCarInfo userCarInfo = userCarInfoCrudService.selectOne(entityWrapper);
            if (userCarInfo != null) {
                UserCarViewResultDto userCarViewResultDto = modelMapper.map(userCarInfo, UserCarViewResultDto.class);
                resultDto.setData(userCarViewResultDto);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取用户车辆失败:" + e.getMessage());
            return resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取当前用户车辆列表
     */
    @Override
    public ListResultDto<UserCarViewResultDto> getUserCarList(UserPlateNumberListGetRequestDto requestDto) {
        ListResultDto<UserCarViewResultDto> resultDto = new ListResultDto<>();
        try {
            Long currentUserId = requestDto.getSessionIdentity().getUserId();
            EntityWrapper<UserCarInfo> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("userId", currentUserId);
            entityWrapper.orderBy("status");
            entityWrapper.orderBy("creationTime", true);
            List<UserCarInfo> carInfoList = userCarInfoCrudService.selectList(entityWrapper);
            if (!carInfoList.isEmpty()) {
                List<UserCarViewResultDto> userCarAuthResultDtos = modelMapper.map(carInfoList, new TypeToken<List<UserCarViewResultDto>>() {
                }.getType());
                resultDto.setItems(userCarAuthResultDtos);
            }
            return resultDto.success();
        } catch (Exception e) {
            log.error("获取用户车辆失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 查询用户车辆违章记录
     *
     * @param requestDto requestDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<UserCarViolationQueryResultDto> queryCarViolations(UserCarViolationQueryRequestDto requestDto) {
        ObjectResultDto<UserCarViolationQueryResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            Long currentUserId = requestDto.getSessionIdentity().getUserId();
            //已认证车辆
            EntityWrapper<UserCarInfo> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("userId", currentUserId);
            entityWrapper.eq("id", requestDto.getCarId());
            UserCarInfo userCarInfo = userCarInfoCrudService.selectOne(entityWrapper);
            if (userCarInfo == null) {
                return objectResultDto.makeResult(UCenterResultEnum.CAR_UNBIND.getValue(), UCenterResultEnum.CAR_UNBIND.getComment());
            }
            if (!userCarInfo.getStatus().equals(CarAuthStatusEnum.AUTHENTICATED.getValue())) {
                return objectResultDto.makeResult(UCenterResultEnum.CAR_NOT_AUTHENTICATE_VIOLATION_NOT_SUPPORT.getValue(),
                        UCenterResultEnum.CAR_NOT_AUTHENTICATE_VIOLATION_NOT_SUPPORT.getComment());
            }
            if (StringUtils.isEmpty(userCarInfo.getEngineNumber()) || StringUtils.isEmpty(userCarInfo.getVehicleNumber())) {
                return objectResultDto.makeResult(UCenterResultEnum.CAR_INFO_NOT_COMPLETE.getValue(),
                        UCenterResultEnum.CAR_INFO_NOT_COMPLETE.getComment());
            }
            //如果是首次查询违章，始终实时查询
            //如果上次实时查询不是今天,也实时查询
            if (userCarInfo.getViolationUpdateTime() == null
                    || (userCarInfo.getViolationUpdateTime() != null
                    && !DateTimeUtils.isSameDayWithToday(userCarInfo.getViolationUpdateTime()))) {

                LockInfo lockInfo = new LockInfo();
                lockInfo.setType(LockType.Fair);
                lockInfo.setName("lock.redission.zhuyi.parking.car.violation." + userCarInfo.getFullPlateNumber());
                lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
                lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME);
                Lock lock = lockFactory.getLock(lockInfo);
                boolean lockAcquired = false;
                try {

                    lockAcquired = lock.acquire();
                    if (lockAcquired) {

                        VehicleViolationQueryRequestDto vehicleViolationQueryRequestDto = new VehicleViolationQueryRequestDto();
                        vehicleViolationQueryRequestDto.setCarId(userCarInfo.getId());
                        vehicleViolationQueryRequestDto.setCarType(userCarInfo.getCarLevel());
                        vehicleViolationQueryRequestDto.setPlateNumber(userCarInfo.getFullPlateNumber());
                        vehicleViolationQueryRequestDto.setVehicleNumber(userCarInfo.getVehicleNumber());
                        vehicleViolationQueryRequestDto.setEngineNo(userCarInfo.getEngineNumber());
                        ObjectResultDto<VehicleViolationResultDto> violationResultDto =
                                vehicleViolationService.queryVehicleViolation(vehicleViolationQueryRequestDto);
                        if (violationResultDto.isFailed() || violationResultDto.getData() == null) {

                            UserCarViolationQueryResultDto userCarViolationQueryResultDto = new UserCarViolationQueryResultDto();
                            userCarViolationQueryResultDto.setPlateNumber(userCarInfo.getFullPlateNumber());
                            userCarViolationQueryResultDto.setStatus(userCarInfo.getStatus());
                            userCarViolationQueryResultDto.setTotalFine(BigDecimal.ZERO);
                            userCarViolationQueryResultDto.setTotalPoints(0);
                            userCarViolationQueryResultDto.setUntreatedCount(0);
                            userCarViolationQueryResultDto.setTotalCount(0);
                            userCarViolationQueryResultDto.setViolationUpdateTime(new Date());
                            objectResultDto.setData(userCarViolationQueryResultDto);
                            objectResultDto.success();
                            return objectResultDto;
                        } else {
                            VehicleViolationResultDto vehicleViolationResultDto = violationResultDto.getData();
                            Date now = new Date();

                            //更新用户车辆违章信息
                            UserCarInfo updateUserCarInfo = new UserCarInfo();
                            updateUserCarInfo.setTotalCount(vehicleViolationResultDto.getTotalCount());
                            updateUserCarInfo.setTotalFine(vehicleViolationResultDto.getTotalFine());
                            updateUserCarInfo.setUntreatedCount(vehicleViolationResultDto.getUntreatedCount());
                            updateUserCarInfo.setTotalPoints(vehicleViolationResultDto.getTotalPoints());
                            updateUserCarInfo.setViolationUpdateCount(1);
                            updateUserCarInfo.setViolationUpdateTime(now);
                            EntityWrapper<UserCarInfo> ew = new EntityWrapper<>();
                            ew.eq("id", userCarInfo.getId());
                            userCarInfoCrudService.update(updateUserCarInfo, ew);

                            UserCarViolationQueryResultDto violationQueryResultDto = new UserCarViolationQueryResultDto();
                            violationQueryResultDto.setStatus(userCarInfo.getStatus());
                            violationQueryResultDto.setPlateNumber(userCarInfo.getFullPlateNumber());
                            violationQueryResultDto.setTotalCount(vehicleViolationResultDto.getTotalCount());
                            violationQueryResultDto.setTotalFine(vehicleViolationResultDto.getTotalFine());
                            violationQueryResultDto.setUntreatedCount(vehicleViolationResultDto.getUntreatedCount());
                            violationQueryResultDto.setTotalPoints(vehicleViolationResultDto.getTotalPoints());
                            violationQueryResultDto.setViolationUpdateTime(now);
                            //未处理违章
                            violationQueryResultDto.setUntreatedViolations(vehicleViolationResultDto.getUntreatedViolations());
                            //已处理违章
                            violationQueryResultDto.setTreatedViolations(vehicleViolationResultDto.getTreatedViolations());
                            objectResultDto.setData(violationQueryResultDto);
                        }
                    }
                } finally {
                    if (lockAcquired) {
                        lock.release();
                    }
                }
            } else {

                //从本地查询违章信息
                VehicleViolationQueryByCarRequestDto vehicleViolationQueryByCarRequestDto = new VehicleViolationQueryByCarRequestDto();
                vehicleViolationQueryByCarRequestDto.setCarId(userCarInfo.getId());
                ObjectResultDto<VehicleViolationCategoryResultDto> violationCategoryResultDtoObjectResultDto =
                        vehicleViolationService.queryLocalVehicleViolation(vehicleViolationQueryByCarRequestDto);
                if (violationCategoryResultDtoObjectResultDto.isFailed() || violationCategoryResultDtoObjectResultDto.getData() == null) {
                    UserCarViolationQueryResultDto violationQueryResultDto = new UserCarViolationQueryResultDto();
                    violationQueryResultDto.setStatus(userCarInfo.getStatus());
                    violationQueryResultDto.setPlateNumber(userCarInfo.getFullPlateNumber());
                    violationQueryResultDto.setTotalCount(userCarInfo.getTotalCount());
                    violationQueryResultDto.setTotalFine(userCarInfo.getTotalFine());
                    violationQueryResultDto.setUntreatedCount(userCarInfo.getUntreatedCount());
                    violationQueryResultDto.setTotalPoints(userCarInfo.getTotalPoints());
                    violationQueryResultDto.setViolationUpdateTime(new Date());
                    objectResultDto.setData(violationQueryResultDto);
                    objectResultDto.success();
                    return objectResultDto;
                }
                VehicleViolationCategoryResultDto vehicleViolationCategoryResultDto = violationCategoryResultDtoObjectResultDto.getData();
                UserCarViolationQueryResultDto violationQueryResultDto = new UserCarViolationQueryResultDto();
                violationQueryResultDto.setStatus(userCarInfo.getStatus());
                violationQueryResultDto.setPlateNumber(userCarInfo.getFullPlateNumber());
                violationQueryResultDto.setTotalCount(userCarInfo.getTotalCount());
                violationQueryResultDto.setTotalFine(userCarInfo.getTotalFine());
                violationQueryResultDto.setUntreatedCount(userCarInfo.getUntreatedCount());
                violationQueryResultDto.setTotalPoints(userCarInfo.getTotalPoints());
                violationQueryResultDto.setViolationUpdateTime(userCarInfo.getViolationUpdateTime());
                //未处理违章
                violationQueryResultDto.setUntreatedViolations(vehicleViolationCategoryResultDto.getUntreatedViolations());
                //已处理违章
                violationQueryResultDto.setTreatedViolations(vehicleViolationCategoryResultDto.getTreatedViolations());
                objectResultDto.setData(violationQueryResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户车辆失败:" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 查询用户是否有绑定的车辆
     *
     * @param requestDto UserHasCarRequestDto
     * @return UserHasCarResultDto
     */
    @Override
    public ObjectResultDto<UserHasCarResultDto> userHasCar(UserHasCarRequestDto requestDto) {
        ObjectResultDto<UserHasCarResultDto> resultDto = new ObjectResultDto<>();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            UserHasCarResultDto userHasCarResultDto = new UserHasCarResultDto();
            Integer carCount = userCarInfoCrudService.selectCountByUserIdAndStatus(userId, null);
            if (carCount.compareTo(0) > 0) {
                userHasCarResultDto.setHasCar(true);
                Integer authedCount = userCarInfoCrudService.selectCountByUserIdAndStatus(userId, UserCarAuthStatusEnum.CAR_AUTH_ALREADY.getValue());
                if (authedCount.compareTo(0) > 0) {
                    userHasCarResultDto.setHasAuthenticatedCar(true);
                } else {
                    userHasCarResultDto.setHasAuthenticatedCar(false);
                }
            } else {
                userHasCarResultDto.setHasCar(false);
                userHasCarResultDto.setHasAuthenticatedCar(false);
            }
            resultDto.setData(userHasCarResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("查询用户是否有绑定的车辆失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 查询用户车辆个数
     *
     * @param requestDto UserHasCarRequestDto
     * @return UserHasCarResultDto
     */
    @Override
    public ObjectResultDto<UserCarCountResultDto> userCarCount(UserCarCountRequestDto requestDto) {
        ObjectResultDto<UserCarCountResultDto> resultDto = new ObjectResultDto<>();
        try {

            Long userId = requestDto.getSessionIdentity().getUserId();
            UserCarCountResultDto userCarCountResultDto = new UserCarCountResultDto();
            userCarCountResultDto.setCount(userCarInfoCrudService.selectCountByUserIdAndStatus(userId, null));
            resultDto.setData(userCarCountResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("查询用户车辆数量失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 查询用户绑定的车辆是否超过绑定限制
     *
     * @param requestDto UserHasCarRequestDto
     * @return UserHasCarResultDto
     */
    @Override
    public ObjectResultDto<UserCarExceedLimitResultDto> userCarExceedLimit(UserCarExceedLimitRequestDto requestDto) {
        ObjectResultDto<UserCarExceedLimitResultDto> resultDto = new ObjectResultDto<>();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            UserCarExceedLimitResultDto userCarExceedLimitResultDto = new UserCarExceedLimitResultDto();
            Integer carCount = userCarInfoCrudService.selectCountByUserIdAndStatus(userId, null);
            if (carCount.compareTo(LIMIT_QUANTITY) > 0) {
                userCarExceedLimitResultDto.setExceed(true);
            } else {
                userCarExceedLimitResultDto.setExceed(false);
            }
            resultDto.setData(userCarExceedLimitResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("查询用户绑定的车辆是否超过绑定限制失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 用户解绑车辆
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto userUnbindCar(UserCarInfoUnBindRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            Long id = requestDto.getId();
            UserCarInfo userCarInfo = userCarInfoCrudService.selectById(id);
            if (null == userCarInfo) {
                return resultDto.makeResult(UCenterResultEnum.CAR_UNBIND.getValue(), UCenterResultEnum.CAR_UNBIND.getComment());
            }
            if (!userId.equals(userCarInfo.getUserId())) {
                return resultDto.makeResult(UCenterResultEnum.CAR_NOT_BELONG_USER.getValue(), UCenterResultEnum.CAR_NOT_BELONG_USER.getComment());
            }
            if (userCarInfo.getStatus().equals(UserCarAuthStatusEnum.CAR_AUTH_PENDING.getValue())) {
                //认证中车辆不允许修改
                return resultDto.makeResult(UCenterResultEnum.CAR_AUTH_PENDING_UNBIND_ERROR.getValue(), UCenterResultEnum.CAR_AUTH_PENDING_UNBIND_ERROR.getComment());
            }
            //解绑车辆删除认证记录
            EntityWrapper<UserCarAuth> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("userId", userId);
            entityWrapper.eq("plateNumber", userCarInfo.getFullPlateNumber());
            userCarAuthCrudService.delete(entityWrapper);
            userCarInfoCrudService.deleteById(id);
            resultDto.success();
        } catch (Exception e) {
            log.error("用户解绑车辆失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 用户修改默认车辆
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto userUpdateDefaultCar(UserCarUpdateDefaultCarRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (null == requestDto.getCarId()) {
                return resultDto.makeResult(UCenterResultEnum.REQUEST_EMPTY.getValue(), UCenterResultEnum.REQUEST_EMPTY.getComment());
            }
            UserCarInfo userCarInfo = userCarInfoCrudService.selectById(requestDto.getCarId());
            if (null == userCarInfo) {
                return resultDto.makeResult(UCenterResultEnum.CAR_UNBIND.getValue(), UCenterResultEnum.CAR_UNBIND.getComment());
            }
            EntityWrapper<UserCarInfo> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getCarId());
            entityWrapper.eq("userId", requestDto.getSessionIdentity().getUserId());
            UserCarInfo defaultCar = new UserCarInfo();
            defaultCar.setDefaultCar(Boolean.TRUE);
            userCarInfoCrudService.update(defaultCar, entityWrapper);
            EntityWrapper<UserCarInfo> entityWrapperUpdate = new EntityWrapper<>();
            entityWrapperUpdate.eq("userId", requestDto.getSessionIdentity().getUserId());
            entityWrapperUpdate.ne("id", requestDto.getCarId());
            UserCarInfo notDefaultCar = new UserCarInfo();
            notDefaultCar.setDefaultCar(false);
            userCarInfoCrudService.update(notDefaultCar, entityWrapperUpdate);

            resultDto.success();
        } catch (Exception e) {
            log.error("用户修改默认车辆失败:" + e.getMessage());
            resultDto.failed();
        }
        return null;
    }

    @Override
    public ListResultDto<ComboboxItemDto> userPlateColor() {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto(String.valueOf(PlateColorEnum.BLUE.getValue()), PlateColorEnum.BLUE.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(PlateColorEnum.YELLOW.getValue()), PlateColorEnum.YELLOW.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(PlateColorEnum.NEW_ENERGY.getValue()), PlateColorEnum.NEW_ENERGY.getComment(), false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取车牌颜色失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 根据限行尾号和车牌前缀获取车辆信息
     */
    @Override
    public PagedResultDto<TrafficRestrictionCarInfoResultDto> getForTrafficRestriction(UserCarInfoGetByTailNumberRequestDto requestDto) {
        PagedResultDto<TrafficRestrictionCarInfoResultDto> resultDto = new PagedResultDto<>();
        try {

            String tailNumber = requestDto.getTailNumber();
            Integer limitPattern = requestDto.getLimitPattern();
            String[] split = tailNumber.split(",");
            Integer firstNumber = Integer.parseInt(split[0]);
            Integer secondNumber = null;
            if (split.length > 1) {
                secondNumber = Integer.parseInt(split[1]);
            }
            EntityWrapper<UserCarInfo> entityWrapper = new EntityWrapper<>();
            //处理车牌前缀
            String[] cityPrefix = requestDto.getCityPrefix().split(",");
            String prefixSql = "concat(platePrefix,plateInitial) like '" + cityPrefix[0] + "%'";
            if (cityPrefix.length > 1) {
                for (int i = 1; i < cityPrefix.length; i++) {
                    prefixSql += " OR concat(platePrefix,plateInitial) like '" + cityPrefix[i] + "%'";
                }
            }
            String numberSql = null;
            if (null == secondNumber) {
                //只有一个限行号码
                if (limitPattern == TrafficRestrictionRemarksEnum.LAST_NUMBER.getValue()) {
                    //尾号字母按最后一位数字处理
                    numberSql = ("(plateLastNumber = " + firstNumber + ")");
                } else if (limitPattern == TrafficRestrictionRemarksEnum.ZERO_TAIL_NUMBER.getValue()) {
                    //尾号字母按0处理
                    if (firstNumber == 0) {
                        //只有一个限行号码且为0
                        numberSql = ("(plateLastNumber= " + firstNumber + " OR plateNumber REGEXP '[a-z]$')");
                    } else {
                        // 只有一个限行号码不为0
                        numberSql = ("(plateNumber  like '____" + firstNumber + "')");
                    }
                } else if (limitPattern == TrafficRestrictionRemarksEnum.FOUR_TAIL_NUMBER.getValue()) {
                    //尾号字母按4处理
                    if (firstNumber == 4) {
                        //只有一个限行号码且为4
                        numberSql = ("(plateLastNumber= " + firstNumber + " OR plateNumber REGEXP '[a-z]$')");
                    } else {
                        // 只有一个限行号码不为4
                        numberSql = ("(plateNumber  like '____" + firstNumber + "')");
                    }
                }
            } else {
                //两个限号
                if (limitPattern == TrafficRestrictionRemarksEnum.LAST_NUMBER.getValue()) {
                    //尾号字母按最后一位数字处理
                    numberSql = ("(plateLastNumber = " + firstNumber + " OR plateLastNumber = " + secondNumber + ")");
                } else if (limitPattern == TrafficRestrictionRemarksEnum.ZERO_TAIL_NUMBER.getValue()) {
                    //尾号字母按0处理
                    if (firstNumber == 0 || secondNumber == 0) {
                        //限行号码为0
                        numberSql = ("(plateLastNumber= " + firstNumber + " OR plateLastNumber= " + secondNumber + " OR plateNumber REGEXP '[a-z]$') ");
                    } else {
                        //限行号码不为0
                        numberSql = ("(plateNumber  like '____" + firstNumber + "' OR plateNumber like '____" + secondNumber + "')");
                    }
                } else if (limitPattern == TrafficRestrictionRemarksEnum.FOUR_TAIL_NUMBER.getValue()) {
                    //尾号字母按4处理
                    if (firstNumber == 4 || secondNumber == 4) {
                        //限行号码且为4
                        numberSql = ("(plateLastNumber= " + firstNumber + " OR plateLastNumber= " + secondNumber + " OR plateNumber REGEXP '[a-z]$')");
                    } else {
                        //限行号码不为4
                        numberSql = ("(plateNumber  like '____" + firstNumber + "' OR plateNumber like '____" + secondNumber + "')");
                    }
                }
            }
            entityWrapper.eq("status", UserCarAuthStatusEnum.CAR_AUTH_ALREADY.getValue());
            if (StringUtils.isNotEmpty(numberSql)) {
                entityWrapper.and(numberSql);
            }
            entityWrapper.and("(" + prefixSql + ")");
            Page<UserCarInfo> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<UserCarInfo> userCarInfoPage = userCarInfoCrudService.selectPage(page, entityWrapper);
            if (null == userCarInfoPage.getRecords() || userCarInfoPage.getRecords().isEmpty()) {
                return resultDto.success();
            }
            List<TrafficRestrictionCarInfoResultDto> list = modelMapper.map(userCarInfoPage.getRecords(), new TypeToken<List<TrafficRestrictionCarInfoResultDto>>() {
            }.getType());
            resultDto.setPageNo(userCarInfoPage.getCurrent());
            resultDto.setPageSize(userCarInfoPage.getSize());
            resultDto.setTotalCount(userCarInfoPage.getTotal());
            resultDto.setItems(list);
            resultDto.success();
        } catch (Exception e) {
            log.error("根据限行尾号和车牌前缀获取车辆信息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据车牌获取用户信息
     *
     * @param requestDto requestDto
     */
    @Override
    public ListResultDto<CarOwnerGetResultDto> getCarOwnerList(CarOwnerListGetRequestDto requestDto) {

        ListResultDto<CarOwnerGetResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<UserCarInfo> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("concat(platePrefix,plateInitial,plateNumber)", requestDto.getPlateNumber());
            if (requestDto.getPlateColor() != null) {
                entityWrapper.eq("plateColor", requestDto.getPlateColor());
            }
            entityWrapper.orderBy("defaultCar");
            List<UserCarInfo> userCarInfoList = userCarInfoCrudService.selectList(entityWrapper);
            if (CollectionUtils.isNotEmpty(userCarInfoList)) {

                List<CarOwnerGetResultDto> carOwnerResultDtoList = new ArrayList<>();
                for (UserCarInfo userCarInfo : userCarInfoList) {
                    User user = userCrudService.selectById(userCarInfo.getUserId());
                    if (user != null) {
                        CarOwnerGetResultDto ownerGetResultDto = new CarOwnerGetResultDto();
                        ownerGetResultDto.setPhoneNumber(user.getPhoneNumber());
                        ownerGetResultDto.setUserId(user.getId());
                        ownerGetResultDto.setUsername(user.getUsername());
                        carOwnerResultDtoList.add(ownerGetResultDto);
                    }
                }
                listResultDto.setItems(carOwnerResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取用户信息失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 通过车牌获取车辆信息
     */
    @Override
    public ListResultDto<UserCarInfoResultDto> getCarInfoListByPlateNumber(UserCarInfoGetOnlyByPlateNumberRequestDto
                                                                                   requestDto) {
        ListResultDto<UserCarInfoResultDto> objectResultDto = new ListResultDto<>();
        try {

            List<UserCarInfo> userCarInfoList = userCarInfoCrudService.findByPlateNumber(requestDto.getPlateNumber());
            if (CollectionUtils.isNotEmpty(userCarInfoList)) {
                List<UserCarInfoResultDto> userCarInfoResultDtoList = modelMapper.map(userCarInfoList, new TypeToken<List<UserCarInfoResultDto>>() {
                }.getType());
                objectResultDto.setItems(userCarInfoResultDtoList);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取车辆信息失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取用户车辆信息通过UserID
     */
    @Override
    public ListResultDto<UserCarViewResultDto> getUserCarByUserId(UserCarInfoGetByUserIdRequestDto requestDto) {
        ListResultDto<UserCarViewResultDto> resultDto = new ListResultDto<>();
        try {

            EntityWrapper<UserCarInfo> entityWrapper = new EntityWrapper<>();
            entityWrapper.in("userId", requestDto.getUserIds());

            List<UserCarInfo> authCarList = userCarInfoCrudService.selectList(entityWrapper);
            if (authCarList.size() != 0) {
                List<UserCarViewResultDto> list = new ArrayList<>();
                for (UserCarInfo userCarInfo : authCarList) {
                    list.add(modelMapper.map(userCarInfo, UserCarViewResultDto.class));
                }
                resultDto.setItems(list);
            }
        } catch (Exception e) {
            log.error("获取用户车辆失败:" + e.getMessage());
            return resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页获取logo
     */
    @Override
    public PagedResultDto<UserCarImageResultDto> getCarImageViewList(UserCarImageQueryPagedResultRequestDto requestDto) {
        PagedResultDto<UserCarImageResultDto> pagedResultDto = new PagedResultDto<>();
        try {

            EntityWrapper<UserCarInfo> entityWrapper = new EntityWrapper<>();
            Page<UserCarInfo> userCarInfoPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<UserCarInfo> userCarInfoPageList = userCarInfoCrudService.selectPage(userCarInfoPage, entityWrapper);
            if (userCarInfoPageList != null) {

                List<UserCarImageResultDto> userCarImageDtoList = modelMapper.map(userCarInfoPageList.getRecords(), new TypeToken<List<UserCarImageResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(userCarInfoPageList.getCurrent());
                pagedResultDto.setPageSize(userCarInfoPageList.getSize());
                pagedResultDto.setTotalCount(userCarInfoPageList.getTotal());
                pagedResultDto.setItems(userCarImageDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("logo获取失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 修改logo的url
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateCarImage(UserCarImageUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            UserCarInfo userCarInfo = userCarInfoCrudService.selectById(requestDto.getId());
            if (userCarInfo == null) {
                return resultDto.failed("车辆不存在");
            }
            UserCarInfo userCarInfoUpdate = new UserCarInfo();
            if (StringUtils.isNotEmpty(requestDto.getCarImageUrl())) {
                userCarInfoUpdate.setCarImageUrl(requestDto.getCarImageUrl());
            }
            if (StringUtils.isNotEmpty(requestDto.getLicensePhotoFront())) {
                userCarInfoUpdate.setLicensePhotoFront(requestDto.getLicensePhotoFront());
            }
            if (StringUtils.isNotEmpty(requestDto.getLicensePhotoContrary())) {
                userCarInfoUpdate.setLicensePhotoContrary(requestDto.getLicensePhotoContrary());
            }
            EntityWrapper<UserCarInfo> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", userCarInfo.getId());
            userCarInfoCrudService.update(userCarInfoUpdate, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("修改车辆logo失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页获取logo
     */
    @Override
    public PagedResultDto<CarAuthImageResultDto> getCarAuthImageViewList(CarAuthImageQueryPagedResultRequestDto requestDto) {
        PagedResultDto<CarAuthImageResultDto> pagedResultDto = new PagedResultDto<>();
        try {

            EntityWrapper<UserCarAuth> entityWrapper = new EntityWrapper<>();
            Page<UserCarAuth> carBrandPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<UserCarAuth> carBrandPageList = userCarAuthCrudService.selectPage(carBrandPage, entityWrapper);
            if (carBrandPageList != null) {

                List<CarAuthImageResultDto> carBrandDtoList = modelMapper.map(carBrandPageList.getRecords(), new TypeToken<List<CarAuthImageResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(carBrandPageList.getCurrent());
                pagedResultDto.setPageSize(carBrandPageList.getSize());
                pagedResultDto.setTotalCount(carBrandPageList.getTotal());
                pagedResultDto.setItems(carBrandDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("logo获取失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 修改logo的url
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateCarAuthImage(CarAuthImageUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            UserCarAuth userCarAuth = userCarAuthCrudService.selectById(requestDto.getId());
            if (userCarAuth == null) {
                return resultDto.failed("车辆不存在");
            }
            UserCarAuth userCarAuthUpdate = new UserCarAuth();
            if (StringUtils.isNotEmpty(requestDto.getLicensePhotoFront())) {
                userCarAuthUpdate.setLicensePhotoFront(requestDto.getLicensePhotoFront());
            }
            if (StringUtils.isNotEmpty(requestDto.getLicensePhotoContrary())) {
                userCarAuthUpdate.setLicensePhotoContrary(requestDto.getLicensePhotoContrary());
            }
            EntityWrapper<UserCarAuth> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", userCarAuth.getId());
            userCarAuthCrudService.update(userCarAuthUpdate, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("修改车辆logo失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 查询用户车辆个数
     *
     * @param requestDto UserHasCarRequestDto
     * @return UserHasCarResultDto
     */
    @Override
    public ObjectResultDto<UserCarCountResultDto> selectUserCarCountById(UserCarCountByIdRequestDto requestDto) {
        ObjectResultDto<UserCarCountResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            UserCarCountResultDto userCarCountResultDto = new UserCarCountResultDto();
            userCarCountResultDto.setCount(userCarInfoCrudService.selectCountByUserIdAndStatus(requestDto.getUserId(), null));
            objectResultDto.setData(userCarCountResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("查询用户车辆数量失败:" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 用户车辆是否被解绑
     *
     * @param requestDto UserCarUnbindRequestDto
     * @return UserCarUnbindResultDto
     */
    @Override
    public ObjectResultDto<UserCarUnbindResultDto> carUnbindStatus(UserCarUnbindRequestDto requestDto) {
        ObjectResultDto<UserCarUnbindResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            if (null == requestDto.getCarId()) {
                return objectResultDto.makeResult(UCenterResultEnum.REQUEST_EMPTY.getValue(), UCenterResultEnum.REQUEST_EMPTY.getComment());
            }
            UserCarUnbindResultDto userCarCountResultDto = new UserCarUnbindResultDto();
            EntityWrapper<UserCarInfo> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getCarId());
            entityWrapper.eq("userId", requestDto.getSessionIdentity().getUserId());
            UserCarInfo userCarInfo = userCarInfoCrudService.selectOne(entityWrapper);
            if (null == userCarInfo) {
                userCarCountResultDto.setUnbind(Boolean.TRUE);
            } else {
                userCarCountResultDto.setUnbind(Boolean.FALSE);
            }
            objectResultDto.setData(userCarCountResultDto);
            objectResultDto.success();

        } catch (Exception e) {
            log.error("查询用户车辆是否被解绑失败:" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
