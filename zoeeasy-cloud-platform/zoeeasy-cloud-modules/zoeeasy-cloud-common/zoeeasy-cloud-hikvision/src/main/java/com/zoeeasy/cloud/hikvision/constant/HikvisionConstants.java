package com.zoeeasy.cloud.hikvision.constant;

/**
 * @Description hikvison 中的配置
 * @Date: 2018/1/12 0012
 * @author: AkeemSuper
 */
public class HikvisionConstants {

    private HikvisionConstants() {
    }

    /**
     * 分页获取停车场基本信息
     */
    public static final String GET_PARKING_INFOS_URL = "/getParkingInfos";

    /**
     * 根据停车场编号获取停车场基本信息
     */
    public static final String GET_PARKINGINFOS_BY_PARKCODES_URL = "/getParkingInfosByParkCodes";

    /**
     * 根据停车场编号集获取停车场出入口基本信息
     */
    public static final String GET_GATE_INFOS_BY_PARK_CODES = "/getGateInfosByParkCodes";

    /**
     * 根据出入口编号集获取出入口车道基本信息。
     */
    public static final String GET_LANE_INFOS_BY_GATE_CODES = "/getLaneInfosByGateCodes";

    /**
     * 获取某个停车场的剩余车位数
     */
    public static final String GET_LEFT_PLOT = "/getLeftPlot";

    /**
     * 分页获取路边停车场的泊位信息
     */
    public static final String GET_BERTH_INFOS_BY_PARK_CODES = "/getBerthInfosByParkCodes";

    /**
     * 分页获取停车场某个时间段临时停车缴费记录
     */
    public static final String GET_TEMP_CAR_CHARGE_RECORDS = "/getTempCarChargeRecords";

    /**
     * 分页获取停车场过车记录
     */
    public static final String GET_VEHICLE_RECORDS_URL = "/getVehicleRecords";

    /**
     * 获取过车记录的图片
     */
    public static final String GET_PASSPIC_BY_UUID_URL = "/getPassPicByUuid";

    /**
     * 向第三方平台发送心跳
     */
    public static final String HEART_BEAT = "/heartBeat";

    /**
     * 根据车牌号码获取停车账单
     */
    public static final String GET_PARKING_PAYMENT_INFO = "/getParkingPaymentInfo";

    /**
     * 对停车账单支付成功后通知云平台
     */
    public static final String PAY_PARKING_FEE = "/payParkingFee";

    /**
     * 向云平台请求车位预约
     */
    public static final String PARKING_RESERVATION = "/parkingReservation";

    /**
     * 事件订阅，获取事件消息。
     */
    public static final String SUBSCRIBE_EVENTS_FROM_MQ = "/subscribeEventsFromMQ";

}
