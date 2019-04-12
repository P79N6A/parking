package com.zoeeasy.cloud.charge.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.charge.appointrule.AppointRuleService;
import com.zoeeasy.cloud.charge.appointrule.dto.request.*;
import com.zoeeasy.cloud.charge.appointrule.dto.result.AppointRuleResultDto;
import com.zoeeasy.cloud.charge.appointrule.validator.AppointRuleAddDtoValidator;
import com.zoeeasy.cloud.charge.appointrule.validator.AppointRuleUpdateDtoValidator;
import com.zoeeasy.cloud.charge.domain.AppointmentRuleEntity;
import com.zoeeasy.cloud.charge.enums.ChargeResultEnum;
import com.zoeeasy.cloud.charge.enums.ChargeTypeEnum;
import com.zoeeasy.cloud.charge.service.AppointmentRuleCrudService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 预约规则
 *
 * @Date: 2018/3/30
 * @author: zwq
 */
@Service("appointRuleService")
@Slf4j
public class AppointRuleServiceImpl implements AppointRuleService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AppointmentRuleCrudService appointmentRuleCrudService;

    /**
     * 新增
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addAppointRule(@FluentValid({AppointRuleAddDtoValidator.class}) AppointRuleAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<AppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("ruleCode", requestDto.getRuleCode());
            AppointmentRuleEntity appointmentRuleEntity = appointmentRuleCrudService.selectOne(entityWrapper);
            if (null == appointmentRuleEntity) {
                appointmentRuleEntity = modelMapper.map(requestDto, AppointmentRuleEntity.class);
                if (requestDto.getChargeType().equals(ChargeTypeEnum.GIST_TO_DATE.getValue())) {
                    appointmentRuleEntity.setUnitPrice(requestDto.getChargePrice());
                } else if (requestDto.getChargeType().equals(ChargeTypeEnum.GIST_TO_TIMES.getValue())) {
                    appointmentRuleEntity.setCountAppointPrice(requestDto.getChargePrice());
                } else {
                    return resultDto.makeResult(ChargeResultEnum.CHARGETYPE_ERR.getValue(),
                            ChargeResultEnum.CHARGETYPE_ERR.getComment());
                }
                appointmentRuleEntity.setFee(requestDto.getFee());
                appointmentRuleEntity.setPayLimit(requestDto.getPayLimit());
            } else {
                return resultDto.makeResult(ChargeResultEnum.APPOINT_RULECODE_EXISTS.getValue(),
                        ChargeResultEnum.APPOINT_RULECODE_EXISTS.getComment());
            }
            appointmentRuleCrudService.insert(appointmentRuleEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("预约规则添加失败" + e.getMessage());
            resultDto.makeResult(ChargeResultEnum.APPOINTRULE_ADD_ERR.getValue(), ChargeResultEnum.APPOINTRULE_ADD_ERR.getComment());
        }
        return resultDto;
    }

    /**
     * id获取
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<AppointRuleResultDto> getAppointRule(AppointRuleGetRequestDto requestDto) {
        ObjectResultDto<AppointRuleResultDto> objectResultDto = new ObjectResultDto();
        try {
            AppointmentRuleEntity appointmentRuleEntity = appointmentRuleCrudService.selectById(requestDto.getRuleId());
            if (appointmentRuleEntity != null) {
                AppointRuleResultDto resultDto = modelMapper.map(appointmentRuleEntity, AppointRuleResultDto.class);
                if (resultDto.getChargeType().equals(ChargeTypeEnum.GIST_TO_DATE.getValue())) {
                    resultDto.setChargePrice(appointmentRuleEntity.getUnitPrice());
                } else if (resultDto.getChargeType().equals(ChargeTypeEnum.GIST_TO_TIMES.getValue())) {
                    resultDto.setChargePrice(appointmentRuleEntity.getCountAppointPrice());
                }
                objectResultDto.setData(resultDto);
            } else {
                return objectResultDto.makeResult(ChargeResultEnum.APPOINTRULE_NOT_FOUND.getValue(),
                        ChargeResultEnum.APPOINTRULE_NOT_FOUND.getComment());
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("停车场预定规则获取失败" + e.getMessage());
            objectResultDto.makeResult(ChargeResultEnum.APPOINTRULE_GET_ERR.getValue(),
                    ChargeResultEnum.APPOINTRULE_GET_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 更新
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateAppointRule(@FluentValid({AppointRuleUpdateDtoValidator.class}) AppointRuleUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            AppointmentRuleEntity appointmentRuleEntity = appointmentRuleCrudService.selectById(requestDto.getId());
            if (null == appointmentRuleEntity) {
                return resultDto.makeResult(ChargeResultEnum.APPOINTRULE_NOT_FOUND.getValue(),
                        ChargeResultEnum.APPOINTRULE_NOT_FOUND.getComment());
            }
            if (appointmentRuleEntity.getUsed()) {
                return resultDto.makeResult(ChargeResultEnum.APPOINT_RULE_IN_USED.getValue(),
                        ChargeResultEnum.APPOINT_RULE_IN_USED.getComment());
            }
            AppointmentRuleEntity ruleUpdate = modelMapper.map(requestDto, AppointmentRuleEntity.class);
            if (null != requestDto.getFee()) {
                ruleUpdate.setFee(requestDto.getFee());
            }
            if (null != requestDto.getChargePrice()) {
                if (null == ruleUpdate.getChargeType()) {
                    if (appointmentRuleEntity.getChargeType().equals(ChargeTypeEnum.GIST_TO_DATE.getValue())) {
                        ruleUpdate.setUnitPrice(requestDto.getChargePrice());
                    } else {
                        ruleUpdate.setCountAppointPrice(requestDto.getChargePrice());
                    }
                } else {
                    if (ruleUpdate.getChargeType().equals(ChargeTypeEnum.GIST_TO_DATE.getValue())) {
                        ruleUpdate.setUnitPrice(requestDto.getChargePrice());
                    } else if (ruleUpdate.getChargeType().equals(ChargeTypeEnum.GIST_TO_TIMES.getValue())) {
                        ruleUpdate.setCountAppointPrice(requestDto.getChargePrice());
                    } else {
                        return resultDto.makeResult(ChargeResultEnum.CHARGETYPE_ERR.getValue(),
                                ChargeResultEnum.CHARGETYPE_ERR.getComment());
                    }
                }
            }

            ruleUpdate.setRuleCode(null);
            ruleUpdate.setRuleName(null);
            ruleUpdate.setStartTime(null);
            ruleUpdate.setEndTime(null);
            EntityWrapper<AppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            appointmentRuleCrudService.update(ruleUpdate, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("预约规则更新失败" + e.getMessage());
            resultDto.makeResult(ChargeResultEnum.APPOINTRULE_UPDATE_ERR.getValue(),
                    ChargeResultEnum.APPOINTRULE_UPDATE_ERR.getComment());
        }
        return resultDto;
    }

    /**
     * 删除
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteAppointRule(AppointRuleDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            AppointmentRuleEntity appointmentRuleEntity = appointmentRuleCrudService.selectById(requestDto.getId());
            if (null == appointmentRuleEntity) {
                return resultDto.makeResult(ChargeResultEnum.APPOINTRULE_NOT_FOUND.getValue(),
                        ChargeResultEnum.APPOINTRULE_NOT_FOUND.getComment());
            }
            if (appointmentRuleEntity.getUsed()) {
                return resultDto.makeResult(ChargeResultEnum.APPOINT_RULE_IN_USED.getValue(),
                        ChargeResultEnum.APPOINT_RULE_IN_USED.getComment());
            }
            appointmentRuleCrudService.deleteById(requestDto.getId());
            resultDto.success();
        } catch (Exception e) {
            log.error("预约规则删除失败" + e.getMessage());
            resultDto.makeResult(ChargeResultEnum.APPOINTRULE_DELETE_ERR.getValue(),
                    ChargeResultEnum.APPOINTRULE_DELETE_ERR.getComment());
        }
        return resultDto;
    }

    /**
     * 获取预定规则列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<AppointRuleResultDto> getAppointRuleList(AppointRuleGetListRequestDto requestDto) {
        ListResultDto<AppointRuleResultDto> listResultDto = new ListResultDto();
        try {
            EntityWrapper<AppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getRuleName())) {
                entityWrapper.like("ruleName", requestDto.getRuleName());
            }
            List<AppointmentRuleEntity> list = appointmentRuleCrudService.selectList(entityWrapper);
            if (!list.isEmpty()) {
                List<AppointRuleResultDto> appointRuleResultDtoList = modelMapper.map(list, new TypeToken<List<AppointRuleResultDto>>() {
                }.getType());
                listResultDto.setItems(appointRuleResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("预约规则列表获取失败" + e.getMessage());
            listResultDto.makeResult(ChargeResultEnum.APPOINT_RULELIST_GET_ERR.getValue(),
                    ChargeResultEnum.APPOINT_RULELIST_GET_ERR.getComment());
        }
        return listResultDto;
    }

    /**
     * 分页获取列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<AppointRuleResultDto> getAppointRulePageList(AppointRuleGetPageListRequestDto requestDto) {
        PagedResultDto<AppointRuleResultDto> pagedResultDto = new PagedResultDto();
        try {

            EntityWrapper<AppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getRuleName())) {
                entityWrapper.like("ruleName", requestDto.getRuleName());
            }
            if (!StringUtils.isEmpty(requestDto.getRuleCode())) {
                entityWrapper.like("ruleCode", requestDto.getRuleCode());
            }
            if (null != requestDto.getFee()) {
                entityWrapper.eq("fee", requestDto.getFee());
            }
            if (null != requestDto.getChargeType()) {
                entityWrapper.eq("chargeType", requestDto.getChargeType());
            }
            if (null != requestDto.getUsed()) {
                entityWrapper.eq("used", requestDto.getUsed());
            }
            if (null != requestDto.getChargePrice()) {
                if (null != requestDto.getChargeType() && requestDto.getChargeType().equals(ChargeTypeEnum.GIST_TO_DATE.getValue())) {
                    entityWrapper.eq("unitPrice", requestDto.getChargePrice());
                } else if (null != requestDto.getChargeType() && requestDto.getChargeType().equals(ChargeTypeEnum.GIST_TO_TIMES.getValue())) {
                    entityWrapper.eq("countAppointPrice", requestDto.getChargePrice());
                } else {
                    entityWrapper.andNew("unitPrice=" + requestDto.getChargePrice(), 0);
                    entityWrapper.or("countAppointPrice=" + requestDto.getChargePrice(), 1);
                }
            }
            entityWrapper.orderBy("creationTime", false);
            Page<AppointmentRuleEntity> appointmentRulePage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<AppointmentRuleEntity> appointmentRulePageList = appointmentRuleCrudService.selectPage(appointmentRulePage, entityWrapper);
            if (appointmentRulePageList != null) {

                List<AppointRuleResultDto> appointRuleResultDtoList = modelMapper.map(appointmentRulePageList.getRecords(), new TypeToken<List<AppointRuleResultDto>>() {
                }.getType());
                for (AppointRuleResultDto obj : appointRuleResultDtoList) {
                    if (obj.getChargeType().equals(ChargeTypeEnum.GIST_TO_DATE.getValue())) {
                        obj.setChargePrice(obj.getUnitPrice());
                    } else if (obj.getChargeType().equals(ChargeTypeEnum.GIST_TO_TIMES.getValue())) {
                        obj.setChargePrice(obj.getCountAppointPrice());
                    }
                }
                pagedResultDto.setPageNo(appointmentRulePageList.getCurrent());
                pagedResultDto.setPageSize(appointmentRulePageList.getSize());
                pagedResultDto.setTotalCount(appointmentRulePageList.getTotal());
                pagedResultDto.setItems(appointRuleResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取预约规则列表失败" + e.getMessage());
            pagedResultDto.makeResult(ChargeResultEnum.APPOINT_RULELIST_PAGEGET_ERR.getValue(),
                    ChargeResultEnum.APPOINT_RULELIST_PAGEGET_ERR.getComment());
        }
        return pagedResultDto;
    }

    /**
     * 根据id获取预约规则(查询)
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<AppointRuleResultDto> getAppointRuleById(AppointRuleGetRequestDto requestDto) {
        ObjectResultDto<AppointRuleResultDto> objectResultDto = new ObjectResultDto();
        try {
            AppointmentRuleEntity appointmentRuleEntity = appointmentRuleCrudService.selectById(requestDto.getRuleId());
            if (null != appointmentRuleEntity) {
                AppointRuleResultDto resultDto = modelMapper.map(appointmentRuleEntity, AppointRuleResultDto.class);
                objectResultDto.setData(resultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("根据id获取停车场预定规则获取失败" + e.getMessage());
            objectResultDto.makeResult(ChargeResultEnum.APPOINTRULE_GET_ERR.getValue(),
                    ChargeResultEnum.APPOINTRULE_GET_ERR.getComment());
        }
        return objectResultDto;
    }

    /**
     * 更新预约规则关联状态
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateUsed(AppointRuleUpdateUsedRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<AppointmentRuleEntity> entity = new EntityWrapper<>();
            entity.eq("id", requestDto.getRuleId());
            AppointmentRuleEntity appointmentRuleEntity = new AppointmentRuleEntity();
            appointmentRuleEntity.setUsed(requestDto.getUsed());
            appointmentRuleCrudService.update(appointmentRuleEntity, entity);
            resultDto.success();
        } catch (Exception e) {
            log.error("更新预约规则关联状态失败" + e.getMessage());
            resultDto.makeResult(ChargeResultEnum.APPOINTRULE_UPDATE_ERR.getValue(),
                    ChargeResultEnum.APPOINTRULE_UPDATE_ERR.getComment());
        }
        return resultDto;
    }

    /**
     * 根据id获取预约规则(查询)
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<AppointRuleResultDto> getAppointRuleByIdNoTalentId(AppointRuleGetRequestDto requestDto) {
        ObjectResultDto<AppointRuleResultDto> objectResultDto = new ObjectResultDto();
        try {
            AppointmentRuleEntity appointmentRuleEntity = appointmentRuleCrudService.selectAppointRuleById(requestDto.getRuleId());
            if (null != appointmentRuleEntity) {
                AppointRuleResultDto resultDto = modelMapper.map(appointmentRuleEntity, AppointRuleResultDto.class);
                objectResultDto.setData(resultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("停无租户车场预定规则获取失败" + e.getMessage());
            objectResultDto.makeResult(ChargeResultEnum.APPOINTRULE_GET_ERR.getValue(),
                    ChargeResultEnum.APPOINTRULE_GET_ERR.getComment());
        }
        return objectResultDto;
    }
}
