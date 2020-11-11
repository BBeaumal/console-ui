package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EpreuveRepositoryImpl {


    public Epreuve getById(Long idEpreuve) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Epreuve epreuve = session.get(Epreuve.class, idEpreuve);
        System.out.println("Epreuve lue avec succès");
        return epreuve;
    }

    public List<Epreuve> getAll(String codeTournoi) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Epreuve> query = session.createQuery("select e from Epreuve e where e.tournoi.codeTournoi=?0", Epreuve.class);
        query.setParameter(0, codeTournoi);
        List<Epreuve> epreuves = query.getResultList();
        System.out.println("Liste d'épreuves obtenue avec succès");

        return epreuves;
    }


}
