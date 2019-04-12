package com.zhuyitech.parking.ucc.car.request;


import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * 根据车牌获取车辆信息
 *
 * @author walkman
 */
@ApiModel(value = "UserCarInfoGetOnlyByPlateNumberRequestDto", description = "根据车牌获取车辆信息")
public class UserCarInfoGetOnlyByPlateNumberRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", notes = "车牌号")
    @NotEmpty(message = "车牌号不能为空")
    private String plateNumber;

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

}
