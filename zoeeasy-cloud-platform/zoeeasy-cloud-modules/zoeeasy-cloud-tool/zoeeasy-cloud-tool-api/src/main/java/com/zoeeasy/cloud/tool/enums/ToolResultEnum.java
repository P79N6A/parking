package com.zoeeasy.cloud.tool.enums;

/**
 * TOOL结果枚举
 *
 * @author zwq
 */
public enum ToolResultEnum {

    //区域
    REGION_CODE_EXISTS(100100, "区域代码已经存在"),
    REGION_NAME_EXISTS(100101, "区域名称已经存在"),
    REGION_CODE_EMPTY(100102, "区域代码为空"),
    REGION_NAME_EMPTY(100103, "区域名称为空"),
    REGION_NOT_FOUND(100104, "区域不存在"),
    REGION_LEVEL_EMPTY(100105, "区域等级为空"),
    REGION_CITY_NAME_EMPTY(100106, "城市名称为空"),
    REGION_LEVEL_OUT(100107, "区域超出层级"),

    //车辆违章信息查询
    VEHICLE_VIOLATION_SYSTEM_ERROR(100200, "系统异常"),
    VEHICLE_VIOLATION_INVALID_PARAMETER(100201, "请求参数错误"),
    VEHICLE_VIOLATION_QUERY_PARAMETER_INVALID(100202, "违章查询请求参数错误"),
    VEHICLE_VIOLATION_QUERY_TOO_MUCH(100203, "API接口调用过于频繁"),
    VEHICLE_VIOLATION_QUERY_FAILED(100204, "查询失败 交管接口网络异常"),
    VEHICLE_VIOLATION_QUERY_WAITING(100205, "请稍后再试 交管接口正在繁忙"),
    VEHICLE_VIOLATION_CLOSED(100206, " 官方接口维护中 交管接口暂时关闭"),
    VEHICLE_VIOLATION_EXTERNAL_NOT_SUPPORT(100207, "该接口不支持异地车牌查询 不支持异地查询"),
    VEHICLE_VIOLATION_VEHICLE_INFO_ERROR(100208, "车辆不存在或信息错误"),
    VEHICLE_VIOLATION_ENGINE_NUMBER_ERROR(100209, "发动机号错误"),
    VEHICLE_VIOLATION_VEHICLE_NUMBER_ERROR(100210, "车架号错误"),
    VEHICLE_VIOLATION_PLATE_NUMBER_ERROR(100211, "车牌格式错误"),
    VEHICLE_VIOLATION_CITY_NOT_SUPPORT(100212, "查询城市不支持或暂时关闭"),
    VEHICLE_VIOLATION_NUMBER_LENGTH_ERROR(100213, "车架号或发动机号位数错误"),

    //天气
    ADCODE_EMPTY(100300, "adcode为空"),
    LOCATION_EMPTY(100301, "location为空"),
    REQUEST_PARAM_EMPTY(100302, "请求参数为空"),
    GET_ADCODE_ERR(100303, "获取adcode失败"),
    LOCATION_LATITUDE_EMPTY(100304, "坐标纬度为空"),
    LOCATION_LONGITUDE_EMPTY(100305, "坐标经度为空"),
    LOCATION_LONGITUDE_LATITUDE_EMPTY(100306, "坐标经纬度为空"),
    LOCATION_CONVERT_FAILED(100307, "坐标转换失败"),
    WEATHER_INFO_EMPTY(100308, "天气信息为空"),
    GET_WEATHER_INFO_ERROR(100309, "获取天气信息失败"),
    OIL_PRICE_REQUEST_ERR(100310, "油价获取失败"),
    OIL_PRICE_EMPTY(100311, "油价信息为空"),
    ADCODE_EMPTY_OR_LOCATION_LONGITUDE_LATITUDE_EMPTY(100312, "经纬度和adCode不能同时为空"),

    //尾号限行
    TRAFFIC_RESTRICTION_CITY_EMPTY(100400, "限行城市为空"),
    TRAFFIC_RESTRICTION_CITY_EMPTY_BY_JUHE(100401, "调三方查询城市的限行情况失败"),
    TRAFFIC_RESTRICTION_NOT_SUPPORT(100402, "当前城市不支持限行"),
    TRAFFIC_RESTRICTION_GET_BY_LOCAL(100403, "从本地获取限行信息为空"),
    TRAFFIC_RESTRICTION_REQUEST_PARAM_EMPTY(100404, "限行请求参数为空"),
    TRAFFIC_RESTRICTION_CITY_HAS_POLICY(100405, "该城市还存在限行政策,不能删除"),
    TRAFFIC_RESTRICTION_CITY_NOT_EXIST(100406, "限行城市不存在"),

    //车牌前缀
    LICENSE_PREFIX_NAME_EMPTY(100500, "名称为空"),
    LICENSE_PREFIX_PARENTNUM_EXISTS(100501, "车牌前缀已经存在"),
    LICENSE_PREFIX_CHILDREN_EXISTS(100502, "存在子车牌前缀，不允许删除"),
    LICENSE_PREFIX_NOT_FOUND(100503, "车牌前缀不存在"),
    LICENSE_PREFIX_TYPE_EMPTY(100504, "类型为空"),
    LICENSE_PREFIX_TYPE_ERR(100505, "类型错误"),
    LICENSE_PREFIX_TYPE_ERROR(100506, "前缀不能有父ID"),

    //驾驶证扣分查询
    LICENSE_POINT_REQUEST_ERROR(100600, "获取驾驶证扣分失败"),
    //行驶证
    LICENSE_RECOGNITION_FAIL(100700, "行驶证识别失败"),
    //身份证行驶证识别
    IMAGE_EMPTY(100701, "图片输入流为空"),
    ID_CARD_FACE_VERIFY_FAIL(100702, "身份证正面识别失败"),
    ID_CARD_BACK_VERIFY_FAIL(100702, "身份证背面识别失败"),
    ID_CARD_TYPE_ERROR(100703, "身份证号码错误"),

    //版本记录
    VERSION_ERR(100801, "版本状态有误"),
    TERMINATE_TYPE_ERROR(100802, "客户端类型有误"),
    VERSION_NOT_EXISTS(100803, "版本信息不存在"),
    FILE_UPLOAD_ERR(100804, "文件上传失败"),
    FORCETYPE_ERR(100805, "是否强制更新字段有误"),
    VERSION_EXISTS(100806, "版本信息已存在"),
    VERSION_UNSHELVE(100807, "版本已下架不支持修改"),
    TEMPLATE_UNDEFINED(100808, "消息模板未指定"),
    TEMPLATE_NOT_EXIST(100809, "消息模板不存在"),
    //百度语音识别
    SPEECH_FILE_EMPTY(100900, "语音文件为空"),
    SPEECH_CANNOT_RECOGNITION(100901, "没有听清，请再说一遍"),
    PUSH_DEVICE_REGISTRATION_ID_EMPTY(101000, "registrationId不能为空"),
    PUSH_DEVICE_REGISTER_FAILED(101001, "设备注册失败"),
    PUSH_DEVICE_BIND_FAILED(101002, "设备绑定失败"),

    //充电桩
    GET_GES_INFO_ERROR(130000, "拉取数据失败"),

    //油价查询
    OIL_PRICE_PROVINCE_EMPTY(140000, "油价查询城市为空"),
    OIL_PRICE_DETAIL_EMPTY(140001, "油价详情为空"),

    //文件上传
    IMAGE_TYPE_ERROR(150001, "图片文件格式错误");
//    3300	用户输入错误	输入参数不正确	请仔细核对文档及参照demo，核对输入参数
//3301	用户输入错误	音频质量过差	请上传清晰的音频
//3302	用户输入错误	鉴权失败	token字段校验失败。请使用正确的API_KEY 和 SECRET_KEY生成
//3303	服务端问题	语音服务器后端问题	请将api返回结果反馈至论坛或者QQ群
//3304	用户请求超限	用户的请求QPS超限	请降低识别api请求频率 （qps以appId计算，移动端如果共用则累计）
//            3305	用户请求超限	用户的日pv（日请求量）超限	请“申请提高配额”，如果暂未通过，请降低日请求量
//3307	服务端问题	语音服务器后端识别出错问题	目前请确保16000的采样率音频时长低于30s，8000的采样率音频时长低于60s。如果仍有问题，请将api返回结果反馈至论坛或者QQ群
//3308	用户输入错误	音频过长	音频时长不超过60s，请将音频时长截取为60s以下
//3309	用户输入错误	音频数据问题	服务端无法将音频转为pcm格式，可能是长度问题，音频格式问题等。 请将输入的音频时长截取为60s以下，并核对下音频的编码，是否是8K或者16K， 16bits，单声道。
//            3310	用户输入错误	输入的音频文件过大	语音文件共有3种输入方式： json 里的speech 参数（base64后）； 直接post 二进制数据，及callback参数里url。 分别对应三种情况：json超过10M；直接post的语音文件超过10M；callback里回调url的音频文件超过10M
//3311	用户输入错误	采样率rate参数不在选项里	目前rate参数仅提供8000,16000两种，填写4000即会有此错误
//3312	用户输入错误	音频格式format参数不在选项里	目前格式仅仅支持pcm，wav或amr，如填写mp3即会有此错误

    /**
     * 值
     */
    private Integer value;

    /**
     * 注释
     */
    private String comment;

    ToolResultEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static ToolResultEnum parse(Integer value) {
        if (value != null) {
            ToolResultEnum[] array = values();
            for (ToolResultEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getComment() {
        return this.comment;
    }
}
