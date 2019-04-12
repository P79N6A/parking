package com.zoeeasy.cloud.inspect.platform.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车场关联巡检返回结果
 *
 * @author AkeemSuper
 * @date 2018/11/5 0005
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkInspectorInfoResultDto", description = "停车场和巡检关联视图")
public class ParkInspectorInfoResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty("停车场id")
    private Long parkingId;

    /**
     * 用户id
     */
    @ApiModelProperty("userId")
    private Long userId;

}
