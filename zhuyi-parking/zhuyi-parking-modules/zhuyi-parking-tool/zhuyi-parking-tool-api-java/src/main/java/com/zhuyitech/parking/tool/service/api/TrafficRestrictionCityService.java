package com.zhuyitech.parking.tool.service.api;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.tool.dto.request.trafficrestriction.*;
import com.zhuyitech.parking.tool.dto.result.traffic.*;

/**
 * 限行城市服务
 *
 * @author AkeemSuper
 * @date 2018/5/17 0017
 */
public interface TrafficRestrictionCityService {

    /**
     * 添加限行城市
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ResultDto add(TrafficRestrictionCityAddRequestDto requestDto);

    /**
     * 添加限行政策
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ResultDto addPolicy(TrafficRestrictionPolicyAddRequestDto requestDto);

    /**
     * 删除限行政策
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ResultDto deletedPolicy(TrafficRestrictionPolicyDeletedRequestDto requestDto);

    /**
     * 删除限行城市
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ResultDto deletedCity(TrafficRestrictionCityDeletedRequestDto requestDto);

    /**
     * 后台修改限行政策
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ResultDto updatePolicy(TrafficRestrictionPolicyUpdateRequestDto requestDto);

    /**
     * 修改限行城市
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ResultDto updateCity(TrafficRestrictionCityUpdateRequestDto requestDto);

    /**
     * 分页获取限行城市
     *
     * @param requestDto requestDto
     * @return PagedResultDto
     */
    PagedResultDto<TrafficRestrictionCityPageResultDto> page(TrafficRestrictionCityGetPageRequestDto requestDto);

    /**
     * 后台获取限行城市
     */
    ObjectResultDto<TrafficRestrictionPolicyGetResultDto> getCity(TrafficRestrictionGetCityRequestDto requestDto);

    /**
     * 后台获取限行政策
     *
     * @param requestDto requestDto
     * @return ObjectResultDto
     */
    ObjectResultDto<TrafficRestrictionPolicyResultDto> getPolicy(TrafficRestrictionPolicyGetRequestDto requestDto);

    /**
     * 后天分页获取限行政策
     *
     * @param requestDto requestDto
     * @return PagedResultDto
     */
    PagedResultDto<TrafficRestrictionPolicyResultDto> getPolicyByPage(TrafficRestrictionGetPolicyByPageRequestDto requestDto);

    /**
     * 限行政策车辆类型
     *
     * @return ListResultDto
     */
    ListResultDto<ComboboxItemDto> carType();

    /**
     * 限行城市车辆类型
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> limitCityCarType();


    /**
     * 获取限行政策
     *
     * @param requestDto requestDto
     * @return ObjectResultDto
     */
    ObjectResultDto<TrafficRestrictionCityGetResultDto> getInfo(TrafficRestrictionCityGetDataRequestDto requestDto);

    /**
     * 前台获取限行城市政策分组
     *
     * @param requestDto requestDto
     * @return ObjectResultDto
     */
    ObjectResultDto<TrafficRestrictionPolicyInfoViewGetResultDto> getPolicyInfo(TrafficCityGetByAdCodeRequestDto requestDto);

    /**
     * 前台获取限行的城市
     *
     * @param requestDto requestDto
     * @return ListResultDto
     */
    ListResultDto<TrafficRestrictionCityViewListResultDto> list(TrafficCityGetListByAdCodeRequestDto requestDto);

}
