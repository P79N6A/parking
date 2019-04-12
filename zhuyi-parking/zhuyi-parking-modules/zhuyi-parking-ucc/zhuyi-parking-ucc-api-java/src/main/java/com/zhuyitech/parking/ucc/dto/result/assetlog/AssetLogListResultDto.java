package com.zhuyitech.parking.ucc.dto.result.assetlog;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 用户收支明细列表
 *
 * @author zwq
 * @date 2018-06-13
 */
@ApiModel(value = "AssetLogListResultDto", description = "用户收支明细列表")
@Data
@EqualsAndHashCode(callSuper = true)
public class AssetLogListResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

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
     * 变动方向
     */
    @ApiModelProperty(value = "变动方向 (1.+ 2.-)")
    private Integer direction;

    /**
     * 变动额
     */
    @ApiModelProperty(value = "变动额")
    private BigDecimal amount;

}
