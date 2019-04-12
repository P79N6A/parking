package com.zoeeasy.cloud.charge.park;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.chg.dto.request.ChargeRuleDeleteRequestDto;
import com.zoeeasy.cloud.charge.chg.dto.request.ChargeRuleGetListRequestDto;
import com.zoeeasy.cloud.charge.chg.dto.result.CalculateAmountViewResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleInfoResultDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkChargeRuleCalculateTryRequestDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleDeleteRequestDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleQueryPageRequestDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleUpdateRequestDto;
import com.zoeeasy.cloud.charge.park.dto.result.ParkingChargeRuleViewResultDto;

/**
 * @author AkeemSuper
 * @date 2018/10/13 0013
 */
public interface ParkChargeRuleService {

    /**
     * 删除收费规则
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteChargeRule(ChargeRuleDeleteRequestDto requestDto);

    /**
     * 停车场关联收费规则
     *
     * @param requestDto
     * @return
     */
    ResultDto addParkingChargeRule(ParkingChargeRuleUpdateRequestDto requestDto);

    /**
     * 停车场收费规则删除
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteParkingChargeRule(ParkingChargeRuleDeleteRequestDto requestDto);

    /**
     * 分页获取停车场收费规则
     *
     * @param requestDto
     * @return
     * @throws ValidationException
     * @throws BusinessException
     */
    PagedResultDto<ParkingChargeRuleViewResultDto> queryParkingChargeRulePage(ParkingChargeRuleQueryPageRequestDto requestDto);

    /**
     * 分页获取停车场历史收费规则
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<ParkingChargeRuleViewResultDto> queryHistoryParkingChargeRulePage(ParkingChargeRuleQueryPageRequestDto requestDto);

    /**
     * 获取收费规则列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ChargeRuleInfoResultDto> chargeRuleList(ChargeRuleGetListRequestDto requestDto);

    /**
     * 停车场收费规则试算
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<CalculateAmountViewResultDto> parkChargeRuleCalculateTry(ParkChargeRuleCalculateTryRequestDto requestDto);

    /**
     * 根据停车场id删除停车场关联的收费规则
     * @param requestDto
     * @return
     */
    ResultDto deleteParkingChargeRuleByParkingId(ParkingChargeRuleDeleteRequestDto requestDto);

    /**
     * 根据停车场id删除停车场关联的收费规则(管理端)
     * @param requestDto
     * @return
     */
    ResultDto managementDeleteParkingChargeRuleByParkingId(ParkingChargeRuleDeleteRequestDto requestDto);
}
