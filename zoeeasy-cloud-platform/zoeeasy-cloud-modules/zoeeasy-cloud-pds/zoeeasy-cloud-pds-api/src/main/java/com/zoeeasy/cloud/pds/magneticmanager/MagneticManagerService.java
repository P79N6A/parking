package com.zoeeasy.cloud.pds.magneticmanager;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pds.magneticmanager.dto.request.*;
import com.zoeeasy.cloud.pds.magneticmanager.dto.result.MagneticManagerByIdResultDto;
import com.zoeeasy.cloud.pds.magneticmanager.dto.result.MagneticManagerResultDto;

/**
 * @Date: 2018/8/23
 * @author: wh
 */
public interface MagneticManagerService {

    /**
     * 新增停车设备管理器
     *
     * @param requestDto
     * @return
     */
    ResultDto addMagneticManager(MagneticManagerAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除停车设备管理器
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteMagneticManager(MagneticManagerDeleteRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 更新设备管理器
     */
    ResultDto updateMagneticManager(MagneticManagerUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取停车设备管理器
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<MagneticManagerByIdResultDto> getMagneticManager(MagneticManagerGetRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 分页获取停车管理器列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<MagneticManagerResultDto> getMagneticManagerPageList(MagneticManagerQueryPageRequestDto requestDto);
}
