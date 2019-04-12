package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 获取停车记录图像请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingRecordImageGetRequestDto", description = "获取停车记录图像请求参数")
public class ParkingRecordImageGetRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "平台停车记录号")
    @NotBlank(message = "停车记录号不能为空")
    private String recordNo;

}
