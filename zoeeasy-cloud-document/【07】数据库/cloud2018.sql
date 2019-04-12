/*
Navicat MySQL Data Transfer

Source Server         : cloud
Source Server Version : 50712
Source Host           : 192.168.0.242:33306
Source Database       : cloud2018

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2018-08-29 09:29:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for access_param_config
-- ----------------------------
DROP TABLE IF EXISTS `access_param_config`;
CREATE TABLE `access_param_config` (
  `guid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：记录ID',
  `param_name` varchar(64) NOT NULL COMMENT '参数名称',
  `param_value` text NOT NULL COMMENT '参数值：加密后字符串',
  `param_type` tinyint(4) DEFAULT NULL,
  `is_encrypt` tinyint(4) NOT NULL COMMENT '是否加密：0 否； 1 是',
  `create_person` varchar(32) NOT NULL COMMENT '创建人：账户',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_person` varchar(32) NOT NULL COMMENT '更新人：账户',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=2082 DEFAULT CHARSET=utf8 COMMENT='接入参数配置';

-- ----------------------------
-- Table structure for accounting_record
-- ----------------------------
DROP TABLE IF EXISTS `accounting_record`;
CREATE TABLE `accounting_record` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录Id',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '收费员隶属商户ID',
  `docking_id` bigint(20) unsigned NOT NULL COMMENT '对接网关ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `gate_id` bigint(20) unsigned DEFAULT NULL COMMENT '出入口ID',
  `gate_code` varchar(64) DEFAULT NULL COMMENT '出入口编号',
  `gate_name` varchar(32) DEFAULT NULL COMMENT '出入口名称',
  `collector_id` bigint(20) unsigned DEFAULT NULL COMMENT '上缴费用收费员ID',
  `collector_code` varchar(64) NOT NULL COMMENT '收费员编号',
  `collector_name` varchar(32) NOT NULL COMMENT '收费员名称',
  `start_time` datetime DEFAULT NULL COMMENT '收费员上班时间',
  `end_time` datetime DEFAULT NULL COMMENT '收费员下班时间',
  `should_pay_total` int(11) DEFAULT NULL COMMENT '应缴费总额，单位：分',
  `pay_money` int(11) DEFAULT NULL COMMENT '本次实际缴费总额，单位：分',
  `should_pay_left` int(11) DEFAULT NULL COMMENT '应补缴费总额，单位：分',
  `system_amount` int(11) DEFAULT NULL COMMENT '系统承担金额，单位：分',
  `operator_id` bigint(20) unsigned NOT NULL COMMENT '操作员ID',
  `operator_name` varchar(32) DEFAULT NULL COMMENT '操作员名称',
  `is_cleared` tinyint(3) unsigned DEFAULT NULL COMMENT '本次缴费是否已清算，0-否，1 是',
  `clear_mode` tinyint(3) unsigned DEFAULT NULL COMMENT '清算方式，0-金额一致，1-差额由系统承担，2-收费员后续补缴，3-系统承担+补缴',
  `accounting_time` datetime DEFAULT NULL COMMENT '入账时间',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注信息：清算时如果金额不一致，必须填写',
  PRIMARY KEY (`record_id`),
  KEY `idx_accounting_record_dealer` (`dealer_id`,`park_id`,`accounting_time`,`collector_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入账记录信息表';

-- ----------------------------
-- Table structure for addr_info
-- ----------------------------
DROP TABLE IF EXISTS `addr_info`;
CREATE TABLE `addr_info` (
  `addr_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：地址ID',
  `addr_code` varchar(6) NOT NULL COMMENT '地址代码',
  `addr_name` varchar(32) NOT NULL COMMENT '地址名称',
  `p_code` varchar(6) NOT NULL COMMENT '父代码',
  `addr_type` tinyint(4) NOT NULL COMMENT '地址类型：1 全国；2 省；3 地市',
  `default_display` tinyint(3) unsigned NOT NULL COMMENT '是否默认显示：0 否；1 是',
  PRIMARY KEY (`addr_id`),
  UNIQUE KEY `idx_addr_info_code` (`addr_code`)
) ENGINE=InnoDB AUTO_INCREMENT=842 DEFAULT CHARSET=utf8 COMMENT='地址信息：参照国标GB/T2260-2013';

-- ----------------------------
-- Table structure for alarm_record
-- ----------------------------
DROP TABLE IF EXISTS `alarm_record`;
CREATE TABLE `alarm_record` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `app_id` bigint(20) DEFAULT NULL COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '申请人ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车点ID',
  `berth_code` varchar(64) DEFAULT NULL COMMENT '泊位编号：全局唯一',
  `berth_number` varchar(64) DEFAULT NULL COMMENT '泊位编号：停车场内唯一',
  `plate_no` varchar(10) DEFAULT NULL COMMENT '车牌号码',
  `plate_color` tinyint(4) DEFAULT NULL COMMENT '车牌颜色，-1没有车牌颜色 0-其他,1-蓝色,2-黄色,3-黑色,4-白色',
  `in_time` datetime DEFAULT NULL COMMENT '入车时间',
  `out_time` datetime DEFAULT NULL COMMENT '出车时间',
  `unid` varchar(64) DEFAULT NULL COMMENT '停车唯一标识',
  `direct` tinyint(4) DEFAULT NULL COMMENT '出入方向，0-入场，1-出场',
  `rela_unid` varchar(64) DEFAULT NULL COMMENT '泊位上一次停车的UNID\r\n            业务类型为8 入车时车牌号码冲突报警\r\n            ',
  `rela_plate_no` varchar(10) DEFAULT NULL COMMENT '泊位上一次停车的车牌号码',
  `rela_plate_color` tinyint(4) DEFAULT NULL COMMENT '泊位上一次停车的车牌颜色',
  `appeal_type` tinyint(4) DEFAULT NULL COMMENT '车主申诉类型 1.停车时间有误 2.不存在此次停车',
  `reason` varchar(256) DEFAULT NULL COMMENT '申诉理由,app账单申诉提供',
  `phone` varchar(32) DEFAULT NULL COMMENT '用户手机号码',
  `camera_no` varchar(64) DEFAULT NULL COMMENT '球机设备编号',
  `camera_name` varchar(32) DEFAULT NULL COMMENT '球机设备名称',
  `send_times` tinyint(4) DEFAULT '0' COMMENT '短信发送次数',
  `inspectors_code` varchar(64) DEFAULT NULL COMMENT '巡检员编号',
  `inspectors_rslt` varchar(128) DEFAULT NULL COMMENT '巡检员处理结果：巡检员检查后未发现车牌，照片路径和拍照时间传空，这里写“未发现车辆” \r\n车主不主动入车处理填写的是巡检员上传的是否误报',
  `img_url` varchar(256) DEFAULT NULL COMMENT '报警时路边上传的照片',
  `img_unid` varchar(64) DEFAULT NULL COMMENT '图片唯一ID',
  `dags_send` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'dags发送是否成功：0否；1是',
  `rept_in` tinyint(4) DEFAULT '0' COMMENT '是否重复入车：0否；1是',
  `dis_confident` tinyint(4) DEFAULT '0' COMMENT '是否置信度低：0否；1是',
  `car_abnormal` tinyint(4) DEFAULT '0' COMMENT '无车牌或大型车(车辆异常)：0否；1是',
  `plate_confident` int(11) DEFAULT NULL COMMENT '车牌置信度',
  `action_confident` int(11) DEFAULT NULL COMMENT '行为置信度',
  `car_type` tinyint(3) DEFAULT NULL COMMENT '车辆类型，1-小型汽车,2-大型汽车,0-其他',
  PRIMARY KEY (`record_id`),
  KEY `idx_alarm_record_appid` (`app_id`),
  KEY `idx_alarm_record_berth` (`berth_code`),
  KEY `idx_alarm_record_park` (`park_id`,`dags_send`),
  KEY `idx_alarm_record_unid` (`park_id`,`unid`,`direct`) USING BTREE,
  KEY `idx_alarm_record_dimg` (`dealer_id`,`img_unid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报警记录：\r\n5车主不主动入车报警：流程图3  有巡检员\r\n6车主主动入车（地磁状态无车）报警：流程';

-- ----------------------------
-- Table structure for alipay_agreement
-- ----------------------------
DROP TABLE IF EXISTS `alipay_agreement`;
CREATE TABLE `alipay_agreement` (
  `record_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `plate_no` varchar(10) DEFAULT NULL COMMENT '车牌号码',
  `plate_color` tinyint(4) DEFAULT NULL COMMENT '车牌颜色，-1没有车牌颜色 0-其他,1-蓝色,2-黄色,3-黑色,4-白色',
  `status` tinyint(4) DEFAULT NULL COMMENT '0 为支持代扣 ;1 为不支持代扣;',
  `update_time` datetime DEFAULT NULL COMMENT '协议最后更新时间',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for app_equip
-- ----------------------------
DROP TABLE IF EXISTS `app_equip`;
CREATE TABLE `app_equip` (
  `equip_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `device_type` tinyint(4) NOT NULL COMMENT '设备类型：1 IOS；2 Android',
  `device_id` varchar(64) NOT NULL COMMENT '设备唯一ID：ios-UDID，android-IMEI',
  `device_name` varchar(32) NOT NULL COMMENT '设备名称：手机设备的名称（品牌+型号）',
  PRIMARY KEY (`equip_id`),
  KEY `idx_app_equip_device` (`device_type`,`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='APP设备信息表：驾驶人使用APP时会推送一次';

-- ----------------------------
-- Table structure for app_version_info
-- ----------------------------
DROP TABLE IF EXISTS `app_version_info`;
CREATE TABLE `app_version_info` (
  `version_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version_name` varchar(64) DEFAULT NULL COMMENT '外部版本号',
  `version_code` varchar(64) DEFAULT NULL COMMENT '内部版本号',
  `app_md5` varchar(64) DEFAULT NULL COMMENT '文件md5码',
  `app_type` tinyint(3) unsigned NOT NULL COMMENT 'APP类型：1  IOS；2 Android',
  `app_name` varchar(64) DEFAULT NULL COMMENT '应用名称',
  `app_url` varchar(256) DEFAULT NULL COMMENT '请求地址',
  `is_force` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否强制升级，0-否，1-是',
  `update_info` varchar(512) DEFAULT NULL COMMENT '更新内容',
  `file_size` int(11) DEFAULT NULL COMMENT '文件大小',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`version_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='APP版本信息配置表';


-- ----------------------------
-- Table structure for apply_refund
-- ----------------------------
DROP TABLE IF EXISTS `apply_refund`;
CREATE TABLE `apply_refund` (
  `apply_refund_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '运营商ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '预订人员ID：驾驶人',
  `refund_money` int(11) DEFAULT NULL COMMENT '退款金额，单位分',
  `apply_refund_time` datetime DEFAULT NULL COMMENT '请求退款时间',
  `refund_status` tinyint(3) unsigned NOT NULL COMMENT '退款状态，0-未受理，1-退款中，2-退款成功，3-退款失败',
  `refund_time` datetime DEFAULT NULL COMMENT '退款时间',
  `refund_no` varchar(64) DEFAULT NULL COMMENT '退款编号',
  `orders_num` varchar(64) DEFAULT NULL COMMENT '执行退款的订单编号',
  `pay_type` tinyint(4) NOT NULL COMMENT '付款方式：0 现金；1 支付宝；2 微信；3 银联 4 余额',
  `refund_type` tinyint(4) NOT NULL COMMENT '退款类型：1 优惠券费用；2 包期车费用；3 车位预订费；4 .停车费',
  `is_auto` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否是系统自动发起：0 否；1是',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注信息：退款原因描述',
  PRIMARY KEY (`apply_refund_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退款申请表';

-- ----------------------------
-- Table structure for approve_info
-- ----------------------------
DROP TABLE IF EXISTS `approve_info`;
CREATE TABLE `approve_info` (
  `appro_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '申请人ID',
  `appr_user_id` bigint(20) unsigned NOT NULL COMMENT '审批人ID',
  `appr_time` datetime NOT NULL COMMENT '审批时间',
  `rslt` tinyint(3) unsigned NOT NULL COMMENT '审批结果：0 不通过；1 通过',
  `remark` varchar(256) DEFAULT NULL COMMENT '审批不通过时的描述信息',
  PRIMARY KEY (`appro_id`),
  KEY `idx_approve_info_app_id` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审核信息：审核停车运营商、广告、公告时生成\r\n1）审批不通过时必须填写审批意见\r\n2）关联表、关联主';

-- ----------------------------
-- Table structure for arrears_pay
-- ----------------------------
DROP TABLE IF EXISTS `arrears_pay`;
CREATE TABLE `arrears_pay` (
  `pay_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dealer_code` varchar(32) NOT NULL COMMENT '商户编号',
  `bill_no` varchar(64) NOT NULL COMMENT '账单编号：用于第三方支付下单',
  `create_time` datetime NOT NULL COMMENT '创建时间：二维码请求时间',
  `status` tinyint(4) NOT NULL COMMENT '状态：0处理中；1已处理',
  `other_bill_no` varchar(64) DEFAULT NULL COMMENT '第三方支付编号：支付宝或者微信公众号的单号',
  `money` int(11) NOT NULL COMMENT '金额单位：分，多个欠费单合并后的金额',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_park_code` varchar(64) DEFAULT NULL COMMENT '支付停车场编号：手持机上传',
  `collector_code` varchar(64) DEFAULT NULL COMMENT '收费员编号：手持机上传',
  `terminal_code` varchar(64) DEFAULT NULL COMMENT '手持机终端编号',
  PRIMARY KEY (`pay_id`),
  KEY `idx_arrears_pay_bill_no` (`bill_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='二维码扫码支付欠费记录';

-- ----------------------------
-- Table structure for arrears_pay_detail
-- ----------------------------
DROP TABLE IF EXISTS `arrears_pay_detail`;
CREATE TABLE `arrears_pay_detail` (
  `pay_id` bigint(20) unsigned NOT NULL COMMENT '手持机欠费补缴记录ID',
  `arrears_id` bigint(20) unsigned NOT NULL COMMENT '欠费记录ID',
  PRIMARY KEY (`pay_id`,`arrears_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='欠费补缴明细';

-- ----------------------------
-- Table structure for arrears_record
-- ----------------------------
DROP TABLE IF EXISTS `arrears_record`;
CREATE TABLE `arrears_record` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：记录标识',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车点ID',
  `bill_code` varchar(64) DEFAULT NULL COMMENT '路边欠费账单编号',
  `total_fee` int(11) DEFAULT NULL COMMENT '总金额：单位分',
  `arrears` int(11) DEFAULT NULL COMMENT '欠费金额：单位分',
  `unid` varchar(64) NOT NULL COMMENT '停车唯一标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间，等于结算时间',
  `status` tinyint(4) NOT NULL COMMENT '状态：0未支付；1已支付；99作废',
  `plate_no` varchar(10) DEFAULT NULL COMMENT '车牌号码',
  `plate_color` tinyint(4) DEFAULT NULL COMMENT '车牌颜色，-1没有车牌颜色 0-其他,1-蓝色,2-黄色,3-黑色,4-白色',
  `phone` varchar(32) DEFAULT NULL COMMENT '报警信息时前台写入的手机号码',
  `collector_id` bigint(20) unsigned DEFAULT NULL COMMENT '收费员ID：产生欠费记录的收费员',
  PRIMARY KEY (`record_id`),
  KEY `idx_arrears_record_alarm` (`plate_no`,`plate_color`,`status`,`phone`),
  KEY `idx_arrears_record_bcode` (`dealer_id`,`park_id`,`bill_code`,`unid`,`status`),
  KEY `idx_arrears_record_dc` (`dealer_id`,`collector_id`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='欠费记录';

-- ----------------------------
-- Table structure for back_payment
-- ----------------------------
DROP TABLE IF EXISTS `back_payment`;
CREATE TABLE `back_payment` (
  `back_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '补缴ID',
  `record_id` bigint(20) unsigned DEFAULT NULL COMMENT '入账记录',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '收费员隶属商户ID',
  `pay_money` int(11) NOT NULL COMMENT '补缴费总额，单位：分',
  `fill_time` datetime DEFAULT NULL COMMENT '补缴时间',
  `operator_id` bigint(20) unsigned NOT NULL COMMENT '操作员ID',
  `operator_name` varchar(32) DEFAULT NULL COMMENT '操作员名称',
  PRIMARY KEY (`back_id`),
  KEY `inx_fpr_fill_time` (`fill_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='补缴费记录信息表';

-- ----------------------------
-- Table structure for bag_period_charge
-- ----------------------------
DROP TABLE IF EXISTS `bag_period_charge`;
CREATE TABLE `bag_period_charge` (
  `charge_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：包期账单ID',
  `vehicle_id` bigint(20) unsigned NOT NULL COMMENT '包期车ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID：办理包期的用户ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车点ID',
  `rule_id` bigint(20) unsigned NOT NULL COMMENT '包期规则ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `num` smallint(6) DEFAULT NULL COMMENT '数量：月数 或者 年数',
  `money` int(11) NOT NULL COMMENT '充值金额：金额必须是单价的倍数',
  `bill_no` varchar(64) NOT NULL COMMENT '账单编号',
  `bgn_date` date DEFAULT NULL COMMENT '开始日期：前台输入',
  `end_date` date DEFAULT NULL COMMENT '结束日期：开始日期+金额/单价 月数',
  `is_effected` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '账单是否生效：0-未生效，1-已生效,2-已取消包期',
  PRIMARY KEY (`charge_id`),
  KEY `idx_bag_period_charge_vehicle_id` (`vehicle_id`),
  KEY `idx_bag_period_charge_rule_id` (`rule_id`),
  KEY `idx_bag_period_charge_bill_no` (`bill_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='包期账单：包期时直接充值或者包期到期后充值产生\r\n1）支付时开始日期和结束日期不能有交集\r\n';

-- ----------------------------
-- Table structure for bag_period_charge_private
-- ----------------------------
DROP TABLE IF EXISTS `bag_period_charge_private`;
CREATE TABLE `bag_period_charge_private` (
  `charge_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：包期账单ID',
  `vehicle_id` bigint(20) unsigned DEFAULT NULL COMMENT '包期车ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID：办理包期的用户ID',
  `dealer_id` bigint(20) unsigned DEFAULT NULL COMMENT '商户ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `num` smallint(6) NOT NULL COMMENT '数量：月数',
  `money` int(11) NOT NULL COMMENT '充值金额',
  `bill_no` varchar(64) NOT NULL COMMENT '账单编号',
  `last_end_date` date DEFAULT NULL COMMENT '上次包期结束日期',
  `bgn_date` date NOT NULL COMMENT '开始日期：前台输入',
  `end_date` date NOT NULL COMMENT '结束日期：开始日期+ 月数',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '包期类型：1全部停车场；2部分停车场',
  `plate_no` varchar(10) NOT NULL COMMENT '车牌号码',
  `plate_color` tinyint(3) unsigned NOT NULL COMMENT '车牌颜色，0-其他,1-蓝色,2-黄色,3-黑色,4-白色',
  `plate_type` tinyint(3) unsigned DEFAULT NULL COMMENT '车牌类别:0-无类型,1-92式民用车,2-警用车,3-上下军车,4-92式武警车,5-左右军车车牌类型(一行结构),7-02式个性化车,8-黄色双行尾牌,9-04式新军车,10-使馆车,11-一行结构的新WJ车,12-两行结构的新WJ车,13-黄色1225农用车,14-绿色1325农用车,15-黄色1325农用车,16-摩托车, 17-13式新武警总部一行车牌，18-13式新武警总部两行车牌，19-民航车牌类型,100-教练车,101-临时行驶车,102-挂车,103-领馆汽车,104-港澳入出车,105-临时入境车',
  `vehicle_color` tinyint(3) unsigned DEFAULT NULL COMMENT '车辆颜色，0：其他；1：白色；2：银色；3：灰色；4：黑色；5：红色；6：深蓝；7：蓝色；8：黄色；9：绿色；10：棕色；11.:粉色;12: 紫色',
  `vehicle_type` tinyint(3) unsigned DEFAULT NULL COMMENT '车辆类别，1-小型汽车,2-大型汽车,0-其他',
  `vehicle_brand` varchar(32) DEFAULT NULL COMMENT '车辆品牌',
  `owner_name` varchar(32) DEFAULT NULL COMMENT '车主姓名',
  `owner_phone` varchar(32) NOT NULL COMMENT '车主电话',
  `owner_email` varchar(64) DEFAULT NULL COMMENT '车主邮件',
  `owner_address` varchar(64) DEFAULT NULL COMMENT '车主地址',
  `certify_no` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `status` tinyint(4) NOT NULL COMMENT '状态：0未付；1已付',
  `notice_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '通知路边是否成功，0否；1是',
  `notice_count` smallint(6) DEFAULT NULL COMMENT '通知次数',
  PRIMARY KEY (`charge_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='私有云包期账单：象山模式，包期时不需要选择停车场，可包商户下全部停车场\r\n数模支持选择部分停车场';

-- ----------------------------
-- Table structure for bag_period_rule
-- ----------------------------
DROP TABLE IF EXISTS `bag_period_rule`;
CREATE TABLE `bag_period_rule` (
  `rule_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：规则ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '主键,停车点Id',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `period_name` varchar(32) NOT NULL COMMENT '规则名称',
  `period_type` tinyint(3) unsigned NOT NULL COMMENT '包期类型，1：包月，2：包年',
  `money` int(11) NOT NULL COMMENT '包期金额，单位：分',
  `is_natural_month` tinyint(3) unsigned NOT NULL COMMENT '是否自然月，0-否，1-是',
  `is_delete` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否已删除，0-否，1-是',
  `plate_color` tinyint(3) NOT NULL DEFAULT '1' COMMENT '车牌颜色，-1没有车牌颜色 0-其他,1-蓝色,2-黄色,3-黑色,4-白色；5绿色',
  PRIMARY KEY (`rule_id`),
  KEY `idx_bag_period_rule_dealer` (`dealer_id`,`park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='包期规则表';

-- ----------------------------
-- Table structure for bag_period_vehicle
-- ----------------------------
DROP TABLE IF EXISTS `bag_period_vehicle`;
CREATE TABLE `bag_period_vehicle` (
  `vehicle_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车点ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID：办理包期的用户ID',
  `plate_no` varchar(10) NOT NULL COMMENT '车牌号码',
  `plate_color` tinyint(3) unsigned NOT NULL COMMENT '车牌颜色，0-其他,1-蓝色,2-黄色,3-黑色,4-白色',
  `plate_type` tinyint(3) unsigned DEFAULT NULL COMMENT '车牌类别:0-无类型,1-92式民用车,2-警用车,3-上下军车,4-92式武警车,5-左右军车车牌类型(一行结构),7-02式个性化车,8-黄色双行尾牌,9-04式新军车,10-使馆车,11-一行结构的新WJ车,12-两行结构的新WJ车,13-黄色1225农用车,14-绿色1325农用车,15-黄色1325农用车,16-摩托车, 17-13式新武警总部一行车牌，18-13式新武警总部两行车牌，19-民航车牌类型,100-教练车,101-临时行驶车,102-挂车,103-领馆汽车,104-港澳入出车,105-临时入境车',
  `vehicle_color` tinyint(3) unsigned DEFAULT NULL COMMENT '车辆颜色，0：其他；1：白色；2：银色；3：灰色；4：黑色；5：红色；6：深蓝；7：蓝色；8：黄色；9：绿色；10：棕色；11.:粉色;12: 紫色',
  `vehicle_type` tinyint(3) unsigned DEFAULT NULL COMMENT '车辆类别，1-小型汽车,2-大型汽车,0-其他',
  `vehicle_brand` varchar(32) DEFAULT NULL COMMENT '车辆品牌',
  `owner_name` varchar(32) DEFAULT NULL COMMENT '车主姓名',
  `owner_phone` varchar(32) DEFAULT NULL COMMENT '车主电话',
  `owner_email` varchar(64) DEFAULT NULL COMMENT '车主邮件',
  `owner_address` varchar(64) DEFAULT NULL COMMENT '车主地址',
  `certify_no` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `owner_sex` tinyint(3) unsigned DEFAULT NULL COMMENT '车主性别：0-男，1-女',
  `is_delete` tinyint(3) unsigned NOT NULL COMMENT '是否已删除，0-否，1-是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `bgn_date` date DEFAULT NULL COMMENT '开始日期：前台输入',
  `end_date` date DEFAULT NULL COMMENT '结束日期：通过每次缴费金额和包期规则计算获得。金额是单价的整数倍',
  `is_effected` tinyint(3) unsigned NOT NULL COMMENT '是否有效：0 无效；1 有效；2 过期',
  PRIMARY KEY (`vehicle_id`),
  KEY `idx_bag_period_vehicle_user_id` (`user_id`),
  KEY `idx_bag_period_vehicle_plate` (`plate_no`,`plate_color`),
  KEY `idx_bag_period_vehicle_dealer` (`dealer_id`,`park_id`,`plate_no`,`plate_color`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='包期车辆关系表：包期时产生\r\n1）付费未成功前时是无效状态\r\n2）付费后状态有效\r\n3）定';

-- ----------------------------
-- Table structure for bag_period_vehicle_private
-- ----------------------------
DROP TABLE IF EXISTS `bag_period_vehicle_private`;
CREATE TABLE `bag_period_vehicle_private` (
  `vehicle_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `plate_no` varchar(10) NOT NULL COMMENT '车牌号码',
  `plate_color` tinyint(3) unsigned NOT NULL COMMENT '车牌颜色，0-其他,1-蓝色,2-黄色,3-黑色,4-白色',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `bgn_date` date DEFAULT NULL COMMENT '开始日期：第一次生成时',
  `end_date` date DEFAULT NULL COMMENT '结束日期',
  `is_effected` tinyint(3) unsigned NOT NULL COMMENT '是否有效：0 无效；1 有效；2 过期',
  `is_delete` tinyint(3) unsigned NOT NULL COMMENT '是否已删除，0-否，1-是',
  PRIMARY KEY (`vehicle_id`),
  KEY `idx_bag_period_vehicle_dealer` (`dealer_id`),
  KEY `idx_bag_period_vehicle_plate` (`is_delete`,`plate_no`,`plate_color`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='包期车辆关系表：包期时产生后期只进行更新';

-- ----------------------------
-- Table structure for cancel_bag_vehicle
-- ----------------------------
DROP TABLE IF EXISTS `cancel_bag_vehicle`;
CREATE TABLE `cancel_bag_vehicle` (
  `cancel_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` bigint(20) unsigned NOT NULL COMMENT '申请表ID',
  `vehicle_id` bigint(20) unsigned NOT NULL COMMENT '包期记录ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  `status` int(3) NOT NULL DEFAULT '0' COMMENT '审核状态：0-待审核；1-驳回；2-确认取消;',
  `overrule_reason` varchar(60) DEFAULT NULL COMMENT '驳回原因',
  `remark` varchar(60) DEFAULT NULL COMMENT '备注',
  `vehicle_bag_id` bigint(20) NOT NULL COMMENT '固定车上传表ID',
  PRIMARY KEY (`cancel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for vehicle_bag_info
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_bag_info`;
CREATE TABLE `vehicle_bag_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `name` varchar(32) DEFAULT NULL COMMENT '车主姓名',
  `phone` varchar(32) DEFAULT NULL COMMENT '电话号码',
  `plate_no` varchar(10) NOT NULL COMMENT '车牌号码',
  `plate_color` tinyint(4) NOT NULL COMMENT '车牌颜色：0-其他,1-蓝色,2-黄色,3-黑色,4-白色',
  `plate_type` tinyint(3) unsigned DEFAULT NULL COMMENT '车牌类别:0-无类型,1-92式民用车,2-警用车,3-上下军车,4-92式武警车,5-左右军车车牌类型(一行结构),7-02式个性化车,8-黄色双行尾牌,9-04式新军车,10-使馆车,11-一行结构的新WJ车,12-两行结构的新WJ车,13-黄色1225农用车,14-绿色1325农用车,15-黄色1325农用车,16-摩托车, 17-13式新武警总部一行车牌，18-13式新武警总部两行车牌，19-民航车牌类型,100-教练车,101-临时行驶车,102-挂车,103-领馆汽车,104-港澳入出车,105-临时入境车',
  `vehicle_color` tinyint(3) unsigned DEFAULT NULL COMMENT '车辆颜色，0：其他；1：白色；2：银色；3：灰色；4：黑色；5：红色；6：深蓝；7：蓝色；8：黄色；9：绿色；10：棕色；11.:粉色;12: 紫色',
  `vehicle_type` tinyint(3) unsigned DEFAULT NULL COMMENT '车辆类别，1-小型汽车,2-大型汽车,0-其他',
  `vehicle_brand` varchar(32) DEFAULT NULL COMMENT '车辆品牌',
  `owner_email` varchar(64) DEFAULT NULL COMMENT '车主邮件',
  `owner_address` varchar(64) DEFAULT NULL COMMENT '车主地址',
  `certify_no` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `owner_sex` tinyint(3) unsigned DEFAULT NULL COMMENT '车主性别：0-男，1-女',
  `start_date` datetime NOT NULL COMMENT '包期开始时间',
  `end_date` datetime NOT NULL COMMENT '包期结束时间',
  PRIMARY KEY (`id`),
  KEY `idx_vehicle_bag_info_po` (`dealer_id`,`park_id`,`plate_no`,`plate_color`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='固定（包期）车';

-- ----------------------------
-- Table structure for bank_card_info
-- ----------------------------
DROP TABLE IF EXISTS `bank_card_info`;
CREATE TABLE `bank_card_info` (
  `card_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `card_no` bigint(24) unsigned NOT NULL COMMENT '银行卡号',
  `card_bank` varchar(16) NOT NULL COMMENT '所属银行',
  `card_type` varchar(16) NOT NULL COMMENT '银行卡类型',
  `real_name` varchar(24) NOT NULL COMMENT '持卡人姓名',
  `limit_amount` int(11) NOT NULL COMMENT '限额',
  `phone` bigint(16) unsigned NOT NULL COMMENT '手机号码',
  PRIMARY KEY (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='绑定银行卡信息';

-- ----------------------------
-- Table structure for banner_info
-- ----------------------------
DROP TABLE IF EXISTS `banner_info`;
CREATE TABLE `banner_info` (
  `banner_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：广告ID',
  `rela_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '运营商服务关系表ID',
  `app_id` bigint(20) unsigned NOT NULL COMMENT '申请ID',
  `user_id` bigint(20) NOT NULL COMMENT '申请人ID\r\n            只有在运营商入驻时这里才是驾驶人\r\n            其他是海康管理员和运营商管理员\r\n            ',
  `banner_title` varchar(64) NOT NULL COMMENT '广告标题',
  `banner_content` varchar(2048) NOT NULL COMMENT '广告正文',
  `begin_valid_time` datetime NOT NULL COMMENT '有效开始时间',
  `end_valid_time` datetime NOT NULL COMMENT '有效结束时间',
  `banner_status` tinyint(3) unsigned NOT NULL COMMENT '广告状态：0 新增；1 待审核；2 审核不通过；3 上线；4 下线；5 已删除',
  `photo_name` varchar(64) DEFAULT NULL COMMENT '广告图片名称',
  `photo_path` varchar(256) NOT NULL COMMENT '图片路径',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `issue_type` tinyint(3) DEFAULT NULL COMMENT '发布类型：1 门户网站；2APP',
  `issue_range` varchar(6) NOT NULL COMMENT '发布范围：地址码，参见表ADDR_INFO',
  `type` tinyint(3) unsigned NOT NULL COMMENT '广告类型：1 海康；2 运营商',
  `order_no` int(11) NOT NULL COMMENT '排序',
  PRIMARY KEY (`banner_id`),
  KEY `idx_banner_info_app_id` (`app_id`),
  KEY `idx_banner_info_time` (`type`,`banner_status`,`issue_range`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告信息：运营商或者海康管理员提交广告时产生\r\n1）新增页面点击保存时产生APPLY_INFO\r\n2';

-- ----------------------------
-- Table structure for banner_pro
-- ----------------------------
DROP TABLE IF EXISTS `banner_pro`;
CREATE TABLE `banner_pro` (
  `pro_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：过程ID',
  `rela_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '运营商服务关系ID',
  `appro_id` bigint(20) unsigned NOT NULL COMMENT '审批ID',
  `banner_id` bigint(20) unsigned NOT NULL COMMENT '广告ID',
  `app_id` bigint(20) unsigned NOT NULL COMMENT '申请信息',
  `user_id` bigint(20) NOT NULL COMMENT '申请人ID\r\n            只有在运营商入驻时这里才是驾驶人\r\n            其他是海康管理员和运营商管理员\r\n            ',
  `banner_title` varchar(64) NOT NULL COMMENT '广告标题',
  `banner_content` varchar(2048) NOT NULL COMMENT '广告正文',
  `begin_valid_time` datetime NOT NULL COMMENT '有效开始时间',
  `end_valid_time` datetime NOT NULL COMMENT '有效结束时间',
  `banner_status` tinyint(3) unsigned NOT NULL COMMENT '广告状态：0 新增；1 待审核；2 审核不通过；3 上线；4 下线；5 已删除',
  `photo_name` varchar(64) DEFAULT NULL COMMENT '广告图片名称',
  `photo_path` varchar(256) NOT NULL COMMENT '图片路径',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `issue_type` tinyint(3) DEFAULT NULL COMMENT '发布类型：1 门户网站；2APP',
  `issue_range` varchar(6) NOT NULL COMMENT '发布范围：地址码，参见表ADDR_INFO',
  `type` tinyint(3) unsigned NOT NULL COMMENT '广告类型：1 海康；2 运营商',
  PRIMARY KEY (`pro_id`),
  KEY `idx_banner_pro_banner_id` (`banner_id`),
  KEY `idx_banner_pro_app_id` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告申请信息：审核不通过后添加';

-- ----------------------------
-- Table structure for berth_book_orders
-- ----------------------------
DROP TABLE IF EXISTS `berth_book_orders`;
CREATE TABLE `berth_book_orders` (
  `orders_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车场标识',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '预订人员ID：驾驶人',
  `bill_no` varchar(64) NOT NULL COMMENT '订单编号',
  `create_time` datetime NOT NULL COMMENT '订单生成时间',
  `berth_code` varchar(64) DEFAULT NULL COMMENT '预订车位的编号',
  `plate_no` varchar(10) NOT NULL COMMENT '车牌号码',
  `plate_color` tinyint(3) unsigned NOT NULL COMMENT '车牌颜色，0-其他,1-蓝色,2-黄色,3-黑色,4-白色',
  `phone` varchar(12) DEFAULT NULL COMMENT '预留手机号码',
  `book_rule_id` bigint(20) unsigned NOT NULL COMMENT '预订规则ID',
  `book_time` smallint(5) unsigned NOT NULL COMMENT '预订时长，单位分钟',
  `money` int(10) unsigned NOT NULL COMMENT '订单金额，单位分',
  `pay_time_limit` smallint(5) unsigned NOT NULL COMMENT '订单支付时限，单位分钟',
  `un_book_time_limit` smallint(5) unsigned DEFAULT NULL COMMENT '退订时限，超过该时限后不可退订（超过该时限后可接受续时），单位：分钟',
  `un_book_time` datetime DEFAULT NULL COMMENT '退订时限，在这个时刻前可申请退款',
  `effect_time` datetime DEFAULT NULL COMMENT '订单生效时间',
  `end_time` datetime DEFAULT NULL COMMENT '订单截止时间',
  `status` tinyint(3) unsigned NOT NULL COMMENT '订单状态，\r\n            0-未生效（订单生成且未超时、未支付），\r\n            1-已生效（有效时长内），\r\n            2-订单已失效（未支付），\r\n            3-正常退款流程中，\r\n            4-正常退款流程结束，\r\n            5-支付超时退款中，\r\n            6-支付超时退款流程结束，\r\n            7-系统异常时退款中，\r\n            8-系统异常时退款流程结束，\r\n            9-订单已结束（支付成功已过截止时间，暂时没有线程维护次状态，需要再获取订单时进行判断）',
  PRIMARY KEY (`orders_id`),
  UNIQUE KEY `idx_berth_book_orders_bill_no` (`bill_no`),
  KEY `idx_berth_book_orders_dealer` (`dealer_id`,`park_id`),
  KEY `idx_berth_book_orders_user` (`user_id`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车位预订信息表';

-- ----------------------------
-- Table structure for berth_book_rule
-- ----------------------------
DROP TABLE IF EXISTS `berth_book_rule`;
CREATE TABLE `berth_book_rule` (
  `book_rule_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：规则ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '运营商ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `rule_content_json` varchar(1024) NOT NULL COMMENT '规则内容的json字符串，预订收费时间单位：bookFeeUnit，预订单位时间收费金额：bookFee，可预订的最长时间：maxBookTime，退订时限/续订时限(距离订单截止的时间)：unBookTimeLimit，不收费手续费时限（距离订单生效的时间）：noFeeTimeLimit，退订收费时间单位：unBookFeeUnit，退订收费单位时间收费金额：unBookFee，订单支付时限：payTimeLimit',
  PRIMARY KEY (`book_rule_id`),
  KEY `idx_berth_book_rule_dealer` (`dealer_id`,`park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车位预订规则表';

-- ----------------------------
-- Table structure for berth_info
-- ----------------------------
DROP TABLE IF EXISTS `berth_info`;
CREATE TABLE `berth_info` (
  `berth_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '泊位主键ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '运营商ID',
  `docking_id` bigint(20) unsigned DEFAULT NULL COMMENT '对接网关ID',
  `unid` varchar(64) DEFAULT NULL COMMENT '过车记录ID',
  `berth_code` varchar(64) DEFAULT NULL COMMENT '泊位编号：全局唯一',
  `berth_number` varchar(64) DEFAULT NULL COMMENT '泊位编号：停车场内唯一',
  `status` tinyint(4) NOT NULL COMMENT '泊位状态(0-空闲, 1-占用，2-未知)',
  `plate_no` varchar(10) DEFAULT NULL COMMENT '车牌号码',
  `plate_color` tinyint(4) DEFAULT NULL COMMENT '车牌颜色，-1没有车牌颜色 0-其他,1-蓝色,2-黄色,3-黑色,4-白色',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已经删除：0 否； 1 是',
  `update_time` datetime DEFAULT NULL COMMENT '地磁更新时间 或者 枪球更新时间',
  `occupy_time` datetime DEFAULT NULL COMMENT '占用时间：泊位状态从0，2 到1 的时设置，1到1 不更新，1到0 更新成null',
  `camera_no` varchar(64) DEFAULT NULL COMMENT '球机设备编号',
  `camera_name` varchar(32) DEFAULT NULL COMMENT '球机设备编号',
  `detector_id` bigint(20) unsigned DEFAULT NULL COMMENT '地磁检测器Id',
  `device_id` bigint(20) unsigned DEFAULT NULL COMMENT '抢球Id',
  PRIMARY KEY (`berth_id`),
  KEY `idx_berth_info_park` (`park_id`,`berth_code`,`is_delete`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='泊位信息表：路边系统上传';

-- ----------------------------
-- Table structure for berth_rela
-- ----------------------------
DROP TABLE IF EXISTS `berth_rela`;
CREATE TABLE `berth_rela` (
  `rela_id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `main_berth` varchar(64) DEFAULT NULL COMMENT '主泊位编号',
  `second_berth` varchar(64) DEFAULT NULL COMMENT '副泊位编号',
  PRIMARY KEY (`rela_id`),
  KEY `idx_berth_rela_dp` (`dealer_id`,`park_id`,`main_berth`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主副泊位关系表：pos上传入车时添加，出车时删除';

-- ----------------------------
-- Table structure for bulletin_info
-- ----------------------------
DROP TABLE IF EXISTS `bulletin_info`;
CREATE TABLE `bulletin_info` (
  `bulletin_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `rela_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '运营商服务关系ID',
  `app_id` bigint(20) unsigned NOT NULL COMMENT '申请ID',
  `user_id` bigint(20) NOT NULL COMMENT '申请人ID\r\n            只有在运营商入驻时这里才是驾驶人\r\n            其他是海康管理员和运营商管理员\r\n            ',
  `title` varchar(64) NOT NULL COMMENT '公告标题',
  `content` varchar(512) NOT NULL COMMENT '公告内容',
  `begin_valid_time` datetime NOT NULL COMMENT '有效开始时间',
  `end_valid_time` datetime NOT NULL COMMENT '有效结束时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `bulletin_status` tinyint(3) unsigned NOT NULL COMMENT '公告状态：0 新增；1 待审核；2 审核不通过；3 上线；4 下线；5 已删除',
  `issue_range` varchar(6) NOT NULL COMMENT '发布范围：地址码，参见表ADDR_INFO',
  `issue_target` tinyint(3) unsigned DEFAULT NULL COMMENT '发布对象（运营商管理员添加时默认为2，且不能更改）：1 运营商；2 驾驶人',
  `type` tinyint(3) unsigned NOT NULL COMMENT '公告类型：1 海康；2 运营商',
  PRIMARY KEY (`bulletin_id`),
  KEY `idx_bulletin_time` (`type`,`bulletin_status`,`issue_range`,`create_time`),
  KEY `idx_bulletin_app_id` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告信息：运营商或者海康管理员提交公告时产生\r\n1）新增页面点击保存时产生APPLY_INFO\r\n2';

-- ----------------------------
-- Table structure for bulletin_pro
-- ----------------------------
DROP TABLE IF EXISTS `bulletin_pro`;
CREATE TABLE `bulletin_pro` (
  `pro_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：过程ID',
  `rela_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '运营商服务关系ID',
  `appro_id` bigint(20) unsigned NOT NULL COMMENT '审批ID',
  `app_id` bigint(20) unsigned NOT NULL COMMENT '申请信息',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '申请人ID',
  `bulletin_id` bigint(20) unsigned NOT NULL COMMENT '公告ID',
  `title` varchar(64) NOT NULL COMMENT '公告标题',
  `content` varchar(512) NOT NULL COMMENT '公告内容',
  `begin_valid_time` datetime NOT NULL COMMENT '有效开始时间',
  `end_valid_time` datetime NOT NULL COMMENT '有效结束时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `bulletin_status` tinyint(3) unsigned NOT NULL COMMENT '公告状态：0 新增；1 待审核；2 审核不通过；3 上线；4 下线；5 已删除',
  `issue_range` varchar(6) NOT NULL COMMENT '发布范围：地址码，参见表ADDR_INFO',
  `issue_target` tinyint(3) unsigned DEFAULT NULL COMMENT '发布对象（运营商管理员添加时默认为2，且不能更改）：1 运营商；2 驾驶人',
  `type` tinyint(3) unsigned NOT NULL COMMENT '公告类型：1 海康；2 运营商',
  PRIMARY KEY (`pro_id`),
  KEY `idx_bulletin_pro_bulletin_id` (`bulletin_id`),
  KEY `idx_bulletin_pro_app_id` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告申请信息：审核不通过后添加';

-- ----------------------------
-- Table structure for camera_config
-- ----------------------------
DROP TABLE IF EXISTS `camera_config`;
CREATE TABLE `camera_config` (
  `camera_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '通道ID',
  `xml_config_info` text COMMENT 'XML配置信息',
  `operator_id` bigint(20) unsigned NOT NULL COMMENT '操作员ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`camera_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通道参数配置信息表';

-- ----------------------------
-- Table structure for camera_info
-- ----------------------------
DROP TABLE IF EXISTS `camera_info`;
CREATE TABLE `camera_info` (
  `camera_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '通道ID',
  `device_id` bigint(20) unsigned NOT NULL COMMENT '设备ID',
  `control_unit_id` bigint(20) unsigned DEFAULT NULL COMMENT '控制中心ID',
  `power_channel_id` bigint(20) DEFAULT NULL COMMENT '电源通道ID',
  `index_code` varchar(64) DEFAULT NULL COMMENT '索引编号',
  `name` varchar(256) DEFAULT NULL COMMENT '监控点名称',
  `channel_no` int(11) DEFAULT NULL COMMENT '通道号，从1开始',
  `camera_type` smallint(6) DEFAULT NULL COMMENT '摄像机类型：0-枪机；1-半球；2-快球；3-云台',
  `vrm_server_id` bigint(20) unsigned DEFAULT NULL COMMENT 'VRM服务器ID',
  `record_location_set` smallint(6) DEFAULT '0' COMMENT '录像位置集合：0-NONE；1-设备；2-NVR；3-设备+NVR；4-NVT；8-BN',
  `record_mode` smallint(6) NOT NULL DEFAULT '0' COMMENT '录像模式：0-普通；1-网络',
  `stream_type` smallint(6) NOT NULL DEFAULT '0' COMMENT '码流类型 0-主码流 1-录像码流 2-3G码流',
  `stream_link_type` smallint(6) NOT NULL DEFAULT '1' COMMENT '码流连接类型：1-TCP；2-UDP；3-RTP；4-MULTICAST',
  `share_flag` smallint(6) NOT NULL DEFAULT '1' COMMENT '是否允许下级控制中心使用，1表示可以使用，0表示不允许使用',
  `matrix_code` varchar(64) DEFAULT NULL COMMENT '矩阵编号',
  `pixel` smallint(6) DEFAULT NULL COMMENT '摄像头像素：1-普通；2-130万高清；3-200万高清；4-300万高清',
  `ptz_type` smallint(6) NOT NULL DEFAULT '1' COMMENT 'PTZ类型：1-全方位云台（带转动和变焦）；2-只有变焦，不带转动；3-只有转动，不带变焦；4-无云台，无变焦',
  `is_bind_audio` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否绑定音频',
  `is_display` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否显示',
  `main_bit_rate` int(11) DEFAULT NULL COMMENT '主码流，视频码率，单位Kbps',
  `main_rate_type` int(11) NOT NULL DEFAULT '0' COMMENT '主码流，码率类型：0表示定码率，1表示变码率',
  `stream_url` varchar(256) DEFAULT NULL COMMENT '主码流静态取流URL',
  `sequence_idx` int(11) NOT NULL DEFAULT '1' COMMENT '排序索引',
  `operator_id` int(10) unsigned DEFAULT NULL COMMENT '操作员ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `region_id` int(11) DEFAULT NULL COMMENT 'region_id',
  `latitude` varchar(32) DEFAULT NULL COMMENT '卡口所在地理位置纬度',
  `longitude` varchar(32) DEFAULT NULL COMMENT '卡口所在地理位置经度',
  `is_show_map` smallint(6) DEFAULT NULL COMMENT '是否显示地图上（0：否，1：是）',
  `gis_name` varchar(64) DEFAULT NULL COMMENT '地图预留字段（可能地图上线的名称和列表里的名称不一致）',
  `cascade_index` varchar(128) DEFAULT NULL COMMENT 'NCG级联配置cascadeIndex',
  `local_cascade_index` varchar(128) DEFAULT NULL COMMENT '本级级联cascadeIndex',
  `channel_type` smallint(6) DEFAULT NULL COMMENT '通道类型（1：模拟通道，2：数字通道）',
  `manufacturer_type` smallint(6) DEFAULT NULL COMMENT '厂商类型（10001：海康，20001：大华）',
  PRIMARY KEY (`camera_id`),
  KEY `reference_4_fk` (`device_id`),
  KEY `relationship_86_fk` (`power_channel_id`),
  KEY `inx_camera_info_control_id` (`control_unit_id`),
  KEY `inx_camera_info_is_deplay` (`is_display`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='摄像机信息';

-- ----------------------------
-- Table structure for camera_status
-- ----------------------------
DROP TABLE IF EXISTS `camera_status`;
CREATE TABLE `camera_status` (
  `camera_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '通道ID',
  `is_recording` smallint(6) DEFAULT '0' COMMENT '是否在录像',
  `is_online` smallint(6) DEFAULT '0' COMMENT '是否在线',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `heartbeat_time` datetime DEFAULT NULL COMMENT '最后心跳时间',
  `local_addr` varchar(64) DEFAULT NULL COMMENT '摄像机上报的本地IP地址',
  `local_port` int(11) DEFAULT '0' COMMENT '摄像机上报的本地端口',
  `remote_addr` varchar(64) DEFAULT NULL COMMENT '平台检测的远程IP地址',
  `remote_port` int(11) DEFAULT '0' COMMENT '平台检测的远程端口',
  `version` varchar(64) DEFAULT NULL COMMENT '版本信息',
  PRIMARY KEY (`camera_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='摄像机状态信息';

-- ----------------------------
-- Table structure for camera_sub_stream
-- ----------------------------
DROP TABLE IF EXISTS `camera_sub_stream`;
CREATE TABLE `camera_sub_stream` (
  `sub_stream_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '子码流ID',
  `camera_id` bigint(20) unsigned NOT NULL COMMENT '通道ID',
  `stream_type` smallint(6) NOT NULL DEFAULT '0' COMMENT '子码流类型',
  `bit_rate` int(11) NOT NULL DEFAULT '0' COMMENT '视频码率，单位Kbps',
  `rate_type` smallint(6) NOT NULL DEFAULT '0' COMMENT '码率类型：0表示定码率，1表示变码率',
  `stream_url` varchar(256) DEFAULT NULL COMMENT '码流静态取流URL',
  `operator_id` bigint(20) unsigned NOT NULL COMMENT '操作员ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`sub_stream_id`),
  KEY `reference_87_fk` (`camera_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='摄像机子码流';


-- ----------------------------
-- Table structure for charge_info
-- ----------------------------
DROP TABLE IF EXISTS `charge_info`;
CREATE TABLE `charge_info` (
  `charge_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：场库收费ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) unsigned DEFAULT NULL COMMENT '停车场ID',
  `termin_code` varchar(32) DEFAULT NULL COMMENT '对接终端/平台编号',
  `in_time` datetime DEFAULT NULL COMMENT '入场时间',
  `out_time` datetime DEFAULT NULL COMMENT '出场时间，可以是结算时间',
  `park_time` int(11) DEFAULT NULL COMMENT '停车时长，单位为分钟',
  `gate_id` bigint(20) unsigned DEFAULT NULL COMMENT '出车时出入口ID',
  `berth_code` varchar(64) DEFAULT NULL COMMENT '泊位号',
  `card_no` varchar(16) DEFAULT NULL COMMENT '卡号',
  `plate_no` varchar(10) NOT NULL COMMENT '车牌号码',
  `plate_color` tinyint(4) NOT NULL COMMENT '车牌颜色，-1没有车牌颜色 0-其他,1-蓝色,2-黄色,3-黑色,4-白色',
  `plate_type` tinyint(3) unsigned DEFAULT NULL COMMENT '车牌类别:0-无类型,1-92式民用车,2-警用车,3-上下军车,4-92式武警车,5-左右军车车牌类型(一行结构),7-02式个性化车,8-黄色双行尾牌,9-04式新军车,10-使馆车,11-一行结构的新WJ车,12-两行结构的新WJ车,13-黄色1225农用车,14-绿色1325农用车,15-黄色1325农用车,16-摩托车, 17-13式新武警总部一行车牌，18-13式新武警总部两行车牌，19-民航车牌类型,100-教练车,101-临时行驶车,102-挂车,103-领馆汽车,104-港澳入出车,105-临时入境车',
  `car_type` tinyint(3) unsigned DEFAULT NULL COMMENT '车辆类型，1-小型汽车,2-大型汽车,0-其他',
  `in_unid` varchar(64) DEFAULT NULL COMMENT '入场唯一ID',
  `out_unid` varchar(64) DEFAULT NULL COMMENT '出场唯一ID',
  `pay_money` int(11) NOT NULL COMMENT '收费金额，单位为分',
  `pay_type` tinyint(4) NOT NULL COMMENT '付费方式：具体参见《8630平台封闭式停车场库对接接口规范.docx 附录4.4',
  `book_unid` varchar(32) DEFAULT NULL COMMENT '关联的订单编号：车位预订时产生',
  `need_pay` int(11) NOT NULL COMMENT '应收金额，单位为分',
  `park_type` tinyint(3) unsigned DEFAULT NULL COMMENT '停车类型，0-固定车，1-临时车',
  `collector_id` bigint(20) unsigned DEFAULT NULL COMMENT '收费员ID',
  `collector_code` varchar(64) DEFAULT NULL COMMENT '收费员编号',
  `collector_name` varchar(32) DEFAULT NULL COMMENT '收费员名称',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注信息',
  `coupon_code` varchar(32) DEFAULT NULL COMMENT '优惠券编号',
  `deduct_money` int(11) DEFAULT NULL COMMENT '优惠金额：单位分',
  `pay_time` datetime NOT NULL COMMENT '缴费时间',
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID：如果是云平台或者APP缴费的需要设置USER_ID',
  `pay_channel` tinyint(4) DEFAULT NULL COMMENT '支付渠道(和应收一致)：1 管控平台；2 手机app；3 门户网站；4 微信公众号；5支付宝车主服务应用；6代扣；7POS',
  `left_money` int(11) DEFAULT NULL COMMENT '欠费金额',
  `free_reason` tinyint(4) DEFAULT NULL COMMENT '免费原因：0-不免费，1-包期车，2-特殊车牌，3 白名单 4-未达到收费时长（根据收费规则计算得出的金额为0的情况）',
  `pay_park_id` bigint(20) DEFAULT NULL COMMENT '支付场库ID',
  `bill_no` varchar(64) DEFAULT NULL COMMENT '账单编号：支付宝或者微信预下单时生成的唯一账单号，现金支付时没有',
  `bill_code` varchar(64) DEFAULT NULL COMMENT '停车子系统的账单号',
  `other_bill_no` varchar(64) DEFAULT NULL COMMENT '第三方账单编号：支付宝或者微信',
  `pay_account` varchar(64) DEFAULT NULL COMMENT '用户支付账户',
  `is_add_berth` tinyint(4) DEFAULT NULL COMMENT '是否有附近泊位号：0 没有； 1 有',
  `add_berth_code` varchar(64) DEFAULT NULL COMMENT '附近泊位号',
  `source_info` tinyint(3) unsigned DEFAULT NULL COMMENT '数据来源平台：1 云平台；2停车子系统',
  `arrear_type` tinyint(3) unsigned DEFAULT '0' COMMENT '是否是补缴：0不是欠费补缴；1是欠费补缴',
  PRIMARY KEY (`charge_id`,`pay_time`),
  KEY `idx_charge_info_dealer` (`dealer_id`,`park_id`,`pay_time`),
  KEY `idx_charge_info_plate` (`plate_no`,`plate_color`,`pay_time`),
  KEY `idx_charge_info_unid` (`dealer_id`,`in_unid`,`pay_time`),
  KEY `idx_charge_info_bcode` (`dealer_id`,`bill_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收费数据信息表：场库上传'

-- ----------------------------
-- Table structure for cloud_server
-- ----------------------------
DROP TABLE IF EXISTS `cloud_server`;
CREATE TABLE `cloud_server` (
  `server_id` bigint(20) unsigned NOT NULL COMMENT '服务器ID',
  `vrm_server_id` bigint(20) unsigned NOT NULL COMMENT 'VRM服务器ID',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名称',
  `user_pwd` varchar(64) DEFAULT NULL COMMENT '用户密码',
  `data_port` int(11) NOT NULL COMMENT '数据端口',
  `cloudtype` smallint(6) DEFAULT NULL COMMENT '存储类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for collect_parks
-- ----------------------------
DROP TABLE IF EXISTS `collect_parks`;
CREATE TABLE `collect_parks` (
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车点ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '系统用户ID',
  PRIMARY KEY (`park_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车场收藏信息';

-- ----------------------------
-- Table structure for coupon_charge
-- ----------------------------
DROP TABLE IF EXISTS `coupon_charge`;
CREATE TABLE `coupon_charge` (
  `charge_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：优惠券账单ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '驾驶人ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车点ID',
  `coupon_id` bigint(20) unsigned NOT NULL COMMENT '优惠券ID',
  `coupon_code` varchar(20) DEFAULT NULL COMMENT '优惠券编号：全局唯一',
  `bgn_date` datetime NOT NULL COMMENT '开始时间：优惠券有效期的开始时间',
  `end_date` datetime NOT NULL COMMENT '结束时间：优惠券有效期的开始时间',
  `money` int(11) NOT NULL COMMENT '购买金额：等于优惠券单价',
  `bill_no` varchar(64) NOT NULL COMMENT '账单编号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态：0 未使用；1 使用',
  `use_time` datetime DEFAULT NULL COMMENT '使用时间',
  PRIMARY KEY (`charge_id`),
  KEY `idx_coupon_charge_user_id` (`user_id`,`end_date`,`status`,`coupon_code`),
  KEY `idx_coupon_charge_bill_no` (`bill_no`),
  KEY `idx_coupon_charge_code` (`coupon_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券账单：驾驶人抢购\r\n1）一条记录一条优惠券';

-- ----------------------------
-- Table structure for coupon_config
-- ----------------------------
DROP TABLE IF EXISTS `coupon_config`;
CREATE TABLE `coupon_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dealer_id` bigint(20) unsigned DEFAULT NULL COMMENT '商户id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `param_name` varchar(64) NOT NULL COMMENT '参数名称',
  `param_value` varchar(128) NOT NULL COMMENT '参数值',
  PRIMARY KEY (`id`),
  KEY `idx_coupon_config_dealer` (`dealer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='线上优惠券配置表（(用户未配置,取系统配置参数)）';

-- ----------------------------
-- Table structure for coupon_info
-- ----------------------------
DROP TABLE IF EXISTS `coupon_info`;
CREATE TABLE `coupon_info` (
  `coupon_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：优惠券ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车点ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '操作员',
  `rule_code` varchar(32) NOT NULL COMMENT '优惠券规则编号：接口使用',
  `coupon_name` varchar(32) NOT NULL COMMENT '优惠券名称',
  `count` int(11) NOT NULL COMMENT '发放数量',
  `single_num` smallint(6) DEFAULT NULL COMMENT '单人限购量',
  `bgn_date` datetime NOT NULL COMMENT '开始时间：优惠券有效期的开始时间',
  `end_date` datetime NOT NULL COMMENT '结束时间：优惠券有效期的开始时间',
  `price` int(11) NOT NULL COMMENT '单价：单位分，优惠券卖的金额',
  `free_type` tinyint(3) unsigned NOT NULL COMMENT '规则类型：0 减免金额；1 打折；2 全面j减免；3 按时长',
  `free_price` smallint(6) DEFAULT NULL COMMENT '减免的金额：单位分',
  `description` varchar(128) DEFAULT NULL COMMENT '描述信息',
  `free_rate` tinyint(4) DEFAULT NULL COMMENT '折扣率：单位%',
  `free_time` smallint(6) DEFAULT NULL COMMENT '减免时长：单位 分钟',
  `issue_time` datetime DEFAULT NULL COMMENT '发布时间/上线',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态：0 新增；1 上线；2 下线；3 过期；4 删除',
  PRIMARY KEY (`coupon_id`),
  KEY `idx_coupon_info_dealer` (`dealer_id`,`park_id`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券信息\r\n1）上线后的优惠券才能被驾驶人购买\r\n2）被购买过就不能修改和删除  只能下线';

-- ----------------------------
-- Table structure for coupon_issue_record
-- ----------------------------
DROP TABLE IF EXISTS `coupon_issue_record`;
CREATE TABLE `coupon_issue_record` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '驾驶人用户ID',
  `issue_type` int(11) NOT NULL COMMENT '类型：1 前20000名注册冲20送10；2 平台手动赠送；3-9预留 ；10以后代表平台批量发放优惠券记录id',
  `issue_time` datetime DEFAULT NULL COMMENT '发放时间',
  PRIMARY KEY (`record_id`),
  KEY `idx_coupon_issue_record_user` (`user_id`,`issue_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券发放记录表';

-- ----------------------------
-- Table structure for coupon_rule
-- ----------------------------
DROP TABLE IF EXISTS `coupon_rule`;
CREATE TABLE `coupon_rule` (
  `rule_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：规则ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `rule_code` varchar(32) NOT NULL COMMENT '规则编号',
  `rule_name` varchar(32) DEFAULT NULL COMMENT '规则名称',
  `free_type` tinyint(3) unsigned NOT NULL COMMENT '规则类型：0 减免金额；1 打折；2 全面j减免；3 按时长',
  `free_price` smallint(6) DEFAULT NULL COMMENT '减免的金额：单位分',
  `description` varchar(128) DEFAULT NULL COMMENT '描述信息',
  `free_rate` tinyint(4) DEFAULT NULL COMMENT '折扣率：单位%',
  `free_time` smallint(6) DEFAULT NULL COMMENT '减免时长：单位 分钟',
  `issue` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否发放：0 否；1 是',
  `bgn_date` datetime DEFAULT NULL COMMENT '开始时间：优惠券有效期的开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间：优惠券有效期的开始时间',
  PRIMARY KEY (`rule_id`),
  KEY `idx_coupon_rule_dealer` (`dealer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券规则：运营商管理员添加';

-- ----------------------------
-- Table structure for dags_trhid_request_err_record
-- ----------------------------
DROP TABLE IF EXISTS `dags_trhid_request_err_record`;
CREATE TABLE `dags_trhid_request_err_record` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `exe_time` datetime DEFAULT NULL COMMENT '执行时间',
  `url` varchar(64) DEFAULT NULL COMMENT '请求地址',
  `req_data` text COMMENT '接口数据：json格式',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='DAGS通知失败记录';

-- ----------------------------
-- Table structure for db_notify
-- ----------------------------
DROP TABLE IF EXISTS `db_notify`;
CREATE TABLE `db_notify` (
  `db_notify_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '变动ID',
  `table_name` varchar(64) NOT NULL COMMENT '变动表名',
  `key_value` bigint(20) unsigned NOT NULL COMMENT '键值，0表示使用key_info记录多主键',
  `key_info` varchar(2000) DEFAULT NULL COMMENT '键值信息，用于表示多主键情况或附属信息，用键值对',
  `action` int(11) NOT NULL COMMENT '动作：1-增加；2-删除；3-修改',
  `action_time` datetime NOT NULL COMMENT '变动登记时间',
  PRIMARY KEY (`db_notify_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据库变动通知表';

-- ----------------------------
-- Table structure for apply_info
-- ----------------------------
DROP TABLE IF EXISTS `apply_info`;
CREATE TABLE `apply_info` (
  `app_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '申请人ID 只有在运营商入驻时这里才是驾驶人 其他是海康管理员和运营商管理员 ',
  `app_no` varchar(32) NOT NULL COMMENT '申请编号',
  `app_time` datetime NOT NULL COMMENT '申请时间',
  `busi_type` tinyint(11) unsigned DEFAULT NULL 
  COMMENT '业务类型：1 广告申请；2 公告申请；4 线下服务购买；5车主不主动入车报警；6车主主动入车（地磁状态无车）报警；7车辆未检测到车辆离开；8车主主动入车（上一次停车行为未出车）报警；9车主申诉；10车主主动入车(时间确认)报警；11车主主动入车(车牌重复)报警；12包期取消申请;13枪球报警(追风上传数据)',
  `status` tinyint(3) unsigned NOT NULL COMMENT '状态：0 申请中；1 已经结束',
  PRIMARY KEY (`app_id`),
  KEY `idx_apply_info_app_no` (`app_no`),
  KEY `idx_apply_info_user` (`busi_type`,`user_id`,`status`),
  KEY `idx_apply_info_time` (`busi_type`,`app_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='申请信息：运营商入驻、广告提交、公告提交时产生\r\n1）整个审核过程中只有一条记录';

-- ----------------------------
-- Table structure for dealer_info
-- ----------------------------
DROP TABLE IF EXISTS `dealer_info`;
CREATE TABLE `dealer_info` (
  `dealer_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dealer_code` varchar(32) NOT NULL COMMENT '商户编号：暂定32位，编号生成算法确定后再修改',
  `dealer_name` varchar(64) NOT NULL COMMENT '商户名称（企业名称）',
  `dealer_type` tinyint(3) unsigned NOT NULL COMMENT '商户类型：1 企业； 2 其他组织',
  `status` tinyint(3) unsigned NOT NULL COMMENT '运营商状态：0 新增；1 待审核；2 审核不通过；3 上线；4 下线；5 已删除',
  `register_addr` varchar(128) NOT NULL COMMENT '企业注册地',
  `agency_code` varchar(32) NOT NULL COMMENT '营业执照或者机构代码',
  `legal_representative` varchar(32) NOT NULL COMMENT '法人代表姓名',
  `legal_id_card` varchar(18) NOT NULL COMMENT '法人身份证号',
  `manager` varchar(64) NOT NULL COMMENT '负责人',
  `phone` varchar(32) NOT NULL COMMENT '联系电话，可为手机号码、固定电话号码',
  `email` varchar(64) DEFAULT NULL COMMENT '电子邮箱地址',
  `license_photo` varchar(256) NOT NULL COMMENT '营业执照或者机构代码图片路径',
  `addr_code` varchar(6) DEFAULT NULL COMMENT '地址代码',
  `province_name` varchar(32) DEFAULT NULL COMMENT '省名称：通过ADDR_CODE计算获得，简化获取已对接城市列表sql，如果是直辖市设置为直辖市名称',
  `ezviz_app_key` varchar(256) DEFAULT NULL COMMENT '萤石账户AppKey',
  `ezviz_secret` varchar(256) DEFAULT NULL COMMENT '萤石账户Secret',
  PRIMARY KEY (`dealer_id`),
  KEY `idx_dealer_info_code` (`dealer_code`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='停车运营商信息：驾驶人提交申请信息后产生,保存时生成APPLY_INFO,提交时产生T';

-- ----------------------------
-- Table structure for dealer_apply_info
-- ----------------------------
DROP TABLE IF EXISTS `dealer_apply_info`;
CREATE TABLE `dealer_apply_info` (
  `app_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '申请人ID只有在运营商入驻时这里才是驾驶人其他是海康管理员和运营商管理员',
  `app_no` varchar(32) NOT NULL COMMENT '申请编号',
  `app_time` datetime NOT NULL COMMENT '申请时间',
  `status` tinyint(3) unsigned NOT NULL COMMENT '状态：0 申请中；1 已经结束',
  `dealer_name` varchar(64) NOT NULL COMMENT '商户名称（企业名称）',
  `dealer_code` varchar(20) NOT NULL COMMENT '商户编号',
  `dealer_type` tinyint(3) unsigned NOT NULL COMMENT '商户类型：1 企业；2 其他组织',
  `register_addr` varchar(128) NOT NULL COMMENT '企业注册地',
  `agency_code` varchar(32) NOT NULL COMMENT '营业执照或者机构代码',
  `legal_representative` varchar(32) NOT NULL COMMENT '法人代表姓名',
  `legal_id_card` varchar(18) NOT NULL COMMENT '法人身份证号',
  `manager` varchar(64) NOT NULL COMMENT '负责人',
  `phone` varchar(32) NOT NULL COMMENT '联系电话，可为手机号码、固定电话号码',
  `email` varchar(64) DEFAULT NULL COMMENT '电子邮箱地址',
  `license_photo` varchar(256) NOT NULL COMMENT '营业执照或者机构代码图片路径',
  `addr_code` varchar(6) DEFAULT NULL COMMENT '地址代码',
  `province_name` varchar(32) DEFAULT NULL COMMENT '省名称：通过ADDR_CODE计算获得，简化获取已对接城市列表sql，如果是直辖市设置为直辖市名称',
  `ezviz_app_key` varchar(256) DEFAULT NULL COMMENT '萤石账户AppKey',
  `ezviz_secret` varchar(256) DEFAULT NULL COMMENT '萤石账户Secret',
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商申请信息';

-- ----------------------------
-- Table structure for dealer_account
-- ----------------------------
DROP TABLE IF EXISTS `dealer_account`;
CREATE TABLE `dealer_account` (
  `acc_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：账户ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '开户银行名称',
  `acc_type` tinyint(3) unsigned NOT NULL COMMENT '账户类型：1 银行账户；2 支付宝账号；3 微信账户 等',
  `acc_no` varchar(32) DEFAULT NULL COMMENT '银行账户/支付宝账号/微信账户 等',
  `account_holder` varchar(32) DEFAULT NULL COMMENT '开户人姓名',
  `pay_param` text COMMENT '支付参数JSON格式',
  PRIMARY KEY (`acc_id`),
  KEY `idx_dealer_account_dealer_id` (`dealer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商账户信息';

-- ----------------------------
-- Table structure for dealer_info_pro
-- ----------------------------
DROP TABLE IF EXISTS `dealer_info_pro`;
CREATE TABLE `dealer_info_pro` (
  `pro_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：过程ID',
  `app_id` bigint(20) unsigned NOT NULL COMMENT '申请ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `dealer_name` varchar(64) NOT NULL COMMENT '商户名称（企业名称）',
  `dealer_code` varchar(32) NOT NULL COMMENT '商户编号：暂定32位，编号生成算法确定后再修改',
  `dealer_type` tinyint(3) unsigned NOT NULL COMMENT '商户类型：1 企业； 2 其他组织',
  `register_addr` varchar(128) NOT NULL COMMENT '企业注册地',
  `agency_code` varchar(32) NOT NULL COMMENT '营业执照或者机构代码',
  `legal_representative` varchar(32) NOT NULL COMMENT '法人代表姓名',
  `legal_id_card` varchar(18) NOT NULL COMMENT '法人身份证号',
  `manager` varchar(64) NOT NULL COMMENT '负责人',
  `phone` varchar(32) NOT NULL COMMENT '联系电话，可为手机号码、固定电话号码',
  `email` varchar(64) DEFAULT NULL COMMENT '电子邮箱地址',
  `license_photo` varchar(256) NOT NULL COMMENT '营业执照或者机构代码图片路径',
  `status` tinyint(3) unsigned NOT NULL COMMENT '运营商状态：0 新增；1 待审核；2 审核不通过；3 上线；4 下线；5 已删除',
  PRIMARY KEY (`pro_id`),
  KEY `idx_dealer_info_pro_dealer_id` (`dealer_id`),
  KEY `idx_dealer_info_pro_app_id` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户审核过程信息：审核不通过后添加';

-- ----------------------------
-- Table structure for dealer_approve_info
-- ----------------------------
DROP TABLE IF EXISTS `dealer_approve_info`;
CREATE TABLE `dealer_approve_info` (
  `appro_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `appr_user_id` bigint(20) unsigned NOT NULL COMMENT '审批人ID',
  `appr_time` datetime NOT NULL COMMENT '审批时间',
  `result` tinyint(3) unsigned NOT NULL COMMENT '审批结果：0 不通过；1 通过',
  `remark` varchar(256) DEFAULT NULL COMMENT '审批不通过时的描述信息',
  PRIMARY KEY (`appro_id`),
  KEY `idx_approve_info_app_id` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审核信息：审核停车运营商、广告、公告时生成\r\n1）审批不通过时必须填写审批意见\r\n2）关联表、关联主';

-- ----------------------------
-- Table structure for dealer_custom_locale
-- ----------------------------
DROP TABLE IF EXISTS `dealer_custom_locale`;
CREATE TABLE `dealer_custom_locale` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `parent_id` bigint(20) unsigned DEFAULT NULL COMMENT '父区域ID',
  `level` tinyint(4) NOT NULL COMMENT '区域等级：0、1、2、3、4、5、6',
  `name` varchar(32) DEFAULT NULL COMMENT '区域名称',
  `remarks` varchar(128) DEFAULT NULL COMMENT '备注信息',
  `addr_code` varchar(6) DEFAULT NULL COMMENT '地址代码（详见addr_info.addr_code字段）',
  `use_stream_server` tinyint(2) DEFAULT '0' COMMENT '是否使用流媒体：0-不使用，1-使用',
  `stream_server_id` bigint(20) DEFAULT NULL COMMENT '流媒体服务器ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '最后一次修改时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除：0 否 ；1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8 COMMENT='区域管理';

-- ----------------------------
-- Table structure for dealer_effective_serv
-- ----------------------------
DROP TABLE IF EXISTS `dealer_effective_serv`;
CREATE TABLE `dealer_effective_serv` (
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '运营商超级管理员ID',
  `serv_id` bigint(20) unsigned NOT NULL COMMENT '服务ID',
  `count` int(11) NOT NULL COMMENT '服务个数：\r\n            1、停车场接入，count表示当前有效服务个数\r\n            2、包期车，count大于0表示当前运营商开通了包期车服务\r\n            3、广告新闻，count 表示当前时间总共的广告条数，没有写0\r\n            4、公告信息，count 表示当前时间总共的公告条数，没有写0\r\n            5、车位预订，count大于0表示当前运营商开通了车位预订服务\r\n            6、优惠券，count大于0表示当前运营商开通了优惠券服务\r\n            7、萤石云服务，count大于0表示当前运营商开通了萤石云服务',
  PRIMARY KEY (`dealer_id`,`serv_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商当前有效的服务\r\n1、定时任务维护\r\n2、购买服务后维护\r\n3、dealer_id、';


-- ----------------------------
-- Table structure for dealer_packages_rela
-- ----------------------------
DROP TABLE IF EXISTS `dealer_packages_rela`;
CREATE TABLE `dealer_packages_rela` (
  `rela_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：关系ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '付费用户ID：商户超级管理员ID',
  `charge_id` bigint(20) unsigned NOT NULL COMMENT '费用ID',
  `serv_id` bigint(20) DEFAULT NULL COMMENT '真实服务ID：写死',
  `ext_serv_id` bigint(20) unsigned DEFAULT NULL COMMENT '套餐的服务ID：sys_serv 大于20 的ID',
  `p_id` bigint(20) unsigned NOT NULL COMMENT '系统套餐ID，如果为0表示该服务是自选服务',
  `type` tinyint(4) NOT NULL COMMENT '类型：0 数量；1 时间周期',
  `num` smallint(6) NOT NULL COMMENT '服务个数：前台选择的服务个数',
  `money` int(11) DEFAULT NULL COMMENT '应付金额：单位分',
  `count` int(11) NOT NULL COMMENT '实际数量：服务个数*倍率',
  `left_count` int(11) DEFAULT NULL COMMENT '剩余数量：初始时和实际数量相等',
  `bgn_date` date DEFAULT NULL COMMENT '有效期开始日期：购买服务的第二天开始生效',
  `end_date` date DEFAULT NULL COMMENT '有效期结束日期：开始时间+购买个数*时间单位',
  `status` tinyint(3) unsigned NOT NULL COMMENT '状态：0 未生效；1 生效；2 过期\r\n            付费成功后生效，过期或者使用次数为0 ，则为过期',
  PRIMARY KEY (`rela_id`),
  KEY `idx_dealer_packages_rela_charge_id` (`charge_id`),
  KEY `idx_dealer_packages_rela_user` (`user_id`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商套餐关系表：点击租用服务或者续费时产生';

-- ----------------------------
-- Table structure for dealer_task_info
-- ----------------------------
DROP TABLE IF EXISTS `dealer_task_info`;
CREATE TABLE `dealer_task_info` (
  `task_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `task_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '任务时间',
  `task_type` tinyint(3) unsigned NOT NULL COMMENT '任务类型：1 审核；2 申请修改 ',
  `task_title` varchar(64) DEFAULT NULL COMMENT '任务标题：用于前台代办列表显示',
  `url` varchar(128) NOT NULL COMMENT '处理实际任务的URL',
  `lock_user` bigint(20) DEFAULT NULL COMMENT '锁定人：被锁定后其他管理员就不能处理了',
  `status` tinyint(4) NOT NULL COMMENT '状态：0 未处理；1 已处理',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表：运营商入驻';

-- ----------------------------
-- Table structure for dealer_user_rela
-- ----------------------------
DROP TABLE IF EXISTS `dealer_user_rela`;
CREATE TABLE `dealer_user_rela` (
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '运营商ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '系统用户ID：即运营商管理员',
  PRIMARY KEY (`dealer_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商用户关系表：驾驶人入驻后产生一个超级管理员\n超级管理员可以添加子管理员';

-- ----------------------------
-- Table structure for device_alarm_io
-- ----------------------------
DROP TABLE IF EXISTS `device_alarm_io`;
CREATE TABLE `device_alarm_io` (
  `alarm_io_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '告警通道ID',
  `device_id` bigint(20) unsigned NOT NULL COMMENT '设备ID',
  `control_unit_id` bigint(20) unsigned DEFAULT NULL COMMENT '控制中心ID',
  `index_code` varchar(64) DEFAULT NULL COMMENT '索引编号',
  `name` varchar(256) DEFAULT NULL COMMENT '告警通道名称',
  `channel_no` int(11) DEFAULT NULL COMMENT '窗口通道号，从1开始',
  `type` int(11) DEFAULT NULL COMMENT '告警输入输出类型：0-in；1-out',
  `is_display` int(11) DEFAULT NULL COMMENT '是否显示',
  `operator_id` int(11) NOT NULL COMMENT '操作员ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `region_id` int(11) DEFAULT NULL COMMENT 'region_id',
  `cascade_index` varchar(128) DEFAULT NULL COMMENT 'NCG级联配置cascadeIndex',
  PRIMARY KEY (`alarm_io_id`),
  KEY `relationship_56_fk` (`device_id`),
  KEY `relationship_61_fk` (`control_unit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='告警IO信息';

-- ----------------------------
-- Table structure for device_audio_io
-- ----------------------------
DROP TABLE IF EXISTS `device_audio_io`;
CREATE TABLE `device_audio_io` (
  `audio_io_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '音频通道ID',
  `device_id` bigint(20) unsigned NOT NULL COMMENT '设备ID',
  `control_unit_id` bigint(20) unsigned DEFAULT NULL COMMENT '控制中心ID',
  `index_code` varchar(64) DEFAULT NULL COMMENT '索引编号',
  `name` varchar(256) DEFAULT NULL COMMENT '音频通道名称',
  `channel_no` int(11) DEFAULT NULL COMMENT '音频通道号，从1开始',
  `type` smallint(6) DEFAULT NULL COMMENT '音频输入输出类型：0-in；1-out；2-talk语音对讲',
  `operator_id` bigint(20) unsigned DEFAULT NULL COMMENT '操作员ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`audio_io_id`),
  KEY `relationship_62_fk` (`device_id`),
  KEY `relationship_63_fk` (`control_unit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='音频IO信息';

-- ----------------------------
-- Table structure for device_info
-- ----------------------------
DROP TABLE IF EXISTS `device_info`;
CREATE TABLE `device_info` (
  `device_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `power_channel_id` bigint(20) DEFAULT NULL COMMENT '电源通道ID',
  `matrix_id` bigint(20) DEFAULT NULL COMMENT '矩阵ID',
  `control_unit_id` bigint(20) unsigned DEFAULT NULL COMMENT '控制中心ID',
  `index_code` varchar(64) DEFAULT NULL COMMENT '索引编号',
  `login_pwd` varchar(512) DEFAULT NULL COMMENT '设备登录平台的密码',
  `status` smallint(6) DEFAULT '0' COMMENT '设备状态：0-正常；1-冻结；2-注销',
  `name` varchar(256) DEFAULT NULL COMMENT '设备名称',
  `type_code` int(11) DEFAULT NULL COMMENT '类型代码',
  `register_type` int(11) DEFAULT '0' COMMENT '注册类型：0-普通；1-私有域名；2-普通域名；3-流媒体；4-PAG',
  `record_mode` smallint(6) DEFAULT '0' COMMENT '录像模式：0-普通；1-网络',
  `network_addr` varchar(64) DEFAULT NULL COMMENT '设备的IP地址',
  `network_port` int(11) DEFAULT '0' COMMENT '设备的端口',
  `multicast_addr` varchar(64) DEFAULT NULL COMMENT '组播地址',
  `user_name` varchar(64) DEFAULT NULL COMMENT '设备登录的用户名',
  `user_pwd` varchar(64) DEFAULT NULL COMMENT '设备登录的用户密码',
  `dns_addr` varchar(64) DEFAULT NULL COMMENT 'DNS的IP地址',
  `dns_port` int(11) DEFAULT '0' COMMENT 'DNS的端口',
  `manufacturer` int(11) DEFAULT NULL COMMENT '设备生产厂商：0-海康 1-大华',
  `serial_no` varchar(64) DEFAULT NULL COMMENT '设备序列号：用来在使用IP-Server时查询',
  `bandwidth` int(11) DEFAULT '0' COMMENT '设备带宽，单位bps，0表示不限制',
  `camera_count` smallint(6) DEFAULT '0' COMMENT '视频通道数',
  `ip_chan_count` smallint(6) DEFAULT '0' COMMENT '数字通道数',
  `talk_count` smallint(6) DEFAULT '0' COMMENT '对讲通道数',
  `io_in_count` smallint(6) DEFAULT '0' COMMENT 'IO输入通道数',
  `io_out_count` smallint(6) DEFAULT '0' COMMENT 'IO输出通道数',
  `alarm_in_count` smallint(6) DEFAULT '0' COMMENT '告警输入通道数',
  `alarm_out_count` smallint(6) DEFAULT '0' COMMENT '告警输出通道数',
  `audio_in_count` smallint(6) DEFAULT '0' COMMENT '音频输入通道数',
  `audio_out_count` smallint(6) DEFAULT '0' COMMENT '音频输出通道数',
  `description` varchar(256) DEFAULT NULL COMMENT '描述信息',
  `sequence_idx` int(11) DEFAULT '0' COMMENT '排序索引',
  `operator_id` bigint(20) unsigned DEFAULT NULL COMMENT '操作员ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `install_position` varchar(256) DEFAULT NULL COMMENT '安装位置',
  `install_time` datetime DEFAULT NULL COMMENT '安装时间',
  `device_type` varchar(32) DEFAULT NULL COMMENT '资源类型',
  `pag_server_id` bigint(20) unsigned DEFAULT NULL COMMENT '设备接入服务器id',
  `net_zone_id` bigint(20) unsigned DEFAULT NULL COMMENT 'E家设备等外网设备的网域ID',
  `share_flag` smallint(6) DEFAULT NULL COMMENT '是否允许被下级引用 0-不引用；1-引用',
  `cascade_index` varchar(128) DEFAULT NULL COMMENT 'NCG级联配置cascadeIndex',
  `is_delete` smallint(6) DEFAULT '0' COMMENT '0 未删除  1 已删除',
  PRIMARY KEY (`device_id`),
  UNIQUE KEY `inx_device_info_code` (`index_code`),
  KEY `ak_key_2` (`index_code`),
  KEY `inx_device_info_contro_id` (`control_unit_id`),
  KEY `inx_device_info_type` (`type_code`),
  KEY `inx_device_info_name` (`name`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备信息DEVICE_INFO';

-- ----------------------------
-- Table structure for device_status
-- ----------------------------
DROP TABLE IF EXISTS `device_status`;
CREATE TABLE `device_status` (
  `session_id` varchar(64) NOT NULL COMMENT '最长64位的一个SessionID字符串，用来区分登录设备',
  `device_id` bigint(20) unsigned NOT NULL COMMENT '设备ID',
  `pag_id` bigint(20) unsigned NOT NULL COMMENT '设备接入服务器ID',
  `is_online` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否在线',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  `heartbeat_time` datetime NOT NULL COMMENT '最后心跳时间',
  `local_addr` varchar(64) DEFAULT NULL COMMENT '设备上报的本地IP地址',
  `local_port` int(11) DEFAULT '0' COMMENT '设备上报的本地端口',
  `remote_addr` varchar(64) DEFAULT NULL COMMENT '平台检测的远程IP地址',
  `remote_port` int(11) DEFAULT '0' COMMENT '平台检测的远程端口',
  `version` varchar(64) DEFAULT NULL COMMENT '版本信息',
  `camera_count` smallint(6) NOT NULL DEFAULT '0' COMMENT '视频通道数',
  `talk_count` smallint(6) NOT NULL DEFAULT '0' COMMENT '对讲通道数',
  `io_in_count` smallint(6) NOT NULL DEFAULT '0' COMMENT 'IO输入通道数',
  `io_out_count` smallint(6) NOT NULL DEFAULT '0' COMMENT 'IO输出通道数',
  `alarm_in_count` smallint(6) NOT NULL DEFAULT '0' COMMENT '告警输入通道数',
  `alarm_out_count` smallint(6) NOT NULL DEFAULT '0' COMMENT '告警输出通道数',
  `audio_in_count` smallint(6) NOT NULL DEFAULT '0' COMMENT '音频输入通道数',
  `audio_out_count` smallint(6) NOT NULL DEFAULT '0' COMMENT '音频输出通道数',
  PRIMARY KEY (`session_id`),
  KEY `relationship_72_fk` (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备在线状态信息';

-- ----------------------------
-- Table structure for device_status_current
-- ----------------------------
DROP TABLE IF EXISTS `device_status_current`;
CREATE TABLE `device_status_current` (
  `device_status_current_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '设备状态ID',
  `object_type` int(11) NOT NULL COMMENT '对象类型',
  `object_id` int(11) NOT NULL COMMENT '对象ID',
  `ip_addr` varchar(64) NOT NULL COMMENT 'IP地址',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `interval_time` smallint(6) NOT NULL DEFAULT '1' COMMENT '采集间隔，单位秒，1表示即时信息',
  `oid` varchar(256) NOT NULL COMMENT 'OID',
  `value` int(11) DEFAULT NULL COMMENT '值',
  `info` varchar(256) DEFAULT NULL COMMENT '信息',
  PRIMARY KEY (`device_status_current_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备当前工作状态';

-- ----------------------------
-- Table structure for device_status_history
-- ----------------------------
DROP TABLE IF EXISTS `device_status_history`;
CREATE TABLE `device_status_history` (
  `device_status_current_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '设备状态ID',
  `object_type` int(11) NOT NULL COMMENT '对象类型',
  `object_id` int(11) NOT NULL COMMENT '对象ID',
  `ip_addr` varchar(64) NOT NULL COMMENT 'IP地址',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `interval_time` smallint(6) NOT NULL DEFAULT '1' COMMENT '采集间隔，单位秒，1表示即时信息',
  `oid` varchar(256) NOT NULL COMMENT 'OID',
  `value` int(11) DEFAULT NULL COMMENT '值',
  `info` varchar(256) DEFAULT NULL COMMENT '信息',
  PRIMARY KEY (`device_status_current_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备历史工作状态';

-- ----------------------------
-- Table structure for device_type_code
-- ----------------------------
DROP TABLE IF EXISTS `device_type_code`;
CREATE TABLE `device_type_code` (
  `type_code` bigint(20) unsigned NOT NULL COMMENT '设备类型编码',
  `device_class` smallint(6) NOT NULL COMMENT '设备分类：1-DVR；2-DVS；3-IPC',
  `name` varchar(256) NOT NULL COMMENT '类型名称',
  `vendor` varchar(64) NOT NULL COMMENT '生产商',
  `model` varchar(128) NOT NULL COMMENT '型号',
  `manufacturer` smallint(6) DEFAULT NULL COMMENT '设备生产厂商：0-海康 1-大华  4-亚安',
  `manufacturer_code` varchar(64) DEFAULT NULL COMMENT '厂商代码',
  `device_attribute_name` varchar(64) DEFAULT NULL COMMENT '设备属性：Ehome GB28181 onvif等',
  `use_vag` smallint(6) DEFAULT NULL COMMENT '是否必须使用VAG接入：1：PAG，4：VAG',
  PRIMARY KEY (`type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备类型编码表，系统初始化生成，不能更改';

-- ----------------------------
-- Table structure for distribute_money_detail
-- ----------------------------
DROP TABLE IF EXISTS `distribute_money_detail`;
CREATE TABLE `distribute_money_detail` (
  `detail_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：账单详细信息ID',
  `record_id` bigint(20) unsigned DEFAULT NULL COMMENT '记录ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '运营商ID',
  `acc_type` tinyint(3) unsigned NOT NULL COMMENT '账户类型：1 银行账户；2 支付宝账号；3 微信账户 等',
  `acc_no` varchar(32) NOT NULL COMMENT '银行账户/支付宝账号/微信账户 等',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '开户行名称',
  `account_holder` varchar(32) DEFAULT NULL COMMENT '账户持有人名称',
  `bill_no` varchar(64) DEFAULT NULL COMMENT '内部转账账单编号',
  `third_bill_no` varchar(64) DEFAULT NULL COMMENT '第三方交易账单编号',
  `pay_money` int(11) NOT NULL COMMENT '付款金额',
  `pay_time` datetime DEFAULT NULL COMMENT '转账时间',
  `pay_rslt` tinyint(3) unsigned DEFAULT NULL COMMENT '转账结果：0 不成功；1 成功',
  PRIMARY KEY (`detail_id`),
  KEY `idx_distribute_money_detail_record_id` (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='清分转账记录表';

-- ----------------------------
-- Table structure for distribute_money_record
-- ----------------------------
DROP TABLE IF EXISTS `distribute_money_record`;
CREATE TABLE `distribute_money_record` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：记录ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `state_time` datetime NOT NULL COMMENT '账单统计开始时间',
  `end_time` datetime NOT NULL COMMENT '账单统计结束时间',
  `statement_status` tinyint(3) unsigned NOT NULL COMMENT '账单明细生成状态，0-账单初始化中，1-账单明细已生成，2-账单明细生成失败',
  `description` varchar(256) DEFAULT NULL COMMENT '账单状态描述',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='待清分记录表';

-- ----------------------------
-- Table structure for driver
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver` (
  `driver_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：驾驶人ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '系统用户ID',
  `login_type` tinyint(4) DEFAULT NULL COMMENT '登录类型:1-驾驶人App的登录2-商户App的登录',
  `device_type` tinyint(4) DEFAULT NULL COMMENT '设备类型;1-ios，2-android',
  `device_id` varchar(64) DEFAULT NULL COMMENT '设备唯一ID:ios-UDID，android-IMEI',
  `device_name` varchar(32) DEFAULT NULL COMMENT '设备名称:手机设备的名称',
  `avatar_url` varchar(256) DEFAULT NULL COMMENT '头像下载地址',
  `email` varchar(64) DEFAULT NULL COMMENT '电子邮箱',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `is_rename_allowed` tinyint(4) DEFAULT NULL COMMENT '是否允许更改用户名0-否，1-是',
  `gender` tinyint(4) DEFAULT '0' COMMENT '性别：0 未设置；1 男；2 女',
  `city_name` varchar(32) DEFAULT NULL COMMENT '城市名称',
  `plate_id` bigint(20) unsigned DEFAULT NULL COMMENT '默认车牌ID',
  `amount` int(11) DEFAULT '0' COMMENT '账户余额：单位分',
  `app_user_type` varchar(256) NOT NULL DEFAULT 'cloud' COMMENT '用户注册app包名称：对应系统参数包名对应的值',
  PRIMARY KEY (`driver_id`),
  UNIQUE KEY `idx_driver_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='驾驶人：门户网站、手机APP注册时产生、商户APP登录时也会产生';

-- ----------------------------
-- Table structure for driver_coupon
-- ----------------------------
DROP TABLE IF EXISTS `driver_coupon`;
CREATE TABLE `driver_coupon` (
  `coupon_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `coupon_no` varchar(32) NOT NULL COMMENT '优惠券编号，全局唯一',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '驾驶人用户ID',
  `money` smallint(6) NOT NULL COMMENT '优惠券金额：单位分',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `use_time` datetime DEFAULT NULL COMMENT '使用时间',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '状态：0未使用；1已使用;2锁定 ',
  `issue_id` bigint(20) unsigned NOT NULL COMMENT '发放记录ID',
  `coupon_type` tinyint(3) DEFAULT '0' COMMENT '规则类型：0 减免金额；1 打折；2 全面j减免；3 按时长',
  `free_time` smallint(6) DEFAULT NULL COMMENT '减免时长：单位 分钟',
  `free_rate` tinyint(4) DEFAULT NULL COMMENT '折扣率：单位%',
  `dealer_id` bigint(20) unsigned DEFAULT NULL COMMENT '商户Id',
  `bgn_date` datetime NOT NULL COMMENT '开始时间：优惠券有效期的开始时间',
  `end_date` datetime NOT NULL COMMENT '结束时间：优惠券有效期的结束时间',
  PRIMARY KEY (`coupon_id`),
  KEY `idx_driver_coupon_user` (`user_id`,`status`),
  KEY `idx_driver_coupon_issue` (`issue_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='驾驶人优惠券：有优惠活动时添加\r\n1、象山上线是前20000个APP注册用户充值20块送10块优惠券，一张优惠';

-- ----------------------------
-- Table structure for driver_plate_rela
-- ----------------------------
DROP TABLE IF EXISTS `driver_plate_rela`;
CREATE TABLE `driver_plate_rela` (
  `plate_id` bigint(20) unsigned NOT NULL COMMENT '车牌ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '系统用户ID',
  `cut_payment` tinyint(4) NOT NULL DEFAULT '0' COMMENT '扣款标识：0否；1是，第一次添加时是1，否则为0',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `card_id` bigint(20) unsigned DEFAULT NULL COMMENT '绑定银行卡编号',
  PRIMARY KEY (`plate_id`,`user_id`),
  KEY `idx_driver_plate_rela_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='驾驶人车牌关系表';

-- ----------------------------
-- Table structure for driver_transaction_record
-- ----------------------------
DROP TABLE IF EXISTS `driver_transaction_record`;
CREATE TABLE `driver_transaction_record` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `driver_id` bigint(20) unsigned NOT NULL COMMENT '驾驶人ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `money` int(11) NOT NULL COMMENT '金额，单位分',
  `type` tinyint(4) NOT NULL COMMENT '交易类型：1-手机充值，2-支出, 3-余额退款,4-提现 ,5-平台充值,6-提现失败退回,7-余额包期退回,8-余额预订退回,9-余额购买优惠券退回',
  `bill_no` varchar(64) DEFAULT NULL COMMENT '应收账单编号',
  `rslt` tinyint(4) NOT NULL COMMENT '交易结果：0 未完成，1已完成',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`record_id`),
  KEY `idx_driver_transaction_record_did` (`driver_id`),
  KEY `idx_driver_transaction_record_billno` (`bill_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交易记录';

-- ----------------------------
-- Table structure for elec_invoice
-- ----------------------------
DROP TABLE IF EXISTS `elec_invoice`;
CREATE TABLE `elec_invoice` (
  `invoice_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `invoice_title_type` tinyint(3) unsigned NOT NULL COMMENT '发票抬头类型：1企业抬头；2个人/非企业单位抬头',
  `invoice_title` varchar(64) DEFAULT NULL COMMENT '发票抬头',
  `invoice_type` tinyint(3) DEFAULT NULL COMMENT '发票类型：0其他；1停车费',
  `money` int(10) unsigned NOT NULL COMMENT '申请金额：单位分',
  `consignee` varchar(32) DEFAULT NULL COMMENT '收件人',
  `consignee_addr` varchar(128) DEFAULT NULL COMMENT '收件人地址',
  `city_code` varchar(6) DEFAULT NULL COMMENT '收件人城市代码：具体参考addr_info表',
  `phone` varchar(32) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(32) DEFAULT NULL COMMENT '电子邮件',
  `status` tinyint(3) unsigned NOT NULL COMMENT '状态：0开票失败；1申请中；2已完成；3再次申请',
  `complete_time` datetime DEFAULT NULL COMMENT '完成时间',
  `img_url` varchar(256) DEFAULT NULL COMMENT '电子发票图片URL（预留）',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注信息',
  `taxpayer_number` varchar(64) DEFAULT NULL COMMENT '纳税人识别号',
  PRIMARY KEY (`invoice_id`),
  KEY `idx_elec_invoice_user` (`user_id`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='电子发票申请记录：APP用户申请时添加，用户只能申请自己支付的账单的电子发票';

-- ----------------------------
-- Table structure for elec_invoice_again
-- ----------------------------
DROP TABLE IF EXISTS `elec_invoice_again`;
CREATE TABLE `elec_invoice_again` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `invoice_id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(4) NOT NULL COMMENT '状态：0拒绝处理，1未处理；已处理',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`record_id`),
  KEY `idx_elec_invoice_again_invoiceId` (`invoice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发送成功后再次申请发送';

-- ----------------------------
-- Table structure for ezviz_equip
-- ----------------------------
DROP TABLE IF EXISTS `ezviz_equip`;
CREATE TABLE `ezviz_equip` (
  `equip_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：设备ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) unsigned DEFAULT NULL COMMENT '停车场ID',
  `monitor_area` varchar(64) DEFAULT NULL COMMENT '监控区域',
  `device_serial` varchar(64) NOT NULL COMMENT '设备序列号',
  `device_name` varchar(256) DEFAULT NULL COMMENT '设备名称',
  `validate_code` varchar(64) DEFAULT NULL COMMENT '验证码：大写的6位字母',
  `device_version` varchar(256) DEFAULT NULL COMMENT '设备版本号',
  `device_type` varchar(64) DEFAULT NULL COMMENT '设备类型',
  `status` tinyint(4) DEFAULT NULL COMMENT '在线状态：0 不在线，1 在线',
  `defence` tinyint(4) DEFAULT NULL COMMENT '是否加密：0 不加密，1 加密',
  `is_encrypt` tinyint(4) DEFAULT NULL COMMENT 'A1设备 1-加密布撤防状态：0-睡眠，8-在家，16-外出，非A1设备布撤防状态：0-撤防，1-布防',
  `channel_no` varchar(64) DEFAULT NULL COMMENT '通道号',
  `channel_name` varchar(256) DEFAULT NULL COMMENT '通道名称',
  PRIMARY KEY (`equip_id`),
  KEY `idx_ezviz_equip_dealer` (`dealer_id`,`park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='萤石设备';

-- ----------------------------
-- Table structure for ezviz_equip_channel
-- ----------------------------
DROP TABLE IF EXISTS `ezviz_equip_channel`;
CREATE TABLE `ezviz_equip_channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_serial` varchar(64) DEFAULT NULL,
  `channel_no` varchar(64) DEFAULT NULL,
  `channel_name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=257 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for free_duration_config
-- ----------------------------
DROP TABLE IF EXISTS `free_duration_config`;
CREATE TABLE `free_duration_config` (
  `config_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '配置ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `duration` int(11) NOT NULL COMMENT '时长分布：单位分钟，值不能重复',
  `create_person` varchar(32) DEFAULT NULL COMMENT '创建人员',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_person` varchar(32) DEFAULT NULL COMMENT '更新人员',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`config_id`),
  UNIQUE KEY `idx_free_duration_config_dealer` (`dealer_id`,`duration`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='免费时长分布配置表';



-- ----------------------------
-- Table structure for inf_security
-- ----------------------------
DROP TABLE IF EXISTS `inf_security`;
CREATE TABLE `inf_security` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ip` varchar(32) NOT NULL COMMENT '服务器IP地址',
  `port` int(11) NOT NULL COMMENT '端口',
  `server_code` varchar(32) NOT NULL COMMENT '服务器代码',
  `server_type` tinyint(3) unsigned NOT NULL COMMENT '服务器类型：1 管控平台，2 MSP，3 门户网站，4 DAGS，5 CMS',
  `alias` varchar(32) DEFAULT NULL COMMENT '别名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='接口安全：平台各服务器交互时使用';

-- ----------------------------
-- Table structure for inspector_park
-- ----------------------------
DROP TABLE IF EXISTS `inspector_park`;
CREATE TABLE `inspector_park` (
  `park_id` bigint(20) DEFAULT NULL COMMENT '停车场id',
  `collector_id` bigint(20) DEFAULT NULL COMMENT '巡检员id',
  KEY `park_info` (`park_id`),
  KEY `toll_collector_info` (`collector_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='巡检员,停车场中间关联表';

-- ----------------------------
-- Table structure for inspector_sign_record
-- ----------------------------
DROP TABLE IF EXISTS `inspector_sign_record`;
CREATE TABLE `inspector_sign_record` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '运营商ID',
  `docking_id` bigint(20) DEFAULT NULL COMMENT '对接网关ID',
  `inspectors_code` varchar(64) NOT NULL COMMENT '巡检员编号',
  `sign_type` tinyint(4) DEFAULT NULL COMMENT '操作类型：1签到；2签退',
  `sign_date` datetime DEFAULT NULL COMMENT '操作时间',
  `img_url` varchar(256) DEFAULT NULL COMMENT '巡检员签到图片URL',
  PRIMARY KEY (`record_id`),
  KEY `idx_inspector_sr_code` (`inspectors_code`,`sign_type`,`sign_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='巡检员签到签退记录表';

-- ----------------------------
-- Table structure for license_info
-- ----------------------------
DROP TABLE IF EXISTS `license_info`;
CREATE TABLE `license_info` (
  `guid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `license_no` varchar(32) NOT NULL COMMENT 'license编号',
  `license_version` varchar(32) NOT NULL COMMENT 'license版本号',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '数据创建人',
  `create_user_ip` varchar(32) DEFAULT NULL,
  `release_time` datetime NOT NULL COMMENT 'license发布时间',
  `expire_date` datetime NOT NULL COMMENT 'license过期时间',
  `license_path` varchar(256) NOT NULL COMMENT 'license存储路径',
  `license_xml` text NOT NULL COMMENT 'license内容',
  `license_file` blob COMMENT 'license文件',
  `amount_limit` varchar(256) NOT NULL,
  `type` tinyint(4) NOT NULL COMMENT '类型，1 初始导入license 2更换license',
  `sn` varchar(64) NOT NULL,
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=278 DEFAULT CHARSET=utf8 COMMENT='license信息表';

-- ----------------------------
-- Table structure for notice_info
-- ----------------------------
DROP TABLE IF EXISTS `notice_info`;
CREATE TABLE `notice_info` (
  `notice_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) DEFAULT NULL COMMENT '主键',
  `notice_type` tinyint(4) NOT NULL COMMENT '须知类型：1-车位预订须知，2-包期车须知，3-优惠券须知',
  `notice_content` text COMMENT '须知内容',
  `version` int(11) unsigned DEFAULT NULL COMMENT '须知版本号',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否已经删除：0 否； 1 是',
  PRIMARY KEY (`notice_id`),
  KEY `idx_notice_info_dealer` (`dealer_id`,`park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='须知表：包含：预订须知、包期须知、优惠券须知';

-- ----------------------------
-- Table structure for notify_message
-- ----------------------------
DROP TABLE IF EXISTS `notify_message`;
CREATE TABLE `notify_message` (
  `message_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(100) NOT NULL COMMENT '信息标题',
  `message_info` varchar(255) NOT NULL COMMENT '消息内容',
  `type` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '消息类型 0平台管理员消息 1商户消息',
  `dealer_id` bigint(20) DEFAULT NULL COMMENT '商户id',
  `url` varchar(255) DEFAULT NULL COMMENT '要跳转的url',
  `open_mode` tinyint(4) unsigned DEFAULT '0' COMMENT '链接打开方式 0菜单跳转 1新窗口打开',
  `is_readed` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '是否已读 0未读 1已读',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35449 DEFAULT CHARSET=utf8 COMMENT='通知消息表';

-- ----------------------------
-- Table structure for open_api_config
-- ----------------------------
DROP TABLE IF EXISTS `open_api_config`;
CREATE TABLE `open_api_config` (
  `openapi_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `app_key` varchar(64) NOT NULL COMMENT 'key',
  `secret` text NOT NULL COMMENT '密钥串',
  `create_person` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_person` varchar(32) NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`openapi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for open_api_park_rela
-- ----------------------------
DROP TABLE IF EXISTS `open_api_park_rela`;
CREATE TABLE `open_api_park_rela` (
  `guid` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'openapi停车点关联关系主键',
  `openapi_id` bigint(32) NOT NULL COMMENT 'openAPI编号',
  `park_id` bigint(32) NOT NULL COMMENT '停车点编号',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for package_charge
-- ----------------------------
DROP TABLE IF EXISTS `package_charge`;
CREATE TABLE `package_charge` (
  `charge_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID：费用ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '运营商ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '付费用户ID：商户超级管理员ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `bill_no` varchar(64) NOT NULL COMMENT '账单编号',
  `money` int(10) unsigned NOT NULL COMMENT '金额：单位分',
  PRIMARY KEY (`charge_id`),
  UNIQUE KEY `idx_package_charge_bill_no` (`bill_no`),
  KEY `idx_package_charge_dealer` (`dealer_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='套餐费用（服务租用费用）：点击租用服务或者续费时产生';

-- ----------------------------
-- Table structure for park_apply_info
-- ----------------------------
DROP TABLE IF EXISTS `park_apply_info`;
CREATE TABLE `park_apply_info` (
  `app_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '申请ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车点ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '驾驶人ID',
  `unid` varchar(64) NOT NULL COMMENT '停车唯一标识',
  `berth_code` varchar(64) DEFAULT NULL COMMENT '泊位编号：全局唯一',
  `berth_number` varchar(64) DEFAULT NULL COMMENT '泊位编号：停车场内唯一',
  `plate_no` varchar(10) NOT NULL COMMENT '车牌号码',
  `plate_color` tinyint(4) NOT NULL COMMENT '车牌颜色，-1没有车牌颜色 0-其他,1-蓝色,2-黄色,3-黑色,4-白色',
  `app_time` datetime NOT NULL COMMENT '申请时间',
  `create_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '创建来源：1驾驶人主动入车产生；2中心管理员处理报警信息产生；3缴费时产生；4手持机入车时产生',
  `alarm_id` bigint(20) unsigned DEFAULT NULL COMMENT '报警信息ID',
  PRIMARY KEY (`app_id`),
  KEY `idx_park_apply_info_user` (`user_id`),
  KEY `idx_park_apply_info_berth` (`berth_number`),
  KEY `idx_park_apply_info_dealer` (`dealer_id`,`unid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='MSP调用DAGS，DAGS调用路边系统\r\n场库系统没有这个表的记录';

-- ----------------------------
-- Table structure for park_charge
-- ----------------------------
DROP TABLE IF EXISTS `park_charge`;
CREATE TABLE `park_charge` (
  `charge_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `type` tinyint(4) NOT NULL COMMENT '账单类型：1自主缴费；2欠费（后付费）；3系统自动扣款 ;4POS缴费；5POS后付费',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车点ID',
  `record_id` bigint(20) unsigned NOT NULL COMMENT '停车记录ID',
  `money` int(11) DEFAULT NULL COMMENT '金额：单位分',
  `settle_time` datetime DEFAULT NULL COMMENT '结算时间：主动缴费就是下单时间，欠费补缴就是路边车辆离开的时间',
  `bill_no` varchar(64) DEFAULT NULL COMMENT '账单编号：应收对应的账单编号',
  `other_bill_no` varchar(64) DEFAULT NULL COMMENT '第三方子系统账单编号',
  `status` tinyint(4) NOT NULL COMMENT '状态：0未支付 ；1已支付；2已退款；88；泊链代扣请求超时失败订单；99作废',
  `notice_times` tinyint(4) DEFAULT '0' COMMENT '通知次数',
  `notice_status` tinyint(4) DEFAULT '0' COMMENT '通知成功标识，0 未成功；1成功',
  `coupon_no` varchar(32) DEFAULT NULL COMMENT '优惠券编号，全局唯一',
  `deduct_money` int(11) DEFAULT NULL COMMENT '抵扣金额(单位分)',
  `pos_termin_code` varchar(64) DEFAULT NULL COMMENT 'POS终端编号：pos二维码支付时添加',
  `collector_code` varchar(64) DEFAULT NULL COMMENT '收费员编号：pos获取二维码时添加',
  PRIMARY KEY (`charge_id`),
  KEY `idx_park_charge_recordid` (`record_id`,`status`),
  KEY `idx_park_charge_billno` (`bill_no`),
  KEY `idx_park_charge_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车账单：云平台自主缴费、欠费账单上传时生成\r\n金额为0时不用生成';

-- ----------------------------
-- Table structure for park_docking_info
-- ----------------------------
DROP TABLE IF EXISTS `park_docking_info`;
CREATE TABLE `park_docking_info` (
  `docking_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：对接ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '运营商ID',
  `platform_name` varchar(64) DEFAULT NULL COMMENT '对接终端/平台名称',
  `platform_type` tinyint(3) unsigned NOT NULL COMMENT '对接平台类型，1-TPE-104、2-6710、3-海燕、4-路边停车子系统',
  `termin_code` varchar(32) DEFAULT NULL COMMENT '对接终端/平台编号',
  `password` varchar(128) DEFAULT NULL COMMENT '登录密码',
  `token` varchar(512) DEFAULT NULL COMMENT 'token值',
  `end_time` datetime DEFAULT NULL COMMENT 'token有效截止时间',
  `docking_key` varchar(16) DEFAULT NULL COMMENT '安全秘钥',
  `last_heart_beat_time` datetime DEFAULT NULL COMMENT '最后心跳时间',
  `platform_ip` varchar(32) DEFAULT NULL COMMENT '对接终端/平台注册上来的ip',
  `platform_port` int(11) DEFAULT NULL COMMENT '对接终端/平台注册上来的端口',
  `net_zone_type` tinyint(3) unsigned DEFAULT NULL COMMENT '请求接入的网域类型，0-公网，1-专网',
  `version` varchar(16) DEFAULT NULL COMMENT '终端接入的通讯协议版本号',
  `dynamic_upload` int(11) DEFAULT NULL COMMENT '动态信息定时上传时间间隔：单位秒',
  `manage_locale_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '场库系统管辖的区域ID',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已经删除：0 否； 1 是',
  PRIMARY KEY (`docking_id`),
  KEY `idx_park_docking_info_platform_code` (`termin_code`),
  KEY `idx_park_docking_info_dealer` (`dealer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='场库对接信息：云平台和6710（海燕）、AGV、城市停车平台、第三方场库系统的对接信息';

-- ----------------------------
-- Table structure for park_info
-- ----------------------------
DROP TABLE IF EXISTS `park_info`;
CREATE TABLE `park_info` (
  `park_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '运营商ID',
  `docking_id` bigint(20) unsigned DEFAULT NULL COMMENT '对接网关ID',
  `park_code` varchar(64) NOT NULL COMMENT '停车场编号',
  `park_name` varchar(32) DEFAULT NULL COMMENT '停车场名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `total_parking_space_num` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '总车位数',
  `left_parking_space_num` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '剩余车位数',
  `longitude` decimal(9,6) DEFAULT NULL COMMENT '停车场经度',
  `latitude` decimal(9,6) DEFAULT NULL COMMENT '停车场纬度',
  `park_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '停车场类型：0-路边停车场，1-路边简易停车场，2-场内停车场  3-AGV场库',
  `parking_level` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '停车场等级，0-其它，1-一级停车场，2-二级停车场，3-三级停车场',
  `description` varchar(512) DEFAULT NULL COMMENT '描述信息',
  `parking_alias` varchar(32) DEFAULT NULL COMMENT '停车场别名',
  `parking_address` varchar(256) DEFAULT NULL COMMENT '停车场地址',
  `operation_state` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '运营状态，0-正常营业，1-暂停营业',
  `book_support` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否支持 车位预订，0-不支持，1-支持并可预定到指定车位，2-支持但不能预定到指定车位',
  `bagcar_support` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否可包期，0 否， 1 是',
  `deposit_support` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否支持将停车预订订金作为停车费的一部分0 否， 1 是',
  `total_book_parking_space_num` smallint(5) unsigned DEFAULT '0' COMMENT '可预订总车位数',
  `left_book_parking_space_num` smallint(5) unsigned DEFAULT '0' COMMENT '可预订剩余车位数',
  `coupon_support` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否支持优惠券：0 不支持；1 支持',
  `left_bag_car_num` smallint(6) DEFAULT '0' COMMENT '剩余可申请包期车的数量',
  `pay_rule_desc` varchar(512) DEFAULT NULL COMMENT '收费规则的简短描述',
  `locale_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '停车场所属区域',
  `parking_start_time` varchar(64) DEFAULT NULL COMMENT '停车场开始营业时间，格式 HH:mm:ss 07:00:00',
  `parking_end_time` varchar(64) DEFAULT NULL COMMENT '停车场结束营业时间，格式 HH:mm:ss 03:07:50',
  `parking_lot_type` tinyint(4) DEFAULT NULL COMMENT '停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场',
  `parking_type` tinyint(4) DEFAULT NULL COMMENT '停车场类型(1为地面，2为地下，3为路边)多个类型，中间用,隔开',
  `payment_mode` tinyint(4) DEFAULT NULL COMMENT '缴费模式（1为停车卡缴费，2为物料缴费，3为中央缴费机）',
  `pay_type` varchar(32) DEFAULT NULL COMMENT '支付方式（1为支付宝在线缴费，2为支付宝代扣缴费，3当面付)，如支持多种方式以'''',''''进行间隔',
  `alipay_park_id` varchar(64) DEFAULT NULL COMMENT '支付宝停车场ID ，系统唯一(新增支付宝推送的时候返回)',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已经删除：0 否； 1 是',
  `free_time` int(11) DEFAULT '20' COMMENT '免费停车时长：单位分钟',
  `is_free` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否是免费固定的停车场：0否 ；1是',
  `parking_poiid` varchar(32) DEFAULT '0' COMMENT '高德地图唯一标识',
  `access_plan` tinyint(4) DEFAULT NULL COMMENT '路边接入方案：0POS收费 ，1混合收费，2无人值守',
  `etcp_type` tinyint(4) DEFAULT '0' COMMENT '代扣方式：0-不支持，1-支付宝，2-泊链，3-银联（一咻）',
  `support_type` tinyint(4) DEFAULT '0' COMMENT '第三方接入方式：0-不支持，1-支付宝车主服务接入，2-泊链支付，3-银联支付',
  `is_related_pay_rule` tinyint(2) DEFAULT '0' COMMENT '是否关联收费规则，0-否，1-是',
  PRIMARY KEY (`park_id`),
  KEY `idx_park_info_dealer` (`dealer_id`,`longitude`,`latitude`),
  KEY `idx_park_info_park_code` (`park_code`),
  KEY `idx_park_info_lon` (`longitude`,`latitude`),
  KEY `idx_park_info_did_pid` (`is_delete`,`dealer_id`,`park_id`)
) ENGINE=InnoDB AUTO_INCREMENT=770 DEFAULT CHARSET=utf8 COMMENT='停车场申请信息：驾驶人提交申请信息后产生';

-- ----------------------------
-- Table structure for park_info_chg
-- ----------------------------
DROP TABLE IF EXISTS `park_info_chg`;
CREATE TABLE `park_info_chg` (
  `chg_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '运营商ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `total_parking_space_num` smallint(5) unsigned NOT NULL COMMENT '变更前总车位数',
  `chg_time` datetime NOT NULL COMMENT '变动时间',
  PRIMARY KEY (`chg_id`),
  KEY `idx_park_info_chg_dealer` (`dealer_id`,`park_id`,`chg_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车场变更信息表：记录变更后的数据，目前只有总车位数';

-- ----------------------------
-- Table structure for park_picture
-- ----------------------------
DROP TABLE IF EXISTS `park_picture`;
CREATE TABLE `park_picture` (
  `picture_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：照片ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `url` varchar(256) NOT NULL COMMENT '照片URL',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`picture_id`),
  KEY `idx_park_picture_dealer` (`dealer_id`,`park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车场平面图：停车场出入口、外部道路情况、内部车位情况等照片信息';

-- ----------------------------
-- Table structure for gate_info
-- ----------------------------
DROP TABLE IF EXISTS `gate_info`;
CREATE TABLE `gate_info` (
  `gate_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `gate_name` varchar(32) NOT NULL COMMENT '出入口名称',
  `gate_code` varchar(32) DEFAULT NULL COMMENT '出入口编号',
  `longitude` decimal(9,6) DEFAULT NULL COMMENT '出入口经度',
  `latitude` decimal(9,6) DEFAULT NULL COMMENT '出入口纬度',
  `lane_num` tinyint(4) DEFAULT NULL COMMENT '车道数量',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已经删除：0 否； 1 是',
  PRIMARY KEY (`gate_id`),
  KEY `idx_gate_info_dealer` (`dealer_id`,`park_id`),
  KEY `idx_gate_info_gate_code` (`gate_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出入口';

-- ----------------------------
-- Table structure for lane_info
-- ----------------------------
DROP TABLE IF EXISTS `lane_info`;
CREATE TABLE `lane_info` (
  `lane_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `lane_no` int(11) NOT NULL COMMENT '车道序号：同一对接终端下唯一',
  `lane_name` varchar(32) DEFAULT NULL COMMENT '车道名称',
  `direction` tinyint(4) NOT NULL COMMENT '车道方向：0-入场车道，1-出场车道，2-出入车道',
  `gate_code` varchar(32) DEFAULT NULL COMMENT '出入口编号',
  `park_code` varchar(32) DEFAULT NULL COMMENT '停车场编号',
  `gate_id` bigint(20) unsigned NOT NULL COMMENT '出入口ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `docking_id` bigint(20) unsigned NOT NULL COMMENT '所属场库系统ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '所属商户ID',
  `is_delete` tinyint(4) NOT NULL COMMENT '删除标志位：0 未删除；1已经删除',
  PRIMARY KEY (`lane_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车道信息：DAGS使用，场库系统上传';

-- ----------------------------
-- Table structure for parking_lock
-- ----------------------------
DROP TABLE IF EXISTS `parking_lock`;
CREATE TABLE `parking_lock` (
  `lock_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '车位锁拥有人',
  `lock_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '设备序列号(设备码)',
  `lock_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '取设备码的后7位，仅供ios端使用，该字段不可更改',
  `lock_alias` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '车位锁别名',
  `lock_type` bigint(3) NOT NULL COMMENT '车位锁类型;1、蓝牙车位锁',
  `lock_addr` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '车位地址',
  `lock_key` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '车位锁秘钥,第三方返回的code',
  `lock_mac` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '车位锁mac地址',
  `share_man` bigint(20) DEFAULT NULL COMMENT '共享人数',
  `count` bigint(20) DEFAULT NULL COMMENT '剩余可共享人数',
  `lock_state` bigint(3) NOT NULL COMMENT '车位锁状态；1、已绑定锁主 2、未绑定锁主',
  `bgn_date` datetime DEFAULT NULL COMMENT '共享有效开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '共享有效结束时间',
  `is_share` bigint(20) NOT NULL COMMENT '是否开启共享 1 是2否',
  `create_date` datetime NOT NULL COMMENT '锁添加时间',
  `is_delete` bigint(3) NOT NULL COMMENT '是否删除：0 否；1 是',
  PRIMARY KEY (`lock_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车位锁信息表';

-- ----------------------------
-- Table structure for pass_vehicle_info
-- ----------------------------
DROP TABLE IF EXISTS `pass_vehicle_info`;
CREATE TABLE `pass_vehicle_info` (
  `pass_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：过车记录ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车点ID',
  `termin_code` varchar(32) DEFAULT NULL COMMENT '对接终端/平台编号',
  `unid` varchar(64) NOT NULL COMMENT '过车记录唯一ID，由终端产生UUID，路边停车场入车出车ID一致',
  `berth_code` varchar(64) DEFAULT NULL COMMENT '泊位编号：全局唯一',
  `berth_number` varchar(64) DEFAULT NULL COMMENT '泊位编号：停车场内唯一',
  `gate_id` bigint(20) unsigned DEFAULT NULL COMMENT '出入口ID',
  `gate_code` varchar(64) DEFAULT NULL COMMENT '出入口编号',
  `direct` tinyint(3) unsigned NOT NULL COMMENT '出入方向，0-入场，1-出场',
  `pass_time` datetime NOT NULL COMMENT '过车时间',
  `plate_no` varchar(10) NOT NULL COMMENT '车牌号码',
  `plate_color` tinyint(4) NOT NULL COMMENT '车牌颜色，-1没有车牌颜色 0-其他,1-蓝色,2-黄色,3-黑色,4-白色',
  `plate_type` tinyint(3) unsigned DEFAULT NULL COMMENT '车牌类别:0-无类型,1-92式民用车,2-警用车,3-上下军车,4-92式武警车,5-左右军车车牌类型(一行结构),7-02式个性化车,8-黄色双行尾牌,9-04式新军车,10-使馆车,11-一行结构的新WJ车,12-两行结构的新WJ车,13-黄色1225农用车,14-绿色1325农用车,15-黄色1325农用车,16-摩托车, 17-13式新武警总部一行车牌，18-13式新武警总部两行车牌，19-民航车牌类型,100-教练车,101-临时行驶车,102-挂车,103-领馆汽车,104-港澳入出车,105-临时入境车',
  `car_type` tinyint(3) unsigned DEFAULT NULL COMMENT '车辆类型，1-小型汽车,2-大型汽车,0-其他',
  `card_no` varchar(16) DEFAULT NULL COMMENT '卡号',
  `park_type` tinyint(3) unsigned DEFAULT NULL COMMENT '停车类型，0-固定车，1-临时车',
  `img_unid` varchar(64) DEFAULT NULL COMMENT '图片唯一ID，暂时是POS上传图片使用',
  `img_url` varchar(256) DEFAULT NULL COMMENT '过程图片URL：阿里云OSS的restful 地址\r\n已废弃',
  `in_unid` varchar(64) DEFAULT NULL COMMENT '入车UNID',
  `in_time` datetime DEFAULT NULL COMMENT '入车时间',
  `out_time` datetime DEFAULT NULL COMMENT '出车时间：如果有收费信息则为缴费时间',
  `add_berth_code` varchar(64) DEFAULT NULL COMMENT '附加泊位信息',
  `abnormal_flag` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否异常放行：0正常；1异常放行',
  PRIMARY KEY (`pass_id`,`pass_time`),
  KEY `idx_pass_vehicle_info_dealer` (`dealer_id`,`direct`,`pass_time`,`park_id`,`pass_id`),
  KEY `idx_pass_vehicle_info_unid` (`dealer_id`,`unid`),
  KEY `idx_pass_vehicle_info_plate` (`plate_no`,`plate_color`,`direct`,`pass_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='过车数据信息表'


-- ----------------------------
-- Table structure for pay_param_config
-- ----------------------------
DROP TABLE IF EXISTS `pay_param_config`;
CREATE TABLE `pay_param_config` (
  `guid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：记录ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `param_name` varchar(64) NOT NULL COMMENT '参数名称',
  `param_value` text NOT NULL COMMENT '参数值：加密后字符串',
  `param_type` tinyint(4) DEFAULT NULL COMMENT '类型',
  `create_person` varchar(32) DEFAULT NULL COMMENT '创建人：账户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_person` varchar(32) DEFAULT NULL COMMENT '更新人：账户',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_encrypt` tinyint(4) NOT NULL COMMENT '是否加密：0 否； 1 是',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户支付参数配置';

-- ----------------------------
-- Table structure for plate_black_list
-- ----------------------------
DROP TABLE IF EXISTS `plate_black_list`;
CREATE TABLE `plate_black_list` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `plate_no` varchar(10) NOT NULL COMMENT '车牌编号',
  `plate_color` tinyint(3) unsigned NOT NULL COMMENT '车牌颜色：0-其他,1-蓝色,2-黄色,3-黑色,4-白色',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `is_delete` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0否；1是',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  KEY `idx_plate_black_list_plate` (`dealer_id`,`plate_no`,`is_delete`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车牌黑名单';

-- ----------------------------
-- Table structure for plate_info
-- ----------------------------
DROP TABLE IF EXISTS `plate_info`;
CREATE TABLE `plate_info` (
  `plate_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `plate_no` varchar(10) NOT NULL COMMENT '车牌编号',
  `plate_color` tinyint(3) unsigned NOT NULL COMMENT '车牌颜色：0-其他,1-蓝色,2-黄色,3-黑色,4-白色',
  `is_bolink` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否是泊链会员：0否；1是',
  PRIMARY KEY (`plate_id`),
  UNIQUE KEY `idx_plate_info` (`plate_no`,`plate_color`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车牌信息：驾驶人绑定的车牌信息';

-- ----------------------------
-- Table structure for preset_info
-- ----------------------------
DROP TABLE IF EXISTS `preset_info`;
CREATE TABLE `preset_info` (
  `preset_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '预置点ID号',
  `camera_id` bigint(20) NOT NULL COMMENT '通道ID',
  `type` int(11) NOT NULL COMMENT '0代表预制点；1代表巡航；2代表轨迹',
  `preset_no` int(11) NOT NULL COMMENT '预置点序号；type=1时，代表巡航点序号',
  `name` varchar(256) CHARACTER SET latin1 DEFAULT NULL COMMENT '预制点名称',
  `is_enable` int(11) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `operator_id` bigint(20) NOT NULL COMMENT '操作员ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`preset_id`),
  KEY `inx_preset_info_camera_id` (`camera_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='预置点信息';

-- ----------------------------
-- Table structure for qrcode_coupon
-- ----------------------------
DROP TABLE IF EXISTS `qrcode_coupon`;
CREATE TABLE `qrcode_coupon` (
  `record_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `batch_code` varchar(32) NOT NULL COMMENT '批次编号',
  `coupon_type` tinyint(3) unsigned NOT NULL COMMENT '规则类型：0 减免金额；1 打折；2 全面j减免；3 按时长',
  `record_name` varchar(256) DEFAULT NULL COMMENT '发放名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `issue_count` int(11) unsigned NOT NULL COMMENT '发放数量',
  `issue_money` int(11) unsigned NOT NULL COMMENT '单位分，优惠券的金额',
  `single_limit_count` int(11) unsigned NOT NULL COMMENT '单人最多领取数量',
  `rest_count` int(11) unsigned NOT NULL COMMENT '剩余未领取的优惠券数量',
  `img_url` varchar(256) DEFAULT NULL COMMENT '生成的二维码图片地址',
  `status` tinyint(4) unsigned NOT NULL COMMENT '状态：0 正在发放；1 已发放完毕；2 已终止',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注信息',
  `free_time` smallint(6) DEFAULT NULL COMMENT '减免时长：单位 分钟',
  `single_daily_count` int(11) unsigned NOT NULL COMMENT '单人每天最多领取的数量，必须小于等于limit_count',
  `free_rate` tinyint(4) DEFAULT NULL COMMENT '折扣率：单位%',
  `dealer_id` bigint(11) DEFAULT NULL COMMENT '商户id',
  `bgn_date` datetime NOT NULL COMMENT '开始时间：优惠券有效期的开始时间',
  `end_date` datetime NOT NULL COMMENT '结束时间：优惠券有效期的结束时间',
  PRIMARY KEY (`record_id`),
  KEY `idx_batch_coupon_record_code` (`batch_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='二维码赠券表';

-- ----------------------------
-- Table structure for receivable
-- ----------------------------
DROP TABLE IF EXISTS `receivable`;
CREATE TABLE `receivable` (
  `receivable_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '应收ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '付费用户ID',
  `bill_no` varchar(64) NOT NULL COMMENT '账单编号',
  `bill_type` tinyint(3) unsigned NOT NULL COMMENT '账单类型：0 运营商套餐服务费；1 优惠券费用；2 包期车费用；3 车位预订费；4账户充值；5停车缴费；',
  `money` int(11) NOT NULL COMMENT '金额：单位分',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(256) NOT NULL COMMENT '账单描述信息',
  `status` tinyint(3) unsigned NOT NULL COMMENT '状态：0 未付款；1 部分付款；2 付款完成；99 失效（订单取消）',
  `pay_channel` tinyint(3) unsigned NOT NULL COMMENT '支付渠道：1 管控平台；2 手机app；3 门户网站；4 微信公众号；5支付宝车主服务应用；6代扣；7POS',
  `invoke_befor` varchar(256) DEFAULT NULL COMMENT '调用前业务判断接口',
  `invoke_after` varchar(256) DEFAULT NULL COMMENT '调用后回调接口',
  PRIMARY KEY (`receivable_id`),
  UNIQUE KEY `idx_receivable_bill_no` (`bill_no`),
  KEY `idx_receivable_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应收：运营商套餐费；驾驶人预订车位订单费、包期费、优惠券费 产生的应收';

-- ----------------------------
-- Table structure for received
-- ----------------------------
DROP TABLE IF EXISTS `received`;
CREATE TABLE `received` (
  `received_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '实收ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '付费用户ID',
  `receivable_id` bigint(20) unsigned NOT NULL COMMENT '应收ID',
  `bill_no` varchar(64) NOT NULL COMMENT '账单编号',
  `pay_money` int(11) NOT NULL COMMENT '付款金额',
  `pay_time` datetime NOT NULL COMMENT '付款时间',
  `pay_type` tinyint(4) NOT NULL COMMENT '付款方式：0 现金；1 支付宝；2 微信；3 银联；4账户余额；99其他',
  `third_bill_no` varchar(64) DEFAULT NULL COMMENT '第三方账单编号',
  PRIMARY KEY (`received_id`),
  KEY `idx_received_pay_time` (`pay_time`),
  KEY `idx_received_receivable_id` (`receivable_id`),
  KEY `idx_received_bill_no` (`bill_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实收：收费成功后产生一条实收\r\n1）一条应收可多次缴费，每次一条实收\r\n2）实收金额不能大于应收金额';

-- ----------------------------
-- 排班
-- ----------------------------


-- ----------------------------
-- Table structure for schedule_cfg
-- ----------------------------
DROP TABLE IF EXISTS `schedule_cfg`;
CREATE TABLE `schedule_cfg` (
  `cfg_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：班次ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `type` tinyint(3) unsigned NOT NULL COMMENT '班次类型：1 全天；2  两班倒；3 三班倒',
  `description` varchar(128) DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`cfg_id`),
  KEY `idx_schedule_cfg_dealer` (`dealer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='排班班次表：商户管理员添加的班次信息';

-- ----------------------------
-- Table structure for schedule_cfg_detail
-- ----------------------------
DROP TABLE IF EXISTS `schedule_cfg_detail`;
CREATE TABLE `schedule_cfg_detail` (
  `detail_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：明细ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `cfg_id` bigint(20) unsigned NOT NULL COMMENT '主键：班次ID',
  `time_type` tinyint(3) unsigned NOT NULL COMMENT '时间类型：11 全天的时间；21 两班倒第一班时间；22 两班倒第二班时间；\r\n            31 三班倒第一班时间；32 三班倒第二班时间；33 三班倒第三版时间',
  `bng_time` time NOT NULL COMMENT '开始时间',
  `end_time` time NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_schedule_cfg_detail_cfg_id` (`cfg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='排班班次明细：排班班次表：商户管理员添加';

-- ----------------------------
-- Table structure for schedule_record
-- ----------------------------
DROP TABLE IF EXISTS `schedule_record`;
CREATE TABLE `schedule_record` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) unsigned DEFAULT NULL COMMENT '停车点ID',
  `gate_id` bigint(20) unsigned DEFAULT NULL COMMENT '出入口ID',
  `collector_id` bigint(20) unsigned NOT NULL COMMENT '收费员ID',
  `time_type` tinyint(3) unsigned NOT NULL COMMENT '时间类型：0未启用排班，11 全天的时间；21 两班倒第一班时间；22 两班倒第二班时间；\r\n            31 三班倒第一班时间；32 三班倒第二班时间；33 三班倒第三版时间',
  `start_time` datetime NOT NULL COMMENT '上班时间',
  `end_time` datetime NOT NULL COMMENT '下班时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `operator_id` bigint(20) unsigned NOT NULL COMMENT '操作员ID',
  PRIMARY KEY (`record_id`),
  KEY `idx_schedule_record_dealer` (`dealer_id`,`start_time`,`park_id`,`gate_id`),
  KEY `idx_schedule_record_collector_id` (`collector_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='排班记录表：排班管理产生';

-- ----------------------------
-- Table structure for schedule_task_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_task_log`;
CREATE TABLE `schedule_task_log` (
  `log_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：任务标识',
  `task_name` varchar(32) NOT NULL COMMENT '任务名称',
  `exe_time` datetime DEFAULT NULL COMMENT '执行时间',
  `class_name` varchar(64) NOT NULL COMMENT '定时任务实际业务方法的路径',
  `rslt` tinyint(3) unsigned NOT NULL COMMENT '执行结果：0失败；1成功',
  `exe_again` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否再次执行过：0 否；1 是',
  `bgn_time` datetime DEFAULT NULL COMMENT '抽取开始时间：抽取类定时任务使用',
  `end_time` datetime DEFAULT NULL COMMENT '抽取结束时间：抽取类定时任务使用',
  `err_msg` text COMMENT '执行错误时的消息',
  PRIMARY KEY (`log_id`),
  KEY `idx_schedule_task_log_rslt` (`rslt`,`exe_again`,`exe_time`),
  KEY `idx_schedule_task_log_exe_time` (`exe_time`)
) ENGINE=InnoDB AUTO_INCREMENT=143217 DEFAULT CHARSET=utf8 COMMENT='定时任务运行日志';

-- ----------------------------
-- Table structure for schedule_task_log_detail
-- ----------------------------
DROP TABLE IF EXISTS `schedule_task_log_detail`;
CREATE TABLE `schedule_task_log_detail` (
  `detail_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：明细标识',
  `log_id` bigint(20) unsigned NOT NULL COMMENT '任务标识ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `rslt` tinyint(3) unsigned NOT NULL COMMENT '执行结果：0失败；1成功',
  `input` varchar(1024) DEFAULT NULL COMMENT '入参信息',
  PRIMARY KEY (`detail_id`),
  KEY `idx_schedule_task_log_detail_log` (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66647 DEFAULT CHARSET=utf8 COMMENT='定时任务运行日志详情：记录具体商户ID，执行时参数';

-- ----------------------------
-- Table structure for schedule_template
-- ----------------------------
DROP TABLE IF EXISTS `schedule_template`;
CREATE TABLE `schedule_template` (
  `sched_templ_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '调度模板ID',
  `type` smallint(6) NOT NULL DEFAULT '0' COMMENT '模版类型：0是录像计划模版；1是告警布防计划模版; 3是用户自定义的模版',
  `name` varchar(256) NOT NULL COMMENT '录像计划模版名称',
  `monday_sched` varchar(64) NOT NULL COMMENT '星期一的录像计划ID',
  `tuesday_sched` varchar(64) NOT NULL COMMENT '星期二的计划ID',
  `wednesday_sched` varchar(64) NOT NULL COMMENT '星期三录像计划ID',
  `thursday_sched` varchar(64) NOT NULL COMMENT '星期四计划ID',
  `friday_sched` varchar(64) NOT NULL COMMENT '星期五的计划ID',
  `saturday_sched` varchar(64) NOT NULL COMMENT '星期六计划ID',
  `sunday_sched` varchar(64) NOT NULL COMMENT '星期天计划ID',
  `description` varchar(256) DEFAULT NULL COMMENT '描述信息',
  `sequence_idx` int(11) NOT NULL COMMENT '排序索引',
  `operator_id` bigint(20) NOT NULL COMMENT '操作员ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`sched_templ_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='录像计划模版';

-- ----------------------------
-- Table structure for security_config
-- ----------------------------
DROP TABLE IF EXISTS `security_config`;
CREATE TABLE `security_config` (
  `guid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `valid_code_appear_count` smallint(6) DEFAULT NULL COMMENT '连续登录失败次数(引入验证码)',
  `account_lock_error_count` smallint(6) DEFAULT NULL COMMENT '连续登录失败次数(锁定账号)  ',
  `ip_lock_error_count` smallint(6) DEFAULT NULL COMMENT '连续登录失败次数(锁定IP) ',
  `interval_time` smallint(6) DEFAULT NULL COMMENT '用户锁定后重新登录间隔时间：单位分钟',
  `status` tinyint(4) NOT NULL COMMENT '安全策略启用状态 0:不启用 1:启用',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='安全策略配置';

-- ----------------------------
-- Table structure for serv_offline_pay
-- ----------------------------
DROP TABLE IF EXISTS `serv_offline_pay`;
CREATE TABLE `serv_offline_pay` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `app_id` bigint(20) unsigned DEFAULT NULL COMMENT '申请信息ID',
  `bank_account` varchar(64) NOT NULL COMMENT '银行账户',
  `bank_name` varchar(64) NOT NULL COMMENT '银行名称',
  `bank_addr` varchar(128) DEFAULT NULL COMMENT '银行地址',
  `account_holder` varchar(32) NOT NULL COMMENT '账户持有人',
  `payment_code` varchar(32) NOT NULL COMMENT '付款凭证：系统生成，该内容填写到付款单备注里面',
  `serv_info` text COMMENT '购买的服务信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) unsigned NOT NULL COMMENT '创建的用户ID',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态：0 处理中;1 已完成',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否已经删除：0否；1 是',
  PRIMARY KEY (`id`),
  KEY `idx_serv_offline_pay_appid` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存车主银行转账后填写转账记录和服务信息';

-- ----------------------------
-- Table structure for serv_pay_evidence
-- ----------------------------
DROP TABLE IF EXISTS `serv_pay_evidence`;
CREATE TABLE `serv_pay_evidence` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键ID',
  `app_id` bigint(20) DEFAULT NULL COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户标识（上传人员ID）',
  `create_time` datetime NOT NULL COMMENT '记录生成时间',
  `bank_account` varchar(64) NOT NULL COMMENT '银行账号',
  `img_url` varchar(256) NOT NULL COMMENT '凭证图片URL',
  PRIMARY KEY (`id`),
  KEY `idx_serv_pay_evidence_appid` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务费转账凭证，可多次上传';

-- ----------------------------
-- Table structure for server_address
-- ----------------------------
DROP TABLE IF EXISTS `server_address`;
CREATE TABLE `server_address` (
  `server_address_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '服务器地址ID',
  `server_id` bigint(20) unsigned NOT NULL COMMENT '服务器ID',
  `address_type` smallint(6) NOT NULL COMMENT '地址类型：0-公网地址；1-私网地址；2-电信地址；3-联通地址；4-移动地址；5-教育网地址',
  `ip_addr` varchar(64) NOT NULL COMMENT '监听地址',
  `listen_port` int(11) NOT NULL DEFAULT '0' COMMENT '监听端口',
  `ip_map` varchar(64) DEFAULT NULL COMMENT '地址映射，用于网闸或NAT的IP映射场合',
  `net_zone_id` bigint(20) unsigned DEFAULT NULL COMMENT '网域id',
  PRIMARY KEY (`server_address_id`),
  KEY `relationship_84_fk` (`server_id`)
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8 COMMENT='服务器地址';

-- ----------------------------
-- Table structure for server_config
-- ----------------------------
DROP TABLE IF EXISTS `server_config`;
CREATE TABLE `server_config` (
  `server_config_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID值',
  `server_id` bigint(20) unsigned NOT NULL COMMENT '服务器ID',
  `parameter_name` varchar(256) NOT NULL COMMENT '参数名称',
  `value` varchar(512) DEFAULT NULL COMMENT '配置的参数值,都以字符串保存',
  `operator_id` bigint(20) unsigned NOT NULL COMMENT '操作员ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`server_config_id`),
  KEY `relationship_85_fk` (`server_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务器配置信息';

-- ----------------------------
-- Table structure for server_cvr
-- ----------------------------
DROP TABLE IF EXISTS `server_cvr`;
CREATE TABLE `server_cvr` (
  `server_id` bigint(20) unsigned NOT NULL COMMENT '服务器ID',
  `vrm_server_id` bigint(20) unsigned NOT NULL COMMENT 'VRM服务器ID',
  `ctrl_port` int(11) NOT NULL COMMENT 'CTRL_PORT',
  `user_name` varchar(64) DEFAULT NULL COMMENT '嵌入式服务器用户名',
  `user_pwd` varchar(64) DEFAULT NULL COMMENT '嵌入式服务器用户密码',
  PRIMARY KEY (`vrm_server_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ENVR_SERVER';

-- ----------------------------
-- Table structure for server_envr
-- ----------------------------
DROP TABLE IF EXISTS `server_envr`;
CREATE TABLE `server_envr` (
  `server_id` bigint(20) unsigned NOT NULL COMMENT '服务器ID',
  `vrm_server_id` bigint(20) unsigned NOT NULL COMMENT 'VRM服务器ID',
  `user_name` varchar(64) DEFAULT NULL COMMENT '嵌入式服务器用户名',
  `user_pwd` varchar(64) DEFAULT NULL COMMENT '嵌入式服务器用户密码',
  `ctrl_port` int(11) DEFAULT NULL COMMENT 'CTRL_PORT'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for server_info
-- ----------------------------
DROP TABLE IF EXISTS `server_info`;
CREATE TABLE `server_info` (
  `server_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '服务器ID',
  `control_unit_id` bigint(20) unsigned NOT NULL COMMENT '控制中心ID',
  `server_type` smallint(6) NOT NULL COMMENT '服务器类型',
  `share_flag` smallint(6) NOT NULL DEFAULT '1' COMMENT '是否允许下级控制中心使用，1表示可以使用，0表示不允许使用',
  `index_code` varchar(64) NOT NULL COMMENT '索引编号',
  `name` varchar(256) DEFAULT NULL COMMENT '服务器名称',
  `is_enable` smallint(6) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `description` varchar(256) DEFAULT NULL COMMENT '描述信息',
  `ip_addr` varchar(64) NOT NULL DEFAULT '0.0.0.0' COMMENT '监听地址',
  `hpp_port` int(11) NOT NULL DEFAULT '0' COMMENT 'HPP端口',
  `snmp_port` int(11) NOT NULL DEFAULT '0' COMMENT 'SNMP端口',
  `use_soft_dog` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否启用软件狗',
  `max_capability` int(11) DEFAULT '0' COMMENT '最大负载',
  `sequence_idx` int(11) NOT NULL DEFAULT '0' COMMENT '排序索引',
  `operator_id` bigint(20) NOT NULL COMMENT '操作员ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `res_port_1` int(11) DEFAULT NULL COMMENT '预留port（tdhs数据接收端口、tda数据接收端口）',
  `is_registered` smallint(6) DEFAULT NULL COMMENT '是否注册标志',
  `web_service_port` int(11) DEFAULT NULL COMMENT 'WEB SERVICE端口(VRM、NVR、VTDU、流量预测、vms)',
  `net_zone_id` bigint(20) NOT NULL COMMENT '网域id',
  `listen_port` int(11) DEFAULT NULL COMMENT '监听端口',
  `geom_port` int(11) DEFAULT NULL COMMENT '地磁上传端口',
  `geom_selfport` int(11) DEFAULT NULL COMMENT '自研地磁上传端口',
  `vedio_port` int(11) DEFAULT NULL COMMENT '视频检测接入端口',
  `server_port` int(11) DEFAULT NULL COMMENT 'OOMS服务端口',
  `alarm_port` int(11) DEFAULT NULL COMMENT '上报端口 ',
  `ethernet_ip` varchar(32) DEFAULT NULL COMMENT '地磁外网ip',
  `isserver_mode` smallint(6) DEFAULT NULL COMMENT '是否为服务端模式',
  `device_port` int(11) DEFAULT NULL COMMENT '''LED设备服务端口',
  PRIMARY KEY (`server_id`)
) ENGINE=InnoDB AUTO_INCREMENT=121119 DEFAULT CHARSET=utf8 COMMENT='服务器信息';

-- ----------------------------
-- Table structure for server_nvr
-- ----------------------------
DROP TABLE IF EXISTS `server_nvr`;
CREATE TABLE `server_nvr` (
  `server_id` bigint(20) unsigned NOT NULL COMMENT '服务器ID',
  `max_record_task` smallint(6) NOT NULL DEFAULT '1000' COMMENT '最大的同时录像任务数',
  `max_vod_task` smallint(6) NOT NULL DEFAULT '1000' COMMENT '最大的同时回放任务数',
  `rtsp_port` int(11) NOT NULL DEFAULT '554' COMMENT 'RTSP侦听端口',
  `search_port` int(11) NOT NULL COMMENT '查询端口',
  `type` smallint(6) NOT NULL DEFAULT '0' COMMENT '服务器类型：0-PCNVR；1-嵌入式存储；2-邦诺存储',
  `tcp_base_port` int(11) DEFAULT NULL COMMENT 'TCP起始端口',
  `tcp_port_count` int(11) DEFAULT NULL COMMENT 'TCP端口范围',
  `udp_base_port` int(11) DEFAULT NULL COMMENT 'UDP起始端口',
  `udp_port_count` int(11) DEFAULT NULL COMMENT 'UDP端口范围',
  `vrm_server_id` bigint(20) NOT NULL COMMENT 'VRM服务器ID',
  `web_port` int(11) DEFAULT NULL COMMENT 'WEB服务端口',
  PRIMARY KEY (`server_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='NVR网络录像服务器';

-- ----------------------------
-- Table structure for server_status
-- ----------------------------
DROP TABLE IF EXISTS `server_status`;
CREATE TABLE `server_status` (
  `server_id` bigint(20) unsigned NOT NULL COMMENT '服务器ID',
  `is_online` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否在线',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `heartbeat_time` datetime DEFAULT NULL COMMENT '最后心跳时间',
  `version` varchar(256) DEFAULT NULL COMMENT '版本信息',
  `cur_capability` int(11) DEFAULT '0' COMMENT '当前负载',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `interval_time` smallint(6) DEFAULT '0' COMMENT '采集间隔，单位秒，1表示即时信息',
  `start_time` datetime DEFAULT NULL COMMENT '启动时间：服务器指操作系统的启动时间，服务程序指程序的启动时间',
  `cpu_usage` smallint(6) DEFAULT NULL COMMENT 'CPU使用率，范围0-100',
  `cpu_count` smallint(6) DEFAULT NULL COMMENT 'CPU个数，双核算两个',
  `mem_usage` smallint(6) DEFAULT NULL COMMENT '内存使用率，范围0-100',
  `mem_total` smallint(6) DEFAULT NULL COMMENT '内存大小总数，单位MB',
  `disk_usage` smallint(6) DEFAULT NULL COMMENT '硬盘使用率，范围0-100',
  `disk_total` smallint(6) DEFAULT NULL COMMENT '硬盘大小总数，单位MB',
  `send_bitrate` int(11) DEFAULT NULL COMMENT '发送码率，单位bps',
  `recv_bitrate` int(11) DEFAULT NULL COMMENT '接收码率，单位bps',
  PRIMARY KEY (`server_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务器工作状态';

-- ----------------------------
-- Table structure for server_status_current
-- ----------------------------
DROP TABLE IF EXISTS `server_status_current`;
CREATE TABLE `server_status_current` (
  `server_status_current_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '服务器状态ID',
  `server_id` bigint(20) unsigned NOT NULL COMMENT '服务器ID',
  `object_type` int(11) NOT NULL COMMENT '服务器对象类型',
  `object_id` int(11) NOT NULL COMMENT '服务器对象ID',
  `ip_addr` varchar(64) NOT NULL COMMENT 'IP地址',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `interval_time` smallint(6) NOT NULL DEFAULT '1' COMMENT '采集间隔，单位秒，1表示即时信息',
  `oid` varchar(256) NOT NULL COMMENT 'OID',
  `value` int(11) DEFAULT NULL COMMENT '值',
  `info` varchar(256) DEFAULT NULL COMMENT '信息',
  PRIMARY KEY (`server_status_current_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务器当前工作状态';

-- ----------------------------
-- Table structure for server_status_day
-- ----------------------------
DROP TABLE IF EXISTS `server_status_day`;
CREATE TABLE `server_status_day` (
  `server_status_day_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '服务器状态ID',
  `object_type` int(11) NOT NULL COMMENT '服务器对象类型',
  `object_id` bigint(20) NOT NULL COMMENT '服务器对象ID',
  `ip_addr` varchar(64) NOT NULL COMMENT 'IP地址',
  `update_day` datetime NOT NULL COMMENT '更新时间',
  `oid` varchar(256) NOT NULL COMMENT 'OID',
  `value` int(11) DEFAULT NULL COMMENT '值',
  `info` varchar(256) DEFAULT NULL COMMENT '信息',
  PRIMARY KEY (`server_status_day_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务器每天工作状态';

-- ----------------------------
-- Table structure for server_status_history
-- ----------------------------
DROP TABLE IF EXISTS `server_status_history`;
CREATE TABLE `server_status_history` (
  `server_status_history_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '服务器状态ID',
  `object_type` int(11) NOT NULL COMMENT '服务器对象类型',
  `object_id` bigint(20) NOT NULL COMMENT '服务器对象ID',
  `ip_addr` varchar(64) NOT NULL COMMENT 'IP地址',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `interval_time` smallint(6) NOT NULL COMMENT '采集间隔，单位秒，1表示即时信息',
  `oid` varchar(256) NOT NULL COMMENT 'OID',
  `value` int(11) DEFAULT NULL COMMENT '值',
  `info` varchar(256) DEFAULT NULL COMMENT '信息',
  PRIMARY KEY (`server_status_history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务器历史工作状态';

-- ----------------------------
-- Table structure for server_vag
-- ----------------------------
DROP TABLE IF EXISTS `server_vag`;
CREATE TABLE `server_vag` (
  `server_id` bigint(20) unsigned NOT NULL COMMENT '服务器ID',
  `ehome_port` int(11) DEFAULT NULL COMMENT 'Ehome端口',
  `pag_local_port` int(11) DEFAULT NULL COMMENT 'PAG本地端口',
  `rtsp_port` int(11) DEFAULT NULL COMMENT 'RTSP侦听端口',
  `rtp_rtcp_port` int(11) DEFAULT NULL COMMENT 'RTP_RTCP端口',
  `tcp_base_port` int(11) DEFAULT NULL COMMENT 'TCP起始端口',
  `tcp_port_count` int(11) DEFAULT NULL COMMENT 'TCP端口范围',
  `udp_base_port` int(11) DEFAULT NULL COMMENT 'UDP起始端口',
  `udp_port_count` int(11) DEFAULT NULL COMMENT 'UDP端口范围',
  `vtdu_local_port` int(11) DEFAULT NULL COMMENT 'VTDU本地端口',
  PRIMARY KEY (`server_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='VAG服务器';

-- ----------------------------
-- Table structure for server_vrm
-- ----------------------------
DROP TABLE IF EXISTS `server_vrm`;
CREATE TABLE `server_vrm` (
  `server_id` bigint(20) unsigned NOT NULL COMMENT '服务器ID',
  `store_port` int(11) NOT NULL COMMENT '存储端口',
  `search_port` int(11) NOT NULL COMMENT '查询端口',
  `rtsp_port` int(11) NOT NULL DEFAULT '554' COMMENT 'RTSP侦听端口',
  `web_port` int(11) DEFAULT NULL COMMENT 'WEB服务端口',
  `vod_port` int(11) DEFAULT NULL COMMENT 'VOD点播端口',
  `tcp_base_port` int(11) DEFAULT NULL COMMENT 'TCP起始端口',
  `tcp_port_count` int(11) DEFAULT NULL COMMENT 'TCP端口范围',
  `udp_base_port` int(11) DEFAULT NULL COMMENT 'UDP起始端口',
  `udp_port_count` int(11) DEFAULT NULL COMMENT 'UDP端口范围',
  `total_space` int(11) NOT NULL DEFAULT '0' COMMENT '存储空间总容量，单位MB',
  `free_space` int(11) NOT NULL DEFAULT '0' COMMENT '未分配的存储空间，单位MB',
  `max_record_task` int(11) NOT NULL DEFAULT '5000' COMMENT '最大的同时录像任务数',
  `max_task_per_ipsan` int(11) NOT NULL DEFAULT '100' COMMENT '每台IP-SAN最多同时执行的录像任务数',
  `max_vod_per_ipsan` int(11) NOT NULL DEFAULT '32' COMMENT '每台IP-SAN最多同时执行的回放任务数',
  PRIMARY KEY (`server_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='VRM录像管理服务器';

-- ----------------------------
-- Table structure for server_vtdu
-- ----------------------------
DROP TABLE IF EXISTS `server_vtdu`;
CREATE TABLE `server_vtdu` (
  `server_id` bigint(20) unsigned NOT NULL COMMENT '服务器ID',
  `rtsp_port` int(11) DEFAULT '554' COMMENT 'RTSP侦听端口',
  `tcp_base_port` int(11) DEFAULT NULL COMMENT 'TCP起始端口',
  `tcp_port_count` int(11) DEFAULT NULL COMMENT 'TCP端口范围',
  `udp_base_port` int(11) DEFAULT NULL COMMENT 'UDP起始端口',
  `udp_port_count` int(11) DEFAULT NULL COMMENT 'UDP端口范围',
  PRIMARY KEY (`server_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='VTDU实时流分发服务器';

-- ----------------------------
-- Table structure for share_code
-- ----------------------------
DROP TABLE IF EXISTS `share_code`;
CREATE TABLE `share_code` (
  `code_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '锁主ID',
  `lock_id` bigint(20) NOT NULL COMMENT '锁ID',
  `qr_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '二维码',
  `code_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '共享码编号',
  `bgn_date` datetime NOT NULL COMMENT '有效开始时间',
  `end_date` datetime NOT NULL COMMENT '有效结束时间',
  `is_delete` bigint(3) NOT NULL COMMENT '是否删除：0 否；1 是',
  PRIMARY KEY (`code_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='共享码';

-- ----------------------------
-- Table structure for share_lock
-- ----------------------------
DROP TABLE IF EXISTS `share_lock`;
CREATE TABLE `share_lock` (
  `share_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '使用共享的人',
  `lock_id` bigint(20) NOT NULL COMMENT '锁ID',
  `is_share` bigint(20) NOT NULL COMMENT '是否开启共享：1、是 2、否 ',
  `is_delete` bigint(3) NOT NULL COMMENT '是否删除：0 否；1 是',
  PRIMARY KEY (`share_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='共享车位锁（受益人）';

-- ----------------------------
-- Table structure for sys_code_attr
-- ----------------------------
DROP TABLE IF EXISTS `sys_code_attr`;
CREATE TABLE `sys_code_attr` (
  `code_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `code_name` varchar(256) NOT NULL COMMENT '代码名称，代码类型_代码名称，如：pageIndex_appDownPng；也可以写入汉字字符串',
  `code_value` varchar(256) DEFAULT NULL COMMENT '代码值，具有业务含义的字符串',
  `file_url` varchar(256) DEFAULT NULL COMMENT '存储内容较大的代码值，如：图片、文件等URL',
  `remark` varchar(256) NOT NULL COMMENT '备注信息',
  `is_delete` tinyint(3) unsigned NOT NULL COMMENT '是否已经删除：0 否；1 是',
  `ext1` varchar(256) DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(256) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`code_id`),
  KEY `idx_sys_code_attr_type_id` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=103881 DEFAULT CHARSET=utf8 COMMENT='参数值：某个参数类型的集合';

-- ----------------------------
-- Table structure for sys_code_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_code_type`;
CREATE TABLE `sys_code_type` (
  `type_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_code` varchar(64) NOT NULL COMMENT '类型编码',
  `parent_type_id` bigint(20) unsigned DEFAULT NULL COMMENT '父类型ID',
  `type_name` varchar(256) NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`type_id`),
  UNIQUE KEY `idx_sys_code_type_code` (`type_code`),
  KEY `idx_sys_code_parent_type_id` (`parent_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='代码类型表：记录具有相同业务含义的一类代码的集合共有的属性\r\n不能编辑，数据库后台录入';

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `dict_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：字典ID',
  `dict_type` int(10) unsigned NOT NULL COMMENT '字典分类：9999是用于描述字典分类的分类',
  `dict_value` int(10) NOT NULL COMMENT '代码值：如果是具体字典，value不能大于255',
  `dict_name` varchar(64) NOT NULL COMMENT '名称',
  `description` varchar(128) DEFAULT NULL COMMENT '描述信息',
  `display` tinyint(3) unsigned NOT NULL COMMENT '是否显示：0 否；1 显示',
  `order_num` smallint(6) DEFAULT NULL COMMENT '排序号',
  `readonly` tinyint(3) unsigned NOT NULL COMMENT '是否是只读：0 否；1 是',
  PRIMARY KEY (`dict_id`),
  KEY `idx_sys_dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=101918 DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `log_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：日志ID',
  `log_type` tinyint(3) unsigned NOT NULL COMMENT '日志类型：0 操作日志；1 报警日志；2 错误日志',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `resource_code` varchar(32) NOT NULL COMMENT '资源ID',
  `opt_type` tinyint(3) unsigned NOT NULL COMMENT '操作类型：0 登录；1 新增；2 修改；3 删除；4 查询；5 运行',
  `ip` varchar(32) NOT NULL COMMENT 'IP地址：IPv4或者IPv6',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`log_id`),
  KEY `idx_sys_log_user` (`user_id`,`log_type`,`create_time`,`resource_code`)
) ENGINE=InnoDB AUTO_INCREMENT=121340 DEFAULT CHARSET=utf8 COMMENT='日志表：各个操作时记录的日志，如果没有用户时使用系统用户';

-- ----------------------------
-- Table structure for sys_maintain_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_maintain_log`;
CREATE TABLE `sys_maintain_log` (
  `mlog_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：维护日志ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `content` varchar(256) NOT NULL COMMENT '维护内容',
  `version_code` varchar(32) DEFAULT NULL COMMENT '版本号',
  `mlog_type` tinyint(3) unsigned DEFAULT NULL COMMENT '版本类型：0 管控平台；1 IOS；2 Android',
  PRIMARY KEY (`mlog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统维护表：海康运维管理员云平台各种升级、查看、维护操作。';

-- ----------------------------
-- Table structure for sys_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message` (
  `message_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `content` varchar(256) NOT NULL COMMENT '消息内容',
  `type` smallint(5) unsigned NOT NULL COMMENT '消息类型：2001包期即将到期提醒；2002包期结束提醒；3001优惠券即将到期提醒；3002优惠券消费成功提醒',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`message_id`),
  KEY `idx_sys_message_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息：订单新增、付款、取消、退款；服务套餐新增、变更、取消；平台消息 等\r\n1）驾驶人订单消息，平台消息\r\n                                -';

-- ----------------------------
-- Table structure for sys_opinion
-- ----------------------------
DROP TABLE IF EXISTS `sys_opinion`;
CREATE TABLE `sys_opinion` (
  `opinion_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：意见ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID：提建议的人，如果没有则使用随机ID',
  `advisor` tinyint(4) NOT NULL COMMENT '建议者：1 驾驶人；2 商户；3 游客',
  `create_channel` tinyint(4) unsigned NOT NULL COMMENT '创建渠道：1 管控平台；2 手机app；3 门户网站；4 微信公众号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `content` varchar(256) NOT NULL COMMENT '内容',
  `mobile_phone` varchar(11) DEFAULT NULL COMMENT '手机号码：游客必须输入',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名：游客必须输入',
  `reply_msg` varchar(256) DEFAULT NULL COMMENT '回复信息',
  `is_replied` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已回复：0未回复；1已回复',
  `reply_time` datetime DEFAULT NULL COMMENT '回复时间',
  `reply_person` bigint(20) unsigned DEFAULT NULL COMMENT '回复人',
  PRIMARY KEY (`opinion_id`),
  KEY `idx_sys_opinion_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='意见信息表：用户提交的意见信息\r\n游客、驾驶人、运营商管理员、海康管理员等网站使用人员。';

-- ----------------------------
-- Table structure for sys_packages
-- ----------------------------
DROP TABLE IF EXISTS `sys_packages`;
CREATE TABLE `sys_packages` (
  `p_id` bigint(20) unsigned NOT NULL COMMENT '主键：套餐ID',
  `p_name` varchar(32) NOT NULL COMMENT '套餐名字',
  `icon` varchar(256) DEFAULT NULL COMMENT '解决方案图标URL',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注信息',
  `spend` int(11) NOT NULL COMMENT '购买金额(单位分)',
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统套餐：系统定义的套餐\r\n套餐可以有多个服务';

-- ----------------------------
-- Table structure for sys_packages_serv_rela
-- ----------------------------
DROP TABLE IF EXISTS `sys_packages_serv_rela`;
CREATE TABLE `sys_packages_serv_rela` (
  `p_id` bigint(20) unsigned NOT NULL COMMENT '主键：套餐ID',
  `serv_id` bigint(20) unsigned NOT NULL COMMENT '主键：服务ID',
  `num` int(11) NOT NULL COMMENT '服务个数',
  PRIMARY KEY (`p_id`,`serv_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统套餐服务关系表\n1）服务个数*倍率 = 最终运营商享有的服务数量或者周期';

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `resource_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resource_name` varchar(32) NOT NULL COMMENT '资源名称：菜单名称',
  `resource_key` text,
  `resource_code` varchar(32) NOT NULL COMMENT '资源编号',
  `parent_resource_code` varchar(32) DEFAULT NULL COMMENT '上级资源编码',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '资源类型：0 页面；1 按钮',
  `level` tinyint(4) NOT NULL COMMENT '等级：1 一级菜单，2 是二级菜单',
  `flag` tinyint(4) DEFAULT NULL,
  `order_no` smallint(6) NOT NULL COMMENT '排序号',
  `icon` varchar(32) DEFAULT NULL COMMENT ' 图标名称',
  `sn` varchar(32) DEFAULT NULL COMMENT '权限控制',
  `url` varchar(128) DEFAULT NULL COMMENT 'URL地址，如果是按钮则实际URL+按钮对应的html id',
  `remark` varchar(64) DEFAULT NULL COMMENT '资源备注信息',
  `create_person` varchar(32) NOT NULL COMMENT '创建人：账户',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_person` varchar(32) DEFAULT NULL COMMENT '更新人：账户',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(3) unsigned NOT NULL COMMENT '是否删除：0 否；1 是',
  PRIMARY KEY (`resource_id`),
  KEY `idx_sys_resource_first` (`is_delete`,`level`,`order_no`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=102838 DEFAULT CHARSET=utf8 COMMENT='权限：即资源 菜单URL 按钮';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dealer_id` bigint(20) unsigned DEFAULT NULL COMMENT '商户ID',
  `role_key` varchar(32) DEFAULT NULL COMMENT '角色KEY',
  `role_name` varchar(32) NOT NULL COMMENT '角色名称',
  `description` varchar(128) NOT NULL COMMENT '角色描述',
  `create_person` varchar(32) DEFAULT NULL COMMENT '创建人：账户',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_person` varchar(32) DEFAULT NULL COMMENT '更新人：账户',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(3) unsigned NOT NULL COMMENT '是否删除：0 否；1 是',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=508 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Table structure for sys_role_privilege_rela
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_privilege_rela`;
CREATE TABLE `sys_role_privilege_rela` (
  `guid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `privilege_id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `type` tinyint(4) NOT NULL COMMENT '关联类型，用户说明privilege_id的来源，2 表示停车子系统, 3表示停车场，4表示萤石设备 5表示路边设备',
  PRIMARY KEY (`guid`),
  KEY `idx_sys_role_privilege_rela` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4808 DEFAULT CHARSET=utf8 COMMENT='角色权限关系表';

-- ----------------------------
-- Table structure for sys_role_resource_rela
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource_rela`;
CREATE TABLE `sys_role_resource_rela` (
  `guid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `resource_id` bigint(20) unsigned DEFAULT NULL COMMENT '主键',
  `resource_code` varchar(32) DEFAULT NULL COMMENT 'sys_resource资源表中resource_code',
  `type` tinyint(4) DEFAULT NULL COMMENT '关联类型，用户说明resource_id的来源，1 表示系统资源表，2 表示停车子系统, 3表示停车场，4表示萤石设备',
  PRIMARY KEY (`guid`),
  KEY `idx_sys_role_resource_rela_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14135 DEFAULT CHARSET=utf8 COMMENT='角色资源关系表';

-- ----------------------------
-- Table structure for sys_serv
-- ----------------------------
DROP TABLE IF EXISTS `sys_serv`;
CREATE TABLE `sys_serv` (
  `serv_id` bigint(20) unsigned NOT NULL COMMENT '主键：服务ID',
  `serv_name` varchar(32) NOT NULL COMMENT '服务名称',
  `price` int(11) NOT NULL COMMENT '单价：单位分',
  `type` tinyint(3) unsigned NOT NULL COMMENT '类型：11：个；12：条；13：月；14：年；21：条/月；22：条/年',
  `rate` smallint(5) unsigned NOT NULL COMMENT '倍率：默认为1，主要用于周期类服务',
  `maximum` int(11) NOT NULL COMMENT '套餐内该服务最大数量',
  `free_num` smallint(6) NOT NULL COMMENT '免费数量',
  `icon` varchar(256) DEFAULT NULL COMMENT '服务图标',
  `unit_time` varchar(5) DEFAULT NULL COMMENT '时间单位',
  `true_serv_id` int(10) unsigned NOT NULL COMMENT '真实服务ID：1~7',
  PRIMARY KEY (`serv_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统服务：多个服务组成一个系统套餐，或者用户自定义套餐';

-- ----------------------------
-- Table structure for sys_serv_resource_rela
-- ----------------------------
DROP TABLE IF EXISTS `sys_serv_resource_rela`;
CREATE TABLE `sys_serv_resource_rela` (
  `serv_id` bigint(20) unsigned NOT NULL COMMENT '主键：服务ID',
  `resource_id` bigint(20) unsigned NOT NULL COMMENT '主键',
  PRIMARY KEY (`serv_id`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务资源关系表';

-- ----------------------------
-- Table structure for sys_third_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_third_user`;
CREATE TABLE `sys_third_user` (
  `third_user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：第三方用户ID，内部ID',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '系统用户ID',
  `third_out_user_id` varchar(32) NOT NULL COMMENT '第三方系统唯一ID，外部ID',
  `third_type` tinyint(3) unsigned NOT NULL COMMENT '第三方类型：1 微信；2 QQ；3 新浪',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '昵称：第三方系统的昵称',
  `create_time` datetime NOT NULL COMMENT '本记录创建时间',
  PRIMARY KEY (`third_user_id`),
  KEY `idx_sys_third_user_id` (`user_id`),
  KEY `idx_sys_third_user_tid` (`third_type`,`third_out_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方登录信息表：使用第三方其他账号登录，比如：QQ、微信、新浪，第三方用户第一次登录时要先生成默认系统用户';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户账户',
  `name` varchar(32) DEFAULT NULL COMMENT '用户名称/昵称',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `salt` varchar(32) NOT NULL COMMENT '盐值',
  `create_channel` tinyint(4) DEFAULT NULL COMMENT '创建渠道：1 管控平台；2 手机app；3 门户网站；4 微信公众号',
  `expire_time` datetime NOT NULL COMMENT '失效时间',
  `user_type` tinyint(3) unsigned NOT NULL COMMENT '用户类型：0  驾驶人；1 海康管理员；,2 停车运营商；3 停车运营商子账号；4 第三方账户；5 游客用户；\r\n            游客用户数量等于分片数量，且USER_ID能均匀被分到具体分片\r\n            主要用来对数据进行分片',
  `parent_user_id` bigint(20) unsigned DEFAULT NULL COMMENT '父用户ID：适用于停车运营商超级管理员新增子管理员情形\r\n            和海康超级管理员添加子管理员',
  `login_count` int(11) DEFAULT '0' COMMENT '登录次数',
  `status` tinyint(3) unsigned NOT NULL COMMENT '状态：0 注销 ； 1 正常；2 冻结；3 需要修改密码',
  `mobile_phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `last_login_time` datetime DEFAULT NULL COMMENT '最新一次登录时间',
  `last_login_channel` tinyint(4) DEFAULT NULL COMMENT '最新一次登录渠道：1 管控平台；2 手机app；3 门户网站；4 微信公众号',
  `connect_id` bigint(20) unsigned DEFAULT NULL COMMENT '驾驶人对应的运营商DEALER_ID',
  `create_person` varchar(32) DEFAULT NULL COMMENT '创建人：账户',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_person` varchar(32) DEFAULT NULL COMMENT '更新人：账户',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(3) unsigned NOT NULL COMMENT '是否删除：0 否；1 是',
  PRIMARY KEY (`user_id`),
  KEY `idx_sys_user_account` (`account`),
  KEY `idx_sys_user_puid` (`parent_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=utf8 COMMENT='系统用户：用户申请注册为驾驶人、驾驶人入驻为停车运营商、添加海康运营管理员时产生\r\n1）驾驶人入驻为停车运营商';

-- ----------------------------
-- Table structure for sys_user_role_rela
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_rela`;
CREATE TABLE `sys_user_role_rela` (
  `guid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`guid`),
  KEY `idx_sys_user_role_rela_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Table structure for task_info
-- ----------------------------
DROP TABLE IF EXISTS `task_info`;
CREATE TABLE `task_info` (
  `task_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '申请人ID',
  `task_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '任务时间',
  `task_type` tinyint(3) unsigned NOT NULL COMMENT '任务类型：1 审核；2 申请修改 ',
  `task_title` varchar(64) DEFAULT NULL COMMENT '任务标题：用于前台代办列表显示',
  `url` varchar(128) NOT NULL COMMENT '处理实际任务的URL',
  `lock_user` bigint(20) unsigned DEFAULT NULL COMMENT '锁定人：被锁定后其他管理员就不能处理了',
  `status` tinyint(4) NOT NULL COMMENT '状态：0 未处理；1 已处理',
  `lock_time` datetime DEFAULT NULL COMMENT '锁定时间',
  PRIMARY KEY (`task_id`),
  KEY `idx_task_info_appid` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表：运营商入驻、广告提交、公告提交时产生\r\n1）每提交一次产生一条记录';


-- ----------------------------
-- Table structure for third_inspect_record
-- ----------------------------
DROP TABLE IF EXISTS `third_inspect_record`;
CREATE TABLE `third_inspect_record` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dealer_id` bigint(20) NOT NULL COMMENT '运营商ID',
  `docking_id` bigint(20) NOT NULL COMMENT '对接网关ID',
  `park_id` bigint(20) NOT NULL COMMENT '停车场编号',
  `inspector_code` varchar(64) DEFAULT NULL COMMENT '巡检员编号',
  `inspector_name` varchar(64) DEFAULT NULL COMMENT '巡检员名称',
  `inspect_time` datetime DEFAULT NULL COMMENT '巡检时间',
  `collector_id` bigint(20) DEFAULT NULL COMMENT '收费员ID',
  `collector_code` varchar(64) DEFAULT NULL COMMENT '收费员编号',
  `collector_name` varchar(64) DEFAULT NULL COMMENT '收费员名称',
  `inspect_rslt` tinyint(3) unsigned DEFAULT NULL COMMENT '巡检结果：0-正常，1-异常''',
  `abnormal_reason` tinyint(3) unsigned DEFAULT NULL COMMENT '异常原因，0-剩余车位数不正确，1-车牌号码不匹配',
  `description` varchar(256) DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='巡检记录表：路边系统通过DAGS上传';

-- ----------------------------
-- Table structure for third_recharge_record
-- ----------------------------
DROP TABLE IF EXISTS `third_recharge_record`;
CREATE TABLE `third_recharge_record` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：记录ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `docking_id` bigint(20) unsigned NOT NULL COMMENT '对接网关ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车场ID：为0时表示不能区分场库的费用',
  `plate_no` varchar(10) DEFAULT NULL COMMENT '车牌号码',
  `plate_color` tinyint(4) DEFAULT NULL COMMENT '车牌颜色，0-其他,1-蓝色,2-黄色,3-黑色,4-白色（-1表示没有类似于null，字典不修改）',
  `handing_time` datetime NOT NULL COMMENT '充值办理时间',
  `start_time` datetime DEFAULT NULL COMMENT '充值后有效开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '充值后有效结束时间',
  `should_pay` int(11) DEFAULT NULL COMMENT '应收金额：单位分',
  `actual_pay` int(11) DEFAULT NULL COMMENT '实收金额：单位分',
  `bag_type` tinyint(3) unsigned DEFAULT NULL COMMENT '包期类型：1 仅白天；2 仅晚上；3 包全天',
  `desposit` int(11) DEFAULT NULL COMMENT '押金金额',
  `collector_id` bigint(20) unsigned DEFAULT NULL COMMENT '收费员ID',
  `collector_code` varchar(64) DEFAULT NULL COMMENT '收费员编号',
  `collector_name` varchar(32) DEFAULT NULL COMMENT '收费员名称',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注信息',
  `recharge_type` tinyint(3) unsigned DEFAULT NULL COMMENT '充值类型：1 包期充值；2 账户充值',
  `account` varchar(32) DEFAULT NULL COMMENT '账户：车主',
  PRIMARY KEY (`record_id`),
  KEY `idx_third_recharge_record_dealer` (`dealer_id`,`handing_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方场库充值记录：DAGS接口传入';

-- ----------------------------
-- Table structure for third_refund_record
-- ----------------------------
DROP TABLE IF EXISTS `third_refund_record`;
CREATE TABLE `third_refund_record` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：记录ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `docking_id` bigint(20) unsigned NOT NULL COMMENT '对接网关ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车场ID：为0时表示不能区分场库的费用',
  `plate_no` varchar(10) DEFAULT NULL COMMENT '车牌号码',
  `plate_color` tinyint(4) DEFAULT NULL COMMENT '车牌颜色，0-其他,1-蓝色,2-黄色,3-黑色,4-白色（-1表示没有类似于null，字典不修改）',
  `handing_time` datetime NOT NULL COMMENT '退款办理时间',
  `start_time` datetime DEFAULT NULL COMMENT '退款后有效开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '退款后有效结束时间',
  `refunds_pay` int(11) DEFAULT NULL COMMENT '退款金额：单位分',
  `collector_id` bigint(20) unsigned DEFAULT NULL COMMENT '收费员ID',
  `collector_code` varchar(64) DEFAULT NULL COMMENT '收费员编号',
  `collector_name` varchar(32) DEFAULT NULL COMMENT '收费员名称',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注信息',
  `refunds_type` tinyint(3) unsigned DEFAULT NULL COMMENT '退款类型：1 包期充值；2 账户充值',
  `account` varchar(32) DEFAULT NULL COMMENT '账户：车主',
  PRIMARY KEY (`record_id`),
  KEY `idx_third_refund_record_dealer` (`dealer_id`,`handing_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方场库退款记录';

-- ----------------------------
-- Table structure for toll_collector_info
-- ----------------------------
DROP TABLE IF EXISTS `toll_collector_info`;
CREATE TABLE `toll_collector_info` (
  `collector_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `docking_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '对接ID',
  `park_id` bigint(20) unsigned DEFAULT NULL COMMENT '停车场ID',
  `collector_code` varchar(64) NOT NULL COMMENT '收费员编号',
  `collector_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '收费员名称',
  `id_card` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `phone` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `login_status` tinyint(4) DEFAULT NULL COMMENT '登录状态：0-未登录，1-已登录',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已经删除：0 否； 1 是',
  `effective_date` date DEFAULT NULL COMMENT '生效日期',
  `expire_date` date DEFAULT NULL COMMENT '失效日期',
  `login_account` varchar(64) NOT NULL DEFAULT '-1' COMMENT '登录账号',
  `login_password` varchar(256) NOT NULL DEFAULT '-1' COMMENT '登录密码',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户状态：0-正常，1-冻结，2-注销',
  `type` tinyint(4) NOT NULL DEFAULT '3' COMMENT '工作员类别 1 收费员(路边) 2 巡检员 3 收费员(场库)',
  `login_error_num` int(11) DEFAULT NULL COMMENT '登录错误次数',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次错误验证时间',
  PRIMARY KEY (`collector_id`),
  KEY `idx_toll_collector_info_dealer` (`dealer_id`,`collector_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收费员表';

-- ----------------------------
-- Table structure for transcode_info
-- ----------------------------
DROP TABLE IF EXISTS `transcode_info`;
CREATE TABLE `transcode_info` (
  `transcode_info_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `camera_id` bigint(20) unsigned DEFAULT NULL COMMENT '通道ID',
  `mag_server_id` bigint(20) unsigned DEFAULT NULL COMMENT '关联移动服务器',
  `is_transcode` int(11) DEFAULT NULL COMMENT '是否要转码:0-不转 1-转码',
  `in_stream_type` int(11) DEFAULT NULL COMMENT '输入码流类型',
  `out_encode_type` int(11) DEFAULT NULL COMMENT '编码类型',
  `out_bit_rate` int(11) DEFAULT NULL COMMENT '输出码率类型',
  `out_frame_rate` int(11) DEFAULT NULL COMMENT '输出帧率类型',
  `out_dpi` int(11) DEFAULT NULL COMMENT '输出分辨率：1-QCIF；2-QVGA；3-CIF',
  PRIMARY KEY (`transcode_info_id`),
  KEY `relationship_95_fk` (`camera_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='转码信息';

-- ----------------------------
-- Table structure for upo_city_device_info
-- ----------------------------
DROP TABLE IF EXISTS `upo_city_device_info`;
CREATE TABLE `upo_city_device_info` (
  `device_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `device_name` varchar(64) DEFAULT NULL COMMENT '设备名称',
  `device_code` varchar(64) DEFAULT NULL COMMENT '设备编号',
  `ipaddr` varchar(64) DEFAULT NULL COMMENT '设备ip',
  `port` int(11) DEFAULT NULL COMMENT '设备端口',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `manufacturer` varchar(64) DEFAULT NULL COMMENT '厂商信息',
  `seria_num` varchar(64) DEFAULT NULL COMMENT '序列号',
  `dealer_id` bigint(20) unsigned DEFAULT NULL COMMENT '所属商户id',
  `access_mode` smallint(6) DEFAULT NULL COMMENT '接入方式，0-ip方式，1-PDAS方式',
  `device_type` smallint(6) DEFAULT NULL COMMENT '设备类型：1-led屏',
  `subtype` smallint(6) DEFAULT NULL COMMENT '某个设备的具体类型',
  `version` varchar(64) DEFAULT NULL COMMENT '版本信息',
  `control_unit_id` bigint(20) unsigned NOT NULL COMMENT '所属的区域ID',
  `macaddr` varchar(32) DEFAULT NULL COMMENT '设备的mac地址',
  `longitude` decimal(9,6) DEFAULT NULL COMMENT '设备经度',
  `latitude` decimal(9,6) DEFAULT NULL COMMENT '设备纬度',
  `sim_card` varchar(64) DEFAULT NULL COMMENT 'sim卡',
  `last_heart_beat_time` datetime DEFAULT NULL COMMENT '最后心跳时间',
  `is_delete` smallint(6) DEFAULT '0' COMMENT '0 未删除  1 已删除',
  PRIMARY KEY (`device_id`),
  UNIQUE KEY `idx_city_device_info_name` (`device_name`),
  KEY `idx_city_device_info` (`control_unit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='城市停车运营设备表(LED)';

-- ----------------------------
-- Table structure for upo_device_abnormal_record
-- ----------------------------
DROP TABLE IF EXISTS `upo_device_abnormal_record`;
CREATE TABLE `upo_device_abnormal_record` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `control_unit_id` bigint(20) unsigned NOT NULL COMMENT '区域ID',
  `dealer_code` varchar(64) DEFAULT NULL COMMENT '商户编号',
  `dealer_name` varchar(64) DEFAULT NULL COMMENT '商户名称',
  `park_code` varchar(64) DEFAULT NULL COMMENT '停车点编号',
  `park_name` varchar(64) DEFAULT NULL COMMENT '停车点名称',
  `device_type` smallint(6) DEFAULT NULL COMMENT '设备类型，0-地磁检测器，1-地磁管理器',
  `device_code` varchar(64) DEFAULT NULL COMMENT '设备编号',
  `last_heart_beat_time` datetime DEFAULT NULL COMMENT '最后心跳时间',
  `change_reason` smallint(6) DEFAULT NULL COMMENT '记录异常原因，0-发生异常，1-恢复正常',
  `record_time` datetime DEFAULT NULL COMMENT '记录时间',
  PRIMARY KEY (`record_id`),
  KEY `inx_device_ab_record_con_id` (`control_unit_id`),
  KEY `inx_device_ab_record_time` (`record_time`),
  KEY `inx_device_ab_record_dealer_code` (`dealer_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备异常信息记录表';

-- ----------------------------
-- Table structure for upo_device_parking_map
-- ----------------------------
DROP TABLE IF EXISTS `upo_device_parking_map`;
CREATE TABLE `upo_device_parking_map` (
  `map_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `device_id` bigint(20) unsigned NOT NULL COMMENT '设备ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `berth_num` int(11) DEFAULT NULL COMMENT '关联泊位的数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `operater_id` bigint(20) unsigned NOT NULL COMMENT '创建人ID',
  PRIMARY KEY (`map_id`),
  KEY `inx_device_park_map_device_id` (`device_id`),
  KEY `inx_device_park_map_park_id` (`park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='追风关联停车场';

-- ----------------------------
-- Table structure for upo_geomagnetic_manager
-- ----------------------------
DROP TABLE IF EXISTS `upo_geomagnetic_manager`;
CREATE TABLE `upo_geomagnetic_manager` (
  `manager_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `manager_code` varchar(64) DEFAULT NULL COMMENT '地磁管理器编号',
  `ipaddr` varchar(64) DEFAULT NULL COMMENT 'ip地址',
  `port` int(11) DEFAULT NULL,
  `control_unit_id` bigint(20) unsigned NOT NULL COMMENT '所属的区域ID',
  `is_register` smallint(6) DEFAULT '0' COMMENT '是否已注册，0-未注册，1-已注册',
  `last_heart_beat_time` datetime DEFAULT NULL COMMENT '最后心跳时间',
  `inteval_time` smallint(6) DEFAULT NULL COMMENT '心跳间隔时间(秒)',
  `install_position` varchar(256) DEFAULT NULL COMMENT '安装位置',
  `sim_no` varchar(32) DEFAULT NULL COMMENT 'SIM卡号',
  `business_type` smallint(6) DEFAULT NULL COMMENT '地磁管理器类型(厂商) 数据字',
  `longitude` decimal(9,6) DEFAULT NULL COMMENT '地磁管理器经度',
  `latitude` decimal(9,6) DEFAULT NULL COMMENT '地磁管理器纬度',
  `version` varchar(64) DEFAULT NULL COMMENT '版本',
  `is_delete` smallint(6) DEFAULT '0' COMMENT '0 未删除 1 已删除',
  `access_mode` smallint(6) DEFAULT '1' COMMENT '接入方式，0-ip方式，1-PDAS方式',
  PRIMARY KEY (`manager_id`),
  KEY `inx_geo_manager_contro_id` (`control_unit_id`),
  KEY `inx_geo_manager_code` (`manager_code`),
  KEY `inx_geo_manager_business_type` (`business_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地磁管理器表';

-- ----------------------------
-- Table structure for upo_geomagnetic_detector
-- ----------------------------
DROP TABLE IF EXISTS `upo_geomagnetic_detector`;
CREATE TABLE `upo_geomagnetic_detector` (
  `detector_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `detector_code` varchar(64) DEFAULT NULL COMMENT '地磁检测器编号',
  `manager_id` bigint(20) unsigned DEFAULT NULL COMMENT '关联地磁管理器id',
  `control_unit_id` bigint(20) unsigned NOT NULL COMMENT '所属的区域ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车场ID',
  `electricity` int(11) DEFAULT NULL COMMENT '电量',
  `status` smallint(6) DEFAULT NULL COMMENT '地磁检测器当前状态，-1：未知，0：正常，4：失联',
  `business_type` smallint(6) DEFAULT NULL COMMENT '地磁检测器类型(厂商)，厂商数据数据字典配置',
  `is_delete` smallint(6) DEFAULT '0' COMMENT '0 未删除  1 删除',
  PRIMARY KEY (`detector_id`),
  KEY `inx_gd_detector_code` (`detector_code`),
  KEY `inx_gd_manager_id` (`manager_id`),
  KEY `inx_gd_parking_id` (`park_id`),
  KEY `inx_gd_business_type` (`business_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地磁检测器表';

-- ----------------------------
-- Table structure for upo_geomagnetic_error_report
-- ----------------------------
DROP TABLE IF EXISTS `upo_geomagnetic_error_report`;
CREATE TABLE `upo_geomagnetic_error_report` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `park_code` varchar(64) NOT NULL COMMENT '停车点编号',
  `berth_code` varchar(64) DEFAULT NULL COMMENT '泊位编号',
  `berth_number` varchar(64) DEFAULT NULL COMMENT '泊位号',
  `detector_code` varchar(64) DEFAULT NULL COMMENT '地磁检测器编号',
  `collector_code` varchar(64) DEFAULT NULL COMMENT '收费员编号',
  `terminal_code` varchar(64) DEFAULT NULL COMMENT '手持终端编号',
  `report_time` datetime DEFAULT NULL COMMENT '误报处理时间',
  `report_reason` smallint(6) DEFAULT NULL COMMENT '误报原因，0-地磁误报进车（进车时），1-地磁误报出车（出车时），2-连续上报',
  `pic_uuid` varchar(64) DEFAULT NULL COMMENT '图片上传时唯一的UUID',
  `update_time` datetime DEFAULT NULL COMMENT '地磁更新时间',
  PRIMARY KEY (`record_id`),
  KEY `inx_ger_report_time` (`report_time`),
  KEY `inx_ger_parking_code` (`park_code`),
  KEY `inx_ger_berth_code` (`berth_code`),
  KEY `inx_ger_report_reason` (`report_reason`),
  KEY `inx_ger_collector_code` (`collector_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地磁误报处理记录表';


-- ----------------------------
-- Table structure for upo_geomagnetic_record
-- ----------------------------
DROP TABLE IF EXISTS `upo_geomagnetic_record`;
CREATE TABLE `upo_geomagnetic_record` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `park_code` varchar(64) DEFAULT NULL COMMENT '停车点编号',
  `berth_code` varchar(64) DEFAULT NULL COMMENT '泊位编号',
  `update_time` datetime DEFAULT NULL COMMENT '变更时间，地磁检测到车位状态变更上传数据的时间',
  `update_reason` smallint(6) DEFAULT NULL COMMENT '泊位业务变更原因(1-车辆到达, 2-车辆离开)',
  `park_unique_id` varchar(64) DEFAULT NULL COMMENT '停车唯一id',
  PRIMARY KEY (`record_id`),
  KEY `inx_gr_parking_code` (`park_code`),
  KEY `inx_gr_berth_code` (`berth_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地磁检测记录表';

-- ----------------------------
-- Table structure for upo_handheld_terminal
-- ----------------------------
DROP TABLE IF EXISTS `upo_handheld_terminal`;
CREATE TABLE `upo_handheld_terminal` (
  `terminal_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `terminal_code` varchar(64) DEFAULT NULL COMMENT '终端编号',
  `version_id` bigint(20) DEFAULT NULL COMMENT '版本ID',
  `psam_no` varchar(32) DEFAULT NULL COMMENT 'psam卡号',
  `terminal_type` smallint(6) DEFAULT NULL COMMENT 'POS机类型(0-海康, 1-verifone, 2-华立)',
  `serial_no` varchar(32) DEFAULT NULL COMMENT '终端序列号',
  `mac_no` varchar(32) DEFAULT NULL COMMENT 'MAC号',
  `terminal_name` varchar(64) DEFAULT NULL COMMENT '终端名称',
  `on_off_line` smallint(6) DEFAULT NULL COMMENT '终端状态(0-离线, 1-在线)',
  `update_time` datetime DEFAULT NULL COMMENT '状态更新时间',
  `current_user_code` varchar(64) DEFAULT NULL COMMENT '当前登录人员编号',
  `dealer_id` bigint(20) NOT NULL COMMENT '商户id',
  `manufacturer` varchar(64) DEFAULT NULL COMMENT '厂家信息',
  `access_mode` smallint(6) DEFAULT NULL COMMENT '接入方式，0-ip方式，1-GPRS方式',
  `abnormal_sign_off` smallint(6) DEFAULT NULL COMMENT '上次是否有异常签退的停车场，0-无，1-有',
  `abnormal_sign_off_parkingids` varchar(2000) DEFAULT NULL COMMENT '异常签退的停车场编号-批次号串，如：100-500,105-61',
  `current_pdaserver_code` varchar(64) DEFAULT NULL COMMENT '当前连接CPGS服务器编号',
  `last_heart_beat_time` datetime DEFAULT NULL COMMENT '最后心跳时间',
  `lock_status` smallint(6) DEFAULT NULL COMMENT '锁定状态，0-正常，1-锁定',
  PRIMARY KEY (`terminal_id`),
  KEY `inx_ht_terminal_code` (`terminal_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='手持终端表';

-- ----------------------------
-- Table structure for upo_handheld_version
-- ----------------------------
DROP TABLE IF EXISTS `upo_handheld_version`;
CREATE TABLE `upo_handheld_version` (
  `version_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '终端编号',
  `version` text COMMENT '版本文件',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `description` varchar(2000) DEFAULT NULL COMMENT '描述信息',
  `version_code` varchar(64) DEFAULT NULL COMMENT '版本号',
  `version_md5` varchar(32) DEFAULT NULL COMMENT 'md5值',
  `version_name` varchar(32) DEFAULT NULL COMMENT '版本名称,例如1.2.1',
  PRIMARY KEY (`version_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='手持终端版本信息表';

-- ----------------------------
-- Table structure for upo_holiday_config
-- ----------------------------
DROP TABLE IF EXISTS `upo_holiday_config`;
CREATE TABLE `upo_holiday_config` (
  `holiday_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '运营商ID',
  `holiday_time` date DEFAULT NULL COMMENT '日期',
  `holiday_name` varchar(64) DEFAULT NULL COMMENT '名称',
  `holiday_type` smallint(6) DEFAULT NULL COMMENT '节假日类型: 0-调双休日, 1-小长假, 2-长假',
  PRIMARY KEY (`holiday_id`),
  KEY `idx_upo_holiday_config_dealer` (`dealer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='节假日表';

-- ----------------------------
-- Table structure for upo_inspector_record
-- ----------------------------
DROP TABLE IF EXISTS `upo_inspector_record`;
CREATE TABLE `upo_inspector_record` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `worker_name` varchar(64) DEFAULT NULL COMMENT '巡查员名称',
  `worder_code` varchar(64) DEFAULT NULL COMMENT '巡查员编号',
  `inspector_time` datetime DEFAULT NULL COMMENT '巡查时间',
  `park_code` varchar(64) DEFAULT NULL COMMENT '巡查停车点编号',
  `collector_code` varchar(64) DEFAULT NULL COMMENT '巡查停车点当天上班收费员编号',
  `inspector_result` smallint(6) DEFAULT NULL COMMENT '巡查结果，0-正常，1-异常',
  `abnormal_reason` smallint(6) DEFAULT NULL COMMENT '异常原因，0-剩余车位数不正确，1-车牌号码不匹配',
  `description` varchar(2000) DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`record_id`),
  KEY `inx_ir_inspector_time` (`inspector_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='巡查结果记录表';

-- ----------------------------
-- Table structure for upo_parking_sign_record
-- ----------------------------
DROP TABLE IF EXISTS `upo_parking_sign_record`;
CREATE TABLE `upo_parking_sign_record` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `task_id` int(11) DEFAULT NULL COMMENT '批次号',
  `dealer_code` varchar(64) DEFAULT NULL COMMENT '商户编号',
  `terminal_code` varchar(64) DEFAULT NULL COMMENT '手持终端编号',
  `park_code` varchar(64) DEFAULT NULL COMMENT '停车点编号',
  `park_name` varchar(64) DEFAULT NULL COMMENT '停车点名称',
  `sign_type` smallint(6) DEFAULT NULL COMMENT '操作类型：0-签退,1-签到,2-异常签退（未对账）,3-异常签退（已对账）',
  `sign_time` datetime DEFAULT NULL COMMENT '签到/签退时间',
  `collector_name` varchar(64) DEFAULT NULL COMMENT '收费员名称',
  `collector_code` varchar(64) DEFAULT NULL COMMENT '收费员编号',
  `pic_uuid` varchar(64) DEFAULT NULL COMMENT '图片上传时唯一的UUID',
  `sign_status` smallint(6) DEFAULT NULL COMMENT '签到状态，0-正常，1-迟到，2-早退，3-隔天签退',
  `sign_serial_num` varchar(64) DEFAULT NULL COMMENT '签到流水号',
  `worker_type` smallint(6) DEFAULT NULL COMMENT '1 收费员 2 巡查员 3 中心泊位管理员',
  PRIMARY KEY (`record_id`),
  KEY `inx_psr_collector_code` (`collector_code`),
  KEY `inx_psr_sign_time` (`park_code`,`sign_time`,`sign_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车点签到签退记录表';

-- ----------------------------
-- Table structure for upo_led_config_info
-- ----------------------------
DROP TABLE IF EXISTS `upo_led_config_info`;
CREATE TABLE `upo_led_config_info` (
  `config_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `device_id` bigint(20) unsigned NOT NULL COMMENT '设备ID，UPO_CITY_DEVICE_INFO的DEVICE_ID',
  `config_type` smallint(6) DEFAULT NULL COMMENT '配置模式，取值为1~9',
  `led_width` smallint(6) DEFAULT NULL COMMENT '显示屏长度（像素值）',
  `led_height` smallint(6) DEFAULT NULL COMMENT '显示屏宽(高)度（像素值）',
  `show_mod` smallint(6) DEFAULT NULL COMMENT '显示模式：1-立即显示，2-上展，3-下展，4-左展，5-右展，6-上移，7-下移，8-左移，9-右移，10-淡入淡出，11-随机',
  PRIMARY KEY (`config_id`),
  UNIQUE KEY `inx_led_config_device_id` (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='LED显示内容配置表';

-- ----------------------------
-- Table structure for upo_led_module_info
-- ----------------------------
DROP TABLE IF EXISTS `upo_led_module_info`;
CREATE TABLE `upo_led_module_info` (
  `module_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `device_id` bigint(20) unsigned NOT NULL COMMENT '设备ID，UPO_CITY_DEVICE_INFO的DEVICE_ID',
  `module_name` varchar(256) DEFAULT NULL COMMENT 'LED模组名称',
  `order_num` smallint(6) DEFAULT NULL COMMENT '排序号',
  `display` varchar(2000) DEFAULT NULL COMMENT '显示文本',
  `exp` varchar(2000) DEFAULT NULL COMMENT '表达式',
  `font_color` smallint(6) DEFAULT NULL COMMENT '字体颜色：1-红色，2-绿色，3-蓝色，4-黄色，5-紫色，6-青色，7-白色，8-黑色',
  `font_size` smallint(6) DEFAULT NULL COMMENT '字体大小',
  `xpos` smallint(6) DEFAULT NULL COMMENT 'xPos坐标',
  `ypos` smallint(6) DEFAULT NULL COMMENT 'yPos坐标',
  `park_show_len` smallint(6) DEFAULT NULL COMMENT '车位显示长度',
  PRIMARY KEY (`module_id`),
  KEY `inx_led_module_device_id` (`device_id`),
  KEY `inx_led_module_name` (`module_name`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='LED模组表达式表';

-- ----------------------------
-- Table structure for upo_parking_payrule_map
-- ----------------------------
DROP TABLE IF EXISTS `upo_parking_payrule_map`;
CREATE TABLE `upo_parking_payrule_map` (
  `map_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `park_id` bigint(20) NOT NULL COMMENT '停车点id',
  `pay_rule_id` bigint(20) NOT NULL COMMENT '收费规则id',
  PRIMARY KEY (`map_id`),
  KEY `inx_parking_payrule_map_pid` (`park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='停车点与收费规则关联关系表';

-- ----------------------------
-- Table structure for upo_pay_rule
-- ----------------------------
DROP TABLE IF EXISTS `upo_pay_rule`;
CREATE TABLE `upo_pay_rule` (
  `pay_rule_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rule_name` varchar(64) DEFAULT NULL COMMENT '规则名称',
  `rule_type` smallint(6) DEFAULT NULL COMMENT '规则类型，0-不收费，1-按期收费，2-按时收费，3-按次收费，4-分时段收费，5-分时收费，6-日晚分段收费，8-按单位时段收费，9-日夜分时按次收费',
  `vehicle_type` smallint(6) DEFAULT NULL COMMENT '本规则适用的车辆类型，车辆类型：0-其他，1-小型汽车，2-大型汽车',
  `rule_detail` text COMMENT '规则的详细内容，解析后为xml格式的收费规则',
  `parking_level` smallint(6) DEFAULT NULL COMMENT '本规则适用的停车场等级，停车场等级，0-其它，1-一级停车场，2-二级停车场，3-三级停车场',
  `delete_flag` smallint(6) DEFAULT NULL COMMENT '是否删除，0-未删除，1-已删除',
  `holiday_type` smallint(6) DEFAULT NULL COMMENT '本规则适用的假期类型，假期类型: 0-正常工作日(平常双休日), 1-小长假(旅游旺季双休日), 2-长假',
  `effect_state` smallint(6) DEFAULT NULL COMMENT '生效状态，0-生效，1-未生效，2-已失效',
  `on_line_time` datetime DEFAULT NULL COMMENT '上线时间',
  `description` varchar(512) DEFAULT NULL COMMENT '描述信息',
  `invalid_time` datetime DEFAULT NULL COMMENT '失效时间',
  `operate_id` bigint(20) NOT NULL COMMENT '创建人员',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '运营商ID',
  PRIMARY KEY (`pay_rule_id`),
  KEY `idx_upo_pay_rule_dealer` (`dealer_id`,`effect_state`,`parking_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收费规则表';

-- ----------------------------
-- Table structure for upo_pay_rule_check
-- ----------------------------
DROP TABLE IF EXISTS `upo_pay_rule_check`;
CREATE TABLE `upo_pay_rule_check` (
  `check_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rule_name` varchar(64) DEFAULT NULL COMMENT '规则名称',
  `rule_type` smallint(6) DEFAULT NULL COMMENT '规则类型，0-不收费，1-按期收费，2-按时收费，3-按次收费，4-分时段收费，5-分时收费，6-日晚分段收费，8-按单位时段收费，9-日夜分时按次收费',
  `vehicle_type` smallint(6) DEFAULT NULL COMMENT '本规则适用的车辆类型，车辆类型：0-其他，1-小型汽车，2-大型汽车',
  `rule_detail` text COMMENT '规则的详细内容，解析后为xml格式的收费规则',
  `parking_level` smallint(6) DEFAULT NULL COMMENT '本规则适用的停车场等级，停车场等级，0-其它，1-一级停车场，2-二级停车场，3-三级停车场',
  `delete_flag` smallint(6) DEFAULT NULL COMMENT '是否删除，1-未删除，2-已删除',
  `holiday_type` smallint(6) DEFAULT NULL COMMENT '本规则适用的假期类型，假期类型: 0-正常工作日(平常双休日), 1-小长假(旅游旺季双休日), 2-长假',
  `on_line_time` datetime DEFAULT NULL COMMENT '上线时间',
  `description` varchar(512) DEFAULT NULL COMMENT '描述信息',
  `pay_rule_id` bigint(20) NOT NULL COMMENT '原始记录id，对应UPO_PAY_RULE表中的id',
  `submit_check_time` datetime DEFAULT NULL COMMENT '提交审核时间',
  `submit_check_user_id` bigint(20) NOT NULL COMMENT '提交审核人员id，对应USER_INFO中的USER_ID',
  `submit_check_user_name` varchar(64) DEFAULT NULL COMMENT '提交审核人员姓名',
  `operate_type` smallint(6) DEFAULT NULL COMMENT '操作类型，0-新增，1-删除，2-修改',
  `status` smallint(6) DEFAULT NULL COMMENT '当前状态，0-审核中，1-审核通过，2-审核未通过',
  `check_user_id` bigint(20) DEFAULT NULL COMMENT '审核人员id，对应USER_INFO中的USER_ID',
  `check_user_name` varchar(64) DEFAULT NULL COMMENT '审核人员姓名',
  `check_remark` varchar(256) DEFAULT NULL COMMENT '审核意见',
  `check_time` datetime DEFAULT NULL COMMENT '审核时间',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '运营商ID',
  PRIMARY KEY (`check_id`),
  KEY `idx_upo_pay_rule_check_dealer` (`dealer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收费规则审核表';

-- ----------------------------
-- Table structure for upo_special_plate_type
-- ----------------------------
DROP TABLE IF EXISTS `upo_special_plate_type`;
CREATE TABLE `upo_special_plate_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `dealer_id` bigint(20) unsigned DEFAULT NULL COMMENT '商户ID',
  `plate_type` smallint(6) DEFAULT NULL COMMENT '车牌类别:0-无类型，1-92式民用车牌类型，2-警用车牌类型，5-左右军车车牌类型(一行结构)，7-02式个性化车牌类型，8-黄色双行尾牌（货车或公交车尾牌），9-04式新军车类型(两行结构)，10-使馆车牌类型，11-一行结构的新WJ车牌类型，12-两行结构的新WJ车牌类型，13-黄色1225农用车，14-绿色1325农用车，15-黄色1325农用车，16-摩托车尾牌，17-13式新武警总部一行车牌，18-13式新武警总部两行车牌，19-民航车牌类型',
  PRIMARY KEY (`id`),
  KEY `idx_upo_special_plate_type_dealer` (`dealer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='特殊车牌类型配置表';

-- ----------------------------
-- Table structure for upo_uuid_image_map
-- ----------------------------
DROP TABLE IF EXISTS `upo_uuid_image_map`;
CREATE TABLE `upo_uuid_image_map` (
  `map_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `pic_uuid` varchar(64) DEFAULT NULL COMMENT '图片上传时唯一的UUID',
  `image_url` varchar(512) DEFAULT NULL COMMENT '上传图片url, 绝对路径',
  `type` smallint(6) DEFAULT '0' COMMENT '图片类型 0（其他），1为过车图片 , 2报警',
  PRIMARY KEY (`map_id`),
  KEY `inx_upo_image_uuid` (`pic_uuid`),
  KEY `inx_upo_image_type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='UUID跟图片url关联关系表';

-- ----------------------------
-- Table structure for upo_video_record
-- ----------------------------
DROP TABLE IF EXISTS `upo_video_record`;
CREATE TABLE `upo_video_record` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `device_id` bigint(20) unsigned NOT NULL COMMENT '视频检测设备的id，对应DEVICE_INFO中的DEVICE_ID',
  `berth_code` varchar(64) DEFAULT NULL COMMENT '泊位编号，全局唯一',
  `berth_number` varchar(64) DEFAULT NULL COMMENT '泊位号，停车场内唯一',
  `park_code` varchar(64) DEFAULT NULL COMMENT '停车点编号',
  `pass_time` datetime DEFAULT NULL COMMENT '抓拍时间',
  `pic_uuid` varchar(64) DEFAULT NULL COMMENT '图片UUID',
  `update_reason` smallint(6) DEFAULT NULL COMMENT '泊位业务变更原因(1-车辆到达, 2-车辆离开)',
  `update_time` datetime DEFAULT NULL COMMENT '记录写入库时间',
  `plate_image_url` varchar(512) DEFAULT NULL COMMENT '车牌图片URL',
  `plate_no` varchar(16) DEFAULT NULL COMMENT '车牌号码',
  `plate_type` smallint(6) DEFAULT NULL COMMENT '车牌类型',
  `plate_color` smallint(6) DEFAULT NULL COMMENT '车牌颜色',
  `plate_entire_believe` smallint(6) DEFAULT NULL COMMENT '车牌置信度',
  `unid` varchar(32) DEFAULT NULL COMMENT '停车唯一Id，保留字段暂不使用',
  `berth_threshold` int(11) DEFAULT NULL COMMENT '过车(泊位)置信度',
  `plate_threshold` int(11) DEFAULT NULL COMMENT '车牌置信度',
  `image_uuid` varchar(128) DEFAULT NULL COMMENT '过车图片UUID',
  `three_in_one_image_uuid` varchar(128) DEFAULT NULL COMMENT '三合一图片UUID',
  `column_20device_code` varchar(64) DEFAULT NULL COMMENT '设备编号',
  PRIMARY KEY (`record_id`),
  KEY `inx_video_record_device_id` (`device_id`),
  KEY `inx_video_record_berth_code` (`berth_code`),
  KEY `inx_video_record_parking_code` (`park_code`),
  KEY `inx_video_record_update_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='泊位视频检测记录表';



-- ----------------------------
-- Table structure for vehicle_in_park
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_in_park`;
CREATE TABLE `vehicle_in_park` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：记录ID',
  `dealer_id` bigint(20) unsigned NOT NULL COMMENT '商户ID',
  `park_id` bigint(20) unsigned NOT NULL COMMENT '停车点ID',
  `in_unid` varchar(64) NOT NULL COMMENT '入车UNID',
  `pass_time` datetime NOT NULL COMMENT '过车时间',
  `plate_no` varchar(10) NOT NULL COMMENT '车牌号码',
  `plate_color` tinyint(4) NOT NULL COMMENT '车牌颜色，-1没有车牌颜色 0-其他,1-蓝色,2-黄色,3-黑色,4-白色',
  PRIMARY KEY (`record_id`),
  KEY `idx_vehicle_in_park_dealer` (`dealer_id`,`park_id`,`in_unid`),
  KEY `idx_vehicle_in_park_dplate` (`dealer_id`,`plate_no`,`plate_color`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆在库数据：车辆驶入添加，缴费数据上传后删除';


-- ----------------------------
-- Table structure for vehicle_release_info
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_release_info`;
CREATE TABLE `vehicle_release_info` (
  `release_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `termin_code` varchar(32) NOT NULL COMMENT '对接终端/平台编号',
  `park_code` varchar(64) NOT NULL COMMENT '停车场ID',
   `gate_code` varchar(32) NOT NULL COMMENT '出车时出入口ID',
  `lane_no` int(11) NOT NULL COMMENT '车道序号：同一对接终端下唯一',
  `in_time` datetime DEFAULT NULL COMMENT '入场时间',
  `out_time` datetime DEFAULT NULL COMMENT '出场时间',
  `park_time` int(11) DEFAULT NULL COMMENT '停车时长，单位为分钟',
  `plate_no` varchar(10) NOT NULL COMMENT '车牌号码',
  `plate_color` tinyint(4) NOT NULL COMMENT '车牌颜色，-1没有车牌颜色 0-其他,1-蓝色,2-黄色,3-黑色,4-白色',
  `plate_type` tinyint(3) unsigned DEFAULT NULL COMMENT '车牌类别:0-无类型,1-92式民用车,2-警用车,3-上下军车,4-92式武警车,5-左右军车车牌类型(一行结构),7-02式个性化车,8-黄色双行尾牌,9-04式新军车,10-使馆车,11-一行结构的新WJ车,12-两行结构的新WJ车,13-黄色1225农用车,14-绿色1325农用车,15-黄色1325农用车,16-摩托车, 17-13式新武警总部一行车牌，18-13式新武警总部两行车牌，19-民航车牌类型,100-教练车,101-临时行驶车,102-挂车,103-领馆汽车,104-港澳入出车,105-临时入境车',
  `car_type` tinyint(3) unsigned DEFAULT NULL COMMENT '车辆类型，1-小型汽车,2-大型汽车,0-其他',
  `need_pay` int(11) NOT NULL COMMENT '应收金额，单位为分',
  `direction` tinyint(4) NOT NULL COMMENT '车道方向：0-入场车道，1-出场车道，2-出入车道',
  PRIMARY KEY (`release_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆放行记录';

-- ----------------------------
-- Table structure for vrm_record_schedule
-- ----------------------------
DROP TABLE IF EXISTS `vrm_record_schedule`;
CREATE TABLE `vrm_record_schedule` (
  `vrm_record_sched_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '录像计划ID',
  `camera_id` bigint(20) NOT NULL COMMENT '通道ID',
  `sched_templ_id` bigint(20) DEFAULT NULL COMMENT '调度模板ID',
  `server_id` bigint(20) NOT NULL COMMENT '服务器ID',
  `stream_type` int(11) NOT NULL COMMENT '0-主码流 1-子码流',
  `need_record` int(11) NOT NULL COMMENT '是否录像，用来暂时停止录像计划的执行',
  `config_status` int(11) NOT NULL DEFAULT '0' COMMENT '配置结果 0-失败 1-成功',
  `record_location` int(11) NOT NULL DEFAULT '2' COMMENT '录像位置信息：0-NVT；1-设备；2-NVR；3-BN',
  `nvr_server_id` bigint(20) DEFAULT NULL COMMENT 'NVR服务器ID',
  `group_no` int(11) DEFAULT NULL COMMENT '磁盘分组序号，用来在NVR录像时，设定保存到哪一个磁盘分组中',
  `redundanacy_type` int(11) DEFAULT '0' COMMENT '冗余录像类型：0表示不冗余；1表示冗余',
  `audio_type` int(11) DEFAULT '0' COMMENT '录音频类型：0-不录音频；1-录音频',
  `store_time` int(11) DEFAULT NULL COMMENT '存储时间，单位：天',
  `store_space` int(11) DEFAULT '0' COMMENT '存储空间，单位：MB',
  `post_record_time` int(11) NOT NULL DEFAULT '0' COMMENT '结束时延长录象时间，单位秒',
  `pre_record_time` int(11) NOT NULL DEFAULT '0' COMMENT '预录时间，单位秒',
  `record_overdue_time` int(11) DEFAULT NULL COMMENT '录像过期时间',
  `operator_id` bigint(20) NOT NULL COMMENT '操作员ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `envr_server_id` bigint(20) DEFAULT NULL COMMENT 'ENVR_SERVER_ID',
  `cloud_server_id` int(11) DEFAULT NULL COMMENT '云存储服务器ID',
  `camera_indexcode` varchar(64) DEFAULT NULL COMMENT '监控点编号',
  PRIMARY KEY (`vrm_record_sched_id`),
  KEY `reference_20_fk` (`server_id`),
  KEY `reference_21_fk` (`camera_id`),
  KEY `reference_37_fk` (`sched_templ_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通道录像计划表';

-- ----------------------------
-- Table structure for withdrawal_apply
-- ----------------------------
DROP TABLE IF EXISTS `withdrawal_apply`;
CREATE TABLE `withdrawal_apply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `account` varchar(255) DEFAULT NULL COMMENT '提现账号',
  `real_name` varchar(255) DEFAULT NULL COMMENT '实名',
  `amount` int(11) NOT NULL COMMENT '提现金额',
  `user_id` bigint(20) NOT NULL COMMENT '申请人id',
  `create_time` datetime NOT NULL COMMENT '申请时间',
  `status` int(11) NOT NULL COMMENT '处理状态 0未处理 1已处理',
  `record_id` bigint(20) NOT NULL COMMENT '交易记录表中record_id',
  `error_msg` varchar(255) DEFAULT NULL COMMENT '转账失败错误信息',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_person` bigint(20) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提现申请表';

-- ----------------------------
-- Table structure for withdrawal_refund_record
-- ----------------------------
DROP TABLE IF EXISTS `withdrawal_refund_record`;
CREATE TABLE `withdrawal_refund_record` (
  `record_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `bill_no` varchar(64) DEFAULT NULL COMMENT '充值订单号',
  `refunded_money` int(11) unsigned DEFAULT NULL COMMENT '已退款金额',
  `refund_bill_no` varchar(64) DEFAULT NULL COMMENT '退款订单号',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '退款人',
  `refund_time` datetime DEFAULT NULL COMMENT '退款时间',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提现退款记录表';

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `first_name` char(20) DEFAULT NULL,
  `last_name` char(20) DEFAULT NULL,
  `sex` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Function structure for f_lat_lng_distance
-- ----------------------------
DROP FUNCTION IF EXISTS `f_lat_lng_distance`;
DELIMITER ;;

;;
DELIMITER ;
