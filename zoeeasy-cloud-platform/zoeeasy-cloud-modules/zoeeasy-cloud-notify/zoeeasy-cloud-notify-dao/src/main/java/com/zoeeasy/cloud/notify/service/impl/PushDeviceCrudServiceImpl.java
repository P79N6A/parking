package com.zoeeasy.cloud.notify.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.notify.domain.PushDevice;
import com.zoeeasy.cloud.notify.mapper.PushDeviceMapper;
import com.zoeeasy.cloud.notify.service.PushDeviceCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author walkman
 * @date 2018-05-16
 */
@Service("pushDeviceCrudService")
public class PushDeviceCrudServiceImpl extends ServiceImpl<PushDeviceMapper, PushDevice> implements PushDeviceCrudService {

    /**
     * 根据设备号查询
     *
     * @param registrationId registrationId
     * @param status         status
     * @return
     */
    @Override
    public PushDevice getPushDeviceByRegistrationId(String registrationId, Integer status) {
        EntityWrapper<PushDevice> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("registrationId", registrationId);
        entityWrapper.eq("status", status);
        return selectOne(entityWrapper);
    }

    /**
     * 根据用户的Id和状态查询设备号
     *
     * @param userId userId
     * @param status status
     * @return
     */
    @Override
    public List<PushDevice> queryListByUserIdAndStatus(Long userId, Integer status) {
        EntityWrapper<PushDevice> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("userId", userId);
        entityWrapper.eq("status", status);
        return selectList(entityWrapper);
    }

    /**
     * 根据类型和状态查询推送设备
     *
     * @param type
     * @param status
     * @return
     */
    @Override
    public List<PushDevice> queryListByTypeAndStatus(String type, Integer status) {
        return null;
    }

    /**
     * @param userId
     * @description 根据用户的Id删除设备号
     */
    @Override
    public void deleteByUserId(Long userId) {
    }

    /**
     * 切换数据状态
     *
     * @param oldStatus
     * @param newStatus
     * @param registrationId
     * @return
     */
    @Override
    public int toggleValid(Integer oldStatus, Integer newStatus, Long userId, String registrationId) {
        EntityWrapper<PushDevice> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("userId", userId);
        entityWrapper.eq("registrationId", registrationId);
        entityWrapper.eq("status", oldStatus);
        PushDevice pushDevice = new PushDevice();
        pushDevice.setStatus(newStatus);
        return baseMapper.update(pushDevice, entityWrapper);
    }

    /**
     * 该设备未关联用户记录
     */
    @Override
    public PushDevice selectByRegistrationId(String registrationId) {
        EntityWrapper<PushDevice> wrapper = new EntityWrapper<>();
        wrapper.eq("registrationId", registrationId);
        wrapper.eq("userId", 0);
        List<PushDevice> pushDevices = baseMapper.selectList(wrapper);
        if (!pushDevices.isEmpty()) {
            return pushDevices.get(0);
        }
        return null;

    }

}
