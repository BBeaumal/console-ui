package com.mycompany.tennis.core.DAO;

import com.mycompany.tennis.core.entity.Joueur;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JoueurDAOImpl {

    public void create(Joueur joueur) {
        Connection conn = null;
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setInitialSize(5);
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");

            dataSource.setUsername("root");
            dataSource.setPassword("root");

            conn = dataSource.getConnection();

            // Modification d'une donnée dans le tableau
            String sql = "INSERT INTO JOUEUR (NOM,PRENOM,SEXE) VALUES (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());

            preparedStatement.executeUpdate();


            System.out.println("Joueur créé avec succès");
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


    public void update(Joueur joueur) {
        Connection conn = null;
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setInitialSize(5);
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");

            dataSource.setUsername("root");
            dataSource.setPassword("root");

            conn = dataSource.getConnection();

            // Modification d'une donnée dans le tableau
            String sql = "UPDATE JOUEUR SET NOM=?, PRENOM=?, SEXE=? WHERE ID=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());
            preparedStatement.setLong(4, joueur.getIdJ());

            preparedStatement.executeUpdate();


            System.out.println("Joueur modifié avec succès");
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

    public void delete(Long idJ) {
        Connection conn = null;
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setInitialSize(5);
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");

            dataSource.setUsername("root");
            dataSource.setPassword("root");

            conn = dataSource.getConnection();

            // Modification d'une donnée dans le tableau
            String sql = "DELETE FROM JOUEUR WHERE ID=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setLong(1, idJ);

            preparedStatement.executeUpdate();


            System.out.println("Joueur supprimé avec succès");
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

    public Joueur getById(Long idJ) {
        Connection conn = null;
        Joueur joueur = null;
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setInitialSize(5);
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");

            dataSource.setUsername("root");
            dataSource.setPassword("root");

            conn = dataSource.getConnection();

            // Modification d'une donnée dans le tableau
            String sql = "SELECT NOM, PRENOM, SEXE FROM JOUEUR WHERE ID=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setLong(1, idJ);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                joueur = new Joueur();
                joueur.setIdJ(idJ);
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0)); //possible car SEXE non null en BDD
            }

            System.out.println("Joueur lu avec succès");
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
        return joueur;
    }

    public List<Joueur> getAll() {
        Connection conn = null;
        List<Joueur> joueurList = new ArrayList<>();
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setInitialSize(5);
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");

            dataSource.setUsername("root");
            dataSource.setPassword("root");

            conn = dataSource.getConnection();

            // Modification d'une donnée dans le tableau
            String sql = "SELECT ID, NOM, PRENOM, SEXE FROM JOUEUR";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Joueur joueur = new Joueur();
                joueur.setIdJ(rs.getLong("ID"));
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0)); //possible car SEXE non null en BDD
                joueurList.add(joueur);
            }

            System.out.println("Liste de joueurs obtenue avec succès");
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
        return joueurList;
    }
}
