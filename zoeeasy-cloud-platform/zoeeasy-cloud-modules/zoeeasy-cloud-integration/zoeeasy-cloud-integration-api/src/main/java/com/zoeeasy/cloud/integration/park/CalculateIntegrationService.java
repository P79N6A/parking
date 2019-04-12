package com.zoeeasy.cloud.integration.park;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.chg.dto.result.CalculateAmountResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.CalculateAmountViewResultDto;
import com.zoeeasy.cloud.charge.park.dto.request.ParkChargeRuleCalculateTryRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.request.AppointAmountCalculateRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.result.AppointAmountCalculateResultDto;
import com.zoeeasy.cloud.integration.park.dto.request.ParkingAmountCalculateRequestDto;

/**
 * @Description: 计算停车金额服务
 * @author: AkeemSuper
 * @date: 2018/3/15 0015
 */
public interface CalculateIntegrationService {

    /**
     * 根据停车信息计算停车金额服务
     *
     * @param requestDto 计算停车金额的请求参数
     * @return CalculateAmountResultDto
     */
    ObjectResultDto<CalculateAmountResultDto> calculateAmount(ParkingAmountCalculateRequestDto requestDto);

    /**
     * 停车场收费规则试算
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<CalculateAmountViewResultDto> parkChargeRuleCalculateTry(ParkChargeRuleCalculateTryRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 计算预约费用
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AppointAmountCalculateResultDto> calculateAppointAmount(AppointAmountCalculateRequestDto requestDto);

}
