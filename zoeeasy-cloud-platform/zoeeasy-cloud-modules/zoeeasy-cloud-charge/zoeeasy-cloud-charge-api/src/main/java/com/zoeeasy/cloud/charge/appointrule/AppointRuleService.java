package com.zoeeasy.cloud.charge.appointrule;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.appointrule.dto.request.*;
import com.zoeeasy.cloud.charge.appointrule.dto.result.AppointRuleResultDto;

/**
 * 预定规则
 *
 * @author walkman
 * @date 2018/03/30
 */
public interface AppointRuleService {

    /**
     * 新增预定规则
     *
     * @param requestDto
     * @return
     */
    ResultDto addAppointRule(AppointRuleAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 根据ID获取预定规则
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AppointRuleResultDto> getAppointRule(AppointRuleGetRequestDto requestDto);

    /**
     * 修改预定规则
     *
     * @param requestDto
     * @return
     */
    ResultDto updateAppointRule(AppointRuleUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除预定规则
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteAppointRule(AppointRuleDeleteRequestDto requestDto);

    /**
     * 获取预定规则列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<AppointRuleResultDto> getAppointRuleList(AppointRuleGetListRequestDto requestDto);

    /**
     * 分页获取预定规则列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<AppointRuleResultDto> getAppointRulePageList(AppointRuleGetPageListRequestDto requestDto);

    /**
     * 根据id获取预约规则(查询)
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AppointRuleResultDto> getAppointRuleById(AppointRuleGetRequestDto requestDto);

    /**
     * 更新预约规则关联状态
     *
     * @param requestDto
     * @return
     */
    ResultDto updateUsed(AppointRuleUpdateUsedRequestDto requestDto);

    /**
     * 根据id获取预约规则(查询无talentId)
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AppointRuleResultDto> getAppointRuleByIdNoTalentId(AppointRuleGetRequestDto requestDto);
}
