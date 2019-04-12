package com.zhuyitech.parking.data.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.core.dto.request.PagedResultRequestDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.parking.data.domain.ParkingImage;
import com.zhuyitech.parking.data.domain.PassingVehicleRecord;
import com.zhuyitech.parking.data.dto.result.PassingVehicleRecordResultDto;
import com.zhuyitech.parking.data.service.ParkingImageCrudService;
import com.zhuyitech.parking.data.service.PassingVehicleRecordCrudService;
import com.zhuyitech.parking.data.service.api.PassingVehicleRecordService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Description: 过车记录服务
 * @Autor: AkeemSuper
 * @Date: 2018/2/28 0028
 */
@Service("passingVehicleRecordService")
public class PassingVehicleRecordServiceImpl implements PassingVehicleRecordService {

    private static final Logger LOG = LoggerFactory.getLogger(PassingVehicleRecordServiceImpl.class);

    @Autowired
    private PassingVehicleRecordCrudService passingVehicleRecordCrudService;

    @Autowired
    private ParkingImageCrudService parkingImageCrudService;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PagedResultDto<PassingVehicleRecordResultDto> getCloudPassVehicleRecordListPage(
            PagedResultRequestDto requestDto) {
        PagedResultDto<PassingVehicleRecordResultDto> resultDto = new PagedResultDto<>();
        EntityWrapper<PassingVehicleRecord> entityWrapper = new EntityWrapper<>();
        try {
            entityWrapper.orderBy("passTime", false);
            Page<PassingVehicleRecord> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<PassingVehicleRecord> passingVehicleRecordPage = passingVehicleRecordCrudService.selectPage(
                    page, entityWrapper);
            List<PassingVehicleRecordResultDto> cloudRecordList = new ArrayList<>();
            if (passingVehicleRecordPage != null && passingVehicleRecordPage.getRecords() != null) {
                for (PassingVehicleRecord passingVehicleRecord : passingVehicleRecordPage.getRecords()) {

                    PassingVehicleRecordResultDto recordResultDto = modelMapper.map(
                            passingVehicleRecord, PassingVehicleRecordResultDto.class);

                    //获取过车图像
                    List<ParkingImage> parkingImageList = parkingImageCrudService.findPassingImage(passingVehicleRecord.getParkingId(), passingVehicleRecord.getId());

                    if (CollectionUtil.isNotEmpty(parkingImageList)) {
                        recordResultDto.setImages(parkingImageList.stream().map(ParkingImage::getImageUrl)
                                .collect(Collectors.toList()));
                    }
                    cloudRecordList.add(recordResultDto);
                }
                resultDto.setPageNo(passingVehicleRecordPage.getCurrent());
                resultDto.setPageSize(passingVehicleRecordPage.getSize());
                resultDto.setTotalCount(passingVehicleRecordPage.getTotal());
                resultDto.setItems(cloudRecordList);
            }
            resultDto.success();
        } catch (Exception e) {
            LOG.error("分页查询过车记录失败:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }


}
