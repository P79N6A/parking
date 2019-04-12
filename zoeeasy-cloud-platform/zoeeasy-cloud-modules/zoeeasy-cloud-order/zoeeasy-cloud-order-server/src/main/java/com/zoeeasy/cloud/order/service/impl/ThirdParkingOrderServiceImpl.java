package com.zoeeasy.cloud.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.core.enums.HikParkingPaymentStatusEnum;
import com.zoeeasy.cloud.core.enums.LicensePlateColorEnum;
import com.zoeeasy.cloud.core.enums.PlateColorStyleEnum;
import com.zoeeasy.cloud.core.utils.EnumConverter;
import com.zoeeasy.cloud.hikvision.bean.ParkingPaymentInfoBean;
import com.zoeeasy.cloud.hikvision.dto.request.ParkingPaymentInfoParams;
import com.zoeeasy.cloud.hikvision.dto.request.PayParkingFeeParams;
import com.zoeeasy.cloud.hikvision.dto.result.HikvisionBaseResult;
import com.zoeeasy.cloud.hikvision.dto.result.ParkingPaymentInfoResult;
import com.zoeeasy.cloud.hikvision.enums.ResultCodeEnum;
import com.zoeeasy.cloud.hikvision.service.HikvisionService;
import com.zoeeasy.cloud.order.domain.ThirdParkingOrderEntity;
import com.zoeeasy.cloud.order.hikvision.ThirdParkingOrderService;
import com.zoeeasy.cloud.order.hikvision.dto.request.*;
import com.zoeeasy.cloud.order.hikvision.dto.result.FindThirdOrderByBillNoResultDto;
import com.zoeeasy.cloud.order.hikvision.dto.result.HikvisionParkingOrderPlaceResultDto;
import com.zoeeasy.cloud.order.hikvision.dto.result.HikvisionParkingOrderResultDto;
import com.zoeeasy.cloud.order.service.ThirdParkingOrderCrudService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 海康平台订单服务
 *
 * @author AkeemSuper
 * @date 2018/9/30 0030
 */
@Service(value = "thirdParkingOrderService")
@Slf4j
public class ThirdParkingOrderServiceImpl implements ThirdParkingOrderService {

    @Autowired
    private ThirdParkingOrderCrudService thirdParkingOrderCrudService;

    @Autowired
    private HikvisionService hikvisionService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 海康平台订单请求
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<HikvisionParkingOrderPlaceResultDto> placeHikvisionParkingOrder(HikvisionParkingOrderPlaceRequestDto requestDto) {
        ObjectResultDto<HikvisionParkingOrderPlaceResultDto> resultDto = new ObjectResultDto<>();
        if (StringUtils.isEmpty(requestDto.getOrderNo()) || StringUtils.isEmpty(requestDto.getPlateNumber())) {
            return resultDto.failed();
        }
        try {
            ParkingPaymentInfoParams params = new ParkingPaymentInfoParams();
            //车牌颜色
            LicensePlateColorEnum licensePlateColor = LicensePlateColorEnum.parse(requestDto.getPlateColor());
            if (licensePlateColor != null) {
                PlateColorStyleEnum plateColor = EnumConverter.toHikPlateColorStyle(licensePlateColor);
                params.setPlateColor(plateColor.getValue());
            }
            //车牌号
            params.setPlateNo(requestDto.getPlateNumber());
            ParkingPaymentInfoResult parkingPaymentInfoResult = hikvisionService.getParkingPaymentInfo(params);
            if (parkingPaymentInfoResult != null
                    && parkingPaymentInfoResult.getErrorCode().equals(ResultCodeEnum.SUCCESS.getValue())
                    && parkingPaymentInfoResult.getData() != null) {

                ThirdParkingOrderEntity thirdParkingOrderEntity = new ThirdParkingOrderEntity();
                ParkingPaymentInfoBean paymentInfo = parkingPaymentInfoResult.getData();
                thirdParkingOrderEntity.setTenantId(requestDto.getTenantId());
                thirdParkingOrderEntity.setParkingId(requestDto.getParkingId());
                thirdParkingOrderEntity.setRecordNo(requestDto.getRecordNo());
                thirdParkingOrderEntity.setOrderNo(requestDto.getOrderNo());
                thirdParkingOrderEntity.setPlateColor(requestDto.getPlateColor());
                thirdParkingOrderEntity.setPlateNumber(requestDto.getPlateNumber());
                thirdParkingOrderEntity.setBillNo(paymentInfo.getBillCode());
                if (paymentInfo.getEnterTime().compareTo(0L) > 0) {
                    thirdParkingOrderEntity.setEnterTime(new Date(paymentInfo.getEnterTime()));
                }
                if (paymentInfo.getCostTime().compareTo(0L) > 0) {
                    thirdParkingOrderEntity.setCostTime(new Date(paymentInfo.getCostTime()));
                }
                thirdParkingOrderEntity.setParkPeriodTime(paymentInfo.getParkPeriodTime());
                thirdParkingOrderEntity.setTotalCost(paymentInfo.getTotalCost());
                thirdParkingOrderEntity.setStatus(HikParkingPaymentStatusEnum.CREATED.getValue());
                thirdParkingOrderEntity.setCreationTime(DateUtils.now());
                boolean insert = thirdParkingOrderCrudService.saveOrder(thirdParkingOrderEntity);
                if (!insert) {
                    return resultDto.failed();
                }
                HikvisionParkingOrderPlaceResultDto placeResultDto = new HikvisionParkingOrderPlaceResultDto();
                placeResultDto.setHikBillNo(paymentInfo.getBillCode());
                if (paymentInfo.getCostTime().compareTo(0L) > 0) {
                    placeResultDto.setCostTime(new Date(paymentInfo.getCostTime()));
                }
                placeResultDto.setParkingLength(paymentInfo.getParkPeriodTime());
                placeResultDto.setTotalCost(paymentInfo.getTotalCost());
                resultDto.setData(placeResultDto);
                resultDto.success();
            } else {
                resultDto.failed();
            }
        } catch (Exception e) {
            log.error("请求海康平台订单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 通过平台订单号查询海康平台过车记录
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<HikvisionParkingOrderResultDto> getByOrderNo(HikvisionParkingOrderGetByOrderNoRequestDto requestDto) {
        ObjectResultDto<HikvisionParkingOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ThirdParkingOrderEntity byOrderNo = thirdParkingOrderCrudService.findByOrderNo(requestDto.getOrderNo(),
                    requestDto.getTenantId());
            if (null != byOrderNo) {
                HikvisionParkingOrderResultDto hikvisionParkingOrderResultDto = modelMapper.map(byOrderNo, HikvisionParkingOrderResultDto.class);
                objectResultDto.setData(hikvisionParkingOrderResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("通过平台订单号获取海康停车订单失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 通过billNo查询
     */
    @Override
    public ObjectResultDto<FindThirdOrderByBillNoResultDto> findByBillNo(FindThirdOrderByBillNoRequestDto requestDto) {
        ObjectResultDto<FindThirdOrderByBillNoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ThirdParkingOrderEntity thirdParkingOrderEntity =
                    thirdParkingOrderCrudService.findByBillNo(requestDto.getBillNo());
            if (null != thirdParkingOrderEntity) {
                FindThirdOrderByBillNoResultDto findThirdOrderByBillNoResultDto = modelMapper.map(thirdParkingOrderEntity, FindThirdOrderByBillNoResultDto.class);
                objectResultDto.setData(findThirdOrderByBillNoResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("通过billNo查询失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 海康平台订单确认
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto completeHikvisionParkingOrder(HikvisionPaymentCompleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            PayParkingFeeParams params = new PayParkingFeeParams();
            params.setBillNo(requestDto.getBillNo());
            params.setPayAmount(requestDto.getPayAmount());
            //支付时间
            if (requestDto.getPayTime() != null) {
                params.setPayTime(requestDto.getPayTime().getTime());
            } else {
                params.setPayTime(DateUtils.now().getTime());
            }
            //支付类型
            if (requestDto.getPayType() != null) {
                params.setPayType(String.valueOf(requestDto.getPayType()));
            } else {
                params.setPayType("");
            }
            HikvisionBaseResult parkingPaymentInfoResult = hikvisionService.payParkingFee(params);
            if (parkingPaymentInfoResult != null
                    && parkingPaymentInfoResult.getErrorCode().equals(ResultCodeEnum.SUCCESS.getValue())) {

                ThirdParkingOrderEntity thirdParkingOrderEntity =
                        thirdParkingOrderCrudService.findByBillNo(requestDto.getBillNo());
                //更新海康平台订单状态
                if (thirdParkingOrderEntity != null) {
                    thirdParkingOrderCrudService.updatePayStatus(
                            thirdParkingOrderEntity.getId(),
                            thirdParkingOrderEntity.getBillNo(),
                            requestDto.getPayTime(),
                            HikParkingPaymentStatusEnum.PAY_SUCCESS.getValue(),
                            requestDto.getPayAmount(), requestDto.getPayType()
                    );
                }
                resultDto.success();
            } else {
                if (parkingPaymentInfoResult != null) {
                    log.warn("同步订单支付状态至海康平台失败:{}", JSON.toJSONString(parkingPaymentInfoResult));
                } else {
                    log.warn("同步订单支付状态至海康平台失败");
                }
                resultDto.failed();
            }
        } catch (Exception e) {
            resultDto.failed();
            log.warn("同步订单支付状态至海康平台失败,异常信息:{}", e.getMessage());
        }
        return resultDto;
    }

    /**
     * 保存任意停车平台订单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto saveAnyParkingOrder(AnyParkingOrderSaveRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ThirdParkingOrderEntity thirdParkingOrderEntity = new ThirdParkingOrderEntity();
            thirdParkingOrderEntity.setBillNo(requestDto.getBillNo());
            thirdParkingOrderEntity.setCarType(requestDto.getCarType());
            thirdParkingOrderEntity.setOrderNo(requestDto.getOrderNo());
            thirdParkingOrderEntity.setParkingId(requestDto.getParkingId());
            thirdParkingOrderEntity.setParkPeriodTime(requestDto.getParkingLength().intValue());
            thirdParkingOrderEntity.setTotalCost(requestDto.getAmount());
            thirdParkingOrderEntity.setPlateColor(requestDto.getPlateColor());
            thirdParkingOrderEntity.setPlateNumber(requestDto.getPlateNo());
            thirdParkingOrderEntity.setRecordNo(requestDto.getRecordNo());
            thirdParkingOrderEntity.setTenantId(requestDto.getTenantId());
            thirdParkingOrderEntity.setEnterTime(requestDto.getEnterTime());
            thirdParkingOrderEntity.setCostTime(requestDto.getCostTime());
            thirdParkingOrderEntity.setStatus(requestDto.getThirdOrderStatus());
            thirdParkingOrderEntity.setCreationTime(DateUtils.now());
            boolean insert = thirdParkingOrderCrudService.saveOrder(thirdParkingOrderEntity);
            if (!insert) {
                resultDto.failed();
                return resultDto;
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("保存任意停车平台订单失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

}
