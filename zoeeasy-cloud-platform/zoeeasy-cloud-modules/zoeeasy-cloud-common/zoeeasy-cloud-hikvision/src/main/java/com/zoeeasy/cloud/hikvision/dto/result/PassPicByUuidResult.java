package com.zoeeasy.cloud.hikvision.dto.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description:
 * @author: AkeemSuper
 * @Date: 2018/2/3 0003
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PassPicByUuidResult extends HikvisionBaseResult<String[]> implements Serializable {

    private static final long serialVersionUID = 1L;

}
