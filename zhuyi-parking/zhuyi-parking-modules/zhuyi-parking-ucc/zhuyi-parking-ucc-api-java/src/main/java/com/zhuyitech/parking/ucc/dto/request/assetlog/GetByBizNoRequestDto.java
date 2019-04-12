package com.zhuyitech.parking.ucc.dto.request.assetlog;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 根据订单查询
 *
 * @author walkman
 * @date 2018-04-01
 */
@ApiModel(value = "GetByBizNoRequestDto", description = "根据订单查询")
@Data
@EqualsAndHashCode(callSuper = true)
public class GetByBizNoRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * bizNo
     */
    @ApiModelProperty(value = "bizNo")
    private String bizNo;

}
