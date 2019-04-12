package com.zhuyitech.parking.ucc.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.jackson.annotation.ImageUrl;
import com.scapegoat.infrastructure.jackson.annotation.SensitiveInfo;
import com.scapegoat.infrastructure.jackson.enums.SensitiveType;
import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户实名认证视图模型
 *
 * @author walkman
 */
@ApiModel(value = "UserRealNameAuthApplyResultDto", description = "用户实名认证视图模型")
public class UserRealNameAuthApplyResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户真实姓名
     */
    @ApiModelProperty("用户真实姓名")
    @SensitiveInfo(SensitiveType.CHINESE_NAME)
    private String realName;

    /**
     * 用户身份证号
     */
    @ApiModelProperty("用户身份证号")
    @SensitiveInfo(SensitiveType.ID_CARD)
    private String cardNo;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String portrait;

    /**
     * 认证状态 1 认证中  2 已认证  3 认证不通过
     */
    @ApiModelProperty(value = "认证状态,1 认证中  2 已认证  3 认证不通过")
    private Integer authStatus;

    /**
     * 是否有认证申请
     */
    @ApiModelProperty(value = "是否有认证申请, true 有  false 没有")
    private Boolean hasRealAuth;

    /**
     * 实名认证申请审核驳回原因
     */
    @ApiModelProperty(value = "实名认证申请审核驳回原因")
    private String rejectReason;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Boolean getHasRealAuth() {
        return hasRealAuth;
    }

    public void setHasRealAuth(Boolean hasRealAuth) {
        this.hasRealAuth = hasRealAuth;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
