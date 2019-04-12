package com.zhuyitech.sms.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.sms.domain.SmsClient;
import com.zhuyitech.sms.dto.request.*;
import com.zhuyitech.sms.dto.result.SmsClientResultDto;
import com.zhuyitech.sms.serivce.SmsClientCrudService;
import com.zhuyitech.sms.service.api.SmsClientService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 短信客户端服务类
 *
 * @author walkman
 */
@Service("smsClientService")
public class SmsClientServiceImpl implements SmsClientService {

    @Autowired
    private SmsClientCrudService smsClientCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 添加短信客户端
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addClient(ClientAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            SmsClient smsClient = modelMapper.map(requestDto, SmsClient.class);
            smsClient.setStatus(1);
            if (smsClient != null) {
                smsClientCrudService.insert(smsClient);
                resultDto.success();
            }
        } catch (Exception e) {
            return resultDto.failed(e.getMessage());
        }
        return resultDto;
    }

    /**
     * 修改短信客户端
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateClient(ClientUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {

            SmsClient smsClient = modelMapper.map(requestDto, SmsClient.class);
            smsClient.setStatus(1);
            if (smsClient != null) {
                EntityWrapper<SmsClient> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("id", requestDto.getId());
                smsClientCrudService.update(smsClient, entityWrapper);
                resultDto.success();
            }
        } catch (Exception e) {
            return resultDto.failed(e.getMessage());
        }
        return resultDto;
    }

    /**
     * 删除短信客户端
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteClient(ClientDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {

            EntityWrapper<SmsClient> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            smsClientCrudService.delete(entityWrapper);
            return resultDto.success();
        } catch (Exception e) {
            return resultDto.failed(e.getMessage());
        }
    }

    /**
     * 查询短信客户端
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<SmsClientResultDto> getClient(ClientGetRequestDto requestDto) {
        ObjectResultDto<SmsClientResultDto> objectResultDto = new ObjectResultDto();
        try {

            SmsClient smsClient = smsClientCrudService.selectById(requestDto.getId());
            SmsClientResultDto smsClientResultDto = modelMapper.map(smsClient, SmsClientResultDto.class);
            objectResultDto.setData(smsClientResultDto);
            return objectResultDto.success();
        } catch (Exception e) {
            return objectResultDto.failed(e.getMessage());
        }
    }

    /**
     * 分页查询短信客户端
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<SmsClientResultDto> queryClient(ClientQueryPagedResultRequestDto requestDto) {

        PagedResultDto<SmsClientResultDto> pagedResultDto = new PagedResultDto<>();
        try {

            EntityWrapper<SmsClient> entityWrapper = new EntityWrapper<>();
            if (StringUtils.isNotEmpty(requestDto.getClientId())) {
                entityWrapper.eq("clientId", requestDto.getClientId());
            }
            if (StringUtils.isNotEmpty(requestDto.getClientName())) {
                entityWrapper.like("clientName", requestDto.getClientName());
            }
            Page<SmsClient> clientPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<SmsClient> clientList = smsClientCrudService.selectPage(clientPage, entityWrapper);

            if (clientList != null) {

                List<SmsClientResultDto> clientDtoList = modelMapper.map(clientList.getRecords(), new TypeToken<List<SmsClientResultDto>>() {
                }.getType());

                pagedResultDto.setPageNo(clientPage.getCurrent());
                pagedResultDto.setPageSize(clientPage.getSize());
                pagedResultDto.setTotalCount(clientPage.getTotal());
                pagedResultDto.setItems(clientDtoList);
            }
            return pagedResultDto.success();
        } catch (Exception e) {
            return pagedResultDto.failed(e.getMessage());
        }
    }

}
