package com.mycompany.tennis.core.services;

import com.mycompany.tennis.core.DTO.TournoiDTO;
import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TournoiService {
    private final TournoiRepositoryImpl tournoiRepositoryImpl;

    public TournoiService() {
        this.tournoiRepositoryImpl = new TournoiRepositoryImpl();
    }

    public void createTournoi(TournoiDTO dto) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Tournoi tournoi = new Tournoi();
            tournoi.setIdTournoi(dto.getIdTournoi());
            tournoi.setNomTournoi(dto.getNomTournoi());
            tournoi.setCodeTournoi(dto.getNomTournoi());
            tournoiRepositoryImpl.create(tournoi);
            tx.commit();
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

    public void deleteTournoi(Long idT) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            tournoiRepositoryImpl.delete(idT);
            tx.commit();
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

    public TournoiDTO getTournoi(Long idT) {
//        Session session = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        Tournoi tournoi = null;
        TournoiDTO tournoiDTO = null;
        try {
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
            em = new EntityManagerHolder().getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            tournoi = tournoiRepositoryImpl.getById(idT);
            tournoiDTO = new TournoiDTO();
            tournoiDTO.setIdTournoi(tournoi.getIdTournoi());
            tournoiDTO.setNomTournoi(tournoi.getNomTournoi());
            tournoiDTO.setCodeTournoi(tournoi.getCodeTournoi());
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return tournoiDTO;
    }
}
