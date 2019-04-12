package com.zoeeasy.cloud.hikvision.service.impl;

import com.google.common.base.Charsets;
import com.scapegoat.infrastructure.common.utils.BeanUtils;
import com.zoeeasy.cloud.core.utils.UUIDTool;
import com.zoeeasy.cloud.hikvision.config.HikvisionConfiguration;
import com.zoeeasy.cloud.hikvision.constant.HikvisionConstants;
import com.zoeeasy.cloud.hikvision.dto.request.*;
import com.zoeeasy.cloud.hikvision.dto.result.*;
import com.zoeeasy.cloud.hikvision.service.HikvisionService;
import com.zoeeasy.cloud.hikvision.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author AkeemSuper
 * @date 2018/1/15 0015
 */
@Service("hikvisionService")
@Slf4j
public class HikvisionServiceImpl implements HikvisionService {

    @Autowired
    private HikvisionConfiguration hikvisionProperty;

    /**
     * 初始化接口系统请求参数
     *
     * @param params 系统请求参数
     */
    private void initParams(HikvisionParams params) {
        long currentTimeMillis = System.currentTimeMillis();
        String nonce = UUIDTool.getUUID();
        params.setAppkey(hikvisionProperty.getAppKey());
        params.setTime(currentTimeMillis);
        params.setNonce(nonce);
    }

    /**
     * 分页获取停车场基本信息
     *
     * @param params ParkingInfosParams
     * @return ParkingInfosResult
     */
    @Override
    public ParkingInfosResult getParkingInfos(ParkingInfosParams params) {
        ParkingInfosResult parkingInfosResult = null;
        try {
            //初始化
            initParams(params);
            Map<String, Object> signMap = BeanUtils.objectToMap(params);
            String token = TokenUtils.createToken(signMap, hikvisionProperty.getSecret());
            String urlWithToken = hikvisionProperty.getHikvisionUrlPrefix() + HikvisionConstants.GET_PARKING_INFOS_URL + "?token=" + token;
            parkingInfosResult = Requests.post(urlWithToken).jsonBody(params).charset(Charsets.UTF_8).send().readToJson(ParkingInfosResult.class);
        } catch (Exception e) {
            log.error("分页获取停车场基本信息失败" + e.getMessage());
        }
        return parkingInfosResult;
    }

    /**
     * 根据停车场的parkCode集获取停车场基本信息
     *
     * @param params ParkingInfosByParkCodesParams
     * @return ParkingInfosByParkCodeResult
     */
    @Override
    public ParkingInfosByParkCodeResult getParkingInfosByParkCodes(ParkingInfosByParkCodesParams params) {
        ParkingInfosByParkCodeResult parkingInfosByParkCodeResult = null;
        try {
            //初始化
            initParams(params);
            Map<String, Object> signMap = BeanUtils.objectToMap(params);
            String token = TokenUtils.createToken(signMap, hikvisionProperty.getSecret());
            String urlWithToken = hikvisionProperty.getHikvisionUrlPrefix() + HikvisionConstants.GET_PARKINGINFOS_BY_PARKCODES_URL +
                    "?token" +
                    "=" + token;
            parkingInfosByParkCodeResult = Requests.post(urlWithToken).jsonBody(params).charset(Charsets.UTF_8).send().readToJson(ParkingInfosByParkCodeResult.class);
        } catch (Exception e) {
            log.error("根据停车场的parkCode集获取停车场基本信息失败" + e.getMessage());
        }
        return parkingInfosByParkCodeResult;
    }

    /**
     * 根据停车场编号集获取停车场出入口基本信息
     *
     * @param params GateInfosByParkCodesParams
     * @return GateInfosByParkCodesResult
     */
    @Override
    public GateInfosByParkCodesResult getGateInfosByParkCodes(GateInfosByParkCodesParams params) {
        GateInfosByParkCodesResult gateInfosByParkCodesResult = null;
        try {
            //初始化
            initParams(params);
            Map<String, Object> signMap = BeanUtils.objectToMap(params);
            String token = TokenUtils.createToken(signMap, hikvisionProperty.getSecret());
            String urlWithToken =
                    hikvisionProperty.getHikvisionUrlPrefix() + HikvisionConstants.GET_GATE_INFOS_BY_PARK_CODES + "?token=" + token;
            gateInfosByParkCodesResult = Requests.post(urlWithToken).jsonBody(params).charset(Charsets.UTF_8).send().readToJson(GateInfosByParkCodesResult.class);
        } catch (Exception e) {
            log.error("根据停车场编号集获取停车场出入口基本信息失败" + e.getMessage());
        }
        return gateInfosByParkCodesResult;
    }

    /**
     * 根据出入口编号集获取出入口车道基本信息
     *
     * @param params LaneInfosByGateCodesParams
     * @return LaneInfosByGateCodeResult
     */
    @Override
    public LaneInfosByGateCodeResult getLaneInfosByGateCodes(LaneInfosByGateCodesParams params) {
        LaneInfosByGateCodeResult laneInfosByGateCodeResult = null;
        try {
            //初始化
            initParams(params);
            Map<String, Object> signMap = BeanUtils.objectToMap(params);
            String token = TokenUtils.createToken(signMap, hikvisionProperty.getSecret());
            String urlWithToken =
                    hikvisionProperty.getHikvisionUrlPrefix() + HikvisionConstants.GET_LANE_INFOS_BY_GATE_CODES + "?token=" + token;
            laneInfosByGateCodeResult = Requests.post(urlWithToken).jsonBody(params).charset(Charsets.UTF_8).send().readToJson(LaneInfosByGateCodeResult.class);
        } catch (Exception e) {
            log.error("根据出入口编号集获取出入口车道基本信息失败" + e.getMessage());
        }
        return laneInfosByGateCodeResult;
    }

    /**
     * 获取某个停车场的剩余车位数
     *
     * @param params LeftPlotParams
     * @return LeftPlotResult
     */
    @Override
    public LeftPlotResult getLeftPlot(LeftPlotParams params) {
        LeftPlotResult leftPlotResult = null;
        try {
            //初始化
            initParams(params);
            Map<String, Object> signMap = BeanUtils.objectToMap(params);
            String token = TokenUtils.createToken(signMap, hikvisionProperty.getSecret());
            String urlWithToken = hikvisionProperty.getHikvisionUrlPrefix() + HikvisionConstants.GET_LEFT_PLOT + "?token=" + token;
            leftPlotResult = Requests.post(urlWithToken).jsonBody(params).charset(Charsets.UTF_8).send().readToJson(LeftPlotResult.class);
        } catch (Exception e) {
            log.error("获取某个停车场的剩余车位数失败" + e.getMessage());
        }
        return leftPlotResult;
    }

    /**
     * 根据停车场编号集获取泊位
     *
     * @param params BerthInfosByParkCodesParams
     * @return BerthInfosByParkCodesResult
     */
    @Override
    public BerthInfosByParkCodesResult getBerthInfosByParkCodes(BerthInfosByParkCodesParams params) {
        BerthInfosByParkCodesResult berthInfosByParkCodesResult = null;
        try {
            //初始化
            initParams(params);
            Map<String, Object> signMap = BeanUtils.objectToMap(params);
            String token = TokenUtils.createToken(signMap, hikvisionProperty.getSecret());
            String urlWithToken =
                    hikvisionProperty.getHikvisionUrlPrefix() + HikvisionConstants.GET_BERTH_INFOS_BY_PARK_CODES + "?token=" + token;
            berthInfosByParkCodesResult = Requests.post(urlWithToken).jsonBody(params).charset(Charsets.UTF_8).send().readToJson(BerthInfosByParkCodesResult.class);
        } catch (Exception e) {
            log.error("根据停车场编号集获取泊位失败" + e.getMessage());
        }
        return berthInfosByParkCodesResult;
    }

    /**
     * 分页获取停车场某个时间段临时停车缴费记录
     *
     * @param params TempCarChargeRecordsParams
     * @return TempCarChargeRecordsResult
     */
    @Override
    public TempCarChargeRecordsResult getTempCarChargeRecords(TempCarChargeRecordsParams params) {
        TempCarChargeRecordsResult tempCarChargeRecordsResult = null;
        try {
            //初始化
            initParams(params);
            Map<String, Object> signMap = BeanUtils.objectToMap(params);
            String token = TokenUtils.createToken(signMap, hikvisionProperty.getSecret());
            String urlWithToken =
                    hikvisionProperty.getHikvisionUrlPrefix() + HikvisionConstants.GET_TEMP_CAR_CHARGE_RECORDS + "?token=" + token;
            tempCarChargeRecordsResult = Requests.post(urlWithToken).jsonBody(params).charset(Charsets.UTF_8).send().readToJson(TempCarChargeRecordsResult.class);
        } catch (Exception e) {
            log.error("分页获取停车场某个时间段临时停车缴费记录失败" + e.getMessage());
        }
        return tempCarChargeRecordsResult;
    }

    /**
     * 分页获取停车场过车记录
     *
     * @param params VehicleRecordsParams
     * @return VehicleRecordsResult
     */
    @Override
    public VehicleRecordsResult getVehicleRecords(VehicleRecordsParams params) {
        VehicleRecordsResult vehicleRecordsResult = null;
        try {
            //初始化
            initParams(params);
            Map<String, Object> signMap = BeanUtils.objectToMap(params);
            String token = TokenUtils.createToken(signMap, hikvisionProperty.getSecret());
            String urlWithToken =
                    hikvisionProperty.getHikvisionUrlPrefix() + HikvisionConstants.GET_VEHICLE_RECORDS_URL + "?token=" + token;
            vehicleRecordsResult = Requests.post(urlWithToken).socksTimeout(5000 * 60).connectTimeout(30 * 1000).jsonBody(params).charset(Charsets.UTF_8)
                    .send().readToJson(VehicleRecordsResult.class);
        } catch (Exception e) {
            log.error("分页获取停车场过车记录失败,异常信息:{}", e.getMessage());
        }
        return vehicleRecordsResult;
    }

    /**
     * 根据过车记录UUID获取过车图片
     *
     * @param params PassPicByUuidParams
     * @return PassPicByUuidResult
     */
    @Override
    public PassPicByUuidResult getPassPicByUuid(PassPicByUuidParams params) {
        PassPicByUuidResult passPicByUuidResult = null;
        try {
            //初始化
            initParams(params);
            Map<String, Object> signMap = BeanUtils.objectToMap(params);
            String token = TokenUtils.createToken(signMap, hikvisionProperty.getSecret());
            String urlWithToken =
                    hikvisionProperty.getHikvisionUrlPrefix() + HikvisionConstants.GET_PASSPIC_BY_UUID_URL + "?token=" + token;
            passPicByUuidResult = Requests.post(urlWithToken).socksTimeout(1000 * 60).connectTimeout(30 * 1000).jsonBody(params).charset(Charsets.UTF_8).send().readToJson(PassPicByUuidResult.class);
        } catch (Exception e) {
            log.error("根据过车记录UUID获取过车图片失败" + e.getMessage());
        }
        return passPicByUuidResult;
    }

    /**
     * 向云平台发送心跳
     *
     * @param params HikvisionParams
     * @return HikvisionBaseResult
     */
    @Override
    public HikvisionBaseResult heartBeat(HikvisionParams params) {
        HikvisionBaseResult hikvisionBaseResult = null;
        try {
            //初始化
            initParams(params);
            Map<String, Object> signMap = BeanUtils.objectToMap(params);
            String token = TokenUtils.createToken(signMap, hikvisionProperty.getSecret());
            String urlWithToken = hikvisionProperty.getHikvisionUrlPrefix() + HikvisionConstants.HEART_BEAT + "?token=" + token;
            hikvisionBaseResult = Requests.post(urlWithToken).jsonBody(params).charset(Charsets.UTF_8).send().readToJson(HikvisionBaseResult.class);
        } catch (Exception e) {
            log.error("向云平台发送心跳失败" + e.getMessage());
        }
        return hikvisionBaseResult;
    }

    /**
     * 根据车牌号码获取停车账单
     *
     * @param params ParkingPaymentInfoParams
     * @return ParkingPaymentInfoResult
     */
    @Override
    public ParkingPaymentInfoResult getParkingPaymentInfo(ParkingPaymentInfoParams params) {
        ParkingPaymentInfoResult parkingPaymentInfoResult = null;
        try {
            //初始化
            initParams(params);
            Map<String, Object> signMap = BeanUtils.objectToMap(params);
            String token = TokenUtils.createToken(signMap, hikvisionProperty.getSecret());
            String urlWithToken = hikvisionProperty.getHikvisionUrlPrefix() + HikvisionConstants.GET_PARKING_PAYMENT_INFO + "?token=" + token;
            parkingPaymentInfoResult = Requests.post(urlWithToken).jsonBody(params).charset(Charsets.UTF_8).send().readToJson(ParkingPaymentInfoResult.class);
        } catch (Exception e) {
            log.error("根据车牌号码获取停车账单失败" + e.getMessage());
        }
        return parkingPaymentInfoResult;
    }

    /**
     * 对停车账单支付成功后通知云平台
     *
     * @param params PayParkingFeeParams
     * @return HikvisionBaseResult
     */
    @Override
    public HikvisionBaseResult payParkingFee(PayParkingFeeParams params) {
        HikvisionBaseResult hikvisionBaseResult = null;
        try {
            //初始化
            initParams(params);
            Map<String, Object> signMap = BeanUtils.objectToMap(params);
            String token = TokenUtils.createToken(signMap, hikvisionProperty.getSecret());
            String urlWithToken = hikvisionProperty.getHikvisionUrlPrefix() + HikvisionConstants.PAY_PARKING_FEE + "?token=" + token;
            hikvisionBaseResult = Requests.post(urlWithToken).jsonBody(params).charset(Charsets.UTF_8).send().readToJson(HikvisionBaseResult.class);
        } catch (Exception e) {
            log.error("对停车账单支付成功后通知云平台失败" + e.getMessage());
        }
        return hikvisionBaseResult;
    }

    /**
     * 请求车位预约
     *
     * @param params ParkingReservationParams
     * @return ParkingReservationResult
     */
    @Override
    public ParkingReservationResult parkingReservation(ParkingReservationParams params) {
        ParkingReservationResult parkingReservationResult = null;
        try {
            //初始化
            initParams(params);
            Map<String, Object> signMap = BeanUtils.objectToMap(params);
            String token = TokenUtils.createToken(signMap, hikvisionProperty.getSecret());
            String urlWithToken = hikvisionProperty.getHikvisionUrlPrefix() + HikvisionConstants.PARKING_RESERVATION + "?token=" + token;
            parkingReservationResult = Requests.post(urlWithToken).jsonBody(params).charset(Charsets.UTF_8).send().readToJson(ParkingReservationResult.class);
        } catch (Exception e) {
            log.error("请求车位预约失败" + e.getMessage());
        }
        return parkingReservationResult;
    }

    /**
     * 事件订阅
     *
     * @param params HikvisionParams
     * @return SubscribeEventsFromMQResult
     */
    @Override
    public SubscribeEventsFromMQResult subscribeEventsFromMQ(HikvisionParams params) {
        SubscribeEventsFromMQResult subscribeEventsFromMQResult = null;
        try {
            //初始化
            initParams(params);
            Map<String, Object> signMap = BeanUtils.objectToMap(params);
            String token = TokenUtils.createToken(signMap, hikvisionProperty.getSecret());
            String urlWithToken = hikvisionProperty.getHikvisionUrlPrefix() + HikvisionConstants.SUBSCRIBE_EVENTS_FROM_MQ + "?token=" + token;
            subscribeEventsFromMQResult = Requests.post(urlWithToken).jsonBody(params).charset(Charsets.UTF_8).send().readToJson(SubscribeEventsFromMQResult.class);
        } catch (Exception e) {
            log.error("事件订阅失败" + e.getMessage());
        }
        return subscribeEventsFromMQResult;
    }

}
