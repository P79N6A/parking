package com.zhuyitech.parking.pms.dto.result.park;

import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 停车场视图模型
 *
 * @author walkman
 */
@ApiModel(value = "ParkingResultDto", description = "停车场视图模型")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private String code;

    /**
     * 简称
     */
    @ApiModelProperty(value = "简称")
    private String name;

    /**
     * 全称
     */
    @ApiModelProperty(value = "全称")
    private String fullName;

    /**
     * 支付宝平台停车场ID
     */
    @ApiModelProperty(value = "支付宝平台停车场ID")
    private String aliParkId;

    /**
     * 海康平台停车场ID
     */
    @ApiModelProperty(value = "海康平台停车场ID")
    private String hikParkId;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 停车场类型((1为地面，2为地下，3为路边)（多个类型，中间用,隔开)
     */
    @ApiModelProperty(value = "停车场类型((1为地面，2为地下，3为路边)（多个类型，中间用,隔开)")
    private String type;

    /**
     * 停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场
     */
    @ApiModelProperty(value = "停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场")
    private String lotType;

    /**
     * 是否收费
     */
    @ApiModelProperty(value = "是否收费,true 收费 false 免费")
    private Boolean chargeFee;

    /**
     * 收费模式(1: 离场后缴费 2: 缴费后离场)
     */
    @ApiModelProperty(value = "收费模式", notes = "收费模式(1: 离场后缴费 2: 缴费后离场)")
    private Integer chargeMode;

    /**
     * 收费方式（1为停车卡缴费，2为物料缴费，3为中央缴费机）
     */
    @ApiModelProperty(value = "收费方式（1为停车卡缴费，2为物料缴费，3为中央缴费机）")
    private String payMode;

    /**
     * 支付方式（1为支付宝在线缴费，2为支付宝代扣缴费，3当面付)，如支持多种方式以','进行间隔
     */
    @ApiModelProperty(value = "支付方式（1为支付宝在线缴费，2为支付宝代扣缴费，3当面付)，如支持多种方式以','进行间隔")
    private String payType;

    /**
     * 是否可预约(0不可预约 1可预约)
     */
    @ApiModelProperty(value = "是否可预约", notes = "0不可预约 1可预约")
    private Boolean supportAppointment;

    /**
     * 预约规则
     */
    @ApiModelProperty(value = "预约规则")
    private String appointmentRule;

    /**
     * 用户支付未离场的超时时间(以分钟为单位)
     */
    @ApiModelProperty(value = "用户支付未离场的超时时间(以分钟为单位)")
    private Integer outTime;

    /**
     * LOGO
     */
    @ApiModelProperty(value = "LOGO")
    private String logo;

    /**
     * 开始营业时间，格式HH:mm:ss
     */
    @ApiModelProperty(value = "开始营业时间")
    private String openStartTime;

    /**
     * 结束营业时间，格式HH:mm:ss
     */
    @ApiModelProperty(value = "结束营业时间")
    private String openEndTime;

    /**
     * 收费规则
     */
    @ApiModelProperty(value = "收费规则")
    private String chargeRule;

    /**
     * 收费描述信息
     */
    @ApiModelProperty(value = "收费描述信息")
    private String chargeDescription;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private Double longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private Double latitude;

    /**
     * 省编码
     */
    @ApiModelProperty(value = "省编码")
    private String provinceCode;

    /**
     * 市编码
     */
    @ApiModelProperty(value = "市编码")
    private String cityCode;

    /**
     * 区县编码
     */
    @ApiModelProperty(value = "区县编码")
    private String countyCode;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     * 邮编
     */
    @ApiModelProperty(value = "邮编")
    private String zipCode;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    private String contactName;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String contactTel;

    /**
     * 联系手机
     */
    @ApiModelProperty(value = "联系手机")
    private String contactPhone;

    /**
     * 联系人邮箱
     */
    @ApiModelProperty(value = "联系人邮箱")
    private String contactEmail;

    /**
     * 联系人QQ
     */
    @ApiModelProperty(value = "联系人QQ")
    private String contactQQ;

    /**
     * 联系人微信
     */
    @ApiModelProperty(value = "联系人微信")
    private String contactWeixin;

    /**
     * 联系人支付宝
     */
    @ApiModelProperty(value = "联系人支付宝")
    private String contactAlipay;

    /**
     * 车位总数
     */
    @ApiModelProperty(value = "车位总数")
    private Integer lotTotal;

    /**
     * 固定车位总数
     */
    @ApiModelProperty(value = "固定车位总数")
    private Integer lotFixed;

    /**
     * 可用车位数
     */
    @ApiModelProperty(value = "可用车位数")
    private Integer lotAvailable;

    /**
     * 总共可预约车位
     */
    @ApiModelProperty(value = "总共可预约车位")
    private Integer lotAppointmentTotal;

    /**
     * 剩余可预约车位
     */
    @ApiModelProperty(value = "剩余可预约车位")
    private Integer lotAppointmentAvailable;

    /**
     * 管理单位
     */
    @ApiModelProperty(value = "管理单位")
    private String managerUnit;

    /**
     * 所有人单位
     */
    @ApiModelProperty(value = "所有人单位")
    private String ownerName;

    /**
     * 运营人单位
     */
    @ApiModelProperty(value = "运营人单位")
    private String operatorUnit;

    /**
     * 收费单位
     */
    @ApiModelProperty(value = "收费单位")
    private String chargerUnit;

    /**
     * 描述信息
     */
    @ApiModelProperty(value = "描述信息")
    private String description;

    /**
     * 停车图像URL列表
     */
    @ApiModelProperty(value = "停车图像URL列表")
    private List<ParkingImageViewResultDto> images;

}
