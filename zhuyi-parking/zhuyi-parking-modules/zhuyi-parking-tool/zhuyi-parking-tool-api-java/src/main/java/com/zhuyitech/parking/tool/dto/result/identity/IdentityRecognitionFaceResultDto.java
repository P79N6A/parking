package com.zhuyitech.parking.tool.dto.result.identity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.jackson.annotation.ImageUrl;

import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 身份证识别正面返回结果Dto
 *
 * @author AkeemSuper
 * @date 2018/4/12 0012
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "IdentityRecognitionFaceResultDto", description = "身份证识别正面返回结果Dto")
public class IdentityRecognitionFaceResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 住址
     */
    @ApiModelProperty(value = "住址")
    private String address;

    /**
     * 正反面
     */
    @ApiModelProperty(value = "正反面")
    private String pronsAndCons;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 民族
     */
    @ApiModelProperty(value = "民族")
    private String nationality;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String cardNo;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    private String birthday;

    /**
     * 请求是否成功
     */
    @ApiModelProperty(value = "请求是否成功")
    private Boolean success;

    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String imageUrl;

}
