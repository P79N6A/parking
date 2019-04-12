package com.zoeeasy.cloud.integration.inspect;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.integration.push.dto.request.PushTagGetInfoRequestDto;
import com.zoeeasy.cloud.integration.push.dto.result.PushTagGetInfoResultDto;
import com.zoeeasy.cloud.message.payload.PassingRecordToInspectPayload;

/**
 * 巡检推送服务
 *
 * @author AkeemSuper
 * @date 2018/10/17 0017
 */
public interface InspectPushService {

    /**
     * 处理过车记录推送巡检
     *
     * @param passingRecordToInspectPayLoad
     * @return
     * @throws Exception
     */
    ResultDto processPushPassRecord(PassingRecordToInspectPayload passingRecordToInspectPayLoad) throws Exception;

    /**
     * 获取用户的tag
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<PushTagGetInfoResultDto> getPushTag(PushTagGetInfoRequestDto requestDto);

    /**
     * 根据过车记录发送巡检消息
     *
     * @param passingRecordToInspectPayLoad
     * @return
     * @throws Exception
     */
    ResultDto sendNotifyPassRecord(PassingRecordToInspectPayload passingRecordToInspectPayLoad) throws Exception;

}
