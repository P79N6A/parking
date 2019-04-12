package com.zhuyitech.parking.inpark.service;


import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.inpark.request.InParkOrderListRequest;
import com.zhuyitech.parking.inpark.request.InParkParkingListRequest;
import com.zhuyitech.parking.inpark.result.InParkBaseResult;

/**
 * <pre>
 *    InPark接口服务
 * </pre>
 *
 * @author zwq
 * @date 2018-1-11-10:22
 */
public interface InParkService {
    /**
     * 获取附近停车场列表
     *
     * @param request
     * @return
     */
    ObjectResultDto<InParkBaseResult> getParkingList(InParkParkingListRequest request);

    /**
     * 获取用户订单列表
     *
     * @param request
     * @return
     */
    ObjectResultDto<InParkBaseResult> getOrderList(InParkOrderListRequest request);

}
