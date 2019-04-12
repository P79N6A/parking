package com.zoeeasy.cloud.order.appoint.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 预约订单取消判断
 *
 * @author zwq
 * @date 2018-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderCancelCheckResultDto", description = "预约订单取消判断")
public class AppointOrderCancelCheckResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 是否可取消
     */
    @ApiModelProperty("是否可取消")
    private Boolean cancelCheck;
}
