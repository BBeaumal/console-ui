package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Tournoi;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiDAOImpl {

    public void create(Tournoi tournoi) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            String sql = "INSERT INTO TOURNOI (NOM,CODE) VALUES (?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, tournoi.getNomTournoi());
            preparedStatement.setString(2, tournoi.getCodeTournoi());

            preparedStatement.executeUpdate();

            //Récupère les infos autogénérées
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                tournoi.setIdTournoi(rs.getLong(1));
            }


            System.out.println("Tournoi créé avec succès");
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


    public void update(Tournoi tournoi) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            // Modification d'une donnée dans le tableau
            String sql = "UPDATE TOURNOI SET NOM=?, CODE=? WHERE ID=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, tournoi.getNomTournoi());
            preparedStatement.setString(2, tournoi.getCodeTournoi());
            preparedStatement.setLong(4, tournoi.getIdTournoi());

            preparedStatement.executeUpdate();


            System.out.println("Tournoi modifié avec succès");
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

    public void delete(Long idTournoi) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            // Modification d'une donnée dans le tableau
            String sql = "DELETE FROM TOURNOI WHERE ID=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setLong(1, idTournoi);

            preparedStatement.executeUpdate();


            System.out.println("Tournoi supprimé avec succès");
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

    public Tournoi getById(Long idTournoi) {
        Connection conn = null;
        Tournoi tournoi = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            // Modification d'une donnée dans le tableau
            String sql = "SELECT NOM, CODE FROM TOURNOI WHERE ID=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setLong(1, idTournoi);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tournoi = new Tournoi();
                tournoi.setIdTournoi(idTournoi);
                tournoi.setNomTournoi(rs.getString("NOM"));
                tournoi.setCodeTournoi(rs.getString("CODE"));

            }

            System.out.println("Tournoi lu avec succès");
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
        return tournoi;
    }

    public List<Tournoi> getAll() {
        Connection conn = null;
        List<Tournoi> tournoiList = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            String sql = "SELECT ID, NOM, CODE FROM TOURNOI";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Tournoi tournoi = new Tournoi();
                tournoi.setIdTournoi(rs.getLong("ID"));
                tournoi.setNomTournoi(rs.getString("NOM"));
                tournoi.setCodeTournoi(rs.getString("CODE"));
                tournoiList.add(tournoi);
            }

            System.out.println("Liste de tournois obtenue avec succès");
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
        return tournoiList;
    }
}
