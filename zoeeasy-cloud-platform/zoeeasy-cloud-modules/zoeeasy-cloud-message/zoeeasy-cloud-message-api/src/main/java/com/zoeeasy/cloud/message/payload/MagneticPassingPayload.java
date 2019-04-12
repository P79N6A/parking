package com.zoeeasy.cloud.message.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 地磁检测过车消息内容
 *
 * @author AkeemSuper
 * @date 2018/9/21 0021
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MagneticPassingPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    private Long parkingId;

    /**
     * 泊位ID
     */
    private Long parkingLotId;

    /**
     * 过车类型  0 未知 1.入车 2.出车
     */
    private Integer passingType;

    /**
     * 过车时间
     */
    private Date passTime;

}
