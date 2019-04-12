package com.zoeeasy.cloud.pds.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.pds.domain.MagneticErrorReportEntity;
import com.zoeeasy.cloud.pds.enums.MagneticErrorReportAbnormityEnum;
import com.zoeeasy.cloud.pds.magneticdetector.MagneticDetectorService;
import com.zoeeasy.cloud.pds.magneticdetector.cst.ColumnConstant;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.MagneticDetectorGetRequestByIdDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.park.MagneticDetectorResultDto;
import com.zoeeasy.cloud.pds.magneticerrorreport.MagneticErrorReportService;
import com.zoeeasy.cloud.pds.magneticerrorreport.dto.request.MagneticErrorReportListResultRequestDto;
import com.zoeeasy.cloud.pds.magneticerrorreport.dto.request.MagneticErrorReportQueryPagedResultRequestDto;
import com.zoeeasy.cloud.pds.magneticerrorreport.dto.request.MagneticErrorSaveRequestDto;
import com.zoeeasy.cloud.pds.magneticerrorreport.dto.result.MagneticErrorReportResultDto;
import com.zoeeasy.cloud.pds.service.MagneticErrorReportMapperCrudService;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListGetResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingResultDto;
import com.zoeeasy.cloud.ucc.tenant.TenantService;
import com.zoeeasy.cloud.ucc.tenant.dto.request.TenantGetRequestDto;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 地磁误报处理记录
 *
 * @Date: 2018/10/18
 * @author: lhj
 */
@Service("magneticErrorReportService")
@Slf4j
public class MagneticErrorReportServiceImpl implements MagneticErrorReportService {

    @Autowired
    private MagneticErrorReportMapperCrudService magneticErrorReportMapperCrudService;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private MagneticDetectorService magneticDetectorService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TenantService tenantService;

    /**
     * 查询地磁误报记录列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<MagneticErrorReportResultDto> getMagneticErrorReportList(MagneticErrorReportListResultRequestDto requestDto) {
        ListResultDto<MagneticErrorReportResultDto> listResultDto = new ListResultDto<>();
        List<Long> parkingIds = new ArrayList<>();
        try {
            EntityWrapper<MagneticErrorReportEntity> entityWrapper = new EntityWrapper();
            ParkingListGetRequestDto parkingListGetRequestDto = new ParkingListGetRequestDto();
            if (!StringUtils.isEmpty(requestDto.getAreaCode())) {
                parkingListGetRequestDto.setAreaCode(requestDto.getAreaCode());
                ListResultDto<ParkingListGetResultDto> parkingList = parkingInfoService.getParkingList(parkingListGetRequestDto);
                for (ParkingListGetResultDto parkingListGetResultDto : parkingList.getItems()) {
                    parkingIds.add(parkingListGetResultDto.getId());
                }
                if (CollectionUtils.isNotEmpty(parkingIds)) {
                    entityWrapper.in(ColumnConstant.PARKING_ID, parkingIds);
                }
            }
            if (null != requestDto.getStartTime()) {
                entityWrapper.ge(ColumnConstant.PROCESSTIME, requestDto.getStartTime());
            }
            if (null != requestDto.getEndTime()) {
                entityWrapper.le(ColumnConstant.PROCESSTIME, requestDto.getEndTime());
            }
            entityWrapper.orderBy(ColumnConstant.PROCESSTIME, false);
            List<MagneticErrorReportEntity> magneticErrorReportEntities = magneticErrorReportMapperCrudService.selectList(entityWrapper);
            if (CollectionUtils.isNotEmpty(magneticErrorReportEntities)) {
                List<MagneticErrorReportResultDto> magneticDetectorStatusQueryPageResultDtoList = new ArrayList<>();
                for (MagneticErrorReportEntity magneticErrorReportEntity : magneticErrorReportEntities) {
                    MagneticErrorReportResultDto parkingAndProvider = getParkingAndProvider(magneticErrorReportEntity);
                    //异常记录时间
                    parkingAndProvider.setProcessTime(magneticErrorReportEntity.getProcessTime());
                    parkingAndProvider.setId(magneticErrorReportEntity.getId());
                    magneticDetectorStatusQueryPageResultDtoList.add(parkingAndProvider);
                }
                listResultDto.setItems(magneticDetectorStatusQueryPageResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("查询地磁误报记录列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 分页查询地磁误报记录列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<MagneticErrorReportResultDto> getMagneticErrorReportPagedList(MagneticErrorReportQueryPagedResultRequestDto requestDto) {
        PagedResultDto<MagneticErrorReportResultDto> pagedResultDto = new PagedResultDto<>();
        List<Long> parkingIds = new ArrayList<>();
        try {
            EntityWrapper<MagneticErrorReportEntity> entityWrapper = new EntityWrapper();
            ParkingListGetRequestDto parkingListGetRequestDto = new ParkingListGetRequestDto();
            if (!StringUtils.isEmpty(requestDto.getAreaCode())) {
                parkingListGetRequestDto.setAreaCode(requestDto.getAreaCode());
                ListResultDto<ParkingListGetResultDto> parkingList = parkingInfoService.getParkingList(parkingListGetRequestDto);
                List<ParkingListGetResultDto> parkingListGetResultDtos = parkingList.getItems();
                if (CollectionUtils.isNotEmpty(parkingListGetResultDtos)) {
                    for (ParkingListGetResultDto parkingListGetResultDto : parkingListGetResultDtos) {
                        parkingIds.add(parkingListGetResultDto.getId());
                    }
                    entityWrapper.in(ColumnConstant.PARKING_ID, parkingIds);
                } else {
                    pagedResultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                    pagedResultDto.success();
                    return pagedResultDto;
                }
            }
            if (null != requestDto.getStartTime()) {
                entityWrapper.ge(ColumnConstant.PROCESSTIME, requestDto.getStartTime());
            }
            if (null != requestDto.getEndTime()) {
                entityWrapper.le(ColumnConstant.PROCESSTIME, requestDto.getEndTime());
            }
            entityWrapper.orderBy(ColumnConstant.PROCESSTIME, false);
            Page<MagneticErrorReportEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<MagneticErrorReportEntity> magneticErrorReportEntityPage = magneticErrorReportMapperCrudService.selectPage(page, entityWrapper);
            if (magneticErrorReportEntityPage.getRecords() != null) {
                List<MagneticErrorReportResultDto> magneticDetectorStatusQueryPageResultDtoList = new ArrayList<>();
                for (MagneticErrorReportEntity magneticErrorReportEntity : magneticErrorReportEntityPage.getRecords()) {
                    MagneticErrorReportResultDto parkingAndProvider = getParkingAndProvider(magneticErrorReportEntity);
                    //异常记录时间
                    parkingAndProvider.setProcessTime(magneticErrorReportEntity.getProcessTime());
                    parkingAndProvider.setId(magneticErrorReportEntity.getId());
                    magneticDetectorStatusQueryPageResultDtoList.add(parkingAndProvider);
                }
                pagedResultDto.setPageNo(magneticErrorReportEntityPage.getCurrent());
                pagedResultDto.setPageSize(magneticErrorReportEntityPage.getSize());
                pagedResultDto.setTotalCount(magneticErrorReportEntityPage.getTotal());
                pagedResultDto.setItems(magneticDetectorStatusQueryPageResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页查询地磁误报记录列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 保存地磁误报记录
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto save(MagneticErrorSaveRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            MagneticErrorReportEntity magneticErrorReportEntity = modelMapper.map(requestDto, MagneticErrorReportEntity.class);
            Boolean insert = magneticErrorReportMapperCrudService.insert(magneticErrorReportEntity);
            if (!insert) {
                resultDto.failed();
                return resultDto;
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("保存地磁误报记录失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 查询停车场名称、商户名称、地磁编号
     *
     * @param magneticErrorReportEntity
     * @return
     */
    private MagneticErrorReportResultDto getParkingAndProvider(MagneticErrorReportEntity magneticErrorReportEntity) {
        MagneticErrorReportResultDto magneticErrorReportResultDto = new MagneticErrorReportResultDto();
        //停车场
        ParkingGetRequestDto parkingGetRequestDto = new ParkingGetRequestDto();
        parkingGetRequestDto.setId(magneticErrorReportEntity.getParkingId());
        ObjectResultDto<ParkingResultDto> parkingObjectResultDto = parkingInfoService.getParkingInfo(parkingGetRequestDto);
        if (null != parkingObjectResultDto.getData() && parkingObjectResultDto.isSuccess()) {
            magneticErrorReportResultDto.setParkingName(parkingObjectResultDto.getData().getFullName());
        }
        //地磁编号
        MagneticDetectorGetRequestByIdDto magneticDetectorGetRequestByIdDto = new MagneticDetectorGetRequestByIdDto();
        magneticDetectorGetRequestByIdDto.setId(magneticErrorReportEntity.getDetectorId());
        ObjectResultDto<MagneticDetectorResultDto> magneticDetectorById = magneticDetectorService.getMagneticDetectorById(magneticDetectorGetRequestByIdDto);
        magneticErrorReportResultDto.setCode(magneticDetectorById.getData().getCode());
        //商户名称
        TenantGetRequestDto tenantGetRequestDto = new TenantGetRequestDto();
        tenantGetRequestDto.setId(magneticErrorReportEntity.getTenantId());
        ObjectResultDto<TenantResultDto> tenant = tenantService.getTenant(tenantGetRequestDto);
        if (tenant.getData() != null) {
            magneticErrorReportResultDto.setTenant(tenant.getData().getName());
        }
        //异常信息
        if (magneticErrorReportEntity.getProcessReason().equals(MagneticErrorReportAbnormityEnum.MISINFORMATION_INTO.getValue())) {
            magneticErrorReportResultDto.setProcessReason(MagneticErrorReportAbnormityEnum.MISINFORMATION_INTO.getComment());
        }
        if (magneticErrorReportEntity.getProcessReason().equals(MagneticErrorReportAbnormityEnum.MISINFORMATION_OUT.getValue())) {
            magneticErrorReportResultDto.setProcessReason(MagneticErrorReportAbnormityEnum.MISINFORMATION_OUT.getComment());
        }
        if (magneticErrorReportEntity.getProcessReason().equals(MagneticErrorReportAbnormityEnum.CONTINUOUS_REPORTED.getValue())) {
            magneticErrorReportResultDto.setProcessReason(MagneticErrorReportAbnormityEnum.CONTINUOUS_REPORTED.getComment());
        }
        return magneticErrorReportResultDto;
    }
}
