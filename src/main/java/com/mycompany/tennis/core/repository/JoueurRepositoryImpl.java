package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Joueur;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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
            //declenche l'ajout d'un ou plusieurs elements -> synchronisation de la session et de la BDD

            System.out.println("Joueur créé avec succès");
        } catch (Exception e) {

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

        } catch (Exception e) {

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

        } catch (Exception e) {

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
        List<Joueur> joueurs = new ArrayList<>();
        EntityManager em = null;
        EntityTransaction tx = null;
//        try {
        em = EntityManagerHolder.getCurrentEntityManager();
        tx = em.getTransaction();
//            tx.begin();

        TypedQuery<Joueur> query = em.createQuery("select j from Joueur j ", Joueur.class);
        joueurs = query.getResultList();
        System.out.println("Liste de joueurs obtenue avec succès");

//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
        return joueurs;
    }

    public List<Joueur> getAllBySexe(char sexe) {
        List<Joueur> joueurs = new ArrayList<>();

//        try {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        EntityTransaction tx = em.getTransaction();
//            tx.begin();
        TypedQuery<Joueur> query = em.createNamedQuery("given_sexe", Joueur.class);
        query.setParameter(0, sexe);
        joueurs = query.getResultList();
        System.out.println("Liste de joueurs obtenue avec succès");

//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
        return joueurs;
    }
}
