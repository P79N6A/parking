package com.zoeeasy.cloud.integration.platform.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取可用车位和消息条数模型
 *
 * @Date: 2018/11/20
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAndNotifyResultDto", description = "获取可用车位和消息条数模型")
public class ParkingAndNotifyResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 可用车位数
     */
    @ApiModelProperty("可用车位数")
    private Integer lotAvailable;

    /**
     * 消息条数
     */
    @ApiModelProperty("消息条数")
    private Integer notifyCount;
}
