package com.zoeeasy.cloud.integration.message.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author AkeemSuper
 * @date 2018/9/30 0030
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "HikImageMessageSendRequestDto", description = "地磁消息发送请求参数")
public class HikImageMessageSendRequestDto extends BaseDto {

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
    @NotEmpty(message = "海康过车ID不能为空")
    private String hikPassingUuid;

    /**
     * 海康停车场Code
     */
    @NotEmpty(message = "海康停车场Code不能为空")
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
