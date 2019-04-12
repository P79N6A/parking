package com.zhuyitech.parking.ucc.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 用户标签更新请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserTagUpdateRequestDto", description = "用户角色更新请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserTagUpdateRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    /**
     * 标签ID列表
     */
    @ApiModelProperty(value = "标签ID列表", dataType = "List<Long>")
    private List<Long> tagIds;

}
