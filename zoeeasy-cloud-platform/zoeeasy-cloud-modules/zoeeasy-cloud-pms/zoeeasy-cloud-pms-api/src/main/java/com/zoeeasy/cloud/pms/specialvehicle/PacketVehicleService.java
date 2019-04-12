package com.zoeeasy.cloud.pms.specialvehicle;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.EndDateResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketReceiptResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketVehicleGetResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketVehiclePagedResultDto;

/**
 * 包期车管理服务
 * Created by song on 2018/10/15.
 */
public interface PacketVehicleService {

    /**
     * 添加包期车
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<PacketReceiptResultDto> addPacketVehicle(PacketVehicleAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 分页查询包期车
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<PacketVehiclePagedResultDto> getPacketVehiclePagedList(PacketVehiclePagedRequestDto requestDto);

    /**
     * 更改支付状态
     *
     * @param requestDto
     * @return
     */
    ResultDto updateTopUp(TopUpUpdateRequestDto requestDto);

    /**
     * 删除包期车
     *
     * @param requestDto
     * @return
     */
    ResultDto deletePacketVehicle(PacketVehicleDeleteRequestDto requestDto);

    /**
     * 获取包期车详情
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<PacketVehicleGetResultDto> getPacketVehicle(PacketVehicleGetRequestDto requestDto);

    /**
     * 获取结束时间
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<EndDateResultDto> getEndDate(EndDateRequestDto requestDto);

}
