package com.mycompany.tennis.core.services;

import com.mycompany.tennis.core.DTO.JoueurDTO;
import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class JoueurService {
    private final JoueurRepositoryImpl joueurRepository;

    public JoueurService() {
        this.joueurRepository = new JoueurRepositoryImpl();
    }

    public void createJoueur(Joueur joueur) {
//        Session session = null;
//        Transaction tx = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
//            session = HibernateUtil.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            joueurRepository.create(joueur);
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

    public void deleteJoueur(Long idJ) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            joueurRepository.delete(idJ);
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

    public List<JoueurDTO> getListeJoueurs() {
        EntityManager em = null;
        EntityTransaction tx = null;
        List<JoueurDTO> joueursDTO = new ArrayList<>();
        try {
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            List<Joueur> joueurList = joueurRepository.getAll();

            for (Joueur joueur : joueurList) {
                JoueurDTO joueurDTO = new JoueurDTO();
                joueurDTO.setIdJ(joueur.getIdJ());
                joueurDTO.setSexe(joueur.getSexe());
                joueurDTO.setNom(joueur.getNom());
                joueurDTO.setPrenom(joueur.getPrenom());

                joueursDTO.add(joueurDTO);
            }

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
        return joueursDTO;
    }

    public List<JoueurDTO> getListeParamJoueurs(char sexe) {
        EntityManager em = null;
        EntityTransaction tx = null;
        List<JoueurDTO> joueursDTO = new ArrayList<>();
        try {
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            List<Joueur> joueurList = joueurRepository.getAllBySexe(sexe);

            for (Joueur joueur : joueurList) {
                JoueurDTO joueurDTO = new JoueurDTO();
                joueurDTO.setIdJ(joueur.getIdJ());
                joueurDTO.setSexe(joueur.getSexe());
                joueurDTO.setNom(joueur.getNom());
                joueurDTO.setPrenom(joueur.getPrenom());

                joueursDTO.add(joueurDTO);
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return joueursDTO;
    }


    public Joueur getJoueur(Long idJ) {
        EntityManager em = null;
        EntityTransaction tx = null;
        Joueur joueur = null;
        try {
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            joueur = joueurRepository.getById(idJ);
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
        return joueur;
    }

    public void renameJoueur(Long idJ, String nouveauNom) {

        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();
            joueurRepository.renameJoueur(idJ, nouveauNom);

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

    public void changeSexe(Long idJ, Character newSexe) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = EntityManagerHolder.getCurrentEntityManager();
            tx = em.getTransaction();
            tx.begin();

            joueurRepository.changeSexe(idJ, newSexe);
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
}




