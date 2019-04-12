package com.zhuyitech.parking.tool.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.scapegoat.infrastructure.validate.annotaion.validation.IdentityNo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 身份证校验请求参数
 *
 * @author walkman
 * @date 2018-01-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "IdentityCardVerifyRequestDto", description = "身份证校验请求参数")
public class IdentityCardVerifyRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = true)
    @NotBlank(message = "姓名不能为空")
    private String realName;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号", required = true)
    @NotBlank(message = "身份证号不能为空")
    @IdentityNo
    private String cardNo;

}
