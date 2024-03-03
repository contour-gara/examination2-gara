CREATE TABLE books (
    id integer PRIMARY KEY,
    title varchar(100),
    author varchar(100),
    publisher varchar(100),
    price integer
);

CREATE SEQUENCE IF NOT EXISTS BOOK_ID_SEQ
    INCREMENT BY 1
    MAXVALUE 9999999999
    MINVALUE 1
    START WITH 1
;
