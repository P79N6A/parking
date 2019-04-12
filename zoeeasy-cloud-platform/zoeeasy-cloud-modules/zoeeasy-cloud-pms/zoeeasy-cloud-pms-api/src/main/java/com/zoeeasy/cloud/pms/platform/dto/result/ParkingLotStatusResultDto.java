package com.zoeeasy.cloud.pms.platform.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/9/26 0026
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLotStatusResultDto", description = "泊位信息")
public class ParkingLotStatusResultDto extends EntityDto<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户Id
     */
    @ApiModelProperty("tenantId")
    private Long tenantId;

    /**
     * 停车场ID
     */
    @ApiModelProperty("parkingId")
    private Long parkingId;

    /**
     * 泊位ID
     */
    @ApiModelProperty("parkingLotId")
    private Long parkingLotId;

    /**
     * 泊位状态(0-空闲, 1-占用，2-未知)
     */
    @ApiModelProperty("status")
    private Integer status;

    /**
     * 入车过车记录ID
     */
    @ApiModelProperty("intoPassingId")
    private Long intoPassingId;

    /**
     * 车牌号码
     */
    @ApiModelProperty("plateNumber")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("plateColor")
    private Integer plateColor;

    /**
     * 占用时间：泊位状态从0，2 到1 的时设置，1到1 不更新，1到0 更新成null
     */
    @ApiModelProperty("occupyTime")
    private Date occupyTime;
}
