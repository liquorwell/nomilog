CREATE TABLE m_category (
    category_id     NUMBER(5)       PRIMARY KEY,
    category_name   NVARCHAR2(20)    NOT NULL,
    is_deleted      CHAR(1)         NOT NULL,
    revision        NUMBER(5)       NOT NULL
);


CREATE SEQUENCE seq_category
NOCACHE;


INSERT INTO m_category (
    category_id,
    category_name,
    is_deleted,
    revision
) VALUES (
    seq_category.NEXTVAL,
    '�r�[��',
    '0',
    1
);

INSERT INTO m_category (
    category_id,
    category_name,
    is_deleted,
    revision
) VALUES (
    seq_category.NEXTVAL,
    '���{��',
    '0',
    1
);

INSERT INTO m_category (
    category_id,
    category_name,
    is_deleted,
    revision
) VALUES (
    seq_category.NEXTVAL,
    '���C��',
    '0',
    1
);