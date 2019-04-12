package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* @Description:    获取停车场停车记录请求参数
* @Author:         qhxu
* @CreateDate:     2019/3/23 15:39
* @UpdateUser:     qhxu
* @UpdateDate:     2019/3/23 15:39
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingVehicleRecordRequestDto", description = "获取停车场停车记录请求参数")
public class MyPlateNumberParkingGetRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 入车id
     */
    @ApiModelProperty(value = "入车id")
    private Long intoRecordId;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

}
