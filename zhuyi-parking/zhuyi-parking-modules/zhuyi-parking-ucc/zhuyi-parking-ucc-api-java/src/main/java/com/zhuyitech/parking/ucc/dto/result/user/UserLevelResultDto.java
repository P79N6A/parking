package com.zhuyitech.parking.ucc.dto.result.user;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 当前登录用户等级结果
 *
 * @author walkman
 */
@ApiModel(value = "UserLevelResultDto", description = "当前登录用户等级结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserLevelResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 等级
     */
    @ApiModelProperty("等级")
    private Integer level;

    /**
     * 等级说明
     */
    @ApiModelProperty("等级说明")
    private String levelDesc;

}