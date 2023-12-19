import java.io.*;
import java.sql.*;
import java.util.*;

class Test {
    public static void  main(String[] args) { 
        // Informations de connexion à la base de données Oracle
        String url = "jdbc:oracle:thin:";

        String adress0 = "@localhost:1521/XE";
        String motDePasse0 = "yOUkNOW0";

        String adress1 = "@172.20.10.3:1521/XE";  
        String motDePasse1 = "Violetta12!";
        
        String adress = adress1;
        String motDePasse = motDePasse1;
        String nomUtilisateur = "system";
        
        // Chargement du pilote JDBC Oracle
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connexion = DriverManager.getConnection(url + adress, nomUtilisateur, motDePasse);

            // Informations de la BD
            String PDB = "PDB1";
            String schema = "Library";

            // Exécutions des instructions ALTER SESSION
            Statement statement = connexion.createStatement();
            String instructionPDB = "ALTER SESSION SET CONTAINER = " + "XE" + PDB;
            String instructionSchema = "ALTER SESSION SET CURRENT_SCHEMA = " + schema;
            statement.executeUpdate(instructionPDB);
            statement.executeUpdate(instructionSchema);
           
            System.out.println("Connection réussie à la BD " + schema + " !");

            // Instructions d'insertion
            List<String> insertions = new ArrayList<String>();
            // Juste pour le test
            insertions.add("INSERT INTO %s (id, title, author, publication_year) VALUES (1, 'Book 1', 'Author 1', 2021)");
            insertions.add("INSERT INTO %s (id, title, author, publication_year) VALUES (2, 'Book 2', 'Author 2', 2022)");

            // Reception des entrées
            /*Scanner scanner = new Scanner(System.in);
            System.out.println("*** Ce programme vise à faire l'insertion dans une table Books (id, title, author, publication_year) d'une BD Library et en parallèle dans deux tables Books2 et Books3 de même schéma. L'insertion se fera dans une machine en réseau local. ***");
            String[] line;
            while (true) {
                System.out.print("Entrez un enregistrement (id,title,author,publication year) : ");
                try {
                    line = scanner.nextLine().split(",");
                    int id = Integer.parseInt(line[0]);
                    for (int i = 1; i <= 2; i++) {
                        line[i] = "'" + line[i] + "'";
                    }
                    int year = Integer.parseInt(line[3]);
                    insertions.add("INSERT INTO %s (id, title, author, publication_year) VALUES (" + String.join(",", line) + ")");
                } catch (Exception e) {
                    break;
                }
            }
            scanner.close();

            // Vérification de la présence d'au moins un enregistrement valide
            if (insertions.isEmpty()) {
                System.out.println("Aucune insertion n'a été effectuée !");
                return;
            }*/
           
            // Exécutions des instructions d'insertion dans la table "Books"
            /*for (String insertion : insertions) {
                statement.executeUpdate(String.format(insertion, "Books"));
            }*/

            //Execution avec file d'attente
            Queue<String> insertQueue = new LinkedList<>();
            for (String insertion : insertions) {
                insertQueue.add(String.format(insertion, "Books"));
            }
            
            while (!insertQueue.isEmpty()) {
                String insertion = insertQueue.poll();
                statement.executeUpdate(insertion);
            }

            System.out.println("Insertions effectuées dans la table 'Books' !");

            // Créez le déclencheur (trigger) pour insérer les mêmes données dans la table "Books2"
            String trigger = "CREATE OR REPLACE TRIGGER insert_into_books2\n" +
                            "AFTER INSERT ON Books\n" +
                            "FOR EACH ROW\n" +
                            "BEGIN\n" +
                            "   INSERT INTO Books2 (id, title, author, publication_year) VALUES (:new.id, :new.title, :new.author, :new.publication_year);\n" +
                            "END;";
            statement.executeUpdate(trigger);
            
            System.out.println("Trigger d'insertion dans Books2 créé et exécuté!");

            /* Definition de la procédure stockée */

            String sql = "ALTER SESSION SET CURRENT_SCHEMA = Library;\n\n" +
                "CREATE OR REPLACE PROCEDURE insert_into_books3 AS\n" +
                "BEGIN\n";
            
            for (String insertion : insertions) {
                sql += "    " + String.format(insertion, "Books3") + ";\n";
            }

            sql += "    DBMS_SESSION.SLEEP(30); -- Met en attente pendant 20 secondes\n" +
                "    COMMIT;\n" +
                "END;\n" +
                "/";
    
            BufferedWriter writer = new BufferedWriter(new FileWriter("./testDBLibrary/proc_ins_books3.sql"));
            writer.write(sql);
            writer.close();

            /* Compiler et sauvegarder la porcédure stockée d'insertion dans T3 */
           
            ProcessBuilder processBuilder = new ProcessBuilder("sqlplus", nomUtilisateur + "/" + motDePasse + adress + PDB);
            Process process = processBuilder.start();
            writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            
            String cheminProcedureStockee = "@'D:\\Travaux\\Programmation\\Web\\Java\\apprentissage\\gi3ENSPY\\connectionOracle\\testDBLibrary\\proc_ins_books3.sql';";
            writer.write(cheminProcedureStockee);
            writer.newLine();
            writer.flush();

            writer.write("commit;");
            writer.newLine();
            writer.flush();

            writer.close();

            //Attente des résultats
            int resultat = process.waitFor();
            if (resultat == 0) {
                System.out.println("La procédure stockée d'insertion dans Books3 a été créée et compilée et exécutéé avec succès !");
            } else {
                 // Affichage des erreurs en cas d'échec
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String line2;
                System.err.println("La commande a échoué avec le code de sortie : " + resultat);
                System.err.println("Erreurs :");
                while ((line2 = errorReader.readLine()) != null) {
                    System.err.println(line2);
                }
                errorReader.close();
            }

            // Exécutez la procédure stockée
            String executeProcedure = "CALL insert_into_books3()";
            statement.execute(executeProcedure);

            // Fermez les ressources
            statement.close();
            connexion.close();
        } catch (ClassNotFoundException cnfex) {
            System.err.println("Échec du chargement du pilote JDBC Oracle.");
            cnfex.printStackTrace();
            System.exit(1);
        } catch (SQLException sqlex) {
            System.err.println("Connexion impossible à la base de données Oracle.");
            sqlex.printStackTrace();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } 
    }
}