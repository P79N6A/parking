package com.zoeeasy.cloud.hikvision.dto.result;

import com.zoeeasy.cloud.hikvision.bean.LaneInfosByGateCodesBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description 根据出入口编号集获取出入口车道基本信息
 * @Date: 2018/1/15 0015
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LaneInfosByGateCodeResult extends HikvisionBaseResult<LaneInfosByGateCodesBean> implements Serializable {

    private static final long serialVersionUID = 1L;
}
