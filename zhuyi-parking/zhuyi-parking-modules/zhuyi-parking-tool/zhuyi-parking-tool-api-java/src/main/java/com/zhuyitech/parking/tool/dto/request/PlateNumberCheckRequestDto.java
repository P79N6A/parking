package com.zhuyitech.parking.tool.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 车牌验证请求参数
 *
 * @Date: 2018/1/10
 * @author: yuzhicheng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PlateNumberCheckRequestDto", description = "车牌验证请求参数")
public class PlateNumberCheckRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌前缀
     */
    @ApiModelProperty(value = "车牌前缀", required = true)
    @NotBlank(message = "车牌前缀不能为空")
    private String platePrefix;

    /**
     * 车牌剩余部分
     */
    @ApiModelProperty(value = "车牌剩余部分", required = true)
    @NotBlank(message = "车牌剩余部分不能为空")
    private String plateNumber;

    /**
     * 车牌类型
     */
    @ApiModelProperty(value = "车牌类型", required = true)
    @NotBlank(message = "车牌类型不能为空")
    private String plateType;

    /**
     * 车架号
     */
    @ApiModelProperty(value = "车架号")
    private String vehicleNumber;

    /**
     * 发动机号
     */
    @ApiModelProperty(value = "发动机号", required = true)
    @NotBlank(message = "发动机号不能为空")
    private String engineNumber;

}
