package com.zhuyitech.parking.tool.dto.request.region;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 修改地区请求参数
 *
 * @author walkman
 */
@ApiModel(value = "RegionUpdateRequestDto", description = "修改地区请求参数")
public class RegionUpdateRequestDto extends EntityDto<Long> {

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
    @NotBlank(message = "地区全称不能为空")
    @ApiModelProperty("地区全称")
    private String fullName;

    /**
     * 上级ID
     */
    @ApiModelProperty("上级ID")
    private Long parentId;

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
     * 区域编码
     */
    @ApiModelProperty("区域编码")
    private String adCode;

    /**
     * 拼音名称
     */
    @ApiModelProperty("拼音名称")
    private String pinyinName;

    /**
     * 是否支持限行
     */
    @ApiModelProperty("是否支持限行")
    private Boolean trafficRestriction;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getShortNameEn() {
        return shortNameEn;
    }

    public void setShortNameEn(String shortNameEn) {
        this.shortNameEn = shortNameEn;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getPinyinName() {
        return pinyinName;
    }

    public void setPinyinName(String pinyinName) {
        this.pinyinName = pinyinName;
    }

    public Boolean getTrafficRestriction() {
        return trafficRestriction;
    }

    public void setTrafficRestriction(Boolean trafficRestriction) {
        this.trafficRestriction = trafficRestriction;
    }
}