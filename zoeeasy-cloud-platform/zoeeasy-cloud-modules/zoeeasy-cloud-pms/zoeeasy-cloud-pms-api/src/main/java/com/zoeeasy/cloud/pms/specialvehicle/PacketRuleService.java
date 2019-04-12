package com.zoeeasy.cloud.pms.specialvehicle;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketRuleListGetResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketRuleQueryPagedResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketRuleResultDto;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
public interface PacketRuleService {
    /**
     * 添加包期规则
     *
     * @param requestDto
     * @return
     */
    ResultDto addPacketRule(PacketRuleAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除包期规则
     *
     * @param requestDto
     * @return
     */
    ResultDto deletePacketRule(PacketRuleDeleteRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 修改包期规则
     *
     * @param requestDto
     * @return
     */
    ResultDto updatePacketRule(PacketRuleUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取包期规则
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<PacketRuleResultDto> getPacketRule(PacketRuleGetRequestDto requestDto);

    /**
     * 分页获取包期规则列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<PacketRuleQueryPagedResultDto> getPacketRulePagedList(PacketRuleQueryPagedRequestDto requestDto);

    /**
     * 获取包期规则列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<PacketRuleListGetResultDto> getPacketRuleList(PacketRuleListGetRequestDto requestDto);

}
