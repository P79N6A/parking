package com.zhuyitech.parking.ucc.account.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户登录请求参数
 *
 * @author walkman
 */
@ApiModel(description = "用户登录请求参数")
public class UserLoginRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 登录用户名
     */
    @NotBlank(message = "登录用户名不能为空")
    @ApiModelProperty(value = "登录用户名", required = true)
    private String username;

    /**
     * 登录密码
     */
    @NotBlank(message = "登录密码不能为空")
    @ApiModelProperty(value = "登录密码", required = true)
    private String password;

    /**
     * 客户端类型
     * 0:未知 1:ANDROID 2:APPLE 3:H5 4:WEB
     */
    @NotNull
    @ApiModelProperty(value = "客户端类型", required = true, notes = " 1:ANDROID 2:APPLE 3:H5 4:WEB")
    private Integer terminateType;
    private Integer loginType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTerminateType() {
        return terminateType;
    }

    public void setTerminateType(Integer terminateType) {
        this.terminateType = terminateType;
    }
}
