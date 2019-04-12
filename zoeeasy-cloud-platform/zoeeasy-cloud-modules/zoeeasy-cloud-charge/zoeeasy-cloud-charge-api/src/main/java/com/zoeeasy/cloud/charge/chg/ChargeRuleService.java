package com.zoeeasy.cloud.charge.chg;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.chg.dto.request.*;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleNonTenantResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleTotalResultDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleViewResultDto;

/**
 * @Description: 收费规则服务
 * @Date: 2018/1/26 0026
 * @author: AkeemSuper
 */
public interface ChargeRuleService {

    /**
     * 新增收费规则
     *
     * @param requestDto
     * @return
     */
    ResultDto addChargeRule(ChargeRuleAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 根据ID获取收费规则
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ChargeRuleViewResultDto> getChargeRule(ChargeRuleGetRequestDto requestDto);

    /**
     * 修改收费规则
     *
     * @param requestDto
     * @return
     */
    ResultDto updateChargeRule(ChargeRuleUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 分页获取收费规则列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<ChargeRuleViewResultDto> getChargeRulePageList(ChargeRuleGetPageListRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取规则类型列表
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getChargeRuleTypeComboboxList(GetChargeRuleTypeRequestDto requestDto);

    /**
     * 规则最小计时单位
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getUnitTime(GetUnitTimeRequestDto requestDto);

    /**
     * 删除收费规则
     */
    ResultDto deleteChargeRule(ChargeRuleDeleteRequestDto requestDto);

    /**
     * 获取收费规则条数
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ChargeRuleTotalResultDto> getChargeRuleTotal(ChargeRuleTotalRequestDto requestDto);

    /**
     * 分页获取收费规则列表(无租户)
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<ChargeRuleNonTenantResultDto> getChargeRuleNonTenant(ChargeRuleNonTenantGetRequestDto requestDto);

    /**
     * 根据id获取收费规则（无租户）
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ChargeRuleViewResultDto> getChargeRuleByIdNonTenant(ChargeRuleGetRequestDto requestDto);
}
