package com.zoeeasy.cloud.open.controller.base;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.open.client.ClientService;
import com.zoeeasy.cloud.open.client.dto.request.ApiClientGetByAccessKeyRequestDto;
import com.zoeeasy.cloud.open.client.dto.result.ApiClientResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AkeemSuper
 * @date 2018/12/4 0004
 */
@RestController
@Slf4j
public class BaseController {

    @Autowired
    private ClientService clientService;

    public Long checkAccessKey(SignedRequestDto requestDto) {
        ApiClientGetByAccessKeyRequestDto apiClientGetByAccessKeyRequestDto = new ApiClientGetByAccessKeyRequestDto();
        apiClientGetByAccessKeyRequestDto.setAccessKey(requestDto.getAccessKey());
        ObjectResultDto<ApiClientResultDto> byAccessKey = clientService.getByAccessKey(apiClientGetByAccessKeyRequestDto);
        if (byAccessKey.isSuccess() && byAccessKey.getData() != null) {
            ApiClientResultDto data = byAccessKey.getData();
            return data.getTenantId();
        } else {
            return null;
        }
    }
}
