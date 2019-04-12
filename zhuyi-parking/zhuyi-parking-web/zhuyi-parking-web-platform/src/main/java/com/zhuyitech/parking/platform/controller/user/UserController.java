package com.zhuyitech.parking.platform.controller.user;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.tool.dto.request.notification.NotificationUnreadCountGetRequestDto;
import com.zhuyitech.parking.tool.dto.result.notification.NotificationUnreadCountResultDto;
import com.zhuyitech.parking.tool.service.api.NotificationService;
import com.zhuyitech.parking.ucc.car.UserCarInfoService;
import com.zhuyitech.parking.ucc.car.request.UserCarCountRequestDto;
import com.zhuyitech.parking.ucc.car.result.UserCarCountResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserPacketBalanceResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserPacketPointResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.*;
import com.zhuyitech.parking.ucc.service.api.UserService;
import com.zhuyitech.parking.ucc.user.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author walkman
 */
@Api(value = "用户Api", description = "用户Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/user")
//@Authorization
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserCarInfoService userCarInfoService;

    @Autowired
    private NotificationService notificationService;

    /**
     * 用户是否已实名认证
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "用户是否已实名认证", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserHasCertifiedResultDto.class)
    @RequestMapping(value = "/checkCertified", name = "用户是否已实名认证", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<UserHasCertifiedResultDto> checkCertified(UserHasCertifiedRequestDto requestDto) {
        return userService.hasCertified(requestDto);
    }

    /**
     * 用户修改个人头像
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "用户修改个人头像", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/updatePortrait", name = "用户修改个人头像", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ResultDto userUpdatePortrait(UserPortraitUpdateRequestDto requestDto) {
        return userService.updateUserPortrait(requestDto);
    }

    /**
     * 获取用户个人信息
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户个人信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserProfileResultDto.class)
    @RequestMapping(value = "/profile", name = "获取用户个人信息", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<UserProfileResultDto> getUserProfile(UserProfileGetRequestDto requestDto) {

        ObjectResultDto<UserProfileResultDto>
                userProfileObjectResultDto = userService.getUserProfile(requestDto);
        if (userProfileObjectResultDto.isSuccess() && userProfileObjectResultDto.getData() != null) {
            UserProfileResultDto userProfileResultDto = userProfileObjectResultDto.getData();
            //未读消息数量
            NotificationUnreadCountGetRequestDto notificationUnreadCountGetRequestDto = new NotificationUnreadCountGetRequestDto();
            notificationUnreadCountGetRequestDto.setSessionIdentity(requestDto.getSessionIdentity());
            ObjectResultDto<NotificationUnreadCountResultDto> unreadCountResultDtoObjectResultDto =
                    notificationService.unreadCount(notificationUnreadCountGetRequestDto);
            if (unreadCountResultDtoObjectResultDto.isSuccess() && unreadCountResultDtoObjectResultDto.getData() != null) {
                userProfileResultDto.setUnreadCount(unreadCountResultDtoObjectResultDto.getData().getUnreadCount());
            } else {
                userProfileResultDto.setUnreadCount(String.valueOf(0));
            }
            //已绑定车牌数量
            UserCarCountRequestDto userCarCountRequestDto = new UserCarCountRequestDto();
            userCarCountRequestDto.setSessionIdentity(requestDto.getSessionIdentity());
            ObjectResultDto<UserCarCountResultDto> userCarCountObjectResultDto = userCarInfoService.userCarCount(userCarCountRequestDto);
            if (userCarCountObjectResultDto.isSuccess() && userCarCountObjectResultDto.getData() != null) {
                userProfileResultDto.setCarCount(userCarCountObjectResultDto.getData().getCount());
            } else {
                userProfileResultDto.setUnreadCount(String.valueOf(0));
            }
        }
        return userProfileObjectResultDto;
    }

    /**
     * 用户修改个人信息
     *
     * @param requestDto requestDto
     * @return
     */
    @ApiOperation(value = "用户修改个人信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/modifyProfile", name = "用户修改个人信息", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ResultDto modifyProfile(UserProfileModifyRequestDto requestDto) {
        return userService.modifyProfile(requestDto);
    }

    /**
     * 获取用户钱包余额
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户钱包余额", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserPacketBalanceResultDto.class)
    @RequestMapping(value = "/balance", name = "获取用户钱包余额", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<UserPacketBalanceResultDto> getPacketBalance(UserPacketBalanceGetRequestDto requestDto) {
        return userService.getPacketBalance(requestDto);
    }

    /**
     * 获取用户积分
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户积分", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserPacketPointResultDto.class)
    @RequestMapping(value = "/point", name = "获取用户积分", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<UserPacketPointResultDto> getPacketPoint(UserPacketPointGetRequestDto requestDto) {
        return userService.getPacketPoint(requestDto);
    }

    /**
     * 获取用户等级
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户等级", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserLevelResultDto.class)
    @RequestMapping(value = "/level", name = "获取用户等级", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ObjectResultDto<UserLevelResultDto> getUserLevel(UserLevelGetRequestDto requestDto) {
        return userService.getUserLevel(requestDto);
    }

    /**
     * 登录密码是否已设置
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "登录密码是否已设置", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserPasswordSetStatusResultDto.class)
    @RequestMapping(value = "/passwordset", name = "登录密码是否已设置", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<UserPasswordSetStatusResultDto> hasSetPassword(UserHasSetPasswordRequestDto requestDto) {
        return userService.hasSetPassword(requestDto);
    }

    /**
     * 修改密码
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "修改密码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/modifyPassword", name = "修改密码", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ResultDto modifyPassword(UserPasswordModifyRequestDto requestDto) {
        return userService.modifyPassword(requestDto);
    }

    /**
     * 用户设置登录密码
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "用户设置登录密码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/setPassword", name = "用户设置登录密码", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public ResultDto setPassword(UserSetPasswordRequestDto requestDto) {
        return userService.setPassword(requestDto);
    }

    /**
     * 校验支付密码是否存在
     */
    @ApiOperation(value = "校验支付密码是否存在", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserTradePwdSetStatusResultDto.class)
    @RequestMapping(value = "/checkTradePasswordExist", name = "校验支付密码是否存在", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<UserTradePwdSetStatusResultDto> checkTradePassword(UserTradePasswordExistRequestDto requestDto) {
        return userService.checkTradePasswordExist(requestDto);
    }

    /**
     * 设置支付密码
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "设置支付密码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/setTradePassword", name = "设置支付密码", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ResultDto setTradePassword(UserTradePasswordSetRequestDto requestDto) {
        return userService.setTradePassword(requestDto);
    }

    /**
     * 校验支付密码
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "校验支付密码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/checkTradePassword", name = "校验支付密码", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ResultDto checkTradePassword(UserTradePasswordCheckRequestDto requestDto) {
        return userService.checkTradePassword(requestDto);
    }

    /**
     * 用户是否绑定微信
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "用户是否绑定微信", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserWechatBindStatusResultDto.class)
    @RequestMapping(value = "/wechatbind", name = "用户是否绑定微信", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<UserWechatBindStatusResultDto> isBlindWechat(UserHasBindWechatRequestDto requestDto) {
        return userService.hasBindWechat(requestDto);
    }

    /**
     * 绑定微信账号
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "绑定微信账号", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserWechatBindResultDto.class)
    @RequestMapping(value = "/bindwechat", name = "绑定微信账号", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<UserWechatBindResultDto> blindWechat(UserWechatBindRequestDto requestDto) {
        ObjectResultDto<UserWechatBindResultDto> objectResultDto = new ObjectResultDto<>();
        objectResultDto.failed();
        try {
            objectResultDto = userService.bindWechat(requestDto);
        } catch (Exception e) {
            log.error("绑定微信账号失败:{}", e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 获取用户手机号
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "获取用户手机号", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = GetUserPhoneResultDto.class)
    @RequestMapping(value = "/getUserPhone", name = "获取用户手机号", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<GetUserPhoneResultDto> getUserPhone(UserPhoneGetRequestDto requestDto) {
        return userService.getUserPhone(requestDto);
    }

    /**
     * 用户是否绑定支付宝
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "用户是否绑定支付宝", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserAlipayBindStatusResultDto.class)
    @RequestMapping(value = "/alipaybind", name = "用户是否绑定支付宝", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<UserAlipayBindStatusResultDto> isBlindAlipay(UserHasBindAlipayRequestDto requestDto) {
        return userService.hasBindAlipay(requestDto);
    }

    /**
     * 绑定支付宝账号
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "绑定支付宝账号", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = UserAlipayBindResultDto.class)
    @RequestMapping(value = "/bindAlipay", name = "绑定支付宝账号", method = RequestMethod.POST)
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    @ApiVersion(2)
    public ObjectResultDto<UserAlipayBindResultDto> bindAlipay(UserAlipayBindRequestDto requestDto) {
        return userService.bindAlipay(requestDto);
    }

    /**
     * 用户修改昵称
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "用户修改昵称", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/modifyNickname", name = "用户修改昵称", method = RequestMethod.POST)
    public ResultDto modifyNickname(UserNicknameModifyRequestDto requestDto) {
        return userService.modifyNickname(requestDto);
    }

    /**
     * 用户修改性别
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "用户修改性别", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/modifyGender", name = "用户修改性别", method = RequestMethod.POST)
    public ResultDto modifyGender(UserGenderModifyRequestDto requestDto) {
        return userService.modifyGender(requestDto);
    }

    /**
     * 用户修改生日
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "用户修改生日", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/modifyBirthday", name = "用户修改生日", method = RequestMethod.POST)
    public ResultDto modifyBirthday(UserBirthdayModifyRequestDto requestDto) {
        return userService.modifyBirthday(requestDto);
    }
}
