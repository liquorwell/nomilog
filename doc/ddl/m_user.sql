CREATE TABLE m_user (
    user_id         NUMBER(5)       PRIMARY KEY,
    user_name       NVARCHAR2(20)   NOT NULL,
    user_pass       NVARCHAR2(20)   NOT NULL,
    is_admin        CHAR(1)         NOT NULL,
    display_order   NUMBER(1)       NOT NULL,
    is_deleted      CHAR(1)         NOT NULL,
    revision        NUMBER(5)       NOT NULL
);


CREATE SEQUENCE seq_user
NOCACHE;


INSERT INTO m_user (
    user_id, 
    user_name, 
    user_pass, 
    is_admin, 
    display_order, 
    is_deleted, 
    revision
) VALUES (
    seq_user.NEXTVAL,
    '�e�X�g���[�U�[1',
    '1111',
    '1',
    1,
    '0',
    1
);

INSERT INTO m_user (
    user_id, 
    user_name, 
    user_pass, 
    is_admin, 
    display_order, 
    is_deleted, 
    revision
) VALUES (
    seq_user.NEXTVAL,
    '�e�X�g���[�U�[2',
    '2222',
    '0',
    0,
    '0',
    1
);

INSERT INTO m_user (
    user_id, 
    user_name, 
    user_pass, 
    is_admin, 
    display_order, 
    is_deleted, 
    revision
) VALUES (
    seq_user.NEXTVAL,
    '�e�X�g���[�U�[3',
    '3333',
    '0',
    0,
    '0',
    1
);