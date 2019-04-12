package com.zhuyitech.parking.data.dto.result.park;

import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * 停车场视图模型
 *
 * @author walkman
 */
@ApiModel(value = "ParkingResultDto", description = "停车场视图模型")
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAliParkId() {
        return aliParkId;
    }

    public void setAliParkId(String aliParkId) {
        this.aliParkId = aliParkId;
    }

    public String getHikParkId() {
        return hikParkId;
    }

    public void setHikParkId(String hikParkId) {
        this.hikParkId = hikParkId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLotType() {
        return lotType;
    }

    public void setLotType(String lotType) {
        this.lotType = lotType;
    }

    public Boolean getChargeFee() {
        return chargeFee;
    }

    public void setChargeFee(Boolean chargeFee) {
        this.chargeFee = chargeFee;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Integer getOutTime() {
        return outTime;
    }

    public void setOutTime(Integer outTime) {
        this.outTime = outTime;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getOpenStartTime() {
        return openStartTime;
    }

    public void setOpenStartTime(String openStartTime) {
        this.openStartTime = openStartTime;
    }

    public String getOpenEndTime() {
        return openEndTime;
    }

    public void setOpenEndTime(String openEndTime) {
        this.openEndTime = openEndTime;
    }

    public String getChargeDescription() {
        return chargeDescription;
    }

    public void setChargeDescription(String chargeDescription) {
        this.chargeDescription = chargeDescription;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactQQ() {
        return contactQQ;
    }

    public void setContactQQ(String contactQQ) {
        this.contactQQ = contactQQ;
    }

    public String getContactWeixin() {
        return contactWeixin;
    }

    public void setContactWeixin(String contactWeixin) {
        this.contactWeixin = contactWeixin;
    }

    public String getContactAlipay() {
        return contactAlipay;
    }

    public void setContactAlipay(String contactAlipay) {
        this.contactAlipay = contactAlipay;
    }

    public Integer getLotTotal() {
        return lotTotal;
    }

    public void setLotTotal(Integer lotTotal) {
        this.lotTotal = lotTotal;
    }

    public Integer getLotFixed() {
        return lotFixed;
    }

    public void setLotFixed(Integer lotFixed) {
        this.lotFixed = lotFixed;
    }

    public Integer getLotAvailable() {
        return lotAvailable;
    }

    public void setLotAvailable(Integer lotAvailable) {
        this.lotAvailable = lotAvailable;
    }

    public String getManagerUnit() {
        return managerUnit;
    }

    public void setManagerUnit(String managerUnit) {
        this.managerUnit = managerUnit;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOperatorUnit() {
        return operatorUnit;
    }

    public void setOperatorUnit(String operatorUnit) {
        this.operatorUnit = operatorUnit;
    }

    public String getChargerUnit() {
        return chargerUnit;
    }

    public void setChargerUnit(String chargerUnit) {
        this.chargerUnit = chargerUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getChargeMode() {
        return chargeMode;
    }

    public void setChargeMode(Integer chargeMode) {
        this.chargeMode = chargeMode;
    }

    public Boolean getSupportAppointment() {
        return supportAppointment;
    }

    public void setSupportAppointment(Boolean supportAppointment) {
        this.supportAppointment = supportAppointment;
    }

    public Integer getLotAppointmentTotal() {
        return lotAppointmentTotal;
    }

    public void setLotAppointmentTotal(Integer lotAppointmentTotal) {
        this.lotAppointmentTotal = lotAppointmentTotal;
    }

    public Integer getLotAppointmentAvailable() {
        return lotAppointmentAvailable;
    }

    public void setLotAppointmentAvailable(Integer lotAppointmentAvailable) {
        this.lotAppointmentAvailable = lotAppointmentAvailable;
    }

    public String getChargeRule() {
        return chargeRule;
    }

    public void setChargeRule(String chargeRule) {
        this.chargeRule = chargeRule;
    }

    public String getAppointmentRule() {
        return appointmentRule;
    }

    public void setAppointmentRule(String appointmentRule) {
        this.appointmentRule = appointmentRule;
    }

    public List<ParkingImageViewResultDto> getImages() {
        return images;
    }

    public void setImages(List<ParkingImageViewResultDto> images) {
        this.images = images;
    }
}
