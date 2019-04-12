package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.RequestDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 获取停车场请求参数
 * Created by wangfeng on 2018/12/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingGetByCodeRequestDto", description = "获取停车场id请求参数")
public class ParkingGetByCodeRequestDto extends RequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @NotBlank(message = ParkingConstant.PARKING_INFO_PARKING_CODE_NOT_NULL)
    @ApiModelProperty("id")
    private String code;

}
