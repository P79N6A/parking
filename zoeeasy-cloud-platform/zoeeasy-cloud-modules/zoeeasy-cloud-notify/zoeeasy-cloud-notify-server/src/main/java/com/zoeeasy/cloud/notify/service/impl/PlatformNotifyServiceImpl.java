package com.zoeeasy.cloud.notify.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.reflect.TypeToken;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.notify.cts.NotifyConstant;
import com.zoeeasy.cloud.notify.domain.Notification;
import com.zoeeasy.cloud.notify.domain.PushTag;
import com.zoeeasy.cloud.notify.domain.UserPushTag;
import com.zoeeasy.cloud.notify.platform.PlatformNotifyService;
import com.zoeeasy.cloud.notify.platform.dto.request.NotifyCountRequestDto;
import com.zoeeasy.cloud.notify.platform.dto.request.PushTagAddRequestDto;
import com.zoeeasy.cloud.notify.platform.dto.request.PushTagGetByParkingIdRequestDto;
import com.zoeeasy.cloud.notify.platform.dto.request.UserPushTagSaveRequestDto;
import com.zoeeasy.cloud.notify.platform.dto.result.NotifyCountResultDto;
import com.zoeeasy.cloud.notify.platform.dto.result.PushTagResultDto;
import com.zoeeasy.cloud.notify.platform.dto.result.PushTagSaveResultDto;
import com.zoeeasy.cloud.notify.service.NotificationCrudService;
import com.zoeeasy.cloud.notify.service.PushTagCrudService;
import com.zoeeasy.cloud.notify.service.UserPushTagCrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AkeemSuper
 * @date 2018/11/12 0012
 */
@Service("platformNotifyService")
@Slf4j
public class PlatformNotifyServiceImpl implements PlatformNotifyService {

    @Autowired
    private PushTagCrudService pushTagCrudService;

    @Autowired
    private UserPushTagCrudService userPushTagCrudService;

    @Autowired
    private NotificationCrudService notificationCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 通过停车场获取tag
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<PushTagResultDto> getPushTagByParkingId(PushTagGetByParkingIdRequestDto requestDto) {
        ListResultDto<PushTagResultDto> objectResultDto = new ListResultDto<>();
        try {
            Wrapper<PushTag> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            entityWrapper.eq("tagType", requestDto.getTagTypes());
            List<PushTag> pushTags = pushTagCrudService.selectList(entityWrapper);
            if (CollectionUtils.isNotEmpty(pushTags)) {
                List<PushTagResultDto> pushTagResultDtos = modelMapper.map(pushTags, new TypeToken<List<PushTagResultDto>>() {
                }.getType());
                objectResultDto.setItems(pushTagResultDtos);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("通过停车场获取tag失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 添加pushTag
     *
     * @param requestDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<PushTagSaveResultDto> savePushTag(PushTagAddRequestDto requestDto) {
        ObjectResultDto resultDto = new ObjectResultDto();
        try {
            if (null != requestDto) {
                PushTag pushTag = modelMapper.map(requestDto, PushTag.class);
                Long id = pushTagCrudService.save(pushTag);
                PushTagSaveResultDto pushTagSaveResultDto = new PushTagSaveResultDto();
                pushTagSaveResultDto.setId(id);
                resultDto.setData(pushTagSaveResultDto);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("添加pushTag失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 添加用户标签
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto saveUserPushTag(UserPushTagSaveRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            //根据用户id获取标签
            List<UserPushTag> userPushTags = userPushTagCrudService.findByUserId(requestDto.getUserId());
            List<Long> tagId = requestDto.getTagId();
            if (CollectionUtils.isEmpty(userPushTags)) {
                for (Long aLong : tagId) {
                    UserPushTag userPushTag = new UserPushTag();
                    userPushTag.setTenantId(requestDto.getTenantId());
                    userPushTag.setUserId(requestDto.getUserId());
                    userPushTag.setTagId(aLong);
                    userPushTagCrudService.insert(userPushTag);
                }
            } else {
                List<Long> userPushTagIds = userPushTags.stream().map(UserPushTag::getTagId).collect(Collectors.toList());
                List<Long> insertTagIds =
                        tagId.stream().filter(item -> !userPushTagIds.contains(item)).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(insertTagIds)) {
                    for (Long insertTagId : insertTagIds) {
                        UserPushTag userPushTag = new UserPushTag();
                        userPushTag.setTenantId(requestDto.getTenantId());
                        userPushTag.setUserId(requestDto.getUserId());
                        userPushTag.setTagId(insertTagId);
                        userPushTagCrudService.insert(userPushTag);
                    }
                }
                List<Long> deleteTagIds = userPushTagIds.stream().filter(item -> !tagId.contains(item)).collect(Collectors.toList());
                if (CollectionUtils.isNotEmpty(deleteTagIds)) {
                    userPushTagCrudService.deleteTagIds(requestDto.getUserId(), deleteTagIds);
                }
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("添加用户标签失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据用户查询当天通知条数
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<NotifyCountResultDto> getMessageListCount(NotifyCountRequestDto requestDto) {
        ObjectResultDto<NotifyCountResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<Notification> entityWrapper = new EntityWrapper<>();
            Long userId = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId();
            entityWrapper.eq("userId", userId);
            entityWrapper.ge("sendTime", DateUtils.getStartDate(new Date()));
            entityWrapper.le("sendTime", DateUtils.getEndDate(new Date()));
            entityWrapper.like("content", NotifyConstant.PARKING_ID + requestDto.getParkingId());
            Integer notifyCount = notificationCrudService.selectCount(entityWrapper);
            NotifyCountResultDto notifyCountResultDto = new NotifyCountResultDto();
            notifyCountResultDto.setNotifyCount(notifyCount);
            notifyCountResultDto.setParkingId(requestDto.getParkingId());
            objectResultDto.setData(notifyCountResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("根据用户查询通知条数失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
