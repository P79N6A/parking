package com.zoeeasy.cloud.pms.floor.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pms.cst.PmsConstant;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 楼层列表查询请求参数
 *
 * Created by song on 2019/3/23 10:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FloorListRequestDto", description = "楼层列表查询请求参数")
public class FloorListRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty(value = "停车场ID")
    @NotNull(message = PmsConstant.PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 车库id
     */
    @ApiModelProperty(value = "车库ID")
    private Long garageId;

    /**
     * 楼层名称
     */
    @ApiModelProperty(value = "楼层名称")
    private String floorName;

}
