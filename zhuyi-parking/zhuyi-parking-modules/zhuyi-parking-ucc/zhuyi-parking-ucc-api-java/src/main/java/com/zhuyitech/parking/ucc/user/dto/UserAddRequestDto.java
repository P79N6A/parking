package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.scapegoat.infrastructure.validate.annotaion.validation.Email;
import com.scapegoat.infrastructure.validate.annotaion.validation.Mobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 添加用户请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserAddRequestDto", description = "添加用户请求参数")
public class UserAddRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @ApiModelProperty(required = true, value = "用户名", allowEmptyValue = false)
    @NotBlank(message = "用户名不能为空")
    @Length(min = 3, max = 32)
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(required = true, value = "密码", allowEmptyValue = false)
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 32, message = "请输入6-20位密码")
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String portrait;

    /**
     * 联系电话
     */
    @Mobile()
    private String phoneNumber;

    /**
     * 邮箱
     */
    @Email
    private String emailAddress;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
