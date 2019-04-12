package com.zhuyitech.parking.ucc.dto.request.violation;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 用户车辆违章记录查询请求参数
 *
 * @author walkman
 * @Date: 2018/4/14
 */
@ApiModel(value = "UserCarViolationQueryRequestDto", description = "车辆违章记录查询请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCarViolationQueryRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车辆ID
     */
    @ApiModelProperty(value = "车辆ID")
    @NotNull(message = "车辆ID不能为空")
    private Long carId;

}
