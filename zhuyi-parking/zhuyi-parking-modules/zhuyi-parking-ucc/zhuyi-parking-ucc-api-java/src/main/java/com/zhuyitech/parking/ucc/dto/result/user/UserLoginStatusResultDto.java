package com.zhuyitech.parking.ucc.dto.result.user;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户登录状态结果视图
 *
 * @author walkman
 */
@ApiModel(value = "UserLoginStatusResultDto", description = "用户登录状态结果视图")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserLoginStatusResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 是否已登录
     */
    @ApiModelProperty("是否已登录")
    private Boolean logined;

}
