-- Active: 1702901714969@@127.0.0.1@1521@XEPDB1@SYSTEM

ALTER SESSION 
    SET CURRENT_SCHEMA = Library;

SELECT * FROM books;
SELECT * FROM books2;
SELECT * FROM books3;


DELETE FROM books;
DELETE FROM books2;
DELETE FROM books3;


-- Insertions dans books4
INSERT INTO books4 (title, author, publication_year) VALUES ('Harry Potter and the Philosopher''s Stone', 'J.K. Rowling', 1997);
INSERT INTO books4 (title, author, publication_year)VALUES ('Harry Potter and the Chamber of Secrets', 'J.K. Rowling', 1998);
SELECT * FROM books4;
DELETE FROM books4;