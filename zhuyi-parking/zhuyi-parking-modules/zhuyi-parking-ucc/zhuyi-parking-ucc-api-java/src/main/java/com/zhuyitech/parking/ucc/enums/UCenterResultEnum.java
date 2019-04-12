package com.zhuyitech.parking.ucc.enums;

/**
 * 返回结果枚举
 *
 * @author walkman
 * @date 2018-01-10
 */
public enum UCenterResultEnum {

    NOT_FOUND(102, "记录未找到"),
    RECORD_DELETED(103, "记录已删除"),

    //菜单
    MENU_CODE_EXISTS(10100, "菜单代码已经存在"),
    MENU_NAME_EXISTS(10101, "菜单名称已经存在"),
    MENU_CODE_EMPTY(10102, "菜单代码为空"),
    MENU_NAME_EMPTY(10103, "菜单名称为空"),
    MENU_NOT_FOUND(10104, "菜单不存在"),
    //角色
    ROLE_CODE_EXISTS(10200, "角色代码已经存在"),
    ROLE_NAME_EXISTS(10201, "角色名称已经存在"),
    ROLE_CODE_EMPTY(10202, "角色代码为空"),
    ROLE_NAME_EMPTY(10203, "角色名称为空"),
    ROLE_DEFAULT_NOT_ALLOWED_DELETE(10204, "默认角色不允许删除"),
    ROLE_NOT_FOUND(10205, "角色不存在"),
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
    USER_LOCKED(10503, "用户已被冻结"),
    USERNAME_OR_PASSWORD_ERROR(10504, "用户名或密码错误"),
    OLD_PASSWORD_ERROR(10505, "原密码错误"),
    PASSWORD_DISMATCH_ERROR(10506, "两次密码不一致，请重新输入"),
    USER_NOT_FOUND(10507, "用户不存在"),
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

    //车辆
    CAR_IDENTIFICATION_NUMBER_EMPTY(10800, "车架号为空"),
    CAR_PLATE_NUMBER_EMPTY(10801, "车牌号为空"),
    CAR_ENGINE_NUMBER_EMPTY(10802, "汽车发动机号为空"),
    CAR_DRIVER_LICENSE_FRONT_PHOTO_EMPTY(10803, "车辆行驶证正面为空"),
    CAR_DRIVER_LICENSE_CONTRARY_PHOTO_EMPTY(10804, "车辆行驶证反面为空"),
    CAR_AUTHENTICATE_SUBMITTED(10805, "车辆已注册，请勿重复绑定"),
    CAR_AUTHENTICATE_NOT_FOUND(10806, "车辆认证申请不存在"),
    CAR_AUTHENTICATE_CANNOT_MODIFY(10807, "车辆认证审核中不允许修改"),
    CAR_NOT_FOUND(10809, "车辆不存在"),
    USER_CAR_AUTHENTICATE_STATUS_INVALID(10810, "车辆认证认证申请状态无效"),
    USER_CAR_ALREADY_INSERT(10811, "车辆已插入用户车辆信息"),
    CAR_PLATE_NUMBER_INVALID(10812, "车牌号无效"),
    CAR_CANNOT_MODIFY(10813, "车辆不允许修改"),
    CAR_NOT_AUTHENTICATE_VIOLATION_NOT_SUPPORT(10814, "请先认证车辆才能查询违章记录"),
    CAR_INFO_NOT_COMPLETE(10815, "车辆信息不全"),
    CAR_VIOLATION_QUERY_FAILED(10816, "查询违章失败，请稍后再试"),
    CAR_PLATE_COLOR_INVALID(10817, "车牌颜色无效"),
    CAR_NOT_BELONG_USER(10818, "车辆不属于你"),
    USER_CARINFO_GET_ERROR(10819, "当前用户车辆信息获取失败"),
    CAR_IDENTIFICATION_NUMBER_INVALID(10820, "车架号无效"),
    CAR_BIND_USER_NOT_CERTIFICATED(10821, "未实名认证用户不允许绑定已被绑定车辆"),
    CAR_AUTH_PENDING_UNBIND_ERROR(10822, "认证中不可解绑"),
    CAR_UNBIND(10823, "车辆已被解绑"),
    CAR_REGISTER_DATE_EMPTY(10824, "车辆注册日期为空"),

    CAR_BIND_BY_OTHER(10825, "车辆被他人绑定,请实名认证找回"),
    CAR_USER_NOT_REAL_NAME_AUTH(10826, "还未身份认证通过,暂时不能绑定车牌"),
    CAR_USER_JUST_REAL_NAME_AUTH(10827, "实名认证中，请耐心等待"),
    CAR_USER_REAL_NAME_AUTH_FAIL(10828, "实名认证失败，请重新认证"),
    CAR_PLATE_NUMBER_COLOR_ERR(10829, "车牌与车牌颜色不对应"),

    //驾驶证
    DRIVER_LICENSE_CARD_EMPTY(10900, "驾驶证号为空"),
    DRIVER_LICENSE_ARCHIVE_EMPTY(10901, "驾驶证档案号为空"),
    DRIVER_CLASS_EMPTY(10902, "准驾车型为空"),


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

    //支付
    PAY_RECHARGE_WAY_ERROR(30100, "充值方式无效"),
    PAY_RECHARGE_WAY_NOT_SUPPORT(30101, "充值方式不支持"),
    PAY_RECHARGE_AMOUNT_ERROR(30102, "充值金额无效"),
    PAY_RECHARGE_AMOUNT_TOO_LARGE(30103, "充值金额过大"),
    PAY_RECHARGE_FAILED(30104, "充值失败"),
    PAY_WAY_NOT_SUPPORT(30105, "支付方式不支持"),
    TRADE_PASSWORD_EMPTY(30106, "支付密码为空"),
    TRADE_PASSWORD_ERR(30107, "支付密码有误"),
    TRADE_PASSWORD_NOT_VERIFY(30108, "支付密码未校验"),
    //微信支付
    PARKING_RECORD_EMPTY(30200, "订单不存在"),
    PAY_WAY_EMPTY(30201, "支付方式为空"),
    AMOUNT_ERR(30202, "订单金额有误"),
    //支付宝支付
    ALIPAY_PLACE_ORDER_ERROR(30203, "支付宝下单失败"),
    WECHAT_PLACE_ORDER_ERROR(30204, "微信下单失败"),
    ORDER_AMOUNT_ERROR(30205, "订单金额不一致"),
    ORDER_PAY_STATUS_ERROR(30205, "订单支付状态无效"),
    RECHARGE_ORDER_NOT_FOUND(30206, "充值订单不存在"),
    RECHARGE_ORDER_AMOUNT_ERROR(30207, "充值金额不一致"),
    PARKING_ORDER_PAY_FAILED(30208, "订单支付失败"),
    APPOINT_ORDER_EMPTY(30209, "预定订单号不能为空"),
    APPOINT_ORDER_NOT_FOUND(30210, "预定订单不存在"),
    APPOINT_ORDER_PAY_FAILED(30211, "预定订单支付失败"),
    BALANCE_CHECK_FAILED(30212, "余额检测失败"),
    OPENID_EMPTY(30214, "openid为空"),
    UNIONID_EMPTY(30215, "unionid为空"),
    ORDER_PAYED(30216, "账单已支付"),
    PARKING_ORDER_PAYED_CANT_REPEAT(30217, "停车订单已支付,请勿重复支付"),
    ;;

    /**
     * 值
     */
    private int value;

    /**
     * 注释
     */
    private String comment;

    public int getValue() {
        return this.value;
    }

    public String getComment() {
        return this.comment;
    }

    UCenterResultEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static UCenterResultEnum parse(Integer value) {
        if (value != null) {
            UCenterResultEnum[] array = values();
            for (UCenterResultEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
