package com.mycompany.tennis.core.services;

import com.mycompany.tennis.core.DTO.EpreuveFullDTO;
import com.mycompany.tennis.core.DTO.EpreuveLightDTO;
import com.mycompany.tennis.core.DTO.JoueurDTO;
import com.mycompany.tennis.core.DTO.TournoiDTO;
import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EpreuveService {
    private final EpreuveRepositoryImpl epreuveRepositoryImpl;

    public EpreuveService() {
        this.epreuveRepositoryImpl = new EpreuveRepositoryImpl();
    }

    public EpreuveFullDTO getEpreuveDetaillee(Long idE) {
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

            dto.setParticipants(new HashSet<>());
            for (Joueur joueur : epreuve.getParticipants()) {
                final JoueurDTO joueurDTO = new JoueurDTO();
                joueurDTO.setIdJ(joueur.getIdJ());
                joueurDTO.setPrenom(joueur.getPrenom());
                joueurDTO.setNom(joueur.getNom());
                joueurDTO.setSexe(joueur.getSexe());
                dto.getParticipants().add(joueurDTO);
            }
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

    public List<EpreuveFullDTO> getListeEpreuve(String codeTournoi) {
        EntityManager em = null;
        EntityTransaction tx = null;
        List<EpreuveFullDTO> epreuvesDTO = new ArrayList<>();
        try {
            em= new EntityManagerHolder().getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            List<Epreuve> epreuveList = epreuveRepositoryImpl.getAll(codeTournoi);

            for (Epreuve epreuve : epreuveList) {
                final EpreuveFullDTO epreuveDTO = new EpreuveFullDTO();
                epreuveDTO.setIdEpreuve(epreuve.getIdEpreuve());
                epreuveDTO.setAnnee(epreuve.getAnnee());
                epreuveDTO.setTypeEpreuve(epreuve.getTypeEpreuve());

                TournoiDTO tournoiDTO = new TournoiDTO();
                tournoiDTO.setIdTournoi(epreuve.getTournoi().getIdTournoi());
                tournoiDTO.setNomTournoi(epreuve.getTournoi().getNomTournoi());
                tournoiDTO.setCodeTournoi(epreuve.getTournoi().getCodeTournoi());
                epreuveDTO.setTournoi(tournoiDTO);

                epreuvesDTO.add(epreuveDTO);
            }

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
        return epreuvesDTO;
    }

}
