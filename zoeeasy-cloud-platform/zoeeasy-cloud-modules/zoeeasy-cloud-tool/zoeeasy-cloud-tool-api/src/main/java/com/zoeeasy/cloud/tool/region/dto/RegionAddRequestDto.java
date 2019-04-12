package com.zoeeasy.cloud.tool.region.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 添加地区请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RegionAddRequestDto", description = "添加地区请求参数")
public class RegionAddRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 地区代码
     */
    @NotBlank(message = "地区代码不能为空")
    @ApiModelProperty("地区代码")
    private String code;

    /**
     * 地区名称
     */
    @NotBlank(message = "地区名称不能为空")
    @ApiModelProperty("地区名称")
    private String name;

    /**
     * 地区全称
     */
    @ApiModelProperty("地区全称")
    private String fullName;

    /**
     * 上级ID
     */
    @ApiModelProperty("上级Code")
    private String parentCode;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer order;

    /**
     * 层级
     */
    @ApiModelProperty("层级")
    private Integer level;

    /**
     * 拼音名称
     */
    @ApiModelProperty("拼音名称")
    private String pinyinName;

    /**
     * 英语名称
     */
    @ApiModelProperty("英语名称")
    private String nameEn;

    /**
     * 英语简称
     */
    @ApiModelProperty("英语简称")
    private String shortNameEn;

    /**
     * 区域编码
     */
    @ApiModelProperty("区域编码")
    private String adCode;

    /**
     * 区号
     */
    @ApiModelProperty("区号")
    private String areaCode;

    /**
     * 邮编
     */
    @ApiModelProperty("邮编")
    private String zipCode;

    /**
     * 是否支持限行
     */
    @ApiModelProperty("是否支持限行")
    private Boolean trafficRestriction;

}
