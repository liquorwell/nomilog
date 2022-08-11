CREATE TABLE t_sakelog (
    category_id         NUMBER(5)       REFERENCES m_category(category_id),
    user_id             NUMBER(5)       REFERENCES m_user(user_id),
    sakelog_id          NUMBER(5)       PRIMARY KEY,
    sakelog_name        NVARCHAR2(20)   NOT NULL,
    rating              NUMBER(1)       NOT NULL,
    sakelog_comment     NVARCHAR2(100)  NOT NULL,
    is_deleted          CHAR(1)         NOT NULL,
    ins_date            DATE            NOT NULL,
    upd_date            DATE            NOT NULL
);


CREATE SEQUENCE seq_sakelog
NOCACHE;


INSERT INTO t_sakelog (
    category_id,
    user_id,
    sakelog_id,
    sakelog_name,
    rating,
    sakelog_comment,
    is_deleted,
    ins_date,
    upd_date
) VALUES (
    1,
    1,
    seq_sakelog.NEXTVAL,
    'プレミアムモルツ',
    5,
    'プレミアムなお味',
    '0',
    sysdate,
    sysdate    
);

INSERT INTO t_sakelog (
    category_id,
    user_id,
    sakelog_id,
    sakelog_name,
    rating,
    sakelog_comment,
    is_deleted,
    ins_date,
    upd_date
) VALUES (
    2,
    2,
    seq_sakelog.NEXTVAL,
    '秋鹿',
    4,
    '大阪の地酒。甘くて飲みやすい。',
    '0',
    sysdate,
    sysdate    
);

INSERT INTO t_sakelog (
    category_id,
    user_id,
    sakelog_id,
    sakelog_name,
    rating,
    sakelog_comment,
    is_deleted,
    ins_date,
    upd_date
) VALUES (
    3,
    2,
    seq_sakelog.NEXTVAL,
    'サイゼのワイン',
    3,
    'ジュースみたいで飲みやすい。少し物足りない。',
    '0',
    sysdate,
    sysdate    
);