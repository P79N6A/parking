package com.zhuyitech.parking.platform.controller.financial;

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
import com.zhuyitech.parking.ucc.service.api.UserTransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 财务记录Api
 *
 * @Date: 2018/3/5
 * @author: yuzhicheng
 */
@Api(value = "财务记录Api", description = "财务记录Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/financial")
public class FinancialController {

    @Autowired
    private UserTransactionService userTransactionService;

    /**
     * 分页获取用户充值记录
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页获取用户充值记录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserRechargeRecordResultDto.class)
    @RequestMapping(value = "/UserRechargePage", name = "分页获取用户充值记录", method = RequestMethod.POST)
    public PagedResultDto<UserRechargeRecordResultDto> getUserRechargePagedList(UserRechargeRecordQueryPageRequestDto requestDto) {
        return userTransactionService.getUserRechargePagedList(requestDto);
    }

    /**
     * 获取用户充值记录列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户充值记录列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserRechargeRecordResultDto.class)
    @RequestMapping(value = "/UserRechargeList", name = "获取用户充值记录列表", method = RequestMethod.POST)
    public ListResultDto<UserRechargeRecordResultDto> getUserRechargeList(UserRechargeRecordListGetRequestDto requestDto) {
        return userTransactionService.getUserRechargeList(requestDto);
    }

    /**
     * 获取用户充值记录
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户充值记录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserRechargeRecordResultDto.class)
    @RequestMapping(value = "/UserRecharge", name = "获取用户充值记录", method = RequestMethod.POST)
    public ObjectResultDto<UserRechargeRecordResultDto> getUserRecharge(UserRechargeRecordGetRequestDto requestDto) {
        return userTransactionService.getUserRecharge(requestDto);
    }

    /**
     * 获取用户支付记录
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户支付记录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserPayOrderResultDto.class)
    @RequestMapping(value = "/UserPay", name = "获取用户支付记录", method = RequestMethod.POST)
    public ObjectResultDto<UserPayOrderResultDto> getUserPay(UserPayOrderGetRequestDto requestDto) {
        return userTransactionService.getUserPay(requestDto);
    }

    /**
     * 获取用户支付记录列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户支付记录列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserPayOrderResultDto.class)
    @RequestMapping(value = "/UserPayList", name = "获取用户支付记录列表", method = RequestMethod.POST)
    public ListResultDto<UserPayOrderResultDto> getUserPayList(UserPayOrderListGetRequestDto requestDto) {
        return userTransactionService.getUserPayList(requestDto);
    }

    /**
     * 分页获取用户支付记录列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页获取用户支付记录列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserPayOrderResultDto.class)
    @RequestMapping(value = "/UserPayPageList", name = "分页获取用户支付记录列表", method = RequestMethod.POST)
    public PagedResultDto<UserPayOrderResultDto> getUserPayPageList(UserPayOrderQueryPageGetRequestDto requestDto) {
        return userTransactionService.getUserPayPagedList(requestDto);
    }

    /**
     * 获取充值记录状态选择项
     *
     * @return
     */
    @ApiOperation(value = "获取充值记录状态选择项", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ListResultDto.class)
    @RequestMapping(value = "/getRechargeStatus", name = "获取充值记录状态选择项", method = RequestMethod.POST)
    public ListResultDto<ComboboxItemDto> getRechargeStatusComboboxList() {
        return userTransactionService.getRechargeStatusComboboxList();
    }
}
