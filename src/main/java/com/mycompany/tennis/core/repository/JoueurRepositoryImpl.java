package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
//            joueur = session.get(Joueur.class, idJ);
            Joueur joueurPersistent = (Joueur) session.merge(joueur);
            joueur.setSexe(nouveauSexe);
            System.out.println(" Sexe du joueur modifié ");
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
    }

    public void renameJoueur(Long idJ, String nouveauNom) {
        Joueur joueur = null;
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
//            joueur = session.get(Joueur.class, idJ);
            Joueur joueurPersistent = (Joueur) session.merge(joueur);
            joueur.setNom(nouveauNom);
            System.out.println(" Nom du joueur modifié ");
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
    }

    public void delete(Long idJ) {
        Joueur joueur = getById(idJ);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(joueur);
        System.out.println("Joueur supprimé avec succès");
    }

    public Joueur getById(Long idJ) {

        Joueur joueur = null;
        Session session = null;

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        joueur = session.get(Joueur.class, idJ);
        System.out.println("Joueur lu avec succès");

        return joueur;
    }

    public List<Joueur> getAll() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Joueur> query = session.createQuery("select j from Joueur j ", Joueur.class);
        List<Joueur> joueurs =query.getResultList();
        System.out.println("Liste de joueurs obtenue avec succès");

        return joueurs;
    }
    public List<Joueur> getAllBySexe(char sexe) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Joueur> query = session.createNamedQuery("given_sexe", Joueur.class);
        query.setParameter(0, sexe);
        List<Joueur> joueurs =query.getResultList();
        System.out.println("Liste de joueurs obtenue avec succès");

        return joueurs;
    }
}
