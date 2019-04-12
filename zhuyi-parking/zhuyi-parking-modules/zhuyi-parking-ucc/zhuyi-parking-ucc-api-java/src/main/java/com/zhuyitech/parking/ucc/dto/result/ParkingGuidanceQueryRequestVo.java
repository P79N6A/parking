package com.zhuyitech.parking.ucc.dto.result;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName ParkingGuidanceRequestDto
 * @Description TODO
 * @Author qhxu
 * @Date 2019/3/19 16:16
 * @Version1.0
 **/
@Data
@ApiModel(description = "根据车位车牌查询参数")
public class ParkingGuidanceQueryRequestVo extends SessionEntityDto<Long> {

    /**
     * 泊位编号
     */
    @ApiModelProperty(value = "泊位编号")
    private String parkingLotNumber;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    /**
     * 客户端类型
     */
    @ApiModelProperty(value = "客户端类型")
    private String clientType;


}
