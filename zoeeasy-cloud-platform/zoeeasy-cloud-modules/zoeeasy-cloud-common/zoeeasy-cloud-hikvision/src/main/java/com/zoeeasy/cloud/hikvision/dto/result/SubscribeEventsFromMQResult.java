package com.zoeeasy.cloud.hikvision.dto.result;

import com.zoeeasy.cloud.hikvision.bean.SubscribeEventsFromMQBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 事件订阅返回结果
 * @Date: 2018/1/15 0015
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SubscribeEventsFromMQResult extends HikvisionBaseResult<SubscribeEventsFromMQBean> {

    private static final long serialVersionUID = 1L;
}
