package com.zhuyitech.parking.ucc.service.api;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.parking.ucc.dto.request.record.UserParkingRecordGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.record.UserParkingRecordListGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.record.UserParkingRecordPagedResultRequestDto;
import com.zhuyitech.parking.ucc.dto.result.record.UserParkingRecordDetailResultDto;
import com.zhuyitech.parking.ucc.dto.result.record.UserParkingRecordResultDto;
import com.zhuyitech.parking.ucc.dto.result.record.UserParkingRecordViewResultDto;


/**
 * @Description: 用户停车记录服务
 * @Date: 2018/1/18 0018
 * @author: AkeemSuper
 */
public interface UserParkingRecordService {

    /**
     * 获取用户停车记录
     *
     * @param requestDto requestDto
     * @return 户停车记录
     */
    ObjectResultDto<UserParkingRecordResultDto> getUserParkingRecord(UserParkingRecordGetRequestDto requestDto);

    /**
     * 获取用户停车记录
     *
     * @param requestDto
     * @return
     */
    ListResultDto<UserParkingRecordViewResultDto> getUserParkingRecordList(UserParkingRecordListGetRequestDto requestDto);

    /**
     * 分页获取用户停车记录
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<UserParkingRecordViewResultDto> getUserParkingRecordPageList(UserParkingRecordPagedResultRequestDto requestDto);

    /**
     * 获取用户停车记录详情
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<UserParkingRecordDetailResultDto> getUserParkingRecordView(UserParkingRecordGetRequestDto requestDto);
}
