/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : fams

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 06/05/2022 11:20:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `contact` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `comments` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES (1, '里陈', '1234432123', '');

-- ----------------------------
-- Table structure for departments
-- ----------------------------
DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments`  (
  `id` tinyint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `contact` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `comments` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of departments
-- ----------------------------
INSERT INTO `departments` VALUES (1, '技术部', '123421', '123');

-- ----------------------------
-- Table structure for employee_role
-- ----------------------------
DROP TABLE IF EXISTS `employee_role`;
CREATE TABLE `employee_role`  (
  `emp_id` bigint UNSIGNED NOT NULL,
  `role_id` tinyint UNSIGNED NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of employee_role
-- ----------------------------
INSERT INTO `employee_role` VALUES (1, 1);
INSERT INTO `employee_role` VALUES (2, 1);
INSERT INTO `employee_role` VALUES (3, 2);

-- ----------------------------
-- Table structure for employee_states
-- ----------------------------
DROP TABLE IF EXISTS `employee_states`;
CREATE TABLE `employee_states`  (
  `id` tinyint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of employee_states
-- ----------------------------
INSERT INTO `employee_states` VALUES (1, '在职');
INSERT INTO `employee_states` VALUES (2, '离职');
INSERT INTO `employee_states` VALUES (3, '休假');

-- ----------------------------
-- Table structure for employees
-- ----------------------------
DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birthday` date NULL DEFAULT NULL,
  `gender` tinyint(1) NOT NULL,
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `admin` bit(1) NOT NULL,
  `dept_id` tinyint UNSIGNED NULL DEFAULT NULL,
  `hiredate` date NOT NULL,
  `state_id` tinyint UNSIGNED NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `emp_dept_fk`(`dept_id`) USING BTREE,
  INDEX `emp_state_id_fk`(`state_id`) USING BTREE,
  INDEX `emp_gender_id_fk`(`gender`) USING BTREE,
  CONSTRAINT `emp_dept_fk` FOREIGN KEY (`dept_id`) REFERENCES `departments` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `emp_gender_id_fk` FOREIGN KEY (`gender`) REFERENCES `gender` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `emp_state_id_fk` FOREIGN KEY (`state_id`) REFERENCES `employee_states` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of employees
-- ----------------------------
INSERT INTO `employees` VALUES (1, 'admin', '2000-01-01', 1, '123456789', '5987522@qq.com', 'sad123sad', b'1', 1, '2020-01-01', 1, '21232f297a57a5a743894a0e4a801fc3');
INSERT INTO `employees` VALUES (2, '陈香润', '2022-05-12', 1, '12345678', '12345678@qq.com', 'China', b'0', 1, '2022-05-20', 1, 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `employees` VALUES (3, '老李', '2022-05-03', 1, '17536985214', 'laoli@example.com', '揭阳', b'0', 1, '2022-05-11', 1, 'e10adc3949ba59abbe56e057f20f883e');

-- ----------------------------
-- Table structure for gender
-- ----------------------------
DROP TABLE IF EXISTS `gender`;
CREATE TABLE `gender`  (
  `id` tinyint NOT NULL AUTO_INCREMENT,
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gender
-- ----------------------------
INSERT INTO `gender` VALUES (1, '男');
INSERT INTO `gender` VALUES (2, '女');
INSERT INTO `gender` VALUES (3, '无性别');

-- ----------------------------
-- Table structure for material_application
-- ----------------------------
DROP TABLE IF EXISTS `material_application`;
CREATE TABLE `material_application`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint UNSIGNED NOT NULL,
  `emp_id` bigint UNSIGNED NOT NULL,
  `stock_id` bigint NULL DEFAULT NULL,
  `quantity` int UNSIGNED NOT NULL,
  `state_id` tinyint UNSIGNED NOT NULL,
  `application_time` datetime NOT NULL,
  `operator_id` bigint UNSIGNED NULL DEFAULT NULL,
  `operation_time` datetime NULL DEFAULT NULL,
  `comments` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `mate_app_order_id_fk`(`order_id`) USING BTREE,
  INDEX `mate_app_emp_id_fk`(`emp_id`) USING BTREE,
  INDEX `mate_app_state_id_fk`(`state_id`) USING BTREE,
  INDEX `mate_app_operator_id_fk`(`operator_id`) USING BTREE,
  CONSTRAINT `mate_app_emp_id_fk` FOREIGN KEY (`emp_id`) REFERENCES `employees` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mate_app_operator_id_fk` FOREIGN KEY (`operator_id`) REFERENCES `employees` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mate_app_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mate_app_state_id_fk` FOREIGN KEY (`state_id`) REFERENCES `material_application_states` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of material_application
-- ----------------------------
INSERT INTO `material_application` VALUES (3, 2, 1, 1, 44, 1, '2022-05-05 16:50:00', 1, '2022-05-05 16:50:00', '');
INSERT INTO `material_application` VALUES (4, 2, 1, 1, 1, 1, '2022-05-06 08:58:40', 1, '2022-05-06 08:58:40', '');
INSERT INTO `material_application` VALUES (5, 2, 1, 1, 1, 1, '2022-05-06 09:01:11', 1, '2022-05-06 09:01:11', '');
INSERT INTO `material_application` VALUES (6, 2, 1, 1, 1, 1, '2022-05-06 09:01:53', 1, '2022-05-06 09:01:53', '');
INSERT INTO `material_application` VALUES (7, 2, 1, 1, 1, 1, '2022-05-06 09:02:01', 1, '2022-05-06 09:02:01', '');
INSERT INTO `material_application` VALUES (8, 2, 1, 1, 1, 1, '2022-05-06 09:02:09', 1, '2022-05-06 09:02:09', '');
INSERT INTO `material_application` VALUES (9, 2, 1, 1, 1, 1, '2022-05-06 09:02:15', 1, '2022-05-06 09:02:15', '');
INSERT INTO `material_application` VALUES (10, 2, 1, 1, 1, 1, '2022-05-06 09:02:27', 1, '2022-05-06 09:02:27', '');
INSERT INTO `material_application` VALUES (11, 2, 1, 1, 1, 1, '2022-05-06 10:03:51', 1, '2022-05-06 10:03:51', '');
INSERT INTO `material_application` VALUES (12, 2, 1, 1, 2, 3, '2022-05-06 10:03:59', 1, '2022-05-06 10:03:59', '');

-- ----------------------------
-- Table structure for material_application_states
-- ----------------------------
DROP TABLE IF EXISTS `material_application_states`;
CREATE TABLE `material_application_states`  (
  `id` tinyint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of material_application_states
-- ----------------------------
INSERT INTO `material_application_states` VALUES (1, '已通过');
INSERT INTO `material_application_states` VALUES (2, '待审批');
INSERT INTO `material_application_states` VALUES (3, '未通过');

-- ----------------------------
-- Table structure for material_type
-- ----------------------------
DROP TABLE IF EXISTS `material_type`;
CREATE TABLE `material_type`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of material_type
-- ----------------------------
INSERT INTO `material_type` VALUES (1, '铁板');

-- ----------------------------
-- Table structure for materials
-- ----------------------------
DROP TABLE IF EXISTS `materials`;
CREATE TABLE `materials`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_id` int UNSIGNED NOT NULL,
  `parameter` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `material_type_fk`(`type_id`) USING BTREE,
  CONSTRAINT `material_type_fk` FOREIGN KEY (`type_id`) REFERENCES `material_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of materials
-- ----------------------------
INSERT INTO `materials` VALUES (1, '厚15cm铁板', 1, '厚15cm，长2m，宽3m');

-- ----------------------------
-- Table structure for order_employee
-- ----------------------------
DROP TABLE IF EXISTS `order_employee`;
CREATE TABLE `order_employee`  (
  `order_id` bigint UNSIGNED NOT NULL,
  `emp_id` bigint UNSIGNED NOT NULL,
  INDEX `ord_emp_order_id_fk`(`order_id`) USING BTREE,
  INDEX `ord_emp_emp_id_fk`(`emp_id`) USING BTREE,
  CONSTRAINT `ord_emp_emp_id_fk` FOREIGN KEY (`emp_id`) REFERENCES `employees` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ord_emp_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_employee
-- ----------------------------
INSERT INTO `order_employee` VALUES (3, 1);
INSERT INTO `order_employee` VALUES (3, 2);
INSERT INTO `order_employee` VALUES (2, 1);
INSERT INTO `order_employee` VALUES (2, 2);

-- ----------------------------
-- Table structure for order_state_change
-- ----------------------------
DROP TABLE IF EXISTS `order_state_change`;
CREATE TABLE `order_state_change`  (
  `order_id` bigint UNSIGNED NOT NULL,
  `state_id` tinyint UNSIGNED NOT NULL,
  `occurrence_time` datetime NOT NULL,
  `operator_id` bigint UNSIGNED NOT NULL,
  INDEX `ord_sta_cha_order_id_fk`(`order_id`) USING BTREE,
  INDEX `ord_sta_cha_state_id_fk`(`state_id`) USING BTREE,
  INDEX `ord_sta_cha_operator_id_fk`(`operator_id`) USING BTREE,
  CONSTRAINT `ord_sta_cha_operator_id_fk` FOREIGN KEY (`operator_id`) REFERENCES `employees` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ord_sta_cha_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ord_sta_cha_state_id_fk` FOREIGN KEY (`state_id`) REFERENCES `order_states` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_state_change
-- ----------------------------
INSERT INTO `order_state_change` VALUES (2, 1, '2022-05-05 00:00:00', 1);
INSERT INTO `order_state_change` VALUES (3, 4, '2022-05-05 00:00:00', 1);
INSERT INTO `order_state_change` VALUES (3, 1, '2022-05-05 00:00:00', 1);
INSERT INTO `order_state_change` VALUES (3, 1, '2022-05-06 09:18:19', 1);
INSERT INTO `order_state_change` VALUES (3, 1, '2022-05-06 09:18:25', 1);
INSERT INTO `order_state_change` VALUES (3, 1, '2022-05-06 09:18:31', 1);
INSERT INTO `order_state_change` VALUES (3, 1, '2022-05-06 09:18:36', 1);
INSERT INTO `order_state_change` VALUES (2, 2, '2022-05-06 10:12:15', 1);
INSERT INTO `order_state_change` VALUES (2, 4, '2022-05-06 10:19:49', 1);

-- ----------------------------
-- Table structure for order_states
-- ----------------------------
DROP TABLE IF EXISTS `order_states`;
CREATE TABLE `order_states`  (
  `id` tinyint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_states
-- ----------------------------
INSERT INTO `order_states` VALUES (1, '创建');
INSERT INTO `order_states` VALUES (2, '进行中');
INSERT INTO `order_states` VALUES (3, '已延期');
INSERT INTO `order_states` VALUES (4, '已完成');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `cust_id` bigint UNSIGNED NOT NULL,
  `price` decimal(9, 2) NULL DEFAULT NULL,
  `ddl` date NULL DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `creator_id` bigint UNSIGNED NOT NULL,
  `state_id` tinyint UNSIGNED NOT NULL,
  `process_time` datetime NULL DEFAULT NULL,
  `comments` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ord_creater_id_fk`(`creator_id`) USING BTREE,
  INDEX `ord_cust_id_fk`(`cust_id`) USING BTREE,
  INDEX `ord_order_state_id_fk`(`state_id`) USING BTREE,
  CONSTRAINT `ord_creater_id_fk` FOREIGN KEY (`creator_id`) REFERENCES `employees` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ord_cust_id_fk` FOREIGN KEY (`cust_id`) REFERENCES `customers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `ord_order_state_id_fk` FOREIGN KEY (`state_id`) REFERENCES `order_states` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (2, 1, 123.00, '2022-06-13', '2022-05-01 00:00:00', 1, 4, '2022-05-06 10:19:49', '');
INSERT INTO `orders` VALUES (3, 1, 321.00, '2022-05-11', '2022-05-05 16:29:10', 1, 1, '2022-05-06 09:18:36', '123');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `id` tinyint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `expression` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of permissions
-- ----------------------------
INSERT INTO `permissions` VALUES (1, 'Delete Customer', 'customer:delete');
INSERT INTO `permissions` VALUES (2, 'Query Customer', 'customer:query');
INSERT INTO `permissions` VALUES (3, 'Modify Customer', 'customer:modify');
INSERT INTO `permissions` VALUES (4, 'Customer Detail', 'customer:detail');
INSERT INTO `permissions` VALUES (5, 'Delete Department', 'department:delete');
INSERT INTO `permissions` VALUES (6, 'Query Department', 'department:query');
INSERT INTO `permissions` VALUES (7, 'Modify Department', 'department:modify');
INSERT INTO `permissions` VALUES (8, 'Department Detail', 'department:detail');
INSERT INTO `permissions` VALUES (9, 'Delete Employee', 'employee:delete');
INSERT INTO `permissions` VALUES (10, 'Query Employee', 'employee:query');
INSERT INTO `permissions` VALUES (11, 'Modify Employee', 'employee:modify');
INSERT INTO `permissions` VALUES (12, 'Employee Detail', 'employee:detail');
INSERT INTO `permissions` VALUES (13, 'Apply Material', 'materialApplication:apply');
INSERT INTO `permissions` VALUES (14, 'Delete material application', 'materialApplication:delete');
INSERT INTO `permissions` VALUES (15, 'Query material application', 'materialApplication:query');
INSERT INTO `permissions` VALUES (16, 'Modify material application', 'materialApplication:modify');
INSERT INTO `permissions` VALUES (17, 'Delete Material', 'material:delete');
INSERT INTO `permissions` VALUES (18, 'Query Material', 'material:query');
INSERT INTO `permissions` VALUES (19, 'Modify Material', 'material:modify');
INSERT INTO `permissions` VALUES (20, 'Material Detail', 'material:detail');
INSERT INTO `permissions` VALUES (21, 'Material Type Detail', 'materialType:detail');
INSERT INTO `permissions` VALUES (22, 'Order Detail', 'order:detail');
INSERT INTO `permissions` VALUES (23, 'Delete Order', 'order:delete');
INSERT INTO `permissions` VALUES (24, 'Query Order', 'order:query');
INSERT INTO `permissions` VALUES (25, 'Modify Order', 'order:modify');
INSERT INTO `permissions` VALUES (26, 'Reload Permission', 'permission:reload');
INSERT INTO `permissions` VALUES (27, 'Query Permission', 'permission:query');
INSERT INTO `permissions` VALUES (28, 'Delete Role', 'role:delete');
INSERT INTO `permissions` VALUES (29, 'Query Role', 'role:query');
INSERT INTO `permissions` VALUES (30, 'Role Detail', 'role:detail');
INSERT INTO `permissions` VALUES (31, 'Modify Role', 'role:modify');
INSERT INTO `permissions` VALUES (32, 'Delete Stock', 'stock:delete');
INSERT INTO `permissions` VALUES (33, 'Query Stock', 'stock:query');
INSERT INTO `permissions` VALUES (34, 'Modify Stock', 'stock:modify');
INSERT INTO `permissions` VALUES (35, 'Stock Detail', 'stock:detail');
INSERT INTO `permissions` VALUES (36, 'Query Warehouse', 'warehouse:query');
INSERT INTO `permissions` VALUES (37, 'Modify Warehouse', 'warehouse:modify');
INSERT INTO `permissions` VALUES (38, 'Delete Warehouse', 'warehouse:delete');
INSERT INTO `permissions` VALUES (39, 'Warehouse Detail', 'warehouse:detail');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `role_id` tinyint UNSIGNED NOT NULL,
  `per_id` tinyint UNSIGNED NOT NULL,
  INDEX `rol_per_role_id_fk`(`role_id`) USING BTREE,
  INDEX `rol_per_per_id_fk`(`per_id`) USING BTREE,
  CONSTRAINT `rol_per_per_id_fk` FOREIGN KEY (`per_id`) REFERENCES `permissions` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rol_per_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1);
INSERT INTO `role_permission` VALUES (1, 2);
INSERT INTO `role_permission` VALUES (1, 3);
INSERT INTO `role_permission` VALUES (1, 4);
INSERT INTO `role_permission` VALUES (1, 5);
INSERT INTO `role_permission` VALUES (1, 6);
INSERT INTO `role_permission` VALUES (1, 7);
INSERT INTO `role_permission` VALUES (1, 8);
INSERT INTO `role_permission` VALUES (1, 9);
INSERT INTO `role_permission` VALUES (1, 10);
INSERT INTO `role_permission` VALUES (1, 11);
INSERT INTO `role_permission` VALUES (1, 12);
INSERT INTO `role_permission` VALUES (1, 13);
INSERT INTO `role_permission` VALUES (1, 14);
INSERT INTO `role_permission` VALUES (1, 15);
INSERT INTO `role_permission` VALUES (1, 16);
INSERT INTO `role_permission` VALUES (1, 17);
INSERT INTO `role_permission` VALUES (1, 18);
INSERT INTO `role_permission` VALUES (1, 19);
INSERT INTO `role_permission` VALUES (1, 20);
INSERT INTO `role_permission` VALUES (1, 21);
INSERT INTO `role_permission` VALUES (1, 22);
INSERT INTO `role_permission` VALUES (1, 23);
INSERT INTO `role_permission` VALUES (1, 24);
INSERT INTO `role_permission` VALUES (1, 25);
INSERT INTO `role_permission` VALUES (1, 26);
INSERT INTO `role_permission` VALUES (1, 27);
INSERT INTO `role_permission` VALUES (1, 28);
INSERT INTO `role_permission` VALUES (1, 29);
INSERT INTO `role_permission` VALUES (1, 30);
INSERT INTO `role_permission` VALUES (1, 31);
INSERT INTO `role_permission` VALUES (1, 32);
INSERT INTO `role_permission` VALUES (1, 33);
INSERT INTO `role_permission` VALUES (1, 34);
INSERT INTO `role_permission` VALUES (1, 35);
INSERT INTO `role_permission` VALUES (1, 36);
INSERT INTO `role_permission` VALUES (1, 37);
INSERT INTO `role_permission` VALUES (1, 38);
INSERT INTO `role_permission` VALUES (1, 39);
INSERT INTO `role_permission` VALUES (2, 10);
INSERT INTO `role_permission` VALUES (2, 33);

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `id` tinyint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(129) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `comments` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, '员工', '普通员工');
INSERT INTO `roles` VALUES (2, '师傅', '');

-- ----------------------------
-- Table structure for stocks
-- ----------------------------
DROP TABLE IF EXISTS `stocks`;
CREATE TABLE `stocks`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mate_id` bigint UNSIGNED NOT NULL,
  `warehouse_id` tinyint UNSIGNED NOT NULL,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `quantity` int UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `stocks_materials_fk`(`mate_id`) USING BTREE,
  INDEX `stocks_warehouse_fk`(`warehouse_id`) USING BTREE,
  CONSTRAINT `stocks_materials_fk` FOREIGN KEY (`mate_id`) REFERENCES `materials` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `stocks_warehouse_fk` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouses` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of stocks
-- ----------------------------
INSERT INTO `stocks` VALUES (1, 1, 1, 'A2区域', 58);

-- ----------------------------
-- Table structure for warehouses
-- ----------------------------
DROP TABLE IF EXISTS `warehouses`;
CREATE TABLE `warehouses`  (
  `id` tinyint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `position` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `comments` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of warehouses
-- ----------------------------
INSERT INTO `warehouses` VALUES (1, '北门仓库', '广东省揭阳市榕城区', '电话1563325698');

SET FOREIGN_KEY_CHECKS = 1;
