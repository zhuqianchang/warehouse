-- LICENSE表
create table W_LICENSE
(
  LICENSE NVARCHAR2(256) not null
)
;
comment on table W_LICENSE
  is 'LICENSE表';
comment on column W_LICENSE.LICENSE
  is 'LICENSE';


-- 枚举码
create table W_ECODE
(
  ECODETYPE      NVARCHAR2(20) not null,
  ECODE          NVARCHAR2(40) not null,
  ECODETEXT      NVARCHAR2(60) not null,
  DISPLAYORDINAL NUMBER(11)
)
;
comment on table W_ECODE
  is '枚举码';
comment on column W_ECODE.ECODETYPE
  is 'ECODE类型';
comment on column W_ECODE.ECODE
  is 'ECODE键';
comment on column W_ECODE.ECODETEXT
  is 'ECODE值';
comment on column W_ECODE.DISPLAYORDINAL
  is '顺序号';
alter table W_ECODE
  add constraint W_ECODE_PK primary key (ECODETYPE, ECODE);

INSERT INTO W_ECODE VALUES ('MATERIAL', '01', '原材料', '1');
INSERT INTO W_ECODE VALUES ('MATERIAL', '02', '半成品', '2');
INSERT INTO W_ECODE VALUES ('MATERIAL', '03', '成品', '3');
INSERT INTO W_ECODE VALUES ('MATERIAL', '04', '废品', '4');


-- 菜单表
create table W_MENU
(
  MENUCODE       NVARCHAR2(40) not null,
  MENUTEXT       NVARCHAR2(60) not null,
  PARENTMENUCODE NVARCHAR2(40),
  WEBURL         NVARCHAR2(120),
  MENUTYPE       NVARCHAR2(40) not null,
  DISPLAYORDINAL NUMBER(11),
  CREATEUSER     NVARCHAR2(40) not null,
  CREATEDATETIME DATE not null,
  MODIFYUSER     NVARCHAR2(40) not null,
  MODIFYDATETIME DATE not null
)
;
comment on table W_MENU
  is '菜单表';
comment on column W_MENU.MENUCODE
  is '菜单编号';
comment on column W_MENU.MENUTEXT
  is '菜单描述';
comment on column W_MENU.PARENTMENUCODE
  is '父菜单编号';
comment on column W_MENU.WEBURL
  is 'URL路径';
comment on column W_MENU.MENUTYPE
  is '菜单类型';
comment on column W_MENU.DISPLAYORDINAL
  is '顺序号';
comment on column W_MENU.CREATEUSER
  is '创建人';
comment on column W_MENU.CREATEDATETIME
  is '创建时间';
comment on column W_MENU.MODIFYUSER
  is '修改人';
comment on column W_MENU.MODIFYDATETIME
  is '修改时间';
alter table W_MENU
  add constraint W_MENU_PK primary key (MENUCODE);

INSERT INTO W_MENU VALUES ('ORDER_MANAGE', '订单管理', null, null, 'MODULE', '1', 'ADMIN', sysdate, 'ADMIN', sysdate);
INSERT INTO W_MENU VALUES ('PRODUCTION_ORDER_MANAGE', '生产订单管理', 'ORDER_MANAGE', '/order/list', 'FORM', '1', 'ADMIN', sysdate, 'ADMIN', sysdate);
INSERT INTO W_MENU VALUES ('PURCHASE_MANAGE', '采购清单明细', 'ORDER_MANAGE', '/purchase/list', 'FORM', '2', 'ADMIN', sysdate, 'ADMIN', sysdate);
INSERT INTO W_MENU VALUES ('OPERATION_STOCK', '出入库管理', null, null, 'MODULE', '2', 'ADMIN', sysdate, 'ADMIN', sysdate);
INSERT INTO W_MENU VALUES ('INPUT_STOCK', '入库', 'OPERATION_STOCK', '/operationStock/input', 'FORM', '1', 'ADMIN', sysdate, 'ADMIN', sysdate);
INSERT INTO W_MENU VALUES ('OUTPUT_STOCK', '出库', 'OPERATION_STOCK', '/operationStock/output', 'FORM', '2', 'ADMIN', sysdate, 'ADMIN', sysdate);
INSERT INTO W_MENU VALUES ('STOCK_RECORD', '出入库查询', 'OPERATION_STOCK', '/operationStock/record', 'FORM', '3', 'ADMIN', sysdate, 'ADMIN', sysdate);
INSERT INTO W_MENU VALUES ('BASIC_DATA', '主数据管理', null, null, 'MODULE', '3', 'ADMIN', sysdate, 'ADMIN', sysdate);
INSERT INTO W_MENU VALUES ('MATERIAL_MANAGE', '物料管理', 'BASIC_DATA', '/material/list', 'FORM', '1', 'ADMIN', sysdate, 'ADMIN', sysdate);
INSERT INTO W_MENU VALUES ('PRODUCTION_MANAGE', '成品管理', 'BASIC_DATA', '/production/list', 'FORM', '2', 'ADMIN', sysdate, 'ADMIN', sysdate);
INSERT INTO W_MENU VALUES ('WAREHOUSE_MANAGE', '仓库管理', 'BASIC_DATA', '/warehouse/list', 'FORM', '3', 'ADMIN', sysdate, 'ADMIN', sysdate);
INSERT INTO W_MENU VALUES ('STOCK_MANAGE', '库存管理', 'BASIC_DATA', '/stock/list', 'FORM', '4', 'ADMIN', sysdate, 'ADMIN', sysdate);
INSERT INTO W_MENU VALUES ('SYSTEM_MANAGE', '系统管理', null, null, 'MODULE', '4', 'ADMIN', sysdate, 'ADMIN', sysdate);
INSERT INTO W_MENU VALUES ('USER_MANAGE', '用户管理', 'SYSTEM_MANAGE', '/user/list', 'FORM', '1', 'ADMIN', sysdate, 'ADMIN', sysdate);
INSERT INTO W_MENU VALUES ('ROLE_MANAGE', '角色管理', 'SYSTEM_MANAGE', '/role/list', 'FORM', '2', 'ADMIN', sysdate, 'ADMIN', sysdate);
INSERT INTO W_MENU VALUES ('MENU_MANAGE', '菜单管理', 'SYSTEM_MANAGE', '/menu/tree', 'FORM', '3', 'ADMIN', sysdate, 'ADMIN', sysdate);


-- 角色表
create table W_ROLE
(
  ROLECODE       NVARCHAR2(40) not null,
  ROLETEXT       NVARCHAR2(60) not null,
  CREATEUSER     NVARCHAR2(40) not null,
  CREATEDATETIME DATE not null,
  MODIFYUSER     NVARCHAR2(40) not null,
  MODIFYDATETIME DATE not null
)
;
comment on table W_ROLE
  is '角色表';
comment on column W_ROLE.ROLECODE
  is '角色编号';
comment on column W_ROLE.ROLETEXT
  is '角色描述';
comment on column W_ROLE.CREATEUSER
  is '创建人';
comment on column W_ROLE.CREATEDATETIME
  is '创建时间';
comment on column W_ROLE.MODIFYUSER
  is '修改人';
comment on column W_ROLE.MODIFYDATETIME
  is '修改时间';
alter table W_ROLE
  add constraint W_ROLE_PK primary key (ROLECODE);

INSERT INTO W_ROLE VALUES ('ADMIN', '管理员', 'ADMIN', sysdate, 'ADMIN', sysdate);


-- 角色菜单关联表
create table W_ROLEMENU
(
  ROLECODE NVARCHAR2(40) not null,
  MENUCODE NVARCHAR2(40) not null
)
;
comment on table W_ROLEMENU
  is '角色菜单关联表';
comment on column W_ROLEMENU.ROLECODE
  is '角色编号';
comment on column W_ROLEMENU.MENUCODE
  is '菜单编号';
alter table W_ROLEMENU
  add constraint W_ROLEMENU_PK primary key (ROLECODE, MENUCODE);

INSERT INTO W_ROLEMENU VALUES ('ADMIN', 'BASIC_DATA');
INSERT INTO W_ROLEMENU VALUES ('ADMIN', 'INPUT_STOCK');
INSERT INTO W_ROLEMENU VALUES ('ADMIN', 'MATERIAL_MANAGE');
INSERT INTO W_ROLEMENU VALUES ('ADMIN', 'MENU_MANAGE');
INSERT INTO W_ROLEMENU VALUES ('ADMIN', 'OPERATION_STOCK');
INSERT INTO W_ROLEMENU VALUES ('ADMIN', 'ORDER_MANAGE');
INSERT INTO W_ROLEMENU VALUES ('ADMIN', 'OUTPUT_STOCK');
INSERT INTO W_ROLEMENU VALUES ('ADMIN', 'PRODUCTION_MANAGE');
INSERT INTO W_ROLEMENU VALUES ('ADMIN', 'PRODUCTION_ORDER_MANAGE');
INSERT INTO W_ROLEMENU VALUES ('ADMIN', 'PURCHASE_MANAGE');
INSERT INTO W_ROLEMENU VALUES ('ADMIN', 'ROLE_MANAGE');
INSERT INTO W_ROLEMENU VALUES ('ADMIN', 'STOCK_MANAGE');
INSERT INTO W_ROLEMENU VALUES ('ADMIN', 'STOCK_RECORD');
INSERT INTO W_ROLEMENU VALUES ('ADMIN', 'SYSTEM_MANAGE');
INSERT INTO W_ROLEMENU VALUES ('ADMIN', 'USER_MANAGE');
INSERT INTO W_ROLEMENU VALUES ('ADMIN', 'WAREHOUSE_MANAGE');


-- 用户表
create table W_USER
(
  USERCODE       NVARCHAR2(40) not null,
  USERTEXT       NVARCHAR2(60) not null,
  PASSWORD       NVARCHAR2(128) not null,
  CREATEUSER     NVARCHAR2(40) not null,
  CREATEDATETIME DATE not null,
  MODIFYUSER     NVARCHAR2(40) not null,
  MODIFYDATETIME DATE not null
)
;
comment on table W_USER
  is '用户表';
comment on column W_USER.USERCODE
  is '用户编号';
comment on column W_USER.USERTEXT
  is '用户描述';
comment on column W_USER.PASSWORD
  is '密码';
comment on column W_USER.CREATEUSER
  is '创建人';
comment on column W_USER.CREATEDATETIME
  is '创建时间';
comment on column W_USER.MODIFYUSER
  is '修改人';
comment on column W_USER.MODIFYDATETIME
  is '修改时间';
alter table W_USER
  add constraint W_USER_PK primary key (USERCODE);

INSERT INTO W_USER VALUES ('ADMIN', '超级管理员', '5690dddfa28ae085d23518a035707282', 'ADMIN', sysdate, 'ADMIN', sysdate);



-- 用户角色关联表
create table W_USERROLE
(
  USERCODE NVARCHAR2(40) not null,
  ROLECODE NVARCHAR2(40) not null
)
;
comment on table W_USERROLE
  is '用户角色关联表';
comment on column W_USERROLE.USERCODE
  is '用户编号';
comment on column W_USERROLE.ROLECODE
  is '角色编号';
alter table W_USERROLE
  add constraint W_USERROLE_PK primary key (USERCODE, ROLECODE);
alter table W_USERROLE
  add constraint W_USERROLE_FK1 foreign key (USERCODE)
  references W_USER (USERCODE);
alter table W_USERROLE
  add constraint W_USERROLE_FK2 foreign key (ROLECODE)
  references W_ROLE (ROLECODE);

INSERT INTO W_USERROLE VALUES ('ADMIN', 'ADMIN');


-- 物料表
create table W_MATERIAL
(
  MATERIALCODE   NVARCHAR2(40) not null,
  MATERIALTEXT   NVARCHAR2(60) not null,
  MATERIALTYPE   NVARCHAR2(20) not null,
  CREATEUSER     NVARCHAR2(40) not null,
  CREATEDATETIME DATE not null,
  MODIFYUSER     NVARCHAR2(40) not null,
  MODIFYDATETIME DATE not null
)
;
comment on table W_MATERIAL
  is '物料表';
comment on column W_MATERIAL.MATERIALCODE
  is '物料编号';
comment on column W_MATERIAL.MATERIALTEXT
  is '物料描述';
comment on column W_MATERIAL.MATERIALTYPE
  is '物料类型';
comment on column W_MATERIAL.CREATEUSER
  is '创建人';
comment on column W_MATERIAL.CREATEDATETIME
  is '创建时间';
comment on column W_MATERIAL.MODIFYUSER
  is '修改人';
comment on column W_MATERIAL.MODIFYDATETIME
  is '修改时间';
alter table W_MATERIAL
  add constraint W_MATERIAL_PK primary key (MATERIALCODE);


-- 仓库表
create table W_WAREHOUSE
(
  WAREHOUSECODE  NVARCHAR2(40) not null,
  WAREHOUSETEXT  NVARCHAR2(60) not null,
  CREATEUSER     NVARCHAR2(40) not null,
  CREATEDATETIME DATE not null,
  MODIFYUSER     NVARCHAR2(40) not null,
  MODIFYDATETIME DATE not null
)
;
comment on table W_WAREHOUSE
  is '仓库表';
comment on column W_WAREHOUSE.WAREHOUSECODE
  is '仓库编号';
comment on column W_WAREHOUSE.WAREHOUSETEXT
  is '仓库描述';
comment on column W_WAREHOUSE.CREATEUSER
  is '创建人';
comment on column W_WAREHOUSE.CREATEDATETIME
  is '创建时间';
comment on column W_WAREHOUSE.MODIFYUSER
  is '修改人';
comment on column W_WAREHOUSE.MODIFYDATETIME
  is '修改时间';
alter table W_WAREHOUSE
  add constraint W_WAREHOUSE_PK primary key (WAREHOUSECODE);


-- 库存表
create table W_STOCK
(
  WAREHOUSECODE  NVARCHAR2(40) not null,
  MATERIALCODE   NVARCHAR2(60) not null,
  STOCK          NUMBER(11) not null,
  EDITOR         NVARCHAR2(40) not null,
  EDITTIME       DATE not null,
  CREATEUSER     NVARCHAR2(40) not null,
  CREATEDATETIME DATE not null,
  MODIFYUSER     NVARCHAR2(40) not null,
  MODIFYDATETIME DATE not null
)
;
comment on table W_STOCK
  is '库存表';
comment on column W_STOCK.WAREHOUSECODE
  is '仓库编号';
comment on column W_STOCK.MATERIALCODE
  is '物料编号';
comment on column W_STOCK.STOCK
  is '库存';
comment on column W_STOCK.EDITOR
  is '编辑人';
comment on column W_STOCK.EDITTIME
  is '编辑时间';
comment on column W_STOCK.CREATEUSER
  is '创建人';
comment on column W_STOCK.CREATEDATETIME
  is '创建时间';
comment on column W_STOCK.MODIFYUSER
  is '修改人';
comment on column W_STOCK.MODIFYDATETIME
  is '修改时间';
alter table W_STOCK
  add constraint W_STOCK_PK primary key (WAREHOUSECODE, MATERIALCODE);
alter table W_STOCK
  add constraint W_STOCK_FK1 foreign key (WAREHOUSECODE)
  references W_WAREHOUSE (WAREHOUSECODE);
alter table W_STOCK
  add constraint W_STOCK_FK2 foreign key (MATERIALCODE)
  references W_MATERIAL (MATERIALCODE);


-- 出入库记录表
create table W_OPERATIONSTOCK
(
  RECEIPTCODE    NVARCHAR2(40) not null,
  WAREHOUSECODE  NVARCHAR2(40) not null,
  MATERIALCODE   NVARCHAR2(40) not null,
  QUANTITY       NUMBER(11) not null,
  OPERATIONTYPE  NVARCHAR2(40) not null,
  CREATEUSER     NVARCHAR2(40) not null,
  CREATEDATETIME DATE not null,
  MODIFYUSER     NVARCHAR2(40) not null,
  MODIFYDATETIME DATE not null
)
;
comment on table W_OPERATIONSTOCK
  is '出入库记录表';
comment on column W_OPERATIONSTOCK.RECEIPTCODE
  is '单据编号';
comment on column W_OPERATIONSTOCK.WAREHOUSECODE
  is '仓库编号';
comment on column W_OPERATIONSTOCK.MATERIALCODE
  is '物料编号';
comment on column W_OPERATIONSTOCK.QUANTITY
  is '数量';
comment on column W_OPERATIONSTOCK.OPERATIONTYPE
  is '操作类型';
comment on column W_OPERATIONSTOCK.CREATEUSER
  is '创建人';
comment on column W_OPERATIONSTOCK.CREATEDATETIME
  is '创建时间';
comment on column W_OPERATIONSTOCK.MODIFYUSER
  is '修改人';
comment on column W_OPERATIONSTOCK.MODIFYDATETIME
  is '修改时间';
alter table W_OPERATIONSTOCK
  add constraint W_OPERATIONSTOCK_PK primary key (RECEIPTCODE, WAREHOUSECODE, MATERIALCODE);
alter table W_OPERATIONSTOCK
  add constraint W_OPERATIONSTOCK_FK1 foreign key (WAREHOUSECODE)
  references W_WAREHOUSE (WAREHOUSECODE);
alter table W_OPERATIONSTOCK
  add constraint W_OPERATIONSTOCK_FK2 foreign key (MATERIALCODE)
  references W_MATERIAL (MATERIALCODE);


-- 成品表
create table W_PRODUCTION
(
  PRODUCTIONCODE NVARCHAR2(40) not null,
  CREATEUSER     NVARCHAR2(40) not null,
  CREATEDATETIME DATE not null,
  MODIFYUSER     NVARCHAR2(40) not null,
  MODIFYDATETIME DATE not null
)
;
comment on table W_PRODUCTION
  is '成品表';
comment on column W_PRODUCTION.PRODUCTIONCODE
  is '成品编号';
comment on column W_PRODUCTION.CREATEUSER
  is '创建人';
comment on column W_PRODUCTION.CREATEDATETIME
  is '创建时间';
comment on column W_PRODUCTION.MODIFYUSER
  is '修改人';
comment on column W_PRODUCTION.MODIFYDATETIME
  is '修改时间';
alter table W_PRODUCTION
  add constraint W_PRODUCTION_PK primary key (PRODUCTIONCODE);
alter table W_PRODUCTION
  add constraint W_PRODUCTION_FK1 foreign key (PRODUCTIONCODE)
  references W_MATERIAL (MATERIALCODE);


-- 成品物料关联表
create table W_PRODUCTIONMATERIAL
(
  PRODUCTIONCODE NVARCHAR2(40) not null,
  MATERIALCODE   NVARCHAR2(40) not null,
  QUANTITY       NUMBER(11) not null
)
;
comment on table W_PRODUCTIONMATERIAL
  is '成品物料关联表';
comment on column W_PRODUCTIONMATERIAL.PRODUCTIONCODE
  is '成品编号';
comment on column W_PRODUCTIONMATERIAL.MATERIALCODE
  is '物料编号';
comment on column W_PRODUCTIONMATERIAL.QUANTITY
  is '数量';
alter table W_PRODUCTIONMATERIAL
  add constraint W_PRODUCTIONMATERIAL_PK primary key (PRODUCTIONCODE, MATERIALCODE);
alter table W_PRODUCTIONMATERIAL
  add constraint W_PRODUCTIONMATERIAL_FK1 foreign key (PRODUCTIONCODE)
  references W_PRODUCTION (PRODUCTIONCODE);
alter table W_PRODUCTIONMATERIAL
  add constraint W_PRODUCTIONMATERIAL_FK2 foreign key (MATERIALCODE)
  references W_MATERIAL (MATERIALCODE);


-- 订单表
create table W_ORDER
(
  ORDERCODE      NVARCHAR2(40) not null,
  ORDERTEXT      NVARCHAR2(60) not null,
  PRODUCTDATE    DATE not null,
  ORDERSTATUS    NVARCHAR2(20) not null,
  CREATEUSER     NVARCHAR2(40) not null,
  CREATEDATETIME DATE not null,
  MODIFYUSER     NVARCHAR2(40) not null,
  MODIFYDATETIME DATE not null
)
;
comment on table W_ORDER
  is '订单表';
comment on column W_ORDER.ORDERCODE
  is '订单编号';
comment on column W_ORDER.ORDERTEXT
  is '订单描述';
comment on column W_ORDER.PRODUCTDATE
  is '生产日期';
comment on column W_ORDER.ORDERSTATUS
  is '订单状态';
comment on column W_ORDER.CREATEUSER
  is '创建人';
comment on column W_ORDER.CREATEDATETIME
  is '创建时间';
comment on column W_ORDER.MODIFYUSER
  is '修改人';
comment on column W_ORDER.MODIFYDATETIME
  is '修改时间';
alter table W_ORDER
  add constraint W_ORDER_PK primary key (ORDERCODE);


-- 订单成品关联表
create table W_ORDERPRODUCTION
(
  ORDERCODE      NVARCHAR2(40) not null,
  PRODUCTIONCODE NVARCHAR2(40) not null,
  QUANTITY       NUMBER(11) not null
)
;
comment on table W_ORDERPRODUCTION
  is '订单成品关联表';
comment on column W_ORDERPRODUCTION.ORDERCODE
  is '订单编号';
comment on column W_ORDERPRODUCTION.PRODUCTIONCODE
  is '成品编号';
comment on column W_ORDERPRODUCTION.QUANTITY
  is '数量';
alter table W_ORDERPRODUCTION
  add constraint W_ORDERPRODUCTION_PK primary key (ORDERCODE, PRODUCTIONCODE);
alter table W_ORDERPRODUCTION
  add constraint W_ORDERPRODUCTION_FK1 foreign key (ORDERCODE)
  references W_ORDER (ORDERCODE);
alter table W_ORDERPRODUCTION
  add constraint W_ORDERPRODUCTION_FK2 foreign key (PRODUCTIONCODE)
  references W_PRODUCTION (PRODUCTIONCODE);


-- 采购清单表
create table W_PURCHASE
(
  PURCHASECODE   NVARCHAR2(40) not null,
  PURCHASETYPE   NVARCHAR2(20) not null,
  PURCHASESTATUS NVARCHAR2(20) not null,
  CREATEUSER     NVARCHAR2(40) not null,
  CREATEDATETIME DATE not null,
  MODIFYUSER     NVARCHAR2(40) not null,
  MODIFYDATETIME DATE not null
)
;
comment on table W_PURCHASE
  is '采购清单表';
comment on column W_PURCHASE.PURCHASECODE
  is '采购编号';
comment on column W_PURCHASE.PURCHASETYPE
  is '采购类型';
comment on column W_PURCHASE.PURCHASESTATUS
  is '采购状态';
comment on column W_PURCHASE.CREATEUSER
  is '创建人';
comment on column W_PURCHASE.CREATEDATETIME
  is '创建时间';
comment on column W_PURCHASE.MODIFYUSER
  is '修改人';
comment on column W_PURCHASE.MODIFYDATETIME
  is '修改时间';
alter table W_PURCHASE
  add constraint W_PURCHASE_PK primary key (PURCHASECODE);


-- 采购订单关联表
create table W_PURCHASEORDER
(
  PURCHASECODE NVARCHAR2(40) not null,
  ORDERCODE    NVARCHAR2(40) not null
)
;
comment on table W_PURCHASEORDER
  is '采购订单关联表';
comment on column W_PURCHASEORDER.PURCHASECODE
  is '采购编号';
comment on column W_PURCHASEORDER.ORDERCODE
  is '订单编号';
alter table W_PURCHASEORDER
  add constraint W_PURCHASEORDER_PK primary key (PURCHASECODE, ORDERCODE);
alter table W_PURCHASEORDER
  add constraint W_PURCHASEORDER_FK1 foreign key (PURCHASECODE)
  references W_PURCHASE (PURCHASECODE);
alter table W_PURCHASEORDER
  add constraint W_PURCHASEORDER_FK2 foreign key (ORDERCODE)
  references W_ORDER (ORDERCODE);


-- 采购物料关联表
create table W_PURCHASEMATERIAL
(
  PURCHASECODE NVARCHAR2(40) not null,
  MATERIALCODE NVARCHAR2(20) not null,
  QUANTITY     NUMBER(11) not null
)
;
comment on table W_PURCHASEMATERIAL
  is '采购物料关联表';
comment on column W_PURCHASEMATERIAL.PURCHASECODE
  is '采购编号';
comment on column W_PURCHASEMATERIAL.MATERIALCODE
  is '物料编号';
comment on column W_PURCHASEMATERIAL.QUANTITY
  is '数量';
alter table W_PURCHASEMATERIAL
  add constraint W_PURCHASEMATERIAL_PK primary key (PURCHASECODE, MATERIALCODE);
alter table W_PURCHASEMATERIAL
  add constraint W_PURCHASEMATERIAL_FK1 foreign key (PURCHASECODE)
  references W_PURCHASE (PURCHASECODE);
alter table W_PURCHASEMATERIAL
  add constraint W_PURCHASEMATERIAL_FK2 foreign key (MATERIALCODE)
  references W_MATERIAL (MATERIALCODE);