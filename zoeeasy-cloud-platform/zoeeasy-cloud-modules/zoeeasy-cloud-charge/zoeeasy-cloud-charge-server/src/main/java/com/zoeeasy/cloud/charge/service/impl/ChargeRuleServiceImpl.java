package com.zoeeasy.cloud.charge.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.charge.chg.ChargeRuleService;
import com.zoeeasy.cloud.charge.chg.dto.request.*;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleNonTenantResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleTimeResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleTotalResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleViewResultDto;
import com.zoeeasy.cloud.charge.chg.validator.ChargeRuleAddRequestDtoValidator;
import com.zoeeasy.cloud.charge.chg.validator.ChargeRuleGetPageListRequestDtoValidator;
import com.zoeeasy.cloud.charge.chg.validator.ChargeRuleUpdateRequestDtoValidator;
import com.zoeeasy.cloud.charge.domain.ChargeRuleEntity;
import com.zoeeasy.cloud.charge.domain.ChargeRuleTimeEntity;
import com.zoeeasy.cloud.charge.domain.ParkingChargeRuleEntity;
import com.zoeeasy.cloud.charge.enums.ChargeResultEnum;
import com.zoeeasy.cloud.charge.enums.ChargeRuleTypeEnum;
import com.zoeeasy.cloud.charge.service.ChargeRuleCrudService;
import com.zoeeasy.cloud.charge.service.ChargeRuleTimeCrudService;
import com.zoeeasy.cloud.charge.service.ParkingChargeRuleCrudService;
import com.zoeeasy.cloud.core.cst.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 收费规则服务实现类
 * @Date: 2018/1/26 0026
 * @author: AkeemSuper
 */
@Service(value = "chargeRuleService")
@Slf4j
public class ChargeRuleServiceImpl implements ChargeRuleService {

    @Autowired
    private ChargeRuleCrudService chargeRuleCrudService;

    @Autowired
    private ChargeRuleTimeCrudService chargeRuleTimeCrudService;

    @Autowired
    private ParkingChargeRuleCrudService parkingChargeRuleCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 新增收费规则
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto addChargeRule(@FluentValid({ChargeRuleAddRequestDtoValidator.class}) ChargeRuleAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ChargeRuleEntity chargeRuleEntity = chargeRuleCrudService.selectByCode(requestDto.getCode());
            if (chargeRuleEntity != null) {
                return resultDto.makeResult(ChargeResultEnum.CHARGE_RULE_CODE_EXIST.getValue(), ChargeResultEnum.CHARGE_RULE_CODE_EXIST.getComment());
            }
            ChargeRuleEntity chargeRule = modelMapper.map(requestDto, ChargeRuleEntity.class);
            chargeRule.setCode(requestDto.getCode());
            boolean insert = chargeRuleCrudService.insert(chargeRule);
            if (!insert) {
                resultDto.failed();
                return resultDto;
            }
            if (!requestDto.getPartTimes().isEmpty()) {
                for (ChargeRuleTimeAddRequestDto chargeRuleTimeAddRequestDto : requestDto.getPartTimes()) {
                    ChargeRuleTimeEntity chargeRuleTime = modelMapper.map(chargeRuleTimeAddRequestDto, ChargeRuleTimeEntity.class);
                    chargeRuleTime.setRuleId(chargeRule.getId());
                    chargeRuleTimeCrudService.insert(chargeRuleTime);
                }
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("收费规则添加失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据ID获取收费规则
     */
    @Override
    public ObjectResultDto<ChargeRuleViewResultDto> getChargeRule(ChargeRuleGetRequestDto requestDto) {
        ObjectResultDto<ChargeRuleViewResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            if (null == requestDto.getId()) {
                return objectResultDto.makeResult(ChargeResultEnum.CHARGE_RULE_ID_EMPTY.getValue(), ChargeResultEnum.CHARGE_RULE_ID_EMPTY.getComment());
            }
            ChargeRuleEntity chargingRule = chargeRuleCrudService.selectById(requestDto.getId());
            if (chargingRule != null) {
                ChargeRuleViewResultDto chargeRuleResultDto = modelMapper.map(chargingRule, ChargeRuleViewResultDto.class);
                chargeRuleResultDto.setPerPrice(chargingRule.getPerPrice());
                chargeRuleResultDto.setDayTopAmount(chargingRule.getDayTopAmount());
                chargeRuleResultDto.setTopAmount(chargingRule.getTopAmount());
                //计时获取分时段
                if ((chargingRule.getRuleType().compareTo(ChargeRuleTypeEnum.GIST_TO_DATE.getValue()) == 0)
                        || chargingRule.getRuleType().compareTo(ChargeRuleTypeEnum.GIST_TO_DATE_TIMES.getValue()) == 0) {

                    List<ChargeRuleTimeEntity> chargeRuleTimeList = chargeRuleTimeCrudService.selectByRuleId(chargingRule.getId());
                    ArrayList<ChargeRuleTimeResultDto> chargeRuleTimeResultDtos = new ArrayList<>();
                    if (CollectionUtils.isNotEmpty(chargeRuleTimeList)) {
                        for (ChargeRuleTimeEntity chargeRuleTimeEntity : chargeRuleTimeList) {
                            ChargeRuleTimeResultDto ruleTimeResultDto = modelMapper.map(chargeRuleTimeEntity, ChargeRuleTimeResultDto.class);
                            if (chargeRuleTimeEntity.getPrice() != null) {
                                ruleTimeResultDto.setPrice(chargeRuleTimeEntity.getPrice());
                            }
                            chargeRuleTimeResultDtos.add(ruleTimeResultDto);
                        }
                    }
                    chargeRuleResultDto.setPartTimes(chargeRuleTimeResultDtos);
                }
                objectResultDto.setData(chargeRuleResultDto);
                objectResultDto.success();
            } else {
                objectResultDto.makeResult(ChargeResultEnum.CHARGE_RULE_NOT_EXIST.getValue(),
                        ChargeResultEnum.CHARGE_RULE_NOT_EXIST.getComment());
            }
        } catch (Exception e) {
            log.error("根据ID获取收费规则失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 修改收费规则
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateChargeRule(@FluentValid({ChargeRuleUpdateRequestDtoValidator.class}) ChargeRuleUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Long ruleId = requestDto.getId();
            ChargeRuleEntity queryResult = chargeRuleCrudService.selectById(ruleId);
            if (queryResult == null) {
                return resultDto.makeResult(ChargeResultEnum.CHARGE_RULE_NOT_EXIST.getValue(),
                        ChargeResultEnum.CHARGE_RULE_NOT_EXIST.getComment());
            }
            if (!queryResult.getRuleType().equals(requestDto.getRuleType())) {
                return resultDto.makeResult(ChargeResultEnum.CHARGE_RULE_TYPE_NOT_MODIFY.getValue(),
                        ChargeResultEnum.CHARGE_RULE_TYPE_NOT_MODIFY.getComment());
            }
            List<ParkingChargeRuleEntity> parkingChargeRuleEntities = parkingChargeRuleCrudService.findListByRuleId(queryResult.getId());
            if (!CollectionUtils.isEmpty(parkingChargeRuleEntities)) {
                return resultDto.makeResult(ChargeResultEnum.CHARGE_RULE_CORRELATION_LIMIT_UPDATE.getValue(), ChargeResultEnum.CHARGE_RULE_CORRELATION_LIMIT_UPDATE.getComment());
            }
            ChargeRuleEntity chargeRule = modelMapper.map(requestDto, ChargeRuleEntity.class);
            chargeRuleCrudService.updateById(chargeRule);
            //需要删除的时段
            if (!StringUtils.isEmpty(requestDto.getDeletePartTimeIds())) {
                String[] ids = requestDto.getDeletePartTimeIds().split(",");
                for (String id : ids) {
                    chargeRuleTimeCrudService.deleteById(Long.parseLong(id));
                }
            }
            if (!CollectionUtils.isEmpty(requestDto.getPartTimes())) {
                for (ChargeRuleTimeUpdateRequestDto chargeRuleTimeUpdateRequestDto : requestDto.getPartTimes()) {

                    ChargeRuleTimeEntity chargeRuleTime = modelMapper.map(chargeRuleTimeUpdateRequestDto, ChargeRuleTimeEntity.class);
                    chargeRuleTime.setRuleId(ruleId);
                    if (chargeRuleTime.getId() == null) {
                        chargeRuleTimeCrudService.insert(chargeRuleTime);
                    } else {
                        chargeRuleTimeCrudService.updateById(chargeRuleTime);
                    }
                }
            } else {
                //删除原有规则关联的时间段
                chargeRuleTimeCrudService.deleteByRuleId(ruleId);
            }
            return resultDto.success();
        } catch (Exception e) {
            log.error("修改收费规则失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页获取收费规则列表
     */
    @Override
    public PagedResultDto<ChargeRuleViewResultDto> getChargeRulePageList(@FluentValid({ChargeRuleGetPageListRequestDtoValidator.class}) ChargeRuleGetPageListRequestDto requestDto) {
        PagedResultDto<ChargeRuleViewResultDto> pagedResultDto = new PagedResultDto<>();
        EntityWrapper<ChargeRuleEntity> entityWrapper = new EntityWrapper<>();
        try {
            if (requestDto.getCarType() != null) {
                entityWrapper.eq("carType", requestDto.getCarType());
            }
            if (requestDto.getParkingLevel() != null) {
                entityWrapper.eq("parkingLevel", requestDto.getParkingLevel());
            }
            if (requestDto.getRuleType() != null) {
                entityWrapper.eq("ruleType", requestDto.getRuleType());
            }
            if (StringUtils.isNotEmpty(requestDto.getRuleName())) {
                entityWrapper.like("name", requestDto.getRuleName());
            }
            entityWrapper.orderBy("creationTime", false);
            Page<ChargeRuleEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ChargeRuleEntity> chargingRulePage = chargeRuleCrudService.selectPage(page, entityWrapper);
            if (chargingRulePage != null && CollectionUtils.isNotEmpty(chargingRulePage.getRecords())) {
                List<ChargeRuleViewResultDto> chargeRuleViewResultDtos = new ArrayList<>();
                for (ChargeRuleEntity record : chargingRulePage.getRecords()) {
                    ChargeRuleViewResultDto chargeRuleViewResultDto = modelMapper.map(record, ChargeRuleViewResultDto.class);
                    List<ParkingChargeRuleEntity> listByRuleId = parkingChargeRuleCrudService.findListByRuleId(record.getId());
                    if (CollectionUtils.isNotEmpty(listByRuleId)) {
                        chargeRuleViewResultDto.setBinding(Boolean.TRUE);
                    } else {
                        chargeRuleViewResultDto.setBinding(Boolean.FALSE);
                    }
                    chargeRuleViewResultDtos.add(chargeRuleViewResultDto);
                }
                pagedResultDto.setPageNo(chargingRulePage.getCurrent());
                pagedResultDto.setPageSize(chargingRulePage.getSize());
                pagedResultDto.setTotalCount(chargingRulePage.getTotal());
                pagedResultDto.setItems(chargeRuleViewResultDtos);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取收费规则列表失败", e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取规则类型列表
     *
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getChargeRuleTypeComboboxList(GetChargeRuleTypeRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto(String.valueOf(ChargeRuleTypeEnum.GIST_TO_FREE.getValue()), ChargeRuleTypeEnum.GIST_TO_FREE.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(ChargeRuleTypeEnum.GIST_TO_DATE.getValue()), ChargeRuleTypeEnum.GIST_TO_DATE.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(ChargeRuleTypeEnum.GIST_TO_TIMES.getValue()), ChargeRuleTypeEnum.GIST_TO_TIMES.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(ChargeRuleTypeEnum.GIST_TO_DATE_TIMES.getValue()), ChargeRuleTypeEnum.GIST_TO_DATE_TIMES.getComment(), false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取收费规则类型列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 规则最小计时单位
     */
    @Override
    public ListResultDto<ComboboxItemDto> getUnitTime(GetUnitTimeRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto(String.valueOf(Const.HALF_HOUR), "最小计时单位30分钟", false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(Const.HOUR), "最小计时单位60分钟", false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取规则最小计时单位" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 删除收费规则
     *
     * @param requestDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto deleteChargeRule(ChargeRuleDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            boolean delete = chargeRuleCrudService.deleteById(requestDto.getDeleteId());
            chargeRuleTimeCrudService.deleteByRuleId(requestDto.getDeleteId());
            if (!delete) {
                resultDto.failed();
            }
            return resultDto.success();
        } catch (Exception e) {
            log.error("删除规则信息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取收费规则条数
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ChargeRuleTotalResultDto> getChargeRuleTotal(ChargeRuleTotalRequestDto requestDto) {
        ObjectResultDto<ChargeRuleTotalResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            Integer total = parkingChargeRuleCrudService.selectChargeTotal(requestDto.getParkingId());
            ChargeRuleTotalResultDto chargeRuleTotalResultDto = new ChargeRuleTotalResultDto();
            chargeRuleTotalResultDto.setTotal(total);
            objectResultDto.setData(chargeRuleTotalResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取收费规则条数失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 分页获取收费规则列表(无租户)
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<ChargeRuleNonTenantResultDto> getChargeRuleNonTenant(ChargeRuleNonTenantGetRequestDto requestDto) {
        PagedResultDto<ChargeRuleNonTenantResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<ParkingChargeRuleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            Page<ParkingChargeRuleEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingChargeRuleEntity> rulePage;
            rulePage = page.setRecords(parkingChargeRuleCrudService.selectChargeRulePaged(page, entityWrapper));
            List<ChargeRuleNonTenantResultDto> list = new ArrayList<>();
            for (ParkingChargeRuleEntity parkingChargeRuleEntity : rulePage.getRecords()) {
                ChargeRuleNonTenantResultDto chargeRuleNonTenantResultDto = new ChargeRuleNonTenantResultDto();
                ChargeRuleEntity chargeRuleEntity = chargeRuleCrudService.selectByRuleId(parkingChargeRuleEntity.getRuleId(), parkingChargeRuleEntity.getTenantId());
                if (chargeRuleEntity != null) {
                    chargeRuleNonTenantResultDto.setId(chargeRuleEntity.getId());
                    chargeRuleNonTenantResultDto.setName(chargeRuleEntity.getName());
                }
                chargeRuleNonTenantResultDto.setOnlineTime(parkingChargeRuleEntity.getOnlineTime());
                chargeRuleNonTenantResultDto.setOfflineTime(parkingChargeRuleEntity.getOfflineTime());
                list.add(chargeRuleNonTenantResultDto);
            }
            pagedResultDto.setPageNo(rulePage.getCurrent());
            pagedResultDto.setPageSize(rulePage.getSize());
            pagedResultDto.setTotalCount(rulePage.getTotal());
            pagedResultDto.setItems(list);
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取收费规则列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 根据id获取收费规则（无租户）
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ChargeRuleViewResultDto> getChargeRuleByIdNonTenant(ChargeRuleGetRequestDto requestDto) {
        ObjectResultDto<ChargeRuleViewResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            if (null == requestDto.getId()) {
                return objectResultDto.makeResult(ChargeResultEnum.CHARGE_RULE_ID_EMPTY.getValue(), ChargeResultEnum.CHARGE_RULE_ID_EMPTY.getComment());
            }
            ChargeRuleEntity chargingRule = chargeRuleCrudService.selectByIdNonTenant(requestDto.getId());
            if (chargingRule != null) {

                ChargeRuleViewResultDto chargeRuleResultDto = modelMapper.map(chargingRule, ChargeRuleViewResultDto.class);
                chargeRuleResultDto.setPerPrice(chargingRule.getPerPrice());
                chargeRuleResultDto.setDayTopAmount(chargingRule.getDayTopAmount());
                chargeRuleResultDto.setTopAmount(chargingRule.getTopAmount());
                //计时获取分时段
                if ((chargingRule.getRuleType().compareTo(ChargeRuleTypeEnum.GIST_TO_DATE.getValue()) == 0)
                        || chargingRule.getRuleType().compareTo(ChargeRuleTypeEnum.GIST_TO_DATE_TIMES.getValue()) == 0) {

                    List<ChargeRuleTimeEntity> chargeRuleTimeList = chargeRuleTimeCrudService.findRuleId(chargingRule.getId(), chargingRule.getTenantId());
                    ArrayList<ChargeRuleTimeResultDto> chargeRuleTimeResultDtos = new ArrayList<>();
                    if (CollectionUtils.isNotEmpty(chargeRuleTimeList)) {
                        for (ChargeRuleTimeEntity chargeRuleTimeEntity : chargeRuleTimeList) {
                            ChargeRuleTimeResultDto ruleTimeResultDto = modelMapper.map(chargeRuleTimeEntity, ChargeRuleTimeResultDto.class);
                            if (chargeRuleTimeEntity.getPrice() != null) {
                                ruleTimeResultDto.setPrice(chargeRuleTimeEntity.getPrice());
                            }
                            chargeRuleTimeResultDtos.add(ruleTimeResultDto);
                        }
                    }
                    chargeRuleResultDto.setPartTimes(chargeRuleTimeResultDtos);
                }
                objectResultDto.setData(chargeRuleResultDto);
                objectResultDto.success();
            } else {
                objectResultDto.makeResult(ChargeResultEnum.CHARGE_RULE_NOT_EXIST.getValue(),
                        ChargeResultEnum.CHARGE_RULE_NOT_EXIST.getComment());
            }
        } catch (Exception e) {
            log.error("根据ID获取收费规则失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

}
