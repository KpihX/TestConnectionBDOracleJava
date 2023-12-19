-- Active: 1702317995109@@127.0.0.1@1521@XEPDB1@SYSTEM

SELECT 
    CDB -- Récupère la valeur de la colonne CDB
FROM 
    V$DATABASE; -- Utilise la vue V$DATABASE pour obtenir des informations sur la base de données

SELECT 
    SYS_CONTEXT('USERENV', 'CON_NAME') -- Récupère le nom de la PDB courante
FROM 
    DUAL; -- Utilise la table DUAL pour exécuter une requête sans lire de données réelles

SELECT 
    NAME, OPEN_MODE -- Récupère le nom et le mode d'ouverture des PDBs
FROM 
    V$PDBS; -- Utilise la vue V$PDBS pour obtenir des informations sur les PDBs

-- Change la PDB courante pour la PDB XEPDB1
ALTER SESSION SET CONTAINER = XEPDB1; 

-- Crée un nouvel utilisateur nommé "Library" avec le mot de passe "libraryPassword"
CREATE USER Library IDENTIFIED BY libraryPassword;

-- Pour connaitre le tablespace par défaut reservé pour les insertions dans les tables d'un utilisateur
SELECT DEFAULT_TABLESPACE FROM DBA_USERS WHERE USERNAME = 'Library';

-- Permet à l'utilisateur de pouvoir faire autant d'insertion que possible dans ses tables 
ALTER USER Library QUOTA UNLIMITED ON USERS;

-- Pour les permissions de créations de procédures stockées
GRANT CREATE PROCEDURE TO Library;
REVOKE CREATE PROCEDURE FROM Library;

-- Accorde les privilèges DBA (Database Administrator) à l'utilisateur "Library"
GRANT DBA TO Library; 
REVOKE DBA FROM Library;

-- Change le schéma courant de la session utilisateur pour le schéma "Library"
ALTER SESSION 
    SET CURRENT_SCHEMA = Library; 

-- Pour directement me connecter dans sql*plus à unr BD dans un PCB (cas de 'Library')
CALL set_cont_sche('Library');

-- Pour executer un fichier 
@votre_fichier.sql