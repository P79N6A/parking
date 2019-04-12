package com.zoeeasy.cloud.pms.area.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by Kane on 2018/11/14.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AreaParkingTreeResultDto", description = "区域停车场树返回视图")
public class AreaParkingTreeResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty("停车场id")
    private Long parkingId;


    /**
     * 编号
     */
    @ApiModelProperty("code")
    private String code;


    /**
     * 名称
     */
    @ApiModelProperty("name")
    private String name;

    /**
     *  下级列表
     */
    @ApiModelProperty("childList")
    private List<AreaParkingTreeResultDto> childList;

}
