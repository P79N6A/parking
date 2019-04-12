package com.zoeeasy.cloud.open.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.open.client.ClientService;
import com.zoeeasy.cloud.open.client.dto.request.ApiClientGetByAccessKeyRequestDto;
import com.zoeeasy.cloud.open.client.dto.result.ApiClientResultDto;
import com.zoeeasy.cloud.open.domain.ApiClientEntity;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AkeemSuper
 * @date 2018/12/4 0004
 */
@Service("clientService")
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ApiClientCrudService apiClientCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 通过accessKey获取api_client
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ApiClientResultDto> getByAccessKey(ApiClientGetByAccessKeyRequestDto requestDto) {
        ObjectResultDto<ApiClientResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<ApiClientEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("accessKey", requestDto.getAccessKey());
            ApiClientEntity apiClientEntity = apiClientCrudService.selectOne(entityWrapper);
            if (apiClientEntity != null) {
                ApiClientResultDto apiClientResultDto = modelMapper.map(apiClientEntity, ApiClientResultDto.class);
                objectResultDto.setData(apiClientResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("通过accessKey获取api_client失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
