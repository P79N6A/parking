package com.zhuyitech.parking.ucc.dto.result.assetlog;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 根据订单查询
 *
 * @author zwq
 * @date 2018-06-13
 */
@ApiModel(value = "AssetLogGetByBizNoResultDto", description = "根据订单查询")
@Data
@EqualsAndHashCode(callSuper = true)
public class AssetLogGetByBizNoResultDto extends BaseDto {

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
     * 变动额
     */
    @ApiModelProperty(value = "变动额")
    private Double amount;

}
