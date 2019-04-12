package com.zoeeasy.cloud.pms.specialvehicle.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;import com.scapegoat.infrastructure.jackson.convert.serializer.ToBigDecimalYuanSerializer;


import java.util.Date;
import java.util.List;

/**
 * Created by song on 2018/10/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PacketReceiptResultDto", description = "包期充值收费凭证视图模型")
public class PacketReceiptResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌号码
     */
    @ApiModelProperty("车牌号码")
    private String plateNumber;

    /**
     * 车主姓名
     */
    @ApiModelProperty("车主姓名")
    private String ownerName;

    /**
     * 日期
     */
    @ApiModelProperty("日期")
    @JsonFormat(pattern = Const.FORMAT_DATETIME)
    private Date time;

    /**
     * 总金额(元)
     */
    @ApiModelProperty("总金额")
    @JsonSerialize(using = ToBigDecimalYuanSerializer.class)
    private Integer amount;

    /**
     * 收费项目明细
     */
    @ApiModelProperty("收费项目明细")
    private List<ParkingPacketRuleResultDto> parkingPacketRules;

}
