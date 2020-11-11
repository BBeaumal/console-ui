package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Match;
import org.hibernate.Session;

public class MatchRepositoryImpl {

    public void create(Match match) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(match);
        System.out.println("Match ajouté");
    }

    public void delete(Long idMatch) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Match match = session.get(Match.class, idMatch);
        session.delete(match);
        System.out.println("Match supprimé avec succès");
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

    public Match getById(Long idMatch) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Match match = session.get(Match.class, idMatch);
        System.out.println("Match lu avec succès");
        return match;
    }

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
