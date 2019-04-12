package com.zoeeasy.cloud.charge.chg;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.chg.dto.request.CalculateAmountRequestDto;
import com.zoeeasy.cloud.charge.chg.dto.request.ChargeRuleCalculateTryRequestDto;
import com.zoeeasy.cloud.charge.chg.dto.result.CalculateAmountResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.CalculateAmountViewResultDto;

/**
 * 收费计算服务
 *
 * @author AkeemSuper
 * @date 2018/10/13 0013
 */
public interface CalculateAmountService {

    /**
     * 分时段计算停车费用
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<CalculateAmountResultDto> calculateAmount(CalculateAmountRequestDto requestDto);

    /**
     * 收费规则试算
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<CalculateAmountViewResultDto> chargeRuleCalculateTry(ChargeRuleCalculateTryRequestDto requestDto) throws ValidationException, BusinessException;

}
