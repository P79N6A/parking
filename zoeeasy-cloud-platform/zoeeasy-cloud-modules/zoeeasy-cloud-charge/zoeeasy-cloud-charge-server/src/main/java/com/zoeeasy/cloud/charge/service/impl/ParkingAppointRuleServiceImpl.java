package com.zoeeasy.cloud.charge.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.charge.appointrule.AppointRuleService;
import com.zoeeasy.cloud.charge.appointrule.ParkingAppointRuleService;
import com.zoeeasy.cloud.charge.appointrule.dto.request.*;
import com.zoeeasy.cloud.charge.appointrule.dto.result.AppointRuleResultDto;
import com.zoeeasy.cloud.charge.appointrule.dto.result.ParkingAppointRuleDetailViewResultDto;
import com.zoeeasy.cloud.charge.appointrule.dto.result.ParkingAppointRuleResultDto;
import com.zoeeasy.cloud.charge.appointrule.dto.result.ParkingAppointRuleViewResultDto;
import com.zoeeasy.cloud.charge.appointrule.validator.ParkingAppointRuleUpdateRequestDtoValidator;
import com.zoeeasy.cloud.charge.domain.AppointmentRuleEntity;
import com.zoeeasy.cloud.charge.domain.ParkingAppointmentRuleEntity;
import com.zoeeasy.cloud.charge.domain.ParkingAppointmentRuleHistoryEntity;
import com.zoeeasy.cloud.charge.enums.ChargeResultEnum;
import com.zoeeasy.cloud.charge.service.AppointmentRuleCrudService;
import com.zoeeasy.cloud.charge.service.ParkingAppointmentRuleCrudService;
import com.zoeeasy.cloud.charge.service.ParkingAppointmentRuleHistoryCrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 停车场预约规则服务
 *
 * @Date: 2018/3/30
 * @author: zwq
 */
@Service(value = "parkingAppointRuleService")
@Slf4j
public class ParkingAppointRuleServiceImpl implements ParkingAppointRuleService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ParkingAppointmentRuleCrudService parkingAppointmentRuleCrudService;

    @Autowired
    private ParkingAppointmentRuleHistoryCrudService parkingAppointmentRuleHistoryCrudService;

    @Autowired
    private AppointRuleService appointRuleService;

    @Autowired
    private AppointmentRuleCrudService appointmentRuleCrudService;

    /**
     * 删除停车场约规则
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto deleteParkingAppointRule(ParkingAppointRuleDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingAppointmentRuleEntity parkingAppointmentRuleEntity = parkingAppointmentRuleCrudService.selectById(requestDto.getId());
            if (null == parkingAppointmentRuleEntity) {
                return resultDto.makeResult(ChargeResultEnum.PARKING_APPOINT_RULE_EMPTY.getValue(), ChargeResultEnum.PARKING_APPOINT_RULE_EMPTY.getComment());
            }
            ParkingAppointmentRuleHistoryEntity parkingAppointmentRuleHistoryEntity = modelMapper.map(parkingAppointmentRuleEntity, ParkingAppointmentRuleHistoryEntity.class);
            parkingAppointmentRuleHistoryEntity.setId(null);
            parkingAppointmentRuleCrudService.deleteById(requestDto.getId());
            parkingAppointmentRuleHistoryCrudService.insert(parkingAppointmentRuleHistoryEntity);
            EntityWrapper<ParkingAppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("ruleId", parkingAppointmentRuleEntity.getRuleId());
            List<ParkingAppointmentRuleEntity> list = parkingAppointmentRuleCrudService.selectList(entityWrapper);
            AppointRuleUpdateUsedRequestDto appointRuleUpdateUsedRequestDto = new AppointRuleUpdateUsedRequestDto();
            appointRuleUpdateUsedRequestDto.setRuleId(parkingAppointmentRuleEntity.getRuleId());
            if (null == list || list.isEmpty()) {
                appointRuleUpdateUsedRequestDto.setUsed(Boolean.FALSE);
            } else {
                appointRuleUpdateUsedRequestDto.setUsed(Boolean.TRUE);
            }
            resultDto = appointRuleService.updateUsed(appointRuleUpdateUsedRequestDto);
            if (resultDto.isFailed()) {
                return resultDto;
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("预约规则关联停车场删除失败" + e.getMessage());
            resultDto.makeResult(ChargeResultEnum.PARKING_APPOINT_RULE_DELETE_ERR.getValue(), ChargeResultEnum.PARKING_APPOINT_RULE_DELETE_ERR.getComment());
        }
        return resultDto;
    }

    /**
     * 根据ID查停车场约规则信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingAppointRuleViewResultDto> getParkingAppointRuleById(ParkingAppointRuleGetRequestDto requestDto) {
        ObjectResultDto<ParkingAppointRuleViewResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingAppointmentRuleEntity ruleToParking = parkingAppointmentRuleCrudService.selectById(requestDto.getId());
            if (null != ruleToParking) {
                ParkingAppointRuleViewResultDto viewResultDto = modelMapper.map(ruleToParking, ParkingAppointRuleViewResultDto.class);
                objectResultDto.setData(viewResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("根据ID查停车场约规则信息失败");
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据停车场查约规则信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ParkingAppointRuleViewResultDto> getAppointRuleListByParkingInfo(ParkingAppointRuleListGetByParkingInfoRequestDto requestDto) {
        ListResultDto<ParkingAppointRuleViewResultDto> resultDto = new ListResultDto<>();
        try {
            EntityWrapper<ParkingAppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            List<ParkingAppointRuleViewResultDto> parkingAppointRuleViewResultDtoList = new ArrayList<>();
            List<ParkingAppointmentRuleEntity> parkingAppointmentRuleEntityList = parkingAppointmentRuleCrudService.selectList(entityWrapper);
            if (CollectionUtils.isNotEmpty(parkingAppointmentRuleEntityList)) {
                for (ParkingAppointmentRuleEntity obj : parkingAppointmentRuleEntityList) {
                    ParkingAppointRuleViewResultDto parkingAppointRuleViewResultDto = new ParkingAppointRuleViewResultDto();
                    AppointRuleGetRequestDto appointRuleGetRequestDto = new AppointRuleGetRequestDto();
                    appointRuleGetRequestDto.setRuleId(obj.getRuleId());
                    ObjectResultDto<AppointRuleResultDto> appointRuleResultDto = appointRuleService.getAppointRuleById(appointRuleGetRequestDto);
                    if (appointRuleResultDto.isSuccess() && null != appointRuleResultDto.getData()) {
                        parkingAppointRuleViewResultDto = modelMapper.map(appointRuleResultDto.getData(), ParkingAppointRuleViewResultDto.class);
                    }
                    modelMapper.map(obj, parkingAppointRuleViewResultDto);
                    if (obj.getOfflineTime().getTime() == DateTimeUtils.getDateMax().getTime()) {
                        parkingAppointRuleViewResultDto.setOfflineTime(null);
                    } else {
                        parkingAppointRuleViewResultDto.setOfflineTime(obj.getOfflineTime());
                    }
                    if (obj.getOnlineTime().getTime() == DateTimeUtils.getDateMin().getTime()) {
                        parkingAppointRuleViewResultDto.setOnlineTime(null);
                    } else {
                        parkingAppointRuleViewResultDto.setOnlineTime(obj.getOnlineTime());
                    }
                    parkingAppointRuleViewResultDtoList.add(parkingAppointRuleViewResultDto);
                }
            }
            resultDto.setItems(parkingAppointRuleViewResultDtoList);
            resultDto.success();
        } catch (Exception e) {
            log.error("获取停车场收费规则失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据停车场查约规则信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<ParkingAppointRuleViewResultDto> getAppointRulePagedListRuleByParkingInfo(ParkingAppointRulePagedListGetByParkingInfoRequestDto requestDto) {
        PagedResultDto<ParkingAppointRuleViewResultDto> resultDto = new PagedResultDto<>();
        try {
            EntityWrapper<ParkingAppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            Page<ParkingAppointmentRuleEntity> parkingChargePage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());

            List<ParkingAppointRuleViewResultDto> parkingDtoList = new ArrayList<>();
            Page<ParkingAppointmentRuleEntity> pageList = parkingAppointmentRuleCrudService.selectPage(parkingChargePage, entityWrapper);
            if (null != pageList && CollectionUtils.isNotEmpty(pageList.getRecords())) {
                for (ParkingAppointmentRuleEntity obj : pageList.getRecords()) {
                    ParkingAppointRuleViewResultDto parkingAppointRuleViewResultDto = new ParkingAppointRuleViewResultDto();
                    AppointRuleGetRequestDto appointRuleGetRequestDto = new AppointRuleGetRequestDto();
                    appointRuleGetRequestDto.setRuleId(obj.getRuleId());
                    ObjectResultDto<AppointRuleResultDto> appointRuleResultDto = appointRuleService.getAppointRuleById(appointRuleGetRequestDto);
                    if (appointRuleResultDto.isSuccess() && null != appointRuleResultDto.getData()) {
                        parkingAppointRuleViewResultDto = modelMapper.map(appointRuleResultDto.getData(), ParkingAppointRuleViewResultDto.class);
                    }
                    modelMapper.map(obj, parkingAppointRuleViewResultDto);
                    if (obj.getOfflineTime().getTime() == DateTimeUtils.getDateMax().getTime()) {
                        parkingAppointRuleViewResultDto.setOfflineTime(null);
                    } else {
                        parkingAppointRuleViewResultDto.setOfflineTime(obj.getOfflineTime());
                    }
                    if (obj.getOnlineTime().getTime() == DateTimeUtils.getDateMin().getTime()) {
                        parkingAppointRuleViewResultDto.setOnlineTime(null);
                    } else {
                        parkingAppointRuleViewResultDto.setOnlineTime(obj.getOnlineTime());
                    }
                    parkingDtoList.add(parkingAppointRuleViewResultDto);
                }
                resultDto.setPageNo(pageList.getCurrent());
                resultDto.setPageSize(pageList.getSize());
                resultDto.setTotalCount(pageList.getTotal());
                resultDto.setItems(parkingDtoList);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取停车场收费规则失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 查询停车场预约规则
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ParkingAppointRuleResultDto> getAppointRuleListByParkingInfo(ParkingAppointRuleQueryRequestDto requestDto) {
        ListResultDto<ParkingAppointRuleResultDto> listResultDto = new ListResultDto<>();
        try {
            List<ParkingAppointRuleResultDto> list = new ArrayList<>();
            EntityWrapper<ParkingAppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            List<ParkingAppointmentRuleEntity> parkingAppointmentRuleEntityList = parkingAppointmentRuleCrudService.selectList(entityWrapper);
            if (CollectionUtils.isNotEmpty(parkingAppointmentRuleEntityList)) {
                for (ParkingAppointmentRuleEntity obj : parkingAppointmentRuleEntityList) {
                    ParkingAppointRuleResultDto parkingAppointRuleResultDto = new ParkingAppointRuleResultDto();
                    AppointRuleGetRequestDto appointRuleGetRequestDto = new AppointRuleGetRequestDto();
                    appointRuleGetRequestDto.setRuleId(obj.getRuleId());
                    ObjectResultDto<AppointRuleResultDto> appointRuleResultDto = appointRuleService.getAppointRuleById(appointRuleGetRequestDto);
                    if (appointRuleResultDto.isSuccess() && null != appointRuleResultDto.getData()) {
                        parkingAppointRuleResultDto = modelMapper.map(appointRuleResultDto.getData(), ParkingAppointRuleResultDto.class);
                    }
                    if (obj.getOfflineTime().getTime() == DateTimeUtils.getDateMax().getTime()) {
                        parkingAppointRuleResultDto.setOfflineTime(null);
                    } else {
                        parkingAppointRuleResultDto.setOfflineTime(obj.getOfflineTime());
                    }
                    if (obj.getOnlineTime().getTime() == DateTimeUtils.getDateMin().getTime()) {
                        parkingAppointRuleResultDto.setOnlineTime(null);
                    } else {
                        parkingAppointRuleResultDto.setOnlineTime(obj.getOnlineTime());
                    }
                    parkingAppointRuleResultDto.setId(obj.getId());
                    /* parkingAppointRuleResultDto.setStatus(obj.getStatus());*/
                    list.add(parkingAppointRuleResultDto);
                }
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("停车场该时间段对应预约规则查询失败" + e.getMessage());
            listResultDto.makeResult(ChargeResultEnum.APPOINT_RULE_QUERY_ERR.getValue(), ChargeResultEnum.APPOINT_RULE_QUERY_ERR.getComment());
        }
        return listResultDto;
    }

    /**
     * 通过时间查询停车场对应预约规则详情
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingAppointRuleDetailViewResultDto> getAppointRuleTotalLByAppointTime(ParkingAppointRuleGetByTimeRequestDto requestDto) {
        ObjectResultDto<ParkingAppointRuleDetailViewResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            //获取预约时段内停车场有效的预约规则
            EntityWrapper<ParkingAppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            entityWrapper.le("onlineTime", DateTimeUtils.getDayStart(requestDto.getAppointTime()));
            entityWrapper.gt("offlineTime", DateTimeUtils.getDayEnd(requestDto.getAppointTime()));
            List<ParkingAppointmentRuleEntity> list = parkingAppointmentRuleCrudService.selectParkingAppointRuleList(entityWrapper);
            ParkingAppointRuleDetailViewResultDto parkingAppointRuleDetailViewResultDto = null;
            if (CollectionUtils.isNotEmpty(list)) {
                ParkingAppointmentRuleEntity parkingAppointmentRuleEntity = list.get(0);
                AppointRuleGetRequestDto appointRuleGetRequestDto = new AppointRuleGetRequestDto();
                appointRuleGetRequestDto.setRuleId(parkingAppointmentRuleEntity.getRuleId());
                ObjectResultDto<AppointRuleResultDto> appointRuleResultDto = appointRuleService.getAppointRuleByIdNoTalentId(appointRuleGetRequestDto);

                if (appointRuleResultDto.isFailed() || null == appointRuleResultDto.getData()) {
                    return objectResultDto.makeResult(ChargeResultEnum.APPOINT_RULE_NOT_EXISTS.getValue(), ChargeResultEnum.APPOINT_RULE_NOT_EXISTS.getComment());
                }
                parkingAppointRuleDetailViewResultDto = modelMapper.map(appointRuleResultDto.getData(), ParkingAppointRuleDetailViewResultDto.class);
                if (null == parkingAppointRuleDetailViewResultDto.getCountAppointPrice()) {
                    parkingAppointRuleDetailViewResultDto.setCountAppointPrice(0);
                }
            }
            if (null == parkingAppointRuleDetailViewResultDto) {
                return objectResultDto.makeResult(ChargeResultEnum.APPOINT_RULE_NOT_EXISTS.getValue(), ChargeResultEnum.APPOINT_RULE_NOT_EXISTS.getComment());
            }
            objectResultDto.setData(parkingAppointRuleDetailViewResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("查询停车场对应时间预约规则失败" + e.getMessage());
            objectResultDto.makeResult(ChargeResultEnum.APPOINTRULE_GET_ERR.getValue(), ChargeResultEnum.APPOINTRULE_GET_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 维护停车场预约规则
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto maintainParkingAppointRule(@FluentValid(ParkingAppointRuleUpdateRequestDtoValidator.class) ParkingAppointRuleUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            List<ParkingAppointRuleItemUpdateRequestDto> ruleList = requestDto.getRules();
            for (int i = 0; i < ruleList.size(); i++) {
                ParkingAppointRuleItemUpdateRequestDto ruleItem = ruleList.get(i);
                if (null == ruleItem.getOnlineTime()) {
                    return resultDto.makeResult(ChargeResultEnum.STARTTIME_EMPTY.getValue(), ChargeResultEnum.STARTTIME_EMPTY.getComment());
                }
                if (null == ruleItem.getRuleId()) {
                    return resultDto.makeResult(ChargeResultEnum.APPOINT_RULEID_EMPTY.getValue(), ChargeResultEnum.APPOINT_RULEID_EMPTY.getComment());
                }
                if (null == ruleItem.getOfflineTime()) {
                    ruleItem.setOfflineTime(DateTimeUtils.getDateMax());
                }
                AppointRuleGetRequestDto appointRuleGetRequestDto = new AppointRuleGetRequestDto();
                appointRuleGetRequestDto.setRuleId(ruleItem.getRuleId());
                ObjectResultDto<AppointRuleResultDto> appointRuleResultDto = appointRuleService.getAppointRuleById(appointRuleGetRequestDto);
                if (appointRuleResultDto.isFailed() || null == appointRuleResultDto.getData()) {
                    return resultDto.makeResult(ChargeResultEnum.APPOINTRULE_NOT_FOUND.getValue(), ChargeResultEnum.APPOINTRULE_NOT_FOUND.getComment());
                }
                ParkingAppointmentRuleEntity parkingAppointmentRuleEntity = new ParkingAppointmentRuleEntity();
                //如果是新增规则，规则有效日期不能重合
                if (null == ruleItem.getId()) {
                    //新增规则日期不能小于当天
                    if (ruleItem.getOnlineTime().before(DateUtils.parseDate(DateUtils.getDate()))) {
                        return resultDto.makeResult(ChargeResultEnum.APPOINT_RULE_ONLINE_INVALID.getValue(), ChargeResultEnum.APPOINT_RULE_ONLINE_INVALID.getComment());
                    }
                    EntityWrapper<ParkingAppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
                    entityWrapper.eq("parkingId", requestDto.getParkingId());
                    entityWrapper.le("onlineTime", ruleItem.getOfflineTime());
                    entityWrapper.ge("offlineTime", ruleItem.getOnlineTime());
                    //entityWrapper.eq("status", ParkingAppointRuleStatusEnum.ON_LINE.getValue());
                    List<ParkingAppointmentRuleEntity> list = parkingAppointmentRuleCrudService.selectList(entityWrapper);
                    if (CollectionUtils.isNotEmpty(list)) {
                        return resultDto.makeResult(ChargeResultEnum.PARKING_APPOINT_RULE_EXISTS.getValue(), ChargeResultEnum.PARKING_APPOINT_RULE_EXISTS.getComment());
                    }
                    parkingAppointmentRuleEntity.setParkingId(requestDto.getParkingId());
                    parkingAppointmentRuleEntity.setRuleId(ruleItem.getRuleId());
                    parkingAppointmentRuleEntity.setOnlineTime(ruleItem.getOnlineTime());
                    parkingAppointmentRuleEntity.setOfflineTime(ruleItem.getOfflineTime());
                    /*  parkingAppointmentRule.setStatus(ParkingAppointRuleStatusEnum.ON_LINE.getValue());*/
                    parkingAppointmentRuleCrudService.insert(parkingAppointmentRuleEntity);

                } else {

                    ParkingAppointmentRuleEntity obj = parkingAppointmentRuleCrudService.selectById(ruleItem.getId());
                    if (null == obj) {
                        return resultDto.makeResult(ChargeResultEnum.PARKING_APPOINT_RULE_EMPTY.getValue(), ChargeResultEnum.PARKING_APPOINT_RULE_EMPTY.getComment());
                    }
                    EntityWrapper<ParkingAppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
                    entityWrapper.eq("parkingId", requestDto.getParkingId());
                    entityWrapper.le("onlineTime", ruleItem.getOfflineTime());
                    entityWrapper.ge("offlineTime", ruleItem.getOnlineTime());
                    //entityWrapper.eq("status", ParkingAppointRuleStatusEnum.ON_LINE.getValue());
                    entityWrapper.ne("id", ruleItem.getId());
                    List<ParkingAppointmentRuleEntity> list = parkingAppointmentRuleCrudService.selectList(entityWrapper);
                    if (CollectionUtils.isNotEmpty(list)) {
                        return resultDto.makeResult(ChargeResultEnum.PARKING_APPOINT_RULE_EXISTS.getValue(), ChargeResultEnum.PARKING_APPOINT_RULE_EXISTS.getComment());
                    }
                    parkingAppointmentRuleEntity.setRuleId(ruleItem.getRuleId());
                    parkingAppointmentRuleEntity.setOnlineTime(ruleItem.getOnlineTime());
                    parkingAppointmentRuleEntity.setOfflineTime(ruleItem.getOfflineTime());
                    EntityWrapper<ParkingAppointmentRuleEntity> entity = new EntityWrapper<>();
                    entity.eq("id", ruleItem.getId());
                    parkingAppointmentRuleCrudService.update(parkingAppointmentRuleEntity, entity);
                }
                EntityWrapper<ParkingAppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("ruleId", ruleItem.getRuleId());
                List<ParkingAppointmentRuleEntity> list = parkingAppointmentRuleCrudService.selectList(entityWrapper);
                AppointRuleUpdateUsedRequestDto appointRuleUpdateUsedRequestDto = new AppointRuleUpdateUsedRequestDto();
                appointRuleUpdateUsedRequestDto.setRuleId(ruleItem.getRuleId());
                if (null == list || list.isEmpty()) {
                    appointRuleUpdateUsedRequestDto.setUsed(Boolean.FALSE);
                } else {
                    appointRuleUpdateUsedRequestDto.setUsed(Boolean.TRUE);
                }
                resultDto = appointRuleService.updateUsed(appointRuleUpdateUsedRequestDto);
                if (resultDto.isFailed()) {
                    return resultDto;
                }
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("停车场维护预约规则失败" + e.getMessage());
            resultDto.makeResult(ChargeResultEnum.PARKING_APPOINT_RULE_OPERERR.getValue(), ChargeResultEnum.PARKING_APPOINT_RULE_OPERERR.getComment());
        }
        return resultDto;
    }

    /**
     * 根据上线下线时间及停车场Id获取
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingAppointRuleViewResultDto> getParkingAppointRule(ParkingAppointRuleGetByVaildTimeRequestDto requestDto) {
        ObjectResultDto<ParkingAppointRuleViewResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<ParkingAppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            entityWrapper.le("onlineTime", requestDto.getOnlineTime());
            entityWrapper.ge("offlineTime", requestDto.getOfflineTime());
            entityWrapper.last("LIMIT 1");
            ParkingAppointmentRuleEntity parkingAppointmentRuleEntity = parkingAppointmentRuleCrudService.selectParkingAppointRule(entityWrapper);
            if (null != parkingAppointmentRuleEntity) {
                ParkingAppointRuleViewResultDto viewResultDto = modelMapper.map(parkingAppointmentRuleEntity, ParkingAppointRuleViewResultDto.class);
                objectResultDto.setData(viewResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("根据上线下线时间及停车场Id获取停车场约规则信息失败");
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据停车场删除关联的预约规则
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteParkingAppointRuleByParkingId(ParkingAppointRuleDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<ParkingAppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getId());
            List<ParkingAppointmentRuleEntity> parkingAppointmentRuleEntities = parkingAppointmentRuleCrudService.selectList(entityWrapper);
            for (ParkingAppointmentRuleEntity parkingAppointmentRuleEntity : parkingAppointmentRuleEntities) {
                EntityWrapper<ParkingAppointmentRuleEntity> wrapper = new EntityWrapper<>();
                wrapper.eq("ruleId", parkingAppointmentRuleEntity.getRuleId());
                int count = parkingAppointmentRuleCrudService.selectCount(wrapper);
                if (count <= 1) {
                    AppointmentRuleEntity appointmentRuleEntity = new AppointmentRuleEntity();
                    appointmentRuleEntity.setUsed(Boolean.FALSE);
                    EntityWrapper<AppointmentRuleEntity> appointmentRuleEntityWrapper = new EntityWrapper<>();
                    appointmentRuleEntityWrapper.eq("id", parkingAppointmentRuleEntity.getRuleId());
                    appointmentRuleCrudService.update(appointmentRuleEntity, appointmentRuleEntityWrapper);
                }
                parkingAppointmentRuleCrudService.deleteById(parkingAppointmentRuleEntity.getId());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("根据停车场删除关联的预约规则失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据停车场删除关联的预约规则(无租户)
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto managementDeleteParkingAppointRuleByParkingId(ParkingAppointRuleDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<ParkingAppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getId());
            List<ParkingAppointmentRuleEntity> parkingAppointmentRuleEntities = parkingAppointmentRuleCrudService.selectParkingAppointRuleList(entityWrapper);
            for (ParkingAppointmentRuleEntity parkingAppointmentRuleEntity : parkingAppointmentRuleEntities) {
                int count = parkingAppointmentRuleCrudService.selectParkingAppointRuleCount(parkingAppointmentRuleEntity.getRuleId());
                if (count <= 1) {
                    AppointmentRuleEntity appointmentRuleEntity = new AppointmentRuleEntity();
                    appointmentRuleEntity.setUsed(Boolean.FALSE);
                    appointmentRuleEntity.setId(parkingAppointmentRuleEntity.getRuleId());
                    appointmentRuleCrudService.updateAppointRule(appointmentRuleEntity);
                }
            }
            parkingAppointmentRuleCrudService.deleteParkingAppointRuleNonTenant(requestDto.getId());
            resultDto.success();
        } catch (Exception e) {
            log.error("根据停车场删除关联的预约规则(无租户)失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
