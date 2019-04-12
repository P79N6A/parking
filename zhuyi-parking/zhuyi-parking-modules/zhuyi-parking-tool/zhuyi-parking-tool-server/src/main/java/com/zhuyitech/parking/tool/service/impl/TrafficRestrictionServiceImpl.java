package com.zhuyitech.parking.tool.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.base.Charsets;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.common.enums.PublicEnum;
import com.zhuyitech.parking.tool.bean.traffic.TrafficRestrictionCityBean;
import com.zhuyitech.parking.tool.bean.traffic.TrafficRestrictionInfoBean;
import com.zhuyitech.parking.tool.config.JuHeConfig;
import com.zhuyitech.parking.tool.domain.TrafficRestriction;
import com.zhuyitech.parking.tool.domain.TrafficRestrictionCity;
import com.zhuyitech.parking.tool.domain.TrafficRestrictionInfo;
import com.zhuyitech.parking.tool.dto.request.AdCodeRequestDto;
import com.zhuyitech.parking.tool.dto.request.region.RegionCityPingyinGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.trafficrestriction.TrafficLimitDeleteRequestDto;
import com.zhuyitech.parking.tool.dto.request.trafficrestriction.TrafficRestrictionCityGetDataRequestDto;
import com.zhuyitech.parking.tool.dto.request.trafficrestriction.TrafficRestrictionGetLocalRequestDto;
import com.zhuyitech.parking.tool.dto.request.trafficrestriction.TrafficRestrictionRequestDto;
import com.zhuyitech.parking.tool.dto.result.region.RegionCityPinyinGetResultDto;
import com.zhuyitech.parking.tool.dto.result.traffic.*;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.enums.TrafficRestrictionCarTypeEnum;
import com.zhuyitech.parking.tool.enums.TrafficRestrictionStatusEnum;
import com.zhuyitech.parking.tool.enums.TrafficRestrictionTypeEnum;
import com.zhuyitech.parking.tool.service.TrafficRestrictionCityCrudService;
import com.zhuyitech.parking.tool.service.TrafficRestrictionCrudService;
import com.zhuyitech.parking.tool.service.TrafficRestrictionInfoCrudService;
import com.zhuyitech.parking.tool.service.api.RegionService;
import com.zhuyitech.parking.tool.service.api.TrafficRestrictionCityService;
import com.zhuyitech.parking.tool.service.api.TrafficRestrictionService;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

/**
 * 尾号限行服务
 *
 * @author AkeemSuper
 * @date 2018/4/13 0013
 */
@Service("trafficRestrictionService")
@Slf4j
public class TrafficRestrictionServiceImpl implements TrafficRestrictionService {

    @Autowired
    private TrafficRestrictionInfoCrudService trafficRestrictionInfoCrudService;

    @Autowired
    private TrafficRestrictionCrudService trafficRestrictionCrudService;

    @Autowired
    private TrafficRestrictionCityCrudService trafficRestrictionCityCrudService;

    @Autowired
    private TrafficRestrictionCityService trafficRestrictionCityService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JuHeConfig juHeConfig;


    /**
     * 查询支持限行的城市
     *
     * @return TrafficRestrictionResultDto
     */
    @Override
    public ObjectResultDto<TrafficRestrictionCityBeanResultDto> queryTrafficRestrictionCity() {
        ObjectResultDto<TrafficRestrictionCityBeanResultDto> resultDto = new ObjectResultDto<>();
        try {
            String url = juHeConfig.getCityUrl();
            Map<String, Object> params = new HashMap();
            params.put("key", juHeConfig.getAppkey());
            String response = Requests.get(url).body(params).charset(Charsets.UTF_8).send().readToText();
            JSONObject jsonObject = JSONObject.parseObject(response);
            if (jsonObject.getInteger("error_code") != 0) {
                return resultDto.makeResult(ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY_BY_JUHE.getValue(), ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY_BY_JUHE.getComment());
            }
            TrafficRestrictionCityBeanResultDto trafficRestrictionCityResultDto = JSON.parseObject(response, TrafficRestrictionCityBeanResultDto.class);
            resultDto.setData(trafficRestrictionCityResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("查询支持限行的城市失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 调三方查询指定城市的限行情况
     *
     * @param requestDto TrafficRestrictionRequestDto
     * @return TrafficRestrictionResultDto
     */
    @Override
    public ObjectResultDto<TrafficRestrictionInfoViewResultDto> requestTrafficRestrictionInfo(TrafficRestrictionRequestDto requestDto) {
        ObjectResultDto<TrafficRestrictionInfoViewResultDto> resultDto = new ObjectResultDto<>();
        try {
            if (StringUtils.isBlank(requestDto.getCity())) {
                return resultDto.makeResult(ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY.getValue(), ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY.getComment());
            }
            String url = juHeConfig.getUrl();
            Map<String, Object> params = new HashMap<>();
            params.put("key", juHeConfig.getAppkey());
            params.put("city", requestDto.getCity());
            params.put("type", requestDto.getType());
            String response = Requests.get(url).body(params).charset(Charsets.UTF_8).send().readToText();
            JSONObject json = JSONObject.parseObject(response);
            if (json.getInteger("error_code") != 0) {
                return resultDto.makeResult(ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY_BY_JUHE.getValue(), ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY_BY_JUHE.getComment());
            }
            TrafficRestrictionInfoViewResultDto trafficRestrictionInfoViewResultDto = json.getObject("result", TrafficRestrictionInfoViewResultDto.class);
            resultDto.setData(trafficRestrictionInfoViewResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("调三方查询指定城市的限行情况失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 将支持限行的城市同步到数据库中
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto syncTrafficRestrictionCity() {
        ResultDto resultDto = new ResultDto();
        try {
            //获取所有支持限行的城市
            ObjectResultDto<TrafficRestrictionCityBeanResultDto> restrictionCity = queryTrafficRestrictionCity();
            if (restrictionCity.isFailed() || null == restrictionCity.getData()) {
                return resultDto.makeResult(ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY.getValue(), ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY.getComment());
            }
            List<TrafficRestrictionCityBean> trafficRestrictionCities = restrictionCity.getData().getResult();
            for (TrafficRestrictionCityBean trafficRestrictionCity : trafficRestrictionCities) {
                String cityName = "%" + trafficRestrictionCity.getCityname() + "%";
                EntityWrapper<TrafficRestrictionCity> entityWrapper = new EntityWrapper<>();
                entityWrapper.like("cityName", cityName);
                List<TrafficRestrictionCity> list = trafficRestrictionCityCrudService.selectList(entityWrapper);
                if (null == list || list.isEmpty()) {
                    return resultDto.makeResult(ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY.getValue(), ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY.getComment());
                }
                for (TrafficRestrictionCity city : list) {
                    city.setPinyinName(trafficRestrictionCity.getCity());
                    city.setTrafficRestriction(true);
                    trafficRestrictionCityCrudService.updateById(city);
                }
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("将支持限行的城市同步到数据库中失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 同步所有支持限城市的限行信息
     *
     * @return ResultDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto syncTrafficRestrictionInfo() {
        ResultDto resultDto = new ResultDto();
        try {
            //获取所有支持限行的城市
            EntityWrapper<TrafficRestrictionCity> cityEntityWrapper = new EntityWrapper<>();
            cityEntityWrapper.eq("trafficRestriction", PublicEnum.YES.getValue());
            List<TrafficRestrictionCity> trafficRestrictionCities = trafficRestrictionCityCrudService.selectList(cityEntityWrapper);
            if (trafficRestrictionCities.isEmpty()) {
                return resultDto.makeResult(ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY.getValue(), ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY.getComment());
            }
            for (TrafficRestrictionCity trafficRestrictionCity : trafficRestrictionCities) {
                String pinyinName = trafficRestrictionCity.getPinyinName();
                // insert当天的
                TrafficRestrictionRequestDto todayRequestDto = new TrafficRestrictionRequestDto();
                todayRequestDto.setCity(pinyinName);
                todayRequestDto.setType(TrafficRestrictionTypeEnum.TODAY.getValue());
                resultDto = insertTrafficRestriction(todayRequestDto);
                if (resultDto.isFailed()) {
                    return resultDto;
                }
                //insert明天的
                TrafficRestrictionRequestDto tomorrowRequestDto = new TrafficRestrictionRequestDto();
                tomorrowRequestDto.setCity(pinyinName);
                tomorrowRequestDto.setType(TrafficRestrictionTypeEnum.TOMORROW.getValue());
                resultDto = insertTrafficRestriction(tomorrowRequestDto);
                if (resultDto.isFailed()) {
                    return resultDto;
                }
                //insert后天的
                TrafficRestrictionRequestDto thirdRequestDto = new TrafficRestrictionRequestDto();
                thirdRequestDto.setCity(pinyinName);
                thirdRequestDto.setType(TrafficRestrictionTypeEnum.THIRD_DAY.getValue());
                resultDto = insertTrafficRestriction(thirdRequestDto);
                if (resultDto.isFailed()) {
                    return resultDto;
                }
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("同步所有支持限城市的限行信息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 插入限号记录
     *
     * @param restrictionRequestDto TrafficRestrictionRequestDto
     * @return ResultDto
     * @throws ParseException e
     */
    public ResultDto insertTrafficRestriction(TrafficRestrictionRequestDto restrictionRequestDto) throws ParseException {
        ResultDto resultDto = new ResultDto();
        ObjectResultDto<TrafficRestrictionInfoViewResultDto> trafficRestrictionInfoResultDto = requestTrafficRestrictionInfo(restrictionRequestDto);
        if (trafficRestrictionInfoResultDto.isFailed() || null == trafficRestrictionInfoResultDto.getData()) {
            return resultDto.makeResult(ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY_BY_JUHE.getValue(), ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY_BY_JUHE.getComment());
        }
        TrafficRestrictionInfoViewResultDto infoViewResultDto = trafficRestrictionInfoResultDto.getData();
        TrafficRestriction trafficRestriction = packetTrafficRestriction(infoViewResultDto);
        trafficRestrictionCrudService.insert(trafficRestriction);
        List<TrafficRestrictionInfoBean> tomorrowDes = infoViewResultDto.getDes();
        insertTrafficRestrictionInfo(trafficRestriction.getId(), tomorrowDes);
        resultDto.success();
        return resultDto;
    }

    /**
     * 同步限行详细信息
     *
     * @param trafficRestrictionId trafficRestrictionId
     * @param todayDes             todayDes
     */
    private void insertTrafficRestrictionInfo(Long trafficRestrictionId, List<TrafficRestrictionInfoBean> todayDes) {
        for (TrafficRestrictionInfoBean restrictionInfoBean : todayDes) {
            TrafficRestrictionInfo trafficRestrictionInfo = modelMapper.map(restrictionInfoBean, TrafficRestrictionInfo.class);
            trafficRestrictionInfo.setTrafficRestrictionId(trafficRestrictionId);
            trafficRestrictionInfoCrudService.insert(trafficRestrictionInfo);
        }
    }

    /**
     * 获取用户当前所在城市的限行信息
     *
     * @param requestDto requestDto
     * @return TrafficRestrictionInfoGetResultDto
     */
    @Override
    public ObjectResultDto<TrafficRestrictionPolicyInfoGetResultDto> getTrafficRestrictionInfo(AdCodeRequestDto requestDto) {
        ObjectResultDto<TrafficRestrictionPolicyInfoGetResultDto> resultDto = new ObjectResultDto<>();
        TrafficRestrictionPolicyInfoGetResultDto getResultDto = new TrafficRestrictionPolicyInfoGetResultDto();
        try {
            if (StringUtils.isEmpty(requestDto.getAdCode())) {
                return resultDto.makeResult(ToolResultEnum.ADCODE_EMPTY.getValue(), ToolResultEnum.ADCODE_EMPTY.getComment());
            }
            getResultDto.setDate(DateUtils.now());
            getResultDto.setWeek(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINA));
            RegionCityPingyinGetRequestDto regionCityPingyinGetRequestDto = new RegionCityPingyinGetRequestDto();
            regionCityPingyinGetRequestDto.setAdcode(requestDto.getAdCode());
            RegionCityPinyinGetResultDto regionCityPinyinGetResultDto = regionService.getRegionCityPingyin(regionCityPingyinGetRequestDto).getData();
            if (null == regionCityPinyinGetResultDto) {
                getResultDto.setTailNumber(TrafficRestrictionStatusEnum.UN_KNOW.getComment());
                getResultDto.setTrafficRestriction(TrafficRestrictionStatusEnum.UN_KNOW.getValue());
                resultDto.setData(getResultDto);
                resultDto.success();
                return resultDto;
            }
            getResultDto.setCityName(regionCityPinyinGetResultDto.getName());
            String cityAdCode = regionCityPinyinGetResultDto.getAdCode();
            EntityWrapper<TrafficRestrictionCity> cityEntityWrapper = new EntityWrapper<>();
            cityEntityWrapper.eq("adCode", cityAdCode);
            cityEntityWrapper.eq("trafficRestriction", PublicEnum.YES.getValue());
            TrafficRestrictionCity trafficRestrictionCity = trafficRestrictionCityCrudService.selectOne(cityEntityWrapper);
            if (null == trafficRestrictionCity) {
                getResultDto.setTailNumber(TrafficRestrictionStatusEnum.UN_KNOW.getComment());
                getResultDto.setTrafficRestriction(TrafficRestrictionStatusEnum.UN_KNOW.getValue());
                resultDto.setData(getResultDto);
                resultDto.success();
                return resultDto;
            }
            TrafficRestrictionCityGetDataRequestDto cityGetRequestDto = new TrafficRestrictionCityGetDataRequestDto();
            cityGetRequestDto.setId(trafficRestrictionCity.getId());
            cityGetRequestDto.setCarType(TrafficRestrictionCarTypeEnum.LOCAL_CAR.getValue());
            cityGetRequestDto.setDate(DateUtils.now());
            ObjectResultDto<TrafficRestrictionCityGetResultDto> info = trafficRestrictionCityService.getInfo(cityGetRequestDto);
            if (info.isFailed() || null == info.getData()) {
                getResultDto.setTailNumber(TrafficRestrictionStatusEnum.UN_KNOW.getComment());
                getResultDto.setTrafficRestriction(TrafficRestrictionStatusEnum.UN_KNOW.getValue());
                resultDto.setData(getResultDto);
                resultDto.success();
                return resultDto;
            }
            TrafficRestrictionCityGetResultDto data = info.getData();
            getResultDto.setTrafficRestriction(data.getTrafficRestriction());
            getResultDto.setTailNumber(data.getRestrictionTailNumber());
            getResultDto.setPolicies(data.getTrafficRestrictionPolicys());
            resultDto.setData(getResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("获取用户当前所在城市的限行信息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 从本地获取指定城市的限行记录
     *
     * @param requestDto TrafficRestrictionGetLocalRequestDto
     */
    private ObjectResultDto<TrafficRestrictionInfoGetResultDto> requestTrafficRestrictionInfoByLocal(TrafficRestrictionGetLocalRequestDto requestDto) {
        ObjectResultDto<TrafficRestrictionInfoGetResultDto> resultDto = new ObjectResultDto<>();
        try {
            if (StringUtils.isBlank(requestDto.getCity())) {
                return resultDto.makeResult(ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY.getValue(), ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY.getComment());
            }
            TrafficRestriction trafficRestriction = trafficRestrictionCrudService.findOneTrafficRestriction(requestDto.getCity(), DateUtils.getDate("yyyy-MM-dd"));
            if (null == trafficRestriction) {
                return resultDto.makeResult(ToolResultEnum.TRAFFIC_RESTRICTION_GET_BY_LOCAL.getValue(), ToolResultEnum.TRAFFIC_RESTRICTION_GET_BY_LOCAL.getComment());
            }
            TrafficRestrictionInfoGetResultDto getResultDto = modelMapper.map(trafficRestriction, TrafficRestrictionInfoGetResultDto.class);
            //限号详情
            EntityWrapper<TrafficRestrictionInfo> trafficRestrictionInfoWrapper = new EntityWrapper<>();
            trafficRestrictionInfoWrapper.eq("trafficRestrictionId", trafficRestriction.getId());
            trafficRestrictionInfoWrapper.orderBy("creationTime", false);
            List<TrafficRestrictionInfo> trafficRestrictionInfos = trafficRestrictionInfoCrudService.selectList(trafficRestrictionInfoWrapper);
            if (!trafficRestrictionInfos.isEmpty()) {
                List<TrafficRestrictionInfoBean> trafficRestrictionInfoBeans = modelMapper.map(trafficRestrictionInfos, new TypeToken<List<TrafficRestrictionInfoBean>>() {
                }.getType());
                getResultDto.setTrafficRestrictionInfo(trafficRestrictionInfoBeans);
            }
            resultDto.setData(getResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("从本地过去指定城市指定日期的限行记录失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取当前城市当天的限行尾号
     *
     * @param requestDto TrafficRestrictionGetRequestDto
     * @return TrafficRestrictionInfoForIndexViewResultDto
     */
    @Override
    public ObjectResultDto<TrafficRestrictionViewResultDto> getTrafficRestrictionInfoForIndex(AdCodeRequestDto requestDto) {
        ObjectResultDto<TrafficRestrictionViewResultDto> resultDto = new ObjectResultDto<>();
        TrafficRestrictionViewResultDto trafficRestrictionViewResultDto = new TrafficRestrictionViewResultDto();
        try {
            if (StringUtils.isEmpty(requestDto.getAdCode())) {
                return resultDto.makeResult(ToolResultEnum.ADCODE_EMPTY.getValue(), ToolResultEnum.ADCODE_EMPTY.getComment());
            }
            RegionCityPingyinGetRequestDto regionCityPingyinGetRequestDto = new RegionCityPingyinGetRequestDto();
            regionCityPingyinGetRequestDto.setAdcode(requestDto.getAdCode());
            ObjectResultDto<RegionCityPinyinGetResultDto> regionCityPinyin = regionService.getRegionCityPingyin(regionCityPingyinGetRequestDto);
            if (regionCityPinyin.isFailed() || null == regionCityPinyin.getData()) {
                trafficRestrictionViewResultDto.setTailNumber(TrafficRestrictionStatusEnum.UN_KNOW.getComment());
                trafficRestrictionViewResultDto.setCityName("未知");
                trafficRestrictionViewResultDto.setTrafficRestriction(TrafficRestrictionStatusEnum.UN_KNOW.getValue());
                trafficRestrictionViewResultDto.setTomorrowTrafficRestriction(TrafficRestrictionStatusEnum.UN_KNOW.getValue());
                resultDto.setData(trafficRestrictionViewResultDto);
                resultDto.success();
                return resultDto;
            }
            trafficRestrictionViewResultDto.setCityName(regionCityPinyin.getData().getName());
            EntityWrapper<TrafficRestrictionCity> cityEntityWrapper = new EntityWrapper<>();
            cityEntityWrapper.eq("adCode", regionCityPinyin.getData().getAdCode());
            TrafficRestrictionCity trafficRestrictionCity = trafficRestrictionCityCrudService.selectOne(cityEntityWrapper);
            if (null == trafficRestrictionCity) {
                trafficRestrictionViewResultDto.setTailNumber(TrafficRestrictionStatusEnum.UN_KNOW.getComment());
                trafficRestrictionViewResultDto.setTomorrowTailNumber(TrafficRestrictionStatusEnum.UN_KNOW.getComment());
                trafficRestrictionViewResultDto.setTrafficRestriction(TrafficRestrictionStatusEnum.UN_KNOW.getValue());
                trafficRestrictionViewResultDto.setTomorrowTrafficRestriction(TrafficRestrictionStatusEnum.UN_KNOW.getValue());
                resultDto.setData(trafficRestrictionViewResultDto);
                resultDto.success();
                return resultDto;
            }
            if (!trafficRestrictionCity.isTrafficRestriction()) {
                trafficRestrictionViewResultDto.setTailNumber(TrafficRestrictionStatusEnum.UN_KNOW.getComment());
                trafficRestrictionViewResultDto.setTomorrowTailNumber(TrafficRestrictionStatusEnum.UN_KNOW.getComment());
                trafficRestrictionViewResultDto.setTrafficRestriction(TrafficRestrictionStatusEnum.UN_KNOW.getValue());
                trafficRestrictionViewResultDto.setTomorrowTrafficRestriction(TrafficRestrictionStatusEnum.UN_KNOW.getValue());
                resultDto.setData(trafficRestrictionViewResultDto);
                resultDto.success();
                return resultDto;
            }
            TrafficRestriction trafficRestriction = trafficRestrictionCrudService.findOneTrafficRestriction(trafficRestrictionCity.getPinyinName(), DateUtils.getDate("yyyy-MM-dd"));
            if (null == trafficRestriction) {
                trafficRestrictionViewResultDto.setTailNumber(TrafficRestrictionStatusEnum.UN_KNOW.getComment());
                trafficRestrictionViewResultDto.setTrafficRestriction(TrafficRestrictionStatusEnum.UN_KNOW.getValue());
            } else if (null != trafficRestriction && null != trafficRestriction.getTailNumber()) {
                trafficRestrictionViewResultDto.setTailNumber(trafficRestriction.getTailNumber());
                trafficRestrictionViewResultDto.setTrafficRestriction(TrafficRestrictionStatusEnum.TRAFFIC_LIMIT.getValue());
            } else {
                trafficRestrictionViewResultDto.setTailNumber(TrafficRestrictionStatusEnum.UN_TRAFFIC_LIMIT.getComment());
                trafficRestrictionViewResultDto.setTrafficRestriction(TrafficRestrictionStatusEnum.UN_TRAFFIC_LIMIT.getValue());
            }
            //获取明日限行尾号
            String tomorrowDate = DateUtils.formatDate(DateUtils.addDays(new Date(), 1), "yyyy-MM-dd");
            TrafficRestriction tomorrowTrafficRestriction = trafficRestrictionCrudService.findOneTrafficRestriction(trafficRestrictionCity.getPinyinName(), tomorrowDate);
            if (null == tomorrowTrafficRestriction) {
                trafficRestrictionViewResultDto.setTomorrowTailNumber(TrafficRestrictionStatusEnum.UN_KNOW.getComment());
                trafficRestrictionViewResultDto.setTomorrowTrafficRestriction(TrafficRestrictionStatusEnum.UN_KNOW.getValue());
            } else if (null != tomorrowTrafficRestriction && null != tomorrowTrafficRestriction.getTailNumber()) {
                trafficRestrictionViewResultDto.setTomorrowTailNumber(tomorrowTrafficRestriction.getTailNumber());
                trafficRestrictionViewResultDto.setTomorrowTrafficRestriction(TrafficRestrictionStatusEnum.TRAFFIC_LIMIT.getValue());
            } else {
                trafficRestrictionViewResultDto.setTomorrowTailNumber(TrafficRestrictionStatusEnum.UN_TRAFFIC_LIMIT.getComment());
                trafficRestrictionViewResultDto.setTomorrowTrafficRestriction(TrafficRestrictionStatusEnum.UN_TRAFFIC_LIMIT.getValue());
            }
            resultDto.setData(trafficRestrictionViewResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("从本地过去指定城市指定日期的限行记录失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 定时删除限行记录
     */
    @Override
    public ResultDto deletedTrafficLimit(TrafficLimitDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Date date = DateUtils.sub(DateUtils.now(), 7);
            //删除限号记录
            trafficRestrictionCrudService.deletedTrafficLimit(date);
            //删除限行详情
            trafficRestrictionInfoCrudService.deleteTrafficInfo(date);
            resultDto.success();
        } catch (Exception e) {
            log.error("从本地过去指定城市指定日期的限行记录失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 封装限行数据
     *
     * @param infoViewResultDto TrafficRestrictionInfoViewResultDto
     * @return TrafficRestriction
     * @throws ParseException ParseException
     */
    private TrafficRestriction packetTrafficRestriction(TrafficRestrictionInfoViewResultDto infoViewResultDto) throws ParseException {
        TrafficRestriction trafficRestriction = new TrafficRestriction();
        trafficRestriction.setCity(infoViewResultDto.getCity());
        trafficRestriction.setCityName(infoViewResultDto.getCityname());
        trafficRestriction.setFine(infoViewResultDto.getFine());
        trafficRestriction.setHoliday(infoViewResultDto.getHoliday());
        trafficRestriction.setRemarks(infoViewResultDto.getRemarks());
        trafficRestriction.setTailNumber(StringUtils.join(infoViewResultDto.getXxweihao(), ","));
        trafficRestriction.setTrafficRestriction(1 == infoViewResultDto.getIsxianxing() ? true : false);
        trafficRestriction.setWeek(infoViewResultDto.getWeek());
        String date = infoViewResultDto.getDate();
        Date formatDate = DateUtils.parseDate(date);
        trafficRestriction.setDate(formatDate);
        return trafficRestriction;
    }
}
