package com.zhuyitech.parking.client.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/12/6 0006
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ClientMockRequestDto", description = "模拟客户端过车数据请求参数")
public class ClientMockRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 过车记录编号
     */
    @ApiModelProperty(value = "过车记录编号", required = true)
    @NotNull(message = "过车记录编号不能为空")
    private String passingCode;

    /**
     * 云平台停车场Code
     */
    @ApiModelProperty(value = "云平台停车场Code", required = true)
    @NotNull(message = "云平台停车场Code不能为空")
    private String cloudParkingCode;

    /**
     * 云平台出入口编号
     */
    @ApiModelProperty(value = "云平台出入口编号")
    private String cloudGateCode;

    /**
     * 云平台车道编号
     */
    @ApiModelProperty(value = "云平台车道编号")
    private String cloudLaneCode;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    @NotNull(message = "车牌号不能为空")
    private String plateNumber;

    /**
     * 泊位号
     */
    @ApiModelProperty(value = "泊位号")
    private String parkingLotNumber;

    /**
     * 泊位编号
     */
    @ApiModelProperty("泊位编号")
    private String parkingLotCode;

    /**
     * 过车方向
     */
    @ApiModelProperty(value = "过车方向", required = true)
    @NotNull(message = "过车方向不能为空")
    private Integer direction;

    /**
     * 过车时间
     */
    @ApiModelProperty(value = "过车时间", required = true)
    @NotNull(message = "过车时间不能为空")
    @DateTimeFormat(pattern = Const.FORMAT_DATETIME)
    private Date passTime;

    /**
     * 全景图像
     */
    @ApiModelProperty(value = "全景图像,多个图像,隔开")
    private String fullImage;

    /**
     * 车牌图像
     */
    @ApiModelProperty("车牌图像 ,隔开")
    private String plateImage;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色", required = true)
    @NotNull(message = "车牌颜色不能为空")
    private Integer plateColor;

    /**
     * 整牌可信度
     */
    @ApiModelProperty(value = "整牌可信度")
    private Integer confidence;

    /**
     * 识别耗时
     */
    @ApiModelProperty("识别耗时")
    private Long recTime;

    /**
     * 车辆停车类型
     */
    @ApiModelProperty(value = "车辆停车类型", required = true)
    @NotNull(message = "整牌可信度不能为空")
    private Integer carParkType;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;
}
