package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.domain.PacketApproveEntity;
import com.zoeeasy.cloud.pms.domain.PacketRuleEntity;
import com.zoeeasy.cloud.pms.domain.PacketVehicleEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.enums.*;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListGetResultDto;
import com.zoeeasy.cloud.pms.service.PacketApproveCrudService;
import com.zoeeasy.cloud.pms.service.PacketRuleCrudService;
import com.zoeeasy.cloud.pms.service.PacketVehicleCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.specialvehicle.PacketApproveService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketApproveGetResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketApproveResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 包期取消服务
 * Created by song on 2018/10/15.
 */
@Service("packetApproveService")
@Slf4j
public class PacketApproveServiceImpl implements PacketApproveService {

    @Autowired
    private PacketApproveCrudService packetApproveCrudService;

    @Autowired
    private PacketVehicleCrudService packetVehicleCrudService;

    @Autowired
    private PacketRuleCrudService packetRuleCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 申请取消包期
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto applyCancelPacket(ApplyCancelPacketRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            PacketApproveEntity packetApproveEntity = new PacketApproveEntity();
            packetApproveEntity.setPacketId(requestDto.getId());
            packetApproveEntity.setApproveTime(new Date());
            packetApproveEntity.setApproveStatus(ApproveStatusEnum.WAITING.getValue());
            packetApproveCrudService.insert(packetApproveEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("申请取消包期失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取取消包期车信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<PacketApproveGetResultDto> getPacketApprove(PacketApproveGetRequestDto requestDto) {
        ObjectResultDto<PacketApproveGetResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            PacketApproveGetResultDto packetApproveGetResultDto = new PacketApproveGetResultDto();
            PacketApproveEntity packetApproveEntity = packetApproveCrudService.selectById(requestDto.getId());
            if (packetApproveEntity != null) {
                packetApproveGetResultDto.setId(packetApproveEntity.getId());
                packetApproveGetResultDto.setApproveStatus(ApproveStatusEnum.parse(packetApproveEntity.getApproveStatus()).getComment());
                if (packetApproveEntity.getRejectReason() != null) {
                    packetApproveGetResultDto.setRejectReason(RejectReasonEnum.parse(packetApproveEntity.getRejectReason()).getComment());
                }
                packetApproveGetResultDto.setReason(packetApproveEntity.getReason());

                PacketVehicleEntity packetVehicleEntity = packetVehicleCrudService.selectById(packetApproveEntity.getPacketId());
                if (packetVehicleEntity != null) {
                    packetApproveGetResultDto.setOwnerName(packetVehicleEntity.getOwnerName());
                    packetApproveGetResultDto.setPlateNumber(packetVehicleEntity.getPlateNumber());
                    packetApproveGetResultDto.setPlateColor(PlateColorEnum.parse(packetVehicleEntity.getPlateColor()).getComment());
                    packetApproveGetResultDto.setOwnerPhone(packetVehicleEntity.getOwnerPhone());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    packetApproveGetResultDto.setEffectivityTime(simpleDateFormat.format(packetVehicleEntity.getBeginDate()) + " 至 " +
                            simpleDateFormat.format(packetVehicleEntity.getEndDate()));
                    PacketRuleEntity packetRuleEntity = packetRuleCrudService.selectById(packetVehicleEntity.getRuleId());
                    packetApproveGetResultDto.setPacketType(PacketTypeEnum.parse(packetRuleEntity.getPacketType()).getComment());
                }
            }
            objectResultDto.setData(packetApproveGetResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取取消包期车信息失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 审核取消包期
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto checkPacketApprove(CheckPacketApproveRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            PacketApproveEntity packetApproveEntity = modelMapper.map(requestDto, PacketApproveEntity.class);
            EntityWrapper<PacketApproveEntity> entityEntityWrapper = new EntityWrapper<>();
            entityEntityWrapper.eq("id", requestDto.getId());
            packetApproveCrudService.update(packetApproveEntity, entityEntityWrapper);
            if (requestDto.getApproveStatus().equals(ApproveStatusEnum.PASS.getValue())) {
                PacketApproveEntity packetApprove = packetApproveCrudService.selectById(requestDto.getId());
                PacketVehicleEntity packetVehicleEntity = new PacketVehicleEntity();
                packetVehicleEntity.setEffectedStatus(EffectedStatusEnum.LOSE_EFFECTED.getValue());
                EntityWrapper<PacketVehicleEntity> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("id", packetApprove.getPacketId());
                packetVehicleCrudService.update(packetVehicleEntity, entityWrapper);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("审核取消包期失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页获取取消包期申请
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<PacketApproveResultDto> getPacketApprovePagedList(CancelPacketApplyQueryPagedRequestDto requestDto) {
        PagedResultDto<PacketApproveResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<PacketVehicleEntity> vehicleWrapper = new EntityWrapper<>();
            List<Long> parkingIds = new ArrayList<>();
            ParkingListGetRequestDto parkingListGetRequestDto = new ParkingListGetRequestDto();
            if (!StringUtils.isEmpty(requestDto.getParkingName())) {
                parkingListGetRequestDto.setName(requestDto.getParkingName());
            }
            ListResultDto<ParkingListGetResultDto> list = parkingInfoService.getParkingList(parkingListGetRequestDto);
            if (!list.getItems().isEmpty()) {
                for (ParkingListGetResultDto parkingListGetResultDto : list.getItems()) {
                    parkingIds.add(parkingListGetResultDto.getId());
                }
            } else {
                pagedResultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                pagedResultDto.success();
                return pagedResultDto;
            }
            vehicleWrapper.in("parkingId", parkingIds);
            if (!StringUtils.isEmpty(requestDto.getPlateNumber())) {
                vehicleWrapper.like("plateNumber", requestDto.getPlateNumber());
            }
            List<PacketVehicleEntity> packetVehicles = packetVehicleCrudService.selectList(vehicleWrapper);
            List<Long> packetIds = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(packetVehicles)) {
                for (PacketVehicleEntity packetVehicleEntity : packetVehicles) {
                    packetIds.add(packetVehicleEntity.getId());
                }
            } else {
                pagedResultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                pagedResultDto.success();
                return pagedResultDto;
            }
            EntityWrapper<PacketApproveEntity> approveWrapper = new EntityWrapper<>();
            if (requestDto.getApproveStatus() != null) {
                approveWrapper.eq("approveStatus", requestDto.getApproveStatus());
            }
            approveWrapper.in("packetId", packetIds);
            approveWrapper.orderBy("creationTime", false);
            Page<PacketApproveEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<PacketApproveEntity> approvePageList = packetApproveCrudService.selectPage(page, approveWrapper);
            List<PacketApproveResultDto> packetApproves = new ArrayList<>();
            Map<Long, ParkingInfoEntity> map = new HashMap<>();
            for (PacketApproveEntity packetApproveEntity : approvePageList.getRecords()) {
                PacketApproveResultDto packetApproveResultDto = new PacketApproveResultDto();
                packetApproveResultDto.setId(packetApproveEntity.getId());
                packetApproveResultDto.setApproveTime(packetApproveEntity.getApproveTime());
                packetApproveResultDto.setApproveStatus(packetApproveEntity.getApproveStatus());
                if (packetApproveEntity.getRejectReason() != null) {
                    packetApproveResultDto.setRejectReason(RejectReasonEnum.parse(packetApproveEntity.getRejectReason()).getComment());
                }
                PacketVehicleEntity packetVehicleEntity = packetVehicleCrudService.selectById(packetApproveEntity.getPacketId());
                if (packetVehicleEntity != null) {
                    packetApproveResultDto.setPlateNumber(packetVehicleEntity.getPlateNumber());
                    setParkingName(map, packetApproveResultDto, packetVehicleEntity.getParkingId());
                }
                packetApproves.add(packetApproveResultDto);
            }
            pagedResultDto.setPageNo(approvePageList.getCurrent());
            pagedResultDto.setPageSize(approvePageList.getSize());
            pagedResultDto.setTotalCount(approvePageList.getTotal());
            pagedResultDto.setItems(packetApproves);

            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取取消包期申请失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 设置停车场name
     */
    private void setParkingName(Map<Long, ParkingInfoEntity> map, PacketApproveResultDto packetApproveResultDto, Long parkingId){
        if (map.keySet().contains(parkingId)) {
            ParkingInfoEntity parkingInfoEntity = map.get(parkingId);
            packetApproveResultDto.setParkingName(parkingInfoEntity.getFullName());
        } else {
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingId);
            if (parkingInfoEntity != null) {
                packetApproveResultDto.setParkingName(parkingInfoEntity.getFullName());
                map.put(parkingId, parkingInfoEntity);
            }
        }
    }

    /**
     * 删除取消包期
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deletePacketApprove(PacketApproveDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<PacketApproveEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            packetApproveCrudService.delete(entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("删除取消包期失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }


}
