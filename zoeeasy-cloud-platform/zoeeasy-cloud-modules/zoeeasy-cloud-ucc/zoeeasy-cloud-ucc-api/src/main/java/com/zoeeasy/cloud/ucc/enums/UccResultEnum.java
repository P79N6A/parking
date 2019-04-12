package com.zoeeasy.cloud.ucc.enums;

import com.zoeeasy.cloud.ucc.role.cst.RoleConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 返回结果枚举
 *
 * @author walkman
 * @date 2018-01-10
 */
@NoArgsConstructor
@AllArgsConstructor
public enum UccResultEnum {

    NOT_FOUND(102, "记录未找到"),

    RECORD_DELETED(103, "记录已删除"),


    //菜单
    MENU_CODE_EXISTS(10100, "菜单代码已经存在"),
    MENU_NAME_EXISTS(10101, "菜单名称已经存在"),
    MENU_CODE_EMPTY(10102, "菜单代码为空"),
    MENU_NAME_EMPTY(10103, "菜单名称为空"),
    MENU_NOT_FOUND(10104, "菜单不存在"),

    //角色
    ROLE_CODE_EMPTY(10202, RoleConstant.ROLE_CODE_NOT_EMPTY),
    ROLE_CODE_EXISTS(120, RoleConstant.ROLE_CODE_EXIST),
    ROLE_NAME_EXISTS(10201, RoleConstant.ROLE_NAME_EXIST),
    ROLE_NAME_EMPTY(10203, RoleConstant.ROLE_NAME_NOT_EMPTY),
    ROLE_NOT_FOUND(10205, RoleConstant.ROLE_NOT_EXIST),
    ROLE_STATIC_NOT_ALLOWED_DELETE(10204, RoleConstant.ROLE_STATIC_NOT_TO_DELETE),

    //权限
    PERMISSION_CODE_EXISTS(10300, "权限代码已经存在"),
    PERMISSION_NAME_EXISTS(10301, "权限名称已经存在"),
    PERMISSION_CODE_EMPTY(10302, "权限代码为空"),
    PERMISSION_NAME_EMPTY(10303, "权限名称为空"),
    PERMISSION_DEFAULT_NOT_ALLOWED_DELETE(10304, "默认权限不允许删除"),
    PERMISSION_NOT_FOUND(10305, "权限不存在"),

    //用户
    USER_NAME_EXISTS(10500, "用户名已经存在"),
    USER_PHONE_EXISTS(10501, "手机号已注册"),
    USER_EMAIL_EXISTS(10502, "邮箱已注册"),
    USER_LOCKED(10503, "用户被冻结或已注销"),
    USERNAME_OR_PASSWORD_ERROR(10504, "用户名或密码错误"),
    OLD_PASSWORD_ERROR(10505, "原密码错误"),
    PASSWORD_DISMATCH_ERROR(10506, "两次密码不一致，请重新输入"),
    USER_NOT_FOUND(10507, "账号不存在"),
    USER_LOGIN_ATTEMPT_TOO_MANY(10508, "尝试登陆次数过多"),
    USER_NAME_EMPTY(10509, "用户名为空"),
    USER_PASSWORD_EMPTY(10510, "密码为空"),
    USER_ROLE_EXISTS(10511, "用户已经具有该项角色"),
    USER_ACCESS_TOKEN_FAILED(10512, "用户获取访问令牌失败"),
    USER_REAL_NAME_EMPTY(10513, "用户真实姓名为空"),
    USER_CARD_NO_EMPTY(10514, "用户身份证号为空"),
    USER_CARD_FRONT_EMPTY(10515, "用户身份证正面照为空"),
    USER_CARD_CONTRARY_EMPTY(10516, "用户身份证反面照为空"),
    USER_CARD_INHAND_EMPTY(10517, "用户身份证手持照为空"),
    USER_ID_EMPTY(10518, "用户主键为空"),
    USER_LOGIN_FAIL(10519, "登录失败"),
    USER_REGISTER_FAIL(10520, "注册失败"),
    USER_ALREADY_AUTHENTICATED(10521, "用户已经实名"),

    USER_TRADE_PASSWORD_EMPTY(10522, "支付密码为空"),

    USER_TRADE_PASSWORD_SUCCESS(10523, "支付密码正确"),

    USER_TRADE_PASSWORD_ERROR(10524, "支付密码错误"),

    USER_TRADE_PASSWORD_EXIST(10525, "支付密码存在"),

    USER_TRADE_PASSWORD_STYLE_ERROR(10526, "支付密码格式错误"),

    USER_WEIXIN_SIGN_UP_ERROR(10527, "微信注册失败"),

    USER_PASSWORD_HAS_SET(10528, "登录密码已经设置"),

    USER_AUTHENTICATE_PENDING(50522, "用户实名审核中"),

    USER_AUTHENTICATE_SUBMITTED(50523, "用户已经提交实名认证"),

    USER_APPROVE_OPINION_EMPTY(50524, "审核意见为空"),

    USER_AUTHENTICATE_NOT_FOUND(50525, "认证申请不存在"),

    USER_AUTHENTICATE_CANNOT_MODIFY(50526, "当前状态下不允许修改资料"),

    USER_AUTHENTICATE_STATUS_INVALID(50527, "认证申请状态无效"),

    USER_EMPTY_BY_PLATE_NUMBER(50528, "没有根据车牌号查找到用户"),

    USER_NICKNAME_EMPTY(50529, "昵称为空"),
    ORIGIN_PASSWORD_ERROR(101, "原密码错误"),
    USERNAME_EXISTS(121, "登录用户名已经存在"),
    USER_DISABLED(123, "用户已被冻结"),
    USER_GENDER_EMPTY(50530, "性别为空"),
    USER_BIRTHDAY_EMPTY(50531, "生日为空"),
    USER_PORTRAIT_EMPTY(50532, "头像为空"),
    USER_GENDER_ERR(50533, "性别参数错误"),
    USER_VERIFY_CODE_EMPTY(50534, "验证码为空"),
    LOGIN_TYPE_NOT_SUPPORT(50535, "不支持的登录类型"),
    MOBILE_PHONE_NUMBER_INVALID(50536, "请输入正确的手机号码"),
    USER_PORTRAIT_UPDATE_FAIL(50537, "用户头像修改失败"),
    USER_PASSWORD_LENGTH_FAIL(50538, "请输入6-20位密码"),
    BIRTHDAY_ERR(50539, "生日输入有误"),
    NICKNAME_ERR(50540, "昵称过长，请重新输入"),
    USER_TRAD_PASSWORD_LENGTH_FAIL(50541, "请输入6位支付密码"),
    USER_REAL_NAME_AUTH_REJECT_REASON_EMPTY(50542, "用户实名认证驳回原因为空"),
    SMS_REQUEST_ID_FAIL(50543, "短信回执id不一致"),

    //操作日志
    OPERATION_LOG_TITLE_EMPTY(10600, "操作日志标题为空"),

    OPERATION_LOG_NOT_FOUND(10601, "操作日志不存在"),

    VISIT_LOG_USERNAME_EMPTY(10602, "操作日志用户名为空"),

    VISIT_LOG_NOT_FOUND(10603, "操作日志不存在"),

    //账单&停车记录
    PARKING_RECORD_NOT_FOUND(10701, "停车记录不存在"),

    PARKING_RECORD_IMAGE_GET_ERROR(10702, "停车图像获取失败"),

    //微信登录
    OPENID_NOT_EXISTS(10800, "openId为空"),

    CODE_NOT_VAILD(10801, "code无效"),

    ACCESSTOKEN_NOT_EXISTS(10802, "accessToken为空"),

    ACCESSTOKEN_NOT_VAILD(10803, "accessToken无效"),

    CHECK_ACCESSTOKEN_ERR(10804, "检查accessToken失败"),

    GRT_ACCESSTOKEN_ERR(10805, "获取accessToken失败"),

    GRT_USERINFO_ERR(10806, "获取用户信息失败"),

    REFRESHTOKEN_NOT_EXISTS(10807, "refreshToken为空"),

    USERID_EMPTY(10808, "userId为空"),

    USER_HAS_BIND_WECHAT(10809, "用户已绑定微信"),

    USER_WECHAT_HAS_BIND(10810, "该微信账户已被其他用户绑定"),

    UNIONID_NOT_EXISTS(10811, "unionid为空"),

    //余额支付
    RECORD_NOT_EXISTS(10900, "该用户下不存在该订单"),

    RECORD_EMPTY(10901, "订单为空"),

    PAYMENT_AMOUNT_EMPTY(10902, "订单金额为空"),

    PAYMENT_AMOUNT_NOT_ENOUGH(10903, "余额不足"),

    //支付宝登录
    ALIPAYUSERID_EMPTY(11100, "alipayUserId为空"),

    USER_HAS_BIND_ALIPAY(11101, "用户已绑定支付宝"),

    USER_ALIPAY_SIGN_UP_ERROR(11102, "支付宝注册失败"),

    USER_ALIPAY_BLIND_ERROR(11102, "支付宝绑定失败"),

    USER_ALIPAY_HAS_BIND(11103, "该支付宝账户已被其他用户绑定"),

    REQUEST_EMPTY(120000, "请求参数为空"),

    SCHEDULETIME_ERR(1111230, "入场时间不能大于当前时间"),

    SCHEDULETIME_EMPTY(1111230, "入场时间不能大于当前时间"),
    //收支明细

    GETLIST_ERR(130000, "拉取用户收支明细失败"),

    GETDETAIL_ERR(130001, "获取用户收支明细详情失败"),

    ASSETLOGID_EMPTY(130002, "收支明细ID为空"),

    ASSETLOG_NOT_EXIST(130003, "收支明细不存在"),

    RECHARGEORDER_NOTEXIST(130004, "充值订单不存在"),

    BIZTYPE_ERR(130005, "业务类型有误"),

    PAGEQUERY_ERR(130006, "获取钱包账单列表失败"),

    UPDATE_REMARK_ERR(130007, "修改备注失败"),

    GET_ASSETLOGTYPE_ERR(130007, "获取订单类型列表失败"),

    GET_CARTYPE_ERR(130007, "获取车辆类型列表失败"),
    ;

    /**
     * 值
     */
    @Getter
    @Setter
    private Integer value;

    /**
     * 注释
     */
    @Getter
    @Setter
    private String comment;

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static UccResultEnum parse(Integer value) {
        if (value != null) {
            UccResultEnum[] array = values();
            for (UccResultEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
