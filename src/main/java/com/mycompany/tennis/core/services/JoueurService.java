package com.mycompany.tennis.core.services;

import com.mycompany.tennis.core.DAO.JoueurDAOImpl;
import com.mycompany.tennis.core.entity.Joueur;

public class JoueurService {
    private JoueurDAOImpl joueurDAOImpl;

    public JoueurService() {
        this.joueurDAOImpl = new JoueurDAOImpl();
    }

    public void createJoueur(Joueur joueur) {
        joueurDAOImpl.create(joueur);
    }

    public Joueur getJoueur(Long idJ) {
        Joueur joueur = joueurDAOImpl.getById(idJ);
        return joueur;
    }

}
