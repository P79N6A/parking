package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 微信小程序二维码请求参数
 *
 * @Date: 2019/3/19
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WeChatSmallAppQrcodeRequestDto", description = "微信小程序二维码请求参数")
public class WeChatSmallAppQrcodeRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;
    /**
     * 停车场id
     */
    @NotNull(message = "停车场不能为空")
    @ApiModelProperty("停车场Id")
    private Long parkingId;
}
