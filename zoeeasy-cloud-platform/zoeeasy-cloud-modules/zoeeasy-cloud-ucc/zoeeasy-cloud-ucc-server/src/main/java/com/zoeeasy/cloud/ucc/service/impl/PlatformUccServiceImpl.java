package com.zoeeasy.cloud.ucc.service.impl;

import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.ucc.domain.UserEntity;
import com.zoeeasy.cloud.ucc.platform.PlatformUccService;
import com.zoeeasy.cloud.ucc.platform.dto.request.UserGetByUserIdRequestDto;
import com.zoeeasy.cloud.ucc.platform.dto.result.UserInfoResultDto;
import com.zoeeasy.cloud.ucc.service.UserCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AkeemSuper
 * @date 2018/11/6 0006
 */
@Service("platformUccService")
@Slf4j
public class PlatformUccServiceImpl implements PlatformUccService {

    @Autowired
    private UserCrudService userCrudService;

    /**
     * 通过userid获取用户信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<UserInfoResultDto> getUserByUserId(UserGetByUserIdRequestDto requestDto) {
        ObjectResultDto<UserInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            Long userId = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId();
            UserEntity userEntity = userCrudService.selectById(userId);
            if (null != userEntity) {
                UserInfoResultDto userInfoResultDto = new UserInfoResultDto();
                userInfoResultDto.setUserName(userEntity.getUserName());
                userInfoResultDto.setPhoneNumber(userEntity.getPhoneNumber());
                userInfoResultDto.setPortrait(userEntity.getPortrait());
                objectResultDto.setData(userInfoResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("通过userId获取用户信息失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
