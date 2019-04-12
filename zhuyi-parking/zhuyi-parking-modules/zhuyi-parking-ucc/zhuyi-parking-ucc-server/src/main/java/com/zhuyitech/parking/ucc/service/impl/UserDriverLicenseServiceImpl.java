package com.zhuyitech.parking.ucc.service.impl;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.tool.dto.request.driver.LicenseScoreQueryRequestDto;
import com.zhuyitech.parking.ucc.domain.UserDriverLicense;
import com.zhuyitech.parking.ucc.dto.request.driverlicense.*;
import com.zhuyitech.parking.ucc.dto.result.UserDriverLicenseExistResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserDriverLicenseResultDto;
import com.zhuyitech.parking.ucc.enums.UCenterResultEnum;
import com.zhuyitech.parking.ucc.service.UserDriverLicenseCrudService;
import com.zhuyitech.parking.ucc.service.api.UserDriverLicenseService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户驾驶证
 *
 * @Date: 2018/1/15
 * @author: yuzhicheng
 */
@Service("userDriverLicenseService")
public class UserDriverLicenseServiceImpl implements UserDriverLicenseService {

    private static final Logger LOG = LoggerFactory.getLogger(UserDriverLicenseServiceImpl.class);

    @Autowired
    private UserDriverLicenseCrudService userDriverLicenseCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 获取驾驶人准驾车型列表
     *
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getDriverClassList() {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto("A1", "A1", false));
            itemDtoList.add(new ComboboxItemDto("A2", "A2", false));
            itemDtoList.add(new ComboboxItemDto("A3", "A3", false));
            itemDtoList.add(new ComboboxItemDto("B1", "B1", false));
            itemDtoList.add(new ComboboxItemDto("B2", "B2", false));
            itemDtoList.add(new ComboboxItemDto("C1", "C1", false));
            itemDtoList.add(new ComboboxItemDto("C2", "C2", false));
            itemDtoList.add(new ComboboxItemDto("C3", "C3", false));
            itemDtoList.add(new ComboboxItemDto("C4", "C4", false));
            itemDtoList.add(new ComboboxItemDto("C5", "C5", false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            LOG.error("获取准驾车型列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 用户驾驶证是否存在
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<UserDriverLicenseExistResultDto> existUserDriverLicense(UserDriverLicenseExistRequestDto requestDto) {

        ObjectResultDto<UserDriverLicenseExistResultDto> resultDto = new ObjectResultDto<>();
        try {

            UserDriverLicenseExistResultDto userDriverLicenseExistResultDto = new UserDriverLicenseExistResultDto();
            //一个用户只能绑定一个驾驶证
            Long userId = requestDto.getSessionIdentity().getUserId();
            UserDriverLicense exist = userDriverLicenseCrudService.findByUserId(userId);
            if (exist != null) {
                userDriverLicenseExistResultDto.setExist(Boolean.TRUE);
            } else {
                userDriverLicenseExistResultDto.setExist(Boolean.FALSE);
            }
            return resultDto.success();
        } catch (Exception e) {
            LOG.error("获取用户驾驶证失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 增加用户驾驶证信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addUserDriverLicense(UserDriverLicenseAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Long userId = requestDto.getSessionIdentity().getUserId();
            if (userId == null) {
                return resultDto.makeResult(UCenterResultEnum.USER_ID_EMPTY.getValue(),
                        UCenterResultEnum.USER_ID_EMPTY.getComment());
            }
            if (StringUtils.isEmpty(requestDto.getCardNumber())) {
                return resultDto.makeResult(UCenterResultEnum.DRIVER_LICENSE_CARD_EMPTY.getValue(),
                        UCenterResultEnum.DRIVER_LICENSE_CARD_EMPTY.getComment());
            }
//            String cardType = "([0-9]{17}([0-9]|X))|([0-9]{15})";
//            if (!requestDto.getCardNumber().matches(cardType)) {
//                return resultDto.failed("驾驶证号格式不正确");
//            }
            if (StringUtils.isEmpty(requestDto.getCardNumber())) {
                return resultDto.makeResult(UCenterResultEnum.DRIVER_LICENSE_ARCHIVE_EMPTY.getValue(),
                        UCenterResultEnum.DRIVER_LICENSE_ARCHIVE_EMPTY.getComment());
            }
//            String archiveNumberType = "([0-9]{12})";
//            if (!requestDto.getArchiveNumber().matches(archiveNumberType)) {
//                return resultDto.failed("驾驶证档案编号格式不正确");
//            }
            if (StringUtils.isEmpty(requestDto.getCardNumber())) {
                return resultDto.makeResult(UCenterResultEnum.DRIVER_CLASS_EMPTY.getValue(),
                        UCenterResultEnum.DRIVER_CLASS_EMPTY.getComment());
            }
            String driverClass = "((([a]|[A])[1-3])|(([b]|[B])[1-2])|(([c]|[C])[1-5]))";
            if (!requestDto.getDriveClass().matches(driverClass)) {
                return resultDto.failed("准驾车型格式不正确");
            }
            //一个用户只能绑定一个驾驶证
            UserDriverLicense exist = userDriverLicenseCrudService.findByUserId(userId);
            if (exist != null) {
                return resultDto.failed("该用户已绑定驾驶证");
            }
            UserDriverLicense userDriverLicense = modelMapper.map(requestDto, UserDriverLicense.class);
            userDriverLicense.setUserId(userId);
            userDriverLicenseCrudService.insert(userDriverLicense);
            return resultDto.success();
        } catch (Exception e) {
            LOG.error("添加用户驾驶证失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改用户驾驶证信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateUserDriverLicense(UserDriverLicenseUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            UserDriverLicense userDriverLicense = userDriverLicenseCrudService.findByUserId(requestDto.getSessionIdentity().getUserId());
            if (userDriverLicense == null) {
                return resultDto.failed("用户暂未绑定驾驶证");
            }
            userDriverLicense.setArchiveNumber(requestDto.getArchiveNumber());
            userDriverLicense.setCardNumber(requestDto.getCardNumber());
            userDriverLicense.setDriveClass(requestDto.getDriveClass());
            userDriverLicense.setFirstIssueDate(requestDto.getFirstIssueDate());
            userDriverLicense.setValidateDateStart(requestDto.getValidateDateStart());
            userDriverLicense.setValidateDateEnd(requestDto.getValidateDateEnd());
            userDriverLicenseCrudService.updateById(userDriverLicense);
            return resultDto.success();
        } catch (Exception e) {
            LOG.error("修改用户驾驶证信息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除用户驾驶证信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto deleteUserDriverLicense(UserDriverLicenseDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            userDriverLicenseCrudService.deleteByUserId(requestDto.getSessionIdentity().getUserId());
            return resultDto.success();
        } catch (Exception e) {
            LOG.error("删除用户驾驶证信息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据用户ID获取用户驾驶证信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<UserDriverLicenseResultDto> getUserDriverLicense(UserDriverLicenseGetRequestDto requestDto) {
        ObjectResultDto<UserDriverLicenseResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            UserDriverLicense userDriverLicense = userDriverLicenseCrudService.findByUserId(requestDto.getSessionIdentity().getUserId());
            if (userDriverLicense == null) {
                return objectResultDto.failed("用户暂未绑定驾驶证");
            }
            UserDriverLicenseResultDto resultDto = modelMapper.map(userDriverLicense, UserDriverLicenseResultDto.class);
            objectResultDto.setData(resultDto);
            return objectResultDto.success();
        } catch (Exception e) {
            LOG.error("获取用户驾驶证信息失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 本地同步用户驾驶证扣分数
     *
     * @return Result
     */
    @Override
    public ResultDto updateUserLicense(LicenseScoreQueryRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        LicenseScoreQueryRequestDto carPointPenaltyDataRequestDto = new LicenseScoreQueryRequestDto();
        try {

//            UserDriverLicensePageListGetRequestDto pageRequestDto = new UserDriverLicensePageListGetRequestDto();
//            pageRequestDto.setPageSize(200);
//            PagedResultDto<UserDriverLicenseViewResultDto> pagedResultDto = userDriverLicenseService.getAlreadyBoundLicense(pageRequestDto);
//            Integer pageNo = pagedResultDto.getPageNo();
//
//            for (int i = 0; i <= pageNo; i++) {
//
//                for (UserDriverLicenseViewResultDto licenseViewResultDto : pagedResultDto.getItems()) {
//                    carPointPenaltyDataRequestDto.setLicenseNumber(licenseViewResultDto.getCardNumber());
//                    carPointPenaltyDataRequestDto.setLicenseId(licenseViewResultDto.getArchiveNumber());
//                    ObjectResultDto<LicensePointPenaltyDataResultDto> objectResultDto = requestCarPointPenaltyData(carPointPenaltyDataRequestDto);
//                    if (objectResultDto != null) {
//                        LicensePointPenaltyDataResultDto carPointPenaltyDataResultDto = objectResultDto.getData();
//                        Integer score = carPointPenaltyDataResultDto.getScore();
//                        licenseViewResultDto.setScore(score);
//                        UserDriverLicense userDriverLicense = modelMapper.map(licenseViewResultDto, UserDriverLicense.class);
//                        userDriverLicenseCrudService.updateById(userDriverLicense);
//                        resultDto.success();
//                    } else {
//                        return objectResultDto.makeResult(ToolResultEnum.LICENSE_POINT_REQUEST_ERROR.getValue(), ToolResultEnum.LICENSE_POINT_REQUEST_ERROR.getComment());
//                    }
//                }
//            }

        } catch (Exception e) {
            LOG.error("同步驾驶证扣分数失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }


}
