package com.zoeeasy.cloud.order.parking;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.order.parking.dto.request.*;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderGetByOrderNoRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderGetByRecordNoRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderCreateResultDto;

/**
 * 商户订单管理服务
 *
 * @author AkeemSuper
 * @date 2018/10/7 0007
 */
public interface ParkingOrderManagerService {

    /**
     * 保存订单
     *
     * @param requestDto ParkingOrderInsertRequestDto
     * @return ObjectResultDto<ParkingOrderSaveResultDto>
     */
    ObjectResultDto<ParkingOrderCreateResultDto> createParkingOrder(ParkingOrderInsertRequestDto requestDto);

    /**
     * 获取停车账单
     *
     * @param requestDto ParkingOrderGetRequestDto
     * @return ObjectResultDto<ParkingOrderResultDto>
     */
    ObjectResultDto<ParkingOrderResultDto> getParkingOrder(ParkingOrderGetRequestDto requestDto);

    /**
     * 根据单号获取订单信息
     *
     * @param requestDto ParkingOrderGetByOrderNoRequestDto
     * @return ObjectResultDto<ParkingOrderResultDto>
     */
    ObjectResultDto<ParkingOrderResultDto> findByOrderNo(ParkingOrderGetByOrderNoRequestDto requestDto);

    /**
     * 后台分页获取用户停车订单
     *
     * @param requestDto ParkingOrderSearchRequestDto
     * @return PagedResultDto<ParkingOrderResultDto>
     */
    PagedResultDto<ParkingOrderResultDto> getParkingOrderPageList(ParkingOrderSearchRequestDto requestDto);

    /**
     * 添加网页停车账单备注
     *
     * @param requestDto ParkingOrderAddRemarkRequestDto
     * @return ResultDto
     */
    ResultDto setParkingOrderRemark(ParkingOrderAddRemarkRequestDto requestDto);

    /**
     * 修改停车账单
     *
     * @param requestDto ParkingOrderUpdateRequestDto
     * @return ResultDto
     */
    ResultDto updateParkingOrder(ParkingOrderUpdateRequestDto requestDto);

    /**
     * 删除停车账单
     *
     * @param requestDto ParkingOrderDeleteRequestDto
     * @return ResultDto
     */
    ResultDto deleteParkingOrder(ParkingOrderDeleteRequestDto requestDto);

    /**
     * 根据停车记录号获取订单信息
     *
     * @param requestDto ParkingOrderGetByRecordNoRequestDto
     * @return ObjectResultDto<ParkingOrderResultDto>
     */
    ObjectResultDto<ParkingOrderResultDto> getParkingOrderByRecordNo(ParkingOrderGetByRecordNoRequestDto requestDto);

    /**
     * 更新订单
     *
     * @param requestDto ParkingOrderUpdateForPayRequestDto
     * @return ResultDto
     */
    ResultDto update(InspectParkingOrderUpdateRequestDto requestDto);

    /**
     * 根据车牌颜色和车牌号获取订单
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingOrderResultDto> getParkingOrderByNumberAndColor(ParkingOrderGetByNumberAndColorRequestDto requestDto);

    /**
     * 根据第三方账单编号查询账单
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingOrderResultDto> getParkingOrderByThirdBillNo(ParkingOrderQueryByThirdBillNoRequestDto requestDto);
}
