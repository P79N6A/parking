package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
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
public class UserDeleteRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("uuid")
    private String uuid;

}
