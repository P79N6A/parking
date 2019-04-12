package com.zoeeasy.cloud.message.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 海康海康过车消息图像获取消息内容
 *
 * @author walkman
 * @since 2018/9/25 0025
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HikPassingImageFetchPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 停车场ID
     */
    private Long parkingId;

    /**
     * 海康过车ID
     */
    private String hikPassingUuid;

    /**
     * 海康停车场Code
     */
    private String parkCode;

    /**
     * 平台过车ID
     */
    private Long passingId;

    /**
     * 平台过车流水号
     */
    private String passingNo;

}