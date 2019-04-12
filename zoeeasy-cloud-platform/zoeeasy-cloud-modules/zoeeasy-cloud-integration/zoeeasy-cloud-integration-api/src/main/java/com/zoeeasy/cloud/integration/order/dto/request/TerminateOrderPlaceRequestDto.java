package com.zoeeasy.cloud.integration.order.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 客户端账单下单请求参数
 *
 * @author walkman
 * @since 2018-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TerminateOrderPlaceRequestDto extends BaseDto {

    private Long tenantId;
    private Long parkingId;
    private String orderNo;
    private String recordNo;
    private String plateNo;
    private Integer plateColor;
    private Integer carType;
    
}
