package com.zhuyitech.parking.ucc.dto.request.assetlog;

import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * 收支明细条件查询
 *
 * @author zwq
 * @date 2018-06-26
 */
@ApiModel(value = "AssetLogPageQueryRequestDto", description = "收支明细条件查询")
@Data
@EqualsAndHashCode(callSuper = true)
public class AssetLogPageQueryRequestDto extends SessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String bizNo;

    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额")
    private Double amount;

    /**
     * 订单类型
     */
    @ApiModelProperty(value = "订单类型")
    private Integer bizType;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String nickname;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号")
    private String phoneNumber;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = Const.FORMAT_DATETIME)
    private String startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = Const.FORMAT_DATETIME)
    private String endTime;

}
