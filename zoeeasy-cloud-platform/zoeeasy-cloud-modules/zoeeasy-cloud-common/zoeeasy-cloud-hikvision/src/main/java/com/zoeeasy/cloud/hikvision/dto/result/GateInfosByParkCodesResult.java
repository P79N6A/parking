package com.zoeeasy.cloud.hikvision.dto.result;

import com.zoeeasy.cloud.hikvision.bean.GateInfosByParkCodesBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 根据停车场编号获取出入口基本信息
 * @Date: 2018/1/15 0015
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GateInfosByParkCodesResult extends HikvisionBaseResult<List<GateInfosByParkCodesBean>> implements Serializable {

    private static final long serialVersionUID = 1L;
}
