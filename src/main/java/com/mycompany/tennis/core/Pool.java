package com.mycompany.tennis.core;


import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Pool {
    public static void main(String... args) {
        Connection conn = null;
        try {

            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setInitialSize(5);
            dataSource.setUrl( "jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");
            //dataSource.setServerName("localhost");
            //dataSource.setPort(3306);
            //dataSource.setDatabaseName("tennis");
            //dataSource.setUseSSL(false);
            //dataSource.setServerTimezone("Europe/Paris");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            conn = dataSource.getConnection();
            //MySQL driver MySQL Connector
            // conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris", "root", "root");
            //Evite l'insertion SQL (faille de sécurité)
            //PreparedStatement preparedStatement = conn.prepareStatement("SELECT NOM, PRENOM, ID FROM JOUEUR WHERE ID=?");

            //Permet de mettre en attente toutes les opérations
            conn.setAutoCommit(false);

            // Modification d'une donnée dans le tableau
            String sql = "INSERT INTO JOUEUR (NOM, PRENOM, SEXE) VALUES (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            String nom = "Capriati";
            String prenom = "Jennifer";
            String sexe = "F";
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);

            preparedStatement.executeUpdate();

            nom = "Johansson";
            prenom = "Thomas";
            sexe = "H";
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);

            preparedStatement.executeUpdate();
            conn.commit();

            //ResultSet resultSet = preparedStatement.executeQuery();

//            //permet de vérifier si il y a une nouvelle ligne
//            if (resultSet.next()) {
//                final String nomJ = resultSet.getString("NOM");
//                final String prenomJ = resultSet.getString("PRENOM");
//                final Long idJ = resultSet.getLong("ID");
//                System.out.println("Le joueur representé par le numero " + idJ + " est " + prenomJ + " " + nomJ);
//            } else {
//                System.out.println("Il n'y a pas de joueur au numéro " + identifiant);
//            }
            ;
            //Oracle Driver officiel OJDBC Thin
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:tennis","COURSDB","COURSDB");
            //Postgres Driver officiel
            //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tennis","COURSDB","COURSDB");

            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}



