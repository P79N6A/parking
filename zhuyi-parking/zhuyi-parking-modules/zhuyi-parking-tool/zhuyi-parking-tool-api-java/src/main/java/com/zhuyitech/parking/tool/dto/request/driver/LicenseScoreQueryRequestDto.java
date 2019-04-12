package com.zhuyitech.parking.tool.dto.request.driver;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 驾驶证扣分查询请求参数
 *
 * @author walkman
 * @date 2018/4/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "LicenseScoreQueryRequestDto", description = "驾驶证扣分查询请求参数")
public class LicenseScoreQueryRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 驾驶证号
     */
    @ApiModelProperty(value = "驾驶证号")
    @NotBlank(message = "驾驶证号不能为空")
    private String licenseNumber;

    /**
     * 档案编号
     */
    @ApiModelProperty(value = "档案编号")
    @NotBlank(message = "档案编号不能为空")
    private String archiveNumber;

}
