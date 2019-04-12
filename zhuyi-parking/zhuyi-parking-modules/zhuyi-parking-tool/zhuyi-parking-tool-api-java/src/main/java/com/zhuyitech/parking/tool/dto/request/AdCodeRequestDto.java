package com.zhuyitech.parking.tool.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author AkeemSuper
 * @date 2018/6/26 0026
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AdCodeRequestDto", description = "adCode请求参数")
public class AdCodeRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * adCode
     */
    @ApiModelProperty(value = "adCode", required = true)
    @NotBlank
    private String adCode;

}
