package com.zhuyitech.parking.tool.dto.result.volation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 车辆违章条目视图
 *
 * @author walkman
 * @Date: 2018/4/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "VehicleViolationItemResultDto", description = "车辆违章条目视图")
public class VehicleViolationItemResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 违章编码
     */
    @ApiModelProperty(value = "违章编码")
    private String code;

    /**
     * 违章时间
     */
    @ApiModelProperty(value = "违章时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date violationTime;

    /**
     * 罚款金额
     */
    @ApiModelProperty(value = "罚款金额")
    private BigDecimal fine;

    /**
     * 违章地址
     */
    @ApiModelProperty(value = "违章地址")
    private String address;

    /**
     * 违章处理原因
     */
    @ApiModelProperty(value = "违章处理原因")
    private String reason;

    /**
     * 违章扣分
     */
    @ApiModelProperty(value = "违章扣分")
    private Integer point;

    /**
     * 省份
     */
    @ApiModelProperty(value = "省份")
    private String province;

    /**
     * 违章发生城市
     */
    @ApiModelProperty(value = "违章发生城市")
    private String violationCity;

    /**
     * 城市
     */
    @ApiModelProperty(value = "城市")
    private String city;

    /**
     * 服务费（与代缴有关，用户可忽略本字段）
     */
    @ApiModelProperty(value = "服务费")
    private BigDecimal serviceFee;

    /**
     * 代扣分费用（与代缴有关，用户可忽略本字段）
     */
    @ApiModelProperty(value = "代扣分费用")
    private BigDecimal markFee;

    /**
     * 能否勾选办理：0不可勾选, 1可勾选。（与代缴有关，用户可忽略本字段）
     */
    @ApiModelProperty(value = "能否勾选办理：0不可勾选, 1可勾选")
    private Integer canSelect;

    /**
     * 违章处理状态：1：未处理，2：处理中，3：已处理，4：不支持（与代缴有关，用户可忽略本字段）
     */
    @ApiModelProperty(value = "违章处理状态：1：未处理，2：处理中，3：已处理，4：不支持")
    private Integer processStatus;

    /**
     * 违章官方编码
     */
    @ApiModelProperty(value = "违章官方编码")
    private String violationNum;

    /**
     * 执法机构
     */
    @ApiModelProperty(value = "执法机构")
    private String organName;

    /**
     * 违章缴费状态 不返回表示无法获取该信息，1-未缴费 2-已缴
     */
    @ApiModelProperty(value = "违章缴费状态 1-未缴费 2-已缴")
    private Integer paymentStatus;

}
