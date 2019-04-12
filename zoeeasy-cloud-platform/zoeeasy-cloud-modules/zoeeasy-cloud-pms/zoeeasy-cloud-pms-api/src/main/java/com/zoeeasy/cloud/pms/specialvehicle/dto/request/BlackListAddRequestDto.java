package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.pms.park.validator.MobileValidator;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.validator.PlateColorEnumValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by song on 2018/10/18.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BlackListAddRequestDto", description = "添加黑名单请求参数")
public class BlackListAddRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    @NotBlank(message = SpecialVehicleConstant.PACKET_VEHICLE_PLATE_NUMBER_NOT_NULL)
    @Length(min = SpecialVehicleConstant.PACKET_VEHICLE_PLATE_NUMBER_MIN_LENGTH, max = SpecialVehicleConstant.PACKET_VEHICLE_PLATE_NUMBER_MAX_LENGTH,
            message = SpecialVehicleConstant.PACKET_VEHICLE_PLATE_NUMBER_LENGTH_RANGE)
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    @NotNull(message = SpecialVehicleConstant.PACKET_VEHICLE_PLATE_COLOR_NOT_NULL)
    @FluentValidate({PlateColorEnumValidator.class})
    private Integer plateColor;

    /**
     * 车主姓名
     */
    @ApiModelProperty("车主姓名")
    @Length(max = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_NAME_MAX_LENGTH,
            message = SpecialVehicleConstant.PACKET_VEHICLE_SPECIAL_SYMBOLS_ILLEGAL)
    @Pattern(regexp = SpecialVehicleConstant.PACKET_VEHICLE_SPECIAL_SYMBOLS, message = SpecialVehicleConstant.PACKET_VEHICLE_SPECIAL_SYMBOLS_ILLEGAL)
    private String ownerName;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    @Length(max = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_PHONE_MAX_LENGTH,
            message = SpecialVehicleConstant.SPECIAL_VEHICLE_OWNER_PHONE_LENGTH_RANGE)
    @FluentValidate({MobileValidator.class})
    private String ownerPhone;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    @NotNull(message = SpecialVehicleConstant.PACKET_VEHICLE_BEGIN_DATE_NOT_NULL)
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    @NotNull(message = SpecialVehicleConstant.PACKET_VEHICLE_END_DATE_NOT_NULL)
    @DateTimeFormat(pattern = Const.FORMAT_DATETIME)
    private Date endTime;

    /**
     * 停车场id
     */
    @ApiModelProperty("停车场id")
    @NotNull(message = SpecialVehicleConstant.SPECIAL_VEHICLE_PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @Length(max = SpecialVehicleConstant.SPECIAL_VEHICLE_REMARK_MAX_LENGTH,
            message = SpecialVehicleConstant.PACKET_VEHICLE_REMARK_ILLEGAL)
    @Pattern(regexp = SpecialVehicleConstant.PACKET_VEHICLE_SPECIAL_SYMBOLS, message = SpecialVehicleConstant.PACKET_VEHICLE_REMARK_ILLEGAL)
    private String remark;

}
