package com.zhuyitech.parking.inmotion;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.inmotion.dto.InMotionPushDataMockRequestDto;

public interface InmotionService {

    ResultDto mockInmontionPass();

    /**
     * 模拟Imotion上报数据
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ResultDto mockInmontionPass(InMotionPushDataMockRequestDto requestDto);

}
