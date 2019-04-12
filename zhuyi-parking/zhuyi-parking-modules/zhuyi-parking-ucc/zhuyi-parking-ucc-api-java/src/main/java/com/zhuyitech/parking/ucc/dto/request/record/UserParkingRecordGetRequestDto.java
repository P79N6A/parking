package com.zhuyitech.parking.ucc.dto.request.record;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 根据ID获取用户停车记录的请求参数
 * @Date: 2018/1/18 0018
 * @author: AkeemSuper
 */
@ApiModel(value = "UserParkingRecordGetRequestDto")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserParkingRecordGetRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 记录流水号
     */
    @ApiModelProperty(value = "记录流水号")
    private String recordNo;

}
