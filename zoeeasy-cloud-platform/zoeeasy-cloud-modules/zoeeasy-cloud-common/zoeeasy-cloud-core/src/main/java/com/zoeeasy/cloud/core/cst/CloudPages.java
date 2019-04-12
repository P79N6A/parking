package com.zoeeasy.cloud.core.cst;

/**
 * 平台菜单常量定义
 *
 * @author walkman
 * @date 2018-08-28
 */
public final class CloudPages {
    private CloudPages() {
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
            public static final String ADMINISTRATION_DASHBOARD = "Administration.Dashboard";

            /*          人员管理                                */
            public static final String ADMINISTRATION_USER = "Administration.User";
            public static final String ADMINISTRATION_USER_ORGANIZATION = "Administration.User.Organization";
            public static final String ADMINISTRATION_USER_EMPLOYEES = "Administration.User.Employees";

            //权限管理
            public static final String ADMINISTRATION_USER_PERMISSION = "Administration.User.Permission";
            //角色管理
            public static final String ADMINISTRATION_USER_ROLE = "Administration.User.Role";
            //菜单管理
            public static final String ADMINISTRATION_USER_MENU = "Administration.User.Menu";

            /*   系统管理        */
            public static final String ADMINISTRATION_SYSTEM = "Administration.System";
            //字典管理
            public static final String ADMINISTRATION_SYSTEM_DICTIONARY = "Administration.System.Dictionary";
            //参数配置
            public static final String ADMINISTRATION_SYSTEM_SETTION = "Administration.System.Setting";
            //操作日志
            public static final String ADMINISTRATION_SYSTEM_AUDITLOG = "Administration.System.AuditLog";
            //登录日志
            public static final String ADMINISTRATION_SYSTEM_LOGINLOG = "Administration.System.LoginLog";
        }

        /**
         * 平台特定菜单
         */
        public static class Host {
            private Host() {
            }

            //租户管理
            public static final String ADMINISTRATION_TENANT = "Administration.Tenant";
            //停车场管理
            public static final String ADMINISTRATION_PARKING = "Administration.Parking";
            public static final String ADMINISTRATION_PARKING_MANAGER = "Administration.Parking.Manager";
            //停车场审核
            public static final String ADMINISTRATION_PARKING_APPROVE = "Administration.Parking.Approve";
        }

        /**
         * 租户特定菜单
         */
        public static class Tenant {
            private Tenant() {
            }

            /*                    基础数据                       */
            public static final String ADMINISTRATION_BASIC = "Administration.Basic";
            public static final String ADMINISTRATION_BASIC_AREA = "Administration.Basic.Area";
            public static final String ADMINISTRATION_BASIC_PARKING_INFO = "Administration.Basic.parkingInfo";
            public static final String ADMINISTRATION_BASIC_PARKING_REGION = "Administration.Basic.parkingRegion";
            public static final String ADMINISTRATION_BASIC_PARKING_GATE_INFO = "Administration.Basic.parkingGateInfo";
            public static final String ADMINISTRATION_BASIC_PARKING_LANE_INFO = "Administration.Basic.parkingLaneInfo";
            public static final String ADMINISTRATION_BASIC_PARKING_GARAGE = "Administration.Basic.parkingGarage";
            public static final String ADMINISTRATION_BASIC_PARKING_LOT_INFO = "Administration.Basic.parkingLotInfo";

            /*   运营管理        */
            public static final String ADMINISTRATION_OPERATION = "Administration.Operation";
            //业务管理
            public static final String ADMINISTRATION_OPERATION_BUSSINESS = "Administration.Operation.Business";
            public static final String ADMINISTRATION_OPERATION_BUSSINESS_PASSING = "Administration.Operation.Business.Passing";
            public static final String ADMINISTRATION_OPERATION_BUSSINESS_PARKING = "Administration.Operation.Business.Parking";
            public static final String ADMINISTRATION_OPERATION_BUSSINESS_RECORD = "Administration.Operation.Business.Record";
            //收费配置
            public static final String ADMINISTRATION_OPERATION_CHARGE = "Administration.Operation.Charge";
            public static final String ADMINISTRATION_OPERATION_COLLECTOR_ROADWAY = "Administration.Operation.Collector.Roadway";
            public static final String ADMINISTRATION_OPERATION_CHARGE_HOLIDAY = "Administration.Operation.Charge.Holiday";
            public static final String ADMINISTRATION_OPERATION_CHARGE_RULE = "Administration.Operation.Charge.Rule";
            //排班管理
            public static final String ADMINISTRATION_OPERATION_SCHEDULE = "Administration.Operation.Schedule";
            public static final String ADMINISTRATION_OPERATION_SCHEDULE_SETTION = "Administration.Operation.Schedule.Setting";
            public static final String ADMINISTRATION_OPERATION_SCHEDULE_MANUAL = "Administration.Operation.Schedule.Manual";
            public static final String ADMINISTRATION_OPERATION_SCHEDULE_HISTORY = "Administration.Operation.Schedule.History";
            //巡查管理
            public static final String ADMINISTRATION_OPERATION_INSPECT = "Administration.Operation.Inspect";
            public static final String ADMINISTRATION_OPERATION_INSPECTOR_ROADWAY = "Administration.Operation.Inspector.Roadway";
            public static final String ADMINISTRATION_OPERATION_INSPECT_SIGN = "Administration.Operation.Inspect.SignIn";
            public static final String ADMINISTRATION_OPERATION_INSPECT_RECORD = "Administration.Operation.Inspect.Record";
            public static final String ADMINISTRATION_OPERATION_INSPECT_SWITCH = "Administration.Operation.Inspect.Switch";
            //预定管理
            public static final String ADMINISTRATION_OPERATION_APPOINT = "Administration.Operation.Appoint";
            public static final String ADMINISTRATION_OPERATION_APPOINT_RULE = "Administration.Operation.Appoint.Rule";
            public static final String ADMINISTRATION_OPERATION_APPOINT_ORDER = "Administration.Operation.Appoint.Order";
            //订单管理
            public static final String ADMINISTRATION_OPERATION_ORDER = "Administration.Operation.Order";
            //车辆管理
            public static final String ADMINISTRATION_OPERATION_VEHICLE = "Administration.Operation.Vehicle";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_PACKETRULE = "Administration.Operation.Vehicle.PacketRule";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_PACKETMANAGER = "Administration.Operation.Vehicle.PacketManager";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_PACKETCANCEL = "Administration.Operation.Vehicle.PacketCancel";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_FIXED = "Administration.Operation.Vehicle.Fixed";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_BLACKLIST = "Administration.Operation.Vehicle.BlackList";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_WHITELIST = "Administration.Operation.Vehicle.WhiteList";
            public static final String ADMINISTRATION_OPERATION_VEHICLE_VISITLIST = "Administration.Operation.Vehicle.VisitList";
            //财务管理
            public static final String ADMINISTRATION_OPERATION_FINANCIAL = "Administration.Operation.Financial";
            public static final String ADMINISTRATION_OPERATION_FINANCIAL_RULE = "Administration.Operation.Financial.Rule";
            public static final String ADMINISTRATION_OPERATION_FINANCIAL_ORDER = "Administration.Operation.Financial.Order";
            //发票管理
            public static final String ADMINISTRATION_OPERATION_INVOICE = "Administration.Operation.Invoice";
            public static final String ADMINISTRATION_OPERATION_INVOICE_APPLY = "Administration.Operation.Invoice.Apply";
            public static final String ADMINISTRATION_OPERATION_INVOICE_HISTORY = "Administration.Operation.Invoice.History";
            //报表管理
            public static final String ADMINISTRATION_OPERATION_REPORT = "Administration.Operation.Report";
            public static final String ADMINISTRATION_OPERATION_REPORT_PARKING = "Administration.Operation.Report.Parking";
            public static final String ADMINISTRATION_OPERATION_REPORT_OPERATION = "Administration.Operation.Report.Operating";

            /*   设备管理        */
            public static final String ADMINISTRATION_DEVICE = "Administration.Device";
            //设备管理
            public static final String ADMINISTRATION_DEVICE_MANAGER = "Administration.Device.Manager";
            public static final String ADMINISTRATION_DEVICE_MANAGER_MAGNETIC = "Administration.Device.Manager.Magnetic";
            public static final String ADMINISTRATION_DEVICE_MANAGER_DETECTOR = "Administration.Device.Manager.Detector";
            public static final String ADMINISTRATION_DEVICE_MANAGER_CAMERA = "Administration.Device.Manager.Camera";
            //设备配置
            public static final String ADMINISTRATION_DEVICE_SETTION = "Administration.Device.Manager";
            public static final String ADMINISTRATION_DEVICE_SETTION_PARKING = "Administration.Device.Setting.Parking";
            public static final String ADMINISTRATION_DEVICE_SETTION_PARKINGLOT = "Administration.Device.Setting.ParkingLot";
            public static final String ADMINISTRATION_DEVICE_SETTION_CATE = "Administration.Device.Setting.Gate";

            //设备运营管理
            public static final String ADMINISTRATION_DEVICE_OPERATION = "Administration.Device.Operation";
            public static final String ADMINISTRATION_DEVICE_OPERATION_DETECTRECORD = "Administration.Device.Operation.DetectRecord";
            public static final String ADMINISTRATION_DEVICE_OPERATION_MANAGERSSTATUS = "Administration.Device.Operation.ManagerStatus";
            public static final String ADMINISTRATION_DEVICE_OPERATION_DETECTORSTATUS = "Administration.Device.Operation.DetectorStatus";
            public static final String ADMINISTRATION_DEVICE_OPERATION_MANAGEREXCEPTION = "Administration.Device.Operation.ManagerException";
            public static final String ADMINISTRATION_DEVICE_OPERATION_DETECTOREXCEPTION = "Administration.Device.Operation.DetectorException";
            public static final String ADMINISTRATION_DEVICE_OPERATION_CAMERARECORD = "Administration.Device.Operation.CameraRecord";
        }
    }
}
