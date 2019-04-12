package com.zoeeasy.cloud.integration.park;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.chg.dto.request.ChargeRuleGetListRequestDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleInfoResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleInfoViewResultDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingCurrentChargeInfoRequestDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleForUserParkingRecordRequestDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleQueryPageRequestDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleUpdateRequestDto;
import com.zoeeasy.cloud.charge.park.dto.result.ParkingChargeRuleViewResultDto;
import com.zoeeasy.cloud.charge.park.dto.result.ParkingCurrentChargeInfoResultDto;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/10/28 0028
 */
public interface ParkChargeRuleIntegrationService {

    /**
     * 用户停车记录模块查询收费规则
     *
     * @param requestDto
     * @return
     */
    List<ChargeRuleInfoViewResultDto> getChargeRuleForUserParkingRecord(ParkingChargeRuleForUserParkingRecordRequestDto requestDto);


    /**
     * 带租户获取停车场当前收费信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingCurrentChargeInfoResultDto> getParkingChargeRuleCurrentInfo(ParkingCurrentChargeInfoRequestDto requestDto);

    /**
     * 停车场关联收费规则
     *
     * @param requestDto
     * @return
     */
    ResultDto addParkingChargeRule(ParkingChargeRuleUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 分页获取停车场收费规则
     *
     * @param requestDto
     * @return
     * @throws ValidationException
     * @throws BusinessException
     */
    PagedResultDto<ParkingChargeRuleViewResultDto> queryParkingChargeRulePage(ParkingChargeRuleQueryPageRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 分页获取停车场历史收费规则
     *
     * @param requestDto
     * @return
     * @throws ValidationException
     * @throws BusinessException
     */
    PagedResultDto<ParkingChargeRuleViewResultDto> queryHistoryParkingChargeRulePage(ParkingChargeRuleQueryPageRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取收费规则列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ChargeRuleInfoResultDto> chargeRuleList(ChargeRuleGetListRequestDto requestDto) throws ValidationException, BusinessException;

}
