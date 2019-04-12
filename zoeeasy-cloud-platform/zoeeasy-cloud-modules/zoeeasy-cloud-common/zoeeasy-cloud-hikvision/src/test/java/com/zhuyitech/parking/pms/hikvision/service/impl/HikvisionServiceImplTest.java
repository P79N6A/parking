package com.zhuyitech.parking.pms.hikvision.service.impl;

import com.zoeeasy.cloud.hikvision.dto.request.HikvisionParams;
import com.zoeeasy.cloud.hikvision.service.impl.HikvisionServiceImpl;
//import org.junit.Test;

/**
 * @author AkeemSuper
 * @description hikvision接口测试
 * @date 2018/1/15 0015
 */
public class HikvisionServiceImplTest {

//    @Test
    public void test() {
        String parkCode = null;

        HikvisionServiceImpl hikvisionService = new HikvisionServiceImpl();
        HikvisionParams hikvisionParams = new HikvisionParams();
        //事件订阅
//        SubscribeEventsFromMQResult subscribeEventsFromMQResult = hikvisionService.subscribeEventsFromMQ(hikvisionParams);
        //向云平台发送心跳
//        HikvisionBaseResult heartBeatResult = hikvisionService.heartBeat(hikvisionParams);

        // 分页获取停车场基本信息
//        ParkingInfosParams parkingInfosParams = new ParkingInfosParams();
//        parkingInfosParams.setPageSize(400);
//        parkingInfosParams.setPageNo(1);
//        ParkingInfosResult parkingInfos = hikvisionService.getParkingInfos(parkingInfosParams);
//        List<ParkingInfoBean> list = parkingInfos.getData().getList();
//        for (ParkingInfoBean parkingInfoBean : list) {
//            parkCode = parkingInfoBean.getParkCode();
//        }

        //分页获取停车场某个时间段临时停车缴费记录
        //接口有问题----------系统异常
//        TempCarChargeRecordsParams tempCarChargeRecordsParams = new TempCarChargeRecordsParams();
//        tempCarChargeRecordsParams.setPageNo(1);
//        tempCarChargeRecordsParams.setPageSize(400);
//        TempCarChargeRecordsResult tempCarChargeRecords = hikvisionService.getTempCarChargeRecords(tempCarChargeRecordsParams);

        //根据停车场的parkCode集获取停车场基本信息
//        ParkingInfosByParkCodesParams parkingInfosByParkCodesParams = new ParkingInfosByParkCodesParams();
//        parkingInfosByParkCodesParams.setParkCodes(parkCode);
//        ParkingInfosByParkCodeResult parkingInfosByParkCodes = hikvisionService.getParkingInfosByParkCodes(parkingInfosByParkCodesParams);

//        //根据停车场编号集获取停车场出入口基本信息
//        GateInfosByParkCodesParams gateInfosByParkCodesParams = new GateInfosByParkCodesParams();
//        gateInfosByParkCodesParams.setParkCodes(parkCode);
//        GateInfosByParkCodesResult gateInfosByParkCodes = hikvisionService.getGateInfosByParkCodes(gateInfosByParkCodesParams);
//        String gateCode = gateInfosByParkCodes.getData().getGateCode();//空值
//
//        //根据出入口编号集获取出入口车道基本信息
//        LaneInfosByGateCodesParams laneInfosByGateCodesParams = new LaneInfosByGateCodesParams();
//        laneInfosByGateCodesParams.setGateCodes(gateCode);
//        LaneInfosByGateCodeResult laneInfosByGateCodes = hikvisionService.getLaneInfosByGateCodes(laneInfosByGateCodesParams);

        //获取某个停车场的剩余车位数
//        LeftPlotParams leftPlotParams = new LeftPlotParams();
//        leftPlotParams.setParkCode(parkCode);
//        LeftPlotResult leftPlot = hikvisionService.getLeftPlot(leftPlotParams);

        //根据过车记录UUID获取过车图片
//        PassPicByUuidParams passPicByUuidParams = new PassPicByUuidParams();
//        passPicByUuidParams.setUnid("415F4D87A1639637F0A3230BC");
//        passPicByUuidParams.setParkCode("10118060122091501000");
//        PassPicByUuidResult passPicByUuid = hikvisionService.getPassPicByUuid(passPicByUuidParams);

//        //根据车牌号码获取停车账单
//        ParkingPaymentInfoParams parkingPaymentInfoParams = new ParkingPaymentInfoParams();
//        parkingPaymentInfoParams.setPlateColor(plateColor);
//        parkingPaymentInfoParams.setPlateNo(plateNo);
//        ParkingPaymentInfoResult parkingPaymentInfo = hikvisionService.getParkingPaymentInfo(parkingPaymentInfoParams);

    }
}