package com.zoeeasy.cloud.tool.region.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 区域视图模型
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RegionResultDto", description = "区域视图模型")
public class RegionResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 地区代码
     */
    @ApiModelProperty("地区代码")
    private String code;

    /**
     * 地区名称
     */
    @ApiModelProperty("地区名称")
    private String name;

    /**
     * 地区全称
     */
    @NotBlank(message = "地区全称不能为空")
    @ApiModelProperty("地区全称")
    private String fullName;

    /**
     * 上级ID
     */
    @ApiModelProperty("上级ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    /**
     * 层级
     */
    @ApiModelProperty("层级")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer level;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer order;

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
