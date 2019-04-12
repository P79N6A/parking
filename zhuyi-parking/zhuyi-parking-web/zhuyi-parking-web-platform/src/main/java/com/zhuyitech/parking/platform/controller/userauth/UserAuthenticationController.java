package com.zhuyitech.parking.platform.controller.userauth;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.tool.dto.request.IdentityCardVerifyRequestDto;
import com.zhuyitech.parking.tool.dto.result.identity.IdentityCardVerifyResultDto;
import com.zhuyitech.parking.tool.service.api.IdentityCardService;
import com.zhuyitech.parking.ucc.dto.request.realname.UserAuthApplyGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.realname.UserAuthApplyListGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.realname.UserAuthApplyQueryPagedResultRequestDto;
import com.zhuyitech.parking.ucc.dto.request.realname.UserAuthApproveRequestDto;
import com.zhuyitech.parking.ucc.dto.result.UserAuthApplyResultDto;
import com.zhuyitech.parking.ucc.service.api.UserAuthenticationApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户实名认证后台API
 *
 * @author walkman
 * @date 2018-01-10
 */
@Api(value = "用户实名认证后台API", description = "用户实名认证后台API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/auth")
public class UserAuthenticationController {

    @Autowired
    private UserAuthenticationApplyService userAuthenticationApplyService;

    @Autowired
    private IdentityCardService identityCardService;

    /**
     * 用户身份信息第三方认证
     */
    @ApiOperation(value = "用户身份信息第三方认证")
    @RequestMapping(value = "/checkAuthApply", name = "用户实名认证", method = RequestMethod.POST)
    public ObjectResultDto<IdentityCardVerifyResultDto> checkAuthApply(IdentityCardVerifyRequestDto requestDto) {
        return identityCardService.verifyIdCardSelect(requestDto);
    }

    /**
     * 实名认证申请审核
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "实名认证申请审核")
    @RequestMapping(value = "/authApprove", name = "用户实名认证申请审核", method = RequestMethod.POST)
    public ResultDto approveAuthApply(UserAuthApproveRequestDto requestDto) {
        return userAuthenticationApplyService.approveAuthApply(requestDto);
    }

    /**
     * 获取实名认证申请
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取实名认证申请", notes = "获取实名认证申请", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserAuthApplyResultDto.class)
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ObjectResultDto<UserAuthApplyResultDto> getUserAuthApply(UserAuthApplyGetRequestDto requestDto) {
        return userAuthenticationApplyService.getUserAuthApply(requestDto);
    }

    /**
     * 获取实名认证申请列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取实名认证申请列表", notes = "获取实名认证申请列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserAuthApplyResultDto.class)
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ListResultDto<UserAuthApplyResultDto> getAuthApplyList(UserAuthApplyListGetRequestDto requestDto) {
        return userAuthenticationApplyService.getAuthApplyList(requestDto);
    }

    /**
     * 分页获取实名认证申请列表
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "分页获取实名认证申请列表", notes = "分页获取实名认证申请列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserAuthApplyResultDto.class)
    @RequestMapping(value = "/listpage", method = RequestMethod.POST)
    public PagedResultDto<UserAuthApplyResultDto> getAuthApplyPagedList(UserAuthApplyQueryPagedResultRequestDto requestDto) {
        return userAuthenticationApplyService.getAuthApplyPagedList(requestDto);
    }

    /**
     * 实名认证审核意见下拉
     */
    @ApiOperation(value = "车辆审核驳回意见", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ComboboxItemDto.class)
    @RequestMapping(value = "/rejectReasons", method = RequestMethod.POST, name = "车辆审核驳回意见")
    public ListResultDto<ComboboxItemDto> getRejectReason() {
        return userAuthenticationApplyService.getRejectReason();
    }
}
