package com.zhuyitech.parking.ucc.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.ucc.domain.VisitLog;
import com.zhuyitech.parking.ucc.dto.request.visitlog.*;
import com.zhuyitech.parking.ucc.dto.result.VisitLogResultDto;
import com.zhuyitech.parking.ucc.enums.UCenterResultEnum;
import com.zhuyitech.parking.ucc.service.VisitLogCrudService;
import com.zhuyitech.parking.ucc.service.api.VisitLogService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 访问日志服务
 *
 * @author walkman
 */
@Service("visitLogService")
public class VisitLogServiceImpl implements VisitLogService {

    private static final Logger LOG = LoggerFactory.getLogger(VisitLogServiceImpl.class);

    @Autowired
    private VisitLogCrudService visitLogCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 新增登录日志
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addVisitLog(VisitLogAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (requestDto.getUserId() == null) {
                return resultDto.makeResult(UCenterResultEnum.VISIT_LOG_USERNAME_EMPTY.getValue(),
                        UCenterResultEnum.VISIT_LOG_USERNAME_EMPTY.getComment());
            }
            VisitLog visitLog = modelMapper.map(requestDto, VisitLog.class);
            visitLogCrudService.insert(visitLog);
            resultDto.success();
        } catch (Exception e) {
            LOG.error("登录日志添加失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除登录日志
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteVisitLog(VisitLogDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            visitLogCrudService.deleteById(requestDto.getId());
            resultDto.success();
        } catch (Exception e) {
            LOG.error("登录日志删除失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 更新登录日志
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateVisitLog(VisitLogUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {

            if (StringUtils.isEmpty(requestDto.getUsername())) {
                return resultDto.makeResult(UCenterResultEnum.VISIT_LOG_USERNAME_EMPTY.getValue(),
                        UCenterResultEnum.VISIT_LOG_USERNAME_EMPTY.getComment());
            }
            VisitLog visitLog = modelMapper.map(requestDto, VisitLog.class);
            EntityWrapper<VisitLog> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            visitLogCrudService.update(visitLog, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            LOG.error("登录日志更新失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取登录日志
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<VisitLogResultDto> getVisitLog(VisitLogGetRequestDto requestDto) {
        ObjectResultDto<VisitLogResultDto> objectResultDto = new ObjectResultDto();
        try {

            VisitLog visitLog = visitLogCrudService.selectById(requestDto.getId());
            if (visitLog != null) {
                VisitLogResultDto resultDto = modelMapper.map(visitLog, VisitLogResultDto.class);
                objectResultDto.setData(resultDto);
            } else {
                return objectResultDto.makeResult(UCenterResultEnum.VISIT_LOG_NOT_FOUND.getValue(),
                        UCenterResultEnum.VISIT_LOG_NOT_FOUND.getComment());
            }
            objectResultDto.success();
        } catch (Exception e) {
            LOG.error("登录日志获取失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取登录日志列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<VisitLogResultDto> getVisitLogList(VisitLogListGetRequestDto requestDto) {

        ListResultDto<VisitLogResultDto> listResultDto = new ListResultDto();
        try {

            EntityWrapper<VisitLog> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getClientId())) {
                entityWrapper.eq("clientId", requestDto.getClientId());
            }
            if (!StringUtils.isEmpty(requestDto.getClientIp())) {
                entityWrapper.eq("clientIp", requestDto.getClientIp());
            }
            if (!StringUtils.isEmpty(requestDto.getUsername())) {
                entityWrapper.eq("username", requestDto.getUsername());
            }
            if (!StringUtils.isEmpty(requestDto.getAccessToken())) {
                entityWrapper.eq("accessToken", requestDto.getAccessToken());
            }
            if (!StringUtils.isEmpty(requestDto.getRefreshToken())) {
                entityWrapper.eq("refreshToken", requestDto.getRefreshToken());
            }
            if (!StringUtils.isEmpty(requestDto.getSessionId())) {
                entityWrapper.eq("sessionId", requestDto.getSessionId());
            }
            List<VisitLog> visitLogList = visitLogCrudService.selectList(entityWrapper);
            if (visitLogList != null && !visitLogList.isEmpty()) {
                List<VisitLogResultDto> visitLogResultDtoList = modelMapper.map(visitLogList, new TypeToken<List<VisitLogResultDto>>() {
                }.getType());
                listResultDto.setItems(visitLogResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            LOG.error("获取登录日志列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 分页获取登录日志列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<VisitLogResultDto> getVisitLogPagedList(VisitLogQueryPagedResultRequestDto requestDto) {

        PagedResultDto<VisitLogResultDto> pagedResultDto = new PagedResultDto();
        try {

            EntityWrapper<VisitLog> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getClientId())) {
                entityWrapper.eq("clientId", requestDto.getClientId());
            }
            if (!StringUtils.isEmpty(requestDto.getClientIp())) {
                entityWrapper.eq("clientIp", requestDto.getClientIp());
            }
            if (!StringUtils.isEmpty(requestDto.getUsername())) {
                entityWrapper.eq("username", requestDto.getUsername());
            }
            if (!StringUtils.isEmpty(requestDto.getAccessToken())) {
                entityWrapper.eq("accessToken", requestDto.getAccessToken());
            }
            if (!StringUtils.isEmpty(requestDto.getRefreshToken())) {
                entityWrapper.eq("refreshToken", requestDto.getRefreshToken());
            }
            if (!StringUtils.isEmpty(requestDto.getSessionId())) {
                entityWrapper.eq("sessionId", requestDto.getSessionId());
            }
            Page<VisitLog> visitLogPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<VisitLog> visitLogPageList = visitLogCrudService.selectPage(visitLogPage, entityWrapper);
            if (visitLogPageList != null) {

                List<VisitLogResultDto> visitLogResultDtoList = modelMapper.map(visitLogPageList.getRecords(), new TypeToken<List<VisitLogResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(visitLogPage.getCurrent());
                pagedResultDto.setPageSize(visitLogPage.getSize());
                pagedResultDto.setTotalCount(visitLogPage.getTotal());
                pagedResultDto.setItems(visitLogResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            LOG.error("分页获取登录日志列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }
}
