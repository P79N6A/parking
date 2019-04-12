package com.zoeeasy.cloud.pms.specialvehicle;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketApproveGetResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketApproveResultDto;

/**
 * 包期取消服务
 * Created by song on 2018/10/15.
 */
public interface PacketApproveService {

    /**
     * 申请取消包期
     *
     * @param requestDto
     * @return
     */
    ResultDto applyCancelPacket(ApplyCancelPacketRequestDto requestDto);

    /**
     * 获取取消包期车信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<PacketApproveGetResultDto> getPacketApprove(PacketApproveGetRequestDto requestDto);

    /**
     * 审核取消包期
     *
     * @param requestDto
     * @return
     */
    ResultDto checkPacketApprove(CheckPacketApproveRequestDto requestDto);

    /**
     * 分页获取取消包期申请
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<PacketApproveResultDto> getPacketApprovePagedList(CancelPacketApplyQueryPagedRequestDto requestDto);

    /**
     * 删除取消包期
     *
     * @param requestDto
     * @return
     */
    ResultDto deletePacketApprove(PacketApproveDeleteRequestDto requestDto);

}
