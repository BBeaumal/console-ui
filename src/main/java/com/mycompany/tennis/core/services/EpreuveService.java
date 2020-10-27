package com.mycompany.tennis.core.services;

import com.mycompany.tennis.core.DTO.EpreuveFullDTO;
import com.mycompany.tennis.core.DTO.EpreuveLightDTO;
import com.mycompany.tennis.core.DTO.TournoiDTO;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EpreuveService {
    private final EpreuveRepositoryImpl epreuveRepositoryImpl;

    public EpreuveService() {
        this.epreuveRepositoryImpl = new EpreuveRepositoryImpl();
    }

    public EpreuveFullDTO getEpreuve(Long idE) {
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        EpreuveFullDTO dto = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepositoryImpl.getById(idE);
/*
            System.out.println("la classe de la propriete tournoi est " + epreuve.getTournoi().getClass().getName());
*/
            System.out.println("L'identifiant du tournoi est " + epreuve.getTournoi().getIdTournoi());
            /*System.out.println("L'epreuve selectionnee se deroule en " + epreuve.getAnnee() + " et il s'agit d'une epreuve "
                    + epreuve.getTournoi().getNomTournoi());*/
           //Chargement des données de tournoi
            Hibernate.initialize(epreuve.getTournoi());
            tx.commit();
            dto = new EpreuveFullDTO();
            dto.setIdEpreuve(epreuve.getIdEpreuve());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());

            TournoiDTO tournoiDTO = new TournoiDTO();
            tournoiDTO.setIdTournoi(epreuve.getTournoi().getIdTournoi());
            tournoiDTO.setNomTournoi(epreuve.getTournoi().getNomTournoi());
            tournoiDTO.setCodeTournoi(epreuve.getTournoi().getCodeTournoi());

            dto.setTournoi(tournoiDTO);
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
        return dto;
    }

    public EpreuveLightDTO getEpreuveSansTournoi(Long idE) {
        Session session = null;
        Transaction tx = null;
        Epreuve epreuve = null;
        EpreuveLightDTO dto = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepositoryImpl.getById(idE);
            tx.commit();
            dto = new EpreuveLightDTO();
            dto.setIdEpreuve(epreuve.getIdEpreuve());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());

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
        return dto;
    }
}
