package com.zhuyitech.parking.tool.dto.request.region;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 获取上级区域列表请求参数
 * @author AkeemSuper
 * @date 2018/5/10 0010
 */
@ApiModel(value = "RegionGetUpperListRequestDto", description = "获取上级区域列表请求参数")
public class RegionGetUpperListRequestDto  extends SessionDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "等级",required = true)
    @NotNull(message = "等级不能为空")
    private Integer level;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
