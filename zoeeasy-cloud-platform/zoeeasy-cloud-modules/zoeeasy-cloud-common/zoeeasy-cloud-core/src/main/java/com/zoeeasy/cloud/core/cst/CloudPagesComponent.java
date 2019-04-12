package com.zoeeasy.cloud.core.cst;

/**
 * 平台菜单常量定义
 *
 * @author walkman
 * @since 2018-08-28
 */
public class CloudPagesComponent {

    private CloudPagesComponent() {
    }

    public static class App {

        private App() {
        }

        /**
         * 公共菜单
         */
        public static class Common {
            private Common() {
            }

            public static final String ADMINISTRATION = "Administration";
            public static final String ADMINISTRATION_DASHBOARD = "Dashboard";

            /*          人员管理                                */
            public static final String ADMINISTRATION_USER = "User";
            public static final String ADMINISTRATION_USER_ORGANIZATION = "User_Organization";
            public static final String ADMINISTRATION_USER_EMPLOYEES = "User_Employees";

            //权限管理
            public static final String ADMINISTRATION_USER_PERMISSION = "User_Permission";
            //角色管理
            public static final String ADMINISTRATION_USER_ROLE = "User_Role";
            //菜单管理
            public static final String ADMINISTRATION_USER_MENU = "User_Menu";

            /*   系统管理        */
            public static final String ADMINISTRATION_SYSTEM = "System";
            //字典管理
            public static final String ADMINISTRATION_SYSTEM_DICTIONARY = "System_Dictionary";
            //参数配置
            public static final String ADMINISTRATION_SYSTEM_SETTION = "System_Setting";
            //操作日志
            public static final String ADMINISTRATION_SYSTEM_AUDITLOG = "System_AuditLog";
            //登录日志
            public static final String ADMINISTRATION_SYSTEM_LOGINLOG = "System_LoginLog";
        }

        /**
         * 平台特定菜单
         */
        public static class Host {
            private Host() {
            }

            //租户管理
            public static final String ADMINISTRATION_TENANT = "Tenant";
            //停车场管理
            public static final String ADMINISTRATION_PARKING = "Parking";
            public static final String ADMINISTRATION_PARKING_MANAGER = "Parking_Manager";
            //停车场审核
            public static final String ADMINISTRATION_PARKING_APPROVE = "Parking_Approve";
        }

        /**
         * 租户特定菜单
         */
        public static class Tenant {
            private Tenant() {
            }

            /*                    基础数据                       */
            public static final String ADMINISTRATION_BASIC = "Basic";
            public static final String ADMINISTRATION_BASIC_AREA = "Basic_Area";
            public static final String ADMINISTRATION_BASIC_PARKING_INFO = "Basic_ParkingInfo";
            public static final String ADMINISTRATION_BASIC_PARKING_REGION = "Basic_ParkingRegion";
            public static final String ADMINISTRATION_BASIC_PARKING_GATE_INFO = "Basic_ParkingGateInfo";
            public static final String ADMINISTRATION_BASIC_PARKING_LANE_INFO = "Basic_ParkingLaneInfo";
            public static final String ADMINISTRATION_BASIC_PARKING_GARAGE = "Basic_ParkingGarageInfo";
            public static final String ADMINISTRATION_BASIC_PARKING_LOT_INFO = "Basic_ParkingLotInfo";

            /*   运营管理        */
            public static final String ADMINISTRATION_OPERATION = "Operation";
            //业务管理
            public static final String ADMINISTRATION_OPERATION_BUSSINESS = "Operation_Business";
            public static final String ADMINISTRATION_OPERATION_BUSSINESS_PASSING = "Business_Passing";
            public static final String ADMINISTRATION_OPERATION_BUSSINESS_PARKING = "Business_Parking";
            public static final String ADMINISTRATION_OPERATION_BUSSINESS_RECORD = "Business_Record";
            //收费配置
            public static final String ADMINISTRATION_OPERATION_CHARGE = "Operation_Charge";
            public static final String ADMINISTRATION_OPERATION_CHARGE_HOLIDAY = "Charge_Holiday";
            public static final String ADMINISTRATION_OPERATION_CHARGE_RULE = "Charge_Rule";
            public static final String ADMINISTRATION_OPERATION_COLLECTOR_ROADWAY = "Charge_Collector";
            //排班管理
            public static final String ADMINISTRATION_OPERATION_SCHEDULE = "Operation_Schedule";
            public static final String ADMINISTRATION_OPERATION_SCHEDULE_SETTION = "Schedule_Setting";
            public static final String ADMINISTRATION_OPERATION_SCHEDULE_MANUAL = "Schedule_Manual";
            public static final String ADMINISTRATION_OPERATION_SCHEDULE_HISTORY = "Schedule_History";
            //巡查管理
            public static final String ADMINISTRATION_OPERATION_INSPECT = "Operation_Inspect";
            public static final String ADMINISTRATION_OPERATION_INSPECT_ROADWAY = "Inspect_Inspector";
            public static final String ADMINISTRATION_OPERATION_INSPECT_SIGN = "Inspect_SignIn";
            public static final String ADMINISTRATION_OPERATION_INSPECT_RECORD = "Inspect_Record";
            public static final String ADMINISTRATION_OPERATION_INSPECT_SWITCH = "Inspect_Switch";
            //预定管理
            public static final String ADMINISTRATION_OPERATION_APPOINT = "Operation_Appoint";
            public static final String ADMINISTRATION_OPERATION_APPOINT_RULE = "Appoint_Rule";
            public static final String ADMINISTRATION_OPERATION_APPOINT_ORDER = "Appoint_Order";
            //订单管理
            public static final String ADMINISTRATION_OPERATION_ORDER = "Operation_Order";
            //车辆管理
            public static final String ADMINISTRATION_OPERATION_VEHICLE = "Operation_Vehicle";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_PACKETRULE = "Vehicle_PacketRule";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_PACKETMANAGER = "Vehicle_PacketManager";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_PACKETCANCEL = "Vehicle_PacketCancel";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_FIXED = "Vehicle_Fixed";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_BLACKLIST = "Vehicle_BlackList";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_WHITELIST = "Vehicle_WhiteList";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_VISITLIST = "Vehicle_VisitList";
            //财务管理
            public static final String ADMINISTRATION_OPERATION_FINANCIAL = "Operation_Financial";
            public static final String ADMINISTRATION_OPERATION_FINANCIAL_RULE = "Financial_Rule";
            public static final String ADMINISTRATION_OPERATION_FINANCIAL_ORDER = "Financial_Order";
            //发票管理
            public static final String ADMINISTRATION_OPERATION_INVOICE = "Operation_Invoice";
            public static final String ADMINISTRATION_OPERATION_INVOICE_APPLY = "Invoice_Apply";
            public static final String ADMINISTRATION_OPERATION_INVOICE_HISTORY = "Invoice_History";
            //报表管理
            public static final String ADMINISTRATION_OPERATION_REPORT = "Operation_Report";
            public static final String ADMINISTRATION_OPERATION_REPORT_PARKING = "Report_Parking";
            public static final String ADMINISTRATION_OPERATION_REPORT_OPERATION = "Report_Operating";

            /*   设备管理        */
            public static final String ADMINISTRATION_DEVICE = "Device";
            //设备管理
            public static final String ADMINISTRATION_DEVICE_MANAGER = "Device_Manager";
            public static final String ADMINISTRATION_DEVICE_MANAGER_MAGNETIC = "Manager_Magnetic";
            public static final String ADMINISTRATION_DEVICE_MANAGER_DETECTOR = "Manager_Detector";
            public static final String ADMINISTRATION_DEVICE_MANAGER_CAMERA = "Manager_Camera";
            //设备配置
            public static final String ADMINISTRATION_DEVICE_SETTION = "Device_Setting";
            public static final String ADMINISTRATION_DEVICE_SETTION_PARKING = "Setting_Parking";
            public static final String ADMINISTRATION_DEVICE_SETTION_CATE = "Setting_Gate";
            public static final String ADMINISTRATION_DEVICE_SETTION_PARKINGLOT = "Setting_ParkingLot";

            //设备运营管理
            public static final String ADMINISTRATION_DEVICE_OPERATION = "Device_Operation";
            public static final String ADMINISTRATION_DEVICE_OPERATION_DETECTRECORD = "Operation_DetectRecord";
            public static final String ADMINISTRATION_DEVICE_OPERATION_MANAGERSSTATUS = "Operation_ManagerStatus";
            public static final String ADMINISTRATION_DEVICE_OPERATION_DETECTORSTATUS = "Operation_DetectorStatus";
            public static final String ADMINISTRATION_DEVICE_OPERATION_MANAGEREXCEPTION = "Operation_ManagerException";
            public static final String ADMINISTRATION_DEVICE_OPERATION_DETECTOREXCEPTION = "Operation_DetectorException";
            public static final String ADMINISTRATION_DEVICE_OPERATION_CAMERARECORD = "Operation_CameraRecord";
        }
    }
}
