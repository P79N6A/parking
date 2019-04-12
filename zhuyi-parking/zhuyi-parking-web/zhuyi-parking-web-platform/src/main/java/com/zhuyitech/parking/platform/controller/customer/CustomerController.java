package com.zhuyitech.parking.platform.controller.customer;


import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.CustomerUserResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.UserResultDto;
import com.zhuyitech.parking.ucc.service.api.UserService;
import com.zhuyitech.parking.ucc.user.dto.UserGetRequestDto;
import com.zhuyitech.parking.ucc.user.dto.UserListGetRequestDto;
import com.zhuyitech.parking.ucc.user.dto.UserQueryPagedResultRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员控制器
 *
 * @author walkman
 */
@Api(value = "会员Api", description = "会员Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/customer")
public class CustomerController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/listpage", name = "获取用户列表", method = RequestMethod.POST)
    public PagedResultDto<UserResultDto> getUserPagedList(UserQueryPagedResultRequestDto requestDto) {
        return userService.getUserPagedList(requestDto);
    }

    /**
     * 获取会员用户
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取会员用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = CustomerUserResultDto.class)
    @RequestMapping(value = "/getcustomer", name = "新增用户", method = RequestMethod.POST)
    public ObjectResultDto<CustomerUserResultDto> getCustomer(UserGetRequestDto requestDto) {
        return userService.getCustomer(requestDto);
    }

    /**
     * 获取会员用户列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = CustomerUserResultDto.class)
    @RequestMapping(value = "/listcustomer", name = "获取用户列表", method = RequestMethod.POST)
    public ListResultDto<CustomerUserResultDto> getCustomerList(UserListGetRequestDto requestDto) {
        return userService.getCustomerList(requestDto);
    }

    /**
     * 分页查询会员用户列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页查询会员用户列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = CustomerUserResultDto.class)
    @RequestMapping(value = "/listpagecustomer", name = "分页查询会员用户列表", method = RequestMethod.POST)
    PagedResultDto<CustomerUserResultDto> getCustomerPagedList(UserQueryPagedResultRequestDto requestDto) {
        return userService.getCustomerPagedList(requestDto);
    }
}
