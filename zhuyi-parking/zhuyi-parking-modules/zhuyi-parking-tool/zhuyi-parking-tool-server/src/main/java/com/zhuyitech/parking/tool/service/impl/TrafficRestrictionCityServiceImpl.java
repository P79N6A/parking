package com.zhuyitech.parking.tool.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.common.constant.Const;
import com.zhuyitech.parking.tool.domain.TrafficRestriction;
import com.zhuyitech.parking.tool.domain.TrafficRestrictionCity;
import com.zhuyitech.parking.tool.domain.TrafficRestrictionPolicy;
import com.zhuyitech.parking.tool.dto.request.region.RegionCityPingyinGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.region.RegionProvinceAdCodeGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.trafficrestriction.*;
import com.zhuyitech.parking.tool.dto.request.weather.WeatherGetByDateRequestDto;
import com.zhuyitech.parking.tool.dto.result.region.RegionCityPinyinGetResultDto;
import com.zhuyitech.parking.tool.dto.result.region.RegionProvinceNameGetResultDto;
import com.zhuyitech.parking.tool.dto.result.traffic.*;
import com.zhuyitech.parking.tool.dto.result.weather.WeatherResultDto;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.enums.TrafficRestrictionCarTypeEnum;
import com.zhuyitech.parking.tool.enums.TrafficRestrictionCityCarTypeEnum;
import com.zhuyitech.parking.tool.enums.TrafficRestrictionStatusEnum;
import com.zhuyitech.parking.tool.service.TrafficRestrictionCityCrudService;
import com.zhuyitech.parking.tool.service.TrafficRestrictionCrudService;
import com.zhuyitech.parking.tool.service.TrafficRestrictionPolicyCrudService;
import com.zhuyitech.parking.tool.service.api.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 限行城市服务
 *
 * @author AkeemSuper
 * @date 2018/5/18 0018
 */
@Service("trafficRestrictionCityService")
public class TrafficRestrictionCityServiceImpl implements TrafficRestrictionCityService {

    private static final Logger LOG = LoggerFactory.getLogger(TrafficRestrictionCityServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private TrafficRestrictionCityCrudService trafficRestrictionCityCrudService;

    @Autowired
    private TrafficRestrictionCrudService trafficRestrictionCrudService;

    @Autowired
    private TrafficRestrictionPolicyCrudService trafficRestrictionPolicyCrudService;

    /**
     * 添加限行城市
     *
     * @param requestDto TrafficRestrictionCityAddRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto add(TrafficRestrictionCityAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (StringUtils.isEmpty(requestDto.getCityName())) {
                return resultDto.makeResult(ToolResultEnum.REGION_CITY_NAME_EMPTY.getValue(), ToolResultEnum.REGION_CITY_NAME_EMPTY.getComment());
            }
            TrafficRestrictionCity trafficRestrictionCity = modelMapper.map(requestDto, TrafficRestrictionCity.class);
            RegionProvinceAdCodeGetRequestDto regionProvinceAdCodeGetRequestDto = new RegionProvinceAdCodeGetRequestDto();
            regionProvinceAdCodeGetRequestDto.setCityName(requestDto.getCityName());
            ObjectResultDto<RegionProvinceNameGetResultDto> regionAdcode = regionService.getRegionAdcode(regionProvinceAdCodeGetRequestDto);
            if (regionAdcode.isFailed() || null == regionAdcode.getData()) {
                return resultDto.makeResult(ToolResultEnum.GET_ADCODE_ERR.getValue(), ToolResultEnum.GET_ADCODE_ERR.getComment());
            }
            trafficRestrictionCity.setAdCode(regionAdcode.getData().getAdCode());
            boolean insert = trafficRestrictionCityCrudService.insert(trafficRestrictionCity);
            if (!insert) {
                resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            LOG.error("添加限行城市失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 添加限行政策
     */
    @Override
    public ResultDto addPolicy(TrafficRestrictionPolicyAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (null == requestDto.getCarType() || null == requestDto.getCityId()) {
                return resultDto.makeResult(ToolResultEnum.REQUEST_PARAM_EMPTY.getValue(), ToolResultEnum.REQUEST_PARAM_EMPTY.getComment());
            }
            TrafficRestrictionPolicy map = modelMapper.map(requestDto, TrafficRestrictionPolicy.class);
            boolean insert = trafficRestrictionPolicyCrudService.insert(map);
            if (!insert) {
                resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            LOG.error("添加限行政策失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除限行政策
     */
    @Override
    public ResultDto deletedPolicy(TrafficRestrictionPolicyDeletedRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (null == requestDto.getId()) {
                return resultDto.makeResult(ToolResultEnum.REQUEST_PARAM_EMPTY.getValue(), ToolResultEnum.REQUEST_PARAM_EMPTY.getComment());
            }
            boolean delete = trafficRestrictionPolicyCrudService.deleteById(requestDto.getId());
            if (!delete) {
                resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            LOG.error("删除限行政策失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除限行城市
     */
    @Override
    public ResultDto deletedCity(TrafficRestrictionCityDeletedRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (null == requestDto.getId()) {
                return resultDto.makeResult(ToolResultEnum.REQUEST_PARAM_EMPTY.getValue(), ToolResultEnum.REQUEST_PARAM_EMPTY.getComment());
            }
            EntityWrapper<TrafficRestrictionPolicy> policyEntityWrapper = new EntityWrapper<>();
            policyEntityWrapper.eq("cityId", requestDto.getId());
            List<TrafficRestrictionPolicy> restrictionPolicies = trafficRestrictionPolicyCrudService.selectList(policyEntityWrapper);
            if (!restrictionPolicies.isEmpty()) {
                return resultDto.makeResult(ToolResultEnum.TRAFFIC_RESTRICTION_CITY_HAS_POLICY.getValue(), ToolResultEnum.TRAFFIC_RESTRICTION_CITY_HAS_POLICY.getComment());
            }
            boolean delete = trafficRestrictionCityCrudService.deleteById(requestDto.getId());
            if (!delete) {
                return resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            LOG.error("删除限行城市失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取限行的城市
     *
     * @param requestDto TrafficRestrictionCityGetListRequestDto
     * @return TrafficRestrictionCityListResultDto
     */
    @Override
    public ListResultDto<TrafficRestrictionCityViewListResultDto> list(TrafficCityGetListByAdCodeRequestDto requestDto) {
        ListResultDto<TrafficRestrictionCityViewListResultDto> resultDto = new ListResultDto<>();
        try {
            EntityWrapper<TrafficRestrictionCity> entityWrapper = new EntityWrapper<>();
            if (StringUtils.isEmpty(requestDto.getCarType())) {
                requestDto.setCarType(Integer.toString(TrafficRestrictionCityCarTypeEnum.CAR.getValue()));
            }
            if (!StringUtils.isEmpty(requestDto.getCityName())) {
                entityWrapper.like("cityName", requestDto.getCityName());
            }
            if (!StringUtils.isEmpty(requestDto.getInitial())) {
                entityWrapper.like("initial", requestDto.getInitial());
            }
            entityWrapper.like("carType", requestDto.getCarType());
            entityWrapper.orderBy("initial", true);
            List<TrafficRestrictionCity> trafficRestrictionCities = trafficRestrictionCityCrudService.selectList(entityWrapper);
            List<TrafficRestrictionCityViewListResultDto> trafficRestrictionCityViewListResultDtos = new ArrayList<>();
            if (!trafficRestrictionCities.isEmpty()) {
                Map<String, List<TrafficRestrictionCity>> groupBy = trafficRestrictionCities.stream().collect(Collectors.groupingBy(TrafficRestrictionCity::getInitial));
                for (String initial : groupBy.keySet()) {
                    TrafficRestrictionCityViewListResultDto trafficRestrictionCityViewListResultDto = new TrafficRestrictionCityViewListResultDto();
                    trafficRestrictionCityViewListResultDto.setInitial(initial);
                    List<TrafficRestrictionCityListResultDto> list = modelMapper.map(groupBy.get(initial), new TypeToken<List<TrafficRestrictionCityListResultDto>>() {
                    }.getType());
                    trafficRestrictionCityViewListResultDto.setCities(list);
                    trafficRestrictionCityViewListResultDtos.add(trafficRestrictionCityViewListResultDto);
                }
            }
            trafficRestrictionCityViewListResultDtos = trafficRestrictionCityViewListResultDtos.stream().sorted(Comparator.comparing(TrafficRestrictionCityViewListResultDto::getInitial)).collect(Collectors.toList());
            //将定位本地的城市排第一个
            if (!StringUtils.isEmpty(requestDto.getAdCode())) {
                String adCode = requestDto.getAdCode();
                RegionCityPingyinGetRequestDto regionCityPingyinGetRequestDto = new RegionCityPingyinGetRequestDto();
                regionCityPingyinGetRequestDto.setAdcode(adCode);
                RegionCityPinyinGetResultDto regionCityPinyinGetResultDto = regionService.getRegionCityPingyin(regionCityPingyinGetRequestDto).getData();
                if (null != regionCityPinyinGetResultDto) {
                    String cityAdCode = regionCityPinyinGetResultDto.getAdCode();
                    EntityWrapper<TrafficRestrictionCity> cityEntityWrapper = new EntityWrapper<>();
                    cityEntityWrapper.eq("adCode", cityAdCode);
                    TrafficRestrictionCity trafficRestrictionCity = trafficRestrictionCityCrudService.selectOne(cityEntityWrapper);
                    if (null != trafficRestrictionCity) {
                        TrafficRestrictionCityViewListResultDto trafficRestrictionCityViewListResultDto = new TrafficRestrictionCityViewListResultDto();
                        trafficRestrictionCityViewListResultDto.setInitial("默");
                        TrafficRestrictionCityListResultDto cityListResultDto = modelMapper.map(trafficRestrictionCity, TrafficRestrictionCityListResultDto.class);
                        List<TrafficRestrictionCityListResultDto> list = new ArrayList<>();
                        list.add(cityListResultDto);
                        trafficRestrictionCityViewListResultDto.setCities(list);
                        trafficRestrictionCityViewListResultDtos.add(0, trafficRestrictionCityViewListResultDto);
                    }
                }
            }
            resultDto.setItems(trafficRestrictionCityViewListResultDtos);
            resultDto.success();
        } catch (Exception e) {
            LOG.error("获取限行城市列表失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页获取限行城市
     *
     * @param requestDto TrafficRestrictionCityGetPageRequestDto
     * @return TrafficRestrictionCityListResultDto
     */
    @Override
    public PagedResultDto<TrafficRestrictionCityPageResultDto> page(TrafficRestrictionCityGetPageRequestDto requestDto) {
        PagedResultDto<TrafficRestrictionCityPageResultDto> resultDto = new PagedResultDto<>();
        try {
            EntityWrapper<TrafficRestrictionCity> entityWrapper = new EntityWrapper<>();
            if (!StringUtils.isEmpty(requestDto.getCityName())) {
                entityWrapper.like("cityName", requestDto.getCityName());
            }
            if (!StringUtils.isEmpty(requestDto.getInitial())) {
                entityWrapper.eq("initial", requestDto.getInitial());
            }
            entityWrapper.orderBy("initial", true);
            Page<TrafficRestrictionCity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<TrafficRestrictionCity> restrictionCityPage = trafficRestrictionCityCrudService.selectPage(page, entityWrapper);
            if (null != restrictionCityPage && !restrictionCityPage.getRecords().isEmpty()) {
                List<TrafficRestrictionCityPageResultDto> cityListResultDtos = modelMapper.map(restrictionCityPage.getRecords(), new TypeToken<List<TrafficRestrictionCityPageResultDto>>() {
                }.getType());
                for (TrafficRestrictionCityPageResultDto cityListResultDto : cityListResultDtos) {
                    EntityWrapper<TrafficRestrictionPolicy> policyEntityWrapper = new EntityWrapper<>();
                    policyEntityWrapper.eq("cityId", cityListResultDto.getId());
                    int count = trafficRestrictionPolicyCrudService.selectCount(policyEntityWrapper);
                    cityListResultDto.setPolicyCount(count);
                }
                resultDto.setItems(cityListResultDtos);
                resultDto.setPageNo(restrictionCityPage.getCurrent());
                resultDto.setPageSize(restrictionCityPage.getSize());
                resultDto.setTotalCount(restrictionCityPage.getTotal());
            }
            resultDto.success();
        } catch (Exception e) {
            LOG.error("获取限行城市列表失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 后台获取限行城市
     */
    @Override
    public ObjectResultDto<TrafficRestrictionPolicyGetResultDto> getCity(TrafficRestrictionGetCityRequestDto requestDto) {
        ObjectResultDto<TrafficRestrictionPolicyGetResultDto> resultDto = new ObjectResultDto<>();
        try {
            if (null == requestDto.getId()) {
                return resultDto.makeResult(ToolResultEnum.REQUEST_PARAM_EMPTY.getValue(), ToolResultEnum.REQUEST_PARAM_EMPTY.getComment());
            }
            TrafficRestrictionCity trafficRestrictionCity = trafficRestrictionCityCrudService.selectById(requestDto.getId());
            if (null != trafficRestrictionCity) {
                TrafficRestrictionPolicyGetResultDto policyGetResultDto = modelMapper.map(trafficRestrictionCity, TrafficRestrictionPolicyGetResultDto.class);
                EntityWrapper<TrafficRestrictionPolicy> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("cityId", trafficRestrictionCity.getId());
                List<TrafficRestrictionPolicy> trafficRestrictionPolicies = trafficRestrictionPolicyCrudService.selectListPolicy(entityWrapper);
                if (!trafficRestrictionPolicies.isEmpty()) {
                    List<TrafficRestrictionPolicyResultDto> policyResultDtos = modelMapper.map(trafficRestrictionPolicies, new TypeToken<List<TrafficRestrictionPolicyResultDto>>() {
                    }.getType());
                    policyGetResultDto.setTrafficRestrictionPolicys(policyResultDtos);
                }
                resultDto.setData(policyGetResultDto);
            }
            resultDto.success();
        } catch (Exception e) {
            LOG.error("后台获取限行城市失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取限行城市政策
     *
     * @param requestDto TrafficRestrictionCityGetRequestDto
     * @return TrafficRestrictionCityGetResultDto
     */
    @Override
    public ObjectResultDto<TrafficRestrictionPolicyInfoViewGetResultDto> getPolicyInfo(TrafficCityGetByAdCodeRequestDto requestDto) {
        ObjectResultDto<TrafficRestrictionPolicyInfoViewGetResultDto> resultDto = new ObjectResultDto<>();
        try {
            if (null == requestDto.getCityId()) {
                return resultDto.makeResult(ToolResultEnum.REQUEST_PARAM_EMPTY.getValue(), ToolResultEnum.REQUEST_PARAM_EMPTY.getComment());
            }
            Date now = DateUtils.now();
            if (null == requestDto.getDate()) {
                //时间默认当天
                requestDto.setDate(now);
            }
            TrafficRestrictionCity trafficRestrictionCity = trafficRestrictionCityCrudService.selectById(requestDto.getCityId());
            if (null == trafficRestrictionCity) {
                resultDto.makeResult(ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY.getValue(), ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY.getComment());
            }
            TrafficRestrictionPolicyInfoViewGetResultDto trafficRestrictionCityGetResultDto = modelMapper.map(trafficRestrictionCity, TrafficRestrictionPolicyInfoViewGetResultDto.class);
            EntityWrapper<TrafficRestrictionPolicy> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("cityId", trafficRestrictionCity.getId());
            List<TrafficRestrictionPolicy> trafficRestrictionPolicies = trafficRestrictionPolicyCrudService.selectListPolicy(entityWrapper);
            List<TrafficRestrictionPolicyViewResultDto> trafficRestrictionPolicys = new ArrayList<>();
            if (!trafficRestrictionPolicies.isEmpty()) {
                Map<Integer, List<TrafficRestrictionPolicy>> groupBy = trafficRestrictionPolicies.stream().collect(Collectors.groupingBy(TrafficRestrictionPolicy::getCarType));
                for (Integer catType : groupBy.keySet()) {
                    TrafficRestrictionPolicyViewResultDto trafficRestrictionPolicyViewResultDto = new TrafficRestrictionPolicyViewResultDto();
                    trafficRestrictionPolicyViewResultDto.setCarType(catType);
                    List<TrafficRestrictionPolicyResultDto> list = modelMapper.map(groupBy.get(catType), new TypeToken<List<TrafficRestrictionPolicyResultDto>>() {
                    }.getType());
                    trafficRestrictionPolicyViewResultDto.setList(list);
                    trafficRestrictionPolicys.add(trafficRestrictionPolicyViewResultDto);
                }
            }
            if (!StringUtils.isEmpty(requestDto.getAdCode())) {
                //根据经纬度做本地外地优先
                Boolean sortFlag = false;
                // 根据经纬度获取城市名称
                String adCode = requestDto.getAdCode();
                RegionCityPingyinGetRequestDto regionCityPingyinGetRequestDto = new RegionCityPingyinGetRequestDto();
                regionCityPingyinGetRequestDto.setAdcode(adCode);
                ObjectResultDto<RegionCityPinyinGetResultDto> regionCityPingyin = regionService.getRegionCityPingyin(regionCityPingyinGetRequestDto);
                if (null != regionCityPingyin && null != regionCityPingyin.getData()) {
                    RegionCityPinyinGetResultDto data = regionCityPingyin.getData();
                    String cityAdCode = data.getAdCode();
                    if (cityAdCode.equals(trafficRestrictionCity.getAdCode())) {
                        sortFlag = true;
                    }
                    if (sortFlag) {
                        //本地优先
                        trafficRestrictionPolicys.sort(Comparator.comparingInt(TrafficRestrictionPolicyViewResultDto::getCarType));
                    } else {
                        //外地优先
                        trafficRestrictionPolicys.sort(Comparator.comparingInt(TrafficRestrictionPolicyViewResultDto::getCarType).reversed());
                    }

                }
            }
            trafficRestrictionCityGetResultDto.setPolicies(trafficRestrictionPolicys);
            String adCode = trafficRestrictionCity.getAdCode();
            WeatherGetByDateRequestDto weatherGetByDateRequestDto = new WeatherGetByDateRequestDto();
            weatherGetByDateRequestDto.setAdCode(adCode);
            weatherGetByDateRequestDto.setDate(requestDto.getDate());
            ObjectResultDto<WeatherResultDto> weatherByDate = weatherService.getWeatherByDate(weatherGetByDateRequestDto);
            if (weatherByDate.isSuccess() && null != weatherByDate.getData()) {
                WeatherResultDto data = weatherByDate.getData();
                if (!DateUtils.formatDate(requestDto.getDate(), Const.FORMAT_DATE).equals(DateUtils.formatDate(now, Const.FORMAT_DATE))) {
                    //如果不是当天不显示实时气温
                    data.setTemperature("");
                }
                trafficRestrictionCityGetResultDto.setWeather(data);
            }
            TrafficRestriction trafficRestriction = trafficRestrictionCrudService.findOneTrafficRestriction(trafficRestrictionCity.getPinyinName(), DateUtils.formatDate(requestDto.getDate(), "yyyy-MM-dd"));
            if (null != trafficRestriction) {
                if (null != trafficRestriction.getTailNumber()) {
                    trafficRestrictionCityGetResultDto.setRestrictionTailNumber(trafficRestriction.getTailNumber());
                    trafficRestrictionCityGetResultDto.setTrafficRestriction(TrafficRestrictionStatusEnum.TRAFFIC_LIMIT.getValue());
                } else {
                    trafficRestrictionCityGetResultDto.setRestrictionTailNumber(TrafficRestrictionStatusEnum.UN_TRAFFIC_LIMIT.getComment());
                    trafficRestrictionCityGetResultDto.setTrafficRestriction(TrafficRestrictionStatusEnum.UN_TRAFFIC_LIMIT.getValue());
                }
            } else {
                trafficRestrictionCityGetResultDto.setRestrictionTailNumber(TrafficRestrictionStatusEnum.UN_KNOW.getComment());
                trafficRestrictionCityGetResultDto.setTrafficRestriction(TrafficRestrictionStatusEnum.UN_KNOW.getValue());
            }
            resultDto.setData(trafficRestrictionCityGetResultDto);
            resultDto.success();
        } catch (Exception e) {
            LOG.error("获取限行城市政策失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 后台获取限行政策
     */
    @Override
    public ObjectResultDto<TrafficRestrictionPolicyResultDto> getPolicy(TrafficRestrictionPolicyGetRequestDto requestDto) {
        ObjectResultDto<TrafficRestrictionPolicyResultDto> resultDto = new ObjectResultDto<>();
        try {
            if (null == requestDto.getId()) {
                return resultDto.makeResult(ToolResultEnum.REQUEST_PARAM_EMPTY.getValue(), ToolResultEnum.REQUEST_PARAM_EMPTY.getComment());
            }
            TrafficRestrictionPolicy trafficRestrictionPolicy = trafficRestrictionPolicyCrudService.selectById(requestDto.getId());
            if (null != trafficRestrictionPolicy) {
                TrafficRestrictionPolicyResultDto policyResultDto = modelMapper.map(trafficRestrictionPolicy, TrafficRestrictionPolicyResultDto.class);
                resultDto.setData(policyResultDto);
            }
            resultDto.success();
        } catch (Exception e) {
            LOG.error("获取限行城市政策失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页获取限行政策
     */
    @Override
    public PagedResultDto<TrafficRestrictionPolicyResultDto> getPolicyByPage(TrafficRestrictionGetPolicyByPageRequestDto requestDto) {
        PagedResultDto<TrafficRestrictionPolicyResultDto> resultDto = new PagedResultDto<>();
        try {
            Page<TrafficRestrictionPolicy> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            EntityWrapper<TrafficRestrictionPolicy> entityWrapper = new EntityWrapper<>();
            if (null != requestDto.getTrafficCityId()) {
                entityWrapper.eq("cityId", requestDto.getTrafficCityId());
            }
            if (null != requestDto.getCarType()) {
                entityWrapper.eq("carType", requestDto.getCarType());
            }
            Page<TrafficRestrictionPolicy> trafficRestrictionPolicyPage = trafficRestrictionPolicyCrudService.selectPage(page, entityWrapper);
            if (null != trafficRestrictionPolicyPage && null != trafficRestrictionPolicyPage.getRecords()) {
                List<TrafficRestrictionPolicy> records = trafficRestrictionPolicyPage.getRecords();
                List<TrafficRestrictionPolicyResultDto> data = modelMapper.map(records, new TypeToken<List<TrafficRestrictionPolicyResultDto>>() {
                }.getType());
                resultDto.setItems(data);
                resultDto.setPageNo(trafficRestrictionPolicyPage.getCurrent());
                resultDto.setPageSize(trafficRestrictionPolicyPage.getSize());
                resultDto.setTotalCount(trafficRestrictionPolicyPage.getTotal());
            }
            resultDto.success();
        } catch (Exception e) {
            LOG.error("获取限行政策失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 后台修改限行政策
     */
    @Override
    public ResultDto updatePolicy(TrafficRestrictionPolicyUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            TrafficRestrictionPolicy trafficRestrictionPolicy = trafficRestrictionPolicyCrudService.selectById(requestDto.getId());
            if (null != trafficRestrictionPolicy) {
                trafficRestrictionPolicyCrudService.deleteById(trafficRestrictionPolicy.getId());
            }
            TrafficRestrictionPolicy map = modelMapper.map(requestDto, TrafficRestrictionPolicy.class);
            map.setId(null);
            boolean insert = trafficRestrictionPolicyCrudService.insert(map);
            if (!insert) {
                resultDto.failed();
            }
            resultDto.success();
        } catch (Exception e) {
            LOG.error("修改限行政策失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改限行城市
     *
     * @param requestDto TrafficRestrictionCityUpdateRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto updateCity(TrafficRestrictionCityUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Long id = requestDto.getId();
            TrafficRestrictionCity trafficRestrictionCity = trafficRestrictionCityCrudService.selectById(id);
            if (null == trafficRestrictionCity) {
                return resultDto.makeResult(ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY.getValue(), ToolResultEnum.TRAFFIC_RESTRICTION_CITY_EMPTY.getComment());
            }
            TrafficRestrictionCity restrictionCity = new TrafficRestrictionCity();
            restrictionCity.setTrafficRestriction(requestDto.isTrafficRestriction());
            if (!StringUtils.isEmpty(requestDto.getPinyinName())) {
                restrictionCity.setPinyinName(requestDto.getPinyinName());
            }
            if (!StringUtils.isEmpty(requestDto.getAdCode())) {
                restrictionCity.setAdCode(requestDto.getAdCode());
            }
            if (!StringUtils.isEmpty(requestDto.getInitial())) {
                restrictionCity.setInitial(requestDto.getInitial());
            }
            if (!StringUtils.isEmpty(requestDto.getCityName())) {
                restrictionCity.setCityName(requestDto.getCityName());
            }
            EntityWrapper<TrafficRestrictionCity> cityEntityWrapper = new EntityWrapper<>();
            cityEntityWrapper.eq("id", trafficRestrictionCity.getId());
            trafficRestrictionCityCrudService.update(restrictionCity, cityEntityWrapper);
            resultDto.success();
        } catch (Exception e) {
            LOG.error("修改限行城市失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 车辆类型
     */
    @Override
    public ListResultDto<ComboboxItemDto> carType() {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto(String.valueOf(TrafficRestrictionCarTypeEnum.LOCAL_CAR.getValue()), TrafficRestrictionCarTypeEnum.LOCAL_CAR.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(TrafficRestrictionCarTypeEnum.FOREIGN_CAR.getValue()), TrafficRestrictionCarTypeEnum.FOREIGN_CAR.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(TrafficRestrictionCarTypeEnum.LOCAL_TRUCKS.getValue()), TrafficRestrictionCarTypeEnum.LOCAL_TRUCKS.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(TrafficRestrictionCarTypeEnum.FOREIGN_TRUCKS.getValue()), TrafficRestrictionCarTypeEnum.FOREIGN_TRUCKS.getComment(), false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            LOG.error("获取限行政策车辆类型失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 限行城市车辆类型
     */
    @Override
    public ListResultDto<ComboboxItemDto> limitCityCarType() {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto(String.valueOf(TrafficRestrictionCityCarTypeEnum.CAR.getValue()), TrafficRestrictionCityCarTypeEnum.CAR.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(TrafficRestrictionCityCarTypeEnum.TRUCKS.getValue()), TrafficRestrictionCityCarTypeEnum.TRUCKS.getComment(), false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            LOG.error("获取限行城市车辆类型失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取限行政策
     */
    @Override
    public ObjectResultDto<TrafficRestrictionCityGetResultDto> getInfo(TrafficRestrictionCityGetDataRequestDto requestDto) {
        ObjectResultDto<TrafficRestrictionCityGetResultDto> resultDto = new ObjectResultDto<>();
        try {
            if (null == requestDto.getId()) {
                return resultDto.makeResult(ToolResultEnum.REQUEST_PARAM_EMPTY.getValue(), ToolResultEnum.REQUEST_PARAM_EMPTY.getComment());
            }
            TrafficRestrictionCity trafficRestrictionCity = trafficRestrictionCityCrudService.selectById(requestDto.getId());
            if (null == trafficRestrictionCity) {
                return resultDto.makeResult(ToolResultEnum.TRAFFIC_RESTRICTION_CITY_NOT_EXIST.getValue(), ToolResultEnum.TRAFFIC_RESTRICTION_CITY_NOT_EXIST.getComment());
            }
            TrafficRestrictionCityGetResultDto trafficRestrictionCityGetResultDto = modelMapper.map(trafficRestrictionCity, TrafficRestrictionCityGetResultDto.class);
            EntityWrapper<TrafficRestrictionPolicy> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("cityId", trafficRestrictionCity.getId());
            entityWrapper.eq("carType", requestDto.getCarType());
            List<TrafficRestrictionPolicy> trafficRestrictionPolicies = trafficRestrictionPolicyCrudService.selectListPolicy(entityWrapper);
            if (!trafficRestrictionPolicies.isEmpty()) {
                List<TrafficRestrictionPolicyResultDto> policyResultDtos = modelMapper.map(trafficRestrictionPolicies, new TypeToken<List<TrafficRestrictionPolicyResultDto>>() {
                }.getType());
                trafficRestrictionCityGetResultDto.setTrafficRestrictionPolicys(policyResultDtos);
            }
            String adCode = trafficRestrictionCity.getAdCode();
            WeatherGetByDateRequestDto weatherGetByDateRequestDto = new WeatherGetByDateRequestDto();
            weatherGetByDateRequestDto.setAdCode(adCode);
            weatherGetByDateRequestDto.setDate(requestDto.getDate());
            ObjectResultDto<WeatherResultDto> weatherByDate = weatherService.getWeatherByDate(weatherGetByDateRequestDto);
            if (weatherByDate.isSuccess() && null != weatherByDate.getData()) {
                WeatherResultDto data = weatherByDate.getData();
                trafficRestrictionCityGetResultDto.setWeather(data);
            }
            TrafficRestriction trafficRestriction = trafficRestrictionCrudService.findOneTrafficRestriction(trafficRestrictionCity.getPinyinName(), DateUtils.formatDate(requestDto.getDate(), "yyyy-MM-dd"));
            if (null == trafficRestriction) {
                trafficRestrictionCityGetResultDto.setRestrictionTailNumber(TrafficRestrictionStatusEnum.UN_KNOW.getComment());
                trafficRestrictionCityGetResultDto.setTrafficRestriction(TrafficRestrictionStatusEnum.UN_KNOW.getValue());
            } else if (null != trafficRestriction.getTailNumber()) {
                trafficRestrictionCityGetResultDto.setRestrictionTailNumber(trafficRestriction.getTailNumber());
                trafficRestrictionCityGetResultDto.setTrafficRestriction(TrafficRestrictionStatusEnum.TRAFFIC_LIMIT.getValue());
            } else {
                trafficRestrictionCityGetResultDto.setRestrictionTailNumber(TrafficRestrictionStatusEnum.UN_TRAFFIC_LIMIT.getComment());
                trafficRestrictionCityGetResultDto.setTrafficRestriction(TrafficRestrictionStatusEnum.UN_TRAFFIC_LIMIT.getValue());
            }
            resultDto.setData(trafficRestrictionCityGetResultDto);
            resultDto.success();
        } catch (Exception e) {
            LOG.error("获取限行城市政策失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

}
