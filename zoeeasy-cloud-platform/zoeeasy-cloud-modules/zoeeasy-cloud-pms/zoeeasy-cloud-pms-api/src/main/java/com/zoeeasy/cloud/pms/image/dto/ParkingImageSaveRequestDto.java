package com.zoeeasy.cloud.pms.image.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.passing.cts.PassingConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 停车过车图像保存请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingImageSaveRequestDto extends BaseDto {

    @NotNull(message = ParkingConstant.TENANT_ID_NOT_NULL)
    private Long tenantId;

    @NotNull(message = ParkingConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;

    @NotNull(message = PassingConstant.PASSING_ID_NOT_NULL)
    private Long passingId;

    @NotNull(message = PassingConstant.PASSING_NUMBER_NOT_EMPTY)
    private String passingNo;

    private List<ParkingImageItemDto> images;

}
