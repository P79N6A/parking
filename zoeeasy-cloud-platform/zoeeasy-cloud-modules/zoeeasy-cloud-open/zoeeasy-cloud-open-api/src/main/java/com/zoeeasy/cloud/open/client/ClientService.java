package com.zoeeasy.cloud.open.client;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.open.client.dto.request.ApiClientGetByAccessKeyRequestDto;
import com.zoeeasy.cloud.open.client.dto.result.ApiClientResultDto;

/**
 * @author AkeemSuper
 * @date 2018/12/4 0004
 */
public interface ClientService {

    /**
     * 通过accessKey获取api_client
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ApiClientResultDto> getByAccessKey(ApiClientGetByAccessKeyRequestDto requestDto);
}
