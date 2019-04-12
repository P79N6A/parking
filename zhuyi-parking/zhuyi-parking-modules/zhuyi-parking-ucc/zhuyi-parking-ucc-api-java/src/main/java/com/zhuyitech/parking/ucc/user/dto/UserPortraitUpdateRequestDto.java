package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @Date: 2018/1/3 0003
 * @author: AkeemSuper
 */
@ApiModel(value = "UserUpdatePortraitRequestDto", description = "用户修改个人头像的请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPortraitUpdateRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "新个人图像的url")
    @NotEmpty(message = "图像的url不能为空")
    private String portraitUrl;

}
