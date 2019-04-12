package com.zhuyitech.parking.tool.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.common.utils.QrCodeCreateUtil;
import com.zhuyitech.parking.tool.constant.QrcodeConstant;
import com.zhuyitech.parking.tool.domain.AppVersion;
import com.zhuyitech.parking.tool.dto.request.version.*;
import com.zhuyitech.parking.tool.dto.result.oss.OssFileUploadResultDto;
import com.zhuyitech.parking.tool.dto.result.version.AppVersionCheckResultDto;
import com.zhuyitech.parking.tool.dto.result.version.AppVersionResultDto;
import com.zhuyitech.parking.tool.enums.TerminateTypeEnum;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.enums.VersionStatusEnum;
import com.zhuyitech.parking.tool.service.AppVersionCrudService;
import com.zhuyitech.parking.tool.service.api.OssService;
import com.zhuyitech.parking.tool.service.api.VersionService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

/**
 * 版本服务
 *
 * @author zwq
 */
@Service("versionService")
@Slf4j
public class VersionServiceImpl implements VersionService {

    @Autowired
    private AppVersionCrudService appVersionCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OssService ossService;

    /**
     * 版本号比对
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<AppVersionCheckResultDto> versionCheck(VersionCheckRequestDto requestDto) {
        ObjectResultDto<AppVersionCheckResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            if (StringUtils.isEmpty(requestDto.getVersion())) {
                return objectResultDto.makeResult(ToolResultEnum.REQUEST_PARAM_EMPTY.getValue(), ToolResultEnum.REQUEST_PARAM_EMPTY.getComment());
            }
            if (null == requestDto.getTerminateType()) {
                return objectResultDto.makeResult(ToolResultEnum.REQUEST_PARAM_EMPTY.getValue(), ToolResultEnum.REQUEST_PARAM_EMPTY.getComment());
            }
            if (!requestDto.getTerminateType().equals(TerminateTypeEnum.ANDROID.getValue()) && !requestDto.getTerminateType().equals(TerminateTypeEnum.IOS.getValue())) {
                return objectResultDto.makeResult(ToolResultEnum.TERMINATE_TYPE_ERROR.getValue(), ToolResultEnum.TERMINATE_TYPE_ERROR.getComment());
            }
            AppVersionCheckResultDto versionResultDto = new AppVersionCheckResultDto();
            EntityWrapper<AppVersion> entityForce = new EntityWrapper<>();
            entityForce.eq("terminateType", requestDto.getTerminateType());
            entityForce.eq("status", VersionStatusEnum.PUBLISH.getValue());
            entityForce.eq("forceUpdate", Boolean.TRUE);
            entityForce.gt("version", requestDto.getVersion());
            entityForce.orderBy("version", false);
            entityForce.last("limit 1");
            AppVersion appVersion = appVersionCrudService.selectOne(entityForce);
            if (null == appVersion) {
                EntityWrapper<AppVersion> entity = new EntityWrapper<>();
                entity.eq("terminateType", requestDto.getTerminateType());
                entity.eq("status", VersionStatusEnum.PUBLISH.getValue());
                entity.gt("version", requestDto.getVersion());
                entity.orderBy("version", false);
                entity.last("limit 1");
                appVersion = appVersionCrudService.selectOne(entity);
                if (null == appVersion) {
                    versionResultDto.setNewVersion(Boolean.FALSE);
                    objectResultDto.setData(versionResultDto);
                    return objectResultDto.success();
                }
            }
            versionResultDto = modelMapper.map(appVersion, AppVersionCheckResultDto.class);
            versionResultDto.setNewVersion(Boolean.TRUE);
            versionResultDto.setClientUrl(appVersion.getDownloadUrl());
            objectResultDto.setData(versionResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("比对失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 版本号添加
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto versionAdd(AppVersionAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (StringUtils.isEmpty(requestDto.getVersion())) {
                return resultDto.makeResult(ToolResultEnum.REQUEST_PARAM_EMPTY.getValue(), ToolResultEnum.REQUEST_PARAM_EMPTY.getComment());
            }
            if (null == requestDto.getTerminateType()) {
                return resultDto.makeResult(ToolResultEnum.REQUEST_PARAM_EMPTY.getValue(), ToolResultEnum.REQUEST_PARAM_EMPTY.getComment());
            }
            if (StringUtils.isEmpty(requestDto.getDownloadUrl())) {
                return resultDto.makeResult(ToolResultEnum.REQUEST_PARAM_EMPTY.getValue(), ToolResultEnum.REQUEST_PARAM_EMPTY.getComment());
            }
            if (StringUtils.isEmpty(requestDto.getFileUrl())) {
                return resultDto.makeResult(ToolResultEnum.REQUEST_PARAM_EMPTY.getValue(), ToolResultEnum.REQUEST_PARAM_EMPTY.getComment());
            }
            if (!requestDto.getTerminateType().equals(TerminateTypeEnum.ANDROID.getValue()) && !requestDto.getTerminateType().equals(TerminateTypeEnum.IOS.getValue())) {
                return resultDto.makeResult(ToolResultEnum.TERMINATE_TYPE_ERROR.getValue(), ToolResultEnum.TERMINATE_TYPE_ERROR.getComment());
            }
            EntityWrapper<AppVersion> entity = new EntityWrapper<>();
            entity.eq("terminateType", requestDto.getTerminateType());
            entity.eq("version", requestDto.getVersion());
            entity.ne("status", VersionStatusEnum.UNSHELVE.getValue());
            AppVersion appVersionQuery = appVersionCrudService.selectOne(entity);
            if (null != appVersionQuery) {
                return resultDto.makeResult(ToolResultEnum.VERSION_EXISTS.getValue(), ToolResultEnum.VERSION_EXISTS.getComment());
            }
            AppVersion appVersion = modelMapper.map(requestDto, AppVersion.class);
            appVersion.setStatus(VersionStatusEnum.UNPUBLISH.getValue());
            ByteArrayOutputStream baos = QrCodeCreateUtil.createQrCode(requestDto.getDownloadUrl(), QrcodeConstant.QRCODE_SIZE, QrcodeConstant.QRCODE_FILE_EXTNAME);
            ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
            String fileName = StringUtils.getUUID();
            if (StringUtils.isNotEmpty(QrcodeConstant.QRCODE_FILE_EXTNAME)) {
                fileName = fileName + "." + QrcodeConstant.QRCODE_FILE_EXTNAME;
            }
            ObjectResultDto<OssFileUploadResultDto> objectResultDto = ossService.aliyunOssFileUpload(fileName, swapStream);
            if (objectResultDto.isFailed() || null == objectResultDto.getData()) {
                return resultDto.makeResult(ToolResultEnum.FILE_UPLOAD_ERR.getValue(), ToolResultEnum.FILE_UPLOAD_ERR.getComment());
            }
            appVersion.setCodeUrl(objectResultDto.getData().getFileUrl());
            appVersionCrudService.insert(appVersion);
            resultDto.success();
        } catch (Exception e) {
            log.error("版本添加失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 版本号更新
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto versionUpdate(AppVersionUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (null == requestDto.getId()) {
                return resultDto.makeResult(ToolResultEnum.REQUEST_PARAM_EMPTY.getValue(), ToolResultEnum.REQUEST_PARAM_EMPTY.getComment());
            }
            if (null == requestDto.getTerminateType()) {
                return resultDto.makeResult(ToolResultEnum.REQUEST_PARAM_EMPTY.getValue(), ToolResultEnum.REQUEST_PARAM_EMPTY.getComment());
            }
            if (!requestDto.getTerminateType().equals(TerminateTypeEnum.ANDROID.getValue()) && !requestDto.getTerminateType().equals(TerminateTypeEnum.IOS.getValue())) {
                return resultDto.makeResult(ToolResultEnum.TERMINATE_TYPE_ERROR.getValue(), ToolResultEnum.TERMINATE_TYPE_ERROR.getComment());
            }
            AppVersion versionQuery = appVersionCrudService.selectById(requestDto.getId());
            if (null == versionQuery) {
                return resultDto.makeResult(ToolResultEnum.VERSION_NOT_EXISTS.getValue(), ToolResultEnum.VERSION_NOT_EXISTS.getComment());
            }
            if (versionQuery.getStatus().equals(VersionStatusEnum.UNSHELVE.getValue())) {
                return resultDto.makeResult(ToolResultEnum.VERSION_UNSHELVE.getValue(), ToolResultEnum.VERSION_UNSHELVE.getComment());
            }
            if (StringUtils.isNotEmpty(requestDto.getVersion())) {
                EntityWrapper<AppVersion> entity = new EntityWrapper<>();
                entity.eq("terminateType", requestDto.getTerminateType());
                entity.eq("version", requestDto.getVersion());
                entity.ne("status", VersionStatusEnum.UNSHELVE.getValue());
                AppVersion appVersionQuery = appVersionCrudService.selectOne(entity);
                if (null != appVersionQuery) {
                    return resultDto.makeResult(ToolResultEnum.VERSION_EXISTS.getValue(), ToolResultEnum.VERSION_EXISTS.getComment());
                }
            }
            AppVersion appVersion = modelMapper.map(requestDto, AppVersion.class);
            if (StringUtils.isNotEmpty(requestDto.getDownloadUrl()) && !requestDto.getDownloadUrl().equals(versionQuery.getDownloadUrl())) {
                ByteArrayOutputStream baos = QrCodeCreateUtil.createQrCode(requestDto.getDownloadUrl(), QrcodeConstant.QRCODE_SIZE, QrcodeConstant.QRCODE_FILE_EXTNAME);
                ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
                String fileName = StringUtils.getUUID();
                if (StringUtils.isNotEmpty(QrcodeConstant.QRCODE_FILE_EXTNAME)) {
                    fileName = fileName + "." + QrcodeConstant.QRCODE_FILE_EXTNAME;
                }
                ObjectResultDto<OssFileUploadResultDto> objectResultDto = ossService.aliyunOssFileUpload(fileName, swapStream);
                if (objectResultDto.isFailed() || null == objectResultDto.getData()) {
                    return resultDto.makeResult(ToolResultEnum.FILE_UPLOAD_ERR.getValue(), ToolResultEnum.FILE_UPLOAD_ERR.getComment());
                }
                appVersion.setCodeUrl(objectResultDto.getData().getFileUrl());
            }
            appVersionCrudService.updateById(appVersion);
            resultDto.success();
        } catch (Exception e) {
            log.error("版本更新失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 版本发布
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto versionPublish(AppVersionPublishRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (null == requestDto.getId()) {
                return resultDto.makeResult(ToolResultEnum.REQUEST_PARAM_EMPTY.getValue(), ToolResultEnum.REQUEST_PARAM_EMPTY.getComment());
            }

            AppVersion appVersion = appVersionCrudService.selectById(requestDto.getId());
            if (null == appVersion) {
                return resultDto.makeResult(ToolResultEnum.VERSION_NOT_EXISTS.getValue(), ToolResultEnum.VERSION_NOT_EXISTS.getComment());
            }
            if (!appVersion.getStatus().equals(VersionStatusEnum.UNPUBLISH.getValue())) {
                return resultDto.makeResult(ToolResultEnum.VERSION_ERR.getValue(), ToolResultEnum.VERSION_ERR.getComment());
            }
            AppVersion version = new AppVersion();
            version.setStatus(VersionStatusEnum.PUBLISH.getValue());
            version.setPublishTime(new Date());
            EntityWrapper<AppVersion> entityWrapper = new EntityWrapper<AppVersion>();
            entityWrapper.eq("id", requestDto.getId());
            appVersionCrudService.update(version, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("版本发布失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 版本下架
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto versionWithdraw(AppVersionWithdrawRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (null == requestDto.getId()) {
                return resultDto.makeResult(ToolResultEnum.REQUEST_PARAM_EMPTY.getValue(), ToolResultEnum.REQUEST_PARAM_EMPTY.getComment());
            }
            AppVersion appVersion = appVersionCrudService.selectById(requestDto.getId());
            if (null == appVersion) {
                return resultDto.makeResult(ToolResultEnum.VERSION_NOT_EXISTS.getValue(), ToolResultEnum.VERSION_NOT_EXISTS.getComment());
            }
            if (!appVersion.getStatus().equals(VersionStatusEnum.PUBLISH.getValue())) {
                return resultDto.makeResult(ToolResultEnum.VERSION_ERR.getValue(), ToolResultEnum.VERSION_ERR.getComment());
            }
            AppVersion version = new AppVersion();
            version.setStatus(VersionStatusEnum.UNSHELVE.getValue());
            version.setWithdrawTime(new Date());
            EntityWrapper<AppVersion> entityWrapper = new EntityWrapper<AppVersion>();
            entityWrapper.eq("id", requestDto.getId());
            appVersionCrudService.update(version, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("版本发布失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取版本列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<AppVersionResultDto> getAppVersionList(AppVersionListGetRequestDto requestDto) {
        ListResultDto<AppVersionResultDto> listResultDto = new ListResultDto();
        try {
            EntityWrapper<AppVersion> entityWrapper = new EntityWrapper<>();
            if (null != requestDto.getTerminateType()) {
                entityWrapper.eq("terminateType", requestDto.getTerminateType());
            }
            entityWrapper.orderBy("version", false);
            List<AppVersion> list = appVersionCrudService.selectList(entityWrapper);
            if (null != list && list.size() > 0) {
                List<AppVersionResultDto> appointRuleResultDtoList = modelMapper.map(list, new TypeToken<List<AppVersionResultDto>>() {
                }.getType());
                listResultDto.setItems(appointRuleResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("版本列表获取失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 分页获取版本列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<AppVersionResultDto> getAppVersionPageList(AppVersionPageListGetRequestDto requestDto) {
        PagedResultDto<AppVersionResultDto> pagedResultDto = new PagedResultDto();
        try {

            EntityWrapper<AppVersion> entityWrapper = new EntityWrapper<>();
            if (null != requestDto.getTerminateType()) {
                entityWrapper.eq("terminateType", requestDto.getTerminateType());
            }
            entityWrapper.orderBy("version", false);
            Page<AppVersion> appointmentRulePage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<AppVersion> appointmentRulePageList = appVersionCrudService.selectPage(appointmentRulePage, entityWrapper);
            if (null != appointmentRulePageList) {

                List<AppVersionResultDto> appointRuleResultDtoList = modelMapper.map(appointmentRulePageList.getRecords(), new TypeToken<List<AppVersionResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(appointmentRulePageList.getCurrent());
                pagedResultDto.setPageSize(appointmentRulePageList.getSize());
                pagedResultDto.setTotalCount(appointmentRulePageList.getTotal());
                pagedResultDto.setItems(appointRuleResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取版本列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }
}
