package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Score;

import javax.persistence.EntityManager;

public class ScoreRepositoryImpl {

    public void create(Score score) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        em.persist(score);
        System.out.println("Score ajouté");
    }

    public void delete(Long idS) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        Score score = em.find(Score.class, idS);
        em.remove(score);
        System.out.println("Score supprimé");
    }

    public Score getById(Long idScore) {

        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        Score score = em.find(Score.class, idScore);
        System.out.println("Score lu avec succès");
        return score;
    }

}
