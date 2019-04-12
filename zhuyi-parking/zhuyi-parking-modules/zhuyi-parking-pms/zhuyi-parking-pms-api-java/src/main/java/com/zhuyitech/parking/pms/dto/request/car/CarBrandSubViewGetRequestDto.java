package com.zhuyitech.parking.pms.dto.request.car;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


/**
 * 获取车子品牌请求参数
 *
 * @author walkman
 */
@ApiModel(value = "CarBrandSubViewGetRequestDto", description = "获取车型请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class CarBrandSubViewGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 上级ID
     */
    @ApiModelProperty(value = "上级ID", required = true)
    @NotNull(message = "上级ID不能为空")
    private Long parentId;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 全称
     */
    @ApiModelProperty("全称")
    private String fullName;

    /**
     * 首字母
     */
    @ApiModelProperty("首字母")
    private String initial;

}