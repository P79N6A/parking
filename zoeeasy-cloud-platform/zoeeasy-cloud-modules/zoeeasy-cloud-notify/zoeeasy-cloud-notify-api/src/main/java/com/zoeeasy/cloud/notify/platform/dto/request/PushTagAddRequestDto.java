package com.zoeeasy.cloud.notify.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.notify.cts.NotifyConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author AkeemSuper
 * @date 2018/11/12 0012
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("PushTagAddRequestDto")
public class PushTagAddRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty(value = "停车场id", required = true)
    @NotNull(message = NotifyConstant.PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签", required = true)
    @NotBlank(message = NotifyConstant.TAG_NOT_EMPTY)
    private String tag;

    /**
     * 标签类型
     */
    @ApiModelProperty("标签类型")
    private Integer tagType;

    /**
     * 备注
     */
    private String remarks;

}
