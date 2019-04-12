package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.PlateNumberUtil;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.core.enums.CarTypeEnum;
import com.zoeeasy.cloud.pms.domain.*;
import com.zoeeasy.cloud.pms.enums.ParkingImageTypeEnum;
import com.zoeeasy.cloud.pms.enums.PlateColorEnum;
import com.zoeeasy.cloud.pms.park.ParkingVehicleRecordService;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.request.MyPlateNumberParkingGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingVehicleRecordGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingVehicleRecordQueryPageRequestDto;
import com.zoeeasy.cloud.pms.parkingarea.ParkingAreaService;
import com.zoeeasy.cloud.pms.parkingarea.dto.request.ParkingAreaGetRequestDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.ParkingAreaResultDto;
import com.zoeeasy.cloud.pms.platform.dto.request.*;
import com.zoeeasy.cloud.pms.platform.dto.result.*;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingVehicleRecordLotRequestDto;
import com.zoeeasy.cloud.pms.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author AkeemSuper
 * @date 2018/9/12 0012
 */
@Service("parkingVehicleRecordService")
@Slf4j
public class ParkingVehicleRecordServiceImpl implements ParkingVehicleRecordService {

    @Autowired
    private ParkingVehicleRecordCrudService parkingVehicleRecordCrudService;

    @Autowired
    private ParkingRecordImageCrudService parkingRecordImageCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Autowired
    ParkingAreaService parkingAreaService;

    @Autowired
    private ParkingFloorCrudService parkingFloorCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 分页获取停车场在停车辆`
     *
     * @param requestDto 分页获取停车场在停车辆请求参数
     * @return PagedResultDto
     */
    @Override
    public PagedResultDto<ParkingVehicleRecordViewResultDto> getParkingVehicleRecordListPage(ParkingVehicleRecordQueryPageRequestDto requestDto) {
        PagedResultDto<ParkingVehicleRecordViewResultDto> resultDto = new PagedResultDto<>();
        EntityWrapper<ParkingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
        try {
            if (requestDto.getParkingId() != null) {
                ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(requestDto.getParkingId());
                if (parkingInfoEntity == null) {
                    resultDto.success();
                    return resultDto;
                }
                entityWrapper.eq("parkingId", requestDto.getParkingId());
            }
            if (StringUtils.isNotEmpty(requestDto.getParkingLotNumber())) {
                Wrapper<ParkingLotInfoEntity> lotInfoEntityEntityWrapper = new EntityWrapper<>();
                lotInfoEntityEntityWrapper.like("number", requestDto.getParkingLotNumber());
                List<ParkingLotInfoEntity> parkingLotInfoEntities = parkingLotInfoCrudService.selectList(lotInfoEntityEntityWrapper);
                if (parkingLotInfoEntities.isEmpty()) {
                    resultDto.success();
                    return resultDto;
                } else {
                    List<Long> parkingLotInfoId =
                            parkingLotInfoEntities.stream().map(ParkingLotInfoEntity::getId).collect(Collectors.toList());
                    entityWrapper.in("parkingLotId", parkingLotInfoId);
                }
            }
            if (requestDto.getIntoRecordId() != null) {
                entityWrapper.eq("intoRecordId", requestDto.getIntoRecordId());
            }
            if (requestDto.getPlateNumber() != null && PlateNumberUtil.isPlateNumber(requestDto.getPlateNumber())) {
                entityWrapper.eq("plateNumber", requestDto.getPlateNumber());
            }
            if (requestDto.getPlateColor() != null) {
                PlateColorEnum plateColorEnum = PlateColorEnum.parse(requestDto.getPlateColor());
                if (plateColorEnum != null) {
                    entityWrapper.eq("plateColor", requestDto.getPlateColor());
                }
            }
            if (requestDto.getCarType() != null) {
                CarTypeEnum carTypeEnum = CarTypeEnum.parse(requestDto.getCarType());
                if (carTypeEnum != null) {
                    entityWrapper.eq("carType", requestDto.getCarType());
                }
            }
            if (requestDto.getStartTimeStart() != null) {
                entityWrapper.ge("startTime", requestDto.getStartTimeStart());
            }
            if (requestDto.getStartTimeEnd() != null) {
                entityWrapper.le("startTime", requestDto.getStartTimeEnd());
            }
            entityWrapper.orderBy("startTime", false);
            Page<ParkingVehicleRecordEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingVehicleRecordEntity> parkingVehicleRecordPage = parkingVehicleRecordCrudService.selectPage(page, entityWrapper);
            List<ParkingVehicleRecordViewResultDto> parkingVehicleRecordResultDtos = new ArrayList<>();
            if (parkingVehicleRecordPage != null && CollectionUtils.isNotEmpty(parkingVehicleRecordPage.getRecords())) {
                for (ParkingVehicleRecordEntity parkingVehicleRecord : parkingVehicleRecordPage.getRecords()) {
                    ParkingVehicleRecordViewResultDto parkingVehicleRecordResultDto = modelMapper.map(parkingVehicleRecord, ParkingVehicleRecordViewResultDto.class);
                    long timeMillis = DateUtils.now().getTime() - parkingVehicleRecord.getStartTime().getTime();
                    parkingVehicleRecordResultDto.setParkPeriodTime(DateUtils.formatDateTimeChinese(timeMillis));
                    //获取过车图片
                    EntityWrapper<ParkingRecordImageEntity> parkingImageEntityWrapper = new EntityWrapper<>();
                    parkingImageEntityWrapper.eq("parkingId", parkingVehicleRecord.getParkingId());
                    parkingImageEntityWrapper.eq("bizNo", parkingVehicleRecord.getIntoRecordNo());
                    parkingImageEntityWrapper.eq("bizType", ParkingImageTypeEnum.PASSING.getValue());
                    List<ParkingRecordImageEntity> parkingImageList = parkingRecordImageCrudService.selectList(parkingImageEntityWrapper);
                    if (!parkingImageList.isEmpty()) {
                        List<ParkingImageViewResultDto> parkingImageViewResultDto = modelMapper.map(parkingImageList, new TypeToken<List<ParkingImageViewResultDto>>() {
                        }.getType());
                        parkingVehicleRecordResultDto.setParkingImages(parkingImageViewResultDto);
                    }
                    parkingVehicleRecordResultDtos.add(parkingVehicleRecordResultDto);
                }
                resultDto.setPageNo(parkingVehicleRecordPage.getCurrent());
                resultDto.setPageSize(parkingVehicleRecordPage.getSize());
                resultDto.setTotalCount(parkingVehicleRecordPage.getTotal());
                resultDto.setItems(parkingVehicleRecordResultDtos);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("分页获取停车场在停车辆失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取停车场在停车辆
     *
     * @param requestDto 获取停车场在停车辆请求参数
     * @return ObjectResultDto
     */
    @Override
    public ObjectResultDto<ParkingVehicleRecordViewResultDto> getParkingVehicleRecord(ParkingVehicleRecordGetRequestDto requestDto) {
        ObjectResultDto<ParkingVehicleRecordViewResultDto> resultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<ParkingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
            if (requestDto.getId() != null) {
                entityWrapper.eq("id", requestDto.getId());
            }
            if (requestDto.getIntoRecordId() != null) {
                entityWrapper.eq("intoRecordId", requestDto.getIntoRecordId());
            }
            ParkingVehicleRecordEntity parkingVehicleRecord = parkingVehicleRecordCrudService.selectOne(entityWrapper);
            if (parkingVehicleRecord == null) {
                resultDto.setCode(1);
                return resultDto.failed(ParkingConstant.PARKING_VECHILE_RECORD_EMPTY);
            }
            ParkingVehicleRecordViewResultDto parkingVehicleRecordResultDto = modelMapper.map(parkingVehicleRecord, ParkingVehicleRecordViewResultDto.class);
            long timeMillis = DateUtils.now().getTime() - parkingVehicleRecord.getStartTime().getTime();
            parkingVehicleRecordResultDto.setParkPeriodTime(DateUtils.formatDateTimeChinese(timeMillis));
            ParkingInfoEntity parkingInfo = parkingInfoCrudService.selectById(parkingVehicleRecord.getParkingId());
            if (null != parkingInfo) {
                parkingVehicleRecordResultDto.setParkingName(parkingInfo.getFullName());
            }
            EntityWrapper<ParkingRecordImageEntity> parkingImageEntityWrapper = new EntityWrapper<>();
            parkingImageEntityWrapper.eq("parkingId", parkingVehicleRecord.getParkingId());
            parkingImageEntityWrapper.eq("bizType", ParkingImageTypeEnum.PASSING.getValue());
            parkingImageEntityWrapper.eq("bizNo", parkingVehicleRecord.getIntoRecordNo());
            List<ParkingRecordImageEntity> parkingImageList = parkingRecordImageCrudService.selectList(parkingImageEntityWrapper);
            if (!parkingImageList.isEmpty()) {
                List<ParkingImageViewResultDto> parkingImageViewResultDto = modelMapper.map(parkingImageList, new TypeToken<List<ParkingImageViewResultDto>>() {
                }.getType());
                parkingVehicleRecordResultDto.setParkingImages(parkingImageViewResultDto);
            }
            resultDto.setData(parkingVehicleRecordResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("获取停车场在停车辆失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 带租户根据入车记录获取在场车辆
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingVehicleRecordResultDto> getByIntoRecordNo(ParkingVehicleRecordGetByIntoPassNoRequestDto requestDto) {
        ObjectResultDto<ParkingVehicleRecordResultDto> resultDto = new ObjectResultDto<>();
        try {
            Wrapper<ParkingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("intoRecordNo", requestDto.getIntoRecordNo());
            if (requestDto.getParkingId() != null) {
                entityWrapper.eq("parkingId", requestDto.getParkingId());
            }
            ParkingVehicleRecordEntity parkingVehicleRecordEntity = parkingVehicleRecordCrudService.selectOne(entityWrapper);
            if (parkingVehicleRecordEntity != null) {
                ParkingVehicleRecordResultDto parkingVehicleRecordResultDto = modelMapper.map(parkingVehicleRecordEntity, ParkingVehicleRecordResultDto.class);
                resultDto.setData(parkingVehicleRecordResultDto);
            }
            resultDto.success();
        } catch (Exception e) {
            resultDto.failed();
            log.error("根据入车记录获取在场车辆" + e.getMessage());
        }
        return resultDto;
    }

    /**
     * 更新在停车辆
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateParkingVehicle(ParkingVehicleRecordUpdateRequestDto requestDto) {
        ObjectResultDto<ParkingVehicleRecordResultDto> resultDto = new ObjectResultDto<>();
        try {
            Wrapper<ParkingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            if (null != requestDto.getParkingId()) {
                entityWrapper.eq("parkingId", requestDto.getParkingId());
            }
            ParkingVehicleRecordEntity parkingVehicleRecordEntity = new ParkingVehicleRecordEntity();
            if (null != requestDto.getCarType()) {
                parkingVehicleRecordEntity.setCarType(requestDto.getCarType());
            }
            if (null != requestDto.getParkingLotId()) {
                parkingVehicleRecordEntity.setParkingLotId(requestDto.getParkingLotId());
            }
            if (null != requestDto.getPlateColor()) {
                parkingVehicleRecordEntity.setPlateColor(requestDto.getPlateColor());
            }
            if (StringUtils.isNotEmpty(requestDto.getPlateNumber())) {
                parkingVehicleRecordEntity.setPlateNumber(requestDto.getPlateNumber());
            }
            if (null != requestDto.getStartTime()) {
                parkingVehicleRecordEntity.setStartTime(requestDto.getStartTime());
            }
            Boolean update = parkingVehicleRecordCrudService.update(parkingVehicleRecordEntity, entityWrapper);
            if (!update) {
                return resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            resultDto.failed();
            log.error("根据入车记录获取在场车辆" + e.getMessage());
        }
        return resultDto;
    }

    /**
     * 删除在场车辆记录
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto deleteParkVehicleRecord(ParkingVehicleRecordDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (null != requestDto.getId()) {
                EntityWrapper<ParkingVehicleRecordEntity> entityEntityWrapper = new EntityWrapper<>();
                entityEntityWrapper.eq("id", requestDto.getId());
                if (requestDto.getParkingId() != null) {
                    entityEntityWrapper.eq("parkingId", requestDto.getParkingId());
                }
                boolean delete = parkingVehicleRecordCrudService.delete(entityEntityWrapper);
                if (!delete) {
                    return resultDto.failed();
                }
            }
            resultDto.success();
        } catch (Exception e) {
            resultDto.failed();
            log.error("删除在场车辆记录失败" + e.getMessage());
        }
        return resultDto;
    }

    /**
     * 保存在场车辆记录
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto saveParkingVehicleRecord(ParkingVehicleRecordSaveRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingVehicleRecordEntity parkingVehicleRecordEntity = modelMapper.map(requestDto, ParkingVehicleRecordEntity.class);
            boolean insert = parkingVehicleRecordCrudService.insert(parkingVehicleRecordEntity);
            if (!insert) {
                resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            resultDto.failed();
            log.error("保存在场车辆记录失败" + e.getMessage());
        }
        return resultDto;
    }

    /**
     * 根据车牌/车位查找
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    public ListResultDto<PlateNumberMyCloudResultDto> getParkingGuidance(ParkingGuidanceParamDto requestDto) {
        ListResultDto<PlateNumberMyCloudResultDto> resultDto = new ListResultDto<>();
        List<PlateNumberMyCloudResultDto> plateNumberMyResultDtoList = new ArrayList<>();
        try {
            EntityWrapper<ParkingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
            if (requestDto.getPlateNumber() != null) {
                entityWrapper.like("plateNumber", requestDto.getPlateNumber());
            }
            if (requestDto.getParkingLotNumber() != null) {
                entityWrapper.like("parkingLotNumber", requestDto.getParkingLotNumber());
            }
            List<ParkingVehicleRecordEntity> parkingVehicleRecord = parkingVehicleRecordCrudService.queryPlateNumberOrParkingLotCode(requestDto);
            if (parkingVehicleRecord.isEmpty()) {
                return resultDto.success();
            }
            //对象转换
            if (parkingVehicleRecord.size() > 0) {
                for (int i = 0; i < parkingVehicleRecord.size(); i++) {
                    //在停车辆信息
                    MyPlateNumberParkingGetRequestDto myPlateNumberParkingGetRequestDto = new MyPlateNumberParkingGetRequestDto();
                    myPlateNumberParkingGetRequestDto.setPlateNumber(parkingVehicleRecord.get(i).getPlateNumber());
                    ObjectResultDto<ParkingVehicleRecordViewResultDto> parkingVehicleRecordViewResultDto = getMyPlateNumberParkingVehicleRecord(myPlateNumberParkingGetRequestDto);
                    if (parkingVehicleRecordViewResultDto.getData() != null) {
                        PlateNumberMyCloudResultDto plateNumberMyResultDto = modelMapper.map(parkingVehicleRecordViewResultDto, PlateNumberMyCloudResultDto.class);
                        //车场名称
                        plateNumberMyResultDto.setParkingId(parkingVehicleRecordViewResultDto.getData().getParkingId());
                        plateNumberMyResultDto.setParkingName(parkingVehicleRecordViewResultDto.getData().getParkingName());
                        plateNumberMyResultDto.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
                        plateNumberMyResultDto.setFullPlateNumber(parkingVehicleRecordViewResultDto.getData().getPlateNumber());
                        //泊位编号
                        plateNumberMyResultDto.setParkingName(parkingVehicleRecordViewResultDto.getData().getParkingName());
                        plateNumberMyResultDto.setParkingLotCode(parkingVehicleRecordViewResultDto.getData().getParkingLotNumber());

                        //区域
                        ParkingAreaGetRequestDto parkingAreaGetRequestDto = new ParkingAreaGetRequestDto();
                        parkingAreaGetRequestDto.setId(parkingVehicleRecordViewResultDto.getData().getParkingLotId());
                        ObjectResultDto<ParkingAreaResultDto> parkingArea = parkingAreaService.getParkingArea(parkingAreaGetRequestDto);
                        if (parkingArea.getData() != null) {
                            plateNumberMyResultDto.setAreaName(parkingArea.getData().getName());
                            //楼层
                            ParkingFloorRequestDto parkingFloorRequestDto = new ParkingFloorRequestDto();
                            parkingFloorRequestDto.setId(parkingArea.getData().getFloorId());
                            ObjectResultDto<ParkingFloorResultDto> parkingFloorDto = getParkingFloor(parkingFloorRequestDto);
                            if (parkingFloorDto.getData() != null) {
                                plateNumberMyResultDto.setFloorName(parkingFloorDto.getData().getFloorName());
                                plateNumberMyResultDto.setParkingLotDescription(plateNumberMyResultDto.getFloorName() + plateNumberMyResultDto.getAreaName() + plateNumberMyResultDto.getParkingLotCode());
                                plateNumberMyResultDtoList.add(plateNumberMyResultDto);
                            }
                        }
                    }
                }
                if (plateNumberMyResultDtoList.size() > 0) {
                    resultDto.setItems(plateNumberMyResultDtoList);
                    resultDto.setCode(1);
                    resultDto.setMessage("请求成功");
                } else {
                    resultDto.setCode(1);
                    resultDto.setMessage("没有在停车辆信息!");
                }
            }
        } catch (Exception e) {
            log.error("获取停车场在停车辆失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改在停车辆停车场
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateParkingVehicleRecord(ParkingRecordQueryByIntoRecordNoRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingVehicleRecordEntity parkingVehicleRecordEntity = parkingVehicleRecordCrudService.findByIntoRecordNo(requestDto.getIntoRecordNo());
            if (null != parkingVehicleRecordEntity) {
                //修改泊位
                parkingVehicleRecordEntity.setParkingLotId(requestDto.getParkingLotId());
                parkingVehicleRecordEntity.setParkingLotCode(requestDto.getParkingLotCode());
                parkingVehicleRecordEntity.setParkingLotNumber(requestDto.getParkingLotNumber());
                parkingVehicleRecordCrudService.updateParkVehicleRecord(parkingVehicleRecordEntity);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("修改在停车辆停车场失败" + e.getMessage(), e);
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * @Description: TODO
     * @Author: qhxu
     * @CreateDate: 2019/3/23 15:45
     * @UpdateUser: qhxu
     * @UpdateDate: 2019/3/23 15:45
     * @UpdateRemark: 修改内容
     * @Version: 1.0
     */
    @Override
    public ObjectResultDto<ParkingVehicleRecordViewResultDto> getMyPlateNumberParkingVehicleRecord(MyPlateNumberParkingGetRequestDto requestDto) {
        ObjectResultDto<ParkingVehicleRecordViewResultDto> resultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<ParkingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();

            if (!requestDto.getPlateNumber().isEmpty()) {
                entityWrapper.eq("plateNumber", requestDto.getPlateNumber());
            }
            //在停车辆
            ParkingVehicleRecordEntity parkingVehicleRecord = parkingVehicleRecordCrudService.getPlateNumber(requestDto);

            if (parkingVehicleRecord == null) {
                resultDto.setCode(1);
                return resultDto.failed(ParkingConstant.PARKING_VECHILE_RECORD_EMPTY);
            }
            ParkingVehicleRecordViewResultDto parkingVehicleRecordResultDto = modelMapper.map(parkingVehicleRecord, ParkingVehicleRecordViewResultDto.class);
            long timeMillis = DateUtils.now().getTime() - parkingVehicleRecord.getStartTime().getTime();
            parkingVehicleRecordResultDto.setParkPeriodTime(DateUtils.formatDateTimeChinese(timeMillis));
            ParkingInfoEntity parkingInfo = parkingInfoCrudService.selectById(parkingVehicleRecord.getParkingId());
            if (null != parkingInfo) {
                parkingVehicleRecordResultDto.setParkingName(parkingInfo.getFullName());
            }
            EntityWrapper<ParkingRecordImageEntity> parkingImageEntityWrapper = new EntityWrapper<>();
            parkingImageEntityWrapper.eq("parkingId", parkingVehicleRecord.getParkingId());
            parkingImageEntityWrapper.eq("bizType", ParkingImageTypeEnum.PASSING.getValue());
            parkingImageEntityWrapper.eq("bizNo", parkingVehicleRecord.getIntoRecordNo());
            List<ParkingRecordImageEntity> parkingImageList = parkingRecordImageCrudService.selectList(parkingImageEntityWrapper);
            if (!parkingImageList.isEmpty()) {
                List<ParkingImageViewResultDto> parkingImageViewResultDto = modelMapper.map(parkingImageList, new TypeToken<List<ParkingImageViewResultDto>>() {
                }.getType());
                parkingVehicleRecordResultDto.setParkingImages(parkingImageViewResultDto);
            }
            resultDto.setData(parkingVehicleRecordResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("获取停车场在停车辆失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public ListResultDto<ParkingVehicleRecordLotResultDto> getCodeId(ParkingVehicleRecordLotRequestDto requestDto) {
        ListResultDto<ParkingVehicleRecordLotResultDto> listResultDto = new ListResultDto<>();
        //ParkingVehicleRecordEntity parkingVehicleRecordEntity = new ParkingVehicleRecordEntity();
        //entityEntityWrapper.like("plateNumber",requestDto.getPlateNumber());
        try {
            List<ParkingVehicleRecordEntity> parkingVehicleRecordEntityList = parkingVehicleRecordCrudService.queryPlateNumber(requestDto.getPlateNumber());
            if (!parkingVehicleRecordEntityList.isEmpty()) {
                List<ParkingVehicleRecordLotResultDto> parkingVehicleRecordLotResultDtoList = modelMapper.map(parkingVehicleRecordEntityList, new TypeToken<List<ParkingVehicleRecordLotResultDto>>() {
                }.getType());
                listResultDto.setItems(parkingVehicleRecordLotResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取车牌失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * @Description: 楼层信息
     * @Author: qhxu
     * @CreateDate: 2019/3/26 19:52
     * @UpdateUser: qhxu
     * @UpdateDate: 2019/3/26 19:52
     * @UpdateRemark: 修改内容
     * @Version: 1.0
     */
    @Override
    public ObjectResultDto<ParkingFloorResultDto> getParkingFloor(ParkingFloorRequestDto requestDto) {
        ObjectResultDto<ParkingFloorResultDto> parkingFloorResult = new ObjectResultDto<>();
        ParkingFloorEntity parkingFloorEntity = parkingFloorCrudService.getparkingFloor(requestDto.getId());
        ParkingFloorResultDto parkingFloorResultDto = new ParkingFloorResultDto();
        parkingFloorResultDto.setFloorName(parkingFloorEntity.getFloorName());
        parkingFloorResult.setData(parkingFloorResultDto);
        return parkingFloorResult;
    }
}
