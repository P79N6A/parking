package com.zoeeasy.cloud.charge.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.reflect.TypeToken;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.core.enums.ResultStatusCode;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.charge.chg.CalculateAmountService;
import com.zoeeasy.cloud.charge.chg.ChargeRuleService;
import com.zoeeasy.cloud.charge.chg.dto.request.CalculateAmountRequestDto;
import com.zoeeasy.cloud.charge.chg.dto.request.ChargeRuleDeleteRequestDto;
import com.zoeeasy.cloud.charge.chg.dto.request.ChargeRuleGetListRequestDto;
import com.zoeeasy.cloud.charge.chg.dto.result.*;
import com.zoeeasy.cloud.charge.domain.ChargeRuleEntity;
import com.zoeeasy.cloud.charge.domain.ChargeRuleTimeEntity;
import com.zoeeasy.cloud.charge.domain.ParkingChargeRuleEntity;
import com.zoeeasy.cloud.charge.domain.ParkingChargeRuleHistoryEntity;
import com.zoeeasy.cloud.charge.enums.ChargeResultEnum;
import com.zoeeasy.cloud.charge.enums.HolidayTypeEnum;
import com.zoeeasy.cloud.charge.park.ParkChargeRuleService;
import com.zoeeasy.cloud.charge.park.dto.request.*;
import com.zoeeasy.cloud.charge.park.dto.result.ParkingChargeRuleViewResultDto;
import com.zoeeasy.cloud.charge.service.ChargeRuleCrudService;
import com.zoeeasy.cloud.charge.service.ChargeRuleTimeCrudService;
import com.zoeeasy.cloud.charge.service.ParkingChargeRuleCrudService;
import com.zoeeasy.cloud.charge.service.ParkingChargeRuleHistoryCrudService;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.core.enums.CarTypeEnum;
import com.zoeeasy.cloud.core.enums.ParkingLevelEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AkeemSuper
 * @date 2018/10/13 0013
 */
@Service(value = "parkChargeRuleService")
@Slf4j
public class ParkChargeRuleServiceImpl implements ParkChargeRuleService {

    @Autowired
    private ParkingChargeRuleCrudService parkingChargeRuleCrudService;

    @Autowired
    private ChargeRuleService chargeRuleService;

    @Autowired
    private ChargeRuleCrudService chargeRuleCrudService;

    @Autowired
    private ParkingChargeRuleHistoryCrudService parkingChargeRuleHistoryCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CalculateAmountService calculateAmountService;

    @Autowired
    private ChargeRuleTimeCrudService chargeRuleTimeCrudService;

    /**
     * 删除收费规则
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteChargeRule(ChargeRuleDeleteRequestDto requestDto) {

        ResultDto resultDto = new ResultDto();
        try {
            //如果该收费规则未绑定停车场信息则可以删除
            Integer count = parkingChargeRuleCrudService.selectCountByRuleId(requestDto.getDeleteId());
            if (count <= 0) {
                chargeRuleService.deleteChargeRule(requestDto);
            } else {
                return resultDto.makeResult(ChargeResultEnum.CHARGE_RULE_CORRELATION_LIMIT_UPDATE.getValue(), ChargeResultEnum.CHARGE_RULE_CORRELATION_LIMIT_UPDATE.getComment());
            }
            return resultDto.success();
        } catch (Exception e) {
            log.error("删除规则信息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 停车场关联收费规则
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto addParkingChargeRule(ParkingChargeRuleUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        resultDto.setMessage(ResultStatusCode.SUCCESS.getMessage());
        try {
            if (CollectionUtils.isNotEmpty(requestDto.getRules())) {
                List<ParkingChargeRuleItemUpdateRequestDto> ruleList = requestDto.getRules();
                for (int i = 0; i < ruleList.size(); i++) {
                    ParkingChargeRuleItemUpdateRequestDto ruleItem = ruleList.get(i);
                    if (ruleItem.getId() != null) {
                        //需要更新的规则
                        ParkingChargeRuleEntity parkingChargeRule = new ParkingChargeRuleEntity();
                        parkingChargeRule.setId(ruleItem.getId());
                        parkingChargeRule.setParkingId(requestDto.getParkingId());
                        parkingChargeRule.setRuleId(ruleItem.getRuleId());
                        if (null == ruleItem.getOnlineTime()) {
                            parkingChargeRule.setOnlineTime(DateTimeUtils.getDateMin());
                        } else {
                            parkingChargeRule.setOnlineTime(ruleItem.getOnlineTime());
                        }
                        if (null == ruleItem.getOfflineTime()) {
                            parkingChargeRule.setOnlineTime(DateTimeUtils.getDateMax());
                        } else {
                            parkingChargeRule.setOfflineTime(ruleItem.getOfflineTime());
                        }
                        //判断有没有时间冲突的规则
                        EntityWrapper<ParkingChargeRuleEntity> entityWrapper = new EntityWrapper<>();
                        entityWrapper.ne("id", parkingChargeRule.getId());
                        entityWrapper.eq("parkingId", requestDto.getParkingId());
                        entityWrapper.lt("onlineTime", parkingChargeRule.getOfflineTime());
                        entityWrapper.gt("offlineTime", parkingChargeRule.getOnlineTime());
                        List<ParkingChargeRuleEntity> parkingChargeRules = parkingChargeRuleCrudService.selectList(entityWrapper);
                        Boolean flag = modifyCondition(parkingChargeRule, parkingChargeRules);
                        if (flag) {
                            resultDto.setMessage("规则有冲突");
                        } else {
                            //保存到历史收费规则
                            ParkingChargeRuleHistoryEntity parkingChargeRuleHistoryEntity = modelMapper.map(ruleItem, ParkingChargeRuleHistoryEntity.class);
                            parkingChargeRuleHistoryEntity.setId(null);
                            parkingChargeRuleHistoryEntity.setParkingId(requestDto.getParkingId());
                            parkingChargeRuleHistoryCrudService.insert(parkingChargeRuleHistoryEntity);

                            parkingChargeRuleCrudService.updateById(parkingChargeRule);

                        }
                    } else {
                        //需要新建的规则
                        ParkingChargeRuleEntity parkingChargeRule = new ParkingChargeRuleEntity();
                        parkingChargeRule.setParkingId(requestDto.getParkingId());
                        parkingChargeRule.setRuleId(ruleItem.getRuleId());
                        if (null == ruleItem.getOnlineTime()) {
                            parkingChargeRule.setOnlineTime(DateTimeUtils.getDateMin());
                        } else {
                            parkingChargeRule.setOnlineTime(ruleItem.getOnlineTime());
                        }
                        if (null == ruleItem.getOfflineTime()) {
                            parkingChargeRule.setOnlineTime(DateTimeUtils.getDateMax());
                        } else {
                            parkingChargeRule.setOfflineTime(ruleItem.getOfflineTime());
                        }
                        if (parkingChargeRule.getOfflineTime().compareTo(DateUtils.now()) <= 0) {
                            resultDto.setMessage("新增规则下线时间不能小于当前时间");
                        } else {
                            //判断有没有时间冲突的规则
                            EntityWrapper<ParkingChargeRuleEntity> entityWrapper = new EntityWrapper<>();
                            entityWrapper.eq("parkingId", requestDto.getParkingId());
                            entityWrapper.lt("onlineTime", parkingChargeRule.getOfflineTime());
                            entityWrapper.gt("offlineTime", parkingChargeRule.getOnlineTime());
                            List<ParkingChargeRuleEntity> parkingChargeRules = parkingChargeRuleCrudService.selectList(entityWrapper);
                            Boolean flag = modifyCondition(parkingChargeRule, parkingChargeRules);
                            if (flag) {
                                resultDto.setMessage("规则有冲突");
                            } else {
                                parkingChargeRuleCrudService.insert(parkingChargeRule);
                            }
                        }
                    }
                }
            }
            resultDto.makeResult(ResultStatusCode.SUCCESS.getCode(), resultDto.getMessage());
        } catch (Exception e) {
            log.error("停车场添加收费规则失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 停车场收费规则删除
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto deleteParkingChargeRule(ParkingChargeRuleDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingChargeRuleEntity parkingChargeRuleEntity = parkingChargeRuleCrudService.selectById(requestDto.getId());
            if (null != parkingChargeRuleEntity) {
                //TODO 何种条件下允许删除收费规则
                parkingChargeRuleCrudService.deleteById(requestDto.getId());
                //保存到历史停车规则
                ParkingChargeRuleHistoryEntity parkingChargeRuleHistoryEntity = modelMapper.map(parkingChargeRuleEntity, ParkingChargeRuleHistoryEntity.class);
                parkingChargeRuleHistoryEntity.setId(null);
                parkingChargeRuleHistoryCrudService.insert(parkingChargeRuleHistoryEntity);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("收费规则关联停车场删除失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页获取停车场收费规则
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<ParkingChargeRuleViewResultDto> queryParkingChargeRulePage(ParkingChargeRuleQueryPageRequestDto requestDto) {
        PagedResultDto<ParkingChargeRuleViewResultDto> resultDto = new PagedResultDto<>();
        try {
            Wrapper<ParkingChargeRuleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            entityWrapper.ge("offlineTime", DateUtils.formatDate(DateUtils.now(), Const.FORMAT_DATETIME));
            Page<ParkingChargeRuleEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingChargeRuleEntity> parkingChargeRuleEntityPage = parkingChargeRuleCrudService.selectPage(page, entityWrapper);
            if (parkingChargeRuleEntityPage != null && CollectionUtils.isNotEmpty(parkingChargeRuleEntityPage.getRecords())) {
                List<ParkingChargeRuleEntity> records = parkingChargeRuleEntityPage.getRecords();
                List<ParkingChargeRuleViewResultDto> items = new ArrayList<>();
                for (ParkingChargeRuleEntity parkingChargeRuleEntity : records) {
                    ChargeRuleEntity chargeRuleEntity = chargeRuleCrudService.selectById(parkingChargeRuleEntity.getRuleId());
                    if (chargeRuleEntity != null) {
                        ParkingChargeRuleViewResultDto parkingChargeRuleViewResultDto = modelMapper.map(parkingChargeRuleEntity, ParkingChargeRuleViewResultDto.class);
                        parkingChargeRuleViewResultDto.setName(chargeRuleEntity.getName());
                        items.add(parkingChargeRuleViewResultDto);
                    }
                }
                resultDto.setItems(items);
                resultDto.setPageNo(parkingChargeRuleEntityPage.getCurrent());
                resultDto.setTotalCount(parkingChargeRuleEntityPage.getTotal());
                resultDto.setPageSize(parkingChargeRuleEntityPage.getSize());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("分页获取停车场收费规则失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页获取停车场历史收费规则
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<ParkingChargeRuleViewResultDto> queryHistoryParkingChargeRulePage(ParkingChargeRuleQueryPageRequestDto requestDto) {
        PagedResultDto<ParkingChargeRuleViewResultDto> resultDto = new PagedResultDto<>();
        try {
            Wrapper<ParkingChargeRuleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            entityWrapper.lt("offlineTime", DateUtils.formatDate(DateUtils.now(), Const.FORMAT_DATETIME));
            Page<ParkingChargeRuleEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingChargeRuleEntity> parkingChargeRuleEntityPage = parkingChargeRuleCrudService.selectPage(page, entityWrapper);
            if (parkingChargeRuleEntityPage != null && CollectionUtils.isNotEmpty(parkingChargeRuleEntityPage.getRecords())) {
                List<ParkingChargeRuleEntity> records = parkingChargeRuleEntityPage.getRecords();
                List<ParkingChargeRuleViewResultDto> items = new ArrayList<>();
                for (ParkingChargeRuleEntity parkingChargeRuleEntity : records) {
                    ChargeRuleEntity chargeRuleEntity = chargeRuleCrudService.selectById(parkingChargeRuleEntity.getRuleId());
                    if (chargeRuleEntity != null) {
                        ParkingChargeRuleViewResultDto parkingChargeRuleViewResultDto = modelMapper.map(parkingChargeRuleEntity, ParkingChargeRuleViewResultDto.class);
                        parkingChargeRuleViewResultDto.setName(chargeRuleEntity.getName());
                        items.add(parkingChargeRuleViewResultDto);
                    }
                }
                resultDto.setItems(items);
                resultDto.setPageNo(parkingChargeRuleEntityPage.getCurrent());
                resultDto.setTotalCount(parkingChargeRuleEntityPage.getTotal());
                resultDto.setPageSize(parkingChargeRuleEntityPage.getSize());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("分页获取停车场收费规则失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取收费规则列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ChargeRuleInfoResultDto> chargeRuleList(ChargeRuleGetListRequestDto requestDto) {
        ListResultDto<ChargeRuleInfoResultDto> listResultDto = new ListResultDto<>();
        try {
            //获取该停车场已关联的规则
            EntityWrapper<ChargeRuleEntity> chargeRuleEntityWrapper = new EntityWrapper<>();
            chargeRuleEntityWrapper.orderBy("creationTime", false);
            List<ChargeRuleEntity> chargeRuleEntities = chargeRuleCrudService.selectList(chargeRuleEntityWrapper);
            if (CollectionUtils.isNotEmpty(chargeRuleEntities)) {
                List<ChargeRuleInfoResultDto> chargeRuleInfoResultDtos = modelMapper.map(chargeRuleEntities,
                        new TypeToken<List<ChargeRuleInfoResultDto>>() {
                        }.getType());
                listResultDto.setItems(chargeRuleInfoResultDtos);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取收费规则列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 停车场收费规则试算
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<CalculateAmountViewResultDto> parkChargeRuleCalculateTry(ParkChargeRuleCalculateTryRequestDto requestDto) {
        ObjectResultDto<CalculateAmountViewResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            //根据停车场获取所有的收费规则
            List<ParkingChargeRuleTryRequestDto> parkingChargeRuleEntities = requestDto.getParkingChargeRuleTry();
            if (CollectionUtils.isEmpty(parkingChargeRuleEntities)) {
                objectResultDto.makeResult(ChargeResultEnum.PARKING_CHARGE_RULE_EMPTY.getValue(),
                        ChargeResultEnum.PARKING_CHARGE_RULE_EMPTY.getComment());
                return objectResultDto;
            }
            List<ChargeRuleInfoViewResultDto> list = new ArrayList<>();
            for (ParkingChargeRuleTryRequestDto parkingChargeRuleEntity : parkingChargeRuleEntities) {
                ChargeRuleInfoViewResultDto parkingChargeRuleResultDto = modelMapper.map(parkingChargeRuleEntity, ChargeRuleInfoViewResultDto.class);
                ChargeRuleEntity selectByRuleId = chargeRuleCrudService.selectById(parkingChargeRuleEntity.getRuleId());
                //车辆类型
                if (null != selectByRuleId && (requestDto.getTryCarType().equals(selectByRuleId.getCarType()) || CarTypeEnum.ALL_TYPE.getValue().equals(selectByRuleId.getCarType()))) {
                    modelMapper.map(selectByRuleId, parkingChargeRuleResultDto);
                    List<ChargeRuleTimeEntity> chargeRuleTimeEntities = chargeRuleTimeCrudService.selectByRuleId(parkingChargeRuleEntity.getRuleId());
                    if (CollectionUtils.isNotEmpty(chargeRuleTimeEntities)) {
                        List<ChargeRuleTimeViewResultDto> chargeRuleTimeResultDtos = modelMapper.map(chargeRuleTimeEntities, new org.modelmapper.TypeToken<List<ChargeRuleTimeViewResultDto>>() {
                        }.getType());
                        parkingChargeRuleResultDto.setPartTimes(chargeRuleTimeResultDtos);
                    }
                    list.add(parkingChargeRuleResultDto);
                }
            }
            if (CollectionUtils.isEmpty(list)) {
                objectResultDto.makeResult(ChargeResultEnum.PARKING_CHARGE_RULE_EMPTY.getValue(),
                        ChargeResultEnum.PARKING_CHARGE_RULE_EMPTY.getComment());
                return objectResultDto;
            }
            CalculateAmountRequestDto calculateAmountRequestDto = new CalculateAmountRequestDto();
            calculateAmountRequestDto.setTenantId(FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getTenantId());
            calculateAmountRequestDto.setStartTime(requestDto.getTryStartTime());
            calculateAmountRequestDto.setEndTime(requestDto.getTryEndTime());
            calculateAmountRequestDto.setParkingChargeRules(list);
            ObjectResultDto<CalculateAmountResultDto> calculateAmountResulto = calculateAmountService.calculateAmount(calculateAmountRequestDto);
            if (calculateAmountResulto.isSuccess() && calculateAmountResulto.getData() != null) {
                CalculateAmountResultDto data = calculateAmountResulto.getData();
                CalculateAmountViewResultDto calculateAmountViewResultDto = new CalculateAmountViewResultDto();
                calculateAmountViewResultDto.setAmount(data.getAmount());
                calculateAmountViewResultDto.setFreeType(data.getFreeType());
                calculateAmountViewResultDto.setFreeDuration(data.getFreeDuration());
                calculateAmountViewResultDto.setChargeDuration(data.getChargeDuration());
                objectResultDto.setData(calculateAmountViewResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("停车场收费规则试算失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 判断规则是否有冲突
     *
     * @param parkingChargeRule  将要更新或将要添加的规则
     * @param parkingChargeRules 与之时间相冲突的规则列表
     * @return Boolean
     */
    private Boolean modifyCondition(ParkingChargeRuleEntity parkingChargeRule, List<ParkingChargeRuleEntity> parkingChargeRules) {
        if (parkingChargeRules.isEmpty()) {
            return false;
        } else {
            List<Long> ids = parkingChargeRules.stream().map(ParkingChargeRuleEntity::getRuleId).collect(Collectors.toList());
            List<ChargeRuleEntity> chargeRules = chargeRuleCrudService.findByIds(ids);
            ChargeRuleEntity updateChargeRule = chargeRuleCrudService.selectById(parkingChargeRule.getRuleId());
            for (ChargeRuleEntity chargeRule : chargeRules) {
                if (
                        (chargeRule.getCarType().equals(updateChargeRule.getCarType()) || chargeRule.getCarType().equals(CarTypeEnum.ALL_TYPE.getValue()) || updateChargeRule.getCarType().equals(CarTypeEnum.ALL_TYPE.getValue()))
                                && (chargeRule.getHolidayType().equals(updateChargeRule.getHolidayType()) || chargeRule.getHolidayType().equals(HolidayTypeEnum.ALL.getValue()) || updateChargeRule.getCarType().equals(HolidayTypeEnum.ALL.getValue()))
                                && (chargeRule.getParkingLevel().equals(updateChargeRule.getParkingLevel()) || chargeRule.getParkingLevel().equals(ParkingLevelEnum.EQUALLY.getValue()) || updateChargeRule.getParkingLevel().equals(ParkingLevelEnum.EQUALLY.getValue()))
                ) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 根据停车场id删除停车场关联的收费规则
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteParkingChargeRuleByParkingId(ParkingChargeRuleDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<ParkingChargeRuleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getId());
            parkingChargeRuleCrudService.delete(entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("根据停车场id删除停车场关联的收费规则失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据停车场id删除停车场关联的收费规则(管理端)
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto managementDeleteParkingChargeRuleByParkingId(ParkingChargeRuleDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<ParkingChargeRuleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getId());
            parkingChargeRuleCrudService.deleteParkingChargeRuleNonTenant(entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("根据停车场id删除停车场关联的收费规则失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
