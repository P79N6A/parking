package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 添加停车场请求参数
 *
 * @author wangfeng
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "NonTenantParkingAddRequestDto", description = "添加停车场请求参数")
public class NonTenantParkingAddRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty(value = "code", required = true)
    private String code;

    /**
     * countyCode
     */
    @ApiModelProperty("countyCode")
    private String countyCode;
    /**
     * cityCode
     */
    @ApiModelProperty("cityCode")
    private String cityCode;
    
    /**
     * provinceCode
     */
    @ApiModelProperty("provinceCode")
    private String provinceCode;

    /**
     * 停车场全称
     */
    @ApiModelProperty(value = "fullName", required = true)
    private String fullName;

    /**
     * 运营状态，0-可用，1-不可用
     */
    @ApiModelProperty("运营状态，0-可用，1-不可用")
    private Integer operationState;

    /**
     * 停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场
     */
    @ApiModelProperty("停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场")
    private String lotType;

    /**
     * 用户支付未离场的超时时间(以分钟为单位)
     */
    @ApiModelProperty("用户支付未离场的超时时间(以分钟为单位)")
    private Integer outTime;

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
     * 纬度
     */
    @ApiModelProperty("纬度")
    @NotNull(message = ParkingConstant.PARKING_INFO_PARKING_LATITUDE_NOT_NULL)
    private Double latitude;

    /**
     * 经度
     */
    @ApiModelProperty("经度")
    @NotNull(message = ParkingConstant.PARKING_INFO_PARKING_LONGITUDE_NOT_NULL)
    private Double longitude;

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
    private String openStartTime;

    /**
     * 结束营业时间,格式HH:mm:ss
     */
    @ApiModelProperty("结束营业时间,格式HH:mm:ss")
    private String openEndTime;

    /**
     * 是否收费 0:不收费 1 收费
     */
    @ApiModelProperty("是否收费 0:不收费 1 收费")
    private Integer chargeFee;

    /**
     * 缴费方式（1为停车卡缴费，2为物料缴费，3为中央缴费机）
     */
    @ApiModelProperty("收费方式（1为停车卡缴费，2为物料缴费，3为中央缴费机）")
    private String payMode;

    /**
     * 收费模式 1: 离场后缴费 2: 缴费后离场
     */
    @ApiModelProperty("缴费模式 1: 离场后缴费 2: 缴费后离场")
    private Integer chargeMode;

    /**
     * 支付方式（1为支付宝在线缴费，2为支付宝代扣缴费，3当面付)，如支持多种方式以'',''进行间隔
     */
    @ApiModelProperty("支付方式（1为支付宝在线缴费，2为支付宝代扣缴费，3当面付)，如支持多种方式以'',''进行间隔")
    private String payType;

    /**
     * 免费停车时长：单位分钟
     */
    @ApiModelProperty("免费停车时长：单位分钟")
    private Integer freeTime;

    /**
     * 收费规则详情
     */
    @ApiModelProperty("收费规则详情")
    private String chargeDescription;

    /**
     * 是否支持预约
     */
    @ApiModelProperty("是否支持预约")
    private Integer supportAppointment;

    /**
     * 支持预约车位总数
     */
    @ApiModelProperty("支持预约车位总数")
    private Integer lotAppointmentTotal;

    /**
     * 可预订剩余车位数
     */
    @ApiModelProperty("可预订剩余车位数")
    private Integer lotAppointmentAvailable;

    /**
     * 预约规则的简短描述
     */
    @ApiModelProperty("预约规则的简短描述")
    private String appointmentRule;

    /**
     * 联系人
     */
    @ApiModelProperty("联系人")
    private String contactName;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String contactTel;

    /**
     * 联系手机
     */
    @ApiModelProperty("联系手机")
    private String contactPhone;

    /**
     * 联系人微信
     */
    @ApiModelProperty("联系人微信")
    private String contactWeixin;

    /**
     * 联系人QQ
     */
    @ApiModelProperty("联系人QQ")
    private String contactQQ;

    /**
     * 联系人支付宝
     */
    @ApiModelProperty("联系人支付宝")
    private String contactAlipay;

    /**
     *
     */
    @ApiModelProperty("联系人邮箱")
    private String contactEmail;

    /**
     * 管理单位
     */
    @ApiModelProperty("管理单位")
    private String managerUnit;

    /**
     * 所有人单位
     */
    @ApiModelProperty("所有人单位")
    private String ownerName;

    /**
     * 运营人单位
     */
    @ApiModelProperty("运营人单位")
    private String operatorUnit;

    /**
     * 收费单位
     */
    @ApiModelProperty("收费单位")
    private String chargerUnit;

    /**
     * LOGO
     */
    @ApiModelProperty("logo")
    private String logo;

    /**
     * 停车场内景图片
     */
    @ApiModelProperty("停车场内景图片")
    private List<String> imageUrls;

}
