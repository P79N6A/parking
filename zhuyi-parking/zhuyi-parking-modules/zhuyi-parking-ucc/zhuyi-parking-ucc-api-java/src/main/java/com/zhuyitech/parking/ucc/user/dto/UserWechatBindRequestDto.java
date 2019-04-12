package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * 绑定微信
 *
 * @author zwq
 */
@ApiModel(description = "绑定微信")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserWechatBindRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 授权用户唯一标识
     */
    @ApiModelProperty(value = "openId", required = true, notes = "openId")
    @NotEmpty(message = "openId不能为空")
    private String openId;

    /**
     * nickname
     */
    @ApiModelProperty(value = "nickName", required = true, notes = "nickName")
    private String nickName;

    /**
     * sex
     */
    @ApiModelProperty(value = "sex", required = true, notes = "sex")
    private String sex;

    /**
     * province
     */
    @ApiModelProperty(value = "province", required = true, notes = "province")
    private String province;

    /**
     * city
     */
    @ApiModelProperty(value = "city", required = true, notes = "city")
    private String city;

    /**
     * country
     */
    @ApiModelProperty(value = "country", required = true, notes = "country")
    private String country;

    /**
     * headImgUrl
     */
    @ApiModelProperty(value = "headImgUrl", required = true, notes = "headImgUrl")
    private String headImgUrl;

    /**
     * unionid
     */
    @ApiModelProperty(value = "unionid", required = true, notes = "unionid")
    private String unionid;

}
