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
 * 身份证识别背面返回结果Dto
 *
 * @author AkeemSuper
 * @date 2018/4/12 0012
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "IdentityRecognitionBackResultDto", description = "身份证识别背面返回结果Dto")
public class IdentityRecognitionBackResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 正反面
     */
    @ApiModelProperty(value = "正反面")
    private String prosAndCons;

    /**
     * 有效起始时间
     */
    @ApiModelProperty(value = "有效起始时间")
    private String startDate;

    /**
     * 有效结束时间
     */
    @ApiModelProperty(value = "有效结束时间")
    private String endDate;

    /**
     * 签发机关
     */
    @ApiModelProperty(value = "签发机关")
    private String issue;

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
