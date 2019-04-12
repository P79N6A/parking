package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zhuyitech.parking.common.constant.Const;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户个人信息修改
 *
 * @author walkman
 */
@ApiModel(value = "UserProfileModifyRequestDto", description = "用户个人信息修改")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserProfileModifyRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别(1为男性，2为女性)")
    private Integer gender;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    @DateTimeFormat(pattern = Const.FORMAT_DATE)
    private Date birthday;

}
