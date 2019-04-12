package com.zhuyitech.parking.ucc.dto.request.realname;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/7/11 0011
 */
@ApiModel(value = "UserAuthApplyUpdateRequestDto", description = "更新用户认证申请的请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserAuthApplyUpdateRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * userId
     */
    @ApiModelProperty(value = "userId")
    private Long userId;

    /**
     * realName
     */
    @ApiModelProperty(value = "realName")
    private String realName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "gender")
    private Integer gender;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "cardNo")
    private String cardNo;

    /**
     * 认证状态
     */
    @ApiModelProperty(value = "authStatus")
    private Integer authStatus;

    /**
     * 身份证正面
     */
    @ApiModelProperty(value = "cardFront")
    private String cardFront;

    /**
     * 身份证反面
     */
    @ApiModelProperty(value = "cardContrary")
    private String cardContrary;

    /**
     * 人脸识别照片
     */
    @ApiModelProperty(value = "facePhotos")
    private String facePhotos;

    /**
     * 备注
     */
    @ApiModelProperty(value = "remark")
    private String remark;

    /**
     * 申请时间
     */
    @ApiModelProperty(value = "applyTime")
    private Date applyTime;

    /**
     * 审核用户ID
     */
    @ApiModelProperty(value = "authUserId")
    private Long authUserId;

    /**
     * 审核时间
     */
    @ApiModelProperty(value = "authTime")
    private Date authTime;

}
