package com.zhuyitech.parking.tool.dto.result.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.jackson.annotation.ImageUrl;

import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 行驶证识别背面返回结果Dto
 *
 * @author AkeemSuper
 * @date 2018/4/12 0012
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "LicenseRecognitionBackResultDto", description = "驾驶证识别背面返回结果Dto")
public class LicenseRecognitionBackResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 正反面
     */
    @ApiModelProperty(value = "正反面")
    private String prosAndCons;

    /**
     * 核定载人数
     */
    @ApiModelProperty(value = "核定载人数")
    private String approvedPassengerCapacity;

    /**
     * 核定载质量
     */
    @ApiModelProperty(value = "核定载质量")
    private String approvedLoad;

    /**
     * 档案编号
     */
    @ApiModelProperty(value = "档案编号")
    private String fileNo;

    /**
     * 总质量
     */
    @ApiModelProperty(value = "总质量")
    private String grossMass;

    /**
     * 检验记录
     */
    @ApiModelProperty(value = "检验记录")
    private String inspectionRecord;

    /**
     * 外廓尺寸
     */
    @ApiModelProperty(value = "外廓尺寸")
    private String overallDimension;

    /**
     * 准牵引总质量
     */
    @ApiModelProperty(value = "准牵引总质量")
    private String tractionMass;

    /**
     * 整备质量
     */
    @ApiModelProperty(value = "整备质量")
    private String unladenMass;

    /**
     * 车牌号码
     */
    @ApiModelProperty(value = "车牌号码")
    private String plateNumber;

    /**
     * 请求对应的唯一表示
     */
    @ApiModelProperty(value = "请求对应的唯一表示")
    private String requestId;

    /**
     * 识别成功与否
     */
    @ApiModelProperty(value = "识别成功与否")
    private Boolean success;

    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String imageUrl;

}