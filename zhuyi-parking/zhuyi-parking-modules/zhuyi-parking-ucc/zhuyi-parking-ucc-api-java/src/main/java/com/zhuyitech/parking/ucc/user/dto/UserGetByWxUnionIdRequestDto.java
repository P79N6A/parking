package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 微信UnionID获取用户信息
 *
 * @author walkman
 */
@ApiModel(value = "UserGetByWxUnionIdRequestDto", description = "微信UnionID获取用户信息")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserGetByWxUnionIdRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * unionId
     */
    @ApiModelProperty(value = "unionId")
    private String unionId;

}