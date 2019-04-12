package com.zhuyitech.parking.platform.utils;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 迁移停车场数据用
 *
 * @author wangfeng
 * @date 2018/11/15 18:02
 **/
public class ParkingAddRequestDto {
    /**
     * address : string
     * appointmentRule : string
     * areaCode : string
     * chargeFee : 0
     * chargeMode : 0
     * chargeRule : string
     * chargerUnit : string
     * code : string
     * contactAlipay : string
     * contactEmail : string
     * contactName : string
     * contactPhone : string
     * contactQQ : string
     * contactTel : string
     * contactWeixin : string
     * description : string
     * freeTime : 0
     * fullName : string
     * imageUrls : ["string"]
     * latitude : 0
     * logo : string
     * longitude : 0
     * lotAppointmentAvailable : 0
     * lotAppointmentTotal : 0
     * lotAvailable : 0
     * lotFixed : 0
     * lotTotal : 0
     * lotType : string
     * managerUnit : string
     * openEndTime : string
     * openStartTime : string
     * operationState : 0
     * operatorUnit : string
     * outTime : 0
     * ownerName : string
     * payMode : string
     * payType : string
     * supportAppointment : 0
     * zipCode : string
     * accessKey : string
     * nonce : string
     * timestamp : 0
     * signature : string
     */

    private String address;
    private String appointmentRule;
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
    private int chargeFee;
    private int chargeMode;
    private String chargeRule;
    private String chargerUnit;
    private String code;
    private String contactAlipay;
    private String contactEmail;
    private String contactName;
    private String contactPhone;
    private String contactQQ;
    private String contactTel;
    private String contactWeixin;
    private String description;
    private int freeTime;
    private String fullName;
    private double latitude;
    private String logo;
    private double longitude;
    private int lotAppointmentAvailable;
    private int lotAppointmentTotal;
    private int lotAvailable;
    private int lotFixed;
    private int lotTotal;
    private String lotType;
    private String managerUnit;
    private String openEndTime;
    private String openStartTime;
    private int operationState;
    private String operatorUnit;
    private int outTime;
    private String ownerName;
    private String payMode;
    private String payType;
    private int supportAppointment;
    private String zipCode;
    private String accessKey;
    private String nonce;
    private int timestamp;
    private String signature;
    private List<String> imageUrls;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAppointmentRule() {
        return appointmentRule;
    }

    public void setAppointmentRule(String appointmentRule) {
        this.appointmentRule = appointmentRule;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public int getChargeFee() {
        return chargeFee;
    }

    public void setChargeFee(int chargeFee) {
        this.chargeFee = chargeFee;
    }

    public int getChargeMode() {
        return chargeMode;
    }

    public void setChargeMode(int chargeMode) {
        this.chargeMode = chargeMode;
    }

    public String getChargeRule() {
        return chargeRule;
    }

    public void setChargeRule(String chargeRule) {
        this.chargeRule = chargeRule;
    }

    public String getChargerUnit() {
        return chargerUnit;
    }

    public void setChargerUnit(String chargerUnit) {
        this.chargerUnit = chargerUnit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContactAlipay() {
        return contactAlipay;
    }

    public void setContactAlipay(String contactAlipay) {
        this.contactAlipay = contactAlipay;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactQQ() {
        return contactQQ;
    }

    public void setContactQQ(String contactQQ) {
        this.contactQQ = contactQQ;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getContactWeixin() {
        return contactWeixin;
    }

    public void setContactWeixin(String contactWeixin) {
        this.contactWeixin = contactWeixin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(int freeTime) {
        this.freeTime = freeTime;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


    public int getLotAppointmentAvailable() {
        return lotAppointmentAvailable;
    }

    public void setLotAppointmentAvailable(int lotAppointmentAvailable) {
        this.lotAppointmentAvailable = lotAppointmentAvailable;
    }

    public int getLotAppointmentTotal() {
        return lotAppointmentTotal;
    }

    public void setLotAppointmentTotal(int lotAppointmentTotal) {
        this.lotAppointmentTotal = lotAppointmentTotal;
    }

    public int getLotAvailable() {
        return lotAvailable;
    }

    public void setLotAvailable(int lotAvailable) {
        this.lotAvailable = lotAvailable;
    }

    public int getLotFixed() {
        return lotFixed;
    }

    public void setLotFixed(int lotFixed) {
        this.lotFixed = lotFixed;
    }

    public int getLotTotal() {
        return lotTotal;
    }

    public void setLotTotal(int lotTotal) {
        this.lotTotal = lotTotal;
    }

    public String getLotType() {
        return lotType;
    }

    public void setLotType(String lotType) {
        this.lotType = lotType;
    }

    public String getManagerUnit() {
        return managerUnit;
    }

    public void setManagerUnit(String managerUnit) {
        this.managerUnit = managerUnit;
    }

    public String getOpenEndTime() {
        return openEndTime;
    }

    public void setOpenEndTime(String openEndTime) {
        this.openEndTime = openEndTime;
    }

    public String getOpenStartTime() {
        return openStartTime;
    }

    public void setOpenStartTime(String openStartTime) {
        this.openStartTime = openStartTime;
    }

    public int getOperationState() {
        return operationState;
    }

    public void setOperationState(int operationState) {
        this.operationState = operationState;
    }

    public String getOperatorUnit() {
        return operatorUnit;
    }

    public void setOperatorUnit(String operatorUnit) {
        this.operatorUnit = operatorUnit;
    }

    public int getOutTime() {
        return outTime;
    }

    public void setOutTime(int outTime) {
        this.outTime = outTime;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    public int getSupportAppointment() {
        return supportAppointment;
    }

    public void setSupportAppointment(int supportAppointment) {
        this.supportAppointment = supportAppointment;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
