package com.zhuyitech.parking.ucc.service.api;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.parking.ucc.dto.request.pay.UserPayOrderGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.pay.UserPayOrderListGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.pay.UserPayOrderQueryPageGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.recharge.UserRechargeRecordGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.recharge.UserRechargeRecordListGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.recharge.UserRechargeRecordQueryPageRequestDto;
import com.zhuyitech.parking.ucc.dto.result.UserPayOrderResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserRechargeRecordResultDto;

/**
 * 用户支付充值记录服务
 *
 * @Date: 2018/2/27
 * @author: yuzhicheng
 */
public interface UserTransactionService {

    /**
     * 分页获取充值记录
     *
     * @param requestDto 分页获取充值记录请求参数
     * @return PagedResultDto
     */
    PagedResultDto<UserRechargeRecordResultDto> getUserRechargePagedList(UserRechargeRecordQueryPageRequestDto requestDto);

    /**
     * 获取充值记录列表
     *
     * @param requestDto 获取充值记录列表请求参数
     * @return ListResultDto
     */
    ListResultDto<UserRechargeRecordResultDto> getUserRechargeList(UserRechargeRecordListGetRequestDto requestDto);

    /**
     * 获取充值记录
     *
     * @param requestDto 获取充值记录请求参数
     * @return ObjectResultDto
     */
    ObjectResultDto<UserRechargeRecordResultDto> getUserRecharge(UserRechargeRecordGetRequestDto requestDto);

    /**
     * 获取支付记录
     *
     * @param requestDto 获取支付记录请求参数
     * @return ObjectResultDto
     */
    ObjectResultDto<UserPayOrderResultDto> getUserPay(UserPayOrderGetRequestDto requestDto);

    /**
     * 获取支付记录列表
     *
     * @param requestDto 获取支付记录列表请求参数
     * @return ListResultDto
     */
    ListResultDto<UserPayOrderResultDto> getUserPayList(UserPayOrderListGetRequestDto requestDto);

    /**
     * 分页获取支付记录列表
     *
     * @param requestDto 分页获取支付记录列表请求参数
     * @return PagedResultDto
     */
    PagedResultDto<UserPayOrderResultDto> getUserPayPagedList(UserPayOrderQueryPageGetRequestDto requestDto);

    /**
     * 获取充值记录状态选择项
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getRechargeStatusComboboxList();


}
