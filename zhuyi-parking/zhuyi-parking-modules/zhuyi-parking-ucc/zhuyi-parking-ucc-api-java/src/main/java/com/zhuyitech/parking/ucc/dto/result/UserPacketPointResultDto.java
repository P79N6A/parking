package com.zhuyitech.parking.ucc.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 当前登录用户积分结果
 *
 * @author walkman
 */
@ApiModel(value = "UserPacketPointResultDto", description = "当前登录用户积分结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPacketPointResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 积分
     */
    @ApiModelProperty("积分")
    private Integer amount;

}