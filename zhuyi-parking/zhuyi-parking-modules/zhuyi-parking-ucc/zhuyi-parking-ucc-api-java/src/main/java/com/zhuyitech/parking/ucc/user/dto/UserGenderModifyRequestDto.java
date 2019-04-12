package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 修改性别参数
 *
 * @author zwq
 */
@ApiModel(description = "修改性别参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserGenderModifyRequestDto extends SessionDto {

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", required = true)
    @NotNull(message = "性别不能为空")
    private Integer gender;

}
