package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Tournoi;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TournoiRepositoryImpl {

    public void create(Tournoi tournoi) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        EntityTransaction tx = null;

        try {

            tx = em.getTransaction();
            em.persist(tournoi);

            System.out.println("Tournoi créé avec succès");
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
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

        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        Tournoi tournoi = em.find(Tournoi.class, idTournoi);
        em.remove(tournoi);
        System.out.println("Tournoi supprimé avec succès");
    }

    public Tournoi getById(Long idTournoi) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        Tournoi tournoi = null;
        tournoi = em.find(Tournoi.class, idTournoi);
        System.out.println("Tournoi lu avec succès");
        return tournoi;
    }

    public List<Tournoi> getAll() {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        TypedQuery<Tournoi> query = em.createQuery("select t from Tournoi t  ", Tournoi.class);
        List<Tournoi> tournoiList = query.getResultList();
        System.out.println("Liste d'épreuves obtenue avec succès");
        return tournoiList;
    }

}
