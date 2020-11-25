package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Epreuve;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EpreuveRepositoryImpl {


    public Epreuve getById(Long idEpreuve) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        Epreuve epreuve = em.find(Epreuve.class, idEpreuve);
        System.out.println("Epreuve lue avec succès");
        return epreuve;
    }

    public List<Epreuve> getAll(String codeTournoi) {

        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        TypedQuery<Epreuve> query = em.createQuery("select e from Epreuve e join fetch e.tournoi " +
                "where e.tournoi.codeTournoi=?0", Epreuve.class);
        query.setParameter(0, codeTournoi);
        List<Epreuve> epreuves = query.getResultList();
        System.out.println("Liste d'épreuves obtenue avec succès");

        return epreuves;
    }


}
