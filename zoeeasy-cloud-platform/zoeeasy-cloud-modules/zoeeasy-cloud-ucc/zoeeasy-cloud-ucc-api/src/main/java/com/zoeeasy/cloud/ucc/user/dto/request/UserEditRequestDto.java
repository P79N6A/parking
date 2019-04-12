package com.zoeeasy.cloud.ucc.user.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户编辑请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserEditRequestDto", description = "创建用户请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserEditRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户姓名
     */
    private String realName;

    /**
     * 联系电话
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String emailAddress;

    /**
     *
     */
    @ApiModelProperty("部门ID")
    private Long organizationId;

    /**
     *
     */
    @ApiModelProperty("角色ID")
    private List<Long> roleIds;

}
