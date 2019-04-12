package com.zoeeasy.cloud.hikvision.dto.result;

import com.zoeeasy.cloud.hikvision.bean.LeftPlotBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description 获取某个停车场的剩余车位数的返回结果
 * @Date: 2018/1/15 0015
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LeftPlotResult extends HikvisionBaseResult<LeftPlotBean> implements Serializable {

    private static final long serialVersionUID = 1L;
}
