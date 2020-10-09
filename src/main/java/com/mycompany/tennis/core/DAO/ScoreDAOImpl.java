package com.mycompany.tennis.core.DAO;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Score;

import javax.sql.DataSource;
import java.sql.*;

public class ScoreDAOImpl {

    public void create(Score score) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            // Modification d'une donnée dans le tableau
            String sql = "INSERT INTO SCORE_VAINQUEUR (ID_MATCH,SET_1, SET_2, SET_3, SET_4, SET_5) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, score.getMatch().getIdMatch());
            preparedStatement.setByte(2, score.getSet1());
            preparedStatement.setByte(3, score.getSet2());
            preparedStatement.setByte(4, score.getSet3());
            if (score.getSet4() == null) {
                preparedStatement.setNull(5, Types.TINYINT);
            } else {
                preparedStatement.setByte(5, score.getSet4());
            }
            if (score.getSet5() == null) {
                preparedStatement.setNull(6, Types.TINYINT);
            } else {
                preparedStatement.setByte(6, score.getSet5());
            }

            preparedStatement.executeUpdate();

            //Récupère les infos autogénérées
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                score.setIdScore(rs.getLong(1));
            }


            System.out.println("Score créé avec succès");
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


//    public void update(Match match) {
//        Connection conn = null;
//        try {
//            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
//
//            conn = dataSource.getConnection();
//
//            // Modification d'une donnée dans le tableau
//            String sql = "UPDATE Match SET NOM=?, PRENOM=?, SEXE=? WHERE ID=?";
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//
//            preparedStatement.setString(1, match.getNom());
//            preparedStatement.setString(2, match.getPrenom());
//            preparedStatement.setString(3, match.getSexe().toString());
//            preparedStatement.setLong(4, match.getIdJ());
//
//            preparedStatement.executeUpdate();
//
//
//            System.out.println("Match modifié avec succès");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            try {
//                if (conn != null) conn.rollback();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    public void delete(Long idMatch) {
//        Connection conn = null;
//        try {
//            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
//
//            conn = dataSource.getConnection();
//
//            // Modification d'une donnée dans le tableau
//            String sql = "DELETE FROM MATCH WHERE ID=?";
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//
//            preparedStatement.setLong(1, idMatch);
//
//            preparedStatement.executeUpdate();
//
//
//            System.out.println("Match supprimé avec succès");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            try {
//                if (conn != null) conn.rollback();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    public Match getById(Long idMatch) {
//        Connection conn = null;
//        Match match = null;
//        try {
//            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
//
//            conn = dataSource.getConnection();
//
//            // Modification d'une donnée dans le tableau
//            String sql = "SELECT NOM, PRENOM, SEXE FROM MATCH WHERE ID=?";
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//
//            preparedStatement.setLong(1, idMatch);
//
//            ResultSet rs = preparedStatement.executeQuery();
//
//            if (rs.next()) {
//                match = new Match();
//                match.setIdMatch(idMatch);
//                match.setEpreuve(rs.getString("EPREUVE"));
//                match.setFinaliste(rs.getString("PRENOM"));
//                match.setScore(rs.getString("SEXE").charAt(0)); //possible car SEXE non null en BDD
//                match.setVainqueur(rs.);
//            }
//
//            System.out.println("Match lu avec succès");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            try {
//                if (conn != null) conn.rollback();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return match;
//    }

//    public List<Match> getAll() {
//        Connection conn = null;
//        List<Match> matchList = new ArrayList<>();
//        try {
//            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
//
//            conn = dataSource.getConnection();
//
//            // Modification d'une donnée dans le tableau
//            String sql = "SELECT ID, NOM, PRENOM, SEXE FROM MATCH";
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while (rs.next()) {
//                Match match = new Match();
//                match.setIdJ(rs.getLong("ID"));
//                match.setNom(rs.getString("NOM"));
//                match.setPrenom(rs.getString("PRENOM"));
//                match.setSexe(rs.getString("SEXE").charAt(0)); //possible car SEXE non null en BDD
//                matchList.add(match);
//            }
//
//            System.out.println("Liste de matchs obtenue avec succès");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            try {
//                if (conn != null) conn.rollback();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return matchList;
//    }
}
