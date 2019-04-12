package com.zhuyitech.parking.tool.dto.request.region;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 区域列表请求参数表
 *
 * @author walkman
 */
@ApiModel(value = "RegionListGetRequestDto", description = "区域列表请求参数表")
public class RegionListGetRequestDto extends EntityDto<Long> {

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
     * 上级ID
     */
    @ApiModelProperty("上级ID")
    private Long parentId;

    /**
     * 层级
     */
    @ApiModelProperty("层级")
    private Integer level;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getTrafficRestriction() {
        return trafficRestriction;
    }

    public void setTrafficRestriction(Boolean trafficRestriction) {
        this.trafficRestriction = trafficRestriction;
    }
}