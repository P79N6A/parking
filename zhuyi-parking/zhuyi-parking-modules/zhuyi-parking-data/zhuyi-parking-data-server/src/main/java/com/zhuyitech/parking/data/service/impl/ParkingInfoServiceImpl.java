package com.zhuyitech.parking.data.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.core.dto.request.PagedResultRequestDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.data.domain.ParkingInfo;
import com.zhuyitech.parking.data.dto.result.park.ParkingResultDto;
import com.zhuyitech.parking.data.service.ParkingInfoCrudService;
import com.zhuyitech.parking.data.service.api.ParkingInfoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 停车场服务
 *
 * @author walkman
 * @date 2017-12-01
 */
@Service("parkingInfoService")
public class ParkingInfoServiceImpl implements ParkingInfoService {

    private static final Logger LOG = LoggerFactory.getLogger(ParkingInfoServiceImpl.class);

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;


    @Autowired
    private ModelMapper modelMapper;

    /**
     * 分页获取停车场列表
     */
    @Override
    public PagedResultDto<ParkingResultDto> getParkingPagedList(PagedResultRequestDto requestDto) {
        PagedResultDto<ParkingResultDto> pagedResultDto = new PagedResultDto<>();
        try {

            EntityWrapper<ParkingInfo> entityWrapper = new EntityWrapper<>();
            Page<ParkingInfo> parkingPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingInfo> parkingPageList = parkingInfoCrudService.selectPage(parkingPage, entityWrapper);

            if (CollectionUtil.isNotEmpty(parkingPageList.getRecords())) {
                List<ParkingResultDto> parkingDtoList = modelMapper.map(parkingPageList.getRecords(), new TypeToken<List<ParkingResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(parkingPageList.getCurrent());
                pagedResultDto.setPageSize(parkingPageList.getSize());
                pagedResultDto.setTotalCount(parkingPageList.getTotal());
                pagedResultDto.setItems(parkingDtoList);

            }
            pagedResultDto.success();
        } catch (Exception e) {
            LOG.error("获取停车场失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

}
