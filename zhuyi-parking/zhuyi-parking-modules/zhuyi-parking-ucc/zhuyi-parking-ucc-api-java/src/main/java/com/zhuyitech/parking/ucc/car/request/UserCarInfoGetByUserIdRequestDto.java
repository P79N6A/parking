package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 根据多用户ID获取车辆信息
 * @Date: 2018/6/28
 * @author: wh
 */
@ApiModel(value = "UserCarInfoGetByUserIdRequestDto", description = "根据多用户ID获取车辆信息")
public class UserCarInfoGetByUserIdRequestDto  extends BaseDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @NotNull(message = "用户不能为空")
    private String userIds;

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
}
