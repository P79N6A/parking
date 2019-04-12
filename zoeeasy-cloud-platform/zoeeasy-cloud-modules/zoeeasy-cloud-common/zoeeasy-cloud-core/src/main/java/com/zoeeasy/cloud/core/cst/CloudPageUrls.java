package com.zoeeasy.cloud.core.cst;

/**
 * 平台菜单Url常量定义
 *
 * @author walkman
 * @since 2018-08-28
 */
public class CloudPageUrls {

    public static class App {

        /**
         * 公共菜单
         */
        public static class Common {

            public static final String ADMINISTRATION = "administration";
            //首页
            public static final String DASHBOARD = "administration/dashboard";

            /*          人员管理                                */
            public static final String ADMINISTRATION_USER = "administration/user";
            //部门管理
            public static final String ADMINISTRATION_USER_ORGANIZATION = "administration/user/organization";
            //用户管理
            public static final String ADMINISTRATION_USER_EMPLOYEES = "administration/user/employees";
            //角色管理
            public static final String ADMINISTRATION_USER_ROLE = "administration/user/role";
            //权限管理
            public static final String ADMINISTRATION_USER_PERMISSION = "administration/user/permission";
            //菜单管理
            public static final String ADMINISTRATION_USER_MENU = "administration/user/menu";

            /*   系统管理        */
            public static final String ADMINISTRATION_SYSTEM = "administration/system";
            //字典管理
            public static final String ADMINISTRATION_SYSTEM_DICTIONARY = "administration/system/dictionary";
            //参数配置
            public static final String ADMINISTRATION_SYSTEM_SETTION = "administration/system/setting";
            //操作日志
            public static final String ADMINISTRATION_SYSTEM_AUDITLOG = "administration/system/auditLog";
            //登录日志
            public static final String ADMINISTRATION_SYSTEM_LOGINLOG = "administration/system/loginLog";
        }

        /**
         * 平台特定菜单
         */
        public static class Host {
            private Host() {
            }

            /*          商户管理                                */
            public static final String ADMINISTRATION_TENANT = "administration/tenant";
            //停车场管理
            public static final String ADMINISTRATION_PARKING = "administration/parking";
            public static final String ADMINISTRATION_PARKING_MANAGER = "administration/parking/manager";
            //停车场审核
            public static final String ADMINISTRATION_PARKING_APPROVE = "administration/parking/approve";
        }

        /**
         * 租户特定菜单
         */
        public static class Tenant {
            private Tenant() {
            }

            /*                    基础数据                       */
            public static final String ADMINISTRATION_BASIC = "administration/basic";
            public static final String ADMINISTRATION_BASIC_AREA = "administration/basic/area";
            public static final String ADMINISTRATION_BASIC_PARKING_INFO = "administration/basic/parkingInfo";
            public static final String ADMINISTRATION_BASIC_PARKING_REGION = "administration/basic/parkingRegion";
            public static final String ADMINISTRATION_BASIC_PARKING_GATE_INFO = "administration/basic/parkingGateInfo";
            public static final String ADMINISTRATION_BASIC_PARKING_LANE_INFO = "administration/basic/parkingLaneInfo";
            public static final String ADMINISTRATION_BASIC_PARKING_GARAGE = "administration/basic/parkingGarage";
            public static final String ADMINISTRATION_BASIC_PARKING_LOT_INFO = "administration/basic/parkingLotInfo";

            /*   运营管理        */
            public static final String ADMINISTRATION_OPERATION = "administration/operation";
            //业务管理
            public static final String ADMINISTRATION_OPERATION_BUSSINESS = "administration/operation/business";
            public static final String ADMINISTRATION_OPERATION_BUSSINESS_PASSING = "administration/operation/business/passing";
            public static final String ADMINISTRATION_OPERATION_BUSSINESS_PARKING = "administration/operation/business/parking";
            public static final String ADMINISTRATION_OPERATION_BUSSINESS_RECORD = "administration/operation/business/record";
            //收费配置
            public static final String ADMINISTRATION_OPERATION_COLLECTOR_ROADWAY = "administration/user/employees/collector";
            public static final String ADMINISTRATION_OPERATION_CHARGE = "administration/operation/charge";
            public static final String ADMINISTRATION_OPERATION_CHARGE_HOLIDAY = "administration/operation/charge/holiday";
            public static final String ADMINISTRATION_OPERATION_CHARGE_RULE = "administration/operation/charge/rule";
            //排班管理
            public static final String ADMINISTRATION_OPERATION_SCHEDULE = "administration/operation/schedule";
            public static final String ADMINISTRATION_OPERATION_SCHEDULE_SETTION = "administration/operation/schedule/setting";
            public static final String ADMINISTRATION_OPERATION_SCHEDULE_MANUAL = "administration/operation/schedule/manual";
            public static final String ADMINISTRATION_OPERATION_SCHEDULE_HISTORY = "administration/operation/schedule/history";
            //巡查管理
            public static final String ADMINISTRATION_OPERATION_INSPECT = "administration/operation/inspect";
            public static final String ADMINISTRATION_OPERATION_INSPECT_INSPECTOR = "administration/operation/inspect/inspector";
            public static final String ADMINISTRATION_OPERATION_INSPECT_SIGN = "administration/operation/inspect/signIn";
            public static final String ADMINISTRATION_OPERATION_INSPECT_RECORD = "administration/operation/inspect/record";
            public static final String ADMINISTRATION_OPERATION_INSPECT_SWITCH = "administration/operation/inspect/switch";
            //预定管理
            public static final String ADMINISTRATION_OPERATION_APPOINT = "administration/operation/appoint";
            public static final String ADMINISTRATION_OPERATION_APPOINT_RULE = "administration/operation/appoint/rule";
            public static final String ADMINISTRATION_OPERATION_APPOINT_ORDER = "administration/operation/appoint/order";
            //订单管理
            public static final String ADMINISTRATION_OPERATION_ORDER = "administration/operation/order";
            //车辆管理
            public static final String ADMINISTRATION_OPERATION_VEHICLE = "administration/operation/vehicle";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_PACKETRULE = "administration/operation/vehicle/packetrule";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_PACKETMANAGER = "administration/operation/vehicle/packetmanager";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_PACKETCANCEL = "administration/operation/vehicle/packetcancel";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_FIXED = "administration/operation/vehicle/fixed";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_BLACKLIST = "administration/operation/vehicle/blackList";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_WHITELIST = "administration/operation/vehicle/whiteList";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_VISITLIST = "administration/operation/vehicle/visitList";
            //财务管理
            public static final String ADMINISTRATION_OPERATION_FINANCIAL = "administration/operation/financial";
            public static final String ADMINISTRATION_OPERATION_FINANCIAL_RULE = "administration/operation/financial/rule";
            public static final String ADMINISTRATION_OPERATION_FINANCIAL_ORDER = "administration/operation/financial/order";
            //发票管理
            public static final String ADMINISTRATION_OPERATION_INVOICE = "administration/operation/invoice";
            public static final String ADMINISTRATION_OPERATION_INVOICE_APPLY = "administration/operation/invoice/apply";
            public static final String ADMINISTRATION_OPERATION_INVOICE_HISTORY = "administration/operation/invoice/history";
            //报表管理
            public static final String ADMINISTRATION_OPERATION_REPORT = "administration/operation/report";
            public static final String ADMINISTRATION_OPERATION_REPORT_PARKING = "administration/operation/report/parking";
            public static final String ADMINISTRATION_OPERATION_REPORT_OPERATION = "administration/operation/report/operating";

            /*   设备管理        */
            public static final String ADMINISTRATION_DEVICE = "administration/device";
            //设备管理
            public static final String ADMINISTRATION_DEVICE_MANAGER = "administration/device/manager";
            public static final String ADMINISTRATION_DEVICE_MANAGER_MAGNETIC = "administration/device/manager/magnetic";
            public static final String ADMINISTRATION_DEVICE_MANAGER_DETECTOR = "administration/device/manager/detector";
            public static final String ADMINISTRATION_DEVICE_MANAGER_CAMERA = "administration/device/manager/camera";
            //设备配置
            public static final String ADMINISTRATION_DEVICE_SETTION = "administration/device/setting";
            public static final String ADMINISTRATION_DEVICE_SETTION_PARKING = "administration/device/setting/parking";
            public static final String ADMINISTRATION_DEVICE_SETTION_PARKINGLOT = "administration/device/setting/parkingLot";
            public static final String ADMINISTRATION_DEVICE_SETTION_CATE = "administration/device/setting/gate";

            //设备运营管理
            public static final String ADMINISTRATION_DEVICE_OPERATION = "administration/device/operation";
            public static final String ADMINISTRATION_DEVICE_OPERATION_DETECTRECORD = "administration/device/operation/detectRecord";
            public static final String ADMINISTRATION_DEVICE_OPERATION_MANAGERSSTATUS = "administration/device/operation/managerStatus";
            public static final String ADMINISTRATION_DEVICE_OPERATION_DETECTORSTATUS = "administration/device/operation/detectorStatus";
            public static final String ADMINISTRATION_DEVICE_OPERATION_MANAGEREXCEPTION = "administration/device/operation/managerException";
            public static final String ADMINISTRATION_DEVICE_OPERATION_DETECTOREXCEPTION = "administration/device/operation/detectorException";
            public static final String ADMINISTRATION_DEVICE_OPERATION_CAMERARECORD = "administration/device/operation/cameraRecord";
        }
    }

}
