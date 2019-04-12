package com.zhuyitech.parking.tool.dto.request.inpark;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Inpark附近停车场列表URL请求参数
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Inpark附近停车场列表URL请求参数", description = "adCode请求参数")
public class InParkParkingUrlGetRequestDto extends SessionDto {

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    @NotNull(message = "纬度不能为空")
    private Double latitude;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    @NotNull(message = "经度不能为空")
    private Double longitude;
}
