package com.zoeeasy.cloud.integration.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.esotericsoftware.minlog.Log;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.collect.core.CollectOperateService;
import com.zoeeasy.cloud.collect.dto.request.QueryPriceRequestDto;
import com.zoeeasy.cloud.integration.open.TerminateParkingOrderService;
import com.zoeeasy.cloud.integration.open.dto.request.AnyParkingOrderPlaceRequestDto;
import com.zoeeasy.cloud.integration.open.dto.request.CloudOrderCallbackRequestDto;
import com.zoeeasy.cloud.pms.dock.DockInfoService;
import com.zoeeasy.cloud.pms.dock.dto.request.DockInfoGetByIdRequestDto;
import com.zoeeasy.cloud.pms.dock.dto.result.DockInfoResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingInfoResultDto;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingInfoGetRequestDto;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.apache.commons.io.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 任意停车平台订单服务
 *
 * @author AkeemSuper
 * @date 2018/12/3 0003
 */
@Service("terminateParkingOrderService")
@Slf4j
public class TerminateParkingOrderServiceImpl implements TerminateParkingOrderService {

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @Autowired
    private DockInfoService dockInfoService;

    @Autowired
    private CollectOperateService collectOperateService;

    /**
     * 调用创建订单接口
     *
     * @param requestDto
     */
    @Override
    public ResultDto placeAynParkingOrder(AnyParkingOrderPlaceRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            QueryPriceRequestDto queryPriceRequestDto = new QueryPriceRequestDto();
            ParkingInfoGetRequestDto parkingInfoGetRequestDto = new ParkingInfoGetRequestDto();
            parkingInfoGetRequestDto.setParkingId(requestDto.getParkingId());
            ObjectResultDto<ParkingInfoResultDto> parkingInfoResultDto = platformParkingInfoService.getParkInfoById(parkingInfoGetRequestDto);

            if (parkingInfoResultDto.isFailed() || parkingInfoResultDto.getData() == null) {
                Log.error("根据ParkingId查询停车场失败" + requestDto.getParkingId());
                resultDto.failed();
            }
            queryPriceRequestDto.setLocalCode(parkingInfoResultDto.getData().getLocalCode());
            queryPriceRequestDto.setPlateColor(requestDto.getPlateColor().toString());
            queryPriceRequestDto.setPlateNumber(requestDto.getPlateNumber());
            queryPriceRequestDto.setOrderNo(requestDto.getOrderNo());
            ResultDto queryPriceObjectResultDto = collectOperateService.queryPrice(queryPriceRequestDto);
            if (queryPriceObjectResultDto.isFailed()) {
                log.error("查询价格失败" + queryPriceRequestDto.toString());
                resultDto.failed("查询价格失败！");
            } else {
                resultDto.success();
            }
        } catch (Exception e) {
            log.error("价格查询接口失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 账单支付回调接口
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto orderCallback(CloudOrderCallbackRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            DockInfoResultDto dockInfo = getDockInfo(requestDto.getParkingId());
            if (dockInfo != null) {
                String jsonString = JSONObject.toJSONString(requestDto);
                String response = Requests.post(dockInfo.getNotifyOrderUrl()).socksTimeout(5000 * 60)
                        .connectTimeout(30 * 1000).jsonBody(jsonString).charset(Charsets.UTF_8).send().readToText();
                JSONObject jsonObject = JSONObject.parseObject(response);
                Integer code = jsonObject.getInteger("code");
                if (code.equals(1)) {
                    resultDto.success();
                } else {
                    log.warn("客户端账单支付回调失败:{}", JSON.toJSONString(requestDto));
                    resultDto.failed();
                }
            } else {
                resultDto.failed("未找到客户端信息");
            }
        } catch (Exception e) {
            log.error("账单支付回调接口失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    private DockInfoResultDto getDockInfo(Long parkingId) {
        ParkingInfoGetRequestDto parkingInfoGetByIdRequestDto = new ParkingInfoGetRequestDto();
        parkingInfoGetByIdRequestDto.setParkingId(parkingId);
        ObjectResultDto<ParkingInfoResultDto> parkInfoById = platformParkingInfoService.getParkInfoById(parkingInfoGetByIdRequestDto);
        if (parkInfoById.isSuccess() && parkInfoById.getData() != null) {
            ParkingInfoResultDto data = parkInfoById.getData();
            Long dockId = data.getDockId();
            DockInfoGetByIdRequestDto dockInfoGetByIdRequestDto = new DockInfoGetByIdRequestDto();
            dockInfoGetByIdRequestDto.setId(dockId);
            ObjectResultDto<DockInfoResultDto> dockById = dockInfoService.getDockById(dockInfoGetByIdRequestDto);
            if (dockById.isSuccess() && dockById.getData() != null) {
                return dockById.getData();
            }
        }
        return null;
    }
}
