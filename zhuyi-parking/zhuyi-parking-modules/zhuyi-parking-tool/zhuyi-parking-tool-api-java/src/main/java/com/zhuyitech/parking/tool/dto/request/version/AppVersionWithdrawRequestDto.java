package com.zhuyitech.parking.tool.dto.request.version;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 版本下架请求参数
 *
 * @author zwq
 * @date 2018/4/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AppVersionWithdrawRequestDto", description = "版本下架请求参数")
public class AppVersionWithdrawRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
