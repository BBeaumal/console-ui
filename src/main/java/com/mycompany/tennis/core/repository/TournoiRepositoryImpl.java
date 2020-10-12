package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

    public void create(Tournoi tournoi) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(tournoi);
            tx.commit();
            System.out.println("Tournoi créé avec succès");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
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
        Tournoi tournoi = new Tournoi();
        tournoi.setIdTournoi(idTournoi);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(tournoi);
        System.out.println("Tournoi supprimé avec succès");
    }

    public Tournoi getById(Long idTournoi) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Tournoi tournoi = null;
        try {
            tournoi = session.get(Tournoi.class, idTournoi);
            System.out.println("Tournoi lu avec succès");
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
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
