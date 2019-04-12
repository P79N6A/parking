package com.zoeeasy.cloud.open.provider;

import com.scapegoat.infrastructure.core.client.DefaultOpenClient;
import com.scapegoat.infrastructure.core.client.OpenClient;
import com.scapegoat.infrastructure.core.client.OpenClientProvider;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.open.client.ClientService;
import com.zoeeasy.cloud.open.client.dto.request.ApiClientGetByAccessKeyRequestDto;
import com.zoeeasy.cloud.open.client.dto.result.ApiClientResultDto;
import org.springframework.beans.factory.annotation.Autowired;

public class DefaultOpenClientProvider implements OpenClientProvider {

    @Autowired
    private ClientService clientService;

    private static DefaultOpenClientProvider defaultOpenClientProvider;

    public static DefaultOpenClientProvider getInstance() {
        if (defaultOpenClientProvider == null) {
            defaultOpenClientProvider = new DefaultOpenClientProvider();
        }
        return defaultOpenClientProvider;
    }

    @Override
    public OpenClient loadOpenClient(String accessKey) {
        ApiClientGetByAccessKeyRequestDto apiClientGetByAccessKeyRequestDto = new ApiClientGetByAccessKeyRequestDto();
        apiClientGetByAccessKeyRequestDto.setAccessKey(accessKey);
        ObjectResultDto<ApiClientResultDto> byAccessKey = clientService.getByAccessKey(apiClientGetByAccessKeyRequestDto);
        if (byAccessKey.isSuccess() && byAccessKey.getData() != null) {
            ApiClientResultDto apiClientResultDto = byAccessKey.getData();
            return new DefaultOpenClient(accessKey, apiClientResultDto.getAccessSecrete());
        }
        return null;
    }
}
