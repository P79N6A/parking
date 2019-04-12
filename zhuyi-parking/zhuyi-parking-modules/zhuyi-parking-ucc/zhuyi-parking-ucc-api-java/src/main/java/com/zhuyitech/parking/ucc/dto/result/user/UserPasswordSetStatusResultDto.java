package com.zhuyitech.parking.ucc.dto.result.user;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户是否设置登录密码
 *
 * @author walkman
 */
@ApiModel(value = "UserPasswordSetStatusResultDto", description = "用户是否设置登录密码")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPasswordSetStatusResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 是否设置登录密码
     */
    @ApiModelProperty("是否设置登录密码")
    private Boolean set;

    public Boolean getSet() {
        return set;
    }

    public void setSet(Boolean set) {
        this.set = set;
    }
}
