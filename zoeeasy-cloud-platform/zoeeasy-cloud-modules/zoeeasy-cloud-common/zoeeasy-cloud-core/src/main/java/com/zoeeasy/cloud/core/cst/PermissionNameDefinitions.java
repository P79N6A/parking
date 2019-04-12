package com.zoeeasy.cloud.core.cst;

/**
 * 全局权限定义
 *
 * @author walkman
 * @date 2018-08-28
 */
public final class PermissionNameDefinitions {

    /**
     * 权限分隔符
     */
    private static final String PERMISSION_SEPARATOR = ":";
    private static final String VIEW = "查看";
    private static final String CREATE = "添加";
    private static final String EDIT = "编辑";
    private static final String DELETE = "删除";
    private static final String LOCK = "锁定";
    private static final String UNLOCK = "解锁";
    private static final String TOP_UP = "更改充值状态";
    private static final String APPLY = "申请";
    private static final String APPROVE = "审核";
    private static final String RELEVANCE_PARKINGLOT = "关联泊位";
    private static final String RELEVANCE_DETECTOR = "关联地磁检测器";

    private PermissionNameDefinitions() {

    }

    /**
     * 公共权限
     */
    public static class Common {
        private Common() {
        }

        //COMMON PERMISSIONS (FOR BOTH OF TENANTS AND HOST)
        public static final String PAGES = "pages";
        public static final String PAGES_ADMIN = PAGES + PERMISSION_SEPARATOR + "administration";

        /*          人员管理                                */
        public static final String ADMIN_USER = "人员管理";

        //部门
        public static final String ADMIN_USER_ORGANIZATION = "部门管理";
        public static final String ADMIN_USER_ORGANIZATION_CREATE = CREATE;
        public static final String ADMIN_USER_ORGANIZATION_VIEW = VIEW;
        public static final String ADMIN_USER_ORGANIZATION_EDIT = EDIT;
        public static final String ADMIN_USER_ORGANIZATION_DELETE = DELETE;

        //用户
        public static final String ADMIN_USER_EMPLOYEE = "用户管理";
        public static final String ADMIN_USER_EMPLOYEE_CREATE = CREATE;
        public static final String ADMIN_USER_EMPLOYEE_VIEW = VIEW;
        public static final String ADMIN_USER_EMPLOYEE_EDIT = EDIT;
        public static final String ADMIN_USER_EMPLOYEE_DELETE = DELETE;

        //角色
        public static final String ADMIN_USER_ROLE = "角色管理";
        public static final String ADMIN_USER_ROLE_CREATE = CREATE;
        public static final String ADMIN_USER_ROLE_VIEW = VIEW;
        public static final String ADMIN_USER_ROLE_EDIT = EDIT;
        public static final String ADMIN_USER_ROLE_DELETE = DELETE;

        //权限管理
        public static final String ADMIN_USER_PERMISSION = "权限管理";
        //菜单管理
        public static final String ADMIN_USER_MENU = "菜单管理";

        /*   系统管理        */
        public static final String ADMIN_SYSTEM = "系统管理";
        //字典管理
        public static final String ADMIN_SYSTEM_DICTIONARY = "字典管理";
        //参数配置
        public static final String ADMIN_SYSTEM_SETTING = "参数配置";
        //操作日志
        public static final String ADMIN_SYSTEM_AUDITLOG = "操作日志";
        //登录日志
        public static final String ADMIN_SYSTEM_LOGINLOG = "登录日志";
    }

    /**
     * 公共权限
     */
    public static class Host {
        private Host() {
        }

        /*          商户管理                                */
        public static final String ADMIN_TENANT = "商户管理";
        public static final String ADMIN_TENANT_CREATE = CREATE;
        public static final String ADMIN_TENANT_EDIT = EDIT;
        public static final String ADMIN_TENANT_VIEW = VIEW;
        public static final String ADMIN_TENANT_DELETE = DELETE;
    }

    /**
     * 租户特定权限
     */
    public static class Tenant {
        private Tenant() {
        }

        public static final String ADMIN_DASHBOARD = "首页";

        /*                    基础数据                       */
        public static final String ADMIN_BASIC = "基础数据";

        /*区域*/
        public static final String ADMIN_BASIC_AREA = "区域管理";
        public static final String ADMIN_BASIC_AREA_CREATE = CREATE;
        public static final String ADMIN_BASIC_AREA_EDIT = EDIT;
        public static final String ADMIN_BASIC_AREA_VIEW = VIEW;
        public static final String ADMIN_BASIC_AREA_DELETE = DELETE;

        /*停车场*/
        public static final String ADMIN_BASIC_PARKING_INFO = "停车场管理";
        public static final String ADMIN_BASIC_PARKING_INFO_CREATE = CREATE;
        public static final String ADMIN_BASIC_PARKING_INFO_EDIT = EDIT;
        public static final String ADMIN_BASIC_PARKING_INFO_VIEW = VIEW;
        public static final String ADMIN_BASIC_PARKING_INFO_DELETE = DELETE;

        /*停车区域*/
        public static final String ADMIN_BASIC_PARKING_REGION = "停车区域管理";
        public static final String ADMIN_BASIC_PARKING_REGION_CREATE = CREATE;
        public static final String ADMIN_BASIC_PARKING_REGION_EDIT = EDIT;
        public static final String ADMIN_BASIC_PARKING_REGION_VIEW = VIEW;
        public static final String ADMIN_BASIC_PARKING_REGION_DELETE = DELETE;

        /*出入口*/
        public static final String ADMIN_BASIC_PARKING_GATE = "出入口管理";
        public static final String ADMIN_BASIC_PARKING_GATE_CREATE = CREATE;
        public static final String ADMIN_BASIC_PARKING_GATE_EDIT = EDIT;
        public static final String ADMIN_BASIC_PARKING_GATE_VIEW = VIEW;
        public static final String ADMIN_BASIC_PARKING_GATE_DELETE = DELETE;

        /*车道*/
        public static final String ADMIN_BASIC_PARKING_LANE = "车道管理";
        public static final String ADMIN_BASIC_PARKING_LANE_CREATE = CREATE;
        public static final String ADMIN_BASIC_PARKING_LANE_EDIT = EDIT;
        public static final String ADMIN_BASIC_PARKING_LANE_VIEW = VIEW;
        public static final String ADMIN_BASIC_PARKING_LANE_DELETE = DELETE;

        /*车库*/
        public static final String ADMIN_BASIC_PARKING_GARAGE = "车库管理";
        public static final String ADMIN_BASIC_PARKING_GARAGE_CREATE = CREATE;
        public static final String ADMIN_BASIC_PARKING_GARAGE_EDIT = EDIT;
        public static final String ADMIN_BASIC_PARKING_GARAGE_VIEW = VIEW;
        public static final String ADMIN_BASIC_PARKING_GARAGE_DELETE = DELETE;

        /*泊位*/
        public static final String ADMIN_BASIC_PARKING_LOT = "泊位管理";
        public static final String ADMIN_BASIC_PARKING_LOT_CREATE = CREATE;
        public static final String ADMIN_BASIC_PARKING_LOT_EDIT = EDIT;
        public static final String ADMIN_BASIC_PARKING_LOT_VIEW = VIEW;
        public static final String ADMIN_BASIC_PARKING_LOT_DELETE = DELETE;

        /*   运营管理        */
        public static final String ADMIN_OPERATION = "运营管理";
        //业务管理
        public static final String ADMIN_OPERATION_BUSINESS = "业务管理";
        //过车记录
        public static final String ADMIN_OPERATION_BUSINESS_PASSING = "过车记录";
        public static final String ADMIN_OPERATION_BUSINESS_PASSING_VIEW = VIEW;
        public static final String ADMIN_OPERATION_BUSINESS_PASSING_EDIT = EDIT;
        //在场车辆
        public static final String ADMIN_OPERATION_BUSINESS_PARKING = "在场车辆";
        public static final String ADMIN_OPERATION_BUSINESS_PARKING_VIEW = VIEW;
        //停车记录
        public static final String ADMIN_OPERATION_BUSINESS_RECORD = "停车记录";
        public static final String ADMIN_OPERATION_BUSINESS_RECORD_VIEW = VIEW;

        //收费配置
        public static final String ADMIN_OPERATION_CHARGE = "收费配置";
        //路边收费员
        public static final String ADMIN_OPERATION_CHARGE_ROADWAY = "路边收费员";
        public static final String ADMIN_OPERATION_CHARGE_ROADWAY_CREATE = CREATE;
        public static final String ADMIN_OPERATION_CHARGE_ROADWAY_EDIT = EDIT;
        public static final String ADMIN_OPERATION_CHARGE_ROADWAY_VIEW = VIEW;
        public static final String ADMIN_OPERATION_CHARGE_ROADWAY_DELETE = DELETE;
        public static final String ADMIN_OPERATION_CHARGE_ROADWAY_LOCK = LOCK;
        public static final String ADMIN_OPERATION_CHARGE_ROADWAY_UNLOCK = UNLOCK;
        //假期配置
        public static final String ADMIN_OPERATION_CHARGE_HOLIDAY = "假期配置";
        public static final String ADMIN_OPERATION_CHARGE_HOLIDAY_EDIT = EDIT;
        public static final String ADMIN_OPERATION_CHARGE_HOLIDAY_VIEW = VIEW;
        public static final String ADMIN_OPERATION_CHARGE_HOLIDAY_DELETE = DELETE;
        //收费规则
        public static final String ADMIN_OPERATION_CHARGE_RULE = "收费规则";
        public static final String ADMIN_OPERATION_CHARGE_RULE_CREATE = CREATE;
        public static final String ADMIN_OPERATION_CHARGE_RULE_EDIT = EDIT;
        public static final String ADMIN_OPERATION_CHARGE_RULE_VIEW = VIEW;
        public static final String ADMIN_OPERATION_CHARGE_RULE_DELETE = DELETE;
        //排班管理
        public static final String ADMIN_OPERATION_SCHEDULE = "排班管理";
        //排班配置
        public static final String ADMIN_OPERATION_SCHEDULE_SETTING = "排班配置";
        public static final String ADMIN_OPERATION_SCHEDULE_SETTING_CREATE = CREATE;
        public static final String ADMIN_OPERATION_SCHEDULE_SETTING_EDIT = EDIT;
        public static final String ADMIN_OPERATION_SCHEDULE_SETTING_VIEW = VIEW;
        public static final String ADMIN_OPERATION_SCHEDULE_SETTING_DELETE = DELETE;
        //手动排班
        public static final String ADMIN_OPERATION_SCHEDULE_MANUAL = "手动排班";
        public static final String ADMIN_OPERATION_SCHEDULE_MANUAL_CREATE = CREATE;
        public static final String ADMIN_OPERATION_SCHEDULE_MANUAL_EDIT = EDIT;
        public static final String ADMIN_OPERATION_SCHEDULE_MANUAL_VIEW = VIEW;
        public static final String ADMIN_OPERATION_SCHEDULE_MANUAL_DELETE = DELETE;
        //历史排班
        public static final String ADMIN_OPERATION_SCHEDULE_HISTORY = "历史排班";
        public static final String ADMIN_OPERATION_SCHEDULE_HISTORY_VIEW = VIEW;
        //巡查管理
        public static final String ADMIN_OPERATION_INSPECT = "巡查管理";

        //路边巡检员
        public static final String ADMIN_OPERATION_INSPECTOR_ROADWAY = "路边巡检员";
        public static final String ADMIN_OPERATION_INSPECTOR_ROADWAY_CREATE = CREATE;
        public static final String ADMIN_OPERATION_INSPECTOR_ROADWAY_EDIT = EDIT;
        public static final String ADMIN_OPERATION_INSPECTOR_ROADWAY_VIEW = VIEW;
        public static final String ADMIN_OPERATION_INSPECTOR_ROADWAY_DELETE = DELETE;
        //签到记录
        public static final String ADMIN_OPERATION_INSPECT_SINGIN = "签到记录";
        public static final String ADMIN_OPERATION_INSPECT_SINGIN_VIEW = VIEW;
        //巡查记录
        public static final String ADMIN_OPERATION_INSPECT_RECORD = "巡查记录";
        public static final String ADMIN_OPERATION_INSPECT_RECORD_VIEW = VIEW;
        //交接班记录
        public static final String ADMIN_OPERATION_INSPECT_SWITCH = "交接班记录";
        public static final String ADMIN_OPERATION_INSPECT_SWITCH_VIEW = VIEW;
        //预定管理
        public static final String ADMIN_OPERATION_APPOINT = "预定管理";
        //预约规则
        public static final String ADMIN_OPERATION_APPOINT_RULE = "预约规则";
        public static final String ADMIN_OPERATION_APPOINT_RULE_CREATE = CREATE;
        public static final String ADMIN_OPERATION_APPOINT_RULE_EDIT = EDIT;
        public static final String ADMIN_OPERATION_APPOINT_RULE_VIEW = VIEW;
        public static final String ADMIN_OPERATION_APPOINT_RULE_DELETE = DELETE;
        //预约订单
        public static final String ADMIN_OPERATION_APPOINT_ORDER = "预约订单";
        public static final String ADMIN_OPERATION_APPOINT_ORDER_VIEW = VIEW;
        public static final String ADMIN_OPERATION_APPOINT_ORDER_EDIT = EDIT;
        //订单管理
        public static final String ADMIN_OPERATION_ORDER = "订单管理";
        public static final String ADMIN_OPERATION_ORDER_CREATE = CREATE;
        public static final String ADMIN_OPERATION_ORDER_EDIT = EDIT;
        public static final String ADMIN_OPERATION_ORDER_VIEW = VIEW;
        public static final String ADMIN_OPERATION_ORDER_DELETE = DELETE;
        //车辆管理
        public static final String ADMIN_OPERATION_VEHICLE = "车辆管理";
        //包期规则
        public static final String ADMIN_OPERATION_VEHICLE_PACKETRULE = "包期规则";
        public static final String ADMIN_OPERATION_VEHICLE_PACKETRULE_CREATE = CREATE;
        public static final String ADMIN_OPERATION_VEHICLE_PACKETRULE_EDIT = EDIT;
        public static final String ADMIN_OPERATION_VEHICLE_PACKETRULE_VIEW = VIEW;
        public static final String ADMIN_OPERATION_VEHICLE_PACKETRULE_DELETE = DELETE;

        //包期管理
        public static final String ADMIN_OPERATION_VEHICLE_PACKETMANAGER = "包期管理";
        public static final String ADMIN_OPERATION_VEHICLE_PACKETMANAGER_CREATE = CREATE;
        public static final String ADMIN_OPERATION_VEHICLE_PACKETMANAGER_VIEW = VIEW;
        public static final String ADMIN_OPERATION_VEHICLE_PACKETMANAGER_TOP_UP = TOP_UP;
        public static final String ADMIN_OPERATION_VEHICLE_PACKETMANAGER_DELETE = DELETE;

        //包期取消
        public static final String ADMIN_OPERATION_VEHICLE_PACKETCANCEL = "包期取消";
        public static final String ADMIN_OPERATION_VEHICLE_PACKETCANCEL_APPLY = APPLY;
        public static final String ADMIN_OPERATION_VEHICLE_PACKETCANCEL_APPROVE = APPROVE;
        public static final String ADMIN_OPERATION_VEHICLE_PACKETCANCEL_VIEW = VIEW;
        public static final String ADMIN_OPERATION_VEHICLE_PACKETCANCEL_DELETE = DELETE;

        //固定车
        public static final String ADMIN_OPERATION_VEHICLE_FIXED = "固定车";
        public static final String ADMIN_OPERATION_VEHICLE_FIXED_CREATE = CREATE;
        public static final String ADMIN_OPERATION_VEHICLE_FIXED_EDIT = EDIT;
        public static final String ADMIN_OPERATION_VEHICLE_FIXED_VIEW = VIEW;
        public static final String ADMIN_OPERATION_VEHICLE_FIXED_DELETE = DELETE;
        public static final String ADMIN_OPERATION_VEHICLE_FIXED_RELEVANCE = RELEVANCE_PARKINGLOT;

        //黑名单
        public static final String ADMIN_OPERATION_VEHICLE_BLACKLIST = "黑名单";
        public static final String ADMIN_OPERATION_VEHICLE_BLACKLIST_CREATE = CREATE;
        public static final String ADMIN_OPERATION_VEHICLE_BLACKLIST_EDIT = EDIT;
        public static final String ADMIN_OPERATION_VEHICLE_BLACKLIST_VIEW = VIEW;
        public static final String ADMIN_OPERATION_VEHICLE_BLACKLIST_DELETE = DELETE;

        //白名单
        public static final String ADMIN_OPERATION_VEHICLE_WHITELIST = "白名单";
        public static final String ADMIN_OPERATION_VEHICLE_WHITELIST_CREATE = CREATE;
        public static final String ADMIN_OPERATION_VEHICLE_WHITELIST_EDIT = EDIT;
        public static final String ADMIN_OPERATION_VEHICLE_WHITELIST_VIEW = VIEW;
        public static final String ADMIN_OPERATION_VEHICLE_WHITELIST_DELETE = DELETE;

        public static final String ADMIN_OPERATION_VEHICLE_VISITLIST = "访客车";
        public static final String ADMIN_OPERATION_VEHICLE_VISITLIST_CREATE = CREATE;
        public static final String ADMIN_OPERATION_VEHICLE_VISITLIST_EDIT = EDIT;
        public static final String ADMIN_OPERATION_VEHICLE_VISITLIST_VIEW = VIEW;
        public static final String ADMIN_OPERATION_VEHICLE_VISITLIST_DELETE = DELETE;

        //财务管理
        public static final String ADMIN_OPERATION_FINANCIAL = "财务管理";
        public static final String ADMIN_OPERATION_FINANCIAL_RULE = ADMIN_OPERATION_FINANCIAL + PERMISSION_SEPARATOR + "rule";
        public static final String ADMIN_OPERATION_FINANCIAL_ORDER = ADMIN_OPERATION_FINANCIAL + PERMISSION_SEPARATOR + "order";
        //发票管理
        public static final String ADMIN_OPERATION_INVOICE = "发票管理";
        public static final String ADMIN_OPERATION_INVOICE_APPLY = "开票申请";
        public static final String ADMIN_OPERATION_INVOICE_HISTORY = "开票历史";
        //报表管理
        public static final String ADMIN_OPERATION_REPORT = "报表管理";
        public static final String ADMIN_OPERATION_REPORT_PARKING = "停车效率";
        public static final String ADMIN_OPERATION_REPORT_OPERATING = "运营效率";

        /*   设备管理        */
        public static final String ADMIN_DEVICE = "设备管理";
        //设备管理
        public static final String ADMIN_DEVICE_MANAGER = "地磁设备管理";
        //地磁管理器
        public static final String ADMIN_DEVICE_MANAGER_MAGNETIC = "地磁管理器管理";
        public static final String ADMIN_DEVICE_MANAGER_MAGNETIC_CREATE = CREATE;
        public static final String ADMIN_DEVICE_MANAGER_MAGNETIC_EDIT = EDIT;
        public static final String ADMIN_DEVICE_MANAGER_MAGNETIC_VIEW = VIEW;
        public static final String ADMIN_DEVICE_MANAGER_MAGNETIC_DELETE = DELETE;
        public static final String ADMIN_DEVICE_MANAGER_MAGNETIC_RELEVANCE = RELEVANCE_DETECTOR;

        //地磁检测器
        public static final String ADMIN_DEVICE_MANAGER_DETECTOR = "地磁检测器管理";
        public static final String ADMIN_DEVICE_MANAGER_DETECTOR_CREATE = CREATE;
        public static final String ADMIN_DEVICE_MANAGER_DETECTOR_EDIT = EDIT;
        public static final String ADMIN_DEVICE_MANAGER_DETECTOR_VIEW = VIEW;
        public static final String ADMIN_DEVICE_MANAGER_DETECTOR_DELETE = DELETE;

        public static final String ADMIN_DEVICE_MANAGER_CAMERA = "视频设备管理";
        //设备配置
        public static final String ADMIN_DEVICE_SETTING = "设备配置";
        public static final String ADMIN_DEVICE_SETTING_PARKING = "停车场配置";
        //泊位配置
        public static final String ADMIN_DEVICE_SETTING_PARKINGLOT = "泊位配置";
        public static final String ADMIN_DEVICE_SETTING_PARKINGLOT_VIEW = VIEW;
        public static final String ADMIN_DEVICE_SETTING_PARKINGLOT_RELEVANCE = RELEVANCE_DETECTOR;

        public static final String ADMIN_DEVICE_SETTING_GATE = "出入口配置";

        //设备运营管理
        public static final String ADMIN_DEVICE_OPERATION = "设备运营管理";
        //地磁检测记录
        public static final String ADMIN_DEVICE_OPERATION_DETECTRECORD = "地磁检测记录";
        public static final String ADMIN_DEVICE_OPERATION_DETECTRECORD_VIEW = VIEW;

        public static final String ADMIN_DEVICE_OPERATION_MANAGERSTATUS = "地磁管理器状态";
        //地磁检测状态
        public static final String ADMIN_DEVICE_OPERATION_DETECTORSTATUS = "地磁检测器状态";
        public static final String ADMIN_DEVICE_OPERATION_DETECTORSTATUS_VIEW = VIEW;

        public static final String ADMIN_DEVICE_OPERATION_MANAGEREXCEPTION = "管理器异常";
        //地磁检测异常
        public static final String ADMIN_DEVICE_OPERATION_DETECTOREXCEPTION = "检测器异常";
        public static final String ADMIN_DEVICE_OPERATION_DETECTOREXCEPTION_VIEW = VIEW;

        public static final String ADMIN_DEVICE_OPERATION_CAMERARECORD = "视频检测记录";
    }

}
