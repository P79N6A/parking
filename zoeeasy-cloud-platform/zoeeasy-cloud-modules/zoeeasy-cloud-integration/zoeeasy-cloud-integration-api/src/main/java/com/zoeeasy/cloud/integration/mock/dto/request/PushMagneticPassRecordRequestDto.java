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
@ApiModel(value = "PushMagneticPassRecordRequestDto", description = "模拟推送地磁过车记录")
public class PushMagneticPassRecordRequestDto extends SignedRequestDto {

    /**
     * 停车场id
     */
    private Long parkingId;

    /**
     * 泊位id
     */
    private Long parkingLotId;

    /**
     * 过车时间
     */
    private Date passTime;

    /**
     * 过车类型
     */
    private Integer passingType;
}
