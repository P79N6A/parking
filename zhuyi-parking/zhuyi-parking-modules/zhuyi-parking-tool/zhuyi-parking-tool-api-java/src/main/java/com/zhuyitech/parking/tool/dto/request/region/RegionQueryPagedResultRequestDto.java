package com.zhuyitech.parking.tool.dto.request.region;

import com.scapegoat.infrastructure.core.dto.request.PagedResultRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 区域列表分页请求参数表
 *
 * @author walkman
 */
@ApiModel(value = "RegionQueryPagedResultRequestDto", description = "区域列表分页请求参数表")
public class RegionQueryPagedResultRequestDto extends PagedResultRequestDto {

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
}
