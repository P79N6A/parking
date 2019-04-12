package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.core.enums.PassingTypeEnum;
import com.zoeeasy.cloud.core.enums.PassingVehicleDataSourceEnum;
import com.zoeeasy.cloud.pms.domain.*;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingRecordHistorySaveRequestDto;
import com.zoeeasy.cloud.pms.platform.PlatformProcessService;
import com.zoeeasy.cloud.pms.platform.dto.request.*;
import com.zoeeasy.cloud.pms.platform.dto.result.*;
import com.zoeeasy.cloud.pms.platform.validator.ParkingRecordGetByIntoRecordNoRequestDtoValidator;
import com.zoeeasy.cloud.pms.platform.validator.ParkingRecordGetByPassTimeRequestDtoValidator;
import com.zoeeasy.cloud.pms.platform.validator.ParkingVehicleRecordFindLastRecordRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author AkeemSuper
 * @date 2018/10/31 0031
 */
@Service("platformProcessService")
@Slf4j
public class PlatformProcessServiceImpl implements PlatformProcessService {

    @Autowired
    private PassingVehicleRecordCrudService passingVehicleRecordCrudService;

    @Autowired
    private ParkingRecordImageCrudService parkingRecordImageCrudService;

    @Autowired
    private ParkingRecordHistoryCrudService parkingRecordHistoryCrudService;

    @Autowired
    private ParkingVehicleRecordCrudService parkingVehicleRecordCrudService;

    @Autowired
    private ParkingRecordCrudService parkingRecordCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 找到车辆的最后一次在场记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingVehicleRecordResultDto> findLastParkingRecord(@FluentValid(ParkingVehicleRecordFindLastRecordRequestDtoValidator.class)
                                                                                        ParkingVehicleRecordFindLastRecordRequestDto requestDto) {
        ObjectResultDto<ParkingVehicleRecordResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingVehicleRecordEntity lastParkingRecord = parkingVehicleRecordCrudService.findLastParkingRecord(
                    requestDto.getParkingId(), requestDto.getParkingLotId(), requestDto.getPlateNumber(),
                    requestDto.getPlateColor(), requestDto.getCarType(), requestDto.getEndTime(), requestDto.getTenantId());
            if (null != lastParkingRecord) {
                ParkingVehicleRecordResultDto recordResultDto = modelMapper.map(lastParkingRecord, ParkingVehicleRecordResultDto.class);
                objectResultDto.setData(recordResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("获取停车场在停车辆失败" + e.getMessage());
        }
        return objectResultDto;
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
            parkingVehicleRecordEntity.setCreationTime(DateUtils.now());
            boolean insert = parkingVehicleRecordCrudService.save(parkingVehicleRecordEntity);
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
                boolean delete = parkingVehicleRecordCrudService.deletedById(requestDto.getId(), requestDto.getParkingId());
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
     * 根据入车记录获取在场车辆
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingVehicleRecordResultDto> getByIntoRecordNo(ParkingVehicleRecordGetByIntoPassNoRequestDto requestDto) {
        ObjectResultDto<ParkingVehicleRecordResultDto> resultDto = new ObjectResultDto<>();
        try {
            ParkingVehicleRecordEntity parkingVehicleRecordEntity = parkingVehicleRecordCrudService.findByIntoRecordNo(requestDto.getIntoRecordNo());
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
            ParkingVehicleRecordEntity parkingVehicleRecordEntity = new ParkingVehicleRecordEntity();
            parkingVehicleRecordEntity.setId(requestDto.getId());
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
            if (null != requestDto.getParkingId()) {
                parkingVehicleRecordEntity.setParkingId(requestDto.getParkingId());
            }
            Boolean update = parkingVehicleRecordCrudService.updateParkVehicle(parkingVehicleRecordEntity);
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
     * 保存历史停车记录
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto saveParkingRecordHistory(ParkingRecordHistorySaveRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (null != requestDto) {
                ParkingRecordHistoryEntity parkingRecordHistoryEntity = modelMapper.map(requestDto, ParkingRecordHistoryEntity.class);
                parkingRecordHistoryEntity.setCreationTime(DateUtils.now());
                boolean insert = parkingRecordHistoryCrudService.save(parkingRecordHistoryEntity);
                if (insert) {
                    return resultDto.success();
                }
            }
            resultDto.failed();
        } catch (Exception e) {
            log.error("保存历史停车记录失败", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取停车记录图片
     */
    @Override
    public ListResultDto<ParkingImageViewResultDto> getParkingRecordImageList(ParkingRecordImageGetRequestDto requestDto) {
        ListResultDto<ParkingImageViewResultDto> resultDto = new ListResultDto<>();
        try {
            ParkingRecordEntity parkingRecord = parkingRecordCrudService.findByRecordNo(requestDto.getRecordNo());
            if (parkingRecord == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_RECORD_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_RECORD_NOT_FOUND.getComment()
                );
            }
            //先获取停车图像，停车图像不存在然后获取过车图像
            List<ParkingRecordImageEntity> parkingImageList = parkingRecordImageCrudService.findParkingImage(parkingRecord.getParkingId(), parkingRecord.getId());
            if (parkingImageList.isEmpty()) {
                if (StringUtils.isNotEmpty(parkingRecord.getIntoRecordNo())) {
                    parkingImageList = parkingRecordImageCrudService.findPassingImage(parkingRecord.getParkingId(), parkingRecord.getIntoRecordNo());
                }
                if (StringUtils.isNotEmpty(parkingRecord.getOutRecordNo())) {
                    parkingImageList.addAll(parkingRecordImageCrudService.findPassingImage(parkingRecord.getParkingId(), parkingRecord.getOutRecordNo()));
                }
            }
            resultDto.setItems(parkingImageList.stream()
                    .filter(item ->
                            StringUtils.isNotEmpty(item.getUrl())
                    ).map(
                            item ->
                            {
                                ParkingImageViewResultDto parkingRecordImageResultDto = new ParkingImageViewResultDto();
                                parkingRecordImageResultDto.setUrl(item.getUrl());
                                return parkingRecordImageResultDto;
                            }
                    ).collect(Collectors.toList()));
            resultDto.success();
        } catch (Exception e) {
            log.error("获取停车记录图片失败" + e.getMessage());
            resultDto.failed();
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
        ObjectResultDto<SaveParkingRecordResultDto> resultDto = new ObjectResultDto<>();
        try {
            SaveParkingRecordResultDto saveParkingRecordResultDto = new SaveParkingRecordResultDto();
            ParkingRecordEntity map = modelMapper.map(requestDto, ParkingRecordEntity.class);
            map.setCreationTime(DateUtils.now());
            Long id = parkingRecordCrudService.save(map);
            saveParkingRecordResultDto.setId(id);
            resultDto.setData(saveParkingRecordResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("保存停车记录失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 通过入车记录 停车场id 泊位ID获取停车记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingRecordResultDto> getByIntoRecordNo(@FluentValid(ParkingRecordGetByIntoRecordNoRequestDtoValidator.class) ParkingRecordGetByIntoRecordNoRequestDto requestDto) {
        ObjectResultDto<ParkingRecordResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<ParkingRecordEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("tenantId", requestDto.getTenantId());
            if (null != requestDto.getParkingId()) {
                entityWrapper.eq("parkingId", requestDto.getParkingId());
            }
            if (requestDto.getPassType().equals(PassingTypeEnum.ENTRY.getValue())) {
                entityWrapper.eq("intoRecordNo", requestDto.getPassingNo());
            } else if (requestDto.getPassType().equals(PassingTypeEnum.EXIT.getValue())) {
                entityWrapper.eq("outRecordNo", requestDto.getPassingNo());
            }
            if (null != requestDto.getParkingLotId()) {
                entityWrapper.eq("parkingLotId", requestDto.getParkingLotId());
            }
            ParkingRecordEntity parkingRecordEntity = parkingRecordCrudService.selectByIntoRecordNo(entityWrapper);
            if (null != parkingRecordEntity) {
                ParkingRecordResultDto parkingRecordResultDto = modelMapper.map(parkingRecordEntity, ParkingRecordResultDto.class);
                objectResultDto.setData(parkingRecordResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("通过入车记录 停车场id 泊位ID获取停车记录失败" + e.getMessage());
        }
        return objectResultDto;
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
            ParkingRecordEntity updateRecordEntity = modelMapper.map(requestDto, ParkingRecordEntity.class);
            updateRecordEntity.setLastModificationTime(DateUtils.now());
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
     * 获取过车时间前后的停车记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ParkingRecordResultDto> getByPassTime(@FluentValid(ParkingRecordGetByPassTimeRequestDtoValidator.class) ParkingRecordGetByPassTimeRequestDto requestDto) {
        ListResultDto<ParkingRecordResultDto> resultDto = new ListResultDto<>();
        try {
            Wrapper<ParkingRecordEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            entityWrapper.eq("plateNumber", requestDto.getPlateNumber());
            entityWrapper.lt("startTime", requestDto.getPassTime());
            entityWrapper.gt("endTime", requestDto.getPassTime());
            entityWrapper.notIn("endTime", DateTimeUtils.getDateMax());
            List<ParkingRecordEntity> parkingRecordEntities = parkingRecordCrudService.selectList(entityWrapper);
            if (!parkingRecordEntities.isEmpty()) {
                List<ParkingRecordResultDto> parkingRecordResultDtos = modelMapper.map(parkingRecordEntities, new TypeToken<List<ParkingRecordResultDto>>() {
                }.getType());
                resultDto.setItems(parkingRecordResultDtos);
            }
            resultDto.success();
        } catch (Exception e) {
            resultDto.failed();
            log.error("获取过车时间前后的停车记录失败" + e.getMessage());
        }
        return resultDto;
    }

    /**
     * 通过停车记录流水号获取停车记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingRecordResultDto> getByRecordNo(ParkingRecordGetByRecordNoRequestDto requestDto) {
        ObjectResultDto<ParkingRecordResultDto> resultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<ParkingRecordEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("recordNo", requestDto.getRecordNo());
            ParkingRecordEntity parkingRecordEntity = parkingRecordCrudService.selectOne(entityWrapper);
            if (parkingRecordEntity != null) {
                ParkingRecordResultDto parkingRecordResultDto = modelMapper.map(parkingRecordEntity, ParkingRecordResultDto.class);
                resultDto.setData(parkingRecordResultDto);
            }
            resultDto.success();
        } catch (Exception e) {
            resultDto.failed();
            log.error("通过停车记录流水号获取停车记录失败" + e.getMessage());
        }
        return resultDto;
    }

    /**
     * 根据过车记录流水号获取过车记录
     */
    @Override
    public ObjectResultDto<PassingVehicleRecordResultDto> getPassVehicleRecord(PassingVehicleRecordGetRequestDto requestDto) {
        ObjectResultDto<PassingVehicleRecordResultDto> resultDto = new ObjectResultDto<>();
        try {
            PassingVehicleRecordEntity passingVehicleRecord =
                    passingVehicleRecordCrudService.selectByPassingNo(requestDto.getPassingNo(), requestDto.getParkingId());
            if (passingVehicleRecord == null) {
                return resultDto.failed("未找到相关过车记录");
            }
            PassingVehicleRecordResultDto passingVehicleRecordResultDto = modelMapper.map(passingVehicleRecord, PassingVehicleRecordResultDto.class);
            resultDto.setData(passingVehicleRecordResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("查询过车记录失败", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 添加过车记录
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<PassingVehicleRecordCreatedResultDto> savePassingVehicleRecord(PassingVehicleRecordCreateRequestDto requestDto) {
        ObjectResultDto<PassingVehicleRecordCreatedResultDto> resultDto = new ObjectResultDto<>();
        try {
            PassingVehicleRecordEntity recordEntity = modelMapper.map(requestDto, PassingVehicleRecordEntity.class);
            recordEntity.setCreationTime(DateUtils.now());
            boolean insert = passingVehicleRecordCrudService.insert(recordEntity);
            if (insert) {
                Long passingId = recordEntity.getId();
                PassingVehicleRecordCreatedResultDto passingVehicleRecordSaveResultDto = new PassingVehicleRecordCreatedResultDto();
                passingVehicleRecordSaveResultDto.setId(passingId);
                resultDto.setData(passingVehicleRecordSaveResultDto);
                resultDto.success();
            } else {
                resultDto.failed();
            }
        } catch (Exception e) {
            resultDto.failed();
            log.error("添加过车记录失败, 异常信息:{}", e.getMessage());
        }
        return resultDto;
    }

    /**
     * 根据过车记录流水号更新过车记录
     *
     * @param requestDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updatePassVehicleRecord(PassingRecordUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            PassingVehicleRecordEntity passingVehicleRecordEntity = new PassingVehicleRecordEntity();
            if (null != requestDto.getPassTime()) {
                passingVehicleRecordEntity.setPassTime(requestDto.getPassTime());
            }
            if (null != requestDto.getEntryTime()) {
                passingVehicleRecordEntity.setEntryTime(requestDto.getEntryTime());
            }
            if (StringUtils.isNotEmpty(requestDto.getRemark())) {
                passingVehicleRecordEntity.setRemark(requestDto.getRemark());
            }
            if (StringUtils.isNotEmpty(requestDto.getPlateNumber())) {
                passingVehicleRecordEntity.setPlateNumber(requestDto.getPlateNumber());
            }
            if (null != requestDto.getCarType()) {
                passingVehicleRecordEntity.setCarType(requestDto.getCarType());
            }
            if (null != requestDto.getPlateColor()) {
                passingVehicleRecordEntity.setPlateColor(requestDto.getPlateColor());
            }
            if (null != requestDto.getPlateNoExist()) {
                passingVehicleRecordEntity.setPlateNoExist(requestDto.getPlateNoExist());
            }
            if (null != requestDto.getParkingLotId()) {
                passingVehicleRecordEntity.setParkingLotId(requestDto.getParkingLotId());
            }
            if (null != requestDto.getAbnormalType()) {
                passingVehicleRecordEntity.setAbnormalType(requestDto.getAbnormalType());
            }
            if (null != requestDto.getParkingId()) {
                passingVehicleRecordEntity.setParkingId(requestDto.getParkingId());
            }
            passingVehicleRecordEntity.setPassingNo(requestDto.getPassingNo());
            passingVehicleRecordEntity.setLastModificationTime(DateUtils.now());
            boolean update = passingVehicleRecordCrudService.updateByPassNo(passingVehicleRecordEntity);
            if (!update) {
                return resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            resultDto.failed();
            log.error("根据id更新过车记录失败" + e.getMessage());
        }
        return resultDto;
    }

    /**
     * 保存停车过车记录图片
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto saveParkingRecordImage(ParkingRecordImageSaveRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingRecordImageEntity parkingRecordImageEntity = modelMapper.map(requestDto, ParkingRecordImageEntity.class);
            parkingRecordImageEntity.setCreationTime(DateUtils.now());
            Boolean insert = parkingRecordImageCrudService.save(parkingRecordImageEntity);
            if (!insert) {
                return resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("保存停车过车记录图片失败", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据泊位删除在停车辆
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto deleteParkVehicleByLot(ParkVehicleDeleteByLotRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Wrapper<ParkingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getParkVehicleId());
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            entityWrapper.eq("parkingLotId", requestDto.getParkingLotId());
            parkingVehicleRecordCrudService.delete(entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("根据泊位删除在停车辆失败", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据三方记录号获取过车记录
     *
     * @param requestDto
     */
    @Override
    public ObjectResultDto<PassingVehicleRecordResultDto> getPassRecordByThirdNo(PassRecordGetByThirdNoRequestDto requestDto) {
        ObjectResultDto<PassingVehicleRecordResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<PassingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("thirdPassingId", requestDto.getThirdPassingId());
            entityWrapper.eq("dataSource", PassingVehicleDataSourceEnum.ANY_PARKING.getValue());
            PassingVehicleRecordEntity passingVehicleRecordEntity =
                    passingVehicleRecordCrudService.selectPassRecord(entityWrapper);
            if (passingVehicleRecordEntity != null) {
                PassingVehicleRecordResultDto map = modelMapper.map(passingVehicleRecordEntity, PassingVehicleRecordResultDto.class);
                objectResultDto.setData(map);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("根据三方记录号获取过车记录失败", e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }


    @Override
    public ObjectResultDto<PassingVehicleRecordResultDto> getPassRecordByThirdNoAndCloudParkingCode(PassRecordGetByThirdNoRequestDto requestDto) {
        ObjectResultDto<PassingVehicleRecordResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<PassingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("thirdPassingId", requestDto.getThirdPassingId());
            entityWrapper.eq("parkingCode", requestDto.getCloudParkingCode());
            PassingVehicleRecordEntity passingVehicleRecordEntity =
                    passingVehicleRecordCrudService.selectPassRecord(entityWrapper);
            if (passingVehicleRecordEntity != null) {
                PassingVehicleRecordResultDto map = modelMapper.map(passingVehicleRecordEntity, PassingVehicleRecordResultDto.class);
                objectResultDto.setData(map);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("根据三方记录号获取过车记录失败", e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据停车记录号查询停车记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingRecordResultDto> getParkingRecordByRecordNo(ParkingRecordQueryByRecordNoRequestDto requestDto) {
        ObjectResultDto<ParkingRecordResultDto> resultDto = new ObjectResultDto<>();
        try {
            ParkingRecordEntity parkingRecordEntity = parkingRecordCrudService.findByRecordNo(requestDto.getRecordNo());
            ParkingRecordResultDto parkingRecordResultDto = modelMapper.map(parkingRecordEntity, ParkingRecordResultDto.class);
            resultDto.setData(parkingRecordResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("根据停车记录号查询停车记录失败：" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
