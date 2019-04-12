package com.zoeeasy.cloud.message.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author AkeemSuper
 * @date 2018/12/2 0002
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AnyPassRecordImagePayload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 过车记录code
     */
    private String passingCode;

    /**
     * 全景图像
     */
    private String fullImage;

    /**
     * 车牌图像
     */
    private String plateImage;

    /**
     * 过车记录code
     */
    private String cloudParkingCode;
}
