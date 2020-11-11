package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Score;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreRepositoryImpl {

    public void create(Score score) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(score);
        System.out.println("Score ajouté");
    }

    public void delete(Long idS) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Score score = session.get(Score.class, idS);
        session.delete(score);
        System.out.println("Score supprimé");
    }

    public Score getById(Long idScore) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        Score score = session.get(Score.class, idScore);
        System.out.println("Score lu avec succès");
        return score;
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
