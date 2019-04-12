package com.zhuyitech.parking.pay.wechat.result;



import java.io.Serializable;

/**
 * <pre>
 *     微信登录获取用户信息
 * </pre>
 *
 * @author zwq
 * @date 2018-02-24-14:14
 */
public class WeChatGetUserInfoResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 授权用户唯一标识
     */
    private String openId;

    /**
     * nickname
     */
    private String nickName;

    /**
     * sex
     */
    private String sex;

    /**
     * province
     */
    private String province;

    /**
     * city
     */
    private String city;

    /**
     * country
     */
    private String country;

    /**
     * headimgurl
     */
    private String headimgurl;

    /**
     * unionid
     */
    private String unionid;

    /**
     * errcode
     */
    private String errCode;

    /**
     * errmsg
     */
    private String errMsg;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "WeChatGetUserInfoResult{" +
                "openId='" + openId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex='" + sex + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", unionid='" + unionid + '\'' +
                ", errCode='" + errCode + '\'' +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
