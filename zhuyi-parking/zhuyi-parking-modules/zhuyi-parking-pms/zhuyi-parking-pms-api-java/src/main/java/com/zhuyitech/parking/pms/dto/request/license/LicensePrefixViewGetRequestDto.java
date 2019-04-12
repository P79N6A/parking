package com.zhuyitech.parking.pms.dto.request.license;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 车牌前缀视图请求参数表
 *
 * @author zwq
 */
@ApiModel(value = "LicensePrefixViewGetRequestDto", description = "车牌前缀视图请求参数表")
@Data
@EqualsAndHashCode(callSuper = false)
public class LicensePrefixViewGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", notes = "名称")
    private String name;

}
