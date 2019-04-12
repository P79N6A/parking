package com.zhuyitech.parking.ucc.dto.request.realname;

import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import com.scapegoat.infrastructure.validate.annotaion.validation.IdentityNo;
import com.zhuyitech.parking.common.constant.Const;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 分页查询用户实名认证请求参数
 *
 * @author walkman
 * @date 2018-01-10
 */
@ApiModel(value = "VisitLogQueryPagedResultRequestDto", description = "分页查询登录日志请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserAuthApplyQueryPagedResultRequestDto extends SessionPagedRequestDto {

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