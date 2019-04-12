package com.zhuyitech.parking.ucc.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户是否添加驾驶证结果
 *
 * @author walkamn
 * @Date: 2018/1/15
 */
@ApiModel(value = "UserDriverLicenseExistResultDto", description = "用户是否添加驾驶证结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDriverLicenseExistResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否存在")
    private Boolean exist;

}