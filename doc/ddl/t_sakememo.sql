DROP TABLE t_sakememo;
DROP SEQUENCE seq_sakememo;

CREATE TABLE t_sakememo (
    category_id         NUMBER(5)       REFERENCES m_category(category_id),
    user_id             NUMBER(5)       REFERENCES m_user(user_id),
    sakememo_id         NUMBER(5)       PRIMARY KEY,
    sakememo_name       NVARCHAR2(20)   NOT NULL,
    sakememo_comment    NVARCHAR2(100)  NOT NULL,
    sakelog_id          NUMBER(5)       NOT NULL,
    is_deleted          CHAR(1)         NOT NULL,
    ins_date            DATE            NOT NULL,
    upd_date            DATE            NOT NULL
);


CREATE SEQUENCE seq_sakememo
NOCACHE;


INSERT INTO t_sakememo (
    category_id,
    user_id,
    sakememo_id,
    sakememo_name,
    sakememo_comment,
    sakelog_id,
    is_deleted,
    ins_date,
    upd_date
) VALUES (
    1,
    1,
    seq_sakememo.NEXTVAL,
    'ザ・モルツ',
    'サントリー製のクラシックなビール',
    0,
    '0',
    sysdate,
    sysdate    
);

INSERT INTO t_sakememo (
    category_id,
    user_id,
    sakememo_id,
    sakememo_name,
    sakememo_comment,
    sakelog_id,
    is_deleted,
    ins_date,
    upd_date
) VALUES (
    2,
    3,
    seq_sakememo.NEXTVAL,
    '獺祭',
    '山口発、旋風を巻き起こす日本酒',
    0,
    '0',
    sysdate,
    sysdate    
);

INSERT INTO t_sakememo (
    category_id,
    user_id,
    sakememo_id,
    sakememo_name,
    sakememo_comment,
    sakelog_id,
    is_deleted,
    ins_date,
    upd_date
) VALUES (
    2,
    3,
    seq_sakememo.NEXTVAL,
    '酔鯨',
    '名前がかわいい',
    0,
    '0',
    sysdate,
    sysdate    
);