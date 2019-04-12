package com.zoeeasy.cloud.pms.platform.dto.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingFloorResultDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 租户Id
     */
    private Long tenantId;

    /**
     * 停车场id
     */
    private Long parkingId;

    /**
     * 车库id
     */
    private Long garageId;

    /**
     * 楼层code
     */
    private String floorCode;

    /**
     * 楼层name
     */
    private String floorName;

    /**
     * 泊位数量
     */
    private Integer lotCount;

    /**
     * 固定车位数
     */
    private Integer lotFixed;

    /**
     * 剩余车位数
     */
    private Integer lotAvailable;

    /**
     * 楼层说明
     */
    private String remark;

}
