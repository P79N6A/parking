package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;


/**
 * 根据车牌用户获取车辆信息
 *
 * @author walkman
 */
@ApiModel(value = "UserCarInfoGetRequestDto", description = "根据车牌用户获取车辆信息")
public class UserCarInfoGetByPlateNumberRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @NotNull(message = "用户不能为空")
    private Long userId;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", notes = "车牌号")
    @NotEmpty(message = "用户不能为空")
    private String plateNumber;

    @ApiModelProperty(value = "车牌颜色")
    private Integer plateColor;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(Integer plateColor) {
        this.plateColor = plateColor;
    }
}
