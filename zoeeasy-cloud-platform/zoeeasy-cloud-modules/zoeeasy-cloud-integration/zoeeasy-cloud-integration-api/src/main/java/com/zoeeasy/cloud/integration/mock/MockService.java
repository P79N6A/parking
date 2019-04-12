package com.zoeeasy.cloud.integration.mock;


/**
 * 拉取全国停车场数据
 *
 * @author wangfeng
 * @date 2018/11/23 10:05
 **/
public interface MockService {

    /**
     * 拉取全国停车场数据
     *
     * @param requestDto ParkingInfoFetchRequestDto
     * @return ResultDto
     */
//    ResultDto fetchNationalParkingInfo(ParkingInfoFetchRequestDto requestDto);

    /**
     * 从任意停车平台-拉取停车场数据
     *
     * @param requestDto CloudParkingInfoResultDto
     * @return ResultDto
     */
//    ResultDto fetchLocalParkingInfo(List<CloudParkingInfoResultDto> requestDto);

//    /**
//     * 从任意停车平台-拉取过车记录数据
//     *
//     * @param requestDto CloudPassingVehicleRecordResultDto
//     * @return ResultDto
//     */
//    ResultDto transferOldPassingVehicleRecord(List<PassingVehicleRecordResultDto> requestDto);
//
//    /**
//     * 从任意停车平台-拉取停车记录数据
//     *
//     * @param requestDto CloudParkingRecordResultDto
//     * @return ResultDto
//     */
//    ResultDto transferOldParkingRecord(List<ParkingRecordResultDto> requestDto);
//
//    /**
//     * 从任意停车平台-拉取停车账单数据
//     *
//     * @param requestDto CloudParkingOrderResultDto
//     * @return ResultDto
//     */
//    ResultDto transferOldParkingOrder(List<ParkingOrderResultDto> requestDto);
    /**
     * 从任意停车平台-拉取停车记录数据
     *
     * @param requestDto CloudParkingRecordResultDto
     * @return ResultDto
     */
//    ResultDto transferOldParkingRecord(List<CloudParkingRecordResultDto> requestDto);

    /**
     * 从任意停车平台-拉取停车账单数据
     *
     * @param requestDto CloudParkingOrderResultDto
     * @return ResultDto
     */
//    ResultDto transferOldParkingOrder(List<CloudParkingOrderResultDto> requestDto);
}
