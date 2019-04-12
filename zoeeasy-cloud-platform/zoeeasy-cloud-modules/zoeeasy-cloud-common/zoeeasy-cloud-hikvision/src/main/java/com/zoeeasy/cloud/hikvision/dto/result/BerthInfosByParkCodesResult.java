package com.zoeeasy.cloud.hikvision.dto.result;

import com.zoeeasy.cloud.hikvision.bean.BerthBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: 分页获取路边停车场的泊位信息
 * @Author: AkeemSuper
 * @Date: 2018/2/2 0002
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BerthInfosByParkCodesResult extends HikvisionBaseResult<BerthBean> implements Serializable {

    private static final long serialVersionUID = 1L;
}
