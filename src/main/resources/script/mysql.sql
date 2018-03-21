DROP TABLE IF EXISTS `W_LICENSE`;
DROP TABLE IF EXISTS `W_ECODE`;
DROP TABLE IF EXISTS `W_ROLEMENU`;
DROP TABLE IF EXISTS `W_USERROLE`;
DROP TABLE IF EXISTS `W_USER`;
DROP TABLE IF EXISTS `W_ROLE`;
DROP TABLE IF EXISTS `W_MENU`;

DROP TABLE IF EXISTS `W_OPERATIONSTOCK`;
DROP TABLE IF EXISTS `W_STOCK`;

DROP TABLE IF EXISTS `W_PURCHASEORDER`;
DROP TABLE IF EXISTS `W_PURCHASEMATERIAL`;
DROP TABLE IF EXISTS `W_PURCHASE`;

DROP TABLE IF EXISTS `W_ORDERPRODUCTION`;
DROP TABLE IF EXISTS `W_ORDER`;

DROP TABLE IF EXISTS `W_PRODUCE`;

DROP TABLE IF EXISTS `W_PRODUCTIONMATERIAL`;
DROP TABLE IF EXISTS `W_PRODUCTION`;

DROP TABLE IF EXISTS `W_WAREHOUSE`;
DROP TABLE IF EXISTS `W_MATERIAL`;


-- LICENSE表
CREATE TABLE `W_LICENSE` (
	`LICENSE` VARCHAR (256) NOT NULL COMMENT 'LICENSE'
) ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = 'LICENSE表';


-- ECODE表
CREATE TABLE `W_ECODE` (
	`ECODETYPE` VARCHAR (20) NOT NULL COMMENT 'ECODE类型',
	`ECODE` VARCHAR (40) NOT NULL COMMENT 'ECODE键',
	`ECODETEXT` VARCHAR (60) NOT NULL COMMENT 'ECODE值',
	`DISPLAYORDINAL` INT (11) DEFAULT NULL COMMENT '顺序号',
	PRIMARY KEY (`ECODETYPE`, `ECODE`)
) ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = '枚举码';

INSERT INTO `W_ECODE` VALUES ('MATERIAL', '01', '原材料', '1');
INSERT INTO `W_ECODE` VALUES ('MATERIAL', '02', '半成品', '2');
INSERT INTO `W_ECODE` VALUES ('MATERIAL', '03', '成品', '3');
INSERT INTO `W_ECODE` VALUES ('MATERIAL', '04', '废品', '4');


-- 菜单表
CREATE TABLE `W_MENU` (
	`MENUCODE` VARCHAR (40) NOT NULL COMMENT '菜单编号',
	`MENUTEXT` VARCHAR (60) NOT NULL COMMENT '菜单描述',
	`PARENTMENUCODE` VARCHAR (40) DEFAULT NULL COMMENT '父菜单编号',
	`WEBURL` VARCHAR (120) DEFAULT NULL COMMENT 'URL路径',
	`MENUTYPE` VARCHAR (40) NOT NULL COMMENT '菜单类型',
	`DISPLAYORDINAL` INT (11) DEFAULT NULL COMMENT '顺序号',
	`CREATEUSER` VARCHAR (40) NOT NULL COMMENT '创建人',
	`CREATEDATETIME` DATETIME NOT NULL COMMENT '创建时间',
	`MODIFYUSER` VARCHAR (40) NOT NULL COMMENT '修改人',
	`MODIFYDATETIME` DATETIME NOT NULL COMMENT '修改时间',
	PRIMARY KEY (`MENUCODE`)
) ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = '菜单表';

INSERT INTO `W_MENU` VALUES ('ORDER_MANAGE', '订单管理', null, null, 'MODULE', '1', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('PRODUCTION_ORDER_MANAGE', '生产订单管理', 'ORDER_MANAGE', '/order/list', 'FORM', '1', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('ORDER_ADD', '新增订单', 'PRODUCTION_ORDER_MANAGE', null, 'BUTN', '1', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('ORDER_EDIT', '编辑订单', 'PRODUCTION_ORDER_MANAGE', null, 'BUTN', '2', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('ORDER_VIEW', '订单详情', 'PRODUCTION_ORDER_MANAGE', null, 'BUTN', '3', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('ORDER_CALCULATE', '订单计算', 'PRODUCTION_ORDER_MANAGE', null, 'BUTN', '4', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('ORDER_PRODUCE', '订单生成', 'PRODUCTION_ORDER_MANAGE', null, 'BUTN', '5', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('ORDER_DELETE', '删除订单', 'PRODUCTION_ORDER_MANAGE', null, 'BUTN', '6', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('PURCHASE_MANAGE', '采购清单明细', 'ORDER_MANAGE', '/purchase/list', 'FORM', '2', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('PURCHASE_ADD', '新增采购清单', 'PURCHASE_MANAGE', null, 'BUTN', '1', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('PURCHASE_EDIT', '编辑采购清单', 'PURCHASE_MANAGE', null, 'BUTN', '2', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('PURCHASE_VIEW', '采购清单详情', 'PURCHASE_MANAGE', null, 'BUTN', '3', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('PURCHASE_FINISH', '完成采购清单', 'PURCHASE_MANAGE', null, 'BUTN', '4', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('PURCHASE_DELETE', '删除采购清单', 'PURCHASE_MANAGE', null, 'BUTN', '5', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('PRODUCT_MANAGE', '生产表管理', 'ORDER_MANAGE', '/produce/list', 'FORM', '3', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('PRODUCT_ADD', '新增', 'PRODUCT_MANAGE', null, 'BUTN', '1', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('PRODUCT_EDIT', '编辑', 'PRODUCT_MANAGE', null, 'BUTN', '2', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('PRODUCT_DELETE', '删除', 'PRODUCT_MANAGE', null, 'BUTN', '3', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('PRODUCT_EXPORT', '导出', 'PRODUCT_MANAGE', null, 'BUTN', '4', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('OPERATION_STOCK', '出入库管理', null, null, 'MODULE', '2', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('INPUT_STOCK', '入库', 'OPERATION_STOCK', '/operationStock/input', 'FORM', '1', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('OUTPUT_STOCK', '出库', 'OPERATION_STOCK', '/operationStock/output', 'FORM', '2', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('STOCK_RECORD', '出入库查询', 'OPERATION_STOCK', '/operationStock/record', 'FORM', '3', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('STOCK_RECORD_EXPORT', '出入库导出', 'STOCK_RECORD', null, 'BUTN', '1', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('BASIC_DATA', '主数据管理', null, null, 'MODULE', '3', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('MATERIAL_MANAGE', '物料管理', 'BASIC_DATA', '/material/list', 'FORM', '1', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('MATERIAL_ADD', '新增物料', 'MATERIAL_MANAGE', null, 'BUTN', '1', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('MATERIAL_EDIT', '编辑物料', 'MATERIAL_MANAGE', null, 'BUTN', '2', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('MATERIAL_VIEW', '物料详情', 'MATERIAL_MANAGE', null, 'BUTN', '3', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('MATERIAL_DELETE', '删除物料', 'MATERIAL_MANAGE', null, 'BUTN', '4', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('PRODUCTION_MANAGE', '成品管理', 'BASIC_DATA', '/production/list', 'FORM', '2', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('PRODUCTION_ADD', '新增成品', 'PRODUCTION_MANAGE', null, 'BUTN', '1', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('PRODUCTION_EDIT', '编辑成品', 'PRODUCTION_MANAGE', null, 'BUTN', '2', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('PRODUCTION_VIEW', '成品详情', 'PRODUCTION_MANAGE', null, 'BUTN', '3', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('PRODUCTION_DELETE', '删除成品', 'PRODUCTION_MANAGE', null, 'BUTN', '4', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('WAREHOUSE_MANAGE', '仓库管理', 'BASIC_DATA', '/warehouse/list', 'FORM', '3', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('WAREHOUSE_ADD', '新增仓库', 'WAREHOUSE_MANAGE', null, 'BUTN', '1', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('WAREHOUSE_EDIT', '编辑仓库', 'WAREHOUSE_MANAGE', null, 'BUTN', '2', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('WAREHOUSE_VIEW', '仓库详情', 'WAREHOUSE_MANAGE', null, 'BUTN', '3', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('WAREHOUSE_DELETE', '删除仓库', 'WAREHOUSE_MANAGE', null, 'BUTN', '4', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('STOCK_MANAGE', '库存管理', 'BASIC_DATA', '/stock/list', 'FORM', '4', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('STOCK_ADD', '新增库存', 'STOCK_MANAGE', null, 'BUTN', '1', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('STOCK_EDIT', '编辑库存', 'STOCK_MANAGE', null, 'BUTN', '2', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('STOCK_VIEW', '库存详情', 'STOCK_MANAGE', null, 'BUTN', '3', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('STOCK_DELETE', '删除库存', 'STOCK_MANAGE', null, 'BUTN', '4', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('STOCK_EXPORT', '库存导出', 'STOCK_MANAGE', null, 'BUTN', '5', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('STOCK_SHIFT', '移库', 'STOCK_MANAGE', null, 'BUTN', '6', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('SYSTEM_MANAGE', '系统管理', null, null, 'MODULE', '4', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('USER_MANAGE', '用户管理', 'SYSTEM_MANAGE', '/user/list', 'FORM', '1', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('USER_ADD', '新增用户', 'USER_MANAGE', null, 'BUTN', '1', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('USER_EDIT', '编辑用户', 'USER_MANAGE', null, 'BUTN', '2', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('USER_VIEW', '用户详情', 'USER_MANAGE', null, 'BUTN', '3', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('USER_DELETE', '删除用户', 'USER_MANAGE', null, 'BUTN', '4', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('USER_RESET', '重置密码', 'USER_MANAGE', null, 'BUTN', '5', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('ROLE_MANAGE', '角色管理', 'SYSTEM_MANAGE', '/role/list', 'FORM', '2', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('ROLE_ADD', '新增角色', 'ROLE_MANAGE', null, 'BUTN', '1', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('ROLE_EDIT', '编辑角色', 'ROLE_MANAGE', null, 'BUTN', '2', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('ROLE_AUTHORIZE', '角色授权', 'ROLE_MANAGE', null, 'BUTN', '3', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('ROLE_VIEW', '角色详情', 'ROLE_MANAGE', null, 'BUTN', '4', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('ROLE_DELETE', '删除角色', 'ROLE_MANAGE', null, 'BUTN', '5', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('MENU_MANAGE', '菜单管理', 'SYSTEM_MANAGE', '/menu/tree', 'FORM', '3', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('MENU_EDIT', '编辑菜单', 'MENU_MANAGE', null, 'BUTN', '1', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('MENU_UP', '上移菜单', 'MENU_MANAGE', null, 'BUTN', '2', 'ADMIN', sysdate(), 'ADMIN', sysdate());
INSERT INTO `W_MENU` VALUES ('MENU_DOWN', '下移菜单', 'MENU_MANAGE', null, 'BUTN', '3', 'ADMIN', sysdate(), 'ADMIN', sysdate());


-- 角色表
CREATE TABLE `W_ROLE` (
	`ROLECODE` VARCHAR (40) NOT NULL COMMENT '角色编号',
	`ROLETEXT` VARCHAR (60) NOT NULL COMMENT '角色描述',
	`CREATEUSER` VARCHAR (40) NOT NULL COMMENT '创建人',
	`CREATEDATETIME` DATETIME NOT NULL COMMENT '创建时间',
	`MODIFYUSER` VARCHAR (40) NOT NULL COMMENT '修改人',
	`MODIFYDATETIME` DATETIME NOT NULL COMMENT '修改时间',
	PRIMARY KEY (`ROLECODE`)
) ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = '角色表';

INSERT INTO `W_ROLE` VALUES ('ADMIN', '管理员', 'ADMIN', sysdate(), 'ADMIN', sysdate());


-- 角色菜单关联表
CREATE TABLE `W_ROLEMENU` (
	`ROLECODE` VARCHAR (40) NOT NULL COMMENT '角色编号',
	`MENUCODE` VARCHAR (40) NOT NULL COMMENT '菜单编号',
	PRIMARY KEY (`ROLECODE`, `MENUCODE`),
	KEY `ROLEMENU_FK2` (`MENUCODE`),
	CONSTRAINT `ROLEMENU_FK1` FOREIGN KEY (`ROLECODE`) REFERENCES `W_ROLE` (`ROLECODE`),
	CONSTRAINT `ROLEMENU_FK2` FOREIGN KEY (`MENUCODE`) REFERENCES `W_MENU` (`MENUCODE`)
) ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = '角色菜单关联表';

INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'ORDER_MANAGE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'PRODUCTION_ORDER_MANAGE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'ORDER_ADD');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'ORDER_EDIT');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'ORDER_VIEW');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'ORDER_CALCULATE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'ORDER_PRODUCE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'ORDER_DELETE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'PURCHASE_MANAGE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'PURCHASE_ADD');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'PURCHASE_EDIT');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'PURCHASE_VIEW');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'PURCHASE_FINISH');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'PURCHASE_DELETE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'PRODUCT_MANAGE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'PRODUCT_ADD');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'PRODUCT_EDIT');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'PRODUCT_DELETE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'PRODUCT_EXPORT');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'OPERATION_STOCK');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'INPUT_STOCK');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'OUTPUT_STOCK');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'STOCK_RECORD');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'STOCK_RECORD_EXPORT');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'BASIC_DATA');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'MATERIAL_MANAGE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'MATERIAL_ADD');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'MATERIAL_EDIT');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'MATERIAL_VIEW');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'MATERIAL_DELETE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'PRODUCTION_MANAGE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'PRODUCTION_ADD');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'PRODUCTION_EDIT');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'PRODUCTION_VIEW');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'PRODUCTION_DELETE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'WAREHOUSE_MANAGE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'WAREHOUSE_ADD');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'WAREHOUSE_EDIT');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'WAREHOUSE_VIEW');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'WAREHOUSE_DELETE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'STOCK_MANAGE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'STOCK_ADD');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'STOCK_EDIT');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'STOCK_VIEW');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'STOCK_DELETE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'STOCK_EXPORT');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'STOCK_SHIFT');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'SYSTEM_MANAGE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'USER_MANAGE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'USER_ADD');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'USER_EDIT');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'USER_VIEW');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'USER_DELETE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'USER_RESET');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'ROLE_MANAGE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'ROLE_ADD');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'ROLE_EDIT');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'ROLE_AUTHORIZE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'ROLE_VIEW');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'ROLE_DELETE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'MENU_MANAGE');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'MENU_EDIT');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'MENU_UP');
INSERT INTO `W_ROLEMENU` VALUES ('ADMIN', 'MENU_DOWN');


-- 用户表
CREATE TABLE `W_USER` (
  `USERCODE` VARCHAR(40) NOT NULL COMMENT '用户编号',
  `USERTEXT` VARCHAR(60) NOT NULL COMMENT '用户描述',
  `PASSWORD` VARCHAR(128) NOT NULL COMMENT '密码',
  `CREATEUSER` VARCHAR(40) NOT NULL COMMENT '创建人',
  `CREATEDATETIME` DATE NOT NULL COMMENT '创建时间',
  `MODIFYUSER` VARCHAR(40) NOT NULL COMMENT '修改人',
  `MODIFYDATETIME` DATE NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`USERCODE`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='用户表';

INSERT INTO `W_USER` VALUES ('ADMIN', '超级管理员', '5690dddfa28ae085d23518a035707282', 'ADMIN', sysdate(), 'ADMIN', sysdate());


-- 用户角色关联表
CREATE TABLE `W_USERROLE` (
	`USERCODE` VARCHAR (40) NOT NULL COMMENT '用户编号',
	`ROLECODE` VARCHAR (40) NOT NULL COMMENT '角色编号',
	PRIMARY KEY (`USERCODE`, `ROLECODE`),
	KEY `USERROLE_FK2` (`ROLECODE`),
	CONSTRAINT `USERROLE_FK1` FOREIGN KEY (`USERCODE`) REFERENCES `W_USER` (`USERCODE`),
	CONSTRAINT `USERROLE_FK2` FOREIGN KEY (`ROLECODE`) REFERENCES `W_ROLE` (`ROLECODE`)
) ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = '用户角色关联表';

INSERT INTO `W_USERROLE` VALUES ('ADMIN', 'ADMIN');


-- 物料表
CREATE TABLE `W_MATERIAL` (
	`MATERIALCODE` VARCHAR (40) NOT NULL COMMENT '物料编号',
	`MATERIALTEXT` VARCHAR (60) NOT NULL COMMENT '物料描述',
	`MATERIALTYPE` VARCHAR (20) NOT NULL COMMENT '物料类型',
	`CREATEUSER` VARCHAR (40) NOT NULL COMMENT '创建人',
	`CREATEDATETIME` DATETIME NOT NULL COMMENT '创建时间',
	`MODIFYUSER` VARCHAR (40) NOT NULL COMMENT '修改人',
	`MODIFYDATETIME` DATETIME NOT NULL COMMENT '修改时间',
	PRIMARY KEY (`MATERIALCODE`)
) ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = '物料表';


-- 仓库表
CREATE TABLE `W_WAREHOUSE` (
	`WAREHOUSECODE` VARCHAR (40) NOT NULL COMMENT '仓库编号',
	`WAREHOUSETEXT` VARCHAR (60) NOT NULL COMMENT '仓库描述',
	`CREATEUSER` VARCHAR (40) NOT NULL COMMENT '创建人',
	`CREATEDATETIME` DATETIME NOT NULL COMMENT '创建时间',
	`MODIFYUSER` VARCHAR (40) NOT NULL COMMENT '修改人',
	`MODIFYDATETIME` DATETIME NOT NULL COMMENT '修改时间',
	PRIMARY KEY (`WAREHOUSECODE`)
) ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = '仓库表';


-- 库存表
CREATE TABLE `W_STOCK` (
	`WAREHOUSECODE` VARCHAR (40) NOT NULL COMMENT '仓库编号',
	`MATERIALCODE` VARCHAR (60) NOT NULL COMMENT '物料编号',
	`STOCK` INT (11) NOT NULL COMMENT '库存',
	`EDITOR` VARCHAR (40) NOT NULL COMMENT '编辑人',
	`EDITTIME` DATETIME NOT NULL COMMENT '编辑时间',
	`CREATEUSER` VARCHAR (40) NOT NULL COMMENT '创建人',
	`CREATEDATETIME` DATETIME NOT NULL COMMENT '创建时间',
	`MODIFYUSER` VARCHAR (40) NOT NULL COMMENT '修改人',
	`MODIFYDATETIME` DATETIME NOT NULL COMMENT '修改时间',
	PRIMARY KEY (`WAREHOUSECODE`, `MATERIALCODE`),
	KEY `STOCK_FK2` (`MATERIALCODE`),
	CONSTRAINT `STOCK_FK1` FOREIGN KEY (`WAREHOUSECODE`) REFERENCES `W_WAREHOUSE` (`WAREHOUSECODE`),
	CONSTRAINT `STOCK_FK2` FOREIGN KEY (`MATERIALCODE`) REFERENCES `W_MATERIAL` (`MATERIALCODE`)
) ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = '库存表';


-- 出入库记录表
CREATE TABLE `W_OPERATIONSTOCK` (
	`RECEIPTCODE` VARCHAR (40) NOT NULL COMMENT '单据编号',
	`WAREHOUSECODE` VARCHAR (40) NOT NULL COMMENT '仓库编号',
	`MATERIALCODE` VARCHAR (40) NOT NULL COMMENT '物料编号',
	`QUANTITY` INT (11) NOT NULL COMMENT '数量',
	`PRICE` DOUBLE (11, 2) NULL COMMENT '单价',
	`OPERATIONTYPE` VARCHAR (40) NOT NULL COMMENT '操作类型',
	`STOVECODE` VARCHAR (40) NULL COMMENT '炉号',
	`BATCHCODE` VARCHAR (40) NULL COMMENT '批号',
	`WEIGHT` INT (11) NULL COMMENT '重量',
	`KGNUM` INT (11) NULL COMMENT '公斤数',
	`PRODUCTCODE` VARCHAR (40) NULL COMMENT '生产编号',
	`OPERATOR` VARCHAR (40) NULL COMMENT '经办人',
	`CREATEUSER` VARCHAR (40) NOT NULL COMMENT '创建人',
	`CREATEDATETIME` DATETIME NOT NULL COMMENT '创建时间',
	`MODIFYUSER` VARCHAR (40) NOT NULL COMMENT '修改人',
	`MODIFYDATETIME` DATETIME NOT NULL COMMENT '修改时间',
	PRIMARY KEY (`RECEIPTCODE`,`WAREHOUSECODE`,`MATERIALCODE`),
	KEY `OPERATIONSTOC_FK1` (`WAREHOUSECODE`),
  KEY `OPERATIONSTOC_FK2` (`MATERIALCODE`),
  CONSTRAINT `OPERATIONSTOC_FK1` FOREIGN KEY (`WAREHOUSECODE`) REFERENCES `W_WAREHOUSE` (`WAREHOUSECODE`),
  CONSTRAINT `OPERATIONSTOC_FK2` FOREIGN KEY (`MATERIALCODE`) REFERENCES `W_MATERIAL` (`MATERIALCODE`)
) ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = '出入库记录表';


-- 成品表
CREATE TABLE `W_PRODUCTION` (
	`PRODUCTIONCODE` VARCHAR (40) NOT NULL COMMENT '成品编号',
	`CREATEUSER` VARCHAR (40) NOT NULL COMMENT '创建人',
	`CREATEDATETIME` DATETIME NOT NULL COMMENT '创建时间',
	`MODIFYUSER` VARCHAR (40) NOT NULL COMMENT '修改人',
	`MODIFYDATETIME` DATETIME NOT NULL COMMENT '修改时间',
	PRIMARY KEY (`PRODUCTIONCODE`),
	CONSTRAINT `PRODUCTION_FK` FOREIGN KEY (`PRODUCTIONCODE`) REFERENCES `W_MATERIAL` (`MATERIALCODE`)
) ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = '成品表';


-- 成品物料关联表
CREATE TABLE `W_PRODUCTIONMATERIAL` (
	`PRODUCTIONCODE` VARCHAR (40) NOT NULL COMMENT '成品编号',
	`MATERIALCODE` VARCHAR (40) NOT NULL COMMENT '物料编号',
	`QUANTITY` INT (11) NOT NULL COMMENT '数量',
	PRIMARY KEY (`PRODUCTIONCODE`, `MATERIALCODE`),
	KEY `PRODUCTIONMATERIAL_PK2` (`MATERIALCODE`),
	CONSTRAINT `PRODUCTIONMATERIAL_PK1` FOREIGN KEY (`PRODUCTIONCODE`) REFERENCES `W_PRODUCTION` (`PRODUCTIONCODE`),
	CONSTRAINT `PRODUCTIONMATERIAL_PK2` FOREIGN KEY (`MATERIALCODE`) REFERENCES `W_MATERIAL` (`MATERIALCODE`)
) ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = '成品物料关联表';


-- 订单表
CREATE TABLE `W_ORDER` (
	`ORDERCODE` VARCHAR (40) NOT NULL COMMENT '订单编号',
	`ORDERTEXT` VARCHAR (60) NOT NULL COMMENT '订单描述',
	`PRODUCTDATE` DATE NOT NULL COMMENT '生产日期',
	`ORDERSTATUS` VARCHAR (20) NOT NULL COMMENT '订单状态',
	`CREATEUSER` VARCHAR (40) NOT NULL COMMENT '创建人',
	`CREATEDATETIME` DATETIME NOT NULL COMMENT '创建时间',
	`MODIFYUSER` VARCHAR (40) NOT NULL COMMENT '修改人',
	`MODIFYDATETIME` DATETIME NOT NULL COMMENT '修改时间',
	PRIMARY KEY (`ORDERCODE`)
) ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = '订单表';


-- 订单成品关联表
CREATE TABLE `W_ORDERPRODUCTION` (
	`ORDERCODE` VARCHAR (40) NOT NULL COMMENT '订单编号',
	`PRODUCTIONCODE` VARCHAR (40) NOT NULL COMMENT '成品编号',
	`QUANTITY` INT (11) NOT NULL COMMENT '数量',
	PRIMARY KEY (`ORDERCODE`, `PRODUCTIONCODE`),
	KEY `ORDERPRODUCTION_FK2` (`PRODUCTIONCODE`),
	CONSTRAINT `ORDERPRODUCTION_FK1` FOREIGN KEY (`ORDERCODE`) REFERENCES `W_ORDER` (`ORDERCODE`),
	CONSTRAINT `ORDERPRODUCTION_FK2` FOREIGN KEY (`PRODUCTIONCODE`) REFERENCES `W_PRODUCTION` (`PRODUCTIONCODE`)
) ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = '订单成品关联表';


-- 采购清单表
CREATE TABLE `W_PURCHASE` (
	`PURCHASECODE` VARCHAR (40) NOT NULL COMMENT '采购编号',
	`PURCHASETYPE` VARCHAR (20) NOT NULL COMMENT '采购类型',
	`PURCHASESTATUS` VARCHAR (20) NOT NULL COMMENT '采购状态',
	`CREATEUSER` VARCHAR (40) NOT NULL COMMENT '创建人',
	`CREATEDATETIME` DATETIME NOT NULL COMMENT '创建时间',
	`MODIFYUSER` VARCHAR (40) NOT NULL COMMENT '修改人',
	`MODIFYDATETIME` DATETIME NOT NULL COMMENT '修改时间',
	PRIMARY KEY (`PURCHASECODE`)
) ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = '采购清单表';


-- 采购订单关联表
CREATE TABLE `W_PURCHASEORDER` (
	`PURCHASECODE` VARCHAR (40) NOT NULL COMMENT '采购编号',
	`ORDERCODE` VARCHAR (40) NOT NULL COMMENT '订单编号',
	PRIMARY KEY (`PURCHASECODE`, `ORDERCODE`),
	KEY `PURCHASEORDER_FK2` (`ORDERCODE`),
	CONSTRAINT `PURCHASEORDER_FK1` FOREIGN KEY (`PURCHASECODE`) REFERENCES `W_PURCHASE` (`PURCHASECODE`),
	CONSTRAINT `PURCHASEORDER_FK2` FOREIGN KEY (`ORDERCODE`) REFERENCES `W_ORDER` (`ORDERCODE`)
) ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = '采购订单关联表';


-- 采购物料关联表
CREATE TABLE `W_PURCHASEMATERIAL` (
	`PURCHASECODE` VARCHAR (40) NOT NULL COMMENT '采购编号',
	`MATERIALCODE` VARCHAR (20) NOT NULL COMMENT '物料编号',
	`QUANTITY` INT (11) NOT NULL COMMENT '数量',
	PRIMARY KEY (`PURCHASECODE`, `MATERIALCODE`),
	KEY `PURCHASEMATERIAL_FK2` (`MATERIALCODE`),
	CONSTRAINT `PURCHASEMATERIAL_FK1` FOREIGN KEY (`PURCHASECODE`) REFERENCES `W_PURCHASE` (`PURCHASECODE`),
	CONSTRAINT `PURCHASEMATERIAL_FK2` FOREIGN KEY (`MATERIALCODE`) REFERENCES `W_MATERIAL` (`MATERIALCODE`)
) ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = '采购物料关联表';


-- 生产表
CREATE TABLE `W_PRODUCE` (
	`PRODUCECODE` VARCHAR (40) NOT NULL COMMENT '生产编号',
	`PRODUCTIONCODE` VARCHAR (40) NOT NULL COMMENT '成品编号',
	`PRODUCEDATE` DATE NOT NULL COMMENT '生产日期',
	`QUANTITY` INT (11) NOT NULL COMMENT '数量',
	`CREATEUSER` VARCHAR (40) NOT NULL COMMENT '创建人',
	`CREATEDATETIME` DATETIME NOT NULL COMMENT '创建时间',
	`MODIFYUSER` VARCHAR (40) NOT NULL COMMENT '修改人',
	`MODIFYDATETIME` DATETIME NOT NULL COMMENT '修改时间',
	CONSTRAINT `PRODUCE_FK` FOREIGN KEY (`PRODUCTIONCODE`) REFERENCES `W_PRODUCTION` (`PRODUCTIONCODE`)
) ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = '生产表';