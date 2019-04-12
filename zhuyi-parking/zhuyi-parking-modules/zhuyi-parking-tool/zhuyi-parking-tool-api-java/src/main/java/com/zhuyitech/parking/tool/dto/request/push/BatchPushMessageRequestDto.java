package com.zhuyitech.parking.tool.dto.request.push;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 批量用户消息推送请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BatchPushMessageRequestDto extends SessionDto {

    private List<PushSingleUserMessageRequestDto> params;

    public List<PushSingleUserMessageRequestDto> getParams() {
        return params;
    }

    public void setParams(List<PushSingleUserMessageRequestDto> params) {
        this.params = params;
    }
}
