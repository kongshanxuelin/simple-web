/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.63
Source Server Version : 50622
Source Host           : 192.168.1.63:3306
Source Database       : commission

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2018-01-15 17:57:38
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `commission`
-- ----------------------------
DROP TABLE IF EXISTS `commission`;
CREATE TABLE `commission` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `commission_id` char(32) NOT NULL,
  `customer_mgr_user_id` char(32) NOT NULL,
  `customer_mgr_username` varchar(64) NOT NULL,
  `customer_mgr_realname` varchar(64) DEFAULT NULL,
  `team_mgr_user_id` char(32) NOT NULL,
  `team_mgr_username` varchar(64) NOT NULL,
  `team_mgr_realname` varchar(64) DEFAULT NULL,
  `contact_no` varchar(64) NOT NULL,
  `customer_name` varchar(64) NOT NULL,
  `order_money_per_year` decimal(20,10) NOT NULL COMMENT '年付金额',
  `first_year` varchar(8) DEFAULT NULL,
  `payment_type` varchar(16) NOT NULL COMMENT '付款方式：一次性付清，逐年付清',
  `contact_age_limit` int(11) NOT NULL COMMENT '同合服务年限',
  `predict_money_to_account_date` date NOT NULL,
  `cal_commission_ratio` decimal(20,10) NOT NULL COMMENT '默认1.06',
  `work_difficulty_ratio` decimal(20,10) DEFAULT NULL COMMENT '难易系数6%, 9%, 12%',
  `status` varchar(32) NOT NULL,
  `team_mgr_confirm_time` datetime DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `commission_id_idx` (`commission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of commission
-- ----------------------------

-- ----------------------------
-- Table structure for `commission_payment`
-- ----------------------------
DROP TABLE IF EXISTS `commission_payment`;
CREATE TABLE `commission_payment` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `commission_payment_id` char(32) NOT NULL,
  `commission_id` char(32) NOT NULL,
  `pair_id` char(32) DEFAULT NULL,
  `contact_no` varchar(64) NOT NULL,
  `customer_name` varchar(64) NOT NULL,
  `order_part_money` decimal(20,10) NOT NULL,
  `cal_commission_ratio` decimal(20,10) NOT NULL,
  `work_difficulty_ratio` decimal(20,10) NOT NULL,
  `commission_percent` decimal(20,10) NOT NULL,
  `commission` decimal(20,10) NOT NULL,
  `status` varchar(16) NOT NULL,
  `predict_money_delivery_date` date DEFAULT NULL,
  `actually_money_delivery_date` date DEFAULT NULL,
  `predict_money_to_account_date` date DEFAULT NULL,
  `hr_confirm_time` datetime DEFAULT NULL,
  `finance_confirm_time` datetime DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `commission_payment_id_idx` (`commission_payment_id`),
  KEY `commission_id_idx` (`commission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of commission_payment
-- ----------------------------

-- ----------------------------
-- Table structure for `role_user`
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` char(32) DEFAULT NULL,
  `role_name` varchar(16) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_user
-- ----------------------------
INSERT INTO `role_user` VALUES ('1', '00000000000000029', 'FINANCE', '2018-01-08 13:06:55', '2018-01-08 13:06:57');
INSERT INTO `role_user` VALUES ('2', '00000000000000030', 'HR', '2018-01-08 14:32:44', '2018-01-08 14:32:47');
INSERT INTO `role_user` VALUES ('3', '00000000000000002', 'TEAM_MGR', '2018-01-08 14:37:05', '2018-01-08 14:37:07');
INSERT INTO `role_user` VALUES ('4', '00000000000000006', 'TEAM_MGR', '2018-01-08 14:37:23', '2018-01-08 14:37:25');
INSERT INTO `role_user` VALUES ('5', '00000000000000008', 'TEAM_MGR', '2018-01-08 14:37:53', '2018-01-08 14:37:56');
INSERT INTO `role_user` VALUES ('6', '00000000000000004', 'TEAM_MGR', '2018-01-08 14:38:11', '2018-01-08 14:38:13');
INSERT INTO `role_user` VALUES ('7', '00000000000000007', 'TEAM_MGR', '2018-01-08 14:38:41', '2018-01-08 14:38:44');
INSERT INTO `role_user` VALUES ('8', '00000000000000005', 'TEAM_MGR', '2018-01-08 14:39:04', '2018-01-08 14:39:06');
INSERT INTO `role_user` VALUES ('10', '00000000000000003', 'TEAM_MGR', '2018-01-08 14:39:49', '2018-01-08 14:39:52');
INSERT INTO `role_user` VALUES ('19', '00000000000000001', 'TEAM_MGR', '2018-01-12 14:48:51', '2018-01-12 14:48:51');

-- ----------------------------
-- Table structure for `team`
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `team_id` char(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `description` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES ('1', '1', '鲍寅飞组', '鲍寅飞', '2018-01-08 13:21:23', '2018-01-08 13:21:25');
INSERT INTO `team` VALUES ('2', '2', '王乃升组', '王乃升', '2018-01-08 13:21:47', '2018-01-08 13:21:50');
INSERT INTO `team` VALUES ('3', '3', '辛茜组', '辛茜', '2018-01-08 13:22:09', '2018-01-08 13:22:13');
INSERT INTO `team` VALUES ('4', '4', 'Ken组', 'Ken', '2018-01-08 13:22:26', '2018-01-08 13:22:28');
INSERT INTO `team` VALUES ('5', '5', '许健组', '许健', '2018-01-08 13:23:07', '2018-01-08 13:23:09');
INSERT INTO `team` VALUES ('6', '6', 'Vera组', 'Vera', '2018-01-08 13:37:42', '2018-01-08 13:37:45');
INSERT INTO `team` VALUES ('7', '7', '沈文泓组', '沈文泓', '2018-01-08 13:37:37', '2018-01-08 13:37:40');
INSERT INTO `team` VALUES ('8', '8', '庞冠楠组', '庞冠楠', '2018-01-08 13:38:10', '2018-01-08 13:38:12');

-- ----------------------------
-- Table structure for `team_user`
-- ----------------------------
DROP TABLE IF EXISTS `team_user`;
CREATE TABLE `team_user` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `team_id` char(32) NOT NULL,
  `user_id` char(32) NOT NULL,
  `type` varchar(16) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of team_user
-- ----------------------------
INSERT INTO `team_user` VALUES ('1', '1', '00000000000000001', 'TEAM_MGR', '2018-01-08 13:41:06', '2018-01-12 14:48:51');
INSERT INTO `team_user` VALUES ('3', '2', '00000000000000002', 'TEAM_MGR', '2018-01-08 13:44:11', '2018-01-08 13:44:14');
INSERT INTO `team_user` VALUES ('4', '2', '00000000000000010', 'CUSTOMER_MGR', '2018-01-08 13:44:51', '2018-01-08 13:44:53');
INSERT INTO `team_user` VALUES ('5', '2', '00000000000000011', 'CUSTOMER_MGR', '2018-01-08 13:45:29', '2018-01-08 13:45:32');
INSERT INTO `team_user` VALUES ('6', '3', '00000000000000003', 'TEAN_MGR', '2018-01-08 13:46:55', '2018-01-08 13:46:57');
INSERT INTO `team_user` VALUES ('7', '3', '00000000000000012', 'CUSTOMER_MGR', '2018-01-08 13:47:36', '2018-01-08 13:47:38');
INSERT INTO `team_user` VALUES ('8', '3', '00000000000000019', 'CUSTOMER_MGR', '2018-01-08 13:48:19', '2018-01-08 13:48:21');
INSERT INTO `team_user` VALUES ('9', '3', '00000000000000020', 'CUSTOMER_MGR', '2018-01-08 13:49:11', '2018-01-08 13:49:14');
INSERT INTO `team_user` VALUES ('10', '4', '00000000000000004', 'TEAM_MGR', '2018-01-08 13:49:56', '2018-01-08 13:49:58');
INSERT INTO `team_user` VALUES ('11', '4', '00000000000000013', 'CUSTOMER_MGR', '2018-01-08 13:50:32', '2018-01-08 13:50:34');
INSERT INTO `team_user` VALUES ('12', '4', '00000000000000018', 'CUSTOMER_MGR', '2018-01-08 13:51:08', '2018-01-08 13:51:10');
INSERT INTO `team_user` VALUES ('13', '4', '00000000000000022', 'CUSTOMER_MGR', '2018-01-08 13:51:46', '2018-01-08 13:51:48');
INSERT INTO `team_user` VALUES ('14', '5', '00000000000000005', 'TEAM_MGR', '2018-01-08 13:52:38', '2018-01-08 13:52:40');
INSERT INTO `team_user` VALUES ('15', '5', '00000000000000014', 'CUSTOMER_MGR', '2018-01-08 13:53:46', '2018-01-08 13:53:47');
INSERT INTO `team_user` VALUES ('16', '6', '00000000000000006', 'TEAM_MGR', '2018-01-08 13:55:14', '2018-01-08 13:55:16');
INSERT INTO `team_user` VALUES ('17', '6', '00000000000000015', 'CUSTOMER_MGR', '2018-01-08 13:56:00', '2018-01-08 13:56:03');
INSERT INTO `team_user` VALUES ('18', '6', '00000000000000016', 'CUSTOMER_MGR', '2018-01-08 13:56:50', '2018-01-08 13:56:52');
INSERT INTO `team_user` VALUES ('19', '6', '00000000000000017', 'CUSTOMER_MGR', '2018-01-08 13:57:42', '2018-01-08 13:57:44');
INSERT INTO `team_user` VALUES ('20', '6', '00000000000000021', 'CUSTOMER_MGR', '2018-01-08 13:58:40', '2018-01-08 13:58:42');
INSERT INTO `team_user` VALUES ('21', '6', '00000000000000023', 'CUSTOMER_MGR', '2018-01-08 13:59:15', '2018-01-08 13:59:17');
INSERT INTO `team_user` VALUES ('22', '6', '00000000000000026', 'CUSTOMER_MGR', '2018-01-08 14:00:17', '2018-01-08 14:00:19');
INSERT INTO `team_user` VALUES ('23', '7', '00000000000000007', 'TEAM_MGR', '2018-01-08 14:01:01', '2018-01-08 14:01:03');
INSERT INTO `team_user` VALUES ('24', '7', '00000000000000024', 'CUSTOMER_MGR', '2018-01-08 14:02:23', '2018-01-08 14:02:26');
INSERT INTO `team_user` VALUES ('25', '8', '00000000000000008', 'TEAM_MGR', '2018-01-08 14:04:41', '2018-01-08 14:04:44');
INSERT INTO `team_user` VALUES ('26', '8', '00000000000000027', 'CUSTOMER_MGR', '2018-01-08 14:05:16', '2018-01-08 14:05:19');
INSERT INTO `team_user` VALUES ('27', '8', '00000000000000028', 'CUSTOMER_MGR', '2018-01-08 14:05:43', '2018-01-08 14:05:47');
INSERT INTO `team_user` VALUES ('28', '6', '00000000000000008', 'CUSTOMER_MGR', '2018-01-08 14:08:46', '2018-01-08 14:08:48');
INSERT INTO `team_user` VALUES ('29', '6', '00000000000000007', 'CUSTOMER_MGR', '2018-01-08 14:09:20', '2018-01-08 14:09:24');
INSERT INTO `team_user` VALUES ('30', '6', '00000000000000003', 'CUSTOMER_MGR', '2018-01-08 14:09:53', '2018-01-08 14:09:56');
INSERT INTO `team_user` VALUES ('31', '6', '00000000000000005', 'CUSTOMER_MGR', '2018-01-08 14:10:32', '2018-01-08 14:10:35');
INSERT INTO `team_user` VALUES ('32', '6', '00000000000000004', 'CUSTOMER_MGR', '2018-01-08 14:11:13', '2018-01-08 14:11:15');
INSERT INTO `team_user` VALUES ('33', '6', '00000000000000001', 'CUSTOMER_MGR', '2018-01-08 14:12:10', '2018-01-08 14:12:11');
INSERT INTO `team_user` VALUES ('34', '6', '00000000000000002', 'CUSTOMER_MGR', '2018-01-08 14:12:33', '2018-01-08 14:12:35');
INSERT INTO `team_user` VALUES ('37', '1', '00000000000000009', 'CUSTOMER_MGR', '2018-01-12 17:44:54', '2018-01-12 17:44:54');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` char(64) NOT NULL,
  `username` varchar(64) NOT NULL,
  `email` varchar(128) NOT NULL,
  `password` char(32) NOT NULL,
  `realname` varchar(64) NOT NULL,
  `sex` char(1) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `status` char(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `user_id_idx` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3012 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '00000000000000001', 'yinfei.bao', 'yinfei.bao@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '鲍寅飞', 'm', '18', '0', '2018-01-08 11:00:13', '2018-01-08 11:00:18');
INSERT INTO `user` VALUES ('2', '00000000000000002', 'naishen.wang', 'naishen.wang@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '王乃升', 'm', '18', '0', '2018-01-08 11:25:03', '2018-01-08 11:25:06');
INSERT INTO `user` VALUES ('3', '00000000000000003', 'elaine.xin', 'elaine.xin@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '辛茜', 'm', '18', '0', '2018-01-08 11:26:58', '2018-01-08 11:27:00');
INSERT INTO `user` VALUES ('4', '00000000000000004', 'ken.cai', 'ken.cai@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', 'ken.cai', 'm', '18', '0', '2018-01-08 11:27:52', '2018-01-08 11:27:54');
INSERT INTO `user` VALUES ('5', '00000000000000005', 'jian.xu', 'jian.xu@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '许健', 'm', '18', '0', '2018-01-08 11:29:12', '2018-01-08 11:29:16');
INSERT INTO `user` VALUES ('6', '00000000000000006', 'vera.tang', 'vera.tang@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', 'vera.tang', 'm', '18', '0', '2018-01-08 11:30:02', '2018-01-08 11:30:05');
INSERT INTO `user` VALUES ('7', '00000000000000007', 'wenhong.shen', 'wenhong.shen@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '沈文泓', 'm', '18', '0', '2018-01-08 11:31:13', '2018-01-08 11:31:16');
INSERT INTO `user` VALUES ('8', '00000000000000008', 'guannan.pang', 'guannan.pang@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '庞冠楠', 'm', '18', '0', '2018-01-08 11:32:53', '2018-01-08 11:32:55');
INSERT INTO `user` VALUES ('9', '00000000000000009', 'mandy.jiang', 'mandy.jiang@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '姜婧琛', 'm', '18', '0', '2018-01-08 11:33:40', '2018-01-08 11:33:43');
INSERT INTO `user` VALUES ('10', '00000000000000010', 'vivian.xu', 'vivian.xu@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '许可', 'm', '18', '0', '2018-01-08 11:36:32', '2018-01-08 11:36:35');
INSERT INTO `user` VALUES ('11', '00000000000000011', 'echo.yang', 'echo.yang@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '杨逸舟', 'm', '18', '0', '2018-01-08 11:37:05', '2018-01-08 11:37:07');
INSERT INTO `user` VALUES ('12', '00000000000000012', 'kiki.zhong', 'kiki.zhong@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '仲棋', 'm', '18', '0', '2018-01-08 11:37:42', '2018-01-08 11:37:44');
INSERT INTO `user` VALUES ('13', '00000000000000013', 'qianzi.wang', 'qianzi.wang@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '王倩子', 'm', '18', '0', '2018-01-08 11:38:27', '2018-01-08 11:38:30');
INSERT INTO `user` VALUES ('14', '00000000000000014', 'danfei.pan', 'danfei.pan@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '潘丹斐', 'm', '18', '0', '2018-01-08 11:41:37', '2018-01-08 11:41:40');
INSERT INTO `user` VALUES ('15', '00000000000000015', 'yibo.sun', 'yibo.sun@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '孙轶博', 'm', '18', '0', '2018-01-08 11:42:21', '2018-01-08 11:42:23');
INSERT INTO `user` VALUES ('16', '00000000000000016', 'weiqin.luo', 'weiqin.luo@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '罗伟芹', 'm', '18', '0', '2018-01-08 11:44:43', '2018-01-08 11:44:47');
INSERT INTO `user` VALUES ('17', '00000000000000017', 'juejing.zhang', 'juejing.zhang@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '张珏晶', 'm', '18', '0', '2018-01-08 11:45:27', '2018-01-08 11:45:29');
INSERT INTO `user` VALUES ('18', '00000000000000018', 'jingyi.peng', 'jingyi.peng@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '彭景一', 'm', '18', '0', '2018-01-08 11:46:34', '2018-01-08 11:46:36');
INSERT INTO `user` VALUES ('19', '00000000000000019', 'fanbin.chen', 'fanbin.chen@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '陈范斌', 'm', '18', '0', '2018-01-08 11:47:47', '2018-01-08 11:47:49');
INSERT INTO `user` VALUES ('20', '00000000000000020', 'biaobiao.yang', 'biaobiao.yang@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '杨彪彪', 'm', '18', '0', '2018-01-08 11:50:00', '2018-01-08 11:50:02');
INSERT INTO `user` VALUES ('21', '00000000000000021', 'ruyi.yang', 'ruyi.yang@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '杨如意', 'm', '18', '0', '2018-01-08 11:50:29', '2018-01-08 11:50:32');
INSERT INTO `user` VALUES ('22', '00000000000000022', 'haowen.zhang', 'haowen.zhang@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '张浩文', 'm', '18', '0', '2018-01-08 11:51:20', '2018-01-08 11:51:23');
INSERT INTO `user` VALUES ('23', '00000000000000023', 'na.xiao', 'na.xiao@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '肖娜', 'm', '18', '0', '2018-01-08 11:51:58', '2018-01-08 11:52:03');
INSERT INTO `user` VALUES ('24', '00000000000000024', 'xiaoan.zhou', 'xiaoan.zhou@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '周笑安', 'm', '18', '0', '2018-01-08 11:53:01', '2018-01-08 11:53:04');
INSERT INTO `user` VALUES ('26', '00000000000000026', 'ailun.pan', 'ailun.pan@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '潘艾伦', 'm', '18', '0', '2018-01-08 11:54:23', '2018-01-08 11:54:25');
INSERT INTO `user` VALUES ('27', '00000000000000027', 'wei.mao', 'wei.mao@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '毛伟', 'm', '18', '0', '2018-01-08 11:54:59', '2018-01-08 11:55:05');
INSERT INTO `user` VALUES ('28', '00000000000000028', 'decheng.xiu', 'decheng.xiu@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '修德成', 'm', '18', '0', '2018-01-08 11:55:51', '2018-01-08 11:55:55');
INSERT INTO `user` VALUES ('29', '00000000000000029', 'qin.dong', 'qin.dong@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', '董芹', 'm', '18', '0', '2018-01-08 11:59:11', '2018-01-08 11:59:14');
INSERT INTO `user` VALUES ('30', '00000000000000030', 'hr', 'hr@sumscope.com', 'e10adc3949ba59abbe56e057f20f883e', 'hr', 'm', '18', '0', '2018-01-08 14:31:32', '2018-01-08 14:31:35');
