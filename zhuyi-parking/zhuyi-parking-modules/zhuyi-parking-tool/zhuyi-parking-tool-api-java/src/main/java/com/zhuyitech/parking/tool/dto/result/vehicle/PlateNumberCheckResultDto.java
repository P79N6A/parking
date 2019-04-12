package com.zhuyitech.parking.tool.dto.result.vehicle;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车牌认证结果
 *
 * @Date: 2018/1/10
 * @author: yuzhicheng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CarPlateNumberAuthResultDto", description = "车牌认证结果")
public class PlateNumberCheckResultDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 验证状态 0认证一致 1不一致
     */
    @ApiModelProperty(value = "车牌认证状态")
    private Integer verifyStatus;

    /**
     * 验证消息
     */
    @ApiModelProperty(value = "车牌认证信息")
    private String verifyMsg;

}
