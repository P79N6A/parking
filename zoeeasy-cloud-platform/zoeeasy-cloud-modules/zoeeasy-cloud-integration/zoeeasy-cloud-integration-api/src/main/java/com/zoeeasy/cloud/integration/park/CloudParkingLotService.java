package com.zoeeasy.cloud.integration.park;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.park.dto.request.CloudAddParkingLotRequestDto;

/**
 * 云平台提供给管理系统调用泊位接口
 *
 * @Date: 2019/3/27
 * @author: lhj
 */
public interface CloudParkingLotService {

    /**
     * 添加泊位数据
     *
     * @param requestDto
     * @return
     */
    ResultDto clientAddParkingLot(CloudAddParkingLotRequestDto requestDto);
}
