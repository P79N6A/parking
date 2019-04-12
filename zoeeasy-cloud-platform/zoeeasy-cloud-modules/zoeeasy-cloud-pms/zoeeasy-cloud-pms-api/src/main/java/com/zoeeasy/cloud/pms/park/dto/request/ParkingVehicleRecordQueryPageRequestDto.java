package com.zoeeasy.cloud.pms.park.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.pms.passing.cts.PassingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 分页获取停车场在停车辆请求参数
 *
 * @author AkeemSuper
 * @Date: 2018/3/1
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingVehicleRecordQueryPageRequestDto", description = "分页获取停车场在停车辆请求参数")
public class ParkingVehicleRecordQueryPageRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty(value = "停车场id")
    private Long parkingId;

    /**
     * 泊位编号
     */
    @ApiModelProperty(value = "泊位编号")
    @Length(max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String parkingLotNumber;

    /**
     * 入车记录id
     */
    @ApiModelProperty(value = "入车记录id")
    private Long intoRecordId;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    @Length(max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private Integer carType;

    /**
     * 停车开始时间(开始)
     */
    @ApiModelProperty(value = "停车开始时间(开始)")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date startTimeStart;

    /**
     * 停车开始时间(结束)
     */
    @ApiModelProperty(value = "停车开始时间(结束)")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date startTimeEnd;
}
