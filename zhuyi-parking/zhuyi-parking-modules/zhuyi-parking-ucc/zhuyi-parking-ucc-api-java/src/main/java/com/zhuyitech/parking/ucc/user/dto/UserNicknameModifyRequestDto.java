package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改昵称参数
 *
 * @author zwq
 */
@ApiModel(description = "修改昵称参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserNicknameModifyRequestDto extends SessionDto {

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", required = true)
    @NotBlank(message = "昵称不能为空")
    private String nickname;

}
