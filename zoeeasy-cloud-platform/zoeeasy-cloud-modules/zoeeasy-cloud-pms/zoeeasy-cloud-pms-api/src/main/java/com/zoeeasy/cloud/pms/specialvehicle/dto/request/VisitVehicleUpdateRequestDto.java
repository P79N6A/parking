package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.pms.park.validator.MobileValidator;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.validator.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 修改访客车请求参数
 *
 * @date: 2018/10/18.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "VisitVehicleUpdateRequestDto", description = "修改访客车请求参数")
public class VisitVehicleUpdateRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    @NotNull(message = SpecialVehicleConstant.PACKET_VEHICLE_PLATE_COLOR_NOT_NULL)
    @FluentValidate({PlateColorEnumValidator.class})
    private Integer plateColor;

    /**
     * 车牌类型
     */
    @ApiModelProperty(value = "车牌类型")
    @FluentValidate({PlateTypeEnumValidator.class})
    private String plateType;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    @FluentValidate({CarTypeEnumValidator.class})
    private Integer carType;

    /**
     * 车辆颜色
     */
    @ApiModelProperty(value = "车辆颜色")
    @FluentValidate({CarColorEnumValidator.class})
    private Integer carColor;

    /**
     * 访客车类型
     */
    @ApiModelProperty(value = "访客车类型")
    @NotNull(message = SpecialVehicleConstant.SPECIAL_VEHICLE_VISIT_TYPE_NOT_NULL)
    @FluentValidate({VisitTypeEnumValidator.class})
    private Integer visitType;

    /**
     * 车主姓名
     */
    @ApiModelProperty(value = "车主姓名")
    @Length(max = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_NAME_MAX_LENGTH,
            message = SpecialVehicleConstant.PACKET_VEHICLE_SPECIAL_SYMBOLS_ILLEGAL)
    @Pattern(regexp = SpecialVehicleConstant.PACKET_VEHICLE_SPECIAL_SYMBOLS, message = SpecialVehicleConstant.PACKET_VEHICLE_SPECIAL_SYMBOLS_ILLEGAL)
    private String ownerName;

    /**
     * 车主手机
     */
    @ApiModelProperty(value = "车主手机")
    @Length(max = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_PHONE_MAX_LENGTH,
            message = SpecialVehicleConstant.SPECIAL_VEHICLE_OWNER_PHONE_LENGTH_RANGE)
    @FluentValidate({MobileValidator.class})
    private String ownerPhone;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    @NotNull(message = SpecialVehicleConstant.PACKET_VEHICLE_END_DATE_NOT_NULL)
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date endTime;

}
