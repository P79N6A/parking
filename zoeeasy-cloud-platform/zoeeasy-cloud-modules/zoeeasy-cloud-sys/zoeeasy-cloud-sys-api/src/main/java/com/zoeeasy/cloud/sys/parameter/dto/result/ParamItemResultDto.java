package com.zoeeasy.cloud.sys.parameter.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取参数类型返回参数
 *
 * @author walkman
 */
@ApiModel(value = "ParamItemResultDto", description = "获取参数数据返回参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParamItemResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 参数编码
     */
    @ApiModelProperty("参数编码")
    private String paramCode;

    /**
     * 参数键
     */
    @ApiModelProperty("参数键")
    private String paramKey;

    /**
     * 参数标签
     */
    @ApiModelProperty("参数标签")
    private String paramLabel;

    /**
     * 参数键
     */
    @ApiModelProperty("参数键")
    private String paramValue;

    /**
     * 参数排序
     */
    @ApiModelProperty("参数排序")
    private Integer sort;

    /**
     * 状态(1正常 0停用)
     */
    @ApiModelProperty("状态")
    private Integer status;

    /**
     * 是否必须
     */
    @ApiModelProperty("是否必须")
    private Boolean required;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

}