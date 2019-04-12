package com.zoeeasy.cloud.pms.specialvehicle.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import com.scapegoat.infrastructure.jackson.convert.serializer.ToBigDecimalYuanSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 包期规则列表视图模型
 *
 * @date: 2018/10/15.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PacketRuleQueryPagedResultDto", description = "包期规则列表视图模型")
public class PacketRuleQueryPagedResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 停车场ID
     */
    @ApiModelProperty("parkingId")
    private Long parkingId;

    /**
     * 是否全部停车场
     */
    @ApiModelProperty("是否全部停车场")
    private String allParking;

    /**
     * 包期规则名称
     */
    @ApiModelProperty("包期规则名称")
    private String packetName;

    /**
     * 包期金额
     */
    @ApiModelProperty("包期金额")
    @JsonSerialize(using = ToBigDecimalYuanSerializer.class)
    private Integer price;

    /**
     * 包期类型
     */
    @ApiModelProperty("包期类型")
    private String packetType;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    private String plateColor;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;
}
