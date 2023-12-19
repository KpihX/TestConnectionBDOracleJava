-- Procédure automatique de connection à un PDB et à un utilisateur
CREATE OR REPLACE PROCEDURE set_cont_sche(user_name IN VARCHAR2, pdb_name IN VARCHAR2 DEFAULT 'XEPDB1') AS
BEGIN
  EXECUTE IMMEDIATE 'ALTER SESSION SET CONTAINER = ' || pdb_name;
  EXECUTE IMMEDIATE 'ALTER SESSION SET CURRENT_SCHEMA = ' || user_name;
END;
/