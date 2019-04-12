package com.zoeeasy.cloud.notify.platform.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息条数类表模型
 *
 * @Date: 2018/11/17
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "NotifyCountResultDto", description = "消息条数类表模型")
public class NotifyCountResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场Id
     */
    @ApiModelProperty("停车场Id")
    private Long parkingId;

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
