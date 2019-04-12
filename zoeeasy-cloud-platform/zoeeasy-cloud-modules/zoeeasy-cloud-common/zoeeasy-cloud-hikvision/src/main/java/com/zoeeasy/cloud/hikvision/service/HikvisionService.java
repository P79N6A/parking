package com.zoeeasy.cloud.hikvision.service;

import com.zoeeasy.cloud.hikvision.dto.request.*;
import com.zoeeasy.cloud.hikvision.dto.result.*;


/**
 * @author AkeemSuper
 * @description hkvison接口
 * @date 2018/1/13 0013
 */
public interface HikvisionService {

    /**
     * 分页获取停车场基本信息
     *
     * @param params ParkingInfosParams
     * @return ParkingInfosResult
     */
    ParkingInfosResult getParkingInfos(ParkingInfosParams params);

    /**
     * 根据停车场的parkCode集获取停车场基本信息
     *
     * @param params ParkingInfosByParkCodesParams
     * @return ParkingInfosByParkCodeResult
     */
    ParkingInfosByParkCodeResult getParkingInfosByParkCodes(ParkingInfosByParkCodesParams params);

    /**
     * 根据停车场编号集获取停车场出入口基本信息
     *
     * @param params GateInfosByParkCodesParams
     * @return GateInfosByParkCodesResult
     */
    GateInfosByParkCodesResult getGateInfosByParkCodes(GateInfosByParkCodesParams params);

    /**
     * 根据出入口编号集获取出入口车道基本信息
     *
     * @param params LaneInfosByGateCodesParams
     * @return LaneInfosByGateCodeResult
     */
    LaneInfosByGateCodeResult getLaneInfosByGateCodes(LaneInfosByGateCodesParams params);

    /**
     * 获取某个停车场的剩余车位数
     *
     * @param params LeftPlotParams
     * @return LeftPlotResult
     */
    LeftPlotResult getLeftPlot(LeftPlotParams params);

    /**
     * 根据停车场编号集获取泊位
     *
     * @param params BerthInfosByParkCodesParams
     * @return BerthInfosByParkCodesResult
     */
    BerthInfosByParkCodesResult getBerthInfosByParkCodes(BerthInfosByParkCodesParams params);

    /**
     * 分页获取停车场某个时间段临时停车缴费记录
     *
     * @param params TempCarChargeRecordsParams
     * @return TempCarChargeRecordsResult
     */
    TempCarChargeRecordsResult getTempCarChargeRecords(TempCarChargeRecordsParams params);

    /**
     * 分页获取停车场过车记录
     *
     * @param params VehicleRecordsParams
     * @return VehicleRecordsResult
     */
    VehicleRecordsResult getVehicleRecords(VehicleRecordsParams params);

    /**
     * 根据过车记录UUID获取过车图片
     *
     * @param params PassPicByUuidParams
     * @return PassPicByUuidResult
     */
    PassPicByUuidResult getPassPicByUuid(PassPicByUuidParams params);

    /**
     * 向云平台发送心跳
     *
     * @param params HikvisionParams
     * @return HikvisionBaseResult
     */
    HikvisionBaseResult heartBeat(HikvisionParams params);

    /**
     * 根据车牌号码获取停车账单
     *
     * @param params ParkingPaymentInfoParams
     * @return ParkingPaymentInfoResult
     */
    ParkingPaymentInfoResult getParkingPaymentInfo(ParkingPaymentInfoParams params);

    /**
     * 对停车账单支付成功后通知云平台
     *
     * @param params PayParkingFeeParams
     * @return HikvisionBaseResult
     */
    HikvisionBaseResult payParkingFee(PayParkingFeeParams params);

    /**
     * 请求车位预约
     *
     * @param params ParkingReservationParams
     * @return ParkingReservationResult
     */
    ParkingReservationResult parkingReservation(ParkingReservationParams params);

    /**
     * 事件订阅
     *
     * @param params HikvisionParams
     * @return SubscribeEventsFromMQResult
     */
    SubscribeEventsFromMQResult subscribeEventsFromMQ(HikvisionParams params);
}
