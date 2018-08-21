-- User test data
INSERT INTO ROLE (TYPE) VALUES ('ADMINISTRATOR'), ('PROJECT_MANAGER'), ('TEST_MANAGER'), ('DEVELOPER'), ('TESTER');

INSERT INTO PERMISSION (TYPE) VALUES ('PERMISSION_MANAGEMENT'), ('USER_MANAGEMENT'), ('BUG_MANAGEMENT'), ('BUG_CLOSE'), ('BUG_EXPORT_PDF');

INSERT INTO USER (EMAIL, FIRSTNAME, LASTNAME, MOBILENUMBER, PASSWORD, STATUS, USERNAME) VALUES('paul@msggroup.com', 'Paul', 'Ghemes', '0741023453', 'ŒvéÀDe‡ªNNŸÑ;', 'ACTIVE', 'ghemep');
INSERT INTO USER (EMAIL, FIRSTNAME, LASTNAME, MOBILENUMBER, PASSWORD, STATUS, USERNAME) VALUES('cristi@msggroup.com', 'Cristian', 'Macarie', '0741023453', 'ŒvéÀDe‡ªNNŸÑ;', 'ACTIVE', 'macarc');
INSERT INTO USER (EMAIL, FIRSTNAME, LASTNAME, MOBILENUMBER, PASSWORD, STATUS, USERNAME) VALUES('tihamer@msggroup.com', 'Tihamer', 'Both', '0741023453', 'ŒvéÀDe‡ªNNŸÑ;', 'ACTIVE', 'bothti');
INSERT INTO USER (EMAIL, FIRSTNAME, LASTNAME, MOBILENUMBER, PASSWORD, STATUS, USERNAME) VALUES('botond@msggroup.com', 'Botond', 'Laszlo', '0741023453', 'ŒvéÀDe‡ªNNŸÑ;', 'ACTIVE', 'laszlb');
INSERT INTO USER (EMAIL, FIRSTNAME, LASTNAME, MOBILENUMBER, PASSWORD, STATUS, USERNAME) VALUES('sanziana@msggroup.com', 'Sanziana', 'Sorea', '0741023453', 'ŒvéÀDe‡ªNNŸÑ;', 'ACTIVE', 'soreas');
INSERT INTO USER (EMAIL, FIRSTNAME, LASTNAME, MOBILENUMBER, PASSWORD, STATUS, USERNAME) VALUES('oana@msggroup.com', 'Oana', 'Lung', '0741023453', 'ŒvéÀDe‡ªNNŸÑ;', 'ACTIVE', 'lungoa');
INSERT INTO USER (EMAIL, FIRSTNAME, LASTNAME, MOBILENUMBER, PASSWORD, STATUS, USERNAME) VALUES('admin@msggroup.com', 'Admin', 'Admin', '0768594837', 'ŒvéÀDe‡ªNNŸÑ;', 'ACTIVE', 'admina');


INSERT INTO ROLE_PERMISSION (ROLES_ID, PERMISSIONS_ID) VALUES (1, 2);
INSERT INTO ROLE_PERMISSION (ROLES_ID, PERMISSIONS_ID) VALUES (1, 1);
INSERT INTO ROLE_PERMISSION (ROLES_ID, PERMISSIONS_ID) VALUES (2, 3);
INSERT INTO ROLE_PERMISSION (ROLES_ID, PERMISSIONS_ID) VALUES (2, 4);
INSERT INTO ROLE_PERMISSION (ROLES_ID, PERMISSIONS_ID) VALUES (2, 5);
INSERT INTO ROLE_PERMISSION (ROLES_ID, PERMISSIONS_ID) VALUES (2, 1);
INSERT INTO ROLE_PERMISSION (ROLES_ID, PERMISSIONS_ID) VALUES (3, 3);
INSERT INTO ROLE_PERMISSION (ROLES_ID, PERMISSIONS_ID) VALUES (3, 4);
INSERT INTO ROLE_PERMISSION (ROLES_ID, PERMISSIONS_ID) VALUES (3, 5);
INSERT INTO ROLE_PERMISSION (ROLES_ID, PERMISSIONS_ID) VALUES (4, 3);
INSERT INTO ROLE_PERMISSION (ROLES_ID, PERMISSIONS_ID) VALUES (4, 5);
INSERT INTO ROLE_PERMISSION (ROLES_ID, PERMISSIONS_ID) VALUES (5, 3);
INSERT INTO ROLE_PERMISSION (ROLES_ID, PERMISSIONS_ID) VALUES (5, 4);
INSERT INTO ROLE_PERMISSION (ROLES_ID, PERMISSIONS_ID) VALUES (5, 5);


INSERT INTO USER_ROLE (ROLES_ID, USERS_ID) VALUES (1, 7);
INSERT INTO USER_ROLE (ROLES_ID, USERS_ID) VALUES (2, 1);
INSERT INTO USER_ROLE (ROLES_ID, USERS_ID) VALUES (3, 2);
INSERT INTO USER_ROLE (ROLES_ID, USERS_ID) VALUES (4, 3);
INSERT INTO USER_ROLE (ROLES_ID, USERS_ID) VALUES (4, 4);
INSERT INTO USER_ROLE (ROLES_ID, USERS_ID) VALUES (5, 5);
INSERT INTO USER_ROLE (ROLES_ID, USERS_ID) VALUES (5, 6);


INSERT INTO BUG (DESCRIPTION, DUEDATE, FIXEDINVERSION, SEVERITYTYPE, STATUSTYPE, TITLE, VERSION, ASSIGNEE_ID, CREATOR_ID) VALUES('First bug created', '2018-08-20', '1.0.1', 'LOW', 'CLOSED', 'First bug', '1.0.0', 2, 1);
INSERT INTO BUG (DESCRIPTION, DUEDATE, FIXEDINVERSION, SEVERITYTYPE, STATUSTYPE, TITLE, VERSION, ASSIGNEE_ID, CREATOR_ID) VALUES('Second bug created', '2018-08-25', '-', 'MEDIUM', 'IN_PROGRESS', 'Second bug', '1.0.0', 3, 4);
INSERT INTO BUG (DESCRIPTION, DUEDATE, FIXEDINVERSION, SEVERITYTYPE, STATUSTYPE, TITLE, VERSION, ASSIGNEE_ID, CREATOR_ID) VALUES('Third bug created', '2018-08-23', '-', 'LOW', 'INFO_NEEDED', 'Third bug', '1.0.1', 5, 2);
INSERT INTO BUG (DESCRIPTION, DUEDATE, FIXEDINVERSION, SEVERITYTYPE, STATUSTYPE, TITLE, VERSION, ASSIGNEE_ID, CREATOR_ID) VALUES('Fourth bug created', '2018-08-21', '-', 'HIGH', 'OPEN', 'Fourth bug', '1.0.1', 4, 6);
INSERT INTO BUG (DESCRIPTION, DUEDATE, FIXEDINVERSION, SEVERITYTYPE, STATUSTYPE, TITLE, VERSION, ASSIGNEE_ID, CREATOR_ID) VALUES('Fifth bug created', '2018-08-22', '1.0.2', 'LOW', 'CLOSED', 'Fifth bug', '1.0.1', 3, 1);


INSERT INTO NOTIFICATION (TYPE, AFFECTEDBUGID, AFFECTEDUSERID, CREATEDON, SEEN, USER_ID) VALUES(NULL, 3, '2018-08-20', 0, 'WELCOME_NEW_USER', 3);
INSERT INTO NOTIFICATION (TYPE, AFFECTEDBUGID, AFFECTEDUSERID, CREATEDON, SEEN, USER_ID) VALUES(NULL, 4, '2018-08-21', 1, 'USER_UPDATED', 7);
INSERT INTO NOTIFICATION (TYPE, AFFECTEDBUGID, AFFECTEDUSERID, CREATEDON, SEEN, USER_ID) VALUES(2, NULL, '2018-08-20', 0, 'BUG_UPDATED', 4);
INSERT INTO NOTIFICATION (TYPE, AFFECTEDBUGID, AFFECTEDUSERID, CREATEDON, SEEN, USER_ID) VALUES(2, NULL, '2018-08-20', 1, 'BUG_UPDATED', 3);
INSERT INTO NOTIFICATION (TYPE, AFFECTEDBUGID, AFFECTEDUSERID, CREATEDON, SEEN, USER_ID) VALUES(5, NULL, '2018-08-20', 0, 'BUG_CLOSED', 3);
INSERT INTO NOTIFICATION (TYPE, AFFECTEDBUGID, AFFECTEDUSERID, CREATEDON, SEEN, USER_ID) VALUES(5, NULL, '2018-08-20', 0, 'BUG_CLOSED', 1);



-- other tables TODO