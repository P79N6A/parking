package com.zhuyitech.parking.ucc.dto.request.record;


import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 分页获取用户停车记录列表的请求参数
 * @Date: 2018/1/18 0018
 * @author: AkeemSuper
 */
@ApiModel(value = "UserParkingRecordPagedResultRequestDto", description = "分页获取用户停车记录列表的请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserParkingRecordPagedResultRequestDto extends SessionPagedRequestDto {

    private static final long serialVersionUID = 1L;


    /**
     * 车牌号
     */
    @ApiModelProperty(value = "plateNumber", required = true)
    @NotBlank(message = "车牌号不能为空")
    private String plateNumber;

}
