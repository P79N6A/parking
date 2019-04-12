package com.zhuyitech.parking.ucc.dto.request.realname;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.scapegoat.infrastructure.validate.annotaion.validation.IdentityNo;
import com.zhuyitech.parking.common.constant.Const;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户实名认证请求参数表
 *
 * @author walkman
 * @date 2018-01-01
 */
@ApiModel(value = "UserAuthApplyListGetRequestDto", description = "菜单列表请求参数表")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserAuthApplyListGetRequestDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户真实姓名
     */
    @ApiModelProperty(value = "用户真实姓名")
    private String realName;

    /**
     * 用户身份证号
     */
    @ApiModelProperty(value = "用户身份证号")
    @IdentityNo
    private String cardNo;

    /**
     * 用户身份认证状态
     */
    @ApiModelProperty(value = "用户身份认证状态")
    private Integer authStatus;

    /**
     * 申请时间开始
     */
    @ApiModelProperty(value = "申请时间开始")
    @DateTimeFormat(pattern = Const.FORMAT_DATETIME)
    private Date applyTimeStart;

    /**
     * 申请时间结束
     */
    @ApiModelProperty(value = "申请时间结束")
    @DateTimeFormat(pattern = Const.FORMAT_DATETIME)
    private Date applyTimeEnd;

}
