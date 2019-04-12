package com.zhuyitech.parking.pms.dto.request.license;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


/**
 * 车牌前缀首字母请求参数表
 *
 * @author zwq
 */
@ApiModel(value = "LicensePrefixFirstGetRequestDto", description = "车牌前缀首字母请求参数表")
@Data
@EqualsAndHashCode(callSuper = false)
public class LicensePrefixFirstGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 父ID
     */
    @ApiModelProperty(value = "父ID", required = true)
    @NotNull(message = "父ID不能为空")
    private Long parentId;

}
