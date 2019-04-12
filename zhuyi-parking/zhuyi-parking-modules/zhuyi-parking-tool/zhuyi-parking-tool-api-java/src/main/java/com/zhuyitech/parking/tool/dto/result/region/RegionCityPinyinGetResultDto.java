package com.zhuyitech.parking.tool.dto.result.region;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 通过adcode获取市级拼音
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RegionCityPinyinGetResultDto", description = "通过adcode获取市级拼音")
public class RegionCityPinyinGetResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 地区代码
     */
    @ApiModelProperty("code")
    private String code;

    /**
     * 地区名称
     */
    @ApiModelProperty("name")
    private String name;

    /**
     * 全称
     */
    @ApiModelProperty("fullName")
    private String fullName;

    /**
     * 上级ID
     */
    @ApiModelProperty("parentId")
    private Long parentId;

    /**
     * 层级
     */
    @ApiModelProperty("level")
    private Integer level;

    /**
     * 排序
     */
    @ApiModelProperty("order")
    private Integer order;

    /**
     * 拼音名称
     */
    @ApiModelProperty("pinyinName")
    private String pinyinName;

    /**
     * 英语名称
     */
    @ApiModelProperty("nameEn")
    private String nameEn;

    /**
     * 英语简称
     */
    @ApiModelProperty("shortNameEn")
    private String shortNameEn;

    /**
     * 区域编码
     */
    @ApiModelProperty("adCode")
    private String adCode;

    /**
     * 区号
     */
    @ApiModelProperty("areaCode")
    private String areaCode;

    /**
     * 邮编
     */
    @ApiModelProperty("zipCode")
    private String zipCode;

    /**
     * 是否支持限行
     */
    @ApiModelProperty("trafficRestriction")
    private Boolean trafficRestriction;

}
