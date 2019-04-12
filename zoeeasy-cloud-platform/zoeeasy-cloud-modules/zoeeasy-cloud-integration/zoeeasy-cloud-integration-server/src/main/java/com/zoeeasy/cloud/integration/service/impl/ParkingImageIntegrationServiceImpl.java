package com.zoeeasy.cloud.integration.service.impl;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.common.utils.ThreadUtils;
import com.scapegoat.infrastructure.common.utils.TimeUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.gather.hikvision.HikvisionParkingService;
import com.zoeeasy.cloud.gather.hikvision.dto.request.PassingImageFetchRequestDto;
import com.zoeeasy.cloud.gather.hikvision.dto.result.PassingImageFetchResultDto;
import com.zoeeasy.cloud.integration.parking.ParkingImageIntegrationService;
import com.zoeeasy.cloud.message.payload.HikPassingImageFetchPayload;
import com.zoeeasy.cloud.pms.image.ParkingImageService;
import com.zoeeasy.cloud.pms.image.dto.ParkingImageItemDto;
import com.zoeeasy.cloud.pms.image.dto.ParkingImageSaveRequestDto;
import com.zoeeasy.cloud.tool.oss.OssService;
import com.zoeeasy.cloud.tool.oss.dto.result.OssFileUploadResultDto;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 停车图像集成服务
 *
 * @author walkman
 * @since 2018/11/20
 */
@Service("parkingImageIntegrationService")
@Slf4j
public class ParkingImageIntegrationServiceImpl implements ParkingImageIntegrationService {

    @Autowired
    private OssService ossService;

    @Autowired
    private HikvisionParkingService hikvisionParkingService;

    @Autowired
    private ParkingImageService parkingImageService;

    /**
     * 处理海康过车图像消息
     *
     * @param payload
     * @return
     */
    @Override
    public ResultDto processHikPassingImage(HikPassingImageFetchPayload payload) {
        ResultDto resultDto = new ResultDto();
        try {
            List<ParkingImageItemDto> parkingImageItemDtos = this.fetchAndUploadImage(payload.getHikPassingUuid(), payload.getParkCode());
            if (CollectionUtils.isNotEmpty(parkingImageItemDtos)) {
                ParkingImageSaveRequestDto parkingImageSaveRequestDto = new ParkingImageSaveRequestDto();
                parkingImageSaveRequestDto.setTenantId(payload.getTenantId());
                parkingImageSaveRequestDto.setParkingId(payload.getParkingId());
                parkingImageSaveRequestDto.setPassingId(payload.getPassingId());
                parkingImageSaveRequestDto.setPassingNo(payload.getPassingNo());
                parkingImageSaveRequestDto.setImages(parkingImageItemDtos);
                //保存过车图像
                return this.parkingImageService.savePassingImages(parkingImageSaveRequestDto);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取并上传海康过车图像数据失败:", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 下载并上传图片
     *
     * @param hikPassingUuid hikPassingUuid
     * @param parkCode       parkCode
     * @return
     */
    private List<ParkingImageItemDto> fetchAndUploadImage(String hikPassingUuid, String parkCode) {
        List<ParkingImageItemDto> array = new ArrayList<>();
        if (StringUtils.isEmpty(hikPassingUuid) || StringUtils.isEmpty(parkCode)) {
            return array;
        }
        PassingImageFetchRequestDto passingImageFetchRequestDto = new PassingImageFetchRequestDto();
        passingImageFetchRequestDto.setHikPassingUuid(hikPassingUuid);
        passingImageFetchRequestDto.setParkCode(parkCode);
        //自定义重试3次
        int count = 0;
        while (true) {
            count++;
            ObjectResultDto<PassingImageFetchResultDto> fetchPassingImageResultDto = this.hikvisionParkingService.fetchPassingImage(passingImageFetchRequestDto);
            if (fetchPassingImageResultDto.isSuccess() && fetchPassingImageResultDto.getData() != null) {
                if (CollectionUtils.isNotEmpty(fetchPassingImageResultDto.getData().getImages())) {
                    List<String> images = fetchPassingImageResultDto.getData().getImages();
                    for (String image : images) {
                        if (StringUtils.isNotEmpty(image)) {
                            String fileName = StringUtils.getUUID();
                            //下载文件并上传
                            try {
                                byte[] data = Requests.get(image).send().readToBytes();
                                if (data != null) {
                                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
                                    ObjectResultDto<OssFileUploadResultDto> resultDto = ossService.aliyunOssFileUpload(fileName, byteArrayInputStream);
                                    if (resultDto.isSuccess() && resultDto.getData() != null) {
                                        ParkingImageItemDto imageItemDto = new ParkingImageItemDto();
                                        imageItemDto.setType("");
                                        imageItemDto.setUuid(fileName);
                                        imageItemDto.setUrl(resultDto.getData().getFileUrl());
                                        array.add(imageItemDto);
                                    }
                                }
                            } catch (Exception e) {
                                log.error("过车图像下载失败,异常信息{}", e.getMessage());
                            }
                        }
                    }
                }
                break;
            }
            if (count >= 3) {
                break;
            }
            ThreadUtils.sleep((2 * count - 1) & TimeUtils.MILLIS_OF_MINUTE);
        }
        return array;
    }

}
