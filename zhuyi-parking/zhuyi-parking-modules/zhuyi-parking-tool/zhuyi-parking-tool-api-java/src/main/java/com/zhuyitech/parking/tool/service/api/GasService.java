package com.zhuyitech.parking.tool.service.api;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.tool.dto.request.gas.GasStationGetRequestDto;

/**
 * 查询加油站_加气站_充电站相关服务
 *
 * @author zwq
 * @date 2018/06/21
 */
public interface GasService {

    /**
     *  获取充电桩信息
     *
     * @param requestDto
     * @return
     */
    ResultDto getGas(GasStationGetRequestDto requestDto);

}
