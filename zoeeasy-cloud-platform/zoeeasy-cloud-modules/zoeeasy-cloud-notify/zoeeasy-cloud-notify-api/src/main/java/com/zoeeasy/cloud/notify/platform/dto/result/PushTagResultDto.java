package com.zoeeasy.cloud.notify.platform.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/11/12 0012
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PushTagResultDto", description = "pushTag返回结果")
public class PushTagResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty("停车场id")
    private Long parkingId;

    /**
     * 标签
     */
    @ApiModelProperty("标签")
    private String tag;

    /**
     * 标签类型
     */
    @ApiModelProperty("标签类型")
    private Integer tagTypes;

}
