package com.zhuyitech.parking.ucc.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户实名认证视图模型
 *
 * @author walkman
 */
@ApiModel(value = "UserAuthApplyResultDto", description = "用户实名认证视图模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserAuthApplyResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 用户真实姓名
     */
    @ApiModelProperty("用户真实姓名")
    private String realName;

    /**
     * 用户身份证号
     */
    @ApiModelProperty("用户身份证号")
    private String cardNo;

    /**
     * 用户身份证正面照
     */
    @ApiModelProperty("用户身份证正面照")
    private String cardFront;

    /**
     * 用户身份证反面照
     */
    @ApiModelProperty("用户身份证反面照")
    private String cardContrary;

    /**
     * 认证状态
     */
    @ApiModelProperty(value = "实名状态,0未认证 1 认证中  2 已认证  3 认证不通过")
    private Integer authStatus;

    /**
     * 申请时间
     */
    @ApiModelProperty("申请时间")
    private Date applyTime;

    /**
     * 人脸识别照片
     */
    @ApiModelProperty("人脸识别照片")
    private String[] facePhotos;

    /**
     * 审核时间
     */
    @ApiModelProperty("审核时间")
    private Date authTime;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

}
