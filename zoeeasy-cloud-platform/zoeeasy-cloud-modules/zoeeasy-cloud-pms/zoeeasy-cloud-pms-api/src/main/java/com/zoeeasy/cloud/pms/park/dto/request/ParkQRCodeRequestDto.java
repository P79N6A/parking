package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 生成二维码请求入参
 *
 * @author wangfeng
 * @since 2018/12/21 16:43
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkQRCodeRequestDto", description = "生成二维码请求入参")
public class ParkQRCodeRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;
    /**
     * 停车场id
     */
    @NotNull(message = ParkingConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    @ApiModelProperty("停车场id")
    private Long parkingId;
}
