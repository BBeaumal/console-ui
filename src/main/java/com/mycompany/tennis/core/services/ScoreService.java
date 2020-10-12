package com.mycompany.tennis.core.services;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {

    private final ScoreRepositoryImpl scoreRepositoryImpl;

    public ScoreService() {
        this.scoreRepositoryImpl = new ScoreRepositoryImpl();
    }
    public Score getScore(Long idS) {
        Session session = null;
        Transaction tx = null;
        Score score = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            score = scoreRepositoryImpl.getById(idS);
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
        return score;
    }
}
