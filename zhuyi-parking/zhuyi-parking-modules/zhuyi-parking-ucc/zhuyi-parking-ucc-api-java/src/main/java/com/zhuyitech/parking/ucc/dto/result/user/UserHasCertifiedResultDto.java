package com.zhuyitech.parking.ucc.dto.result.user;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户是否已实名认证结果
 *
 * @author walkman
 */
@ApiModel(description = "用户是否已实名认证结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserHasCertifiedResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 是否已实名认证
     */
    @ApiModelProperty(value = "是否已实名认证")
    private Boolean hasCertified;

}
