package com.zhuyitech.parking.ucc.dto.result.assetlog;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 用户收支明细详情
 *
 * @author zwq
 * @date 2018-06-13
 */
@ApiModel(value = "AssetLogDetailResultDto", description = "用户收支明细详情")
@Data
@EqualsAndHashCode(callSuper = true)
public class AssetLogDetailResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date creationTime;

    /**
     * 业务类型
     */
    @ApiModelProperty(value = "业务类型")
    private Integer bizType;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 变动额
     */
    @ApiModelProperty(value = "变动额")
    private String amount;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private Integer payWay;

    /**
     * 业务流水号
     */
    @ApiModelProperty(value = "业务流水号")
    private String bizNo;

}
