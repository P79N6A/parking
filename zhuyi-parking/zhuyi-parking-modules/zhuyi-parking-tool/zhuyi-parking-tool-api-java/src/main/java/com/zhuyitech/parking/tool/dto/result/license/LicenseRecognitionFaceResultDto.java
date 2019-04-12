package com.zhuyitech.parking.tool.dto.result.license;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.jackson.annotation.ImageUrl;

import java.util.Date;

import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 行驶识别正面返回结果Dto
 *
 * @author AkeemSuper
 * @date 2018/4/12 0012
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "LicenseRecognitionFaceResultDto", description = "驾驶识别正面返回结果Dto")
public class LicenseRecognitionFaceResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 正反面
     */
    @ApiModelProperty(value = "正反面")
    private String prosAndCons;

    /**
     * 车牌号码
     */
    @ApiModelProperty(value = "车牌号码")
    private String plateNumber;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private String vehicleType;

    /**
     * 所有人名称
     */
    @ApiModelProperty(value = "所有人名称")
    private String ownerName;

    /**
     * 使用性质
     */
    @ApiModelProperty(value = "使用性质")
    private String useCharacter;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     * 品牌型号
     */
    @ApiModelProperty(value = "品牌型号")
    private String model;

    /**
     * 车辆识别代号
     */
    @ApiModelProperty(value = "车辆识别代号")
    private String vin;

    /**
     * 发动机号码
     */
    @ApiModelProperty(value = "发动机号码")
    private String engineNumber;

    /**
     * 注册日期
     */
    @ApiModelProperty(value = "注册日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATE, timezone = Const.TIMEZONE_GMT8)
    private Date registerDate;

    /**
     * 发证日期
     */
    @ApiModelProperty(value = "发证日期")
    private String issueDate;

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
