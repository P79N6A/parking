package com.zoeeasy.cloud.inspect.service.impl;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.inspect.domain.InspectRecordEntity;
import com.zoeeasy.cloud.inspect.record.InspectRecordService;
import com.zoeeasy.cloud.inspect.record.dto.request.InspectRecordSaveRequestDto;
import com.zoeeasy.cloud.inspect.service.InspectRecordCrudService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 巡检记录服务
 *
 * @author walkman
 */
@Service(value = "inspectRecordService")
@Slf4j
public class InspectRecordServiceImpl implements InspectRecordService {

    @Autowired
    private InspectRecordCrudService inspectRecordCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 保存巡检记录
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto saveInspectRecord(InspectRecordSaveRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            InspectRecordEntity inspectRecordEntity = modelMapper.map(requestDto, InspectRecordEntity.class);
            boolean insert = inspectRecordCrudService.insert(inspectRecordEntity);
            if (!insert) {
                return resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            resultDto.failed();
            log.error("保存巡检记录失败" + e.getMessage());
        }
        return resultDto;
    }
}
