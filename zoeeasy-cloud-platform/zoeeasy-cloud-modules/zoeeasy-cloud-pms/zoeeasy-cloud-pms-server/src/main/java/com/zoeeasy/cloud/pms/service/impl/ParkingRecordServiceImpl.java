package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.core.enums.CarTypeEnum;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingLotInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingRecordEntity;
import com.zoeeasy.cloud.pms.enums.PlateColorEnum;
import com.zoeeasy.cloud.pms.park.ParkingRecordService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingRecordPagedResultRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingRecordViewResultDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingRecordAddRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingRecordQueryByIntoRecordNoRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingRecordUpdateIntegrationRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.SaveParkingRecordResultDto;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingLotInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingRecordCrudService;
import com.zoeeasy.cloud.pms.service.ParkingRecordImageCrudService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AkeemSuper
 * @date 2018/9/12 0012
 */
@Service("parkingRecordService")
@Slf4j
public class ParkingRecordServiceImpl implements ParkingRecordService {

    @Autowired
    private ParkingRecordCrudService parkingRecordCrudService;

    @Autowired
    private ParkingRecordImageCrudService parkingRecordImageCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 分页查询停车记录
     *
     * @param requestDto
     */
    @Override
    public PagedResultDto<ParkingRecordViewResultDto> getParkingRecordPageList(ParkingRecordPagedResultRequestDto requestDto) {
        PagedResultDto<ParkingRecordViewResultDto> resultDto = new PagedResultDto<>();
        EntityWrapper<ParkingRecordEntity> entityWrapper = new EntityWrapper<>();
        try {
            if (StringUtils.isNotEmpty(requestDto.getRecordNo()) && StringUtils.isNumbers(requestDto.getRecordNo(),
                    true)) {
                entityWrapper.eq("recordNo", requestDto.getRecordNo());
            }
            if (StringUtils.isNotEmpty(requestDto.getThirdParkingRecordId())) {
                entityWrapper.eq("thirdParkingRecordId", requestDto.getThirdParkingRecordId());
            }
            if (StringUtils.isNotEmpty(requestDto.getAliParkingRecordId())) {
                entityWrapper.eq("aliParkingRecordId", requestDto.getAliParkingRecordId());
            }
            if (requestDto.getParkingId() != null) {
                ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(requestDto.getParkingId());
                if (parkingInfoEntity == null) {
                    resultDto.success();
                    return resultDto;
                }
                entityWrapper.eq("parkingId", requestDto.getParkingId());
            }
            if (StringUtils.isNotEmpty(requestDto.getHikParkingId())) {
                entityWrapper.eq("hikParkingId", requestDto.getHikParkingId());
            }
            if (StringUtils.isNotEmpty(requestDto.getAliParkingId())) {
                entityWrapper.eq("aliParkingId", requestDto.getAliParkingId());
            }
            if (StringUtils.isNotEmpty(requestDto.getParkingLotNumber())) {
                Wrapper<ParkingLotInfoEntity> lotInfoEntityEntityWrapper = new EntityWrapper<>();
                lotInfoEntityEntityWrapper.like("number", requestDto.getParkingLotNumber());
                List<ParkingLotInfoEntity> parkingLotInfoEntities = parkingLotInfoCrudService.selectList(lotInfoEntityEntityWrapper);
                if (parkingLotInfoEntities.isEmpty()) {
                    resultDto.success();
                    return resultDto;
                } else {
                    List<Long> arkingLotInfoId = parkingLotInfoEntities.stream().map(ParkingLotInfoEntity::getId).collect(Collectors.toList());
                    entityWrapper.in("parkingLotId", arkingLotInfoId);
                }
            }
            if (StringUtils.isNotEmpty(requestDto.getHikParkingLotId())) {
                entityWrapper.eq("hikParkingLotId", requestDto.getHikParkingLotId());
            }
            if (StringUtils.isNotEmpty(requestDto.getAliParkingLotId())) {
                entityWrapper.eq("aliParkingLotId", requestDto.getAliParkingLotId());
            }
            if (StringUtils.isNotEmpty(requestDto.getPlateNumber())) {
                entityWrapper.like("plateNumber", requestDto.getPlateNumber());
            }
            if (requestDto.getPlateColor() != null) {
                PlateColorEnum plateColorEnum = PlateColorEnum.parse(requestDto.getPlateColor());
                if (plateColorEnum != null) {
                    entityWrapper.eq("plateColor", requestDto.getPlateColor());
                }
            }
            if (requestDto.getCarStyle() != null) {
                CarTypeEnum carTypeEnum = CarTypeEnum.parse(requestDto.getCarStyle());
                if (carTypeEnum != null) {
                    entityWrapper.eq("carType", requestDto.getCarStyle());
                }
            }
            if (requestDto.getStartTime() != null) {
                entityWrapper.ge("startTime", requestDto.getStartTime());
            }
            if (requestDto.getEndTime() != null) {
                entityWrapper.le("endTime", requestDto.getEndTime());
            }
            if (requestDto.getStatus() != null) {
                entityWrapper.eq("status", requestDto.getStatus());
            }
            entityWrapper.orderBy("startTime", false);
            Page<ParkingRecordEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingRecordEntity> parkingRecordPage = parkingRecordCrudService.selectPage(page, entityWrapper);
            if (parkingRecordPage != null && null != parkingRecordPage.getRecords()) {
                List<ParkingRecordEntity> records = parkingRecordPage.getRecords();
                List<ParkingRecordViewResultDto> parkingResultDtoList = new ArrayList<>();
                for (ParkingRecordEntity parkingRecord : records) {
                    ParkingRecordViewResultDto parkingRecordResultDto = buildParkingRecordResult(parkingRecord);
                    if (parkingRecordResultDto != null) {
                        parkingResultDtoList.add(parkingRecordResultDto);
                    }
                }
                resultDto.setPageNo(parkingRecordPage.getCurrent());
                resultDto.setPageSize(parkingRecordPage.getSize());
                resultDto.setTotalCount(parkingRecordPage.getTotal());
                resultDto.setItems(parkingResultDtoList);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("分页查询平台停车记录失败", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 更新停车记录
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateParkingRecord(ParkingRecordUpdateIntegrationRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            
            Wrapper<ParkingRecordEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            if (requestDto.getParkingId() != null) {
                entityWrapper.eq("parkingId", requestDto.getParkingId());
            }
            ParkingRecordEntity updateRecordEntity = modelMapper.map(requestDto, ParkingRecordEntity.class);
            boolean update = parkingRecordCrudService.updateParkingRecordByParkingIdAndId(updateRecordEntity);
            if (!update) {
                return resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            resultDto.failed();
            log.error("更新停车记录失败" + e.getMessage());
        }
        return resultDto;
    }

    /**
     * 保存停车记录
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<SaveParkingRecordResultDto> saveParkingRecord(ParkingRecordAddRequestDto requestDto) {
        ObjectResultDto resultDto = new ObjectResultDto();
        try {
            SaveParkingRecordResultDto saveParkingRecordResultDto = new SaveParkingRecordResultDto();
            ParkingRecordEntity map = modelMapper.map(requestDto, ParkingRecordEntity.class);
            map.setCreationTime(DateUtils.now());
            boolean insert = parkingRecordCrudService.insert(map);
            if (insert) {
                saveParkingRecordResultDto.setId(map.getId());
                resultDto.setData(saveParkingRecordResultDto);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("保存停车记录失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    private ParkingRecordViewResultDto buildParkingRecordResult(ParkingRecordEntity parkingRecord) {

        if (parkingRecord == null) {
            return null;
        }
        ParkingRecordViewResultDto parkingRecordResultDto = new ParkingRecordViewResultDto();
        parkingRecordResultDto.setRecordNo(parkingRecord.getRecordNo());
        parkingRecordResultDto.setThirdParkingRecordId(parkingRecord.getThirdParkingRecordId());
        parkingRecordResultDto.setAliParkingId(parkingRecord.getAliParkingRecordId());
        parkingRecordResultDto.setIntoRecordNo(parkingRecord.getIntoRecordNo());
        parkingRecordResultDto.setOutRecordNo(parkingRecord.getOutRecordNo());
        parkingRecordResultDto.setStatus(parkingRecord.getStatus());
        parkingRecordResultDto.setAppointed(parkingRecord.getAppointed());
        parkingRecordResultDto.setAppointOrderNo(parkingRecord.getAppointOrderNo());
        //获取停车场信息
        parkingRecordResultDto.setParkingId(parkingRecord.getParkingId());
        parkingRecordResultDto.setParkingName(parkingRecord.getParkingName());
        //获取车位信息
        parkingRecordResultDto.setParkingLotNumber(parkingRecord.getParkingLotNumber());
        //停车开始时间
        if (parkingRecord.getStartTime() != null) {
            parkingRecordResultDto.setStartTime(parkingRecord.getStartTime());
        }
        //结束时间
        if (parkingRecord.getEndTime() == null || parkingRecord.getEndTime().compareTo(DateTimeUtils.getDateMax()) == 0) {
            parkingRecordResultDto.setEndTime(null);
        } else {
            parkingRecordResultDto.setEndTime(parkingRecord.getEndTime());
        }
        parkingRecordResultDto.setPlateNumber(parkingRecord.getPlateNumber());
        parkingRecordResultDto.setPlateColor(parkingRecord.getPlateColor());
        parkingRecordResultDto.setCarType(parkingRecord.getCarType());
        return parkingRecordResultDto;
    }

    /**
     * 根据入车流水号修改泊位
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateParkingRecordByIntoRecordNo(ParkingRecordQueryByIntoRecordNoRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<ParkingRecordEntity> parkingRecordEntityWrapper = new EntityWrapper<>();
            if (StringUtils.isNotEmpty(requestDto.getIntoRecordNo())) {
                parkingRecordEntityWrapper.eq("intoRecordNo", requestDto.getIntoRecordNo());
            }
            ParkingRecordEntity parkingRecordEntity = parkingRecordCrudService.selectByIntoRecordNo(parkingRecordEntityWrapper);
            if (null != parkingRecordEntity) {
                //泊位修改
                parkingRecordEntity.setParkingLotId(requestDto.getParkingLotId());
                parkingRecordEntity.setParkingLotCode(requestDto.getParkingLotCode());
                parkingRecordEntity.setParkingLotNumber(requestDto.getParkingLotNumber());
                parkingRecordCrudService.updateParkingLot(parkingRecordEntity);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("根据入车流水号修改泊位失败：" + e.getMessage(), e);
            resultDto.failed();
        }
        return resultDto;
    }

}
