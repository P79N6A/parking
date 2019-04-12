package com.zhuyitech.parking.ucc.service.api;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.ucc.account.request.UserHasExistRequestDto;
import com.zhuyitech.parking.ucc.dto.request.UserTagUpdateRequestDto;
import com.zhuyitech.parking.ucc.dto.result.*;
import com.zhuyitech.parking.ucc.dto.result.user.*;
import com.zhuyitech.parking.ucc.user.dto.*;


/**
 * 用户服务
 *
 * @author walkman
 */
public interface UserService {

    /**
     * 新增用户
     *
     * @param requestDto
     * @return
     */
    ResultDto addUser(UserAddRequestDto requestDto);

    /**
     * 更新用户
     *
     * @param requestDto
     * @return
     */
    ResultDto updateUser(UserUpdateRequestDto requestDto);

    /**
     * 删除用户
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteUser(UserDeleteRequestDto requestDto);

    /**
     * 获取用户
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserResultDto> getUser(UserGetRequestDto requestDto);

    /**
     * 微信OpenID获取用户信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserResultDto> getUserByOpenId(UserGetByWxOpenIdRequestDto requestDto);

    /**
     * 获取用户列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<UserResultDto> getUserList(UserListGetRequestDto requestDto);

    /**
     * 模糊查询获取用户列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<UserResultDto> getUserListFuzzyQuery(UserListGetRequestDto requestDto);

    /**
     * 分页查询用户列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<UserResultDto> getUserPagedList(UserQueryPagedResultRequestDto requestDto);

    /**
     * 获取会员用户
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<CustomerUserResultDto> getCustomer(UserGetRequestDto requestDto);

    /**
     * 获取会员用户列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<CustomerUserResultDto> getCustomerList(UserListGetRequestDto requestDto);

    /**
     * 分页查询会员用户列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<CustomerUserResultDto> getCustomerPagedList(UserQueryPagedResultRequestDto requestDto);

    /**
     * 登录密码是否已设置
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserPasswordSetStatusResultDto> hasSetPassword(UserHasSetPasswordRequestDto requestDto);

    /**
     * 修改密码
     *
     * @param requestDto
     * @return
     */
    ResultDto modifyPassword(UserPasswordModifyRequestDto requestDto);

    /**
     * 用户是否已存在
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserHasExistResultDto> checkExist(UserHasExistRequestDto requestDto);

    /**
     * 用户是否已实名认证
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserHasCertifiedResultDto> hasCertified(UserHasCertifiedRequestDto requestDto);

    /**
     * 用户更新个人头像
     *
     * @param requestDto
     * @return
     */
    ResultDto updateUserPortrait(UserPortraitUpdateRequestDto requestDto);

    /**
     * 获取用户个人信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserProfileResultDto> getUserProfile(UserProfileGetRequestDto requestDto);

    /**
     * 获取用户钱包余额
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserPacketBalanceResultDto> getPacketBalance(UserPacketBalanceGetRequestDto requestDto);

    /**
     * 获取用户积分
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserPacketPointResultDto> getPacketPoint(UserPacketPointGetRequestDto requestDto);

    /**
     * 获取用户等级
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserLevelResultDto> getUserLevel(UserLevelGetRequestDto requestDto);

    /**
     * 获取用户资产信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserAssetInfoResultDto> getUserAsset(UserAssetGetRequestDto requestDto);

    /**
     * 更新用户资产信息
     *
     * @param requestDto
     * @return
     */
    ResultDto updateUserAsset(UserAssetUpdateRequestDto requestDto);

    /**
     * 重置登录密码
     *
     * @param requestDto
     * @return
     */
    ResultDto setPassword(UserSetPasswordRequestDto requestDto);

    /**
     * 设置支付密码
     *
     * @param requestDto
     * @return
     */
    ResultDto setTradePassword(UserTradePasswordSetRequestDto requestDto);

    /**
     * 校验支付密码
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserTradePasswordCheckResultDto> checkTradePassword(UserTradePasswordCheckRequestDto requestDto);

    /**
     * 校验支付码是否存在
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserTradePwdSetStatusResultDto> checkTradePasswordExist(UserTradePasswordExistRequestDto requestDto);

    /**
     * 是否绑定微信账号
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserWechatBindStatusResultDto> hasBindWechat(UserHasBindWechatRequestDto requestDto);

    /**
     * 手机注册用户绑定微信
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserWechatBindResultDto> bindWechat(UserWechatBindRequestDto requestDto);

    /**
     * 获取用户手机号
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<GetUserPhoneResultDto> getUserPhone(UserPhoneGetRequestDto requestDto);

    /**
     * 是否绑定支付宝账号
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserAlipayBindStatusResultDto> hasBindAlipay(UserHasBindAlipayRequestDto requestDto);

    /**
     * 手机注册用户绑定支付宝
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserAlipayBindResultDto> bindAlipay(UserAlipayBindRequestDto requestDto);

    /**
     * aliUserId获取用户信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserResultDto> getUserByAliUserId(UserGetByAliUserIdRequestDto requestDto);

    /**
     * 修改昵称
     *
     * @param requestDto
     * @return
     */
    ResultDto modifyNickname(UserNicknameModifyRequestDto requestDto);

    /**
     * 修改性别
     *
     * @param requestDto
     * @return
     */
    ResultDto modifyGender(UserGenderModifyRequestDto requestDto);

    /**
     * 修改生日
     *
     * @param requestDto
     * @return
     */
    ResultDto modifyBirthday(UserBirthdayModifyRequestDto requestDto);

    /**
     * 修改个人信息
     *
     * @param requestDto
     * @return
     */
    ResultDto modifyProfile(UserProfileModifyRequestDto requestDto);

    /**
     * userId获取用户信息
     *
     * @param requestDto UserGetByUserNameRequestDto
     * @return UserResultDto
     */
    ObjectResultDto<UserResultDto> getUserByUserName(UserGetByUserNameRequestDto requestDto);

    /**
     * 更新用户标签
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto updateUserTag(UserTagUpdateRequestDto requestDto);

    /**
     * 获取用户标签
     *
     * @param requestDto requestDto
     * @return
     */
    ListResultDto<UserTagResultDto> getUserTagList(UserTagListGetRequestDto requestDto);

    /**
     * 删除用户标签
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto deleteUserTag(UserTagDeleteRequestDto requestDto);

    /**
     * 分页获取logo
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<UserPortraitViewResultDto> getUserPortraitViewList(UserPortraitQueryPagedResultRequestDto requestDto);

    /**
     * 修改logo的url
     *
     * @param requestDto
     * @return
     */
    ResultDto updateUserPortraitView(UserPortraitUpdateRequestDto requestDto);

    /**
     * 用户是否已存在公众号
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserHasExistResultDto> checkUserExist(UserHasExistRequestDto requestDto);

    /**
     * 微信unionID获取用户信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserResultDto> getUserByUnionId(UserGetByWxUnionIdRequestDto requestDto);

    /**
     * 通过用户Id获取用户信息
     *
     * @param requestDto UserInfoGetRequestDto
     * @return UserInfoResultDto
     */
    ObjectResultDto<UserInfoResultDto> getUserInfoByUserId(UserInfoGetRequestDto requestDto);

    /**
     * 更新用户信息
     *
     * @param requestDto UserInfoUpDateRequestDto
     * @return ResultDto
     */
    ResultDto updateUserInfo(UserInfoUpdateRequestDto requestDto);
}
