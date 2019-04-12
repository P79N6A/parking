package com.zoeeasy.cloud.notify.service.impl;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.common.utils.ValueUtils;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.notify.domain.PushDevice;
import com.zoeeasy.cloud.notify.enums.DeviceStatus;
import com.zoeeasy.cloud.notify.enums.NotifyResultEnum;
import com.zoeeasy.cloud.notify.push.PushDeviceService;
import com.zoeeasy.cloud.notify.push.dto.request.InvalidPushDeviceRequestDto;
import com.zoeeasy.cloud.notify.push.dto.request.RegisterDeviceRequestDto;
import com.zoeeasy.cloud.notify.push.dto.request.UserBindDeviceRequestDto;
import com.zoeeasy.cloud.notify.service.PushDeviceCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 消息推送设备服务
 *
 * @author walkamn
 */
@Service("pushDeviceService")
@Slf4j
public class PushDeviceServiceImpl implements PushDeviceService {

    @Autowired
    private PushDeviceCrudService pushDeviceCrudService;

    @Override
    public ResultDto registerDevice(RegisterDeviceRequestDto requestDto) {

        ResultDto resultDto = new ResultDto();
        if (StringUtils.isEmpty(requestDto.getRegistrationId())) {
            return resultDto.makeResult(NotifyResultEnum.PUSH_DEVICE_REGISTRATION_ID_EMPTY.getValue(),
                    NotifyResultEnum.PUSH_DEVICE_REGISTRATION_ID_EMPTY.getComment()
            );
        }
        EntityWrapper<PushDevice> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("registrationId", requestDto.getRegistrationId());
        List<PushDevice> pushDevices = pushDeviceCrudService.selectList(entityWrapper);
//        PushDevice pushDevice = pushDeviceCrudService.getPushDeviceByRegistrationId(requestDto.getRegistrationId(), DeviceStatus.VALID.getValue());
        if (!pushDevices.isEmpty()) {
            //防止app重复注册
            return resultDto.success();
        }
        try {

            PushDevice pushDevice = new PushDevice();
            pushDevice.setUserId(ValueUtils.getLong(0));
            pushDevice.setDeviceToken(requestDto.getDeviceToken());
            pushDevice.setRegistrationId(requestDto.getRegistrationId());
            pushDevice.setTerminateType(requestDto.getTerminateType());
            pushDevice.setChannelCode(requestDto.getChannelCode());
            pushDevice.setAppName(requestDto.getAppName());
            pushDevice.setAppVersionName(requestDto.getAppVersionName());
            pushDevice.setAppVersionCode(requestDto.getAppVersionCode());
            pushDevice.setBuildBrand(requestDto.getBuildBrand());
            pushDevice.setBuildModel(requestDto.getBuildModel());
            pushDevice.setBuildVersionSdkInt(requestDto.getBuildVersionSdkInt());
            pushDevice.setBuildVersionRelease(requestDto.getBuildVersionRelease());
            pushDevice.setImei(requestDto.getImei());
            pushDevice.setPackageName(requestDto.getPackageName());
            pushDevice.setScreenHeight(requestDto.getScreenHeight());
            pushDevice.setScreenWidth(requestDto.getScreenWidth());
            pushDevice.setDensityDpi(requestDto.getDensityDpi());
            pushDevice.setStatus(DeviceStatus.VALID.getValue());
            pushDeviceCrudService.insert(pushDevice);
            resultDto.success();
        } catch (Exception e) {
            log.error("设备注册失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public ResultDto bindDevice(UserBindDeviceRequestDto requestDto) {

        ResultDto resultDto = new ResultDto();
        if (StringUtils.isEmpty(requestDto.getRegistrationId())) {
            return resultDto.makeResult(NotifyResultEnum.PUSH_DEVICE_REGISTRATION_ID_EMPTY.getValue(),
                    NotifyResultEnum.PUSH_DEVICE_REGISTRATION_ID_EMPTY.getComment()
            );
        }
        try {

            Long userId = ValueUtils.getLong(FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId());
            EntityWrapper<PushDevice> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("registrationId", requestDto.getRegistrationId());
            entityWrapper.eq("userId", userId);
            PushDevice pushDevice = pushDeviceCrudService.selectOne(entityWrapper);
//            PushDevice pushDevice = pushDeviceCrudService.getPushDeviceByRegistrationId(requestDto.getRegistrationId(), DeviceStatus.VALID.getValue());
            if (pushDevice == null) {
                //查找该设备非用户相关的设备
                PushDevice existPushDevice = pushDeviceCrudService.selectByRegistrationId(requestDto.getRegistrationId());
                if (existPushDevice != null) {
                    EntityWrapper<PushDevice> wrapper = new EntityWrapper<>();
                    wrapper.eq("id", existPushDevice.getId());
                    existPushDevice.setStatus(DeviceStatus.VALID.getValue());
                    existPushDevice.setUserId(userId);
                    pushDeviceCrudService.update(existPushDevice, wrapper);
                } else {
                    //如果没有查到设备号相关信息
                    pushDevice = new PushDevice();
                    pushDevice.setUserId(userId);
                    pushDevice.setRegistrationId(requestDto.getRegistrationId());
                    pushDevice.setDeviceToken(requestDto.getDeviceToken());
                    pushDevice.setStatus(DeviceStatus.VALID.getValue());
                    pushDevice.setTerminateType(requestDto.getTerminateType());
                    pushDevice.setChannelCode(requestDto.getChannelCode());
                    pushDevice.setAppName(requestDto.getAppName());
                    pushDevice.setAppVersionName(requestDto.getAppVersionName());
                    pushDevice.setAppVersionCode(requestDto.getAppVersionCode());
                    pushDevice.setBuildBrand(requestDto.getBuildBrand());
                    pushDevice.setBuildModel(requestDto.getBuildModel());
                    pushDevice.setBuildVersionSdkInt(requestDto.getBuildVersionSdkInt());
                    pushDevice.setBuildVersionRelease(requestDto.getBuildVersionRelease());
                    pushDevice.setImei(requestDto.getImei());
                    pushDevice.setPackageName(requestDto.getPackageName());
                    pushDevice.setScreenHeight(requestDto.getScreenHeight());
                    pushDevice.setScreenWidth(requestDto.getScreenWidth());
                    pushDevice.setDensityDpi(requestDto.getDensityDpi());
                    this.pushDeviceCrudService.insert(pushDevice);
                }
            } else {
                pushDevice.setUserId(userId);
                if (StringUtils.isNotEmpty(requestDto.getDeviceToken())) {
                    pushDevice.setDeviceToken(requestDto.getDeviceToken());
                }
                if (StringUtils.isNotEmpty(requestDto.getChannelCode())) {
                    pushDevice.setChannelCode(requestDto.getChannelCode());
                }
                if (StringUtils.isNotEmpty(requestDto.getAppName())) {
                    pushDevice.setAppName(requestDto.getAppName());
                }
                if (StringUtils.isNotEmpty(requestDto.getAppVersionName())) {
                    pushDevice.setAppVersionName(requestDto.getAppVersionName());
                }
                if (requestDto.getAppVersionCode() != null) {
                    pushDevice.setAppVersionCode(requestDto.getAppVersionCode());
                }
                if (StringUtils.isNotEmpty(requestDto.getBuildBrand())) {
                    pushDevice.setBuildBrand(requestDto.getBuildBrand());
                }
                if (StringUtils.isNotEmpty(requestDto.getBuildModel())) {
                    pushDevice.setBuildModel(requestDto.getBuildModel());
                }
                if (requestDto.getBuildVersionSdkInt() != null) {
                    pushDevice.setBuildVersionSdkInt(requestDto.getBuildVersionSdkInt());
                }
                if (StringUtils.isNotEmpty(requestDto.getBuildVersionRelease())) {
                    pushDevice.setBuildVersionRelease(requestDto.getBuildVersionRelease());
                }
                if (StringUtils.isNotEmpty(requestDto.getImei())) {
                    pushDevice.setImei(requestDto.getImei());
                }
                if (StringUtils.isNotEmpty(requestDto.getPackageName())) {
                    pushDevice.setPackageName(requestDto.getPackageName());
                }
                if (requestDto.getScreenHeight() != null) {
                    pushDevice.setScreenHeight(requestDto.getScreenHeight());
                }
                if (requestDto.getScreenWidth() != null) {
                    pushDevice.setScreenWidth(requestDto.getScreenWidth());
                }
                if (requestDto.getDensityDpi() != null) {
                    pushDevice.setDensityDpi(requestDto.getDensityDpi());
                }
                pushDevice.setStatus(DeviceStatus.VALID.getValue());
                this.pushDeviceCrudService.updateById(pushDevice);
                //设置当前设备其他用户失活
                EntityWrapper<PushDevice> wrapper = new EntityWrapper<>();
                wrapper.eq("registrationId", requestDto.getRegistrationId());
                wrapper.ne("userId", userId);
                PushDevice device = new PushDevice();
                device.setStatus(DeviceStatus.INVALID.getValue());
                pushDeviceCrudService.update(device, wrapper);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("设备注册失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public ResultDto invalidDevice(InvalidPushDeviceRequestDto param) {
        int c = pushDeviceCrudService.toggleValid(DeviceStatus.VALID.getValue(), DeviceStatus.INVALID.getValue(),
                FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId(), param.getRegistrationId());
        if (c > 0) {
            return new ResultDto().success();
        }
        return new ResultDto().success();
    }

}
