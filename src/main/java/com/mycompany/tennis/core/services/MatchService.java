package com.mycompany.tennis.core.services;

import com.mycompany.tennis.core.DTO.*;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {

    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;
//    private MatchDAO matchDAO;

    public MatchService() {
        this.scoreRepository = new ScoreRepositoryImpl();
        this.matchRepository = new MatchRepositoryImpl();
//        this.matchDAO = new MatchDAO();
    }

    public void enregistrerNouveauMatch(Match match) {
        matchRepository.create(match);
        scoreRepository.create(match.getScore());
//        matchDAO.createMatchScore(match);
    }

    public MatchDTO getMatch(Long idM) {
        Session session = null;
        Transaction tx = null;
        Match match = null;
        MatchDTO dto = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
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
            if (session != null) {
                session.close();
            }
        }
        return dto;
    }
}
