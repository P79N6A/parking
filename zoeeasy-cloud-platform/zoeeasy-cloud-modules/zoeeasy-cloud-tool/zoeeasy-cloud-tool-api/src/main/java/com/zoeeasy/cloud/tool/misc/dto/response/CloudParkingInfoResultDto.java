package com.zoeeasy.cloud.tool.misc.dto.response;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 迁移停车场数据用
 *
 * @author wangfeng
 * @date 2018/11/15 18:02
 **/
@Data
public class CloudParkingInfoResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;
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
    /**
     * 运营状态，0-可用，1-不可用
     */
    @ApiModelProperty("运营状态，0-可用，1-不可用")
    private Integer status;

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
    private List<String> imageUrls;
}
