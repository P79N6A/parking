package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
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
public class ParkingGuidanceParamDto extends BaseDto {

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
     * 蓝牙ID
     */
    @ApiModelProperty(value = "蓝牙ID")
    private String bluetoothID;

    /**
     * 开始车位
     */
    @ApiModelProperty(value = "开始车位")
    private String startParking;

    /**
     * 开始车位
     */
    @ApiModelProperty(value = "终点车位")
    private String endParking;


}
