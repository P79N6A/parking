package com.zoeeasy.cloud.integration.open.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.integration.cts.IntegrationConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author AkeemSuper
 * @date 2018/12/1 0001
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CloudAddPassRecordRequestDto", description = "停车管理系统添加过车记录请求参数")
public class CloudAddParkingImageRequest extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 过车记录编号
     */
    @ApiModelProperty(value = "过车记录编号", required = true)
    @NotBlank(message = IntegrationConstant.PASSING_CODE_NOT_EMPTY)
    @Length(min = IntegrationConstant.MIN, max = IntegrationConstant.MAX, message = IntegrationConstant.LENGTH_RANGE)
    private String passingCode;

    /**
     * 云平台停车场Code
     */
    @ApiModelProperty(value = "云平台停车场Code", required = true)
    @NotBlank(message = IntegrationConstant.CLOUD_PARKING_NOT_EMPTY)
    @Length(min = IntegrationConstant.MIN, max = IntegrationConstant.MAX, message = IntegrationConstant.LENGTH_RANGE)
    private String cloudParkingCode;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    @NotBlank(message = IntegrationConstant.PLATE_NUMBER_NOT_EMPTY)
    @Length(min = IntegrationConstant.MIN, max = IntegrationConstant.MAX, message = IntegrationConstant.LENGTH_RANGE)
    private String plateNumber;

    /**
     * 泊位号
     */
    @ApiModelProperty(value = "泊位号")
    @Length(max = IntegrationConstant.MAX, message = IntegrationConstant.LENGTH_RANGE)
    private String parkingLotNumber;

    /**
     * 泊位编号
     */
    @ApiModelProperty("泊位编号")
    @Length(max = IntegrationConstant.MAX, message = IntegrationConstant.LENGTH_RANGE)
    private String parkingLotCode;

    /**
     * 全景图像
     */
    @ApiModelProperty(value = "全景图像,多个图像,隔开")
    private String fullImage;

    /**
     * 车牌图像
     */
    @ApiModelProperty("车牌图像 ,隔开")
    private String plateImage;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色", required = true)
    @NotNull(message = IntegrationConstant.PLATE_COLOR_NOT_EMPTY)
    private Integer plateColor;

    /**
     * 整牌可信度
     */
    @ApiModelProperty(value = "整牌可信度")
    private Integer confidence;

}
