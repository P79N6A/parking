package com.zoeeasy.cloud.pms.park.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车场二维码请求结果
 *
 * @author wangfeng
 * @since 2018/12/21 18:17
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkQRCodeResultDto", description = "停车场二维码请求结果")
public class ParkQRCodeResultDto extends BaseDto {
    private static final long serialVersionUID = 1L;
    private String codeUrl;
}
