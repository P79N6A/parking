package com.zoeeasy.cloud.message.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 平台过车消息内容
 *
 * @author AkeemSuper
 * @since 2018/9/25 0025
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PassingVehiclePayload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 过车消息数据源
     */
    private Integer dataSource;

    /**
     * 平台过车记录唯一ID
     */
    private String passingUuid;

    /**
     * 第三方平台过车记录唯一ID
     */
    private String thirdPassingId;

    /**
     * 车牌号码
     */
    private String plateNumber;

    /**
     * 车牌颜色
     */
    private Integer plateColor;

    /**
     * 车辆类型
     */
    private Integer carType;

    /**
     * 停车类型
     */
    private Integer parkingType;

    /**
     * 通过时间 格式：yyyy-MM-dd HH:mm:ss
     */
    private Date passTime;

    /**
     * 过车方向
     */
    private Integer direct;

    /**
     * 停车场编号
     */
    private String parkCode;

    /**
     * 停车场名称
     */
    private String parkName;

    /**
     * 出入口编号
     */
    private String gateCode;

    /**
     * 出入口名称
     */
    private String gateName;

    /**
     * 车道编号
     */
    private String laneCode;

    /**
     * 车道名称
     */
    private String laneName;

    /**
     * 泊位编号(全局唯一)
     */
    private String berthCode;

    /**
     * 泊位编号(停车场唯一)
     */
    private String berthNumber;

    /**
     * (平台)停车场编号
     */
    private String cloudParkingCode;

    /**
     * (平台)停车场名称
     */
    private String cloudParkingName;

    /**
     * (平台)出入口编号
     */
    private String cloudGateCode;

    /**
     * (平台)出入口名称
     */
    private String cloudGateName;

    /**
     * (平台)车道编号
     */
    private String cloudLaneCode;

    /**
     * (平台)车道名称
     */
    private String cloudLaneName;

    /**
     * (平台)泊位编号
     */
    private String cloudLotCode;

    /**
     * (平台)泊位编号(停车场唯一)
     */
    private String cloudLotNumber;

}
