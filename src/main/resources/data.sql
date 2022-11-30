INSERT INTO DEPT_MASTER (dept_id, dept_nm, dept_desc, up_dept_id, use_yn, cr_dt) VALUES ('1000','캡퍼스', '캠퍼스', '', 'Y', '2022-01-01');

INSERT INTO ROLE_MASTER (role_id, role_nm, role_desc, use_yn, cr_dt) VALUES ('ADMIN', '관리자', '관리자', 'Y', '2022-01-01');

INSERT INTO MEMBER (user_id, user_name, user_pwd, email_id, address, birth, cell_phone, regi_dt, appr_dt, dept_id, role_id, use_yn ) VALUES ('test', '테스트', 'test', 'test@test.com', '서울시 관악구', '2020-01-01', '010-1111-2222', '2022-01-01', '2022-01-01', '1000', 'ADMIN', 'Y');
