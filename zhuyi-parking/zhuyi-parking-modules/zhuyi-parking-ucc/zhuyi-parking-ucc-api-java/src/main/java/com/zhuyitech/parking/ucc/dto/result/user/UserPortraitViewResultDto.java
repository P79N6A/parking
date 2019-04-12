package com.zhuyitech.parking.ucc.dto.result.user;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户个人信息视图
 *
 * @author walkman
 */
@ApiModel(value = "UserPortraitViewResultDto", description = "用户个人信息视图")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPortraitViewResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    private String portrait;

}
