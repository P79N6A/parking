package com.zoeeasy.cloud.integration.pass.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.inspect.cts.InspectConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/10/15 0015
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PassVehicleRecordAddRequestDto", description = "添加过车记录请求参数")
public class PassVehicleRecordAddRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 泊位id
     */
    @ApiModelProperty(value = "泊位id", required = true)
    @NotBlank(message = InspectConstant.PARKING_LOT_CODE_NOT_EMPTY)
    private String parkingLotCode;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    @NotBlank(message = "车牌号不能为空")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色", required = true)
    @NotNull(message = "车牌颜色不能为空")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型", required = true)
    @NotNull(message = "车辆类型不能为空")
    private Integer carType;

    /**
     * 过车时间
     */
    @ApiModelProperty(value = "过车时间", required = true)
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    @NotNull(message = "过车时间不能为空")
    private Date passTime;

}
