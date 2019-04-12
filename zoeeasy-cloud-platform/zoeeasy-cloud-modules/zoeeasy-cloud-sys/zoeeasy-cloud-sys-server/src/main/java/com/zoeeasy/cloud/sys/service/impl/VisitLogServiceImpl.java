package com.zoeeasy.cloud.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.sys.domain.VisitLogEntity;
import com.zoeeasy.cloud.sys.service.VisitLogCrudService;
import com.zoeeasy.cloud.sys.visit.VisitLogService;
import com.zoeeasy.cloud.sys.visit.dto.request.VisitLogAddRequestDto;
import com.zoeeasy.cloud.sys.visit.dto.request.VisitLogQueryPageRequestDto;
import com.zoeeasy.cloud.sys.visit.dto.result.VisitLogResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2019/2/21 0021
 */
@Service("visitLogService")
@Slf4j
public class VisitLogServiceImpl implements VisitLogService {

    @Autowired
    private VisitLogCrudService visitLogCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 添加用户登录日志
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addVisitLog(VisitLogAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            VisitLogEntity visitLogEntity = modelMapper.map(requestDto, VisitLogEntity.class);
            boolean insert = visitLogCrudService.insert(visitLogEntity);
            if (!insert) {
                return resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("添加用户登录日志失败,异常信息:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页获取登录日志
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<VisitLogResultDto> queryPage(VisitLogQueryPageRequestDto requestDto) {
        PagedResultDto<VisitLogResultDto> resultDto = new PagedResultDto<>();
        try {
            EntityWrapper<VisitLogEntity> entityWrapper = new EntityWrapper<>();
            if (StringUtils.isNotBlank(requestDto.getAccount())) {
                entityWrapper.like("account", requestDto.getAccount());
            }
            if (null != requestDto.getStartTime()) {
                entityWrapper.ge("creationTime", requestDto.getStartTime());
            }
            if (null != requestDto.getEndTime()) {
                entityWrapper.le("creationTime", requestDto.getEndTime());
            }
            Page<VisitLogEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<VisitLogEntity> visitLogEntityPage = visitLogCrudService.selectPage(page, entityWrapper);
            if (visitLogEntityPage != null && CollectionUtils.isNotEmpty(visitLogEntityPage.getRecords())) {
                List<VisitLogResultDto> visitLogResultDtos = modelMapper.map(visitLogEntityPage.getRecords(), new TypeToken<List<VisitLogResultDto>>() {
                }.getType());
                resultDto.setItems(visitLogResultDtos);
                resultDto.setPageNo(requestDto.getPageNo());
                resultDto.setPageSize(requestDto.getPageSize());
                resultDto.setTotalCount(visitLogEntityPage.getTotal());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("分页获取登录日志失败,异常信息:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
