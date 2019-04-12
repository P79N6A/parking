package com.zoeeasy.cloud.gather.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.gather.domain.MagneticHeartBeatEntity;
import com.zoeeasy.cloud.gather.domain.MagneticReportRecordEntity;
import com.zoeeasy.cloud.gather.domain.MagneticStatusRecordEntity;
import com.zoeeasy.cloud.gather.enums.DetectorChangeTypeEnum;
import com.zoeeasy.cloud.gather.magnetic.MagneticHeartBeatService;
import com.zoeeasy.cloud.gather.magnetic.cst.ColumnConstant;
import com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector.*;
import com.zoeeasy.cloud.gather.magnetic.dto.result.ChangeTypeResultDto;
import com.zoeeasy.cloud.gather.magnetic.dto.result.GetLastMagneticHeartBeatAddResultDto;
import com.zoeeasy.cloud.gather.magnetic.dto.result.MagneticReportRecordQueryPageResultDto;
import com.zoeeasy.cloud.gather.service.MagneticHeartBeatCrudService;
import com.zoeeasy.cloud.gather.service.MagneticReportRecordCrudService;
import com.zoeeasy.cloud.gather.service.MagneticStatusRecordCrudService;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.ParkingLotInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingLotByCodeListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingLotGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListGetResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 地磁检测器心跳服务
 *
 * @author lhj
 */
@Service("magneticHeartBeatService")
@Slf4j
public class MagneticHeartBeatServiceImpl implements MagneticHeartBeatService {

    @Autowired
    private MagneticHeartBeatCrudService magneticHeartBeatCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MagneticReportRecordCrudService magneticReportRecordCrudService;

    @Autowired
    private MagneticStatusRecordCrudService magneticStatusRecordCrudService;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private ParkingLotInfoService parkingLotInfoService;

    /**
     * 地磁心跳数据添加
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addMagneticHeartBeat(MagneticHeartBeatAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            MagneticHeartBeatEntity magneticHeartBeatEntity = modelMapper.map(requestDto, MagneticHeartBeatEntity.class);
            magneticHeartBeatCrudService.addMagneticHeartBeat(magneticHeartBeatEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("地磁检测器心跳数据插入失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 地磁检测记录添加
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto magneticReportRecordAdd(MagneticReportRecordAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            MagneticReportRecordEntity magneticReportRecordEntity = modelMapper.map(requestDto, MagneticReportRecordEntity.class);
            if (requestDto.getChangeType().equals(DetectorChangeTypeEnum.CAR_COMEIN.getValue())) {
                magneticReportRecordEntity.setBeginTime(magneticReportRecordEntity.getChangeTime());
            }
            if (requestDto.getChangeType().equals(DetectorChangeTypeEnum.CAR_COMEOUT.getValue())) {
                magneticReportRecordEntity.setEndTime(magneticReportRecordEntity.getChangeTime());
            }
            magneticReportRecordCrudService.addMagneticReportRecord(magneticReportRecordEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("地磁检测记录插入失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页获取地磁检测记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<MagneticReportRecordQueryPageResultDto> getMagneticReportRecordPageList(MagneticReportRecordQueryPageRequestDto requestDto) {
        PagedResultDto<MagneticReportRecordQueryPageResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<MagneticReportRecordEntity> entityWrapper = new EntityWrapper<>();
            ParkingListGetRequestDto parkingListGetRequestDto = new ParkingListGetRequestDto();
            ParkingLotByCodeListGetRequestDto parkingLotByCodeListGetRequestDto = new ParkingLotByCodeListGetRequestDto();
            List<Long> parkingIds = new ArrayList<>();
            List<Long> parkingLotIds = new ArrayList<>();
            //区域
            if (!StringUtils.isEmpty(requestDto.getAreaCode())) {
                parkingListGetRequestDto.setAreaCode(requestDto.getAreaCode());
            }
            //停车场类型
            if (!StringUtils.isEmpty(requestDto.getLotType())) {
                parkingListGetRequestDto.setLotType(requestDto.getLotType());
            }
            //停车场名称
            if (!StringUtils.isEmpty(requestDto.getParkingName())) {
                parkingListGetRequestDto.setName(requestDto.getParkingName());
            }
            if (StringUtils.isNotEmpty(parkingListGetRequestDto.getAreaCode()) || StringUtils.isNotEmpty(parkingListGetRequestDto.getLotType()) || StringUtils.isNotEmpty(parkingListGetRequestDto.getName())) {
                ListResultDto<ParkingListGetResultDto> parkingListGetResultDtoListResultDto = parkingInfoService.getParkingList(parkingListGetRequestDto);
                List<ParkingListGetResultDto> parkingListGetResultDtoListResultDtoItems = parkingListGetResultDtoListResultDto.getItems();
                //停车场
                for (ParkingListGetResultDto parkingListGetResultDto : parkingListGetResultDtoListResultDtoItems) {
                    parkingIds.add(parkingListGetResultDto.getId());
                }
                if (CollectionUtils.isEmpty(parkingIds)) {
                    pagedResultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                    pagedResultDto.success();
                    return pagedResultDto;
                }
                entityWrapper.in(ColumnConstant.PARKING_ID, parkingIds);
            }
            if (!StringUtils.isEmpty(requestDto.getCode())) {
                parkingLotByCodeListGetRequestDto.setCode(requestDto.getCode());
                ListResultDto<ParkingLotResultDto> parkingLotList = parkingLotInfoService.getParkingLotByCodeList(parkingLotByCodeListGetRequestDto);
                List<ParkingLotResultDto> parkingLotListItems = parkingLotList.getItems();
                for (ParkingLotResultDto parkingLotResultDto : parkingLotListItems) {
                    parkingLotIds.add(parkingLotResultDto.getId());
                }
                if (CollectionUtils.isEmpty(parkingLotIds)) {
                    pagedResultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                    pagedResultDto.success();
                    return pagedResultDto;
                }
                entityWrapper.in(ColumnConstant.PARKING_LOT_ID, parkingLotIds);
            }
            //开始时间
            if (null != requestDto.getBeginTime()) {
                entityWrapper.ge(ColumnConstant.CHANGE_TIME, requestDto.getBeginTime());
            }
            //结束时间
            if (null != requestDto.getEndTime()) {
                entityWrapper.le(ColumnConstant.CHANGE_TIME, requestDto.getEndTime());
            }
            entityWrapper.orderBy("creationTime", false);
            Page<MagneticReportRecordEntity> parkingLotPageList = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<MagneticReportRecordEntity> parkingLotStatusPage = magneticReportRecordCrudService.selectPage(parkingLotPageList, entityWrapper);
            if (parkingLotStatusPage.getRecords() != null) {
                List<MagneticReportRecordQueryPageResultDto> magneticReportRecordQueryPageResultDtos = new ArrayList<>();
                for (MagneticReportRecordEntity magneticReportRecordEntity : parkingLotStatusPage.getRecords()) {
                    MagneticReportRecordQueryPageResultDto inspectionRecordResultDto = buildInspectionRecordSearch(magneticReportRecordEntity);
                    magneticReportRecordQueryPageResultDtos.add(inspectionRecordResultDto);
                }
                pagedResultDto.setPageNo(parkingLotStatusPage.getCurrent());
                pagedResultDto.setPageSize(parkingLotStatusPage.getSize());
                pagedResultDto.setTotalCount(parkingLotStatusPage.getTotal());
                pagedResultDto.setItems(magneticReportRecordQueryPageResultDtos);
                pagedResultDto.success();
            }
        } catch (Exception e) {
            log.error("分页获取地磁检测记录失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取停车场、泊位、出入场时间、时长
     *
     * @param magneticReportRecordEntity
     * @return
     */
    private MagneticReportRecordQueryPageResultDto buildInspectionRecordSearch(MagneticReportRecordEntity magneticReportRecordEntity) {
        MagneticReportRecordQueryPageResultDto magneticReportRecordQueryPageResultDto = new MagneticReportRecordQueryPageResultDto();
        EntityWrapper<MagneticReportRecordEntity> entityWrapper = new EntityWrapper<>();
        //停车场
        ParkingGetRequestDto parkingGetRequestDto = new ParkingGetRequestDto();
        parkingGetRequestDto.setId(magneticReportRecordEntity.getParkingId());
        ObjectResultDto<ParkingResultDto> parkingObjectResultDto = parkingInfoService.getParking(parkingGetRequestDto);
        if (null != parkingObjectResultDto.getData() && parkingObjectResultDto.isSuccess()) {
            magneticReportRecordQueryPageResultDto.setParkingName(parkingObjectResultDto.getData().getFullName());
        }
        //泊位
        ParkingLotGetRequestDto parkingLotGetRequestDto = new ParkingLotGetRequestDto();
        parkingLotGetRequestDto.setId(magneticReportRecordEntity.getParkingLotId());
        ObjectResultDto<ParkingLotResultDto> parkingLotObjectResultDto = parkingLotInfoService.getParkingLot(parkingLotGetRequestDto);
        if (null != parkingLotObjectResultDto.getData() && !parkingLotObjectResultDto.isFailed()) {
            magneticReportRecordQueryPageResultDto.setParkingLotNumber(parkingLotObjectResultDto.getData().getNumber());
        }
        //出车类型
        String detectorChangeType = getDetectorChangeType(magneticReportRecordEntity);
        magneticReportRecordQueryPageResultDto.setChangeType(detectorChangeType);
        //出入场时间
        if (null != magneticReportRecordEntity.getBeginTime()) {
            magneticReportRecordQueryPageResultDto.setBeginTime(magneticReportRecordEntity.getBeginTime());
        }
        if (null != magneticReportRecordEntity.getEndTime()) {
            magneticReportRecordQueryPageResultDto.setEndTime(magneticReportRecordEntity.getEndTime());
        }
        Date begin;
        //入车时间
        entityWrapper.eq(ColumnConstant.PARKING_ID, magneticReportRecordEntity.getParkingId());
        entityWrapper.eq(ColumnConstant.PARKING_LOT_ID, magneticReportRecordEntity.getParkingLotId());
        entityWrapper.eq(ColumnConstant.DETECTOR_ID, magneticReportRecordEntity.getDetectorId());
        Date end = null;
        if (magneticReportRecordEntity.getEndTime() != null) {
            end = magneticReportRecordEntity.getEndTime();
        }
        List<MagneticReportRecordEntity> recordEntities = magneticReportRecordCrudService.selectList(entityWrapper);
        if (!recordEntities.isEmpty()) {
            for (MagneticReportRecordEntity reportRecordEntity : recordEntities) {
                begin = reportRecordEntity.getBeginTime();
                Long passTime;
                if (begin != null && end != null && begin.getTime() <= end.getTime()) {
                    passTime = (DateTimeUtils.getSecondDiff(end, begin)) / 60;
                    magneticReportRecordQueryPageResultDto.setStopTime(passTime);
                }
            }
        }
        return magneticReportRecordQueryPageResultDto;
    }

    public String getDetectorChangeType(MagneticReportRecordEntity magneticReportRecordEntity) {
        String type = null;
        DetectorChangeTypeEnum typeEnum = DetectorChangeTypeEnum.parse(magneticReportRecordEntity.getChangeType());
        if (typeEnum != null) {
            type = typeEnum.getComment();
        }
        return type;
    }

    /**
     * 出车类型列表
     *
     * @return
     */
    @Override
    public ListResultDto<ChangeTypeResultDto> getPassingTypeList(ChangeTypeListRequestDto requestDto) {
        ListResultDto<ChangeTypeResultDto> listResultDto = new ListResultDto<>();
        try {
            List<ChangeTypeResultDto> list = new ArrayList<>();
            for (DetectorChangeTypeEnum detectorChangeTypeEnum : DetectorChangeTypeEnum.values()) {
                ChangeTypeResultDto changeTypeResultDto = new ChangeTypeResultDto();
                changeTypeResultDto.setChangeType(detectorChangeTypeEnum.getValue());
                changeTypeResultDto.setChangeTypeName(detectorChangeTypeEnum.getComment());
                list.add(changeTypeResultDto);
            }
            listResultDto.setItems(list);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取出车类型列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 地磁检测器状态变更推送数据添加
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addMagneticStatusRecord(MagneticStatusRecordAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            MagneticStatusRecordEntity magneticStatusRecordEntity = modelMapper.map(requestDto, MagneticStatusRecordEntity.class);
            magneticStatusRecordCrudService.addMagneticStatusRecord(magneticStatusRecordEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("地磁检测器状态变更推送数据添加失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 查询当前地磁检测器最近一条心跳数据
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<GetLastMagneticHeartBeatAddResultDto> getLastMagneticHeartBeat(GetLastMagneticHeartBeatRequestDto requestDto) {
        ObjectResultDto<GetLastMagneticHeartBeatAddResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            List<MagneticHeartBeatEntity> magneticHeartBeatEntityList = magneticHeartBeatCrudService.getMagneticHeartBeatList(requestDto.getDetectorId(), requestDto.getProvider());
            if (CollectionUtils.isNotEmpty(magneticHeartBeatEntityList)) {
                GetLastMagneticHeartBeatAddResultDto getLastMagneticHeartBeatAddResultDto = modelMapper.map(magneticHeartBeatEntityList.get(0), GetLastMagneticHeartBeatAddResultDto.class);
                objectResultDto.setData(getLastMagneticHeartBeatAddResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("查询当前地磁检测器最近一条心跳数据失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
