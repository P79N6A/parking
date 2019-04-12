package com.zhuyitech.parking.client;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.client.dto.ClientMockRequestDto;

/**
 * @author AkeemSuper
 * @date 2018/12/6 0006
 */
public interface ClientService {

    /**
     * 模拟客户端数据
     */
    ResultDto mockClientPassRecord(ClientMockRequestDto requestDto);
}


