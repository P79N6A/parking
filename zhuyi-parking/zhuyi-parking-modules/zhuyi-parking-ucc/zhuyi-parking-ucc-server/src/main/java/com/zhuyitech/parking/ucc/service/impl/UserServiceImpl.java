package com.zhuyitech.parking.ucc.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.shiro.service.SaltGenerator;
import com.scapegoat.infrastructure.shiro.service.ShiroCryptoService;
import com.zhuyitech.parking.ucc.account.request.UserHasExistRequestDto;
import com.zhuyitech.parking.ucc.domain.User;
import com.zhuyitech.parking.ucc.domain.UserAsset;
import com.zhuyitech.parking.ucc.domain.UserInfo;
import com.zhuyitech.parking.ucc.domain.UserPasswordLog;
import com.zhuyitech.parking.ucc.dto.request.UserTagUpdateRequestDto;
import com.zhuyitech.parking.ucc.dto.result.UserAssetInfoResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserPacketBalanceResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserPacketPointResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserTagResultDto;
import com.zhuyitech.parking.ucc.dto.result.user.*;
import com.zhuyitech.parking.ucc.enums.*;
import com.zhuyitech.parking.ucc.service.UserAssetCrudService;
import com.zhuyitech.parking.ucc.service.UserCrudService;
import com.zhuyitech.parking.ucc.service.UserInfoCrudService;
import com.zhuyitech.parking.ucc.service.UserPasswordLogCrudService;
import com.zhuyitech.parking.ucc.service.api.UserService;
import com.zhuyitech.parking.ucc.user.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author AkeemSuper
 */
@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private UserInfoCrudService userInfoCrudService;

    @Autowired
    private UserAssetCrudService userAssetCrudService;

    @Autowired
    private UserPasswordLogCrudService userPasswordLogCrudService;

    @Autowired
    private SaltGenerator saltGenerator;

    @Autowired
    private ShiroCryptoService shiroCryptoService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 新增用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto addUser(UserAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (StringUtils.isEmpty(requestDto.getUsername())) {
                return resultDto.makeResult(UCenterResultEnum.USER_NAME_EMPTY.getValue(),
                        UCenterResultEnum.USER_NAME_EMPTY.getComment());
            }
            if (StringUtils.isEmpty(requestDto.getPassword())) {
                return resultDto.makeResult(UCenterResultEnum.USER_PASSWORD_EMPTY.getValue(),
                        UCenterResultEnum.USER_PASSWORD_EMPTY.getComment());
            }
            User upUserExist = userCrudService.findByUsername(requestDto.getUsername());
            if (upUserExist != null) {
                return resultDto.makeResult(UCenterResultEnum.USER_NAME_EXISTS.getValue(),
                        UCenterResultEnum.USER_NAME_EXISTS.getComment());
            }
            if (!StringUtils.isEmpty(requestDto.getPhoneNumber())) {
                upUserExist = userCrudService.findByPhoneNumber(requestDto.getPhoneNumber());
                if (upUserExist != null) {
                    return resultDto.makeResult(UCenterResultEnum.USER_PHONE_EXISTS.getValue(),
                            UCenterResultEnum.USER_PHONE_EXISTS.getComment());
                }
            }
            if (!StringUtils.isEmpty(requestDto.getEmailAddress())) {
                upUserExist = userCrudService.findByEmailAddress(requestDto.getEmailAddress());
                if (upUserExist != null) {
                    return resultDto.makeResult(UCenterResultEnum.USER_EMAIL_EXISTS.getValue(),
                            UCenterResultEnum.USER_EMAIL_EXISTS.getComment());
                }
            }
            User user = modelMapper.map(requestDto, User.class);
            //密码处理
            String salt = saltGenerator.generate();
            String encryptPassword = shiroCryptoService.password(requestDto.getPassword(), user.getUsername() + salt);
            user.setSalt(salt);
            user.setPassword(encryptPassword);
            String uuid = StringUtils.getUUID();
            user.setUuid(uuid);
            user.setAlias(uuid);
            user.setAccessAttemptCount(0);
            user.setAdministrator(Boolean.FALSE);
            user.setUserType(UserTypeEnum.MANAGER.getValue());
            user.setDefaultUser(Boolean.FALSE);
            if (!StringUtils.isEmpty(requestDto.getPhoneNumber())) {
                user.setPhoneNumberConfirmed(Boolean.TRUE);
            }
            if (!StringUtils.isEmpty(requestDto.getEmailAddress())) {
                user.setEmailAddressConfirmed(Boolean.TRUE);
            }
            user.setStatus(String.valueOf(UserStatusEnum.ENABLED.getValue()));
            //保存用户
            boolean retVal = userCrudService.insert(user);
            if (!retVal) {
                return resultDto.failed();
            }
            //静态角色赋值
            resultDto.success();
        } catch (Exception e) {
            log.error("新建用户失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto deleteUser(UserDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {

            //删除用户
            userCrudService.deleteById(requestDto.getId());

            //删除用户角色

            return resultDto.success();
        } catch (Exception e) {
            log.error("删除用户" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取用户
     */
    @Override
    public ObjectResultDto<UserResultDto> getUser(UserGetRequestDto requestDto) {
        ObjectResultDto<UserResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<User> entityWrapper = new EntityWrapper<>();
            if (requestDto.getId() != null) {
                entityWrapper.eq("id", requestDto.getId());
            }
            if (!StringUtils.isEmpty(requestDto.getUsername())) {
                entityWrapper.and().eq("username", requestDto.getUsername());
            }
            if (!StringUtils.isEmpty(requestDto.getPhoneNumber())) {
                entityWrapper.and().eq("phoneNumber", requestDto.getPhoneNumber());
            }
            if (!StringUtils.isEmpty(requestDto.getEmailAddress())) {
                entityWrapper.and().eq("emailAddress", requestDto.getEmailAddress());
            }
            entityWrapper.and().ne("userType", UserTypeEnum.TEMPORARY_CUSTOMER.getValue());
            List<User> systemUserList = userCrudService.selectList(entityWrapper);
            if (systemUserList.size() > 0) {
                UserResultDto userResultDto = modelMapper.map(systemUserList.get(0), UserResultDto.class);
                objectResultDto.setData(userResultDto);
            }
            return objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 微信OpenID获取用户信息
     */
    @Override
    public ObjectResultDto<UserResultDto> getUserByOpenId(UserGetByWxOpenIdRequestDto requestDto) {
        ObjectResultDto<UserResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            UserInfo userInfo = userInfoCrudService.findByOpenId(requestDto.getOpenId());
            if (userInfo == null) {
                return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            User user = userCrudService.selectById(userInfo.getUserId());
            if (user == null) {
                return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            UserResultDto userResultDto = modelMapper.map(user, UserResultDto.class);
            objectResultDto.setData(userResultDto);
            return objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 更新用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateUser(UserUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {

            User existUser = userCrudService.selectById(requestDto.getId());
            if (existUser == null) {
                return resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            existUser.setEmailAddress(requestDto.getEmailAddress());
            if (requestDto.getStatus() != null) {
                existUser.setStatus(requestDto.getStatus().toString());
            }
            existUser.setPhoneNumber(requestDto.getPhoneNumber());
            existUser.setNickname(requestDto.getNickname());
            existUser.setPortrait(requestDto.getPortrait());
            existUser.setAccessAttemptCount(requestDto.getAccessAttemptCount());
            existUser.setLastLoginTime(requestDto.getLastLoginTime());
            EntityWrapper<User> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            userCrudService.update(existUser, entityWrapper);
            return resultDto.success();
        } catch (Exception e) {
            log.error("更新用户" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取用户列表
     */
    @Override
    public ListResultDto<UserResultDto> getUserList(UserListGetRequestDto requestDto) {
        ListResultDto<UserResultDto> listResultDto = new ListResultDto<>();
        try {

            EntityWrapper<User> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getUsername())) {
                entityWrapper.eq("username", requestDto.getUsername());
            }
            if (!StringUtils.isEmpty(requestDto.getNickname())) {
                entityWrapper.eq("nickname", requestDto.getNickname());
            }
            if (!StringUtils.isEmpty(requestDto.getEmailAddress())) {
                entityWrapper.eq("emailAddress", requestDto.getEmailAddress());
            }
            if (!StringUtils.isEmpty(requestDto.getPhoneNumber())) {
                entityWrapper.eq("phoneNumber", requestDto.getPhoneNumber());
            }
            if (requestDto.getStatus() != null) {
                entityWrapper.eq("status", requestDto.getStatus());
            }
            if (requestDto.getCreationTimeStart() != null) {
                entityWrapper.gt("creationTime", requestDto.getCreationTimeStart());
            }
            if (requestDto.getCreationTimeEnd() != null) {
                entityWrapper.lt("creationTime", requestDto.getCreationTimeEnd());
            }
            if (requestDto.getLastLoginTimeStart() != null) {
                entityWrapper.gt("lastLoginTime", requestDto.getLastLoginTimeStart());
            }
            if (requestDto.getLastLoginTimeEnd() != null) {
                entityWrapper.lt("lastLoginTime", requestDto.getLastLoginTimeEnd());
            }
            List<User> userList = userCrudService.selectList(entityWrapper);
            List<UserResultDto> userDtoList = modelMapper.map(userList, new TypeToken<List<UserResultDto>>() {
            }.getType());
            listResultDto.setItems(userDtoList);
            return listResultDto.success();
        } catch (Exception e) {
            log.error("获取用户列表" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 模糊查询获取用户列表
     */
    @Override
    public ListResultDto<UserResultDto> getUserListFuzzyQuery(UserListGetRequestDto requestDto) {
        ListResultDto<UserResultDto> listResultDto = new ListResultDto<>();
        try {

            EntityWrapper<User> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getUsername())) {
                entityWrapper.like("username", requestDto.getUsername());
            }
            if (!StringUtils.isEmpty(requestDto.getNickname())) {
                entityWrapper.like("nickname", requestDto.getNickname());
            }
            if (!StringUtils.isEmpty(requestDto.getEmailAddress())) {
                entityWrapper.like("emailAddress", requestDto.getEmailAddress());
            }
            if (!StringUtils.isEmpty(requestDto.getPhoneNumber())) {
                entityWrapper.like("phoneNumber", requestDto.getPhoneNumber());
            }
            if (requestDto.getStatus() != null) {
                entityWrapper.eq("status", requestDto.getStatus());
            }
            if (requestDto.getCreationTimeStart() != null) {
                entityWrapper.gt("creationTime", requestDto.getCreationTimeStart());
            }
            if (requestDto.getCreationTimeEnd() != null) {
                entityWrapper.lt("creationTime", requestDto.getCreationTimeEnd());
            }
            if (requestDto.getLastLoginTimeStart() != null) {
                entityWrapper.gt("lastLoginTime", requestDto.getLastLoginTimeStart());
            }
            if (requestDto.getLastLoginTimeEnd() != null) {
                entityWrapper.lt("lastLoginTime", requestDto.getLastLoginTimeEnd());
            }
            List<User> userList = userCrudService.selectList(entityWrapper);
            List<UserResultDto> userDtoList = modelMapper.map(userList, new TypeToken<List<UserResultDto>>() {
            }.getType());
            listResultDto.setItems(userDtoList);
            return listResultDto.success();
        } catch (Exception e) {
            log.error("获取用户列表" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 分页查询用户列表
     */
    @Override
    public PagedResultDto<UserResultDto> getUserPagedList(UserQueryPagedResultRequestDto requestDto) {
        PagedResultDto<UserResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<User> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getUsername())) {
                entityWrapper.eq("username", requestDto.getUsername());
            }
            if (!StringUtils.isEmpty(requestDto.getNickname())) {
                entityWrapper.eq("nickname", requestDto.getNickname());
            }
            if (!StringUtils.isEmpty(requestDto.getEmailAddress())) {
                entityWrapper.eq("emailAddress", requestDto.getEmailAddress());
            }
            if (!StringUtils.isEmpty(requestDto.getPhoneNumber())) {
                entityWrapper.eq("phoneNumber", requestDto.getPhoneNumber());
            }
            if (requestDto.getStatus() != null) {
                entityWrapper.eq("status", requestDto.getStatus());
            }
            if (requestDto.getCreationTimeStart() != null) {
                entityWrapper.gt("creationTime", requestDto.getCreationTimeStart());
            }
            if (requestDto.getCreationTimeEnd() != null) {
                entityWrapper.lt("creationTime", requestDto.getCreationTimeEnd());
            }
            if (requestDto.getLastLoginTimeStart() != null) {
                entityWrapper.gt("lastLoginTime", requestDto.getLastLoginTimeStart());
            }
            if (requestDto.getLastLoginTimeEnd() != null) {
                entityWrapper.lt("lastLoginTime", requestDto.getLastLoginTimeEnd());
            }
            Page<User> userPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<User> userPageList = userCrudService.selectPage(userPage, entityWrapper);
            if (userPageList != null) {
                List<UserResultDto> userDtoList = modelMapper.map(userPageList.getRecords(), new TypeToken<List<UserResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(userPageList.getCurrent());
                pagedResultDto.setPageSize(userPageList.getSize());
                pagedResultDto.setTotalCount(userPageList.getTotal());
                pagedResultDto.setItems(userDtoList);
            }
            return pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页查询用户列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取会员用户
     */
    @Override
    public ObjectResultDto<CustomerUserResultDto> getCustomer(UserGetRequestDto requestDto) {
        ObjectResultDto<CustomerUserResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            User user = userCrudService.selectByIdWithUserInfo(requestDto.getId());
            if (user != null) {
                CustomerUserResultDto userResultDto = modelMapper.map(user, CustomerUserResultDto.class);
                objectResultDto.setData(userResultDto);
            }
            return objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取会员用户列表
     */
    @Override
    public ListResultDto<CustomerUserResultDto> getCustomerList(UserListGetRequestDto requestDto) {
        ListResultDto<CustomerUserResultDto> listResultDto = new ListResultDto<>();
        try {

            EntityWrapper<User> entityWrapper = new EntityWrapper<>();

            entityWrapper.eq("t.userType", UserTypeEnum.CUSTOMER.getValue());

            if (!StringUtils.isEmpty(requestDto.getUsername())) {
                entityWrapper.eq("t.username", requestDto.getUsername());
            }
            if (!StringUtils.isEmpty(requestDto.getNickname())) {
                entityWrapper.eq("t.nickname", requestDto.getNickname());
            }
            if (!StringUtils.isEmpty(requestDto.getEmailAddress())) {
                entityWrapper.eq("t.emailAddress", requestDto.getEmailAddress());
            }
            if (!StringUtils.isEmpty(requestDto.getPhoneNumber())) {
                entityWrapper.eq("t.phoneNumber", requestDto.getPhoneNumber());
            }
            if (requestDto.getStatus() != null) {
                entityWrapper.eq("t.status", requestDto.getStatus());
            }
            if (requestDto.getCreationTimeStart() != null) {
                entityWrapper.gt("t.creationTime", requestDto.getCreationTimeStart());
            }
            if (requestDto.getCreationTimeEnd() != null) {
                entityWrapper.lt("t.creationTime", requestDto.getCreationTimeEnd());
            }
            if (requestDto.getLastLoginTimeStart() != null) {
                entityWrapper.gt("t.lastLoginTime", requestDto.getLastLoginTimeStart());
            }
            if (requestDto.getLastLoginTimeEnd() != null) {
                entityWrapper.lt("t.lastLoginTime", requestDto.getLastLoginTimeEnd());
            }
            List<User> userList = userCrudService.selectListWithUserInfo(entityWrapper);
            if (!userList.isEmpty()) {
                List<CustomerUserResultDto> userDtoList = modelMapper.map(userList, new TypeToken<List<CustomerUserResultDto>>() {
                }.getType());
                listResultDto.setItems(userDtoList);
            }
            return listResultDto.success();
        } catch (Exception e) {
            log.error("获取用户列表" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 分页查询会员用户列表
     */
    @Override
    public PagedResultDto<CustomerUserResultDto> getCustomerPagedList(UserQueryPagedResultRequestDto requestDto) {
        PagedResultDto<CustomerUserResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<User> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("t.userType", UserTypeEnum.CUSTOMER.getValue());
            if (!StringUtils.isEmpty(requestDto.getUsername())) {
                entityWrapper.eq("t.username", requestDto.getUsername());
            }
            if (!StringUtils.isEmpty(requestDto.getNickname())) {
                entityWrapper.eq("t.nickname", requestDto.getNickname());
            }
            if (!StringUtils.isEmpty(requestDto.getEmailAddress())) {
                entityWrapper.eq("t.emailAddress", requestDto.getEmailAddress());
            }
            if (!StringUtils.isEmpty(requestDto.getPhoneNumber())) {
                entityWrapper.eq("t.phoneNumber", requestDto.getPhoneNumber());
            }
            if (requestDto.getStatus() != null) {
                entityWrapper.eq("t.status", requestDto.getStatus());
            }
            if (requestDto.getCreationTimeStart() != null) {
                entityWrapper.gt("t.creationTime", requestDto.getCreationTimeStart());
            }
            if (requestDto.getCreationTimeEnd() != null) {
                entityWrapper.lt("t.creationTime", requestDto.getCreationTimeEnd());
            }
            if (requestDto.getLastLoginTimeStart() != null) {
                entityWrapper.gt("t.lastLoginTime", requestDto.getLastLoginTimeStart());
            }
            if (requestDto.getLastLoginTimeEnd() != null) {
                entityWrapper.lt("t.lastLoginTime", requestDto.getLastLoginTimeEnd());
            }
            entityWrapper.orderBy("t.creationTime", false);
            Page<User> userPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<User> userPageList = userCrudService.selectPageWithUserInfo(userPage, entityWrapper);
            if (userPageList != null) {
                List<CustomerUserResultDto> userDtoList = modelMapper.map(userPageList.getRecords(), new TypeToken<List<CustomerUserResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(userPageList.getCurrent());
                pagedResultDto.setPageSize(userPageList.getSize());
                pagedResultDto.setTotalCount(userPageList.getTotal());
                pagedResultDto.setItems(userDtoList);
            }
            return pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页查询用户列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    @Override
    public ObjectResultDto<UserPasswordSetStatusResultDto> hasSetPassword(UserHasSetPasswordRequestDto requestDto) {
        ObjectResultDto<UserPasswordSetStatusResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            UserPasswordSetStatusResultDto resultDto = new UserPasswordSetStatusResultDto();
            Long userId = requestDto.getSessionIdentity().getUserId();
            User user = userCrudService.selectById(userId);
            if (null == user) {
                return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            if (StringUtils.isEmpty(user.getPassword())) {
                resultDto.setSet(Boolean.FALSE);
            } else {
                resultDto.setSet(Boolean.TRUE);
            }
            objectResultDto.setData(resultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("查询用户是否已设置登录密码失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 修改密码
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto modifyPassword(UserPasswordModifyRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            User user = userCrudService.selectById(userId);
            if (user == null) {
                return resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            if (StringUtils.isEmpty(requestDto.getOldPassword())) {
                return resultDto.makeResult(UCenterResultEnum.OLD_PASSWORD_ERROR.getValue(),
                        UCenterResultEnum.OLD_PASSWORD_ERROR.getComment());
            }
            if (StringUtils.isEmpty(requestDto.getNewPassword())) {
                return resultDto.makeResult(UCenterResultEnum.USER_PASSWORD_EMPTY.getValue(),
                        UCenterResultEnum.USER_PASSWORD_EMPTY.getComment());
            }
            String regx = "^[a-zA-Z0-9\\W]{6,20}$";
            if (!requestDto.getNewPassword().matches(regx)) {
                return resultDto.makeResult(UCenterResultEnum.USER_PASSWORD_LENGTH_FAIL.getValue(),
                        UCenterResultEnum.USER_PASSWORD_LENGTH_FAIL.getComment());
            }
            //密码处理
            String oldEncryptPassword = shiroCryptoService.password(requestDto.getOldPassword(), user.getUsername() + user.getSalt());
            if (!oldEncryptPassword.equals(user.getPassword())) {
                return resultDto.makeResult(UCenterResultEnum.OLD_PASSWORD_ERROR.getValue(),
                        UCenterResultEnum.OLD_PASSWORD_ERROR.getComment());
            }
            //密码处理
            String salt = saltGenerator.generate();
            String encryptPassword = shiroCryptoService.password(requestDto.getNewPassword(), user.getUsername() + salt);
            user.setSalt(salt);
            user.setPassword(encryptPassword);
            //修改用户
            boolean retVal = userCrudService.updateById(user);
            if (!retVal) {
                return resultDto.failed();
            }
            //密码修改记录
            UserPasswordLog userPasswordLog = new UserPasswordLog();
            userPasswordLog.setUserId(user.getId());
            userPasswordLog.setPasswordType(PasswordTypeEnum.ACCESS.getValue());
            userPasswordLog.setOldPassword(oldEncryptPassword);
            userPasswordLog.setNewPassword(encryptPassword);
            userPasswordLogCrudService.insert(userPasswordLog);
            return resultDto.success();
        } catch (Exception e) {
            log.error("修改密码" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 用户是否已存在
     */
    @Override
    public ObjectResultDto<UserHasExistResultDto> checkExist(UserHasExistRequestDto requestDto) {
        ObjectResultDto<UserHasExistResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            User user = userCrudService.findByUsername(requestDto.getPhoneNumber());
            UserHasExistResultDto userHasExistResultDto = new UserHasExistResultDto();
            userHasExistResultDto.setHasExist(user != null);
            objectResultDto.setData(userHasExistResultDto);
            return objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户是否已存在失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 用户是否已实名认证
     */
    @Override
    public ObjectResultDto<UserHasCertifiedResultDto> hasCertified(UserHasCertifiedRequestDto requestDto) {
        ObjectResultDto<UserHasCertifiedResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            UserInfo userInfo = userInfoCrudService.findByUserId(userId);
            if (userInfo == null) {
                objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
            } else {
                UserHasCertifiedResultDto userHasCertifiedResultDto = new UserHasCertifiedResultDto();
                if (userInfo.getCertificateStatus().equals(UserAuthStatusEnum.AUTHENTICATED.getValue())) {
                    userHasCertifiedResultDto.setHasCertified(Boolean.TRUE);
                } else {
                    userHasCertifiedResultDto.setHasCertified(Boolean.FALSE);
                }
                objectResultDto.setData(userHasCertifiedResultDto);
                objectResultDto.success();
            }
            return objectResultDto;
        } catch (Exception e) {
            log.error("获取用户实名认证状态失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取用户个人信息
     */
    @Override
    public ObjectResultDto<UserProfileResultDto> getUserProfile(UserProfileGetRequestDto requestDto) {
        ObjectResultDto<UserProfileResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            User user = userCrudService.selectById(userId);
            if (user == null) {
                objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
            } else {
                UserInfo userInfo = userInfoCrudService.findByUserId(userId);
                if (userInfo == null) {
                    objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
                } else {
                    UserProfileResultDto userProfileResultDto = new UserProfileResultDto();
                    userProfileResultDto.setAuthenticated(userInfo.getCertificateStatus());
                    if (userInfo.getBirthday() != null) {
                        userProfileResultDto.setBirthday(DateUtils.formatDate(userInfo.getBirthday(), DateUtils.YYYY_MM_DD));
                    } else {
                        userProfileResultDto.setBirthday("");
                    }
                    userProfileResultDto.setGender(userInfo.getGender());
                    userProfileResultDto.setRealName(userInfo.getRealName());
                    userProfileResultDto.setCardNo(userInfo.getCardNo());
                    userProfileResultDto.setUsername(user.getUsername());
                    userProfileResultDto.setEmailAddress(user.getEmailAddress());
                    userProfileResultDto.setPhoneNumber(user.getPhoneNumber());
                    userProfileResultDto.setPortrait(user.getPortrait());
                    userProfileResultDto.setNickname(user.getNickname());
                    if (user.getCreationTime() != null) {
                        userProfileResultDto.setCreationTime(DateUtils.formatDate(user.getCreationTime(), DateUtils.YYYY_MM_DD));
                    }
                    objectResultDto.setData(userProfileResultDto);
                    objectResultDto.success();
                }
            }
        } catch (Exception e) {
            log.error("用户个人信息获取失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取用户钱包余额
     */
    @Override
    public ObjectResultDto<UserPacketBalanceResultDto> getPacketBalance(UserPacketBalanceGetRequestDto requestDto) {

        ObjectResultDto<UserPacketBalanceResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            UserAsset userAsset = userAssetCrudService.findByUserId(userId);
            if (userAsset == null) {
                objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
            } else {
                UserPacketBalanceResultDto userPacketBalanceResultDto = new UserPacketBalanceResultDto();
                if (userAsset.getBalance() != null) {
                    userPacketBalanceResultDto.setBalance(new BigDecimal(userAsset.getBalance().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
                } else {
                    userPacketBalanceResultDto.setBalance(BigDecimal.valueOf(0.00).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                objectResultDto.setData(userPacketBalanceResultDto);
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("用户钱包余额获取失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 用户修改个人头像
     */
    @Override
    public ResultDto updateUserPortrait(UserPortraitUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (StringUtils.isEmpty(requestDto.getPortraitUrl())) {
                return resultDto.failed("图像地址不能为空");
            }
            Long userId = requestDto.getSessionIdentity().getUserId();
            User user = userCrudService.selectById(userId);
            if (user == null) {
                return resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            User userUpdate = new User();
            userUpdate.setPortrait(requestDto.getPortraitUrl());
            EntityWrapper<User> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", userId);
            userCrudService.update(userUpdate, entityWrapper);
            return resultDto.success();
        } catch (Exception e) {
            log.error("上传头像" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取用户积分
     */
    @Override
    public ObjectResultDto<UserPacketPointResultDto> getPacketPoint(UserPacketPointGetRequestDto requestDto) {
        ObjectResultDto<UserPacketPointResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            UserAsset userAsset = userAssetCrudService.findByUserId(userId);
            if (userAsset == null) {
                objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
            } else {
                UserPacketPointResultDto packetPointResultDto = new UserPacketPointResultDto();
                if (userAsset.getPoint() != null) {
                    packetPointResultDto.setAmount(userAsset.getPoint().intValue());
                } else {
                    packetPointResultDto.setAmount(0);
                }
                objectResultDto.setData(packetPointResultDto);
                objectResultDto.success();
            }
            return objectResultDto.success();
        } catch (Exception e) {
            log.error("用户积分获取失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取用户等级
     */
    @Override
    public ObjectResultDto<UserLevelResultDto> getUserLevel(UserLevelGetRequestDto requestDto) {
        ObjectResultDto<UserLevelResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            UserInfo userInfo = userInfoCrudService.findByUserId(userId);
            if (userInfo == null) {
                objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
            } else {
                UserLevelResultDto levelResultDto = new UserLevelResultDto();
                levelResultDto.setLevel(userInfo.getLevel());
                UserLevelEnum userLevelEnum = UserLevelEnum.parse(userInfo.getLevel());
                if (userLevelEnum != null) {
                    levelResultDto.setLevelDesc(userLevelEnum.getComment());
                }
                objectResultDto.setData(levelResultDto);
                objectResultDto.success();
            }
            return objectResultDto.success();
        } catch (Exception e) {
            log.error("用户等级获取失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取用户资产信息
     */
    @Override
    public ObjectResultDto<UserAssetInfoResultDto> getUserAsset(UserAssetGetRequestDto requestDto) {
        ObjectResultDto<UserAssetInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            UserAsset userAsset = userAssetCrudService.findByUserId(requestDto.getUserId());
            if (userAsset == null) {
                objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
            } else {
                UserAssetInfoResultDto userAssetInfoResultDto = modelMapper.map(userAsset, UserAssetInfoResultDto.class);
                objectResultDto.setData(userAssetInfoResultDto);
                objectResultDto.success();
            }
            return objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户资产信息失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 更新用户资产信息
     */
    @Override
    public ResultDto updateUserAsset(UserAssetUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            UserAsset userAsset = new UserAsset();
            userAsset.setBalance(requestDto.getBalance());
            userAsset.setId(requestDto.getId());
            userAssetCrudService.updateById(userAsset);
            return resultDto.success();
        } catch (Exception e) {
            log.error("更新用户资产信息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 重置登录密码
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto setPassword(UserSetPasswordRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (StringUtils.isEmpty(requestDto.getPassword()) || StringUtils.isEmpty(requestDto.getConfirmedPassword())) {
                return resultDto.makeResult(UCenterResultEnum.USER_PASSWORD_EMPTY.getValue(),
                        UCenterResultEnum.USER_PASSWORD_EMPTY.getComment());
            }
            String regx = "^[a-zA-Z0-9\\W]{6,20}$";
            if (!requestDto.getPassword().matches(regx)) {
                return resultDto.makeResult(UCenterResultEnum.USER_PASSWORD_LENGTH_FAIL.getValue(),
                        UCenterResultEnum.USER_PASSWORD_LENGTH_FAIL.getComment());
            }
            if (!requestDto.getConfirmedPassword().equals(requestDto.getPassword())) {
                return resultDto.makeResult(UCenterResultEnum.PASSWORD_DISMATCH_ERROR.getValue(),
                        UCenterResultEnum.PASSWORD_DISMATCH_ERROR.getComment()
                );
            }
            Long userId = requestDto.getSessionIdentity().getUserId();
            User user = userCrudService.selectById(userId);
            if (user == null) {
                return resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            if (StringUtils.isNotEmpty(user.getPassword())) {

                return resultDto.makeResult(UCenterResultEnum.USER_PASSWORD_HAS_SET.getValue(),
                        UCenterResultEnum.USER_PASSWORD_HAS_SET.getComment());
            }
            //密码处理
            String salt = saltGenerator.generate();
            String encryptPassword = shiroCryptoService.password(requestDto.getPassword(), user.getUsername() + salt);
            user.setSalt(salt);
            user.setPassword(encryptPassword);
            //修改用户
            boolean retVal = userCrudService.updateById(user);
            if (!retVal) {
                return resultDto.failed();
            }
            //密码修改记录
            UserPasswordLog userPasswordLog = new UserPasswordLog();
            userPasswordLog.setUserId(user.getId());
            userPasswordLog.setPasswordType(PasswordTypeEnum.ACCESS.getValue());
            userPasswordLog.setOldPassword("");
            userPasswordLog.setNewPassword(encryptPassword);
            userPasswordLogCrudService.insert(userPasswordLog);
            resultDto.success();
        } catch (Exception e) {
            log.error("用户重置登录密码失败");
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 校验支付密码
     */
    @Override
    public ObjectResultDto<UserTradePasswordCheckResultDto> checkTradePassword(UserTradePasswordCheckRequestDto requestDto) {
        ObjectResultDto<UserTradePasswordCheckResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            UserTradePasswordCheckResultDto resultDto = new UserTradePasswordCheckResultDto();
            String rex = "[0-9]{6}";
            if (StringUtils.isEmpty(requestDto.getTradePassword())
                    || !requestDto.getTradePassword().matches(rex)) {
                resultDto.setPassed(Boolean.FALSE);
            } else {
                Long userId = requestDto.getSessionIdentity().getUserId();
                User user = userCrudService.selectById(userId);
                if (user == null) {
                    return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                            UCenterResultEnum.USER_NOT_FOUND.getComment());
                }
                //更新密码校验次数
                User userUpdate = new User();
                userUpdate.setId(user.getId());
                //获得加盐后的密码
                String encryptPassword = shiroCryptoService.password(requestDto.getTradePassword(), user.getUsername() + user.getTradeSalt());
                if (!encryptPassword.equals(user.getTradePassword())) {
                    user.setTradeAttemptCount(user.getTradeAttemptCount() + 1);
                    resultDto.setPassed(Boolean.FALSE);
                    objectResultDto.makeResult(UCenterResultEnum.USER_TRADE_PASSWORD_ERROR.getValue(),
                            UCenterResultEnum.USER_TRADE_PASSWORD_ERROR.getComment());
                } else {
                    user.setTradeAttemptCount(0);
                    resultDto.setPassed(Boolean.TRUE);
                    objectResultDto.makeResult(UCenterResultEnum.USER_TRADE_PASSWORD_SUCCESS.getValue(),
                            UCenterResultEnum.USER_TRADE_PASSWORD_SUCCESS.getComment());
                }
                EntityWrapper<User> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("id", user.getId());
                userCrudService.update(userUpdate, entityWrapper);
            }
            objectResultDto.setData(resultDto);
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("校验支付密码失败");
        }
        return objectResultDto;
    }

    /**
     * 设置支付密码
     */
    @Override
    public ResultDto setTradePassword(UserTradePasswordSetRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (StringUtils.isEmpty(requestDto.getTradePassword())) {
                return resultDto.makeResult(UCenterResultEnum.USER_TRADE_PASSWORD_EMPTY.getValue(),
                        UCenterResultEnum.USER_TRADE_PASSWORD_EMPTY.getComment());
            }
            String rex = "[0-9]{6}";
            if (!requestDto.getTradePassword().matches(rex)) {
                return resultDto.makeResult(UCenterResultEnum.USER_TRAD_PASSWORD_LENGTH_FAIL.getValue(),
                        UCenterResultEnum.USER_TRAD_PASSWORD_LENGTH_FAIL.getComment());
            }
            Long userId = requestDto.getSessionIdentity().getUserId();
            User user = userCrudService.selectById(userId);
            if (user == null) {
                return resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            String oldTradePassword = user.getTradePassword();
            //密码处理
            String salt = saltGenerator.generate();
            String tradePassword = shiroCryptoService.password(requestDto.getTradePassword(), user.getUsername() + salt);
            user.setTradeSalt(salt);
            user.setTradePassword(tradePassword);
            user.setTradeAttemptCount(0);
            boolean retVal = userCrudService.updateById(user);
            if (!retVal) {
                return resultDto.failed();
            }
            //密码修改记录
            UserPasswordLog userPasswordLog = new UserPasswordLog();
            userPasswordLog.setUserId(user.getId());
            userPasswordLog.setPasswordType(PasswordTypeEnum.TRADE.getValue());
            userPasswordLog.setOldPassword(oldTradePassword);
            userPasswordLog.setNewPassword(tradePassword);
            userPasswordLogCrudService.insert(userPasswordLog);
            return resultDto.success();
        } catch (Exception e) {
            resultDto.failed();
            log.error("设置支付密码失败");
        }
        return resultDto;
    }

    /**
     * 校验支付码是否存在
     */
    @Override
    public ObjectResultDto<UserTradePwdSetStatusResultDto> checkTradePasswordExist(UserTradePasswordExistRequestDto requestDto) {
        ObjectResultDto<UserTradePwdSetStatusResultDto> resultDto = new ObjectResultDto<>();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            User user = userCrudService.selectById(userId);
            if (user == null) {
                resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            } else {
                UserTradePwdSetStatusResultDto userTradePwdSetStatusResultDto = new UserTradePwdSetStatusResultDto();
                if (StringUtils.isNotEmpty(user.getTradePassword())) {
                    userTradePwdSetStatusResultDto.setSet(Boolean.TRUE);
                } else {
                    userTradePwdSetStatusResultDto.setSet(Boolean.FALSE);
                }
                resultDto.setData(userTradePwdSetStatusResultDto);
                resultDto.success();
            }
        } catch (Exception e) {
            resultDto.failed();
            log.error("校验支付密码存在失败");
        }
        return resultDto;
    }

    /**
     * 用户微信绑定状态
     */
    @Override
    public ObjectResultDto<UserWechatBindStatusResultDto> hasBindWechat(UserHasBindWechatRequestDto requestDto) {
        ObjectResultDto<UserWechatBindStatusResultDto> objectResultDto = new ObjectResultDto<>();
        UserWechatBindStatusResultDto resultDto = new UserWechatBindStatusResultDto();
        Long userId = requestDto.getSessionIdentity().getUserId();
        UserInfo userInfo = userInfoCrudService.findByUserId(userId);
        if (null == userInfo) {
            return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
        }
        if (StringUtils.isEmpty(userInfo.getWxUnionId())) {
            resultDto.setBind(Boolean.FALSE);
        } else {
            resultDto.setWxNickname(userInfo.getWxNickname());
            resultDto.setBind(Boolean.TRUE);
        }
        objectResultDto.setData(resultDto);
        objectResultDto.success();
        return objectResultDto;
    }

    /**
     * 绑定微信账号
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<UserWechatBindResultDto> bindWechat(UserWechatBindRequestDto requestDto) {
        ObjectResultDto<UserWechatBindResultDto> objectResultDto = new ObjectResultDto<>();
        UserWechatBindResultDto userWechatBindResultDto = new UserWechatBindResultDto();
        if (null == requestDto.getUnionid()) {
            return objectResultDto.makeResult(UCenterResultEnum.UNIONID_NOT_EXISTS.getValue(), UCenterResultEnum.UNIONID_NOT_EXISTS.getComment());
        }
        UserInfo userInfo = userInfoCrudService.findByUnionId(requestDto.getUnionid());
        if (userInfo != null) {
            User existUser = userCrudService.selectById(userInfo.getUserId());
            if (null != existUser && existUser.getUserType().equals(UserTypeEnum.TEMPORARY_CUSTOMER.getValue())) {
                //通过微信公众号绑定的无手机号的账号
                //再次被手机号绑定，需要删除之前的账号信息
                userCrudService.deleteById(existUser.getId());
                userInfoCrudService.deleteById(userInfo.getId());
            } else {
                userWechatBindResultDto.setSuccess(Boolean.FALSE);
                objectResultDto.setData(userWechatBindResultDto);
                return objectResultDto.makeResult(UCenterResultEnum.USER_WECHAT_HAS_BIND.getValue(), UCenterResultEnum.USER_WECHAT_HAS_BIND.getComment());
            }
        }
        Long userId = requestDto.getSessionIdentity().getUserId();
        User existUser = userCrudService.selectById(userId);
        if (null == existUser) {
            return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
        }
        UserInfo existUserInfo = userInfoCrudService.findByUserId(userId);
        if (null == existUserInfo) {
            return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
        }
        if (StringUtils.isNotEmpty(existUserInfo.getWxOpenId())) {
            return objectResultDto.makeResult(UCenterResultEnum.USER_HAS_BIND_WECHAT.getValue(), UCenterResultEnum.USER_HAS_BIND_WECHAT.getComment());
        }

        //未设置头像或昵称用微信
        if (StringUtils.isEmpty(existUser.getNickname())
                || StringUtils.isEmpty(existUser.getPortrait())) {
            EntityWrapper<User> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", userId);
            User userUpdate = new User();
            if (StringUtils.isEmpty(existUser.getNickname())) {
                userUpdate.setNickname(requestDto.getNickName());
            }
            if (StringUtils.isEmpty(existUser.getPortrait())) {
                userUpdate.setPortrait(requestDto.getHeadImgUrl());
            }
            userCrudService.update(userUpdate, entityWrapper);
        }

        existUserInfo.setWxUuid(StringUtils.getUUID());
        existUserInfo.setWxOpenId(requestDto.getOpenId());
        existUserInfo.setWxUnionId(requestDto.getUnionid());
        existUserInfo.setWxNickname(requestDto.getNickName());
        existUserInfo.setWxCity(requestDto.getCity());
        existUserInfo.setWxCounty(requestDto.getCountry());
        existUserInfo.setWxProvince(requestDto.getProvince());
        existUserInfo.setWxSex(Integer.parseInt(requestDto.getSex()));
        existUserInfo.setWxAuthorized(Boolean.TRUE);
        userInfoCrudService.updateById(existUserInfo);

        userWechatBindResultDto.setSuccess(Boolean.TRUE);
        objectResultDto.setData(userWechatBindResultDto);
        objectResultDto.success();
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<GetUserPhoneResultDto> getUserPhone(UserPhoneGetRequestDto requestDto) {
        ObjectResultDto<GetUserPhoneResultDto> objectResultDto = new ObjectResultDto<>();
        GetUserPhoneResultDto resultDto = new GetUserPhoneResultDto();
        String phoneNum = requestDto.getSessionIdentity().getPhoneNumber();
        resultDto.setPhone(phoneNum);
        objectResultDto.setData(resultDto);
        objectResultDto.success();
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<UserAlipayBindStatusResultDto> hasBindAlipay(UserHasBindAlipayRequestDto requestDto) {
        ObjectResultDto<UserAlipayBindStatusResultDto> objectResultDto = new ObjectResultDto<>();
        UserAlipayBindStatusResultDto resultDto = new UserAlipayBindStatusResultDto();
        Long userId = requestDto.getSessionIdentity().getUserId();
        UserInfo userInfo = userInfoCrudService.findByUserId(userId);
        if (null == userInfo) {
            return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
        }
        if (StringUtils.isEmpty(userInfo.getAliUserId())) {
            resultDto.setBind(Boolean.FALSE);
        } else {
            resultDto.setZfbNickname(userInfo.getAliNickname());
            resultDto.setBind(Boolean.TRUE);
        }
        objectResultDto.setData(resultDto);
        objectResultDto.success();
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<UserAlipayBindResultDto> bindAlipay(UserAlipayBindRequestDto requestDto) {
        ObjectResultDto<UserAlipayBindResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            UserAlipayBindResultDto resultDto = new UserAlipayBindResultDto();
            if (null == requestDto.getAlipayUserId()) {
                return objectResultDto.makeResult(UCenterResultEnum.ALIPAYUSERID_EMPTY.getValue(), UCenterResultEnum.ALIPAYUSERID_EMPTY.getComment());
            }
            UserInfo euserInfo = userInfoCrudService.findByAliUserId(requestDto.getAlipayUserId());
            if (euserInfo != null) {
                resultDto.setSuccess(Boolean.FALSE);
                objectResultDto.setData(resultDto);
                return objectResultDto.makeResult(UCenterResultEnum.USER_ALIPAY_HAS_BIND.getValue(), UCenterResultEnum.USER_ALIPAY_HAS_BIND.getComment());
            }
            Long userId = requestDto.getSessionIdentity().getUserId();
            User existUser = userCrudService.selectById(userId);
            if (null == existUser) {
                return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            UserInfo existUserInfo = userInfoCrudService.findByUserId(userId);
            if (null == existUserInfo) {
                return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(), UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            if (StringUtils.isNotEmpty(existUserInfo.getAliUserId())) {
                return objectResultDto.makeResult(UCenterResultEnum.USER_HAS_BIND_ALIPAY.getValue(), UCenterResultEnum.USER_HAS_BIND_ALIPAY.getComment());
            }

            //未设置头像或昵称用支付宝
            if (StringUtils.isEmpty(existUser.getNickname())
                    || StringUtils.isEmpty(existUser.getPortrait())) {
                EntityWrapper<User> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("id", userId);
                User userUpdate = new User();
                if (StringUtils.isEmpty(existUser.getNickname())) {
                    userUpdate.setNickname(requestDto.getNickName());
                }
                if (StringUtils.isEmpty(existUser.getPortrait())) {
                    userUpdate.setPortrait(requestDto.getAvatar());
                }
                userCrudService.update(userUpdate, entityWrapper);
            }
            existUserInfo.setAliUserId(requestDto.getAlipayUserId());
            existUserInfo.setAliProvince(requestDto.getProvince());
            existUserInfo.setAliCity(requestDto.getCity());
            existUserInfo.setAliUserType(requestDto.getUserType());
            existUserInfo.setAliUserStatus(requestDto.getUserStatus());
            existUserInfo.setAliNickname(requestDto.getNickName());
            existUserInfo.setAliAvatar(requestDto.getAvatar());
            existUserInfo.setAliIsStudentCertified(requestDto.getIsStudentCertified());
            existUserInfo.setAliIsCertified(requestDto.getIsCertified());
            existUserInfo.setAliGender(requestDto.getGender());
            existUserInfo.setAliAuthorized(Boolean.TRUE);
            userInfoCrudService.updateById(existUserInfo);

            resultDto.setSuccess(Boolean.TRUE);
            objectResultDto.setData(resultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("绑定支付宝账号失败" + e.getMessage());
            objectResultDto.makeResult(UCenterResultEnum.USER_ALIPAY_BLIND_ERROR.getValue(), UCenterResultEnum.USER_ALIPAY_BLIND_ERROR.getComment());
        }
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<UserResultDto> getUserByAliUserId(UserGetByAliUserIdRequestDto requestDto) {
        ObjectResultDto<UserResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            UserInfo userInfo = userInfoCrudService.findByAliUserId(requestDto.getAliUserId());
            if (userInfo == null) {
                return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            User user = userCrudService.selectById(userInfo.getUserId());
            if (user == null) {
                return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            UserResultDto userResultDto = modelMapper.map(user, UserResultDto.class);
            objectResultDto.setData(userResultDto);
            return objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 用户修改昵称
     */
    @Override
    public ResultDto modifyNickname(UserNicknameModifyRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (StringUtils.isEmpty(requestDto.getNickname())) {
                return resultDto.makeResult(UCenterResultEnum.USER_NICKNAME_EMPTY.getValue(),
                        UCenterResultEnum.USER_NICKNAME_EMPTY.getComment());
            }

            Long userId = requestDto.getSessionIdentity().getUserId();
            User user = userCrudService.selectById(userId);
            if (user == null) {
                return resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            EntityWrapper<User> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", userId);
            User userUpdate = new User();
            userUpdate.setNickname(requestDto.getNickname());
            userCrudService.update(userUpdate, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("昵称修改失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 用户修改性别
     */
    @Override
    public ResultDto modifyGender(UserGenderModifyRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (null == requestDto.getGender()) {
                return resultDto.makeResult(UCenterResultEnum.USER_GENDER_EMPTY.getValue(),
                        UCenterResultEnum.USER_GENDER_EMPTY.getComment());
            }
            if (requestDto.getGender() != UserGenderEnum.MAN.getValue() && requestDto.getGender() != UserGenderEnum.WOMEN.getValue()) {
                return resultDto.makeResult(UCenterResultEnum.USER_GENDER_ERR.getValue(),
                        UCenterResultEnum.USER_GENDER_ERR.getComment());
            }
            Long userId = requestDto.getSessionIdentity().getUserId();
            UserInfo userInfo = userInfoCrudService.findByUserId(userId);
            if (userInfo == null) {
                return resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }

            EntityWrapper<UserInfo> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", userInfo.getId());
            UserInfo userInfoUpdate = new UserInfo();
            userInfoUpdate.setGender(requestDto.getGender());
            userInfoCrudService.update(userInfoUpdate, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("性别修改失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 用户修改生日
     */
    @Override
    public ResultDto modifyBirthday(UserBirthdayModifyRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (null == requestDto.getBirthday()) {
                return resultDto.makeResult(UCenterResultEnum.USER_BIRTHDAY_EMPTY.getValue(),
                        UCenterResultEnum.USER_BIRTHDAY_EMPTY.getComment());
            }
            Long userId = requestDto.getSessionIdentity().getUserId();
            UserInfo userInfo = userInfoCrudService.findByUserId(userId);
            if (userInfo == null) {
                return resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }

            EntityWrapper<UserInfo> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", userInfo.getId());
            UserInfo userInfoUpdate = new UserInfo();
            userInfoUpdate.setBirthday(requestDto.getBirthday());
            userInfoCrudService.update(userInfoUpdate, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("生日修改失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改个人信息
     */
    @Override
    public ResultDto modifyProfile(UserProfileModifyRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {

            Long userId = requestDto.getSessionIdentity().getUserId();

            User user = userCrudService.selectById(userId);
            if (user == null) {
                return resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }

            UserInfo userInfo = userInfoCrudService.findByUserId(userId);
            if (userInfo == null) {
                return resultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }

            if (null != userInfo.getBirthday() && requestDto.getBirthday().compareTo(new Date()) > 0) {
                return resultDto.makeResult(UCenterResultEnum.BIRTHDAY_ERR.getValue(),
                        UCenterResultEnum.BIRTHDAY_ERR.getComment());
            }
            EntityWrapper<User> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", userId);
            User userUpdate = new User();
            userUpdate.setNickname(requestDto.getNickname());
            userCrudService.update(userUpdate, entityWrapper);

            EntityWrapper<UserInfo> entityWrapperUserInfo = new EntityWrapper<>();
            entityWrapperUserInfo.eq("id", userInfo.getId());
            UserInfo userInfoUpdate = new UserInfo();
            userInfoUpdate.setBirthday(requestDto.getBirthday());
            userInfoUpdate.setGender(requestDto.getGender());
            userInfoCrudService.update(userInfoUpdate, entityWrapperUserInfo);
            resultDto.success();
        } catch (Exception e) {
            log.error("个人信息修改失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * userId获取用户信息
     *
     * @param requestDto UserGetByUserNameRequestDto
     * @return UserResultDto
     */
    @Override
    public ObjectResultDto<UserResultDto> getUserByUserName(UserGetByUserNameRequestDto requestDto) {
        ObjectResultDto<UserResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            User user = userCrudService.findByUsername(requestDto.getUserName());
            if (user == null) {
                return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            UserResultDto userResultDto = modelMapper.map(user, UserResultDto.class);
            objectResultDto.setData(userResultDto);
            return objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 更新用户标签
     *
     * @param requestDto requestDto
     */
    @Override
    public ResultDto updateUserTag(UserTagUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {

            resultDto.success();
        } catch (Exception e) {
            log.error("获取用户失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取用户标签
     *
     * @param requestDto requestDto
     */
    @Override
    public ListResultDto<UserTagResultDto> getUserTagList(UserTagListGetRequestDto requestDto) {
        ListResultDto<UserTagResultDto> resultDto = new ListResultDto<>();
        try {

            resultDto.success();
        } catch (Exception e) {
            log.error("获取用户失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除用户标签
     *
     * @param requestDto requestDto
     */
    @Override
    public ResultDto deleteUserTag(UserTagDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {

            resultDto.success();
        } catch (Exception e) {
            log.error("获取用户失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页获取logo
     */
    @Override
    public PagedResultDto<UserPortraitViewResultDto> getUserPortraitViewList(UserPortraitQueryPagedResultRequestDto requestDto) {
        PagedResultDto<UserPortraitViewResultDto> pagedResultDto = new PagedResultDto<>();
        try {

            EntityWrapper<User> entityWrapper = new EntityWrapper<>();
            Page<User> userPortraitPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            entityWrapper.isNotNull("portrait");
            Page<User> userPortraitPageList = userCrudService.selectPage(userPortraitPage, entityWrapper);
            if (userPortraitPageList != null) {

                List<UserPortraitViewResultDto> userPortraitDtoList = modelMapper.map(userPortraitPageList.getRecords(), new TypeToken<List<UserPortraitViewResultDto>>() {
                }.getType());
                pagedResultDto.setTotalCount(userPortraitPageList.getTotal());
                pagedResultDto.setPageNo(userPortraitPageList.getCurrent());
                pagedResultDto.setPageSize(userPortraitPage.getSize());
                pagedResultDto.setItems(userPortraitDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("logo获取失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 修改logo的url
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateUserPortraitView(UserPortraitUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            User userInfo = userCrudService.selectById(requestDto.getId());
            if (userInfo == null) {
                return resultDto.failed("车辆不存在");
            }
            User userInfoUpdate = new User();
            userInfoUpdate.setPortrait(requestDto.getPortraitUrl());
            EntityWrapper<User> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", userInfo.getId());
            userCrudService.update(userInfoUpdate, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("修改车辆logo失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 公众号判断用户是否已存（手机号已存在未绑定微信为FALSE）
     */
    @Override
    public ObjectResultDto<UserHasExistResultDto> checkUserExist(UserHasExistRequestDto requestDto) {
        ObjectResultDto<UserHasExistResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            if (StringUtils.isEmpty(requestDto.getPhoneNumber())) {
                return objectResultDto.failed();
            }
            User user = userCrudService.findByUsername(requestDto.getPhoneNumber());
            UserHasExistResultDto userHasExistResultDto = new UserHasExistResultDto();
            userHasExistResultDto.setHasExist(Boolean.FALSE);
            if (null != user) {
                UserInfo userInfo = userInfoCrudService.findByUserId(user.getId());
                if (null != userInfo && StringUtils.isNotEmpty(userInfo.getWxUnionId())) {
                    userHasExistResultDto.setHasExist(Boolean.TRUE);
                }
            }
            objectResultDto.setData(userHasExistResultDto);
            return objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户是否已存在失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<UserResultDto> getUserByUnionId(UserGetByWxUnionIdRequestDto requestDto) {
        ObjectResultDto<UserResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            UserInfo userInfo = userInfoCrudService.findByUnionId(requestDto.getUnionId());
            if (userInfo == null) {
                return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            User user = userCrudService.selectById(userInfo.getUserId());
            if (user == null) {
                return objectResultDto.makeResult(UCenterResultEnum.USER_NOT_FOUND.getValue(),
                        UCenterResultEnum.USER_NOT_FOUND.getComment());
            }
            UserResultDto userResultDto = modelMapper.map(user, UserResultDto.class);
            objectResultDto.setData(userResultDto);
            return objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 通过用户Id获取用户信息
     *
     * @param requestDto UserInfoGetRequestDto
     * @return UserInfoResultDto
     */
    @Override
    public ObjectResultDto<UserInfoResultDto> getUserInfoByUserId(UserInfoGetRequestDto requestDto) {
        ObjectResultDto<UserInfoResultDto> resultDto = new ObjectResultDto<>();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            UserInfo userInfo = userInfoCrudService.findByUserId(userId);
            if (userId != null) {
                UserInfoResultDto userInfoResultDto = modelMapper.map(userInfo, UserInfoResultDto.class);
                userInfoResultDto.setCertificated(userInfo.getCertificateStatus());
                resultDto.setData(userInfoResultDto);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("通过用户Id获取用户信息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 更新用户信息
     *
     * @param requestDto UserInfoUpDateRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto updateUserInfo(UserInfoUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<UserInfo> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getId());
            UserInfo userInfo = modelMapper.map(requestDto, UserInfo.class);
            userInfo.setCertificateStatus(requestDto.getCertificated());
            userInfo.setLastModifierUserId(requestDto.getSessionIdentity().getUserId());
            boolean update = userInfoCrudService.update(userInfo, entityWrapper);
            if (!update) {
                return resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("更新用户信息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

}
