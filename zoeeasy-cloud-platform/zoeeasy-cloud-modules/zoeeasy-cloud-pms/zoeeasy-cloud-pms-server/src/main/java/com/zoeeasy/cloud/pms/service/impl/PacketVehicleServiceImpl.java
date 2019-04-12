package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.core.enums.CarTypeEnum;
import com.zoeeasy.cloud.core.enums.LicensePlateTypeEnum;
import com.zoeeasy.cloud.pms.domain.*;
import com.zoeeasy.cloud.pms.enums.*;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListGetResultDto;
import com.zoeeasy.cloud.pms.service.*;
import com.zoeeasy.cloud.pms.specialvehicle.PacketVehicleService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.*;
import com.zoeeasy.cloud.pms.specialvehicle.validator.PacketVehicleAddRequestDtoValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 包期车管理服务
 * Created by song on 2018/10/15.
 */
@Service("packetVehicleService")
@Slf4j
public class PacketVehicleServiceImpl implements PacketVehicleService {

    @Autowired
    private PacketVehicleCrudService packetVehicleCrudService;

    @Autowired
    private VehicleRecordCrudService vehicleRecordCrudService;

    @Autowired
    private ParkingPacketRuleCrudService parkingPacketRuleCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private PacketRuleCrudService packetRuleCrudService;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 添加包期车
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<PacketReceiptResultDto> addPacketVehicle(@FluentValid({PacketVehicleAddRequestDtoValidator.class}) PacketVehicleAddRequestDto requestDto) {
        ObjectResultDto<PacketReceiptResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            //先将该车加入平台车辆
            VehicleRecordEntity vehicleRecordExist = vehicleRecordCrudService.findByPlateNumber(requestDto.getPlateNumber());
            if (vehicleRecordExist == null) {
                vehicleRecordExist = modelMapper.map(requestDto, VehicleRecordEntity.class);
                vehicleRecordCrudService.insert(vehicleRecordExist);
            }
            PacketVehicleEntity packetVehicleEntity = modelMapper.map(requestDto, PacketVehicleEntity.class);
            packetVehicleEntity.setEffectedStatus(EffectedStatusEnum.NO_EFFECTED.getValue());
            packetVehicleEntity.setTopUpStatus(TopUpStatusEnum.NO_TOP_UP.getValue());
            packetVehicleEntity.setAllParking(requestDto.getAllParking());
            packetVehicleEntity.setCustomerUserId(vehicleRecordExist.getId());
            List<ParkingPacketRuleDto> parkingPacketRuleDtos = null;
            if (requestDto.getAllParking().equals(AllParkingEnum.YES.getValue())) {
                //查询该用户的所有停车场的包年或包月规则
                List<ParkingInfoEntity> parkings = parkingInfoCrudService.selectAllParkingInfoList();
                List<Long> parkingIds = new ArrayList<>();
                if (CollectionUtils.isNotEmpty(parkings)) {
                    for (ParkingInfoEntity parkingInfoEntity : parkings) {
                        parkingIds.add(parkingInfoEntity.getId());
                    }
                    List<ParkingPacketRuleEntity> parkingPacketRuleEntities = parkingPacketRuleCrudService.selectParkingPacketRules(requestDto.getPacketType(),
                            parkingIds);
                    parkingPacketRuleDtos = modelMapper.map(parkingPacketRuleEntities, new TypeToken<List<ParkingPacketRuleDto>>() {
                    }.getType());
                }
            } else {
                parkingPacketRuleDtos = requestDto.getRules();
            }
            PacketReceiptResultDto packetReceiptResultDto = new PacketReceiptResultDto();
            packetReceiptResultDto.setOwnerName(requestDto.getOwnerName());
            packetReceiptResultDto.setPlateNumber(requestDto.getPlateNumber());
            packetReceiptResultDto.setTime(new Date());
            List<ParkingPacketRuleResultDto> parkingPacketRules = new ArrayList<>();
            Integer amount = 0;
            if (CollectionUtils.isNotEmpty(parkingPacketRuleDtos)) {
                //根据传过来的规则id添加
                for (ParkingPacketRuleDto ruleDto : parkingPacketRuleDtos) {
                    packetVehicleEntity.setParkingId(ruleDto.getParkingId());
                    packetVehicleEntity.setRuleId(ruleDto.getRuleId());
                    packetVehicleCrudService.insert(packetVehicleEntity);
                    ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(ruleDto.getParkingId());
                    PacketRuleEntity packetRuleEntity = packetRuleCrudService.selectById(ruleDto.getRuleId());
                    ParkingPacketRuleResultDto parkingPacketRuleResultDto = new ParkingPacketRuleResultDto();
                    parkingPacketRuleResultDto.setParkingName(parkingInfoEntity.getFullName());
                    parkingPacketRuleResultDto.setPacketName(packetRuleEntity.getPacketName());
                    parkingPacketRuleResultDto.setCount(1);
                    parkingPacketRuleResultDto.setPrice(packetRuleEntity.getPrice());
                    amount = amount + packetRuleEntity.getPrice();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    parkingPacketRuleResultDto.setValidTime(simpleDateFormat.format(requestDto.getBeginDate()) + " 至 " +
                            simpleDateFormat.format(requestDto.getEndDate()));
                    parkingPacketRules.add(parkingPacketRuleResultDto);
                }
            } else {
                objectResultDto.failed();
                return objectResultDto;
            }
            packetReceiptResultDto.setParkingPacketRules(parkingPacketRules);
            packetReceiptResultDto.setAmount(amount);
            objectResultDto.setData(packetReceiptResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("添加包期车失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 分页获取包期车
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<PacketVehiclePagedResultDto> getPacketVehiclePagedList(PacketVehiclePagedRequestDto requestDto) {
        PagedResultDto<PacketVehiclePagedResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<PacketVehicleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.orderBy("creationTime", false);
            if (!StringUtils.isEmpty(requestDto.getPlateNumber())) {
                entityWrapper.like("plateNumber", requestDto.getPlateNumber());
            }
            if (requestDto.getPlateColor() != null) {
                entityWrapper.eq("plateColor", requestDto.getPlateColor());
            }
            if (requestDto.getAllParking() != null) {
                entityWrapper.eq("allParking", requestDto.getAllParking());
            }
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
            entityWrapper.in("parkingId", parkingIds);
            List<Long> ruleIds = new ArrayList<>();
            EntityWrapper<PacketRuleEntity> packetRuleWrapper = new EntityWrapper<>();
            if (requestDto.getPacketType() != null) {
                packetRuleWrapper.eq("packetType", requestDto.getPacketType());
            }
            List<PacketRuleEntity> packetRuleEntities = packetRuleCrudService.selectList(packetRuleWrapper);
            for (PacketRuleEntity packetRuleEntity : packetRuleEntities) {
                ruleIds.add(packetRuleEntity.getId());
            }
            if (CollectionUtils.isNotEmpty(ruleIds)) {
                entityWrapper.in("ruleId", ruleIds);
            } else {
                pagedResultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                pagedResultDto.success();
                return pagedResultDto;
            }

            Page<PacketVehicleEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<PacketVehicleEntity> packetVehiclePageList = packetVehicleCrudService.selectPage(page, entityWrapper);

            Map<Long, ParkingInfoEntity> map = new HashMap<>();
            List<PacketVehiclePagedResultDto> packetVehicles = new ArrayList<>();
            for (PacketVehicleEntity packetVehicleEntity : packetVehiclePageList.getRecords()) {
                PacketVehiclePagedResultDto packetVehiclePagedResultDto = new PacketVehiclePagedResultDto();
                packetVehiclePagedResultDto.setId(packetVehicleEntity.getId());
                packetVehiclePagedResultDto.setPlateNumber(packetVehicleEntity.getPlateNumber());
                packetVehiclePagedResultDto.setAllParking(AllParkingEnum.parse(packetVehicleEntity.getAllParking()).getComment());
                packetVehiclePagedResultDto.setPlateColor(PlateColorEnum.parse(packetVehicleEntity.getPlateColor()).getComment());
                packetVehiclePagedResultDto.setOwnerName(packetVehicleEntity.getOwnerName());
                packetVehiclePagedResultDto.setOwnerPhone(packetVehicleEntity.getOwnerPhone());
                packetVehiclePagedResultDto.setBeginDate(packetVehicleEntity.getBeginDate());
                packetVehiclePagedResultDto.setEndDate(packetVehicleEntity.getEndDate());
                packetVehiclePagedResultDto.setTopUpStatus(packetVehicleEntity.getTopUpStatus());
                setParkingName(map, packetVehiclePagedResultDto, packetVehicleEntity.getParkingId());
                PacketRuleEntity packetRuleEntity = packetRuleCrudService.selectById(packetVehicleEntity.getRuleId());
                packetVehiclePagedResultDto.setPacketType(PacketTypeEnum.parse(packetRuleEntity.getPacketType()).getComment());
                packetVehicles.add(packetVehiclePagedResultDto);
            }
            pagedResultDto.setPageNo(packetVehiclePageList.getCurrent());
            pagedResultDto.setPageSize(packetVehiclePageList.getSize());
            pagedResultDto.setTotalCount(packetVehiclePageList.getTotal());
            pagedResultDto.setItems(packetVehicles);

            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取包期车失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 设置停车场name
     */
    private void setParkingName(Map<Long, ParkingInfoEntity> map, PacketVehiclePagedResultDto packetVehiclePagedResultDto, Long parkingId) {
        if (map.keySet().contains(parkingId)) {
            ParkingInfoEntity parkingInfoEntity = map.get(parkingId);
            packetVehiclePagedResultDto.setParkingName(parkingInfoEntity.getFullName());
        } else {
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingId);
            if (parkingInfoEntity != null) {
                packetVehiclePagedResultDto.setParkingName(parkingInfoEntity.getFullName());
                map.put(parkingId, parkingInfoEntity);
            }
        }
    }

    /**
     * 更改支付状态
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateTopUp(TopUpUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            PacketVehicleEntity packetVehicleEntity = new PacketVehicleEntity();
            packetVehicleEntity.setTopUpStatus(TopUpStatusEnum.YET_TOP_UP.getValue());
            EntityWrapper<PacketVehicleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            packetVehicleCrudService.update(packetVehicleEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("更改支付状态失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除包期车
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deletePacketVehicle(PacketVehicleDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<PacketVehicleEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            packetVehicleCrudService.delete(entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("删除包期车失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取包期车详情
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<PacketVehicleGetResultDto> getPacketVehicle(PacketVehicleGetRequestDto requestDto) {
        ObjectResultDto<PacketVehicleGetResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            PacketVehicleEntity packetVehicleEntity = packetVehicleCrudService.selectById(requestDto.getId());
            if (packetVehicleEntity != null) {
                VehicleRecordEntity vehicleRecordEntity = vehicleRecordCrudService.selectById(packetVehicleEntity.getCustomerUserId());
                PacketVehicleGetResultDto packetVehicleGetResultDto = new PacketVehicleGetResultDto();
                packetVehicleGetResultDto.setPlateNumber(packetVehicleEntity.getPlateNumber());
                if (packetVehicleEntity.getPlateColor() != null) {
                    packetVehicleGetResultDto.setPlateColor(PlateColorEnum.parse(packetVehicleEntity.getPlateColor()).getComment());
                }
                if (packetVehicleEntity.getCarColor() != null) {
                    packetVehicleGetResultDto.setCarColor(CarColorEnum.parse(packetVehicleEntity.getCarColor()).getComment());
                }
                if (packetVehicleEntity.getPlateType() != null) {
                    packetVehicleGetResultDto.setPlateType(LicensePlateTypeEnum.parse(packetVehicleEntity.getPlateType()).getName());
                }
                if (packetVehicleEntity.getCarType() != null) {
                    packetVehicleGetResultDto.setCarType(CarTypeEnum.parse(packetVehicleEntity.getCarType()).getComment());
                }
                packetVehicleGetResultDto.setOwnerName(packetVehicleEntity.getOwnerName());
                packetVehicleGetResultDto.setOwnerPhone(packetVehicleEntity.getOwnerPhone());
                packetVehicleGetResultDto.setOwnerCardNo(packetVehicleEntity.getOwnerCardNo());
                packetVehicleGetResultDto.setOwnerAddress(packetVehicleEntity.getOwnerAddress());
                packetVehicleGetResultDto.setOwnerEmail(packetVehicleEntity.getOwnerEmail());
                if (vehicleRecordEntity != null) {
                    packetVehicleGetResultDto.setCarBrand(vehicleRecordEntity.getCarBrand());
                }
                objectResultDto.setData(packetVehicleGetResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取包期车详情失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取结束时间
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<EndDateResultDto> getEndDate(EndDateRequestDto requestDto) {
        ObjectResultDto<EndDateResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EndDateResultDto endDateResultDto = new EndDateResultDto();
            Date beginDate = requestDto.getBeginDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(beginDate);
            Date endDate = null;
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DATE);
            if (requestDto.getPacketType() == PacketTypeEnum.PACKET_MONTH.getValue()) {
                calendar.add(Calendar.MONTH, 1);
                if (day == 1) {
                    calendar.add(Calendar.DATE, -1);
                    endDate = calendar.getTime();
                } else {
                    endDate = calendar.getTime();
                }
            } else {
                calendar.add(Calendar.YEAR, 1);
                if (month + 1 == 1 && day == 1) {
                    calendar.add(Calendar.DATE, -1);
                }
                endDate = calendar.getTime();
            }
            endDateResultDto.setEndDate(endDate);
            objectResultDto.setData(endDateResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取结束时间失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
