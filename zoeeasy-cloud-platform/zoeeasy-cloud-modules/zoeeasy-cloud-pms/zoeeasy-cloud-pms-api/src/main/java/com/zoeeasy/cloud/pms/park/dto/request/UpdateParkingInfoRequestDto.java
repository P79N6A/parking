package com.zoeeasy.cloud.pms.park.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * 停车场管理系统修改停车场请求参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UpdateParkingInfoRequestDto", description = "停车场管理系统修改停车场请求参数")
public class UpdateParkingInfoRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Long tenantId;

    /**
     * 云平台停车场编号
     */
    @ApiModelProperty(value = "code", required = true)
    @NotBlank(message = ParkingConstant.PARKING_INFO_PARKING_CODE_NOT_NULL)
    @Length(min = ParkingConstant.PARKING_INFO_PARKING_CODE_MIN_LENGTH, max = ParkingConstant.PARKING_INFO_PARKING_CODE_MAX_LENGTH, message = ParkingConstant.PARKING_INFO_PARKING_CODE_LENGTH_RANGE)
    @Pattern(regexp = ParkingConstant.PARKING_CODE_PATTERN, message = ParkingConstant.PARKING_CODE_ILLEGAL)
    private String cloudParkingCode;

    /**
     * 管理系统停车场编号
     */
    @ApiModelProperty("管理系统停车场编号")
    @NotBlank(message = ParkingConstant.PARKING_LOCAL_CODE_NOT_NULL)
    @Length(min = ParkingConstant.PARKING_LOCAL_CODE_MIN_LENGTH, max = ParkingConstant.PARKING_LOCAL_CODE_MAX_LENGTH,
            message = ParkingConstant.PARKING_LOCAL_CODE_LENGTH_RANGE)
    @Pattern(regexp = ParkingConstant.PARKING_LOCAL_CODE_PATTERN, message = ParkingConstant.PARKING_LOCAL_CODE_ILLEGAL)
    private String parkingCode;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    @NotBlank(message = ParkingConstant.PARKING_INFO_PARKING_FULLNAME_NOT_NULL)
    @Length(min = ParkingConstant.PARKING_INFO_PARKING_FULLNAME_MIN_LENGTH, max = ParkingConstant.PARKING_INFO_PARKING_FULLNAME_MAX_LENGTH, message = ParkingConstant.PARKING_INFO_PARKING_FULLNAME_LENGTH_RANGE)
    private String parkingName;

    /**
     * 停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场
     */
    @ApiModelProperty("停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场")
    @FluentValidate({LotTypeEnumValidator.class})
    private String lotType;

    /**
     * 车位总数
     */
    @ApiModelProperty("车位总数")
    private Integer lotTotal;

    /**
     * 可用车位数
     */
    @ApiModelProperty("可用车位数")
    private Integer lotAvailable;

    /**
     * 固定车总数
     */
    @ApiModelProperty("固定车总数")
    private Integer lotFixed;

    /**
     * 详细地址
     */
    @ApiModelProperty("详细地址")
    @Length(max = ParkingConstant.PARKING_INFO_PARKING_ADDRESS_MAX_LENGTH, message = ParkingConstant.PARKING_INFO_PARKING_ADDRESS_LENGTH_RANGE)
    private String address;

    /**
     * 邮编
     */
    @ApiModelProperty("邮编")
    @Length(max = ParkingConstant.PARKING_INFO_PARKING_ZIPCODE_MAX_LENGTH, message = ParkingConstant.PARKING_INFO_PARKING_ZIPCODE_LENGTH_RANGE)
    private String zipCode;

    /**
     * 车场描述
     */
    @ApiModelProperty("车场描述")
    private String description;

    /**
     * 开始营业时间,格式HH:mm:ss
     */
    @ApiModelProperty("开始营业时间,格式HH:mm:ss")
    @NotBlank(message = ParkingConstant.PARKING_INFO_PARKING_OPENSTARTTIME_NOT_NULL)
    @Length(min = ParkingConstant.PARKING_INFO_PARKING_OPENSTARTTIME_MIN_LENGTH, max = ParkingConstant.PARKING_INFO_PARKING_OPENSTARTTIME_MAX_LENGTH,
            message = ParkingConstant.PARKING_INFO_PARKING_OPENSTARTTIME_LENGTH_RANGE)
    @Pattern(regexp = ParkingConstant.OPEN_START_END_TIME_PATTERN, message = ParkingConstant.OPEN_START_END_TIME_ILLEGAL)
    private String openStartTime;

    /**
     * 结束营业时间,格式HH:mm
     */
    @ApiModelProperty("结束营业时间,格式HH:mm")
    @NotBlank(message = ParkingConstant.PARKING_INFO_PARKING_OPENENDTIME_NOT_NULL)
    @Length(min = ParkingConstant.PARKING_INFO_PARKING_OPENENDTIME_MIN_LENGTH, max = ParkingConstant.PARKING_INFO_PARKING_OPENENDTIME_MAX_LENGTH,
            message = ParkingConstant.PARKING_INFO_PARKING_OPENENDTIME_LENGTH_RANGE)
    @Pattern(regexp = ParkingConstant.OPEN_START_END_TIME_PATTERN, message = ParkingConstant.OPEN_START_END_TIME_ILLEGAL)
    private String openEndTime;

    /**
     * 是否收费 0:不收费 1 收费
     */
    @ApiModelProperty("是否收费 0:不收费 1 收费")
    @NotNull(message = ParkingConstant.PARKING_INFO_PARKING_CHARGEFEE_NOT_NULL)
    @FluentValidate({ChargeFeeEnumValidator.class})
    private Integer chargeFee;

    /**
     * 缴费方式（1为停车卡缴费，2为物料缴费，3为中央缴费机）
     */
    @ApiModelProperty("缴费方式（1为停车卡缴费，2为物料缴费，3为中央缴费机）")
    @FluentValidate({PayModeEnumValidator.class})
    private String payMode;

    /**
     * 收费方式 1: 离场后缴费 2: 缴费后离场
     */
    @ApiModelProperty("收费方式 1: 离场后缴费 2: 缴费后离场")
    @FluentValidate({ChargeModeEnumValidator.class})
    private Integer chargeMode;

    /**
     * 支付方式（1为支付宝在线缴费，2为支付宝代扣缴费，3当面付)，如支持多种方式以'',''进行间隔
     */
    @ApiModelProperty("支付方式（1为支付宝在线缴费，2为支付宝代扣缴费，3当面付)，如支持多种方式以'',''进行间隔")
    @FluentValidate({PayTypeEnumValidator.class})
    private String payType;

    /**
     * 收费规则详情
     */
    @ApiModelProperty("收费规则详情")
    @NotNull(message = ParkingConstant.CHARGE_DESCRIPTION_NOT_NULL)
    @Length(max = ParkingConstant.PARKING_INFO_PARKING_CHARGERULE_MAX_LENGTH, message = ParkingConstant.PARKING_INFO_PARKING_CHARGERULE_LENGTH_RANGE)
    private String chargeRule;

    /**
     * 免费停车时长：单位分钟
     */
    @ApiModelProperty("免费停车时长：单位分钟")
    private Integer freeTime;

    /**
     * 是否支持预约
     */
    @ApiModelProperty("是否支持预约")
    @FluentValidate({AppointmentEnumValidator.class})
    private Integer supportAppointment;

    /**
     * 支持预约车位总数
     */
    @ApiModelProperty("支持预约车位总数")
    private Integer lotAppointmentTotal;

    /**
     * 预约规则的简短描述
     */
    @ApiModelProperty("预约规则的简短描述")
    private String appointmentRule;

    /**
     * 联系人
     */
    @ApiModelProperty("联系人")
    @Length(max = ParkingConstant.PARKING_INFO_PARKING_CONTACTNAME_MAX_LENGTH, message = ParkingConstant.PARKING_INFO_PARKING_CONTACTNAME_LENGTH_RANGE)
    private String contactName;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    @Length(max = ParkingConstant.PARKING_INFO_PARKING_CONTACTTEL_MAX_LENGTH, message = ParkingConstant.PARKING_INFO_PARKING_CONTACTTEL_LENGTH_RANGE)
    private String contactTel;

    /**
     * 联系手机
     */
    @ApiModelProperty("联系手机")
    @FluentValidate({MobileValidator.class})
    private String contactPhone;

    /**
     * 联系人微信
     */
    @ApiModelProperty("联系人微信")
    @Length(max = ParkingConstant.PARKING_INFO_PARKING_CONTACTWEIXIN_MAX_LENGTH, message = ParkingConstant.PARKING_INFO_PARKING_CONTACTWEIXIN_LENGTH_RANGE)
    private String contactWeixin;

    /**
     * 联系人QQ
     */
    @ApiModelProperty("联系人QQ")
    @Length(max = ParkingConstant.PARKING_INFO_PARKING_CONTACTQQ_MAX_LENGTH, message = ParkingConstant.PARKING_INFO_PARKING_CONTACTQQ_LENGTH_RANGE)
    @FluentValidate({QQValidator.class})
    private String contactQQ;

    /**
     * 联系人支付宝
     */
    @ApiModelProperty("联系人支付宝")
    @Length(max = ParkingConstant.PARKING_INFO_PARKING_CONTACTALIPAY_MAX_LENGTH, message = ParkingConstant.PARKING_INFO_PARKING_CONTACTALIPAY_LENGTH_RANGE)
    private String contactAlipay;

    /**
     * 联系人邮箱
     */
    @ApiModelProperty("联系人邮箱")
    @Email
    private String contactEmail;

    /**
     * 管理单位
     */
    @ApiModelProperty("管理单位")
    @Length(max = ParkingConstant.PARKING_INFO_PARKING_MANAGERUNIT_MAX_LENGTH, message = ParkingConstant.PARKING_INFO_PARKING_MANAGERUNIT_LENGTH_RANGE)
    private String managerUnit;

    /**
     * 所有人单位
     */
    @ApiModelProperty("所有人单位")
    @Length(max = ParkingConstant.PARKING_INFO_PARKING_OWNERNAME_MAX_LENGTH, message = ParkingConstant.PARKING_INFO_PARKING_OWWNERNAME_LENGTH_RANGE)
    private String ownerName;

    /**
     * 运营人单位
     */
    @ApiModelProperty("运营人单位")
    @Length(max = ParkingConstant.PARKING_INFO_PARKING_OPERATORUNIT_MAX_LENGTH, message = ParkingConstant.PARKING_INFO_PARKING_OPERATORUNIT_LENGTH_RANGE)
    private String operatorUnit;

    /**
     * 收费单位
     */
    @ApiModelProperty("收费单位")
    @Length(max = ParkingConstant.PARKING_INFO_PARKING_CHARGERUNIT_MAX_LENGTH, message = ParkingConstant.PARKING_INFO_PARKING_CHARGERUNIT_LENGTH_RANGE)
    private String chargerUnit;

    /**
     * LOGO
     */
    @ApiModelProperty("logo")
    @Length(max = ParkingConstant.PARKING_INFO_PARKING_LOGO_MAX_LENGTH, message = ParkingConstant.PARKING_INFO_PARKING_LOGO_LENGTH_RANGE)
    private String logo;

    /**
     * 停车场内景图片
     */
    @ApiModelProperty("停车场内景图片")
    @FluentValidate({ParkingImagesValidator.class})
    private List<String> imageUrls;

}
