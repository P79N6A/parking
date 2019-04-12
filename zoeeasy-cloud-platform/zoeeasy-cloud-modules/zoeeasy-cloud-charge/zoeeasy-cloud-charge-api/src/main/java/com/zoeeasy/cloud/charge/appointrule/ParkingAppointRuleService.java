package com.zoeeasy.cloud.charge.appointrule;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.appointrule.dto.request.*;
import com.zoeeasy.cloud.charge.appointrule.dto.result.ParkingAppointRuleDetailViewResultDto;
import com.zoeeasy.cloud.charge.appointrule.dto.result.ParkingAppointRuleResultDto;
import com.zoeeasy.cloud.charge.appointrule.dto.result.ParkingAppointRuleViewResultDto;

/**
 * 停车场预约规则服务
 *
 * @author zwq
 * @date 2018/03/30
 */
public interface ParkingAppointRuleService {

    /**
     * 删除停车场约规则
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteParkingAppointRule(ParkingAppointRuleDeleteRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 根据ID查停车场约规则信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingAppointRuleViewResultDto> getParkingAppointRuleById(ParkingAppointRuleGetRequestDto requestDto);

    /**
     * 根据停车场查约规则信息
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ParkingAppointRuleViewResultDto> getAppointRuleListByParkingInfo(ParkingAppointRuleListGetByParkingInfoRequestDto requestDto);

    /**
     * 根据停车场查约规则信息
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<ParkingAppointRuleViewResultDto> getAppointRulePagedListRuleByParkingInfo(ParkingAppointRulePagedListGetByParkingInfoRequestDto requestDto);

    /**
     * 查询停车场预约规则
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ParkingAppointRuleResultDto> getAppointRuleListByParkingInfo(ParkingAppointRuleQueryRequestDto requestDto);

    /**
     * 通过时间查询停车场对应预约规则详情
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingAppointRuleDetailViewResultDto> getAppointRuleTotalLByAppointTime(ParkingAppointRuleGetByTimeRequestDto requestDto);

    /**
     * 维护停车场预约规则
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto maintainParkingAppointRule(ParkingAppointRuleUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 根据上线下线时间及停车场Id获取
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<ParkingAppointRuleViewResultDto> getParkingAppointRule(ParkingAppointRuleGetByVaildTimeRequestDto requestDto);

    /**
     * 根据停车场删除关联的预约规则
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteParkingAppointRuleByParkingId(ParkingAppointRuleDeleteRequestDto requestDto);

    /**
     * 根据停车场删除关联的预约规则(无租户)
     *
     * @param requestDto
     * @return
     */
    ResultDto managementDeleteParkingAppointRuleByParkingId(ParkingAppointRuleDeleteRequestDto requestDto);
}
