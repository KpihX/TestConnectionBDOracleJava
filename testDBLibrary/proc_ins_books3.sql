ALTER SESSION SET CURRENT_SCHEMA = Library;

CREATE OR REPLACE PROCEDURE insert_into_books3 AS
BEGIN
    INSERT INTO Books3 (id, title, author, publication_year) VALUES (1, 'Book 1', 'Author 1', 2021);
    INSERT INTO Books3 (id, title, author, publication_year) VALUES (2, 'Book 2', 'Author 2', 2022);
    DBMS_SESSION.SLEEP(30); -- Met en attente pendant 20 secondes
    COMMIT;
END;
/