package com.zoeeasy.cloud.core.cst;

/**
 * 平台菜单名称常量定义
 *
 * @author walkman
 * @date 2018-08-28
 */
public class CloudPageNames {

    private CloudPageNames() {
    }

    public static class App {
        private App() {
        }

        /**
         * 公共菜单
         */
        public static class Common {

            public static final String ADMINISTRATION = "Administration";
            public static final String ADMINISTRATION_DASHBOARD = "首页";

            /*          人员管理                                */
            public static final String ADMINISTRATION_USER = "人员管理";
            public static final String ADMINISTRATION_USER_ORGANIZATION = "部门管理";
            public static final String ADMINISTRATION_USER_EMPOLYEES = "用户管理";

            //权限管理
            public static final String ADMINISTRATION_USER_PERMISSION = "权限管理";
            //角色管理
            public static final String ADMINISTRATION_USER_ROLE = "角色管理";
            //菜单管理
            public static final String ADMINISTRATION_USER_MENU = "菜单管理";

            /*   系统管理        */
            public static final String ADMINISTRATION_SYSTEM = "系统管理";
            //字典管理
            public static final String ADMINISTRATION_SYSTEM_DICTIONARY = "字典管理";
            //参数配置
            public static final String ADMINISTRATION_SYSTEM_SETTING = "参数配置";
            //操作日志
            public static final String ADMINISTRATION_SYSTEM_AUDITLOG = "操作日志";
            //登录日志
            public static final String ADMINISTRATION_SYSTEM_LOGINLOG = "登录日志";
        }

        /**
         * 平台特定菜单
         */
        public static class Host {
            private Host() {
            }

            //商户管理
            public static final String ADMINISTRATION_TENANT = "商户管理";
            //停车场管理
            public static final String ADMINISTRATION_PARKING = "停车场管理";
            public static final String ADMINISTRATION_PARKING_MANAGER = "停车场管理";
            //停车场审核
            public static final String ADMINISTRATION_PARKING_APPROVE = "停车场审核";
        }

        /**
         * 租户特定菜单
         */
        public static class Tenant {
            private Tenant() {
            }

            /*                    基础数据                       */
            public static final String ADMINISTRATION_BASIC = "基础数据";
            public static final String ADMINISTRATION_BASIC_AREA = "区域管理";
            public static final String ADMINISTRATION_BASIC_PARKING_INFO = "停车场管理";
            public static final String ADMINISTRATION_BASIC_PARKING_LOT_INFO = "泊位管理";
            public static final String ADMINISTRATION_BASIC_PARKING_GATE_INFO = "出入口管理";
            public static final String ADMINISTRATION_BASIC_PARKING_LANE_INFO = "车道管理";
            public static final String ADMINISTRATION_BASIC_PARKING_REGION = "停车区域管理";
            public static final String ADMINISTRATION_BASIC_PARKING_GARAGE = "车库管理";

            /*   运营管理        */
            public static final String ADMINISTRATION_OPERATION = "运营管理";
            //业务管理
            public static final String ADMINISTRATION_OPERATION_BUSSINESS = "业务管理";
            public static final String ADMINISTRATION_OPERATION_BUSSINESS_PASSING = "过车记录";
            public static final String ADMINISTRATION_OPERATION_BUSSINESS_PARKING = "在场车辆";
            public static final String ADMINISTRATION_OPERATION_BUSSINESS_RECORD = "停车记录";
            //收费配置
            public static final String ADMINISTRATION_OPERATION_CHARGE = "收费配置";
            public static final String ADMINISTRATION_OPERATION_CHARGE_HOLIDAY = "节假日管理";
            public static final String ADMINISTRATION_OPERATION_CHARGE_RULE = "收费规则";
            public static final String ADMINISTRATION_OPERATION_COLLECTOR_ROADWAY = "路边收费员";
            //排班管理
            public static final String ADMINISTRATION_OPERATION_SCHEDULE = "排班管理";
            public static final String ADMINISTRATION_OPERATION_SCHEDULE_SETTION = "排班配置";
            public static final String ADMINISTRATION_OPERATION_SCHEDULE_MANUAL = "手动排班";
            public static final String ADMINISTRATION_OPERATION_SCHEDULE_HISTORY = "历史排班";
            //巡查管理
            public static final String ADMINISTRATION_OPERATION_INSPECT = "巡查管理";
            public static final String ADMINISTRATION_OPERATION_INSPECT_INSPECTOR = "路边巡检员";
            public static final String ADMINISTRATION_OPERATION_INSPECT_SIGN = "签到记录";
            public static final String ADMINISTRATION_OPERATION_INSPECT_RECORD = "巡查记录";
            public static final String ADMINISTRATION_OPERATION_INSPECT_SWITCH = "交接班记录";
            //预定管理
            public static final String ADMINISTRATION_OPERATION_APPOINT = "预定管理";
            public static final String ADMINISTRATION_OPERATION_APPOINT_RULE = "预定规则";
            public static final String ADMINISTRATION_OPERATION_APPOINT_ORDER = "预定订单";
            //订单管理
            public static final String ADMINISTRATION_OPERATION_ORDER = "订单管理";
            //车辆管理
            public static final String ADMINISTRATION_OPERATION_VEHICLE = "车辆管理";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_PACKETRULE = "包期规则";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_PACKETMANAGER = "包期车管理";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_PACKETCANCEL = "包期取消";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_FIXED = "固定车";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_BLACKLIST = "黑名单";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_WHITELIST = "白名单";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_VISITLIST = "访客车";

            //财务管理
            public static final String ADMINISTRATION_OPERATION_FINANCIAL = "财务管理";
            public static final String ADMINISTRATION_OPERATION_FINANCIAL_RULE = "";
            public static final String ADMINISTRATION_OPERATION_FINANCIAL_ORDER = "";
            //发票管理
            public static final String ADMINISTRATION_OPERATION_INVOICE = "发票管理";
            public static final String ADMINISTRATION_OPERATION_INVOICE_APPLY = "发票申请";
            public static final String ADMINISTRATION_OPERATION_INVOICE_HISTORY = "历史开票";
            //报表管理
            public static final String ADMINISTRATION_OPERATION_REPORT = "报表管理";
            public static final String ADMINISTRATION_OPERATION_REPORT_PARKING = "停车效率分析";
            public static final String ADMINISTRATION_OPERATION_REPORT_OPERATION = "运营效率分析";

            /*   设备管理        */
            public static final String ADMINISTRATION_DEVICE = "设备管理";
            //设备管理
            public static final String ADMINISTRATION_DEVICE_MANAGER = "设备管理";
            public static final String ADMINISTRATION_DEVICE_MANAGER_MAGNETIC = "地磁管理器";
            public static final String ADMINISTRATION_DEVICE_MANAGER_DETECTOR = "地磁检测器";
            public static final String ADMINISTRATION_DEVICE_MANAGER_CAMERA = "视频设备";
            //设备配置
            public static final String ADMINISTRATION_DEVICE_SETTION = "设备配置";
            public static final String ADMINISTRATION_DEVICE_SETTION_PARKING = "停车场配置";
            public static final String ADMINISTRATION_DEVICE_SETTION_PARKINGLOT = "泊位配置";
            public static final String ADMINISTRATION_DEVICE_SETTION_CATE = "出入口配置";

            //设备运营管理
            public static final String ADMINISTRATION_DEVICE_OPERATION = "设备运营管理";
            public static final String ADMINISTRATION_DEVICE_OPERATION_DETECTRECORD = "地磁检测记录";
            public static final String ADMINISTRATION_DEVICE_OPERATION_MANAGERSSTATUS = "地磁管理器状态";
            public static final String ADMINISTRATION_DEVICE_OPERATION_DETECTORSTATUS = "地磁检测器状态";
            public static final String ADMINISTRATION_DEVICE_OPERATION_MANAGEREXCEPTION = "地磁管理器异常";
            public static final String ADMINISTRATION_DEVICE_OPERATION_DETECTOREXCEPTION = "地磁检测器异常";
            public static final String ADMINISTRATION_DEVICE_OPERATION_CAMERARECORD = "视频检测记录";
        }
    }
}
