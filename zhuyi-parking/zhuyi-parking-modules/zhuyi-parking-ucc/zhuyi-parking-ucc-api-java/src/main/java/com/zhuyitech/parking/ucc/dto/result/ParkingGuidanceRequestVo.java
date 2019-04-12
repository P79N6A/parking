package com.zhuyitech.parking.ucc.dto.result;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @ClassName ParkingGuidanceRequestDto
 * @Description 用户导航请求视图模型
 * @Author qhxu
 * @Date 2019/3/19 16:16
 * @Version1.0
 **/
@Data
@ApiModel(value = "ParkingGuidanceRequestVo", description = "用户导航请求视图模型")
public class ParkingGuidanceRequestVo extends SessionDto {

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private String parkingId;

    /**
     * 蓝牙ID
     */
    @ApiModelProperty(value = "蓝牙ID")
    private String bluetoothId;


    /**
     * 终点车位/车位号
     */
    @ApiModelProperty(value = "终点车位/车位号")
    private String endParking;

    /**
     * 客户端类型
     */
    @ApiModelProperty(value = "客户端类型")
    private String clientType;


}
