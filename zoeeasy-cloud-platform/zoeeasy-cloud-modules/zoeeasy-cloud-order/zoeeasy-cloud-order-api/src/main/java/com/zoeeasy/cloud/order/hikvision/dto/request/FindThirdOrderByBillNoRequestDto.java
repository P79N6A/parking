package com.zoeeasy.cloud.order.hikvision.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Description:通过BillNo查询
 * @Autor: zwq
 * @Date: 2018/2/2 0002
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FindThirdOrderByBillNoRequestDto")
public class FindThirdOrderByBillNoRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * billNo
     */
    @ApiModelProperty(value = "billNo", required = true)
    @NotEmpty(message = "billNo不能为空")
    private String billNo;

}
