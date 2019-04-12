package com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 设备检测器心跳，定时器监控服务
 *
 * @Date: 2018/9/21
 * @author: lhj
 */
@Data
@ApiModel(value = "MagneticHeartBeatStatusByUpdateTimeRequestDto", description = "设备检测器心跳，定时器监控服务")
@EqualsAndHashCode(callSuper = false)
public class MagneticHeartBeatStatusByUpdateTimeRequestDto extends SignedSessionRequestDto {
    private static final long serialVersionUID = 1L;

    /**
     * 心跳频率
     */
    @ApiModelProperty(value = "心跳频率")
    private int second;

    /**
     * 厂商
     */
    @ApiModelProperty(value = "厂商")
    private Integer provider;
}
