package com.zoeeasy.cloud.notify.platform;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.notify.platform.dto.request.NotifyCountRequestDto;
import com.zoeeasy.cloud.notify.platform.dto.request.PushTagAddRequestDto;
import com.zoeeasy.cloud.notify.platform.dto.request.PushTagGetByParkingIdRequestDto;
import com.zoeeasy.cloud.notify.platform.dto.request.UserPushTagSaveRequestDto;
import com.zoeeasy.cloud.notify.platform.dto.result.NotifyCountResultDto;
import com.zoeeasy.cloud.notify.platform.dto.result.PushTagResultDto;
import com.zoeeasy.cloud.notify.platform.dto.result.PushTagSaveResultDto;

/**
 * @author AkeemSuper
 * @date 2018/11/12 0012
 */
public interface PlatformNotifyService {

    /**
     * 通过停车场获取tag
     *
     * @param requestDto PushTagGetByParkingIdRequestDto
     * @return ListResultDto<PushTagResultDto>
     */
    ListResultDto<PushTagResultDto> getPushTagByParkingId(PushTagGetByParkingIdRequestDto requestDto);

    /**
     * 添加pushTag
     */
    ObjectResultDto<PushTagSaveResultDto> savePushTag(PushTagAddRequestDto requestDto);

    /**
     * 添加用户标签
     *
     * @param requestDto UserPushTagSaveRequestDto
     * @return ResultDto
     */
    ResultDto saveUserPushTag(UserPushTagSaveRequestDto requestDto);

    /**
     * 根据用户查询通知条数
     *
     * @param requestDto NotifyCountRequestDto
     * @return ObjectResultDto<NotifyCountResultDto>
     */
    ObjectResultDto<NotifyCountResultDto> getMessageListCount(NotifyCountRequestDto requestDto);

}
