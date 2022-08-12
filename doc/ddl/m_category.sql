DROP TABLE m_category;
DROP SEQUENCE seq_category;

CREATE TABLE m_category (
    category_id     NUMBER(5)       PRIMARY KEY,
    category_name   NVARCHAR2(20)   NOT NULL,
    user_id         NUMBER(5)       REFERENCES m_user(user_id),
    is_deleted      CHAR(1)         NOT NULL,
    ins_date        DATE            NOT NULL,
    upd_date        DATE            NOT NULL
);


CREATE SEQUENCE seq_category
NOCACHE;


INSERT INTO m_category (
    category_id,
    category_name,
    user_id,
    is_deleted,
    ins_date,
    upd_date
) VALUES (
    seq_category.NEXTVAL,
    'ビール',
    1,
    '0',
    sysdate,
    sysdate
);

INSERT INTO m_category (
    category_id,
    category_name,
    user_id,
    is_deleted,
    ins_date,
    upd_date
) VALUES (
    seq_category.NEXTVAL,
    '日本酒',
    1,
    '0',
    sysdate,
    sysdate
);

INSERT INTO m_category (
    category_id,
    category_name,
    user_id,
    is_deleted,
    ins_date,
    upd_date
) VALUES (
    seq_category.NEXTVAL,
    'ワイン',
    1,
    '0',
    sysdate,
    sysdate
);