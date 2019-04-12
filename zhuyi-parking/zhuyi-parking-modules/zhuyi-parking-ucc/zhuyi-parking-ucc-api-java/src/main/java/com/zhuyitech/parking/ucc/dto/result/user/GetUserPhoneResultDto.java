package com.zhuyitech.parking.ucc.dto.result.user;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取用户手机号
 *
 * @author zwq
 */
@ApiModel(value = "GetUserPhoneResultDto", description = "获取用户手机号")
@Data
@EqualsAndHashCode(callSuper = true)
public class GetUserPhoneResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;

}
