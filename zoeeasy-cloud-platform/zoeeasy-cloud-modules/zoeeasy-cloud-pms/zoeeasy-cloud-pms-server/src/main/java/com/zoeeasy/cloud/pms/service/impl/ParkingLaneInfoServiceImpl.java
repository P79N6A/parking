package com.zoeeasy.cloud.pms.service.impl;

import cn.hutool.poi.excel.sax.Excel03SaxReader;
import cn.hutool.poi.excel.sax.Excel07SaxReader;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.domain.*;
import com.zoeeasy.cloud.pms.enums.ParkingLaneTypeEnum;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.lane.ParkingLaneInfoService;
import com.zoeeasy.cloud.pms.lane.cst.ParkingLaneConstant;
import com.zoeeasy.cloud.pms.lane.dto.request.*;
import com.zoeeasy.cloud.pms.lane.dto.result.ParkingLaneListGetResultDto;
import com.zoeeasy.cloud.pms.lane.dto.result.ParkingLaneQueryPagedResultDto;
import com.zoeeasy.cloud.pms.lane.dto.result.ParkingLaneResultDto;
import com.zoeeasy.cloud.pms.lane.validator.ParkingLaneAddValidator;
import com.zoeeasy.cloud.pms.lane.validator.ParkingLaneDeleteValidator;
import com.zoeeasy.cloud.pms.lane.validator.ParkingLaneUpdateValidator;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingListGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListGetResultDto;
import com.zoeeasy.cloud.pms.service.*;
import com.zoeeasy.cloud.tool.enums.RegionLevelEnum;
import lombok.extern.slf4j.Slf4j;
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
 * 车道服务
 *
 * @author Kane
 * @date 2018-09-21
 */
@Service("parkingLaneInfoService")
@Slf4j
public class ParkingLaneInfoServiceImpl implements ParkingLaneInfoService {

    @Autowired
    private ParkingLaneInfoCrudService parkingLaneInfoCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Autowired
    private ParkingGateInfoCrudService parkingGateInfoCrudService;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private AreaCrudService areaCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 添加车道
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto addParkingLane(@FluentValid(ParkingLaneAddValidator.class) ParkingLaneAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingLaneInfoEntity parkingLaneInfoEntity = modelMapper.map(requestDto, ParkingLaneInfoEntity.class);
            parkingLaneInfoCrudService.insert(parkingLaneInfoEntity);
            //如果归属于gate,该gate的LaneCount应当加一
            if (requestDto.getGateId() != null) {
                ParkingGateInfoEntity parkingGateInfoEntity = parkingGateInfoCrudService.selectById(requestDto.getGateId());
                ParkingGateInfoEntity update = new ParkingGateInfoEntity();
                update.setLaneCount(parkingGateInfoEntity.getLaneCount() + ParkingLaneConstant.PARKING_LANE_COUNT_CHANGE);
                Wrapper<ParkingGateInfoEntity> entityEntityWrapper = new EntityWrapper<>();
                entityEntityWrapper.eq("id", parkingGateInfoEntity.getId());
                parkingGateInfoCrudService.update(update, entityEntityWrapper);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("添加车道失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除车道
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto deleteParkingLane(@FluentValid({ParkingLaneDeleteValidator.class}) ParkingLaneDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingLaneInfoEntity parkingLaneInfoEntity = parkingLaneInfoCrudService.selectById(requestDto.getId());
            parkingLaneInfoCrudService.deleteById(requestDto.getId());
            //如果归属于gate,该gate的LaneCount应当减一
            if (parkingLaneInfoEntity.getGateId() != null) {
                ParkingGateInfoEntity parkingGateInfoEntity = parkingGateInfoCrudService.selectById(parkingLaneInfoEntity.getGateId());
                ParkingGateInfoEntity update = new ParkingGateInfoEntity();
                update.setLaneCount(parkingGateInfoEntity.getLaneCount() - ParkingLaneConstant.PARKING_LANE_COUNT_CHANGE);
                Wrapper<ParkingGateInfoEntity> entityEntityWrapper = new EntityWrapper<>();
                entityEntityWrapper.eq("id", parkingGateInfoEntity.getId());
                parkingGateInfoCrudService.update(update, entityEntityWrapper);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("删除车道失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改车道
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateParkingLane(@FluentValid(ParkingLaneUpdateValidator.class) ParkingLaneUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingLaneInfoEntity parkingLaneInfoEntity = modelMapper.map(requestDto, ParkingLaneInfoEntity.class);
            if (parkingLaneInfoEntity != null) {
                EntityWrapper<ParkingLaneInfoEntity> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("id", requestDto.getId());
                parkingLaneInfoCrudService.update(parkingLaneInfoEntity, entityWrapper);
            } else {
                return resultDto.makeResult(PmsResultEnum.PARKING_LANE_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_LANE_NOT_FOUND.getComment());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("修改车道失败:" + e.getMessage());
            resultDto.failed();
        }

        return resultDto;
    }

    /**
     * 获取车道
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingLaneResultDto> getParkingLane(ParkingLaneGetRequestDto requestDto) {
        ObjectResultDto<ParkingLaneResultDto> objectResultDto = new ObjectResultDto<>();

        try {
            if (requestDto.getId() != null) {
                ParkingLaneInfoEntity parkingLaneInfoEntity = parkingLaneInfoCrudService.selectById(requestDto.getId());
                if (parkingLaneInfoEntity != null) {
                    ParkingLaneResultDto parkingLaneResultDto = modelMapper.map(parkingLaneInfoEntity, ParkingLaneResultDto.class);

                    ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingLaneInfoEntity.getParkingId());
                    if (parkingInfoEntity != null) {
                        Map<String, String> map = getAreaNames(parkingInfoEntity.getAreaId());
                        parkingLaneResultDto.setProvinceName(map.get("provinceName"));
                        parkingLaneResultDto.setCityName(map.get("cityName"));
                        parkingLaneResultDto.setCustomName(map.get("customName"));
                        parkingLaneResultDto.setParkingName(parkingInfoEntity.getFullName());

                    }
                    if (parkingLaneInfoEntity.getGarageId() != null) {
                        ParkingGarageInfoEntity parkingGarageInfoEntity = parkingGarageInfoCrudService.selectById(parkingLaneInfoEntity.getGarageId());
                        if (parkingGarageInfoEntity != null) {
                            parkingLaneResultDto.setGarageName(parkingGarageInfoEntity.getName());
                        }
                    }
                    if (parkingLaneInfoEntity.getGateId() != null) {
                        ParkingGateInfoEntity parkingGateInfoEntity = parkingGateInfoCrudService.selectById(parkingLaneInfoEntity.getGateId());
                        if (parkingGateInfoEntity != null) {
                            parkingLaneResultDto.setGateName(parkingGateInfoEntity.getName());
                        }
                    }
                    objectResultDto.setData(parkingLaneResultDto);
                    objectResultDto.success();
                }
            }
        } catch (Exception e) {
            log.error("获取车道失败:" + e.getMessage());
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
     * 获取车道列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ParkingLaneListGetResultDto> getParkingLaneList(ParkingLaneListGetRequestDto requestDto) {
        ListResultDto<ParkingLaneListGetResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<ParkingLaneInfoEntity> entityWrapper = new EntityWrapper<>();
            if (requestDto.getParkingId() != null) {
                entityWrapper.eq("parkingId", requestDto.getParkingId());
            }
            if (requestDto.getName() != null) {
                entityWrapper.like("name", requestDto.getName());
            }
            List<ParkingLaneInfoEntity> parkingLaneInfoEntityList = parkingLaneInfoCrudService.selectList(entityWrapper);
            if (!parkingLaneInfoEntityList.isEmpty()) {
                List<ParkingLaneListGetResultDto> resultDtoList = modelMapper.map(parkingLaneInfoEntityList,
                        new TypeToken<List<ParkingLaneListGetResultDto>>() {
                        }.getType());
                listResultDto.setItems(resultDtoList);
            }
            listResultDto.success();

        } catch (Exception e) {
            log.error("获取车道列表失败:" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }


    /**
     * 分页获取车道列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<ParkingLaneQueryPagedResultDto> getParkingLanePagedList(ParkingLaneQueryPagedResultRequestDto requestDto) {
        PagedResultDto<ParkingLaneQueryPagedResultDto> pagedResultDto = new PagedResultDto<>();

        try {
            EntityWrapper<ParkingLaneInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.orderBy("creationTime", false);
            List<Long> parkingIds = new ArrayList<>();
            Page<ParkingLaneInfoEntity> parkingLaneInfoEntityPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingLaneInfoEntity> parkingLaneInfoEntityPageList = new Page<>();
            if (requestDto.getAreaCode() == null && StringUtils.isEmpty(requestDto.getParkingName()) && StringUtils.isEmpty(requestDto.getLotType())) {
                parkingIds = null;
                if (!StringUtils.isEmpty(requestDto.getName())) {
                    entityWrapper.like("name", requestDto.getName());
                }
                if (!StringUtils.isEmpty(requestDto.getDirection())) {
                    entityWrapper.eq("direction", requestDto.getDirection());
                }
                if (!StringUtils.isEmpty(requestDto.getCode())) {
                    entityWrapper.like("code", requestDto.getCode());
                }

                parkingLaneInfoEntityPageList = parkingLaneInfoCrudService.selectPage(parkingLaneInfoEntityPage, entityWrapper);
            } else {
                ParkingListGetRequestDto parkingListGetRequestDto = new ParkingListGetRequestDto();
                if (requestDto.getAreaCode() != null) {
                    parkingListGetRequestDto.setAreaCode(requestDto.getAreaCode());
                }
                if (!StringUtils.isEmpty(requestDto.getLotType())) {
                    parkingListGetRequestDto.setLotType(requestDto.getLotType());
                }
                if (!StringUtils.isEmpty(requestDto.getParkingName())) {
                    parkingListGetRequestDto.setName(requestDto.getParkingName());
                }
                ListResultDto<ParkingListGetResultDto> list = parkingInfoService.getParkingList(parkingListGetRequestDto);
                if (!list.getItems().isEmpty()) {
                    for (ParkingListGetResultDto parkingListGetResultDto : list.getItems()) {
                        parkingIds.add(parkingListGetResultDto.getId());
                    }
                }
                if (parkingIds.size() > 0) {
                    entityWrapper.in("parkingId", parkingIds);
                    if (!StringUtils.isEmpty(requestDto.getName())) {
                        entityWrapper.like("name", requestDto.getName());
                    }
                    if (!StringUtils.isEmpty(requestDto.getDirection())) {
                        entityWrapper.eq("direction", requestDto.getDirection());
                    }
                    if (!StringUtils.isEmpty(requestDto.getCode())) {
                        entityWrapper.like("code", requestDto.getCode());
                    }
                    parkingLaneInfoEntityPageList = parkingLaneInfoCrudService.selectPage(parkingLaneInfoEntityPage, entityWrapper);

                }
            }

            List<ParkingLaneQueryPagedResultDto> parkingLaneResultDtoList = modelMapper.map(parkingLaneInfoEntityPageList.getRecords(),
                    new TypeToken<List<ParkingLaneQueryPagedResultDto>>() {
                    }.getType());
            if (parkingLaneResultDtoList != null) {
                Map<Long, ParkingInfoEntity> map = new HashMap();
                for (ParkingLaneQueryPagedResultDto parkingLaneQueryPagedResultDto : parkingLaneResultDtoList) {
                    if (map.keySet().contains(parkingLaneQueryPagedResultDto.getParkingId())) {
                        ParkingInfoEntity parkingInfoEntity = map.get(parkingLaneQueryPagedResultDto.getParkingId());
                        parkingLaneQueryPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
                        parkingLaneQueryPagedResultDto.setParkingCode(parkingInfoEntity.getCode());
                    } else {
                        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingLaneQueryPagedResultDto.getParkingId());
                        if (parkingInfoEntity != null) {
                            parkingLaneQueryPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
                            parkingLaneQueryPagedResultDto.setParkingCode(parkingInfoEntity.getCode());
                            map.put(parkingLaneQueryPagedResultDto.getParkingId(), parkingInfoEntity);
                        }
                    }
                }
                pagedResultDto.setPageNo(parkingLaneInfoEntityPageList.getCurrent());
                pagedResultDto.setPageSize(parkingLaneInfoEntityPageList.getSize());
                pagedResultDto.setTotalCount(parkingLaneInfoEntityPageList.getTotal());
                pagedResultDto.setItems(parkingLaneResultDtoList);
            }

            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页查询失败:" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;

    }

    /**
     * 批量删除车道
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteBatchParkingLane(ParkingLaneBatchDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            parkingLaneInfoCrudService.deleteBatchIds(requestDto.getIds());
            resultDto.success();
        } catch (Exception e) {
            log.error("批量删除车道失败:" + e.getMessage());
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
        List<ParkingLaneInfoEntity> parkingLaneList = new ArrayList<>();
        try {
            InputStream inputStream = new ByteArrayInputStream(bytes);
            if (fileName.endsWith("xls")) {
                Excel03SaxReader reader = new Excel03SaxReader(createRowHandler(parkingLaneList));
                reader.read(inputStream);
            } else if (fileName.endsWith("xlsx")) {
                Excel07SaxReader reader = new Excel07SaxReader(createRowHandler(parkingLaneList));
                reader.read(inputStream);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error(":" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取车道类型
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getParkingLaneType(ParkingLaneTypeGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> resultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> list = new ArrayList<>();
            for (ParkingLaneTypeEnum parkingLaneTypeEnum : ParkingLaneTypeEnum.values()) {
                ComboboxItemDto comboboxItemDto = new ComboboxItemDto(String.valueOf(
                        parkingLaneTypeEnum.getValue()), parkingLaneTypeEnum.getComment(), false);
                list.add(comboboxItemDto);
            }
            resultDto.setItems(list);
            resultDto.success();
        } catch (Exception e) {
            log.error("获取车道类型失败:" + e.getMessage());
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
    public RowHandler createRowHandler(List<ParkingLaneInfoEntity> parkingGateList) {
        return new RowHandler() {
            @Override
            public void handle(int sheetIndex, int rowIndex, List<Object> rowList) {
                try {
                    ParkingLaneInfoEntity parkingLaneInfoEntity = new ParkingLaneInfoEntity();
                    if (rowList.get(0) != null) {
                        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.findByFullName(String.valueOf(rowList.get(0)));
                        if (parkingInfoEntity != null) {
                            parkingLaneInfoEntity.setParkingId(parkingInfoEntity.getId());
                        }
                    }
                    if (rowList.get(1) != null) {
                        ParkingGateInfoEntity parkingGarageInfoEntity = parkingGateInfoCrudService.findByName(String.valueOf(rowList.get(1)));
                        if (parkingGarageInfoEntity != null) {
                            parkingLaneInfoEntity.setGarageId(parkingGarageInfoEntity.getId());
                        }
                    }
                    if (rowList.get(2) != null) {
                        ParkingLaneInfoEntity parkingGateExist = parkingLaneInfoCrudService.findByCode(String.valueOf(rowList.get(2)));
                        if (parkingGateExist == null) {
                            parkingLaneInfoEntity.setCode(String.valueOf(rowList.get(2)));
                        }
                    }
                    if (rowList.get(3) != null) {
                        parkingLaneInfoEntity.setName(String.valueOf(rowList.get(3)));
                    }
                    if (rowList.get(4) != null) {
                        parkingLaneInfoEntity.setDirection(ParkingLaneTypeEnum.getValue(String.valueOf(rowList.get(4))).getValue());
                    }
                    if (rowList.get(5) != null) {
                        parkingLaneInfoEntity.setRemarks(String.valueOf(rowList.get(5)));
                    }
                    parkingGateList.add(parkingLaneInfoEntity);

                } catch (Exception e) {
                    throw new RuntimeException("处理Excel文件失败:" + e.getMessage());
                }
            }
        };
    }

}
