package com.zhuyitech.parking.ucc.dto.request.assetlog;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 钱包账单详情
 *
 * @author zwq
 * @date 2018-06-26
 */
@ApiModel(value = "WalletDetailRequestDto", description = "订单类型列表")
@Data
@EqualsAndHashCode(callSuper = true)
public class WalletDetailRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

}
