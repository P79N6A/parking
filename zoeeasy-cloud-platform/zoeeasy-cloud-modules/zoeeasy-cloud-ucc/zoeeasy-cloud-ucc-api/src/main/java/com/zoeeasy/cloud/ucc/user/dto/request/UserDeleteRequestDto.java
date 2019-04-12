package com.zoeeasy.cloud.ucc.user.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 删除用户请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserDeleteRequestDto", description = "删除用户请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDeleteRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("uuid")
    private String uuid;

}
