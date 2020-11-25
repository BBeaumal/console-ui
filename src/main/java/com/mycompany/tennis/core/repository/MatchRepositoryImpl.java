package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Match;

import javax.persistence.EntityManager;

public class MatchRepositoryImpl {

    public void create(Match match) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        em.persist(match);
        System.out.println("Match ajouté");
    }

    public void delete(Long idMatch) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        Match match = em.find(Match.class, idMatch);
        em.remove(match);
        System.out.println("Match supprimé avec succès");
    }


    public Match getById(Long idMatch) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        Match match = em.find(Match.class, idMatch);
        System.out.println("Match lu avec succès");
        return match;
    }

}
