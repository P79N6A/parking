package com.zoeeasy.cloud.ucc.user.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 分页查询用户请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserPagedListRequestDto", description = "分页查询用户请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPagedListRequestDto extends SignedSessionPagedRequestDto {

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

    /**
     * 用户状态
     */
    @ApiModelProperty("用户状态")
    private Integer status;

    /**
     * 创建时间-开始
     */
    @ApiModelProperty("创建时间-开始")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date creationTimeStart;

    /**
     * 创建时间-结束
     */
    @ApiModelProperty("创建时间-结束")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date creationTimeEnd;

}
