package com.zhuyitech.parking.ucc.dto.result;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户是否注册
 *
 * @author walkman
 */
@ApiModel(value = "UserHasRegisteredResultDto", description = "用户是否注册")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserHasRegisteredResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 是否注册
     */
    @ApiModelProperty("是否注册")
    private Boolean registered;

}
