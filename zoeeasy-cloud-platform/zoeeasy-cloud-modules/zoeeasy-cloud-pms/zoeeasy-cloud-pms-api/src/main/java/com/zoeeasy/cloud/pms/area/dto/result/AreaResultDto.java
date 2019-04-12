package com.zoeeasy.cloud.pms.area.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 区域视图模型
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AreaResultDto", description = "区域视图模型")
public class AreaResultDto extends AuditedEntityDto {
    private static final long serialVersionUID = 1L;

    /**
     * 地区名称
     */
    @ApiModelProperty("区域名称")
    private String name;

    /**
     * 区域编码
     */
    @ApiModelProperty("区域编码")
    private String code;

    /**
     * 层级
     */
    @ApiModelProperty("层级")
    private Integer level;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer order;

}
