package com.zhuyitech.parking.ucc.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.common.utils.VehicleNumberUtils;
import com.zhuyitech.parking.pms.dto.request.vehicle.VehicleRecordUpdateRequestDto;
import com.zhuyitech.parking.pms.service.api.VehicleRecordService;
import com.zhuyitech.parking.ucc.car.request.*;
import com.zhuyitech.parking.ucc.domain.UserCarAuth;
import com.zhuyitech.parking.ucc.domain.UserCarInfo;
import com.zhuyitech.parking.ucc.car.result.UserCarAuthResultDto;
import com.zhuyitech.parking.ucc.enums.CarAuthRejectReasonEnum;
import com.zhuyitech.parking.ucc.enums.UCenterResultEnum;
import com.zhuyitech.parking.ucc.enums.UserCarAuthOpinionEnum;
import com.zhuyitech.parking.ucc.enums.UserCarAuthStatusEnum;
import com.zhuyitech.parking.ucc.service.UserCarAuthCrudService;
import com.zhuyitech.parking.ucc.service.UserCarInfoCrudService;
import com.zhuyitech.parking.ucc.car.UserCarAuthService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/4/19 0019
 */
@Service("userCarAuthService")
@Slf4j
public class UserCarAuthServiceImpl implements UserCarAuthService {

    @Autowired
    private UserCarAuthCrudService userCarAuthCrudService;

    @Autowired
    private UserCarInfoCrudService userCarInfoCrudService;

    @Autowired
    private VehicleRecordService vehicleRecordService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 用户添加车辆认证
     *
     * @param requestDto UserCarAuthAddRequestDto
     * @return ResultDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto addUserCarAuth(UserCarAuthAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (null == requestDto.getCarId()) {
                return resultDto.makeResult(UCenterResultEnum.CAR_NOT_FOUND.getValue(),
                        UCenterResultEnum.CAR_NOT_FOUND.getComment());
            }

            if (!StringUtils.isEmpty(requestDto.getVehicleNumber())) {
                if (!VehicleNumberUtils.checkVIN(requestDto.getVehicleNumber())) {
                    requestDto.setVehicleNumber("");
                }
            }

            if (StringUtils.isEmpty(requestDto.getLicensePhotoFront())) {
                return resultDto.makeResult(UCenterResultEnum.CAR_DRIVER_LICENSE_FRONT_PHOTO_EMPTY.getValue(),
                        UCenterResultEnum.CAR_DRIVER_LICENSE_FRONT_PHOTO_EMPTY.getComment());
            }
            UserCarInfo userCarInfo = userCarInfoCrudService.selectById(requestDto.getCarId());
            if (null == userCarInfo) {
                return resultDto.makeResult(UCenterResultEnum.CAR_UNBIND.getValue(),
                        UCenterResultEnum.CAR_UNBIND.getComment());
            }
            String fullPlateNumber = userCarInfo.getFullPlateNumber();
            //已提交认证车辆
            UserCarAuth existCarAuth = userCarAuthCrudService.findByPlateNumberAndUserId(fullPlateNumber, requestDto.getSessionIdentity().getUserId());
            if (existCarAuth != null) {
                //车牌号不允许修改
                if (!userCarInfo.getFullPlateNumber().equals(existCarAuth.getFullPlateNumber())) {
                    return resultDto.makeResult(UCenterResultEnum.CAR_CANNOT_MODIFY.getValue(),
                            UCenterResultEnum.CAR_CANNOT_MODIFY.getComment());
                }
                if (existCarAuth.getStatus().equals(UserCarAuthStatusEnum.CAR_AUTH_PENDING.getValue())) {
                    return resultDto.makeResult(UCenterResultEnum.USER_AUTHENTICATE_CANNOT_MODIFY.getValue(),
                            UCenterResultEnum.USER_AUTHENTICATE_CANNOT_MODIFY.getComment());
                } else if (existCarAuth.getStatus().equals(UserCarAuthStatusEnum.CAR_AUTH_ALREADY.getValue())) {
                    return resultDto.makeResult(UCenterResultEnum.USER_AUTHENTICATE_CANNOT_MODIFY.getValue(),
                            UCenterResultEnum.USER_AUTHENTICATE_CANNOT_MODIFY.getComment());
                }
                if (existCarAuth.getStatus().equals(UserCarAuthStatusEnum.AUTHENTICATION_REJECT.getValue())) {
                    //修改车辆认证
                    Date now = new Date();
                    UserCarAuth carAuth = modelMapper.map(existCarAuth, UserCarAuth.class);
                    carAuth.setCarId(requestDto.getCarId());
                    carAuth.setVehicleNumber(requestDto.getVehicleNumber());
                    carAuth.setEngineNumber(requestDto.getEngineNumber());
                    carAuth.setLicensePhotoContrary(requestDto.getLicensePhotoContrary());
                    carAuth.setLicensePhotoFront(requestDto.getLicensePhotoFront());
                    carAuth.setRegisterDate(requestDto.getRegisterDate());
                    carAuth.setApplyTime(now);
                    carAuth.setStatus(UserCarAuthStatusEnum.CAR_AUTH_PENDING.getValue());
                    carAuth.setAuthTime(null);
                    carAuth.setRemark(null);
                    carAuth.setId(null);
                    carAuth.setUserId(requestDto.getSessionIdentity().getUserId());
                    userCarAuthCrudService.insert(carAuth);

                    //修改用户车辆信息表未认证中
                    UserCarInfo userCarInfoUpdate = new UserCarInfo();
                    userCarInfoUpdate.setVehicleNumber(requestDto.getVehicleNumber());
                    userCarInfoUpdate.setEngineNumber(requestDto.getEngineNumber());
                    userCarInfoUpdate.setLicensePhotoContrary(requestDto.getLicensePhotoContrary());
                    userCarInfoUpdate.setLicensePhotoFront(requestDto.getLicensePhotoFront());
                    userCarInfoUpdate.setRegisterDate(requestDto.getRegisterDate());
                    userCarInfoUpdate.setStatus(UserCarAuthStatusEnum.CAR_AUTH_PENDING.getValue());
                    userCarInfoUpdate.setRemark(null);
                    userCarInfoUpdate.setApplyTime(now);
                    EntityWrapper<UserCarInfo> entityWrapper = new EntityWrapper<>();
                    entityWrapper.eq("id", requestDto.getCarId());
                    userCarInfoCrudService.update(userCarInfoUpdate, entityWrapper);
                    return resultDto.success();
                } else {
                    return resultDto.makeResult(UCenterResultEnum.CAR_CANNOT_MODIFY.getValue(),
                            UCenterResultEnum.CAR_CANNOT_MODIFY.getComment());
                }
            }
            Date now = new Date();
            UserCarAuth authCar = new UserCarAuth();
            authCar.setUserId(userCarInfo.getUserId());
            authCar.setCarId(requestDto.getCarId());
            authCar.setCarBrand(userCarInfo.getCarBrand());
            authCar.setCarType(userCarInfo.getCarType());
            authCar.setCarLevel(userCarInfo.getCarLevel());
            authCar.setPlateColor(userCarInfo.getPlateColor());
            authCar.setCarColor(userCarInfo.getCarColor());
            authCar.setPlateNumber(fullPlateNumber);
            authCar.setVehicleNumber(requestDto.getVehicleNumber());
            authCar.setEngineNumber(requestDto.getEngineNumber());
            authCar.setLicensePhotoContrary(requestDto.getLicensePhotoContrary());
            authCar.setLicensePhotoFront(requestDto.getLicensePhotoFront());
            authCar.setRegisterDate(requestDto.getRegisterDate());
            authCar.setApplyTime(now);
            authCar.setStatus(UserCarAuthStatusEnum.CAR_AUTH_PENDING.getValue());
            userCarAuthCrudService.insert(authCar);
            //修改用户车辆信息表未认证中
            UserCarInfo userCarInfoUpdate = new UserCarInfo();
            userCarInfoUpdate.setVehicleNumber(requestDto.getVehicleNumber());
            userCarInfoUpdate.setEngineNumber(requestDto.getEngineNumber());
            userCarInfoUpdate.setLicensePhotoContrary(requestDto.getLicensePhotoContrary());
            userCarInfoUpdate.setLicensePhotoFront(requestDto.getLicensePhotoFront());
            userCarInfoUpdate.setRegisterDate(requestDto.getRegisterDate());
            userCarInfoUpdate.setStatus(UserCarAuthStatusEnum.CAR_AUTH_PENDING.getValue());
            userCarInfoUpdate.setApplyTime(now);
            EntityWrapper<UserCarInfo> entityWrapper = new EntityWrapper<UserCarInfo>();
            entityWrapper.eq("id", requestDto.getCarId());
            userCarInfoCrudService.update(userCarInfoUpdate, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("用户添加车辆认证失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 用户修改车辆认证
     *
     * @param requestDto UserCarAuthUpdateRequestDto
     * @return ResultDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateUserCarAuth(UserCarAuthUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            UserCarAuth authCar = userCarAuthCrudService.selectById(requestDto.getId());
            if (authCar == null) {
                return resultDto.makeResult(UCenterResultEnum.CAR_AUTHENTICATE_NOT_FOUND.getValue(),
                        UCenterResultEnum.CAR_AUTHENTICATE_NOT_FOUND.getComment());
            }
            if (!StringUtils.isEmpty(requestDto.getVehicleNumber())) {
                if (!VehicleNumberUtils.checkVIN(requestDto.getVehicleNumber())) {
                    return resultDto.makeResult(UCenterResultEnum.CAR_IDENTIFICATION_NUMBER_INVALID.getValue(),
                            UCenterResultEnum.CAR_IDENTIFICATION_NUMBER_INVALID.getComment());
                }
            }
            if (StringUtils.isEmpty(requestDto.getLicensePhotoFront())) {
                return resultDto.makeResult(UCenterResultEnum.CAR_DRIVER_LICENSE_FRONT_PHOTO_EMPTY.getValue(),
                        UCenterResultEnum.CAR_DRIVER_LICENSE_FRONT_PHOTO_EMPTY.getComment());
            }
            String fullPlateNumber = authCar.getFullPlateNumber();
            //车牌号修改的情况下
            if (!fullPlateNumber.equals(authCar.getFullPlateNumber())) {
                //已提交车辆认证申请的车辆不可重复提交
                UserCarAuth existCarAuth = userCarAuthCrudService.findByPlateNumber(fullPlateNumber);
                if (existCarAuth != null) {
                    resultDto.makeResult(UCenterResultEnum.CAR_AUTHENTICATE_SUBMITTED.getValue(),
                            UCenterResultEnum.CAR_AUTHENTICATE_SUBMITTED.getComment());
                }
            }
            //审批中的不允许修改
            if (authCar.getStatus().equals(UserCarAuthStatusEnum.CAR_AUTH_PENDING.getValue())) {
                return resultDto.makeResult(UCenterResultEnum.USER_AUTHENTICATE_CANNOT_MODIFY.getValue(),
                        UCenterResultEnum.USER_AUTHENTICATE_CANNOT_MODIFY.getComment());
            } else if (authCar.getStatus().equals(UserCarAuthStatusEnum.CAR_AUTH_ALREADY.getValue())) {
                return resultDto.makeResult(UCenterResultEnum.USER_AUTHENTICATE_CANNOT_MODIFY.getValue(),
                        UCenterResultEnum.USER_AUTHENTICATE_CANNOT_MODIFY.getComment());
            }

            Date now = new Date();
            UserCarAuth authCarNew = new UserCarAuth();
            authCarNew.setUserId(authCar.getUserId());
            authCarNew.setCarId(authCar.getCarId());
            authCarNew.setCarBrand(authCar.getCarBrand());
            authCarNew.setCarType(authCar.getCarType());
            authCarNew.setCarLevel(authCar.getCarLevel());
            authCarNew.setPlateColor(authCar.getPlateColor());
            authCarNew.setCarColor(authCar.getCarColor());
            authCarNew.setPlateNumber(fullPlateNumber);
            authCarNew.setVehicleNumber(requestDto.getVehicleNumber());
            authCarNew.setEngineNumber(requestDto.getEngineNumber());
            authCarNew.setLicensePhotoContrary(requestDto.getLicensePhotoContrary());
            authCarNew.setLicensePhotoFront(requestDto.getLicensePhotoFront());
            authCarNew.setRegisterDate(requestDto.getRegisterDate());
//            if (authCar.getStatus().equals(UserCarAuthStatusEnum.AUTHENTICATION_REJECT.getValue())) {
            authCarNew.setStatus(UserCarAuthStatusEnum.CAR_AUTH_PENDING.getValue());
            authCarNew.setApplyTime(now);
//            }
            authCarNew.setCreatorUserId(requestDto.getSessionIdentity().getUserId());
            authCarNew.setStatus(UserCarAuthStatusEnum.CAR_AUTH_PENDING.getValue());
            userCarAuthCrudService.insert(authCarNew);
            //修改用户车辆信息表未认证中
            UserCarInfo userCarInfoUpdate = new UserCarInfo();
            userCarInfoUpdate.setStatus(UserCarAuthStatusEnum.CAR_AUTH_PENDING.getValue());
            userCarInfoUpdate.setApplyTime(now);
            userCarInfoUpdate.setVehicleNumber(requestDto.getVehicleNumber());
            userCarInfoUpdate.setEngineNumber(requestDto.getEngineNumber());
            userCarInfoUpdate.setLicensePhotoContrary(requestDto.getLicensePhotoContrary());
            userCarInfoUpdate.setLicensePhotoFront(requestDto.getLicensePhotoFront());
            userCarInfoUpdate.setRegisterDate(requestDto.getRegisterDate());
            EntityWrapper<UserCarInfo> entityWrapper = new EntityWrapper<UserCarInfo>();
            entityWrapper.eq("id", authCar.getCarId());
            userCarInfoCrudService.update(userCarInfoUpdate, entityWrapper);

            resultDto.success();
        } catch (Exception e) {
            log.error("用户添加车辆认证失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;

    }

    /**
     * 分页获取车辆认证申请列表
     */
    @Override
    public PagedResultDto<UserCarAuthResultDto> getUserCarAuthPage(UserCarAuthQueryPageRequestDto requestDto) {
        PagedResultDto<UserCarAuthResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<UserCarAuth> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getCarBrand())) {
                entityWrapper.eq("carBrand", requestDto.getCarBrand());
            }
            if (!StringUtils.isEmpty(requestDto.getCarType())) {
                entityWrapper.eq("carType", requestDto.getCarType());
            }
            if (!StringUtils.isEmpty(requestDto.getFullPlateNumber())) {
                entityWrapper.eq("plateNumber", requestDto.getFullPlateNumber());
            }
            if (requestDto.getRegisterDate() != null) {
                entityWrapper.eq("registerDate", requestDto.getRegisterDate());
            }
            if (!StringUtils.isEmpty(requestDto.getVehicleNumber())) {
                entityWrapper.eq("vehicleNumber", requestDto.getVehicleNumber());
            }
            if (!StringUtils.isEmpty(requestDto.getEngineNumber())) {
                entityWrapper.eq("engineNumber", requestDto.getEngineNumber());
            }
            if (requestDto.getApplyTimeStart() != null) {
                entityWrapper.ge("applyTime", requestDto.getApplyTimeStart());
            }
            if (requestDto.getApplyTimeEnd() != null) {
                entityWrapper.le("applyTime", requestDto.getApplyTimeEnd());
            }
            if (null == requestDto.getStatus()) {
                entityWrapper.orderBy("status");
            } else {
                entityWrapper.eq("status", requestDto.getStatus());
            }
            entityWrapper.orderBy("applyTime", false);

            Page<UserCarAuth> authApplyPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<UserCarAuth> authApplyPageList = userCarAuthCrudService.selectPage(authApplyPage, entityWrapper);
            if (authApplyPageList != null) {
                List<UserCarAuthResultDto> userCarAuthResultDtoList = modelMapper.map(authApplyPageList.getRecords(), new TypeToken<List<UserCarAuthResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(authApplyPageList.getCurrent());
                pagedResultDto.setPageSize(authApplyPageList.getSize());
                pagedResultDto.setTotalCount(authApplyPageList.getTotal());
                pagedResultDto.setItems(userCarAuthResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取车辆信息失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取车辆认证信息
     */
    @Override
    public ObjectResultDto<UserCarAuthResultDto> getUserCarAuth(UserCarAuthGetRequestDto requestDto) {
        ObjectResultDto<UserCarAuthResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            UserCarAuth authCar = userCarAuthCrudService.selectById(requestDto.getId());
            if (authCar == null) {
                return objectResultDto.makeResult(UCenterResultEnum.CAR_AUTHENTICATE_NOT_FOUND.getValue(), UCenterResultEnum.CAR_AUTHENTICATE_NOT_FOUND.getComment());
            }
            UserCarAuthResultDto userCarAuthResultDto = modelMapper.map(authCar, UserCarAuthResultDto.class);
            objectResultDto.setData(userCarAuthResultDto);
            return objectResultDto.success();
        } catch (Exception e) {
            log.error("获取车辆认证信息失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 车辆认证申请审核
     *
     * @param requestDto requestDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto approveCar(UserCarAuthApproveRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            UserCarAuth authCar = userCarAuthCrudService.selectById(requestDto.getId());
            if (authCar == null) {
                return resultDto.makeResult(UCenterResultEnum.CAR_NOT_FOUND.getValue(),
                        UCenterResultEnum.CAR_NOT_FOUND.getComment());
            }
            //只有认证中的允许审批
            if (authCar.getStatus() != UserCarAuthStatusEnum.CAR_AUTH_PENDING.getValue()) {
                return resultDto.makeResult(UCenterResultEnum.USER_CAR_AUTHENTICATE_STATUS_INVALID.getValue(),
                        UCenterResultEnum.USER_CAR_AUTHENTICATE_STATUS_INVALID.getComment());
            }
            Long currentUserId = requestDto.getSessionIdentity().getUserId();
            Integer status;
            UserCarAuth userCarAuth = new UserCarAuth();
            UserCarInfo userCarInfoUpdate = new UserCarInfo();
            if (requestDto.getApproveOpinion().equals(UserCarAuthOpinionEnum.APPROVED.getValue())) {
                status = UserCarAuthStatusEnum.CAR_AUTH_ALREADY.getValue();
                //设置车牌号车架号注册日期
                if (StringUtils.isEmpty(authCar.getVehicleNumber())) {
                    if (StringUtils.isEmpty(requestDto.getVehicleNumber())) {
                        return resultDto.makeResult(UCenterResultEnum.CAR_IDENTIFICATION_NUMBER_EMPTY.getValue(), UCenterResultEnum.CAR_IDENTIFICATION_NUMBER_EMPTY.getComment());
                    } else {
                        if (!VehicleNumberUtils.checkVIN(requestDto.getVehicleNumber())) {
                            return resultDto.makeResult(UCenterResultEnum.CAR_IDENTIFICATION_NUMBER_INVALID.getValue(), UCenterResultEnum.CAR_IDENTIFICATION_NUMBER_INVALID.getComment());
                        }
                        userCarAuth.setVehicleNumber(requestDto.getVehicleNumber());
                        userCarInfoUpdate.setVehicleNumber(requestDto.getVehicleNumber());
                    }
                }
                if (StringUtils.isEmpty(authCar.getEngineNumber())) {
                    if (StringUtils.isEmpty(requestDto.getEngineNumber())) {
                        return resultDto.makeResult(UCenterResultEnum.CAR_ENGINE_NUMBER_EMPTY.getValue(), UCenterResultEnum.CAR_ENGINE_NUMBER_EMPTY.getComment());
                    } else {
                        userCarAuth.setEngineNumber(requestDto.getEngineNumber());
                        userCarInfoUpdate.setEngineNumber(requestDto.getEngineNumber());
                    }
                }
                if (null == authCar.getRegisterDate()) {
                    if (null == requestDto.getRegisterDate()) {
                        return resultDto.makeResult(UCenterResultEnum.CAR_REGISTER_DATE_EMPTY.getValue(), UCenterResultEnum.CAR_REGISTER_DATE_EMPTY.getComment());
                    } else {
                        userCarAuth.setRegisterDate(requestDto.getRegisterDate());
                        userCarInfoUpdate.setRegisterDate(requestDto.getRegisterDate());
                    }
                }
            } else if (requestDto.getApproveOpinion().equals(UserCarAuthOpinionEnum.REJECTED.getValue())) {
                status = UserCarAuthStatusEnum.AUTHENTICATION_REJECT.getValue();
            } else {
                return resultDto.makeResult(UCenterResultEnum.USER_CAR_AUTHENTICATE_STATUS_INVALID.getValue(),
                        UCenterResultEnum.USER_CAR_AUTHENTICATE_STATUS_INVALID.getComment());
            }
            UserCarInfo userCarInfo = userCarInfoCrudService.selectById(authCar.getCarId());
            if (null == userCarInfo) {
                return resultDto.makeResult(UCenterResultEnum.CAR_NOT_FOUND.getValue(),
                        UCenterResultEnum.CAR_NOT_FOUND.getComment());
            }

            VehicleRecordUpdateRequestDto updateRequestDto = new VehicleRecordUpdateRequestDto();
            //维护车辆颜色车牌类型车辆类型数据
            if (null != requestDto.getCarColor()) {
                userCarAuth.setCarColor(requestDto.getCarColor());
                userCarInfoUpdate.setCarColor(requestDto.getCarColor());
                updateRequestDto.setCarColor(requestDto.getCarColor());
            }
            if (null != requestDto.getCarLevel()) {
                userCarAuth.setCarLevel(requestDto.getCarLevel());
                userCarInfoUpdate.setCarLevel(requestDto.getCarLevel());
                updateRequestDto.setCarLevel(requestDto.getCarLevel());
            }
            if (!StringUtils.isEmpty(requestDto.getPlateType())) {
                userCarAuth.setPlateType(requestDto.getPlateType());
                userCarInfoUpdate.setPlateType(requestDto.getPlateType());
                updateRequestDto.setPlateType(requestDto.getPlateType());
            }
            userCarAuth.setStatus(status);
            userCarAuth.setAuthUserId(currentUserId);
            userCarAuth.setLastModifierUserId(currentUserId);
            userCarAuth.setRemark(requestDto.getRemarks());
            userCarAuth.setAuthTime(DateUtils.now());
            EntityWrapper<UserCarAuth> userCarAuthEntityWrapper = new EntityWrapper<>();
            userCarAuthEntityWrapper.eq("id", authCar.getId());
            userCarAuthCrudService.update(userCarAuth, userCarAuthEntityWrapper);
            userCarInfoUpdate.setStatus(status);
            userCarInfoUpdate.setAuthUserId(currentUserId);
            userCarInfoUpdate.setLastModifierUserId(currentUserId);
            userCarInfoUpdate.setRemark(requestDto.getRemarks());
            EntityWrapper<UserCarInfo> userCarInfoEntityWrapper = new EntityWrapper<>();
            userCarInfoEntityWrapper.eq("id", authCar.getCarId());
            userCarInfoCrudService.update(userCarInfoUpdate, userCarInfoEntityWrapper);
            //同步平台车辆表
            updateRequestDto.setCarType(authCar.getCarType());
            if (StringUtils.isEmpty(authCar.getVehicleNumber())) {
                updateRequestDto.setEngineNumber(requestDto.getEngineNumber());
            } else {
                updateRequestDto.setEngineNumber(authCar.getEngineNumber());
            }
            if (StringUtils.isEmpty(authCar.getEngineNumber())) {
                updateRequestDto.setVehicleNumber(requestDto.getVehicleNumber());
            } else {
                updateRequestDto.setVehicleNumber(authCar.getVehicleNumber());
            }
            updateRequestDto.setPlateNumber(authCar.getFullPlateNumber());
            updateRequestDto.setCarType(authCar.getCarType());
            updateRequestDto.setCarBrand(authCar.getCarBrand());
            updateRequestDto.setPlateColor(authCar.getPlateColor());
            if (status.equals(UserCarAuthStatusEnum.CAR_AUTH_ALREADY.getValue())) {
                updateRequestDto.setProofStatus(true);
            }
            vehicleRecordService.updateVehicleRecord(updateRequestDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("车辆认证申请审核失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取车辆认证驳回原因
     */
    @Override
    public ListResultDto<ComboboxItemDto> getRejectReason() {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto(CarAuthRejectReasonEnum.PLATE_NUMBER_NOT_COINCIDE.getValue(), CarAuthRejectReasonEnum.PLATE_NUMBER_NOT_COINCIDE.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(CarAuthRejectReasonEnum.VEHICLE_NUMBER_NOT_COINCIDE.getValue(), CarAuthRejectReasonEnum.VEHICLE_NUMBER_NOT_COINCIDE.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(CarAuthRejectReasonEnum.ENERGY_NUMBER_NOT_COINCIDE.getValue(), CarAuthRejectReasonEnum.ENERGY_NUMBER_NOT_COINCIDE.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(CarAuthRejectReasonEnum.REGISTER_DATE_NUMBER_NOT_COINCIDE.getValue(), CarAuthRejectReasonEnum.REGISTER_DATE_NUMBER_NOT_COINCIDE.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(CarAuthRejectReasonEnum.OTHER.getValue(), CarAuthRejectReasonEnum.OTHER.getComment(), false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取车辆认证驳回原因" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }
}
