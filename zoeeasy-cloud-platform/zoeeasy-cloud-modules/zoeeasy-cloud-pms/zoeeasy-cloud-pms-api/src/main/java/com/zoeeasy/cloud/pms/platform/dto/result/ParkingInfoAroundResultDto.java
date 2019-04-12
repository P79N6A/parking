package com.zoeeasy.cloud.pms.platform.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 附近停车场视图模型
 *
 * @author walkman
 */
@ApiModel(value = "ParkingInfoAroundResultDto", description = "附近停车场视图模型")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingInfoAroundResultDto extends EntityDto<Long> {

    @ApiModelProperty(value = "租户Id")
    private Long tenantId;

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
     * 停车场类型
     */
    @ApiModelProperty(value = "停车场类型(1为地面，2为地下，3为路边)")
    private String type;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "0 为现金支付 1为支付宝在线缴费，2为支付宝代扣缴费，3当面付")
    private String payType;

    /**
     * 是否收费
     */
    @ApiModelProperty(value = "是否收费")
    private Boolean chargeFee;

    /**
     * 是否限免
     */
    @ApiModelProperty(value = "是否限免(True 是 False 否 )")
    private Boolean nowFree;

    /**
     * 免费停车时长
     */
    @ApiModelProperty(value = "免费停车时长")
    private Integer freeTime;

    /**
     * 是否接入平台
     */
    @ApiModelProperty(value = "是否接入平台", notes = "是否接入平台")
    private Boolean platformSupport;

    /**
     * 是否可预约(false不可预约 true可预约)
     */
    @ApiModelProperty(value = "是否可预约", notes = "是否可预约(false不可预约 true可预约)")
    private Boolean supportAppointment;

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
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     * 车位总数
     */
    @ApiModelProperty(value = "车位总数")
    private Integer lotTotal;

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
     * 距离
     */
    @ApiModelProperty(value = "距离")
    private Double distance;

    /**
     * 距离单位(小于1km，单位m;d大于1km，单位km)
     */
    @ApiModelProperty(value = "距离单位")
    private String distanceUnit;

    /**
     * 空闲等级(1:绿色 2: 黄色 3: 红色)
     */
    @ApiModelProperty(value = "空闲等级(1:绿色 2: 黄色 3: 红色)")
    private Integer freeLevel;
}
