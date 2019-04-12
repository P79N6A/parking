package com.zoeeasy.cloud.hikvision.dto.result;

import com.zoeeasy.cloud.hikvision.bean.ParkingInfoBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description 根据停车场的parkCode集获取停车场基本信息的返回结果
 * @Date: 2018/1/15 0015
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingInfosByParkCodeResult extends HikvisionBaseResult<ParkingInfoBean> implements Serializable {

    private static final long serialVersionUID = 1L;

}
