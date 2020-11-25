package com.mycompany.tennis.core.services;

import com.mycompany.tennis.core.DTO.*;
import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MatchService {

    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;
    private EpreuveRepositoryImpl epreuveRepository;
    private JoueurRepositoryImpl joueurRepository;
//    private MatchDAO matchDAO;

    public MatchService() {
        this.scoreRepository = new ScoreRepositoryImpl();
        this.matchRepository = new MatchRepositoryImpl();
        this.epreuveRepository = new EpreuveRepositoryImpl();
        this.joueurRepository = new JoueurRepositoryImpl();
//        this.matchDAO = new MatchDAO();
    }

    public void deleteMatch(Long idM) {
        EntityManager em = null;
        EntityTransaction tx = null;
        Match match = null;
        try {
            em= EntityManagerHolder.getCurrentEntityManager();
            tx=em.getTransaction();
            tx.begin();

            matchRepository.delete(idM);

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

    public void enregistrerNouveauMatch(Match match) {
        matchRepository.create(match);
        scoreRepository.create(match.getScore());
//        matchDAO.createMatchScore(match);
    }

    public void createMatch(MatchDTO matchDTO) {
        EntityManager em = null;
        EntityTransaction tx = null;
        Match match = null;
        try {
           em=EntityManagerHolder.getCurrentEntityManager();
           tx=em.getTransaction();
           tx.begin();

            match = new Match();
            match.setEpreuve(epreuveRepository.getById(matchDTO.getEpreuve().getIdEpreuve()));
            match.setVainqueur(joueurRepository.getById(matchDTO.getVainqueur().getIdJ()));
            match.setFinaliste(joueurRepository.getById(matchDTO.getFinaliste().getIdJ()));
            Score score = new Score();
            score.setMatch(match);
            match.setScore(score);
            score.setSet1(matchDTO.getScore().getSet1());
            score.setSet2(matchDTO.getScore().getSet2());
            score.setSet3(matchDTO.getScore().getSet3());
            score.setSet4(matchDTO.getScore().getSet4());
            score.setSet5(matchDTO.getScore().getSet5());

            matchRepository.create(match);

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

    public void tapisVert(Long idM) {
        EntityManager em = null;
        EntityTransaction tx = null;
        Match match = null;
        try {
            em=EntityManagerHolder.getCurrentEntityManager();
            tx=em.getTransaction();
            tx.begin();
            match = matchRepository.getById(idM);

            Joueur ancienVainqueur = match.getVainqueur();
            match.setVainqueur(match.getFinaliste());
            match.setFinaliste(ancienVainqueur);

            match.getScore().setSet1((byte) 0);
            match.getScore().setSet2((byte) 0);
            match.getScore().setSet3((byte) 0);
            match.getScore().setSet4((byte) 0);
            match.getScore().setSet5((byte) 0);


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

    public MatchDTO getMatch(Long idM) {
        EntityManager em = null;
        EntityTransaction tx = null;
        Match match = null;
        MatchDTO dto = null;
        try {
            em=EntityManagerHolder.getCurrentEntityManager();
            tx=em.getTransaction();
            tx.begin();
            match = matchRepository.getById(idM);

            dto = new MatchDTO();

            JoueurDTO finalisteDTO = new JoueurDTO();
            finalisteDTO.setIdJ(match.getFinaliste().getIdJ());
            finalisteDTO.setNom(match.getFinaliste().getNom());
            finalisteDTO.setPrenom(match.getFinaliste().getPrenom());
            finalisteDTO.setSexe(match.getFinaliste().getSexe());

            JoueurDTO vainqueurDTO = new JoueurDTO();
            vainqueurDTO.setIdJ(match.getVainqueur().getIdJ());
            vainqueurDTO.setNom(match.getVainqueur().getNom());
            vainqueurDTO.setPrenom(match.getVainqueur().getPrenom());
            vainqueurDTO.setSexe(match.getVainqueur().getSexe());

            EpreuveFullDTO epreuvedto = new EpreuveFullDTO();
            epreuvedto.setIdEpreuve(match.getEpreuve().getIdEpreuve());
            epreuvedto.setAnnee(match.getEpreuve().getAnnee());
            epreuvedto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());

            TournoiDTO tournoiDTO = new TournoiDTO();
            tournoiDTO.setIdTournoi(match.getEpreuve().getTournoi().getIdTournoi());
            tournoiDTO.setNomTournoi(match.getEpreuve().getTournoi().getNomTournoi());
            tournoiDTO.setCodeTournoi(match.getEpreuve().getTournoi().getCodeTournoi());
            epreuvedto.setTournoi(tournoiDTO);

            ScoreFullDTO scoreDTO = new ScoreFullDTO();
            scoreDTO.setIdScore(match.getScore().getIdScore());
            scoreDTO.setSet1(match.getScore().getSet1());
            scoreDTO.setSet2(match.getScore().getSet2());
            scoreDTO.setSet3(match.getScore().getSet3());
            scoreDTO.setSet4(match.getScore().getSet4());
            scoreDTO.setSet5(match.getScore().getSet5());
            scoreDTO.setMatch(dto);

            dto.setIdMatch(match.getIdMatch());
            dto.setFinaliste(finalisteDTO);
            dto.setVainqueur(vainqueurDTO);
            dto.setEpreuve(epreuvedto);
            dto.setScore(scoreDTO);

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
        return dto;
    }
}
