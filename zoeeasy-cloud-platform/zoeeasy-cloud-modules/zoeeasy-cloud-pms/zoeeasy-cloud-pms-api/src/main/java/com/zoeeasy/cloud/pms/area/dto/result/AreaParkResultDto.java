package com.zoeeasy.cloud.pms.area.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by Kane on 2018/11/14.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AreaParkTreeResultDto", description = "区域停车场树参数")
public class AreaParkResultDto extends AuditedEntityDto<Long> {
    private static final long serialVersionUID = 1L;


    /**
     * 区域编号
     */
    @ApiModelProperty("编号")
    private String code;


    /**
     * 区域名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 父级id
     */
    @ApiModelProperty("父级id")
    private Long parentId;

    /**
     * 叶子节点
     */
    @ApiModelProperty("childList")
    private List<AreaParkResultDto> childList;

}
