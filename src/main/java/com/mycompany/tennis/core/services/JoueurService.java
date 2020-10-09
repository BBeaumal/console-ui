package com.mycompany.tennis.core.services;

import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.core.entity.Joueur;

public class  JoueurService {
    private final JoueurRepositoryImpl joueurRepository;

    public JoueurService() {
        this.joueurRepository = new JoueurRepositoryImpl();
    }

    public void createJoueur(Joueur joueur) {
        joueurRepository.create(joueur);
    }

    public Joueur getJoueur(Long idJ) {
        return joueurRepository.getById(idJ);
    }

}
