package com.mycompany.tennis.core.services;

import com.mycompany.tennis.core.DTO.JoueurDTO;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class JoueurService {
    private final JoueurRepositoryImpl joueurRepository;

    public JoueurService() {
        this.joueurRepository = new JoueurRepositoryImpl();
    }

    public void createJoueur(Joueur joueur) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueurRepository.create(joueur);
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

    public void deleteJoueur(Long idJ) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueurRepository.delete(idJ);
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

    public List<JoueurDTO> getListeJoueurs() {
        Session session = null;
        Transaction tx = null;
        List<JoueurDTO> joueursDTO = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
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
            if (session != null) {
                session.close();
            }
        }
        return joueursDTO;
    }

    public List<JoueurDTO> getListeParamJoueurs(char sexe) {
        Session session = null;
        Transaction tx = null;
        List<JoueurDTO> joueursDTO = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
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
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return joueursDTO;
    }


    public Joueur getJoueur(Long idJ) {
        Session session = null;
        Transaction tx = null;
        Joueur joueur = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueur = joueurRepository.getById(idJ);
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
        return joueur;
    }

    public void renameJoueur(Long idJ, String nouveauNom) {
//        joueurRepository.renameJoueur(idJ, nouveauNom);
        Joueur joueur = getJoueur(idJ);

        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            joueur.setNom(nouveauNom);
            Joueur joueur2 = (Joueur) session.merge(joueur);
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

    public void changeSexe(Long idJ, Character newSexe) {
//        joueurRepository.renameJoueur(idJ, nouveauNom);
        Joueur joueur = getJoueur(idJ);

        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            joueur.setSexe(newSexe);
            Joueur joueur2 = (Joueur) session.merge(joueur);
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
}




