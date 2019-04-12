package com.zoeeasy.cloud.alipay.service;


import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.alipay.result.AlipayParkingConfigQueryResult;
import com.zoeeasy.cloud.alipay.result.AlipayParkingVehicleQueryResult;
import com.zoeeasy.cloud.alipay.result.AlipayParkingCreateParkingLotInfoResult;
import com.zoeeasy.cloud.alipay.params.*;

/**
 * <pre>
 *    支付宝车主服务接口
 * </pre>
 *
 * @author walkman
 * @date 2018-03-20 10:22
 */
public interface AlipayParkingService {

    /**
     * 停车ISV系统配置
     *
     * @param
     * @return
     */
    ResultDto setConfig(AlipayParkingConfigSetParam params);

    /**
     * ISV系统配置查询接口
     *
     * @param params
     * @return
     */
    ObjectResultDto<AlipayParkingConfigQueryResult> queryConfig(AlipayParkingConfigQueryParam params);

    /**
     * 录入停车场信息
     *
     * @param params
     * @return
     */
    ObjectResultDto<AlipayParkingCreateParkingLotInfoResult> createParkingLotInfo(AlipayParkingCreateParkingLotInfoParam params);

    /**
     * 修改停车场信息
     *
     * @param params
     * @return
     */
    ResultDto updateParkingLotInfo(AlipayParkingUpdateParkingLotInfoParam params);

    /**
     * 车辆驶入接口
     *
     * @param params
     * @return
     */
    ResultDto syncEnterInfo(AlipayParkingSyncEnterInfoParam params);

    /**
     * 车辆驶出接口
     *
     * @param params
     * @return
     */
    ResultDto syncExitInfo(AlipayParkingSyncExitInfoParam params);

    /**
     * 订单同步接口
     *
     * @param params
     * @return
     */
    ResultDto syncOrder(AlipayParkingSyncOrderParam params);

    /**
     * 订单更新接口
     *
     * @param params
     * @return
     */
    ResultDto updateOrder(AlipayParkingUpdateOrderParam params);

    /**
     * 车牌查询接口
     *
     * @param params
     * @return
     */
    ObjectResultDto<AlipayParkingVehicleQueryResult> queryVehicle(AlipayParkingVehicleQueryParam params);
}
