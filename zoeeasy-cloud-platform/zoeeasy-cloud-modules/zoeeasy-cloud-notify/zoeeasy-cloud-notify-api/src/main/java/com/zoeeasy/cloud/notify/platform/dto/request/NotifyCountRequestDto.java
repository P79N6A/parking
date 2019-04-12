package com.zoeeasy.cloud.notify.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.notify.cts.NotifyConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 消息条数列表请求参数
 *
 * @Date: 2018/11/17
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "NotifyCountRequestDto", description = "消息条数列表请求参数")
public class NotifyCountRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "停车场id")
    @NotNull(message = NotifyConstant.PARKING_ID_NOT_NULL)
    private Long parkingId;

}
