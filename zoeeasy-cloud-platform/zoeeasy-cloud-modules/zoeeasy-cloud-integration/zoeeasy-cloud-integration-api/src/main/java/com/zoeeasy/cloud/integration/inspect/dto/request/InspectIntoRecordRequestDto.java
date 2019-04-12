package com.zoeeasy.cloud.integration.inspect.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.inspect.cts.InspectConstant;
import com.zoeeasy.cloud.pms.passing.cts.PassingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 巡检入车记录请求参数
 *
 * @author zwq
 * @date 2018-09-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "InspectIntoRecordRequestDto", description = "巡检入车记录请求参数")
public class InspectIntoRecordRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 平台过车流水号
     */
    @ApiModelProperty(value = "passingNo", required = true)
    @NotBlank(message = PassingConstant.PASSING_NUMBER_NOT_EMPTY)
    @Length(min = PassingConstant.PASSING_NUMBER_MIN_LENGTH, max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String passingNo;

    /**
     * 泊位code
     */
    @ApiModelProperty(value = "泊位code", required = true)
    @NotBlank(message = InspectConstant.PARKING_LOT_ID_NOT_EMPTY)
    private String parkingLotCode;

    /**
     * 入车时间
     */
    @ApiModelProperty(value = "入车时间", required = true)
    @NotNull(message = PassingConstant.ENTRY_TIME_NOT_EMPTY)
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date startTime;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色", required = true)
    @NotNull(message = PassingConstant.PLATE_COLOR_NOT_EMPTY)
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型", required = true)
    @NotNull(message = PassingConstant.CAR_TYPE_NOT_EMPTY)
    private Integer carType;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    @NotBlank(message = PassingConstant.PLATE_NUMBER_NOT_EMPTY)
    private String plateNumber;

    /**
     * 是否出车
     */
    @ApiModelProperty(value = "是否出车", required = true)
    @NotNull(message = InspectConstant.TURN_OUT_NOT_EMPTY)
    private Boolean turnOut;

    /**
     * 车辆出场时间
     */
    @ApiModelProperty(value = "车辆出场时间")
    @DateTimeFormat(pattern = Const.FORMAT_DATETIME)
    private Date endTime;

}
