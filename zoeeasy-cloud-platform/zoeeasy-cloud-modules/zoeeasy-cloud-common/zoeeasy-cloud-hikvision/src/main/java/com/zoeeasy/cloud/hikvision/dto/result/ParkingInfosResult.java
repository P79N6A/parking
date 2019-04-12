package com.zoeeasy.cloud.hikvision.dto.result;

import com.zoeeasy.cloud.hikvision.bean.ParkingInfoView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description 分页获取停车场基本信息的返回结果
 * @Date: 2018/1/13 0013
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingInfosResult extends HikvisionBaseResult<ParkingInfoView> implements Serializable {

    private static final long serialVersionUID = 1L;
}
