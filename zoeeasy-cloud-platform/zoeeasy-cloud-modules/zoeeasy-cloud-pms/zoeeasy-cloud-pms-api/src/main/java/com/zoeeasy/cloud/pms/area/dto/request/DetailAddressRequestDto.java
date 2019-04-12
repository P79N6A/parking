package com.zoeeasy.cloud.pms.area.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 地址详情请求参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "DetailAddressRequestDto", description = "地址详情请求参数")
public class DetailAddressRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("省编码")
    private String provinceCode;

    @ApiModelProperty("市编码")
    private String cityCode;

    @ApiModelProperty("区县编码")
    private String countyCode;

    @ApiModelProperty("address")
    private String address;

    @ApiModelProperty("租户id")
    private Long tenantId;

}
