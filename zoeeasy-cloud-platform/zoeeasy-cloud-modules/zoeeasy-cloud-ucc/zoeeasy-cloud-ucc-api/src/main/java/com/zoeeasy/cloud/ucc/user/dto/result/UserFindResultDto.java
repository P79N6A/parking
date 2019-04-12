package com.zoeeasy.cloud.ucc.user.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/11/15 0015
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserFindRequestDto", description = "查找用户请求参数")
public class UserFindResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    @ApiModelProperty("租户id")
    private Long tenantId;

    private String account;

    private String salt;

    private String password;

    /**
     * uuid
     */
    @ApiModelProperty("uuid")
    private String uuid;

    /**
     * 用户类型
     */
    @ApiModelProperty("用户类型")
    private Integer userType;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
    private String realName;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String phoneNumber;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String emailAddress;

    private Integer status;
}
