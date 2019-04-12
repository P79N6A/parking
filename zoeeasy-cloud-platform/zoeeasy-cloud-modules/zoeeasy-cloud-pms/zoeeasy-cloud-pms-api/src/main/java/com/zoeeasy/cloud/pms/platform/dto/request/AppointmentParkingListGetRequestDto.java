package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 可预约停车场列表查询参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointmentParkingListGetRequestDto", description = "可预约停车场列表查询参数")
public class AppointmentParkingListGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /*
     * minLongitude
     */
    private Double minLongitude;

    /*
     * maxLongitude
     */
    private Double maxLongitude;

    /*
     * minLatitude
     */
    private Double minLatitude;

    /*
     * maxLatitude
     */
    private Double maxLatitude;
}