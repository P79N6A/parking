package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


/**
 * 用户标签列表获取请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserTagListGetRequestDto", description = "用户标签列表获取请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserTagListGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty(value = "userId", required = true)
    private Long userId;

}
