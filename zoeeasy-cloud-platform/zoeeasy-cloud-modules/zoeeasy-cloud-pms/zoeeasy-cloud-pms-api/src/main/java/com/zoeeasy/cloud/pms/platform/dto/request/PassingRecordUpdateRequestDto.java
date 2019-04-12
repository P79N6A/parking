package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/10/17 0017
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PassingRecordUpdateRequestDto", description = "更新过车记录请求参数")
public class PassingRecordUpdateRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 过车记录流水号
     */
    @ApiModelProperty(value = "过车记录流水号", required = true)
    @NotBlank(message = "过车记录流水号不能为空")
    private String passingNo;

    /**
     * parkingId
     */
    @ApiModelProperty(value = "parkingId", required = true)
    @NotNull(message = ParkingConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 过车时间
     */
    @ApiModelProperty("过车时间")
    private Date passTime;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 车辆颜色
     */
    @ApiModelProperty("车辆颜色")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty("车辆类型")
    private Integer carType;

    /**
     * 是否有车牌号
     */
    @ApiModelProperty("是否有车牌号")
    private Boolean plateNoExist;

    /**
     * 泊位Id
     */
    @ApiModelProperty("泊位Id")
    private Long parkingLotId;

    /**
     * 过车时间
     */
    @ApiModelProperty("过车时间")
    private Date entryTime;

    /**
     * 异常过车类型
     */
    @ApiModelProperty("异常过车类型")
    private Integer abnormalType;

}
