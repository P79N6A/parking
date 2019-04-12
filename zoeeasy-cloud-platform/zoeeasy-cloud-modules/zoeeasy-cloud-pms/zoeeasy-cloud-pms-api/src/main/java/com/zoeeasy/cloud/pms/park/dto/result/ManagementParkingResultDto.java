package com.zoeeasy.cloud.pms.park.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 管理端停车场视图模型
 * Created by song on 2018/9/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ManagementParkingResultDto", description = "管理端停车场视图模型")
public class ManagementParkingResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 租户Id
     */
    @ApiModelProperty(value = "租户Id")
    private Long tenantId;

    /**
     * 编号
     */
    @ApiModelProperty(value = "code")
    private String code;

    /**
     * 支付宝平台停车场ID
     */
    @ApiModelProperty("aliParkId")
    private String aliParkId;

    /**
     * 海康平台停车场ID
     */
    @ApiModelProperty(value = "hikParkId")
    private String hikParkId;

    /**
     * 停车场全称
     */
    @ApiModelProperty(value = "fullName")
    private String fullName;

    /**
     * 停车场简称
     */
    @ApiModelProperty(value = "name")
    private String name;

    /**
     * 停车场等级
     */
    @ApiModelProperty(value = "停车场等级")
    private Integer level;

    /**
     * 停车场类型((1为地面，2为地下，3为路边)
     */
    @ApiModelProperty("停车场类型((1为地面，2为地下，3为路边)")
    private String type;

    /**
     * 停车场状态 0：下线，1：上线
     */
    @ApiModelProperty("停车场状态 0：下线，1：上线")
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
    private String latitude;

    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private String longitude;

    /**
     * 详细地址
     */
    @ApiModelProperty("详细地址")
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
    private Boolean chargeFee;

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
     * 收费描述信息
     */
    @ApiModelProperty("收费描述信息")
    private String chargeDescription;

    /**
     * 是否支持预约
     */
    @ApiModelProperty("是否支持预约")
    private Boolean supportAppointment;

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
    private List<String> images;

    /**
     * 商户区域省编码
     */
    @ApiModelProperty("商户区域省编码")
    private String provinceCode;
    /**
     * 商户区域市编码
     */
    @ApiModelProperty("商户区域市编码")
    private String cityCode;
    /**
     * 商户区域区县编码
     */
    @ApiModelProperty("商户区域区县编码")
    private String countyCode;

    /**
     * 全国区域省编码
     */
    @ApiModelProperty("全国区域省编码")
    private String regionProvinceCode;
    /**
     * 全国区域市编码
     */
    @ApiModelProperty("全国区域市编码")
    private String regionCityCode;

    /**
     * 全国区域区县编码
     */
    @ApiModelProperty("全国区域区县编码")
    private String regionCountyCode;

    @ApiModelProperty("detailAddress")
    private String detailAddress;

    /**
     * 申请类别: 1上线申请 2下线申请
     */
    @ApiModelProperty("申请类别: 1上线申请 2下线申请")
    private Integer applyType;

    /**
     * 申请备注
     */
    @ApiModelProperty("申请备注")
    private String applyRemark;

    /**
     * 审核状态
     */
    @ApiModelProperty("审核状态")
    private Integer auditStatus;
}
