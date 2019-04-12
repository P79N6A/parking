package com.zoeeasy.cloud.notify.push.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 批量用户消息推送请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BatchPushMessageRequestDto extends BaseDto {

    private List<PushSingleUserMessageRequestDto> params;

}
