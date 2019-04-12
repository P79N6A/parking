package com.zoeeasy.cloud.integration.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.charge.chg.dto.request.ChargeRuleGetListRequestDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleInfoResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleInfoViewResultDto;
import com.zoeeasy.cloud.charge.park.ParkChargeRuleService;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingCurrentChargeInfoRequestDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleForUserParkingRecordRequestDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleQueryPageRequestDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleUpdateRequestDto;
import com.zoeeasy.cloud.charge.park.dto.result.ParkingChargeRuleViewResultDto;
import com.zoeeasy.cloud.charge.park.dto.result.ParkingCurrentChargeInfoResultDto;
import com.zoeeasy.cloud.charge.platform.PlatformChargeService;
import com.zoeeasy.cloud.integration.park.ParkChargeRuleIntegrationService;
import com.zoeeasy.cloud.integration.park.validator.ChargeRuleGetListRequestDtoValidator;
import com.zoeeasy.cloud.integration.park.validator.ParkingChargeRuleQueryPageRequestDtoValidator;
import com.zoeeasy.cloud.integration.park.validator.ParkingChargeRuleUpdateRequestDtoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/10/28 0028
 */
@Service(value = "parkChargeRuleIntegrationService")
@Slf4j
public class ParkChargeRuleIntegrationServiceImpl implements ParkChargeRuleIntegrationService {

    @Autowired
    private PlatformChargeService platformChargeService;

    @Autowired
    private ParkChargeRuleService parkChargeRuleService;

    /**
     * 用户停车记录模块查询收费规则
     *
     * @param requestDto
     * @return
     */
    @Override
    public List<ChargeRuleInfoViewResultDto> getChargeRuleForUserParkingRecord(ParkingChargeRuleForUserParkingRecordRequestDto requestDto) {
        return platformChargeService.getChargeRuleForUserParkingRecord(requestDto);
    }


    /**
     * 带租户获取停车场当前收费信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingCurrentChargeInfoResultDto> getParkingChargeRuleCurrentInfo(ParkingCurrentChargeInfoRequestDto requestDto) {
        return platformChargeService.getParkingCurrentChargeInfo(requestDto);
    }

    /**
     * 停车场关联收费规则
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addParkingChargeRule(@FluentValid({ParkingChargeRuleUpdateRequestDtoValidator.class}) ParkingChargeRuleUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            return parkChargeRuleService.addParkingChargeRule(requestDto);
        } catch (Exception e) {
            log.error("停车场关联收费规则失败" + e.getMessage());
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
    public PagedResultDto<ParkingChargeRuleViewResultDto> queryParkingChargeRulePage(@FluentValid({ParkingChargeRuleQueryPageRequestDtoValidator.class}) ParkingChargeRuleQueryPageRequestDto requestDto) {
        PagedResultDto<ParkingChargeRuleViewResultDto> resultDto = new PagedResultDto<>();
        try {
            return parkChargeRuleService.queryParkingChargeRulePage(requestDto);
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
     */
    @Override
    public PagedResultDto<ParkingChargeRuleViewResultDto> queryHistoryParkingChargeRulePage(@FluentValid({ParkingChargeRuleQueryPageRequestDtoValidator.class}) ParkingChargeRuleQueryPageRequestDto requestDto) {
        PagedResultDto<ParkingChargeRuleViewResultDto> resultDto = new PagedResultDto<>();
        try {
            return parkChargeRuleService.queryHistoryParkingChargeRulePage(requestDto);
        } catch (Exception e) {
            log.error("分页获取停车场历史收费规则失败" + e.getMessage());
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
    public ListResultDto<ChargeRuleInfoResultDto> chargeRuleList(@FluentValid({ChargeRuleGetListRequestDtoValidator.class}) ChargeRuleGetListRequestDto requestDto) {
        ListResultDto<ChargeRuleInfoResultDto> listResultDto = new ListResultDto<>();
        try {
            return parkChargeRuleService.chargeRuleList(requestDto);
        } catch (Exception e) {
            log.error("获取收费规则列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

}
