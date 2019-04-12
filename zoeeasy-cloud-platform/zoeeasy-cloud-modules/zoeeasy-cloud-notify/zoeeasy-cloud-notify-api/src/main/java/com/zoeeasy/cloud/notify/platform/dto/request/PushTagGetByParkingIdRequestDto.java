package com.zoeeasy.cloud.notify.platform.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.notify.cts.NotifyConstant;
import com.zoeeasy.cloud.notify.platform.validator.TagTypeValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author AkeemSuper
 * @date 2018/11/12 0012
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("PushTagGetByParkingIdRequestDto")
public class PushTagGetByParkingIdRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty(value = "停车场id", required = true)
    @NotNull(message = NotifyConstant.PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 标签类型
     */
    @ApiModelProperty(value = "标签类型", required = true)
    @NotNull(message = NotifyConstant.TAG_TYPE_NOT_NULL)
    @FluentValidate(TagTypeValidator.class)
    private Integer tagTypes;
}
