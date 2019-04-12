package com.zhuyitech.parking.ucc.service.impl;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.ucc.domain.UserPayOrder;
import com.zhuyitech.parking.ucc.dto.request.trade.CustomerPayOrderGetRequestDto;
import com.zhuyitech.parking.ucc.dto.result.UserPayOrderResultDto;
import com.zhuyitech.parking.ucc.service.UserPayOrderCrudService;
import com.zhuyitech.parking.ucc.service.api.UserPayOrderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 用户支付服务
 *
 * @author zwq
 */
@Service("userPayOrderService")
@Slf4j
public class UserPayOrderServiceImpl implements UserPayOrderService {

    @Autowired
    private UserPayOrderCrudService userPayOrderCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 获取用户支付订单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<UserPayOrderResultDto> getCustomerPayOrder(CustomerPayOrderGetRequestDto requestDto) {
        ObjectResultDto<UserPayOrderResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            UserPayOrder userPayOrder = this.userPayOrderCrudService.findByOrderNo(requestDto.getOrderNo());
            if (userPayOrder != null) {
                UserPayOrderResultDto userPayOrderResultDto = modelMapper.map(userPayOrder, UserPayOrderResultDto.class);
                objectResultDto.setData(userPayOrderResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户支付记录失败" + e.getMessage());
            return objectResultDto.failed();
        }
        return objectResultDto;
    }

}
