package com.mycompany.tennis.core.services;

import com.mycompany.tennis.core.DTO.JoueurDTO;
import com.mycompany.tennis.core.DTO.MatchDTO;
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

            JoueurDTO finalisteDTO = new JoueurDTO();
            finalisteDTO.setIdJ(match.getFinaliste().getIdJ());
            finalisteDTO.setNom(match.getFinaliste().getNom());
            finalisteDTO.setPrenom(match.getFinaliste().getPrenom());
            finalisteDTO.setSexe(match.getFinaliste().getSexe());
            dto = new MatchDTO();

            JoueurDTO vainqueurDTO = new JoueurDTO();
            vainqueurDTO.setIdJ(match.getVainqueur().getIdJ());
            vainqueurDTO.setNom(match.getVainqueur().getNom());
            vainqueurDTO.setPrenom(match.getVainqueur().getPrenom());
            vainqueurDTO.setSexe(match.getVainqueur().getSexe());
            dto = new MatchDTO();

            dto.setIdMatch(match.getIdMatch());
            dto.setFinaliste(finalisteDTO);
            dto.setVainqueur(vainqueurDTO);

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
