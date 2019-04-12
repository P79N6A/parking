package com.zoeeasy.cloud.integration.mock.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/11/2 0002
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PusHikPassRecordRequestDto", description = "模拟推送海康过车记录")
public class PusHikPassRecordRequestDto extends SignedRequestDto {

    /**
     * 停车场id
     */
    private String hikParkId;

    /**
     * 泊位id
     */
    private String hikParkingLotId;

    /**
     * 过车时间
     */
    private Date passTime;

    /**
     * 过车类型
     */
    private Integer passingType;

    /**
     * 车牌号
     */
    private String plateNumber;

    /**
     * 车牌颜色
     */
    private Integer plateColor;

    /**
     * 车辆类型
     */
    private Integer carType;


}
