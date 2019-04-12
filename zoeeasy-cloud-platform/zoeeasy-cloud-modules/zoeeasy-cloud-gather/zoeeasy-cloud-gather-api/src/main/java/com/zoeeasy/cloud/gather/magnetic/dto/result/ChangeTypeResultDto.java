package com.zoeeasy.cloud.gather.magnetic.dto.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 出车类型参数返回
 *
 * @Date: 2018/9/30
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ChangeTypeResultDto", description = "出车类型参数返回")
public class ChangeTypeResultDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 出车类型
     */
    @ApiModelProperty(value = "出车类型")
    private Integer changeType;

    /**
     * 出车类型名称
     */
    private String changeTypeName;
}
