-- Active: 1702317995109@@127.0.0.1@1521@XEPDB1@SYSTEM

ALTER SESSION 
    SET CURRENT_SCHEMA = Library;

DROP TABLE books;
CREATE TABLE books (
    id NUMBER PRIMARY KEY,
    title VARCHAR2(100),
    author VARCHAR2(100),
    publication_year NUMBER
);
SELECT * FROM books;

DROP TABLE books2;
CREATE TABLE books2 (
    id NUMBER PRIMARY KEY,
    title VARCHAR2(100),
    author VARCHAR2(100),
    publication_year NUMBER
);
SELECT * FROM books2;

DROP TABLE books3;
CREATE TABLE books3 (
    id NUMBER PRIMARY KEY,
    title VARCHAR2(100),
    author VARCHAR2(100),
    publication_year NUMBER
);
SELECT * FROM books3;

-- Création de books4
DROP SEQUENCE books4_id_seq;
CREATE SEQUENCE books4_id_seq START WITH 1 INCREMENT BY 1;
ALTER SEQUENCE books4_id_seq MINVALUE 0;

DROP TABLE books4;
CREATE TABLE books4 (
  id NUMBER DEFAULT books4_id_seq.NEXTVAL PRIMARY KEY,
  title VARCHAR2(100) NOT NULL,
  author VARCHAR2(100) NOT NULL,
  publication_year NUMBER CHECK (publication_year BETWEEN 1900 AND 2100)
);
SELECT * FROM books4;





