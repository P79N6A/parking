/*
Navicat MySQL Data Transfer

Source Server         : mycat@server242
Source Server Version : 50629
Source Host           : 192.168.0.242:8066
Source Database       : lts

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2018-06-22 14:01:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for lts_admin_job_client_monitor_data
-- ----------------------------
DROP TABLE IF EXISTS `lts_admin_job_client_monitor_data`;
CREATE TABLE `lts_admin_job_client_monitor_data` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_created` bigint(20) DEFAULT NULL,
  `node_group` varchar(64) DEFAULT NULL,
  `identity` varchar(64) DEFAULT NULL,
  `submit_success_num` bigint(20) DEFAULT NULL,
  `submit_failed_num` bigint(11) DEFAULT NULL,
  `fail_store_num` bigint(20) DEFAULT NULL,
  `submit_fail_store_num` bigint(20) DEFAULT NULL,
  `handle_feedback_num` bigint(20) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_timestamp` (`timestamp`),
  KEY `idx_identity` (`identity`),
  KEY `idx_node_group` (`node_group`)
) ENGINE=InnoDB AUTO_INCREMENT=42436 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lts_admin_job_tracker_monitor_data
-- ----------------------------
DROP TABLE IF EXISTS `lts_admin_job_tracker_monitor_data`;
CREATE TABLE `lts_admin_job_tracker_monitor_data` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_created` bigint(20) DEFAULT NULL,
  `identity` varchar(64) DEFAULT NULL,
  `receive_job_num` bigint(20) DEFAULT NULL,
  `push_job_num` bigint(20) DEFAULT NULL,
  `exe_success_num` bigint(20) DEFAULT NULL,
  `exe_failed_num` bigint(11) DEFAULT NULL,
  `exe_later_num` bigint(20) DEFAULT NULL,
  `exe_exception_num` bigint(20) DEFAULT NULL,
  `fix_executing_job_num` bigint(20) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_timestamp` (`timestamp`),
  KEY `idx_identity` (`identity`)
) ENGINE=InnoDB AUTO_INCREMENT=205570 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lts_admin_jvm_gc
-- ----------------------------
DROP TABLE IF EXISTS `lts_admin_jvm_gc`;
CREATE TABLE `lts_admin_jvm_gc` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_created` bigint(20) DEFAULT NULL,
  `identity` varchar(64) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `node_type` varchar(32) DEFAULT NULL,
  `node_group` varchar(64) DEFAULT NULL,
  `young_gc_collection_count` bigint(20) DEFAULT NULL,
  `young_gc_collection_time` bigint(20) DEFAULT NULL,
  `full_gc_collection_count` bigint(20) DEFAULT NULL,
  `full_gc_collection_time` bigint(20) DEFAULT NULL,
  `span_young_gc_collection_count` bigint(20) DEFAULT NULL,
  `span_young_gc_collection_time` bigint(20) DEFAULT NULL,
  `span_full_gc_collection_count` bigint(20) DEFAULT NULL,
  `span_full_gc_collection_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_identity` (`identity`),
  KEY `idx_timestamp` (`timestamp`)
) ENGINE=InnoDB AUTO_INCREMENT=264762 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lts_admin_jvm_memory
-- ----------------------------
DROP TABLE IF EXISTS `lts_admin_jvm_memory`;
CREATE TABLE `lts_admin_jvm_memory` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_created` bigint(20) DEFAULT NULL,
  `identity` varchar(64) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `node_type` varchar(32) DEFAULT NULL,
  `node_group` varchar(64) DEFAULT NULL,
  `heap_memory_committed` bigint(20) DEFAULT NULL,
  `heap_memory_init` bigint(20) DEFAULT NULL,
  `heap_memory_max` bigint(20) DEFAULT NULL,
  `heap_memory_used` bigint(20) DEFAULT NULL,
  `non_heap_memory_committed` bigint(20) DEFAULT NULL,
  `non_heap_memory_init` bigint(20) DEFAULT NULL,
  `non_heap_memory_max` bigint(20) DEFAULT NULL,
  `non_heap_memory_used` bigint(20) DEFAULT NULL,
  `perm_gen_committed` bigint(20) DEFAULT NULL,
  `perm_gen_init` bigint(20) DEFAULT NULL,
  `perm_gen_max` bigint(20) DEFAULT NULL,
  `perm_gen_used` bigint(20) DEFAULT NULL,
  `old_gen_committed` bigint(20) DEFAULT NULL,
  `old_gen_init` bigint(20) DEFAULT NULL,
  `old_gen_max` bigint(20) DEFAULT NULL,
  `old_gen_used` bigint(20) DEFAULT NULL,
  `eden_space_committed` bigint(20) DEFAULT NULL,
  `eden_space_init` bigint(20) DEFAULT NULL,
  `eden_space_max` bigint(20) DEFAULT NULL,
  `eden_space_used` bigint(20) DEFAULT NULL,
  `survivor_committed` bigint(20) DEFAULT NULL,
  `survivor_init` bigint(20) DEFAULT NULL,
  `survivor_max` bigint(20) DEFAULT NULL,
  `survivor_used` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_identity` (`identity`),
  KEY `idx_timestamp` (`timestamp`)
) ENGINE=InnoDB AUTO_INCREMENT=264762 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lts_admin_jvm_thread
-- ----------------------------
DROP TABLE IF EXISTS `lts_admin_jvm_thread`;
CREATE TABLE `lts_admin_jvm_thread` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_created` bigint(20) DEFAULT NULL,
  `identity` varchar(64) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `node_type` varchar(32) DEFAULT NULL,
  `node_group` varchar(64) DEFAULT NULL,
  `daemon_thread_count` int(11) DEFAULT NULL,
  `thread_count` int(11) DEFAULT NULL,
  `total_started_thread_count` bigint(20) DEFAULT NULL,
  `dead_locked_thread_count` int(11) DEFAULT NULL,
  `process_cpu_time_rate` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_identity` (`identity`),
  KEY `idx_timestamp` (`timestamp`)
) ENGINE=InnoDB AUTO_INCREMENT=264762 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lts_admin_node_onoffline_log
-- ----------------------------
DROP TABLE IF EXISTS `lts_admin_node_onoffline_log`;
CREATE TABLE `lts_admin_node_onoffline_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `log_time` timestamp NULL DEFAULT NULL,
  `event` varchar(32) DEFAULT NULL,
  `node_type` varchar(16) DEFAULT NULL,
  `cluster_name` varchar(64) DEFAULT NULL,
  `ip` varchar(16) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `host_name` varchar(64) DEFAULT NULL,
  `group` varchar(64) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `threads` int(11) DEFAULT NULL,
  `identity` varchar(64) DEFAULT NULL,
  `http_cmd_port` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_log_time` (`log_time`),
  KEY `idx_event` (`event`),
  KEY `idx_identity` (`identity`),
  KEY `idx_group` (`group`)
) ENGINE=InnoDB AUTO_INCREMENT=1689 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lts_admin_task_tracker_monitor_data
-- ----------------------------
DROP TABLE IF EXISTS `lts_admin_task_tracker_monitor_data`;
CREATE TABLE `lts_admin_task_tracker_monitor_data` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_created` bigint(20) DEFAULT NULL,
  `node_group` varchar(64) DEFAULT NULL,
  `identity` varchar(64) DEFAULT NULL,
  `exe_success_num` bigint(20) DEFAULT NULL,
  `exe_failed_num` bigint(11) DEFAULT NULL,
  `exe_later_num` bigint(20) DEFAULT NULL,
  `exe_exception_num` bigint(20) DEFAULT NULL,
  `total_running_time` bigint(20) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_timestamp` (`timestamp`),
  KEY `idx_identity` (`identity`),
  KEY `idx_node_group` (`node_group`)
) ENGINE=InnoDB AUTO_INCREMENT=16735 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lts_cron_job_queue
-- ----------------------------
DROP TABLE IF EXISTS `lts_cron_job_queue`;
CREATE TABLE `lts_cron_job_queue` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID,??????',
  `job_id` varchar(32) DEFAULT NULL COMMENT '??ID,?????',
  `job_type` varchar(32) DEFAULT NULL COMMENT '????',
  `priority` int(11) DEFAULT NULL COMMENT '???,(????,?????)',
  `retry_times` int(11) DEFAULT '0' COMMENT '????',
  `max_retry_times` int(11) DEFAULT '0' COMMENT '??????',
  `rely_on_prev_cycle` tinyint(4) DEFAULT NULL COMMENT '???????????',
  `task_id` varchar(64) DEFAULT NULL COMMENT '??ID,?????????ID',
  `real_task_id` varchar(64) DEFAULT NULL COMMENT '??ID,?????????ID',
  `gmt_created` bigint(20) DEFAULT NULL COMMENT '????',
  `gmt_modified` bigint(11) DEFAULT NULL COMMENT '????',
  `submit_node_group` varchar(64) DEFAULT NULL COMMENT '?????,?????????',
  `task_tracker_node_group` varchar(64) DEFAULT NULL COMMENT '?????,??job?????',
  `ext_params` text COMMENT '???? JSON',
  `internal_ext_params` text COMMENT '?????? JSON',
  `is_running` tinyint(1) DEFAULT NULL COMMENT '??????',
  `task_tracker_identity` varchar(64) DEFAULT NULL COMMENT 'taskTrackerId,???taskTracker?????',
  `need_feedback` tinyint(4) DEFAULT NULL COMMENT '?????,??????????',
  `cron_expression` varchar(128) DEFAULT NULL COMMENT 'Cron???,??????? (? quartz ?????)',
  `trigger_time` bigint(20) DEFAULT NULL COMMENT '???????',
  `repeat_count` int(11) DEFAULT '0' COMMENT '????',
  `repeated_count` int(11) DEFAULT '0' COMMENT '???????',
  `repeat_interval` bigint(20) DEFAULT '0' COMMENT '????',
  `last_generate_trigger_time` bigint(20) DEFAULT '0' COMMENT '?????triggerTime??',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_job_id` (`job_id`),
  UNIQUE KEY `idx_taskId_taskTrackerNodeGroup` (`task_id`,`task_tracker_node_group`),
  KEY `idx_realTaskId_taskTrackerNodeGroup` (`real_task_id`,`task_tracker_node_group`),
  KEY `idx_relyOnPrevCycle_lgtt` (`rely_on_prev_cycle`,`last_generate_trigger_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1778 DEFAULT CHARSET=utf8 COMMENT='Cron??';

-- ----------------------------
-- Table structure for lts_executable_job_queue_sms_send
-- ----------------------------
DROP TABLE IF EXISTS `lts_executable_job_queue_sms_send`;
CREATE TABLE `lts_executable_job_queue_sms_send` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID,??????',
  `job_id` varchar(32) DEFAULT NULL COMMENT '??ID,?????',
  `job_type` varchar(32) DEFAULT NULL COMMENT '????',
  `priority` int(11) DEFAULT NULL COMMENT '???,(????,?????)',
  `retry_times` int(11) DEFAULT '0' COMMENT '????',
  `max_retry_times` int(11) DEFAULT '0' COMMENT '??????',
  `rely_on_prev_cycle` tinyint(4) DEFAULT NULL COMMENT '???????????',
  `task_id` varchar(64) DEFAULT NULL COMMENT '??ID,?????????ID',
  `real_task_id` varchar(64) DEFAULT NULL COMMENT '??ID,?????????ID',
  `gmt_created` bigint(20) DEFAULT NULL COMMENT '????',
  `gmt_modified` bigint(11) DEFAULT NULL COMMENT '????',
  `submit_node_group` varchar(64) DEFAULT NULL COMMENT '?????,?????????',
  `task_tracker_node_group` varchar(64) DEFAULT NULL COMMENT '?????,??job?????',
  `ext_params` text COMMENT '???? JSON',
  `internal_ext_params` text COMMENT '?????? JSON',
  `is_running` tinyint(1) DEFAULT NULL COMMENT '??????',
  `task_tracker_identity` varchar(64) DEFAULT NULL COMMENT 'taskTrackerId,???taskTracker?????',
  `need_feedback` tinyint(4) DEFAULT NULL COMMENT '?????,??????????',
  `cron_expression` varchar(128) DEFAULT NULL COMMENT 'Cron???,??????? (? quartz ?????)',
  `trigger_time` bigint(20) DEFAULT NULL COMMENT '???????',
  `repeat_count` int(11) DEFAULT '0' COMMENT '????',
  `repeated_count` int(11) DEFAULT '0' COMMENT '???????',
  `repeat_interval` bigint(20) DEFAULT '0' COMMENT '????',
  `last_generate_trigger_time` bigint(20) DEFAULT '0' COMMENT '?????triggerTime??',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_job_id` (`job_id`),
  UNIQUE KEY `idx_taskId_taskTrackerNodeGroup` (`task_id`,`task_tracker_node_group`),
  KEY `idx_taskTrackerIdentity` (`task_tracker_identity`),
  KEY `idx_job_type` (`job_type`),
  KEY `idx_realTaskId_taskTrackerNodeGroup` (`real_task_id`,`task_tracker_node_group`),
  KEY `idx_triggerTime_priority_gmtCreated` (`trigger_time`,`priority`,`gmt_created`),
  KEY `idx_isRunning` (`is_running`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='??????';

-- ----------------------------
-- Table structure for lts_executable_job_queue_test_trade_tasktracker
-- ----------------------------
DROP TABLE IF EXISTS `lts_executable_job_queue_test_trade_tasktracker`;
CREATE TABLE `lts_executable_job_queue_test_trade_tasktracker` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID,??????',
  `job_id` varchar(32) DEFAULT NULL COMMENT '??ID,?????',
  `job_type` varchar(32) DEFAULT NULL COMMENT '????',
  `priority` int(11) DEFAULT NULL COMMENT '???,(????,?????)',
  `retry_times` int(11) DEFAULT '0' COMMENT '????',
  `max_retry_times` int(11) DEFAULT '0' COMMENT '??????',
  `rely_on_prev_cycle` tinyint(4) DEFAULT NULL COMMENT '???????????',
  `task_id` varchar(64) DEFAULT NULL COMMENT '??ID,?????????ID',
  `real_task_id` varchar(64) DEFAULT NULL COMMENT '??ID,?????????ID',
  `gmt_created` bigint(20) DEFAULT NULL COMMENT '????',
  `gmt_modified` bigint(11) DEFAULT NULL COMMENT '????',
  `submit_node_group` varchar(64) DEFAULT NULL COMMENT '?????,?????????',
  `task_tracker_node_group` varchar(64) DEFAULT NULL COMMENT '?????,??job?????',
  `ext_params` text COMMENT '???? JSON',
  `internal_ext_params` text COMMENT '?????? JSON',
  `is_running` tinyint(1) DEFAULT NULL COMMENT '??????',
  `task_tracker_identity` varchar(64) DEFAULT NULL COMMENT 'taskTrackerId,???taskTracker?????',
  `need_feedback` tinyint(4) DEFAULT NULL COMMENT '?????,??????????',
  `cron_expression` varchar(128) DEFAULT NULL COMMENT 'Cron???,??????? (? quartz ?????)',
  `trigger_time` bigint(20) DEFAULT NULL COMMENT '???????',
  `repeat_count` int(11) DEFAULT '0' COMMENT '????',
  `repeated_count` int(11) DEFAULT '0' COMMENT '???????',
  `repeat_interval` bigint(20) DEFAULT '0' COMMENT '????',
  `last_generate_trigger_time` bigint(20) DEFAULT '0' COMMENT '?????triggerTime??',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_job_id` (`job_id`),
  UNIQUE KEY `idx_taskId_taskTrackerNodeGroup` (`task_id`,`task_tracker_node_group`),
  KEY `idx_taskTrackerIdentity` (`task_tracker_identity`),
  KEY `idx_job_type` (`job_type`),
  KEY `idx_realTaskId_taskTrackerNodeGroup` (`real_task_id`,`task_tracker_node_group`),
  KEY `idx_triggerTime_priority_gmtCreated` (`trigger_time`,`priority`,`gmt_created`),
  KEY `idx_isRunning` (`is_running`)
) ENGINE=InnoDB AUTO_INCREMENT=447 DEFAULT CHARSET=utf8 COMMENT='??????';

-- ----------------------------
-- Table structure for lts_executing_job_queue
-- ----------------------------
DROP TABLE IF EXISTS `lts_executing_job_queue`;
CREATE TABLE `lts_executing_job_queue` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID,??????',
  `job_id` varchar(32) DEFAULT NULL COMMENT '??ID,?????',
  `job_type` varchar(32) DEFAULT NULL COMMENT '????',
  `priority` int(11) DEFAULT NULL COMMENT '???,(????,?????)',
  `retry_times` int(11) DEFAULT '0' COMMENT '????',
  `max_retry_times` int(11) DEFAULT '0' COMMENT '??????',
  `rely_on_prev_cycle` tinyint(4) DEFAULT NULL COMMENT '???????????',
  `task_id` varchar(64) DEFAULT NULL COMMENT '??ID,?????????ID',
  `real_task_id` varchar(64) DEFAULT NULL COMMENT '??ID,?????????ID',
  `gmt_created` bigint(20) DEFAULT NULL COMMENT '????',
  `gmt_modified` bigint(11) DEFAULT NULL COMMENT '????',
  `submit_node_group` varchar(64) DEFAULT NULL COMMENT '?????,?????????',
  `task_tracker_node_group` varchar(64) DEFAULT NULL COMMENT '?????,??job?????',
  `ext_params` text COMMENT '???? JSON',
  `internal_ext_params` text COMMENT '?????? JSON',
  `is_running` tinyint(1) DEFAULT NULL COMMENT '??????',
  `task_tracker_identity` varchar(64) DEFAULT NULL COMMENT 'taskTrackerId,???taskTracker?????',
  `need_feedback` tinyint(4) DEFAULT NULL COMMENT '?????,??????????',
  `cron_expression` varchar(128) DEFAULT NULL COMMENT 'Cron???,??????? (? quartz ?????)',
  `trigger_time` bigint(20) DEFAULT NULL COMMENT '???????',
  `repeat_count` int(11) DEFAULT '0' COMMENT '????',
  `repeated_count` int(11) DEFAULT '0' COMMENT '???????',
  `repeat_interval` bigint(20) DEFAULT '0' COMMENT '????',
  `last_generate_trigger_time` bigint(20) DEFAULT '0' COMMENT '?????triggerTime??',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_job_id` (`job_id`),
  UNIQUE KEY `idx_taskId_taskTrackerNodeGroup` (`task_id`,`task_tracker_node_group`),
  KEY `idx_job_type` (`job_type`),
  KEY `idx_realTaskId_taskTrackerNodeGroup` (`real_task_id`,`task_tracker_node_group`),
  KEY `idx_taskTrackerIdentity` (`task_tracker_identity`),
  KEY `idx_gmtCreated` (`gmt_created`)
) ENGINE=InnoDB AUTO_INCREMENT=8279 DEFAULT CHARSET=utf8 COMMENT='?????';

-- ----------------------------
-- Table structure for lts_feedback_job_queue_test_jobclient
-- ----------------------------
DROP TABLE IF EXISTS `lts_feedback_job_queue_test_jobclient`;
CREATE TABLE `lts_feedback_job_queue_test_jobclient` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `gmt_created` bigint(11) DEFAULT NULL COMMENT '????',
  `job_result` text COMMENT '??????,JSON',
  PRIMARY KEY (`id`),
  KEY `idex_gmt_created` (`gmt_created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='??????';

-- ----------------------------
-- Table structure for lts_fjq_zhuyi_parking_job_client
-- ----------------------------
DROP TABLE IF EXISTS `lts_fjq_zhuyi_parking_job_client`;
CREATE TABLE `lts_fjq_zhuyi_parking_job_client` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `gmt_created` bigint(11) DEFAULT NULL COMMENT '创建时间',
  `job_result` text COMMENT '任务执行结果,JSON',
  PRIMARY KEY (`id`),
  KEY `idex_gmt_created` (`gmt_created`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='作业反馈结果';

-- ----------------------------
-- Table structure for lts_job_log_po
-- ----------------------------
DROP TABLE IF EXISTS `lts_job_log_po`;
CREATE TABLE `lts_job_log_po` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_created` bigint(20) DEFAULT NULL COMMENT '??????',
  `log_time` bigint(20) DEFAULT NULL COMMENT '??????',
  `log_type` varchar(32) DEFAULT NULL COMMENT '????',
  `success` tinyint(11) DEFAULT NULL COMMENT '????',
  `msg` text COMMENT '??',
  `code` varchar(32) DEFAULT NULL COMMENT '????',
  `job_type` varchar(32) DEFAULT NULL COMMENT '????',
  `task_tracker_identity` varchar(64) DEFAULT NULL COMMENT '????????',
  `level` varchar(32) DEFAULT NULL COMMENT '??????',
  `task_id` varchar(64) DEFAULT NULL COMMENT '???ID',
  `real_task_id` varchar(64) DEFAULT NULL COMMENT '???ID',
  `job_id` varchar(64) DEFAULT '' COMMENT '?????ID',
  `priority` int(11) DEFAULT NULL COMMENT '???',
  `submit_node_group` varchar(64) DEFAULT NULL COMMENT '?????',
  `task_tracker_node_group` varchar(64) DEFAULT NULL COMMENT '?????',
  `ext_params` text COMMENT '????',
  `internal_ext_params` text COMMENT '?????? JSON',
  `need_feedback` tinyint(4) DEFAULT NULL COMMENT '??????',
  `cron_expression` varchar(128) DEFAULT NULL COMMENT 'cron???',
  `trigger_time` bigint(20) DEFAULT NULL COMMENT '????',
  `retry_times` int(11) DEFAULT NULL COMMENT '????',
  `max_retry_times` int(11) DEFAULT '0' COMMENT '??????',
  `rely_on_prev_cycle` tinyint(4) DEFAULT NULL COMMENT '???????????',
  `repeat_count` int(11) DEFAULT '0' COMMENT '????',
  `repeated_count` int(11) DEFAULT '0' COMMENT '???????',
  `repeat_interval` bigint(20) DEFAULT '0' COMMENT '????',
  PRIMARY KEY (`id`),
  KEY `log_time` (`log_time`),
  KEY `task_id_task_tracker_node_group` (`task_id`,`task_tracker_node_group`),
  KEY `idx_realTaskId_taskTrackerNodeGroup` (`real_task_id`,`task_tracker_node_group`)
) ENGINE=InnoDB AUTO_INCREMENT=392980 DEFAULT CHARSET=utf8 COMMENT='????';

-- ----------------------------
-- Table structure for lts_node_group_store
-- ----------------------------
DROP TABLE IF EXISTS `lts_node_group_store`;
CREATE TABLE `lts_node_group_store` (
  `node_type` varchar(16) NOT NULL DEFAULT '' COMMENT '????',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '??',
  `gmt_created` bigint(20) DEFAULT NULL COMMENT '????',
  PRIMARY KEY (`node_type`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='???';

-- ----------------------------
-- Table structure for lts_repeat_job_queue
-- ----------------------------
DROP TABLE IF EXISTS `lts_repeat_job_queue`;
CREATE TABLE `lts_repeat_job_queue` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID,??????',
  `job_id` varchar(32) DEFAULT NULL COMMENT '??ID,?????',
  `job_type` varchar(32) DEFAULT NULL COMMENT '????',
  `priority` int(11) DEFAULT NULL COMMENT '???,(????,?????)',
  `retry_times` int(11) DEFAULT '0' COMMENT '????',
  `max_retry_times` int(11) DEFAULT '0' COMMENT '??????',
  `rely_on_prev_cycle` tinyint(4) DEFAULT NULL COMMENT '???????????',
  `task_id` varchar(64) DEFAULT NULL COMMENT '??ID,?????????ID',
  `real_task_id` varchar(64) DEFAULT NULL COMMENT '??ID,?????????ID',
  `gmt_created` bigint(20) DEFAULT NULL COMMENT '????',
  `gmt_modified` bigint(11) DEFAULT NULL COMMENT '????',
  `submit_node_group` varchar(64) DEFAULT NULL COMMENT '?????,?????????',
  `task_tracker_node_group` varchar(64) DEFAULT NULL COMMENT '?????,??job?????',
  `ext_params` text COMMENT '???? JSON',
  `internal_ext_params` text COMMENT '?????? JSON',
  `is_running` tinyint(1) DEFAULT NULL COMMENT '??????',
  `task_tracker_identity` varchar(64) DEFAULT NULL COMMENT 'taskTrackerId,???taskTracker?????',
  `need_feedback` tinyint(4) DEFAULT NULL COMMENT '?????,??????????',
  `cron_expression` varchar(128) DEFAULT NULL COMMENT 'Cron???,??????? (? quartz ?????)',
  `trigger_time` bigint(20) DEFAULT NULL COMMENT '???????',
  `repeat_count` int(11) DEFAULT '0' COMMENT '????',
  `repeated_count` int(11) DEFAULT '0' COMMENT '???????',
  `repeat_interval` bigint(20) DEFAULT '0' COMMENT '????',
  `last_generate_trigger_time` bigint(20) DEFAULT '0' COMMENT '?????triggerTime??',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_job_id` (`job_id`),
  UNIQUE KEY `idx_taskId_taskTrackerNodeGroup` (`task_id`,`task_tracker_node_group`),
  KEY `idx_realTaskId_taskTrackerNodeGroup` (`real_task_id`,`task_tracker_node_group`),
  KEY `idx_relyOnPrevCycle_lgtt` (`rely_on_prev_cycle`,`last_generate_trigger_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Repeat??';

-- ----------------------------
-- Table structure for lts_suspend_job_queue
-- ----------------------------
DROP TABLE IF EXISTS `lts_suspend_job_queue`;
CREATE TABLE `lts_suspend_job_queue` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID,??????',
  `job_id` varchar(32) DEFAULT NULL COMMENT '??ID,?????',
  `job_type` varchar(32) DEFAULT NULL COMMENT '????',
  `priority` int(11) DEFAULT NULL COMMENT '???,(????,?????)',
  `retry_times` int(11) DEFAULT '0' COMMENT '????',
  `max_retry_times` int(11) DEFAULT '0' COMMENT '??????',
  `rely_on_prev_cycle` tinyint(4) DEFAULT NULL COMMENT '???????????',
  `task_id` varchar(64) DEFAULT NULL COMMENT '??ID,?????????ID',
  `real_task_id` varchar(64) DEFAULT NULL COMMENT '??ID,?????????ID',
  `gmt_created` bigint(20) DEFAULT NULL COMMENT '????',
  `gmt_modified` bigint(11) DEFAULT NULL COMMENT '????',
  `submit_node_group` varchar(64) DEFAULT NULL COMMENT '?????,?????????',
  `task_tracker_node_group` varchar(64) DEFAULT NULL COMMENT '?????,??job?????',
  `ext_params` text COMMENT '???? JSON',
  `internal_ext_params` text COMMENT '?????? JSON',
  `is_running` tinyint(1) DEFAULT NULL COMMENT '??????',
  `task_tracker_identity` varchar(64) DEFAULT NULL COMMENT 'taskTrackerId,???taskTracker?????',
  `need_feedback` tinyint(4) DEFAULT NULL COMMENT '?????,??????????',
  `cron_expression` varchar(128) DEFAULT NULL COMMENT 'Cron???,??????? (? quartz ?????)',
  `trigger_time` bigint(20) DEFAULT NULL COMMENT '???????',
  `repeat_count` int(11) DEFAULT '0' COMMENT '????',
  `repeated_count` int(11) DEFAULT '0' COMMENT '???????',
  `repeat_interval` bigint(20) DEFAULT '0' COMMENT '????',
  `last_generate_trigger_time` bigint(20) DEFAULT '0' COMMENT '?????triggerTime??',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_job_id` (`job_id`),
  UNIQUE KEY `idx_taskId_taskTrackerNodeGroup` (`task_id`,`task_tracker_node_group`),
  KEY `idx_job_type` (`job_type`),
  KEY `idx_realTaskId_taskTrackerNodeGroup` (`real_task_id`,`task_tracker_node_group`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='Cron?????';

-- ----------------------------
-- Table structure for lts_wjq_test_trade_tasktracker
-- ----------------------------
DROP TABLE IF EXISTS `lts_wjq_test_trade_tasktracker`;
CREATE TABLE `lts_wjq_test_trade_tasktracker` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID,??????',
  `job_id` varchar(32) DEFAULT NULL COMMENT '??ID,?????',
  `job_type` varchar(32) DEFAULT NULL COMMENT '????',
  `priority` int(11) DEFAULT NULL COMMENT '???,(????,?????)',
  `retry_times` int(11) DEFAULT '0' COMMENT '????',
  `max_retry_times` int(11) DEFAULT '0' COMMENT '??????',
  `rely_on_prev_cycle` tinyint(4) DEFAULT NULL COMMENT '???????????',
  `task_id` varchar(64) DEFAULT NULL COMMENT '??ID,?????????ID',
  `real_task_id` varchar(64) DEFAULT NULL COMMENT '??ID,?????????ID',
  `gmt_created` bigint(20) DEFAULT NULL COMMENT '????',
  `gmt_modified` bigint(11) DEFAULT NULL COMMENT '????',
  `submit_node_group` varchar(64) DEFAULT NULL COMMENT '?????,?????????',
  `task_tracker_node_group` varchar(64) DEFAULT NULL COMMENT '?????,??job?????',
  `ext_params` text COMMENT '???? JSON',
  `internal_ext_params` text COMMENT '?????? JSON',
  `is_running` tinyint(1) DEFAULT NULL COMMENT '??????',
  `task_tracker_identity` varchar(64) DEFAULT NULL COMMENT 'taskTrackerId,???taskTracker?????',
  `need_feedback` tinyint(4) DEFAULT NULL COMMENT '?????,??????????',
  `cron_expression` varchar(128) DEFAULT NULL COMMENT 'Cron???,??????? (? quartz ?????)',
  `trigger_time` bigint(20) DEFAULT NULL COMMENT '???????',
  `repeat_count` int(11) DEFAULT '0' COMMENT '????',
  `repeated_count` int(11) DEFAULT '0' COMMENT '???????',
  `repeat_interval` bigint(20) DEFAULT '0' COMMENT '????',
  `last_generate_trigger_time` bigint(20) DEFAULT '0' COMMENT '?????triggerTime??',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_job_id` (`job_id`),
  UNIQUE KEY `idx_taskId_taskTrackerNodeGroup` (`task_id`,`task_tracker_node_group`),
  KEY `idx_taskTrackerIdentity` (`task_tracker_identity`),
  KEY `idx_job_type` (`job_type`),
  KEY `idx_realTaskId_taskTrackerNodeGroup` (`real_task_id`,`task_tracker_node_group`),
  KEY `idx_priority_triggerTime_gmtCreated` (`priority`,`trigger_time`,`gmt_created`),
  KEY `idx_isRunning` (`is_running`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8 COMMENT='??????';

-- ----------------------------
-- Table structure for lts_wjq_zhuyi_parking_task_tracker
-- ----------------------------
DROP TABLE IF EXISTS `lts_wjq_zhuyi_parking_task_tracker`;
CREATE TABLE `lts_wjq_zhuyi_parking_task_tracker` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID,与业务无关的',
  `job_id` varchar(32) DEFAULT NULL COMMENT '作业ID,程序生成的',
  `job_type` varchar(32) DEFAULT NULL COMMENT '任务类型',
  `priority` int(11) DEFAULT NULL COMMENT '优先级,(数值越大,优先级越低)',
  `retry_times` int(11) DEFAULT '0' COMMENT '重试次数',
  `max_retry_times` int(11) DEFAULT '0' COMMENT '最大重试次数',
  `rely_on_prev_cycle` tinyint(4) DEFAULT NULL COMMENT '是否依赖上一个执行周期',
  `task_id` varchar(64) DEFAULT NULL COMMENT '任务ID,客户端传过来的任务ID',
  `real_task_id` varchar(64) DEFAULT NULL COMMENT '任务ID,客户端传过来的任务ID',
  `gmt_created` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` bigint(11) DEFAULT NULL COMMENT '修改时间',
  `submit_node_group` varchar(64) DEFAULT NULL COMMENT '提交节点组,提交客户端的节点组',
  `task_tracker_node_group` varchar(64) DEFAULT NULL COMMENT '执行节点组,执行job的任务节点',
  `ext_params` text COMMENT '用户参数 JSON',
  `internal_ext_params` text COMMENT '内部扩展参数 JSON',
  `is_running` tinyint(1) DEFAULT NULL COMMENT '是否正在执行',
  `task_tracker_identity` varchar(64) DEFAULT NULL COMMENT 'taskTrackerId,执行的taskTracker的唯一标识',
  `need_feedback` tinyint(4) DEFAULT NULL COMMENT '反馈客户端,是否需要反馈给客户端',
  `cron_expression` varchar(128) DEFAULT NULL COMMENT 'Cron表达式,执行时间表达式 (和 quartz 表达式一样)',
  `trigger_time` bigint(20) DEFAULT NULL COMMENT '下一次执行时间',
  `repeat_count` int(11) DEFAULT '0' COMMENT '重复一次',
  `repeated_count` int(11) DEFAULT '0' COMMENT '已经重复的次数',
  `repeat_interval` bigint(20) DEFAULT '0' COMMENT '重复间隔',
  `last_generate_trigger_time` bigint(20) DEFAULT '0' COMMENT '最后生成的triggerTime时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_job_id` (`job_id`),
  UNIQUE KEY `idx_taskId_taskTrackerNodeGroup` (`task_id`,`task_tracker_node_group`),
  KEY `idx_taskTrackerIdentity` (`task_tracker_identity`),
  KEY `idx_job_type` (`job_type`),
  KEY `idx_realTaskId_taskTrackerNodeGroup` (`real_task_id`,`task_tracker_node_group`),
  KEY `idx_priority_triggerTime_gmtCreated` (`priority`,`trigger_time`,`gmt_created`),
  KEY `idx_isRunning` (`is_running`)
) ENGINE=InnoDB AUTO_INCREMENT=194030 DEFAULT CHARSET=utf8 COMMENT='等待执行任务';
