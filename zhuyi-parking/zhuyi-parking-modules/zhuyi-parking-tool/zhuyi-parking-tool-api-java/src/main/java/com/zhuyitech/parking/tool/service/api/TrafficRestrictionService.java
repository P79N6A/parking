package com.zhuyitech.parking.tool.service.api;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.tool.dto.request.AdCodeRequestDto;
import com.zhuyitech.parking.tool.dto.request.trafficrestriction.TrafficLimitDeleteRequestDto;
import com.zhuyitech.parking.tool.dto.request.trafficrestriction.TrafficRestrictionRequestDto;
import com.zhuyitech.parking.tool.dto.result.traffic.TrafficRestrictionCityBeanResultDto;
import com.zhuyitech.parking.tool.dto.result.traffic.TrafficRestrictionInfoViewResultDto;
import com.zhuyitech.parking.tool.dto.result.traffic.TrafficRestrictionPolicyInfoGetResultDto;
import com.zhuyitech.parking.tool.dto.result.traffic.TrafficRestrictionViewResultDto;

/**
 * 尾号限行服务
 *
 * @author AkeemSuper
 * @date 2018/4/13 0013
 */
public interface TrafficRestrictionService {

    /**
     * 查询支持限行的城市
     *
     * @return TrafficRestrictionResultDto
     */
    ObjectResultDto<TrafficRestrictionCityBeanResultDto> queryTrafficRestrictionCity();

    /**
     * 调三方查询指定城市的限行情况
     *
     * @param requestDto TrafficRestrictionRequestDto
     * @return TrafficRestrictionResultDto
     */
    ObjectResultDto<TrafficRestrictionInfoViewResultDto> requestTrafficRestrictionInfo(TrafficRestrictionRequestDto requestDto);

    /**
     * 将支持限行的城市同步到数据库中
     *
     * @return ResultDto
     */
    ResultDto syncTrafficRestrictionCity();

    /**
     * 同步所有支持限城市的限行信息
     *
     * @return ResultDto
     */
    ResultDto syncTrafficRestrictionInfo();

    /**
     * 获取用户当前所在城市的当天的限行信息
     *
     * @param requestDto requestDto
     * @return TrafficRestrictionInfoGetResultDto
     */
    ObjectResultDto<TrafficRestrictionPolicyInfoGetResultDto> getTrafficRestrictionInfo(AdCodeRequestDto requestDto);

    /**
     * 首页当前城市当天的限行尾号
     *
     * @param requestDto requestDto
     * @return TrafficRestrictionViewResultDto
     */
    ObjectResultDto<TrafficRestrictionViewResultDto> getTrafficRestrictionInfoForIndex(AdCodeRequestDto requestDto);

    /**
     * 定时删除限行记录
     * @param requestDto
     * @return
     */
    ResultDto deletedTrafficLimit(TrafficLimitDeleteRequestDto requestDto);
}
