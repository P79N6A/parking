package com.zhuyitech.parking.ucc.service.api;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.ucc.dto.result.*;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingGuidanceParamDto;
import com.zoeeasy.cloud.pms.platform.dto.result.PlateNumberMyCloudResultDto;

public interface ParkingGuidanceService {


    /**
     * @Description: 获取我的车牌/停车信息
     * @Author: qhxu
     * @CreateDate: 2019/3/23 16:17
     * @UpdateUser: qhxu
     * @UpdateDate: 2019/3/23 16:17
     * @UpdateRemark: 修改内容
     * @Version: 1.0
     */
    ListResultDto<PlateNumberMyResultDto> getMyPlateNumber(PlateNumberMyRequestVo plateNumberMyRequestVo);


    /**
     * @Description: 根据车位车牌查找信息
     * @Author: qhxu
     * @CreateDate: 2019/3/23 16:17
     * @UpdateUser: qhxu
     * @UpdateDate: 2019/3/23 16:17
     * @UpdateRemark: 修改内容
     * @Version: 1.0
     */
    ListResultDto<PlateNumberMyCloudResultDto> getPlateNumberList(ParkingGuidanceQueryRequestVo parkingGuidanceRequestVo);

    /**
     * @Description: 定位导航
     * @Author: qhxu
     * @CreateDate: 2019/3/23 16:17
     * @UpdateUser: qhxu
     * @UpdateDate: 2019/3/23 16:17
     * @UpdateRemark: 修改内容
     * @Version: 1.0
     */
    ObjectResultDto<ParkingGuidanceResultDto> getParkingGuidance(ParkingGuidanceRequestVo requestDto);

}
