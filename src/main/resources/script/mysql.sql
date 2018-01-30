INSERT INTO w_menu (menucode, menuText, parentmenucode, weburl, menutype, displayordinal, createuser, createdatetime, modifyuser, modifydatetime)
VALUES ('OPERATION_STOCK', '出入库管理', NULL, NULL, 'MODULE', 2, 'sa', sysdate(), 'sa', sysdate());

INSERT INTO w_menu (menucode, menuText, parentmenucode, weburl, menutype, displayordinal, createuser, createdatetime, modifyuser, modifydatetime)
VALUES ('INPUT_STOCK', '入库', 'OPERATION_STOCK', '/operationStock/input', 'FORM', 1, 'sa', sysdate(), 'sa', sysdate());

INSERT INTO w_menu (menucode, menuText, parentmenucode, weburl, menutype, displayordinal, createuser, createdatetime, modifyuser, modifydatetime)
VALUES ('OUTPUT_STOCK', '出库', 'OPERATION_STOCK', '/operationStock/output', 'FORM', 2, 'sa', sysdate(), 'sa', sysdate());

INSERT INTO w_menu (menucode, menuText, parentmenucode, weburl, menutype, displayordinal, createuser, createdatetime, modifyuser, modifydatetime)
VALUES ('STOCK_RECORD', '出入库查询', 'OPERATION_STOCK', '/operationStock/record', 'FORM', 3, 'sa', sysdate(), 'sa', sysdate());

INSERT INTO w_menu (menucode, menuText, parentmenucode, weburl, menutype, displayordinal, createuser, createdatetime, modifyuser, modifydatetime)
VALUES ('BASIC_DATA', '主数据管理', NULL, NULL, 'MODULE', 3, 'sa', sysdate(), 'sa', sysdate());

INSERT INTO w_menu (menucode, menuText, parentmenucode, weburl, menutype, displayordinal, createuser, createdatetime, modifyuser, modifydatetime)
VALUES ('MATERIAL_MANAGE', '物料管理', 'BASIC_DATA', '/material/list', 'FORM', 1, 'sa', sysdate(), 'sa', sysdate());

INSERT INTO w_menu (menucode, menuText, parentmenucode, weburl, menutype, displayordinal, createuser, createdatetime, modifyuser, modifydatetime)
VALUES ('WAREHOUSE_MANAGE', '仓库管理', 'BASIC_DATA', '/warehouse/list', 'FORM', 2, 'sa', sysdate(), 'sa', sysdate());

INSERT INTO w_menu (menucode, menuText, parentmenucode, weburl, menutype, displayordinal, createuser, createdatetime, modifyuser, modifydatetime)
VALUES ('STOCK_MANAGE', '库存管理', 'BASIC_DATA', '/stock/list', 'FORM', 3, 'sa', sysdate(), 'sa', sysdate());

INSERT INTO w_menu (menucode, menuText, parentmenucode, weburl, menutype, displayordinal, createuser, createdatetime, modifyuser, modifydatetime)
VALUES ('PRODUCTION_MANAGE', '成品管理', 'BASIC_DATA', '/production/list', 'FORM', 4, 'sa', sysdate(), 'sa', sysdate());

INSERT INTO w_menu (menucode, menuText, parentmenucode, weburl, menutype, displayordinal, createuser, createdatetime, modifyuser, modifydatetime)
VALUES ('SYSTEM_MANAGE', '系统管理', NULL, NULL, 'MODULE', 4, 'sa', sysdate(), 'sa', sysdate());

INSERT INTO w_menu (menucode, menuText, parentmenucode, weburl, menutype, displayordinal, createuser, createdatetime, modifyuser, modifydatetime)
VALUES ('USER_MANAGE', '用户管理', 'SYSTEM_MANAGE', '/user/list', 'FORM', 1, 'sa', sysdate(), 'sa', sysdate());

INSERT INTO w_menu (menucode, menuText, parentmenucode, weburl, menutype, displayordinal, createuser, createdatetime, modifyuser, modifydatetime)
VALUES ('ROLE_MANAGE', '角色管理', 'SYSTEM_MANAGE', '/role/list', 'FORM', 2, 'sa', sysdate(), 'sa', sysdate());

INSERT INTO w_menu (menucode, menuText, parentmenucode, weburl, menutype, displayordinal, createuser, createdatetime, modifyuser, modifydatetime)
VALUES ('MENU_MANAGE', '菜单管理', 'SYSTEM_MANAGE', '/menu/tree', 'FORM', 3, 'sa', sysdate(), 'sa', sysdate());