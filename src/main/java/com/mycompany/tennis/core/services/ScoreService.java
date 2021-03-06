package com.mycompany.tennis.core.services;

import com.mycompany.tennis.core.DTO.*;
import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ScoreService {

    private final ScoreRepositoryImpl scoreRepositoryImpl;

    public ScoreService() {
        this.scoreRepositoryImpl = new ScoreRepositoryImpl();
    }

    public void deleteScore(Long idS) {
        EntityManager em = null;
        EntityTransaction tx = null;

        Score score = null;
        try {
            em= EntityManagerHolder.getCurrentEntityManager();
            tx=em.getTransaction();
            tx.begin();

            scoreRepositoryImpl.delete(idS);

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
    }

    public Score getScore(Long idS) {
        EntityManager em = null;
        EntityTransaction tx = null;

        Score score = null;
        ScoreFullDTO dto = null;
        try {
            em=EntityManagerHolder.getCurrentEntityManager();
            tx=em.getTransaction();
            tx.begin();
            score = scoreRepositoryImpl.getById(idS);

            dto = new ScoreFullDTO();

            JoueurDTO finalisteDTO = new JoueurDTO();
            finalisteDTO.setIdJ(score.getMatch().getFinaliste().getIdJ());
            finalisteDTO.setNom(score.getMatch().getFinaliste().getNom());
            finalisteDTO.setPrenom(score.getMatch().getFinaliste().getPrenom());
            finalisteDTO.setSexe(score.getMatch().getFinaliste().getSexe());

            JoueurDTO vainqueurDTO = new JoueurDTO();
            vainqueurDTO.setIdJ(score.getMatch().getVainqueur().getIdJ());
            vainqueurDTO.setNom(score.getMatch().getVainqueur().getNom());
            vainqueurDTO.setPrenom(score.getMatch().getVainqueur().getPrenom());
            vainqueurDTO.setSexe(score.getMatch().getVainqueur().getSexe());

            EpreuveFullDTO epreuvedto = new EpreuveFullDTO();
            epreuvedto.setIdEpreuve(score.getMatch().getEpreuve().getIdEpreuve());
            epreuvedto.setAnnee(score.getMatch().getEpreuve().getAnnee());
            epreuvedto.setTypeEpreuve(score.getMatch().getEpreuve().getTypeEpreuve());

            TournoiDTO tournoiDTO = new TournoiDTO();
            tournoiDTO.setIdTournoi(score.getMatch().getEpreuve().getTournoi().getIdTournoi());
            tournoiDTO.setNomTournoi(score.getMatch().getEpreuve().getTournoi().getNomTournoi());
            tournoiDTO.setCodeTournoi(score.getMatch().getEpreuve().getTournoi().getCodeTournoi());
            epreuvedto.setTournoi(tournoiDTO);

            MatchDTO matchDTO = new MatchDTO();
            matchDTO.setEpreuve(epreuvedto);
            matchDTO.setVainqueur(vainqueurDTO);
            matchDTO.setFinaliste(finalisteDTO);
            matchDTO.setIdMatch(score.getMatch().getIdMatch());

            dto.setIdScore(score.getIdScore());
            dto.setMatch(matchDTO);
            dto.setSet1(score.getSet1());
            dto.setSet2(score.getSet2());
            dto.setSet3(score.getSet3());
            dto.setSet4(score.getSet4());
            dto.setSet5(score.getSet5());


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
        return score;
    }
}
