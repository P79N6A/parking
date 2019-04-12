package com.zoeeasy.cloud.pms.floor.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.pms.cst.PmsConstant;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 分页查询楼层请求参数
 *
 * Created by song on 2019/3/23 10:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FloorPagedRequestDto", description = "分页查询楼层请求参数")
public class FloorPagedRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 区域code
     */
    @ApiModelProperty(value = "区域code")
    private String areaCode;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkingName;

    /**
     * 车库名称
     */
    @ApiModelProperty(value = "车库名称")
    private String garageName;

    /**
     * 楼层名称
     */
    @ApiModelProperty(value = "楼层名称")
    private String floorName;

    /**
     * 楼层编号
     */
    @ApiModelProperty(value = "楼层编号")
    private String floorCode;

}
