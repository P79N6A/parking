package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.domain.PacketRuleEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingPacketRuleEntity;
import com.zoeeasy.cloud.pms.enums.AllParkingEnum;
import com.zoeeasy.cloud.pms.enums.PacketTypeEnum;
import com.zoeeasy.cloud.pms.enums.PlateColorEnum;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListGetResultDto;
import com.zoeeasy.cloud.pms.service.PacketRuleCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingPacketRuleCrudService;
import com.zoeeasy.cloud.pms.specialvehicle.PacketRuleService;
import com.zoeeasy.cloud.pms.specialvehicle.cst.PacketConstant;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketRuleListGetResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketRuleQueryPagedResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketRuleResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.PacketRuleAddRequestDtoValidator;
import com.zoeeasy.cloud.pms.specialvehicle.validator.PacketRuleDeleteRequestDtoValidator;
import com.zoeeasy.cloud.pms.specialvehicle.validator.PacketRuleUpdateRequestDtoValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
@Service("packetRuleService")
@Slf4j
public class PacketRuleServiceImpl implements PacketRuleService {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private PacketRuleCrudService packetRuleCrudService;

    @Autowired
    private ParkingPacketRuleCrudService parkingPacketRuleCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 添加包期规则
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto addPacketRule(@FluentValid({PacketRuleAddRequestDtoValidator.class}) PacketRuleAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            PacketRuleEntity packetRuleEntity = modelMapper.map(requestDto, PacketRuleEntity.class);
            packetRuleCrudService.insert(packetRuleEntity);
            if (AllParkingEnum.YES.getValue().equals(requestDto.getAllParking())) {
                //查询所有的停车场
                List<ParkingInfoEntity> parkings = parkingInfoCrudService.selectAllParkingInfoList();
                for (ParkingInfoEntity parkingInfoEntity : parkings) {
                    ParkingPacketRuleEntity parkingPacketRuleEntity = new ParkingPacketRuleEntity();
                    parkingPacketRuleEntity.setParkingId(parkingInfoEntity.getId());
                    parkingPacketRuleEntity.setRuleId(packetRuleEntity.getId());
                    parkingPacketRuleCrudService.insert(parkingPacketRuleEntity);
                }
            } else {
                if (requestDto.getParkingIds() != null) {
                    for (Long parkingId : requestDto.getParkingIds()) {
                        ParkingPacketRuleEntity parkingPacketRuleEntity = new ParkingPacketRuleEntity();
                        parkingPacketRuleEntity.setParkingId(parkingId);
                        parkingPacketRuleEntity.setRuleId(packetRuleEntity.getId());
                        parkingPacketRuleCrudService.insert(parkingPacketRuleEntity);
                    }
                }
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("添加包期规则失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除包期规则
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto deletePacketRule(@FluentValid(value = {PacketRuleDeleteRequestDtoValidator.class}) PacketRuleDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingPacketRuleEntity parkingPacketRuleEntity = parkingPacketRuleCrudService.selectById(requestDto.getId());
            parkingPacketRuleCrudService.deleteById(requestDto.getId());
            EntityWrapper<ParkingPacketRuleEntity> packetRuleEntityWrapper = new EntityWrapper<>();
            packetRuleEntityWrapper.eq(PacketConstant.PACKET_ID, parkingPacketRuleEntity.getRuleId());
            List<ParkingPacketRuleEntity> parkingPacketRuleEntities = parkingPacketRuleCrudService.selectList(packetRuleEntityWrapper);
            if (parkingPacketRuleEntities.isEmpty()) {
                packetRuleCrudService.deleteById(parkingPacketRuleEntity.getRuleId());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("删除包期规则失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改包期规则
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updatePacketRule(@FluentValid(value = {PacketRuleUpdateRequestDtoValidator.class}) PacketRuleUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            PacketRuleEntity packetRuleEntity = modelMapper.map(requestDto, PacketRuleEntity.class);
            EntityWrapper<PacketRuleEntity> entityWrapper = new EntityWrapper<>();
            ParkingPacketRuleEntity parkingPacketRuleEntity = parkingPacketRuleCrudService.selectById(requestDto.getId());
            entityWrapper.eq("id", parkingPacketRuleEntity.getRuleId());
            packetRuleCrudService.update(packetRuleEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("修改包期规则失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取包期规则
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<PacketRuleResultDto> getPacketRule(PacketRuleGetRequestDto requestDto) {
        ObjectResultDto<PacketRuleResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingPacketRuleEntity parkingPacketRuleEntity = parkingPacketRuleCrudService.selectById(requestDto.getId());
            if (parkingPacketRuleEntity != null) {
                PacketRuleEntity packetRuleEntity = packetRuleCrudService.selectById(parkingPacketRuleEntity.getRuleId());
                PacketRuleResultDto packetRuleResultDto = modelMapper.map(packetRuleEntity, PacketRuleResultDto.class);
                packetRuleResultDto.setId(parkingPacketRuleEntity.getId());
                objectResultDto.setData(packetRuleResultDto);
                packetRuleResultDto.setPrice(packetRuleEntity.getPrice());
                objectResultDto.success();
            } else {
                return objectResultDto.makeResult(PmsResultEnum.PACKET_RULE_NOT_FOUND.getValue(),
                        PmsResultEnum.PACKET_RULE_NOT_FOUND.getComment());
            }
        } catch (Exception e) {
            log.error("获取包期规则失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 分页获取包期规则列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<PacketRuleQueryPagedResultDto> getPacketRulePagedList(PacketRuleQueryPagedRequestDto requestDto) {
        PagedResultDto<PacketRuleQueryPagedResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<ParkingPacketRuleEntity> entityWrapper = new EntityWrapper<>();
            List<Long> parkingIds = new ArrayList<>();
            entityWrapper.orderBy("creationTime", false);
            EntityWrapper<PacketRuleEntity> ruleWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getPacketName())) {
                ruleWrapper.like("packetName", requestDto.getPacketName());
            }
            if (requestDto.getPacketType() != null) {
                ruleWrapper.eq("packetType", requestDto.getPacketType());
            }
            List<PacketRuleEntity> packetRules = packetRuleCrudService.selectList(ruleWrapper);
            List<Long> ruleIds = new ArrayList<>();
            for (PacketRuleEntity packetRuleEntity : packetRules) {
                ruleIds.add(packetRuleEntity.getId());
            }
            if (CollectionUtils.isNotEmpty(ruleIds)) {
                entityWrapper.in("ruleId", ruleIds);
            } else {
                pagedResultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                pagedResultDto.success();
                return pagedResultDto;
            }
            ParkingListGetRequestDto parkingListGetRequestDto = new ParkingListGetRequestDto();
            if (!StringUtils.isEmpty(requestDto.getParkingName())) {
                parkingListGetRequestDto.setName(requestDto.getParkingName());
            }
            ListResultDto<ParkingListGetResultDto> parkingList = parkingInfoService.getParkingList(parkingListGetRequestDto);
            if (!parkingList.getItems().isEmpty()) {
                for (ParkingListGetResultDto parkingListGetResultDto : parkingList.getItems()) {
                    parkingIds.add(parkingListGetResultDto.getId());
                }
            } else {
                pagedResultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                pagedResultDto.success();
                return pagedResultDto;
            }
            entityWrapper.in("parkingId", parkingIds);
            Page<ParkingPacketRuleEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingPacketRuleEntity> parkingPacketRuleEntityPage = parkingPacketRuleCrudService.selectPage(page, entityWrapper);
            List<PacketRuleQueryPagedResultDto> list = new ArrayList<>();
            for (ParkingPacketRuleEntity parkingPacketRuleEntity : parkingPacketRuleEntityPage.getRecords()) {
                PacketRuleQueryPagedResultDto packetRuleQueryPagedResultDto = get(parkingPacketRuleEntity);
                list.add(packetRuleQueryPagedResultDto);
            }
            pagedResultDto.setPageNo(parkingPacketRuleEntityPage.getCurrent());
            pagedResultDto.setPageSize(parkingPacketRuleEntityPage.getSize());
            pagedResultDto.setTotalCount(parkingPacketRuleEntityPage.getTotal());
            pagedResultDto.setItems(list);
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取包期规则列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取包期信息
     *
     * @param parkingPacketRuleEntity
     * @return
     */
    private PacketRuleQueryPagedResultDto get(ParkingPacketRuleEntity parkingPacketRuleEntity) {
        PacketRuleQueryPagedResultDto packetRuleQueryPagedResultDto = new PacketRuleQueryPagedResultDto();
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingPacketRuleEntity.getParkingId());
        PacketRuleEntity packetRuleEntity = packetRuleCrudService.selectById(parkingPacketRuleEntity.getRuleId());
        if (packetRuleEntity != null) {
            packetRuleQueryPagedResultDto.setId(parkingPacketRuleEntity.getId());
            packetRuleQueryPagedResultDto.setPacketName(packetRuleEntity.getPacketName());
            packetRuleQueryPagedResultDto.setPrice(packetRuleEntity.getPrice());
            packetRuleQueryPagedResultDto.setAllParking(AllParkingEnum.parse(packetRuleEntity.getAllParking()).getComment());
            packetRuleQueryPagedResultDto.setPlateColor(PlateColorEnum.parse(packetRuleEntity.getPlateColor()).getComment());
            packetRuleQueryPagedResultDto.setPacketType(PacketTypeEnum.parse(packetRuleEntity.getPacketType()).getComment());
            packetRuleQueryPagedResultDto.setCreationTime(packetRuleEntity.getCreationTime());
            packetRuleQueryPagedResultDto.setParkingId(parkingPacketRuleEntity.getParkingId());
        }
        if (parkingInfoEntity != null) {
            packetRuleQueryPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
        }
        return packetRuleQueryPagedResultDto;
    }

    /**
     * 获取包期规则列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<PacketRuleListGetResultDto> getPacketRuleList(PacketRuleListGetRequestDto requestDto) {
        ListResultDto<PacketRuleListGetResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<ParkingPacketRuleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.orderBy("creationTime", false);
            EntityWrapper<PacketRuleEntity> ruleWrapper = new EntityWrapper<>();
            List<ParkingPacketRuleEntity> parkingPacketRuleEntities = new ArrayList<>();
            if (requestDto.getPacketType() != null) {
                ruleWrapper.eq("packetType", requestDto.getPacketType());
            }
            List<PacketRuleEntity> packetRules = packetRuleCrudService.selectList(ruleWrapper);
            List<Long> ruleIds = new ArrayList<>();
            for (PacketRuleEntity packetRuleEntity : packetRules) {
                ruleIds.add(packetRuleEntity.getId());
            }
            if (CollectionUtils.isNotEmpty(ruleIds)) {
                entityWrapper.in(PacketConstant.PACKET_ID, ruleIds);
            } else {
                listResultDto = new PagedResultDto(new ArrayList<>(), 0L);
                listResultDto.success();
                return listResultDto;
            }
            ParkingListGetRequestDto parkingListGetRequestDto = new ParkingListGetRequestDto();
            if (requestDto.getAreaCode() != null) {
                parkingListGetRequestDto.setAreaCode(requestDto.getAreaCode());
            }
            if (!StringUtils.isEmpty(requestDto.getLotType())) {
                parkingListGetRequestDto.setLotType(requestDto.getLotType());
            }
            ListResultDto<ParkingListGetResultDto> parkingList = parkingInfoService.getParkingList(parkingListGetRequestDto);
            List<Long> parkingIds = new ArrayList<>();
            if (!parkingList.getItems().isEmpty()) {
                for (ParkingListGetResultDto parkingInfoEntity : parkingList.getItems()) {
                    parkingIds.add(parkingInfoEntity.getId());
                }
                entityWrapper.in("parkingId", parkingIds);
                parkingPacketRuleEntities = parkingPacketRuleCrudService.selectList(entityWrapper);
            }
            List<PacketRuleListGetResultDto> list = new ArrayList<>();
            for (ParkingPacketRuleEntity parkingPacketRuleEntity : parkingPacketRuleEntities) {
                PacketRuleListGetResultDto packetRuleListGetResultDto = new PacketRuleListGetResultDto();
                ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingPacketRuleEntity.getParkingId());
                PacketRuleEntity packetRuleEntity = packetRuleCrudService.selectById(parkingPacketRuleEntity.getRuleId());
                if (packetRuleEntity != null) {
                    packetRuleListGetResultDto.setId(parkingPacketRuleEntity.getRuleId());
                    packetRuleListGetResultDto.setPacketName(packetRuleEntity.getPacketName());
                    packetRuleListGetResultDto.setParkingId(parkingPacketRuleEntity.getParkingId());
                    packetRuleListGetResultDto.setPacketType(PacketTypeEnum.parse(packetRuleEntity.getPacketType()).getComment());
                    packetRuleListGetResultDto.setPrice(packetRuleEntity.getPrice());
                    packetRuleListGetResultDto.setPlateColor(PlateColorEnum.parse(packetRuleEntity.getPlateColor()).getComment());
                }
                if (parkingInfoEntity != null) {
                    packetRuleListGetResultDto.setParkingName(parkingInfoEntity.getFullName());
                }
                list.add(packetRuleListGetResultDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取包期规则列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

}
