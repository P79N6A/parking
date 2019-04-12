package com.zoeeasy.cloud.hikvision.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description 向云平台发送心跳的请求参数
 * @Date: 2018/1/13 0013
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HeartBeatParams extends HikvisionParams implements Serializable {

    private static final long serialVersionUID = 1L;

}
