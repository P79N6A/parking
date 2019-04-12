package com.zhuyitech.parking.ucc.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户标签视图模型
 *
 * @author walkman
 */
@ApiModel(value = "UserTagResultDto", description = "用户标签视图模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserTagResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 标签ID
     */
    @ApiModelProperty("标签ID")
    private Long tagId;

    /**
     * 标签
     */
    @ApiModelProperty("标签")
    private String tag;

}