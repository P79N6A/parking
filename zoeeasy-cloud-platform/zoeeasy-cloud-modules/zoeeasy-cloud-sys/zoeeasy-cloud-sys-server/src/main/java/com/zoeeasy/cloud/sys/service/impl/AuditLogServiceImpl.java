package com.zoeeasy.cloud.sys.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.core.enums.BusinessType;
import com.zoeeasy.cloud.sys.domain.AuditLogEntity;
import com.zoeeasy.cloud.sys.enums.SysResultEnum;
import com.zoeeasy.cloud.sys.auditlog.*;
import com.zoeeasy.cloud.sys.auditlog.dto.*;
import com.zoeeasy.cloud.sys.service.AuditLogCrudService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 操作日志服务
 *
 * @author walkman
 */
@Service("auditLogService")
@Slf4j
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    private AuditLogCrudService auditLogCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 新增操作日志
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addAuditLog(AuditLogAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            AuditLogEntity auditLogEntity = modelMapper.map(requestDto, AuditLogEntity.class);
            auditLogCrudService.insert(auditLogEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("操作日志添加失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除操作日志
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteAuditLog(AuditLogDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            auditLogCrudService.deleteById(requestDto.getId());
            resultDto.success();
        } catch (Exception e) {
            log.error("操作日志删除失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 更新操作日志
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateAuditLog(AuditLogUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {

            if (StringUtils.isEmpty(requestDto.getTitle())) {
                return resultDto.makeResult(SysResultEnum.OPERATION_LOG_TITLE_EMPTY.getValue(),
                        SysResultEnum.OPERATION_LOG_TITLE_EMPTY.getComment());
            }
            AuditLogEntity auditLogEntity = modelMapper.map(requestDto, AuditLogEntity.class);
            EntityWrapper<AuditLogEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            auditLogCrudService.update(auditLogEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("操作日志更新失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取操作日志
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<AuditLogResultDto> getAuditLog(AuditLogGetRequestDto requestDto) {
        ObjectResultDto<AuditLogResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            AuditLogEntity auditLogEntity = auditLogCrudService.selectById(requestDto.getId());
            if (auditLogEntity != null) {
                AuditLogResultDto resultDto = modelMapper.map(auditLogEntity, AuditLogResultDto.class);
                objectResultDto.setData(resultDto);
            } else {
                return objectResultDto.makeResult(SysResultEnum.OPERATION_LOG_NOT_FOUND.getValue(),
                        SysResultEnum.OPERATION_LOG_NOT_FOUND.getComment());
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("操作日志获取失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取操作类型枚举
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getType(AuditLogTypeGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (BusinessType businessType : BusinessType.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(businessType.getValue()), businessType.getComment(), false);
                list.add(comboboxItemDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取操作类型失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }


    /**
     * 分页获取操作日志列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<AuditLogResultDto> getAuditLogPagedList(AuditLogPagedRequestDto requestDto) {
        PagedResultDto<AuditLogResultDto> pagedResultDto = new PagedResultDto<>();
        try {

            EntityWrapper<AuditLogEntity> entityWrapper = new EntityWrapper<>();
            if (requestDto.getType() != null) {
                entityWrapper.eq("type", requestDto.getType());
            }
            if (requestDto.getCreationTimeStart() != null) {
                entityWrapper.ge("creationTime", requestDto.getCreationTimeStart());
            }
            if (requestDto.getCreationTimeEnd() != null) {
                entityWrapper.le("creationTime", this.getDayOfAfter(requestDto.getCreationTimeEnd()));
            }
            if (StringUtils.isNotBlank(requestDto.getAccount())) {
                entityWrapper.like("account", requestDto.getAccount());
            }
            Page<AuditLogEntity> auditLogPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<AuditLogEntity> auditLogPageList = auditLogCrudService.selectPage(auditLogPage, entityWrapper);
            if (auditLogPageList != null) {
                List<AuditLogResultDto> auditLogResultDtoList = modelMapper.map(auditLogPageList.getRecords(), new TypeToken<List<AuditLogResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(auditLogPage.getCurrent());
                pagedResultDto.setPageSize(auditLogPage.getSize());
                pagedResultDto.setTotalCount(auditLogPage.getTotal());
                pagedResultDto.setItems(auditLogResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取操作日志列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取后一天时间
     *
     * @param time
     * @return
     */
    private Date getDayOfAfter(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DATE, 1);
        Date newDate = calendar.getTime();
        return newDate;
    }


}
