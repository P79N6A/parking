package com.zoeeasy.cloud.pms.service.impl;

import cn.hutool.poi.excel.sax.Excel03SaxReader;
import cn.hutool.poi.excel.sax.Excel07SaxReader;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.domain.AreaEntity;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingGateInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.enums.ParkingGateTypeEnum;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.gate.ParkingGateInfoService;
import com.zoeeasy.cloud.pms.gate.dto.request.*;
import com.zoeeasy.cloud.pms.gate.dto.result.ParkingGateListGetResultDto;
import com.zoeeasy.cloud.pms.gate.dto.result.ParkingGateQueryPagedResultDto;
import com.zoeeasy.cloud.pms.gate.dto.result.ParkingGateResultDto;
import com.zoeeasy.cloud.pms.gate.validator.ParkingGateAddValidator;
import com.zoeeasy.cloud.pms.gate.validator.ParkingGateDeleteValidator;
import com.zoeeasy.cloud.pms.gate.validator.ParkingGateUpdateValidator;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListGetResultDto;
import com.zoeeasy.cloud.pms.service.AreaCrudService;
import com.zoeeasy.cloud.pms.service.ParkingGarageInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingGateInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.tool.enums.RegionLevelEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 进出口服务
 *
 * @author Kane
 * @date 2018-09-21
 */
@Service("parkingGateInfoService")
@Slf4j
public class ParkingGateInfoServiceImpl implements ParkingGateInfoService {

    @Autowired
    private ParkingGateInfoCrudService parkingGateInfoCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private AreaCrudService areaCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 添加进出口
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto addParkingGate(@FluentValid({ParkingGateAddValidator.class}) ParkingGateAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingGateInfoEntity parkingGateInfoEntity = modelMapper.map(requestDto, ParkingGateInfoEntity.class);
            parkingGateInfoCrudService.insert(parkingGateInfoEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("添加出入口失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除进出口
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto deleteParkingGate(@FluentValid({ParkingGateDeleteValidator.class}) ParkingGateDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            parkingGateInfoCrudService.deleteById(requestDto.getId());
            resultDto.success();
        } catch (Exception e) {
            log.error("删除出入口失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改进出口
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateParkingGate(@FluentValid({ParkingGateUpdateValidator.class}) ParkingGateUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingGateInfoEntity parkingGateInfoEntity = modelMapper.map(requestDto, ParkingGateInfoEntity.class);
            if (parkingGateInfoEntity != null) {
                EntityWrapper<ParkingGateInfoEntity> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("id", requestDto.getId());
                parkingGateInfoCrudService.update(parkingGateInfoEntity, entityWrapper);
            } else {
                return resultDto.makeResult(PmsResultEnum.PARKING_GATE_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_GATE_NOT_FOUND.getComment());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("修改出入口失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取进出口
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingGateResultDto> getParkingGate(ParkingGateGetRequestDto requestDto) {
        ObjectResultDto<ParkingGateResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            if (requestDto.getId() == null) {
                return objectResultDto.failed();
            }
            ParkingGateInfoEntity parkingGateInfoEntity = parkingGateInfoCrudService.selectById(requestDto.getId());
            if (parkingGateInfoEntity != null) {
                ParkingGateResultDto parkingGateResultDto = modelMapper.map(parkingGateInfoEntity, ParkingGateResultDto.class);
                ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingGateInfoEntity.getParkingId());
                if (parkingInfoEntity != null) {
                    Map<String, String> map = getAreaNames(parkingInfoEntity.getAreaId());
                    parkingGateResultDto.setProvinceName(map.get("provinceName"));
                    parkingGateResultDto.setCityName(map.get("cityName"));
                    parkingGateResultDto.setCustomName(map.get("customName"));
                    parkingGateResultDto.setParkingName(parkingInfoEntity.getFullName());
                }

                if (parkingGateInfoEntity.getGarageId() != null) {
                    ParkingGarageInfoEntity parkingGarageInfoEntity = parkingGarageInfoCrudService.selectById(parkingGateInfoEntity.getGarageId());
                    if (parkingGarageInfoEntity != null) {
                        parkingGateResultDto.setGarageName(parkingGarageInfoEntity.getName());
                    }
                }
                objectResultDto.setData(parkingGateResultDto);
            } else {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_GATE_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_GATE_NOT_FOUND.getComment());
            }
            objectResultDto.success();
        } catch (Exception e) {

            log.error("获取出入口失败:" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取省市区名称
     *
     * @return
     */
    private Map<String, String> getAreaNames(Long areaId) {
        Map<String, String> map = new HashMap<>();
        AreaEntity areaEntity = areaCrudService.selectById(areaId);
        while (areaEntity != null && !areaEntity.getLevel().equals(RegionLevelEnum.COUNTRY.getValue())) {
            switch (areaEntity.getLevel()) {
                case 1:
                    map.put("provinceName", areaEntity.getName());
                    break;
                case 2:
                    map.put("cityName", areaEntity.getName());
                    break;
                case 4:
                    map.put("customName", areaEntity.getName());
                    break;
            }
            areaEntity = areaCrudService.selectById(areaEntity.getParentId());
        }
        return map;
    }

    /**
     * 获取出入口列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ParkingGateListGetResultDto> getParkingGateList(ParkingGateListGetRequestDto requestDto) {
        ListResultDto<ParkingGateListGetResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<ParkingGateInfoEntity> entityWrapper = new EntityWrapper<>();
            if (requestDto.getParkingId() != null) {
                entityWrapper.eq("parkingId", requestDto.getParkingId());
            }
            if (requestDto.getName() != null) {
                entityWrapper.like("name", requestDto.getName());
            }
            List<ParkingGateInfoEntity> parkingGateInfoEntityList = parkingGateInfoCrudService.selectList(entityWrapper);
            if (!parkingGateInfoEntityList.isEmpty()) {
                List<ParkingGateListGetResultDto> resultDtoList = modelMapper.map(parkingGateInfoEntityList,
                        new TypeToken<List<ParkingGateListGetResultDto>>() {
                        }.getType());
                listResultDto.setItems(resultDtoList);
            }
            listResultDto.success();

        } catch (Exception e) {
            log.error("获取出入口列表失败:" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 分页获取进出口列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<ParkingGateQueryPagedResultDto> getParkingGatePagedList(ParkingGateQueryPagedResultRequestDto requestDto) {
        PagedResultDto<ParkingGateQueryPagedResultDto> pagedResultDto = new PagedResultDto<>();

        try {
            EntityWrapper<ParkingGateInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.orderBy("creationTime", false);
            List<Long> parkingIds = new ArrayList<>();
            Page<ParkingGateInfoEntity> parkingGateInfoEntityPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingGateInfoEntity> parkingGateInfoEntityPageList = new Page<>();
            if (requestDto.getAreaCode() == null && StringUtils.isEmpty(requestDto.getParkingName())) {
                parkingIds = null;
                if (!StringUtils.isEmpty(requestDto.getName())) {
                    entityWrapper.like("name", requestDto.getName());
                }
                if (!StringUtils.isEmpty(requestDto.getCode())) {
                    entityWrapper.like("code", requestDto.getCode());
                }
                if (!StringUtils.isEmpty(requestDto.getDirection())) {
                    entityWrapper.eq("direction", requestDto.getDirection());
                }
                parkingGateInfoEntityPageList = parkingGateInfoCrudService.selectPage(parkingGateInfoEntityPage, entityWrapper);
            } else {
                ParkingListGetRequestDto parkingEListGetRequestDto = new ParkingListGetRequestDto();
                if (requestDto.getAreaCode() != null) {
                    parkingEListGetRequestDto.setAreaCode(requestDto.getAreaCode());
                }
                if (!StringUtils.isEmpty(requestDto.getParkingName())) {
                    parkingEListGetRequestDto.setName(requestDto.getParkingName());
                }

                ListResultDto<ParkingListGetResultDto> list = parkingInfoService.getParkingList(parkingEListGetRequestDto);
                if (!list.getItems().isEmpty()) {
                    for (ParkingListGetResultDto parkingListGetResultDto : list.getItems()) {
                        parkingIds.add(parkingListGetResultDto.getId());
                    }
                }
                if (CollectionUtils.isNotEmpty(parkingIds)) {
                    entityWrapper.in("parkingId", parkingIds);
                    if (!StringUtils.isEmpty(requestDto.getName())) {
                        entityWrapper.like("name", requestDto.getName());
                    }
                    if (!StringUtils.isEmpty(requestDto.getCode())) {
                        entityWrapper.like("code", requestDto.getCode());
                    }
                    if (!StringUtils.isEmpty(requestDto.getDirection())) {
                        entityWrapper.eq("direction", requestDto.getDirection());
                    }
                    parkingGateInfoEntityPageList = parkingGateInfoCrudService.selectPage(parkingGateInfoEntityPage, entityWrapper);

                }
            }
            List<ParkingGateQueryPagedResultDto> parkingGateResultDtoList = modelMapper.map(parkingGateInfoEntityPageList.getRecords(),
                    new TypeToken<List<ParkingGateQueryPagedResultDto>>() {
                    }.getType());
            if (parkingGateResultDtoList != null) {
                Map<Long, ParkingInfoEntity> map = new HashMap();
                for (ParkingGateQueryPagedResultDto parkingGateQueryPagedResultDto : parkingGateResultDtoList) {
                    if (map.keySet().contains(parkingGateQueryPagedResultDto.getParkingId())) {
                        ParkingInfoEntity parkingInfoEntity = map.get(parkingGateQueryPagedResultDto.getParkingId());
                        parkingGateQueryPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
                        parkingGateQueryPagedResultDto.setParkingCode(parkingInfoEntity.getCode());
                    } else {
                        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingGateQueryPagedResultDto.getParkingId());
                        if (parkingInfoEntity != null) {
                            parkingGateQueryPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
                            parkingGateQueryPagedResultDto.setParkingCode(parkingInfoEntity.getCode());
                            map.put(parkingGateQueryPagedResultDto.getParkingId(), parkingInfoEntity);
                        }
                    }
                    pagedResultDto.setPageNo(parkingGateInfoEntityPageList.getCurrent());
                    pagedResultDto.setPageSize(parkingGateInfoEntityPageList.getSize());
                    pagedResultDto.setTotalCount(parkingGateInfoEntityPageList.getTotal());
                    pagedResultDto.setItems(parkingGateResultDtoList);
                }
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页查询失败:" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 批量删除进出口
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto deleteBatchParkingGate(ParkingGateBatchDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            parkingGateInfoCrudService.deleteBatchIds(requestDto.getIds());
            resultDto.success();
        } catch (Exception e) {
            log.error("批量删除进出口失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 批量导入
     *
     * @param bytes
     * @param fileName
     * @return
     */
    @Override
    public ResultDto importExcel(byte[] bytes, String fileName) {
        ResultDto resultDto = new ResultDto();
        List<ParkingGateInfoEntity> parkingGateList = new ArrayList<>();
        try {
            InputStream inputStream = new ByteArrayInputStream(bytes);
            if (fileName.endsWith("xls")) {
                Excel03SaxReader reader = new Excel03SaxReader(createRowHandler(parkingGateList));
                reader.read(inputStream);
            } else if (fileName.endsWith("xlsx")) {
                Excel07SaxReader reader = new Excel07SaxReader(createRowHandler(parkingGateList));
                reader.read(inputStream);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("批量导入失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取出入口类型
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getParkingGateType(ParkingGateTypeGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> resultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (ParkingGateTypeEnum parkingGateTypeEnum : ParkingGateTypeEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf
                        (parkingGateTypeEnum.getValue()), parkingGateTypeEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            resultDto.setItems(list);
            resultDto.success();
        } catch (Exception e) {
            log.error("获取出入口类型失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 处理Excel文件
     *
     * @param parkingGateList
     * @return
     */
    public RowHandler createRowHandler(List<ParkingGateInfoEntity> parkingGateList) {
        return new RowHandler() {
            @Override
            public void handle(int sheetIndex, int rowIndex, List<Object> rowList) {
                try {
                    if (rowIndex != 0) {
                        ParkingGateInfoEntity parkingGateInfoEntity = new ParkingGateInfoEntity();
                        if (rowList.get(0) != null) {
                            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.findByFullName(String.valueOf(rowList.get(0)));
                            if (parkingInfoEntity != null) {
                                parkingGateInfoEntity.setParkingId(parkingInfoEntity.getId());
                            }
                        }
                        if (rowList.get(1) != null) {
                            ParkingGarageInfoEntity parkingGarageInfoEntity = parkingGarageInfoCrudService.findByName(String.valueOf(rowList.get(1)));
                            if (parkingGarageInfoEntity != null) {
                                parkingGateInfoEntity.setGarageId(parkingGarageInfoEntity.getId());
                            }
                        }
                        if (rowList.get(2) != null) {
                            ParkingGateInfoEntity parkingGateExist = parkingGateInfoCrudService.findByCode(String.valueOf(rowList.get(2)));
                            if (parkingGateExist == null) {
                                parkingGateInfoEntity.setCode(String.valueOf(rowList.get(2)));
                            }
                        }
                        if (rowList.get(3) != null) {
                            parkingGateInfoEntity.setName(String.valueOf(rowList.get(3)));
                        }
                        if (rowList.get(4) != null) {
                            parkingGateInfoEntity.setDirection(ParkingGateTypeEnum.getValue(String.valueOf(rowList.get(4))).getValue());
                        }
                        if (rowList.get(5) != null) {
                            parkingGateInfoEntity.setRemarks(String.valueOf(rowList.get(5)));
                        }
                        parkingGateList.add(parkingGateInfoEntity);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("处理Excel文件失败:" + e.getMessage());
                }
            }
        };
    }

}
