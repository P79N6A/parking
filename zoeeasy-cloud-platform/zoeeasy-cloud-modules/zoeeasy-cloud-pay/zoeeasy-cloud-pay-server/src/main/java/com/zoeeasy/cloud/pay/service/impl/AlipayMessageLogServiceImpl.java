package com.zoeeasy.cloud.pay.service.impl;

import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.pay.domain.AlipayMessageLogEntity;
import com.zoeeasy.cloud.pay.service.AlipayMessageLogCrudService;
import com.zoeeasy.cloud.pay.trade.AlipayMessageLogService;
import com.zoeeasy.cloud.pay.trade.dto.request.message.AlipayMessageLogAddRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.message.AlipayMessageLogGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.result.message.AlipayMessageLogResultDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author walkman
 */
@Service(value = "alipayMessageLogService")
@Slf4j
public class AlipayMessageLogServiceImpl implements AlipayMessageLogService {

    @Autowired
    private AlipayMessageLogCrudService alipayMessageLogCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 新增通知日志
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addMessageLog(AlipayMessageLogAddRequestDto requestDto) {

        ResultDto resultDto = new ResultDto();
        try {

            AlipayMessageLogEntity auditLog = modelMapper.map(requestDto, AlipayMessageLogEntity.class);
            auditLog.setCreationTime(DateUtils.now());
            alipayMessageLogCrudService.insert(auditLog);
            resultDto.success();
        } catch (Exception e) {
            log.error("操作日志添加失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取通知日志
     *
     * @param requestDto requestDto
     * @return AlipayMessageLogResultDto
     */
    @Override
    public ObjectResultDto<AlipayMessageLogResultDto> getMessageLog(AlipayMessageLogGetRequestDto requestDto) {

        ObjectResultDto<AlipayMessageLogResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            EntityWrapper<AlipayMessageLogEntity> entityWrapper = new EntityWrapper<>();
            if (StringUtils.isNotEmpty(requestDto.getNotifyId())) {
                entityWrapper.eq("notifyId", requestDto.getNotifyId());
            }
            if (StringUtils.isNotEmpty(requestDto.getAppId())) {
                entityWrapper.eq("appId", requestDto.getAppId());
            }
            if (StringUtils.isNotEmpty(requestDto.getTradeNo())) {
                entityWrapper.eq("tradeNo", requestDto.getTradeNo());
            }
            if (StringUtils.isNotEmpty(requestDto.getOutTradeNo())) {
                entityWrapper.eq("outTradeNo", requestDto.getOutTradeNo());
            }
            if (StringUtils.isNotEmpty(requestDto.getOutBizNo())) {
                entityWrapper.eq("outBizNo", requestDto.getOutBizNo());
            }
            if (requestDto.getStatus() != null) {
                entityWrapper.eq("status", requestDto.getStatus());
            }
            entityWrapper.last("LIMIT 1");
            AlipayMessageLogEntity alipayMessageLogEntity = alipayMessageLogCrudService.selectOne(entityWrapper);
            if (alipayMessageLogEntity != null) {
                AlipayMessageLogResultDto resultDto = modelMapper.map(alipayMessageLogEntity, AlipayMessageLogResultDto.class);
                objectResultDto.setData(resultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取支付宝通知日志失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
