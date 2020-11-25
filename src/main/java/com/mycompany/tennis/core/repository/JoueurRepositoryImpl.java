package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Joueur;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class JoueurRepositoryImpl {

    public void create(Joueur joueur) {
//        Session session = null;
//        Transaction tx = null;
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        EntityTransaction tx = null;

        try {
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();
//            session.persist(joueur);
            tx = em.getTransaction();
            em.persist(joueur);
            tx.commit(); //declenche l'ajout d'un ou plusieurs elements -> synchronisation de la session et de la BDD

            System.out.println("Joueur créé avec succès");
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

    public void changeSexe(Long idJ, char nouveauSexe) {
        Joueur joueur = null;
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            joueur = em.find(Joueur.class, idJ);
            Joueur joueurPersistent = (Joueur) em.merge(joueur);
            joueur.setSexe(nouveauSexe);
            System.out.println(" Sexe du joueur modifié ");
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

    public void renameJoueur(Long idJ, String nouveauNom) {
        Joueur joueur = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            joueur = em.find(Joueur.class, idJ);
            Joueur joueurPersistent = (Joueur) em.merge(joueur);
            joueur.setNom(nouveauNom);
            System.out.println(" Nom du joueur modifié ");
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

    public void delete(Long idJ) {
        Joueur joueur = getById(idJ);
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        em.remove(joueur);
        System.out.println("Joueur supprimé avec succès");
    }

    public Joueur getById(Long idJ) {

        Joueur joueur = null;
        EntityManager em = null;

        em = EntityManagerHolder.getCurrentEntityManager();
        joueur = em.find(Joueur.class, idJ);
        System.out.println("Joueur lu avec succès");

        return joueur;
    }

    public List<Joueur> getAll() {

        EntityManager em = null;

        em = EntityManagerHolder.getCurrentEntityManager();
        TypedQuery<Joueur> query = em.createQuery("select j from Joueur j ", Joueur.class);
        List<Joueur> joueurs = query.getResultList();
        System.out.println("Liste de joueurs obtenue avec succès");

        return joueurs;
    }

    public List<Joueur> getAllBySexe(char sexe) {

        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        TypedQuery<Joueur> query = em.createNamedQuery("given_sexe", Joueur.class);
        query.setParameter(0, sexe);
        List<Joueur> joueurs = query.getResultList();
        System.out.println("Liste de joueurs obtenue avec succès");

        return joueurs;
    }
}
