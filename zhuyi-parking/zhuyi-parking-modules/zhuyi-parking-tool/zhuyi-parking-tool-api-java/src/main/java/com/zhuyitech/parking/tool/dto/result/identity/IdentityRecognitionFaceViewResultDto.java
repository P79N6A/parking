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
@ApiModel(value = "IdentityRecognitionFaceViewResultDto", description = "身份证识别正面返回结果Dto")
public class IdentityRecognitionFaceViewResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String cardNo;

    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String imageUrl;

}
