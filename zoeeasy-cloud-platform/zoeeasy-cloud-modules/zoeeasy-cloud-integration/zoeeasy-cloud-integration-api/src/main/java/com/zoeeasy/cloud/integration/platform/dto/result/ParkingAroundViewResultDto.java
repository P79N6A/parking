package com.zoeeasy.cloud.integration.platform.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 附近停车场列表
 *
 * @author walkman
 * @date 2018/04/03
 */
@ApiModel(value = "ParkingAroundViewResultDto", description = "附近停车场列表")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingAroundViewResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 附近停车场数量
     */
    @ApiModelProperty(value = "附近停车场数量")
    private Integer totalCount;

    /**
     * 1KM内最近的停车场
     */
    @ApiModelProperty(value = "1KM内最近的停车场")
    private ParkingAroundItemViewResultDto closest;

    /**
     * 全部停车场列表
     */
    @ApiModelProperty(value = "全部停车场列表")
    private List<ParkingAroundItemViewResultDto> allParkings;

    /**
     * 限免停车场列表
     */
    @ApiModelProperty(value = "限免停车场列表")
    private List<ParkingAroundItemViewResultDto> freeParkings;

    /**
     * 收费停车场列表
     */
    @ApiModelProperty(value = "收费停车场列表")
    private List<ParkingAroundItemViewResultDto> chargeParkings;

}
