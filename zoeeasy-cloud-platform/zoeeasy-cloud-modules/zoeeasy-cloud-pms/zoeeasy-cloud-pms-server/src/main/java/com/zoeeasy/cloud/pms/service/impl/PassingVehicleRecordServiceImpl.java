package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.core.enums.CarTypeEnum;
import com.zoeeasy.cloud.core.enums.PassingExceptionTypeEnum;
import com.zoeeasy.cloud.core.enums.PassingTypeEnum;
import com.zoeeasy.cloud.core.enums.PassingVehicleDataSourceEnum;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingLotInfoEntity;
import com.zoeeasy.cloud.pms.domain.PassingVehicleRecordEntity;
import com.zoeeasy.cloud.pms.enums.PlateColorEnum;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.passing.PassingVehicleRecordService;
import com.zoeeasy.cloud.pms.passing.dto.request.*;
import com.zoeeasy.cloud.pms.passing.dto.result.PassingVehicleRecordViewResultDto;
import com.zoeeasy.cloud.pms.platform.dto.request.PassingRecordUpdateRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.PassingVehicleRecordCreateRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.PassingVehicleRecordGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.PassingVehicleRecordCreatedResultDto;
import com.zoeeasy.cloud.pms.platform.dto.result.PassingVehicleRecordResultDto;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingLotInfoCrudService;
import com.zoeeasy.cloud.pms.service.PassingVehicleRecordCrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
@Service("passingVehicleRecordService")
@Slf4j
public class PassingVehicleRecordServiceImpl implements PassingVehicleRecordService {

    @Autowired
    private PassingVehicleRecordCrudService passingVehicleRecordCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 分页查询过车记录
     */
    @Override
    public PagedResultDto<PassingVehicleRecordViewResultDto> getPassVehicleRecordListPage(PassingVehicleRecordQueryPageRequestDto requestDto) {
        PagedResultDto<PassingVehicleRecordViewResultDto> resultDto = new PagedResultDto<>();
        EntityWrapper<PassingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
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
                    List<Long> arkingLotInfoId = parkingLotInfoEntities.stream().map(ParkingLotInfoEntity::getId).collect(Collectors.toList());
                    entityWrapper.in("parkingLotId", arkingLotInfoId);
                }
            }
            if (StringUtils.isNotEmpty(requestDto.getPlateNumber())) {
                entityWrapper.like("plateNumber", requestDto.getPlateNumber());
            }
            if (requestDto.getPlateColor() != null) {
                PlateColorEnum parse = PlateColorEnum.parse(requestDto.getPlateColor());
                if (parse != null) {
                    entityWrapper.eq("plateColor", requestDto.getPlateColor());
                }
            }
            if (requestDto.getCarType() != null) {
                CarTypeEnum carTypeEnum = CarTypeEnum.parse(requestDto.getCarType());
                if (carTypeEnum != null) {
                    entityWrapper.eq("carType", requestDto.getCarType());
                }
            }
            if (requestDto.getProofStatus() != null) {
                entityWrapper.eq("proofStatus", requestDto.getProofStatus());
            }
            if (requestDto.getDataSource() != null) {
                PassingVehicleDataSourceEnum dataSourceEnum = PassingVehicleDataSourceEnum.parse(requestDto.getDataSource());
                if (dataSourceEnum != null) {
                    entityWrapper.eq("dataSource", requestDto.getDataSource());
                }
            }
            if (requestDto.getPassCarType() != null) {
                PassingTypeEnum passingTypeEnum = PassingTypeEnum.parse(requestDto.getPassCarType());
                if (passingTypeEnum != null) {
                    entityWrapper.eq("passingType", requestDto.getPassCarType());
                }
            }
            if (requestDto.getStartTime() != null) {
                entityWrapper.ge("passTime", requestDto.getStartTime());
            }
            if (requestDto.getEndTime() != null) {
                entityWrapper.le("passTime", requestDto.getEndTime());
            }
            entityWrapper.orderBy("passTime", false);
            Page<PassingVehicleRecordEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<PassingVehicleRecordEntity> passingVehicleRecordPage = passingVehicleRecordCrudService.selectPage(page, entityWrapper);
            List<PassingVehicleRecordViewResultDto> passingVehicleRecordResultDtos = new ArrayList<>();
            if (passingVehicleRecordPage != null && passingVehicleRecordPage.getRecords() != null) {
                for (PassingVehicleRecordEntity passingVehicleRecord : passingVehicleRecordPage.getRecords()) {
                    PassingVehicleRecordViewResultDto passingVehicleRecordResultDto = modelMapper.map(passingVehicleRecord, PassingVehicleRecordViewResultDto.class);
                    if (null != passingVehicleRecord.getDataSource()) {
                        PassingVehicleDataSourceEnum dataSourceEnum = PassingVehicleDataSourceEnum.parse(passingVehicleRecord.getDataSource());
                        if (dataSourceEnum != null) {
                            passingVehicleRecordResultDto.setDataOrigin(dataSourceEnum.getComment());
                        }
                    }
                    passingVehicleRecordResultDtos.add(passingVehicleRecordResultDto);
                }
                resultDto.setPageNo(passingVehicleRecordPage.getCurrent());
                resultDto.setPageSize(passingVehicleRecordPage.getSize());
                resultDto.setTotalCount(passingVehicleRecordPage.getTotal());
                resultDto.setItems(passingVehicleRecordResultDtos);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("分页查询过车记录失败:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 后台根据过车记录号获取过车记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<PassingVehicleRecordViewResultDto> getPassRecordByPassNo(PassingVehicleRecordGetByPassNoRequestDto requestDto) {
        ObjectResultDto<PassingVehicleRecordViewResultDto> resultDto = new ObjectResultDto<>();
        try {
            if (!StringUtils.isNumbers(requestDto.getPassingNo())) {
                return resultDto.makeResult(PmsResultEnum.PASSING_NO_NOT_STANDARD.getValue(), PmsResultEnum.PASSING_NO_NOT_STANDARD.getComment());
            }
            EntityWrapper<PassingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("passingNo", requestDto.getPassingNo());
            PassingVehicleRecordEntity passingVehicleRecord = passingVehicleRecordCrudService.selectOne(entityWrapper);
            if (passingVehicleRecord == null) {
                return resultDto.makeResult(PmsResultEnum.PASSING_RECORD_NOT_FOUND.getValue(), PmsResultEnum.PASSING_RECORD_NOT_FOUND.getComment());
            }
            PassingVehicleRecordViewResultDto passingVehicleRecordResultDto = modelMapper.map(passingVehicleRecord, PassingVehicleRecordViewResultDto.class);
            if (passingVehicleRecord.getDataSource() != null) {
                PassingVehicleDataSourceEnum dataSourceEnum = PassingVehicleDataSourceEnum.parse(passingVehicleRecord.getDataSource());
                passingVehicleRecordResultDto.setDataOrigin(dataSourceEnum.getComment());
            }
            resultDto.setData(passingVehicleRecordResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("根据过车记录号获取过车记录失败", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 添加过车记录备注
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto addPassVehicleRecordRemark(PassVehicleRecordAddRemarkRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (!StringUtils.isNumbers(requestDto.getPassingNo())) {
                return resultDto.makeResult(PmsResultEnum.PASSING_NO_NOT_STANDARD.getValue(), PmsResultEnum.PASSING_NO_NOT_STANDARD.getComment());
            }
            EntityWrapper<PassingVehicleRecordEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("passingNo", requestDto.getPassingNo());
            PassingVehicleRecordEntity passingVehicleRecord = passingVehicleRecordCrudService.selectOne(wrapper);
            if (passingVehicleRecord == null) {
                resultDto.makeResult(PmsResultEnum.PASSING_RECORD_NOT_FOUND.getValue(),
                        PmsResultEnum.PASSING_RECORD_NOT_FOUND.getComment()
                );
            } else {
                PassingVehicleRecordEntity passingVehicleRecordUpdate = new PassingVehicleRecordEntity();
                passingVehicleRecordUpdate.setRemark(requestDto.getRemark());
                //更新校验状态
                EntityWrapper<PassingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("id", passingVehicleRecord.getId());
                passingVehicleRecordCrudService.update(passingVehicleRecordUpdate, entityWrapper);

                resultDto.success();
            }
        } catch (Exception e) {
            log.error("添加过车记录备注失败" + e.getMessage());
            return resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 人工校对海康过车记录
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto proofreadPassVehicleRecord(PassVehicleRecordProofreadRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (!StringUtils.isNumbers(requestDto.getPassingNo())) {
                return resultDto.makeResult(PmsResultEnum.PASSING_NO_NOT_STANDARD.getValue(), PmsResultEnum.PASSING_NO_NOT_STANDARD.getComment());
            }
            EntityWrapper<PassingVehicleRecordEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("passingNo", requestDto.getPassingNo());
            PassingVehicleRecordEntity passingVehicleRecord = passingVehicleRecordCrudService.selectOne(wrapper);
            if (passingVehicleRecord == null) {
                resultDto.makeResult(PmsResultEnum.PASSING_RECORD_NOT_FOUND.getValue(),
                        PmsResultEnum.PASSING_RECORD_NOT_FOUND.getComment()
                );
            } else {
                PassingVehicleRecordEntity passingVehicleRecordUpdate = new PassingVehicleRecordEntity();
                passingVehicleRecordUpdate.setProofStatus(true);
                //更新校验状态
                EntityWrapper<PassingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("id", passingVehicleRecord.getId());
                passingVehicleRecordCrudService.update(passingVehicleRecordUpdate, entityWrapper);

                resultDto.success();
            }
        } catch (Exception e) {
            log.error("人工校对过车记录失败" + e.getMessage());
            return resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 带租户根据过车记录号获取过车记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<PassingVehicleRecordResultDto> getByPassNo(PassingVehicleRecordGetRequestDto requestDto) {
        ObjectResultDto<PassingVehicleRecordResultDto> resultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<PassingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("passingNo", requestDto.getPassingNo());
            if (requestDto.getParkingId() != null) {
                entityWrapper.eq("parkingId", requestDto.getParkingId());
            }
            PassingVehicleRecordEntity passingVehicleRecord = passingVehicleRecordCrudService.selectOne(entityWrapper);
            if (passingVehicleRecord == null) {
                return resultDto.makeResult(PmsResultEnum.PASSING_RECORD_NOT_FOUND.getValue(), PmsResultEnum.PASSING_RECORD_NOT_FOUND.getComment());
            }
            PassingVehicleRecordResultDto passingVehicleRecordResultDto = modelMapper.map(passingVehicleRecord, PassingVehicleRecordResultDto.class);
            resultDto.setData(passingVehicleRecordResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("根据过车记录号获取过车记录失败", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 带租户根据过车记录流水号更新过车记录
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
            EntityWrapper<PassingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("passingNo", requestDto.getPassingNo());
            if (requestDto.getParkingId() != null) {
                entityWrapper.eq("parkingId", requestDto.getParkingId());
            }
            boolean update = passingVehicleRecordCrudService.update(passingVehicleRecordEntity, entityWrapper);
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
     * 带租户添加过车记录
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<PassingVehicleRecordCreatedResultDto> savePassRecord(PassingVehicleRecordCreateRequestDto requestDto) {
        ObjectResultDto<PassingVehicleRecordCreatedResultDto> resultDto = new ObjectResultDto<>();
        try {
            PassingVehicleRecordEntity recordEntity = modelMapper.map(requestDto, PassingVehicleRecordEntity.class);
            boolean insert = passingVehicleRecordCrudService.insert(recordEntity);
            if (!insert) {
                resultDto.failed();
            }
            PassingVehicleRecordCreatedResultDto passingVehicleRecordSaveResultDto = new PassingVehicleRecordCreatedResultDto();
            passingVehicleRecordSaveResultDto.setId(recordEntity.getId());
            resultDto.setData(passingVehicleRecordSaveResultDto);
            resultDto.success();
        } catch (Exception e) {
            resultDto.failed();
            log.error("添加过车记录失败" + e.getMessage());
        }
        return resultDto;
    }

    /**
     * 获取异常过车记录
     *
     * @param requestDto
     */
    @Override
    public ListResultDto<PassingVehicleRecordResultDto> getExceptionPassRecord(PassVehicleGetExceptionRequestDto requestDto) {
        ListResultDto<PassingVehicleRecordResultDto> resultDto = new ListResultDto<>();
        try {
            EntityWrapper<PassingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            entityWrapper.eq("passingType", requestDto.getPassingType());
            entityWrapper.ne("abnormalType", PassingExceptionTypeEnum.NOT_EXCEPTION.getComment());
            entityWrapper.gt("passTime", requestDto.getPassTime());
            entityWrapper.andNew("plateNumber = '" + requestDto.getPlateNumber() + "'", 0).or("parkingLotId = " + requestDto.getParkingLotId());
            entityWrapper.orderBy("passTime");
            List<PassingVehicleRecordEntity> outPassException = passingVehicleRecordCrudService.selectList(entityWrapper);
            if (CollectionUtils.isNotEmpty(outPassException)) {
                List<PassingVehicleRecordResultDto> exceptionRecordResultDtos = modelMapper.map(outPassException,
                        new TypeToken<List<PassingVehicleRecordResultDto>>() {
                        }.getType());
                resultDto.setItems(exceptionRecordResultDtos);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取异常过车记录失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改过车记录泊位
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updatePassVehicleRecordByCode(PassVehicleRecordByPassingRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            //根据passNo获取过车记录
            EntityWrapper<PassingVehicleRecordEntity> passingVehicleRecordEntityEntityWrapper = new EntityWrapper<>();
            passingVehicleRecordEntityEntityWrapper.eq("passingNo", requestDto.getPassingNo());
            PassingVehicleRecordEntity passingVehicleRecordEntity = passingVehicleRecordCrudService.selectPassRecord(passingVehicleRecordEntityEntityWrapper);
            if (null != passingVehicleRecordEntity) {
                //修改泊位
                passingVehicleRecordEntity.setParkingLotId(requestDto.getParkingLotId());
                passingVehicleRecordEntity.setParkingLotNumber(requestDto.getParkingLotNumber());
                passingVehicleRecordCrudService.updateByPassNo(passingVehicleRecordEntity);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("修改过车记录泊位失败" + e.getMessage(), e);
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 无租户根据过车记录号获取过车记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<PassingVehicleRecordResultDto> getPassVehicleByPassNo(PassingVehicleRecordGetRequestDto requestDto) {
        ObjectResultDto<PassingVehicleRecordResultDto> resultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<PassingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("passingNo", requestDto.getPassingNo());
            PassingVehicleRecordEntity passingVehicleRecord = passingVehicleRecordCrudService.selectPassRecord(entityWrapper);
            if (passingVehicleRecord == null) {
                return resultDto.makeResult(PmsResultEnum.PASSING_RECORD_NOT_FOUND.getValue(), PmsResultEnum.PASSING_RECORD_NOT_FOUND.getComment());
            }
            PassingVehicleRecordResultDto passingVehicleRecordResultDto = modelMapper.map(passingVehicleRecord, PassingVehicleRecordResultDto.class);
            resultDto.setData(passingVehicleRecordResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("根据过车记录号获取过车记录失败", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

}
