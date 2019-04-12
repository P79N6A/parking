package com.zoeeasy.cloud.ucc.account;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.jackson.annotation.SensitiveInfo;
import com.scapegoat.infrastructure.jackson.enums.SensitiveType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 登录用户结果
 */
@ApiModel(value = "登录用户结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginUserView extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一性编号
     */
    private Long id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", notes = "姓名")
    @SensitiveInfo(SensitiveType.CHINESE_NAME)
    private String realName;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", notes = "手机号码")
    @SensitiveInfo(SensitiveType.MOBILE_PHONE)
    private String phone;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(value = "电子邮箱", notes = "电子邮箱")
    @SensitiveInfo(SensitiveType.EMAIL_ADDRESS)
    private String email;

    /**
     * 管理员类型
     */
    private Integer type;

    /**
     * 通用状态 1,enabled,可用;2,disabled,不可用;3,deleted,已删除;
     */
    private Integer status;

    /**
     * 登录token
     */
    private String accessToken;

    /**
     * 角色列表
     */
    private String[] roles;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

}
