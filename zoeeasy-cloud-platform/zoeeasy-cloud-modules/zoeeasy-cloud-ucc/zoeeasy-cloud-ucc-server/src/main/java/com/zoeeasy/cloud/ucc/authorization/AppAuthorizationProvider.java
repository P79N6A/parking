package com.zoeeasy.cloud.ucc.authorization;

import com.scapegoat.infrastructure.core.multitenancy.TenancyHostSide;
import com.zoeeasy.cloud.core.cst.PermissionDefinitions;
import com.zoeeasy.cloud.core.cst.PermissionNameDefinitions;

/**
 * 应用程序权限提供者
 */
public class AppAuthorizationProvider extends AbstractAuthorizationProvider {

    private boolean multiTenancyEnabled;

    public AppAuthorizationProvider(boolean multiTenancyEnabled) {
        this.multiTenancyEnabled = multiTenancyEnabled;
    }

    @Override
    public void setPermissions(IPermissionDefinitionContext context) {
        //租户和宿主端通用权限
        Permission pages = context.getPermissionOrNull(PermissionDefinitions.Common.PAGES);
        if (pages == null) {
            pages = context.createPermission(PermissionDefinitions.Common.PAGES,
                    PermissionNameDefinitions.Common.PAGES, "", TenancyHostSide.Tenant);
        }
        //管理
        Permission administration = pages.createChildPermission(PermissionDefinitions.Common.PAGES_ADMIN,
                PermissionNameDefinitions.Common.PAGES_ADMIN, "", TenancyHostSide.Tenant);
        //人员管理
        Permission employeeAdmin = administration.createChildPermission(PermissionDefinitions.Common.ADMIN_USER,
                PermissionNameDefinitions.Common.ADMIN_USER, "", TenancyHostSide.Tenant);

        //部门管理
        Permission employeeOrganizationAdmin = employeeAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_USER_ORGANIZATION,
                PermissionNameDefinitions.Common.ADMIN_USER_ORGANIZATION, "", TenancyHostSide.Tenant);
        employeeOrganizationAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_USER_ORGANIZATION_CREATE,
                PermissionNameDefinitions.Common.ADMIN_USER_ORGANIZATION_CREATE, "", TenancyHostSide.Tenant);
        employeeOrganizationAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_USER_ORGANIZATION_EDIT,
                PermissionNameDefinitions.Common.ADMIN_USER_ORGANIZATION_EDIT, "", TenancyHostSide.Tenant);
        employeeOrganizationAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_USER_ORGANIZATION_VIEW,
                PermissionNameDefinitions.Common.ADMIN_USER_ORGANIZATION_VIEW, "", TenancyHostSide.Tenant);
        employeeOrganizationAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_USER_ORGANIZATION_DELETE,
                PermissionNameDefinitions.Common.ADMIN_USER_ORGANIZATION_DELETE, "", TenancyHostSide.Tenant);

        //用户管理
        Permission employeeUserAdmin = employeeAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE,
                PermissionNameDefinitions.Common.ADMIN_USER_EMPLOYEE, "", TenancyHostSide.Tenant);
        employeeUserAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_CREATE,
                PermissionNameDefinitions.Common.ADMIN_USER_EMPLOYEE_CREATE, "", TenancyHostSide.Tenant);
        employeeUserAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_EDIT,
                PermissionNameDefinitions.Common.ADMIN_USER_EMPLOYEE_EDIT, "", TenancyHostSide.Tenant);
        employeeUserAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_VIEW,
                PermissionNameDefinitions.Common.ADMIN_USER_EMPLOYEE_VIEW, "", TenancyHostSide.Tenant);
        employeeUserAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_USER_EMPLOYEE_DELETE,
                PermissionNameDefinitions.Common.ADMIN_USER_EMPLOYEE_DELETE, "", TenancyHostSide.Tenant);

        //角色管理
        Permission employeeRoleAdmin = employeeAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_USER_ROLE,
                PermissionNameDefinitions.Common.ADMIN_USER_ROLE, "", TenancyHostSide.Tenant);
        employeeRoleAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_USER_ROLE_CREATE,
                PermissionNameDefinitions.Common.ADMIN_USER_ROLE_CREATE, "", TenancyHostSide.Tenant);
        employeeRoleAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_USER_ROLE_EDIT,
                PermissionNameDefinitions.Common.ADMIN_USER_ROLE_EDIT, "", TenancyHostSide.Tenant);
        employeeRoleAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_USER_ROLE_VIEW,
                PermissionNameDefinitions.Common.ADMIN_USER_ROLE_VIEW, "", TenancyHostSide.Tenant);
        employeeRoleAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_USER_ROLE_DELETE,
                PermissionNameDefinitions.Common.ADMIN_USER_ROLE_DELETE, "", TenancyHostSide.Tenant);

        //系统管理
        Permission systemAdmin = administration.createChildPermission(PermissionDefinitions.Common.ADMIN_SYSTEM,
                PermissionNameDefinitions.Common.ADMIN_SYSTEM, "", TenancyHostSide.Tenant);
        //字典管理
        Permission dictionaryAdmin = systemAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_SYSTEM_DICTIONARY,
                PermissionNameDefinitions.Common.ADMIN_SYSTEM_DICTIONARY, "", TenancyHostSide.Tenant);
        //参数管理
        Permission settingAdmin = systemAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_SYSTEM_SETTING,
                PermissionNameDefinitions.Common.ADMIN_SYSTEM_SETTING, "", TenancyHostSide.Tenant);
        //参数管理
        Permission aduitLogAdmin = systemAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_SYSTEM_AUDITLOG,
                PermissionNameDefinitions.Common.ADMIN_SYSTEM_AUDITLOG, "", TenancyHostSide.Tenant);
        //登录日志
        Permission loginLogAdmin = systemAdmin.createChildPermission(PermissionDefinitions.Common.ADMIN_SYSTEM_LOGINLOG,
                PermissionNameDefinitions.Common.ADMIN_SYSTEM_LOGINLOG, "", TenancyHostSide.Tenant);

        //宿主特定权限
        //商户管理
        Permission tenantAdmin = administration.createChildPermission(PermissionDefinitions.Host.ADMIN_TENANT,
                PermissionNameDefinitions.Host.ADMIN_TENANT, "", TenancyHostSide.Host);
        tenantAdmin.createChildPermission(PermissionDefinitions.Host.ADMIN_TENANT_CREATE,
                PermissionNameDefinitions.Host.ADMIN_TENANT_CREATE, "", TenancyHostSide.Host);
        tenantAdmin.createChildPermission(PermissionDefinitions.Host.ADMIN_TENANT_EDIT,
                PermissionNameDefinitions.Host.ADMIN_TENANT_EDIT, "", TenancyHostSide.Host);
        tenantAdmin.createChildPermission(PermissionDefinitions.Host.ADMIN_TENANT_VIEW,
                PermissionNameDefinitions.Host.ADMIN_TENANT_VIEW, "", TenancyHostSide.Host);
        tenantAdmin.createChildPermission(PermissionDefinitions.Host.ADMIN_TENANT_DELETE,
                PermissionNameDefinitions.Host.ADMIN_TENANT_DELETE, "", TenancyHostSide.Host);

        //商户特定权限
        //首页
        Permission dashboardAdmin = administration.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DASHBOARD,
                PermissionNameDefinitions.Tenant.ADMIN_DASHBOARD, "", TenancyHostSide.Tenant);
        //基础数据
        Permission basicAdmin = administration.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC, "", TenancyHostSide.Tenant);

        //区域管理
        Permission areaAdmin = basicAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_AREA,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_AREA, "", TenancyHostSide.Tenant);
        areaAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_AREA_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_AREA_CREATE, "", TenancyHostSide.Tenant);
        areaAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_AREA_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_AREA_EDIT, "", TenancyHostSide.Tenant);
        areaAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_AREA_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_AREA_VIEW, "", TenancyHostSide.Tenant);
        areaAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_AREA_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_AREA_DELETE, "", TenancyHostSide.Tenant);

        //停车场管理
        Permission parkingInfoAdmin = basicAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO, "", TenancyHostSide.Tenant);
        parkingInfoAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO_CREATE, "", TenancyHostSide.Tenant);
        parkingInfoAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO_EDIT, "", TenancyHostSide.Tenant);
        parkingInfoAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO_VIEW, "", TenancyHostSide.Tenant);
        parkingInfoAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_INFO_DELETE, "", TenancyHostSide.Tenant);

        //停车区域管理
        Permission parkingRegionAdmin = basicAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_AREA,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_REGION, "", TenancyHostSide.Tenant);
        parkingRegionAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_AREA_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_REGION_CREATE, "", TenancyHostSide.Tenant);
        parkingRegionAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_AREA_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_REGION_EDIT, "", TenancyHostSide.Tenant);
        parkingRegionAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_AREA_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_REGION_VIEW, "", TenancyHostSide.Tenant);
        parkingRegionAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_AREA_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_REGION_DELETE, "", TenancyHostSide.Tenant);

        //出入口
        Permission parkingGateAdmin = basicAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE, "", TenancyHostSide.Tenant);
        parkingGateAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE_CREATE, "", TenancyHostSide.Tenant);
        parkingGateAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE_EDIT, "", TenancyHostSide.Tenant);
        parkingGateAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE_VIEW, "", TenancyHostSide.Tenant);
        parkingGateAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_GATE_DELETE, "", TenancyHostSide.Tenant);

        //车道
        Permission parkingLaneAdmin = basicAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE, "", TenancyHostSide.Tenant);
        parkingLaneAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE_CREATE, "", TenancyHostSide.Tenant);
        parkingLaneAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE_EDIT, "", TenancyHostSide.Tenant);
        parkingLaneAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE_VIEW, "", TenancyHostSide.Tenant);
        parkingLaneAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_LANE_DELETE, "", TenancyHostSide.Tenant);

        //车库管理
        Permission parkingGarageAdmin = basicAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE, "", TenancyHostSide.Tenant);
        parkingGarageAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE_CREATE, "", TenancyHostSide.Tenant);
        parkingGarageAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE_EDIT, "", TenancyHostSide.Tenant);
        parkingGarageAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE_VIEW, "", TenancyHostSide.Tenant);
        parkingGarageAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_GARAGE_DELETE, "", TenancyHostSide.Tenant);

        //泊位管理
        Permission parkingLotAdmin = basicAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT, "", TenancyHostSide.Tenant);
        parkingLotAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT_CREATE, "", TenancyHostSide.Tenant);
        parkingLotAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT_EDIT, "", TenancyHostSide.Tenant);
        parkingLotAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT_VIEW, "", TenancyHostSide.Tenant);
        parkingLotAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_BASIC_PARKING_LOT_DELETE, "", TenancyHostSide.Tenant);

        /*   运营管理        */
        Permission operationAdmin = basicAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION, "", TenancyHostSide.Tenant);

        //业务管理
        Permission operationBusinessAdmin = operationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_BUSINESS,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_BUSINESS, "", TenancyHostSide.Tenant);
        //过车记录
        Permission operationBusinessPassingAdmin = operationBusinessAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_PASSING,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_PASSING, "", TenancyHostSide.Tenant);
        operationBusinessPassingAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_PASSING_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_PASSING_VIEW, "", TenancyHostSide.Tenant);
        operationBusinessPassingAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_PASSING_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_PASSING_EDIT, "", TenancyHostSide.Tenant);
        //在场车辆
        Permission operationBusinessParkingAdmin = operationBusinessAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_PARKING,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_PARKING, "", TenancyHostSide.Tenant);
        operationBusinessParkingAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_PARKING_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_PARKING_VIEW, "", TenancyHostSide.Tenant);
        //停车记录
        Permission operationBusinessRecordAdmin = operationBusinessAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_RECORD,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_RECORD, "", TenancyHostSide.Tenant);
        operationBusinessRecordAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_RECORD_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_BUSINESS_RECORD_VIEW, "", TenancyHostSide.Tenant);
        //收费配置
        Permission operationChargeAdmin = operationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_CHARGE, "", TenancyHostSide.Tenant);

        //路边收费员
        Permission operationChargeRoadwayAdmin = operationChargeAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_ROADWAY,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_CHARGE_ROADWAY, "", TenancyHostSide.Tenant);
        operationChargeRoadwayAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_ROADWAY_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_CHARGE_ROADWAY_CREATE, "", TenancyHostSide.Tenant);
        operationChargeRoadwayAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_ROADWAY_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_CHARGE_ROADWAY_EDIT, "", TenancyHostSide.Tenant);
        operationChargeRoadwayAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_ROADWAY_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_CHARGE_ROADWAY_VIEW, "", TenancyHostSide.Tenant);
        operationChargeRoadwayAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_ROADWAY_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_CHARGE_ROADWAY_DELETE, "", TenancyHostSide.Tenant);
        operationChargeRoadwayAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_ROADWAY_LOCK,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_CHARGE_ROADWAY_LOCK, "", TenancyHostSide.Tenant);
        operationChargeRoadwayAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_ROADWAY_UNLOCK,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_CHARGE_ROADWAY_UNLOCK, "", TenancyHostSide.Tenant);
        //假期配置
        Permission operationChargeHolidayAdmin = operationChargeAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_HOLIDAY,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_CHARGE_HOLIDAY, "", TenancyHostSide.Tenant);
        operationChargeHolidayAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_HOLIDAY_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_CHARGE_HOLIDAY_EDIT, "", TenancyHostSide.Tenant);
        operationChargeHolidayAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_HOLIDAY_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_CHARGE_HOLIDAY_VIEW, "", TenancyHostSide.Tenant);
        operationChargeHolidayAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_HOLIDAY_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_CHARGE_HOLIDAY_DELETE, "", TenancyHostSide.Tenant);
        //收费规则
        Permission operationChargeRuleAdmin = operationChargeAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE, "", TenancyHostSide.Tenant);
        operationChargeRuleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_CREATE, "", TenancyHostSide.Tenant);
        operationChargeRuleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_EDIT, "", TenancyHostSide.Tenant);
        operationChargeRuleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_VIEW, "", TenancyHostSide.Tenant);
        operationChargeRuleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_CHARGE_RULE_DELETE, "", TenancyHostSide.Tenant);
        //排班管理
        Permission operationScheduleAdmin = operationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE, "", TenancyHostSide.Tenant);
        //排班配置
        Permission operationScheduleSettingAdmin = operationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_SETTING,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_SETTING, "", TenancyHostSide.Tenant);
        operationScheduleSettingAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_SETTING_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_SETTING_CREATE, "", TenancyHostSide.Tenant);
        operationScheduleSettingAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_SETTING_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_SETTING_EDIT, "", TenancyHostSide.Tenant);
        operationScheduleSettingAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_SETTING_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_SETTING_VIEW, "", TenancyHostSide.Tenant);
        operationScheduleSettingAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_SETTING_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_SETTING_DELETE, "", TenancyHostSide.Tenant);
        //手动排班
        Permission operationScheduleManualAdmin = operationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_MANUAL,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_MANUAL, "", TenancyHostSide.Tenant);
        operationScheduleManualAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_MANUAL_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_MANUAL_CREATE, "", TenancyHostSide.Tenant);
        operationScheduleManualAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_MANUAL_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_MANUAL_EDIT, "", TenancyHostSide.Tenant);
        operationScheduleManualAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_MANUAL_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_MANUAL_VIEW, "", TenancyHostSide.Tenant);
        operationScheduleManualAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_MANUAL_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_MANUAL_DELETE, "", TenancyHostSide.Tenant);
        //历史排班
        Permission operationScheduleHistoryAdmin = operationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_HISTORY,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_HISTORY, "", TenancyHostSide.Tenant);
        operationScheduleHistoryAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_HISTORY_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_SCHEDULE_HISTORY_VIEW, "", TenancyHostSide.Tenant);
        //巡查管理
        Permission operationInspectAdmin = operationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_INSPECT,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_INSPECT, "", TenancyHostSide.Tenant);
        //路边巡检员
        Permission operationInspectRoadwayAdmin = operationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_INSPECTOR_ROADWAY,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_INSPECTOR_ROADWAY, "", TenancyHostSide.Tenant);
        operationInspectRoadwayAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_INSPECTOR_ROADWAY_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_INSPECTOR_ROADWAY_CREATE, "", TenancyHostSide.Tenant);
        operationInspectRoadwayAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_INSPECTOR_ROADWAY_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_INSPECTOR_ROADWAY_EDIT, "", TenancyHostSide.Tenant);
        operationInspectRoadwayAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_INSPECTOR_ROADWAY_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_INSPECTOR_ROADWAY_VIEW, "", TenancyHostSide.Tenant);
        operationInspectRoadwayAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_INSPECTOR_ROADWAY_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_INSPECTOR_ROADWAY_DELETE, "", TenancyHostSide.Tenant);
        //签到记录
        Permission operationInspectSignAdmin = operationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_INSPECT_SINGIN,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_INSPECT_SINGIN, "", TenancyHostSide.Tenant);
        operationInspectSignAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_INSPECT_SINGIN_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_INSPECT_SINGIN_VIEW, "", TenancyHostSide.Tenant);
        //巡查记录
        Permission operationInspectRecordAdmin = operationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_INSPECT_RECORD,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_INSPECT_RECORD, "", TenancyHostSide.Tenant);
        operationInspectRecordAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_INSPECT_RECORD_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_INSPECT_RECORD_VIEW, "", TenancyHostSide.Tenant);
        //交接班记录
        Permission operationInspectSwitchAdmin = operationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_INSPECT_SWITCH,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_INSPECT_SWITCH, "", TenancyHostSide.Tenant);
        operationInspectSwitchAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_INSPECT_SWITCH_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_INSPECT_SWITCH_VIEW, "", TenancyHostSide.Tenant);
        //预定管理
        Permission operationAppointAdmin = operationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_APPOINT,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_APPOINT, "", TenancyHostSide.Tenant);
        //预约规则
        Permission operationAppointRuleAdmin = operationAppointAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_APPOINT_RULE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_APPOINT_RULE, "", TenancyHostSide.Tenant);
        operationAppointRuleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_APPOINT_RULE_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_APPOINT_RULE_CREATE, "", TenancyHostSide.Tenant);
        operationAppointRuleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_APPOINT_RULE_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_APPOINT_RULE_EDIT, "", TenancyHostSide.Tenant);
        operationAppointRuleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_APPOINT_RULE_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_APPOINT_RULE_VIEW, "", TenancyHostSide.Tenant);
        operationAppointRuleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_APPOINT_RULE_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_APPOINT_RULE_DELETE, "", TenancyHostSide.Tenant);
        //预约订单
        Permission operationAppointOrderAdmin = operationAppointAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_APPOINT_ORDER,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_APPOINT_ORDER, "", TenancyHostSide.Tenant);
        operationAppointOrderAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_APPOINT_ORDER_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_APPOINT_ORDER_VIEW, "", TenancyHostSide.Tenant);
        operationAppointOrderAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_APPOINT_ORDER_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_APPOINT_ORDER_EDIT, "", TenancyHostSide.Tenant);
        //订单管理
        Permission operationOrderAdmin = operationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_ORDER,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_ORDER, "", TenancyHostSide.Tenant);
        operationOrderAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_ORDER_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_ORDER_CREATE, "", TenancyHostSide.Tenant);
        operationOrderAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_ORDER_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_ORDER_EDIT, "", TenancyHostSide.Tenant);
        operationOrderAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_ORDER_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_ORDER_VIEW, "", TenancyHostSide.Tenant);
        operationOrderAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_ORDER_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_ORDER_DELETE, "", TenancyHostSide.Tenant);
        //车辆管理
        Permission operationVehicleAdmin = operationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE, "", TenancyHostSide.Tenant);
        //包期规则
        Permission operationVehiclePacketRuleAdmin = operationVehicleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETRULE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETRULE, "", TenancyHostSide.Tenant);
        operationVehiclePacketRuleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETRULE_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETRULE_CREATE, "", TenancyHostSide.Tenant);
        operationVehiclePacketRuleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETRULE_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETRULE_EDIT, "", TenancyHostSide.Tenant);
        operationVehiclePacketRuleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETRULE_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETRULE_VIEW, "", TenancyHostSide.Tenant);
        operationVehiclePacketRuleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETRULE_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETRULE_DELETE, "", TenancyHostSide.Tenant);
        //包期管理
        Permission operationVehiclePacketManagerAdmin = operationVehicleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETMANAGER,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETMANAGER, "", TenancyHostSide.Tenant);
        operationVehiclePacketManagerAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETMANAGER_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETMANAGER_CREATE, "", TenancyHostSide.Tenant);
        operationVehiclePacketManagerAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETMANAGER_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETMANAGER_VIEW, "", TenancyHostSide.Tenant);
        operationVehiclePacketManagerAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETMANAGER_TOP_UP,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETMANAGER_TOP_UP, "", TenancyHostSide.Tenant);
        operationVehiclePacketManagerAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETMANAGER_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETMANAGER_DELETE, "", TenancyHostSide.Tenant);
        //包期取消
        Permission operationVehiclePacketCancelAdmin = operationVehicleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETCANCEL,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETCANCEL, "", TenancyHostSide.Tenant);
        operationVehiclePacketCancelAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETCANCEL_APPLY,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETCANCEL_APPLY, "", TenancyHostSide.Tenant);
        operationVehiclePacketCancelAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETCANCEL_APPROVE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETCANCEL_APPROVE, "", TenancyHostSide.Tenant);
        operationVehiclePacketCancelAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETCANCEL_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETCANCEL_VIEW, "", TenancyHostSide.Tenant);
        operationVehiclePacketCancelAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETCANCEL_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_PACKETCANCEL_DELETE, "", TenancyHostSide.Tenant);
        //固定车
        Permission operationVehicleFixedAdmin = operationVehicleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_FIXED,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_FIXED, "", TenancyHostSide.Tenant);
        operationVehicleFixedAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_FIXED_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_FIXED_CREATE, "", TenancyHostSide.Tenant);
        operationVehicleFixedAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_FIXED_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_FIXED_EDIT, "", TenancyHostSide.Tenant);
        operationVehicleFixedAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_FIXED_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_FIXED_VIEW, "", TenancyHostSide.Tenant);
        operationVehicleFixedAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_FIXED_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_FIXED_DELETE, "", TenancyHostSide.Tenant);
        operationVehicleFixedAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_FIXED_RELEVANCE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_FIXED_RELEVANCE, "", TenancyHostSide.Tenant);
        //黑名单
        Permission operationVehicleBlackAdmin = operationVehicleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_BLACKLIST,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_BLACKLIST, "", TenancyHostSide.Tenant);
        operationVehicleBlackAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_BLACKLIST_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_BLACKLIST_CREATE, "", TenancyHostSide.Tenant);
        operationVehicleBlackAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_BLACKLIST_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_BLACKLIST_EDIT, "", TenancyHostSide.Tenant);
        operationVehicleBlackAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_BLACKLIST_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_BLACKLIST_VIEW, "", TenancyHostSide.Tenant);
        operationVehicleBlackAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_BLACKLIST_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_BLACKLIST_DELETE, "", TenancyHostSide.Tenant);
        //白名单
        Permission operationVehicleWhiteAdmin = operationVehicleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_WHITELIST,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_WHITELIST, "", TenancyHostSide.Tenant);
        operationVehicleWhiteAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_WHITELIST_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_WHITELIST_CREATE, "", TenancyHostSide.Tenant);
        operationVehicleWhiteAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_WHITELIST_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_WHITELIST_EDIT, "", TenancyHostSide.Tenant);
        operationVehicleWhiteAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_WHITELIST_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_WHITELIST_VIEW, "", TenancyHostSide.Tenant);
        operationVehicleWhiteAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_WHITELIST_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_WHITELIST_DELETE, "", TenancyHostSide.Tenant);
        //访客车
        Permission operationVehicleVisitAdmin = operationVehicleAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_VISITLIST,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_VISITLIST, "", TenancyHostSide.Tenant);
        operationVehicleWhiteAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_VISITLIST_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_VISITLIST_CREATE, "", TenancyHostSide.Tenant);
        operationVehicleWhiteAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_VISITLIST_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_VISITLIST_EDIT, "", TenancyHostSide.Tenant);
        operationVehicleWhiteAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_VISITLIST_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_VISITLIST_VIEW, "", TenancyHostSide.Tenant);
        operationVehicleWhiteAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_VISITLIST_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_VEHICLE_VISITLIST_DELETE, "", TenancyHostSide.Tenant);
        //财务管理
        Permission operationFinancialAdmin = operationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_FINANCIAL,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_FINANCIAL, "", TenancyHostSide.Tenant);
        //发票管理
        Permission operationInvoiceAdmin = operationFinancialAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_INVOICE,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_INVOICE, "", TenancyHostSide.Tenant);
        //开票申请
        Permission operationInvoiceApplyAdmin = operationFinancialAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_INVOICE_APPLY,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_INVOICE_APPLY, "", TenancyHostSide.Tenant);
        //开票历史
        Permission operationInvoiceHistoryAdmin = operationFinancialAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_INVOICE_HISTORY,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_INVOICE_HISTORY, "", TenancyHostSide.Tenant);
        //报表管理
        Permission operationReportAdmin = operationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_REPORT,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_REPORT, "", TenancyHostSide.Tenant);
        //停车效率
        Permission operationReportParkingAdmin = operationReportAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_REPORT_PARKING,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_REPORT_PARKING, "", TenancyHostSide.Tenant);
        //运营效率
        Permission operationReportOperatingAdmin = operationReportAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_OPERATION_REPORT_OPERATING,
                PermissionNameDefinitions.Tenant.ADMIN_OPERATION_REPORT_OPERATING, "", TenancyHostSide.Tenant);

        //设备管理
        Permission deviceAdmin = operationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE, "", TenancyHostSide.Tenant);
        //设备管理
        Permission deviceManagerAdmin = deviceAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_MANAGER, "", TenancyHostSide.Tenant);
        //地磁管理器管理
        Permission deviceMagneticAdmin = deviceManagerAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_MAGNETIC,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_MANAGER_MAGNETIC, "", TenancyHostSide.Tenant);
        deviceMagneticAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_MAGNETIC_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_MANAGER_MAGNETIC_CREATE, "", TenancyHostSide.Tenant);
        deviceMagneticAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_MAGNETIC_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_MANAGER_MAGNETIC_EDIT, "", TenancyHostSide.Tenant);
        deviceMagneticAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_MAGNETIC_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_MANAGER_MAGNETIC_VIEW, "", TenancyHostSide.Tenant);
        deviceMagneticAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_MAGNETIC_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_MANAGER_MAGNETIC_DELETE, "", TenancyHostSide.Tenant);
        deviceMagneticAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_MAGNETIC_RELEVANCE,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_MANAGER_MAGNETIC_RELEVANCE, "", TenancyHostSide.Tenant);

        //地磁检测器管理
        Permission deviceManagerDetctorAdmin = deviceManagerAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_DETECTOR,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_MANAGER_DETECTOR, "", TenancyHostSide.Tenant);
        deviceMagneticAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_DETECTOR_CREATE,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_MANAGER_DETECTOR_CREATE, "", TenancyHostSide.Tenant);
        deviceMagneticAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_DETECTOR_EDIT,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_MANAGER_DETECTOR_EDIT, "", TenancyHostSide.Tenant);
        deviceMagneticAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_DETECTOR_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_MANAGER_DETECTOR_VIEW, "", TenancyHostSide.Tenant);
        deviceMagneticAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_DETECTOR_DELETE,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_MANAGER_DETECTOR_DELETE, "", TenancyHostSide.Tenant);

        Permission deviceManagerCameraAdmin = deviceManagerAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_MANAGER_CAMERA,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_MANAGER_CAMERA, "", TenancyHostSide.Tenant);

        //设备配置
        Permission deviceSettingAdmin = deviceAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_SETTING,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_SETTING, "", TenancyHostSide.Tenant);
        //停车场配置
        Permission deviceSettingParkingAdmin = deviceSettingAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_SETTING_PARKING,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_SETTING_PARKING, "", TenancyHostSide.Tenant);
        //泊位配置
        Permission deviceSettingLotAdmin = deviceSettingAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_SETTING_PARKINGLOT,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_SETTING_PARKINGLOT, "", TenancyHostSide.Tenant);
        deviceSettingLotAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_SETTING_PARKINGLOT_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_SETTING_PARKINGLOT_VIEW, "", TenancyHostSide.Tenant);
        deviceSettingLotAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_SETTING_PARKINGLOT_RELEVANCE,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_SETTING_PARKINGLOT_RELEVANCE, "", TenancyHostSide.Tenant);

        //出入口配置
        Permission deviceSettingGateAdmin = deviceSettingAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_SETTING_GATE,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_SETTING_GATE, "", TenancyHostSide.Tenant);

        //设备运营管理
        Permission deviceOperationAdmin = deviceAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_OPERATION,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_OPERATION, "", TenancyHostSide.Tenant);
        //地磁检测记录
        Permission deviceOperationDetectRecordAdmin = deviceOperationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_OPERATION_DETECTRECORD,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_OPERATION_DETECTRECORD, "", TenancyHostSide.Tenant);
        deviceOperationDetectRecordAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_OPERATION_DETECTRECORD_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_OPERATION_DETECTRECORD_VIEW, "", TenancyHostSide.Tenant);

        //地磁管理器状态
        Permission deviceOperationManagerStatusAdmin = deviceOperationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_OPERATION_MANAGERSTATUS,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_OPERATION_MANAGERSTATUS, "", TenancyHostSide.Tenant);
        //地磁检测器状态
        Permission deviceOperationDetectorStatusAdmin = deviceOperationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_OPERATION_DETECTORSTATUS,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_OPERATION_DETECTORSTATUS, "", TenancyHostSide.Tenant);
        deviceOperationDetectorStatusAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_OPERATION_DETECTORSTATUS_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_OPERATION_DETECTORSTATUS_VIEW, "", TenancyHostSide.Tenant);
        //管理器异常
        Permission deviceOperationManagerExceptionAdmin = deviceOperationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_OPERATION_MANAGEREXCEPTION,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_OPERATION_MANAGEREXCEPTION, "", TenancyHostSide.Tenant);
        //检测器异常
        Permission deviceOperationDetectorExceptionAdmin = deviceOperationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_OPERATION_DETECTOREXCEPTION,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_OPERATION_DETECTOREXCEPTION, "", TenancyHostSide.Tenant);
        deviceOperationDetectorExceptionAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_OPERATION_DETECTOREXCEPTION_VIEW,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_OPERATION_DETECTOREXCEPTION_VIEW, "", TenancyHostSide.Tenant);

        //视频检测记录
        Permission deviceOperationCameraRecordAdmin = deviceOperationAdmin.createChildPermission(PermissionDefinitions.Tenant.ADMIN_DEVICE_OPERATION_CAMERARECORD,
                PermissionNameDefinitions.Tenant.ADMIN_DEVICE_OPERATION_CAMERARECORD, "", TenancyHostSide.Tenant);

    }

}
