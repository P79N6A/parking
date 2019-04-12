package com.zhuyitech.parking.ucc.dto.request.record;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 用户停车记录列表的请求参数
 * @Date: 2018/1/18 0018
 * @author: AkeemSuper
 */
@ApiModel(value = "UserParkingRecordListGetRequestDto", description = "用户停车记录列表的请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserParkingRecordListGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "plateNumber", required = true)
    @NotBlank(message = "车牌号不能为空")
    private String plateNumber;

}
