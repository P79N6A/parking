package com.zhuyitech.parking.data.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.NumberUtils;
import com.scapegoat.infrastructure.core.dto.request.PagedResultRequestDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.data.domain.ParkingInfo;
import com.zhuyitech.parking.data.domain.ParkingLotInfo;
import com.zhuyitech.parking.data.domain.ParkingOrder;
import com.zhuyitech.parking.data.dto.result.order.ParkingOrderResultDto;
import com.zhuyitech.parking.data.service.ParkingInfoCrudService;
import com.zhuyitech.parking.data.service.ParkingLotInfoCrudService;
import com.zhuyitech.parking.data.service.ParkingOrderCrudService;
import com.zhuyitech.parking.data.service.api.ParkingOrderService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 停车订单服务
 *
 * @author walkman
 */
@Service(value = "parkingOrderService")
public class ParkingOrderServiceImpl implements ParkingOrderService {

    private static final Logger LOG = LoggerFactory.getLogger(ParkingOrderServiceImpl.class);

    @Autowired
    private ParkingOrderCrudService parkingOrderCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Override
    public PagedResultDto<ParkingOrderResultDto> getCloudParkingOrderPageList(PagedResultRequestDto requestDto) {
        PagedResultDto<ParkingOrderResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<ParkingOrder> entityWrapper = new EntityWrapper<>();
            Page<ParkingOrder> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingOrder> parkingOrderPage = parkingOrderCrudService.selectPage(page, entityWrapper);
            if (null != parkingOrderPage.getRecords()) {
                List<ParkingOrder> records = parkingOrderPage.getRecords();
                List<ParkingOrderResultDto> parkingResultDtoList = new ArrayList<>();
                for (ParkingOrder parkingOrder : records) {

                    ParkingOrderResultDto parkingOrderResultDto = modelMapper.map(
                            parkingOrder, ParkingOrderResultDto.class);
                    ParkingInfo parkingInfo = parkingInfoCrudService.selectById(parkingOrder.getParkingId());
                    if (parkingInfo != null) {
                        parkingOrderResultDto.setParkingCode(parkingInfo.getCode());
                    }
                    ParkingLotInfo parkingLotInfo = parkingLotInfoCrudService.selectById(
                            parkingOrderResultDto.getParkingLotId());
                    if (parkingLotInfo != null) {
                        parkingOrderResultDto.setParkingLotNumber(parkingLotInfo.getCode());
                    }
                    parkingOrderResultDto.setThirdBillNo(parkingOrder.getHikBillNo());
                    //海康威视
                    parkingOrderResultDto.setThirdBillSourceType(2);
                    parkingOrderResultDto.setThirdBillSyncStatus(parkingOrder.getHikSyncStatus());
                    parkingOrderResultDto.setSettleMode(parkingOrder.getChargeMode());
                    //元->分 结算金额
                    if (parkingOrder.getSettleAmount() != null) {
                        parkingOrderResultDto.setSettleAmount(
                                NumberUtils.amountYuan2FenInt(parkingOrder.getSettleAmount()));
                    }
                    //元->分 总金额
                    if (parkingOrder.getPayableAmount() != null) {
                        parkingOrderResultDto.setPayableAmount(
                                NumberUtils.amountYuan2Fen(parkingOrder.getPayableAmount()).intValue());
                    }
                    //元->分 实付金额
                    if (parkingOrder.getPayableAmount() != null) {
                        parkingOrderResultDto.setActualPayAmount(
                                NumberUtils.amountYuan2Fen(parkingOrder.getActualPayAmount()).intValue());
                    }
                    parkingResultDtoList.add(parkingOrderResultDto);
                }
                pagedResultDto.setPageNo(parkingOrderPage.getCurrent());
                pagedResultDto.setPageSize(parkingOrderPage.getSize());
                pagedResultDto.setTotalCount(parkingOrderPage.getTotal());
                pagedResultDto.setItems(parkingResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            LOG.error("获取停车订单失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }
}
