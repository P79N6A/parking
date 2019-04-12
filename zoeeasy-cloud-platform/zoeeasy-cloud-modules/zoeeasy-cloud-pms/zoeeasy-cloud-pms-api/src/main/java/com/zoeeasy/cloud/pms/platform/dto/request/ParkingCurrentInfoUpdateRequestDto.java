package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zwq
 * @Description: 修改停车场当前余位数量
 * @Date: 2018/09/20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingCurrentInfoUpdateRequestDto", description = "修改停车场当前余位数量")
public class ParkingCurrentInfoUpdateRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场当前信息ID
     */
    @ApiModelProperty(value = "停车场当前信息ID")
    private Long parkingCurrentInfoId;

    /**
     * 可用车位数
     */
    @ApiModelProperty(value = "可用车位数")
    private Integer lotAvailable;
}
