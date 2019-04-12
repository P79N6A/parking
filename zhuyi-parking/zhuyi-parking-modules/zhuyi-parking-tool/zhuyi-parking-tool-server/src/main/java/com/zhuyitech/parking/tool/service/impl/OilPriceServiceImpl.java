package com.zhuyitech.parking.tool.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.common.constant.Const;
import com.zhuyitech.parking.common.utils.ProvinceConvertUtils;
import com.zhuyitech.parking.tool.config.JuHeConfig;
import com.zhuyitech.parking.tool.domain.OilPrice;
import com.zhuyitech.parking.tool.domain.Region;
import com.zhuyitech.parking.tool.dto.request.oilprice.OilPriceDetailRequestDto;
import com.zhuyitech.parking.tool.dto.request.oilprice.OilPriceListRequestDto;
import com.zhuyitech.parking.tool.dto.request.oilprice.OilPricePutInRequestDto;
import com.zhuyitech.parking.tool.dto.request.oilprice.OilPriceRequestDto;
import com.zhuyitech.parking.tool.dto.request.region.ProvinceGetByAdcodeRequestDto;
import com.zhuyitech.parking.tool.dto.request.region.ProvinceListGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.region.RegionProvinceAdCodeGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.weather.GeocodeGetRequestDto;
import com.zhuyitech.parking.tool.dto.result.oilprice.*;
import com.zhuyitech.parking.tool.dto.result.region.RegionProvinceNameGetResultDto;
import com.zhuyitech.parking.tool.dto.result.region.RegionViewByAdcodeResultDto;
import com.zhuyitech.parking.tool.dto.result.region.RegionViewResultDto;
import com.zhuyitech.parking.tool.dto.result.weather.RegeoCodeGetResultDto;
import com.zhuyitech.parking.tool.enums.OilPriceTypeEnum;
import com.zhuyitech.parking.tool.enums.OilTypeEnum;
import com.zhuyitech.parking.tool.enums.RegionLevelEnum;
import com.zhuyitech.parking.tool.enums.ToolResultEnum;
import com.zhuyitech.parking.tool.service.OilPriceCrudService;
import com.zhuyitech.parking.tool.service.RegionCrudService;
import com.zhuyitech.parking.tool.service.api.AmapService;
import com.zhuyitech.parking.tool.service.api.OilPriceService;
import com.zhuyitech.parking.tool.service.api.RegionService;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 油价服务
 *
 * @author zwq
 */
@Service("oilPriceService")
@Slf4j
public class OilPriceServiceImpl implements OilPriceService {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    private static final String EMPTY_PRICE = "---";

    @Autowired
    private OilPriceCrudService oilPriceCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AmapService amapService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private RegionCrudService regionCrudService;

    @Autowired
    private JuHeConfig juHeConfig;

    @Override
    public ObjectResultDto<OilPriceResultDto> oilPriceRequest(OilPriceRequestDto requestDto) {
        ObjectResultDto<OilPriceResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("key", juHeConfig.getOilAppkey());
            String response = Requests.post(juHeConfig.getOilUrl()).verify(false).followRedirect(false).
                    forms(params).charset(Charset.forName("UTF-8")).send().readToText();
            JSONObject json = JSONObject.parseObject(response);
            String resultCode = json.getString("resultcode");
            if (!"200".equals(resultCode)) {
                return objectResultDto.makeResult(ToolResultEnum.OIL_PRICE_REQUEST_ERR.getValue(), ToolResultEnum.OIL_PRICE_REQUEST_ERR.getComment());
            }
            OilPriceResultDto oilPriceResultDto = modelMapper.map(json, OilPriceResultDto.class);
            objectResultDto.setData(oilPriceResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("油价信息请求失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto oilPricePutIn(OilPricePutInRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            OilPriceRequestDto oilPriceRequestDto = new OilPriceRequestDto();
            ObjectResultDto<OilPriceResultDto> objectResultDto = oilPriceRequest(oilPriceRequestDto);
            if (objectResultDto.isFailed() || null == objectResultDto.getData()) {
                return objectResultDto.makeResult(ToolResultEnum.OIL_PRICE_REQUEST_ERR.getValue(), ToolResultEnum.OIL_PRICE_REQUEST_ERR.getComment());
            }
            OilPriceResultDto oilPriceResultDto = objectResultDto.getData();
            if ("200".equals(oilPriceResultDto.getResultcode())) {
                List<OilPriceInfo> list = oilPriceResultDto.getResult();
                if (null == list || list.size() <= 0) {
                    return objectResultDto.makeResult(ToolResultEnum.OIL_PRICE_REQUEST_ERR.getValue(), ToolResultEnum.OIL_PRICE_REQUEST_ERR.getComment());
                }
                for (OilPriceInfo oilPriceInfo : list) {
                    EntityWrapper<OilPrice> wrapper = new EntityWrapper<>();
                    wrapper.eq("province", oilPriceInfo.getCity());
                    wrapper.eq("priceType", OilPriceTypeEnum.REAL_PRICE.getValue());
                    wrapper.eq("oilDate", DateUtils.formatDate(DateUtils.now(), Const.FORMAT_DATE));
                    OilPrice exitOilPrice = oilPriceCrudService.selectOne(wrapper);
                    if (null != exitOilPrice) {
                        EntityWrapper<OilPrice> updateWrapper = new EntityWrapper<>();
                        updateWrapper.eq("id", exitOilPrice.getId());
                        OilPrice oilPrice = new OilPrice();
                        oilPrice.setB0(oilPriceInfo.getB0());
                        oilPrice.setB90(oilPriceInfo.getB90());
                        oilPrice.setB93(oilPriceInfo.getB93());
                        oilPrice.setB97(oilPriceInfo.getB97());
                        oilPriceCrudService.update(oilPrice, updateWrapper);
                    } else {
                        OilPrice oilPrice = new OilPrice();
                        oilPrice.setProvince(oilPriceInfo.getCity());
                        RegionProvinceAdCodeGetRequestDto regionProvinceAdCodeGetRequestDto = new RegionProvinceAdCodeGetRequestDto();
                        regionProvinceAdCodeGetRequestDto.setCityName(oilPriceInfo.getCity());
                        ObjectResultDto<RegionProvinceNameGetResultDto> regionAdcode = regionService.getRegionAdcode(regionProvinceAdCodeGetRequestDto);
                        if (regionAdcode.isSuccess() && null != regionAdcode.getData()) {
                            RegionProvinceNameGetResultDto data = regionAdcode.getData();
                            oilPrice.setCode(data.getAdCode());
                        }
                        if (!StringUtils.isEmpty(oilPriceInfo.getB0())) {
                            oilPrice.setB0(oilPriceInfo.getB0());
                        }
                        if (!StringUtils.isEmpty(oilPriceInfo.getB90())) {
                            oilPrice.setB90(oilPriceInfo.getB90());
                        }
                        if (!StringUtils.isEmpty(oilPriceInfo.getB93())) {
                            oilPrice.setB93(oilPriceInfo.getB93());
                        }
                        if (!StringUtils.isEmpty(oilPriceInfo.getB97())) {
                            oilPrice.setB97(oilPriceInfo.getB97());
                        }
                        oilPrice.setOilDate(DateUtils.now());
                        oilPrice.setPriceType(OilPriceTypeEnum.REAL_PRICE.getValue());
                        oilPriceCrudService.insert(oilPrice);
                    }
                }
            } else {
                return objectResultDto.makeResult(ToolResultEnum.OIL_PRICE_REQUEST_ERR.getValue(), ToolResultEnum.OIL_PRICE_REQUEST_ERR.getComment());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("油价插入失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取油价列表
     */
    @Override
    public ObjectResultDto<OilPriceListResultDto> getOliPriceList(OilPriceListRequestDto requestDto) {
        ObjectResultDto<OilPriceListResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            OilPriceListResultDto oilPriceListResultDto = new OilPriceListResultDto();
            //默认设置无默认城市
            oilPriceListResultDto.setDefaultProvince(0);
            String defaultProvinceCode = null;
            if (null != requestDto.getLatitude() && null != requestDto.getLongitude()) {
                //经纬度不为空
                //设置 默认城市
                GeocodeGetRequestDto geocodeGetRequestDto = new GeocodeGetRequestDto();
                geocodeGetRequestDto.setLocation(requestDto.getLongitude() + "," + requestDto.getLatitude());
                ObjectResultDto<RegeoCodeGetResultDto> geocoderGet = amapService.geocoderGet(geocodeGetRequestDto);
                if (geocoderGet.isSuccess() || null != geocoderGet.getData()) {
                    RegeoCodeGetResultDto geocodeGetResultDto = geocoderGet.getData();
                    ProvinceGetByAdcodeRequestDto provinceGetByAdcodeRequestDto = new ProvinceGetByAdcodeRequestDto();
                    provinceGetByAdcodeRequestDto.setAdcode(geocodeGetResultDto.getAdCode());
                    ObjectResultDto<RegionViewByAdcodeResultDto> provinceByAdcode = regionService.getProvinceByAdcode(provinceGetByAdcodeRequestDto);
                    if (provinceByAdcode.isSuccess() || null != provinceByAdcode.getData()) {
                        RegionViewByAdcodeResultDto regionViewByAdcodeResultDto = provinceByAdcode.getData();
                        defaultProvinceCode = regionViewByAdcodeResultDto.getAdcode();
                        //更改设置为有默认城市
                        oilPriceListResultDto.setDefaultProvince(1);
                    }
                }
            }
            List<OilPriceCurrentResultDto> oilPriceCurrentResultDtos = new ArrayList<>();
            //获取所有省份当日油价
            List<OilPrice> oilPriceList = oilPriceCrudService.getCurrentOilPrice(DateUtils.formatDate(DateUtils.now(), Const.FORMAT_DATE), OilPriceTypeEnum.REAL_PRICE.getValue());
            if (!oilPriceList.isEmpty()) {
                //获取所有省份
                EntityWrapper<Region> regionWrapper = new EntityWrapper<>();
                regionWrapper.eq("level", RegionLevelEnum.PROVINCE.getValue());
                regionWrapper.orderBy("adCode", true);
                List<Region> regions = regionCrudService.selectList(regionWrapper);
                if (!regions.isEmpty()) {
                    for (Region region : regions) {
                        OilPriceCurrentResultDto oilPriceCurrentResultDto;
                        Optional<OilPrice> optionalPrice = oilPriceList.stream().filter(s -> s.getCode().equals(region.getAdCode())).findFirst();
                        if (optionalPrice.isPresent()) {
                            oilPriceCurrentResultDto = buildOilPriceCurrent(optionalPrice.get());
                        } else {
                            oilPriceCurrentResultDto = new OilPriceCurrentResultDto();
                            oilPriceCurrentResultDto.setProvince(ProvinceConvertUtils.abbreviation(region.getName()));
                            oilPriceCurrentResultDto.setCode(region.getAdCode());
                            oilPriceCurrentResultDto.setB89(EMPTY_PRICE);
                            oilPriceCurrentResultDto.setB95(EMPTY_PRICE);
                            oilPriceCurrentResultDto.setB92(EMPTY_PRICE);
                            oilPriceCurrentResultDto.setB0(EMPTY_PRICE);
                        }
                        //设置当前省份为第一个
                        if (region.getAdCode().equals(defaultProvinceCode)) {
                            oilPriceCurrentResultDtos.add(0, oilPriceCurrentResultDto);
                        } else {
                            oilPriceCurrentResultDtos.add(oilPriceCurrentResultDto);
                        }
                    }
                }
                oilPriceListResultDto.setOilPriceList(oilPriceCurrentResultDtos);
                oilPriceListResultDto.setUpdateTime(DateUtils.now());
                objectResultDto.setData(oilPriceListResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取油价列表失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取油价详情
     */
    @Override
    public ObjectResultDto<OilPriceDetailResultDto> getOilPriceDetail(OilPriceDetailRequestDto requestDto) {
        ObjectResultDto<OilPriceDetailResultDto> objectResultDto = new ObjectResultDto<>();
        OilPriceDetailResultDto oilPriceDetailResultDto = new OilPriceDetailResultDto();
        try {
            if (StringUtils.isEmpty(requestDto.getCode())) {
                return objectResultDto.makeResult(ToolResultEnum.OIL_PRICE_PROVINCE_EMPTY.getValue(), ToolResultEnum.OIL_PRICE_PROVINCE_EMPTY.getComment());
            }
            //今日油价详情
            OilPrice oilPrice = oilPriceCrudService.selectCurrentOilPrice(requestDto.getCode());
            OilPriceCurrentResultDto oilPriceCurrentResultDto = buildOilPriceCurrent(oilPrice);
            if (null == oilPriceCurrentResultDto) {
                oilPriceDetailResultDto.setCurrentOilPrice(new OilPriceCurrentResultDto());
            } else {
                oilPriceDetailResultDto.setCurrentOilPrice(oilPriceCurrentResultDto);
            }
            //油价曲线图
            List<OilPriceGraphResultDto> priceGraphs = new ArrayList<>();
            //最近6个月平均油价
            List<OilPrice> avgPrices = oilPriceCrudService.getAvgPrice(requestDto.getCode(), 6);
            if (avgPrices.isEmpty()) {
                if (null == oilPriceCurrentResultDto) {
                    return objectResultDto.makeResult(ToolResultEnum.OIL_PRICE_DETAIL_EMPTY.getValue(), ToolResultEnum.OIL_PRICE_DETAIL_EMPTY.getComment());
                }
                oilPriceDetailResultDto.setPriceGraphs(priceGraphs);
                objectResultDto.setData(oilPriceDetailResultDto);
                objectResultDto.success();
                return objectResultDto;
            }
            //90号油价曲线图
            OilPriceGraphResultDto b90PriceGraph = new OilPriceGraphResultDto();
            b90PriceGraph.setOilType(OilTypeEnum.B90.getValue());
            //93号油价曲线图
            OilPriceGraphResultDto b93PriceGraph = new OilPriceGraphResultDto();
            b93PriceGraph.setOilType(OilTypeEnum.B93.getValue());
            //97号油价曲线图
            OilPriceGraphResultDto b97PriceGraph = new OilPriceGraphResultDto();
            b97PriceGraph.setOilType(OilTypeEnum.B97.getValue());
            //0号油价曲线图
            OilPriceGraphResultDto b0PriceGraph = new OilPriceGraphResultDto();
            b0PriceGraph.setOilType(OilTypeEnum.B0.getValue());
            //90号油价坐标
            List<OilPriceCoordinateResultDto> b90Coordinates = new ArrayList<>();
            //93号油价坐标
            List<OilPriceCoordinateResultDto> b93Coordinates = new ArrayList<>();
            //97号油价坐标
            List<OilPriceCoordinateResultDto> b97Coordinates = new ArrayList<>();
            //0号油价坐标
            List<OilPriceCoordinateResultDto> b0Coordinates = new ArrayList<>();
            for (OilPrice avgPrice : avgPrices) {
                String oilTime = DateUtils.formatDate(avgPrice.getOilDate(), "yyyy.MM");
                //90号油价坐标
                OilPriceCoordinateResultDto b90oilPriceCoordinate = new OilPriceCoordinateResultDto();
                b90oilPriceCoordinate.setOilTime(oilTime);
                b90oilPriceCoordinate.setOilPrice(avgPrice.getB90());
                b90Coordinates.add(0, b90oilPriceCoordinate);
                //93号油价坐标
                OilPriceCoordinateResultDto b93oilPriceCoordinate = new OilPriceCoordinateResultDto();
                b93oilPriceCoordinate.setOilTime(oilTime);
                b93oilPriceCoordinate.setOilPrice(avgPrice.getB93());
                b93Coordinates.add(0, b93oilPriceCoordinate);
                //97号油价坐标
                OilPriceCoordinateResultDto b97oilPriceCoordinate = new OilPriceCoordinateResultDto();
                b97oilPriceCoordinate.setOilTime(oilTime);
                b97oilPriceCoordinate.setOilPrice(avgPrice.getB97());
                b97Coordinates.add(0, b97oilPriceCoordinate);
                //0号油价坐标
                OilPriceCoordinateResultDto b0oilPriceCoordinate = new OilPriceCoordinateResultDto();
                b0oilPriceCoordinate.setOilTime(oilTime);
                b0oilPriceCoordinate.setOilPrice(avgPrice.getB0());
                b0Coordinates.add(0, b0oilPriceCoordinate);
            }
            b90PriceGraph.setCoordinates(b90Coordinates);
            b93PriceGraph.setCoordinates(b93Coordinates);
            b97PriceGraph.setCoordinates(b97Coordinates);
            b0PriceGraph.setCoordinates(b0Coordinates);
            priceGraphs.add(b90PriceGraph);
            priceGraphs.add(b93PriceGraph);
            priceGraphs.add(b97PriceGraph);
            priceGraphs.add(b0PriceGraph);
            //油价曲线
            oilPriceDetailResultDto.setCurrentOilPrice(oilPriceCurrentResultDto);
            oilPriceDetailResultDto.setPriceGraphs(priceGraphs);
            objectResultDto.setData(oilPriceDetailResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取油价详情失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 油价均值计算
     */
    @Override
    public ResultDto calculateOilPriceAvg() {
        ResultDto resultDto = new ResultDto();
        try {
            LocalDate now = LocalDate.now();
            LocalDate monthStartDay = LocalDate.of(now.getYear(), now.getMonth(), 1);
            String oilDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM")) + "%";
            ListResultDto<RegionViewResultDto> provinceList = regionService.getProvinceList(new ProvinceListGetRequestDto());
            if (provinceList.isSuccess() && !provinceList.getItems().isEmpty()) {
                List<RegionViewResultDto> provinces = provinceList.getItems();
                for (RegionViewResultDto province : provinces) {
                    //获取某省当前月一号的均价记录
                    EntityWrapper<OilPrice> wrapper = new EntityWrapper<>();
                    wrapper.eq("oilDate", monthStartDay);
                    wrapper.eq("priceType", OilPriceTypeEnum.AVG_PRICE.getValue());
                    wrapper.eq("code", province.getCode());
                    OilPrice avgOilPrice = oilPriceCrudService.selectOne(wrapper);
                    if (null == avgOilPrice) {
                        //创建均价记录
                        avgOilPrice = new OilPrice();
                        avgOilPrice.setOilDate(DateUtils.parseDate(monthStartDay));
                        avgOilPrice.setPriceType(OilPriceTypeEnum.AVG_PRICE.getValue());
                        avgOilPrice.setProvince(province.getName());
                        avgOilPrice.setCode(province.getCode());
                        avgOilPrice.setB0("0.00");
                        avgOilPrice.setB90("0.00");
                        avgOilPrice.setB93("0.00");
                        avgOilPrice.setB97("0.00");
                        oilPriceCrudService.insert(avgOilPrice);
                    }
                    // 维护均值
                    //获取本市该月已有的所有记录
                    EntityWrapper<OilPrice> entityWrapper = new EntityWrapper<>();
                    entityWrapper.like("oilDate", oilDate);
                    entityWrapper.eq("priceType", OilPriceTypeEnum.REAL_PRICE.getValue());
                    entityWrapper.eq("code", province.getCode());
                    List<OilPrice> oilPriceList = oilPriceCrudService.selectList(entityWrapper);
                    long b90Count = oilPriceList.stream().filter(s -> StringUtils.isNotEmpty(s.getB90())).count();
                    double b90avgPrice;
                    if (b90Count == 0) {
                        b90avgPrice = 0d;
                    } else {
                        b90avgPrice = oilPriceList.stream().filter(s -> StringUtils.isNotEmpty(s.getB90())).mapToDouble(s -> new Double(s.getB90())).average().getAsDouble();
                    }
                    long b93Count = oilPriceList.stream().filter(s -> StringUtils.isNotEmpty(s.getB93())).count();
                    double b93avgPrice;
                    if (b93Count == 0) {
                        b93avgPrice = 0d;
                    } else {
                        b93avgPrice = oilPriceList.stream().filter(s -> StringUtils.isNotEmpty(s.getB93())).mapToDouble(s -> new Double(s.getB93())).average().getAsDouble();
                    }
                    long b97Count = oilPriceList.stream().filter(s -> StringUtils.isNotEmpty(s.getB97())).count();
                    double b97avgPrice;
                    if (b97Count == 0) {
                        b97avgPrice = 0d;
                    } else {
                        b97avgPrice = oilPriceList.stream().filter(s -> StringUtils.isNotEmpty(s.getB97())).mapToDouble(s -> new Double(s.getB97())).average().getAsDouble();
                    }
                    long b0Count = oilPriceList.stream().filter(s -> StringUtils.isNotEmpty(s.getB0())).count();
                    double b0avgPrice;
                    if (b0Count == 0) {
                        b0avgPrice = 0d;
                    } else {
                        b0avgPrice = oilPriceList.stream().filter(s -> StringUtils.isNotEmpty(s.getB0())).mapToDouble(s -> new Double(s.getB0())).average().getAsDouble();
                    }
                    OilPrice updateOilPrice = new OilPrice();
                    updateOilPrice.setB90(DECIMAL_FORMAT.format(b90avgPrice));
                    updateOilPrice.setB93(DECIMAL_FORMAT.format(b93avgPrice));
                    updateOilPrice.setB97(DECIMAL_FORMAT.format(b97avgPrice));
                    updateOilPrice.setB0(DECIMAL_FORMAT.format(b0avgPrice));
                    Wrapper<OilPrice> updateWrapper = new EntityWrapper<>();
                    updateWrapper.eq("id", avgOilPrice.getId());
                    oilPriceCrudService.update(updateOilPrice, updateWrapper);
                }
            }

            resultDto.success();
        } catch (Exception e) {
            log.error("油价均值计算失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto sysOilPriceData(OilPricePutInRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            List<OilPrice> oilPriceList = jsoupData();
            for (OilPrice oilPrice : oilPriceList) {
                EntityWrapper<OilPrice> wrapper = new EntityWrapper<>();
                wrapper.eq("province", oilPrice.getProvince());
                wrapper.eq("priceType", OilPriceTypeEnum.REAL_PRICE.getValue());
                wrapper.eq("oilDate", DateUtils.formatDate(DateUtils.now(), Const.FORMAT_DATE));
                OilPrice exitOilPrice = oilPriceCrudService.selectOne(wrapper);
                if (null != exitOilPrice) {
                    EntityWrapper<OilPrice> updateWrapper = new EntityWrapper<>();
                    updateWrapper.eq("id", exitOilPrice.getId());
                    OilPrice updateOilPrice = new OilPrice();
                    updateOilPrice.setB0(oilPrice.getB0());
                    updateOilPrice.setB90(oilPrice.getB90());
                    updateOilPrice.setB93(oilPrice.getB93());
                    updateOilPrice.setB97(oilPrice.getB97());
                    oilPriceCrudService.update(updateOilPrice, updateWrapper);
                } else {
                    oilPrice.setOilDate(DateUtils.now());
                    String province = oilPrice.getProvince();
                    Wrapper<Region> regionEntityWrapper = new EntityWrapper<>();
                    regionEntityWrapper.like("name", province);
                    regionEntityWrapper.eq("level", RegionLevelEnum.PROVINCE.getValue());
                    regionEntityWrapper.last("LIMIT 1");
                    Region region = regionCrudService.selectOne(regionEntityWrapper);
                    if (null != region) {
                        oilPrice.setCode(region.getAdCode());
                    }
                    oilPrice.setPriceType(OilPriceTypeEnum.REAL_PRICE.getValue());
                    oilPriceCrudService.insert(oilPrice);
                }
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("油价信息入库失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;

    }

    /**
     * @param oilPrice
     * @return
     */
    private OilPriceCurrentResultDto buildOilPriceCurrent(OilPrice oilPrice) {
        if (null != oilPrice) {
            OilPriceCurrentResultDto oilPriceCurrentResultDto = new OilPriceCurrentResultDto();
            if (StringUtils.isEmpty(oilPrice.getB0())) {
                oilPriceCurrentResultDto.setB0(EMPTY_PRICE);
            } else {
                oilPriceCurrentResultDto.setB0(oilPrice.getB0());
            }
            if (StringUtils.isEmpty(oilPrice.getB93())) {
                oilPriceCurrentResultDto.setB92(EMPTY_PRICE);
            } else {
                oilPriceCurrentResultDto.setB92(oilPrice.getB93());
            }
            if (StringUtils.isEmpty(oilPrice.getB97())) {
                oilPriceCurrentResultDto.setB95(EMPTY_PRICE);
            } else {
                oilPriceCurrentResultDto.setB95(oilPrice.getB97());
            }
            if (StringUtils.isEmpty(oilPrice.getB90())) {
                oilPriceCurrentResultDto.setB89(EMPTY_PRICE);
            } else {
                oilPriceCurrentResultDto.setB89(oilPrice.getB90());
            }
            oilPriceCurrentResultDto.setProvince(oilPrice.getProvince());
            oilPriceCurrentResultDto.setCode(oilPrice.getCode());
            return oilPriceCurrentResultDto;
        }
        return null;

    }

    /**
     * 使用Jsoup爬油价信息 并封装
     */
    private List<OilPrice> jsoupData() throws Exception {
        String url = "http://youjia.chemcp.com/";
        Document document = Jsoup.connect(url).timeout(5000).get();
        Elements dicElements = document.getElementsByTag("table");
        Element tableElement = dicElements.get(4);
        Elements trElements = tableElement.getElementsByTag("tr");
        List<OilPrice> oilPrices = new ArrayList<>();
        for (int i = 1; i < trElements.size(); i++) {
            Element trelement = trElements.get(i);
            Elements children = trelement.children();
            String city = children.get(0).text();
            String b90 = children.get(1).text();
            String b93 = children.get(2).text();
            String b97 = children.get(3).text();
            String b0 = children.get(5).text();
            OilPrice oilPrice = new OilPrice();
            oilPrice.setProvince(city);
            oilPrice.setB90(b90);
            oilPrice.setB93(b93);
            oilPrice.setB97(b97);
            oilPrice.setB0(b0);
            oilPrices.add(oilPrice);
        }
        return oilPrices;
    }
}
