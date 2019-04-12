package com.zoeeasy.cloud.hikvision.dto.result;

import com.zoeeasy.cloud.hikvision.bean.VehicleRecordsView;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description 分页获取停车场过车记录的返回结果
 * @Date: 2018/1/15 0015
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VehicleRecordsResult extends HikvisionBaseResult<VehicleRecordsView> implements Serializable {

    private static final long serialVersionUID = 1L;

}
