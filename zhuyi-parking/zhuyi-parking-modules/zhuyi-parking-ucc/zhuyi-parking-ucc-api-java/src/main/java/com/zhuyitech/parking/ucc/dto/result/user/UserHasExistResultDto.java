package com.zhuyitech.parking.ucc.dto.result.user;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户是否已存在结果
 *
 * @author walkman
 */
@ApiModel(description = "用户是否已存在结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserHasExistResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户是否已存在
     */
    @ApiModelProperty(value = "用户是否已存在")
    private Boolean hasExist;

}