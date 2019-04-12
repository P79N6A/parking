package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.pms.park.validator.MobileValidator;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.validator.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

/**
 * 添加包期车请求参数
 * Created by song on 2018/10/15.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PacketVehicleAddRequestDto", description = "添加包期车请求参数")
public class PacketVehicleAddRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 是否全部停车场
     */
    @ApiModelProperty("是否全部停车场")
    @NotNull(message = SpecialVehicleConstant.PACKET_VEHICLE_ALL_PARKING_NOT_NULL)
    @FluentValidate({AllParkingEnumValidator.class})
    private Integer allParking;

    /**
     * 包期类型
     */
    @ApiModelProperty("包期类型")
    @NotNull(message = SpecialVehicleConstant.PACKET_VEHICLE_PACKET_TYPE_NOT_NULL)
    @FluentValidate({PacketTypeEnumValidator.class})
    private Integer packetType;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    @NotNull(message = SpecialVehicleConstant.PACKET_VEHICLE_BEGIN_DATE_NOT_NULL)
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATE)
    private Date beginDate;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    @NotNull(message = SpecialVehicleConstant.PACKET_VEHICLE_END_DATE_NOT_NULL)
    @DateTimeFormat(pattern = Const.FORMAT_DATE)
    private Date endDate;

    /**
     * 车牌号码
     */
    @ApiModelProperty("车牌号码")
    @NotBlank(message = SpecialVehicleConstant.PACKET_VEHICLE_PLATE_NUMBER_NOT_NULL)
    @Length(min = SpecialVehicleConstant.PACKET_VEHICLE_PLATE_NUMBER_MIN_LENGTH, max = SpecialVehicleConstant.PACKET_VEHICLE_PLATE_NUMBER_MAX_LENGTH,
            message = SpecialVehicleConstant.PACKET_VEHICLE_PLATE_NUMBER_LENGTH_RANGE)
    private String plateNumber;

    /**
     * 车辆类型
     */
    @ApiModelProperty("车辆类型")
    @NotNull(message = SpecialVehicleConstant.PACKET_VEHICLE_CAR_TYPE_NOT_NULL)
    @FluentValidate({CarTypeEnumValidator.class})
    private Integer carType;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    @NotNull(message = SpecialVehicleConstant.PACKET_VEHICLE_PLATE_COLOR_NOT_NULL)
    @FluentValidate({PlateColorEnumValidator.class})
    private Integer plateColor;

    /**
     * 车牌类型
     */
    @ApiModelProperty("车牌类型")
    @NotNull(message = SpecialVehicleConstant.PACKET_VEHICLE_PLATE_TYPE_NOT_NULL)
    @FluentValidate({PlateTypeEnumValidator.class})
    private String plateType;

    /**
     * 车辆颜色
     */
    @ApiModelProperty("车辆颜色")
    @FluentValidate({CarColorEnumValidator.class})
    private Integer carColor;

    /**
     * 车辆品牌
     */
    @ApiModelProperty("车辆品牌")
    private String carBrand;

    /**
     * 车主姓名
     */
    @ApiModelProperty("车主姓名")
    @NotBlank(message = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_NAME_NOT_NULL)
    @Length(min = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_NAME_MIN_LENGTH, max = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_NAME_MAX_LENGTH,
            message = SpecialVehicleConstant.PACKET_VEHICLE_SPECIAL_SYMBOLS_ILLEGAL)
    @Pattern(regexp = SpecialVehicleConstant.PACKET_VEHICLE_SPECIAL_SYMBOLS, message = SpecialVehicleConstant.PACKET_VEHICLE_SPECIAL_SYMBOLS_ILLEGAL)
    private String ownerName;

    /**
     * 车主手机
     */
    @ApiModelProperty("车主手机")
    @NotBlank(message = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_PHONE_NOT_NULL)
    @Length(min = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_PHONE_MIN_LENGTH, max = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_PHONE_MAX_LENGTH,
            message = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_PHONE_LENGTH_RANGE)
    @FluentValidate({MobileValidator.class})
    private String ownerPhone;

    /**
     * 车主身份证
     */
    @ApiModelProperty("车主身份证")
    @NotBlank(message = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_CARD_NO_NOT_NULL)
    @Length(min = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_CARD_NO_MIN_LENGTH, max = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_CARD_NO_MAX_LENGTH,
            message = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_CARD_NO_LENGTH_RANGE)
    @Pattern(regexp = SpecialVehicleConstant.CARD_NO, message = SpecialVehicleConstant.CARD_NO_ILLEGAL)
    private String ownerCardNo;

    /**
     * 车主地址
     */
    @ApiModelProperty("车主地址")
    @Length(max = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_ADDRESS_MAX_LENGTH, message = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_ADDRESS_LENGTH_RANGE)
    @Pattern(regexp = SpecialVehicleConstant.PACKET_VEHICLE_SPECIAL_SYMBOLS, message = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_ADDRESS_LENGTH_RANGE)
    private String ownerAddress;

    /**
     * 车主邮件
     */
    @ApiModelProperty("车主邮件")
    @Length(max = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_EMAIL_MAX_LENGTH, message = SpecialVehicleConstant.PACKET_VEHICLE_OWNER_EMAIL_LENGTH_RANGE)
    @Email
    private String ownerEmail;

    /**
     * 规则id
     */
    @ApiModelProperty("规则id")
    private List<ParkingPacketRuleDto> rules;

}
