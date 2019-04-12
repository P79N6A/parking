package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 获取停车场二维码数据请求参数
 *
 * @author zwq
 * @date 2018/12/21 0028
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingQrcodeGetRequestDto", description = "获取停车场二维码数据请求参数")
public class ParkingQrcodeGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 随机字符串
     */
    @ApiModelProperty(value = "随机字符串")
    @NotNull(message = ParkingConstant.NONCESTR_NOT_EMPTY)
    private String noncestr;
}
