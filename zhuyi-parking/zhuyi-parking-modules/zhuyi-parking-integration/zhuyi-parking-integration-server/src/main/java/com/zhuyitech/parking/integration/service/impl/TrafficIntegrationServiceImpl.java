package com.zhuyitech.parking.integration.service.impl;

import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.integration.service.api.TrafficIntegrationService;
import com.zhuyitech.parking.tool.dto.request.trafficrestriction.PushTrafficLimitRequestDto;
import com.zhuyitech.parking.tool.dto.request.trafficrestriction.TrafficRestrictionRemindRequestDto;
import com.zhuyitech.parking.tool.dto.result.traffic.TrafficRestrictionRemindResultDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("trafficIntegrationService")
@Slf4j
public class TrafficIntegrationServiceImpl implements TrafficIntegrationService {

    /**
     * 尾号限行提醒
     *
     * @param requestDto requestDto
     * @return PagedResultDto
     */
    @Override
    public PagedResultDto<TrafficRestrictionRemindResultDto> trafficRestrictionTailNumberRemind(TrafficRestrictionRemindRequestDto requestDto) {
        PagedResultDto<TrafficRestrictionRemindResultDto> resultDto = new PagedResultDto<>();
        try {

//            if (StringUtils.isEmpty(requestDto.getCityPinyin())) {
//                return resultDto.makeResult(ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY.getValue(), ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY.getComment());
//            }
//            EntityWrapper<TrafficRestrictionCity> cityEntityWrapper = new EntityWrapper<>();
//            cityEntityWrapper.eq("trafficRestriction", PublicEnum.YES.getValue());
//            cityEntityWrapper.eq("pinyinName", requestDto.getCityPinyin());
//            TrafficRestrictionCity trafficRestrictionCity = trafficRestrictionCityCrudService.selectOne(cityEntityWrapper);
//            if (null == trafficRestrictionCity) {
//                return resultDto.makeResult(ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY.getValue(), ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY.getComment());
//            }
//            TrafficRestriction trafficRestriction = trafficRestrictionCrudService.findOneTrafficRestriction(requestDto.getCityPinyin(), DateUtils.formatDate(DateUtils.addDays(DateUtils.now(), 1), "yyyy-MM-dd"));
//            if (null == trafficRestriction || !trafficRestriction.getTrafficRestriction() || StringUtils.isEmpty(trafficRestriction.getTailNumber()) || StringUtils.isEmpty(trafficRestrictionCity.getCityPrefix())) {
//                resultDto.success();
//                return resultDto;
//            }
//            UserCarInfoGetByTailNumberRequestDto userCarRequest = new UserCarInfoGetByTailNumberRequestDto();
//            userCarRequest.setCityPrefix(trafficRestrictionCity.getCityPrefix());
//            userCarRequest.setTailNumber(trafficRestriction.getTailNumber());
//            userCarRequest.setLimitPattern(trafficRestrictionCity.getLimitPattern());
//            userCarRequest.setPageNo(requestDto.getPageNo());
//            userCarRequest.setPageSize(requestDto.getPageSize());
//            PagedResultDto<TrafficRestrictionCarInfoResultDto> forTrafficRestriction = userCarInfoService.getForTrafficRestriction(userCarRequest);
//            if (forTrafficRestriction.isSuccess() && !forTrafficRestriction.getItems().isEmpty()) {
//                List<TrafficRestrictionCarInfoResultDto> items = forTrafficRestriction.getItems();
//                List<TrafficRestrictionRemindResultDto> resultDtoList = modelMapper.map(items, new TypeToken<List<TrafficRestrictionRemindResultDto>>() {
//                }.getType());
//                resultDto.setPageNo(forTrafficRestriction.getPageNo());
//                resultDto.setPageSize(forTrafficRestriction.getPageSize());
//                resultDto.setTotalCount(forTrafficRestriction.getTotalCount());
//                resultDto.setItems(resultDtoList);
//            }
            resultDto.success();
        } catch (Exception e) {
            log.error("限行提醒失败" + e.getMessage());
            return resultDto;
        }
        return resultDto;
    }

    /**
     * 尾号限行提醒推送
     */
    @Override
    public ResultDto pushTrafficLimit(PushTrafficLimitRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
//            if (StringUtils.isEmpty(requestDto.getCityPinyin())) {
//                return resultDto.makeResult(ToolResultEnum.REGION_CITY_NAME_EMPTY.getValue(), ToolResultEnum.REGION_CITY_NAME_EMPTY.getComment());
//            }
//            TrafficRestrictionRemindRequestDto remindRequestDto = new TrafficRestrictionRemindRequestDto();
//            PagedResultDto<TrafficRestrictionRemindResultDto> pagedResultDto;
//            Integer totalPage = 1;
//            do {
//                remindRequestDto.setPageNo(totalPage);
//                remindRequestDto.setPageSize(200);
//                remindRequestDto.setCityPinyin(requestDto.getCityPinyin());
//                pagedResultDto = trafficRestrictionTailNumberRemind(remindRequestDto);
//                if (pagedResultDto.getItems().isEmpty() || 0 == pagedResultDto.getTotalCount()) {
//                    log.info("第" + totalPage + "页的限行号牌为空");
//                    break;
//                }
//                List<PushSingleUserMessageRequestDto> request = new ArrayList<>();
//                for (TrafficRestrictionRemindResultDto remindResultDto : pagedResultDto.getItems()) {
//                    PushSingleUserMessageRequestDto pushRequestDto = new PushSingleUserMessageRequestDto();
//                    pushRequestDto.setUserId(remindResultDto.getUserId());
//                    Map<Object, Object> mapMsg = new HashMap<>();
//                    mapMsg.put("plateNumber", SensitiveInfoUtils.plateNumber(remindResultDto.getFullPlateNumber()));
//                    pushRequestDto.setParameters(mapMsg);
//                    request.add(pushRequestDto);
//                }
//                BatchPushMessageRequestDto batchPushMessageRequestDto = new BatchPushMessageRequestDto();
//                batchPushMessageRequestDto.setParams(request);
//                //批量推送
//                pushNotificationService.pushTrafficLimitHintNotification(batchPushMessageRequestDto);
//                totalPage++;
//            }
//            while (totalPage <= Math.ceil(Double.valueOf(pagedResultDto.getTotalCount()) / Double.valueOf(pagedResultDto.getPageSize())));
            resultDto.success();
        } catch (Exception e) {
            log.error("尾号限行提醒推送失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

}
