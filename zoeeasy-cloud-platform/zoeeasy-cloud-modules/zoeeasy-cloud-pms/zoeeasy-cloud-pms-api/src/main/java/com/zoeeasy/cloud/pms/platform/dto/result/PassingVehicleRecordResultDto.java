package com.zoeeasy.cloud.pms.platform.dto.result;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author AkeemSuper
 * @Description: 平台过车记录查询结果视图
 * @Date: 2018/2/28 0028
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "OldPassingVehicleRecordResultDto", description = "平台过车记录查询结果视图")
public class PassingVehicleRecordResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "tenantId")
    private Long tenantId;

    /**
     * 平台过车流水号
     */
    @ApiModelProperty("平台过车流水号")
    private String passingNo;

    /**
     * 停车场Id
     */
    @ApiModelProperty("停车场Id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parkingId;

    /**
     * 出入口ID
     */
    @ApiModelProperty("出入口ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long gateId;

    /**
     * 泊位Id
     */
    @ApiModelProperty("泊位Id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parkingLotId;

    /**
     * 第三方平台过车Id
     */
    @ApiModelProperty("海康平台过车Id")
    private String thirdPassingId;

    /**
     * 阿里过车Id
     */
    @ApiModelProperty("阿里过车Id")
    private String aliPassingId;

    /**
     * 停车卡号
     */
    @ApiModelProperty("停车卡号")
    private String cardNumber;

    /**
     * 停车码
     */
    @ApiModelProperty("停车码")
    private String codeNumber;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 车牌号是否存在
     */
    @ApiModelProperty("车牌号是否存在")
    private Boolean plateNoExist;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty("车辆类型")
    private Integer carType;

    /**
     * 车牌置信度
     */
    @ApiModelProperty("车牌置信度")
    private Integer plateNumberConfidence;

    /**
     * 过车类型
     */
    @ApiModelProperty("过车类型")
    private Integer passingType;

    /**
     * 置信度
     */
    @ApiModelProperty("置信度")
    private Integer confidence;

    /**
     * 停车类型
     */
    @ApiModelProperty("停车类型")
    private Integer parkingType;

    /**
     * 是否异常放行：0 正常；1异常放行
     */
    @ApiModelProperty("是否异常放行")
    private Boolean abnormal;

    /**
     * 过车时间
     */
    @ApiModelProperty("过车时间")
    private Date passTime;

    /**
     * 入车时间
     */
    @ApiModelProperty("入车时间")
    private Date entryTime;

    /**
     * 出车时间
     */
    @ApiModelProperty("出车时间")
    private Date exitTime;

    /**
     * 过车数据来源
     */
    @ApiModelProperty("过车数据来源")
    private Integer dataSource;

    /**
     * 数据类型
     */
    @ApiModelProperty("数据类型")
    private Integer dataType;

    /**
     * 过车图片是否上传
     */
    @ApiModelProperty("过车图片是否上传")
    private Boolean photoUploaded;

    /**
     * 图片数量
     */
    @ApiModelProperty("图片数量")
    private Integer photoCount;

    /**
     * 图片上传时间
     */
    @ApiModelProperty("图片上传时间")
    private Date uploadedDate;

    /**
     * 校对状态
     */
    @ApiModelProperty("校对状态")
    private Boolean proofStatus;

    /**
     * 校对用户
     */
    @ApiModelProperty("校对用户")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long proofUserId;

    /**
     * 校对时间
     */
    @ApiModelProperty("校对时间")
    private Date proofDate;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

}
