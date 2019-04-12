package com.zoeeasy.cloud.ucc.service.impl;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.ucc.domain.UserLoginLogEntity;
import com.zoeeasy.cloud.ucc.service.UserLoginLogCrudService;
import com.zoeeasy.cloud.ucc.user.UserLoginLogService;
import com.zoeeasy.cloud.ucc.user.dto.request.UserLoginLogSaveRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AkeemSuper
 * @date 2018/11/15 0015
 */
@Service("userLoginLogService")
@Slf4j
public class UserLoginLogServiceImpl implements UserLoginLogService {

    @Autowired
    private UserLoginLogCrudService userLoginLogCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 保存用户登录日志
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto saveUserLoginLog(UserLoginLogSaveRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            UserLoginLogEntity userLoginLogEntity = modelMapper.map(requestDto, UserLoginLogEntity.class);
            userLoginLogCrudService.save(userLoginLogEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("添加用户登录日志失败" + e.getMessage());
        }
        return resultDto;
    }
}
