package com.zoeeasy.cloud.pms.gate;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.gate.dto.request.*;
import com.zoeeasy.cloud.pms.gate.dto.result.ParkingGateListGetResultDto;
import com.zoeeasy.cloud.pms.gate.dto.result.ParkingGateQueryPagedResultDto;
import com.zoeeasy.cloud.pms.gate.dto.result.ParkingGateResultDto;

/**
 * 停车场进出口管理服务
 */
public interface ParkingGateInfoService {
    /**
     * 新增停车场进出口
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto addParkingGate(ParkingGateAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除停车场进出口
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteParkingGate(ParkingGateDeleteRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 更新停车场进出口
     *
     * @param requestDto
     * @return
     */
    ResultDto updateParkingGate(ParkingGateUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取停车场进出口
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingGateResultDto> getParkingGate(ParkingGateGetRequestDto requestDto);

    /**
     * 获取停车场进出口列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ParkingGateListGetResultDto> getParkingGateList(ParkingGateListGetRequestDto requestDto);

    /**
     * 分页获取停车场进出口列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<ParkingGateQueryPagedResultDto> getParkingGatePagedList(ParkingGateQueryPagedResultRequestDto requestDto);

    /**
     * 批量删除停车场进出口
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteBatchParkingGate(ParkingGateBatchDeleteRequestDto requestDto);

    /**
     * 批量导入
     *
     * @param bytes
     * @param fileName
     * @return
     */
    ResultDto importExcel(byte[] bytes, String fileName);

    /**
     * 获取出入口类型
     */
    ListResultDto<ComboboxItemDto> getParkingGateType(ParkingGateTypeGetRequestDto requestDto);

}
